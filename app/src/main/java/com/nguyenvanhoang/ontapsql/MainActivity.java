package com.nguyenvanhoang.ontapsql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnExit,btnSelect,btnSave,btnDelete,btnUpdate;
    private EditText edtId,edtName,edtAddress,edtEmail;
    private GridView gridView;
    DBHelper dbHelper;
    private List<String> list_String = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        dbHelper = new DBHelper(this);
        for(Author author : dbHelper.getAllAuthor()){
            list_String.add(author.getId()+ " ");
            list_String.add(author.getName());
            list_String.add(author.getAddress());
            list_String.add(author.getEmail());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_String);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtId.getText().toString().trim().equals("")){
                    int id = Integer.parseInt(edtId.getText().toString());
                    String name = edtName.getText().toString();
                    String address = edtAddress.getText().toString();
                    String email = edtEmail.getText().toString();
                    if(dbHelper.insertAuthor(new Author(id,name,address,email)) > 0){
                        Toast.makeText(MainActivity.this,"Thêm thành công!!!",Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Them loi!!!",Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this,"Không được bỏ trống ID!!",Toast.LENGTH_LONG).show();

            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtId.getText().toString().trim().equals("")){
                    int id = Integer.parseInt(edtId.getText().toString());
                    Author author = dbHelper.searchAuthorById(id);
                    list_String.add(author.getId()+ " ");
                    list_String.add(author.getName());
                    list_String.add(author.getAddress());
                    list_String.add(author.getEmail());
                    adapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(MainActivity.this,"Không được bỏ trống ID!!",Toast.LENGTH_LONG).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtId.getText().toString().trim().equals("")){
                    int id = Integer.parseInt(edtId.getText().toString());
                    if(dbHelper.deleteAuthor(id) >0){
                        Toast.makeText(MainActivity.this,"Xoa thanh cong!!!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Xoa loi!!!",Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this,"Không được bỏ trống ID!!",Toast.LENGTH_LONG).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtId.getText().toString().trim().equals("")){
                    int id = Integer.parseInt(edtId.getText().toString());
                    String name = edtName.getText().toString();
                    String address = edtAddress.getText().toString();
                    String email = edtEmail.getText().toString();
                    if(dbHelper.updateAuthor(new Author(id,name,address,email)) >0){
                        Toast.makeText(MainActivity.this,"Update thanh cong!!!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Update loi!!!",Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this,"Không được bỏ trống ID!!",Toast.LENGTH_LONG).show();

            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn thoát?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
    public void connectView(){
        btnExit = (Button) findViewById(R.id.btnExit);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        edtId = (EditText) findViewById(R.id.edtId);
        edtName = (EditText) findViewById(R.id.edtName);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        gridView = (GridView) findViewById(R.id.gridView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuChon1:
                Toast.makeText(MainActivity.this,"Chon 1",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this,CustomListViewActivity.class));
                break;
            case R.id.menuChon2:
                Toast.makeText(MainActivity.this,"Chon 2",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}