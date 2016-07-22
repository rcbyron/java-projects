import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		System.out.println("rules".hashCode());
		List<? extends Double> l4 = new LinkedList<Double>();
		Double d1 = 3.0;
		((LinkedList<Double>)l4).add(d1);
		((LinkedList<Double>)l4).add(4.0);
		for (Double d : l4)
			System.out.println(d);
		
	}

}
