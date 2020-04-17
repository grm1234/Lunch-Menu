package com.example.lunchilicious;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

/*public class MenuFragment extends Fragment{
        private MenuViewModel viewModel;
        private RecyclerView recyclerView;
        private ExAdapter recyclerViewAdapter;
        public static com.example.lunchilicious.MenuFragment newinstance() {return new com.example.lunchilicious.MenuFragment();}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        viewModel = new ViewModelProvider(this.requireActivity()).get(MenuViewModel.class);
        recyclerViewAdapter = new ExAdapter(viewModel.vMenu);
        recyclerView.setAdapter(recyclerViewAdapter);
        return  view;
    }
}*/
