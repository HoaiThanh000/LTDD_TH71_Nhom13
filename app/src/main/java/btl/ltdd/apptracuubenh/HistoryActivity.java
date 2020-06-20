package btl.ltdd.apptracuubenh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import btl.ltdd.adapter.TkAdapter;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    String[] title = {
            "Covid19"
    };
    Integer[] imgid = {R.drawable.ic_history};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);
        Toolbar toolbar = findViewById(R.id.toolBarAccount);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        TkAdapter tkAdapter = new TkAdapter(this, title, imgid);
        listView = (ListView) findViewById(R.id.lv_of_account);
        listView.setAdapter(tkAdapter);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
