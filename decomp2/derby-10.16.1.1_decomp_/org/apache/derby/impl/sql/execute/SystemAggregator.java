package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.sql.execute.ExecAggregator;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

abstract class SystemAggregator implements ExecAggregator {
   private boolean eliminatedNulls;

   public boolean didEliminateNulls() {
      return this.eliminatedNulls;
   }

   public void accumulate(DataValueDescriptor var1, Object var2) throws StandardException {
      if (var1 != null && !var1.isNull()) {
         this.accumulate(var1);
      } else {
         this.eliminatedNulls = true;
      }
   }

   protected abstract void accumulate(DataValueDescriptor var1) throws StandardException;

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeBoolean(this.eliminatedNulls);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.eliminatedNulls = var1.readBoolean();
   }

   public String toString() {
      try {
         String var10000 = super.toString();
         return var10000 + "[" + this.getResult().getString() + "]";
      } catch (Exception var2) {
         return var2.getMessage();
      }
   }
}
