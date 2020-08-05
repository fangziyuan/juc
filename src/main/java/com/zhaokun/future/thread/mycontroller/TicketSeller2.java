package com.zhaokun.future.thread.mycontroller;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller2 {

    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票号： " + i);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() > 0) {
                            String remove = tickets.remove(0);
                            System.out.println("销售了---" + remove);
                        } else {
                            return;
                        }
                    }
                }
            }).start();
        }
    }


}
