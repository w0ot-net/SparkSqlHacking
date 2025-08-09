package org.datanucleus.store.fieldmanager;

import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.state.ObjectProvider;

public abstract class AbstractStoreFieldManager extends AbstractFieldManager {
   protected ExecutionContext ec;
   protected ObjectProvider op;
   protected AbstractClassMetaData cmd;
   protected boolean insert;

   public AbstractStoreFieldManager(ExecutionContext ec, AbstractClassMetaData cmd, boolean insert) {
      this.ec = ec;
      this.cmd = cmd;
      this.insert = insert;
   }

   public AbstractStoreFieldManager(ObjectProvider op, boolean insert) {
      this.ec = op.getExecutionContext();
      this.op = op;
      this.cmd = op.getClassMetaData();
      this.insert = insert;
   }

   protected boolean isStorable(int fieldNumber) {
      AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      return this.isStorable(mmd);
   }

   protected boolean isStorable(AbstractMemberMetaData mmd) {
      if (mmd.getPersistenceModifier() != FieldPersistenceModifier.PERSISTENT) {
         return false;
      } else {
         return this.insert && mmd.isInsertable() || !this.insert && mmd.isUpdateable();
      }
   }
}
