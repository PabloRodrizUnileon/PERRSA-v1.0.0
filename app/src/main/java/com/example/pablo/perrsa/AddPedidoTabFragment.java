package com.example.pablo.perrsa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pablo.perrsa.Adapter.MyRecyclerViewAdapter;
import com.example.pablo.perrsa.Objetos.Pedido;
import com.example.pablo.perrsa.Objetos.ProductoItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pablo on 6/1/18.
 */

public class AddPedidoTabFragment extends Fragment {

    private DatabaseReference mDatabase;
// ...



    boolean isTablet;
    boolean mDualPane;
    RecyclerView recyclerViewProductos;
    MyRecyclerViewAdapter adapter;
    Map<String, ProductoItem> productoItemsList;


    private Button btnAdd, btnBorrar;
    private EditText editText_ordenante, editText_pueblo, editText_direccion, editText_fechaPedido, editText_hora_pedido;
    private String ordenante, pueblo, direccion, fecha_pedido, hora_pedido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTablet = getResources().getBoolean(R.bool.isTablet);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Fragment uno");
        View rootView = inflater.inflate(R.layout.fragment_add_pedido, container, false);
        mDualPane = rootView.findViewById(R.id.pedidoFragment) != null;
        recyclerViewProductos = rootView.findViewById(R.id.recycler_view_layour_recycler);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), getDummyData());
        recyclerViewProductos.setAdapter(adapter);
        recyclerViewProductos.setItemViewCacheSize(getDummyData().size());

        if(mDualPane){
            editText_ordenante = rootView.findViewById(R.id.edit_cliente);
            editText_pueblo = rootView.findViewById(R.id.edit_pueblo);
            editText_direccion = rootView.findViewById(R.id.edit_direccion);
            editText_fechaPedido = rootView.findViewById(R.id.edit_fecha);
            editText_hora_pedido = rootView.findViewById(R.id.edit_hora);




            btnAdd = rootView.findViewById(R.id.btn_add);
            btnBorrar = rootView.findViewById(R.id.btn_borrar);
            btnAdd.setOnClickListener(v -> {
                ordenante = editText_ordenante.getText().toString();
                pueblo = editText_pueblo.getText().toString();
                direccion= editText_direccion.getText().toString();
                fecha_pedido = editText_fechaPedido.getText().toString();
                hora_pedido = editText_hora_pedido.getText().toString();

                productoItemsList = adapter.getProductosAdd();
                Pedido pedido = new Pedido(ordenante, pueblo, direccion, fecha_pedido, hora_pedido, productoItemsList);
                writePedido(pedido);
                Toast.makeText(getContext(), "Producto añadido", Toast.LENGTH_SHORT).show();
            });

            btnBorrar.setOnClickListener(v -> {
                resetLayout();
            });
        }

        return rootView;
    }

    private void resetLayout() {
        editText_ordenante.setText("");
        editText_pueblo.setText("");
        editText_direccion.setText("");
        editText_fechaPedido.setText("");
        editText_hora_pedido.setText("");

        adapter.resetData(getDummyData());

    }

    private void writePedido(Pedido pedido) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("pedidos").push().setValue(pedido);

    }

    private static ArrayList<ProductoItem> getDummyData(){
        ArrayList<ProductoItem> result = new ArrayList<ProductoItem>();
        result.add(new ProductoItem("Empanada de chorizo", 1,false));
        result.add(new ProductoItem("Empanada de cecina", 1,false));
        result.add(new ProductoItem("Empanada de bonito", 1,false));
        result.add(new ProductoItem("Hogaza", 1,false));
        result.add(new ProductoItem("Melondro", 1,false));
        result.add(new ProductoItem("Montejo", 1,false));
        result.add(new ProductoItem("Barra pequeña", 1,false));
        result.add(new ProductoItem("Barra grande", 1,false));
        result.add(new ProductoItem("Lenguas de mantequilla", 1,false));
        result.add(new ProductoItem("Pastas de te", 1,false));
        result.add(new ProductoItem("Pastas de nueces", 1,false));
        result.add(new ProductoItem("Croissant", 1,false));
        result.add(new ProductoItem("Napolitana", 1,false));
        result.add(new ProductoItem("Donut", 1,false));
        return result;
    }




}
