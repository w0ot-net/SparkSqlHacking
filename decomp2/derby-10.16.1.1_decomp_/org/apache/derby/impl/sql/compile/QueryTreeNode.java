package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.types.RowMultiSetImpl;
import org.apache.derby.catalog.types.SynonymAliasInfo;
import org.apache.derby.catalog.types.UserDefinedTypeIdImpl;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.StatementUtil;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.OptTrace;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.impl.sql.execute.GenericConstantActionFactory;
import org.apache.derby.impl.sql.execute.GenericExecutionFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public abstract class QueryTreeNode implements Visitable {
   static final int AUTOINCREMENT_START_INDEX = 0;
   static final int AUTOINCREMENT_INC_INDEX = 1;
   static final int AUTOINCREMENT_IS_AUTOINCREMENT_INDEX = 2;
   static final int AUTOINCREMENT_CREATE_MODIFY = 3;
   static final int AUTOINCREMENT_CYCLE = 4;
   private int beginOffset = -1;
   private int endOffset = -1;
   private ContextManager cm;
   private LanguageConnectionContext lcc;
   private GenericConstantActionFactory constantActionFactory;
   private ArrayList visitableTags;
   private boolean isPrivilegeCollectionRequired = true;

   QueryTreeNode(ContextManager var1) {
      this.cm = var1;
   }

   final ContextManager getContextManager() {
      return this.cm;
   }

   public final OptimizerFactory getOptimizerFactory() {
      return this.getLanguageConnectionContext().getLanguageConnectionFactory().getOptimizerFactory();
   }

   public OptTrace getOptimizerTracer() {
      return this.getLanguageConnectionContext().getOptimizerTracer();
   }

   public boolean optimizerTracingIsOn() {
      return this.getLanguageConnectionContext().optimizerTracingIsOn();
   }

   public final GenericConstantActionFactory getGenericConstantActionFactory() {
      if (this.constantActionFactory == null) {
         GenericExecutionFactory var1 = (GenericExecutionFactory)this.getExecutionFactory();
         this.constantActionFactory = var1.getConstantActionFactory();
      }

      return this.constantActionFactory;
   }

   public final ExecutionFactory getExecutionFactory() {
      ExecutionFactory var1 = this.getLanguageConnectionContext().getLanguageConnectionFactory().getExecutionFactory();
      return var1;
   }

   protected final ClassFactory getClassFactory() {
      return this.getLanguageConnectionContext().getLanguageConnectionFactory().getClassFactory();
   }

   protected final LanguageConnectionContext getLanguageConnectionContext() {
      if (this.lcc == null) {
         this.lcc = (LanguageConnectionContext)this.getContextManager().getContext("LanguageConnectionContext");
      }

      return this.lcc;
   }

   public int getBeginOffset() {
      return this.beginOffset;
   }

   public void setBeginOffset(int var1) {
      this.beginOffset = var1;
   }

   public int getEndOffset() {
      return this.endOffset;
   }

   public void setEndOffset(int var1) {
      this.endOffset = var1;
   }

   protected String nodeHeader() {
      return "";
   }

   static String formatNodeString(String var0, int var1) {
      return "";
   }

   public void treePrint() {
   }

   void stackPrint() {
   }

   void treePrint(int var1) {
   }

   private static boolean containsInfo(String var0) {
      for(int var1 = 0; var1 < var0.length(); ++var1) {
         if (var0.charAt(var1) != '\t' && var0.charAt(var1) != '\n') {
            return true;
         }
      }

      return false;
   }

   static void debugPrint(String var0) {
   }

   protected static void debugFlush() {
   }

   void printSubNodes(int var1) {
   }

   public String toString() {
      return "";
   }

   void printLabel(int var1, String var2) {
   }

   public boolean referencesSessionSchema() throws StandardException {
      return false;
   }

   final boolean isSessionSchema(SchemaDescriptor var1) {
      return isSessionSchema(var1.getSchemaName());
   }

   static boolean isSessionSchema(String var0) {
      return "SESSION".equals(var0);
   }

   final void disablePrivilegeCollection() {
      this.isPrivilegeCollectionRequired = false;
   }

   boolean isPrivilegeCollectionRequired() throws StandardException {
      return this.isPrivilegeCollectionRequired && this.getCompilerContext().passesPrivilegeFilters(this);
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      throw StandardException.newException("42Z50", new Object[]{this.nodeHeader()});
   }

   public DataTypeDescriptor[] getParameterTypes() throws StandardException {
      return ((CompilerContextImpl)this.getCompilerContext()).getParameterTypes();
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return null;
   }

   public final DataDictionary getDataDictionary() {
      return this.getLanguageConnectionContext().getDataDictionary();
   }

   final DependencyManager getDependencyManager() {
      return this.getDataDictionary().getDependencyManager();
   }

   protected final CompilerContext getCompilerContext() {
      return (CompilerContext)this.getContextManager().getContext("CompilerContext");
   }

   protected final TypeCompiler getTypeCompiler(TypeId var1) {
      return this.getCompilerContext().getTypeCompilerFactory().getTypeCompiler(var1);
   }

   public final Visitable accept(Visitor var1) throws StandardException {
      boolean var2 = var1.visitChildrenFirst(this);
      boolean var3 = var1.skipChildren(this);
      if (var2 && !var3 && !var1.stopTraversal()) {
         this.acceptChildren(var1);
      }

      Object var4 = var1.stopTraversal() ? this : var1.visit(this);
      if (!var2 && !var3 && !var1.stopTraversal()) {
         this.acceptChildren(var1);
      }

      return (Visitable)var4;
   }

   void acceptChildren(Visitor var1) throws StandardException {
   }

   public void addTag(String var1) {
      if (this.visitableTags == null) {
         this.visitableTags = new ArrayList();
      }

      this.visitableTags.add(var1);
   }

   public boolean taggedWith(String var1) {
      return this.visitableTags == null ? false : this.visitableTags.contains(var1);
   }

   protected void copyTagsFrom(QueryTreeNode var1) {
      if (var1.visitableTags != null) {
         for(String var3 : var1.visitableTags) {
            this.addTag(var3);
         }

      }
   }

   protected int getIntProperty(String var1, String var2) throws StandardException {
      int var3 = -1;

      try {
         var3 = Integer.parseInt(var1);
         return var3;
      } catch (NumberFormatException var5) {
         throw StandardException.newException("42Y58", new Object[]{var1, var2});
      }
   }

   protected long getLongProperty(String var1, String var2) throws StandardException {
      long var3 = -1L;

      try {
         var3 = Long.parseLong(var1);
         return var3;
      } catch (NumberFormatException var6) {
         throw StandardException.newException("42Y58", new Object[]{var1, var2});
      }
   }

   StatementNode parseStatement(String var1, boolean var2) throws StandardException {
      return (StatementNode)this.parseStatementOrSearchCondition(var1, var2, true);
   }

   ValueNode parseSearchCondition(String var1, boolean var2) throws StandardException {
      return (ValueNode)this.parseStatementOrSearchCondition(var1, var2, false);
   }

   private Visitable parseStatementOrSearchCondition(String var1, boolean var2, boolean var3) throws StandardException {
      LanguageConnectionContext var4 = this.getLanguageConnectionContext();
      CompilerContext var5 = var4.pushCompilerContext();
      if (var2) {
         var5.setReliability(0);
      }

      Visitable var7;
      try {
         Parser var6 = var5.getParser();
         var7 = var3 ? var6.parseStatement(var1) : var6.parseSearchCondition(var1);
      } finally {
         var4.popCompilerContext(var5);
      }

      return var7;
   }

   protected int getStatementType() {
      return 0;
   }

   ConstantNode getNullNode(DataTypeDescriptor var1) throws StandardException {
      switch (var1.getTypeId().getJDBCTypeId()) {
         case -6:
         case -5:
         case 3:
         case 4:
         case 5:
         case 7:
         case 8:
            NumericConstantNode var14 = new NumericConstantNode(var1.getTypeId(), this.cm);
            var14.setType(var1.getNullabilityType(true));
            return var14;
         case -4:
            VarbitConstantNode var13 = new VarbitConstantNode(var1.getTypeId(), this.cm);
            var13.setType(var1.getNullabilityType(true));
            return var13;
         case -3:
            VarbitConstantNode var12 = new VarbitConstantNode(var1.getTypeId(), this.cm);
            var12.setType(var1.getNullabilityType(true));
            return var12;
         case -2:
            BitConstantNode var11 = new BitConstantNode(var1.getTypeId(), this.cm);
            var11.setType(var1.getNullabilityType(true));
            return var11;
         case -1:
            CharConstantNode var10 = new CharConstantNode(2, var1.getTypeId(), this.cm);
            var10.setType(var1.getNullabilityType(true));
            return var10;
         case 1:
            CharConstantNode var9 = new CharConstantNode(var1.getTypeId(), this.cm);
            var9.setType(var1.getNullabilityType(true));
            return var9;
         case 2:
            NumericConstantNode var8 = new NumericConstantNode(TypeId.getBuiltInTypeId(3), this.cm);
            var8.setType(var1.getNullabilityType(true));
            return var8;
         case 12:
            CharConstantNode var7 = new CharConstantNode(1, var1.getTypeId(), this.cm);
            var7.setType(var1.getNullabilityType(true));
            return var7;
         case 16:
            BooleanConstantNode var6 = new BooleanConstantNode(var1.getTypeId(), this.cm);
            var6.setType(var1.getNullabilityType(true));
            return var6;
         case 91:
         case 92:
         case 93:
            UserTypeConstantNode var5 = new UserTypeConstantNode(var1.getTypeId(), this.cm);
            var5.setType(var1.getNullabilityType(true));
            return var5;
         case 2004:
            VarbitConstantNode var4 = new VarbitConstantNode(var1.getTypeId(), this.cm);
            var4.setType(var1.getNullabilityType(true));
            return var4;
         case 2005:
            CharConstantNode var3 = new CharConstantNode(3, var1.getTypeId(), this.cm);
            var3.setType(var1.getNullabilityType(true));
            return var3;
         case 2009:
            XMLConstantNode var2 = new XMLConstantNode(var1.getTypeId(), this.cm);
            var2.setType(var1.getNullabilityType(true));
            return var2;
         default:
            if (var1.getTypeId().userType()) {
               UserTypeConstantNode var15 = new UserTypeConstantNode(var1.getTypeId(), this.cm);
               var15.setType(var1.getNullabilityType(true));
               return var15;
            } else {
               return null;
            }
      }
   }

   DataValueDescriptor convertDefaultNode(DataTypeDescriptor var1) throws StandardException {
      return null;
   }

   public TableName makeTableName(String var1, String var2) throws StandardException {
      return makeTableName(this.getContextManager(), var1, var2);
   }

   public static TableName makeTableName(ContextManager var0, String var1, String var2) throws StandardException {
      return new TableName(var1, var2, var0);
   }

   public boolean isAtomic() throws StandardException {
      return false;
   }

   protected final TableDescriptor getTableDescriptor(String var1, SchemaDescriptor var2) throws StandardException {
      if (this.isSessionSchema(var2)) {
         TableDescriptor var3 = this.getLanguageConnectionContext().getTableDescriptorForDeclaredGlobalTempTable(var1);
         if (var3 != null) {
            return var3;
         }
      }

      if (var2.getUUID() == null) {
         return null;
      } else {
         TableDescriptor var4 = this.getDataDictionary().getTableDescriptor(var1, var2, this.getLanguageConnectionContext().getTransactionCompile());
         return var4 != null && !var4.isSynonymDescriptor() ? var4 : null;
      }
   }

   final SchemaDescriptor getSchemaDescriptor(String var1) throws StandardException {
      return this.getSchemaDescriptor(var1, true);
   }

   final SchemaDescriptor getSchemaDescriptor(String var1, boolean var2) throws StandardException {
      return StatementUtil.getSchemaDescriptor(var1, var2, this.getDataDictionary(), this.getLanguageConnectionContext(), this.getCompilerContext());
   }

   TableName resolveTableToSynonym(TableName var1) throws StandardException {
      DataDictionary var2 = this.getDataDictionary();
      String var3 = var1.getTableName();
      String var4 = var1.getSchemaName();
      boolean var5 = false;
      CompilerContext var6 = this.getCompilerContext();

      while(true) {
         SchemaDescriptor var7 = this.getSchemaDescriptor(var4, false);
         if (var7 == null || var7.getUUID() == null) {
            break;
         }

         AliasDescriptor var8 = var2.getAliasDescriptor(var7.getUUID().toString(), var3, 'S');
         if (var8 == null) {
            break;
         }

         var6.createDependency(var8);
         var5 = true;
         SynonymAliasInfo var9 = (SynonymAliasInfo)var8.getAliasInfo();
         var3 = var9.getSynonymTable();
         var4 = var9.getSynonymSchema();
      }

      if (!var5) {
         return null;
      } else {
         TableName var10 = new TableName(var4, var3, this.getContextManager());
         return var10;
      }
   }

   void verifyClassExist(String var1) throws StandardException {
      ClassInspector var2 = this.getClassFactory().getClassInspector();
      ClassNotFoundException var3 = null;
      boolean var4 = false;

      try {
         var4 = var2.accessible(var1);
      } catch (ClassNotFoundException var6) {
         var3 = var6;
      }

      if (!var4) {
         throw StandardException.newException("42X51", var3, new Object[]{var1});
      } else if (ClassInspector.primitiveType(var1)) {
         throw StandardException.newException("42Y37", new Object[]{var1});
      }
   }

   void setRefActionInfo(long var1, int[] var3, String var4, boolean var5) {
   }

   void generateAuthorizeCheck(ActivationClassBuilder var1, MethodBuilder var2, int var3) {
      var1.pushThisAsActivation(var2);
      var2.callMethod((short)185, (String)null, "getLanguageConnectionContext", "org.apache.derby.iapi.sql.conn.LanguageConnectionContext", 0);
      var2.callMethod((short)185, (String)null, "getAuthorizer", "org.apache.derby.iapi.sql.conn.Authorizer", 0);
      var1.pushThisAsActivation(var2);
      var2.push(var3);
      var2.callMethod((short)185, (String)null, "authorize", "void", 2);
   }

   public void checkReliability(String var1, int var2) throws StandardException {
      if ((this.getCompilerContext().getReliability() & var2) != 0) {
         this.throwReliabilityException(var1, var2);
      }

   }

   public void checkReliability(int var1, String var2) throws StandardException {
      if ((this.getCompilerContext().getReliability() & var1) != 0) {
         String var3 = MessageService.getTextMessage(var2, new Object[0]);
         this.throwReliabilityException(var3, var1);
      }

   }

   public DataTypeDescriptor bindUserType(DataTypeDescriptor var1) throws StandardException {
      if (var1.getCatalogType().isRowMultiSet()) {
         return this.bindRowMultiSet(var1);
      } else if (!var1.getTypeId().userType()) {
         return var1;
      } else {
         UserDefinedTypeIdImpl var2 = (UserDefinedTypeIdImpl)var1.getTypeId().getBaseTypeId();
         if (var2.isBound()) {
            return var1;
         } else {
            DataDictionary var3 = this.getDataDictionary();
            SchemaDescriptor var4 = this.getSchemaDescriptor(var2.getSchemaName());
            char var5 = 'A';
            String var6 = var2.getUnqualifiedName();
            AliasDescriptor var7 = var3.getAliasDescriptor(var4.getUUID().toString(), var6, var5);
            if (var7 == null) {
               throw StandardException.newException("42X94", new Object[]{AliasDescriptor.getAliasType(var5), var6});
            } else {
               this.createTypeDependency(var7);
               DataTypeDescriptor var8 = new DataTypeDescriptor(TypeId.getUserDefinedTypeId(var4.getSchemaName(), var6, var7.getJavaClassName()), var1.isNullable());
               return var8;
            }
         }
      }
   }

   public TypeDescriptor bindUserCatalogType(TypeDescriptor var1) throws StandardException {
      if (!var1.isUserDefinedType()) {
         return var1;
      } else {
         DataTypeDescriptor var2 = DataTypeDescriptor.getType(var1);
         var2 = this.bindUserType(var2);
         return var2.getCatalogType();
      }
   }

   public AliasDescriptor getUDTDesc(DataTypeDescriptor var1) throws StandardException {
      UserDefinedTypeIdImpl var2 = (UserDefinedTypeIdImpl)var1.getTypeId().getBaseTypeId();
      DataDictionary var3 = this.getDataDictionary();
      SchemaDescriptor var4 = this.getSchemaDescriptor(var2.getSchemaName());
      char var5 = 'A';
      String var6 = var2.getUnqualifiedName();
      AliasDescriptor var7 = var3.getAliasDescriptor(var4.getUUID().toString(), var6, var5);
      return var7;
   }

   void addUDTUsagePriv(List var1) throws StandardException {
      if (this.isPrivilegeCollectionRequired()) {
         for(ValueNode var3 : var1) {
            this.addUDTUsagePriv(var3);
         }

      }
   }

   void addUDTUsagePriv(ValueNode var1) throws StandardException {
      if (this.isPrivilegeCollectionRequired()) {
         DataTypeDescriptor var2 = var1.getTypeServices();
         if (var2 != null && var2.getTypeId().userType()) {
            AliasDescriptor var3 = this.getUDTDesc(var2);
            this.getCompilerContext().addRequiredUsagePriv(var3);
         }

      }
   }

   public DataTypeDescriptor bindRowMultiSet(DataTypeDescriptor var1) throws StandardException {
      if (!var1.getCatalogType().isRowMultiSet()) {
         return var1;
      } else {
         RowMultiSetImpl var2 = (RowMultiSetImpl)var1.getTypeId().getBaseTypeId();
         TypeDescriptor[] var3 = var2.getTypes();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            var3[var5] = this.bindUserCatalogType(var3[var5]);
         }

         var2.setTypes(var3);
         return var1;
      }
   }

   public void createTypeDependency(DataTypeDescriptor var1) throws StandardException {
      AliasDescriptor var2 = this.getDataDictionary().getAliasDescriptorForUDT((TransactionController)null, var1);
      if (var2 != null) {
         this.createTypeDependency(var2);
      }

   }

   private void createTypeDependency(AliasDescriptor var1) throws StandardException {
      this.getCompilerContext().createDependency(var1);
      if (this.isPrivilegeCollectionRequired() && !this.getCompilerContext().skippingTypePrivileges()) {
         this.getCompilerContext().addRequiredUsagePriv(var1);
      }

   }

   private void throwReliabilityException(String var1, int var2) throws StandardException {
      int var3 = this.getCompilerContext().getReliability();
      String var4;
      if (var3 == 1192) {
         var4 = "42Y84";
      } else if (var3 == 30329) {
         switch (var2) {
            case 8192 -> var4 = "42XA5";
            default -> var4 = "42XA2";
         }
      } else if ((var3 & var2 & 8192) != 0) {
         var4 = "42XA5";
      } else if (var3 == 18041) {
         var4 = "42Y39";
      } else {
         var4 = "42Y98";
      }

      throw StandardException.newException(var4, new Object[]{var1});
   }

   public int orReliability(int var1) {
      CompilerContext var2 = this.getCompilerContext();
      int var3 = var2.getReliability();
      var2.setReliability(var3 | var1);
      return var3;
   }

   public static void bindOffsetFetch(ValueNode var0, ValueNode var1) throws StandardException {
      if (var0 instanceof ConstantNode) {
         DataValueDescriptor var2 = ((ConstantNode)var0).getValue();
         long var3 = var2.getLong();
         if (var3 < 0L) {
            throw StandardException.newException("2201X", new Object[]{Long.toString(var3)});
         }
      } else if (var0 instanceof ParameterNode) {
         var0.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(-5), false));
      }

      if (var1 instanceof ConstantNode) {
         DataValueDescriptor var5 = ((ConstantNode)var1).getValue();
         long var6 = var5.getLong();
         if (var6 < 1L) {
            throw StandardException.newException("2201W", new Object[]{Long.toString(var6)});
         }
      } else if (var1 instanceof ParameterNode) {
         var1.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(-5), false));
      }

   }

   public SortedSet getOffsetOrderedNodes(Class var1) throws StandardException {
      OffsetOrderVisitor var2 = new OffsetOrderVisitor(var1, this.getBeginOffset(), this.getEndOffset() + 1);
      this.accept(var2);
      return var2.getNodes();
   }

   static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
