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
        if(source == null || target == null) { throw new NoSuchPathException(); }  //Manca una condizione da inserire dopo
        return null;
    }
}
