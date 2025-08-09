package org.apache.derby.iapi.types;

public class LocatedRow {
   private DataValueDescriptor[] _columnValues;
   private RowLocation _rowLocation;

   public LocatedRow(DataValueDescriptor[] var1, RowLocation var2) {
      this._columnValues = var1;
      this._rowLocation = var2;
   }

   public LocatedRow(DataValueDescriptor[] var1) {
      int var2 = var1.length - 1;
      int var3 = 0;

      for(this._columnValues = new DataValueDescriptor[var2]; var3 < var2; ++var3) {
         this._columnValues[var3] = var1[var3];
      }

      this._rowLocation = (RowLocation)var1[var3];
   }

   public DataValueDescriptor[] columnValues() {
      return this._columnValues;
   }

   public DataValueDescriptor[] flatten() {
      return flatten(this._columnValues, this._rowLocation);
   }

   public RowLocation rowLocation() {
      return this._rowLocation;
   }

   public static DataValueDescriptor[] flatten(DataValueDescriptor[] var0, RowLocation var1) {
      DataValueDescriptor[] var2 = new DataValueDescriptor[var0.length + 1];

      int var3;
      for(var3 = 0; var3 < var0.length; ++var3) {
         var2[var3] = var0[var3];
      }

      var2[var3] = var1;
      return var2;
   }
}
