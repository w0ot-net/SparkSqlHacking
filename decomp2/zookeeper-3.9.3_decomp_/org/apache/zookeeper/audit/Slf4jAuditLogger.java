package org.apache.zookeeper.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jAuditLogger implements AuditLogger {
   private static final Logger LOG = LoggerFactory.getLogger(Slf4jAuditLogger.class);

   public void logAuditEvent(AuditEvent auditEvent) {
      if (auditEvent.getResult() == AuditEvent.Result.FAILURE) {
         LOG.error(auditEvent.toString());
      } else {
         LOG.info(auditEvent.toString());
      }

   }
}
