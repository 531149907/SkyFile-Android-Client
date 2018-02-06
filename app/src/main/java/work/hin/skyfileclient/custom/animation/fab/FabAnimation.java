package work.hin.skyfileclient.custom.animation.fab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.animation.DecelerateInterpolator;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhouzhixuan on 2018/2/6.
 */

public class FabAnimation {
    private List<FloatingActionButton> fabList;
    private List<CardView> labels;
    private int totalTime = 250;
    private AnimatorSet animatorSet = new AnimatorSet();
    private Collection<Animator> objectAnimators = new LinkedList<>();

    public FabAnimation(List<FloatingActionButton> fabList, List<CardView> labels, int totalTime) {
        this.fabList = fabList;
        this.totalTime = totalTime;
        this.labels = labels;
    }

    public FabAnimation(List<FloatingActionButton> fabList, List<CardView> labels) {
        this.fabList = fabList;
        this.labels = labels;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void play() {
        for (int i = 0; i < fabList.size(); i++) {
            Animator oa_alpha = ObjectAnimator.ofFloat(fabList.get(i), "alpha", 0, 1);
            Animator oa_scale_x = ObjectAnimator.ofFloat(fabList.get(i), "scaleX", 0.4f, 1);
            Animator oa_scale_y = ObjectAnimator.ofFloat(fabList.get(i), "scaleY", 0.4f, 1);
            Animator oa_translation = ObjectAnimator.ofFloat(fabList.get(i), "translationY", 30, 0);

            long startDelay = i * 30;

            oa_alpha.setStartDelay(startDelay);
            oa_scale_x.setStartDelay(startDelay);
            oa_scale_y.setStartDelay(startDelay);
            oa_translation.setStartDelay(startDelay);

            long duration = totalTime - startDelay;

            oa_alpha.setDuration(duration);
            oa_scale_x.setDuration(duration);
            oa_scale_y.setDuration(duration);
            oa_translation.setDuration(duration);

            objectAnimators.add(oa_alpha);
            objectAnimators.add(oa_scale_x);
            objectAnimators.add(oa_scale_y);
            objectAnimators.add(oa_translation);
        }

        for (int i = 0; i < fabList.size(); i++) {
            Animator oa_alpha = ObjectAnimator.ofFloat(labels.get(i), "alpha", 0, 1);
            Animator oa_translation = ObjectAnimator.ofFloat(labels.get(i), "translationY", 30, 0);

            long startDelay = i * 30;

            oa_alpha.setStartDelay(startDelay);
            oa_translation.setStartDelay(startDelay);

            long duration = totalTime - startDelay;

            oa_alpha.setDuration(duration);
            oa_translation.setDuration(duration);

            objectAnimators.add(oa_alpha);
            objectAnimators.add(oa_translation);
        }

        animatorSet.playTogether(objectAnimators);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.3f));
        animatorSet.start();
    }

}
