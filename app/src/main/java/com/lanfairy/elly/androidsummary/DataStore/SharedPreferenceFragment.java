package com.lanfairy.elly.androidsummary.DataStore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lanfairy.elly.androidsummary.DataStore.util.UserInfoUtil;
import com.lanfairy.elly.androidsummary.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SharedPreferenceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SharedPreferenceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@ContentView(R.layout.fragment_shared_preference)
public class SharedPreferenceFragment extends Fragment {

    private View rootView;
    @ViewInject(R.id.userName)
    private EditText userNameEt;
    @ViewInject(R.id.passWord)
    private EditText passWordEt;
    @ViewInject(R.id.btn_login)
    private Button btnLogin;
    @ViewInject(R.id.cb_remember)
    private CheckBox cbRemeber;

    public SharedPreferenceFragment() {
        // Required empty public constructor
    }


    public static SharedPreferenceFragment getInstance() {
        SharedPreferenceFragment fragment = new SharedPreferenceFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        rootView = inflater.inflate(R.layout.fragment_shared_preference, container, false);
//        userNameEt = rootView.findViewById(R.id.userName);
//        passWordEt = rootView.findViewById(R.id.passWord);

//        btnLogin = rootView.findViewById(R.id.btn_login);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = userNameEt.getText().toString().trim();
//                String password = passWordEt.getText().toString().trim();
//                if (username.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(getContext(),"用户名或密码不能为空", Toast.LENGTH_LONG).show();
//                    return;
//                }else{
//                    if (cbRemeber.isChecked()){
//                        boolean isSaved = UserInfoUtil.saveUserInfo(getContext(), username, password);
//                        Toast.makeText(getContext(), isSaved ? "用户信息保存成功" : "用户信息保存失败", Toast.LENGTH_LONG).show();
//                    }else {
//                        Toast.makeText(getContext(), "用户信息无需保存", Toast.LENGTH_LONG).show();
//                        UserInfoUtil.deleteUserInfo(getContext());
//                    }
//                }
//
//            }
//        });
//        cbRemeber = rootView.findViewById(R.id.cb_remember);

        rootView = x.view().inject(this, inflater, container);
        Map<String, String> userInfo = UserInfoUtil.getUserInfo(getContext());
        String username = userInfo.get(UserInfoUtil.USERNAME);
        String password = userInfo.get(UserInfoUtil.PASSWORD);

        cbRemeber.setChecked(!userInfo.isEmpty() && !password.isEmpty());
        userNameEt.setText(username);
        passWordEt.setText(password);

        return rootView;
    }

    @Event(R.id.btn_login)
    private void btnLoginonClick(View v) {
        String username = userNameEt.getText().toString().trim();
        String password = passWordEt.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "用户名或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (cbRemeber.isChecked()) {
                boolean isSaved = UserInfoUtil.saveUserInfo(getContext(), username, password);
                Toast.makeText(getContext(), isSaved ? "用户信息保存成功" : "用户信息保存失败", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "用户信息无需保存", Toast.LENGTH_LONG).show();
                UserInfoUtil.deleteUserInfo(getContext());
            }
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
