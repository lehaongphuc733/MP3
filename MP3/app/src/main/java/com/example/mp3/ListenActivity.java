package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListenActivity extends AppCompatActivity {

    TextView txtTenBH, txtAlbum,txtEnd,txtStart;
    ImageButton imgBefore, imgAfter,imgPlay,imgTuaLen,imgTuaVe;
    ImageView imgCD,imgLike,imgSave;
    SeekBar skMusic;


    int position ;
    String Album = "NOALBUM";
    String email = "NOEMAIL";
    MediaPlayer mediaPlayer;
    Animation animation;

    int playing = 0 ;
    int like = 0;



    int count_page = 0;
    int count_bh = 0 ;
    int skip = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        anhxa();

        Intent intent = getIntent();
        String noidung = intent.getStringExtra("dulieu");
        String[] chuoi = noidung.split("\\+");
        email = chuoi[0];
        position = Integer.parseInt(chuoi[1]);
        Album = chuoi[2];

        if(Album.equals("NOALBUM")==false){
            if(Album.equals("#LIKED")==true)
                txtAlbum.setText("Bài hát đã thích");
            else
                txtAlbum.setText(Album);
        }



//        Intent intent1 = getIntent();
//        String noidung1 = intent1.getStringExtra("dulieu1");
//        String[] chuoi1 = noidung1.split("\\+");
//        email = chuoi1[0];
//        position = Integer.parseInt(chuoi1[1]);
//        Album = chuoi1[2];



        if(email.equals("NOEMAIL")==false){
            ThucHienCongViec th = new ThucHienCongViec(23,email);
            th.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count_page = Integer.parseInt(th.gt);
            if(Album.equals("NOALBUM")==false){
                ThucHienCongViec th1 = new ThucHienCongViec(22,email+"+"+Album);
                th1.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count_bh = Integer.parseInt(th1.gt);
            }
        }





        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_LuuAlbum();
            }
        });


        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(like==0){
                    imgLike.setImageResource(R.drawable.like1);
                    like=1;
                }
                else
                {
                    imgLike.setImageResource(R.drawable.unlike1);
                    like=0;
                }
                String[] s = txtTenBH.getText().toString().split("-");
                String TenBaiHat = s[0];
                String TenCaSi = s[1];
                ThucHienCongViec th = new ThucHienCongViec(9,email+"+"+TenBaiHat+"+"+TenCaSi);
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        animation = AnimationUtils.loadAnimation(this,R.anim.disc_rotate);



//        Log.d("abc",String.valueOf(position));

        if(email.equals("NOEMAIL")==true) {
            imgSave.setVisibility(View.INVISIBLE);
            imgLike.setVisibility(View.INVISIBLE);
        }




        khoitaoMediaPlayer();
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playing == 1){
                    playing = 0;
                    mediaPlayer.stop();
                    imgPlay.setImageResource(R.drawable.ic_play);
                }
                else
                {
                    playing = 1;
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.ic_pause);
                }
                SetTimeTotal();
                UpdateTimeSong();
                imgCD.startAnimation(animation);
            }
        });

        imgAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                int count ;
                if(Album.equals("NOALBUM")==true){
                    ThucHienCongViec th = new ThucHienCongViec(21,"NO");
                    th.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count = Integer.parseInt(th.gt);
                }
                else {
                    count = count_bh;
                }

                if(position>count){
                    position=1;
                }
                if(playing == 1){
                    mediaPlayer.stop();
//                    playing=0;
                }
                playing=1;
                khoitaoMediaPlayer();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                imgPlay.setImageResource(R.drawable.ic_pause);
                SetTimeTotal();
                UpdateTimeSong();
                imgCD.startAnimation(animation);
            }
        });

        imgBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                int count ;
                if(Album.equals("NOALBUM")== true){
                    ThucHienCongViec th = new ThucHienCongViec(21,"NO");
                    th.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count = Integer.parseInt(th.gt);
                }
                else {
                    count = count_bh;
                }
                if(position<1){
                    position=count;
                }
                if(playing == 1){
                    mediaPlayer.stop();
                }
                playing=1;
                khoitaoMediaPlayer();

                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                imgPlay.setImageResource(R.drawable.ic_pause);
                SetTimeTotal();
                UpdateTimeSong();
                imgCD.startAnimation(animation);
            }
        });
    }

    public void khoitaoMediaPlayer(){
        String noidung = String.valueOf(position);
        ThucHienCongViec th;
        if(Album.equals("NOALBUM")==true){
            th = new ThucHienCongViec(4,noidung);
            th.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        {
            th = new ThucHienCongViec(7,noidung+"+"+Album+"+"+email);
//            Toast.makeText(ListenActivity.this,noidung+"+"+Album+"+"+email,Toast.LENGTH_LONG).show();
            th.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String[] ss = th.gt.split("\\+");
        String tenbaihat = ss[0];
        String tencasi = ss[1];
        String filenhac = ss[2];
        txtTenBH.setText(tenbaihat + "-" + tencasi);

        if(email.equals("NOEMAIL")==false)
        {
            KiemTraUser_ListenBH();

            String[] s = txtTenBH.getText().toString().split("-");
            String TenBaiHat = s[0];
            String TenCaSi = s[1];
            ThucHienCongViec th1 =new ThucHienCongViec(18,email+"+"+TenBaiHat+"+"+TenCaSi);
            th1.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(th1.gt.equals("NOLIKED_BHUSER")==false){
                like = 1;
                imgLike.setImageResource(R.drawable.like1);
            }
        }

        mediaPlayer =new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(filenhac);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void anhxa() {
        txtTenBH = (TextView) findViewById(R.id.txtTenbaihat);
        txtAlbum = (TextView) findViewById(R.id.txtAlbum);
        imgBefore = (ImageButton) findViewById(R.id.imgBefore);
        imgAfter = (ImageButton) findViewById(R.id.imgAfter);
        imgPlay = (ImageButton) findViewById(R.id.imgPlay);
        imgTuaVe = (ImageButton) findViewById(R.id.imgTuaVe);
        imgTuaLen = (ImageButton) findViewById(R.id.imgTuaLen);
        imgCD = (ImageView) findViewById(R.id.imgCD);
        skMusic = (SeekBar) findViewById(R.id.skMusic);
        txtStart = (TextView) findViewById(R.id.txtStart);
        txtEnd = (TextView) findViewById(R.id.txtEnd);
        imgLike = (ImageView) findViewById(R.id.imgLike);
        imgSave = (ImageView) findViewById(R.id.imgSave);

    }
    public void SetTimeTotal() {
        SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
        txtEnd.setText(dinhdanggio.format(mediaPlayer.getDuration()));
        skMusic.setMax(mediaPlayer.getDuration());
    }
    public void UpdateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
                txtStart.setText(dinhdanggio.format(mediaPlayer.getCurrentPosition()));
                skMusic.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        ThucHienCongViec th = new ThucHienCongViec(21,"NO");
                        th.start();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String x = th.gt;
                        int count = Integer.parseInt(th.gt);
                        position++;
                        if(position>count){
                            position=1;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        khoitaoMediaPlayer();
                        try {
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                        imgPlay.setImageResource(R.drawable.ic_pause);
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });
            }
        },100);
    }
    public void KiemTraUser_ListenBH(){
        String[] s = txtTenBH.getText().toString().split("-");
        String TenBaiHat = s[0];
        String TenCaSi = s[1];
        ThucHienCongViec th = new ThucHienCongViec(8,email+"+"+TenBaiHat+"+"+TenCaSi);
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void Dialog_LuuAlbum(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_luubh);
        dialog.setCanceledOnTouchOutside(false);

        //anhxa
        TextView txtTenBaiHatLuu = (TextView) dialog.findViewById(R.id.txtTenBaiHatLuu);
        EditText edtNhaptenalbummoi = (EditText) dialog.findViewById(R.id.edtNhaptenalbummoi);
        ImageView imgAddLuu = (ImageView) dialog.findViewById(R.id.imgAddLuu);
        TextView txtChonAlbumCanLuu = (TextView) dialog.findViewById(R.id.txtChonAlbumCanLuu);
        ListView ListLuuAlbum = (ListView) dialog.findViewById(R.id.ListLuuAlbum);
        ImageView imgNextLuu =(ImageView) dialog.findViewById(R.id.imgNextLuu);
        ImageView imgPreLuu = (ImageView) dialog.findViewById(R.id.imgPreLuu);

        txtTenBaiHatLuu.setText(txtTenBH.getText());

        ArrayList<String> arrayAlbum = LayDS(ListLuuAlbum);

        ListLuuAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenalbum = arrayAlbum.get(position);
                String[] chuoi = txtTenBaiHatLuu.getText().toString().split("-");
                String tenbaihat = chuoi[0];
                String tencasi = chuoi[1];


                ThucHienCongViec th = new ThucHienCongViec(13,email+"+"+tenalbum+"+"+tenbaihat+"+"+tencasi);
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(ListenActivity.this,"Thành công!",Toast.LENGTH_LONG).show();
            }
        });



        imgNextLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if( (skip + 10) > (count_page +1) ){
                        skip = 2;
                    }
                    else
                        skip+=10;


//                    ArrayList<String> arrayAlbum = LayDS(ListLuuAlbum);
                    ArrayAdapter adapter = new ArrayAdapter(ListenActivity.this,R.layout.support_simple_spinner_dropdown_item,arrayAlbum);
                    ListLuuAlbum.setAdapter(adapter);
                    dialog.show();

                }
        });

        imgPreLuu.setOnClickListener(new View.OnClickListener() {
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
//                ArrayList<String> arrayAlbum = LayDS(ListLuuAlbum);
                ArrayAdapter adapter = new ArrayAdapter(ListenActivity.this,R.layout.support_simple_spinner_dropdown_item,arrayAlbum);
                ListLuuAlbum.setAdapter(adapter);
                dialog.show();
            }
        });

        imgAddLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNhaptenalbummoi.getText().toString().length()==0){
                    Toast.makeText(ListenActivity.this,"Vui lòng nhập tên Album!",Toast.LENGTH_LONG).show();
                    return ;
                }

                if(KiemTraTenAlbum(edtNhaptenalbummoi.getText().toString())==0){
                    Toast.makeText(ListenActivity.this,"Tên Album không được có ký tự đặc biệt [Ngoại trừ _ và -]!",Toast.LENGTH_LONG).show();
                    return;
                }


                ThucHienCongViec th =new ThucHienCongViec(14,email+"+"+edtNhaptenalbummoi.getText().toString());
                th.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(th.gt.equals("EXISTED-ALBUMUSER")==true){
                    Toast.makeText(ListenActivity.this,"Tên Album này đã tồn tại!",Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();
                showSuccess();
                count_page++;
                Toast.makeText(ListenActivity.this,String.valueOf(count_page),Toast.LENGTH_LONG).show();
                if(count_page>10 && (count_page-1)%10==0)
                {
                    skip += 10;
                }
//                ArrayList<String> arrayAlbum = LayDS(ListLuuAlbum);
                ArrayAdapter adapter = new ArrayAdapter(ListenActivity.this,R.layout.support_simple_spinner_dropdown_item,arrayAlbum);
                ListLuuAlbum.setAdapter(adapter);
                dialog.show();
            }
        });
//        ArrayList<String> arrayAlbum = LayDS(ListLuuAlbum);
        ArrayAdapter adapter = new ArrayAdapter(ListenActivity.this,R.layout.support_simple_spinner_dropdown_item,arrayAlbum);
        ListLuuAlbum.setAdapter(adapter);
        dialog.show();

    }
    public void showSuccess(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.success);
        dialog.setCanceledOnTouchOutside(true);

        //anhxa
        Button btnOK1 = (Button) dialog.findViewById(R.id.btnOK);

        btnOK1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
    public ArrayList<String> LayDS(ListView ListLuuAlbum){
        ArrayList<String> arrayAlbum = new ArrayList<>();
        ThucHienCongViec th = new ThucHienCongViec(5,email+"+"+skip);
        th.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] album = th.listX;
        if(album.length==1){
            if(album[0].length()==0)
                ListLuuAlbum.setVisibility(View.INVISIBLE);
            else
                ListLuuAlbum.setVisibility(View.VISIBLE);
        }
        for(int i =0; i<album.length;i++){
            arrayAlbum.add(album[i]);
        }
        return arrayAlbum;


    }
}