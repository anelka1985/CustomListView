package nico.com.customlistview;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/2.
 */
public class CustomToast extends Toast {

    private static CustomToast mCustomToast = null;

    public static CustomToast getInstance(Context context){
        if(mCustomToast==null)
        {
            mCustomToast = new CustomToast(context);
        }
        return mCustomToast;
    }

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CustomToast(Context context) {
        super(context);


    }


    public static void showToast(Context context, String msg) {

        TextView textView = new TextView(context);

        textView.setTextSize(60);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.LTGRAY);
        textView.setGravity(Gravity.CENTER);
        textView.setText(msg);
        getInstance(context).setView(textView);
        getInstance(context).setGravity(Gravity.CENTER, 0, 0);
        getInstance(context).setDuration(Toast.LENGTH_SHORT);

        getInstance(context).show();

    }

    public void cancelToast(Context context){
        getInstance(context).cancel();
    }


}
