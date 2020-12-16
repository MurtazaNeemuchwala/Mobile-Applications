package com.example.q2project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Heros> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.id_listview_main);
        arrayList = new ArrayList<>();

        CustomAdapter adapter = new CustomAdapter(this, R.layout.adapter_listview, arrayList);
        listView.setAdapter(adapter);

        arrayList.add(new Heros("Batman", "DC Universe. His abilities include above human intelligence, access to advanced technologies, adept hand to hand combat, and close to peak human capabilities", (R.drawable.batman)));
        arrayList.add(new Heros("Captain America", "Marvel Universe. His abilities include superhuman capabilities, mastery in all forms of martial arts, and dexterity with his vibranium crafted shield.", (R.drawable.captain_america)));
        arrayList.add(new Heros("Spiderman", "Marvel Universe. Spiderman uses his genius level mind and superhuman strength and stamina to fight off crime in the city of New York.", (R.drawable.spiderman)));
        arrayList.add(new Heros("Ironman", "Marvel Universe. His abilities include above human intelligence, access to advanced technologies, and phenomenal engineering skills.", (R.drawable.ironman)));
        arrayList.add(new Heros("Hulk", "Marvel Universe. His abilities include, above human intelligence, superior knowledge in the field of chemistry and physics, and can become an enraged super human when angered. ", (R.drawable.hulk)));
        arrayList.add(new Heros("Flash", "DC Universe. His abilities include, above human intelligence, superhuman speed which allows him to asses and resolve critical situation in the matter of a split second.", (R.drawable.flash)));

    }

    public class CustomAdapter extends ArrayAdapter<Heros> {
        List<Heros> list;
        Context context;
        int xml;

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
            Button button = adapterLayout.findViewById(R.id.id_adapter_button);
            ImageView picture = adapterLayout.findViewById(R.id.id_adapter_imageView);

            adapterLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(list.get(position));

                }
            });

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                textView.setText(list.get(position).getName());
                button.setText("REMOVE");
                picture.setImageResource(list.get(position).getImage());
            }
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                textView.setText(list.get(position).getDescription());
                button.setText("REMOVE");
                picture.setImageResource(list.get(position).getImage());
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