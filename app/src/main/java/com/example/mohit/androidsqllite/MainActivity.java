package com.example.mohit.androidsqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    EditText editfname;
    EditText editlname;
    EditText editid;
    Button btn;
    ListView lv;
    List<Student> data;
    ArrayList list;
    ArrayAdapter<String> adapter;
    Studentdb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editfname = (EditText) findViewById(R.id.editTextfname);
        editlname = (EditText) findViewById(R.id.editTextlname);
        editid = (EditText) findViewById(R.id.editTextid);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
        lv = (ListView) findViewById(R.id.listView);

        db = new Studentdb(this);
        //db.clearall();
        data = db.getAllStudent();
        list = getList(data);
        adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

    }

    public ArrayList getList(List<Student> data){
        ArrayList list = new ArrayList();

        for(int i = 0; i < data.size(); i++){
            Student student = data.get(i);
            String tmp = student.getFname()+ " " + student.getLname()+ " " + String.valueOf(student.getId());
            list.add(tmp);
        }

        return list;
    }

    @Override
    public void onClick(View v) {
        String fname = editfname.getText().toString();
        String lname = editlname.getText().toString();
        String id = editid.getText().toString();
        editfname.setText(null);
        editlname.setText(null);
        editid.setText(null);
        if(!(fname.isEmpty()) && !(lname.isEmpty()) && !(id.isEmpty())){

            Student student = new Student(fname,lname,Integer.parseInt(id));

            db.addStudent(student);
            updateList(student);
           // Toast.makeText(getBaseContext(),"Lis Updated",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getBaseContext(),"Lol Khupach ^_*",Toast.LENGTH_SHORT).show();
        }

    }

    public void updateList(Student student){
        String tmp = student.getFname()+ " " + student.getLname()+ " " + String.valueOf(student.getId());
        list.add(tmp);
        Toast.makeText(getBaseContext(),"adapter Updated",Toast.LENGTH_SHORT).show();
        //adapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getBaseContext(), (CharSequence) list.get(position),Toast.LENGTH_SHORT).show();

    }
}
