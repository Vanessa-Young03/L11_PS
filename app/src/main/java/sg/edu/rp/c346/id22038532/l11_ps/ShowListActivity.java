package sg.edu.rp.c346.id22038532.l11_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {
    ListView lvMovies;
    Button btnBack;
    ArrayAdapter<Movies> aaMovies;
    ArrayList<Movies> alMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        lvMovies = findViewById(R.id.ListViewMovies);
        btnBack = findViewById(R.id.buttonBack);
        alMovies = new ArrayList<Movies>();
        aaMovies = new ArrayAdapter<Movies>(this, android.R.layout.simple_list_item_1,alMovies);
        lvMovies.setAdapter(aaMovies);
        DBHelper dbh = new DBHelper(ShowListActivity.this);

        alMovies.clear();
        alMovies.addAll(dbh.getMovies());
        aaMovies.notifyDataSetChanged();

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movies data = alMovies.get(i);
                Intent intent = new Intent(ShowListActivity.this, Update_Delete.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}