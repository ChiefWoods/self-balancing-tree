public class Node {
	private int value;
	private Node left;
	private Node right;

	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public int getValue() {
		return value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public String toString() {
		return String.format("Node value : %d, Left child node: %d, Right child node : %d", getValue(), getLeft() != null ? getLeft().getValue() : null,
				getRight() != null ? getRight().getValue() : null);
	}
}
