package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoiMatKhauActivity extends AppCompatActivity {
    EditText edtMKCu, edtMKMoi, edtXacNhanMK;
    Button btnCapNhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        anhxa();

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtMKCu.getText().length()==0||edtMKMoi.getText().length()==0||edtXacNhanMK.getText().length()==0) {
                    Toast.makeText(DoiMatKhauActivity.this,"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edtMKMoi.getText().toString().equals(edtXacNhanMK.getText().toString())==false){
                    Toast.makeText(DoiMatKhauActivity.this,"Mật khẩu không khớp!",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = getIntent();
                String Email = intent.getStringExtra("DoiMK");
                ThucHienCongViec th = new ThucHienCongViec(11,Email+"+"+edtMKCu.getText().toString()+"+"+edtMKMoi.getText().toString());
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(th.gt.equals("OLDPASSWORD-FAIL")==true) {
                    Toast.makeText(DoiMatKhauActivity.this,"Mật khẩu cũ không chính xác!",Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(DoiMatKhauActivity.this,"Thay dổi thành công!",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(DoiMatKhauActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void anhxa() {
        edtMKCu = (EditText) findViewById(R.id.edtNhapMKCu);
        edtMKMoi = (EditText) findViewById(R.id.edtNhapMKMoi);
        edtXacNhanMK = (EditText) findViewById(R.id.edtXacNhanMK);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
    }
}