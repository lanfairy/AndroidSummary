package com.lanfairy.elly.androidsummary.ChooseAnimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.ChooseAnimation.dummy.DummyContent;


public class ChooseAnimationAty extends AppCompatActivity implements ChooseAnimationFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_animation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.chooseAnimationAty,ChooseAnimationFragment.newInstance(2))
                    .commitNow();
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
