import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class btTraversal {
    static class Node {
        Node left, right;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    static class Btree {

        static int idx = -1;

        public Node createTree(int[] arr) {
            idx++;
            if (arr[idx] == -1) {
                return null;
            }
            Node newNode = new Node(arr[idx]);
            newNode.left = createTree(arr);
            newNode.right = createTree(arr);
            return newNode;
        }

        // Traversals
        // 1st Preorder
        // in preorder first root then left and then right
        public void Preorder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(" " + root.data + " ");
            Preorder(root.left);
            Preorder(root.right);
        }

        // 2nd Inorder
        // in Inorder first left, then root and then right
        public void Inorder(Node root) {
            if (root == null) {
                return;
            }
            Inorder(root.left);
            System.out.print(" " + root.data + " ");
            Inorder(root.right);
        }
        // 3nrd Postorder
        // in Postorder first left, then right and then root.
        public void Postorder(Node root) {
            if (root == null) {
                return;
            }
            Postorder(root.left);
            Postorder(root.right);
            System.out.print(" " + root.data + " ");
        }

        public void LevelOrder(Node root) {
            if (root == null) {
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while (!q.isEmpty()) {
                Node currNode = q.remove();
                if (currNode == null) {
                    System.out.println();
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null);
                    }
                } else {
                    System.out.print(" " + currNode.data + " ");
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }

        public int Count(Node root){
            if(root == null ){
                return 0;
            }

            int left =Count(root.left);
            int right =Count(root.right);
            return left+right+1;
        }
        public int SumOfNode(Node root){
            if(root == null){
                return 0;
            }
            int leftSum = SumOfNode(root.left);
            int rightSum = SumOfNode(root.right);

            return leftSum+rightSum+ root.data;
        }
        public int Height(Node root){
            if(root==null){
                return 0;
            }
            int leftH = Height(root.left);
            int rightH = Height(root.right);

            return 1+ Math.max(leftH, rightH);
        }
        void leftView(Node root){

            ArrayList<Integer> list = new ArrayList<Integer>();
            lfu(root, list, 0);
            
            for(int i =0;i<list.size();i++){
                System.out.print(" "+list.get(i)+ " ");
            }
        }
        void lfu( Node root, ArrayList<Integer> list, int level ){
            if(root==null){
                return;
            }
            if(list.size() == level){
                list.add(root.data);
            }
            lfu(root.left, list, level+1);
            lfu(root.right, list, level+1);
        }
     
    }
    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        Btree tree = new Btree();
        Node root = tree.createTree(nodes);
        System.out.println(" In-order");
        tree.Inorder(root);
        System.out.println();
        System.out.println("\n Pre-order");
        tree.Preorder(root);
        System.out.println();
        System.out.println("\n Post-order");
        tree.Postorder(root);
        System.out.println();
        System.out.println("\n Level-order ");
        tree.LevelOrder(root);
        System.out.println("\n Count of nodes = "+tree.Count(root));
        System.out.println("\n Sum of nodes = "+tree.SumOfNode(root));
        System.out.println("\n Height of tree = "+tree.Height(root));
        System.out.println("\n Left view of the tree");
        tree.leftView(root);
    }

}
