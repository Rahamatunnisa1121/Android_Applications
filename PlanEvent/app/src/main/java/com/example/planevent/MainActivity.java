package com.example.planevent;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    private ImageButton selectImage;
    private Button btnSelectDate,btnSubmit;
    private TextView title,eventType,tvSelectedDate,addServices;
    private RadioGroup radioGroupEvent;
    private CheckBox catering, photography, liveMusic;
    private Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiation and initialization
        title=findViewById(R.id.title);
        selectImage=findViewById(R.id.selectImage);
        tvSelectedDate=findViewById(R.id.tvSelectedDate);
        btnSelectDate=findViewById(R.id.btnSelectDate);
        eventType=findViewById(R.id.eventType);
        radioGroupEvent=findViewById(R.id.radioGroupEvent);
        addServices=findViewById(R.id.addServices);
        catering=findViewById(R.id.catering);
        photography=findViewById(R.id.photography);
        liveMusic=findViewById(R.id.liveMusic);
        btnSubmit=findViewById(R.id.btnSubmit);

        btnSelectDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                processForm();
            }
        });

        selectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openImageChosen();
            }
        });
    }

    private void openImageChosen(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            try
            {
                imageUri = data.getData();
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                selectImage.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    private void showDatePicker(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Months are indexed from 0, so add 1
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    tvSelectedDate.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void processForm(){
        int selectedEventID= radioGroupEvent.getCheckedRadioButtonId();
        String eventType = selectedEventID != -1 ?
                ((RadioButton) findViewById(selectedEventID)).getText().toString()
                : "No event selected";
        String results="";
        if(catering.isChecked())results+=" Catering,";
        if(photography.isChecked())results+=" Photography,";
        if(liveMusic.isChecked())results+=" Live Music,";
        if (results.isEmpty()) results = " No additional services";
        Toast.makeText(this, "Event: " + eventType + "\nServices:" + results, Toast.LENGTH_LONG).show();
    }
}