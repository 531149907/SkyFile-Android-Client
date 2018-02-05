package work.hin.skyfileclient.core.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class BasePresenter<T extends BaseView> {
    /**
     * Null Mvp View InvocationHandler
     */
    private static final InvocationHandler NULL_VIEW = new MvpViewInvocationHandler();
    /**
     * application context
     */
    private static Context sAppContext;
    /**
     * context weak reference
     */
    private WeakReference<Context> mContextRef;
    /**
     * mvp view weak reference
     */
    private WeakReference<T> mViewRef;
    /**
     * mvp view class
     */
    private Class mMvpViewClass = null;
    /**
     * Mvp View created by dynamic Proxy
     */
    private T mNullViewProxy;

    /**
     * init application context with reflection.
     */
    static {
        try {
            // 先通过 ActivityThread 来获取 Application Context
            Application application = (Application) Class.forName("android.app.ActivityThread").getMethod
                    ("currentApplication").invoke(null, (Object[]) null);
            if (application != null) {
                sAppContext = application;
            }
            if (sAppContext == null) {
                // 第二种方式初始化
                application = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication")
                        .invoke(null, (Object[]) null);
                if (application != null) {
                    sAppContext = application;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public BasePresenter() {
    }


    public BasePresenter(Context context, T view) {
        attach(context, view);
    }

    /**
     * attach context & mvp view
     *
     * @param context
     * @param view
     */
    protected void attach(Context context, T view) {
        mContextRef = new WeakReference<>(context);
        mViewRef = new WeakReference<>(view);
        if (sAppContext == null && context != null) {
            sAppContext = context.getApplicationContext();
        }
    }


    /**
     * release resource
     */
    protected void detach() {
        if (mContextRef != null) {
            mContextRef.clear();
        }
        mContextRef = null;
        if (mViewRef != null) {
            mViewRef.clear();
        }
        mViewRef = null;
    }

    /**
     * UI展示相关的操作需要判断一下 Activity 是否已经 finish.
     * <p>
     * todo : 只有当 isActivityAlive 返回true时才可以执行与Activity相关的操作,
     * 比如 弹出Dialog、Window、跳转Activity等操作.
     *
     * @return
     */
    protected boolean isActivityAlive() {
        return !isActivityFinishing() && mViewRef.get() != null;
    }


    /**
     * 返回 Context. 如果 Activity被销毁, 那么返回应用的Context.
     * <p>
     * 注意:
     * 通过过Context进行UI方面的操作时应该调用 {@link #isActivityAlive()}
     * 判断Activity是否还已经被销毁, 在Activity未销毁的状态下才能操作. 否则会引发crash.
     * 而获取资源等操作则可以使用应用的Context.
     *
     * @return
     */
    protected Context getContext() {
        Context context = mContextRef != null ? mContextRef.get() : null;
        if (context == null || isActivityFinishing()) {
            context = sAppContext;
        }
        return context;
    }


    protected String getString(int rid) {
        return getContext().getString(rid);
    }


    /**
     * activity 是否是finishing状态
     *
     * @return
     */
    private boolean isActivityFinishing() {
        if (mContextRef == null) {
            return true;
        }
        Context context = mContextRef.get();
        if (context instanceof Activity) {
            Activity hostActivity = (Activity) context;
            return hostActivity.isFinishing();
        }
        return true;
    }

    /**
     * 返回 Mvp View对象. 如果真实的 View对象已经被销毁, 那么会通过动态代理构建一个View,
     * 确保调用 View对象执行操作时不会crash.
     *
     * @return Mvp View
     */
    protected T getMvpView() {
        T view = mViewRef != null ? mViewRef.get() : null;
        if (view == null) {
            // create null mvp view
            if (mNullViewProxy == null) {
                mNullViewProxy = createView(getMvpViewClass());
            }
            view = mNullViewProxy;
        }
        return view;
    }


    /**
     * 创建 mvp view
     *
     * @param viewClz
     * @param <T>
     * @return
     */
    private static <T> T createView(Class<T> viewClz) {
        return (T) Proxy.newProxyInstance(viewClz.getClassLoader(),
                new Class[]{viewClz}, NULL_VIEW);
    }


    private Class<T> getMvpViewClass() {
        if (mMvpViewClass == null) {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            mMvpViewClass = (Class<T>) params[0];
        }
        return mMvpViewClass;
    }


    /**
     * 动态代理 InvocationHandler
     */
    private static class MvpViewInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //Log.e("", "### MvpView InvocationHandler do nothing -> " + method.getName()) ;
            return null;
        }
    }

}
