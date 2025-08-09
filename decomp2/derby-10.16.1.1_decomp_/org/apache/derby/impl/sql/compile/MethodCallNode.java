package org.apache.derby.impl.sql.compile;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.catalog.types.TypeDescriptorImpl;
import org.apache.derby.catalog.types.UserDefinedTypeIdImpl;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.sql.compile.TypeCompilerFactory;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.JSQLType;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

abstract class MethodCallNode extends JavaValueNode {
   String methodName;
   String javaClassName;
   RoutineAliasInfo routineInfo;
   boolean internalCall;
   private String[] procedurePrimitiveArrayType;
   protected JSQLType[] signature;
   protected JavaValueNode[] methodParms;
   protected Member method;
   protected String actualMethodReturnType;
   String[] methodParameterTypes;

   MethodCallNode(String var1, ContextManager var2) {
      super(var2);
      this.methodName = var1;
   }

   String getMethodName() {
      return this.methodName;
   }

   TableName getFullName() {
      return null;
   }

   public String getJavaClassName() {
      return this.javaClassName;
   }

   public Member getResolvedMethod() {
      return this.method;
   }

   public RoutineAliasInfo getRoutineInfo() {
      return this.routineInfo;
   }

   void addParms(List var1) throws StandardException {
      this.methodParms = new JavaValueNode[var1.size()];
      int var2 = var1.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ValueNode var4 = (ValueNode)var1.get(var3);
         SQLToJavaValueNode var5 = new SQLToJavaValueNode(var4, this.getContextManager());
         this.methodParms[var3] = var5;
      }

   }

   Class[] getMethodParameterClasses() {
      ClassInspector var1 = this.getClassFactory().getClassInspector();
      Class[] var2 = new Class[this.methodParms.length];

      for(int var3 = 0; var3 < this.methodParms.length; ++var3) {
         String var4 = this.methodParameterTypes[var3];

         try {
            var2[var3] = var1.getClass(var4);
         } catch (ClassNotFoundException var6) {
            return null;
         }
      }

      return var2;
   }

   void getCorrelationTables(JBitSet var1) throws StandardException {
      CollectNodesVisitor var2 = new CollectNodesVisitor(ColumnReference.class);
      this.accept(var2);

      for(ColumnReference var4 : var2.getList()) {
         if (var4.getCorrelated()) {
            var1.set(var4.getTableNumber());
         }
      }

   }

   void printSubNodes(int var1) {
   }

   public String toString() {
      return "";
   }

   final void bindParameters(FromList var1, SubqueryList var2, List var3) throws StandardException {
      if (this.methodParms != null) {
         int var4 = this.methodParms.length;
         if (this.signature == null) {
            this.signature = new JSQLType[var4];
         }

         for(int var5 = 0; var5 < var4; ++var5) {
            if (this.methodParms[var5] != null) {
               this.methodParms[var5] = this.methodParms[var5].bindExpression(var1, var2, var3);
               if (this.routineInfo == null) {
                  this.signature[var5] = this.methodParms[var5].getJSQLType();
               }

               SelectNode.checkNoWindowFunctions(this.methodParms[var5], "method argument");
            }
         }
      }

   }

   protected boolean areParametersQueryInvariant() throws StandardException {
      return this.getVariantTypeOfParams() == 2;
   }

   void throwNoMethodFound(String var1, String[] var2, String[] var3) throws StandardException {
      StringBuilder var4 = new StringBuilder();
      int var5 = this.signature.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         if (var6 != 0) {
            var4.append(", ");
         }

         boolean var7 = this.isVararg(var6);
         String var8 = var2[var6];
         if (var2[var6].length() == 0) {
            var8 = "UNTYPED";
         } else if (var7) {
            var8 = this.getVarargTypeName(var8);
         }

         var4.append(var8);
         if (var3 != null && !var3[var6].equals(var2[var6])) {
            String var9 = var3[var6];
            if (var7) {
               var9 = this.getVarargTypeName(var9);
            }

            var4.append("(" + var9 + ")");
         }
      }

      throw StandardException.newException("42X50", new Object[]{var1, this.methodName, var4});
   }

   private String getVarargTypeName(String var1) {
      String var10000 = this.stripOneArrayLevel(var1);
      return var10000 + "...";
   }

   void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      if (this.methodParms != null) {
         for(int var5 = 0; var5 < this.methodParms.length; ++var5) {
            if (this.methodParms[var5] != null) {
               this.methodParms[var5].preprocess(var1, var2, var3, var4);
            }
         }
      }

   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      if (var2) {
         return false;
      } else {
         boolean var3 = true;
         if (this.methodParms != null) {
            for(int var4 = 0; var4 < this.methodParms.length; ++var4) {
               if (this.methodParms[var4] != null) {
                  var3 = this.methodParms[var4].categorize(var1, var2) && var3;
               }
            }
         }

         return var3;
      }
   }

   JavaValueNode remapColumnReferencesToExpressions() throws StandardException {
      if (this.methodParms != null) {
         for(int var1 = 0; var1 < this.methodParms.length; ++var1) {
            if (this.methodParms[var1] != null) {
               this.methodParms[var1] = this.methodParms[var1].remapColumnReferencesToExpressions();
            }
         }
      }

      return this;
   }

   public boolean hasVarargs() {
      return this.routineInfo == null ? false : this.routineInfo.hasVarargs();
   }

   public int getFirstVarargIdx() {
      return this.signature.length - 1;
   }

   public boolean isVararg(int var1) {
      if (!this.hasVarargs()) {
         return false;
      } else {
         return var1 >= this.getFirstVarargIdx();
      }
   }

   public int generateParameters(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var4 = this.hasVarargs() ? this.routineInfo.getParameterCount() - 1 : this.methodParms.length;
      int var5 = this.hasVarargs() ? var4 + 1 : var4;

      for(int var3 = 0; var3 < var4; ++var3) {
         this.generateAndCastOneParameter(var1, var2, var3, this.methodParameterTypes[var3]);
      }

      if (this.hasVarargs()) {
         this.generateVarargs(var1, var2);
      }

      return var5;
   }

   private void generateAndCastOneParameter(ExpressionClassBuilder var1, MethodBuilder var2, int var3, String var4) throws StandardException {
      ClassInspector var5 = this.getClassFactory().getClassInspector();
      this.generateOneParameter(var1, var2, var3);
      String var6 = getParameterTypeName(this.methodParms[var3]);
      if (!var4.equals(var6)) {
         if (ClassInspector.primitiveType(var6) && var4.equals(JSQLType.getWrapperClassName(JSQLType.getPrimitiveID(var6)))) {
            if ("short".equals(var6)) {
               var2.cast("int");
            }

            var2.callMethod((short)184, var4, "valueOf", var4, 1);
         } else if (ClassInspector.primitiveType(var4)) {
            var2.cast(var4);
         } else {
            if (this.routineInfo != null) {
               return;
            }

            var2.upCast(var4);
         }
      }

   }

   private void generateVarargs(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = this.getFirstVarargIdx();
      String var4 = this.methodParameterTypes[var3];
      String var5 = this.stripOneArrayLevel(var4);
      if (this.routineInfo != null && this.routineInfo.getParameterModes()[var3] != 1) {
         this.stripOneArrayLevel(var5);
      }

      int var7 = this.methodParms.length - var3;
      if (var7 < 0) {
         var7 = 0;
      }

      LocalField var8 = var1.newFieldDeclaration(2, var4);
      MethodBuilder var9 = var1.getConstructor();
      var9.pushNewArray(var5, var7);
      var9.setField(var8);

      for(int var10 = 0; var10 < var7; ++var10) {
         var2.getField(var8);
         this.generateAndCastOneParameter(var1, var2, var10 + var3, var5);
         var2.setArrayElement(var10);
      }

      var2.getField(var8);
   }

   protected int getRoutineArgIdx(int var1) {
      return this.routineInfo == null ? var1 : this.getRoutineArgIdx(this.routineInfo, var1);
   }

   protected int getRoutineArgIdx(RoutineAliasInfo var1, int var2) {
      if (!var1.hasVarargs()) {
         return var2;
      } else {
         int var3 = var1.getParameterCount() - 1;
         return var3 < var2 ? var3 : var2;
      }
   }

   public static String getParameterTypeName(JavaValueNode var0) throws StandardException {
      String var1;
      if (var0.isPrimitiveType()) {
         var1 = var0.getPrimitiveTypeName();
      } else {
         var1 = var0.getJavaTypeName();
      }

      return var1;
   }

   void generateOneParameter(ExpressionClassBuilder var1, MethodBuilder var2, int var3) throws StandardException {
      this.methodParms[var3].generateExpression(var1, var2);
   }

   void setNullParameterInfo(String[] var1) throws StandardException {
      for(int var2 = 0; var2 < this.methodParms.length; ++var2) {
         if (this.methodParms[var2].getJavaTypeName().equals("")) {
            DataTypeDescriptor var3 = DataTypeDescriptor.getSQLDataTypeDescriptor(var1[var2]);
            ((SQLToJavaValueNode)this.methodParms[var2]).value.setType(var3);
            this.methodParms[var2].setJavaTypeName(var1[var2]);
            this.signature[var2] = this.methodParms[var2].getJSQLType();
         }
      }

   }

   protected void resolveMethodCall(String var1, boolean var2) throws StandardException {
      if (this.routineInfo == null && !this.internalCall && (this.getCompilerContext().getReliability() & 1024) != 0) {
         throw StandardException.newException("42X01", new Object[]{var1 + (var2 ? "::" : ".") + this.methodName});
      } else {
         int var3 = this.signature.length;
         ClassInspector var4 = this.getClassFactory().getClassInspector();
         String[] var6 = null;
         boolean[] var7 = this.getIsParam();
         boolean var8 = this.hasVarargs() ? false : this.routineInfo != null && var3 != 0 && var3 != this.methodParms.length;
         int var9 = this.methodName.indexOf(40);
         String[] var5;
         if (var9 != -1) {
            var5 = this.parseValidateSignature(this.methodName, var9, var8);
            this.methodName = this.methodName.substring(0, var9);
            var8 = false;
         } else {
            var5 = this.getObjectSignature();
         }

         if (this.hasVarargs()) {
            var5[var3 - 1] = var5[var3 - 1] + "[]";
         }

         try {
            this.method = var4.findPublicMethod(var1, this.methodName, var5, (String[])null, var7, var2, var8, this.hasVarargs());
            if (var9 == -1 && this.routineInfo == null && this.method == null) {
               var6 = this.getPrimitiveSignature(false);
               this.method = var4.findPublicMethod(var1, this.methodName, var5, var6, var7, var2, var8, this.hasVarargs());
            }
         } catch (ClassNotFoundException var17) {
            this.method = null;
         }

         if (this.method == null) {
            this.throwNoMethodFound(var1, var5, var6);
         }

         String var10 = var4.getType(this.method);
         this.actualMethodReturnType = var10;
         if (this.routineInfo == null) {
            if (var10.equals("void") && !this.forCallStatement) {
               throw StandardException.newException("42Y09", new Object[0]);
            }
         } else {
            String var11 = null;
            TypeDescriptorImpl var12 = (TypeDescriptorImpl)this.routineInfo.getReturnType();
            String var13;
            if (var12 == null) {
               var13 = "void";
            } else {
               TypeId var14 = TypeId.getBuiltInTypeId(var12.getJDBCTypeId());
               if (var12.isRowMultiSet() && this.routineInfo.getParameterStyle() == 1) {
                  var13 = ResultSet.class.getName();
               } else if (var12.getTypeId().userType()) {
                  var13 = ((UserDefinedTypeIdImpl)var12.getTypeId()).getClassName();
               } else {
                  var13 = var14.getCorrespondingJavaTypeName();
                  if (!var13.equals(var10)) {
                     switch (var12.getJDBCTypeId()) {
                        case -5:
                        case 4:
                        case 5:
                        case 7:
                        case 8:
                        case 16:
                           TypeCompiler var15 = this.getTypeCompiler(var14);
                           var13 = var15.getCorrespondingPrimitiveTypeName();
                           if (!this.routineInfo.calledOnNullInput() && this.routineInfo.getParameterCount() != 0) {
                              var11 = var14.getCorrespondingJavaTypeName();
                           }
                     }
                  }
               }
            }

            boolean var24;
            if (ResultSet.class.getName().equals(var13)) {
               try {
                  Class var26 = var4.getClass(var10);
                  var24 = ResultSet.class.isAssignableFrom(var26);
               } catch (ClassNotFoundException var16) {
                  var24 = false;
               }
            } else {
               var24 = var13.equals(var10);
            }

            if (!var24) {
               this.throwNoMethodFound(var13 + " " + var1, var5, var6);
            }

            if (var11 != null) {
               var10 = var11;
            }

            if (this.routineInfo.getReturnType() != null) {
               this.setCollationType(this.routineInfo.getReturnType().getCollationType());
            }
         }

         this.setJavaTypeName(var10);
         this.methodParameterTypes = var4.getParameterTypes(this.method);
         String var18 = null;

         for(int var19 = 0; var19 < this.methodParameterTypes.length; ++var19) {
            var18 = this.methodParameterTypes[var19];
            if (this.routineInfo != null && var19 < this.routineInfo.getParameterCount()) {
               int var22 = this.routineInfo.getParameterModes()[this.getRoutineArgIdx(var19)];
               switch (var22) {
                  case 1:
                  case 3:
                  default:
                     break;
                  case 2:
                     var18 = this.stripOneArrayLevel(var18);
                     break;
                  case 4:
                     continue;
               }
            }

            if (this.hasVarargs() && var19 >= this.getFirstVarargIdx()) {
               var18 = this.stripOneArrayLevel(var18);
            }

            if (ClassInspector.primitiveType(var18) && var19 < this.methodParms.length) {
               this.methodParms[var19].castToPrimitive(true);
            }
         }

         if (this.hasVarargs()) {
            int var20 = this.getFirstVarargIdx();
            int var23 = this.methodParms.length - var20;

            for(int var25 = 1; var25 < var23; ++var25) {
               if (ClassInspector.primitiveType(var18)) {
                  this.methodParms[var25 + var20].castToPrimitive(true);
               }
            }
         }

         if (this.someParametersAreNull()) {
            this.setNullParameterInfo(this.methodParameterTypes);
         }

         DataTypeDescriptor var21 = DataTypeDescriptor.getSQLDataTypeDescriptor(var10);
         if (this.getCompilerContext().getReturnParameterFlag()) {
            this.getParameterTypes()[0] = var21;
         }

      }
   }

   protected String stripOneArrayLevel(String var1) {
      return var1.substring(0, var1.length() - 2);
   }

   private String[] parseValidateSignature(String var1, int var2, boolean var3) throws StandardException {
      int var4 = var1.length();
      if (var2 + 1 != var4 && var1.charAt(var4 - 1) == ')') {
         StringTokenizer var5 = new StringTokenizer(var1.substring(var2 + 1, var4 - 1), ",", true);
         String[] var6 = new String[this.signature.length];
         boolean var8 = false;
         int var7 = 0;

         String var9;
         TypeId var13;
         while(true) {
            if (!var5.hasMoreTokens()) {
               if (var7 != 0 && !var8) {
                  throw StandardException.newException("46J01", new Object[0]);
               }

               if (var7 < var6.length) {
                  if (var3 && var7 == this.signature.length - 1) {
                     var9 = new String[var7];
                     System.arraycopy(var6, 0, var9, 0, var7);
                     return var9;
                  }

                  throw StandardException.newException("46J02", new Object[]{Integer.toString(var7), Integer.toString(this.signature.length)});
               }

               return var6;
            }

            var9 = var5.nextToken().trim();
            if (!",".equals(var9)) {
               if (var9.length() == 0) {
                  throw StandardException.newException("46J01", new Object[0]);
               }

               var8 = true;
               ++var7;
               if (var7 <= this.signature.length) {
                  var13 = this.signature[var7 - 1].getSQLType().getTypeId();
                  if (!var9.equals(var13.getCorrespondingJavaTypeName())) {
                     if ((!var13.isNumericTypeId() || var13.isDecimalTypeId()) && !var13.isBooleanTypeId()) {
                        break;
                     }

                     TypeCompiler var14 = this.getTypeCompiler(var13);
                     if (!var9.equals(var14.getCorrespondingPrimitiveTypeName())) {
                        break;
                     }

                     var6[var7 - 1] = var9;
                  } else {
                     var6[var7 - 1] = var9;
                  }
               } else {
                  if (!var3) {
                     throw StandardException.newException("46J02", new Object[]{Integer.toString(var7), Integer.toString(this.signature.length)});
                  }

                  String var10 = this.signature[this.signature.length - 1].getSQLType().getTypeId().getCorrespondingJavaTypeName();
                  if (!var9.equals(var10)) {
                     throw StandardException.newException("22005", new Object[]{var9, var10});
                  }

                  if (var6.length == this.signature.length) {
                     String[] var11 = new String[var5.countTokens()];
                     System.arraycopy(var6, 0, var11, 0, var6.length);
                     var6 = var11;
                  }

                  var6[var7 - 1] = var9;
               }
            } else {
               if (!var8) {
                  throw StandardException.newException("46J01", new Object[0]);
               }

               var8 = false;
            }
         }

         throw StandardException.newException("22005", new Object[]{var9, var13.getSQLTypeName()});
      } else {
         throw StandardException.newException("46J01", new Object[0]);
      }
   }

   protected boolean someParametersAreNull() {
      int var1 = this.signature.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         if (this.signature[var2] == null) {
            return true;
         }
      }

      return false;
   }

   protected String[] getObjectSignature() throws StandardException {
      int var1 = this.signature.length;
      String[] var2 = new String[var1];
      TypeCompilerFactory var3 = this.routineInfo == null ? null : this.getCompilerContext().getTypeCompilerFactory();

      for(int var4 = 0; var4 < var1; ++var4) {
         var2[var4] = getObjectTypeName(this.signature[var4], var3);
      }

      return var2;
   }

   protected boolean[] getIsParam() {
      if (this.methodParms == null) {
         return new boolean[0];
      } else {
         boolean[] var1 = new boolean[this.methodParms.length];

         for(int var2 = 0; var2 < this.methodParms.length; ++var2) {
            if (this.methodParms[var2] instanceof SQLToJavaValueNode) {
               SQLToJavaValueNode var3 = (SQLToJavaValueNode)this.methodParms[var2];
               if (var3.value.requiresTypeFromContext()) {
                  var1[var2] = true;
               }
            }
         }

         return var1;
      }
   }

   static String getObjectTypeName(JSQLType var0, TypeCompilerFactory var1) throws StandardException {
      if (var0 != null) {
         switch (var0.getCategory()) {
            case 0:
               TypeId var2 = mapToTypeID(var0);
               if (var2 == null) {
                  return null;
               }

               switch (var2.getJDBCTypeId()) {
                  case -5:
                  case 4:
                  case 5:
                  case 7:
                  case 8:
                  case 16:
                     if (var1 != null) {
                        return var1.getTypeCompiler(var2).getCorrespondingPrimitiveTypeName();
                     }
                  default:
                     return var2.getCorrespondingJavaTypeName();
               }
            case 1:
               return var0.getJavaClassName();
            case 2:
               return JSQLType.getPrimitiveName(var0.getPrimitiveKind());
         }
      }

      return "";
   }

   String[] getPrimitiveSignature(boolean var1) throws StandardException {
      int var2 = this.signature.length;
      String[] var3 = new String[var2];

      for(int var5 = 0; var5 < var2; ++var5) {
         JSQLType var4 = this.signature[var5];
         if (var4 == null) {
            var3[var5] = "";
         } else {
            switch (var4.getCategory()) {
               case 0:
                  if (this.procedurePrimitiveArrayType != null && var5 < this.procedurePrimitiveArrayType.length && this.procedurePrimitiveArrayType[var5] != null) {
                     var3[var5] = this.procedurePrimitiveArrayType[var5];
                  } else {
                     TypeId var6 = mapToTypeID(var4);
                     if ((!var6.isNumericTypeId() || var6.isDecimalTypeId()) && !var6.isBooleanTypeId()) {
                        var3[var5] = var6.getCorrespondingJavaTypeName();
                     } else {
                        TypeCompiler var7 = this.getTypeCompiler(var6);
                        var3[var5] = var7.getCorrespondingPrimitiveTypeName();
                        if (var1) {
                           this.methodParms[var5].castToPrimitive(true);
                        }
                     }
                  }
                  break;
               case 1:
                  var3[var5] = var4.getJavaClassName();
                  break;
               case 2:
                  var3[var5] = JSQLType.getPrimitiveName(var4.getPrimitiveKind());
                  if (var1) {
                     this.methodParms[var5].castToPrimitive(true);
                  }
            }
         }
      }

      return var3;
   }

   int getOrderableVariantType() throws StandardException {
      return this.getVariantTypeOfParams();
   }

   private int getVariantTypeOfParams() throws StandardException {
      int var1 = 2;
      if (this.methodParms != null) {
         for(int var2 = 0; var2 < this.methodParms.length; ++var2) {
            if (this.methodParms[var2] != null) {
               int var3 = this.methodParms[var2].getOrderableVariantType();
               if (var3 < var1) {
                  var1 = var3;
               }
            } else {
               var1 = 0;
            }
         }
      }

      return var1;
   }

   DataTypeDescriptor getDataType() throws StandardException {
      if (this.routineInfo != null) {
         TypeDescriptor var1 = this.routineInfo.getReturnType();
         if (var1 != null) {
            return DataTypeDescriptor.getType(var1);
         }
      }

      return super.getDataType();
   }

   JavaValueNode[] getMethodParms() {
      return this.methodParms;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);

      for(int var2 = 0; !var1.stopTraversal() && var2 < this.methodParms.length; ++var2) {
         if (this.methodParms[var2] != null) {
            this.methodParms[var2] = (JavaValueNode)this.methodParms[var2].accept(var1);
         }
      }

   }
}
