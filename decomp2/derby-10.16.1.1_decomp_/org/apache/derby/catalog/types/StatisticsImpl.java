package org.apache.derby.catalog.types;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.Statistics;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.FormatableHashtable;

public class StatisticsImpl implements Statistics, Formatable {
   private long numRows;
   private long numUnique;

   public StatisticsImpl(long var1, long var3) {
      this.numRows = var1;
      this.numUnique = var3;
   }

   public StatisticsImpl() {
   }

   public long getRowEstimate() {
      return this.numRows;
   }

   public double selectivity(Object[] var1) {
      return (double)this.numRows == (double)0.0F ? 0.1 : (double)1.0F / (double)this.numUnique;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      FormatableHashtable var2 = (FormatableHashtable)var1.readObject();
      this.numRows = var2.getLong("numRows");
      this.numUnique = var2.getLong("numUnique");
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      FormatableHashtable var2 = new FormatableHashtable();
      var2.putLong("numRows", this.numRows);
      var2.putLong("numUnique", this.numUnique);
      var1.writeObject(var2);
   }

   public int getTypeFormatId() {
      return 397;
   }

   public String toString() {
      return "numunique= " + this.numUnique + " numrows= " + this.numRows;
   }
}
