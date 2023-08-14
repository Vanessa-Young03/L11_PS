package sg.edu.rp.c346.id22038532.l11_ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    //Start version with 1
    //increment by 1 whenever db schema changes
    private static final int DATABASE_VER = 1;
    //Filename of the database
    private static final String DATABASE_NAME = "MyMovies.db";

    private static final String TABLE_MOVIE = "Movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATE = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    public void insertMovie(String title, String genre, int year, String rating) {
        //Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        //Use ContentValues object to store the values for the db operation
        ContentValues values = new ContentValues();
        //Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATE, rating);
        //Insert the row into the TABLES_TASK
        db.insert(TABLE_MOVIE, null, values);
        //Close database connection
        db.close();
    }

    public ArrayList<String> getMovieContent()
    {
        //Create an ArrayList that holds String objects
        ArrayList<String> movies = new ArrayList<String>();
        //Get the instance of the database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATE};
        //Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_MOVIE, columns, null,null,null,null,null);

        //moveToFirst() moves to first row, null if no records
        if(cursor.moveToFirst())
        {
            //Loop while moveToNext() points to next row
            //and retruns true; moveToNext() return false
            // when no more rows to move to
            do {
                //Add the task content to the ArrayList object
                //getString(0) retrieves first column data
                //  getString(1) return second column data
                //  getInt(0) if data is an integer value
                movies.add(cursor.getString(1));
                movies.add(cursor.getString(2));
                movies.add(cursor.getString(3));
                movies.add(cursor.getString(4));
            }
            while (cursor.moveToNext());
        }
        //close connection
        cursor.close();
        db.close();
        return movies;
    }

    public int updateMovie(Movies data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYears());
        values.put(COLUMN_RATE, data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIE, values,condition,args);
        db.close();
        return result;
    }

    public int deleteMovie(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition,args);
        db.close();
        return result;
    }

    public ArrayList <Movies> getMovies() {
        ArrayList<Movies> movies = new ArrayList<Movies>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATE};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int years = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies obj = new Movies(id,title,genre,years,rating);
                movies.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT," + COLUMN_GENRE + " TEXT," + COLUMN_YEAR + " TEXT," + COLUMN_RATE + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        // Create table(s) again
        onCreate(db);

    }
}

