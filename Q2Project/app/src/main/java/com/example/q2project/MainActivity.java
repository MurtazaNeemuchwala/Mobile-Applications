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

        arrayList.add(new Heros("Batman", "DC Universe.", (R.drawable.batman)));
        arrayList.add(new Heros("Captain America", "Marvel Universe.", (R.drawable.captain_america)));
        arrayList.add(new Heros("Spiderman", "Marvel Universe.", (R.drawable.spiderman)));
        arrayList.add(new Heros("Ironman", "Marvel Universe.", (R.drawable.ironman)));
        arrayList.add(new Heros("Hulk", "Marvel Universe.", (R.drawable.hulk)));
        arrayList.add(new Heros("Flash", "DC Universe.", (R.drawable.flash)));
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

            textView.setText(list.get(position).getName());
            button.setText("REMOVE");
            picture.setImageResource(list.get(position).getImage());
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