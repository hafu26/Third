package demo.isoft.com.third;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_guide_fragment3, container, false);
        ImageButton btn =(ImageButton)view.findViewById((R.id.btn_mag));
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        return  view;
    }

}
