package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class SequenceDescriptor extends PrivilegedSQLObject implements Provider, Dependent {
   private UUID sequenceUUID;
   private String sequenceName;
   private final SchemaDescriptor schemaDescriptor;
   private UUID schemaId;
   private DataTypeDescriptor dataType;
   private Long currentValue;
   private long startValue;
   private long minimumValue;
   private long maximumValue;
   private long increment;
   private boolean canCycle;

   public SequenceDescriptor(DataDictionary var1, SchemaDescriptor var2, UUID var3, String var4, DataTypeDescriptor var5, Long var6, long var7, long var9, long var11, long var13, boolean var15) {
      super(var1);
      this.sequenceUUID = var3;
      this.schemaDescriptor = var2;
      this.sequenceName = var4;
      this.schemaId = var2.getUUID();
      this.dataType = var5;
      this.currentValue = var6;
      this.startValue = var7;
      this.minimumValue = var9;
      this.maximumValue = var11;
      this.increment = var13;
      this.canCycle = var15;
   }

   public UUID getUUID() {
      return this.sequenceUUID;
   }

   public String getObjectTypeName() {
      return "SEQUENCE";
   }

   public String toString() {
      return "";
   }

   public void drop(LanguageConnectionContext var1) throws StandardException {
      DataDictionary var2 = this.getDataDictionary();
      DependencyManager var3 = this.getDataDictionary().getDependencyManager();
      TransactionController var4 = var1.getTransactionExecute();
      var3.invalidateFor(this, 49, var1);
      var2.dropSequenceDescriptor(this, var4);
      var3.clearDependencies(var1, this);
   }

   public synchronized boolean isValid() {
      return true;
   }

   public void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      switch (var2) {
         default ->       }
   }

   public void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
      switch (var1) {
         case 14:
            DependencyManager var3 = this.getDataDictionary().getDependencyManager();
            var3.invalidateFor(this, 11, var2);
         default:
      }
   }

   public String getName() {
      return this.sequenceName;
   }

   public SchemaDescriptor getSchemaDescriptor() throws StandardException {
      return this.schemaDescriptor;
   }

   public String getDescriptorType() {
      return "Sequence";
   }

   public String getDescriptorName() {
      return this.sequenceName;
   }

   public UUID getObjectID() {
      return this.sequenceUUID;
   }

   public boolean isPersistent() {
      return true;
   }

   public String getObjectName() {
      return this.sequenceName;
   }

   public String getClassType() {
      return "Sequence";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(472);
   }

   public String getSequenceName() {
      return this.sequenceName;
   }

   public UUID getSchemaId() {
      return this.schemaId;
   }

   public DataTypeDescriptor getDataType() {
      return this.dataType;
   }

   public Long getCurrentValue() {
      return this.currentValue;
   }

   public long getStartValue() {
      return this.startValue;
   }

   public long getMinimumValue() {
      return this.minimumValue;
   }

   public long getMaximumValue() {
      return this.maximumValue;
   }

   public long getIncrement() {
      return this.increment;
   }

   public boolean canCycle() {
      return this.canCycle;
   }
}
