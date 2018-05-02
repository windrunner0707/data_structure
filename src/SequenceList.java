import java.util.Arrays;

/**
 * 自定义线性表,模仿ArrayList
 * @param <T>
 */
public class SequenceList<T> {

    //默认容器大小值16
    private final int DEFAULT_SIZE = 16;

    //容器大小变量
    private int capacity;

    //定义新的数组存放元素
    private Object[] elementData;

    //数组(链表)长度
    private int size = 0;

    //无参构造
    public SequenceList(){
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    //以一个元素初始化线性表
    public SequenceList(T element){
        this();
        elementData[0] = element;
        size++;
    }

    //制定链表长度和第一个元素构造线性表,线性表每次扩容是2的幂
    public SequenceList(T element, int initSize){
        capacity = 1;
        while(capacity < initSize){
            capacity <<= 1;
        }
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    //返回线性表长度
    public int length(){
        return size;
    }

    //根据索引获取元素
    public T get(int i){
        if(i < 0 || i >size -1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        return (T)elementData[i];
    }

    //根据元素获取索引
    public int locate(T element){
        for (int i = 0; i < size ; i++) {
            if(elementData[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    //制定索引插入元素
    //arrayCopy四个参数:源数组,源数组开始位置索引,目标数组,目标数组开始位置索引,需要复制的元素个数
    public void insert(T element, int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        //判读数组是否需要扩容(每次2倍)
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData,index+1,size-index);
        //元素插入指定索引位置,线性表长度+1
        elementData[index] = element;
        size++;
    }

    //线性表尾部追加元素
    public void add(T element){
        insert(element, size);
    }

    //重新确定线性表容量大小,不足则扩大2倍
    //copyOf方法参数:被复制的数组,数组扩容后的大小
    private void ensureCapacity(int minCapacity){
        if(minCapacity > capacity){
            while(capacity < minCapacity){
                capacity <<= 1;
            }
            elementData = Arrays.copyOf(elementData,capacity);
        }
    }

    //删除指定索引内容,该索引后的内容前移1
    public T delete(int index){
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        T oldValue = (T) elementData[index];
        int numMoved = size - index - 1;
        if(numMoved > 0){
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    //删除最后一个元素
    public T remove(){
        return delete(size - 1);
    }

    //判断表是否为空
    public boolean empty(){
        return size == 0;
    }

    //清空表
    public void clear(){
        Arrays.fill(elementData,null);
        size = 0;
    }

    //重写toString,构造[]格式,展示表中内容
    public String toString(){
        if(size == 0){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            //删除最后的逗号和空格,加上右括号
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
