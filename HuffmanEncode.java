import java.io.*;
import java.util.*;

public class HuffmanEncode<T> implements Comparable<T> {
	
	CharNode overallRoot;
	FileReader output;

	public HuffmanEncode(File object) throws IOException {
		output = new FileReader(object);
	}

	public void encode() throws IOException {
		HashMap<Character, Integer> valFreq = new HashMap<Character, Integer>();
		int c;
		while ((c = output.read()) != -1) {
			valFreq.putIfAbsent((char) c, 0);
			valFreq.replace((char) c, valFreq.get((char) c) + 1);
			System.out.println(c);
			System.out.println(valFreq.get((char) c));
		}
		output.close();
		
		// Push them all into a priority queue with your custom class
		PriorityQueue<CharNode> queue = new PriorityQueue<CharNode>();
		for (Map.Entry<Character, Integer> item : valFreq.entrySet()) {
			queue.add(new CharNode(item.getKey(), null, null, item.getValue()));
		}
		
		while(queue.size() > 1) {
			CharNode temp1 = queue.remove();
			CharNode temp2 = queue.remove();
			CharNode newNode = new CharNode(null, temp1, temp2, temp1.weight + temp2.weight);
			queue.add(newNode);
		}
		
		traversal();
		//CharNode overallRoot = queue.remove();
		
	}

	public void traversal() {
		traversal(overallRoot, 1);
	}
	
	private void traversal(CharNode root, int level) {
		if(root != null) {
			traversal(root.leftChild, level++);
			System.out.println(root.symbol + ", " + root.weight);
			traversal(root.rightChild, level++);
		}
	}

	private class CharNode implements Comparable<T> {

		private Character symbol; // char to be encoded, empty if combined node
		CharNode leftChild;
		CharNode rightChild;
		CharNode parent;
		Integer weight; // occurrence # of this char in the text

		public CharNode(Character symbol, CharNode leftChild,
				CharNode rightChild, Integer weight) {
			this.symbol = symbol;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
			this.weight = weight;
		}

		public int compareTo(CharNode o) {
			return this.weight - o.weight;
		}

		@Override
		public int compareTo(T o) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
