package com.github.talick.photoapp.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class TestA {
    {
        System.out.println("A1");
    }
    static {
        System.out.println("A2");
    }

    public TestA() {
        System.out.println("a3");
    }
}
public class TestB extends TestA {

    {
        System.out.println("B1");
    }
    static {
        System.out.println("B2");
    }

    public TestB() {
        System.out.println("b3");
    }

    public static void main(String[] args) {
        final List<String> list = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};

        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            if (iter.next().equals("2")) {
                iter.remove();
            }
        }

        System.out.println(list.size());


    }
}
