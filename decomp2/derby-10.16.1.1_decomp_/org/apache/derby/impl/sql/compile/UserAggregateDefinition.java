package org.apache.derby.impl.sql.compile;

import org.apache.derby.catalog.types.AggregateAliasInfo;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.TypeCompilerFactory;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.JSQLType;
import org.apache.derby.shared.common.error.StandardException;

class UserAggregateDefinition implements AggregateDefinition {
   private static final int INPUT_TYPE = 0;
   private static final int RETURN_TYPE = 1;
   private static final int AGGREGATOR_TYPE = 2;
   private static final int AGGREGATOR_PARAM_COUNT = 3;
   private static final String DERBY_BYTE_ARRAY_NAME = "byte[]";
   private AliasDescriptor _alias;

   public UserAggregateDefinition(AliasDescriptor var1) {
      this._alias = var1;
   }

   public AliasDescriptor getAliasDescriptor() {
      return this._alias;
   }

   public final DataTypeDescriptor getAggregator(DataTypeDescriptor var1, StringBuffer var2) throws StandardException {
      try {
         CompilerContext var3 = (CompilerContext)QueryTreeNode.getContext("CompilerContext");
         ClassFactory var4 = var3.getClassFactory();
         TypeCompilerFactory var5 = var3.getTypeCompilerFactory();
         Class var6 = var4.loadApplicationClass("org.apache.derby.agg.Aggregator");
         Class var7 = var4.loadApplicationClass(this._alias.getJavaClassName());
         Class[][] var8 = var4.getClassInspector().getTypeBounds(var6, var7);
         if (var8 != null && var8.length == 3 && var8[0] != null && var8[1] != null) {
            Class[] var9 = var4.getClassInspector().getGenericParameterTypes(var6, var7);
            if (var9 == null) {
               var9 = new Class[3];
            }

            AggregateAliasInfo var10 = (AggregateAliasInfo)this._alias.getAliasInfo();
            DataTypeDescriptor var11 = DataTypeDescriptor.getType(var10.getForType());
            DataTypeDescriptor var12 = DataTypeDescriptor.getType(var10.getReturnType());
            Class var13 = this.getJavaClass(var4, var11);
            Class var14 = this.getJavaClass(var4, var12);
            if (!var5.getTypeCompiler(var11.getTypeId()).storable(var1.getTypeId(), var4)) {
               return null;
            } else {
               Class[] var15 = var8[0];

               for(int var16 = 0; var16 < var15.length; ++var16) {
                  this.vetCompatibility(var15[var16], var13, "42ZC6");
               }

               if (var9[0] != null) {
                  this.vetCompatibility(var9[0], var13, "42ZC6");
               }

               Class[] var19 = var8[1];

               for(int var17 = 0; var17 < var19.length; ++var17) {
                  this.vetCompatibility(var19[var17], var14, "42ZC7");
               }

               if (var9[1] != null) {
                  this.vetCompatibility(var9[1], var14, "42ZC7");
               }

               var2.append("org.apache.derby.impl.sql.execute.UserDefinedAggregator");
               return var12;
            }
         } else {
            throw StandardException.newException("42ZC4", new Object[]{this._alias.getSchemaName(), this._alias.getName(), var7.getName()});
         }
      } catch (ClassNotFoundException var18) {
         throw this.aggregatorInstantiation(var18);
      }
   }

   private void vetCompatibility(Class var1, Class var2, String var3) throws StandardException {
      if (!var1.isAssignableFrom(var2)) {
         throw StandardException.newException(var3, new Object[]{this._alias.getSchemaName(), this._alias.getName(), var2.toString(), var1.toString()});
      }
   }

   final ValueNode castInputValue(ValueNode var1, ContextManager var2) throws StandardException {
      AggregateAliasInfo var3 = (AggregateAliasInfo)this._alias.getAliasInfo();
      DataTypeDescriptor var4 = DataTypeDescriptor.getType(var3.getForType());
      DataTypeDescriptor var5 = var1.getTypeServices();
      return var4.isExactTypeAndLengthMatch(var5) ? null : StaticMethodCallNode.makeCast(var1, var4, var2);
   }

   private Class getJavaClass(ClassFactory var1, DataTypeDescriptor var2) throws StandardException, ClassNotFoundException {
      JSQLType var3 = new JSQLType(var2);
      String var4 = MethodCallNode.getObjectTypeName(var3, (TypeCompilerFactory)null);
      if ("byte[]".equals(var4)) {
         var4 = byte[].class.getName();
      }

      return var1.loadApplicationClass(var4);
   }

   private StandardException aggregatorInstantiation(Throwable var1) {
      return StandardException.newException("42ZC8", var1, new Object[]{this._alias.getJavaClassName(), this._alias.getSchemaName(), this._alias.getName(), var1.getMessage()});
   }
}
