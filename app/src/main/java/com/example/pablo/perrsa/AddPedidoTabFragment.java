package com.example.pablo.perrsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Pablo on 6/1/18.
 */

public class AddPedidoTabFragment extends Fragment {

    //  Constantes
    private static final int PEDIDO_ACTIVITY_REQUEST_CODE = 23;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase_Productos;
// ...


    private FirebaseAuth mAuth;

    boolean isTablet;
    boolean mDualPane;
    RecyclerView recyclerViewProductos;
    MyRecyclerViewAdapter adapter;
    List<ProductoItem> listaProductos;

    Map<String, ProductoItem> productoItemsList;
    String userUId = "";
    String userName = "";
    private ActionBar actionBar;


    private Button btnAdd, btnBorrar, btn_siguiente;
    private EditText editText_ordenante, editText_pueblo, editText_direccion, editText_fechaPedido, editText_hora_pedido;
    private String ordenante, pueblo, direccion, fecha_pedido, hora_pedido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase_Productos = FirebaseDatabase.getInstance().getReference("products");
        listaProductos = new ArrayList<>();
        isTablet = getResources().getBoolean(R.bool.isTablet);
        mAuth = FirebaseAuth.getInstance();
        userUId = mAuth.getCurrentUser().getUid();
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = dataSnapshot.child("users").child(userUId).child("name").getValue(String.class);
                actionBar.setTitle(userName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_pedido, container, false);
        mDualPane = rootView.findViewById(R.id.pedidoFragment) != null;

        recyclerViewProductos = rootView.findViewById(R.id.recycler_view_layour_recycler);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyRecyclerViewAdapter(getContext(), listaProductos);
        recyclerViewProductos.setAdapter(adapter);
//        recyclerViewProductos.setItemViewCacheSize(getDummyData().size());

        if (mDualPane) {
            editText_ordenante = rootView.findViewById(R.id.edit_cliente);
            editText_pueblo = rootView.findViewById(R.id.edit_pueblo);
            editText_direccion = rootView.findViewById(R.id.edit_direccion);
            editText_fechaPedido = rootView.findViewById(R.id.edit_fecha);
            editText_hora_pedido = rootView.findViewById(R.id.edit_hora);
            btnAdd = rootView.findViewById(R.id.btn_add);
            btnBorrar = rootView.findViewById(R.id.btn_borrar);
            setListenersDualPane();
        } else {

            btn_siguiente = rootView.findViewById(R.id.btn_siguiente);
            btnBorrar = rootView.findViewById(R.id.btn_resetData);
            setListenersSinglePane();
        }

        mDatabase_Productos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaProductos.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ProductoItem productoItem = postSnapshot.getValue(ProductoItem.class);
                    productoItem.setId(postSnapshot.getKey());

                    listaProductos.add(productoItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return rootView;
    }

    private void setListenersSinglePane() {
        Pedido pedido = new Pedido();
        Bundle data = new Bundle();

        btn_siguiente.setOnClickListener(v -> {
            if (adapter.getProductosAdd().isEmpty()) {
                Toast.makeText(getActivity(), "SELECCIONAR PRODUCTO Y CANTIDAD", Toast.LENGTH_SHORT).show();
            } else {
                pedido.setProductos(adapter.getProductosAdd());
                Intent intent = new Intent(getActivity(), PedidoActivity.class);

                data.putSerializable("pedido", pedido);
                intent.putExtras(data);
                startActivityForResult(intent, PEDIDO_ACTIVITY_REQUEST_CODE);

            }
        });

        btnBorrar.setOnClickListener(v -> {
            adapter = new MyRecyclerViewAdapter(getContext(), listaProductos);
            recyclerViewProductos.setAdapter(adapter);
        });
    }


    private void setListenersDualPane() {
        btnAdd.setOnClickListener(v -> {
            ordenante = editText_ordenante.getText().toString();
            pueblo = editText_pueblo.getText().toString();
            direccion = editText_direccion.getText().toString();
            fecha_pedido = editText_fechaPedido.getText().toString();
            hora_pedido = editText_hora_pedido.getText().toString();

            productoItemsList = adapter.getProductosAdd();
            Pedido pedido = new Pedido(ordenante, pueblo, direccion, fecha_pedido, hora_pedido, productoItemsList);
            writePedido(pedido);
            Toast.makeText(getContext(), "Producto añadido", Toast.LENGTH_SHORT).show();
        });

        btnBorrar.setOnClickListener(v -> {
            resetLayout();
            adapter = new MyRecyclerViewAdapter(getContext(), listaProductos);
            recyclerViewProductos.setAdapter(adapter);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PEDIDO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //  Resetea el adapter cuando vuelve de la activity con éxito.
                adapter = new MyRecyclerViewAdapter(getContext(), listaProductos);
                recyclerViewProductos.setAdapter(adapter);
            }
        }
    }

    private void resetLayout() {

        editText_ordenante.setText("");
        editText_pueblo.setText("");
        editText_direccion.setText("");
        editText_fechaPedido.setText("");
        editText_hora_pedido.setText("");

    }

    private void writePedido(Pedido pedido) {

        String key = mDatabase.child("pedidos").push().getKey();
        pedido.setPushId(key);
        pedido.setUserId(userUId);
        mDatabase.child("pedidos").child(key).setValue(pedido);
        mDatabase.child("users").child(userUId).child("pedidos").child(key).setValue(true);
//        mDatabase.child("users").child(userUId).child("pedidos").push().setValue(key);


    }


}
