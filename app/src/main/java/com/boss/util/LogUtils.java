package com.boss.util;

/**
 * The custom utility class for log. Centralize the control of log.
 * In real project, you may consider use <a href="https://github.com/JakeWharton/timber">Timber</a>
 * instead rather than reinventing the wheels.
 *
 * <p/>
 *
 * 自定义日志工具类，统一对日志输出做出控制。
 * 在公司项目中，可以考虑使用Timber等开源日志工具，不用重复造轮子。
 */
@SuppressWarnings("unused")
public final class LogUtils {

    private static final String TAG = "gitdroid";

    private static final String TAG_TRACE = "gitdroid";

    private static final class StackTraceDebug extends RuntimeException {
        final static private long serialVersionUID = 27058374L;
    }

    /**
     * The debug flag is cached here so that we don't need to access the settings every time we have to evaluate it.
     */
    private static boolean isDebug = true;

    private static final boolean logTrace = true;

    private LogUtils() {
        // utility class
    }

    public static void trace(Object object) {
        if (logTrace) {
            StackTraceElement[] traces = Thread.currentThread().getStackTrace();
            StackTraceElement trace = traces[3];
            android.util.Log.d(TAG_TRACE, addThreadInfo(object.getClass().getSimpleName() + " : " + trace.getMethodName()));
        }
    }


    public static boolean isDebug() {
        return isDebug;
    }

    public static boolean isLogTrace() {
        return logTrace;
    }

    /**
     * Save a copy of the debug flag from the settings for performance reasons.
     */
    public static void setDebug(final boolean isDebug) {
        LogUtils.isDebug = isDebug;
    }

    private static String addThreadInfo(final String msg) {
        final String threadName = Thread.currentThread().getName();
        final String shortName = threadName.startsWith("OkHttp") ? "OkHttp" : threadName;
        return "[" + shortName + "] " + msg;
    }

    public static void v(final String msg) {
        if (isDebug) {
            android.util.Log.v(TAG, addThreadInfo(msg));
        }
    }

    public static void v(final String msg, final Throwable t) {
        if (isDebug) {
            android.util.Log.v(TAG, addThreadInfo(msg), t);
        }
    }

    public static void d(final String msg) {
        if (isDebug) {
            android.util.Log.d(TAG, addThreadInfo(msg));
        }
    }

    public static void d(final String msg, final Throwable t) {
        if (isDebug) {
            android.util.Log.d(TAG, addThreadInfo(msg), t);
        }
    }

    public static void i(final String msg) {
        if (isDebug) {
            android.util.Log.i(TAG, addThreadInfo(msg));
        }
    }

    public static void i(final String msg, final Throwable t) {
        if (isDebug) {
            android.util.Log.i(TAG, addThreadInfo(msg), t);
        }
    }

    public static void w(final String msg) {
        android.util.Log.w(TAG, addThreadInfo(msg));
    }

    public static void w(final String msg, final Throwable t) {
        android.util.Log.w(TAG, addThreadInfo(msg), t);
    }

    public static void e(final String msg) {
        android.util.Log.e(TAG, addThreadInfo(msg));
    }

    public static void e(final String msg, final Throwable t) {
        android.util.Log.e(TAG, addThreadInfo(msg), t);
    }

    /**
     * Record a debug message with the actual stack trace.
     *
     * @param msg the debug message
     */
    public static void logStackTrace(final String msg) {
        try {
            throw new StackTraceDebug();
        } catch (final StackTraceDebug dbg) {
            d(msg, dbg);
        }
    }
}
