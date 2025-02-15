package Binary_Tree;

import java.util.Scanner;

class AVLNode{
	int key , height ;
	AVLNode left , right ;
	
	public AVLNode(int key) {
		this.key = key;
		this.height = 1;
	}
}

public class AVL_Tree {
	AVLNode root;

	private int height(AVLNode node) {
		return (node == null)? 0 : node.height;
	}
	
	private int getBalance(AVLNode node) {
		return (node == null)? 0 : height(node.left) - height(node.right);
	}

	public AVLNode insert(AVLNode node, int key) {
		
		if(node == null) return new AVLNode(key);
		if(key < node.key) node.left = insert(node.left, key);
		else if(key > node.key) node.right = insert(node.right, key);
		else return node;            // Duplicate keys not allowed
		
		node.height = 1 + Math.max(height(node.left), height(node.right));  // height of AVL tree
		int balance = getBalance(node);       // Checking Balance of nodes
		
		if(balance > 1 && key < node.left.key) return rightRotate(node);   // Left-Left Rotation
		if(balance < -1 && key > node.right.key) return leftRotate(node);  // Right-Right Rotation
		if(balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);              //Left-Right Rotation
			return rightRotate(node);
		}
		if(balance < -1 && key < node.right.key) {
			node.right = leftRotate(node.right);            //Right-left Rotation
			return leftRotate(node);
		}
		
		return node;
	}
	
	private AVLNode rightRotate(AVLNode y) {
		AVLNode x = y.left;
		AVLNode temp = x.right;
		
		// Perform Rotation
		x.right = y;
		y.left = temp;
		
		//Update heights
		y.height = 1 + Math.max(height(y.left), height(y.right)); 
		x.height = 1 + Math.max(height(x.left), height(x.right)); 
		
		return x;
	}

	private AVLNode leftRotate(AVLNode x) {
		AVLNode y = x.right;
		AVLNode temp = y.left;
		
		// Perform Rotation
		y.left = x;
		x.right= temp;
		
		//Update heights
		x.height = 1 + Math.max(height(x.left), height(x.right)); 
		y.height = 1 + Math.max(height(y.left), height(y.right)); 
		
		return y;
	}


	public void inorder(AVLNode node) {
		if(node != null) {
			inorder(node.left);
			System.out.print(node.key + " ");
			inorder(node.right);
		}
		
	}

	public AVLNode delete(AVLNode node, int key) {
		
	if (node == null) return node;
    // BST deletion
    if (key < node.key)
        node.left = delete(node.left, key);
    else if (key > node.key)
        node.right = delete(node.right, key);
    else {
        // Node with one or no children
        if (node.left == null || node.right == null) {
            AVLNode temp = (node.left != null) ? node.left : node.right;
            if (temp == null) {
                temp = node;
                node = null;
            } else
                node = temp;
        } else {
            // Node with two children: Get inorder successor (smallest in right subtree)
            AVLNode temp = minValueNode(node.right);
            node.key = temp.key;
            node.right = delete(node.right, temp.key);
        }
    }

    if (node == null) return node;

    // Update height
    node.height = Math.max(height(node.left), height(node.right)) + 1;

    // Get balance factor
    int balance = getBalance(node);

    // Balance the tree
    if (balance > 1 && getBalance(node.left) >= 0) // Left-Left Case
        return rightRotate(node);

    if (balance > 1 && getBalance(node.left) < 0) { // Left-Right Case
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    if (balance < -1 && getBalance(node.right) <= 0) // Right-Right Case
        return leftRotate(node);

    if (balance < -1 && getBalance(node.right) > 0) { // Right-Left Case
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    return node;
	}

	private AVLNode minValueNode(AVLNode node) {
		AVLNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AVL_Tree tree = new AVL_Tree();
		
		System.out.println("Enter the no.of elements you want to enter :");
		int size = sc.nextInt();
		
		System.out.println("Enter the elements :");
		for(int i=0;i<size;i++) {
			int a = sc.nextInt();
			tree.root = tree.insert(tree.root,a);
		}
		
		System.out.println("In-order traversal of AVL tree:");
        tree.inorder(tree.root);
        System.out.println();
        
        System.out.println("\nEnter value to be deleted :");
        int a = sc.nextInt();
        tree.root = tree.delete(tree.root, a);
        
        System.out.println("\nIn-order traversal after deleting "+a);
        tree.inorder(tree.root);
        System.out.println();
        sc.close();
	}

}
