import Exceptions.NoSuchNodeException;
import Exceptions.NoSuchPathException;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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
    public void addNode(T v) {  //Aggiunge il nodo solo se non è presente un nodo uguale o se stesso
        boolean test = true;
        for(T n : V) { if(n.equals(v)) { test = false; } }
        if(test && !V.contains(v)) { V.add(v); }
    }

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

        for(int i = 0; i < temp.size(); i++) {  //NON posso usare il foreach per motivi di lettura (la lista si incrementa col tempo)
            List<T> l = temp.get(i);
            while(true) {  //Il ciclo viene interrotto manualmente in situazioni limite
                T elem = l.get(l.size()-1);  //Ultimo elemento della lista
                if(edg(elem) == null) { break; }  //Se non è presente un arco il ciclo si interrompe
                else if(edg(elem).size() == 1 && !l.contains(edg(elem).get(0))) {  //Casi con un arco solo
                    l.add(edg(elem).get(0));
                    if(edg(elem).get(0).equals(target)) { break; }  //Se l'elemento corrisponde al target si interrompe il ciclo
                }
                else if(edg(elem).size() == 1 && l.contains(edg(elem).get(0))) { break; }  //Singolo arco già presente nella lista, interruzione

                else {  //Casi con più di un arco
                    for(T el : edg(elem).subList(1, edg(elem).size())) {  //Salto il primo elemento nell'esecuzione del ciclo per motivi di lettura
                        if(!l.contains(el)) {  //Se l'elemento non è contenuto nella lista crea una nuova entry in temp da analizzare più tardi
                            List<T> tList = new ArrayList<>(l);  //Copia della lista attuale
                            tList.add(el);  //Aggiunta elemento da analizzare in seguito
                            temp.add(tList);
                        }
                    }
                    if(!l.contains(edg(elem).get(0))) { l.add(edg(elem).get(0)); }  //Controllo se posso inserire il primo elemento
                    else { break; }  //Se non è possibile interrompo il ciclo, eventuali archi verranno analizzati in seguito
                }
            }
        }

        for(List<T> el : temp) {  //Analizzo temp per trovare la lista (che termina con target) più corta
            T last = el.get(el.size()-1); //Ultimo elemento della lista in analisi
            if(res.size() == 0  && last.equals(target)) { res = el; }  //Primissima iterazione
            else if(last.equals(target) && res.size() > el.size()) { res = el; }
        }
        if(res.size() == 0) { throw new NoSuchPathException(); }  //Se res NON si è riempito allora non ci sono percorsi possibili
/*
        //Print per testare l'esecuzione di shortestPath()
        for(List<T> l : temp) {
            System.out.print("\n(");
            for(T t : l) { System.out.print(((Node)t).getValue()+", "); }
            System.out.print(")\n");
        }
*/
        return res;
    }
    
    private List<T> edg(T t) { return E.get(t); }  //Ritorna la lista degli archi associati ad un nodo (utilizzo privato, non necessita eccezioni)

}
