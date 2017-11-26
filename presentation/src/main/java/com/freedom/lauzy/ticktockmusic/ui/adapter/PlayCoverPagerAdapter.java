package com.freedom.lauzy.ticktockmusic.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.freedom.lauzy.ticktockmusic.model.SongEntity;
import com.freedom.lauzy.ticktockmusic.service.MusicManager;
import com.freedom.lauzy.ticktockmusic.ui.fragment.PlayCoverFragment;

import java.util.List;

/**
 * Desc : 歌词、专辑图片 PagerAdapter
 * Author : Lauzy
 * Date : 2017/10/17
 * Blog : http://www.jianshu.com/u/e76853f863a9
 * Email : freedompaladin@gmail.com
 */
public class PlayCoverPagerAdapter extends FragmentStatePagerAdapter {

    private int mPosition = -1;

    public PlayCoverPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            List<SongEntity> songData = MusicManager.getInstance().getMusicService().getSongData();
            return PlayCoverFragment.newInstance(songData.get(songData.size() - 1));
        }
        if (position == MusicManager.getInstance().getMusicService().getSongData().size() + 1) {
            List<SongEntity> songData = MusicManager.getInstance().getMusicService().getSongData();
            return PlayCoverFragment.newInstance(songData.get(0));
        }
        return PlayCoverFragment.newInstance(MusicManager.getInstance().getMusicService()
                .getSongData().get(position - 1));
    }

    @Override
    public int getCount() {
        return MusicManager.getInstance().getMusicService().getSongData().size() + 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    public void setNotifyPosition(int position) {
        mPosition = position;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mPosition == -1) {
            super.destroyItem(container, position, object);
            return;
        }
        if (mPosition == MusicManager.getInstance().getCurPosition()) {
            super.destroyItem(container, position, object);
        } else if (mPosition > MusicManager.getInstance().getCurPosition()) {
            if (position != MusicManager.getInstance().getCurPosition() + 1) {
                super.destroyItem(container, position, object);
            }
        } else {
//            int curPosition = MusicManager.getInstance().getCurPosition();
            /*if (position != curPosition + 1) {
                super.destroyItem(container, position, object);
            }*/
            //此处未解决，网易云音乐采用自定义的 ViewFlipper，有兴趣可以反编译研究下
            super.destroyItem(container, position, object);
        }
    }
}