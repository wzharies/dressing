package com.example.dress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dress.R;
import com.example.dress.adapter.EnvelopeAdapter;
import com.example.dress.adapter.PersonLetterAdapter;
import com.example.dress.util.Envelope;
import com.example.dress.util.Letter;
import com.example.dress.util.PerLetter;
import com.example.dress.util.cache;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class More_letterActivity extends BaseActivity {
    @BindView(R.id.recycle_view_letter) RecyclerView recyclerView;
    private PerLetter letters;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_letter);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        letters = cache.getLetters().getAllletter().get(position);
        PersonLetterAdapter adapter = new PersonLetterAdapter(letters);
        recyclerView.setAdapter(adapter);
    }
}
