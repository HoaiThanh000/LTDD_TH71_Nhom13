package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import btl.ltdd.apptracuubenh.Util.Server;
import btl.ltdd.apptracuubenh.Util.User;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView txtRegister;
    private EditText edtUserName, edtPassword;
    private ArrayList<User> arrayUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        getDataUser();
        Intent intent = getIntent();
        edtUserName.setText(intent.getStringExtra("UserName"));
        edtPassword.setText(intent.getStringExtra("Password"));
        final Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
        final Intent intent2 = new Intent(LoginActivity.this,CreateAccountActivity.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUserName.getText() == null || edtPassword == null){
                    CheckConnection.showToast_Short(getApplicationContext(), "Chưa nhập đủ thông tin!");
                } else if(edtUserName.getText().length() >= 20 || edtPassword.getText().length() >= 20){
                    CheckConnection.showToast_Short(getApplicationContext(), "Bạn nhập quá dài(usernam, pass < 20 kí tự)");
                } else{
                    //check coi có đăng nhập đc chưa
                    int check = 0;
                    for (User u: arrayUser) {
                        if(edtUserName.getText().toString().equals(u.getUserName()) && edtPassword.getText().toString().equals(u.getPassword())){
                            intent1.putExtra("check", "true");
                            intent1.putExtra("userName", u.getUserName());
                            intent1.putExtra("userID", u.getUserID());
                            startActivity(intent1);
                            check = 1;
                        }
                    }
                    if(check == 0){
                        CheckConnection.showToast_Short(getApplicationContext(), "Tên tài khoản và mật khẩu không đúng!");
                    }
                }
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
    }

    public void mapping(){
        btnLogin = findViewById(R.id.btnSignin);
        txtRegister = findViewById(R.id.txtRegister);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        arrayUser = new ArrayList<>();
    }

    public void getDataUser(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    Log.d("data", String.valueOf(response));
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = null;
                        try {
                            int userID;
                            String userName, fullName, password, phone, job;
                            jsonObject = response.getJSONObject(i);
                            userID = jsonObject.getInt("UserID");
                            fullName = jsonObject.getString("FullName");
                            userName = jsonObject.getString("UserName");
                            password = jsonObject.getString("Password");
                            phone = jsonObject.getString("Phone");
                            job = jsonObject.getString("Job");
                            arrayUser.add(new User(userID, fullName, userName, password, phone, job));
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
