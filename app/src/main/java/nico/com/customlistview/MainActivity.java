package nico.com.customlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnIndexClickListener {


    GuideView gv;

    List<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        setContentView(R.layout.activity_main);



        gv = (GuideView) findViewById(R.id.guide_view);

        gv.setData(mDataList);

        gv.setOnIndexClickListener(this);

    }



    private void initData() {
        mDataList.add("A");
        mDataList.add("B");
        mDataList.add("C");
        mDataList.add("D");
        mDataList.add("E");
        mDataList.add("F");
        mDataList.add("G");
        mDataList.add("H");
        mDataList.add("I");
        mDataList.add("J");
        mDataList.add("K");
        mDataList.add("L");
        mDataList.add("M");
        mDataList.add("N");
        mDataList.add("O");
        mDataList.add("P");
        mDataList.add("Q");
        mDataList.add("R");
        mDataList.add("S");
        mDataList.add("T");
        mDataList.add("U");
        mDataList.add("V");
        mDataList.add("W");
        mDataList.add("X");
        mDataList.add("Y");
        mDataList.add("Z");
        mDataList.add("#");

    }

    @Override
    public void onIndexClick(int pos) {
//        Intent intent = new Intent(this, DialogActvity.class);
//        intent.putExtra("data", mDataList.get(pos));
//        startActivity(intent);


        if(pos==10086)
        {
            CustomToast.getInstance(this).cancelToast(this);
        }
        else
        {
            CustomToast.showToast(this,mDataList.get(pos));
        }
    }
}
