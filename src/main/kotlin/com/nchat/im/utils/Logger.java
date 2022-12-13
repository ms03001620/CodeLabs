package com.nchat.im.utils;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
	/**
	 * log tag
	 */
	private String tagName = "BudChat";// tag name
	//private static int logLevel = Log.DEBUG;

	private static Logger inst;
	private Lock lock;

	private Logger() {
		lock = new ReentrantLock();
	}

	public static synchronized Logger getLogger(Class<?> key) {
		if (inst == null) {
			inst = new Logger();
		}
		return inst;
	}

	private String getFunctionName() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();

		if (sts == null) {
			return null;
		}

		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}

			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}

			if (st.getClassName().equals(this.getClass().getName())) {
				continue;
			}

			return "[" + st.getFileName() + ":" + st.getLineNumber() + "]";
		}

		return null;
	}

	private String createMessage(String msg) {
		String functionName = getFunctionName();
		long threadId = Thread.currentThread().getId();
		String currentTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS").format(new Date());
		String message = (functionName == null ? msg : (functionName + " - "
				+ String.valueOf(threadId) + " - " + msg));
        String finalRes = currentTime + " - " + message;


		return finalRes;
	}



	/**
	 * log.d
	 */
	public void d(String format, Object... args) {
		lock.lock();
		try {
			String message = createMessage(getInputString(format, args));
			System.out.println(tagName+message);
		} finally {
			lock.unlock();
		}
	}



	private String getInputString(String format, Object... args) {
		if (format == null) {
			return "null log format";
		}

		return String.format(format, args);
	}


	public void e(String message) {
		lock.lock();
		try {
			System.out.println(tagName + "e" + message);
		} finally {
			lock.unlock();
		}
	}
}
