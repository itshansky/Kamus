package com.farhanrinsky.kamus.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farhanrinsky.kamus.R;
import com.farhanrinsky.kamus.adapter.KamusAdapter;
import com.farhanrinsky.kamus.database.KamusHelper;
import com.farhanrinsky.kamus.model.KamusModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_eng_id extends Fragment {

    @BindView(R.id.search_kata)
    SearchView searchKata;
    @BindView(R.id.rv_kata)
    RecyclerView rvKata;

    KamusAdapter adapter;
    KamusHelper kamusHelper;

    ArrayList<KamusModel> listKamus = new ArrayList<>();

    public Fragment_eng_id() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getResources().getString(R.string.app_name)+"/"+getResources().getString(R.string.inggris_indo));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eng_id, container, false);
        ButterKnife.bind(this, v);

        int searchPlateId = searchKata.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchKata.findViewById(searchPlateId);
        if (searchPlate!=null) {
            searchPlate.setBackgroundColor (Color.TRANSPARENT);
            int searchTextId = searchPlate.getContext ().getResources ().getIdentifier ("android:id/search_src_text", null, null);

        }

        kamusHelper = new KamusHelper(getActivity());
        adapter = new KamusAdapter(getActivity());
        rvKata.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvKata.setHasFixedSize(true);
        rvKata.setAdapter(adapter);

        initAllData();

        searchKata.setQueryHint(getString(R.string.pencarian));
        searchKata.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                initDataById(newText);
                return true;
            }
        });

        return v;
    }

    private void initDataById(String query) {
        kamusHelper.open();
        listKamus = kamusHelper.selectByKata(String.valueOf(query), true);
        kamusHelper.close();

        adapter.replaceItem(listKamus);
    }

    private void initAllData() {
        kamusHelper.open();
        listKamus = kamusHelper.selectAll(true);
        kamusHelper.close();

        adapter.addItem(listKamus);
    }

}