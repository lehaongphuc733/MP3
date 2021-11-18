package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuaThongTInActivity extends AppCompatActivity {

    EditText edtSuaEmail,edtNhapHoTen;
    Button btnDoiMK , btnCapNhat;

    String Email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin);
        anhxa();

        Intent intent = getIntent();
        String s = intent.getStringExtra("updateinfo");
        String[] ss = s.split("\\+");
        edtSuaEmail.setText(ss[0]);
        edtNhapHoTen.setText(ss[1]);
        Email = ss[0];

        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SuaThongTInActivity.this,DoiMatKhauActivity.class);
                intent1.putExtra("DoiMK",Email);
                startActivity(intent1);
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSuaEmail.getText().length()==0 ||edtNhapHoTen.getText().length()==0) {
                    Toast.makeText(SuaThongTInActivity.this,"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_LONG).show();
                    return;
                }
                KiemTraEmail kt = new KiemTraEmail();
                if(kt.validateEmailAddress(edtSuaEmail)==false){
                    Toast.makeText(SuaThongTInActivity.this,"Email không hợp lệ!",Toast.LENGTH_LONG).show();
                    return;
                }
                String t = Email+"+"+edtSuaEmail.getText().toString()+"+"+edtNhapHoTen.getText().toString();
                ThucHienCongViec th = new ThucHienCongViec(10,t);
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(th.gt.equals("EMAIL-EXISTED")==true){
                    Toast.makeText(SuaThongTInActivity.this,"Emai này đã tồn tại!",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(SuaThongTInActivity.this,"Update successfull!",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(SuaThongTInActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("email",edtSuaEmail.getText().toString());
                bundle.putString("hoten",edtNhapHoTen.getText().toString());
                intent.putExtra("login",bundle);
                startActivity(intent1);
            }
        });


    }
    public void anhxa() {
        edtSuaEmail = (EditText) findViewById(R.id.edtSuaEmail);
        edtNhapHoTen = (EditText) findViewById(R.id.edtNhaphoten);
        btnDoiMK = (Button) findViewById(R.id.btnDoiMK);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
    }
}