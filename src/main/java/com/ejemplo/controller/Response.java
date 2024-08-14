package com.ejemplo.controller;

public class Response {
    private int id;
    private boolean success;

    public Response(int id, boolean success) {
        this.id = id;
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }
}
