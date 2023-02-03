//package com.mrwang.concurrent.lock;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//
//public class FunnelRateLimiter {
//    static class Funnel {
//        int capacity;
//        float leakingRate;
//        int leftQuota;
//        long startTs;
//        AtomicLong usedQuota;
//
////        public Funnel(int capacity, float leakingRate) {
//            this.capacity = capacity;
//            this.leakingRate = leakingRate;
//            this.leftQuota = capacity;
//            this.startTs = System.currentTimeMillis();
//            this.usedQuota = new AtomicLong(0);
//        }
//
//        void makeSpace() {
//            long nowTs = System.currentTimeMillis();
//            long deltaTs = (nowTs - startTs) / 1000;
//            int deltaQuota = (int)(deltaTs * leakingRate);
//            if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
//                this.leftQuota = capacity;
//                this.leakingTs = nowTs;
//                return;
//            }
//            if (deltaQuota < 1) { // 腾出空间太小，最小单位是 1
//                return;
//            }
//            this.leftQuota += deltaQuota;
//            this.leakingTs = nowTs;
//            if (this.leftQuota > this.capacity) {
//                this.leftQuota = this.capacity;
//            }
//        }
//
//        boolean watering(int quota) {
//            makeSpace();
//            if (this.leftQuota >= quota) {
//                this.leftQuota -= quota;
//                return true;
//            }
//            return false;
//        }
//    }
//
//    private Map<String, Funnel> funnels = new HashMap<>();
//
//    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
//        String key = String.format("%s:%s", userId, actionKey);
//        Funnel funnel = funnels.get(key);
//        if (funnel == null) {
//            funnel = new Funnel(capacity, leakingRate);
//            funnels.put(key, funnel);
//        }
//        return funnel.watering(1); // 需要 1 个 quota
//    }
//
//    public static void main(String[] args) {
//        AtomicInteger count = new AtomicInteger(0);
//        FunnelRateLimiter funnelRateLimiter = new FunnelRateLimiter();
//
//        for (int i = 0; i < 1000; i++) {
//            new Thread(() -> {
//                boolean actionAllowed = funnelRateLimiter.isActionAllowed("aaa", "bbb", 10, 1);
//                if (actionAllowed) {
//                    count.incrementAndGet();
//                }
//            }).start();
//        }
//        System.out.println(count.get());
//    }
//
//}