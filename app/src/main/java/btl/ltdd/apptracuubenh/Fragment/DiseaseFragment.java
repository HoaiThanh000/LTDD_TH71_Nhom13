package btl.ltdd.apptracuubenh.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import btl.ltdd.apptracuubenh.Adapter.DiseaseAdapter;
import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.Disease;
import btl.ltdd.apptracuubenh.Util.Server;

public class DiseaseFragment extends Fragment {
    private ProgressBar progressBar;
    private ListView lvDisease;
    public ArrayList<Disease> mangDisease;
    private DiseaseAdapter diseaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disease, container, false);
        progressBar = view.findViewById(R.id.progress_bar_disease);
        lvDisease = view.findViewById(R.id.lv_of_disease);
        mangDisease = new ArrayList<>();
        diseaseAdapter = new DiseaseAdapter(mangDisease, getContext());
        getDataDisease();
        lvDisease.setDivider(null);
        lvDisease.setAdapter(diseaseAdapter);
        return view;
    }

    public void getDataDisease() {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathDisease, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = null;
                        try {
                            int diseaseID, groupID;
                            String diseaseName, symptom, advice, Illustration;
                            jsonObject = response.getJSONObject(i);
                            diseaseID = jsonObject.getInt("DiseaseID");
                            diseaseName = jsonObject.getString("DiseaseName");
                            symptom = jsonObject.getString("Symptom");
                            advice = jsonObject.getString("Advice");
                            Illustration = jsonObject.getString("Illustration");
                            groupID = jsonObject.getInt("GroupID");
                            mangDisease.add(new Disease(diseaseID, diseaseName, symptom, advice, Illustration, groupID));
                            diseaseAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getContext(), error.toString());
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
