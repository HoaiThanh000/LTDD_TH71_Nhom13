package btl.ltdd.apptracuubenh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import btl.ltdd.apptracuubenh.Activity.DiseaseDetailActivity;
import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.Disease;

public class DiseaseAdapter extends BaseAdapter {
    private List<Disease> arrayDisease;
    private ArrayList<Disease> arrayList;
    private Context context;

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
                Intent intent = new Intent(context, DiseaseDetailActivity.class);
                intent.putExtra("DiseaseName", disease.getDiseaseName());
                intent.putExtra("DiseaseSymptom", disease.getSymptom());
                intent.putExtra("DiseaseAdvice", disease.getAdvice());
                intent.putExtra("DiseaseIll", disease.getIllustration());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
