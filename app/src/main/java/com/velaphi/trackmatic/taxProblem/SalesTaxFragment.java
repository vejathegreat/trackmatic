package com.velaphi.trackmatic.taxProblem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.velaphi.trackmatic.R;

import java.util.ArrayList;
import java.util.List;

public class SalesTaxFragment extends Fragment {

    Spinner spinner;
    Button addToCartButton;
    Button clearCartButton;
    Button calculateButton;
    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter;
    List<SaleItem> itemList = new ArrayList<>();
    String[] taxableItems;
    String[]importedItems;
    EditText price, quantity;
    TextView outputTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sales_tax_fragment, container, false);
        setupView(view);
        setupRecyclerView();
        setupSpinner();
        setupList();
        return view;
    }

    private void setupList() {
        taxableItems = getResources().getStringArray(R.array.sale_items_tax_free);
        importedItems = getResources().getStringArray(R.array.imported_items);
    }

    private void setupRecyclerView() {
        cartAdapter = new CartAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        cartRecyclerView.setLayoutManager(mLayoutManager);
        cartRecyclerView.setItemAnimator(new DefaultItemAnimator());
        cartRecyclerView.setAdapter(cartAdapter);
    }

    private void setupView(View view) {
        spinner = view.findViewById(R.id.spinner);
        price = view.findViewById(R.id.price_edit_text);
        quantity = view.findViewById(R.id.quantity_edit_text);
        addToCartButton = view.findViewById(R.id.add_to_cart_button);
        clearCartButton = view.findViewById(R.id.clear_button);
        calculateButton = view.findViewById(R.id.calculate_button);
        cartRecyclerView = view.findViewById(R.id.itemsRecyclerView);
        outputTextView = view.findViewById(R.id.output_text_view);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        });

        clearCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.clear();
                cartAdapter.clear();
                price.setText(R.string.r0_00);
                quantity.setText(R.string._0);
                outputTextView.setText("");
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(quantity.getText().toString()) > 0) &&
                        (Double.parseDouble(price.getText().toString()) > 0)) {
                    addItemToCart();
                }
            }
        });

    }

    private void addItemToCart() {
        SaleItem saleItem = new SaleItem();
        boolean added = false;

        String name = spinner.getSelectedItem().toString();
        saleItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
        saleItem.setPrice(Double.parseDouble(price.getText().toString()));
        saleItem.setItemName(name);
        saleItem.setImported(isImportItem(name));
        saleItem.setTaxFree(isTaxItem(name));

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getItemName().equals(saleItem.getItemName())) {
                int newQuantity = itemList.get(i).getQuantity() + saleItem.getQuantity();
                double newPrice = itemList.get(i).getPrice() + saleItem.getPrice();
                saleItem.setQuantity(newQuantity);
                saleItem.setPrice(newPrice);
                itemList.set(i, saleItem);
                cartAdapter.setItems(itemList);
                added = true;
            }
        }

        if (!added) {
            itemList.add(saleItem);
            cartAdapter.setItems(itemList);
        }
    }

    private void setupSpinner() {
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    private boolean isImportItem(String name){
        for(String item: importedItems){
            if(item.equals(name)){
               return true;
            }
        }
        return false;
    }

    private boolean isTaxItem(String name){
        for(String item: taxableItems){
            if(item.equals(name)){
                return true;
            }
        }
        return false;
    }

    private void calculateTotal(){
      StringBuilder result = new StringBuilder();
      double basicSalesTaxTotal = 0;
      double importDutyTotal = 0;
      double totalPrice = 0;

      for (SaleItem item: itemList){

          if(!item.isTaxFree()){
              basicSalesTaxTotal += getSaleTax(item.getPrice());
          }

          if(item.isImported()){
              importDutyTotal += getImportDuty(item.getPrice());
          }

          totalPrice += item.getPrice() + importDutyTotal;

          result.append(item.getQuantity())
                  .append(" ")
                  .append(item.getItemName())
                  .append(" ")
                  .append("R")
                  .append(item.getPrice())
                  .append("\n");
      }

        totalPrice += basicSalesTaxTotal;

                result.append("Sales Tax: R").append(String.format("%.2f", basicSalesTaxTotal)).append("\n");
      result.append("Total: R").append(String.format("%.2f", totalPrice));
      outputTextView.setText(result);
    }

    private double getImportDuty(double price){

        return price * 0.5;
    }

    private double getSaleTax(double price){

        return price * 0.1;
    }
}