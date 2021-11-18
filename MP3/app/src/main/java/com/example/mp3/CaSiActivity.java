package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CaSiActivity extends AppCompatActivity {

    ImageView imgHinhCaSi , imgChualike;
    TextView txtCaSiName , txtTieuSu , txtSoLuotYeuThich;



    int like = 0;
    String email = "NOEMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_si);
        anhxa();



        Intent intent = getIntent();
        String chuoi = intent.getStringExtra("casi");
        String[] s = chuoi.split("\\+");
        String tencasi = s[0];
        String hinh = s[1];
        String tieusu = s[2];
        String soluotthich = s[3];
        email = s[4];

        if(email.equals("NOEMAIL")==true){
            imgChualike.setVisibility(View.INVISIBLE);
        }


        txtCaSiName.setText(tencasi);
        txtTieuSu.setText(tieusu);
        long slyt = Integer.parseInt(soluotthich);
        String SoLuotThich = hienthiSoLuong(slyt);
        txtSoLuotYeuThich.setText("Số lượt yêu thích: "+SoLuotThich);
        new LoadImageInternet(imgHinhCaSi).execute(hinh);

        KiemTraLiked_CaSi();


        imgChualike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soluotthich = "";
                long slt = 0;
                if(like == 0){
                    like = 1;
                    imgChualike.setImageResource(R.drawable.like1);
                    soluotthich = ChuyenDoiLiked_CaSi();
                    slt = Integer.parseInt(soluotthich)+1;
                    txtSoLuotYeuThich.setTextColor(Color.parseColor("#E91E63"));
                }
                else
                {
                    like = 0;
                    imgChualike.setImageResource(R.drawable.unlike1);
                    soluotthich = ChuyenDoiLiked_CaSi();
                    slt = Integer.parseInt(soluotthich)-1;
                    txtSoLuotYeuThich.setTextColor(Color.parseColor("#FFFFFF"));
                }

                String SLT = hienthiSoLuong(slt);
                txtSoLuotYeuThich.setText("Số lượt yêu thích: "+SLT);

            }
        });
    }

    public void anhxa() {

        imgHinhCaSi = (ImageView) findViewById(R.id.imgHinhCaSi);
        imgChualike = (ImageView) findViewById(R.id.imgChualike);
        txtCaSiName = (TextView) findViewById(R.id.txtCaSiName);
        txtTieuSu = (TextView) findViewById(R.id.txtTieuSu);
        txtSoLuotYeuThich = (TextView) findViewById(R.id.txtSoLuotYeuThich);
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
    public void KiemTraLiked_CaSi(){
        ThucHienCongViec th = new ThucHienCongViec(19,email+"+"+txtCaSiName.getText());
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(th.gt.equals("NOLIKED-CSUSER")==false)
        {
            imgChualike.setImageResource(R.drawable.like1);
            like = 1;
            txtSoLuotYeuThich.setTextColor(Color.parseColor("#E91E63"));
        }
    }
    public String ChuyenDoiLiked_CaSi(){
        ThucHienCongViec th = new ThucHienCongViec(17,email+"+"+txtCaSiName.getText());
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return th.gt;
    }
}