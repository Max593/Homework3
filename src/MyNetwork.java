import java.util.*;

/**
 * Created by max on 08/04/2017.
 */
public class MyNetwork <T> implements Network <T> {
    private Set<T> V = new HashSet<>();  //Vertici
    private MyMap<T> E = new MyMap<>();  //Archi
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
    public MyMap<T> getE() { return E; }

    @Override
    public void addNode(T v) { V.add(v); }

    @Override
    public void addEdge(T p, T a) throws NoSuchNodeException {
        if(!V.contains(p) || !V.contains(a)) { throw new NoSuchNodeException(); }
        E.add(p, a);
    }

    @Override
    public List<T> shortestPath() throws NoSuchPathException {
        if(source == null || target == null) { throw new NoSuchPathException(); }
        
        List<List<T>> temp = new ArrayList<>();
        for(Map.Entry<T, List<T>> e : E.entrySet()) {
            if(e.getKey().equals(source)) {
                temp.add(e.getValue());
            }
        }
        
        int count = temp.size();
        for(int i = 0; i < count; i++) {
            List<T> l = temp.get(i);
            T el = l.get(l.size()-1);  //Ultimo elemento.
            if(edg(el).size() == 1) {
                l.addAll(edg(el));
            }
            else {
                //Aggiunge un nuovo elemento a temp su cui dovrà iterare
            }
        }

        return null;
    }
    
    private List<T> edg(T t) {
        for(Map.Entry<T, List<T>> e : E.entrySet()) {
            if(e.getKey().equals(t)) { return e.getValue(); }
        }
        return null;
    }

}
