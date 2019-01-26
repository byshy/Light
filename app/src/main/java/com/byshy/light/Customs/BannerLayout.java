package com.byshy.light.Customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.byshy.light.R;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

public class BannerLayout extends FrameLayout {

    private int autoPlayDuration;

    private RecyclerView mRecyclerView;
    private BannerLayoutManager mLayoutManager;

    private int WHAT_AUTO_PLAY = 1000;

    private boolean hasInit;
    private int bannerSize = 1;
    private int currentIndex;
    private boolean isPlaying = false;

    private boolean isAutoPlaying = true;
    int itemSpace;
    float centerScale;
    float moveSpeed;

    protected Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT_AUTO_PLAY) {
                if (currentIndex == mLayoutManager.getCurrentPosition()) {
                    ++currentIndex;
                    mRecyclerView.smoothScrollToPosition(currentIndex);
                    mHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
                }
            }
            return false;
        }
    });

    public BannerLayout(Context context) {
        this(context, null);
    }

    public BannerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    protected void initView(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BannerLayout);
        autoPlayDuration = a.getInt(R.styleable.BannerLayout_interval, 4000);
        isAutoPlaying = a.getBoolean(R.styleable.BannerLayout_autoPlaying, true);
        itemSpace = a.getInt(R.styleable.BannerLayout_itemSpace, 40);
        centerScale = a.getFloat(R.styleable.BannerLayout_centerScale, 1.4f);
        moveSpeed = a.getFloat(R.styleable.BannerLayout_moveSpeed, 1.0f);

        a.recycle();

        mRecyclerView = new RecyclerView(context);
        LayoutParams vpLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mRecyclerView, vpLayoutParams);
        mLayoutManager = new BannerLayoutManager();
        mLayoutManager.setItemSpace(itemSpace);
        mLayoutManager.setCenterScale(centerScale);
        mLayoutManager.setMoveSpeed(moveSpeed);
        mRecyclerView.setLayoutManager(mLayoutManager);
        new CenterSnapHelper().attachToRecyclerView(mRecyclerView);

    }

    public boolean isPlaying() {
        return isPlaying;
    }

    protected synchronized void setPlaying(boolean playing) {
        if (isAutoPlaying && hasInit) {
            if (!isPlaying && playing) {
                mHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
                isPlaying = true;
            } else if (isPlaying && !playing) {
                mHandler.removeMessages(WHAT_AUTO_PLAY);
                isPlaying = false;
            }
        }
    }

    public void setAdapter(final RecyclerView.Adapter adapter) {
        hasInit = false;
        mRecyclerView.setAdapter(adapter);
        bannerSize = adapter.getItemCount();
        mLayoutManager.setInfinite(bannerSize >= 3);
        setPlaying(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx != 0) {
                    setPlaying(false);
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int first = mLayoutManager.getCurrentPosition();
                if (currentIndex != first) {
                    currentIndex = first;
                }
                if (newState == SCROLL_STATE_IDLE) {
                    setPlaying(true);
                }
            }

        });
        hasInit = true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setPlaying(false);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setPlaying(true);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setPlaying(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setPlaying(false);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            setPlaying(true);
        } else {
            setPlaying(false);
        }
    }

    public interface OnBannerItemClickListener {
        void onItemClick(int position);
    }

}
