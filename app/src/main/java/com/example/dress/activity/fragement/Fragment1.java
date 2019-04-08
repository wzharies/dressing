package com.example.dress.activity.fragement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.dress.R;
import com.example.dress.activity.Museum_Activity;
import com.example.dress.adapter.ShowLetterAdapter;
import com.example.dress.util.ShowLetter.TempShowLetter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Fragment1 extends Fragment implements OnBannerListener {
    //@BindView(R.id.recycle_view) RecyclerView recyclerView;
    //@BindView(R.id.banner) Banner mBanner;
    Banner mBanner;
    private MyLoader mMyImageLoader;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    //轮播图的图片列表和文字列表
    private ImageView letter_museum_icon;
    private ImageView letter_good_icon;
    private RecyclerView recyclerView;
    //recycleView的数据
    private List<TempShowLetter> showLetters = new ArrayList<>();
    //recycleView的适配器
    private ShowLetterAdapter adapter;

    public Fragment1() {
        super();
    }

    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initData() {
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553869136867&di=2342ce250390a08adb9ddede9fbcbc42&imgtype=0&src=http%3A%2F%2Fimglf2.ph.126.net%2FOmEDMcQiv1o1KtlgvH6AEg%3D%3D%2F1053560837844848150.jpg");
        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553869166757&di=e4d14c2cc3193d379c8a646e694e04db&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201806%2F19%2F20180619024855_hdwnd.jpg");
        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553869190627&di=de716c0314c5beb0b63be6cb5257c9e3&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201412%2F16%2F20141216144731_zCZJQ.jpeg");
        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553869206766&di=a6d74e95065af70de60753b3d5caef1e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201504%2F07%2F20150407H1649_HGXka.jpeg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");

        //测试数据
        TempShowLetter tempShowLetter_0 = new TempShowLetter("王正浩真厉害","很久以前注册了帐号，但是现在不想用那个id了。不过好像码云不支持修改用户名。这个功能对我很重要，我希望有这个功能。如果实在不行，请提供一个删除帐号的功能，我重新注册。",0);
        TempShowLetter tempShowLetter_1 = new TempShowLetter("王正浩真厉害","很久以前注册了帐号，但是现在不想用那个id了。不过好像码云不支持修改用户名。这个功能对我很重要，我希望有这个功能。如果实在不行，请提供一个删除帐号的功能，我重新注册。",0);
        showLetters.add(tempShowLetter_0);
        showLetters.add(tempShowLetter_1);
        showLetters.add(tempShowLetter_1);
        showLetters.add(tempShowLetter_1);
        showLetters.add(tempShowLetter_1);
        showLetters.add(tempShowLetter_1);
    }

    private void initView() {
        mMyImageLoader = new MyLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        mBanner.setBannerTitles(list_title);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mBanner.setImages(list_path)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement1, container, false);
        mBanner = view.findViewById(R.id.banner);
        //recyclerView = (RecyclerView)view.findViewById(R.id.fragment1_recycle_view) ;

        letter_museum_icon = view.findViewById(R.id.letter_museum);
        letter_good_icon = view.findViewById(R.id.good_letter);
        //LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity());
       // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //adapter = new ShowLetterAdapter(showLetters);
       // recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(adapter);





        //加载图片资源
        Glide.with(this)
                .load(R.mipmap.letter_museum)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(letter_museum_icon);
        Glide.with(this)
                .load(R.mipmap.letter_good)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(letter_good_icon);
        //设置博物馆的点击动作
        letter_museum_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Museum_Activity.class);
                startActivity(intent);

            }
        });
        //设置精选信件的点击事件
        letter_good_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), Museum_good_letter_Activity.class);
                //startActivity(intent);
            }
        });

        TextView more_textView = view.findViewById(R.id.more);

        more_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"you want to see more",Toast.LENGTH_SHORT).show();
            }
        });
        //设置图片加载器
        initData();
        initView();
        return view;

    }





    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }


    /**
     * 图片加载类
     */
    /*
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
    */

    private class MyLoader extends ImageLoader  {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String)path).into((ImageView) imageView);
        }
    }

}
