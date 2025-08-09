package org.datanucleus.store.rdbms.request;

import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.key.PrimaryKey;
import org.datanucleus.store.rdbms.table.AbstractClassTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;

public abstract class Request {
   protected DatastoreClass table;
   protected PrimaryKey key;

   public Request(DatastoreClass table) {
      this.table = table;
      this.key = ((AbstractClassTable)table).getPrimaryKey();
   }

   public abstract void execute(ObjectProvider var1);
}
