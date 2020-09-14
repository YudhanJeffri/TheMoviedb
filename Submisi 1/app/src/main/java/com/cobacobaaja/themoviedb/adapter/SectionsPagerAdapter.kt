package com.cobacobaaja.themoviedb.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.cobacobaaja.themoviedb.R
import com.cobacobaaja.themoviedb.ui.fragment.MovieFragment
import com.cobacobaaja.themoviedb.ui.fragment.NowShowingFragment
import com.cobacobaaja.themoviedb.ui.fragment.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3)
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NowShowingFragment()
            }
            1 -> MovieFragment()
            else -> return TvShowFragment()
        }
    }
    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return 3
    }
}