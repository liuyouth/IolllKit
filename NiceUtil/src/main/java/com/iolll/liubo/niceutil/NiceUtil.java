package com.iolll.liubo.niceutil;

import com.iolll.liubo.ifunction.IFunction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


/**
 * Created by LiuBo on 2018/12/17.
 */
public enum NiceUtil {
    INS;

    private static final String TAG = "NiceUtil";
    public static NiceContextCallBack callBack;

    public void init(NiceContextCallBack contextCallBack) {
        callBack = contextCallBack;
    }

    /**
     * 利用序列化将 父类转为子类
     * 或者将两个不同的类进行转换 只要其中变量名字相同就会被转过去
     *
     * @param son
     * @param superClass
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R sonBeSuAutoper(T son, Class<R> superClass) {
        return callBack.gson().fromJson(callBack.gson().toJson(son), superClass);
    }


    @SafeVarargs
    public static <T> void forEach(IFunction.Run<T> run, T... tList) {
        for (T t : tList) run.run(t);
    }

    public static <T> void forEach(IFunction.Run<T> run, ArrayList<T> ts) {
        for (T t : ts) run.run(t);
    }

    public static <T> void forEach(ArrayList<T> ts, IFunction.Run<T> run) {
        for (T t : ts) run.run(t);
    }

    public static <T> ArrayList<T> forEachApply(ArrayList<T> ts, IFunction.Apply<T> apply) {
        for (int i = 0; i < ts.size(); i++) {
            T t = ts.get(i);
            t = apply.apply(t);
            ts.set(i, t);
        }
        return ts;
    }

    /**
     * Rxjava 常见filter 拦截器
     *
     * @param isShow
     * @param msg
     * @return
     */

    public static boolean handleFilter(boolean isShow, String msg) {
        if (!isEmpty(msg) && isShow) {
            callBack.toast(msg);
        }
        return !isShow;
    }


    /**
     * 基于上面的拦截器进行简易拦截
     * 多变类型
     *
     * @param msg
     * @return
     */
    public static boolean hFilter(boolean isShow, String msg) {
        return handleFilter(isShow, msg);
    }

    public static boolean hFilter(String s, String msg) {
        return handleFilter(s, msg);
    }

    public static boolean handleFilter(String s, String msg) {
        return handleFilter(isEmpty(s), msg);
    }

    public static boolean hFilter(Integer s, String msg) {
        return handleFilter(s, msg);
    }

    public static boolean handleFilter(Integer s, String msg) {
        return handleFilter(s == null || s == 0, msg);
    }

    public static boolean isEmpty(Integer value) {
        return value == null || value == 0;
    }

    public static boolean isEmpty(String... strings) {
        for (String string : strings)
            if (!isEmpty(string))
                return false;
        return true;
    }

    private static boolean isEmpty(String msg) {
        return null == msg || "".equals(msg);
    }

    /**
     * 粗略版String 时间格式校验
     * 原理通过传入时间长度以及是否能format 进行转换
     * 不如正则的校验严谨，但大家都看得懂
     * 1999-99-99 false
     * 1999-00-00 false
     * 1999-1-1 true 自动补0
     * 长度不一样的我就不做举例了，不同长度的string 和 format 肯定不是合格的
     *
     * @param str
     * @param formats
     * @return
     */
    public static boolean isValidDate(String str, String formats) {
        boolean convertSuccess = true;
        if (isEmpty(str) || isEmpty(formats))
            return false;
        if (str.length() != formats.length()) //长度都不一样 还校验个啥？？？
            return false;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(formats);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static <T> T newTClass(Class clazz) {
        T t = null;
        try {
            t = (T) clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * 自动将月份日子的0补齐
     * 将2019-1-1 的格式转为 2019-01-01
     *
     * @param formats
     * @param date
     * @return
     */
    public static String dateAutoZoro(String formats, String date) {
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(formats);
        Date date1 = new Date();
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            date1 = format.parse(date);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
        }
        return format.format(date1);
    }

    public static String defaultString(String plateNo) {
        return defaultString(plateNo, "");
    }

    public static boolean defaultValue(Boolean b) {
        if (b == null) {
            return false;
        } else return b;
    }

    public static String defaultString(String plateNo, String defaultV) {
        return isEmpty(plateNo) ? defaultV : plateNo;
    }


    public static class IOZip<T1, T2> {
        public T1 t1;
        public T2 t2;

        public IOZip(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        @Override
        public String toString() {
            return "IOZip{" +
                    "t1=" + t1 +
                    ", t2=" + t2 +
                    '}';
        }
    }


    public static <T1, T2> IOZip<T1, T2> create(T1 t1, T2 t2) {
        return new IOZip<>(t1, t2);
    }

    public static <T1, T2> Observable<IOZip<T1, T2>> just(T1 t1, T2 t2) {
        return Observable.just(create(t1, t2));
    }

    /**
     * 简化迭代器使用
     *
     * @param eIterator
     * @param runR      将数据传递出去进行判断是否需要移除
     * @param <E>
     * @param <T>
     */
    public static <E, T> void remove(Iterator<E> eIterator, IFunction.RunR<T, Boolean> runR) {
        while (eIterator.hasNext()) if (runR.run((T) eIterator.next())) eIterator.remove();
    }

    /**
     * 根据下标移除数据
     *
     * @param eIterator
     * @param range
     */
    public static <E> void remove(Iterator<E> eIterator, ArrayList<Integer> range) {
        // TODO 根据下标移除数据
        int index = 0;
        while (eIterator.hasNext()) {
            eIterator.next();
            if (range.contains(index)) {
                eIterator.remove();
            }
            index++;
        }
    }

    /**
     * 根据下标移除数据
     *
     * @param eIterator
     * @param indexes
     * @param <E>
     */
    public static <E> void remove(Iterator<E> eIterator, int... indexes) {
        remove(eIterator, (ArrayList<Integer>) asList(indexes));
    }

    /**
     * 获取一个int区间
     *
     * @param start
     * @param count
     * @return
     */
    public static ArrayList<Integer> getRange(final int start, final int count) {
        return new ArrayList<Integer>() {{
            for (int i = start; i < count + start; i++)
                add(i);
        }};
    }

    public static List<Integer> asList(int[] ints) {
        List<Integer> list = new ArrayList<>();
        for (int anInt : ints)
            list.add(anInt);
        return list;
    }

    /**
     * 仅仅用于单层结构  ，嵌套结构不可使用 切记
     * 不是空的 = true
     *
     * @param objects
     * @return
     */
    public static boolean isNotNull(Object... objects) {
        return !isNull(objects);
    }

    public static boolean isNull(Object... objects) {
        for (Object o : objects) {
            if (null == o)
                return true;
        }
        return false;
    }

    public static <T> void forEach(List<T> ts, IFunction.Run<T> run) {
        for (T t : ts) run.run(t);
    }

    // 遍历所有
    public static <T> Boolean forEachCheck(ArrayList<T> ts, IFunction.RunR<T, Boolean> apply) {
        if (null == ts)
            return false;
        boolean isCheck = false;
        for (int i = 0; i < ts.size(); i++) {
            if (apply.run(ts.get(i))) {
                isCheck = true;
            }
        }
        return isCheck;
    }

    // 遍历所有
    public static <T> Boolean forEachCheck(IFunction.RunR<T, Boolean> apply, T... ts) {
        if (null == ts)
            return false;
        boolean isCheck = false;
        for (T t : ts) {
            if (apply.run(t)) {
                isCheck = true;
            }
        }
        return isCheck;
    }

    private static <T> boolean forEachCheck(IFunction.RunR<T, Boolean> runR, List<T> ts) {
        if (null == ts)
            return false;
        boolean isCheck = false;
        for (T t : ts) {
            if (runR.run(t)) {
                isCheck = true;
            }
        }
        return isCheck;
    }

    /**
     * 返回apply 为 true 的数据
     *
     * @param ts
     * @param apply
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> arrayListOfAccord(ArrayList<T> ts, IFunction.RunR<T, Boolean> apply) {
        ArrayList<T> results = new ArrayList<>();
        for (int i = 0; i < ts.size(); i++) {
            T t = ts.get(i);
            if (apply.run(t))
                results.add(t);
        }
        return results;
    }


    /**
     * 循环拿出所有的数据 然后run 如果返回值true 就 返回 true 反之 循环完毕返回 false
     *
     * @param run
     * @param ts
     * @param <T>
     */
    public static <T> Boolean forEachR(ArrayList<T> ts, IFunction.RunR<T, Boolean> run) {
        for (T t : ts) if (run.run(t)) return true;
        return false;
    }


    // 遍历指定
    public static <T> void forEachStartAndEnd(ArrayList<T> ts, int start, int end, IFunction.Run<T> apply) {
        for (int i = 0; i < ts.size(); i++) {
            if (start <= i && i <= end)
                apply.run(ts.get(i));
        }
    }


//    /**
//     * 粗略版String 时间格式校验
//     * 原理通过传入时间长度以及是否能format 进行转换
//     * 不如正则的校验严谨，但大家都看得懂
//     * 1999-99-99 false
//     * 1999-00-00 false
//     * 1999-1-1 true 自动补0
//     * 长度不一样的我就不做举例了，不同长度的string 和 format 肯定不是合格的
//     *
//     * @param str
//     * @param formats
//     * @return
//     */
//    public static boolean isValidDate(String str, String formats) {
//        boolean convertSuccess = true;
//        if (isEmpty(str) || isEmpty(formats))
//            return false;
//        if (str.length() != formats.length()) //长度都不一样 还校验个啥？？？
//            return false;
//        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(formats);
//        try {
//            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
//            format.setLenient(false);
//            format.parse(str);
//        } catch (ParseException e) {
//            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
//            convertSuccess = false;
//        }
//        return convertSuccess;
//    }


//    public static String dateAutoZoro(String formats, String date) {
//        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(formats);
//        Date date1 = new Date();
//        try {
//            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
//            format.setLenient(false);
//            date1 = format.parse(date);
//        } catch (ParseException e) {
//            Log.e(TAG, "dateAutoZoro: " + e.getMessage());
//            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
//        }
//        return format.format(date1);
//    }


    /**
     * 用于将一个数组中不同的字段提取成不同的list结构
     *
     * @param datas
     * @param arrayListRunRHashMap
     */
    public static <T> void forEachSplitToList(List<T> datas, HashMap<ArrayList, IFunction.RunR> arrayListRunRHashMap) {
        for (T data : datas) {
            for (Map.Entry<ArrayList, IFunction.RunR> entry : arrayListRunRHashMap.entrySet()) {
                Object o = entry.getValue().run(data);
                if (null != o)
                    entry.getKey().add(o);
            }
        }
    }

    public static <K, T> List<T> mapToList(LinkedHashMap<K, T> map) {
        Iterator<Map.Entry<K, T>> iterator = map.entrySet().iterator();
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next().getValue());
        }
        return list;
    }

    public static String getString(String name, int count, boolean isAddEllipsis, boolean isEnd) {
        if (isEmpty(name))
            return "";
        if (name.length() <= count)
            return name;
        int leng = name.length();
        return isEnd ? name.substring(leng - count, leng) : name.substring(0, count) + (isAddEllipsis ? "..." : "");
    }

    public static String get2String(String name) {
        if (isEmpty(name))
            return "";
        if (name.length() <= 2)
            return name;
        int leng = name.length();
        return name.substring(leng - 2, leng);
    }

//    public static int getSize(List<HotelNewApprovePsgsBean.ShenpirensBean> shenpirensBeans) {
//        if (shenpirensBeans == null)
//            return 0;
//        else return shenpirensBeans.size();
//    }


    public static <T> boolean isEmpty(List<T> datas) {
        return null == datas || datas.isEmpty();
    }


}