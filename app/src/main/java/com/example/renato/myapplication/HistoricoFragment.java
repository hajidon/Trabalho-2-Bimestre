package com.example.renato.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricoFragment extends Fragment {


    public HistoricoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Persons");
        View view = inflater.inflate( R.layout.fragment_historico, container, false );
        RecyclerView recyclerView = (RecyclerView) view.findViewById( R.id.rv );
        recyclerView.setHasFixedSize( true );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager( linearLayoutManager );

        List<Persons> persons;
        persons = new ArrayList<>();
        persons.add(new Persons("Emma Wilson", "23 years old", R.drawable.ic_baseline_history_24px));
        persons.add(new Persons("Lavery Maiss", "25 years old", R.drawable.ic_baseline_history_24px));
        RVAdapter adapter = new RVAdapter(persons);
        recyclerView.setAdapter(adapter);

        return view;
    }
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        //super.onViewCreated(view, savedInstanceState);
//        //you can set the title for your toolbar here for different fragments different titles
//        getActivity().setTitle("Persons");
//
//        return inflater.inflate( R.layout.fragment_historico, container, false );
//    }
}
