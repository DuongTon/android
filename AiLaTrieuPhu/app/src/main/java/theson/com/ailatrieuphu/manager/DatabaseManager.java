package theson.com.ailatrieuphu.manager;


import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import theson.com.ailatrieuphu.model.Question;

public class DatabaseManager {
    private String DATABASE_NAME = "Question";
    private String DATABASE_PATH = Environment.getDataDirectory()
            .getAbsolutePath()
            + "/data/theson.com.ailatrieuphu/";
    private Context context;

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {
        this.context = context;
        copyDatabases();
    }

    private void copyDatabases() {
        try {
            File file = new File(DATABASE_PATH + DATABASE_NAME);
            if (file.exists()) {
                return;
            }
            AssetManager asset = context.getAssets();
            DataInputStream in = new DataInputStream(asset.open(DATABASE_NAME));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(DATABASE_PATH + DATABASE_NAME));

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH +
                    DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDatabase() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public Question getOneQuestion(String tableName) {
        openDatabase();
        Question question;
        question = new Question("", "", "", "", "", 0);

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "
                + tableName + " ORDER BY Random() LIMIT 1", null);
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        while (cursor.moveToNext()) {
            String q = cursor.getString(cursor.getColumnIndex("Question"));
            String a = cursor.getString(cursor.getColumnIndex("CaseA"));
            String b = cursor.getString(cursor.getColumnIndex("CaseB"));
            String c = cursor.getString(cursor.getColumnIndex("CaseC"));
            String d = cursor.getString(cursor.getColumnIndex("CaseD"));
            int t = cursor.getInt(cursor.getColumnIndex("TrueCase"));
            question = new Question(q,a,b,c,d,t);
        }
        closeDatabase();
        return question;
    }

    public ArrayList<Question> get15Question(){
        ArrayList<Question> questions = new ArrayList<>();
        for (int i =1;i<=15; i++){
            Question question = getOneQuestion("Question"+i);
            if(question !=null){
                questions.add(question);
            }
        }
        return questions;
    }




}
