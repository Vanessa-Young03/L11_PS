package sg.edu.rp.c346.id22038532.l11_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Update_Delete extends AppCompatActivity {

    Button btnUpdate , btnDelete, btnCancel;
    EditText ID, Title, Genre,Year;

    Spinner spinnerRating;
    Movies data;

    String ratings = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        ID = findViewById(R.id.editTextTextID);
        Title = findViewById(R.id.editTextMovieTitleUpdate);
        Genre = findViewById(R.id.editTextGenreUpdate);
        Year = findViewById(R.id.editTextYearUpdate);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);
        spinnerRating = findViewById(R.id.spinnerRatingUpdate);

        DBHelper dbh = new DBHelper(Update_Delete.this);

        Intent intent = getIntent();
        data = (Movies) intent.getSerializableExtra("data");

        ID.setText(String.valueOf(data.getId()));
        Title.setText(String.valueOf(data.getTitle()));
        Genre.setText(String.valueOf(data.getGenre()));
        Year.setText(String.valueOf(data.getYears()));

        switch (data.getRating()) {
            case "G":
                spinnerRating.setSelection(0);
                break;
            case "PG":
                spinnerRating.setSelection(1);
                break;
            case "PG13":
                spinnerRating.setSelection(2);
                break;
            case "NC16":
                spinnerRating.setSelection(3);
                break;
            case "M18":
                spinnerRating.setSelection(4);
                break;
            default:
                spinnerRating.setSelection(5);
                break;
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setTitle(Title.getText().toString());
                data.setGenre(Genre.getText().toString());
                data.setYears(Integer.parseInt(Year.getText().toString()));
                data.setRating(spinnerRating.getSelectedItem().toString());
                dbh.updateMovie(data);
                dbh.close();
                finish();
            }});


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbh.deleteMovie(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
};