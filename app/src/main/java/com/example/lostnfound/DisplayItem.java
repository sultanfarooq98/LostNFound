package com.example.lostnfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayItem extends AppCompatActivity {
    ImageView iv;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);
        iv = (ImageView)findViewById(R.id.display_iv1);
        tv1 = (TextView)findViewById(R.id.display_tv1);
        tv2 = (TextView)findViewById(R.id.display_tv2);
        tv3 = (TextView)findViewById(R.id.display_tv3);
        tv4 = (TextView)findViewById(R.id.display_tv4);

        Intent intent = getIntent();

        iv.setImageResource(intent.getIntExtra("image", 0));
        tv1.setText(intent.getStringExtra("type"));
        tv2.setText(intent.getStringExtra("color"));
        tv3.setText(intent.getStringExtra("date"));
        tv4.setText(intent.getStringExtra("des"));


    }

    public void sendMail(View view) {
        String to = "yasinghaznavi@gmail.com";
        String subject = "Claiming Item";
        String message = "I think the Wallet you posted on JUN 28, 2021 belongs to me. Kindly meet with me to clarify that this item is mine.";

        Intent email = new Intent (Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] { to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}