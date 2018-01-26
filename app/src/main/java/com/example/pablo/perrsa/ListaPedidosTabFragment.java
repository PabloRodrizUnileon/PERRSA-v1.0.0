package com.example.pablo.perrsa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pablo.perrsa.Adapter.PedidosList;
import com.example.pablo.perrsa.Objetos.Pedido;
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

    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference("pedidos");
        pedidos = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_pedidos, container, false);
        listViewPedidos = (ListView) rootView.findViewById(R.id.listView_pedidos);
        mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //clearing the previous artist list
                    pedidos.clear();

                    //iterating through all the nodes
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //getting artist
                        String pedido = postSnapshot.getValue(Pedido.class).toString();
                        //adding artist to the list
                        pedidos.add(pedido);
                    }

                    //creating adapter
                    final ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,pedidos);
//                PedidosList pedidosList = new PedidosList(getActivity(), pedidos);
                    //attaching adapter to the listview
                    listViewPedidos.setAdapter(adapter);
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return rootView;
    }


}
