package com.example.curencyconverterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView convertFromDropDownTextView, convertToDropDownTextView, conversionRateText;
    EditText amountToConvert;
    ArrayList<String> arrayList;
    Dialog fromDialog;
    Dialog toDialog;
    Button convertButton;
    String convertFromValue, convertToValue, conversionValue;
    String[] country = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertFromDropDownTextView=findViewById(R.id.convertFromDropDownMenu);
        convertToDropDownTextView=findViewById(R.id.convertToDropDownMenu);
        convertButton=findViewById(R.id.conversionButton);
        conversionRateText=findViewById(R.id.convertionRateText);
        amountToConvert=findViewById(R.id.amauntToCovertValueEditText);

        arrayList=new ArrayList<>();
        for(String i:country){
            arrayList.add(i);
        }

        convertFromDropDownTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDialog=new Dialog(MainActivity.this);
                fromDialog.setContentView(R.layout.from_spinner);
                fromDialog.show();

                EditText editText=fromDialog.findViewById(R.id.edit_text);
                ListView listView=fromDialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        convertFromDropDownTextView.setText(adapter.getItem(position));
                        fromDialog.dismiss();
                        convertFromValue=adapter.getItem(position);
                    }
                });
            }
        });

        convertToDropDownTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDialog=new Dialog(MainActivity.this);
                toDialog.setContentView(R.layout.to_spinner);
                toDialog.getWindow().setLayout(650,800);



            }
        });
    }
}