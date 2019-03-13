package com.example.dress.activity.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dress.R;
import com.example.dress.adapter.EnvelopeAdapter;
import com.example.dress.util.Envelope;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {
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
        List<Envelope> envelopes=new ArrayList<>();
        Envelope temp1= new Envelope("  我国历史文化悠久，是有名的礼仪之邦。人们的社会交往和思想感情交流，大多通过一定的礼仪形式和一定的文化活动方式来进行。在实际生活中，每个人都经常使用到一系列的应用文，如传统的书信、名片、柬贴、启事、题诗题词、对对联等，现代的如电报、传真、特快专递、电子邮件等。这些应用写作包含着丰富的礼仪内容，具有中华民族浓厚的文化色彩。\n" +
                "书信是一种向特定对象传递信息、交流思想感情的应用文书。“信”在古文中有音讯、消息之义，如“阳气极於上，阴信萌乎下。”（扬雄：《太玄经·应》）；另外，“信”也有托人所传之言可信的意思，不论是托人捎的口信，还是通过邮差邮递的书信用语言文字向特定对象传递信息和进行思想感情交流的信，一是有运用文字述说事情原委和表达自己思想感情的能力；二是具备相应的书写工具；三是有人进行传递。亲笔给亲戚朋友写信，不仅可以传达自己的思想感情，而且能给受信人以“见字如面”的亲切感；科技不断进步，又相继出现了电话、电报、邮寄录音带、录像带、电子邮件等交流信息的手段，可以预见，未来电子邮件这一新兴的手段会被越来越多的人运用。随着社会的发展，人与社会的关系也在进行重新建构，书信的运用除传统用法，即公函私函之外，一个新的发展动向便是原先私函类中因为个人需要而向政府机构、企事业单位、知名学者等个人所发的事务性的信件，这一类信件的使用量逐渐增多，值得注意。我们将其称为个人公文。","wwhhh","wangzhenghao",R.drawable.stamp_1);
        envelopes.add(temp1);
        envelopes.add(temp1);
        envelopes.add(temp1);
        envelopes.add(temp1);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        EnvelopeAdapter adapter = new EnvelopeAdapter(envelopes);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
