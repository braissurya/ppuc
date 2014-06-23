package com.melawai.ppuc.model;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = -5850101755307438211L;
    private String name;
    private boolean ascending;

    public Order(String name) {
	this.name = name;
	this.ascending = true;
    }

    public Order(String name, boolean ascending) {
	this.name = name;
	this.ascending = ascending;
    }

    public String getName() {
	return name;
    }

    public boolean isAscending() {
	return ascending;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setAscending(boolean ascending) {
	this.ascending = ascending;
    }

}
