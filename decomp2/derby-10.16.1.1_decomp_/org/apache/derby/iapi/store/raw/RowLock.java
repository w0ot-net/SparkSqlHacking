package org.apache.derby.iapi.store.raw;

public final class RowLock {
   private final int type;
   private final int typeBit;
   private final int compat;
   private static final String[] shortnames = new String[]{"S", "S", "U", "U", "X", "X", "X", "X"};
   public static final int R_NUMBER = 8;
   private static final boolean[][] R_COMPAT = new boolean[][]{{true, true, true, true, true, false, false, false}, {true, true, true, true, false, false, false, false}, {true, true, false, false, true, false, false, false}, {true, true, false, false, false, false, false, false}, {true, false, true, false, true, true, true, false}, {false, false, false, false, true, false, false, false}, {false, false, false, false, true, false, false, false}, {false, false, false, false, false, false, false, false}};
   public static final RowLock RS2 = new RowLock(0);
   public static final RowLock RS3 = new RowLock(1);
   public static final RowLock RU2 = new RowLock(2);
   public static final RowLock RU3 = new RowLock(3);
   public static final RowLock RIP = new RowLock(4);
   public static final RowLock RI = new RowLock(5);
   public static final RowLock RX2 = new RowLock(6);
   public static final RowLock RX3 = new RowLock(7);
   public static final String DIAG_INDEX = "index";
   public static final String DIAG_XACTID = "xactid";
   public static final String DIAG_LOCKTYPE = "locktype";
   public static final String DIAG_LOCKMODE = "lockmode";
   public static final String DIAG_CONGLOMID = "conglomId";
   public static final String DIAG_CONTAINERID = "containerId";
   public static final String DIAG_SEGMENTID = "segmentId";
   public static final String DIAG_PAGENUM = "pageNum";
   public static final String DIAG_RECID = "RecId";
   public static final String DIAG_COUNT = "count";
   public static final String DIAG_GROUP = "group";
   public static final String DIAG_STATE = "state";

   private RowLock(int var1) {
      this.type = var1;
      this.typeBit = 1 << var1;
      int var2 = 0;

      for(int var3 = 0; var3 < 8; ++var3) {
         if (R_COMPAT[var1][var3]) {
            var2 |= 1 << var3;
         }
      }

      this.compat = var2;
   }

   public int getType() {
      return this.type;
   }

   public boolean isCompatible(RowLock var1) {
      return (var1.typeBit & this.compat) != 0;
   }

   public String toString() {
      return shortnames[this.getType()];
   }
}
