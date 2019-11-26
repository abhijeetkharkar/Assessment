package com.eurail.test.app.entities;

import com.eurail.test.app.State;

/**
 * Created  2019-07-17 16:14
 * Copyright (c) 2019 Eurail.com B.V.
 *
 * @author Radion, Rodion.Kyryliak@eurail.com
 */

public class Order {
    private String title;
    private String uuid;
    private State state;

    public Order(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
