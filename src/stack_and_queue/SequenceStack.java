package stack_and_queue;

import java.util.Arrays;

/**
 * 顺序栈的实现
 */
public class SequenceStack<T> {

    private int DEFAULT_SIZE = 10;

    private int capacity;

    private int capacityIncrement = 0;

    private Object[] elementData;

    private int size = 0;

    public SequenceStack(){
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public SequenceStack(T element){
        this();
        elementData[0] = element;
        size++;
    }

    public SequenceStack(T element, int initSize){
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    public SequenceStack(T element, int initSize, int capacityIncrement){
        this.capacity = initSize;
        this.capacityIncrement = capacityIncrement;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    public int length(){
        return size;
    }

    public void push(T element){
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }

    public void ensureCapacity(int minCapacity){
        if(minCapacity > capacity){
            if(capacityIncrement > 0){
                while (capacity < minCapacity){
                    capacity += capacityIncrement;
                }
            } else {
                while (capacity < minCapacity){
                    capacity <<= 1;
                }
            }
        }
        elementData = Arrays.copyOf(elementData,capacity);
    }

    public T pop(){
        T oldValue = (T) elementData[size-1];
        elementData[--size] = null;
        return oldValue;
    }

    public T peek(){
        return (T)elementData[size-1];
    }

    public boolean empty(){
        return size == 0;
    }

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
