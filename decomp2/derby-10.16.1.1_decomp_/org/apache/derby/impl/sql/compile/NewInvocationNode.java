package org.apache.derby.impl.sql.compile;

import java.lang.reflect.Member;
import java.util.List;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

class NewInvocationNode extends MethodCallNode {
   private boolean singleInstantiation = false;
   private boolean delimitedIdentifier;
   private boolean isBuiltinVTI = false;

   NewInvocationNode(String var1, List var2, boolean var3, ContextManager var4) throws StandardException {
      super("<init>", var4);
      this.addParms(var2);
      this.javaClassName = var1;
      this.delimitedIdentifier = var3;
   }

   NewInvocationNode(TableName var1, TableDescriptor var2, List var3, boolean var4, ContextManager var5) throws StandardException {
      super("<init>", var5);
      this.addParms(var3);
      TableName var6 = var1;
      TableDescriptor var7 = var2;
      boolean var8 = var1 != null;
      if (var8) {
         var7 = new TableDescriptor(this.getDataDictionary(), var1.getTableName(), this.getSchemaDescriptor(var1.getSchemaName()), 5, 'R');
      }

      this.javaClassName = this.getDataDictionary().getVTIClass(var7, var8);
      this.isBuiltinVTI = this.getDataDictionary().getBuiltinVTIClass(var7, var8) != null;
      if (this.javaClassName == null) {
         if (!var8) {
            var6 = this.makeTableName(var7.getSchemaName(), var7.getDescriptorName());
         }

         throw StandardException.newException(var8 ? "42Y03.S.0" : "42X05", new Object[]{var6.getFullTableName()});
      } else {
         this.delimitedIdentifier = Boolean.valueOf(var4);
      }
   }

   boolean isBuiltinVTI() {
      return this.isBuiltinVTI;
   }

   void setSingleInstantiation() {
      this.singleInstantiation = true;
   }

   JavaValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindParameters(var1, var2, var3);
      this.verifyClassExist(this.javaClassName);
      String[] var4 = this.getObjectSignature();
      boolean[] var5 = this.getIsParam();
      ClassInspector var6 = this.getClassFactory().getClassInspector();

      try {
         this.method = var6.findPublicConstructor(this.javaClassName, var4, (String[])null, var5);
         if (this.method == null) {
            String[] var7 = this.getPrimitiveSignature(false);
            this.method = var6.findPublicConstructor(this.javaClassName, var4, var7, var5);
         }
      } catch (ClassNotFoundException var9) {
         this.method = null;
      }

      if (this.method == null) {
         String var12 = "";

         for(int var8 = 0; var8 < var4.length; ++var8) {
            if (var8 != 0) {
               var12 = var12 + ", ";
            }

            var12 = var12 + (var4[var8].length() != 0 ? var4[var8] : MessageService.getTextMessage("42Z01.U", new Object[0]));
         }

         throw StandardException.newException("42X75", new Object[]{this.javaClassName, var12});
      } else {
         this.methodParameterTypes = var6.getParameterTypes(this.method);

         for(int var10 = 0; var10 < this.methodParameterTypes.length; ++var10) {
            if (ClassInspector.primitiveType(this.methodParameterTypes[var10])) {
               this.methodParms[var10].castToPrimitive(true);
            }
         }

         if (this.someParametersAreNull()) {
            this.setNullParameterInfo(this.methodParameterTypes);
         }

         this.setJavaTypeName(this.javaClassName);
         if (this.routineInfo != null) {
            TypeDescriptor var11 = this.routineInfo.getReturnType();
            if (var11 != null) {
               this.setCollationType(var11.getCollationType());
            }
         }

         return this;
      }
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      if (var2) {
         return false;
      } else {
         boolean var3 = true;
         var3 = var3 && super.categorize(var1, var2);
         return var3;
      }
   }

   protected boolean assignableTo(String var1) throws StandardException {
      ClassInspector var2 = this.getClassFactory().getClassInspector();
      return var2.assignableTo(this.javaClassName, var1);
   }

   protected Member findPublicMethod(String var1, boolean var2) throws StandardException {
      String[] var4 = this.getObjectSignature();
      boolean[] var5 = this.getIsParam();
      ClassInspector var6 = this.getClassFactory().getClassInspector();

      try {
         Member var3 = var6.findPublicMethod(this.javaClassName, var1, var4, (String[])null, var5, var2, false, this.hasVarargs());
         if (var3 == null) {
            String[] var7 = this.getPrimitiveSignature(false);
            var3 = var6.findPublicMethod(this.javaClassName, var1, var4, var7, var5, var2, false, this.hasVarargs());
         }

         return var3;
      } catch (ClassNotFoundException var8) {
         return null;
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      LocalField var3 = null;
      if (this.singleInstantiation) {
         var3 = var1.newFieldDeclaration(2, this.javaClassName);
         var2.getField(var3);
         var2.conditionalIfNull();
      }

      var2.pushNewStart(this.javaClassName);
      int var4 = this.generateParameters(var1, var2);
      var2.pushNewComplete(var4);
      if (this.singleInstantiation) {
         var2.putField(var3);
         var2.startElseCode();
         var2.getField(var3);
         var2.completeConditional();
      }

   }
}
