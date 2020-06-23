package btl.ltdd.apptracuubenh.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import btl.ltdd.apptracuubenh.Adapter.DiseaseAdapter;
import btl.ltdd.apptracuubenh.Fragment.ChapterFragment;
import btl.ltdd.apptracuubenh.Fragment.DiseaseFragment;
import btl.ltdd.apptracuubenh.Fragment.DiseaseSearchFragment;
import btl.ltdd.apptracuubenh.Fragment.FeedbackFragment;
import btl.ltdd.apptracuubenh.Fragment.GroupFragment;
import btl.ltdd.apptracuubenh.Fragment.HomeFragment;
import btl.ltdd.apptracuubenh.Fragment.InformationFragment;
import btl.ltdd.apptracuubenh.Util.Chapter;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.Disease;
import btl.ltdd.apptracuubenh.Util.DiseaseUser;
import btl.ltdd.apptracuubenh.Util.Group;
import btl.ltdd.apptracuubenh.Util.Server;
import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Adapter.ChapterAdapter;
import btl.ltdd.apptracuubenh.Adapter.GroupAdapter;
import btl.ltdd.apptracuubenh.Util.User;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private MaterialSearchView materialSearchView;
    private DrawerLayout drawerLayout;
    private String[] list;
    private TextView txtUserName;
    private ImageView imageView;
    private Intent intentCheck;
    private int check;
    public static ArrayList<User> arrayUser;
    public static ArrayList<DiseaseUser> arrayDiseaseUser;
    NavigationView navigationView;
    String textSearch = "", user = "";
    public static int userID = 0;
    DiseaseSearchFragment diseaseSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        getDataUser();
        getDataDiseaeseUser();


        intentCheck = getIntent();
        check = (intentCheck.getStringExtra("check") == null) ? 0 : 1;
        user = intentCheck.getStringExtra("userName");
        userID = intentCheck.getIntExtra("userID", 0);
        Log.d("userID", String.valueOf(userID));
        if (user != null) {
            Log.d("username", user);
            txtUserName.setText(user);
            imageView.setImageResource(R.mipmap.avatar_defaiult_round);
            imageView.setBackground(null);
        }
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            search();
            navigationView.bringToFront();
            navigationView.setNavigationItemSelectedListener(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
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
        if (!materialSearchView.isSearchOpen()) {
            materialSearchView.closeSearch();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.account) {
            if (check == 0) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            } else {
                Intent intent = new Intent(this, ProfileUserActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void mapping() {
        toolbar = findViewById(R.id.toolBar);
        materialSearchView = findViewById(R.id.material_search_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txtUserName = headerView.findViewById(R.id.txtTenUserNavHeader);
        imageView = headerView.findViewById(R.id.imgNavHeader);
        arrayUser = new ArrayList<>();
        arrayDiseaseUser = new ArrayList<>();
    }

    public void ActionBar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void search() {
        list = new String[]{"ho", "sốt", "đau đầu", "đỏ mắt", "sưng mắt"};
        materialSearchView.closeSearch();
        materialSearchView.setSuggestions(list);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                diseaseSearchFragment = new DiseaseSearchFragment();
                Bundle bundle = new Bundle();
                bundle.putString("TextSearch", textSearch);
                diseaseSearchFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        diseaseSearchFragment).commit();
                materialSearchView.closeSearch();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != "")
                    textSearch = newText;
                return true;
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                navigationView.getMenu().getItem(0).setChecked(true);
                break;
            case R.id.nav_chapter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChapterFragment()).commit();
                navigationView.getMenu().getItem(1).setChecked(true);
                break;
            case R.id.nav_group:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GroupFragment()).commit();
                navigationView.getMenu().getItem(2).setChecked(true);
                break;
            case R.id.nav_disease:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DiseaseFragment()).commit();
                navigationView.getMenu().getItem(3).setChecked(true);
                break;
            case R.id.nav_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FeedbackFragment()).commit();
                setCheckedFalse();

                break;
            case R.id.nav_information:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InformationFragment()).commit();
                setCheckedFalse();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setCheckedFalse() {
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(1).setChecked(false);
        navigationView.getMenu().getItem(2).setChecked(false);
        navigationView.getMenu().getItem(3).setChecked(false);
    }

    public void getDataUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    Log.d("data", String.valueOf(response));
                    for (int i = 0; i < response.length(); i++) {
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

    public void getDataDiseaeseUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathDiseaseUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    Log.d("dataDiseaseUser", String.valueOf(response));
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = null;
                        try {
                            int userID, diseaseID, saved;
                            jsonObject = response.getJSONObject(i);
                            userID = jsonObject.getInt("UserID");
                            diseaseID = jsonObject.getInt("DiseaseID");
                            saved = jsonObject.getInt("Saved");
                            arrayDiseaseUser.add(new DiseaseUser(userID, diseaseID, saved));
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
