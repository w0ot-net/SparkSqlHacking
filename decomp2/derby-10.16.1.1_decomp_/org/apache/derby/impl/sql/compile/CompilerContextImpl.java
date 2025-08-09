package org.apache.derby.impl.sql.compile;

import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.compiler.JavaFactory;
import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.TypeCompilerFactory;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.VisitableFilter;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.LanguageConnectionFactory;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.PrivilegedSQLObject;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.sql.dictionary.StatementColumnPermission;
import org.apache.derby.iapi.sql.dictionary.StatementGenericPermission;
import org.apache.derby.iapi.sql.dictionary.StatementPermission;
import org.apache.derby.iapi.sql.dictionary.StatementRolePermission;
import org.apache.derby.iapi.sql.dictionary.StatementRoutinePermission;
import org.apache.derby.iapi.sql.dictionary.StatementSchemaPermission;
import org.apache.derby.iapi.sql.dictionary.StatementTablePermission;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.SortCostController;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class CompilerContextImpl extends ContextImpl implements CompilerContext {
   private static final int SCOPE_CELL = 0;
   private final Parser parser;
   private final LanguageConnectionContext lcc;
   private final LanguageConnectionFactory lcf;
   private TypeCompilerFactory typeCompilerFactory;
   private Dependent currentDependent;
   private DependencyManager dm;
   private boolean firstOnStack;
   private boolean inUse;
   private int reliability = 1024;
   private int nextColumnNumber = 1;
   private int nextTableNumber;
   private int nextSubqueryNumber;
   private int nextResultSetNumber;
   private int scanIsolationLevel;
   private int nextEquivalenceClass = -1;
   private long nextClassName;
   private List savedObjects;
   private String classPrefix;
   private SchemaDescriptor compilationSchema;
   private ArrayList privilegeCheckFilters;
   private HashMap namedScopes;
   private boolean skippingTypePrivileges;
   private ArrayList defaultSchemaStack;
   private ProviderList currentAPL;
   private boolean returnParameterFlag;
   private final HashMap storeCostControllers = new HashMap();
   private SortCostController sortCostController;
   private List parameterList;
   private DataTypeDescriptor[] parameterDescriptors;
   private Object cursorInfo;
   private SQLWarning warnings;
   private final ArrayList privTypeStack = new ArrayList();
   private int currPrivType = -1;
   private HashMap requiredColumnPrivileges;
   private HashMap requiredTablePrivileges;
   private HashMap requiredSchemaPrivileges;
   private HashMap requiredRoutinePrivileges;
   private HashMap requiredUsagePrivileges;
   private HashMap requiredRolePrivileges;
   private HashMap referencedSequences;

   public void cleanupOnError(Throwable var1) throws StandardException {
      this.setInUse(false);
      this.resetContext();
      if (var1 instanceof StandardException var2) {
         int var3 = var2.getSeverity();
         if (var3 < 50000) {
            if (this.currentDependent != null) {
               this.currentDependent.makeInvalid(0, this.lcc);
            }

            this.closeStoreCostControllers();
            this.closeSortCostControllers();
         }

         if (var3 >= 40000) {
            this.popMe();
         }
      }

   }

   public void resetContext() {
      this.nextColumnNumber = 1;
      this.nextTableNumber = 0;
      this.nextSubqueryNumber = 0;
      this.resetNextResultSetNumber();
      this.nextEquivalenceClass = -1;
      this.compilationSchema = null;
      this.parameterList = null;
      this.parameterDescriptors = null;
      this.scanIsolationLevel = 0;
      this.warnings = null;
      this.savedObjects = null;
      this.reliability = 1024;
      this.returnParameterFlag = false;
      this.initRequiredPriv();
      this.defaultSchemaStack = null;
      this.referencedSequences = null;
      this.privilegeCheckFilters = null;
      this.namedScopes = null;
      this.skippingTypePrivileges = false;
   }

   public Parser getParser() {
      return this.parser;
   }

   public OptimizerFactory getOptimizerFactory() {
      return this.lcf.getOptimizerFactory();
   }

   public int getNextColumnNumber() {
      return this.nextColumnNumber++;
   }

   public int getNextTableNumber() {
      return this.nextTableNumber++;
   }

   public int getNumTables() {
      return this.nextTableNumber;
   }

   public int getNextSubqueryNumber() {
      return this.nextSubqueryNumber++;
   }

   public int getNumSubquerys() {
      return this.nextSubqueryNumber;
   }

   public int getNextResultSetNumber() {
      return this.nextResultSetNumber++;
   }

   public void resetNextResultSetNumber() {
      this.nextResultSetNumber = 0;
   }

   public int getNumResultSets() {
      return this.nextResultSetNumber;
   }

   public String getUniqueClassName() {
      return this.classPrefix.concat(Long.toHexString((long)(this.nextClassName++)));
   }

   public int getNextEquivalenceClass() {
      return ++this.nextEquivalenceClass;
   }

   public ClassFactory getClassFactory() {
      return this.lcf.getClassFactory();
   }

   public JavaFactory getJavaFactory() {
      return this.lcf.getJavaFactory();
   }

   public void setCurrentDependent(Dependent var1) {
      this.currentDependent = var1;
   }

   public ProviderList getCurrentAuxiliaryProviderList() {
      return this.currentAPL;
   }

   public void setCurrentAuxiliaryProviderList(ProviderList var1) {
      this.currentAPL = var1;
   }

   public void createDependency(Provider var1) throws StandardException {
      if (this.dm == null) {
         this.dm = this.lcc.getDataDictionary().getDependencyManager();
      }

      this.dm.addDependency(this.currentDependent, var1, this.getContextManager());
      this.addProviderToAuxiliaryList(var1);
   }

   public void createDependency(Dependent var1, Provider var2) throws StandardException {
      if (this.dm == null) {
         this.dm = this.lcc.getDataDictionary().getDependencyManager();
      }

      this.dm.addDependency(var1, var2, this.getContextManager());
      this.addProviderToAuxiliaryList(var2);
   }

   private void addProviderToAuxiliaryList(Provider var1) {
      if (this.currentAPL != null) {
         this.currentAPL.addProvider(var1);
      }

   }

   public int addSavedObject(Object var1) {
      if (this.savedObjects == null) {
         this.savedObjects = new ArrayList();
      }

      this.savedObjects.add(var1);
      return this.savedObjects.size() - 1;
   }

   public Object[] getSavedObjects() {
      if (this.savedObjects == null) {
         return null;
      } else {
         Object[] var1 = this.savedObjects.toArray();
         this.savedObjects = null;
         return var1;
      }
   }

   public void setSavedObjects(List var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         this.addSavedObject(var2.next());
      }

   }

   public void setCursorInfo(Object var1) {
      this.cursorInfo = var1;
   }

   public Object getCursorInfo() {
      return this.cursorInfo;
   }

   public void firstOnStack() {
      this.firstOnStack = true;
   }

   public boolean isFirstOnStack() {
      return this.firstOnStack;
   }

   public void setInUse(boolean var1) {
      this.inUse = var1;
      if (!var1) {
         this.closeStoreCostControllers();
         this.closeSortCostControllers();
      }

   }

   public boolean getInUse() {
      return this.inUse;
   }

   public void setReliability(int var1) {
      this.reliability = var1;
   }

   public int getReliability() {
      return this.reliability;
   }

   public StoreCostController getStoreCostController(long var1) throws StandardException {
      Long var3 = var1;
      StoreCostController var4 = (StoreCostController)this.storeCostControllers.get(var3);
      if (var4 == null) {
         var4 = this.lcc.getTransactionCompile().openStoreCost(var1);
         this.storeCostControllers.put(var3, var4);
      }

      return var4;
   }

   private void closeStoreCostControllers() {
      for(StoreCostController var2 : this.storeCostControllers.values()) {
         try {
            var2.close();
         } catch (StandardException var4) {
         }
      }

      this.storeCostControllers.clear();
   }

   public SortCostController getSortCostController() throws StandardException {
      if (this.sortCostController == null) {
         this.sortCostController = this.lcc.getTransactionCompile().openSortCostController();
      }

      return this.sortCostController;
   }

   private void closeSortCostControllers() {
      if (this.sortCostController != null) {
         this.sortCostController.close();
         this.sortCostController = null;
      }

   }

   public SchemaDescriptor getCompilationSchema() {
      return this.compilationSchema;
   }

   public SchemaDescriptor setCompilationSchema(SchemaDescriptor var1) {
      SchemaDescriptor var2 = this.compilationSchema;
      this.compilationSchema = var1;
      return var2;
   }

   public void pushCompilationSchema(SchemaDescriptor var1) {
      if (this.defaultSchemaStack == null) {
         this.defaultSchemaStack = new ArrayList(2);
      }

      this.defaultSchemaStack.add(this.defaultSchemaStack.size(), this.getCompilationSchema());
      this.setCompilationSchema(var1);
   }

   public void popCompilationSchema() {
      SchemaDescriptor var1 = (SchemaDescriptor)this.defaultSchemaStack.remove(this.defaultSchemaStack.size() - 1);
      this.setCompilationSchema(var1);
   }

   public void setParameterList(List var1) {
      this.parameterList = var1;
      int var2 = var1 == null ? 0 : var1.size();
      if (var2 > 0) {
         this.parameterDescriptors = new DataTypeDescriptor[var2];
      }

   }

   public List getParameterList() {
      return this.parameterList;
   }

   public void setReturnParameterFlag() {
      this.returnParameterFlag = true;
   }

   public boolean getReturnParameterFlag() {
      return this.returnParameterFlag;
   }

   DataTypeDescriptor[] getParameterTypes() {
      return this.parameterDescriptors;
   }

   public void setScanIsolationLevel(int var1) {
      this.scanIsolationLevel = var1;
   }

   public int getScanIsolationLevel() {
      return this.scanIsolationLevel;
   }

   public TypeCompilerFactory getTypeCompilerFactory() {
      return this.typeCompilerFactory;
   }

   public void addWarning(SQLWarning var1) {
      if (this.warnings == null) {
         this.warnings = var1;
      } else {
         this.warnings.setNextWarning(var1);
      }

   }

   public SQLWarning getWarnings() {
      return this.warnings;
   }

   public CompilerContextImpl(ContextManager var1, LanguageConnectionContext var2, TypeCompilerFactory var3) {
      super(var1, "CompilerContext");
      this.lcc = var2;
      this.lcf = var2.getLanguageConnectionFactory();
      this.parser = this.lcf.newParser(this);
      this.typeCompilerFactory = var3;
      String var10001 = this.lcf.getUUIDFactory().createUUID().toString();
      this.classPrefix = "ac" + var10001.replace('-', 'x');
      this.initRequiredPriv();
   }

   private void initRequiredPriv() {
      this.currPrivType = -1;
      this.privTypeStack.clear();
      this.requiredColumnPrivileges = null;
      this.requiredTablePrivileges = null;
      this.requiredSchemaPrivileges = null;
      this.requiredRoutinePrivileges = null;
      this.requiredUsagePrivileges = null;
      this.requiredRolePrivileges = null;
      if (this.lcc.usesSqlAuthorization()) {
         this.requiredColumnPrivileges = new HashMap();
         this.requiredTablePrivileges = new HashMap();
         this.requiredSchemaPrivileges = new HashMap();
         this.requiredRoutinePrivileges = new HashMap();
         this.requiredUsagePrivileges = new HashMap();
         this.requiredRolePrivileges = new HashMap();
      }

   }

   public void pushCurrentPrivType(int var1) {
      this.privTypeStack.add(this.currPrivType);
      this.currPrivType = var1;
   }

   public void popCurrentPrivType() {
      Integer var1 = (Integer)this.privTypeStack.remove(this.privTypeStack.size() - 1);
      this.currPrivType = var1;
   }

   public void addRequiredColumnPriv(ColumnDescriptor var1) {
      if (this.requiredColumnPrivileges != null && this.currPrivType != -1 && this.currPrivType != 4 && this.currPrivType != 3 && this.currPrivType != 5 && this.currPrivType != 6 && var1 != null) {
         TableDescriptor var2 = var1.getTableDescriptor();
         if (var2 != null) {
            if (var2.getTableType() != 3) {
               UUID var3 = var2.getUUID();
               if (this.currPrivType == 8) {
                  StatementTablePermission var4 = new StatementTablePermission(var3, 0);
                  if (this.requiredColumnPrivileges.containsKey(var4) || this.requiredTablePrivileges.containsKey(var4)) {
                     return;
                  }
               }

               if (this.currPrivType == 0) {
                  StatementTablePermission var6 = new StatementTablePermission(var3, 8);
                  this.requiredColumnPrivileges.remove(var6);
               }

               StatementTablePermission var7 = new StatementTablePermission(var3, this.currPrivType);
               StatementColumnPermission var5 = (StatementColumnPermission)this.requiredColumnPrivileges.get(var7);
               if (var5 == null) {
                  var5 = new StatementColumnPermission(var3, this.currPrivType, new FormatableBitSet(var2.getNumberOfColumns()));
                  this.requiredColumnPrivileges.put(var7, var5);
               }

               var5.getColumns().set(var1.getPosition() - 1);
            }
         }
      }
   }

   public void addRequiredTablePriv(TableDescriptor var1) {
      if (this.requiredTablePrivileges != null && var1 != null) {
         if (var1.getTableType() != 3) {
            if (this.currPrivType == 0) {
               StatementTablePermission var2 = new StatementTablePermission(var1.getUUID(), 8);
               this.requiredColumnPrivileges.remove(var2);
            }

            StatementTablePermission var3 = new StatementTablePermission(var1.getUUID(), this.currPrivType);
            this.requiredTablePrivileges.put(var3, var3);
         }
      }
   }

   public void addRequiredRoutinePriv(AliasDescriptor var1) {
      if (this.requiredRoutinePrivileges != null && var1 != null) {
         if (!var1.getSchemaUUID().toString().equals("c013800d-00fb-2642-07ec-000000134f30")) {
            if (this.requiredRoutinePrivileges.get(var1.getUUID()) == null) {
               this.requiredRoutinePrivileges.put(var1.getUUID(), 1);
            }

         }
      }
   }

   public void addRequiredUsagePriv(PrivilegedSQLObject var1) {
      if (this.requiredUsagePrivileges != null && var1 != null) {
         UUID var2 = var1.getUUID();
         String var3 = var1.getObjectTypeName();
         if (this.requiredUsagePrivileges.get(var2) == null) {
            this.requiredUsagePrivileges.put(var2, var3);
         }

      }
   }

   public void addRequiredSchemaPriv(String var1, String var2, int var3) {
      if (this.requiredSchemaPrivileges != null && var1 != null) {
         StatementSchemaPermission var4 = new StatementSchemaPermission(var1, var2, var3);
         this.requiredSchemaPrivileges.put(var4, var4);
      }
   }

   public void addRequiredRolePriv(String var1, int var2) {
      if (this.requiredRolePrivileges != null) {
         StatementRolePermission var3 = new StatementRolePermission(var1, var2);
         this.requiredRolePrivileges.put(var3, var3);
      }
   }

   public List getRequiredPermissionsList() {
      int var1 = 0;
      if (this.requiredRoutinePrivileges != null) {
         var1 += this.requiredRoutinePrivileges.size();
      }

      if (this.requiredUsagePrivileges != null) {
         var1 += this.requiredUsagePrivileges.size();
      }

      if (this.requiredTablePrivileges != null) {
         var1 += this.requiredTablePrivileges.size();
      }

      if (this.requiredSchemaPrivileges != null) {
         var1 += this.requiredSchemaPrivileges.size();
      }

      if (this.requiredColumnPrivileges != null) {
         var1 += this.requiredColumnPrivileges.size();
      }

      if (this.requiredRolePrivileges != null) {
         var1 += this.requiredRolePrivileges.size();
      }

      ArrayList var2 = new ArrayList(var1);
      if (this.requiredRoutinePrivileges != null) {
         for(UUID var4 : this.requiredRoutinePrivileges.keySet()) {
            var2.add(new StatementRoutinePermission(var4));
         }
      }

      if (this.requiredUsagePrivileges != null) {
         for(UUID var10 : this.requiredUsagePrivileges.keySet()) {
            var2.add(new StatementGenericPermission(var10, (String)this.requiredUsagePrivileges.get(var10), "USAGE"));
         }
      }

      if (this.requiredTablePrivileges != null) {
         Iterator var6 = this.requiredTablePrivileges.values().iterator();

         while(var6.hasNext()) {
            var2.add((StatementPermission)var6.next());
         }
      }

      if (this.requiredSchemaPrivileges != null) {
         Iterator var7 = this.requiredSchemaPrivileges.values().iterator();

         while(var7.hasNext()) {
            var2.add((StatementPermission)var7.next());
         }
      }

      if (this.requiredColumnPrivileges != null) {
         Iterator var8 = this.requiredColumnPrivileges.values().iterator();

         while(var8.hasNext()) {
            var2.add((StatementPermission)var8.next());
         }
      }

      if (this.requiredRolePrivileges != null) {
         Iterator var9 = this.requiredRolePrivileges.values().iterator();

         while(var9.hasNext()) {
            var2.add((StatementPermission)var9.next());
         }
      }

      return var2;
   }

   public void addReferencedSequence(SequenceDescriptor var1) {
      if (this.referencedSequences == null) {
         this.referencedSequences = new HashMap();
      }

      this.referencedSequences.put(var1.getUUID(), var1);
   }

   public boolean isReferenced(SequenceDescriptor var1) {
      return this.referencedSequences == null ? false : this.referencedSequences.containsKey(var1.getUUID());
   }

   public void addPrivilegeFilter(VisitableFilter var1) {
      if (this.privilegeCheckFilters == null) {
         this.privilegeCheckFilters = new ArrayList();
      }

      this.privilegeCheckFilters.add(var1);
   }

   public void removePrivilegeFilter(VisitableFilter var1) {
      if (var1 != null && this.privilegeCheckFilters != null) {
         this.privilegeCheckFilters.remove(var1);
      }

   }

   public boolean passesPrivilegeFilters(Visitable var1) throws StandardException {
      if (this.privilegeCheckFilters == null) {
         return true;
      } else {
         for(VisitableFilter var3 : this.privilegeCheckFilters) {
            if (!var3.accept(var1)) {
               return false;
            }
         }

         return true;
      }
   }

   public void beginScope(String var1) {
      if (this.namedScopes == null) {
         this.namedScopes = new HashMap();
      }

      int[] var2 = (int[])this.namedScopes.get(var1);
      if (var2 == null) {
         var2 = new int[1];
         this.namedScopes.put(var1, var2);
      }

      int var10002 = var2[0]++;
   }

   public void endScope(String var1) {
      if (this.namedScopes != null) {
         int[] var2 = (int[])this.namedScopes.get(var1);
         if (var2 != null) {
            int var10002 = var2[0]--;
            if (var2[0] <= 0) {
               this.namedScopes.remove(var1);
            }

         }
      }
   }

   public int scopeDepth(String var1) {
      if (this.namedScopes == null) {
         return 0;
      } else {
         int[] var2 = (int[])this.namedScopes.get(var1);
         return var2 == null ? 0 : var2[0];
      }
   }

   public boolean skipTypePrivileges(boolean var1) {
      boolean var2 = this.skippingTypePrivileges;
      this.skippingTypePrivileges = var1;
      return var2;
   }

   public boolean skippingTypePrivileges() {
      return this.skippingTypePrivileges;
   }
}
