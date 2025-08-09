package org.apache.derby.impl.sql.conn;

import org.apache.derby.iapi.sql.dictionary.TableDescriptor;

class TempTableInfo {
   private TableDescriptor td;
   private int declaredInSavepointLevel;
   private int droppededInSavepointLevel;
   private int dataModifiedInSavepointLevel;

   TempTableInfo(TableDescriptor var1, int var2) {
      this.td = var1;
      this.declaredInSavepointLevel = var2;
      this.droppededInSavepointLevel = -1;
      this.dataModifiedInSavepointLevel = -1;
   }

   TableDescriptor getTableDescriptor() {
      return this.td;
   }

   void setTableDescriptor(TableDescriptor var1) {
      this.td = var1;
   }

   boolean matches(String var1) {
      return this.td.getName().equals(var1) && this.droppededInSavepointLevel == -1;
   }

   int getModifiedInSavepointLevel() {
      return this.dataModifiedInSavepointLevel;
   }

   void setModifiedInSavepointLevel(int var1) {
      this.dataModifiedInSavepointLevel = var1;
   }

   int getDeclaredInSavepointLevel() {
      return this.declaredInSavepointLevel;
   }

   void setDeclaredInSavepointLevel(int var1) {
      this.declaredInSavepointLevel = var1;
   }

   int getDroppedInSavepointLevel() {
      return this.droppededInSavepointLevel;
   }

   public void setDroppedInSavepointLevel(int var1) {
      this.droppededInSavepointLevel = var1;
   }
}
