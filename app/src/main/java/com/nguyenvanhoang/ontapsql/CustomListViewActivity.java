package com.nguyenvanhoang.ontapsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewActivity extends AppCompatActivity {
    private ListView listView;
    private DBHelper dbHelper;
    private Spinner spinner;
    private List<Author> authors = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);
        listView = (ListView) findViewById(R.id.listView);
        spinner = (Spinner) findViewById(R.id.spinner);
        dbHelper = new DBHelper(this);
        authors = dbHelper.getAllAuthor();
//        CustomListViewAuthor adapter = new CustomListViewAuthor(CustomListViewActivity.this,authors);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(CustomListViewActivity.this,"Ban da chon : "+ i,Toast.LENGTH_LONG ).show();
//            }
//        });
        //listView.setAdapter(adapter);
        List<String> stringList = new ArrayList<>();
        for(Author author : authors){
            stringList.add(author.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CustomListViewActivity.this,android.R.layout.simple_list_item_checked,stringList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CustomListViewActivity.this,"chon spiner" + i,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        System.out.println(spinner.getSelectedItem());

    }
}