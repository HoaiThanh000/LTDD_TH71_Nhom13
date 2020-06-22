package btl.ltdd.apptracuubenh.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import btl.ltdd.apptracuubenh.Adapter.ChapterAdapter;
import btl.ltdd.apptracuubenh.R;
import btl.ltdd.apptracuubenh.Util.Chapter;
import btl.ltdd.apptracuubenh.Util.CheckConnection;
import btl.ltdd.apptracuubenh.Util.Server;


public class HomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        ArrayList<String> mangViewFlipper = new ArrayList<>();
        mangViewFlipper.add("https://sohanews.sohacdn.com/zoom/600_315/2016/ava-1478143522959-105-96-492-719-crop-1478143542906.png");
        mangViewFlipper.add("https://binhphuoc.gov.vn/uploads/binhphuoc/news/2020_03/phong-chong-covid-19.png   ");
        mangViewFlipper.add("https://image.sggp.org.vn/Uploaded/2020/dudbexqdre/2020_03_07/covid_fspr.jpg");
        mangViewFlipper.add("https://campused.s3.ap-south-1.amazonaws.com/2017/04/Guide-to-Good-habits-for-healthy-Life_Course.png");
        mangViewFlipper.add("https://vinmec-prod.s3.amazonaws.com/images/20190607_060744_990408_tap-the-duc-buoi-sa.max-1800x1800.jpg");
        for (int i = 0; i < mangViewFlipper.size(); i++){
            ImageView imageView = new ImageView(getContext());
            Picasso.with(getContext()).load(mangViewFlipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
        return view;
    }

}
