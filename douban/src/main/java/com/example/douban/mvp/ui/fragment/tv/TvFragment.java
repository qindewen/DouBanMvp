package com.example.douban.mvp.ui.fragment.tv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.douban.app.base.MySupportFragment;
import com.example.douban.app.eventbus.Sort;
import com.example.douban.mvp.contract.TvContract;
import com.example.douban.mvp.ui.adapter.tv.TabAdapter;
import com.example.douban.mvp.ui.view.NoAnimationViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.example.douban.di.component.DaggerTvComponent;
import com.example.douban.mvp.presenter.TvPresenter;

import com.example.douban.R;

import org.simple.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class TvFragment extends MySupportFragment<TvPresenter> implements TvContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_content)
    NoAnimationViewPager vp_content;
    @BindView(R.id.tab)
    SlidingTabLayout st_tab;
    View view;

    public static TvFragment newInstance() {
        TvFragment fragment = new TvFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerTvComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tv, container, false);
        }
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initToolBar();
        mPresenter.initTab();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    private void initToolBar() {
        toolbar.setTitle("电视");
        toolbar.inflateMenu(R.menu.main_book_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.book_filter:
                    case R.id.main_note:
                        showMessage("暂未开发...");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void setTabTitle(List<String> title) {
        TabAdapter adapter = new TabAdapter(getChildFragmentManager());
        for (int i = 0; i < title.size(); i++) {
            Timber.d(title.get(i));
            adapter.addFragment(TvChildFragment.newInstance(title.get(i)), title.get(i));
        }
        vp_content.setAdapter(adapter);
        st_tab.setViewPager(vp_content);
        st_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp_content.setCurrentItem(st_tab.getCurrentTab());
            }
        });
        // 设置tab选项卡的默认选项
        st_tab.setCurrentTab(0);
    }
}
