import linear_list.DuLinkList;
import linear_list.LinkList;
import linear_list.SequenceList;

public class Main {

    public static void main(String[] args) {

/*
        SequenceList<String> list = new SequenceList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.insert("d",1);
        System.out.println(list);
        list.delete(2);
        System.out.println(list);
        System.out.println(list.locate("c"));
*/


/*        LinkList<String> list2 = new LinkList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.insert("d",1);
        System.out.println(list2);
        list2.delete(2);
        System.out.println(list2);
        System.out.println(list2.locate("c"));*/

        DuLinkList<String> list3 = new DuLinkList<>();
        list3.add("a");
        list3.add("b");
        list3.add("c");
        list3.insert("d",1);
        System.out.println(list3);
        list3.delete(2);
        System.out.println(list3);
        System.out.println(list3.locate("c"));
    }
}
