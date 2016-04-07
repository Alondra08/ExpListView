package com.example.ftsoporte.explistview;

/**
 * Created by ft.soporte on 06/04/2016.
 */

import android.widget.BaseExpandableListAdapter;



import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> header;
    private HashMap<String, List<String>> child;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.header = listDataHeader;
        this.child = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {


        return this.child.get(this.header.get(groupPosition)).get(
                childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        final String childText = (String) getChild(groupPosition, childPosition);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.childs, parent, false);
        }

        TextView child_text = (TextView) convertView.findViewById(R.id.child);

        child_text.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return this.child.get(this.header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {


        return this.header.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        return this.header.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header, parent, false);
        }

        TextView header_text = (TextView) convertView.findViewById(R.id.header);

        header_text.setText(headerTitle);

        if (isExpanded) {
            header_text.setTypeface(null, Typeface.BOLD);
        } else {
            header_text.setTypeface(null, Typeface.NORMAL);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}