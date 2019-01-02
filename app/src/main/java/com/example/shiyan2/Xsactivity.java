package com.example.shiyan2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.List;
public class Xsactivity extends Activity {
    SQLiteDbHelper sqLiteDbHelper;
    int id_query;
    MessageDao messageDao;
    EditText detail_title;
    EditText detail_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xianshi);
        sqLiteDbHelper=new SQLiteDbHelper(this,"contact.db",null,1);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        id_query=Integer.parseInt(id);
        SQLiteDatabase db = sqLiteDbHelper.getWritableDatabase();
        messageDao=new MessageDao(db);
        List<Message> list=messageDao.visit(id);
        detail_title=(EditText)this.findViewById(R.id.EditText1);
        detail_title.setText(list.get(0).getTitle());
        detail_content=(EditText)this.findViewById(R.id.EditText2);
        detail_content.setText(list.get(0).getContent());
    }
    /* 返回按钮 */
    public void btnReturn_detail(View view){
        Intent intent;
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
