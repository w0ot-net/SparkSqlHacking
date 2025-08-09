package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.sql.execute.ExecAggregator;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.iapi.types.SQLDecimal;
import org.apache.derby.iapi.types.SQLDouble;
import org.apache.derby.iapi.types.SQLInteger;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.shared.common.error.StandardException;

public final class AvgAggregator extends SumAggregator {
   private long count;
   private int scale;

   protected void accumulate(DataValueDescriptor var1) throws StandardException {
      if (this.count == 0L) {
         String var2 = var1.getTypeName();
         if (!var2.equals("TINYINT") && !var2.equals("SMALLINT") && !var2.equals("INTEGER") && !var2.equals("BIGINT")) {
            if (!var2.equals("REAL") && !var2.equals("DOUBLE")) {
               this.scale = ((SQLDecimal)var1).getDecimalValueScale();
               if (this.scale < 4) {
                  this.scale = 4;
               }
            } else {
               this.scale = 31;
            }
         } else {
            this.scale = 0;
         }
      }

      try {
         super.accumulate(var1);
         ++this.count;
      } catch (StandardException var4) {
         if (!var4.getMessageId().equals("22003")) {
            throw var4;
         } else {
            String var5 = this.value.getTypeName();
            Object var3;
            if (var5.equals("INTEGER")) {
               var3 = new SQLLongint();
            } else if (!var5.equals("TINYINT") && !var5.equals("SMALLINT")) {
               if (var5.equals("REAL")) {
                  var3 = new SQLDouble();
               } else {
                  var3 = new SQLDecimal();
               }
            } else {
               var3 = new SQLInteger();
            }

            ((DataValueDescriptor)var3).setValue(this.value);
            this.value = (DataValueDescriptor)var3;
            this.accumulate(var1);
         }
      }
   }

   public void merge(ExecAggregator var1) throws StandardException {
      AvgAggregator var2 = (AvgAggregator)var1;
      if (this.count == 0L) {
         this.count = var2.count;
         this.value = var2.value;
         this.scale = var2.scale;
      } else {
         if (var2.value != null) {
            this.count += var2.count - 1L;
            this.accumulate(var2.value);
         }

      }
   }

   public DataValueDescriptor getResult() throws StandardException {
      if (this.count == 0L) {
         return null;
      } else {
         NumberDataValue var1 = (NumberDataValue)this.value;
         NumberDataValue var2 = (NumberDataValue)this.value.getNewNull();
         if (this.count > 2147483647L) {
            String var3 = var1.getTypeName();
            if (var3.equals("INTEGER") || var3.equals("TINYINT") || var3.equals("SMALLINT")) {
               var2.setValue(0);
               return var2;
            }
         }

         SQLLongint var4 = new SQLLongint(this.count);
         var1.divide(var1, var4, var2, this.scale);
         return var2;
      }
   }

   public ExecAggregator newAggregator() {
      return new AvgAggregator();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      var1.writeLong(this.count);
      var1.writeInt(this.scale);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.count = var1.readLong();
      this.scale = var1.readInt();
   }

   public int getTypeFormatId() {
      return 149;
   }
}
