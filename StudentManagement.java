import java.time.temporal.Temporal;
import java.util.*;

public class StudentManagement {
    private AVL tree;
    private Stack<Node> undoState;
    private Stack<Node> redoState;

    public StudentManagement() {
        this.tree = new AVL();
        this.undoState = new Stack<Node>();
        this.redoState = new Stack<Node>();
    }

    public AVL getTree() {
        return this.tree;
    }

    // Requirement 1
    public boolean addStudent(Student st) {
        // code here
        if (tree.search(st.getId()) != null) {
            return false;
        }
        undoState.push(tree.copyNode(tree.getRoot()));
        tree.insert(st);
        return true;

    }

    // Requirement 2
    public Student searchStudentById(int id) {
        // code here
        if (tree.search(id) == null)
            return null;
        return tree.search(id).getData();
    }

    // Requirement 3
    public boolean removeStudent(int id) {
        // code here
        Node tr = tree.search(id);
        if (tr == null)
            return false;
        undoState.push(tree.copyNode(tree.getRoot()));
        tree.delete(tr.getData());
        return true;

    }

    // Requirement 4
    public void undo() {
        // code here
        if (!undoState.isEmpty()) {
            Node temp = undoState.pop();
            redoState.push(tree.getRoot());
            tree.setRoot(temp);
        }

    }

    // Requirement 5
    public void redo() {
        // code here
        if (!redoState.isEmpty()) {
            Node temp = redoState.pop();
            undoState.push(temp);
            tree.setRoot(temp);
        }
    }

    // Requirement 6
    public ScoreAVL scoreTree(AVL tree) {
        // code here
        Queue<Node> queue = new ArrayDeque<>();
        Queue<Node> storage = new ArrayDeque<>();
        ScoreAVL scoreAVL = new ScoreAVL();
        queue.add(tree.getRoot());
        Node tempNode;
        while (!queue.isEmpty()) {
            tempNode = queue.poll();
            storage.add(tempNode);
            if (tempNode.getLeft() != null) {
                queue.add(tempNode.getLeft());
            }
            if (tempNode.getRight() != null) {
                queue.add(tempNode.getRight());
            }
        }
        while (!storage.isEmpty()) {
            scoreAVL.insert(storage.poll().getData());
        }
        return scoreAVL;
    }
}
