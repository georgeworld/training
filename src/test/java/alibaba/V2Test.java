package alibaba;

import com.alibaba.chainlist.v2.MyLinkedList;
import org.junit.Before;
import org.junit.Test;

public class V2Test {
    private MyLinkedList list;

    @Before
    public void setup() {
        //构建链表
        list = new MyLinkedList();
        list.buildList2("a","b","c","d","e","f");
    }

    @Test
    public void traverList() {
//        list.traversalList(list.getFirstNode());
        list.traversalList2();
    }
}
