package btl.ltdd.apptracuubenh.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import btl.ltdd.apptracuubenh.R;

public class FeedbackFragment extends Fragment {
    private EditText edtSoSao, edtGopY;
    private Button btnGui;
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        final View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        edtSoSao = view.findViewById(R.id.edtSoSao);
        edtGopY = view.findViewById(R.id.edtGopY);
        btnGui = view.findViewById(R.id.btnGui);
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtSoSao.getText().toString().equals("")){
                    int soSao = Integer.parseInt(edtSoSao.getText().toString());
                    if(soSao >= 0 && soSao <= 5){
                        if(edtGopY.getText().toString().equals("")){
                            Toast.makeText(view.getContext(), "Bạn chưa nhập góp ý!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent  intent = new Intent(Intent.ACTION_SENDTO);
                            intent.setData(Uri.parse("mailto:"));
                            intent.putExtra(Intent.EXTRA_EMAIL, new String []{"1751010136thanh@ou.edu.vn"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App tra cứu bệnh");
                            intent.putExtra(Intent.EXTRA_TEXT, String.format("Tôi nghĩ app được: %d\nGóp ý: %s", soSao, edtGopY.getText()));
                            try{
                                startActivity(Intent.createChooser(intent, "Hãy chọn ứng dụng email"));
                            }catch (android.content.ActivityNotFoundException ex){
                                Toast.makeText(view.getContext(), "Không có ứng dụng email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        Toast.makeText(view.getContext(), "Bạn nhập số sao chưa đúng!!!(0->5)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Bạn chưa nhập số sao!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
