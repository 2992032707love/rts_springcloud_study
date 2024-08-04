package com.rts.config;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/8/3 2:51
 **/
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task " + r.toString() + " rejected from " + executor.toString());

        // Add the task back to the queue
        if (!executor.isShutdown()) {
            // 暂停几秒钟线程
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task rejectedExecution, 重新添加任务" + Thread.currentThread().getName());
            // 重新添加任务
            executor.execute(r);
        }

    }
}
