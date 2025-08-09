package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.DefaultInfoImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public class ColumnDefinitionNode extends TableElementNode {
   boolean isAutoincrement;
   DataTypeDescriptor type;
   DataValueDescriptor defaultValue;
   DefaultInfoImpl defaultInfo;
   DefaultNode defaultNode;
   boolean keepCurrentDefault;
   GenerationClauseNode generationClauseNode;
   long autoincrementIncrement;
   long autoincrementStart;
   boolean autoincrementCycle;
   long autoinc_create_or_modify_Start_Increment;
   boolean autoincrementVerify;
   public static final int CREATE_AUTOINCREMENT = 0;
   public static final int MODIFY_AUTOINCREMENT_RESTART_VALUE = 1;
   public static final int MODIFY_AUTOINCREMENT_INC_VALUE = 2;
   public static final int MODIFY_AUTOINCREMENT_ALWAYS_VS_DEFAULT = 3;
   public static final int MODIFY_AUTOINCREMENT_CYCLE_VALUE = 4;

   ColumnDefinitionNode(String var1, ValueNode var2, DataTypeDescriptor var3, long[] var4, ContextManager var5) throws StandardException {
      super(var1, var5);
      this.type = var3;
      if (var2 instanceof UntypedNullConstantNode) {
         if (var3 != null) {
            this.defaultValue = ((UntypedNullConstantNode)var2).convertDefaultNode(this.type);
         }
      } else if (var2 instanceof GenerationClauseNode) {
         this.generationClauseNode = (GenerationClauseNode)var2;
      } else {
         this.defaultNode = (DefaultNode)var2;
         if (var4 != null) {
            this.autoincrementStart = var4[0];
            this.autoincrementIncrement = var4[1];
            this.autoincrementCycle = var4[4] == 1L;
            this.autoinc_create_or_modify_Start_Increment = var4[3];
            this.autoincrementVerify = var4[2] <= 0L;
            this.isAutoincrement = true;
            if (var3 != null) {
               this.setNullability(false);
            }
         }
      }

      this.keepCurrentDefault = var2 == null;
   }

   public String toString() {
      return "";
   }

   String getColumnName() {
      return this.name;
   }

   final DataTypeDescriptor getType() {
      return this.type;
   }

   public void setType(DataTypeDescriptor var1) {
      this.type = var1;
   }

   final void setNullability(boolean var1) {
      this.type = this.getType().getNullabilityType(var1);
   }

   void setCollationType(int var1) {
      this.type = this.getType().getCollatedType(var1, 1);
   }

   DataValueDescriptor getDefaultValue() {
      return this.defaultValue;
   }

   DefaultInfo getDefaultInfo() {
      return this.defaultInfo;
   }

   public void setDefaultInfo(DefaultInfoImpl var1) {
      this.defaultInfo = var1;
   }

   DefaultNode getDefaultNode() {
      return this.defaultNode;
   }

   public boolean hasGenerationClause() {
      return this.generationClauseNode != null;
   }

   GenerationClauseNode getGenerationClauseNode() {
      return this.generationClauseNode;
   }

   boolean isAutoincrementColumn() {
      return this.isAutoincrement;
   }

   long getAutoincrementStart() {
      return this.autoincrementStart;
   }

   long getAutoincrementIncrement() {
      return this.autoincrementIncrement;
   }

   boolean getAutoincrementCycle() {
      return this.autoincrementCycle;
   }

   long getAutoinc_create_or_modify_Start_Increment() {
      return this.autoinc_create_or_modify_Start_Increment;
   }

   void checkUserType(TableDescriptor var1) throws StandardException {
      if (!this.hasGenerationClause() || this.getType() != null) {
         if (this.getType().getTypeId().userType()) {
            this.setType(this.bindUserType(this.getType()));
            ClassInspector var3 = this.getClassFactory().getClassInspector();
            String var2 = this.getType().getTypeId().getCorrespondingJavaTypeName();
            boolean var4 = false;
            ClassNotFoundException var5 = null;

            try {
               var4 = var3.accessible(var2);
            } catch (ClassNotFoundException var7) {
               var5 = var7;
            }

            if (!var4) {
               throw StandardException.newException("42X26", var5, new Object[]{var2, this.name});
            } else {
               if (!var3.assignableTo(var2, "java.io.Serializable") && !var3.assignableTo(var2, "java.sql.SQLData")) {
                  this.getCompilerContext().addWarning(StandardException.newWarning("01J04", new Object[]{var2, this.name}));
               }

            }
         }
      }
   }

   UUID getOldDefaultUUID() {
      return null;
   }

   int getAction() {
      return 0;
   }

   void bindAndValidateDefault(DataDictionary var1, TableDescriptor var2) throws StandardException {
      if (var2 != null && !this.hasGenerationClause() && !this.getType().isNullable() && this.defaultNode == null && !this.isAutoincrement) {
         throw StandardException.newException("42601", new Object[]{this.getColumnName()});
      } else if (this.defaultNode != null) {
         if (this.defaultValue == null) {
            this.validateDefault(var1, var2);
         }
      }
   }

   void validateAutoincrement(DataDictionary var1, TableDescriptor var2, int var3) throws StandardException {
      if (this.isAutoincrement) {
         if (var3 == 3) {
            throw StandardException.newException("42995", new Object[0]);
         } else if (this.autoincrementIncrement != 0L || this.autoinc_create_or_modify_Start_Increment != 0L && this.autoinc_create_or_modify_Start_Increment != 2L) {
            int var4 = this.getType().getTypeId().getJDBCTypeId();
            switch (var4) {
               case -6 -> this.autoincrementCheckRange(-128L, 127L, "TINYINT");
               case -5 -> this.autoincrementCheckRange(Long.MIN_VALUE, Long.MAX_VALUE, "BIGINT");
               case 4 -> this.autoincrementCheckRange(-2147483648L, 2147483647L, "INTEGER");
               case 5 -> this.autoincrementCheckRange(-32768L, 32767L, "SMALLINT");
               default -> throw StandardException.newException("42Z22", new Object[]{this.getColumnName()});
            }

         } else {
            throw StandardException.newException("42Z21", new Object[]{this.getColumnName()});
         }
      }
   }

   private void autoincrementCheckRange(long var1, long var3, String var5) throws StandardException {
      if (var1 <= this.autoincrementIncrement && var3 >= this.autoincrementIncrement) {
         if (var1 > this.autoincrementStart || var3 < this.autoincrementStart) {
            throw StandardException.newException("22003", new Object[]{var5});
         }
      } else {
         throw StandardException.newException("22003", new Object[]{var5});
      }
   }

   void validateDefault(DataDictionary var1, TableDescriptor var2) throws StandardException {
      if (this.defaultNode != null) {
         if (this.isAutoincrement) {
            this.defaultInfo = createDefaultInfoOfAutoInc();
         } else {
            CompilerContext var3 = this.getCompilerContext();
            ValueNode var4 = this.defaultNode.getDefaultTree();
            int var5 = var3.getReliability();

            try {
               Object var6 = null;
               Object var7 = null;
               var3.setReliability(1192);
               var4 = var4.bindExpression(new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()), (SubqueryList)null, (List)null);
               TypeId var8 = this.getType().getTypeId();
               TypeId var9 = var4.getTypeId();
               if (!this.defaultTypeIsValid(var8, this.getType(), var9, var4, this.defaultNode.getDefaultText())) {
                  throw StandardException.newException("42894", new Object[]{this.name});
               }

               if (!this.getTypeCompiler(var8).storable(var9, this.getClassFactory())) {
                  throw StandardException.newException("42821", new Object[]{var8.getSQLTypeName(), var9.getSQLTypeName()});
               }

               this.defaultInfo = new DefaultInfoImpl(false, this.defaultNode.getDefaultText(), this.defaultValue);
            } finally {
               var3.setReliability(var5);
            }

         }
      }
   }

   protected static DefaultInfoImpl createDefaultInfoOfAutoInc() {
      return new DefaultInfoImpl(true, (String)null, (DataValueDescriptor)null);
   }

   boolean defaultTypeIsValid(TypeId var1, DataTypeDescriptor var2, TypeId var3, ValueNode var4, String var5) throws StandardException {
      int var6 = var1.getTypeFormatId();
      int var7 = var3 == null ? -1 : var3.getTypeFormatId();
      if (!var4.isConstantExpression()) {
         boolean var8 = var6 == 5 || var6 == 13 || var6 == 230;
         if (var4 instanceof SpecialFunctionNode) {
            switch (((SpecialFunctionNode)var4).kind) {
               case 2:
                  return var8 && var2.getMaximumWidth() >= 128;
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
                  return var8 && var2.getMaximumWidth() >= 8;
               default:
                  return false;
            }
         }
      }

      switch (var6) {
         case 4:
            return var4 instanceof BooleanConstantNode;
         case 5:
         case 13:
         case 230:
            return var7 == 5;
         case 6:
         case 8:
         case 10:
         case 35:
         case 36:
         case 40:
         case 440:
         case 444:
            return true;
         case 7:
            return var7 == 7;
         case 11:
            return var7 == 7 || var7 == 11;
         case 27:
         case 29:
         case 232:
            return var7 == 27;
         case 197:
            if (var7 != 197) {
               if (var7 != 11 && var7 != 7) {
                  return false;
               }

               return true;
            }

            DataTypeDescriptor var13 = var4.getTypeServices();
            int var9 = var5.length();
            int var10 = var13.getPrecision();
            int var11 = var13.getScale();

            for(byte var12 = 1; var12 <= var11 && var5.charAt(var9 - var12) == '0'; --var10) {
               --var11;
            }

            return var11 <= var2.getScale() && var10 - var11 <= var2.getPrecision() - var2.getScale();
         case 267:
            return var7 == var6;
         default:
            return false;
      }
   }

   void printSubNodes(int var1) {
   }
}
