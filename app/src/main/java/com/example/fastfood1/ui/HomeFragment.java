package com.example.fastfood1.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.fastfood1.FoodItem;
import com.example.fastfood1.FoodItemAdapter;
import com.example.fastfood1.R;
import com.example.fastfood1.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayList<FoodItem> foodItems=new ArrayList();
        foodItems.add(new FoodItem("Bhel Puri",R.drawable.bhel));
        foodItems.add(new FoodItem("Bhel Puri",R.drawable.bhel));
        foodItems.add(new FoodItem("Bhel Puri",R.drawable.bhel));
        binding.recyclerview.setAdapter(new FoodItemAdapter(requireContext(), foodItems));
        binding.recyclerview.setLayoutManager(new GridLayoutManager(requireContext(),2));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}