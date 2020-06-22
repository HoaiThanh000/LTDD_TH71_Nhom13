package btl.ltdd.apptracuubenh.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import java.util.List;
import java.util.Map;

import btl.ltdd.apptracuubenh.Adapter.DiseaseAdapter;
import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.Disease;
import btl.ltdd.apptracuubenh.Util.Server;

public class DiseaseSearchFragment extends Fragment {
    private ListView lvDiseaseSearch;
    private List<Disease> mangDisease;
    private DiseaseAdapter diseaseAdapter;
    private String textSearch;
    private TextView txtNoData;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_disease_search, container, false);
        lvDiseaseSearch = view.findViewById(R.id.lv_of_disease_search);
        mangDisease = new ArrayList<>();
        diseaseAdapter = new DiseaseAdapter(mangDisease, getContext());

        getDataDisease();
        Bundle bundle = getArguments();
        if (bundle != null) {
            textSearch = bundle.getString("TextSearch");
        }
        if(diseaseAdapter == null){
            lvDiseaseSearch.setVisibility(View.GONE);
        }
        lvDiseaseSearch.setAdapter(diseaseAdapter);
        lvDiseaseSearch.setDivider(null);
        return view;
    }

    private void getDataDisease() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathSearchDisease,
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
                                mangDisease.add(new Disease(diseaseID, diseaseName, symptom, advice, Illustration, groupID));
                            }
                            diseaseAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getContext(), error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("search", textSearch);
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

