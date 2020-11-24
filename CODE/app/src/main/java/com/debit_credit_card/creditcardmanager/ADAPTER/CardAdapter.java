package com.debit_credit_card.creditcardmanager.ADAPTER;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.debit_credit_card.creditcardmanager.DATABASE.TABLE.CARD_TABLE;
import com.debit_credit_card.creditcardmanager.LIBRARY.UTILITY;
import com.debit_credit_card.creditcardmanager.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.Todo_View_Holder> {
    Context context;
    List<CARD_TABLE> cat_list;
    UTILITY utility;


    public CardAdapter(List<CARD_TABLE> to, Context c) {
        cat_list = to;
        context = c;
        utility = new UTILITY(context);
    }

    public class Todo_View_Holder extends RecyclerView.ViewHolder {
        CardView card_view;
        ImageView card_logo;
        TextView card_number;
        TextView card_name;
        TextView card_expire;

        public Todo_View_Holder(View view) {
            super(view);
            card_view = view.findViewById(R.id.card_view);
            card_logo = view.findViewById(R.id.card_logo);
            card_number = view.findViewById(R.id.card_number);
            card_name = view.findViewById(R.id.card_name);
            card_expire = view.findViewById(R.id.card_expire);
        }
    }

    @Override
    public CardAdapter.Todo_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card, parent, false);

        return new CardAdapter.Todo_View_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(final CardAdapter.Todo_View_Holder holder, int position) {
        final CARD_TABLE bodyResponse = cat_list.get(position);
        try {
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("category_id", bodyResponse.getCatid().toString());
//                    Fragment navhost = ((AppCompatActivity) context).getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//                    NavController c = NavHostFragment.findNavController(navhost);
//                    //c.popBackStack();
//                    c.navigate(R.id.frag_subcategory, bundle);
                }
            });
            holder.card_number.setText(bodyResponse.getCard_number());
            holder.card_name.setText(bodyResponse.getCard_name());
            holder.card_expire.setText(bodyResponse.getCard_expire());
            if (bodyResponse.getCard_type().equalsIgnoreCase("VISA")) {
                holder.card_logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_visacard));
            } else if (bodyResponse.getCard_type().equalsIgnoreCase("MASTER")) {
                holder.card_logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_mastercard));
            } else if (bodyResponse.getCard_type().equalsIgnoreCase("AMEX")) {
                holder.card_logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_amexcard));
            } else if (bodyResponse.getCard_type().equalsIgnoreCase("DINERS")) {
                holder.card_logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dinnerscard));
            } else if (bodyResponse.getCard_type().equalsIgnoreCase("DISCOVER")) {
                holder.card_logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_discovercard));
            } else if (bodyResponse.getCard_type().equalsIgnoreCase("JCB")) {
                holder.card_logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_jcbcard));
            }
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    @Override
    public int getItemCount() {
        return cat_list.size();
    }
}