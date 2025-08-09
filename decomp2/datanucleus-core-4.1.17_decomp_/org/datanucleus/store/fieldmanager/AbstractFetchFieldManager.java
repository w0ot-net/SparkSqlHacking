package org.datanucleus.store.fieldmanager;

import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.state.ObjectProvider;

public abstract class AbstractFetchFieldManager extends AbstractFieldManager {
   protected ExecutionContext ec;
   protected ObjectProvider op;
   protected AbstractClassMetaData cmd;

   public AbstractFetchFieldManager(ObjectProvider op) {
      this.op = op;
      this.ec = op.getExecutionContext();
      this.cmd = op.getClassMetaData();
   }

   public AbstractFetchFieldManager(ExecutionContext ec, AbstractClassMetaData cmd) {
      this.ec = ec;
      this.cmd = cmd;
   }
}
