package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import btl.ltdd.apptracuubenh.R;

public class DiseaseDetailActivity extends AppCompatActivity {
    private TextView txtDiseaseName, txtDiseaseSymptom, txtDiseaseAdvice;
    private ImageView imageView;
    private String pathImage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_detail);
        mapping();
        Intent intent = getIntent();
        String name = intent.getStringExtra("DiseaseName");
        String symptom = intent.getStringExtra("DiseaseSymptom");
        String advice = intent.getStringExtra("DiseaseAdvice");
        pathImage = intent.getStringExtra("DiseaseIll");
        Picasso.with(this).load(pathImage).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        txtDiseaseName.setText(name);
        txtDiseaseSymptom.setText("Triệu chứng: " + symptom);
        txtDiseaseAdvice.setText("Lời khuyên: "+ advice);

    }

    public void mapping(){
        txtDiseaseAdvice = findViewById(R.id.txtDiseaseAdvice);
        txtDiseaseName = findViewById(R.id.txtDiseaseName);
        txtDiseaseSymptom = findViewById(R.id.txtDiseaseSymptom);
        imageView = (ImageView) findViewById(R.id.imgIll);
    }
}
