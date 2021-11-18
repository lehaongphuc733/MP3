package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKyActivity extends AppCompatActivity {

    EditText edtNhapEmail, edtNhapHoTen, edtNhapMK,edtReMK;
    Button btnDangNhap,btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        anhxa();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edtNhapEmail.getText().toString();
                String HoTen = edtNhapHoTen.getText().toString();
                String MatKhau = edtNhapMK.getText().toString();
                String ReMatKhau = edtReMK.getText().toString();
                if(Email.length()==0 || HoTen.length()==0 || MatKhau.length()==0 || ReMatKhau.length()==0)
                {
                    Toast.makeText(DangKyActivity.this,"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_LONG).show();
                    return;
                }
                KiemTraEmail kt = new KiemTraEmail();
                if(kt.validateEmailAddress(edtNhapEmail)==false){
                    Toast.makeText(DangKyActivity.this,"Email không hợp lệ!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(MatKhau.equals(ReMatKhau)== false){
                    Toast.makeText(DangKyActivity.this,"Mật khẩu không khớp!",Toast.LENGTH_LONG).show();
                    return;
                }
                ThucHienCongViec th = new ThucHienCongViec(3,HoTen+"+"+Email+"+"+MatKhau);
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(th.gt.equals("EXISTEMAIL")==true){
                    Toast.makeText(DangKyActivity.this,"Email này đã tồn tại!",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(DangKyActivity.this,"Đăng ký thành công!",Toast.LENGTH_LONG).show();
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
    public void anhxa() {
        edtNhapEmail = (EditText) findViewById(R.id.edtDangKyEmail);
        edtNhapHoTen = (EditText) findViewById(R.id.edtDangKyHoTen);
        edtNhapMK = (EditText) findViewById(R.id.edtDangKyMK);
        edtReMK = (EditText) findViewById(R.id.edtReMK);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
    }
}