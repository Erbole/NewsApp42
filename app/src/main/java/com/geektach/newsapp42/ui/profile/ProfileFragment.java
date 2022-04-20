package com.geektach.newsapp42.ui.profile;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.geektach.newsapp42.Prefs;
import com.geektach.newsapp42.R;
import com.geektach.newsapp42.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imageView.setOnClickListener(view1 -> mGetContext.launch("image/*"));
        Prefs prefs = new Prefs(requireContext());

        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.saveEditText(binding.etName.getText().toString());
            }
        });

        binding.etName.setText(prefs.isEditText());
        if (prefs.isImageView() != null) ;
        Glide.with(binding.imageView)
                .load(prefs.isImageView())
                .into(binding.imageView);
    }

    ActivityResultLauncher<String> mGetContext = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    Prefs prefs = new Prefs(requireContext());
                    if (result != null) {
                        Glide.with(binding.imageView)
                                .load(result)
                                .into(binding.imageView);
                        prefs.saveImageView(String.valueOf(result));
                    }
                }
            });
}