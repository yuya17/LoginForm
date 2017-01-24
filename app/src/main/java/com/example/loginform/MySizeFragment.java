package com.example.loginform;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


//SharedPrefferencesオブジェクトとの間でデータのやりとりをするクラス。
public class MySizeFragment extends Fragment {

    // TODO: Rename and change types and number of parameters
    public static MySizeFragment newInstance(Context context) {
        MySizeFragment fragment = new MySizeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_my_size,null);
        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //ぷリファレンスを取得
        SharedPreferences prefs = MySizeFragment.this.getActivity().getSharedPreferences("mysize",Context.MODE_PRIVATE);
        int neck = prefs.getInt("neck",0);
        int sleeve = prefs.getInt("sleeve",0);
        int waist = prefs.getInt("waist",0);
        int insideLeg = prefs.getInt("insideLeg",0);
        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        if(neck != 0){
            edText1.setText(Integer.toString(neck));
        }
        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        if (sleeve != 0){
            edText2.setText(Integer.toString(sleeve));
        }
        EditText edText3 = (EditText) getView().findViewById(R.id.editText3);
        if(waist != 0){
            edText3.setText(Integer.toString(waist));
        }
        EditText edText4 = (EditText) getView().findViewById(R.id.editText4);
        if(insideLeg != 0){
            edText4.setText(Integer.toString(insideLeg));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EditText edText1 = (EditText) getView().findViewById(R.id.editText1);
        EditText edText2 = (EditText) getView().findViewById(R.id.editText2);
        EditText edText3 = (EditText) getView().findViewById(R.id.editText3);
        EditText edText4 = (EditText) getView().findViewById(R.id.editText4);
        int neck;
        //ここで例外をキャッチして抜ける。
        try {
            neck = Integer.parseInt(edText1.getText().toString());
        }
        catch(NumberFormatException e){
            neck = 0;
        }
        int sleeve;
        try {
            sleeve = Integer.parseInt(edText2.getText().toString());
        }
        catch(NumberFormatException e){
            sleeve = 0;
        }
        int waist;
        try {
            waist = Integer.parseInt(edText3.getText().toString());
        }
        catch(NumberFormatException e){
            waist = 0;
        }
        int insideLeg;
        try {
            insideLeg = Integer.parseInt(edText4.getText().toString());
        }
        catch(NumberFormatException e){
            insideLeg = 0;
        }

        //保存
        SharedPreferences prefs  = MySizeFragment.this.getActivity().getSharedPreferences("mysize",Context.MODE_PRIVATE);
        //Preferencesの保存処理
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("neck",neck);
        editor.putInt("sleeve",sleeve);
        editor.putInt("waist",waist);
        editor.putInt("insideLeg",insideLeg);
        //commitの非同期版

        editor.apply();
    }

}
