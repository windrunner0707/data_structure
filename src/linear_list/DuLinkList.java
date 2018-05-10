package linear_list;

/**
 * 双向链表,比一般链表多了一个头节点,操作变复杂
 * @param <T>
 */
public class DuLinkList<T> {

    private class Node{
        //符合双向链表的特征,node内部类中比一般链表多了prev节点
        private T data;
        private Node prev;
        private Node next;
        public Node(){}
        public Node(T data, Node prev, Node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node header;
    private Node tail;
    private int size;

    public DuLinkList(){
        header = null;
        tail = null;
    }

    public DuLinkList(T element){
        header = new Node(element, null, null);
        tail = header;
        size++;
    }

    public int length(){
        return size;
    }

    public T get(int index){
        return getNodeByIndex(index).data;
    }

    private Node getNodeByIndex(int index){
        if(index < 0 || index > size -1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        if(index <= size / 2 ){
            Node current = header;
            for(int i = 0 ; i <= size / 2 && current != null ; i++ ,current = current.next ){
                if(i == index){
                    return current;
                }
            }
        } else {
            Node current = tail;
            for(int i = size - 1 ; i >= size / 2 && current != null ; i++ ,current = current.prev ){
                if(i == index){
                    return current;
                }
            }
        }
        return null;
    }

    public int locate(T element){
        Node current = header;
        for(int i = 0 ; i <= size / 2 && current != null ; i++ ,current = current.next ){
            if(current.data.equals(element)){
                return i;
            }
        }
        return -1;
    }

    public void insert(T element, int index){
        if(index < 0 || index > size ){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        if(header == null){
            add(element);
        } else {
            if(index == 0){
                addAtHeader(element);
            } else {
                Node prev = getNodeByIndex(index - 1);
                Node next = prev.next;
                Node newNode = new Node(element,prev,next);
                prev.next = newNode;
                next.prev = newNode;
                size++;
            }
        }
    }

    //采用头插法插入节点
    public void addAtHeader(T element){
        header = new Node(element,null,header);
        if(tail == null) {
            tail = header;
        }
        size++;
    }

    //采用尾插法插入节点
    public void add(T element){
        if(header == null){
            header = new Node(element,null,null);
            tail = header;
        } else {
            Node newNode = new Node(element, tail,null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    //删除指定索引的节点
    public T delete(int index){
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        Node del = null;
        if(index == 0){
            del = header;
            header = header.next;
            header.prev = null;
        } else {
            //使目标节点的前一节点引用指向目标节点的下一节点,完成目标节点的删除
            Node prev = getNodeByIndex(index - 1);
            del = prev.next;
            prev.next = del.next;
            if(del.next != null){
                del.next.prev = prev;
            }
            del.next = null;
            del.prev = null;
        }
        size--;
        return del.data;
    }

    //删除链表的最后一个元素
    public T remove(){
        return delete(size - 1);
    }

    //判断链表是否为空
    public boolean empty(){
        return size == 0;
    }

    //清空链表
    public void clear(){
        header = null;
        tail = null;
        size = 0;
    }

    //构造toString
    public String toString(){
        if(empty()){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = header ; current != null ; current = current.next){
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
