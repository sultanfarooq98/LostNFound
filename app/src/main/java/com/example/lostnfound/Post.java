package com.example.lostnfound;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class Post extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final int upload = 100;
    ImageView im1;
    Button type;
    Button color;
    Button showDate;
    Button post;
    EditText ed1;
    Uri selectImage;
    StorageReference storageReference;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        reference = FirebaseDatabase.getInstance().getReference("Images");
        ed1 = (EditText)findViewById(R.id.post_ed1);
        im1 = (ImageView)findViewById(R.id.post_im1);
        type = (Button)findViewById(R.id.post_bt2);
        color = (Button)findViewById(R.id.post_bt3);
        post = (Button)findViewById(R.id.post_bt5);
        showDate = (Button)findViewById(R.id.post_bt4);
        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });
    }

    public String GetFileExtension(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void UploadImage(){
        if(selectImage != null){
            StorageReference sr1 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(selectImage));
            sr1.putFile(selectImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Post.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                    Upload uploaditems = new Upload(type.getText().toString().trim(), color.getText().toString().trim(), showDate.getText().toString().trim(), taskSnapshot.getUploadSessionUri().toString());

                    String uploadId = reference.push().getKey();
                    reference.child(uploadId).setValue(uploaditems);
                }
            });
        }
        else{
            Toast.makeText(this, "Picture Not Selected", Toast.LENGTH_SHORT).show();
        }

    }


    public void upload(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, upload);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == upload && resultCode == RESULT_OK  && data != null)
        {
            selectImage = data.getData();
            im1.setImageURI(selectImage);
        }
    }



    public void itemType(View view) {
        PopupMenu pmenu = new PopupMenu(getApplicationContext(), view);
        pmenu.getMenuInflater().inflate(R.menu.type_menu, pmenu.getMenu());
        pmenu.show();

        pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.type_bag){
                    type.setText("Type: Bag");
                }
                else if(item.getItemId() == R.id.type_laptop){
                    type.setText("Type: Laptop");
                }
                else if(item.getItemId() == R.id.type_mobile){
                    type.setText("Type: Mobile");
                }
                else if(item.getItemId() == R.id.type_wallet){
                    type.setText("Type: Wallet");
                }
                else if(item.getItemId() == R.id.type_purse){
                    type.setText("Type: Purse");
                }
                else{
                    type.setText("Type: Other");
                }
                return false;
            }
        });
    }

    public void itemColor(View view) {
        PopupMenu pmenu = new PopupMenu(getApplicationContext(), view);
        pmenu.getMenuInflater().inflate(R.menu.item_color, pmenu.getMenu());
        pmenu.show();

        pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.color_black){
                    color.setText("Color: Black");
                }
                else if(item.getItemId() == R.id.color_blue){
                    color.setText("Color: Blue");
                }
                else if(item.getItemId() == R.id.color_green){
                    color.setText("Color: Green");
                }
                else if(item.getItemId() == R.id.color_orange){
                    color.setText("Color: Orange");
                }
                else if(item.getItemId() == R.id.color_red){
                    color.setText("Color: Red");
                }
                else if(item.getItemId() == R.id.color_brown){
                    color.setText("Color: Brown");
                }
                else if(item.getItemId() == R.id.color_yellow){
                    color.setText("Color: Yellow");
                }
                else if(item.getItemId() == R.id.color_white){
                    color.setText("Color: White");
                }
                else if(item.getItemId() == R.id.color_grey){
                    color.setText("Color: Grey");
                }
                else{
                    color.setText("Color: Other");
                }
                return false;
            }
        });
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month +=1;
        String mon;
        if (month == 1){
            mon = "JAN";
        }
        else if (month == 2){
            mon = "FEB";
        }
        else if (month == 3){
            mon = "MAR";
        }
        else if (month == 4){
            mon = "APR";
        }
        else if (month == 5){
            mon = "MAY";
        }
        else if (month == 6){
            mon = "JUN";
        }
        else if (month == 7){
            mon = "JUL";
        }
        else if (month == 8){
            mon = "AUG";
        }
        else if (month == 9){
            mon = "SEP";
        }
        else if (month == 10){
            mon = "OCT";
        }
        else if (month == 11){
            mon = "NOV";
        }
        else{
            mon = "DEC";
        }
        String date = "Date: " + mon + " " + dayOfMonth + ", " + year;
        showDate.setText(date);
    }

}