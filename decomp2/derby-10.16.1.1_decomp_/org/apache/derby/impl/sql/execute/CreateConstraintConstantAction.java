package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.ReferencedColumns;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DDUtils;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.ForeignKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class CreateConstraintConstantAction extends ConstraintConstantAction {
   private final boolean forCreateTable;
   private String[] columnNames;
   private String constraintText;
   private ConstraintInfo otherConstraintInfo;
   private ClassFactory cf;
   private boolean enabled;
   private boolean[] characteristics;
   private ProviderInfo[] providerInfo;

   CreateConstraintConstantAction(String var1, int var2, boolean[] var3, boolean var4, String var5, UUID var6, String var7, String[] var8, IndexConstantAction var9, String var10, ConstraintInfo var11, ProviderInfo[] var12) {
      super(var1, var2, var5, var6, var7, var9);
      this.forCreateTable = var4;
      this.columnNames = var8;
      this.constraintText = var10;
      this.characteristics = (boolean[])(([Z)var3).clone();
      this.otherConstraintInfo = var11;
      this.providerInfo = var12;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      ConglomerateDescriptor var2 = null;
      Object var3 = null;
      Object var4 = null;
      TableDescriptor var5 = null;
      UUID var6 = null;
      if (this.constraintType != 1) {
         LanguageConnectionContext var9 = var1.getLanguageConnectionContext();
         DataDictionary var10 = var9.getDataDictionary();
         DependencyManager var11 = var10.getDependencyManager();
         TransactionController var12 = var9.getTransactionExecute();
         this.cf = var9.getLanguageConnectionFactory().getClassFactory();
         var10.startWriting(var9);
         SchemaDescriptor var13 = var10.getSchemaDescriptor(this.schemaName, var12, true);
         var5 = var1.getDDLTableDescriptor();
         if (var5 == null) {
            if (this.tableId != null) {
               var5 = var10.getTableDescriptor(this.tableId);
            } else {
               var5 = var10.getTableDescriptor(this.tableName, var13, var12);
            }

            if (var5 == null) {
               throw StandardException.newException("X0X05.S", new Object[]{this.tableName});
            }

            var1.setDDLTableDescriptor(var5);
         }

         UUIDFactory var14 = var10.getUUIDFactory();
         UUID var15 = var14.createUUID();
         if (this.indexAction != null) {
            String var8;
            if (this.indexAction.getIndexName() == null) {
               var8 = var14.createUUID().toString();
               this.indexAction.setIndexName(var8);
            } else {
               var8 = this.indexAction.getIndexName();
            }

            this.indexAction.setConstraintID(var15);
            this.indexAction.executeConstantAction(var1);
            ConglomerateDescriptor[] var20 = var5.getConglomerateDescriptors();

            for(int var16 = 0; var16 < var20.length; ++var16) {
               var2 = var20[var16];
               if (var2.isIndex() && var8.equals(var2.getConglomerateName())) {
                  break;
               }
            }

            var6 = var2.getUUID();
         }

         boolean[] var22 = new boolean[]{false, false, true};

         for(int var17 = 0; var17 < this.characteristics.length; ++var17) {
            if (this.characteristics[var17] != var22[var17]) {
               var10.checkVersion(230, "DEFERRED CONSTRAINTS");
               if ((this.constraintType == 1 || !this.characteristics[2]) && !PropertyUtil.getSystemProperty("derby.constraintsTesting", "false").equals("true")) {
                  throw StandardException.newException("0A000.S", new Object[]{"non-default constraint characteristics"});
               }
            }
         }

         DataDescriptorGenerator var23 = var10.getDataDescriptorGenerator();
         switch (this.constraintType) {
            case 2:
               var4 = var23.newPrimaryKeyConstraintDescriptor(var5, this.constraintName, this.characteristics[0], this.characteristics[1], this.genColumnPositions(var5, false), var15, var6, var13, this.characteristics[2], 0);
               var10.addConstraintDescriptor((ConstraintDescriptor)var4, var12);
               break;
            case 3:
               var4 = var23.newUniqueConstraintDescriptor(var5, this.constraintName, this.characteristics[0], this.characteristics[1], this.genColumnPositions(var5, false), var15, var6, var13, this.characteristics[2], 0);
               var10.addConstraintDescriptor((ConstraintDescriptor)var4, var12);
               break;
            case 4:
               var4 = var23.newCheckConstraintDescriptor(var5, this.constraintName, this.characteristics[0], this.characteristics[1], var15, this.constraintText, (ReferencedColumns)(new ReferencedColumnsDescriptorImpl(this.genColumnPositions(var5, false))), var13, this.characteristics[2]);
               var10.addConstraintDescriptor((ConstraintDescriptor)var4, var12);
               this.storeConstraintDependenciesOnPrivileges(var1, (Dependent)var4, (UUID)null, this.providerInfo);
            case 5:
            default:
               break;
            case 6:
               ReferencedKeyConstraintDescriptor var18 = DDUtils.locateReferencedConstraint(var10, var5, this.constraintName, this.columnNames, this.otherConstraintInfo);
               DDUtils.validateReferentialActions(var10, var5, this.constraintName, this.otherConstraintInfo, this.columnNames);
               var4 = var23.newForeignKeyConstraintDescriptor(var5, this.constraintName, this.characteristics[0], this.characteristics[1], this.genColumnPositions(var5, false), var15, var6, var13, var18, this.characteristics[2], this.otherConstraintInfo.getReferentialActionDeleteRule(), this.otherConstraintInfo.getReferentialActionUpdateRule());
               var10.addConstraintDescriptor((ConstraintDescriptor)var4, var12);
               if (!this.forCreateTable && var10.activeConstraint((ConstraintDescriptor)var4)) {
                  validateFKConstraint(var1, var12, var10, (ForeignKeyConstraintDescriptor)var4, var18, ((CreateIndexConstantAction)this.indexAction).getIndexTemplateRow());
               }

               var11.addDependency((Dependent)var4, var18, var9.getContextManager());
               this.storeConstraintDependenciesOnPrivileges(var1, (Dependent)var4, var18.getTableId(), this.providerInfo);
               break;
            case 7:
               throw StandardException.newException("0A000.S", new Object[]{"ALTER CONSTRAINT"});
         }

         if (this.providerInfo != null) {
            for(int var24 = 0; var24 < this.providerInfo.length; ++var24) {
               Object var19 = null;
               Provider var25 = (Provider)this.providerInfo[var24].getDependableFinder().getDependable(var10, this.providerInfo[var24].getObjectId());
               var11.addDependency((Dependent)var4, var25, var9.getContextManager());
            }
         }

         if (!this.forCreateTable) {
            var11.invalidateFor(var5, 22, var9);
         }

         if (this.constraintType == 6) {
            var11.invalidateFor(((ForeignKeyConstraintDescriptor)var4).getReferencedConstraint().getTableDescriptor(), 22, var9);
         }

         this.constraintId = var15;
      }
   }

   boolean isForeignKeyConstraint() {
      return this.constraintType == 6;
   }

   boolean isInitiallyDeferred() {
      return this.characteristics[1];
   }

   private int[] genColumnPositions(TableDescriptor var1, boolean var2) throws StandardException {
      int[] var3 = new int[this.columnNames.length];

      for(int var4 = 0; var4 < this.columnNames.length; ++var4) {
         ColumnDescriptor var5 = var1.getColumnDescriptor(this.columnNames[var4]);
         if (var5 == null) {
            throw StandardException.newException("42X14", new Object[]{this.columnNames[var4], this.tableName});
         }

         if (var2 && !var5.getType().getTypeId().orderable(this.cf)) {
            throw StandardException.newException("X0X67.S", new Object[]{var5.getType().getTypeId().getSQLTypeName()});
         }

         var3[var4] = var5.getPosition();
      }

      return var3;
   }

   String getConstraintText() {
      return this.constraintText;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append("CREATE CONSTRAINT " + this.constraintName);
      var1.append("\n=========================\n");
      if (this.columnNames == null) {
         var1.append("columnNames == null\n");
      } else {
         for(int var2 = 0; var2 < this.columnNames.length; ++var2) {
            var1.append("\n\tcol[" + var2 + "]" + this.columnNames[var2].toString());
         }
      }

      var1.append("\n");
      var1.append(this.constraintText);
      var1.append("\n");
      if (this.otherConstraintInfo != null) {
         var1.append(this.otherConstraintInfo.toString());
      }

      var1.append("\n");
      return var1.toString();
   }
}
