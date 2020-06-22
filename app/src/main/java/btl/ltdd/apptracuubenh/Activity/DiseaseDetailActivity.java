package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.DiseaseUser;
import btl.ltdd.apptracuubenh.Util.Server;

public class DiseaseDetailActivity extends AppCompatActivity {
    private TextView txtDiseaseName, txtDiseaseSymptom, txtDiseaseAdvice;
    private ImageView imageView;
    private Button btnSave;
    private String pathImage = "";
    private int diseaseID;
    public ArrayList<DiseaseUser> arrayDiseaseUser = MainActivity.arrayDiseaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_detail);
        mapping();
        Intent intent = getIntent();
        diseaseID = intent.getIntExtra("DiseaseID", 0);
        String name = intent.getStringExtra("DiseaseName");
        String symptom = intent.getStringExtra("DiseaseSymptom");
        String advice = intent.getStringExtra("DiseaseAdvice");
        pathImage = intent.getStringExtra("DiseaseIll");
        Picasso.with(this).load(pathImage).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        txtDiseaseName.setText(name);
        txtDiseaseSymptom.setText("Triệu chứng: " + symptom);
        txtDiseaseAdvice.setText("Lời khuyên: "+ advice);

        if(MainActivity.userID == 0){
            btnSave.setVisibility(View.INVISIBLE);
            LinearLayout.LayoutParams with = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            txtDiseaseName.setLayoutParams(with);
        }
        for (int i = 0; i < arrayDiseaseUser.size(); i++){
            if(arrayDiseaseUser.get(i).getDiseaseID() == diseaseID &&
                    arrayDiseaseUser.get(i).getUserID() == MainActivity.userID ){
                if(arrayDiseaseUser.get(i).getSaved() == 0){
                    btnSave.setText("Lưu");
                } else {
                    btnSave.setText("Hủy Lưu");
                }
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnSave.getText().toString().equals("Lưu")){
                    btnSave.setText("Hủy Lưu");
                    editDiseaseUser(MainActivity.userID, diseaseID, 1);
                    getDataDiseaeseUser();
                    MainActivity.arrayDiseaseUser.clear();
                    for(int i = 0; i < arrayDiseaseUser.size(); i++){
                        MainActivity.arrayDiseaseUser.add(arrayDiseaseUser.get(i));
                    }
                } else {
                    btnSave.setText("Lưu");
                    editDiseaseUser(MainActivity.userID, diseaseID, 0);
                    getDataDiseaeseUser();
                    MainActivity.arrayDiseaseUser.clear();
                    for(int i = 0; i < arrayDiseaseUser.size(); i++){
                        MainActivity.arrayDiseaseUser.add(arrayDiseaseUser.get(i));
                    }
                }
            }
        });

    }

    public void mapping(){
        txtDiseaseAdvice = findViewById(R.id.txtDiseaseAdvice);
        txtDiseaseName = findViewById(R.id.txtDiseaseName);
        txtDiseaseSymptom = findViewById(R.id.txtDiseaseSymptom);
        imageView = (ImageView) findViewById(R.id.imgIll);
        btnSave = findViewById(R.id.btnSaveOfDD);
    }

    private void editDiseaseUser(final int userID, final int diseaseID, final int saved) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathEditDiseaseUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Result", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getApplicationContext(), error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("userID", String.valueOf(userID));
                hashMap.put("diseaseID", String.valueOf(diseaseID));
                hashMap.put("saved", String.valueOf(saved));
                return hashMap;
            }
        };
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }
            @Override
            public void retry(VolleyError error) throws VolleyError {
            }
        });
        requestQueue.add(stringRequest);
    }
    private void getDataDiseaeseUser(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathDiseaseUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    Log.d("data", String.valueOf(response));
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = null;
                        try {
                            int userID, diseaseID, saved;
                            jsonObject = response.getJSONObject(i);
                            userID = jsonObject.getInt("UserID");
                            diseaseID = jsonObject.getInt("DiseaseID");
                            saved = jsonObject.getInt("Saved");
                            arrayDiseaseUser.add(new DiseaseUser(userID, diseaseID, saved));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getApplicationContext(), error.toString());
            }
        });
        jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }
            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }
            @Override
            public void retry(VolleyError error) throws VolleyError {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
