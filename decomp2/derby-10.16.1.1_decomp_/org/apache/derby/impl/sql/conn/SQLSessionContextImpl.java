package org.apache.derby.impl.sql.conn;

import java.util.HashMap;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.conn.SQLSessionContext;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;

public class SQLSessionContextImpl implements SQLSessionContext {
   private String currentUser;
   private String currentRole = null;
   private SchemaDescriptor currentDefaultSchema;
   private HashMap constraintModes;
   private Boolean deferredAll;

   public SQLSessionContextImpl(SchemaDescriptor var1, String var2) {
      this.currentDefaultSchema = var1;
      this.currentUser = var2;
   }

   public void setRole(String var1) {
      this.currentRole = var1;
   }

   public String getRole() {
      return this.currentRole;
   }

   public void setUser(String var1) {
      this.currentUser = var1;
   }

   public String getCurrentUser() {
      return this.currentUser;
   }

   public void setDefaultSchema(SchemaDescriptor var1) {
      this.currentDefaultSchema = var1;
   }

   public SchemaDescriptor getDefaultSchema() {
      return this.currentDefaultSchema;
   }

   public HashMap getConstraintModes() {
      return this.constraintModes != null ? new HashMap(this.constraintModes) : null;
   }

   public void setConstraintModes(HashMap var1) {
      this.constraintModes = var1 != null ? new HashMap(var1) : null;
   }

   public void setDeferred(UUID var1, boolean var2) {
      if (this.constraintModes == null) {
         this.constraintModes = new HashMap();
      }

      this.constraintModes.put(var1, var2);
   }

   public Boolean isDeferred(UUID var1) {
      Boolean var2 = null;
      if (this.constraintModes != null) {
         var2 = (Boolean)this.constraintModes.get(var1);
      }

      return var2 != null ? var2 : this.deferredAll;
   }

   public void resetConstraintModes() {
      if (this.constraintModes != null) {
         this.constraintModes.clear();
      }

      this.deferredAll = null;
   }

   public void setDeferredAll(Boolean var1) {
      this.deferredAll = var1;
      if (this.constraintModes != null) {
         this.constraintModes.clear();
      }

   }

   public Boolean getDeferredAll() {
      return this.deferredAll;
   }
}
