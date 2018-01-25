package com.example.pablo.perrsa.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pablo.perrsa.Objetos.ProductoItem;
import com.example.pablo.perrsa.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pablo on 25/01/2018.
 */

public class CustomAdapter extends ArrayAdapter<ProductoItem> implements View.OnClickListener{

    private List<ProductoItem> productosList;
    private Map<String, ProductoItem> productosAdd = new HashMap<String, ProductoItem>();
    private Context mContext;

    private static class ViewHolder{
        TextView textViewTitulo;
        EditText editTextCantidad;
        CheckBox checkBox;
    }

    public CustomAdapter (Context context, List<ProductoItem> productoItems){
        super(context, R.layout.item_list, productoItems);
        this.productosList = productoItems;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        ProductoItem productoItem = (ProductoItem) object;
    }
}
