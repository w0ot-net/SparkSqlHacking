package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.store.access.ColumnOrdering;

public class IndexColumnOrder implements ColumnOrdering, Formatable {
   int colNum;
   boolean ascending;
   boolean nullsOrderedLow;

   public IndexColumnOrder() {
   }

   public IndexColumnOrder(int var1) {
      this.colNum = var1;
      this.ascending = true;
      this.nullsOrderedLow = false;
   }

   public IndexColumnOrder(int var1, boolean var2) {
      this.colNum = var1;
      this.ascending = var2;
      this.nullsOrderedLow = false;
   }

   public IndexColumnOrder(int var1, boolean var2, boolean var3) {
      this.colNum = var1;
      this.ascending = var2;
      this.nullsOrderedLow = var3;
   }

   public int getColumnId() {
      return this.colNum;
   }

   public boolean getIsAscending() {
      return this.ascending;
   }

   public boolean getIsNullsOrderedLow() {
      return this.nullsOrderedLow;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.colNum);
      var1.writeBoolean(this.ascending);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.colNum = var1.readInt();
      this.ascending = var1.readBoolean();
   }

   public int getTypeFormatId() {
      return 218;
   }

   public String toString() {
      return super.toString();
   }
}
