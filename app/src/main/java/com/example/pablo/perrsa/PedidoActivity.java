package com.example.pablo.perrsa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pablo.perrsa.Adapter.MyRecyclerViewAdapter;
import com.example.pablo.perrsa.Objetos.Pedido;
import com.example.pablo.perrsa.Objetos.ProductoItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;


public class PedidoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    boolean isTablet;
    boolean mDualPane;
    RecyclerView recyclerViewProductos;
    MyRecyclerViewAdapter adapter;
    Map<String, ProductoItem> productoItemsList;
    String userUId = "jiji";
    String userName = "sd";
    private ActionBar actionBar;
    Pedido pedido;



    private Button btnAdd, btnBorrar, btn_siguiente;
    private EditText editText_ordenante, editText_pueblo, editText_direccion, editText_fechaPedido, editText_hora_pedido;
    private String ordenante, pueblo, direccion, fecha_pedido, hora_pedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pedido = (Pedido) getIntent().getExtras().getSerializable("pedido");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        isTablet = getResources().getBoolean(R.bool.isTablet);
        mAuth = FirebaseAuth.getInstance();
        userUId = mAuth.getCurrentUser().getUid();
        actionBar = ((AppCompatActivity)this).getSupportActionBar();


        editText_ordenante = findViewById(R.id.edit_cliente);
        editText_pueblo = findViewById(R.id.edit_pueblo);
        editText_direccion = findViewById(R.id.edit_direccion);
        editText_fechaPedido = findViewById(R.id.edit_fecha);
        editText_hora_pedido = findViewById(R.id.edit_hora);


        btnAdd = findViewById(R.id.btn_add);
        btnBorrar = findViewById(R.id.btn_borrar);
        btnAdd.setOnClickListener(v -> {
            ordenante = editText_ordenante.getText().toString();
            pueblo = editText_pueblo.getText().toString();
            direccion= editText_direccion.getText().toString();
            fecha_pedido = editText_fechaPedido.getText().toString();
            hora_pedido = editText_hora_pedido.getText().toString();

            pedido.setOrdenante(ordenante);
            pedido.setPueblo(pueblo);
            pedido.setDireccion(direccion);
            pedido.setFecha_pedido(fecha_pedido);
            pedido.setHora_pedido(hora_pedido);


            writePedido(pedido);
            Toast.makeText(this, "Producto añadido", Toast.LENGTH_SHORT).show();
        });

        btnBorrar.setOnClickListener(v -> {
            resetLayout();
        });


    }

    private void resetLayout() {

        resetTotalLayout();


    }

    private void resetTotalLayout(){
        editText_ordenante.setText("");
        editText_pueblo.setText("");
        editText_direccion.setText("");
        editText_fechaPedido.setText("");
        editText_hora_pedido.setText("");

    }

    private void writePedido(Pedido pedido) {

        String key = mDatabase.child("pedidos").push().getKey();
        mDatabase.child("pedidos").child(key).setValue(pedido);
        mDatabase.child("users").child(userUId).child("pedidos").setValue(key);


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
