package com.example.customtablayoutdemo.paramimport;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.customtablayoutdemo.R;
import com.example.customtablayoutdemo.base.BaseContract;
import com.example.customtablayoutdemo.base.BaseMVPActivity;
import com.example.customtablayoutdemo.customView.CustomViewPager;
import com.example.customtablayoutdemo.customView.tablayout.ColorTrackTabLayout;
import com.example.customtablayoutdemo.model.BaseStation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
@SuppressLint("Registered")
public class ParamImportActivity extends BaseMVPActivity {
    ImageButton pointer;
    TextView titleTxt;
    ColorTrackTabLayout tab;
    CustomViewPager viewPager;
    Button btnCancel;
    Button btnOk;

    private ArrayList<String> mFilePaths;
    private List<Fragment> fragments;
    private int mapType;

    private ProgressDialog progressDialog;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (4002 == msg.what) {
                showProgressDialog((Integer) msg.obj);
                if ((Integer) msg.obj == 100) {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.activity_parma_import;
    }

    @Override
    protected void initData() {
        viewPager = findViewById(R.id.viewPager);
        tab = findViewById(R.id.tab);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.btn_ok);
        mFilePaths = getIntent().getStringArrayListExtra("mFilePaths");
        mapType = getIntent().getIntExtra("mapType", 0);
        fragments = new ArrayList<>();
        if (mFilePaths != null && mFilePaths.size() > 0) {
            for (String filePath : mFilePaths) {
                fragments.add(ParamImportFragment.getInstance(filePath, mapType));
            }
            MyPageAdapter adapter = new MyPageAdapter(fragments, getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            tab.setupWithViewPager(viewPager);
            viewPager.setOffscreenPageLimit(mFilePaths.size());
        }
        initListener();
    }

    private void showProgressDialog(int progress) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("提示");
            progressDialog.setMessage("提示");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        progressDialog.setProgress(progress);
    }

    private void initListener() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0);
                finish();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<BaseStation> baseList = new ArrayList<>();
                for (int i = 0; i < fragments.size(); i++) {
                    ParamImportFragment fragment = (ParamImportFragment) fragments.get(i);
                    baseList.addAll(fragment.getBaseStations());
                }
                if (baseList.size() == 0) {
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();
            }
        });

    }

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }


    class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(List<Fragment> fragments, FragmentManager manager) {
            super(manager);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = mFilePaths.get(position).substring(mFilePaths.get(position).lastIndexOf("/") + 1);
            return title;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
        }
    }

    @Override
    protected void onDestroy() {
        if (mFilePaths != null) {
            mFilePaths.clear();
            mFilePaths = null;
        }
        if (fragments != null) {
            fragments.clear();
            fragments = null;
        }
        handler.removeCallbacksAndMessages(null);
        handler = null;
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        super.onDestroy();
    }
}
