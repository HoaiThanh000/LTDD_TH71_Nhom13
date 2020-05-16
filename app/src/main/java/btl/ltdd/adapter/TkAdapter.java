package btl.ltdd.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import btl.ltdd.apptracuubenh.R;

public class TkAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;
    private final Integer[] imgid;

    public TkAdapter(Activity context, String[] title, Integer[] imgid){
        super(context, R.layout.item_txtview_account, title);
        this.context = context;
        this.title = title;
        this.imgid = imgid;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.item_txtview_account, null, true);
        TextView t = (TextView) row.findViewById(R.id.txtview_title);
        ImageView imgId = (ImageView) row.findViewById(R.id.img_icon);

        t.setText(title[position]);
        imgId.setImageResource(imgid[position]);
        return row;
    }
}
