package org.apache.derby.impl.sql.compile;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public final class StaticClassFieldReferenceNode extends JavaValueNode {
   private String fieldName;
   private String javaClassName;
   private boolean classNameDelimitedIdentifier;
   private Member field;

   StaticClassFieldReferenceNode(String var1, String var2, boolean var3, ContextManager var4) {
      super(var4);
      this.fieldName = var2;
      this.javaClassName = var1;
      this.classNameDelimitedIdentifier = var3;
   }

   JavaValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      ClassInspector var4 = this.getClassFactory().getClassInspector();
      if ((this.getCompilerContext().getReliability() & 1024) == 0 && this.javaClassName.startsWith("java.sql.")) {
         this.verifyClassExist(this.javaClassName);
         this.field = var4.findPublicField(this.javaClassName, this.fieldName, true);
         this.setJavaTypeName(var4.getType(this.field));
         return this;
      } else {
         throw StandardException.newException("42X01", new Object[]{this.javaClassName + "::" + this.fieldName});
      }
   }

   void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
   }

   boolean categorize(JBitSet var1, boolean var2) {
      return true;
   }

   JavaValueNode remapColumnReferencesToExpressions() throws StandardException {
      return this;
   }

   int getOrderableVariantType() {
      return Modifier.isFinal(this.field.getModifiers()) ? 3 : 0;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      var2.getStaticField(this.field.getDeclaringClass().getName(), this.fieldName, this.getJavaTypeName());
   }
}
