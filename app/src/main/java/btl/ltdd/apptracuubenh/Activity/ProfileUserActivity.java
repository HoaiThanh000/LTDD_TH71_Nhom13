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
import btl.ltdd.apptracuubenh.Util.Server;
import btl.ltdd.apptracuubenh.Util.User;

public class ProfileUserActivity extends AppCompatActivity {
    private TextView txtUserName, txtDangXuat, txtLichSu, txtBenhDaLuu, txtThongTin;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        mapping();

        Intent intent = getIntent();
        user = intent.getStringExtra("username");
        txtUserName.setText(user);

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


}
