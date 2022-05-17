package com.excitness.taskapp.ui.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.excitness.taskapp.R;
import com.excitness.taskapp.databinding.FragmentDetailBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailFragment extends Fragment {

    public static final String HOME_KEY = "home_key";
    public static final String HOME_KEYS = "home_keys";
    public static final String RESULT_HOME_KEY = "result_home_key";

    private FragmentDetailBinding binding;

    String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);
        initListeners();
    }

    private void initListeners() {
    binding.saveBtn.setOnClickListener(v ->{
        sendDataToHomeFragment();
        closeFragment();
    });
    }

    private void closeFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    private void sendDataToHomeFragment() {
        String text = binding.taskEt.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(HOME_KEYS, date);
        bundle.putString(HOME_KEY, text);
        getParentFragmentManager().setFragmentResult(RESULT_HOME_KEY, bundle);
    }

}