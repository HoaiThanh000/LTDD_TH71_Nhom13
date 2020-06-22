package btl.ltdd.apptracuubenh.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

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

import btl.ltdd.apptracuubenh.Adapter.ChapterAdapter;
import btl.ltdd.apptracuubenh.Adapter.GroupAdapter;
import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.Chapter;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.Group;
import btl.ltdd.apptracuubenh.Util.Server;

public class GroupFragment extends Fragment {
    private ProgressBar progressBar;
    private ListView lvGroup;
    private ArrayList<Group> mangGroup;
    private GroupAdapter groupAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        progressBar = view.findViewById(R.id.progress_bar_group);
        lvGroup = view.findViewById(R.id.lv_of_group);
        mangGroup = new ArrayList<>();
        groupAdapter = new GroupAdapter(mangGroup, getContext());
        getDataGroup();
        lvGroup.setDivider(null);
        lvGroup.setAdapter(groupAdapter);
        return view;
    }
    public void getDataGroup(){
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
