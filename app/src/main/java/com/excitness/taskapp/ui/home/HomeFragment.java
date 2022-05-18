package com.excitness.taskapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.excitness.taskapp.App;
import com.excitness.taskapp.Model.TaskModel;
import com.excitness.taskapp.R;
import com.excitness.taskapp.databinding.FragmentHomeBinding;
import com.excitness.taskapp.ui.detail.DetailFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NavController controller;
    private final TaskAdapter adapter = new TaskAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initResultListener();
        initRecycler();
        initNavController();
        initListeners();
    }

    private void initRecycler() {
        TaskAdapter taskAdapter = new TaskAdapter();
        adapter.addList(App.dataBase.taskDao().getAllTasks());
        binding.taskRv.setAdapter(taskAdapter);
    }

    private void initResultListener() {
        getParentFragmentManager().
                setFragmentResultListener(
                        DetailFragment.RESULT_HOME_KEY,
                        getViewLifecycleOwner(),
                        ((requestKey, result) -> {
                            String text = result.getString(DetailFragment.HOME_KEY);
                            String date = result.getString(DetailFragment.HOME_KEYS);
                            App.dataBase.taskDao().addTask(new TaskModel(text, date));
                        })
                );

    }

    private void initNavController() {
        NavHostFragment navHostController = (NavHostFragment)
                requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostController != null;
        controller = navHostController.getNavController();
    }

    private void initListeners(){
        binding.addTaskBtn.setOnClickListener(v -> controller.navigate(R.id.detailFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}