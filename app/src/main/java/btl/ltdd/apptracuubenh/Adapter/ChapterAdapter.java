package btl.ltdd.apptracuubenh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import btl.ltdd.apptracuubenh.Util.Chapter;
import btl.ltdd.apptracuubenh.R;

public class ChapterAdapter extends BaseAdapter {
    private ArrayList<Chapter> arrayChapter;
    private Context context;

    public ChapterAdapter(ArrayList<Chapter> arrayChapter, Context context) {
        this.arrayChapter = arrayChapter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayChapter.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayChapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHoder{
        TextView txtChapter;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        if(convertView == null){
            viewHoder = new ViewHoder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.item_listview_chapter, null);
            viewHoder.txtChapter = (TextView) convertView.findViewById(R.id.txtviewChapter);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }
        Chapter chapter = (Chapter) getItem(position);
        viewHoder.txtChapter.setText(chapter.getChapterName());
        return convertView;
    }
}
