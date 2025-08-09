package org.apache.derby.impl.sql.conn;

import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.impl.sql.GenericPreparedStatement;
import org.apache.derby.impl.sql.GenericStatement;

public class CachedStatement implements Cacheable {
   private GenericPreparedStatement ps;
   private Object identity;

   public GenericPreparedStatement getPreparedStatement() {
      return this.ps;
   }

   public void clean(boolean var1) {
   }

   public Cacheable setIdentity(Object var1) {
      this.identity = var1;
      this.ps = new GenericPreparedStatement((GenericStatement)var1);
      this.ps.setCacheHolder(this);
      return this;
   }

   public Cacheable createIdentity(Object var1, Object var2) {
      return null;
   }

   public void clearIdentity() {
      this.ps.setCacheHolder((Cacheable)null);
      this.identity = null;
      this.ps = null;
   }

   public Object getIdentity() {
      return this.identity;
   }

   public boolean isDirty() {
      return false;
   }
}
