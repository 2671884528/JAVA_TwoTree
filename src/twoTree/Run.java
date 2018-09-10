package twoTree;

/**
 * 二叉树的基础应用方法
 * 二叉树的节点可以插入许多的数据
 */
public class Run {

    public static void main(String[] args) {

        Tree tree = new Tree();
        tree.inset(10);
        tree.inset(100, "我是傻子");
        tree.inset(4);
        tree.inset(5);
        tree.inset(11);
        tree.inset(19, "余惠是猪");
        tree.inset(17, "郭永钢是猪");
        tree.inset(16, "郭永钢是天才");
        tree.inset(20, "郭永钢是天才");
        tree.inset(30, "郭永钢是天才");
        tree.inset(40, "郭永钢是天才");
        System.out.println(tree.root.rightChild.date + "    " + tree.root.rightChild.sdate);
        System.out.println(tree.find(100));
        System.out.println("###############################");
        tree.frontOrder(tree.root);
        System.out.println("###############################");
        tree.deleteOder(19);
        tree.frontOrder(tree.root);

    }

}

class Tree {

    public static Node root;

    /**
     * 插入节点,将插入的方法改静态的方法，使得
     * 插入数据的方法都可以调用，实现代码的简洁
     * @param newNode
     * @param value
     */
    public static void exchange(Node newNode, long value){
        Node current = root;
        //引用父节点
        Node parent;
        if (root == null) {
            root = newNode;
            return;
        } else {
            while (true) {
                parent = current;
                if (current.date > value) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }

            }
        }
    }

    /**
     * 一个变量
     * @param value
     */

    public void inset(long value) {
        //封装节点
        Node newNode = new Node(value);
        exchange(newNode,value);
    }

    /**
     * 两个变量
     * @param value
     * @param sdate
     */
    public void inset(long value, String sdate) {
        //封装节点
        Node newNode = new Node(value, sdate);
       exchange(newNode,value);
    }

    /**
     * 前序遍历
     */

    public void frontOrder(Node localNode) {
        if (localNode != null) {
            System.out.println(localNode.date + "   " + localNode.sdate);
            //前序遍历左子树
            frontOrder(localNode.leftChild);
            //前序遍历右子树
            frontOrder(localNode.rightChild);
        }
    }

    /**
     * 查找节点数据
     */

    public String find(long value) {

        //引用当前的节点
        Node current = root;

        while (current.date != value) {
            if (current.date > value) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return "没有找到你要的节点数据";
            }
        }
        return ("你找的数据已找到为" + current.date);
    }

        /**
        *删除节点
        */
        public boolean deleteOder(long value){

            //父节点的引用
            Node current = root;
            Node parent = root;
            boolean isleftChild = true;

            while(current.date != value){
                parent = current;
                if (current.date > value){
                    current = current.leftChild;
                    isleftChild = true;
                }else{
                    current = current.rightChild;
                    isleftChild = false;
                }
                if (current == null) {
                    return false;
                }
            }
            //删除叶子节点
            if (current == root){
                root = null;
            }else{
            if(current.leftChild == null && current.rightChild == null){
                if (isleftChild){
                    parent.leftChild = null;
                }else{
                    parent.rightChild = null;
                }
                //删除单叶子节点
            }else if (current.rightChild == null && current.leftChild != null){
                if(isleftChild){
                    parent.leftChild = current.leftChild;
                }else{
                    parent.rightChild = current.leftChild;
                }
            }else if (current.leftChild == null && current.rightChild != null) {
                if (isleftChild) {
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;

                }
            }else{
                Node success = getsuccess(current);
                if (current == root){
                    root = success;
                }else if(isleftChild){
                    parent.leftChild = success;

                }else{
                    parent.rightChild = success;
                }
            }
            }
            return true;
        }
    /**
     * 寻找中序后继节点
     */
    public static Node getsuccess(Node delNode){

        Node success = delNode;
        Node successparent = delNode;
        Node current = delNode.rightChild;
        while(current != null){
            successparent =  success;
            success = current;
            current = current.leftChild;
        }
        if (success != delNode.rightChild){
            successparent.leftChild = success.rightChild;
            success.rightChild = delNode.rightChild;
        }else{
            success.leftChild = delNode.leftChild;
        }
        return success;
    }

}

/**
 * 二叉树的节点
 */
class Node {

    public long date;
    public String sdate;
    public Node leftChild;
    public Node rightChild;

    public Node(long date) {
        this.date = date;
    }

    public Node(String sdate) {
        this.sdate = sdate;
    }

    public Node(long date, String sdate) {
        this.date = date;
        this.sdate = sdate;
    }
}
