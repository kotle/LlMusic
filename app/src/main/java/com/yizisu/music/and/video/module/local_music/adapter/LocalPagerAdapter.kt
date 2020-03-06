package com.yizisu.music.and.video.module.local_music.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.yizisu.basemvvm.utils.getResString
import com.yizisu.music.and.video.R
import com.yizisu.music.and.video.module.fragment.test.LocalMusicFragment
import com.yizisu.music.and.video.module.fragment.test.LocalRecentFragment
import com.yizisu.music.and.video.module.fragment.test.LocalVideoFragment

class LocalPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private data class PagerData(
        val fg: Fragment,
        val title: CharSequence
    )

    private val pagers = mutableListOf<PagerData>(
        PagerData(LocalMusicFragment.create(), getResString(R.string.local_music)),
        PagerData(LocalVideoFragment.create(), getResString(R.string.local_video)),
        PagerData(LocalRecentFragment.create(), getResString(R.string.local_recent))
    )

    override fun getItem(position: Int): Fragment {
        return pagers[position].fg
    }

    override fun getCount(): Int = pagers.count()

    override fun getPageTitle(position: Int): CharSequence? {
        return pagers[position].title
    }
}