package com.example.q2project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Heros> arrayList;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("KEY_LIST", arrayList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        arrayList = (ArrayList<Heros>) savedInstanceState.getSerializable("KEY_LIST");
    }

   /* @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.id_listview_main);
        arrayList = new ArrayList<>();

        //Batman
        arrayList.add(new Heros(
                "Batman",
                "Hails from DC Universe. His whole story started when he witnessed his parents getting murdered in a robbery gone wrong. Ever since that moment in his life he pledged that he would make it his job to end crime in Gotham City. With the help of his team which includes, Alfred, Lucius Fox, and Oracle he plans to take down every Villain he can.",
                (R.drawable.batman)));

        //Captain America
        arrayList.add(new Heros(
                "Captain America",
                "Hails from Marvel Universe. Steve Rodgers was just a teenager drafted by the army to fight in WWII. The army started conducting special experiments to create biological Super-Humans so he volunteered and the experiment was a success. He was given super human abilities which made him unstoppable. He takes on Hydra to defeat them once and for all but in the process he is forced to sacrifice his life to save a city from a bombing. Captain does the ultimate deed and nose dives the plane with his body in it.",
                (R.drawable.captain_america)));

        //Spidy
        arrayList.add(new Heros(
                "Spiderman",
                "Hails from Marvel Universe. Peter Parker was on a school field trip to a laboratory, Peter was wandering around and found a radioactive spider and got bit by it. He started to notice there were some changes happening to him, he had strong grip, great strength and high jumping abilities. He started to create web slingers and a suit to become his new persona, \"spiderman\".",
                (R.drawable.spiderman)));

        //Ironman
        arrayList.add(new Heros(
                "Ironman",
                "Hails from Marvel Universe. Tony Stark, a tech wiz, a millionaire. He was once very arrogant a very egotistical person until he faced the hardest moments in his life. Ironman was held hostage by a terror group, he knew the only was he'd survive is if he escapes. In a cave Tony made a manned robot which he would use to fight his way out. He then realised his true mission in life, he must eradicate all terrorists in the world and make world peace.",
                (R.drawable.ironman)));

        //Hulk
        arrayList.add(new Heros("Hulk",
                "Hails from Marvel Universe. Bruce Banner a physicist, a chemist, and a biologist was conducting an experiment where he was hit by gamma rays and radiation. The radiation was so strong that the human biology in him was altered. Bruce had become a brute. He had become the \"Hulk\".",
                (R.drawable.hulk)));

        //Flash
        arrayList.add(new Heros(
                "Flash",
                "Hails from DC Universe. Barry Allen a detective was working a case and during an  investigation he was struck by lighting due to a particle accelerator. This put him in coma for several years. Once he finally woke up he had felt more energetic than ever, it was almost like time around him had slowed down. He then realised that the particle accelerator had caused a biological change in his body, his body and brain was actually moving faster. Upon more experimentation he had found out that he had super human speed.",
                (R.drawable.flash)));

        if (savedInstanceState != null) {
            arrayList = (ArrayList<Heros>) savedInstanceState.getSerializable("KEY_LIST");
        }
        final CustomAdapter adapter = new CustomAdapter(this, R.layout.adapter_listview, arrayList);
        listView.setAdapter(adapter);
    }

    public class CustomAdapter extends ArrayAdapter<Heros> {
        List<Heros> list;
        Context context;
        int xml;
        boolean showDesc = false;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Heros> objects) {
            super(context, resource, objects);
            this.context = context;
            xml = resource;
            list = objects;
        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = inflater.inflate(xml, null);

            TextView textView = adapterLayout.findViewById(R.id.id_adapter_TextView);
            TextView description = adapterLayout.findViewById(R.id.textView_description);
           // Button button = adapterLayout.findViewById(R.id.id_adapter_button);
            ImageButton remove = adapterLayout.findViewById(R.id.imageButton_remove);
            ImageView picture = adapterLayout.findViewById(R.id.id_adapter_imageView);

            /*button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(list.get(position));
                }
            });

             */
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(list.get(position));
                }
            });

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                textView.setText(list.get(position).getName());
                //button.setText("REMOVE");
                remove.setImageResource(R.drawable.trash);
                picture.setImageResource(list.get(position).getImage());
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                textView.setText(list.get(position).getName());
               // button.setText("REMOVE");
                remove.setImageResource(R.drawable.trash);
                picture.setImageResource(list.get(position).getImage());
                adapterLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        description.setText(list.get(position).getDescription());
                    }
                });
            }
            return adapterLayout;
        }
    }

    public class Heros {
        String name;
        String description;
        int image;

        public Heros(String name, String description, int image) {
            this.name = name;
            this.description = description;
            this.image = image;
        }

        public int getImage() {
            return image;
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }
    }
}