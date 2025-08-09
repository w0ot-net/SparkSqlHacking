package org.apache.zookeeper.audit;

public final class AuditConstants {
   static final String OP_START = "serverStart";
   static final String OP_STOP = "serverStop";
   public static final String OP_CREATE = "create";
   public static final String OP_DELETE = "delete";
   public static final String OP_SETDATA = "setData";
   public static final String OP_SETACL = "setAcl";
   public static final String OP_MULTI_OP = "multiOperation";
   public static final String OP_RECONFIG = "reconfig";
   public static final String OP_DEL_EZNODE_EXP = "ephemeralZNodeDeletionOnSessionCloseOrExpire";

   private AuditConstants() {
   }
}
