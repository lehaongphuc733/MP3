package com.example.mp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BHAlbumAdapter extends BaseAdapter {
    private QLBHAlbumActivity context;
    private int layout;
    private List<BHAlbum> bhAlbumList;

    public BHAlbumAdapter(QLBHAlbumActivity context, int layout, List<BHAlbum> bhAlbumList) {
        this.context = context;
        this.layout = layout;
        this.bhAlbumList = bhAlbumList;
    }

    @Override
    public int getCount() {
        return bhAlbumList.size();
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
        ImageView imgHinh1,imgDeleteBH;
        TextView txtTenBaiHat1,txtTenCaSi1,txtNgayLuu;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;

        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder = new ViewHolder();

            //anhxa
            holder.imgHinh1 = (ImageView) view.findViewById(R.id.imgHinh1);
            holder.txtTenBaiHat1 = (TextView) view.findViewById(R.id.txtTenBaiHat1);
            holder.txtTenCaSi1 = (TextView) view.findViewById(R.id.txtTenCaSi1);
            holder.txtNgayLuu = (TextView) view.findViewById(R.id.txtNgayLuu);
            holder.imgDeleteBH = (ImageView) view.findViewById(R.id.imgDeleteBH);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(layout,null);
//
//        //anhxa
//        ImageView imgHinh1 = (ImageView) view.findViewById(R.id.imgHinh1);
//        TextView txtTenBaiHat1 = (TextView) view.findViewById(R.id.txtTenBaiHat1);
//        TextView txtTenCaSi1 = (TextView) view.findViewById(R.id.txtTenCaSi1);
//        TextView txtNgayLuu = (TextView) view.findViewById(R.id.txtNgayLuu);
//        ImageView imgDeleteBH = (ImageView) view.findViewById(R.id.imgDeleteBH);

        BHAlbum bhAlbum = bhAlbumList.get(i);
        holder.txtTenBaiHat1.setText(bhAlbum.getTenBaiHat());
        holder.txtTenCaSi1.setText(bhAlbum.getTenCaSi());
        holder.txtNgayLuu.setText(bhAlbum.getNgayLuu());
        new LoadImageInternet(holder.imgHinh1).execute(bhAlbum.getHinh());

        holder.txtTenCaSi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThucHienCongViec th = new ThucHienCongViec(24,bhAlbum.getTenCaSi());
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String chuoi = bhAlbum.getTenCaSi()+"+"+th.gt;
                context.ThongTinCaSi(chuoi);
            }
        });

        holder.imgDeleteBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.Dialog_XoaAlbum(bhAlbum.getTenBaiHat(),bhAlbum.getTenCaSi());
            }
        });
        return view;
    }
}
