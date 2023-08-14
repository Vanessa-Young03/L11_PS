package sg.edu.rp.c346.id22038532.l11_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter {

        Context parent_context;
        int layout_id;
        ArrayList<Movies> movieList;

        public CustomAdapter(Context context, int resource, ArrayList<Movies> objects)
        {
            super(context,resource,objects);

            parent_context = context;
            layout_id = resource;
            movieList = objects; // VersionList refer to ArrayList
        }

        @Override

        //Gernerate row View
        public View getView(int position, View convertView, ViewGroup parent)
        {
            //Obtain the LayoutInflater object
            LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //"Inflate" the View for each row
            View rowView = inflater.inflate(layout_id,parent,false); // Represent row.xml

            //Obtain the UI components and do the necessary binding
            TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
            TextView tvYear = rowView.findViewById(R.id.textViewYear);
            TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
            ImageView imageViewRating = rowView.findViewById(R.id.imageView);


            //Obtain the Android Version information based on the position
            Movies currentVersion = movieList.get(position);

            //Set values to the TextView to display the corresponding information
            tvTitle.setText(currentVersion.getTitle());
            tvYear.setText(String.valueOf(currentVersion.getYears()));
            tvGenre.setText(currentVersion.getGenre());

            switch (currentVersion.getRating())
            {
                case "PG":
                    imageViewRating.setImageResource(R.drawable.rating_pg);
                    break;
                case "M18":
                    imageViewRating.setImageResource(R.drawable.rating_m18);
                    break;
                case "NC16":
                    imageViewRating.setImageResource(R.drawable.rating_nc16);
                    break;
                case "G":
                    imageViewRating.setImageResource(R.drawable.rating_g);
                    break;
                case "PG13":
                    imageViewRating.setImageResource(R.drawable.rating_pg13);
                    break;
                case "R21":
                    imageViewRating.setImageResource(R.drawable.rating_r21);
                    break;
            }


            return rowView;
        }
    }

