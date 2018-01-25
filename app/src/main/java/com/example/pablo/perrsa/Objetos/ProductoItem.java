package com.example.pablo.perrsa.Objetos;

import java.io.Serializable;

/**
 * Created by Pablo on 24/01/2018.
 */

public class ProductoItem implements Serializable{
    private String title;
    private int quantity;
    private boolean checked;

    public ProductoItem(){

    }

    public ProductoItem(String title, int quantity, boolean checked) {
        this.title = title;
        this.quantity = quantity;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
