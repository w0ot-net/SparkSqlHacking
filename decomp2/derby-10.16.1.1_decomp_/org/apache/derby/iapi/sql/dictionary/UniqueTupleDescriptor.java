package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;

public abstract class UniqueTupleDescriptor extends TupleDescriptor {
   public UniqueTupleDescriptor() {
   }

   public UniqueTupleDescriptor(DataDictionary var1) {
      super(var1);
   }

   public abstract UUID getUUID();
}
