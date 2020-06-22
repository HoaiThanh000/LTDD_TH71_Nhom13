package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.Server;
import btl.ltdd.apptracuubenh.Util.User;

public class InformationUserActivity extends AppCompatActivity {
    private ArrayList<User> arrayUser;
    private Button btnSave;
    private TextView txtUserName;
    private EditText edtFullName, edtPhone, edtJob;
    private String user, fullName, phone, job;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);
        mapping();

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        txtUserName.setText(user);
        for(int i = 0; i < arrayUser.size(); i++){
            if(arrayUser.get(i).getUserName().equals(user)){
                index = i;
            }
        }
        fullName = arrayUser.get(index).getFullName();
        Log.d("fullName", arrayUser.get(index).getFullName());
        phone = arrayUser.get(index).getPhone();
        job = arrayUser.get(index).getJob();
        if(!fullName.equals("") || !phone.equals("") || !job.equals("")){
            edtFullName.setText(fullName);
            edtPhone.setText(phone);
            edtJob.setText(job);

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = edtFullName.getText().toString();
                phone = edtPhone.getText().toString();
                job = edtJob.getText().toString();
                if(fullName.equals("") || phone.equals("") || job.equals("")){
                    CheckConnection.showToast_Short(getApplicationContext(), "Bạn chưa nhập đủ dữ liệu!");
                } else {
                    int userID = arrayUser.get(index).getUserID();
                    editDataUser(fullName, phone, job, userID);
                    CheckConnection.showToast_Short(getApplicationContext(), "Lưu thành công!");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("check", "true");
                    intent.putExtra("userName", user);
                    startActivity(intent);
                }
            }
        });
    }

    public void mapping(){
        btnSave = findViewById(R.id.btnSave);
        txtUserName = findViewById(R.id.txtUserNameOfInformationUser);
        edtFullName = findViewById(R.id.edtFullName);
        edtJob = findViewById(R.id.edtJob);
        edtPhone = findViewById(R.id.edtPhone);
        arrayUser = MainActivity.arrayUser;
    }

    private void editDataUser(final String fullName, final String phone, final String job, final int userID) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathEditUser,
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
                hashMap.put("fullName", fullName);
                hashMap.put("phone", phone);
                hashMap.put("job", job);
                hashMap.put("userID", String.valueOf(userID));
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
}
