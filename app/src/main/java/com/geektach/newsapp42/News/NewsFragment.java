package com.geektach.newsapp42.News;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.geektach.newsapp42.App;
import com.geektach.newsapp42.R;
import com.geektach.newsapp42.databinding.FragmentHomeBinding;
import com.geektach.newsapp42.databinding.FragmentNewsBinding;
import com.geektach.newsapp42.models.Article;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save() {
        Bundle bundle = new Bundle();
        String text = binding.editText.getText().toString().trim();
        if (text.isEmpty()) {
            boolean invalid = true;
            if (invalid) {
                Animation snake = AnimationUtils.loadAnimation(requireContext(), R.anim.snake);
                binding.editText.startAnimation(snake);
            }
            Toast.makeText(requireContext(), "Введите новость", Toast.LENGTH_SHORT).show();
            return;
        }
        Article article = new Article(text, System.currentTimeMillis());
        App.getDataBase().articleDao().insert(article);

        bundle.putSerializable("article", article);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}