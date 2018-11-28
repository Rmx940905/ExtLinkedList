package cn.itcast.extlinkedlist;

import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import java.util.LinkedList;

public class ExtLinkedList<E> {

    //链表实际存储元素
    private  int size;

    //第一个元素（头节点,查询开始）
    private  Node first;

    //最后一个元素（尾节点,添加开始）
    private  Node last;

    public void add(E e){

        Node node = new Node();
        node.e = e;
        if(first == null) {
            //添加第一个元素
            first = node;
        } else {
            //将此节点存储的上一个节点赋值为last
            node.prev = last;
            //将last节点存储的下个节点存储为新添加的节点
            last.next = node;
        }
        //将last赋值为新添加的节点
        last = node;
        size++;
    }

    public void add(int index,E e) {
        checkElementIndex(index);
            Node oldNode = getNote(index);
            Node newNode = new Node();
            newNode.e = e;
            if (oldNode != null) {
                if (oldNode.prev != null) {
                    newNode.prev = oldNode.prev;
                    oldNode.prev = newNode;

                } else {
                    first = newNode;
                }
                newNode.next = oldNode;
            }else {
                first = newNode;
                last = newNode;
            }
            size++;

    }

    public Object get(int index) {

        return getNote(index).e;
    }

    private Node getNote(int index){

        checkElementIndex(index);
        return node(index);
    }

    public int getSize(){

        return size;
    }

    public E remove(int index){
        checkElementIndex(index);
        Node<E> node = node(index);
        final E element = node.e;
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;

        if(prev == null){
            first = next;
        }else{
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        }else{
            next.prev = prev;
            node.next = null;
        }

        node.e = null;
        size--;
        return element;
    }

    //二分法查找
    private Node node(int index) {

        if (index < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void checkElementIndex(int index){

        if(!isElementIndex(index))
            throw new IndexOutOfBoundsException("查询越界了");
    }

    private boolean isElementIndex (int index){

        return index >= 0 && index <size;
    }

    //链表阶段存储元素
    private static class Node<E>{
        //上一个节点
        Node prev;
        //下一个节点
        Node next;
        //存放元素的值
        E e;
    }

    public static void main(String[] args) {
        ExtLinkedList<String> extLinkedList = new ExtLinkedList<>();
        extLinkedList.add("a");
        extLinkedList.add("b");
        extLinkedList.add("c");
        extLinkedList.add("d");
        extLinkedList.add("e");
        extLinkedList.add(0,"n");
        for (int i = 0; i <extLinkedList.size ; i++) {
            System.out.println(extLinkedList.get(i));
        }
    }
}
