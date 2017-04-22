import Exceptions.NoSuchNodeException;
import Exceptions.NoSuchPathException;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Created by max on 08/04/2017.
 */
public class MyNetwork <T> implements Network <T> {
    private Set<T> V = new HashSet<>();  //Insieme di vertici, no ripetizioni
    private Map<T, List<T>> E = new HashMap<>();  //Archi (Nodo -> Lista di nodi associati)
    private T source;
    private T target;

    public MyNetwork() { }

    @Override
    public T source() { return source; }

    @Override
    public T target() { return target; }

    @Override
    public void setSource(T newsource) throws NoSuchNodeException {
        if(!V.contains(newsource)) { throw new NoSuchNodeException(); }
        this.source = newsource;
    }

    @Override
    public void setTarget(T newtarget) throws NoSuchNodeException {
        if(!V.contains(newtarget)) { throw new NoSuchNodeException(); }
        this.target = newtarget;
    }

    //Per testare il network
    public Set<T> getV() { return V; }
    public Map<T, List<T>> getE() { return E; }

    @Override
    public void addNode(T v) { V.add(v); }

    @Override
    public void addEdge(T p, T a) throws NoSuchNodeException {
        if(!V.contains(p) || !V.contains(a)) { throw new NoSuchNodeException(); }
        List<T> current = E.computeIfAbsent(p, k -> new ArrayList<>());
        current.add(a);
    }

    @Override
    public List<T> shortestPath() throws NoSuchPathException {
        if(source == null || target == null) { throw new NoSuchPathException(); }

        List<T> res = new ArrayList<>();  //Risultato
        CopyOnWriteArrayList<List<T>> temp = new CopyOnWriteArrayList<>();  //"Lista" di liste da analizzare (fork iniziale) DEVE permettere accessi multipli
        for(T t : E.get(source)) { temp.add(new ArrayList<>(Arrays.asList(source, t))); }  //Aggiunge il primo fork

        Consumer<List<T>> iter = (l -> {  //All'interno di un elemento (l) di temp
            while(true) {  //Il ciclo viene interrotto manualmente in situazioni limite
                T elem = l.get(l.size()-1);  //Ultimo elemento della lista
                if(edg(elem) == null) { break; }
                else if(edg(elem).size() == 1 && !l.contains(edg(elem).get(0))) {  //Casi con un arco solo
                    l.add(edg(elem).get(0));
                    if(edg(elem).get(0).equals(target)) { break; }  //Se l'elemento corrisponde al target interrompe il ciclo
                }
                else if(edg(elem).size() == 1 && l.contains(edg(elem).get(0))) { break; }  //Singolo arco già presente nella lista

                else {  //Casi con più di un arco
                    for(T el : edg(elem).subList(1, edg(elem).size())) {  //Salto il primo elemento nell'esecuzione del ciclo
                        if(!l.contains(el)) {  //Se l'elemento non è contenuto nella lista crea una nuova entry in temp da analizzare più tardi
                            List<T> tList = new ArrayList<>(l);
                            tList.add(el);
                            temp.add(tList);
                        }
                    }
                    if(!l.contains(edg(elem).get(0))) { l.add(edg(elem).get(0)); }  //Controllo se posso inserire il primo elemento
                    else { break; }
                }

            }
        });

        for(int i = 0; i < temp.size(); i++) { iter.accept(temp.get(i)); }
        for(List<T> el : temp) {
            T last = el.get(el.size()-1);
            if(res.size() == 0  && last.equals(target)) { res = el; }  //Primissima iterazione
            else if(last.equals(target) && res.size() > el.size()) { res = el; }
        }
/*
        //Print per testare
        for(List<T> l : temp) {
            System.out.print("\n(");
            for(T t : l) { System.out.print(((Node)t).getValue()+", "); }
            System.out.print(")\n");
        }
*/
        return res;
    }
    
    private List<T> edg(T t) { return E.get(t); }  //Ritorna tutti gli archi associati ad un nodo

}
