package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.ClassBuilder;
import org.apache.derby.iapi.services.compiler.JavaFactory;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.ExpressionClassBuilderInterface;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.impl.sql.execute.IndexColumnOrder;
import org.apache.derby.shared.common.error.StandardException;

abstract class ExpressionClassBuilder implements ExpressionClassBuilderInterface {
   protected static final String currentDatetimeFieldName = "cdt";
   protected ClassBuilder cb;
   protected GeneratedClass gc;
   protected int nextExprNum;
   protected int nextNonFastExpr;
   protected int nextFieldNum;
   protected MethodBuilder constructor;
   CompilerContext myCompCtx;
   MethodBuilder executeMethod;
   protected LocalField cdtField;
   private String currentRowScanResultSetName;
   private Object getDVF;
   private Object getRSF;
   private Object getEF;

   ExpressionClassBuilder(String var1, String var2, CompilerContext var3) throws StandardException {
      byte var4 = 17;
      this.myCompCtx = var3;
      JavaFactory var5 = this.myCompCtx.getJavaFactory();
      if (var2 == null) {
         var2 = this.myCompCtx.getUniqueClassName();
      }

      this.cb = var5.newClassBuilder(this.myCompCtx.getClassFactory(), this.getPackageName(), var4, var2, var1);
      this.beginConstructor();
   }

   abstract String getPackageName();

   abstract int getRowCount() throws StandardException;

   abstract void setNumSubqueries() throws StandardException;

   abstract String getBaseClassName();

   MethodBuilder getConstructor() {
      return this.constructor;
   }

   ClassBuilder getClassBuilder() {
      return this.cb;
   }

   MethodBuilder getExecuteMethod() {
      if (this.executeMethod == null) {
         this.executeMethod = this.cb.newMethodBuilder(4, "void", "reinit");
         this.executeMethod.addThrownException("org.apache.derby.shared.common.error.StandardException");
      }

      return this.executeMethod;
   }

   private void beginConstructor() {
      MethodBuilder var1 = this.cb.newConstructorBuilder(1);
      var1.callSuper();
      var1.methodReturn();
      var1.complete();
      this.constructor = this.cb.newMethodBuilder(1, "void", "postConstructor");
      this.constructor.addThrownException("org.apache.derby.shared.common.error.StandardException");
   }

   void finishConstructor() throws StandardException {
      this.setNumSubqueries();
      int var1 = this.getRowCount();
      if (var1 >= 1) {
         this.addNewArrayOfRows(var1);
      }

      this.constructor.methodReturn();
      this.constructor.complete();
   }

   private void addNewArrayOfRows(int var1) {
      this.constructor.pushThis();
      this.constructor.pushNewArray("org.apache.derby.iapi.sql.execute.ExecRow", var1);
      this.constructor.putField("org.apache.derby.impl.sql.execute.BaseActivation", "row", "org.apache.derby.iapi.sql.execute.ExecRow[]");
      this.constructor.endStatement();
   }

   LocalField newFieldDeclaration(int var1, String var2, String var3) {
      return this.cb.addField(var2, var3, var1);
   }

   LocalField newFieldDeclaration(int var1, String var2) {
      return this.cb.addField(var2, this.newFieldName(), var1);
   }

   MethodBuilder newGeneratedFun(String var1, int var2) {
      return this.newGeneratedFun(var1, var2, (String[])null);
   }

   MethodBuilder newGeneratedFun(String var1, int var2, String[] var3) {
      String var4 = "g".concat(Integer.toString(this.nextNonFastExpr++));
      return this.newGeneratedFun(var4, var1, var2, var3);
   }

   private MethodBuilder newGeneratedFun(String var1, String var2, int var3, String[] var4) {
      MethodBuilder var5;
      if (var4 == null) {
         var5 = this.cb.newMethodBuilder(var3, var2, var1);
      } else {
         var5 = this.cb.newMethodBuilder(var3, var2, var1, var4);
      }

      var5.addThrownException("org.apache.derby.shared.common.error.StandardException");
      return var5;
   }

   MethodBuilder newExprFun() {
      String var1 = "e".concat(Integer.toString(this.nextExprNum++));
      return this.newGeneratedFun(var1, "java.lang.Object", 1, (String[])null);
   }

   void pushMethodReference(MethodBuilder var1, MethodBuilder var2) {
      var1.pushThis();
      var1.push(var2.getName());
      var1.callMethod((short)185, "org.apache.derby.iapi.services.loader.GeneratedByteCode", "getMethod", "org.apache.derby.iapi.services.loader.GeneratedMethod", 1);
   }

   MethodBuilder newUserExprFun() {
      MethodBuilder var1 = this.newExprFun();
      var1.addThrownException("java.lang.Exception");
      return var1;
   }

   void getCurrentDateExpression(MethodBuilder var1) {
      LocalField var2 = this.getCurrentSetup();
      var1.getField(var2);
      var1.callMethod((short)182, (String)null, "getCurrentDate", "java.sql.Date", 0);
   }

   void getCurrentTimeExpression(MethodBuilder var1) {
      LocalField var2 = this.getCurrentSetup();
      var1.getField(var2);
      var1.callMethod((short)182, (String)null, "getCurrentTime", "java.sql.Time", 0);
   }

   void getCurrentTimestampExpression(MethodBuilder var1) {
      LocalField var2 = this.getCurrentSetup();
      var1.getField(var2);
      var1.callMethod((short)182, (String)null, "getCurrentTimestamp", "java.sql.Timestamp", 0);
   }

   FormatableArrayHolder getColumnOrdering(ResultColumnList var1) {
      int var3 = var1 == null ? 0 : var1.size();
      int var4 = 0;

      for(int var5 = 0; var5 < var3; ++var5) {
         if (!var1.getResultColumn(var5 + 1).isGeneratedForUnmatchedColumnInInsert()) {
            ++var4;
         }
      }

      IndexColumnOrder[] var2 = new IndexColumnOrder[var4];
      int var7 = 0;

      for(int var6 = 0; var7 < var3; ++var7) {
         if (!var1.getResultColumn(var7 + 1).isGeneratedForUnmatchedColumnInInsert()) {
            var2[var6] = new IndexColumnOrder(var7);
            ++var6;
         }
      }

      return new FormatableArrayHolder(var2);
   }

   FormatableArrayHolder addColumnToOrdering(FormatableArrayHolder var1, int var2) {
      org.apache.derby.iapi.store.access.ColumnOrdering[] var3 = (org.apache.derby.iapi.store.access.ColumnOrdering[])var1.getArray(org.apache.derby.iapi.store.access.ColumnOrdering[].class);
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         if (var3[var5].getColumnId() == var2) {
            return var1;
         }
      }

      IndexColumnOrder[] var6 = new IndexColumnOrder[var4 + 1];
      System.arraycopy(var3, 0, var6, 0, var4);
      var6[var4] = new IndexColumnOrder(var2);
      return new FormatableArrayHolder(var6);
   }

   FormatableArrayHolder getColumnOrdering(OrderedColumnList var1) {
      int var2 = var1 == null ? 0 : var1.size();
      return var2 == 0 ? new FormatableArrayHolder(new IndexColumnOrder[0]) : new FormatableArrayHolder(var1.getColumnOrdering());
   }

   int addItem(Object var1) {
      return this.myCompCtx.addSavedObject(var1);
   }

   void pushDataValueFactory(MethodBuilder var1) {
      if (this.getDVF == null) {
         this.getDVF = var1.describeMethod((short)182, this.getBaseClassName(), "getDataValueFactory", "org.apache.derby.iapi.types.DataValueFactory");
      }

      var1.pushThis();
      var1.callMethod(this.getDVF);
   }

   void pushGetResultSetFactoryExpression(MethodBuilder var1) {
      if (this.getRSF == null) {
         this.getRSF = var1.describeMethod((short)182, this.getBaseClassName(), "getResultSetFactory", "org.apache.derby.iapi.sql.execute.ResultSetFactory");
      }

      var1.pushThis();
      var1.callMethod(this.getRSF);
   }

   void pushGetExecutionFactoryExpression(MethodBuilder var1) {
      if (this.getEF == null) {
         this.getEF = var1.describeMethod((short)182, this.getBaseClassName(), "getExecutionFactory", "org.apache.derby.iapi.sql.execute.ExecutionFactory");
      }

      var1.pushThis();
      var1.callMethod(this.getEF);
   }

   void pushColumnReference(MethodBuilder var1, int var2, int var3) {
      var1.pushThis();
      var1.push(var2);
      var1.push(var3);
      var1.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "getColumnFromRow", "org.apache.derby.iapi.types.DataValueDescriptor", 2);
   }

   void pushPVSReference(MethodBuilder var1) {
      var1.pushThis();
      var1.getField("org.apache.derby.impl.sql.execute.BaseActivation", "pvs", "org.apache.derby.iapi.sql.ParameterValueSet");
   }

   protected LocalField getCurrentSetup() {
      if (this.cdtField != null) {
         return this.cdtField;
      } else {
         this.cdtField = this.newFieldDeclaration(2, "org.apache.derby.impl.sql.execute.CurrentDatetime", "cdt");
         this.constructor.pushNewStart("org.apache.derby.impl.sql.execute.CurrentDatetime");
         this.constructor.pushNewComplete(0);
         this.constructor.setField(this.cdtField);
         return this.cdtField;
      }
   }

   private String newFieldName() {
      return "e".concat(Integer.toString(this.nextFieldNum++));
   }

   protected TypeCompiler getTypeCompiler(TypeId var1) {
      return this.myCompCtx.getTypeCompilerFactory().getTypeCompiler(var1);
   }

   GeneratedClass getGeneratedClass(ByteArray var1) throws StandardException {
      if (this.gc != null) {
         return this.gc;
      } else {
         if (var1 != null) {
            ByteArray var2 = this.cb.getClassBytecode();
            var1.setBytes(var2.getArray());
            var1.setLength(var2.getLength());
         }

         this.gc = this.cb.getGeneratedClass();
         return this.gc;
      }
   }

   void pushThisAsActivation(MethodBuilder var1) {
      var1.pushThis();
      var1.upCast("org.apache.derby.iapi.sql.Activation");
   }

   void generateNull(MethodBuilder var1, TypeCompiler var2, int var3) {
      this.pushDataValueFactory(var1);
      var1.pushNull(var2.interfaceName());
      var2.generateNull(var1, var3);
   }

   void generateNullWithExpress(MethodBuilder var1, TypeCompiler var2, int var3) {
      this.pushDataValueFactory(var1);
      var1.swap();
      var1.cast(var2.interfaceName());
      var2.generateNull(var1, var3);
   }

   void generateDataValue(MethodBuilder var1, TypeCompiler var2, int var3, LocalField var4) {
      this.pushDataValueFactory(var1);
      var1.swap();
      var2.generateDataValue(var1, var3, var4);
   }

   String newRowLocationScanResultSetName() {
      this.currentRowScanResultSetName = this.newFieldName();
      return this.currentRowScanResultSetName;
   }

   String getRowLocationScanResultSetName() {
      return this.currentRowScanResultSetName;
   }
}
