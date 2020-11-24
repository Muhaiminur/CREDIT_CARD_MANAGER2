package com.debit_credit_card.creditcardmanager.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.debit_credit_card.creditcardmanager.ADAPTER.CardAdapter;
import com.debit_credit_card.creditcardmanager.DATABASE.TABLE.CARD_TABLE;
import com.debit_credit_card.creditcardmanager.LIBRARY.CenterZoomLayoutManager;
import com.debit_credit_card.creditcardmanager.LIBRARY.OverlapDecoration;
import com.debit_credit_card.creditcardmanager.LIBRARY.UTILITY;
import com.debit_credit_card.creditcardmanager.R;
import com.debit_credit_card.creditcardmanager.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding homeBinding;
    Context context;
    UTILITY utility;
    CardAdapter cardAdapter;
    List<CARD_TABLE> card_tables = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (homeBinding == null) {
            homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
            try {
                context = getActivity();
                utility = new UTILITY(context);
                card_tables.add(new CARD_TABLE("ABIR1", "12345678910111", "10/21", "sdnasjd", "VISA", "sdjksd", "dsdn"));
                card_tables.add(new CARD_TABLE("ABIR2", "12345678910111", "10/21", "sdnasjd", "MASTER", "sdjksd", "dsdn"));
                card_tables.add(new CARD_TABLE("ABIR3", "12345678910111", "10/21", "sdnasjd", "AMEX", "sdjksd", "dsdn"));
                card_tables.add(new CARD_TABLE("ABIR4", "12345678910111", "10/21", "sdnasjd", "DINERS", "sdjksd", "dsdn"));
                cardAdapter = new CardAdapter(card_tables, context);
                cardAdapter.notifyDataSetChanged();
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setReverseLayout(true);
                layoutManager.setStackFromEnd(true);
                //recyclerView.setLayoutManager(layoutManager);
                //homeBinding.cardRecyclerview.addItemDecoration(new OverlapDecoration());
                //homeBinding.cardRecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                homeBinding.cardRecyclerview.setLayoutManager(new CenterZoomLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                homeBinding.cardRecyclerview.setAdapter(cardAdapter);

            } catch (Exception e) {
                Log.d("Error Line Number", Log.getStackTraceString(e));
            }

        }
        return homeBinding.getRoot();
    }
}
