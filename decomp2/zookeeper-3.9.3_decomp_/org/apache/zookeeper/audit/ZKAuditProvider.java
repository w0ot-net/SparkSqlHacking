package org.apache.zookeeper.audit;

import java.lang.reflect.Constructor;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKAuditProvider {
   static final String AUDIT_ENABLE = "zookeeper.audit.enable";
   static final String AUDIT_IMPL_CLASS = "zookeeper.audit.impl.class";
   private static final Logger LOG = LoggerFactory.getLogger(ZKAuditProvider.class);
   private static boolean auditEnabled = Boolean.getBoolean("zookeeper.audit.enable");
   private static AuditLogger auditLogger;

   private static AuditLogger getAuditLogger() {
      String auditLoggerClass = System.getProperty("zookeeper.audit.impl.class");
      if (auditLoggerClass == null) {
         auditLoggerClass = Slf4jAuditLogger.class.getName();
      }

      try {
         Constructor<?> clientCxnConstructor = Class.forName(auditLoggerClass).getDeclaredConstructor();
         AuditLogger auditLogger = (AuditLogger)clientCxnConstructor.newInstance();
         auditLogger.initialize();
         return auditLogger;
      } catch (Exception e) {
         throw new RuntimeException("Couldn't instantiate " + auditLoggerClass, e);
      }
   }

   public static boolean isAuditEnabled() {
      return auditEnabled;
   }

   public static void log(String user, String operation, String znode, String acl, String createMode, String session, String ip, AuditEvent.Result result) {
      auditLogger.logAuditEvent(createLogEvent(user, operation, znode, acl, createMode, session, ip, result));
   }

   static AuditEvent createLogEvent(String user, String operation, AuditEvent.Result result) {
      AuditEvent event = new AuditEvent(result);
      event.addEntry(AuditEvent.FieldName.USER, user);
      event.addEntry(AuditEvent.FieldName.OPERATION, operation);
      return event;
   }

   static AuditEvent createLogEvent(String user, String operation, String znode, String acl, String createMode, String session, String ip, AuditEvent.Result result) {
      AuditEvent event = new AuditEvent(result);
      event.addEntry(AuditEvent.FieldName.SESSION, session);
      event.addEntry(AuditEvent.FieldName.USER, user);
      event.addEntry(AuditEvent.FieldName.IP, ip);
      event.addEntry(AuditEvent.FieldName.OPERATION, operation);
      event.addEntry(AuditEvent.FieldName.ZNODE, znode);
      event.addEntry(AuditEvent.FieldName.ZNODE_TYPE, createMode);
      event.addEntry(AuditEvent.FieldName.ACL, acl);
      return event;
   }

   public static void addZKStartStopAuditLog() {
      if (isAuditEnabled()) {
         log(getZKUser(), "serverStart", AuditEvent.Result.SUCCESS);
         Runtime.getRuntime().addShutdownHook(new Thread(() -> log(getZKUser(), "serverStop", AuditEvent.Result.INVOKED)));
      }

   }

   public static void addServerStartFailureAuditLog() {
      if (isAuditEnabled()) {
         log(getZKUser(), "serverStart", AuditEvent.Result.FAILURE);
      }

   }

   private static void log(String user, String operation, AuditEvent.Result result) {
      auditLogger.logAuditEvent(createLogEvent(user, operation, result));
   }

   public static String getZKUser() {
      return ServerCnxnFactory.getUserName();
   }

   static {
      if (auditEnabled) {
         auditLogger = getAuditLogger();
         LOG.info("ZooKeeper audit is enabled.");
      } else {
         LOG.info("ZooKeeper audit is disabled.");
      }

   }
}
