package com.zxj.lrulibrary;

import java.util.HashMap;

/**
 * LRU单链表的优化
 * 基于单链表时间复杂度O(n)的问题 采用 hashMap + 双链表的方式，时间复杂度O(1) 但多余占用了空间，是时间换空间的做法
 */
public class LRUCache {

    private int capacity;
    private HashMap<Integer,ListNode> hashMap;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity){
        this.capacity = capacity;
        hashMap = new HashMap<>();
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 将添加的元素加入到链表的尾部
     * @param key
     * @param value
     */
    public void put(int key,int value){

        if (hashMap.containsKey(key)){
            ListNode node = hashMap.get(key);
            node.val = value;
            moveNodeToLast(node);
            return;
        }

        if (hashMap.size() == capacity){
            hashMap.remove(head.next.key);
            removeNode(head.next);
        }

        ListNode node = new ListNode(key,value);
        hashMap.put(key,node);
        addNodeToLast(node);
    }

    public int get(int key){
        if (hashMap.containsKey(key)){
            ListNode node = hashMap.get(key);
            moveNodeToLast(node);
            return node.val;
        }else {
            return -1;
        }
    }

    private void addNodeToLast(ListNode node) {
        node.prev = tail.prev;
        node.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }

    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     *
     * @param node
     */
    private void moveNodeToLast(ListNode node) {
        removeNode(node);
        addNodeToLast(node);
    }

    private class ListNode{
        int key;
        int val;
        ListNode prev;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


}
