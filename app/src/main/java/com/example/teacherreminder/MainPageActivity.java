package com.example.teacherreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teacherreminder.db.MyDatabase;
import com.example.teacherreminder.model.CourseListener;

import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity {

    Button buttonHelp;
    Button buttonAdd;
    Button buttonClose;
    ListView listView;
    myListAdapter myAdapter;

    MyDatabase DBConnector;
    Context context;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        buttonHelp = findViewById(R.id.buttonHelp);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonClose = findViewById(R.id.buttonClose);

        context = this;
        DBConnector = new MyDatabase(this);

        listView = findViewById(R.id.listenersList);
        myAdapter = new myListAdapter(this, DBConnector.selectAll());
        listView.setAdapter(myAdapter);

        View.OnClickListener listener = new View.OnClickListener() {

            Intent intent;

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buttonAdd:
                        intent = new Intent(MainPageActivity.this, AddListenerActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.buttonHelp:
                        intent = new Intent(MainPageActivity.this, HelpActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.buttonClose:
                        finish();
                        break;
                }
            }
        };

        buttonHelp.setOnClickListener(listener);
        buttonAdd.setOnClickListener(listener);
        buttonClose.setOnClickListener(listener);
    }

    class myListAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private ArrayList<CourseListener> arrayMyCourseListeners;

        public myListAdapter (Context ctx, ArrayList<CourseListener> arr) {
            mLayoutInflater = LayoutInflater.from(ctx);
            setArrayMyData(arr);
        }

        public ArrayList<CourseListener> getArrayMyData() {
            return arrayMyCourseListeners;
        }

        public void setArrayMyData(ArrayList<CourseListener> arrayMyData) {
            this.arrayMyCourseListeners = arrayMyData;
        }

        public int getCount () {
            return arrayMyCourseListeners.size();
        }

        public Object getItem (int position) {
            return position;
        }

        public long getItemId (int position) {
            CourseListener md = arrayMyCourseListeners.get(position);
            if (md != null) {
                return md.getId();
            }
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null)
                convertView = mLayoutInflater.inflate(R.layout.item, null);

            TextView name = convertView.findViewById(R.id.listenerName);
            TextView login = convertView.findViewById(R.id.listenerLogin);
            TextView email = convertView.findViewById(R.id.listenerEmail);
            TextView info = convertView.findViewById(R.id.listenerInfo);

            CourseListener md = arrayMyCourseListeners.get(position);
            name.setText(md.getName());
            login.setText(md.getLogin());
            email.setText(md.getEmail());
            info.setText(md.getInfo());

            return convertView;
        }
    }
}
