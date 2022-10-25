package SetOperations;
import java.util.ArrayList;

//Set's programming assignment
public class SetOperations {
	
	public void runProgram() {
		// Data Type: Integer
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		for (int i = 1; i <= 13; i++) {
			list1.add(i);
			if(i % 2 != 0) {
				list2.add(i);
			}
		}
		
		System.out.println("List 1: " + list1);
		System.out.println("List 2: " + list2);
		System.out.println("Union of List 1 and List 2: " + unionOf(list1, list2));
		System.out.println("Intersection of List 1 and List 2: " + intersectionOf(list1, list2));
		// Values excluded in List 2 that are in List 1
		System.out.println("Complement of List 1 and List 2: " + complementOf(list1, list2));
	}
	
	/*
	 * union two array lists
	 * union - basically put two array lists in one array list
	 * loop through al1, add
	 * loop through al2, add if not in al1
	 */
	
	public ArrayList<Integer> unionOf (ArrayList<Integer> al1, ArrayList<Integer> al2) {
		ArrayList<Integer> unionList = new ArrayList<Integer>();
		
		for (int i = 0; i < al1.size(); i++) {
			if (!(unionList.contains(al1.get(i)))) {
				unionList.add(al1.get(i));
			}
			for (int j = 0; j < al2.size(); j++) {
				if (!(unionList.contains(al2.get(j)))) {
					unionList.add(al2.get(j));
				}
			}
		}
		
		return unionList;
	}
	
	/*
	 * intersect two array lists
	 * intersection - put the similar array list into 1 array list
	 * loop through al1 and loop through al2, if both = then add 
	 */
	
	public ArrayList<Integer> intersectionOf (ArrayList<Integer> al1, ArrayList<Integer> al2) {
		ArrayList<Integer> intersectList = new ArrayList<Integer>();
		
		for (int i = 0; i < al1.size(); i++) {
			for (int j = 0; j < al2.size(); j++) {
				if (al1.get(i) == al2.get(j)) {
					intersectList.add(al2.get(j));
				}
			}
		}
		
		return intersectList;
	}
	
	/*
	 * complement of an array list
	 * method accepts 2 parameters - array list of all values, accept A subset, solve for complement
	 * subset to check if it exists in all values, if it doesn't
	 * check if it's already in complementList (if not then add)
	 * want evens
	 */
	
	public ArrayList<Integer> complementOf (ArrayList<Integer> allValues, ArrayList<Integer> subset) {
		ArrayList<Integer> complementList = new ArrayList<Integer>();
		
		for (int element: allValues) {
			if (!(subset.contains(element))) {
				complementList.add(element);
			}
		}
		
		return complementList;
	}
	
}
