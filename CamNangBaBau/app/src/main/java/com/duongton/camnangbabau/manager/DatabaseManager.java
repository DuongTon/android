package com.duongton.camnangbabau.manager;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.duongton.camnangbabau.model.AnUong;
import com.duongton.camnangbabau.model.CanMuaCanLam;
import com.duongton.camnangbabau.model.ChuanBiSinh;
import com.duongton.camnangbabau.model.DatTen;
import com.duongton.camnangbabau.model.DocTruyen;
import com.duongton.camnangbabau.model.ThaiKi;
import com.duongton.camnangbabau.model.TiemPhong;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by duong on 10/5/2017.
 */

public class DatabaseManager  {
    private String DATABASE_NAME = "BaBauDB.sqlite";
    private String DATABASE_PATH = Environment.getDataDirectory()
            .getAbsolutePath()
            + "/data/com.duongton.camnangbabau/";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {
        this.context = context;
        copyDatabases();
    }

    private void copyDatabases() {
        try {
            File file = new File(DATABASE_PATH + DATABASE_NAME);
            if(file.exists()){
                return;
            }
            AssetManager assetManager = context.getAssets();
            DataInputStream  in = new
                    DataInputStream(assetManager.open(DATABASE_NAME));
            DataOutputStream out =
                    new DataOutputStream(
                            new FileOutputStream(DATABASE_PATH +
                                    DATABASE_NAME));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != - 1){
                out.write(buffer, 0, length);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabase(){
        if(sqLiteDatabase == null || !sqLiteDatabase.isOpen()){
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH +
                    DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDatabase(){
        if(sqLiteDatabase !=null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
    }


    public ArrayList<ThaiKi> getDataThaiKi(){
        openDatabase();
        ArrayList<ThaiKi> thaiKi = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThaiKy"
        , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String image = cursor.getString(cursor.getColumnIndex("IconTuan"));
            String name = cursor.getString(cursor.getColumnIndex("TenTuan"));
            thaiKi.add(new ThaiKi(image,name));
        }
        closeDatabase();
        return thaiKi;
    }

    public ArrayList<ThaiKi> getDataThaiKiItems(int position){
        openDatabase();
        ArrayList<ThaiKi> thaiKi = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThaiKy WHERE MaTuan = '" + position + "'"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String image = cursor.getString(cursor.getColumnIndex("HinhAnhTuan"));
            String be = cursor.getString(cursor.getColumnIndex("ThongTinBe"));
            String me = cursor.getString(cursor.getColumnIndex("ThongTinMe"));
            String meoTuan = cursor.getString(cursor.getColumnIndex("MeoTuan"));
            thaiKi.add(new ThaiKi(image, be));
            thaiKi.add(new ThaiKi(image, me));
            thaiKi.add(new ThaiKi(image, meoTuan));
        }
        closeDatabase();
        return thaiKi;
    }

    public ArrayList<AnUong> getDataAnUong(String tenLoai){
        openDatabase();
        ArrayList<AnUong> anUong = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM AnUong WHERE TenLoai = '" + tenLoai + "'"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("MaAnUong"));
            String image = cursor.getString(cursor.getColumnIndex("Icon"));
            String name = cursor.getString(cursor.getColumnIndex("TenAnUong"));
            String check = cursor.getString(cursor.getColumnIndex("NenKhongNen"));
            anUong.add(new AnUong(id,image,name,check));
        }
        closeDatabase();
        return anUong;
    }

    public ArrayList<AnUong> getDataAnUongItem(int id){
        openDatabase();
        ArrayList<AnUong> anUong = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM AnUong WHERE MaAnUong = '" + id + "'"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("TenAnUong"));
            String content = cursor.getString(cursor.getColumnIndex("NoiDung"));
            anUong.add(new AnUong(name,content));
        }
        closeDatabase();
        return anUong;
    }

    public ArrayList<TiemPhong> getDataTiemPhong(String nguoiTiem){
        openDatabase();
        ArrayList<TiemPhong> tiemPhongs = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TiemPhong WHERE NguoiTiem = '" + nguoiTiem + "'"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("TenTiemPhong"));
            String content = cursor.getString(cursor.getColumnIndex("NoiDung"));
            tiemPhongs.add(new TiemPhong(name,content));
        }
        closeDatabase();
        return tiemPhongs;
    }

    public ArrayList<TiemPhong> getDataKhamThai(){
        openDatabase();
        ArrayList<TiemPhong> khamThai = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KhamThai"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String lanKham = cursor.getString(cursor.getColumnIndex("LanKham"));
            String noiDung = cursor.getString(cursor.getColumnIndex("NoiDung"));
            khamThai.add(new TiemPhong(lanKham,noiDung));
        }
        closeDatabase();
        return khamThai;
    }

    public ArrayList<CanMuaCanLam> getDataCanMuaCanLam(){
        openDatabase();
        ArrayList<CanMuaCanLam> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CanMuaCanLam"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String noiDung = cursor.getString(cursor.getColumnIndex("NoiDung"));
            arrayList.add(new CanMuaCanLam(noiDung));
        }
        closeDatabase();
        return arrayList;
    }

    public ArrayList<Object> getDataChuanBiSinh(String nguoiCan){
        openDatabase();
        ArrayList<Object> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ChuanBiSinh WHERE NguoiCan = '" + nguoiCan + "'"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String noiDung = cursor.getString(cursor.getColumnIndex("NoiDung"));
            arrayList.add(new CanMuaCanLam(noiDung));
            arrayList.add(new ChuanBiSinh("Chuẩn Bị Cho Mẹ"));
        }
        closeDatabase();
        return arrayList;
    }

    public ArrayList<DatTen> getDataDatTen(String gioiTinh){
        openDatabase();
        ArrayList<DatTen> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TenBe WHERE GioiTinh = '" + gioiTinh + "'"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("MaTen"));
            String ten = cursor.getString(cursor.getColumnIndex("TenBe"));
            String yNghia = cursor.getString(cursor.getColumnIndex("YNghia"));
            String tenKhac = cursor.getString(cursor.getColumnIndex("TenKhac"));
            arrayList.add(new DatTen(id,ten, yNghia, tenKhac));
        }
        closeDatabase();
        return arrayList;
    }

    public ArrayList<DatTen> getDataDatTenTongQuan(){
        openDatabase();
        ArrayList<DatTen> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TongQuan"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String noiDung = cursor.getString(cursor.getColumnIndex("NoiDung"));
            arrayList.add(new DatTen(noiDung));
        }
        closeDatabase();
        return arrayList;
    }

    public ArrayList<DocTruyen> getDataDocTruyenTenLoai(){
        openDatabase();
        ArrayList<DocTruyen> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiTruyen"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            int maTruyen = cursor.getInt(cursor.getColumnIndex("MaLoai"));
            String tenLoai = cursor.getString(cursor.getColumnIndex("TenLoai"));
            arrayList.add(new DocTruyen(maTruyen,tenLoai));
        }
        closeDatabase();
        return arrayList;
    }

    public ArrayList<DocTruyen> getDataDocTruyenTenTruyen(int maLoai){
        openDatabase();
        ArrayList<DocTruyen> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Truyen WHERE MaLoai = '" + maLoai + "'"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            int maTruyen = cursor.getInt(cursor.getColumnIndex("MaTruyen"));
            String tenTruyen = cursor.getString(cursor.getColumnIndex("TenTruyen"));
            arrayList.add(new DocTruyen(maTruyen,tenTruyen));
        }
        closeDatabase();
        return arrayList;
    }

    public ArrayList<DocTruyen> getDataDocTruyenNoiDung(int maTruyen){
        openDatabase();
        ArrayList<DocTruyen> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Truyen WHERE MaTruyen = '" + maTruyen + "'"
                , null);
        if(cursor == null || cursor.getCount() == 0){
            return null;
        }
        while (cursor.moveToNext()){
            String noiDung = cursor.getString(cursor.getColumnIndex("NoiDung"));
            arrayList.add(new DocTruyen(noiDung));
        }
        closeDatabase();
        return arrayList;
    }



}
