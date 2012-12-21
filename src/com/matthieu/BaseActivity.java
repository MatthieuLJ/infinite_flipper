package com.matthieu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class BaseActivity extends Activity {
    /**
     * Called when the activity is first created.
     */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Animation animFlipInNext,animFlipOutNext, animFlipInPrevious, animFlipOutPrevious;

        animFlipInNext = AnimationUtils.loadAnimation(this, R.anim.flipinnext);
        animFlipOutNext = AnimationUtils.loadAnimation(this, R.anim.flipoutnext);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final view_generator generator = new view_generator();

        Button flip_button = (Button) findViewById(R.id.flip_button);
        flip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
                View new_view = generator.get_new_view();
                flipper.setInAnimation(animFlipInNext);
                flipper.setOutAnimation(animFlipOutNext);

                if (flipper.getChildCount() == 0) {
                    flipper.addView(new_view);
                } else if (flipper.getChildCount() == 1) {
                    flipper.addView(new_view);
                    flipper.showNext();
                    //flipper.removeViewAt(0);
                } else {
                    //flipper.removeViewAt(1);
                    flipper.addView(new_view);
                    flipper.showNext();
                    //flipper.removeViewAt(0);
                }
            }
        });
    }

    private class view_generator {
        private int count = 0;
        public View get_new_view() {
            View res = View.inflate(BaseActivity.this, R.layout.flip_card, null);
            TextView text = (TextView) res.findViewById(R.id.card_text);
            text.setText(Integer.toString(count));
            count++;
            return res;
        }
    }
}
