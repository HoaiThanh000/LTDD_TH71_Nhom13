package btl.ltdd.apptracuubenh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import btl.ltdd.apptracuubenh.Activity.DiseaseDetailActivity;
import btl.ltdd.apptracuubenh.Activity.MainActivity;
import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.Disease;
import btl.ltdd.apptracuubenh.Util.DiseaseUser;
import btl.ltdd.apptracuubenh.Util.Server;

public class DiseaseAdapter extends BaseAdapter {
    private List<Disease> arrayDisease;
    private ArrayList<Disease> arrayList;
    private Context context;
    private ArrayList<DiseaseUser> arrayDiseaseUser;

    public DiseaseAdapter(List<Disease> arrayDisease, Context context) {
        this.arrayDisease = arrayDisease;
        arrayList = new ArrayList<Disease>();
        arrayList.addAll(arrayDisease);
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayDisease.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayDisease.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void getFilter(String text) {
        text = text.toLowerCase(Locale.getDefault());
        arrayDisease.clear();
        if(text.length() == 0){
            arrayDisease.addAll(arrayList);
        } else {
            for (Disease d : arrayList) {
                if(d.getDiseaseName().toLowerCase(Locale.getDefault()).contains(text)){
                    arrayDisease.add(d);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHoder{
        TextView txtDiseaseName;
        TextView txtDiseaseSymptom;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        if(convertView == null){
            viewHoder = new ViewHoder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.item_listview_disease, null);
            viewHoder.txtDiseaseName = (TextView) convertView.findViewById(R.id.txtviewDiseaseName);
            viewHoder.txtDiseaseSymptom = (TextView) convertView.findViewById(R.id.txtviewDiseaseSymptom);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }
        final Disease disease = (Disease) getItem(position);
        viewHoder.txtDiseaseName.setText(disease.getDiseaseName());
        viewHoder.txtDiseaseSymptom.setText(disease.getSymptom());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 0;
                arrayDiseaseUser = MainActivity.arrayDiseaseUser;
                for(int i = 0; i < arrayDiseaseUser.size(); i++){
                    if(arrayDiseaseUser.get(i).getUserID() == MainActivity.userID && arrayDiseaseUser.get(i).getDiseaseID() == disease.getDiseaseID()){
                        check = 1;
                    }
                }
                if(MainActivity.userID != 0 && check == 0){
                    addDiseaseUser(MainActivity.userID, disease.getDiseaseID(), context);
                    //Cập nhật lại mảng disease user
                    getDataDiseaeseUser();
                    MainActivity.arrayDiseaseUser.clear();
                    for(int i = 0; i < arrayDiseaseUser.size(); i++){
                        Log.d("userid", String.valueOf(arrayDiseaseUser.get(i).getUserID()));
                        MainActivity.arrayDiseaseUser.add(arrayDiseaseUser.get(i));
                    }
                }
                Intent intent = new Intent(context, DiseaseDetailActivity.class);
                intent.putExtra("DiseaseID", disease.getDiseaseID());
                intent.putExtra("DiseaseName", disease.getDiseaseName());
                intent.putExtra("DiseaseSymptom", disease.getSymptom());
                intent.putExtra("DiseaseAdvice", disease.getAdvice());
                intent.putExtra("DiseaseIll", disease.getIllustration());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    private void addDiseaseUser(final int userID, final int diseaseID, final Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathAddDiseaseUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Result", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(context, error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("userID", String.valueOf(userID));
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
    private void getDataDiseaeseUser(){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathDiseaseUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for (int i = 0; i < response.length(); i++){
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
                CheckConnection.showToast_Short(context, error.toString());
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
