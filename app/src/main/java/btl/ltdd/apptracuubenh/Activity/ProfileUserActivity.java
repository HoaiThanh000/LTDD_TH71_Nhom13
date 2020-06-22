package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import btl.ltdd.apptracuubenh.R;

public class ProfileUserActivity extends AppCompatActivity {
    private TextView txtUserName, txtDangXuat, txtLichSu, txtBenhDaLuu, txtThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        mapping();

        Intent intent = getIntent();
        txtUserName.setText(intent.getStringExtra("username"));
        txtThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InformationUserActivity.class);
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
