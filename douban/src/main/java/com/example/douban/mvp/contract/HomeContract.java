package com.example.douban.mvp.contract;

import com.example.douban.app.data.entity.Banner;
import com.example.douban.app.data.entity.MoviesList;
import com.example.douban.app.data.entity.home.SectionMultipleItem;
import com.example.douban.mvp.ui.adapter.MovieItemAdapter;
import com.example.douban.mvp.ui.adapter.MoviesListAdapter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/02/2019 13:29
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public interface HomeContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void setBanner(List<Banner> banners);

        void addHeadView(MovieItemAdapter movieItemAdapter);

        void setMovieItem(MovieItemAdapter movieItemAdapter);

        void setMoviesListItem(MoviesListAdapter moviesListItem);

        void addFootView(MovieItemAdapter movieItemAdapter);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        // 获取头部Banners
        Observable<List<Banner>> getBanners();
        // 获取所有数据
        Observable<List<SectionMultipleItem>> getAllData();
        // 获取电影榜单数据
        Observable<MoviesList> getFootData();
    }
}
