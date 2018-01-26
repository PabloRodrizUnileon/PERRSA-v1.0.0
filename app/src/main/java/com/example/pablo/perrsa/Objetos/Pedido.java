package com.example.pablo.perrsa.Objetos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Pablo on 25/01/2018.
 */

public class Pedido implements Serializable {
    private Map<String, ProductoItem> productos;
    private String productosString;
    private String ordenante, pueblo, direccion, fecha_pedido, hora_pedido;

    public Pedido(){

    }

    public Pedido(String ordenante, String pueblo, String direccion, String fecha_pedido, String hora_pedido, Map<String, ProductoItem> items) {
        this.ordenante = ordenante;
        this.pueblo = pueblo;
        this.direccion = direccion;
        this.fecha_pedido = fecha_pedido;
        this.hora_pedido = hora_pedido;
        this.productos = items;
    }

    public Map<String, ProductoItem> getProductos() {
        return productos;
    }

    public void setProductos(Map<String, ProductoItem> productos) {
        this.productos = productos;
    }

    public String getOrdenante() {
        return ordenante;
    }

    public void setOrdenante(String ordenante) {
        this.ordenante = ordenante;
    }

    public String getPueblo() {
        return pueblo;
    }

    public void setPueblo(String pueblo) {
        this.pueblo = pueblo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public String getHora_pedido() {
        return hora_pedido;
    }

    public void setHora_pedido(String hora_pedido) {
        this.hora_pedido = hora_pedido;
    }

    public String getProductosString() {
        String result = "";
        Iterator it = productos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            result = result + pair.getKey() + ":" + pair.getValue() + " ";
            it.remove(); // avoids a ConcurrentModificationException
        }
        return result;
    }

    public void setProductosString(String productosString) {
        this.productosString = productosString;
    }

    @Override
    public String toString() {
        return ordenante + " " + pueblo + " " + direccion + " ";
    }
}


