package btl.ltdd.apptracuubenh;

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

import btl.ltdd.adapter.TkAdapter;

public class LoginActivity extends AppCompatActivity {
    private ListView listView;
    String[] title = {
            "Dang nhap", "Dang ky"
    };
    Integer[] imgid = {R.drawable.img_user,
            R.drawable.ic_logout};
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
        final Intent intent1 = new Intent(LoginActivity.this,SiginActivity.class);
        final Intent intent2 = new Intent(LoginActivity.this,CreateAccountActivity.class);
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
                }
                Toast.makeText(LoginActivity.this,"1" + position,Toast.LENGTH_SHORT).show();
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
