package org.apache.curator;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;

public interface RetryPolicy {
   boolean allowRetry(int var1, long var2, RetrySleeper var4);

   default boolean allowRetry(Throwable exception) {
      if (!(exception instanceof KeeperException)) {
         return false;
      } else {
         int rc = ((KeeperException)exception).code().intValue();
         return rc == Code.CONNECTIONLOSS.intValue() || rc == Code.OPERATIONTIMEOUT.intValue() || rc == Code.SESSIONMOVED.intValue() || rc == Code.SESSIONEXPIRED.intValue();
      }
   }
}
