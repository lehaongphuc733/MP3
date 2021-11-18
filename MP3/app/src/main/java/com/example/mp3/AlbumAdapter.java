package com.example.mp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AlbumAdapter extends BaseAdapter {
    private QLAlbumActivity context;
    private int Layout;
    private List<Album> albumList;

    public AlbumAdapter(QLAlbumActivity context, int layout, List<Album> albumList) {
        this.context = context;
        Layout = layout;
        this.albumList = albumList;
    }

    @Override
    public int getCount() {
        return albumList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTenAlbum;
        ImageView imgEdit,imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder ;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout,null);
            holder = new ViewHolder();

            //Anh xa view
            holder.txtTenAlbum = (TextView) view.findViewById(R.id.txtTenAlbum);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(Layout,null);
//
//        //Anh xa view
//        TextView txtTenAlbum = (TextView) view.findViewById(R.id.txtTenAlbum);
//        ImageView imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
//        ImageView imgDelete = (ImageView) view.findViewById(R.id.imgDelete);

        Album album = albumList.get(i);
        holder.txtTenAlbum.setText(album.getTenAlbum());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.Dialog_DeleteAlbum(album.getTenAlbum());
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.Dialog_EditAlbum(album.getTenAlbum());
            }
        });
        return view;
    }
}
