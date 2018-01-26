package com.example.pablo.perrsa.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pablo.perrsa.Objetos.Pedido;
import com.example.pablo.perrsa.Objetos.ProductoItem;
import com.example.pablo.perrsa.R;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Pablo on 26/01/2018.
 */

public class PedidosList extends ArrayAdapter<Pedido> {
    private Activity context;
    List<Pedido> pedidoList;

    public PedidosList(Activity context, List<Pedido> pedidos){
        super(context, R.layout.item_list_pedido);
        this.context = context;
        this.pedidoList = pedidos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.item_list_pedido, null, true);

        TextView textViewOrdenante = listViewItem.findViewById(R.id.textView_ordenante);
        TextView textViewPueblo = listViewItem.findViewById(R.id.textView_pueblo);
        TextView textViewDireccion = listViewItem.findViewById(R.id.textView_direccion);
        TextView textViewFecha = listViewItem.findViewById(R.id.textView_fecha);
        TextView textViewProductos = listViewItem.findViewById(R.id.textView_productos);

        Pedido pedido = pedidoList.get(position);
        textViewOrdenante.setText(pedido.getOrdenante());
        textViewPueblo.setText(pedido.getPueblo());
        textViewDireccion.setText(pedido.getDireccion());
        textViewFecha.setText(pedido.getFecha_pedido());
        textViewProductos.setText(pedido.getProductosString());

        return listViewItem;
    }
}
