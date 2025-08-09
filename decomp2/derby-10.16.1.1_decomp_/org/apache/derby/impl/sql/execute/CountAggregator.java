package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.execute.ExecAggregator;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.shared.common.error.StandardException;

public final class CountAggregator extends SystemAggregator {
   private long value;
   private boolean isCountStar;

   public void setup(ClassFactory var1, String var2, DataTypeDescriptor var3) {
      this.isCountStar = var2.equals("COUNT(*)");
   }

   public void merge(ExecAggregator var1) throws StandardException {
      this.value += ((CountAggregator)var1).value;
   }

   public DataValueDescriptor getResult() {
      return new SQLLongint(this.value);
   }

   public void accumulate(DataValueDescriptor var1, Object var2) throws StandardException {
      if (this.isCountStar) {
         ++this.value;
      } else {
         super.accumulate(var1, var2);
      }

   }

   protected final void accumulate(DataValueDescriptor var1) {
      ++this.value;
   }

   public ExecAggregator newAggregator() {
      CountAggregator var1 = new CountAggregator();
      var1.isCountStar = this.isCountStar;
      return var1;
   }

   public boolean isCountStar() {
      return this.isCountStar;
   }

   public final void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      var1.writeBoolean(this.isCountStar);
      var1.writeLong(this.value);
   }

   public final void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.isCountStar = var1.readBoolean();
      this.value = var1.readLong();
   }

   public int getTypeFormatId() {
      return 151;
   }
}
