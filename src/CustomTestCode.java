/**
 * Created by max on 09/04/2017.
 */
public class CustomTestCode {
    public static void main(String[] args) {
        MyNetwork t = new MyNetwork();
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
        } catch (NoSuchNodeException ignore) {}
    }
}
