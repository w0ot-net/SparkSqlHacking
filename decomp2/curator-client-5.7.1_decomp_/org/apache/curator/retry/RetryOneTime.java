package org.apache.curator.retry;

public class RetryOneTime extends RetryNTimes {
   public RetryOneTime(int sleepMsBetweenRetry) {
      super(1, sleepMsBetweenRetry);
   }
}
