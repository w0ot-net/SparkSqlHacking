package org.apache.curator.framework.recipes.shared;

public class IllegalTrySetVersionException extends IllegalArgumentException {
   public String getMessage() {
      return "overflowed Stat.version -1 is not suitable for trySet(a.k.a. compare-and-set ZooKeeper::setData)";
   }
}
