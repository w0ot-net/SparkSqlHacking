package org.apache.curator.framework.api.transaction;

import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.zookeeper.data.Stat;

public class CuratorTransactionResult {
   private final OperationType type;
   private final String forPath;
   private final String resultPath;
   private final Stat resultStat;
   private final int error;

   public static Predicate ofTypeAndPath(OperationType type, String forPath) {
      return (result) -> result.getType() == type && result.getForPath().equals(forPath);
   }

   public CuratorTransactionResult(OperationType type, String forPath, String resultPath, Stat resultStat) {
      this(type, forPath, resultPath, resultStat, 0);
   }

   public CuratorTransactionResult(OperationType type, String forPath, String resultPath, Stat resultStat, int error) {
      this.forPath = forPath;
      this.resultPath = resultPath;
      this.resultStat = resultStat;
      this.type = type;
      this.error = error;
   }

   public OperationType getType() {
      return this.type;
   }

   public String getForPath() {
      return this.forPath;
   }

   public String getResultPath() {
      return this.resultPath;
   }

   public Stat getResultStat() {
      return this.resultStat;
   }

   public int getError() {
      return this.error;
   }
}
