package com.example.mp3;

import android.util.Log;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ThucHienCongViec extends Thread{
    private int congviec ;
    /*
    case 1: Lấy 10 bài hát nổi bật gần đây đưa lên ListView của trang chủ
    case 2: Kiểm tra đăng nhập
    case 3: Kiểm tra đăng ký
    case 4: Tìm bài hát theo số thứ tự trong tất cả danh sách
    case 5: Lấy danh sách album của user
    case 6: Lấy danh sách bài hát trong album của User
    case 7:
    case 8: Kiểm tra trạng thái số lượt xem của User qua bài hát
    case 9: Kiểm tra trạng thái số lượt yêu thích của User qua bài hát
    case 10: Chỉnh sửa thông tin User
    case 11: Đổi password User
    case 12:
    case 13: Thêm bài hát vô 1 album của User
    case 14: Thêm album của User
    case 15: Sửa album của User
    case 16: Xóa album của User
    case 17: Kiểm tra tình trạng của User khi thích ca sĩ (Kiểm tra đã like chưa)
    case 18: Kiểm tra User đã thích bài hát hay chưa
    case 19: Kiểm tra User đã thích ca sĩ chưa
    case 20:
    case 21: Đếm số lượng bài hát trong tất cả danh sách
    case 22:
    case 23: Đếm số lượng album của User
    case 24: Xem thông tin ca sĩ

    * */
    private String chuoi = "";

    String[] listX;
    String gt= "";

    Socket client = null;
    PrintWriter out = null;
    Scanner in = null;
    public ThucHienCongViec(int congviec, String chuoi) {
        this.congviec = congviec;
        this.chuoi = chuoi;

    }

    String[] layDS_ChuaPhanTach(String ketqua)
    {
        String[] dong = ketqua.split("\\*");


        return dong;
    }
    String[] layDS_DaPhanTach(String ketqua)
    {
        String[] dong = ketqua.split("\\+");
        return dong;
    }
    public void run() {
        String ketqua = "";
        try
        {
            client = new Socket("192.168.1.6",3000);
            out = new PrintWriter(client.getOutputStream(),true);
            in = new Scanner(client.getInputStream());

            switch(congviec){

                case 1 :
                case 6 :
                    out.println(congviec+"*"+chuoi);
                    out.flush();
                    ketqua = in.nextLine();
                    listX = layDS_ChuaPhanTach(ketqua);
                    client.close();
                    break;
                case 2 :
                case 3 :
                case 4 :
                case 7 :
                case 8 :
                case 9 :
                case 10 :
                case 11 :
                case 12 :
                case 13 :
                case 14 :
                case 15 :
                case 16 :
                case 17 :
                case 18 :
                case 19 :
                case 21:
                case 22 :
                case 23 :
                case 24 :
                    out.println(congviec+"*"+chuoi);
                    out.flush();
                    ketqua = in.nextLine();
                    gt = ketqua;
                    client.close();
                    break;
                case 5 :

                    out.println(congviec+"*"+chuoi);
                    out.flush();
                    ketqua = in.nextLine();
                    listX = layDS_DaPhanTach(ketqua);
                    client.close();
                    break;

            }
        }catch(Exception e)
        {
            try
            {
                if (client != null)
                    client.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

