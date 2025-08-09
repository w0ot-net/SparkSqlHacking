package org.apache.curator.framework.api.transaction;

public class TypeAndPath {
   private final OperationType type;
   private final String forPath;

   public TypeAndPath(OperationType type, String forPath) {
      this.type = type;
      this.forPath = forPath;
   }

   public OperationType getType() {
      return this.type;
   }

   public String getForPath() {
      return this.forPath;
   }
}
