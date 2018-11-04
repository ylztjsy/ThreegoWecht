package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelperUtil extends SQLiteOpenHelper {

    public DBOpenHelperUtil(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create table friend(" +
                "id integer primary key," +
                "name varchar(30)," +
                "mcode varchar(30)," +
                "mark varchar(30)," +
                "head_pic_path varchar(32)," +
                "friend_pic_path varchar(32)," +
                "province varchar(20)," +
                "city varchar(30)," +
                "motto varchar(100)" +
                ")";
        db.execSQL(sql);

        sql ="create table chat_list(" +
                "id integer primary key," +
                "fid integer," +
                "last_time integer" +
                ")";
        db.execSQL(sql);

        sql ="create table chat_content(" +
                "id integer primary key," +
                "cid integer," +
                "is_mine integer," +
                "insert_time integer," +
                "content varchar(300)," +
                "state integer" +
                ")";
        db.execSQL(sql);

        sql ="create table friend_circle(" +
                "id integer primary key," +
                "uid integer," +
                "content varchar(300)," +
                "insert_time integer," +
                "is_good integer," +
                "num_good integer," +
                "pic_paths varchar(300)" +
                ")";
        db.execSQL(sql);

        sql ="create table comment(" +
                "id integer primary key," +
                "fc_id integer," +
                "s_uid integer," +
                "r_uid integer," +
                "content varchar(300)," +
                "insert_time integer" +
                ")";
        db.execSQL(sql);

        sql ="create table configure(" +
                "id integer primary key," +
                "mcode varchar(30)," +
                "tel varchar(15)," +
                "token varchar(50)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
