package com.nguyenvanhoang.ontapsql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListViewAuthor extends BaseAdapter {
    private Context context;
    private List<Author> authors;

    public CustomListViewAuthor(Context context, List<Author> authors) {
        this.context = context;
        this.authors = authors;
    }

    @Override
    public int getCount() {
        return authors.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodel viewHodel;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_listview,viewGroup,false);
            viewHodel = new ViewHodel();
            viewHodel.tvId = (TextView) view.findViewById(R.id.tvId);
            viewHodel.tvName = (TextView) view.findViewById(R.id.tvName);
            viewHodel.tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            viewHodel.tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            view.setTag(viewHodel);
        }
        else{
            viewHodel = (ViewHodel) view.getTag();
        }
        viewHodel.tvId.setText(authors.get(i).getId()+"");
        viewHodel.tvName.setText(authors.get(i).getName());
        viewHodel.tvAddress.setText(authors.get(i).getAddress());
        viewHodel.tvEmail.setText(authors.get(i).getEmail());
        return view;
    }
    private class ViewHodel{
        TextView tvId,tvName,tvAddress,tvEmail;
    }
}
