package com.example.dress.activity.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.adapter.EnvelopeAdapter;
import com.example.dress.util.AllLetter;
import com.example.dress.util.Api.ApiService;
import com.example.dress.util.Letter;
import com.example.dress.util.PerLetter;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.cache;
import com.example.dress.util.jsondata.ResponseData;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Fragment2 extends Fragment {
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycle_view) RecyclerView recyclerView;

    private EnvelopeAdapter adapter=null;
    private List<Letter> envelopeList;

    public Fragment2() {
        super();
    }

    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement2, container, false);
        ButterKnife.bind(this,view);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        if(cache.getLetters()==null){
            adapter = new EnvelopeAdapter(new ArrayList<Letter>());
        }else {
            adapter = new EnvelopeAdapter(cache.getLetters().getAllFirstLetter());
        }
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void refresh(){
        JsonObject jsonuser = new JsonObject();
        jsonuser.addProperty("id",cache.getUser().getId());
        RetrofitManager.create(ApiService.class).getAllLetter(cache.getUser().getToken(),jsonuser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<List<Letter>>>(){
                    @Override
                    public void accept(@NonNull ResponseData<List<Letter>> rd) throws Exception {
                        if(rd!=null){
                            Toast.makeText(getActivity(),rd.getMsg(),Toast.LENGTH_SHORT);
                            if(rd.getRet()==0){
                                list_to_Allletter(rd.getData());
                            }
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    private void list_to_Allletter(List<Letter> letters){
        AllLetter allletters = new AllLetter();
        Map<Integer,Integer> position_to_id=new HashMap<Integer, Integer>();
        Map<Integer,String> id_to_name=new HashMap<Integer, String>();
        Map<Integer,Integer> id_to_position=new HashMap<Integer, Integer>();
        List<PerLetter> allletter=new ArrayList<PerLetter>();

        int id = cache.getUser().getId();
        for(Letter letter:letters){
            if(letter.getReceiverid()==id){
                if(id_to_name.get(letter.getSenderid())==null){
                    id_to_name.put(letter.getSenderid(),letter.getSender());
                    id_to_position.put(letter.getSenderid(),allletter.size());
                    position_to_id.put(allletter.size(),letter.getSenderid());
                    List<Letter> perletter = new ArrayList<Letter>();
                    perletter.add(letter);
                    allletter.add(new PerLetter(letter.getSenderid(),perletter));
                }else{
                    allletter.get(id_to_position.get(letter.getSenderid())).getPerletter().add(letter);
                }
            }else if(letter.getSenderid()==id){
                if(id_to_name.get(letter.getReceiverid())==null){
                    if(letter.getType()==0){
                        id_to_name.put(letter.getReceiverid(),letter.getReceiver());
                        id_to_position.put(letter.getReceiverid(),allletter.size());
                        position_to_id.put(allletter.size(),letter.getReceiverid());
                        List<Letter> perletter = new ArrayList<Letter>();
                        perletter.add(letter);
                        allletter.add(new PerLetter(letter.getReceiverid(),perletter));
                    }
                }else{
                    allletter.get(id_to_position.get(letter.getReceiverid())).getPerletter().add(letter);
                }
            }

        }
        allletters.setAllletter(allletter);
        allletters.setId_to_name(id_to_name);
        allletters.setId_to_position(id_to_position);
        allletters.setPosition_to_id(position_to_id);
        allletters.updateAllFirstLetter();
        cache.setLetters(allletters);

        adapter.changeDate(cache.getLetters().getAllFirstLetter());
    }
}
