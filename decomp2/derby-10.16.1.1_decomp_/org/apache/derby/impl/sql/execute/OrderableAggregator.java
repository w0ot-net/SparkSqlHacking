package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.execute.ExecAggregator;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

abstract class OrderableAggregator extends SystemAggregator {
   protected DataValueDescriptor value;

   public void setup(ClassFactory var1, String var2, DataTypeDescriptor var3) {
   }

   public void merge(ExecAggregator var1) throws StandardException {
      DataValueDescriptor var2 = ((OrderableAggregator)var1).value;
      if (var2 != null) {
         this.accumulate(var2);
      }

   }

   public DataValueDescriptor getResult() throws StandardException {
      return this.value;
   }

   public String toString() {
      try {
         return "OrderableAggregator: " + this.value.getString();
      } catch (StandardException var2) {
         String var10000 = super.toString();
         return var10000 + ":" + var2.getMessage();
      }
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      var1.writeObject(this.value);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.value = (DataValueDescriptor)var1.readObject();
   }
}
