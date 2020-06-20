package btl.ltdd.apptracuubenh;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import btl.ltdd.Chapter;
import btl.ltdd.CheckConnection;
import btl.ltdd.Group;
import btl.ltdd.Server;
import btl.ltdd.adapter.ChapterAdapter;
import btl.ltdd.adapter.GroupAdapter;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private MaterialSearchView materialSearchView;
    private DrawerLayout drawerLayout;
    private String[] list;
    private ListView lvChapter, lvGroup;
    private ArrayList<Chapter> mangChapter;
    private ArrayList<Group> mangGroup;
    private ChapterAdapter chapterAdapter;
    private GroupAdapter groupAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            search();
            getDataChapter();
            //getDataGroup();
        } else {
            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối internet");
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        //icon search
        MenuItem item = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.account){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mapping() {
        toolbar = findViewById(R.id.toolBar);
        materialSearchView = (MaterialSearchView) findViewById(R.id.material_search_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        mangChapter = new ArrayList<>();
        mangGroup = new ArrayList<>();
        chapterAdapter = new ChapterAdapter(mangChapter, getApplicationContext());
        lvChapter = (ListView) findViewById(R.id.lv_of_chapter);
        lvChapter.setAdapter(chapterAdapter);
        groupAdapter = new GroupAdapter(mangGroup, getApplicationContext());
        //lvGroup = (ListView) findViewById(R.id.lv_of_group);
        //lvGroup.setAdapter(groupAdapter);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar1);
    }

    public void ActionBar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void search() {
        list = new String[]{"android", "ios"};
        materialSearchView.closeSearch();
        materialSearchView.setSuggestions(list);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //khi click icon search hien searchview ra
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                materialSearchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
            }
        });
    }
    public void getDataChapter(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathChapter, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    progressBar.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = null;
                        try {
                            int chapterID;
                            String chapterName;
                            jsonObject = response.getJSONObject(i);
                            chapterID = jsonObject.getInt("ChapterID");
                            chapterName = jsonObject.getString("ChapterName");
                            mangChapter.add(new Chapter(chapterID, chapterName));
                            chapterAdapter.notifyDataSetChanged();
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

    /*public void getDataGroup(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathGroup, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    progressBar.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = null;
                        try {
                            int groupID;
                            int chapterID;
                            String groupName = "";
                            jsonObject = response.getJSONObject(i);
                            groupID = jsonObject.getInt("GroupID");
                            groupName = jsonObject.getString("GroupName");
                            chapterID = jsonObject.getInt("ChapterID");
                            mangGroup.add(new Group(groupID, groupName, chapterID));
                            groupAdapter.notifyDataSetChanged();
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
    }*/
}
