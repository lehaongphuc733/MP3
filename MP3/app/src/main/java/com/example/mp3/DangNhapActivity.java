package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DangNhapActivity extends AppCompatActivity {

    EditText edtEmail,edtMatKhau;
    Button btnDangNhap,btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhxa();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten2 = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(inten2);
            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edtEmail.getText().toString();
                String MatKhau = edtMatKhau.getText().toString();
                if(Email.length()==0 || MatKhau.length()==0)  {
                    Toast.makeText(DangNhapActivity.this,"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_LONG).show();
                    return;
                }
                KiemTraEmail kt = new KiemTraEmail();
                if(kt.validateEmailAddress(edtEmail)==false){
                    Toast.makeText(DangNhapActivity.this,"Email không hợp lệ!",Toast.LENGTH_LONG).show();
                    return;
                }
                ThucHienCongViec th = new ThucHienCongViec(2,Email+"+"+MatKhau);
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(th.gt.equals("NOEMAIL")) {
                    Toast.makeText(DangNhapActivity.this,"Email is not values!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(th.gt.equals("PASSWORDFAIL")) {
                    Toast.makeText(DangNhapActivity.this,"Password is wrong!",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(DangNhapActivity.this,"Login is successfull! - "+th.gt,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("email",edtEmail.getText().toString());
                bundle.putString("hoten",th.gt);
                intent.putExtra("login",bundle);
//                intent.putExtra("email",edtEmail.getText()+"+"+th.gt);
                startActivity(intent);
            }
        });

    }
    public void anhxa(){
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
    }
}