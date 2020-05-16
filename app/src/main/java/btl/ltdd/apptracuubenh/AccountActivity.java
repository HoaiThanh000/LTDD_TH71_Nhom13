package btl.ltdd.apptracuubenh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import btl.ltdd.adapter.TkAdapter;

public class AccountActivity extends AppCompatActivity {
    private ListView listView;
    String[] title = {
            "Tên tài khoản", "Lịch sử", "Bệnh đã lưu", "Đăng xuất"
    };
    Integer[] imgid = {R.drawable.img_user, R.drawable.ic_history, R.drawable.ic_disease_saved, R.drawable.ic_logout};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = findViewById(R.id.toolBarAccount);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        TkAdapter tkAdapter = new TkAdapter(this, title, imgid);
        listView = (ListView) findViewById(R.id.lv_of_account);
        listView.setAdapter(tkAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
