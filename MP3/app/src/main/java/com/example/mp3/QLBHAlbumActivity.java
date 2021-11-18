package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QLBHAlbumActivity extends AppCompatActivity {
    TextView txtTenAlbumThem;
    ListView ListAlbumThem;
    ImageView imgNextOnALbum,imgPreviousOnALbum;

    ArrayList<BHAlbum> arrayListBH;

    BHAlbumAdapter adapter;
    String email = "NOEMAIL";
    String album = "NOALBUM";
    int skip = 0;

    int count_page = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlbhalbum);
        anhxa();

        Intent intent = getIntent();
        String s = intent.getStringExtra("songalbum");
        String[] chuoi = s.split("\\+");
        email = chuoi[0];
        album = chuoi[1];



        if(album.equals("#LIKED")==true){
            txtTenAlbumThem.setText("Danh sách bài hát đã thích");
        }
        else
        {
            txtTenAlbumThem.setText(album);
        }

//        Toast.makeText(this,String.valueOf(email)+" - "+album,Toast.LENGTH_LONG).show();
        setDSBH_Album();
        adapter = new BHAlbumAdapter(this,R.layout.dong_bhalbum,arrayListBH);
        ListAlbumThem.setAdapter(adapter);

        ThucHienCongViec th = new ThucHienCongViec(22,email+"+"+album);
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count_page = Integer.parseInt(th.gt);


        ListAlbumThem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chuoi = arrayListBH.get(position).getSTT();
                Toast.makeText(QLBHAlbumActivity.this,chuoi,Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(QLBHAlbumActivity.this,ListenActivity.class);
                intent1.putExtra("dulieu",email+"+"+chuoi+"+"+album);
                startActivity(intent1);
            }
        });


        //Chua hoan thien
        imgNextOnALbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_page == 2 ){
                    return;
                }
                if( (skip + 2) < count_page ){
                    skip += 2;
                }
                else
                    skip = 0;
                setDSBH_Album();
                adapter = new BHAlbumAdapter(QLBHAlbumActivity.this,R.layout.dong_bhalbum,arrayListBH);
                ListAlbumThem.setAdapter(adapter);
//                Toast.makeText(QLBHAlbumActivity.this,String.valueOf(count_page),Toast.LENGTH_LONG).show();
            }
        });

        //Chua hoan thien
        imgPreviousOnALbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_page == 2 ){
                    return;
                }
                if(skip == 0){
                    int t = (count_page - 1)/2;
                    skip = t*2;
                }
                else
                {
                    skip -= 2;
                }
                setDSBH_Album();
                adapter = new BHAlbumAdapter(QLBHAlbumActivity.this,R.layout.dong_bhalbum,arrayListBH);
                ListAlbumThem.setAdapter(adapter);
            }
        });




    }

    public void anhxa(){
        txtTenAlbumThem = (TextView) findViewById(R.id.txtTenAlbumThem);
        ListAlbumThem = (ListView) findViewById(R.id.ListAlbumThem);
        imgNextOnALbum = (ImageView) findViewById(R.id.imgNextOnALbum);
        imgPreviousOnALbum = (ImageView) findViewById(R.id.imgPreviousOnALbum);
    }

    public void setDSBH_Album(){
        arrayListBH = new ArrayList<>();

        ThucHienCongViec th = new ThucHienCongViec(6,email+"+"+album+"+"+skip);
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] listX = th.listX;
        if(listX.length == 1 && listX[0].length()==0){
            ListAlbumThem.setVisibility(View.INVISIBLE);
            return;
        }
        for(int i = 0 ; i < listX.length;i++)
        {
            String[] s = listX[i].split("\\+");
            String TenBaiHat = s[0];
            String TenCaSi = s[1];
            String NgayLuu = s[2];
            String STT = s[3];
            String Hinh = s[4];

            arrayListBH.add(new BHAlbum(STT,TenBaiHat,TenCaSi,NgayLuu,Hinh));
        }
    }
    public void Dialog_XoaAlbum(String tenbaihat,String tencasi) {
        ThucHienCongViec th = new ThucHienCongViec(12,email+"+"+album+"+"+tenbaihat+"+"+tencasi);
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(QLBHAlbumActivity.this,"Thành công!",Toast.LENGTH_LONG).show();

        count_page--;
        if(skip > 0 && count_page%2==0){
            skip -= 2;
        }
        setDSBH_Album();
        adapter = new BHAlbumAdapter(this,R.layout.dong_bhalbum,arrayListBH);
        ListAlbumThem.setAdapter(adapter);
    }
    public void ThongTinCaSi(String chuoi){
        Intent intent = new Intent(QLBHAlbumActivity.this,CaSiActivity.class);
        intent.putExtra("casi",chuoi+"+"+email);
        startActivity(intent);
    }
}