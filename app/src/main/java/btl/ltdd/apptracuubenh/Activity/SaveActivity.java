package btl.ltdd.apptracuubenh.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import btl.ltdd.apptracuubenh.Adapter.DiseaseAdapter;
import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.Disease;
import btl.ltdd.apptracuubenh.Util.DiseaseUser;
import btl.ltdd.apptracuubenh.Util.Server;

public class SaveActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<DiseaseUser> arrayDiseaseUser = MainActivity.arrayDiseaseUser;
    private ArrayList<Disease> arrayDisease;
    private DiseaseAdapter diseaseAdapter;
    private int userID = MainActivity.userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Toolbar toolbar = findViewById(R.id.toolBarAccount);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.lv_of_disease_save);
        arrayDisease = new ArrayList<>();
        diseaseAdapter = new DiseaseAdapter(arrayDisease, getApplicationContext());

        for(int i = 0; i < arrayDiseaseUser.size(); i++){
            if(arrayDiseaseUser.get(i).getUserID() == userID && arrayDiseaseUser.get(i).getSaved() == 1){
                int diseaseID = arrayDiseaseUser.get(i).getDiseaseID();
                searchDataDiseaseById(diseaseID);
            }
        }
        listView.setAdapter(diseaseAdapter);
        listView.setDivider(null);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchDataDiseaseById(final int diseaseID) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathSearchDiseaseById,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = null;
                                int diseaseID, groupID;
                                String diseaseName, symptom, advice, Illustration;
                                jsonObject = jsonArray.getJSONObject(i);
                                Log.d("res123", String.valueOf(jsonObject));
                                diseaseID = jsonObject.getInt("DiseaseID");
                                diseaseName = jsonObject.getString("DiseaseName");
                                symptom = jsonObject.getString("Symptom");
                                advice = jsonObject.getString("Advice");
                                Illustration = jsonObject.getString("Illustration");
                                groupID = jsonObject.getInt("GroupID");
                                arrayDisease.add(new Disease(diseaseID, diseaseName, symptom, advice, Illustration, groupID));
                            }
                            diseaseAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                hashMap.put("diseaseID", String.valueOf(diseaseID));
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
