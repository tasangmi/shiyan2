package com.example.shiyan2;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Intent;


public class addactivity extends Activity {
    SQLiteOpenHelper sqLiteDbHelper;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tianjia);
        sqLiteDbHelper = new SQLiteDbHelper(this, "contact.db", null, 1);
        saveButton = (Button) findViewById(R.id.button1);
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = sqLiteDbHelper.getWritableDatabase();
                MessageDao messageDao = new MessageDao(db);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");// HH:mm:ss
                Date date = new Date(System.currentTimeMillis());

                EditText txtTitle = (EditText) findViewById(R.id.EditText1);
                EditText txtContext = (EditText) findViewById(R.id.EditText2);
                String dates = simpleDateFormat.format(date);
                String title = txtTitle.getText().toString();
                String context = txtContext.getText().toString();

                Message message = new Message();
                message.setDate(dates);
                message.setContent(context);
                message.setTitle(title);
                messageDao.save(message);
                Toast.makeText(addactivity.this, "数据库保存成功！", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( addactivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}

