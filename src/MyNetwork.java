import java.util.*;

/**
 * Created by maxmo on 08/04/2017.
 */
public class MyNetwork <T> implements Network <T> {
    private Set<T> V = new HashSet<>();  //Vertici
    private Map<T, T> E = new HashMap<>();  //Archi
    private T source;
    private T target;

    public MyNetwork() { }

    @Override
    public T source() { return source; }

    @Override
    public T target() { return target; }

    @Override
    public void setSource(T newsource) throws NoSuchNodeException {
        for(T v : V) {  //Trattandosi di un Set non esiste un primo elemento, dunque inizio l'iterazione e la interrompo subito
            if(v.getClass() != newsource.getClass()) { throw new NoSuchNodeException(); }
            break;
        }
        this.source = newsource;
    }

    @Override
    public void setTarget(T newtarget) throws NoSuchNodeException {
        for(T v : V) {  //Trattandosi di un Set non esiste un primo elemento, dunque inizio l'iterazione e la interrompo subito
            if(v.getClass() != newtarget.getClass()) { throw new NoSuchNodeException(); }
            break;
        }
        this.target = newtarget;
    }

    @Override
    public void addNode(T v) {

    }

    @Override
    public void addEdge(T p, T a) throws NoSuchNodeException {

    }

    @Override
    public List<T> shortestPath() throws NoSuchPathException {
        return null;
    }
}
