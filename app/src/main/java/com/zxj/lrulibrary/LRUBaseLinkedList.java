package com.zxj.lrulibrary;

/**
 *  单链表实现的LRU的算法，缺点时间复查度O(n)
 * @param <T>
 */
public class LRUBaseLinkedList<T> {

    //链表默认初始的容量大小
    private final static Integer DEFAULT_CAPACITY = 10;

    //头结点
    private SNode<T> headNode;

    //链表的长度
    private Integer length;

    //链表的容量
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data){
        SNode preNode = findPreNode(data);

        // 如果链表中存在要插入的元素，就删除链表中的该元素，再插入到链表的头部
        if (preNode != null){
            deleteElementOptim(preNode);
            insertElementAtBegin(data);
        }else {
            if (length >= this.capacity){
                //删除尾部的节点
                deleteElementAtEnd();
            }
            insertElementAtBegin(data);
        }
    }

    /**
     * 删除preNode节点的下一个节点，即删除链表中已存在的节点
     * @param preNode
     */
    private void deleteElementOptim(SNode preNode) {
        SNode tmp = preNode.getNext();
        preNode.setNext(tmp.getNext());
        tmp = null;
        length--;
    }

    /**
     * 删除尾节点
     */
    private void deleteElementAtEnd() {
        SNode ptr = headNode;

        //空链表直接返回, 处理用户初始化容量capacity为0的情况
        if (ptr.getNext() == null){
            return;
        }

        while (ptr.getNext().getNext() != null){
            ptr = ptr.getNext();
        }

        //链表的倒数第二个
        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }

    /**
     * 插入在头节点之后
     * @param data
     */
    private void insertElementAtBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data,next));
        length++;
    }

    /**
     * 获取查找元素的前一个节点
     * @param data
     * @return
     */
    private SNode findPreNode(T data){
        SNode node = headNode;
        while (node.getNext() != null){
            if (data.equals(node.getNext().getElement())){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public void printAll(){
        SNode node = headNode.getNext();
        while (node != null){
            System.out.print(node.getElement()+" ,");
            node = node.getNext();
        }
        System.out.println();
    }

    class SNode<T> {

        /**
         * 要存储的数据
         */
        private T element;

        /**
         * 当前元素的下一个节点
         */
        private SNode next;

        public SNode() {
            this.next = null;
        }

        public SNode(T element){
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }
}
