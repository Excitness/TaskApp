package com.excitness.taskapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class HomeFragment extends Fragment implements TaskAdapter.ItemClick {

    private FragmentHomeBinding binding;
    private NavController controller;
    private TaskAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        initResultListener();
        initRecycler();
        initNavController();
        initListeners();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.action_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sort_alph:
                adapter.addList(App.dataBase.taskDao().getAllTasksAlph());
                return true;
            case R.id.menu_sort_date:
                adapter.addList(App.dataBase.taskDao().getAllTasksDate());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initRecycler() {
        adapter = new TaskAdapter(this);
        adapter.addList(App.dataBase.taskDao().getAllTasks());
        binding.taskRv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.addList(App.dataBase.taskDao().getAllTasks());
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
        getParentFragmentManager().
                setFragmentResultListener(
                        DetailFragment.RESULT_HOME_KEY_EDIT,
                        getViewLifecycleOwner(),
                        ((requestKey, result) -> {
                            TaskModel model = (TaskModel) result.getSerializable("edit");
                            App.dataBase.taskDao().update(model);
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

    private void initListeners() {
        binding.addTaskBtn.setOnClickListener(v -> controller.navigate(R.id.detailFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void click(int pos) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", adapter.getItem(pos));
        controller.navigate(R.id.detailFragment, bundle);
    }

    @Override
    public void longClick(int pos) {
        App.dataBase.taskDao().delete(adapter.getItem(pos));
        adapter.delete(pos);
    }
}