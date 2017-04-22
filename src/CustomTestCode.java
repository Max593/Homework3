import Exceptions.NoSuchNodeException;

import java.util.List;
import java.util.Map;

/**
 * Created by max on 09/04/2017.
 */
public class CustomTestCode {
    public static void main(String[] args) {
        MyNetwork<MyNode<String>> t = new MyNetwork<>();
        MyNode<String> A = new MyNode<>("A");
        MyNode<String> B = new MyNode<>("B");
        MyNode<String> C = new MyNode<>("C");
        MyNode<String> D = new MyNode<>("D");
        MyNode<String> E = new MyNode<>("E");
        MyNode<String> F = new MyNode<>("F");
        MyNode<String> G = new MyNode<>("G");
        MyNode<String> H = new MyNode<>("H");
        MyNode<String> I = new MyNode<>("I");
        MyNode<String> L = new MyNode<>("L");
        t.addNode(A);
        t.addNode(B);
        t.addNode(C);
        t.addNode(D);
        t.addNode(E);
        t.addNode(F);
        t.addNode(G);
        t.addNode(H);
        t.addNode(I);
        t.addNode(L);
        try {
            t.addEdge(A, C);
            t.addEdge(A, D);
            t.addEdge(D, H);
            t.addEdge(H, I);
            t.addEdge(H, L);
            t.addEdge(I, D);
            t.addEdge(C, E);
            t.addEdge(E, D);
            t.addEdge(B, E);
            t.addEdge(E, G);
            t.addEdge(E, F);
            t.addEdge(G, H);
            t.addEdge(L, E);
            t.addEdge(F, A);
        } catch (NoSuchNodeException ignore) {}

        try {
            t.setSource(A);
            t.setTarget(I);
        } catch (NoSuchNodeException ignore) { }
/*
        for(MyNode x : t.getV()) { System.out.print(x.getValue()); }
        System.out.print("\n");
        for(Map.Entry<MyNode<String>, List<MyNode<String>>> e : t.getE().entrySet()) {
            for(MyNode a : e.getValue()) { System.out.println(e.getKey().getValue()+" : "+a.getValue()); }
        }
*/
        try {
            t.shortestPath();
        } catch (Exceptions.NoSuchPathException ignore) {  }
    }
}
