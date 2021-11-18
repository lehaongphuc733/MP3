package com.example.mp3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BHAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<BH> listBH;

    public BHAdapter(MainActivity context, int layout, List<BH> listBH) {
        this.context = context;
        this.layout = layout;
        this.listBH = listBH;
    }

    @Override
    public int getCount() {
        return listBH.size();
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
        TextView txttenbaihat,txttencasi,txtsoluotxem;
        ImageView imgHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder = new ViewHolder();

            //Anh xa view
            holder.txttenbaihat = (TextView) view.findViewById(R.id.txtTenBaiHat);
            holder.txttencasi = (TextView) view.findViewById(R.id.txtTenCaSi);
            holder.txtsoluotxem = (TextView) view.findViewById(R.id.txtSoLuotXem);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imgHinh);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        BH bh = listBH.get(i);

        holder.txttenbaihat.setText(bh.getTenbaihat());
        holder.txttencasi.setText(bh.getTencasi());
        long slx = Integer.parseInt(bh.getSoluotxem());
        String SoLuotXem = hienthiSoLuong(slx);
        holder.txtsoluotxem.setText(SoLuotXem);
        new LoadImageInternet(holder.imgHinh).execute(bh.getHinh());

        holder.txttencasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThucHienCongViec th = new ThucHienCongViec(24,bh.getTencasi());
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String chuoi = bh.getTencasi()+"+"+th.gt;
                context.ThongTinCaSi(chuoi);
            }
        });
        return view;
    }
    public String hienthiSoLuong (long soluong) {
        if (soluong < 1000)
            return String.valueOf(soluong);

        if (soluong >= 1000 && soluong < 1000000) {
            double t = (double) (soluong / 1000.0);
            return t + "K";
        }

        if (soluong >= 1000000 && soluong < 1000000000) {
            double t = (double) (soluong / 1000000.0);
            return t + "Tr";
        }
        return ">= 1 Tỷ";
    }
}
