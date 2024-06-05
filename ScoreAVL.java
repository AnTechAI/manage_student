public class ScoreAVL extends AVL {

    public ScoreAVL() {
        super();
    }

    public void insert(Student key) {
        this.root = insert(this.root, key);
    }

    private Node insert(Node node, Student key) {
        if(node == null){
            return new Node(key);
        }
        if(key.getGpa() < node.getData().getGpa()){
            node.setLeft(insert(node.getLeft(), key));
        }
        else if(key.getGpa() > node.getData().getGpa()){
            node.setRight(insert(node.getRight(), key));
        }
        else{
            node.setData(key);
        }
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        return balance(node);
    }
}
