package com.example.renato.myapplication;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        getActivity().setTitle("Historico");
        final View view = inflater.inflate( R.layout.fragment_historico, container, false );
        RecyclerView recyclerView = (RecyclerView) view.findViewById( R.id.rv );
        recyclerView.setHasFixedSize( true );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager( linearLayoutManager );

        List<Historico> historico;
        historico = new ArrayList<>();
        historico.add(new Historico("Emma Wilson", "12:20",20,10));
        historico.add(new Historico("Lavery Maiss", "17:20",30, 20));
        final RVAdapter adapter = new RVAdapter( historico );
        recyclerView.setAdapter(adapter);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Refresh items
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(view.getContext(), "Arquivos atualizados!", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });

        return view;
    }
}
