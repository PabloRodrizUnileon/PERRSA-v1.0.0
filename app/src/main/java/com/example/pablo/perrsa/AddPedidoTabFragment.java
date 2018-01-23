package com.example.pablo.perrsa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Pablo on 6/1/18.
 */

public class AddPedidoTabFragment extends Fragment {

    boolean isTablet;

    View card1, card2, card3, card4;
    ImageView img1, img2, img3, img4;
    TextView txt1, txt2, txt3, txt4;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTablet = getResources().getBoolean(R.bool.isTablet);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Fragment uno");
        View rootView = inflater.inflate(R.layout.fragment_add_pedido, container, false);

        card1 = rootView.findViewById(R.id.include);
        txt1 = card1.findViewById(R.id.title);

        card2 = rootView.findViewById(R.id.include2);
        txt2 = card2.findViewById(R.id.title);

        card3 = rootView.findViewById(R.id.include3);
        txt3 = card3.findViewById(R.id.title);

        card4 = rootView.findViewById(R.id.include4);
        txt4 = card4.findViewById(R.id.title);


        img1 = card1.findViewById(R.id.thumbnail);
        img2 = card2.findViewById(R.id.thumbnail);
        img3 = card3.findViewById(R.id.thumbnail);
        img4 = card4.findViewById(R.id.thumbnail);
        img1.setImageResource(R.drawable.pan2);
        img2.setImageResource(R.drawable.pastas2);
        img3.setImageResource(R.drawable.empanada);
        img4.setImageResource(R.drawable.reposteria);


        txt1.setText("PANADERIA");


        txt2.setText("PASTAS");


        txt3.setText("EMPANADAS");


        txt4.setText("REPOSTERIA");


        setListeners();

        return rootView;
    }

    private void setListeners() {
        card1.setOnClickListener(view -> {
            clickOnCard(txt1);
        });

        card2.setOnClickListener(view -> {
            clickOnCard(txt2);
        });

        card3.setOnClickListener(view -> {
            clickOnCard(txt3);
        });

        card4.setOnClickListener(view -> {
            clickOnCard(txt4);
        });

    }

    private void clickOnCard(TextView textView) {


        resetCardsTouch();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, img1.getId());
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(32);
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView.setBackgroundColor(getResources().getColor(R.color.shadeexpandido));
    }

    private void resetCardsTouch() {

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, img1.getId());
        txt1.setLayoutParams(layoutParams);
        txt1.setTextSize(18);
        txt1.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
        txt1.setBackgroundColor(getResources().getColor(R.color.shadeInicial));

        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, img2.getId());
        txt2.setLayoutParams(layoutParams);
        txt2.setTextSize(18);
        txt2.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
        txt2.setBackgroundColor(getResources().getColor(R.color.shadeInicial));

        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, img3.getId());
        txt3.setLayoutParams(layoutParams);
        txt3.setTextSize(18);
        txt3.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
        txt3.setBackgroundColor(getResources().getColor(R.color.shadeInicial));

        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, img4.getId());
        txt4.setLayoutParams(layoutParams);
        txt4.setTextSize(18);
        txt4.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
        txt4.setBackgroundColor(getResources().getColor(R.color.shadeInicial));

    }


}
