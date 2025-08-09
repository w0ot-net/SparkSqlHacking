package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.execute.ExecAggregator;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public final class MaxMinAggregator extends OrderableAggregator {
   private boolean isMax;

   public void setup(ClassFactory var1, String var2, DataTypeDescriptor var3) {
      super.setup(var1, var2, var3);
      this.isMax = var2.equals("MAX");
   }

   protected void accumulate(DataValueDescriptor var1) throws StandardException {
      if (this.value == null || this.isMax && this.value.compare(var1) < 0 || !this.isMax && this.value.compare(var1) > 0) {
         this.value = var1.cloneValue(false);
      }

   }

   public ExecAggregator newAggregator() {
      MaxMinAggregator var1 = new MaxMinAggregator();
      var1.isMax = this.isMax;
      return var1;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeBoolean(this.isMax);
      super.writeExternal(var1);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.isMax = var1.readBoolean();
      super.readExternal(var1);
   }

   public int getTypeFormatId() {
      return 152;
   }
}
