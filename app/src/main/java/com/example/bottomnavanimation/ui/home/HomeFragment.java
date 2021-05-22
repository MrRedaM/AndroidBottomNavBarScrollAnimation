package com.example.bottomnavanimation.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavanimation.MainActivity;
import com.example.bottomnavanimation.R;
import com.example.bottomnavanimation.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private MainActivity activity;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private Adapter adapter;
    // For animation logic
    private Boolean navBarVisible = true;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity)getActivity();

        // Init recyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new Adapter(getContext());
        adapter.setList(getRandomStringList(50)); // Get random data list to test
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0){
                    // Scroll down
                    if (!navBarVisible){
                        activity.showNavBar();
                        navBarVisible = true;
                    }
                } else if (dy > 0){
                    // Scroll up
                    if (navBarVisible) {
                        activity.hideNavBar();
                        navBarVisible = false;
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private ArrayList<String> getRandomStringList(int size){
        ArrayList<String> randomList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            randomList.add("Level " + i);
        }
        return randomList;
    }

}