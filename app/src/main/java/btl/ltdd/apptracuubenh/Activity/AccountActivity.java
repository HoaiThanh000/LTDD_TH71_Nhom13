package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import btl.ltdd.apptracuubenh.R;

public class AccountActivity extends AppCompatActivity {
    private ListView listView;
    String[] title = {
            "Tên tài khoản", "Lịch sử", "Bệnh đã lưu", "Đăng xuất"
    };
    Integer[] imgid = {R.drawable.img_user, R.drawable.ic_history, R.drawable.ic_disease_saved, R.drawable.ic_logout};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolBarAccount);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //TkAdapter tkAdapter = new TkAdapter(this, title, imgid);
        listView = (ListView) findViewById(R.id.lv_of_disease_watched);
        //listView.setAdapter(tkAdapter);
        final Intent intent1 = new Intent(AccountActivity.this, CreateAccountActivity.class);
        final Intent intent2 = new Intent(AccountActivity.this,HistoryActivity.class);
        final Intent intent3 = new Intent(AccountActivity.this,SaveActivity.class);
        final Intent intent4 = new Intent(AccountActivity.this,MainActivity.class);
        //startActivity(intent1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(intent1);
                        break;
                    case 1:
                        startActivity(intent2);
                        break;
                    case 2:
                        startActivity(intent3);
                        break;
                    case 3:
                        startActivity(intent4);
                        break;
                }
                Toast.makeText(AccountActivity.this,"1" + position,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
