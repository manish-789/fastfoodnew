package com.example.fastfood1.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
        ArrayList<FoodItem> foodItems=new ArrayList<FoodItem>();
        foodItems.add(new FoodItem("Bhel Puri ",R.drawable.bhel_, 40));
        foodItems.add(new FoodItem("Chines bhel ",R.drawable.chines_bhel, 25));
        foodItems.add(new FoodItem("Dahi puri ",R.drawable.dahipuri,50));
        foodItems.add(new FoodItem("Papadi chat ",R.drawable.papadi_chat,70));
        foodItems.add(new FoodItem("Sev",R.drawable.sev,45));
        foodItems.add(new FoodItem("Sukha bhel",R.drawable.sukha_bhel,30));
        foodItems.add(new FoodItem("Vada paav",R.drawable.vada_paav,20));
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