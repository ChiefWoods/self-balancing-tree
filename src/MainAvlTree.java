import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainAvlTree {

	public static void main(String[] args) {
		AvlTree tree = new AvlTree();
		Scanner scanIn = new Scanner(System.in);
		int heightValue = 0;
		int depthValue = 0;

		System.out.println("AVL Tree\n");
		while (true) {
			System.out.println("Choose an option below:");
			System.out.println("( 1 ) - BUILD a new tree");
			System.out.println("( 2 ) - PRINT tree");
			System.out.println("( 3 ) - INSERT a new node");
			System.out.println("( 4 ) - DELETE a node");
			System.out.println("( 5 ) - FIND if a node exists");
			System.out.println("( 6 ) - HEIGHT of a node");
			System.out.println("( 7 ) - DEPTH of a node");

			String option = scanIn.next();

			switch (option) {
			case "1":
				System.out.println("Enter the amount of nodes to populate the tree with : ");
				int amount = scanIn.nextInt();
				tree = new AvlTree(generateArray(amount));
				System.out.println("New tree generated\n");
				break;
			case "2":
				System.out.println("Choose an order to print the tree in : ");
				System.out.println("( 1 ) - Level-Order");
				System.out.println("( 2 ) - Pre-Order");
				System.out.println("( 3 ) - Post-Order");
				System.out.println("( 4 ) - In-Order");
				System.out.println("( 5 ) - Visualized");
				int order = scanIn.nextInt();
				switch (order) {
				case 1:
					System.out.printf("Tree nodes in level-order : %s\n\n", tree.levelOrder(new ArrayList<Integer>(), new ArrayList<Node>(), tree.getRoot()));
					break;
				case 2:
					System.out.printf("Tree nodes in pre-order : %s\n\n", tree.preOrder(new ArrayList<Integer>(), tree.getRoot()));
					break;
				case 3:
					System.out.printf("Tree nodes in post-order : %s\n\n", tree.postOrder(new ArrayList<Integer>(), tree.getRoot()));
					break;
				case 4:
					System.out.printf("Tree nodes in in-order : %s\n\n", tree.inOrder(new ArrayList<Integer>(), tree.getRoot()));
					break;
				case 5:
					tree.printTree(tree.getRoot(), "", true);
					System.out.println("");
				}
				break;
			case "3":
				System.out.println("Enter the value of the new node to be inserted : ");
				int insertValue = scanIn.nextInt();
				tree.insert(insertValue);
				break;
			case "4":
				System.out.println("Enter the value of the node to be deleted: ");
				int deleteValue = scanIn.nextInt();
				tree.delete(deleteValue);
				break;
			case "5":
				System.out.println("Enter the value of the node to find :");
				int findValue = scanIn.nextInt();
				tree.findHandler(tree.find(findValue));
				break;
			case "6":
				System.out.println("Enter the value of the node: ");
				heightValue = scanIn.nextInt();
				tree.heightHandler(tree.find(heightValue));
				break;
			case "7":
				System.out.println("Enter the value of the node: ");
				depthValue = scanIn.nextInt();
				tree.depthHandler(tree.find(depthValue));
				break;
			default:
				System.out.println("Invalid option!\n");
			}
		}
	}

	public static ArrayList<Integer> generateArray(int length) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			int num = (int) (Math.random() * 100);
			while (arr.contains(num)) {
				num = (int) (Math.random() * 100);
			}
			arr.add(num);
		}
		Collections.sort(arr);
		return arr;
	}
}
