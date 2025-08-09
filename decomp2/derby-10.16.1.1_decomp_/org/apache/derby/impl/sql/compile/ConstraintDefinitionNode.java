package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

public class ConstraintDefinitionNode extends TableElementNode {
   private TableName constraintName;
   protected int constraintType;
   protected Properties properties;
   ProviderList apl;
   UUIDFactory uuidFactory;
   String backingIndexName;
   UUID backingIndexUUID;
   ResultColumnList columnList;
   String constraintText;
   ValueNode checkCondition;
   private int behavior;
   private int verifyType;
   public static final boolean DEFERRABLE_DEFAULT = false;
   public static final boolean INITIALLY_DEFERRED_DEFAULT = false;
   public static final boolean ENFORCED_DEFAULT = true;
   private boolean[] characteristics;

   ConstraintDefinitionNode(TableName var1, int var2, ResultColumnList var3, Properties var4, ValueNode var5, String var6, int var7, int var8, ContextManager var9) {
      super((String)null, var9);
      this.constraintName = var1;
      if (this.constraintName != null) {
         this.name = this.constraintName.getTableName();
      }

      this.constraintType = var2;
      this.properties = var4;
      this.columnList = var3;
      this.checkCondition = var5;
      this.constraintText = var6;
      this.behavior = var7;
      this.verifyType = var8;
   }

   void setCharacteristics(boolean[] var1) {
      this.characteristics = (boolean[])(([Z)var1).clone();
   }

   boolean[] getCharacteristics() {
      if (this.characteristics == null) {
         this.characteristics = new boolean[]{false, false, true};
      }

      return (boolean[])this.characteristics.clone();
   }

   public String toString() {
      return "";
   }

   void bind(DDLStatementNode var1, DataDictionary var2) throws StandardException {
      if (this.constraintType != 5) {
         if (this.constraintName != null) {
            String var3 = this.constraintName.getSchemaName();
            if (var3 != null) {
               TableName var4 = var1.getObjectName();
               String var5 = var4.getSchemaName();
               if (var5 == null) {
                  var5 = this.getSchemaDescriptor((String)null).getSchemaName();
                  var4.setSchemaName(var5);
               }

               if (!var3.equals(var5)) {
                  throw StandardException.newException("42X85", new Object[]{this.constraintName, var4});
               }
            }
         } else {
            this.name = this.getBackingIndexName(var2);
         }

      }
   }

   String getConstraintMoniker() {
      return this.name;
   }

   String getDropSchemaName() {
      return this.constraintName != null ? this.constraintName.getSchemaName() : null;
   }

   UUID getBackingIndexUUID() {
      if (this.backingIndexUUID == null) {
         this.backingIndexUUID = this.getUUIDFactory().createUUID();
      }

      return this.backingIndexUUID;
   }

   String getBackingIndexName(DataDictionary var1) {
      if (this.backingIndexName == null) {
         this.backingIndexName = var1.getSystemSQLName();
      }

      return this.backingIndexName;
   }

   void setAuxiliaryProviderList(ProviderList var1) {
      this.apl = var1;
   }

   ProviderList getAuxiliaryProviderList() {
      return this.apl;
   }

   boolean hasPrimaryKeyConstraint() {
      return this.constraintType == 2;
   }

   boolean hasUniqueKeyConstraint() {
      return this.constraintType == 3;
   }

   boolean hasForeignKeyConstraint() {
      return this.constraintType == 6;
   }

   boolean hasCheckConstraint() {
      return this.constraintType == 4;
   }

   boolean hasConstraint() {
      return true;
   }

   boolean requiresBackingIndex() {
      switch (this.constraintType) {
         case 2:
         case 3:
         case 6:
            return true;
         case 4:
         case 5:
         default:
            return false;
      }
   }

   boolean requiresUniqueIndex() {
      switch (this.constraintType) {
         case 2:
         case 3:
            return true;
         default:
            return false;
      }
   }

   int getConstraintType() {
      return this.constraintType;
   }

   void setProperties(Properties var1) {
      this.properties = var1;
   }

   Properties getProperties() {
      return this.properties;
   }

   boolean isReferenced() {
      return false;
   }

   int getReferenceCount() {
      return 0;
   }

   boolean isEnabled() {
      return true;
   }

   ResultColumnList getColumnList() {
      return this.columnList;
   }

   void setColumnList(ResultColumnList var1) {
      this.columnList = var1;
   }

   ValueNode getCheckCondition() {
      return this.checkCondition;
   }

   void setCheckCondition(ValueNode var1) {
      this.checkCondition = var1;
   }

   String getConstraintText() {
      return this.constraintText;
   }

   int getDropBehavior() {
      return this.behavior;
   }

   int getVerifyType() {
      return this.verifyType;
   }

   private UUIDFactory getUUIDFactory() {
      if (this.uuidFactory == null) {
         this.uuidFactory = getMonitor().getUUIDFactory();
      }

      return this.uuidFactory;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.constraintName != null) {
         this.constraintName = (TableName)this.constraintName.accept(var1);
      }

   }

   void qualifyNames() throws StandardException {
      OffsetOrderVisitor var1 = new OffsetOrderVisitor(TableName.class, this.checkCondition.getBeginOffset(), this.checkCondition.getEndOffset() + 1);
      this.checkCondition.accept(var1);
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;
      int var4 = this.checkCondition.getBeginOffset();

      for(TableName var6 : var1.getNodes()) {
         var2.append(this.constraintText, var3, var6.getBeginOffset() - var4);
         var2.append(var6.getFullSQLName());
         var3 = var6.getEndOffset() + 1 - var4;
      }

      var2.append(this.constraintText, var3, this.constraintText.length());
      this.constraintText = var2.toString();
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
