import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class AvlTree {
	private Node root;

	public AvlTree() {
		root = null;
	}

	public AvlTree(ArrayList<Integer> nodeValues) {
		root = buildTree(nodeValues, 0, nodeValues.size() - 1);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node node) {
		root = node;
	}

	public Node buildTree(ArrayList<Integer> values, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		Node root = new Node(values.get(mid));
		root.setLeft(buildTree(values, start, mid - 1));
		root.setRight(buildTree(values, mid + 1, end));
		return root;
	}

	public void printTree(Node node, String prefix, boolean isLeft) {
		if (node == null) {
			return;
		}
		if (node.getRight() != null) {
			String formattedPrefix = String.format("%s%s", prefix, isLeft == true ? "│   " : "    ");
			printTree(node.getRight(), formattedPrefix, false);
		}
		System.out.printf("%s%s%d\n", prefix, isLeft == true ? "└── " : "┌── ", node.getValue());
		if (node.getLeft() != null) {
			String formattedPrefix = String.format("%s%s", prefix, isLeft == true ? "    " : "│   ");
			printTree(node.getLeft(), formattedPrefix, true);
		}
	}

	public void insert(int value) {
		if (root == null) {
			this.setRoot(new Node(value));
		} else {
			Node previous = null;
			Node temp = root;
			while (temp != null) {
				if (temp.getValue() > value) {
					previous = temp;
					temp = temp.getLeft();
				} else {
					previous = temp;
					temp = temp.getRight();
				}
			}
			if (previous.getValue() > value) {
				previous.setLeft(new Node(value));
			} else {
				previous.setRight(new Node(value));
			}
		}
		rebalanceTree();
		System.out.println("New node inserted\n");
	}

	public void delete(int value) {
		Node previous = null;
		Node temp = root;
		while (temp.getValue() != value) {
			if (temp.getValue() > value) {
				previous = temp;
				if (temp.getLeft() == null) {
					System.out.printf("Delete unsuccessful : Node %d does not exist\n\n", value);
					return;
				}
				temp = temp.getLeft();
			} else {
				previous = temp;
				if (temp.getRight() == null) {
					System.out.printf("Delete unsuccessful : Node %d does not exist\n\n", value);
					return;
				}
				temp = temp.getRight();
			}
		}
		if (temp.getLeft() == null && temp.getRight() == null) {
			temp = null;
		} else if (temp.getLeft() != null && temp.getRight() == null) {
			previous.setRight(temp.getLeft());
		} else if (temp.getLeft() == null && temp.getRight() != null) {
			previous.setLeft(temp.getRight());
		} else {
			Node targetParent = previous;
			Node target = temp;
			previous = target.getRight();
			if (previous.getLeft() != null) {
				temp = previous.getLeft();
				while (temp.getLeft() != null) {
					previous = temp;
					temp = temp.getLeft();
				}
				previous.setLeft(temp.getRight());
				if (targetParent != null) {
					if (targetParent.getValue() > value) {
						targetParent.setLeft(temp);
					} else {
						targetParent.setRight(temp);
					}
				} else {
					this.setRoot(temp);
				}
				temp.setLeft(target.getLeft());
				temp.setRight(target.getRight());
			} else {
				if (targetParent != null) {
					if (targetParent.getValue() > value) {
						targetParent.setLeft(previous);
					} else {
						targetParent.setRight(previous);
					}
				}
				previous.setLeft(target.getLeft());
			}
		}
		rebalanceTree();
		System.out.println("Node deleted\n");
	}

	public Node find(int value) {
		Node temp = root;
		while (temp.getValue() != value) {
			if (temp.getValue() > value) {
				if (temp.getLeft() == null) {
					return null;
				}
				temp = temp.getLeft();
			} else {
				if (temp.getRight() == null) {
					return null;
				}
				temp = temp.getRight();
			}
		}
		return temp;
	}

	public void findHandler(Node node) {
		if (node != null) {
			System.out.printf("%s\n\n", node.toString());
		} else {
			System.out.println("Find unsuccessful : node does not exist\n");
		}
	}

	public Integer height(Node node) {
		if (node != null) {
			Queue<Node> queue = new LinkedList<Node>();
			int nodeHeight = -1;
			queue.add(node);
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					Node temp = queue.poll();
					if (temp.getLeft() != null) {
						queue.add(temp.getLeft());
					}
					if (temp.getRight() != null) {
						queue.add(temp.getRight());
					}
				}
				nodeHeight++;
			}
			return nodeHeight;
		} else {
			return null;
		}
	}

	public void heightHandler(Node node) {
		if (node != null) {
			System.out.printf("Height of node %d is %d\n\n", node.getValue(), height(node));
		} else {
			System.out.println("Cannot get height : node does not exist\n");
		}
	}

	public Integer depth(Node node) {
		if (node != null) {
			int nodeDepth = 0;
			Node temp = root;
			while (temp != node) {
				if (temp.getValue() > node.getValue()) {
					temp = temp.getLeft();
					nodeDepth++;
				} else {
					temp = temp.getRight();
					nodeDepth++;
				}
			}
			return nodeDepth;
		} else {
			return null;
		}
	}

	public void depthHandler(Node node) {
		if (node != null) {
			System.out.printf("Depth of node %d is %d\n\n", node.getValue(), depth(node));
		} else {
			System.out.println("Cannot get depth : node does not exist\n");
		}
	}

	public ArrayList<Integer> levelOrder(ArrayList<Integer> arr, ArrayList<Node> queue, Node node) {
		if (node == null) {
			return null;
		}
		arr.add(node.getValue());
		if (node.getLeft() != null) {
			queue.add(node.getLeft());
		}
		if (node.getRight() != null) {
			queue.add(node.getRight());
		}
		while (queue.size() > 0) {
			node = queue.get(0);
			queue.remove(0);
			levelOrder(arr, queue, node);
		}
		return arr;
	}

	public ArrayList<Integer> inOrder(ArrayList<Integer> arr, Node node) {
		if (node == null) {
			return arr;
		}
		inOrder(arr, node.getLeft());
		arr.add(node.getValue());
		inOrder(arr, node.getRight());
		return arr;
	}

	public ArrayList<Integer> preOrder(ArrayList<Integer> arr, Node node) {
		if (node == null) {
			return null;
		}
		arr.add(node.getValue());
		preOrder(arr, node.getLeft());
		preOrder(arr, node.getRight());
		return arr;
	}

	public ArrayList<Integer> postOrder(ArrayList<Integer> arr, Node node) {
		if (node == null) {
			return null;
		}
		postOrder(arr, node.getLeft());
		postOrder(arr, node.getRight());
		arr.add(node.getValue());
		return arr;
	}

	public boolean isTreeBalanced() {
		int leftHeight = height(root.getLeft());
		int rightHeight = height(root.getRight());
		return Math.abs(leftHeight - rightHeight) < 2;
	}

	public void rebalanceTree() {
		ArrayList<Integer> sortedArr = levelOrder(new ArrayList<Integer>(), new ArrayList<Node>(), root);
		Collections.sort(sortedArr);
		root = buildTree(sortedArr, 0, sortedArr.size() - 1);
	}
}
