package ru.venediktov.javacore.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

@Slf4j
public class ForkJoinPoolExample2 {
    public static void main(String[] args) {
        CustomRecursiveAction recursiveAction = new CustomRecursiveAction("venediktov vladimir sergeevich");
        ForkJoinPool myPool = new ForkJoinPool(3);
        myPool.invoke(recursiveAction);
        log.info("end!");
    }
}
