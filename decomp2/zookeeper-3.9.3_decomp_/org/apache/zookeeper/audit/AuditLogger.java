package org.apache.zookeeper.audit;

public interface AuditLogger {
   default void initialize() {
   }

   void logAuditEvent(AuditEvent var1);
}
