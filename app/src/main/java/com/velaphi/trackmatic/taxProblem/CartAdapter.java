package com.velaphi.trackmatic.taxProblem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.velaphi.trackmatic.R;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemViewHolder> {


    private List<SaleItem> itemList = new ArrayList<>();

    public TextView quantity, name, price;

    public CartAdapter() {
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View view) {
            super(view);
            quantity = view.findViewById(R.id.quantity_text_view);
            name = view.findViewById(R.id.name_text_view);
            price = view.findViewById(R.id.price_text_view);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        SaleItem saleItem = itemList.get(i);
        quantity.setText(Integer.toString(saleItem.getQuantity()));
        name.setText(saleItem.getItemName());
        price.setText("R" + String.format("%.2f", saleItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItems(List<SaleItem> saleItems) {
        this.itemList = saleItems;
        notifyDataSetChanged();
    }

    public void clear(){
        itemList.clear();
        notifyDataSetChanged();
    }

}
