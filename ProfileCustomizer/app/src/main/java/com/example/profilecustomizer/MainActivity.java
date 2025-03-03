package com.example.profilecustomizer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri; //uniform resource identifier
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText ename;
    private Button chooseImage;
    private Button saveProfile;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST=1;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ename=findViewById(R.id.ename); //instantiation and initialization
        chooseImage=findViewById(R.id.chooseImage);
        saveProfile=findViewById(R.id.saveProfile);
        imageView=findViewById(R.id.imageView);
        //or implement the interface
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChosen();
            }
        });
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveProfileFun();
            }
        });
    }
    private void openImageChosen(){ //onActivityResult is called from here automatically
        //allow one activity to connect to other and
        // allows both activities to mutually exchange the data
        Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
        // start an activity with the expectation of receiving a result back
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            try
            {
                imageUri = data.getData();
                Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bmp);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    private void SaveProfileFun()
    {
        String nameOfText=ename.getText().toString().trim();
        if(nameOfText.isEmpty()) {
            Toast.makeText(this, "Please Enter your name", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Your Profile is Successfully saved "+nameOfText, Toast.LENGTH_LONG).show();
       }
    }
}