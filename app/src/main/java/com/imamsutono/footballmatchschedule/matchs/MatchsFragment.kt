package com.imamsutono.footballmatchschedule.matchs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.matchs.search.SearchMatchActivity
import kotlinx.android.synthetic.main.fragment_matchs.*
import org.jetbrains.anko.startActivity

class MatchsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_matchs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentAdapter = MatchsPagerAdapter(childFragmentManager)
        viewpager_home.adapter = fragmentAdapter

        tabs_home.setupWithViewPager(viewpager_home)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                true
            }
            R.id.search -> {
                context?.startActivity<SearchMatchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun newInstance(): MatchsFragment = MatchsFragment()
    }
}
