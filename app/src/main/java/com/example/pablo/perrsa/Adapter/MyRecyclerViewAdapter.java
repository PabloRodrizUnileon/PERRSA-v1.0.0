package com.example.pablo.perrsa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.perrsa.Objetos.ProductoItem;
import com.example.pablo.perrsa.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pablo on 24/01/2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {


    private List<ProductoItem> productosList;
    private Map<String, ProductoItem> productosAdd = new HashMap<String, ProductoItem>();
    private Context mContext;

    public Map<String, ProductoItem> getProductosAdd() {
        return productosAdd;
    }

    public void clearData() {
        int size = this.productosList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.productosList.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }


    public MyRecyclerViewAdapter(Context context, List<ProductoItem> productosList) {
        this.productosList = productosList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        view.setOnClickListener(view1 -> {
            if (viewHolder.editTextCantidad.getVisibility() == View.INVISIBLE) {
                viewHolder.editTextCantidad.setVisibility(View.VISIBLE);
                viewHolder.checkBox.setVisibility(View.VISIBLE);
            } else {
                viewHolder.editTextCantidad.setVisibility(View.INVISIBLE);
                viewHolder.checkBox.setVisibility(View.INVISIBLE);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        ProductoItem productoItem = productosList.get(i);


        customViewHolder.textViewTitulo.setText(productoItem.getTitle());
        customViewHolder.checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (TextUtils.isEmpty(customViewHolder.editTextCantidad.getText())) {
                Toast.makeText(mContext, "Introducir cantidad", Toast.LENGTH_SHORT).show();
            } else {


                int cantidad = Integer.parseInt(customViewHolder.editTextCantidad.getText().toString());
                if (b) {
//                if(TextUtils.isEmpty(customViewHolder.editTextCantidad.getText())){
//                    Toast.makeText(mContext, "Introducir Cantidad", Toast.LENGTH_SHORT).show();
//                }

                    customViewHolder.editTextCantidad.setEnabled(false);
                    productoItem.setQuantity(cantidad);
                    productosAdd.put(productoItem.getTitle(), productoItem);
                } else {
                    customViewHolder.editTextCantidad.setEnabled(true);
                    productosAdd.remove(productoItem);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return productosList.size();

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewTitulo;
        protected EditText editTextCantidad;
        protected CheckBox checkBox;

        public CustomViewHolder(View view) {
            super(view);
            this.textViewTitulo = view.findViewById(R.id.textView_title);
            this.editTextCantidad = view.findViewById(R.id.editTextCantidad);
            this.checkBox = view.findViewById(R.id.checkbox_item);
        }

    }
}

