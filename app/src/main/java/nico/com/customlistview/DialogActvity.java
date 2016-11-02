package nico.com.customlistview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/1.
 */
public class DialogActvity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.in, R.anim.out);
        String index = getIntent().getStringExtra("data");
        TextView textView = new TextView(this);
        textView.setText(index);
        textView.setTextSize(60);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.LTGRAY);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(50, 50, 50, 50);
        setContentView(textView);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(R.anim.in, R.anim.out);
            }
        },500);
    }



}
