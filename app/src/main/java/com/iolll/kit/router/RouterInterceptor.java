package com.iolll.kit.router;

import android.content.Context;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import java.util.ArrayList;

//
// 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
// 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
@Interceptor(priority = 1, name = "校验uri是否都被实现的拦截器")
public class RouterInterceptor implements IInterceptor {
    public static boolean isTextUrl = false;
    public static ArrayList<String > noRealizeRouters = new ArrayList<>();
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (!isTextUrl) {
            callback.onContinue(postcard);  // 处理完成，交还控制权
        }else {
            callback.onInterrupt(new RuntimeException("改uri 未被实现,"+postcard.getPath()));
        }
        // callback.onInterrupt(new RuntimeException("我觉得有点异常"));      // 觉得有问题，中断路由流程
        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }
}