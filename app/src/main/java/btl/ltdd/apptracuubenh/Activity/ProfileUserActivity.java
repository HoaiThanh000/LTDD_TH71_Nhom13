package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.DiseaseUser;
import btl.ltdd.apptracuubenh.Util.Server;
import btl.ltdd.apptracuubenh.Util.User;

public class ProfileUserActivity extends AppCompatActivity {
    private TextView txtUserName, txtDangXuat, txtLichSu, txtBenhDaLuu, txtThongTin;
    private String user;
    private ArrayList<User> arrayUser = MainActivity.arrayUser;
    private int userID;
    public static ArrayList<DiseaseUser> arrayDiseaseUser = MainActivity.arrayDiseaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        mapping();
        arrayDiseaseUser.clear();
        getDataDiseaeseUser();

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        txtUserName.setText(user);
        for(int i = 0; i < arrayUser.size(); i++){
            if(arrayUser.get(i).getUserName().equals(user)){
                userID = arrayUser.get(i).getUserID();
            }
        }

        txtThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InformationUserActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
        });

        txtLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                intent.putExtra("userID", String.valueOf(userID));
                startActivity(intent);
            }
        });

        txtBenhDaLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SaveActivity.class);
                startActivity(intent);
            }
        });

        txtDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void mapping(){
        txtUserName = findViewById(R.id.txtUserName);
        txtDangXuat = findViewById(R.id.txtDangXuat);
        txtLichSu = findViewById(R.id.txtLichSu);
        txtBenhDaLuu = findViewById(R.id.txtBenhDaLuu);
        txtThongTin = findViewById(R.id.txtThongTinTK);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        arrayDiseaseUser.clear();
        getDataDiseaeseUser();
    }

    public void getDataDiseaeseUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathDiseaseUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    Log.d("dataDiseaseUser", String.valueOf(response));
                    for (int i = 0; i < response.length(); i++) {
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
