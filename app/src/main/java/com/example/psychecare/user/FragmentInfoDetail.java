package com.example.psychecare.user;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.psychecare.LoginActivity;
import com.example.psychecare.R;


public class FragmentInfoDetail extends Fragment {
    private TextView txtName, txtPhone, txtEmail;
    private Button btn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infodetail, container, false);

        txtName = view.findViewById(R.id.textViewName);
        txtPhone = view.findViewById(R.id.textViewPhone);
        txtEmail = view.findViewById(R.id.textViewEmail);
        btn = view.findViewById(R.id.btnlogout);


        Context context = getActivity();
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE);
            String userName = sharedPreferences.getString("user_name", "");
            String userPhoneNumber = sharedPreferences.getString("user_phone_number", "");
            String userEmail = sharedPreferences.getString("user_email", "");
            txtName.setText(userName);
            txtPhone.setText(userPhoneNumber);
            txtEmail.setText(userEmail);

            // Sử dụng các giá trị này trong fragment
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
