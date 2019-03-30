package com.example.dress.util;

import android.util.Log;

import com.example.dress.R;

import java.lang.reflect.Field;

public class GetResouce {
    //通过反射机制获取id
    public static int getResource(int group,int index){
        String imageName="stamp"+String.valueOf(group)+"_"+String.valueOf(index);
        Class drawble = R.drawable.class;
        Log.i("Imageid",imageName);
        try {
            Field field = drawble.getField(imageName);
            int resId = field.getInt(imageName);
            return resId;
        } catch (NoSuchFieldException e) {//如果没有在"mipmap"下找到imageName,将会返回0
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }
    }
}
