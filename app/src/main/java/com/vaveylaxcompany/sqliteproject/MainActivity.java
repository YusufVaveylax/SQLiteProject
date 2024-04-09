package com.vaveylaxcompany.sqliteproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import javax.crypto.NullCipher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR, age INT)");

            //database.execSQL("INSERT INTO musicians (name, age ) VALUES ('James',50)");
            //database.execSQL("INSERT INTO musicians (name, age ) VALUES ('Pazzo', 19)");
            //database.execSQL("INSERT INTO musicians (name, age ) VALUES ('Yusuf', 19)");

            //database.execSQL("UPDATE musicians SET age = 18 WHERE name = 'James'");         // For UPDATE

            //database.execSQL("DELETE FROM musicians WHERE id = 2");         // For DELETE

            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE LIKE "%s",null);       // Sonu "s" harfi ile biten kullanıcıları getirir.
            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);       // WHERE kullanarak filtreleme yapılabilir. Ör: WHERE age < 20

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("Id: " + cursor.getInt(idIx));
            }

            cursor.close();


        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}