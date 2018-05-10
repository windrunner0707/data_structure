package linear_list;

/**
 * 自定义单链表,模仿LinkedList
 */
public class LinkList<T> {

    private class Node{

        //保存节点数据
        private T data;

        //指向下个节点的引用
        private Node next;

        //无参构造
        public Node(){

        }

        //初始化全部属性的构造器
        public Node(T data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    //保存链表的头节点
    private Node header;

    //保存链表的尾节点
    private Node tail;

    //保存链表的全部节点数
    private int size;

    //创建一个空链表,头尾节点都是空
    public LinkList(){
        header = null;
        tail = null;
    }

    //用指定元素创建一个链表,该链表的初始化元素只有一个
    public LinkList(T element){
        header = new Node(element,null);
        //头尾节点相等
        tail = header;
        size++;
    }

    //返回链表长度
    public int length(){
        return size;
    }

    //获取指定索引的元素
    public T get(int index){
        return getNodeByIndex(index).data;
    }

    //根据索引找到链表的节点
    private Node getNodeByIndex(int index){
        //防止索引越界
        if(index < 0 || index > size -1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        //指定当前节点为第一个节点,方便遍历
        Node current = header;
        //以链表长度size作为循环次数,同时保证在循环时当前节点不断变化(current = current.next)
        for (int i = 0; i < size && current != null ; i++ , current = current.next) {
            if(i == index){
                return current;
            }
        }
        return null;
    }

    //确定该元素在链表中的位置,没有该元素返回-1
    public int locate(T element){
        Node current = header;
        for (int i = 0; i < size && current != null ; i++ , current = current.next) {
            if(current.data.equals(element)){
                return i;
            }
        }
        return -1;
    }

    //在指定索引处插入新元素,插入过程中无数组复制,效率比arraylist高
    public void insert(T element, int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        //如果是空链表,使用尾插法
        if(header == null){
            add(element);
        } else {
            //如果index为0即从链表的头部插入
            if(index == 0){
                addAtHeader(element);
            } else {
                //产生新节点,装载element,根据前一节点,修改节点引用
                Node prev = getNodeByIndex(index - 1);
                prev.next = new Node(element, prev.next);
                size++;
            }
        }
    }

    //采用头插法插入节点
    public void addAtHeader(T element){
            header = new Node(element, null);
        if(tail == null) {
            tail = header;
        }
        size++;
    }

    //采用尾插法插入节点
    public void add(T element){
        if(header == null){
            header = new Node(element, null);
            tail = header;
        } else {
            Node newNode = new Node(element, null);
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
        } else {
            //使目标节点的前一节点引用指向目标节点的下一节点,完成目标节点的删除
            Node prev = getNodeByIndex(index - 1);
            del = prev.next;
            prev.next = del.next;
            del.next = null;
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
