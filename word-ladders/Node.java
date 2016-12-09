
import java.util.Collection;
//import java.util.Set;

public class Node {
	public boolean visited = false;
	public String name = null;
	public Collection<Node> neighbors;
	
	public Node() {}
	
	public Node(String n){
		name = n;
	}
	
	public Node(String n , Boolean v) {
		name = n;
		visited = v;
	}
}