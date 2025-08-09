package org.datanucleus.flush;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;

public class UpdateMemberOperation implements Operation {
   final ObjectProvider op;
   final int fieldNumber;
   Object oldValue;
   Object newValue;

   public UpdateMemberOperation(ObjectProvider op, int fieldNum, Object newVal, Object oldVal) {
      this.op = op;
      this.fieldNumber = fieldNum;
      this.newValue = newVal;
      this.oldValue = oldVal;
   }

   public Object getNewValue() {
      return this.newValue;
   }

   public Object getOldValue() {
      return this.oldValue;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(this.fieldNumber);
   }

   public ObjectProvider getObjectProvider() {
      return this.op;
   }

   public void perform() {
   }

   public String toString() {
      return "UPDATE : " + this.op + " field=" + this.getMemberMetaData().getName();
   }
}
