package org.apache.derby.impl.services.locks;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public final class Timeout {
   public static final int TABLE_AND_ROWLOCK = 2;
   public static final int ALL = -1;
   public static final String newline = "\n";
   private TransactionController tc;
   private TableNameInfo tabInfo;
   private Latch currentLock;
   private char[] outputRow;
   private StringBuffer sb;
   private Hashtable currentRow;
   private final long currentTime;
   private final Enumeration lockTable;
   private static final String[] column = new String[9];
   private static final int LENGTHOFTABLE;
   private static final char LINE = '-';
   private static final char SEPARATOR = '|';

   private Timeout(Latch var1, Enumeration var2, long var3) {
      this.currentLock = var1;
      this.lockTable = var2;
      this.currentTime = var3;
   }

   private StandardException createException() {
      try {
         this.buildLockTableString();
      } catch (StandardException var2) {
         return var2;
      }

      StandardException var1 = StandardException.newException("40XL1.T.1", new Object[]{this.sb.toString()});
      var1.setReport(2);
      return var1;
   }

   private String buildLockTableString() throws StandardException {
      this.sb = new StringBuffer(8192);
      this.outputRow = new char[LENGTHOFTABLE];
      LanguageConnectionContext var2 = (LanguageConnectionContext)Deadlock.getContext("LanguageConnectionContext");
      if (var2 != null) {
         this.tc = var2.getTransactionExecute();
      }

      try {
         this.tabInfo = new TableNameInfo(var2, true);
      } catch (Exception var4) {
      }

      this.sb.append("\n");
      this.sb.append(new Date(this.currentTime));
      this.sb.append("\n");

      for(int var1 = 0; var1 < column.length; ++var1) {
         this.sb.append(column[var1]);
         this.sb.append('|');
      }

      this.sb.append("\n");

      for(int var5 = 0; var5 < LENGTHOFTABLE; ++var5) {
         this.sb.append('-');
      }

      this.sb.append("\n");
      if (this.currentLock != null) {
         this.dumpLock();
         if (this.timeoutInfoHash()) {
            this.sb.append("*** The following row is the victim ***");
            this.sb.append("\n");
            this.sb.append(this.outputRow);
            this.sb.append("\n");
            this.sb.append("*** The above row is the victim ***");
            this.sb.append("\n");
         } else {
            this.sb.append("*** A victim was chosen, but it cannot be printed because the lockable object, " + this.currentLock + ", does not want to participate ***");
            this.sb.append("\n");
         }
      }

      if (this.lockTable != null) {
         while(this.lockTable.hasMoreElements()) {
            this.currentLock = (Latch)this.lockTable.nextElement();
            this.dumpLock();
            if (this.timeoutInfoHash()) {
               this.sb.append(this.outputRow);
               this.sb.append("\n");
            } else {
               this.sb.append("*** A latch/lock, " + this.currentLock + ", exist in the lockTable that cannot be printed ***");
               this.sb.append("\n");
            }
         }

         for(int var6 = 0; var6 < LENGTHOFTABLE; ++var6) {
            this.sb.append('-');
         }

         this.sb.append("\n");
      }

      return this.sb.toString();
   }

   static StandardException buildException(Latch var0, Enumeration var1, long var2) {
      Timeout var4 = new Timeout(var0, var1, var2);
      return var4.createException();
   }

   public static String buildString(Enumeration var0, long var1) throws StandardException {
      Timeout var3 = new Timeout((Latch)null, var0, var1);
      return var3.buildLockTableString();
   }

   private void dumpLock() throws StandardException {
      Hashtable var1 = new Hashtable(17);
      Object var2 = this.currentLock.getQualifier();
      Lockable var3 = this.currentLock.getLockable();
      if (!var3.lockAttributes(-1, var1)) {
         this.currentRow = null;
      } else {
         Long var4 = (Long)var1.get("CONGLOMID");
         if (var4 == null && var1.get("CONTAINERID") != null && this.tc != null) {
            Long var5 = (Long)var1.get("CONTAINERID");
            var4 = this.tc.findConglomid(var5);
            var1.put("CONGLOMID", var4);
         }

         Long var10 = (Long)var1.get("CONTAINERID");
         if (var10 == null && var4 != null && this.tc != null) {
            try {
               var10 = this.tc.findContainerid(var4);
               var1.put("CONTAINERID", var10);
            } catch (Exception var8) {
            }
         }

         var1.put("LOCKOBJ", this.currentLock);
         var1.put("XID", String.valueOf(this.currentLock.getCompatabilitySpace().getOwner()));
         var1.put("MODE", var2.toString());
         var1.put("LOCKCOUNT", Integer.toString(this.currentLock.getCount()));
         var1.put("STATE", this.currentLock.getCount() != 0 ? "GRANT" : "WAIT");
         if (this.tabInfo != null && var4 != null) {
            try {
               String var6 = this.tabInfo.getTableName(var4);
               var1.put("TABLENAME", var6);
            } catch (NullPointerException var7) {
               var1.put("TABLENAME", var4);
            }

            try {
               String var12 = this.tabInfo.getIndexName(var4);
               if (var12 != null) {
                  var1.put("INDEXNAME", var12);
               } else if (var1.get("TYPE").equals("LATCH")) {
                  var1.put("INDEXNAME", var1.get("MODE"));
               } else {
                  var1.put("INDEXNAME", "NULL");
               }
            } catch (Exception var9) {
               if ("CONTAINERID" != null) {
                  var1.put("INDEXNAME", "CONTAINERID");
               } else {
                  var1.put("INDEXNAME", "NULL");
               }
            }

            String var13 = this.tabInfo.getTableType(var4);
            var1.put("TABLETYPE", var13);
         } else {
            if (var4 != null) {
               var1.put("TABLENAME", "CONGLOMID");
            } else {
               var1.put("TABLENAME", "NULL");
            }

            if ("CONTAINERID" != null) {
               var1.put("INDEXNAME", "CONTAINERID");
            } else {
               var1.put("INDEXNAME", "NULL");
            }

            var1.put("TABLETYPE", this.currentLock.toString());
         }

         this.currentRow = var1;
      }
   }

   private void cpArray(String var1, int var2, int var3) {
      int var4 = 0;
      int var5 = var3 - var2;
      if (var1 != null) {
         while(var4 < var1.length() && var5 - var4 != 0) {
            this.outputRow[var4 + var2] = var1.charAt(var4);
            ++var4;
         }
      }

      while(var4 + var2 != var3) {
         this.outputRow[var4 + var2] = ' ';
         ++var4;
      }

      this.outputRow[var3] = '|';
   }

   private boolean timeoutInfoHash() {
      if (this.currentRow == null) {
         return false;
      } else {
         String[] var1 = new String[]{"XID", "TYPE", "MODE", "LOCKCOUNT", "LOCKNAME", "STATE", "TABLETYPE", "INDEXNAME", "TABLENAME"};
         int var2 = 0;

         for(int var3 = 0; var3 < var1.length; ++var3) {
            this.cpArray(this.currentRow.get(var1[var3]).toString(), var2, var2 + column[var3].length());
            var2 = var2 + column[var3].length() + 1;
         }

         return true;
      }
   }

   static {
      column[0] = "XID       ";
      column[1] = "TYPE         ";
      column[2] = "MODE";
      column[3] = "LOCKCOUNT";
      column[4] = "LOCKNAME                                                                        ";
      column[5] = "STATE";
      column[6] = "TABLETYPE / LOCKOBJ                   ";
      column[7] = "INDEXNAME / CONTAINER_ID / (MODE for LATCH only)  ";
      column[8] = "TABLENAME / CONGLOM_ID                ";
      int var0 = 0;

      for(int var1 = 0; var1 < column.length; ++var1) {
         var0 += column[var1].length();
      }

      var0 += column.length;
      LENGTHOFTABLE = var0;
   }
}
