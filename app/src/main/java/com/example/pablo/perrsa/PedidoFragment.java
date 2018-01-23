package com.example.pablo.perrsa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pablo on 6/1/18.
 */

public class PedidoFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Fragment uno");
        View rootView = inflater.inflate(R.layout.pedido_fragment, container, false);



        return rootView;
    }
}
