import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
class Tree {
    public int data;
    public final Tree leftChild;
    public final Tree rightChild;
    public Character ch;

    //constructor
    private Tree(int d, Character c,  Tree left, Tree right) {
        data = d;
        leftChild = left;
        rightChild = right;
        ch = c;
    }

    static Tree leaf(char c, int f) {
        return new Tree(f, c, null, null);
    }

    static Tree combine(Tree a, Tree b) {
        return new Tree(a.data + b.data, null, a, b);
    }

    public boolean isLeaf() {return leftChild == null && rightChild == null;}




}class MyComparator implements Comparator<Tree> {
    public int compare(Tree x, Tree y)
    {
 
        return x.data - y.data;
    }
}
public class Huff {

		
		public static void printcodes(Tree root, String codeword, Map<Character, String>entre) {
			if(root.isLeaf()) { // if its a leaf, finished with this side, add to the map
			 entre.put(root.ch, codeword); //put the character and string into the map
			 return; //return
				
			}
			printcodes(root.rightChild, codeword + "1", entre); // return this function with the next right node being root of the tree
			printcodes(root.leftChild, codeword + "0", entre); // return this function with the next left node being root of the tree
			// this increments the string by adding a 1 or 0 if it chooses to go right or left
		}

	    public Map<Character, String> compute_coding(Map<Character, Integer> character_counts) {
	    	String[] codeword;
			PriorityQueue<Tree> Que = new PriorityQueue<Tree>(5, new MyComparator()); // create a new priority queue
	    		for (Map.Entry<Character,Integer> entry : character_counts.entrySet()) {// for each entry of the map
	              Tree c = Tree.leaf(entry.getKey(), entry.getValue());  // create a new tree with the character from the key and the data from the value of the map
	              Que.add(c); // add it to the priority queue created
	    }
	    		while(Que.size()!= 1)  { //until there is only one node in the priority queue
	    			Tree x = Que.poll(); //theoretically take the smallest node in the queue
	    			Tree y = Que.poll(); //taking the second smallest node in queue
	    			
	    			Tree z = Tree.combine(x, y); //creating another node with leftchild and rightchild with the two above
	    			z.ch = '-'; //setting the character to -
	    			Que.add(z); // adding the node back to the queue
	    			
	    		}
	    		Tree root = Que.poll(); //get the only node that should be in the queue
	    		Map<Character, String> codes = new HashMap<Character, String>(); /// create another hashmap with string so we can print out the 0's and 1's
	    		printcodes(root, "",  codes);// calling a function to create a string for each node to the given hashmap
	    		return codes; //returning the hashmap to the original problem.
	    			
	    		
	    		}
	            
	    		
	    		
	    
	    


	    public static void main(String[] args) {
	        Map<Character, Integer> freqs = new HashMap<Character, Integer>(){{
	            put('a', 15);
	            put('e', 7);
	            put('i', 30);
	            put('o', 120);
	            put('u', 11);
	        }};
	        Map<Character, String> codes = new Huff().compute_coding(freqs);
	        for (Character ch : freqs.keySet()) {
	            String code_word = codes.get(ch);
	            System.out.println(ch + ": " + code_word);
	        }
	    }
	}

