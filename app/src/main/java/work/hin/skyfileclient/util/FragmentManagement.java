package work.hin.skyfileclient.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work.hin.skyfileclient.core.base.BaseFragment;

public class FragmentManagement {

    private static FragmentTransaction transaction;
    private static List<BaseFragment> fragmentCollection = new ArrayList<>();

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void replaceFragmentWithSharedElement(FragmentManager fragmentManager, Fragment fragment, int frameId, HashMap<View, String> sharedElements) {
        transaction = fragmentManager.beginTransaction();

        if (sharedElements != null) {
            for (View sharedView : sharedElements.keySet()) {
                transaction.addSharedElement(sharedView, sharedElements.get(sharedView));
            }
        }

        transaction.addToBackStack(null);
        transaction.replace(frameId, fragment);
        transaction.commitAllowingStateLoss();
    }

    public static void switchFragment(FragmentManager fragmentManager, Fragment fragment, int frameId, boolean isAddToBackStack) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        if (isAddToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void switchFragment(FragmentManager fragmentManager, Fragment fragment, int frameId, boolean isAddToBackStack, boolean isAnimated) {
        transaction = fragmentManager.beginTransaction();
        if (isAnimated) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        }
        transaction.replace(frameId, fragment);
        if (isAddToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}