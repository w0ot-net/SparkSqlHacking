package org.apache.hive.service;

import org.apache.hadoop.hive.conf.HiveConf;

public class BreakableService extends AbstractService {
   private boolean failOnInit;
   private boolean failOnStart;
   private boolean failOnStop;
   private final int[] counts;

   public BreakableService() {
      this(false, false, false);
   }

   public BreakableService(boolean failOnInit, boolean failOnStart, boolean failOnStop) {
      super("BreakableService");
      this.counts = new int[4];
      this.failOnInit = failOnInit;
      this.failOnStart = failOnStart;
      this.failOnStop = failOnStop;
      this.inc(Service.STATE.NOTINITED);
   }

   private int convert(Service.STATE state) {
      switch (state) {
         case NOTINITED -> {
            return 0;
         }
         case INITED -> {
            return 1;
         }
         case STARTED -> {
            return 2;
         }
         case STOPPED -> {
            return 3;
         }
         default -> {
            return 0;
         }
      }
   }

   private void inc(Service.STATE state) {
      int index = this.convert(state);
      int var10002 = this.counts[index]++;
   }

   public int getCount(Service.STATE state) {
      return this.counts[this.convert(state)];
   }

   private void maybeFail(boolean fail, String action) {
      if (fail) {
         throw new BrokenLifecycleEvent(action);
      }
   }

   public void init(HiveConf conf) {
      this.inc(Service.STATE.INITED);
      this.maybeFail(this.failOnInit, "init");
      super.init(conf);
   }

   public void start() {
      this.inc(Service.STATE.STARTED);
      this.maybeFail(this.failOnStart, "start");
      super.start();
   }

   public void stop() {
      this.inc(Service.STATE.STOPPED);
      this.maybeFail(this.failOnStop, "stop");
      super.stop();
   }

   public void setFailOnInit(boolean failOnInit) {
      this.failOnInit = failOnInit;
   }

   public void setFailOnStart(boolean failOnStart) {
      this.failOnStart = failOnStart;
   }

   public void setFailOnStop(boolean failOnStop) {
      this.failOnStop = failOnStop;
   }

   public static class BrokenLifecycleEvent extends RuntimeException {
      BrokenLifecycleEvent(String action) {
         super("Lifecycle Failure during " + action);
      }
   }
}
