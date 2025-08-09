package org.apache.derby.iapi.sql.dictionary;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.Statement;
import org.apache.derby.iapi.sql.StorablePreparedStatement;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.LanguageConnectionFactory;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class SPSDescriptor extends UniqueSQLObjectDescriptor implements Dependent, Provider {
   public static final char SPS_TYPE_TRIGGER = 'T';
   public static final char SPS_TYPE_REGULAR = 'S';
   public static final char SPS_TYPE_EXPLAIN = 'X';
   private static final int RECOMPILE = 1;
   private static final int INVALIDATE = 0;
   private final SchemaDescriptor sd;
   private final String name;
   private final UUID compSchemaId;
   private final char type;
   private String text;
   private final String usingText;
   private final UUID uuid;
   private boolean valid;
   private ExecPreparedStatement preparedStatement;
   private DataTypeDescriptor[] params;
   private Timestamp compileTime;
   private Object[] paramDefaults;
   private final boolean initiallyCompilable;
   private boolean lookedUpParams;
   private UUIDFactory uuidFactory;

   public SPSDescriptor(DataDictionary var1, String var2, UUID var3, UUID var4, UUID var5, char var6, boolean var7, String var8, boolean var9) throws StandardException {
      this(var1, var2, var3, var4, var5, var6, var7, var8, (String)null, (Timestamp)null, (ExecPreparedStatement)null, var9);
   }

   public SPSDescriptor(DataDictionary var1, String var2, UUID var3, UUID var4, UUID var5, char var6, boolean var7, String var8, String var9, Timestamp var10, ExecPreparedStatement var11, boolean var12) throws StandardException {
      super(var1);
      if (var3 == null) {
         throw new IllegalArgumentException("UUID is null");
      } else {
         this.name = var2;
         this.uuid = var3;
         this.type = var6;
         this.text = var8;
         this.usingText = var9;
         this.valid = var7;
         this.compileTime = DataTypeUtilities.clone(var10);
         this.sd = var1.getSchemaDescriptor(var4, (TransactionController)null);
         this.preparedStatement = var11;
         this.compSchemaId = var5;
         this.initiallyCompilable = var12;
      }
   }

   public final synchronized void prepareAndRelease(LanguageConnectionContext var1, TableDescriptor var2, TransactionController var3) throws StandardException {
      this.compileStatement(var1, var2, var3);
      this.preparedStatement.makeInvalid(11, var1);
   }

   public final synchronized void prepareAndRelease(LanguageConnectionContext var1, TableDescriptor var2) throws StandardException {
      this.prepareAndRelease(var1, var2, (TransactionController)null);
   }

   public final synchronized void prepareAndRelease(LanguageConnectionContext var1) throws StandardException {
      this.prepareAndRelease(var1, (TableDescriptor)null, (TransactionController)null);
   }

   private void compileStatement(LanguageConnectionContext var1, TableDescriptor var2, TransactionController var3) throws StandardException {
      ContextManager var4 = var1.getContextManager();
      LanguageConnectionFactory var5 = var1.getLanguageConnectionFactory();
      DataDictionary var6 = this.getDataDictionary();
      if (this.type == 'T' && var2 == null) {
         String var7 = this.name.substring(49);
         var2 = var6.getTableDescriptor(this.recreateUUID(var7));
      }

      if (var2 != null) {
         var1.pushTriggerTable(var2);
      }

      Statement var11 = var5.getStatement(var6.getSchemaDescriptor(this.compSchemaId, (TransactionController)null), this.text, true);

      try {
         this.preparedStatement = (ExecPreparedStatement)var11.prepareStorable(var1, this.preparedStatement, this.getParameterDefaults(), this.getSchemaDescriptor(), this.type == 'T');
      } finally {
         if (var2 != null) {
            var1.popTriggerTable(var2);
         }

      }

      if (this.preparedStatement.referencesSessionSchema()) {
         throw StandardException.newException("XCL51.S", new Object[0]);
      } else {
         this.setCompileTime();
         this.setParams(this.preparedStatement.getParameterTypes());
         if (!var6.isReadOnlyUpgrade()) {
            var6.startWriting(var1);
            DependencyManager var8 = var6.getDependencyManager();
            var8.clearDependencies(var1, this, var3);
            var8.copyDependencies(this.preparedStatement, this, false, var4, var3);
            if (var2 != null) {
               var8.addDependency(this, var2, var1.getContextManager());
            }
         }

         this.valid = true;
      }
   }

   public final String getName() {
      return this.name;
   }

   public final String getQualifiedName() {
      String var10000 = this.sd.getSchemaName();
      return var10000 + "." + this.name;
   }

   public final SchemaDescriptor getSchemaDescriptor() {
      return this.sd;
   }

   public final char getType() {
      return this.type;
   }

   public final String getTypeAsString() {
      return String.valueOf(this.type);
   }

   public boolean initiallyCompilable() {
      return this.initiallyCompilable;
   }

   public static boolean validType(char var0) {
      return var0 == 'S' || var0 == 'T';
   }

   public final synchronized Timestamp getCompileTime() {
      return DataTypeUtilities.clone(this.compileTime);
   }

   public final synchronized void setCompileTime() {
      this.compileTime = new Timestamp(System.currentTimeMillis());
   }

   public final synchronized String getText() {
      return this.text;
   }

   public final synchronized void setText(String var1) {
      this.text = var1;
   }

   public final String getUsingText() {
      return this.usingText;
   }

   public final UUID getUUID() {
      return this.uuid;
   }

   public final synchronized DataTypeDescriptor[] getParams() throws StandardException {
      if (this.params == null && !this.lookedUpParams) {
         ArrayList var1 = new ArrayList();
         this.params = this.getDataDictionary().getSPSParams(this, var1);
         this.paramDefaults = var1.toArray();
         this.lookedUpParams = true;
      }

      return (DataTypeDescriptor[])ArrayUtil.copy(this.params);
   }

   public final synchronized void setParams(DataTypeDescriptor[] var1) {
      this.params = (DataTypeDescriptor[])ArrayUtil.copy(var1);
   }

   public final synchronized Object[] getParameterDefaults() throws StandardException {
      if (this.paramDefaults == null) {
         this.getParams();
      }

      return ArrayUtil.copy(this.paramDefaults);
   }

   public final synchronized void setParameterDefaults(Object[] var1) {
      this.paramDefaults = ArrayUtil.copy(var1);
   }

   public final ExecPreparedStatement getPreparedStatement() throws StandardException {
      return this.getPreparedStatement(true);
   }

   public final synchronized ExecPreparedStatement getPreparedStatement(boolean var1) throws StandardException {
      if (var1 && (!this.valid || this.preparedStatement == null)) {
         ContextManager var2 = getContextService().getCurrentContextManager();
         LanguageConnectionContext var3 = (LanguageConnectionContext)var2.getContext("LanguageConnectionContext");
         if (!var3.getDataDictionary().isReadOnlyUpgrade()) {
            String var4 = var3.getUniqueSavepointName();

            TransactionController var5;
            try {
               var5 = var3.getTransactionCompile().startNestedUserTransaction(false, true);
               var5.setNoLockWait(true);
               var5.setSavePoint(var4, (Object)null);
            } catch (StandardException var11) {
               var5 = null;
            }

            try {
               this.prepareAndRelease(var3, (TableDescriptor)null, var5);
               this.updateSYSSTATEMENTS(var3, 1, var5);
            } catch (StandardException var12) {
               if (var5 != null) {
                  var5.rollbackToSavePoint(var4, false, (Object)null);
               }

               if (var5 == null || !var12.isLockTimeout() && !var12.isSelfDeadlock()) {
                  throw var12;
               }

               var5.commit();
               var5.destroy();
               var5 = null;
               this.prepareAndRelease(var3, (TableDescriptor)null, (TransactionController)null);
               this.updateSYSSTATEMENTS(var3, 1, (TransactionController)null);
            } finally {
               if (var5 != null) {
                  var5.commit();
                  var5.destroy();
               }

            }
         }
      }

      return this.preparedStatement;
   }

   public final UUID getCompSchemaId() {
      return this.compSchemaId;
   }

   public final String toString() {
      return "";
   }

   public final DependableFinder getDependableFinder() {
      return this.getDependableFinder(226);
   }

   public final String getObjectName() {
      return this.name;
   }

   public final UUID getObjectID() {
      return this.uuid;
   }

   public final String getClassType() {
      return "StoredPreparedStatement";
   }

   public final synchronized boolean isValid() {
      return this.valid;
   }

   public final void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      switch (var2) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 9:
         case 10:
         case 11:
         case 12:
         case 14:
         case 15:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 27:
         case 28:
         case 29:
         case 30:
         case 33:
         case 34:
         case 37:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 46:
            return;
         case 7:
         case 8:
         case 13:
         case 16:
         case 17:
         case 18:
         case 24:
         case 25:
         case 26:
         case 31:
         case 32:
         case 35:
         case 36:
         case 38:
         case 44:
         case 45:
         default:
            DependencyManager var4 = this.getDataDictionary().getDependencyManager();
            throw StandardException.newException("X0Y24.S", new Object[]{var4.getActionString(var2), var1.getObjectName(), this.name});
      }
   }

   public final synchronized void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
      DependencyManager var3 = this.getDataDictionary().getDependencyManager();
      switch (var1) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 9:
         case 12:
         case 14:
         case 15:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 27:
         case 28:
         case 29:
         case 30:
         case 33:
         case 34:
         case 37:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 46:
            if (this.valid) {
               this.valid = false;
               this.preparedStatement = null;
               this.updateSYSSTATEMENTS(var2, 0, (TransactionController)null);
            }

            var3.invalidateFor(this, 14, var2);
         case 7:
         case 8:
         case 10:
         case 11:
         case 16:
         case 17:
         case 18:
         case 24:
         case 25:
         case 26:
         case 31:
         case 32:
         case 35:
         case 36:
         case 38:
         case 44:
         case 45:
         default:
            break;
         case 13:
            var3.clearDependencies(var2, this);
      }

   }

   public final synchronized void revalidate(LanguageConnectionContext var1) throws StandardException {
      this.valid = false;
      this.makeInvalid(14, var1);
      this.prepareAndRelease(var1);
      this.updateSYSSTATEMENTS(var1, 1, (TransactionController)null);
   }

   public void loadGeneratedClass() throws StandardException {
      if (this.preparedStatement != null) {
         ((StorablePreparedStatement)this.preparedStatement).loadGeneratedClass();
      }

   }

   private void updateSYSSTATEMENTS(LanguageConnectionContext var1, int var2, TransactionController var3) throws StandardException {
      DataDictionary var4 = this.getDataDictionary();
      if (!var4.isReadOnlyUpgrade()) {
         var4.startWriting(var1);
         if (var3 == null) {
            var3 = var1.getTransactionExecute();
         }

         var4.updateSPS(this, var3, var2 == 1);
      }
   }

   private UUID recreateUUID(String var1) {
      if (this.uuidFactory == null) {
         this.uuidFactory = DataDescriptorGenerator.getMonitor().getUUIDFactory();
      }

      return this.uuidFactory.recreateUUID(var1);
   }

   public String getDescriptorType() {
      return "Statement";
   }

   public String getDescriptorName() {
      return this.name;
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }
}
