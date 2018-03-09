package pl.michalz.hideonscrollexample.activity.parttwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.lcodecore.tkrefreshlayout.PullListener;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import pl.michalz.hideonscrollexample.R;
import pl.michalz.hideonscrollexample.Utils;
import pl.michalz.hideonscrollexample.adapter.parttwo.RecyclerAdapter;
import pl.michalz.hideonscrollexample.listener.parttwo.HidingScrollListener;

public class PartTwoActivity extends AppCompatActivity {

    private LinearLayout mToolbarContainer;
    private int mToolbarHeight;
    private TwinklingRefreshLayout twinklingRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeGreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_two);

        mToolbarContainer = (LinearLayout) findViewById(R.id.toolbarContainer);
        twinklingRefreshLayout = (TwinklingRefreshLayout) findViewById(R.id.twinklayout);
        twinklingRefreshLayout.setEnableRefresh(false);
        twinklingRefreshLayout.setEnableLoadmore(false);
        twinklingRefreshLayout.setPullListener(new PullListener() {
            @Override
            public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {

            }

            @Override
            public void onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) {

            }

            @Override
            public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {

            }

            @Override
            public void onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {

            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {

            }

            @Override
            public void onFinishRefresh() {

            }

            @Override
            public void onFinishLoadMore() {

            }

            @Override
            public void onRefreshCanceled() {

            }

            @Override
            public void onLoadmoreCanceled() {

            }
        });
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbarHeight = Utils.getToolbarHeight(this);
    }

    private void initRecyclerView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        int paddingTop = Utils.getToolbarHeight(PartTwoActivity.this) + Utils.getTabsHeight(PartTwoActivity.this);
        recyclerView.setPadding(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addOnScrollListener(new HidingScrollListener(this) {

            @Override
            public void onMoved(int distance) {
                Log.e("hecp", "distance=" + distance + "，Container Height=" + mToolbarContainer.getHeight());
                mToolbarContainer.setTranslationY(-distance);
            }

            @Override
            public void onShow() {
                mToolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void onHide() {
                mToolbarContainer.animate().translationY(-mToolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
            }

        });
    }

    private List<String> createItemList() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemList.add("Item " + i);
        }
        return itemList;
    }
}
