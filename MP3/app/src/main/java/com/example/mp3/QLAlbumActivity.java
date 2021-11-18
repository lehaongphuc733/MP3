package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QLAlbumActivity extends AppCompatActivity {
    ImageView imgAdd ,imgNext,imgPrevious;
    ListView ListAlbum;

    String Email = "";
    int skip = 2;
    int count_page = 0;


    AlbumAdapter albumAdapter;
    ArrayList<Album> albumArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlalbum);
        anhxa();

        Intent intent = getIntent();
        Email = intent.getStringExtra("QLAlbum");

        LayDS();

        ThucHienCongViec th = new ThucHienCongViec(23,Email);
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count_page = Integer.parseInt(th.gt);

        ListAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(QLAlbumActivity.this,QLBHAlbumActivity.class);
                intent2.putExtra("songalbum",Email+"+"+albumArrayList.get(position).getTenAlbum());
                startActivity(intent2);
            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_AddAlbum();

            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( (skip + 10) > (count_page +1) ){
                    skip = 2;
                }
                else
                    skip+=10;
                LayDS();
            }
        });


        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skip%12 == 0){
                    skip-=10;
                }
                else
                {
                    int t = (count_page-1)/10;
                    skip = 2 + 10*t;
                }
                LayDS();
            }
        });


    }

    public void Dialog_DeleteAlbum(String TenAlbum){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_xoalbum);
        dialog.setCanceledOnTouchOutside(false);


        //anhxa
        TextView txtXoaAlbum = (TextView) dialog.findViewById(R.id.txtXoaAlbum);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.btnXacNhan);
        Button btnDong = (Button) dialog.findViewById(R.id.btnDong);

        txtXoaAlbum.setText("Xóa Album - "+TenAlbum);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThucHienCongViec th =new ThucHienCongViec(16,Email+"+"+TenAlbum);
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
                show_Success();
                count_page--;
                if(skip>2 && count_page%10==0){
                    skip-=10;
                }
                LayDS();
            }
        });
        dialog.show();
    }
    public void show_Success(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.success);
        dialog.setCanceledOnTouchOutside(true);

        //anhxa
        Button btnOK = (Button) dialog.findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void Dialog_AddAlbum(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_addalbum);
        dialog.setCanceledOnTouchOutside(false);

        //AnhXa
        EditText edtNhapAlbumMoi = (EditText) dialog.findViewById(R.id.edtNhapAlbumMoi);
        Button btnThem = (Button) dialog.findViewById(R.id.btnThem);
        Button btnDong = (Button) dialog.findViewById(R.id.btnDong);


        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNhapAlbumMoi.getText().toString().length()==0){
                    Toast.makeText(QLAlbumActivity.this,"Vui lòng nhập tên Album!",Toast.LENGTH_LONG).show();
                    return ;
                }

                if(KiemTraTenAlbum(edtNhapAlbumMoi.getText().toString())==0){
                    Toast.makeText(QLAlbumActivity.this,"Tên Album không được có ký tự đặc biệt [Ngoại trừ _ và -]!",Toast.LENGTH_LONG).show();
                    return;
                }


                ThucHienCongViec th =new ThucHienCongViec(14,Email+"+"+edtNhapAlbumMoi.getText().toString());
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(th.gt.equals("EXISTED-ALBUMUSER")==true){
                    Toast.makeText(QLAlbumActivity.this,"Tên Album này đã tồn tại!",Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();
                show_Success();
                count_page++;
                Toast.makeText(QLAlbumActivity.this,String.valueOf(count_page),Toast.LENGTH_LONG).show();
                if(count_page>10 && (count_page-1)%10==0)
                {
                    skip += 10;
                }
                LayDS();
            }
        });
        dialog.show();
    }
    public void Dialog_EditAlbum(String TenAlbum) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_edit);
        dialog.setCanceledOnTouchOutside(false);

        //anhxa
        EditText edtSuaAlbum = (EditText) dialog.findViewById(R.id.edtTenAlbum);
        Button btnXacNhanEdit = (Button) dialog.findViewById(R.id.btnXacNhanEdit);
        Button btnDongEdit = (Button) dialog.findViewById(R.id.btnDongEdit);

        edtSuaAlbum.setText(TenAlbum);
        btnDongEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btnXacNhanEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSuaAlbum.getText().toString().length()==0)
                {
                    Toast.makeText(QLAlbumActivity.this,"Vui lòng nhập tên Album!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(KiemTraTenAlbum(edtSuaAlbum.getText().toString())==0){
                    Toast.makeText(QLAlbumActivity.this,"Tên Album không được có ký tự đặc biệt [Ngoại trừ _ và -]!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TenAlbum.equals(edtSuaAlbum.getText().toString())==true){
                    dialog.dismiss();
                    show_Success();
                    return;
                }
                ThucHienCongViec th = new ThucHienCongViec(15,Email+"+"+TenAlbum+"+"+edtSuaAlbum.getText().toString());
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(th.gt.equals("EXIST-ALBUM")){
                    Toast.makeText(QLAlbumActivity.this,"Tên Album này đã tồn tại!",Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();
                show_Success();
                LayDS();
            }
        });
        dialog.show();
    }
    public void anhxa() {
        imgAdd = (ImageView) findViewById(R.id.imgAdd);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        imgPrevious = (ImageView) findViewById(R.id.imgPrevious);
        ListAlbum = (ListView) findViewById(R.id.ListAlbum);
    }
    public int KiemTraTenAlbum(String TenAlbum){
        for(int i = 0 ; i < TenAlbum.length() ; i ++){
            if(TenAlbum.charAt(i)<'-')
                return 0;
            if(TenAlbum.charAt(i)>='.' && TenAlbum.charAt(i)<='/')
                return 0;
            if(TenAlbum.charAt(i)>=':' && TenAlbum.charAt(i)<='@')
                return 0;
            if(TenAlbum.charAt(i)>='[' && TenAlbum.charAt(i)<='^')
                return 0;
            if(TenAlbum.charAt(i)=='`')
                return 0;
            if(TenAlbum.charAt(i)>'z')
                return 0;
        }
        return 1;
    }
    public void LayDS() {
        albumArrayList = new ArrayList<>();

//        albumArrayList.add(new Album("abc"));
//        albumArrayList.add(new Album("def"));
//        albumArrayList.add(new Album("ghi"));
//        albumArrayList.add(new Album("jkl"));
//        albumArrayList.add(new Album("qwe"));

        ThucHienCongViec th= new ThucHienCongViec(5,Email+"+"+skip);
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String[] album = th.listX;
        if(album.length==1){
            if(album[0].length()==0)
            ListAlbum.setVisibility(View.INVISIBLE);
            else
                ListAlbum.setVisibility(View.VISIBLE);
        }
        for(int i = 0 ; i<album.length;i++){
            albumArrayList.add(new Album(album[i]));
        }

        albumAdapter = new AlbumAdapter(this,R.layout.dong_album,albumArrayList);
        ListAlbum.setAdapter(albumAdapter);
    }
}