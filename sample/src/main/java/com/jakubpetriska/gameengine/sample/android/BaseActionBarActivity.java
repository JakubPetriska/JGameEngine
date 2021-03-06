package com.jakubpetriska.gameengine.sample.android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Basic Activity class containing action bar.
 */
public abstract class BaseActionBarActivity extends ActionBarActivity {

    @InjectView(com.jakubpetriska.gameengine.sample.R.id.toolbar)
    Toolbar mToolbar;

    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
    }
}
