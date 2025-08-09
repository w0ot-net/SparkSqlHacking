package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.shared.common.error.StandardException;

public final class DefaultDescriptor extends UniqueTupleDescriptor implements Provider, Dependent {
   private final int columnNumber;
   private final UUID defaultUUID;
   private final UUID tableUUID;

   public DefaultDescriptor(DataDictionary var1, UUID var2, UUID var3, int var4) {
      super(var1);
      this.defaultUUID = var2;
      this.tableUUID = var3;
      this.columnNumber = var4;
   }

   public UUID getUUID() {
      return this.defaultUUID;
   }

   public UUID getTableUUID() {
      return this.tableUUID;
   }

   public int getColumnNumber() {
      return this.columnNumber;
   }

   public String toString() {
      return "";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(325);
   }

   public String getObjectName() {
      return "default";
   }

   public UUID getObjectID() {
      return this.defaultUUID;
   }

   public String getClassType() {
      return "Default";
   }

   public synchronized boolean isValid() {
      return true;
   }

   public void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      DependencyManager var4 = this.getDataDictionary().getDependencyManager();
      switch (var2) {
         default:
            DataDictionary var5 = this.getDataDictionary();
            ColumnDescriptor var6 = var5.getColumnDescriptorByDefaultId(this.defaultUUID);
            TableDescriptor var7 = var5.getTableDescriptor(var6.getReferencingUUID());
            Object[] var10001 = new Object[]{var4.getActionString(var2), var1.getObjectName(), "DEFAULT", null};
            String var10004 = var7.getQualifiedName();
            var10001[3] = var10004 + "." + var6.getColumnName();
            throw StandardException.newException("X0Y25.S", var10001);
      }
   }

   public void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
   }
}
