/**
 * Created by max on 08/04/2017.
 */
public class MyNode <X> implements Node {  //Nodo estremamente generico
    private X value;

    public MyNode(X input) { this.value = input; }
    public X getValue() { return value; }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MyNode && value.equals(((MyNode) obj).getValue());
    }
}
