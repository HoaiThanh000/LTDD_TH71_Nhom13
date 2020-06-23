package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class CreateAccountActivity extends AppCompatActivity {
    private Button btnRegister;
    private EditText edtPass, edtUser, edtComfirmPass;
    private TextView txtDaCoTK;
    private ArrayList<User> arrayUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mapping();
        getDataUser();


        final Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pass, confirm;
                user = edtUser.getText().toString();
                pass = edtPass.getText().toString();
                confirm= edtComfirmPass.getText().toString();
                if(user.equals("") || pass.equals("") || confirm.equals("")){
                    CheckConnection.showToast_Short(getApplicationContext(), "Bạn chưa nhập đủ dữ liệu!");
                } else if(user.length() >= 20 || pass.length() >= 20){
                    CheckConnection.showToast_Short(getApplicationContext(), "Bạn nhập quá dài(usernam, pass < 20 kí tự)");
                } else if(!pass.equals(confirm)){
                    CheckConnection.showToast_Short(getApplicationContext(), "Comfirm Password không đúng!");
                } else{
                    int check = 0;
                    for (User u : arrayUser) {
                        if(user.equals(u.getUserName())){
                            check = 1;
                            CheckConnection.showToast_Short(getApplicationContext(), "UserName đã tồn tại!");
                        }
                    }
                    if(check == 0){
                        addDataUser(user, pass);
                        CheckConnection.showToast_Short(getApplicationContext(), "Tạo tài khoản thành công");
                        intent.putExtra("UserName", user);
                        intent.putExtra("Password", pass);
                        startActivity(intent);
                    }
                }

            }
        });

        txtDaCoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public void mapping() {
        btnRegister = findViewById(R.id.btnRegister);
        edtPass = findViewById(R.id.edtInputPassword);
        edtUser = findViewById(R.id.edtInputUserName);
        edtComfirmPass = findViewById(R.id.edtConfirmPassword);
        txtDaCoTK = findViewById(R.id.txtDaCoTK);
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
                            int UserID;
                            String userName, fullName, password;
                            jsonObject = response.getJSONObject(i);
                            UserID = jsonObject.getInt("UserID");
                            fullName = jsonObject.getString("FullName");
                            userName = jsonObject.getString("UserName");
                            password = jsonObject.getString("Password");
                            arrayUser.add(new User(userName, password));
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

    private void addDataUser(final String userName, final String pass) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathAddUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("UserID", response);
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
                hashMap.put("userName", userName);
                hashMap.put("passWord", pass);
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
