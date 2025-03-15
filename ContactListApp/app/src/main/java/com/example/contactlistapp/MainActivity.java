package com.example.contactlistapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private String[] contacts={
            "Adithya - 9876543210",
            "Bhargav - 8765432109",
            "Chaitanya - 7654321098",
            "Damodar - 6543210987",
            "Eesha - 5432109876",
            "Farheen - 4321098765",
            "Gowtham - 3210987654",
            "Hemanth - 2109876543",
            "Indhra - 1098765432",
            "Jasmine - 9988776655"

    };
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedContact=contacts[position];
                Toast.makeText(MainActivity.this, "Selected contact: "+selectedContact,Toast.LENGTH_LONG).show();
            }
        });
    }
}