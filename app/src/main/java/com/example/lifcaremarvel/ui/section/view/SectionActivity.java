package com.example.lifcaremarvel.ui.section.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lifcaremarvel.R;
import com.example.lifcaremarvel.api.data.SectionPOJO;
import com.example.lifcaremarvel.databinding.ActivitySectionBinding;
import com.example.lifcaremarvel.ui.section.SectionContract;
import com.example.lifcaremarvel.ui.section.SectionPresenter;
import com.example.lifcaremarvel.ui.util.PagerSharedElementCallback;

import java.io.Serializable;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

/**
 * This class <i>SectionActivity</i> is activity class for different sections of app. It uses pager adapter to show items
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class SectionActivity extends AppCompatActivity implements SectionContract.View {

    private static final String EXTRA_ATTRIBUTION = SectionActivity.class.getPackage().getName() + ".extra.ATTRIBUTION";
    private static final String EXTRA_ENTRIES = SectionActivity.class.getPackage().getName() + ".extra.ENTRIES";
    private static final String EXTRA_POSITION = SectionActivity.class.getPackage().getName() + ".extra.POSITION";
    private static final String EXTRA_TYPE = SectionActivity.class.getPackage().getName() + ".extra.TYPE";
    public static final int EXTRA_NOT_FOUND = -1;

    public static void start(Activity activity, @SectionPOJO.Type int type, View heroView, String attribution, List<SectionPOJO> entries, int position) {

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity,
                        heroView, ViewCompat.getTransitionName(heroView));
        Intent intent = new Intent(activity, SectionActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        intent.putExtra(EXTRA_ATTRIBUTION, attribution);
        intent.putExtra(EXTRA_ENTRIES, (Serializable) entries);
        intent.putExtra(EXTRA_POSITION, position);

        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public static int getType(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null && data.hasExtra(EXTRA_TYPE)) {
            return data.getIntExtra(EXTRA_TYPE, EXTRA_NOT_FOUND);
        }
        return EXTRA_NOT_FOUND;
    }

    public static int getPosition(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null && data.hasExtra(EXTRA_POSITION)) {
            return data.getIntExtra(EXTRA_POSITION, EXTRA_NOT_FOUND);
        }
        return EXTRA_NOT_FOUND;
    }

    private ViewPager mViewPager;
    private SectionPresenter mPresenter;
    private int mType;
    private PagerSharedElementCallback mSharedElementCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySectionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_section);

        // Postpone transition until the image of ViewPager's initial item is loaded
        supportPostponeEnterTransition();
        setEnterSharedElementCallback(mSharedElementCallback = new PagerSharedElementCallback());

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mType = getIntent().getExtras().getInt(EXTRA_TYPE);
        String attribution = getIntent().getStringExtra(EXTRA_ATTRIBUTION);
        //noinspection unchecked
        List<SectionPOJO> entries = (List<SectionPOJO>) getIntent().getExtras().get(EXTRA_ENTRIES);
        assert entries != null;
        int position = getIntent().getExtras().getInt(EXTRA_POSITION);

        if (savedInstanceState == null) {
            mPresenter = new SectionPresenter(entries, position);
        } else {
            mPresenter = (SectionPresenter) getLastCustomNonConfigurationInstance();
        }
        mPresenter.attachView(this);

        binding.setAttribution(attribution);
        binding.setPresenter(mPresenter);
    }

    @Override
    public void finish() {
        setResult();
        super.finish();
    }

    @Override
    public void finishAfterTransition() {
        setResult();
        super.finishAfterTransition();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showItems(List<SectionPOJO> entries, int position) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(this, mType, entries, mSharedElementCallback);
        adapter.setInitialPosition(position);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(position, false);
    }

    @Override
    public void close() {
        super.onBackPressed();
    }

    private void setResult() {
        int position = mViewPager.getCurrentItem();
        Intent data = new Intent();
        data.putExtra(EXTRA_TYPE, mType);
        data.putExtra(EXTRA_POSITION, position);
        setResult(RESULT_OK, data);
    }

}
