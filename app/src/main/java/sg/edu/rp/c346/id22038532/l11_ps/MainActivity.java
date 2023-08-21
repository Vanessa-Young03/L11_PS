package sg.edu.rp.c346.id22038532.l11_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etMovieTitle, etMovieGenre, etMovieYear;
    Button btnInsert, btnShowList;
    Spinner spinningRating;

    CustomAdapter caMovie;
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
        spinningRating = findViewById(R.id.spinnerRating);

        DBHelper db = new DBHelper(MainActivity.this);

        spinningRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int rate, long id) {
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
                db.insertMovie(etMovieTitle.getText().toString(), etMovieGenre.getText().toString(), Integer.parseInt(String.valueOf(etMovieYear.getText())), rating);

                etMovieTitle.setText("");
                etMovieGenre.setText("");
                etMovieYear.setText("");

                Toast.makeText(MainActivity.this, "Successfully Added Movie", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowListActivity.class));
            }
        });






}}