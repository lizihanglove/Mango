/*
 * Copyright (c) 2017 Lizhaotailang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.tonnyl.mango.ui.shot.likes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.tonnyl.mango.R
import kotlinx.android.synthetic.main.activity_common.*

/**
 * Created by lizhaotailang on 2017/7/3.
 *
 * Show [io.github.tonnyl.mango.data.Like]s of a shot.
 */
class LikesActivity : AppCompatActivity() {

    private var mLikesFragment: LikesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        savedInstanceState?.let {
            mLikesFragment = supportFragmentManager.getFragment(it, LikesFragment::class.java.simpleName) as? LikesFragment
        } ?: run {
            mLikesFragment = LikesFragment.newInstance()
        }

        mLikesFragment?.let {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, it, LikesFragment::class.java.simpleName)
                    .commit()
            LikesPresenter(it, intent.getParcelableExtra(LikesPresenter.EXTRA_SHOT))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mLikesFragment?.let {
            if (it.isAdded) {
                supportFragmentManager.putFragment(outState, LikesFragment::class.java.simpleName, mLikesFragment)
            }
        }
    }

}