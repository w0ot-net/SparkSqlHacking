package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptorList;
import org.apache.derby.shared.common.util.ArrayUtil;

public final class TriggerInfo implements Formatable {
   TriggerDescriptor[] triggerArray;

   public TriggerInfo() {
   }

   public TriggerInfo(TriggerDescriptorList var1) {
      this.triggerArray = (TriggerDescriptor[])var1.toArray(new TriggerDescriptor[var1.size()]);
   }

   boolean hasTrigger(boolean var1, boolean var2) {
      if (this.triggerArray == null) {
         return false;
      } else {
         for(int var3 = 0; var3 < this.triggerArray.length; ++var3) {
            if (this.triggerArray[var3].isBeforeTrigger() == var1 && this.triggerArray[var3].isRowTrigger() == var2) {
               return true;
            }
         }

         return false;
      }
   }

   TriggerDescriptor[] getTriggerArray() {
      return this.triggerArray;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      ArrayUtil.writeArray(var1, this.triggerArray);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.triggerArray = new TriggerDescriptor[ArrayUtil.readArrayLength(var1)];
      ArrayUtil.readArrayItems(var1, this.triggerArray);
   }

   public int getTypeFormatId() {
      return 317;
   }

   public String toString() {
      return "";
   }
}
