package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    ImageButton imgMenu;

    String Email = "NOEMAIL";


    TextView txtLogin,txtXemTatCa;




    ListView ListBHNoiBat;
    ArrayList<BH> arrayListBH;
    BHAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        AnhXa();
        setDSBH_NoiBat();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("login");

        if(bundle!=null) {
            String Email1 = bundle.getString("email");
            String HoTen = bundle.getString("hoten");
            txtLogin.setText(HoTen.toString());
            Email = Email1;
        }

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(txtLogin.getText().toString().equals("Đăng nhập")==true){
                    Intent intent1 = new Intent(MainActivity.this,DangNhapActivity.class);
                    startActivity(intent1);

                }
                else {
                    showMenu();
                }
            }
        });

        txtXemTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,DSNhacActivity.class);
                startActivity(intent1);
            }
        });

        ListBHNoiBat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String chuoi = arrayListBH.get(i).getSTT();
                Intent intent1 = new Intent(MainActivity.this,ListenActivity.class);
                intent1.putExtra("dulieu",Email+"+"+chuoi+"+"+"NOALBUM");
                startActivity(intent1);
//                Toast.makeText(MainActivity.this,arrayListBH.get(i).getSTT(),Toast.LENGTH_SHORT).show();
            }
        });



//        imgMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showMenu();
//            }
//        });
        adapter = new BHAdapter(this,R.layout.dong_bai_hat,arrayListBH);
        ListBHNoiBat.setAdapter(adapter);




    }
    public void showMenu(){
        PopupMenu popupMenu = new PopupMenu(this,txtLogin);
        popupMenu.getMenuInflater().inflate(R.menu.listmenu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.itemQLTK:
                        Intent intent = new Intent(MainActivity.this,SuaThongTInActivity.class);
                        intent.putExtra("updateinfo",Email+"+"+txtLogin.getText().toString());
                        startActivity(intent);
                         break;

                    case R.id.itemQLPlayList:
                        Intent intent1 = new Intent(MainActivity.this,QLAlbumActivity.class);
                        intent1.putExtra("QLAlbum",Email);
                        startActivity(intent1);
                         break;

                    case R.id.itemDSDaThich:
                        Intent intent2 = new Intent(MainActivity.this,QLBHAlbumActivity.class);
                        intent2.putExtra("songalbum",Email+"+"+"#LIKED");
                        startActivity(intent2);
                         break;

                    case R.id.itemDSCaSi:
                        break;

                    case R.id.itemTimKiem:
                        break;

                    case R.id.itemDangXuat:
                        txtLogin.setText("Đăng nhập");
                        Email = "NOEMAIL";
                        Toast.makeText(MainActivity.this,"Logout is successfull! - ",Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    public void setDSBH_NoiBat(){
        arrayListBH = new ArrayList<>();

        ThucHienCongViec th = new ThucHienCongViec(1,"NO");
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] listX = th.listX;
//        if(listX.length == 1 && listX[0].length() == 0){
//            ListBHNoiBat.setVisibility(View.INVISIBLE);
//            return;
//        }
        for(int i = 0 ; i < listX.length;i++)
        {
            String[] s = listX[i].split("\\+");
            String STT = s[0];
            String TenBaiHat = s[1];
            String TenCaSi = s[2];
            String SoLuotXem = s[3];
            String Hinh = s[4];
//            String FileNhac = s[5];
            arrayListBH.add(new BH(STT,TenBaiHat,TenCaSi,SoLuotXem,Hinh));
        }
    }
    public void ThongTinCaSi(String chuoi){
        Intent intent = new Intent(MainActivity.this,CaSiActivity.class);
        intent.putExtra("casi",chuoi+"+"+Email);
        startActivity(intent);
    }
    public void AnhXa() {



        txtLogin = (TextView) findViewById(R.id.txtLogin);
        txtXemTatCa = (TextView) findViewById(R.id.txtXemTatCa);
        ListBHNoiBat = (ListView) findViewById(R.id.ListViewBHNoiBat);
//        imgMenu = (ImageButton) findViewById(R.id.imgMenu);

    }


//


}