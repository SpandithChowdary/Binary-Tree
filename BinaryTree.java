package Binary_Tree;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

class Node{
	int key;
	Node left, right;
	
	public Node(int item) {
		key = item;
		left = right = null;
	}
}

class BinaryTree {
	Node root;
	
	void insert(int key){
		Node newNode = new Node(key);
		if(root == null) {
			root = newNode;
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node temp = queue.poll();
			if(temp.left == null) {
				temp.left = newNode;
				return;
			}else {
				queue.add(temp.left);
			}
			if(temp.right == null) {
				temp.right = newNode;
				return;
			}else {
				queue.add(temp.right);
			}
		}
	}
	public void preorder(Node root) {
		if(root!=null) {
			System.out.print(root.key+" ");
			preorder(root.left);
			preorder(root.right);
		}
	}
	public void postorder(Node root) {
		if(root!=null) {
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.key+" ");
		}
	}
	public void inorder(Node root) {
		if(root!=null) {
			inorder(root.left);
			System.out.print(root.key+" ");
			inorder(root.right);
		}
	}
	
	boolean search(int key) {
		if(root == null) return false;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node temp = queue.poll();
			if(temp.key == key) return true;
			if(temp.right != null) queue.add(temp.right); 
			if(temp.left != null) queue.add(temp.left); 
		}
		return false;
		
	}
	public static void main(String[] args) {
	  Scanner sc = new Scanner(System.in);
	  BinaryTree tree = new BinaryTree();
	  System.out.println("Enter no.of node you want to insert :");
	  int size = sc.nextInt();
	  System.out.println("Enter the node values:");
	  for(int i=0;i<size ; i++) {
		  int a = sc.nextInt();
		  tree.insert(a);
	  }
	  System.out.println("Pre-Order");
	  tree.preorder(tree.root);
	  System.out.println("\nPost-Order");
	  tree.postorder(tree.root);
	  System.out.println("\nIn-Order");
	  tree.inorder(tree.root);
	  System.out.println("\nEnter a value to search :");
	  int num = sc.nextInt();
	  if(tree.search(num)) {
		  System.out.println("value = "+num+" is found in tree");
	  }else {
		  System.out.println("value = "+num+" is not found in tree");
	  }
	  sc.close();
	}

}

