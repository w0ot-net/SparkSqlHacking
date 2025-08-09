package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Vector;
import org.apache.derby.iapi.services.io.Formatable;

public class AggregatorInfoList extends Vector implements Formatable {
   public boolean hasDistinct() {
      for(AggregatorInfo var2 : this) {
         if (var2.isDistinct()) {
            return true;
         }
      }

      return false;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      int var2 = this.size();
      var1.writeInt(var2);

      for(int var3 = 0; var3 < var2; ++var3) {
         var1.writeObject(this.elementAt(var3));
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      int var2 = var1.readInt();
      this.ensureCapacity(var2);

      for(int var3 = 0; var3 < var2; ++var3) {
         AggregatorInfo var4 = (AggregatorInfo)var1.readObject();
         this.addElement(var4);
      }

   }

   public int getTypeFormatId() {
      return 224;
   }
}
