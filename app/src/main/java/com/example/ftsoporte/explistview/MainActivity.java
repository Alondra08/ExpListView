package com.example.ftsoporte.explistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);


        expandableListView.setGroupIndicator(null);

        setItems();
        setListener();

    }
    void setItems() {

        ArrayList<String> header = new ArrayList<String>();

        List<String> Matemáticas = new ArrayList<String>();
        List<String> Anatomia = new ArrayList<String>();
        List<String> Ciencias = new ArrayList<String>();
        List<String> Comunicación = new ArrayList<String>();

        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        for (int i = 1; i < 5; i++) {
            header.add("Curso " + i);

        }
        for (int i = 1; i < 5; i++) {
            Matemáticas.add("Curso A  - " + " : Curso" + i);

        }
        for (int i = 1; i < 5; i++) {
            Anatomia.add("Curso B  - " + " : Curso" + i);

        }
        for (int i = 1; i < 6; i++) {
            Ciencias.add("Curso C  - " + " : Curso" + i);

        }

        for (int i = 1; i < 7; i++) {
            Comunicación.add("Curso D  - " + " : Curso" + i);

        }

        hashMap.put(header.get(0), Matemáticas);
        hashMap.put(header.get(1), Anatomia);
        hashMap.put(header.get(2), Ciencias);
        hashMap.put(header.get(3), Comunicación);

        adapter = new ExpandableListAdapter(MainActivity.this, header, hashMap);

        expandableListView.setAdapter(adapter);
    }


    void setListener() {


        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                Toast.makeText(MainActivity.this,
                        " " + adapter.getGroup(group_pos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        expandableListView
                .setOnGroupExpandListener(new OnGroupExpandListener() {


                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)


                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });


        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                Toast.makeText(
                        MainActivity.this,
                        "" + adapter.getChild(groupPos, childPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}