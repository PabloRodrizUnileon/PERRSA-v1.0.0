package com.example.pablo.perrsa;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pablo.perrsa.Adapter.PedidosList;
import com.example.pablo.perrsa.Objetos.Pedido;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 6/1/18.
 */

public class ListaPedidosTabFragment extends Fragment {


    ListView listViewPedidos;
    List<String> pedidos;
    List<Pedido> pedidoslist;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    String userUId = "";

    AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        userUId = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference("pedidos");
        pedidos = new ArrayList<String>();
        pedidoslist = new ArrayList<Pedido>();
        builder = new AlertDialog.Builder(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_pedidos, container, false);
        listViewPedidos = (ListView) rootView.findViewById(R.id.listView_pedidos);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pedidos.clear();
                pedidoslist.clear();


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Pedido pedido = postSnapshot.getValue(Pedido.class);
                    if (pedido.getUserId().equals(userUId)) {
                        pedidos.add(pedido.toString());
                        pedidoslist.add(pedido);
                    }


                }


                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, pedidos);
//                PedidosList pedidosList = new PedidosList(getActivity(), pedidos);
                //attaching adapter to the listview
                listViewPedidos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listViewPedidos.setOnItemLongClickListener((parent, view, position, id) -> {
            Pedido pedido = pedidoslist.get(position);
            builder.setTitle("Detalles del pedido");
            builder.setMessage(pedido.getPedidoDetailString());

            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        });


        return rootView;
    }


}
