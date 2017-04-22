import java.util.*;

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
        
        List<List<T>> temp = new ArrayList<>();  //Lista di liste da analizzare
        for(Map.Entry<T, List<T>> e : E.entrySet()) {
            if(e.getKey().equals(source)) {
                temp.add(e.getValue());
            }
        }

        return null;
    }
    
    private List<T> edg(T t) { return E.get(t); }  //Ritorna tutti gli archi associati ad un nodo

}
