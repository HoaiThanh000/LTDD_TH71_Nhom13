package btl.ltdd.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import btl.ltdd.Chapter;
import btl.ltdd.Group;
import btl.ltdd.apptracuubenh.R;

public class GroupAdapter extends BaseAdapter {
    private ArrayList<Group> arrayGroup;
    private Context context;

    public GroupAdapter(ArrayList<Group> arrayGroup, Context context) {
        this.arrayGroup = arrayGroup;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayGroup.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGroup.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHoder{
        TextView txtGroup;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        if(convertView == null){
            viewHoder = new ViewHoder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.item_listview_group, null);
            viewHoder .txtGroup = (TextView) convertView.findViewById(R.id.txtviewGroup);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }
        Group group = (Group) getItem(position);
        viewHoder.txtGroup.setText(group.getGroupName());
        return convertView;
    }
}
