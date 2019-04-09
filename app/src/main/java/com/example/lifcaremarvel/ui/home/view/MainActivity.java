package com.example.lifcaremarvel.ui.home.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.lifcaremarvel.R;
import com.example.lifcaremarvel.api.MarvelApi;
import com.example.lifcaremarvel.api.data.CharacterPOJO;
import com.example.lifcaremarvel.databinding.ActivityMainBinding;
import com.example.lifcaremarvel.databinding.ItemListCharacterBinding;
import com.example.lifcaremarvel.ui.adapter.MarvelArrayAdapter;
import com.example.lifcaremarvel.ui.character.view.CharacterActivity;
import com.example.lifcaremarvel.ui.home.MainContract;
import com.example.lifcaremarvel.ui.home.MainPresenter;
import com.example.lifcaremarvel.ui.util.StringUtils;
import com.example.lifcaremarvel.ui.util.ViewUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * This class <i>MainActivity</i> is root activity
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity
        implements MainContract.View,
        CharacterAdapterMarvel.OnItemClickListener<CharacterPOJO, CharacterAdapterMarvel.ViewHolder, ItemListCharacterBinding>,
        CharacterAdapterMarvel.OnLoadListener {

    private ActivityMainBinding mBinding;
    private CharacterAdapterMarvel mCharacterAdapter;
    private MainPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);

        mBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recycler.setHasFixedSize(true);
        mBinding.recycler.setAdapter(mCharacterAdapter = new CharacterAdapterMarvel(R.layout.item_list_character, this, this));

        if (savedInstanceState == null) {
            mPresenter = new MainPresenter(MarvelApi.getInstance());
        } else {
            mPresenter = (MainPresenter) getLastCustomNonConfigurationInstance();
        }
        mPresenter.attachView(this);
        mPresenter.initScreen();

        mBinding.setPresenter(mPresenter);
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
    public void onItemClick(MarvelArrayAdapter<CharacterPOJO, CharacterAdapterMarvel.ViewHolder> adapter, ItemListCharacterBinding binding, int position) {
        mPresenter.characterClick(binding.image, adapter.getItem(position));
    }

    @Override
    public void onLoadMore(int offset) {
        mPresenter.loadCharacters(offset);
    }

    @Override
    public void showProgress() {
        mCharacterAdapter.setLoading(true);
        mCharacterAdapter.setHasMore(true);
    }

    @Override
    public void stopProgress(boolean hasMore) {
        mCharacterAdapter.setLoading(false);
        mCharacterAdapter.setHasMore(hasMore);
        mBinding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showAttribution(String attribution) {
        //noinspection ConstantConditions
        getSupportActionBar().setSubtitle(attribution);
    }

    @Override
    public void showResult(@NonNull List<CharacterPOJO> entries) {
        mCharacterAdapter.setItems(entries);

        if (mBinding.error.isShown()) {
            mBinding.error.setText(null);
            ViewUtils.fadeView(mBinding.recycler, true, true);
            ViewUtils.fadeView(mBinding.error, false, true);
        }
    }

    @Override
    public void showError(@NonNull Throwable e) {
        String msg = StringUtils.getApiErrorMessage(this, e);
        if (mCharacterAdapter.getItemCount() > 1) { // If user already has items shown
            Snackbar.make(mBinding.recycler, msg, Snackbar.LENGTH_LONG).show();
        } else {
            boolean animate = !TextUtils.equals(mBinding.error.getText(), msg);
            boolean showError = !TextUtils.isEmpty(msg);
            mBinding.error.setText(msg);
            ViewUtils.fadeView(mBinding.recycler, !showError, animate);
            ViewUtils.fadeView(mBinding.error, showError, animate);
        }
    }

    @Override
    public void openCharacter(@NonNull View heroView, @NonNull CharacterPOJO character) {
        CharacterActivity.start(this, heroView, character);
    }
}