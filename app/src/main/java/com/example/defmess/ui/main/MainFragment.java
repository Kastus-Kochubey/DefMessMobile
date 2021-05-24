package com.example.defmess.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.defmess.R;
import com.example.defmess.RequestToServer;
import com.example.defmess.databinding.FragmentMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainFragment extends Fragment {

//    private MainViewModel mainViewModel;
    private FragmentMainBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        mainViewModel =
//                new ViewModelProvider(this).get(MainViewModel.class);

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Button button = binding.button;
        ScrollView scrollView = binding.ScrollView;
        LinearLayout linearLayout = binding.LinearLayout;

        button.setOnClickListener((v) -> {
//            RequestToServer request = new RequestToServer("https://82.148.29.139");
            RequestToServer request = new RequestToServer("http://127.0.0.1:5000");
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", "russian_post");
                jsonObject.put("password", "1234");
//                String text = request.post("/user/login", "{'email': 'russian_post','password': '1234'}");
                String text = request.post("/user/login", jsonObject.toString());
                for (int i = 0; i < 20; i++) {
                    TextView textView = new TextView(root.getContext());
                    textView.setTextColor(Color.rgb(255, 13 * i % 256, 150));
                    textView.setText(text);
                    linearLayout.addView(textView);
                }
            } catch (IOException | ExecutionException | InterruptedException | JSONException e) {
                e.printStackTrace();
            }

        });

//        final LinearLayout linearLayout = binding.LinearLayout;
//        mainViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
