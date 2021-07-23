package com.example.lostnfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AllItems extends AppCompatActivity {
    ListView lv;
    int images[] = {R.drawable.wallet, R.drawable.mobile, R.drawable.laptop};
    String itemType[] = {"Type: Wallet", "Type: Mobile", "Type: Laptop"};
    String itemColor[] = {"Color: Brown", "Color: Black", "Color: Grey"};
    String foundDate[] = {"Date: JUN 28, 2021", "Date: FEB 24, 2021", "Date: MAR 17, 2021"};
    String foundDescription[] = {"Found Description: Found it in Class B07", "Found Description: Found it in Cafeteria", "Found Description: Found it near Library"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        lv = (ListView)findViewById(R.id.lv1);
        CustomAdapter adapter = new CustomAdapter();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DisplayItem.class);
                intent.putExtra("image", images[position]);
                intent.putExtra("type", itemType[position]);
                intent.putExtra("color", itemColor[position]);
                intent.putExtra("date", foundDate[position]);
                intent.putExtra("des", foundDescription[position]);
                startActivity(intent);
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.items, null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.item_iv1);
            TextView tv1 = (TextView)convertView.findViewById(R.id.item_tv1);
            TextView tv2 = (TextView)convertView.findViewById(R.id.item_tv2);

            iv.setImageResource(images[position]);
            tv1.setText(itemType[position]);
            tv2.setText(foundDate[position]);
            return convertView;
        }
    }


}