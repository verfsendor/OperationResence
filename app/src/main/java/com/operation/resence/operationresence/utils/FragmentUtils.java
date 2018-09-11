package com.operation.resence.operationresence.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class FragmentUtils {
    public static void replaceFragmentWithAnim(@NonNull FragmentManager fragmentManager,
                                               @NonNull Class<? extends Fragment> fragmentClazz, int frameId,
                                               Bundle bundle) {
        if (null == fragmentManager || null == fragmentClazz) {
            return;
        }
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentClazz.getSimpleName());
        if (null == fragment) {
            try {
                fragment = fragmentClazz.newInstance();
                fragment.setArguments(bundle);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
//        setUserInvisible(fragmentManager);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.setCustomAnimations(
//                R.anim.slide_in_from_right,
//                R.anim.slide_out_to_left,
//                R.anim.slide_in_from_left,
//                R.anim.slide_out_to_right);
        transaction.replace(frameId, fragment, fragmentClazz.getSimpleName());
        transaction.addToBackStack(fragmentClazz.getSimpleName());
        transaction.commit();
        fragment.setUserVisibleHint(true);
    }
}
