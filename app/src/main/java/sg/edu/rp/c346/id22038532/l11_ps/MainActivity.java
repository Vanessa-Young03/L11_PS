package sg.edu.rp.c346.id22038532.l11_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    EditText etMovieTitle, etMovieGenre, etMovieYear;
    Button btnInsert, btnShowList;
    Spinner spinningRating;
    String rating = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovieTitle = findViewById(R.id.editTextMovieTitle);
        etMovieGenre = findViewById(R.id.editTextMovieGenre);
        etMovieYear = findViewById(R.id.editTextMovieYear);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShowList = findViewById(R.id.buttonShowList);

        DBHelper db = new DBHelper(MainActivity.this);

        String[] ratingArray ={"G", "PG", "PG13", "NC16", "M18", "R21"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinnerlayout, ratingArray);
        spinningRating.setAdapter(adapter);



        spinningRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int rate, long l) {
                switch (rate)
                {
                    case 0:
                        rating = "G";
                        break;
                    case 1:
                        rating = "PG";
                        break;
                    case 2:
                        rating = "PG13";
                        break;
                    case 4:
                        rating = "NC16";
                        break;
                    case 5:
                        rating = "M18";
                        break;
                    case 6:
                        rating = "R21";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




}}