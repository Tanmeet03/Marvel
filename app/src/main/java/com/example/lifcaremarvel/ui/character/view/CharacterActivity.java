package com.example.lifcaremarvel.ui.character.view;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.lifcaremarvel.R;
import com.example.lifcaremarvel.api.data.CharacterPOJO;
import com.example.lifcaremarvel.api.data.SectionPOJO;
import com.example.lifcaremarvel.databinding.ActivityCharacterBinding;
import com.example.lifcaremarvel.databinding.ItemListSectionBinding;
import com.example.lifcaremarvel.ui.adapter.MarvelArrayAdapter;
import com.example.lifcaremarvel.ui.character.CharacterContract;
import com.example.lifcaremarvel.ui.character.CharacterPresenter;
import com.example.lifcaremarvel.ui.section.view.SectionActivity;
import com.example.lifcaremarvel.ui.util.PagerSharedElementCallback;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/**
 * This class <i>CharacterActivity</i> is marvel character details activity
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class CharacterActivity extends AppCompatActivity implements CharacterContract.View,
        MarvelArrayAdapter.OnItemClickListener<SectionPOJO, SectionAdapterMarvel.ViewHolder, ItemListSectionBinding> {

    public static final String EXTRA_CHARACTER = CharacterActivity.class.getPackage().getName() + ".extra.CHARACTER";

    public static void start(@NonNull Activity activity, @NonNull View characterView, @NonNull CharacterPOJO character) {

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity,
                        characterView, ViewCompat.getTransitionName(characterView));
        Intent intent = new Intent(activity, CharacterActivity.class);
        intent.putExtra(EXTRA_CHARACTER, character);

        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public static PendingIntent getPendingIntent(@NonNull Context context, @NonNull CharacterPOJO character, int id) {

        Intent intent = new Intent(context, CharacterActivity.class);
        intent.setAction(Integer.toString(id)); // Used to update all PendingIntent extras data for each widget
        intent.putExtra(EXTRA_CHARACTER, character);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(intent); // Return to MainActivity

        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private ActivityCharacterBinding mBinding;
    private CharacterPresenter mPresenter;
    private PagerSharedElementCallback mSharedElementCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_character);
        setSupportActionBar(mBinding.toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupSectionView(mBinding.recyclerComics, SectionPOJO.TYPE_COMIC);
        setupSectionView(mBinding.recyclerSeries, SectionPOJO.TYPE_SERIES);
        setupSectionView(mBinding.recyclerStories, SectionPOJO.TYPE_STORY);
        setupSectionView(mBinding.recyclerEvents, SectionPOJO.TYPE_EVENT);

        CharacterPOJO character = (CharacterPOJO) getIntent().getExtras().get(EXTRA_CHARACTER);
        assert character != null;

        if (savedInstanceState == null) {
            mPresenter = new CharacterPresenter(character);
        } else {
            mPresenter = (CharacterPresenter) getLastCustomNonConfigurationInstance();
        }
        mPresenter.attachView(this);

        mBinding.setPresenter(mPresenter);
    }

    private void setupSectionView(RecyclerView recyclerView, @SectionPOJO.Type int type) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        SectionAdapterMarvel adapter = new SectionAdapterMarvel(this, type, this);
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        //noinspection ConstantConditions
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
    public void onActivityReenter(int resultCode, Intent data) {

        int type = SectionActivity.getType(resultCode, data);
        final int position = SectionActivity.getPosition(resultCode, data);

        final RecyclerView recyclerView;
        switch (type) {
            case SectionPOJO.TYPE_COMIC:
                recyclerView = mBinding.recyclerComics;
                break;
            case SectionPOJO.TYPE_SERIES:
                recyclerView = mBinding.recyclerSeries;
                break;
            case SectionPOJO.TYPE_STORY:
                recyclerView = mBinding.recyclerStories;
                break;
            case SectionPOJO.TYPE_EVENT:
                recyclerView = mBinding.recyclerEvents;
                break;
            default:
                recyclerView = null;
        }

        if (recyclerView == null) {
            return;
        }

        if (position != SectionActivity.EXTRA_NOT_FOUND) {
            recyclerView.scrollToPosition(position);
        }

        mSharedElementCallback = new PagerSharedElementCallback();
        setExitSharedElementCallback(mSharedElementCallback);

        //noinspection ConstantConditions
        supportPostponeEnterTransition();
        recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);

                RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(position);
                if (holder instanceof SectionAdapterMarvel.ViewHolder) {
                    SectionAdapterMarvel.ViewHolder mediaViewHolder = (SectionAdapterMarvel.ViewHolder) holder;
                    // TODO: 01/02/2017 Change findViewById(R.id.image) for a view reference
                    mSharedElementCallback.setSharedElementViews(mediaViewHolder.itemView.findViewById(R.id.image));
                }

                supportStartPostponedEnterTransition();

                return true;
            }
        });

    }

    @Override
    public void onItemClick(MarvelArrayAdapter<SectionPOJO, SectionAdapterMarvel.ViewHolder> adapter, ItemListSectionBinding binding, int position) {
        SectionAdapterMarvel sectionAdapter = (SectionAdapterMarvel) adapter;
        mPresenter.sectionClick(sectionAdapter.getType(), binding.image, adapter.getItems(), position);
    }

    @Override
    public void showAttribution(String attribution) {
        mBinding.setAttribution(attribution);
    }

    @Override
    public void showCharacter(@NonNull CharacterPOJO character) {
        mBinding.setCharacter(character);
    }

    @Override
    public void showComics(@NonNull List<SectionPOJO> entries) {
        SectionAdapterMarvel adapter = (SectionAdapterMarvel) mBinding.recyclerComics.getAdapter();
        adapter.setItems(entries);
    }

    @Override
    public void showSeries(@NonNull List<SectionPOJO> entries) {
        SectionAdapterMarvel adapter = (SectionAdapterMarvel) mBinding.recyclerSeries.getAdapter();
        adapter.setItems(entries);
    }

    @Override
    public void showStories(@NonNull List<SectionPOJO> entries) {
        SectionAdapterMarvel adapter = (SectionAdapterMarvel) mBinding.recyclerStories.getAdapter();
        adapter.setItems(entries);
    }

    @Override
    public void showEvents(@NonNull List<SectionPOJO> entries) {
        SectionAdapterMarvel adapter = (SectionAdapterMarvel) mBinding.recyclerEvents.getAdapter();
        adapter.setItems(entries);
    }

    @Override
    public void showError(@NonNull Throwable e) {
        Snackbar.make(mBinding.toolbarLayout, e.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void openSection(int type, @NonNull View heroView, String attribution, @NonNull List<SectionPOJO> entries, int position) {
        SectionActivity.start(this, type, heroView, attribution, entries, position);
    }

    @Override
    public void openLink(@NonNull String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
