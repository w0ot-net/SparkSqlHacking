package org.apache.derby.diag;

class ConglomInfo {
   private String tableID;
   private long conglomId;
   private String conglomName;
   private boolean isIndex;

   public ConglomInfo(String var1, long var2, String var4, boolean var5) {
      this.tableID = var1;
      this.conglomId = var2;
      this.conglomName = var4;
      this.isIndex = var5;
   }

   public String getTableID() {
      return this.tableID;
   }

   public long getConglomId() {
      return this.conglomId;
   }

   public String getConglomName() {
      return this.conglomName;
   }

   public boolean getIsIndex() {
      return this.isIndex;
   }
}
