package org.apache.derby.iapi.sql.depend;

import java.util.Hashtable;

public class ProviderList extends Hashtable {
   public void addProvider(Provider var1) {
      this.put(var1.getObjectID(), var1);
   }
}
