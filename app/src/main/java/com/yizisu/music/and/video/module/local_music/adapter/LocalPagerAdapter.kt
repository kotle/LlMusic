package com.yizisu.music.and.video.module.local_music.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.yizisu.music.and.video.module.fragment.LocalMusicFragment
import com.yizisu.music.and.video.module.fragment.LocalRecentFragment

class LocalPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private data class PagerData(
        val fg: Fragment,
        val title: CharSequence
    )

    private val pagers = mutableListOf<PagerData>(
        PagerData(LocalMusicFragment.create(), "本地音乐"),
        PagerData(LocalRecentFragment.create(), "最近播放")
    )

    override fun getItem(position: Int): Fragment {
        return pagers[position].fg
    }

    override fun getCount(): Int = pagers.count()

    override fun getPageTitle(position: Int): CharSequence? {
        return pagers[position].title
    }
}