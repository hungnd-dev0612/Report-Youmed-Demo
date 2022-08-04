package model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private int id;

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private String username;
    private String address;

    public User() {
        this.id = COUNTER
                .getAndIncrement();
    }

    public User(String username, String address) {
        this.id = COUNTER.getAndIncrement();
        this.username = username;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
