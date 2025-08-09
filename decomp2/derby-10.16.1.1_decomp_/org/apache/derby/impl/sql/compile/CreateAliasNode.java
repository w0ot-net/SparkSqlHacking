package org.apache.derby.impl.sql.compile;

import java.util.Arrays;
import java.util.List;
import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.types.AggregateAliasInfo;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.catalog.types.SynonymAliasInfo;
import org.apache.derby.catalog.types.UDTAliasInfo;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class CreateAliasNode extends DDLStatementNode {
   public static final int PARAMETER_ARRAY = 0;
   public static final int TABLE_NAME = 1;
   public static final int DYNAMIC_RESULT_SET_COUNT = 2;
   public static final int LANGUAGE = 3;
   public static final int EXTERNAL_NAME = 4;
   public static final int PARAMETER_STYLE = 5;
   public static final int SQL_CONTROL = 6;
   public static final int DETERMINISTIC = 7;
   public static final int NULL_ON_NULL_INPUT = 8;
   public static final int RETURN_TYPE = 9;
   public static final int ROUTINE_SECURITY_DEFINER = 10;
   public static final int VARARGS = 11;
   public static final int ROUTINE_ELEMENT_COUNT = 12;
   private static final String[] NON_RESERVED_FUNCTION_NAMES = new String[]{"ABS", "ABSVAL", "DATE", "DAY", "LCASE", "LENGTH", "MONTH", "SQRT", "TIME", "TIMESTAMP", "UCASE"};
   private static final String[] NON_RESERVED_AGGREGATES = new String[]{"COLLECT", "COUNT", "EVERY", "FUSION", "INTERSECTION", "STDDEV_POP", "STDDEV_SAMP", "VAR_POP", "VAR_SAMP"};
   public static final int AGG_FOR_TYPE = 0;
   public static final int AGG_RETURN_TYPE = 1;
   public static final int AGG_ELEMENT_COUNT = 2;
   private String javaClassName;
   private String methodName;
   private char aliasType;
   private AliasInfo aliasInfo;

   CreateAliasNode(TableName var1, Object var2, String var3, Object var4, char var5, ContextManager var6) throws StandardException {
      super(var1, var6);
      this.aliasType = var5;
      switch (this.aliasType) {
         case 'A':
            this.javaClassName = (String)var2;
            this.aliasInfo = new UDTAliasInfo();
            this.implicitCreateSchema = true;
            break;
         case 'F':
         case 'P':
            this.javaClassName = (String)var2;
            this.methodName = var3;
            Object[] var30 = var4;
            Object[] var31 = var30[0];
            int var12 = ((List)var31[0]).size();
            if (this.methodName.indexOf(40) != -1) {
               this.getDataDictionary().checkVersion(130, "EXTERNAL NAME 'class.method(<signature>)'");
            }

            String[] var13 = null;
            TypeDescriptor[] var14 = null;
            int[] var15 = null;
            if (var12 != 0) {
               var13 = new String[var12];
               var14 = new TypeDescriptor[var12];
               var15 = new int[var12];

               for(int var16 = 0; var16 < var12; ++var16) {
                  var13[var16] = (String)((List)var31[0]).get(var16);
                  var14[var16] = (TypeDescriptor)((List)var31[1]).get(var16);
                  int var17 = (Integer)((List)var31[2]).get(var16);
                  var15[var16] = var17;
                  if (!var14[var16].isUserDefinedType() && TypeId.getBuiltInTypeId(var14[var16].getJDBCTypeId()).isXMLTypeId()) {
                     throw StandardException.newException("42962", new Object[]{var13[var16]});
                  }
               }

               if (var12 > 1) {
                  String[] var32 = new String[var12];
                  System.arraycopy(var13, 0, var32, 0, var12);
                  Arrays.sort(var32);

                  for(int var34 = 1; var34 < var32.length; ++var34) {
                     if (!var32[var34].equals("") && var32[var34].equals(var32[var34 - 1])) {
                        throw StandardException.newException("42734", new Object[]{var32[var34], this.getFullName()});
                     }
                  }
               }
            }

            Integer var33 = (Integer)var30[2];
            int var35 = var33 == null ? 0 : var33;
            Short var19 = (Short)var30[6];
            int var18;
            if (var19 != null) {
               var18 = var19;
            } else {
               var18 = this.aliasType == 'P' ? 0 : 1;
            }

            Boolean var20 = (Boolean)var30[7];
            boolean var21 = var20 == null ? false : var20;
            Boolean var22 = (Boolean)var30[11];
            boolean var23 = var22 == null ? false : var22;
            Boolean var24 = (Boolean)var30[10];
            boolean var25 = var24 == null ? false : var24;
            Boolean var26 = (Boolean)var30[8];
            boolean var27;
            if (var26 == null) {
               var27 = true;
            } else {
               var27 = var26;
            }

            TypeDescriptor var28 = (TypeDescriptor)var30[9];
            if (var28 != null) {
               DataTypeDescriptor var29 = DataTypeDescriptor.getType(var28);
               var29 = this.bindUserType(var29);
               var28 = var29.getCatalogType();
            }

            this.aliasInfo = new RoutineAliasInfo(this.methodName, var12, var13, var14, var15, var35, (Short)var30[5], (short)var18, var21, var23, var25, var27, var28);
            this.implicitCreateSchema = true;
            break;
         case 'G':
            this.javaClassName = (String)var2;
            Object[] var7 = var4;
            TypeDescriptor var8 = this.bindUserCatalogType((TypeDescriptor)var7[0]);
            TypeDescriptor var9 = this.bindUserCatalogType((TypeDescriptor)var7[1]);
            if (var8.getJDBCTypeId() == 2009 || var9.getJDBCTypeId() == 2009) {
               throw StandardException.newException("42ZB3", new Object[0]);
            }

            this.aliasInfo = new AggregateAliasInfo(var8, var9);
            this.implicitCreateSchema = true;
            break;
         case 'S':
            this.implicitCreateSchema = true;
            TableName var11 = (TableName)var2;
            String var10;
            if (var11.getSchemaName() != null) {
               var10 = var11.getSchemaName();
            } else {
               var10 = this.getSchemaDescriptor().getSchemaName();
            }

            this.aliasInfo = new SynonymAliasInfo(var10, var11.getTableName());
      }

   }

   String statementToString() {
      switch (this.aliasType) {
         case 'A' -> {
            return "CREATE TYPE";
         }
         case 'G' -> {
            return "CREATE DERBY AGGREGATE";
         }
         case 'P' -> {
            return "CREATE PROCEDURE";
         }
         case 'S' -> {
            return "CREATE SYNONYM";
         }
         default -> {
            return "CREATE FUNCTION";
         }
      }
   }

   public void bindStatement() throws StandardException {
      if (this.aliasType == 'F' || this.aliasType == 'P') {
         RoutineAliasInfo var1 = (RoutineAliasInfo)this.aliasInfo;
         var1.setCollationTypeForAllStringTypes(this.getSchemaDescriptor().getCollationType());
         this.bindParameterTypes((RoutineAliasInfo)this.aliasInfo);
         if (var1.hasVarargs()) {
            switch (var1.getParameterStyle()) {
               case 1:
               case 2:
                  if (var1.getMaxDynamicResultSets() > 0) {
                     throw StandardException.newException("42ZCB", new Object[0]);
                  }
                  break;
               default:
                  throw StandardException.newException("42ZC9", new Object[0]);
            }
         }

         if (var1.getParameterStyle() == 2 && !var1.hasVarargs()) {
            throw StandardException.newException("42ZCA", new Object[0]);
         }
      }

      if (this.aliasType != 'A') {
         if (this.aliasType == 'G') {
            this.bindAggregate();
         }

         if (this.aliasType == 'S') {
            if (isSessionSchema(this.getSchemaDescriptor().getSchemaName())) {
               throw StandardException.newException("XCL51.S", new Object[0]);
            } else {
               String var8 = ((SynonymAliasInfo)this.aliasInfo).getSynonymSchema();
               String var9 = ((SynonymAliasInfo)this.aliasInfo).getSynonymTable();
               if (this.getObjectName().equals(var8, var9)) {
                  throw StandardException.newException("42916", new Object[]{this.getFullName(), var8 + "." + var9});
               } else {
                  SchemaDescriptor var10 = this.getSchemaDescriptor(var8, false);
                  if (var10 != null && this.isSessionSchema(var10)) {
                     throw StandardException.newException("XCL51.S", new Object[0]);
                  }
               }
            }
         }
      } else {
         TypeId[] var7 = TypeId.getAllBuiltinTypeIds();
         int var2 = var7.length;
         boolean var3 = this.javaClassName.startsWith("org.apache.derby.");
         if (!var3) {
            for(int var4 = 0; var4 < var2; ++var4) {
               TypeId var5 = var7[var4];
               String var6 = var5.getCorrespondingJavaTypeName();
               if (var6.equals(this.javaClassName)) {
                  var3 = true;
                  break;
               }
            }
         }

         if (var3) {
            throw StandardException.newException("42Z10", new Object[]{this.javaClassName});
         }
      }
   }

   private void bindAggregate() throws StandardException {
      String var1 = this.getRelativeName();
      SchemaDescriptor var2 = this.getSchemaDescriptor("SYSFUN", true);
      List var3 = this.getDataDictionary().getRoutineList(var2.getUUID().toString(), var1, 'F');

      for(int var4 = 0; var4 < var3.size(); ++var4) {
         AliasDescriptor var5 = (AliasDescriptor)var3.get(var4);
         RoutineAliasInfo var6 = (RoutineAliasInfo)var5.getAliasInfo();
         int var7 = var6.getParameterCount();
         if (var7 == 1) {
            throw this.illegalAggregate();
         }
      }

      for(int var8 = 0; var8 < NON_RESERVED_FUNCTION_NAMES.length; ++var8) {
         if (NON_RESERVED_FUNCTION_NAMES[var8].equals(var1)) {
            throw this.illegalAggregate();
         }
      }

      for(int var9 = 0; var9 < NON_RESERVED_AGGREGATES.length; ++var9) {
         if (NON_RESERVED_AGGREGATES[var9].equals(var1)) {
            throw this.illegalAggregate();
         }
      }

      AggregateAliasInfo var10 = (AggregateAliasInfo)this.aliasInfo;
      var10.setCollationTypeForAllStringTypes(this.getSchemaDescriptor().getCollationType());
   }

   private StandardException illegalAggregate() {
      return StandardException.newException("42ZC3", new Object[]{this.getRelativeName()});
   }

   private void bindParameterTypes(RoutineAliasInfo var1) throws StandardException {
      TypeDescriptor[] var2 = var1.getParameterTypes();
      if (var2 != null) {
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            var2[var4] = this.bindUserCatalogType(var2[var4]);
         }

         var1.setParameterTypes(var2);
      }
   }

   public ConstantAction makeConstantAction() throws StandardException {
      String var1 = this.getSchemaDescriptor().getSchemaName();
      return this.getGenericConstantActionFactory().getCreateAliasConstantAction(this.getRelativeName(), var1, this.javaClassName, this.aliasInfo, this.aliasType);
   }
}
