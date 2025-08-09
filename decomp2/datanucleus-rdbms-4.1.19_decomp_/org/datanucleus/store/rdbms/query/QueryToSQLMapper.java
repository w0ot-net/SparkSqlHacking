package org.datanucleus.store.rdbms.query;

import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchGroup;
import org.datanucleus.FetchGroupManager;
import org.datanucleus.FetchPlan;
import org.datanucleus.FetchPlanForClass;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.metadata.VersionStrategy;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.query.NullOrderingType;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.evaluator.AbstractExpressionEvaluator;
import org.datanucleus.query.expression.ArrayExpression;
import org.datanucleus.query.expression.CaseExpression;
import org.datanucleus.query.expression.ClassExpression;
import org.datanucleus.query.expression.CreatorExpression;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.JoinExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.OrderExpression;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.SubqueryExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.query.expression.JoinExpression.JoinType;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.VersionHelper;
import org.datanucleus.store.query.QueryCompilerSyntaxException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.AbstractContainerMapping;
import org.datanucleus.store.rdbms.mapping.java.DatastoreIdMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableIdMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.StringMapping;
import org.datanucleus.store.rdbms.mapping.java.TemporalMapping;
import org.datanucleus.store.rdbms.sql.SQLJoin;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLTableGroup;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.BooleanLiteral;
import org.datanucleus.store.rdbms.sql.expression.BooleanSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.CaseBooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.CaseNumericExpression;
import org.datanucleus.store.rdbms.sql.expression.CaseStringExpression;
import org.datanucleus.store.rdbms.sql.expression.CollectionLiteral;
import org.datanucleus.store.rdbms.sql.expression.ColumnExpression;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.IntegerLiteral;
import org.datanucleus.store.rdbms.sql.expression.MapExpression;
import org.datanucleus.store.rdbms.sql.expression.NewObjectExpression;
import org.datanucleus.store.rdbms.sql.expression.NullLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.NumericSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.ResultAliasExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.StringSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.SubqueryExpressionComponent;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalLiteral;
import org.datanucleus.store.rdbms.sql.expression.TemporalSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.UnboundExpression;
import org.datanucleus.store.rdbms.table.ClassTable;
import org.datanucleus.store.rdbms.table.CollectionTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.ElementContainerTable;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Imports;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class QueryToSQLMapper extends AbstractExpressionEvaluator implements QueryGenerator {
   public static final String OPTION_CASE_INSENSITIVE = "CASE_INSENSITIVE";
   public static final String OPTION_EXPLICIT_JOINS = "EXPLICIT_JOINS";
   public static final String OPTION_BULK_UPDATE_VERSION = "BULK_UPDATE_VERSION";
   public static final String OPTION_BULK_DELETE_NO_RESULT = "BULK_DELETE_NO_RESULT";
   public static final String OPTION_SELECT_CANDIDATE_ID_ONLY = "RESULT_CANDIDATE_ID";
   public static final String OPTION_NULL_PARAM_USE_IS_NULL = "USE_IS_NULL_FOR_NULL_PARAM";
   public static final String MAP_VALUE_ALIAS_SUFFIX = "_VALUE";
   final String candidateAlias;
   final AbstractClassMetaData candidateCmd;
   final boolean subclasses;
   final QueryCompilation compilation;
   final Map parameters;
   Map parameterValueByName = null;
   Map paramNameByPosition = null;
   int positionalParamNumber = -1;
   Map extensionsByName = null;
   final SQLStatement stmt;
   final StatementClassMapping resultDefinitionForClass;
   final StatementResultMapping resultDefinition;
   Map expressionForParameter;
   final RDBMSStoreManager storeMgr;
   final FetchPlan fetchPlan;
   final SQLExpressionFactory exprFactory;
   ExecutionContext ec;
   ClassLoaderResolver clr;
   Imports importsDefinition = null;
   Map compileProperties = new HashMap();
   CompilationComponent compileComponent;
   Deque stack = new ArrayDeque();
   Map sqlTableByPrimary = new HashMap();
   Set resultAliases = null;
   Map explicitJoinPrimaryByAlias = null;
   Map paramMappingForName = new HashMap();
   Set options = new HashSet();
   public QueryToSQLMapper parentMapper = null;
   SQLJoin.JoinType defaultJoinType = null;
   boolean precompilable = true;
   boolean processingOnClause = false;

   public QueryToSQLMapper(SQLStatement stmt, QueryCompilation compilation, Map parameters, StatementClassMapping resultDefForClass, StatementResultMapping resultDef, AbstractClassMetaData cmd, boolean subclasses, FetchPlan fetchPlan, ExecutionContext ec, Imports importsDefinition, Set options, Map extensions) {
      this.parameters = parameters;
      this.compilation = compilation;
      this.stmt = stmt;
      this.resultDefinitionForClass = resultDefForClass;
      this.resultDefinition = resultDef;
      this.candidateCmd = cmd;
      this.candidateAlias = compilation.getCandidateAlias();
      this.subclasses = subclasses;
      this.fetchPlan = fetchPlan;
      this.storeMgr = stmt.getRDBMSManager();
      this.exprFactory = stmt.getRDBMSManager().getSQLExpressionFactory();
      this.ec = ec;
      this.clr = ec.getClassLoaderResolver();
      this.importsDefinition = importsDefinition;
      if (options != null) {
         this.options.addAll(options);
      }

      this.extensionsByName = extensions;
      this.stmt.setQueryGenerator(this);
      SQLTableMapping tblMapping = new SQLTableMapping(stmt.getPrimaryTable(), this.candidateCmd, stmt.getPrimaryTable().getTable().getIdMapping());
      this.setSQLTableMappingForAlias(this.candidateAlias, tblMapping);
   }

   void setDefaultJoinType(SQLJoin.JoinType joinType) {
      this.defaultJoinType = joinType;
   }

   void setParentMapper(QueryToSQLMapper parent) {
      this.parentMapper = parent;
   }

   public String getQueryLanguage() {
      return this.compilation.getQueryLanguage();
   }

   public ClassLoaderResolver getClassLoaderResolver() {
      return this.clr;
   }

   public CompilationComponent getCompilationComponent() {
      return this.compileComponent;
   }

   public ExecutionContext getExecutionContext() {
      return this.ec;
   }

   public Object getProperty(String name) {
      return this.compileProperties.get(name);
   }

   public boolean isPrecompilable() {
      return this.precompilable;
   }

   protected void setNotPrecompilable() {
      if (this.parentMapper != null) {
         this.parentMapper.setNotPrecompilable();
      }

      this.precompilable = false;
   }

   public Map getParameterNameByPosition() {
      return this.paramNameByPosition;
   }

   public void compile() {
      this.compileFrom();
      this.compileFilter();
      if (this.compilation.getResultDistinct()) {
         this.stmt.setDistinct(true);
      } else if (!this.options.contains("EXPLICIT_JOINS") && this.compilation.getExprResult() == null && this.stmt.getNumberOfTableGroups() > 1) {
         this.stmt.setDistinct(true);
      }

      this.compileResult();
      this.compileGrouping();
      this.compileHaving();
      this.compileOrdering();
      Collection<String> symbols = this.compilation.getSymbolTable().getSymbolNames();
      Iterator<String> symIter = symbols.iterator();

      while(symIter.hasNext()) {
         Symbol sym = this.compilation.getSymbolTable().getSymbol((String)symIter.next());
         if (sym.getType() == 2 && this.compilation.getCompilationForSubquery(sym.getQualifiedName()) == null && !this.hasSQLTableMappingForAlias(sym.getQualifiedName())) {
            throw new QueryCompilerSyntaxException("Query has variable \"" + sym.getQualifiedName() + "\" which is not bound to the query");
         }
      }

   }

   protected void compileFrom() {
      if (this.compilation.getExprFrom() != null) {
         this.compileComponent = CompilationComponent.FROM;
         Expression[] fromExprs = this.compilation.getExprFrom();

         for(int i = 0; i < fromExprs.length; ++i) {
            ClassExpression clsExpr = (ClassExpression)fromExprs[i];
            this.compileFromClassExpression(clsExpr);
         }

         this.compileComponent = null;
      }

   }

   protected void compileFilter() {
      if (this.compilation.getExprFilter() != null) {
         this.compileComponent = CompilationComponent.FILTER;
         if (QueryUtils.expressionHasOrOperator(this.compilation.getExprFilter())) {
            this.compileProperties.put("Filter.OR", true);
         }

         if (QueryUtils.expressionHasNotOperator(this.compilation.getExprFilter())) {
            this.compileProperties.put("Filter.NOT", true);
         }

         SQLExpression filterSqlExpr = (SQLExpression)this.compilation.getExprFilter().evaluate(this);
         if (!(filterSqlExpr instanceof BooleanExpression)) {
            throw new QueryCompilerSyntaxException("Filter compiles to something that is not a boolean expression. Kindly fix your query : " + filterSqlExpr);
         }

         BooleanExpression filterExpr = (BooleanExpression)filterSqlExpr;
         filterExpr = this.getBooleanExpressionForUseInFilter(filterExpr);
         this.stmt.whereAnd(filterExpr, true);
         this.compileComponent = null;
      }

   }

   protected void compileResult() {
      if (this.compilation.getExprUpdate() == null) {
         if (this.compilation.getExprResult() != null) {
            this.compileComponent = CompilationComponent.RESULT;
            Expression[] resultExprs = this.compilation.getExprResult();

            for(int i = 0; i < resultExprs.length; ++i) {
               String alias = resultExprs[i].getAlias();
               if (alias != null && this.resultAliases == null) {
                  this.resultAliases = new HashSet();
               }

               if (!(resultExprs[i] instanceof InvokeExpression)) {
                  if (resultExprs[i] instanceof PrimaryExpression) {
                     PrimaryExpression primExpr = (PrimaryExpression)resultExprs[i];
                     if (primExpr.getId().equals(this.candidateAlias)) {
                        StatementClassMapping map = new StatementClassMapping(this.candidateCmd.getFullClassName(), (String)null);
                        SQLStatementHelper.selectFetchPlanOfCandidateInStatement(this.stmt, map, this.candidateCmd, this.fetchPlan, 1);
                        this.resultDefinition.addMappingForResultExpression(i, map);
                     } else {
                        this.processPrimaryExpression(primExpr);
                        SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
                        this.validateExpressionForResult(sqlExpr);
                        if (sqlExpr instanceof SQLLiteral) {
                           int[] cols = this.stmt.select(sqlExpr, alias);
                           StatementMappingIndex idx = new StatementMappingIndex(sqlExpr.getJavaTypeMapping());
                           idx.setColumnPositions(cols);
                           if (alias != null) {
                              this.resultAliases.add(alias);
                              idx.setColumnAlias(alias);
                           }

                           this.resultDefinition.addMappingForResultExpression(i, idx);
                        } else {
                           int[] cols = this.stmt.select(sqlExpr.getSQLTable(), sqlExpr.getJavaTypeMapping(), alias);
                           StatementMappingIndex idx = new StatementMappingIndex(sqlExpr.getJavaTypeMapping());
                           idx.setColumnPositions(cols);
                           if (alias != null) {
                              this.resultAliases.add(alias);
                              idx.setColumnAlias(alias);
                           }

                           this.resultDefinition.addMappingForResultExpression(i, idx);
                        }
                     }
                  } else if (resultExprs[i] instanceof ParameterExpression) {
                     this.processParameterExpression((ParameterExpression)resultExprs[i], true);
                     SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
                     this.validateExpressionForResult(sqlExpr);
                     int[] cols = this.stmt.select(sqlExpr, alias);
                     StatementMappingIndex idx = new StatementMappingIndex(sqlExpr.getJavaTypeMapping());
                     idx.setColumnPositions(cols);
                     if (alias != null) {
                        this.resultAliases.add(alias);
                        idx.setColumnAlias(alias);
                     }

                     this.resultDefinition.addMappingForResultExpression(i, idx);
                  } else if (resultExprs[i] instanceof VariableExpression) {
                     this.processVariableExpression((VariableExpression)resultExprs[i]);
                     SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
                     this.validateExpressionForResult(sqlExpr);
                     if (sqlExpr instanceof UnboundExpression) {
                        this.processUnboundExpression((UnboundExpression)sqlExpr);
                        sqlExpr = (SQLExpression)this.stack.pop();
                        NucleusLogger.QUERY.debug("QueryToSQL.exprResult variable was still unbound, so binding via cross-join");
                     }

                     StatementMappingIndex idx = new StatementMappingIndex(sqlExpr.getJavaTypeMapping());
                     int[] cols = this.stmt.select(sqlExpr, alias);
                     idx.setColumnPositions(cols);
                     if (alias != null) {
                        this.resultAliases.add(alias);
                        idx.setColumnAlias(alias);
                     }

                     this.resultDefinition.addMappingForResultExpression(i, idx);
                  } else if (resultExprs[i] instanceof Literal) {
                     this.processLiteral((Literal)resultExprs[i]);
                     SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
                     this.validateExpressionForResult(sqlExpr);
                     int[] cols = this.stmt.select(sqlExpr, alias);
                     StatementMappingIndex idx = new StatementMappingIndex(sqlExpr.getJavaTypeMapping());
                     idx.setColumnPositions(cols);
                     if (alias != null) {
                        this.resultAliases.add(alias);
                        idx.setColumnAlias(alias);
                     }

                     this.resultDefinition.addMappingForResultExpression(i, idx);
                  } else if (resultExprs[i] instanceof CreatorExpression) {
                     this.processCreatorExpression((CreatorExpression)resultExprs[i]);
                     NewObjectExpression sqlExpr = (NewObjectExpression)this.stack.pop();
                     StatementNewObjectMapping stmtMap = this.getStatementMappingForNewObjectExpression(sqlExpr);
                     this.resultDefinition.addMappingForResultExpression(i, stmtMap);
                  } else if (resultExprs[i] instanceof DyadicExpression) {
                     resultExprs[i].evaluate(this);
                     SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
                     int[] cols = this.stmt.select(sqlExpr, alias);
                     StatementMappingIndex idx = new StatementMappingIndex(sqlExpr.getJavaTypeMapping());
                     idx.setColumnPositions(cols);
                     if (alias != null) {
                        this.resultAliases.add(alias);
                        idx.setColumnAlias(alias);
                     }

                     this.resultDefinition.addMappingForResultExpression(i, idx);
                  } else {
                     if (!(resultExprs[i] instanceof CaseExpression)) {
                        throw new NucleusException("Dont currently support result clause containing expression of type " + resultExprs[i]);
                     }

                     resultExprs[i].evaluate(this);
                     SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
                     int[] cols = this.stmt.select(sqlExpr, alias);
                     StatementMappingIndex idx = new StatementMappingIndex(sqlExpr.getJavaTypeMapping());
                     idx.setColumnPositions(cols);
                     if (alias != null) {
                        this.resultAliases.add(alias);
                        idx.setColumnAlias(alias);
                     }

                     this.resultDefinition.addMappingForResultExpression(i, idx);
                  }
               } else {
                  InvokeExpression invokeExpr = (InvokeExpression)resultExprs[i];
                  this.processInvokeExpression((InvokeExpression)resultExprs[i]);
                  SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
                  this.validateExpressionForResult(sqlExpr);
                  String invokeMethod = invokeExpr.getOperation();
                  if ((invokeMethod.equalsIgnoreCase("mapKey") || invokeMethod.equalsIgnoreCase("mapValue")) && alias == null) {
                     if (sqlExpr.getJavaTypeMapping() instanceof PersistableMapping) {
                        String selectedType = ((PersistableMapping)sqlExpr.getJavaTypeMapping()).getType();
                        AbstractClassMetaData selectedCmd = this.ec.getMetaDataManager().getMetaDataForClass(selectedType, this.clr);
                        FetchPlanForClass fpForCmd = this.fetchPlan.getFetchPlanForClass(selectedCmd);
                        int[] membersToSelect = fpForCmd.getMemberNumbers();
                        ClassTable selectedTable = (ClassTable)sqlExpr.getSQLTable().getTable();
                        StatementClassMapping map = new StatementClassMapping(selectedCmd.getFullClassName(), (String)null);
                        if (selectedCmd.getIdentityType() == IdentityType.DATASTORE) {
                           int[] cols = this.stmt.select(sqlExpr.getSQLTable(), selectedTable.getDatastoreIdMapping(), alias);
                           StatementMappingIndex idx = new StatementMappingIndex(selectedTable.getDatastoreIdMapping());
                           idx.setColumnPositions(cols);
                           map.addMappingForMember(-1, idx);
                        }

                        for(int j = 0; j < membersToSelect.length; ++j) {
                           AbstractMemberMetaData selMmd = selectedCmd.getMetaDataForManagedMemberAtAbsolutePosition(j);
                           SQLStatementHelper.selectMemberOfSourceInStatement(this.stmt, map, this.fetchPlan, sqlExpr.getSQLTable(), selMmd, this.clr, 1, (SQLJoin.JoinType)null);
                        }

                        this.resultDefinition.addMappingForResultExpression(i, map);
                        continue;
                     }

                     if (sqlExpr.getJavaTypeMapping() instanceof EmbeddedMapping) {
                        EmbeddedMapping embMapping = (EmbeddedMapping)sqlExpr.getJavaTypeMapping();
                        String selectedType = embMapping.getType();
                        AbstractClassMetaData selectedCmd = this.ec.getMetaDataManager().getMetaDataForClass(selectedType, this.clr);
                        FetchPlanForClass fpForCmd = this.fetchPlan.getFetchPlanForClass(selectedCmd);
                        int[] membersToSelect = fpForCmd.getMemberNumbers();
                        StatementClassMapping map = new StatementClassMapping(selectedCmd.getFullClassName(), (String)null);

                        for(int j = 0; j < membersToSelect.length; ++j) {
                           AbstractMemberMetaData selMmd = selectedCmd.getMetaDataForManagedMemberAtAbsolutePosition(j);
                           JavaTypeMapping selMapping = embMapping.getJavaTypeMapping(selMmd.getName());
                           if (selMapping.includeInFetchStatement()) {
                              int[] cols = this.stmt.select(sqlExpr.getSQLTable(), selMapping, alias);
                              StatementMappingIndex idx = new StatementMappingIndex(selMapping);
                              idx.setColumnPositions(cols);
                              map.addMappingForMember(membersToSelect[j], idx);
                           }
                        }

                        this.resultDefinition.addMappingForResultExpression(i, map);
                        continue;
                     }
                  }

                  StatementMappingIndex idx = new StatementMappingIndex(sqlExpr.getJavaTypeMapping());
                  int[] cols = this.stmt.select(sqlExpr, alias);
                  idx.setColumnPositions(cols);
                  if (alias != null) {
                     this.resultAliases.add(alias);
                     idx.setColumnAlias(alias);
                  }

                  this.resultDefinition.addMappingForResultExpression(i, idx);
               }
            }

            if (this.stmt.getNumberOfSelects() == 0) {
               this.stmt.select(this.exprFactory.newLiteral(this.stmt, this.storeMgr.getMappingManager().getMapping(Integer.class), 1), (String)null);
            }
         } else if (!this.options.contains("BULK_DELETE_NO_RESULT")) {
            this.compileComponent = CompilationComponent.RESULT;
            if (this.candidateCmd.getIdentityType() == IdentityType.NONDURABLE) {
               if (NucleusLogger.QUERY.isDebugEnabled()) {
                  NucleusLogger.QUERY.debug(Localiser.msg("052520", new Object[]{this.candidateCmd.getFullClassName()}));
               }

               this.fetchPlan.setGroup("all");
            }

            if (this.subclasses) {
            }

            int maxFetchDepth = this.fetchPlan.getMaxFetchDepth();
            if (maxFetchDepth < 0) {
               maxFetchDepth = 3;
            }

            if (this.options.contains("RESULT_CANDIDATE_ID")) {
               SQLStatementHelper.selectIdentityOfCandidateInStatement(this.stmt, this.resultDefinitionForClass, this.candidateCmd);
            } else if (this.parentMapper != null && this.resultDefinitionForClass == null) {
               SQLStatementHelper.selectIdentityOfCandidateInStatement(this.stmt, this.resultDefinitionForClass, this.candidateCmd);
            } else if (this.stmt.allUnionsForSamePrimaryTable()) {
               SQLStatementHelper.selectFetchPlanOfCandidateInStatement(this.stmt, this.resultDefinitionForClass, this.candidateCmd, this.fetchPlan, this.parentMapper == null ? maxFetchDepth : 0);
            } else if (this.candidateCmd.getInheritanceMetaData() != null && this.candidateCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
               SQLStatementHelper.selectFetchPlanOfCandidateInStatement(this.stmt, this.resultDefinitionForClass, this.candidateCmd, this.fetchPlan, this.parentMapper == null ? maxFetchDepth : 0);
            } else {
               SQLStatementHelper.selectIdentityOfCandidateInStatement(this.stmt, this.resultDefinitionForClass, this.candidateCmd);
            }
         }
      } else {
         this.compileComponent = CompilationComponent.UPDATE;
         Expression[] updateExprs = this.compilation.getExprUpdate();
         SQLExpression[] updateSqlExprs = new SQLExpression[updateExprs.length];
         boolean performingUpdate = false;

         for(int i = 0; i < updateExprs.length; ++i) {
            DyadicExpression updateExpr = (DyadicExpression)updateExprs[i];
            SQLExpression leftSqlExpr = null;
            if (!(updateExpr.getLeft() instanceof PrimaryExpression)) {
               throw new NucleusException("Dont currently support update clause containing left expression of type " + updateExpr.getLeft());
            }

            this.processPrimaryExpression((PrimaryExpression)updateExpr.getLeft());
            leftSqlExpr = (SQLExpression)this.stack.pop();
            if (leftSqlExpr.getSQLTable() != this.stmt.getPrimaryTable()) {
               leftSqlExpr = null;
            }

            if (leftSqlExpr != null) {
               if (!this.stmt.getDatastoreAdapter().supportsOption("UpdateStmtAllowTableAliasInSet")) {
                  for(int j = 0; j < leftSqlExpr.getNumberOfSubExpressions(); ++j) {
                     ColumnExpression colExpr = leftSqlExpr.getSubExpression(j);
                     colExpr.setOmitTableFromString(true);
                  }
               }

               performingUpdate = true;
               SQLExpression rightSqlExpr = null;
               if (updateExpr.getRight() instanceof Literal) {
                  this.processLiteral((Literal)updateExpr.getRight());
                  rightSqlExpr = (SQLExpression)this.stack.pop();
               } else if (updateExpr.getRight() instanceof ParameterExpression) {
                  ParameterExpression paramExpr = (ParameterExpression)updateExpr.getRight();
                  this.paramMappingForName.put(paramExpr.getId(), leftSqlExpr.getJavaTypeMapping());
                  this.processParameterExpression(paramExpr);
                  rightSqlExpr = (SQLExpression)this.stack.pop();
               } else if (updateExpr.getRight() instanceof PrimaryExpression) {
                  this.processPrimaryExpression((PrimaryExpression)updateExpr.getRight());
                  rightSqlExpr = (SQLExpression)this.stack.pop();
               } else if (updateExpr.getRight() instanceof DyadicExpression) {
                  updateExpr.getRight().evaluate(this);
                  rightSqlExpr = (SQLExpression)this.stack.pop();
               } else if (updateExpr.getRight() instanceof CaseExpression) {
                  updateExpr.getRight().evaluate(this);
                  rightSqlExpr = (SQLExpression)this.stack.pop();
               } else {
                  if (!(updateExpr.getRight() instanceof VariableExpression)) {
                     throw new NucleusException("Dont currently support update clause containing right expression of type " + updateExpr.getRight());
                  }

                  this.processVariableExpression((VariableExpression)updateExpr.getRight());
                  rightSqlExpr = (SQLExpression)this.stack.pop();
                  if (rightSqlExpr instanceof UnboundExpression) {
                     throw new NucleusException("Found UnboundExpression in UPDATE clause!");
                  }
               }

               if (rightSqlExpr != null) {
                  updateSqlExprs[i] = leftSqlExpr.eq(rightSqlExpr);
               }
            }
         }

         if (this.candidateCmd.isVersioned() && this.options.contains("BULK_UPDATE_VERSION")) {
            SQLExpression updateSqlExpr = null;
            ClassTable table = (ClassTable)this.stmt.getPrimaryTable().getTable();
            JavaTypeMapping verMapping = table.getVersionMapping(true);
            ClassTable verTable = table.getTableManagingMapping(verMapping);
            if (verTable == this.stmt.getPrimaryTable().getTable()) {
               VersionMetaData vermd = this.candidateCmd.getVersionMetaDataForClass();
               if (vermd.getVersionStrategy() == VersionStrategy.VERSION_NUMBER) {
                  SQLTable verSqlTbl = this.stmt.getTable(verTable, this.stmt.getPrimaryTable().getGroupName());
                  SQLExpression verExpr = new NumericExpression(this.stmt, verSqlTbl, verMapping);
                  SQLExpression incrExpr = verExpr.add(new IntegerLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Integer.class, false), 1, (String)null));
                  SQLExpression primExpr = verExpr.eq(incrExpr);
                  SQLExpression[] oldArray = updateSqlExprs;
                  updateSqlExprs = new SQLExpression[updateSqlExprs.length + 1];
                  System.arraycopy(oldArray, 0, updateSqlExprs, 0, oldArray.length);
                  updateSqlExprs[oldArray.length] = primExpr;
                  performingUpdate = true;
               } else if (vermd.getVersionStrategy() == VersionStrategy.DATE_TIME) {
                  SQLTable verSqlTbl = this.stmt.getTable(verTable, this.stmt.getPrimaryTable().getGroupName());
                  SQLExpression verExpr = new NumericExpression(this.stmt, verSqlTbl, verMapping);
                  Object newVersion = VersionHelper.getNextVersion(vermd.getVersionStrategy(), (Object)null);
                  JavaTypeMapping valMapping = this.stmt.getSQLExpressionFactory().getMappingForType(newVersion.getClass(), false);
                  SQLExpression valExpr = new TemporalLiteral(this.stmt, valMapping, newVersion, (String)null);
                  SQLExpression sqlExpr = verExpr.eq(valExpr);
                  SQLExpression[] oldArray = updateSqlExprs;
                  updateSqlExprs = new SQLExpression[updateSqlExprs.length + 1];
                  System.arraycopy(oldArray, 0, updateSqlExprs, 0, oldArray.length);
                  updateSqlExprs[oldArray.length] = sqlExpr;
                  performingUpdate = true;
               }
            }
         }

         if (performingUpdate) {
            this.stmt.setUpdates(updateSqlExprs);
         }
      }

      this.compileComponent = null;
   }

   protected void validateExpressionForResult(SQLExpression sqlExpr) {
      JavaTypeMapping m = sqlExpr.getJavaTypeMapping();
      if (m != null && m instanceof AbstractContainerMapping && m.getNumberOfDatastoreMappings() != 1) {
         throw new NucleusUserException(Localiser.msg("021213"));
      }
   }

   protected void compileGrouping() {
      if (this.compilation.getExprGrouping() != null) {
         this.compileComponent = CompilationComponent.GROUPING;
         Expression[] groupExprs = this.compilation.getExprGrouping();

         for(int i = 0; i < groupExprs.length; ++i) {
            Expression groupExpr = groupExprs[i];
            SQLExpression sqlGroupExpr = (SQLExpression)groupExpr.evaluate(this);
            this.stmt.addGroupingExpression(sqlGroupExpr);
         }

         this.compileComponent = null;
      }

   }

   protected void compileHaving() {
      if (this.compilation.getExprHaving() != null) {
         this.compileComponent = CompilationComponent.HAVING;
         Expression havingExpr = this.compilation.getExprHaving();
         Object havingEval = havingExpr.evaluate(this);
         if (!(havingEval instanceof BooleanExpression)) {
            throw new NucleusUserException(Localiser.msg("021051", new Object[]{havingExpr}));
         }

         this.stmt.setHaving((BooleanExpression)havingEval);
         this.compileComponent = null;
      }

   }

   protected void compileOrdering() {
      if (this.compilation.getExprOrdering() != null) {
         this.compileComponent = CompilationComponent.ORDERING;
         Expression[] orderingExpr = this.compilation.getExprOrdering();
         SQLExpression[] orderSqlExprs = new SQLExpression[orderingExpr.length];
         boolean[] directions = new boolean[orderingExpr.length];
         NullOrderingType[] nullOrders = new NullOrderingType[orderingExpr.length];

         for(int i = 0; i < orderingExpr.length; ++i) {
            OrderExpression orderExpr = (OrderExpression)orderingExpr[i];
            Expression expr = orderExpr.getLeft();
            if (expr instanceof PrimaryExpression) {
               PrimaryExpression orderPrimExpr = (PrimaryExpression)expr;
               if (orderPrimExpr.getTuples().size() == 1 && this.resultAliases != null && this.resultAliases.contains(orderPrimExpr.getId().toLowerCase())) {
                  orderSqlExprs[i] = new ResultAliasExpression(this.stmt, orderPrimExpr.getId());
               }
            }

            if (orderSqlExprs[i] == null) {
               orderSqlExprs[i] = (SQLExpression)orderExpr.getLeft().evaluate(this);
            }

            String orderDir = orderExpr.getSortOrder();
            directions[i] = orderDir != null && !orderDir.equals("ascending");
            nullOrders[i] = orderExpr.getNullOrder();
         }

         this.stmt.setOrdering(orderSqlExprs, directions, nullOrders);
         this.compileComponent = null;
      }

   }

   protected void compileFromClassExpression(ClassExpression clsExpr) {
      Symbol clsExprSym = clsExpr.getSymbol();
      Class baseCls = clsExprSym != null ? clsExprSym.getValueType() : null;
      SQLTable candSqlTbl = this.stmt.getPrimaryTable();
      MetaDataManager mmgr = this.storeMgr.getMetaDataManager();
      AbstractClassMetaData cmd = mmgr.getMetaDataForClass(baseCls, this.clr);
      if (baseCls != null && !this.candidateAlias.equals(clsExpr.getAlias())) {
         DatastoreClass candTbl = this.storeMgr.getDatastoreClass(baseCls.getName(), this.clr);
         candSqlTbl = this.stmt.crossJoin(candTbl, clsExpr.getAlias(), (String)null);
         SQLTableMapping tblMapping = new SQLTableMapping(candSqlTbl, cmd, candTbl.getIdMapping());
         this.setSQLTableMappingForAlias(clsExpr.getAlias(), tblMapping);
      }

      if (clsExpr.getCandidateExpression() != null && this.parentMapper != null) {
         String[] tokens = StringUtils.split(clsExpr.getCandidateExpression(), ".");
         String leftAlias = tokens[0];
         SQLTableMapping outerSqlTblMapping = this.parentMapper.getSQLTableMappingForAlias(leftAlias);
         AbstractClassMetaData leftCmd = outerSqlTblMapping.cmd;
         AbstractMemberMetaData[] leftMmds = new AbstractMemberMetaData[tokens.length - 1];
         AbstractMemberMetaData[] rightMmds = new AbstractMemberMetaData[tokens.length - 1];

         for(int i = 0; i < tokens.length - 1; ++i) {
            String joinedField = tokens[i + 1];
            AbstractMemberMetaData leftMmd = leftCmd.getMetaDataForMember(joinedField);
            AbstractMemberMetaData rightMmd = null;
            AbstractClassMetaData rightCmd = null;
            RelationType relationType = leftMmd.getRelationType(this.clr);
            if (RelationType.isBidirectional(relationType)) {
               rightMmd = leftMmd.getRelatedMemberMetaData(this.clr)[0];
               rightCmd = rightMmd.getAbstractClassMetaData();
            } else if (relationType == RelationType.ONE_TO_ONE_UNI) {
               rightCmd = mmgr.getMetaDataForClass(leftMmd.getType(), this.clr);
            } else {
               if (relationType != RelationType.ONE_TO_MANY_UNI) {
                  throw new NucleusUserException("Subquery has been specified with a candidate-expression that includes \"" + tokens[i] + "\" that isnt a relation field!!");
               }

               if (leftMmd.hasCollection()) {
                  rightCmd = mmgr.getMetaDataForClass(leftMmd.getCollection().getElementType(), this.clr);
               } else if (leftMmd.hasMap()) {
                  rightCmd = mmgr.getMetaDataForClass(leftMmd.getMap().getValueType(), this.clr);
               }
            }

            leftMmds[i] = leftMmd;
            rightMmds[i] = rightMmd;
            leftCmd = rightCmd;
         }

         SQLTable rSqlTbl = candSqlTbl;
         SQLTable outerSqlTbl = outerSqlTblMapping.table;

         for(int i = leftMmds.length - 1; i >= 0; --i) {
            AbstractMemberMetaData leftMmd = leftMmds[i];
            AbstractMemberMetaData rightMmd = rightMmds[i];
            DatastoreClass leftTbl = this.storeMgr.getDatastoreClass(leftMmd.getClassName(true), this.clr);
            SQLTable lSqlTbl = null;
            RelationType relationType = leftMmd.getRelationType(this.clr);
            if (relationType == RelationType.ONE_TO_ONE_UNI) {
               if (i == 0) {
                  SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getMemberMapping(leftMmd));
                  SQLExpression rightExpr = this.exprFactory.newExpression(this.stmt, rSqlTbl, rSqlTbl.getTable().getIdMapping());
                  this.stmt.whereAnd(outerExpr.eq(rightExpr), false);
               } else {
                  JavaTypeMapping leftMapping = leftTbl.getMemberMapping(leftMmd);
                  lSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getIdMapping(), leftTbl, (String)null, leftMapping, (Object[])null, (String)null);
               }
            } else if (relationType == RelationType.ONE_TO_ONE_BI) {
               if (leftMmd.getMappedBy() != null) {
                  JavaTypeMapping rightMapping = rSqlTbl.getTable().getMemberMapping(rightMmd);
                  if (i == 0) {
                     SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getIdMapping());
                     SQLExpression rightExpr = this.exprFactory.newExpression(this.stmt, rSqlTbl, rightMapping);
                     this.stmt.whereAnd(outerExpr.eq(rightExpr), false);
                  } else {
                     lSqlTbl = this.stmt.innerJoin(rSqlTbl, rightMapping, leftTbl, (String)null, leftTbl.getIdMapping(), (Object[])null, (String)null);
                  }
               } else if (i == 0) {
                  SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getMemberMapping(leftMmd));
                  SQLExpression rightExpr = this.exprFactory.newExpression(this.stmt, rSqlTbl, rSqlTbl.getTable().getIdMapping());
                  this.stmt.whereAnd(outerExpr.eq(rightExpr), false);
               } else {
                  lSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getIdMapping(), leftTbl, (String)null, leftTbl.getMemberMapping(leftMmd), (Object[])null, (String)null);
               }
            } else if (relationType == RelationType.ONE_TO_MANY_UNI) {
               if (leftMmd.getJoinMetaData() == null && rightMmd.getJoinMetaData() == null) {
                  if (i == 0) {
                     SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getMemberMapping(leftMmd));
                     SQLExpression rightExpr = this.exprFactory.newExpression(this.stmt, rSqlTbl, rSqlTbl.getTable().getMemberMapping(rightMmd));
                     this.stmt.whereAnd(outerExpr.eq(rightExpr), false);
                  } else {
                     lSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getMemberMapping(rightMmd), leftTbl, (String)null, leftTbl.getIdMapping(), (Object[])null, (String)null);
                  }
               } else {
                  JoinTable joinTbl = (JoinTable)this.storeMgr.getTable(leftMmd);
                  SQLTable joinSqlTbl = null;
                  if (leftMmd.hasCollection()) {
                     joinSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getIdMapping(), joinTbl, (String)null, ((ElementContainerTable)joinTbl).getElementMapping(), (Object[])null, (String)null);
                  } else if (leftMmd.hasMap()) {
                     joinSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getIdMapping(), joinTbl, (String)null, ((MapTable)joinTbl).getValueMapping(), (Object[])null, (String)null);
                  }

                  if (i == 0) {
                     SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getIdMapping());
                     SQLExpression joinExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getOwnerMapping());
                     this.stmt.whereAnd(outerExpr.eq(joinExpr), false);
                  } else {
                     lSqlTbl = this.stmt.innerJoin(joinSqlTbl, joinTbl.getOwnerMapping(), leftTbl, (String)null, leftTbl.getIdMapping(), (Object[])null, (String)null);
                  }
               }
            } else if (relationType == RelationType.ONE_TO_MANY_BI) {
               if (leftMmd.getJoinMetaData() == null && rightMmd.getJoinMetaData() == null) {
                  if (i == 0) {
                     SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getIdMapping());
                     SQLExpression rightExpr = this.exprFactory.newExpression(this.stmt, rSqlTbl, rSqlTbl.getTable().getMemberMapping(rightMmd));
                     this.stmt.whereAnd(outerExpr.eq(rightExpr), false);
                  } else {
                     lSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getMemberMapping(rightMmd), leftTbl, (String)null, leftTbl.getIdMapping(), (Object[])null, (String)null);
                  }
               } else {
                  JoinTable joinTbl = (JoinTable)this.storeMgr.getTable(leftMmd);
                  SQLTable joinSqlTbl = null;
                  if (leftMmd.hasCollection()) {
                     joinSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getIdMapping(), joinTbl, (String)null, ((ElementContainerTable)joinTbl).getElementMapping(), (Object[])null, (String)null);
                  } else if (leftMmd.hasMap()) {
                     joinSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getIdMapping(), joinTbl, (String)null, ((MapTable)joinTbl).getValueMapping(), (Object[])null, (String)null);
                  }

                  if (i == 0) {
                     SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getIdMapping());
                     SQLExpression joinExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getOwnerMapping());
                     this.stmt.whereAnd(outerExpr.eq(joinExpr), false);
                  } else {
                     lSqlTbl = this.stmt.innerJoin(joinSqlTbl, joinTbl.getOwnerMapping(), leftTbl, (String)null, leftTbl.getIdMapping(), (Object[])null, (String)null);
                  }
               }
            } else if (relationType == RelationType.MANY_TO_ONE_BI) {
               if (leftMmd.getJoinMetaData() == null && rightMmd.getJoinMetaData() == null) {
                  if (i == 0) {
                     SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getMemberMapping(leftMmd));
                     SQLExpression rightExpr = this.exprFactory.newExpression(this.stmt, rSqlTbl, rSqlTbl.getTable().getIdMapping());
                     this.stmt.whereAnd(outerExpr.eq(rightExpr), false);
                  } else {
                     lSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getIdMapping(), leftTbl, (String)null, leftTbl.getMemberMapping(leftMmd), (Object[])null, (String)null);
                  }
               } else {
                  JoinTable joinTbl = (JoinTable)this.storeMgr.getTable(leftMmd);
                  SQLTable joinSqlTbl = this.stmt.innerJoin(rSqlTbl, rSqlTbl.getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getOwnerMapping(), (Object[])null, (String)null);
                  if (leftMmd.hasCollection()) {
                     if (i == 0) {
                        SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getIdMapping());
                        SQLExpression joinExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, ((ElementContainerTable)joinTbl).getElementMapping());
                        this.stmt.whereAnd(outerExpr.eq(joinExpr), false);
                     } else {
                        lSqlTbl = this.stmt.innerJoin(joinSqlTbl, ((ElementContainerTable)joinTbl).getElementMapping(), leftTbl, (String)null, leftTbl.getIdMapping(), (Object[])null, (String)null);
                     }
                  } else if (leftMmd.hasMap()) {
                     if (i == 0) {
                        SQLExpression outerExpr = this.exprFactory.newExpression(outerSqlTbl.getSQLStatement(), outerSqlTbl, outerSqlTbl.getTable().getIdMapping());
                        SQLExpression joinExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, ((MapTable)joinTbl).getValueMapping());
                        this.stmt.whereAnd(outerExpr.eq(joinExpr), false);
                     } else {
                        lSqlTbl = this.stmt.innerJoin(joinSqlTbl, ((MapTable)joinTbl).getValueMapping(), leftTbl, (String)null, leftTbl.getIdMapping(), (Object[])null, (String)null);
                     }
                  }
               }
            }

            rSqlTbl = lSqlTbl;
         }
      }

      Expression rightExpr = clsExpr.getRight();

      for(JavaTypeMapping previousMapping = null; rightExpr != null; rightExpr = rightExpr.getRight()) {
         if (rightExpr instanceof JoinExpression) {
            JoinExpression joinExpr = (JoinExpression)rightExpr;
            JoinExpression.JoinType exprJoinType = joinExpr.getType();
            SQLJoin.JoinType joinType = SQLJoin.getJoinTypeForJoinExpressionType(exprJoinType);
            String joinAlias = joinExpr.getAlias();
            Expression joinedExpr = joinExpr.getJoinedExpression();
            Expression joinOnExpr = joinExpr.getOnExpression();
            PrimaryExpression joinPrimExpr = null;
            Class castCls = null;
            if (joinedExpr instanceof PrimaryExpression) {
               joinPrimExpr = (PrimaryExpression)joinedExpr;
            } else {
               if (!(joinedExpr instanceof DyadicExpression) || joinedExpr.getOperator() != Expression.OP_CAST) {
                  throw new NucleusException("We do not currently support JOIN to " + joinedExpr);
               }

               joinPrimExpr = (PrimaryExpression)joinedExpr.getLeft();
               String castClassName = (String)((Literal)joinedExpr.getRight()).getLiteral();
               castCls = this.clr.classForName(castClassName);
            }

            Iterator<String> iter = joinPrimExpr.getTuples().iterator();
            String rootId = (String)iter.next();
            String joinTableGroupName = null;
            SQLTable sqlTbl;
            if (rootId.equalsIgnoreCase(this.candidateAlias)) {
               cmd = this.candidateCmd;
               joinTableGroupName = joinPrimExpr.getId();
               sqlTbl = candSqlTbl;
            } else {
               SQLTableMapping sqlTblMapping = this.getSQLTableMappingForAlias(rootId);
               if (sqlTblMapping == null) {
                  throw new NucleusUserException("Query has " + joinPrimExpr.getId() + " yet the first component " + rootId + " is unknown!");
               }

               if (sqlTblMapping.mmd != null) {
                  cmd = sqlTblMapping.mmd.getMap().getValueClassMetaData(this.clr, mmgr);
                  sqlTbl = this.stmt.getTable(rootId + "_VALUE");
                  if (sqlTbl == null) {
                     sqlTbl = this.stmt.getTable((rootId + "_VALUE").toUpperCase());
                  }
               } else {
                  cmd = sqlTblMapping.cmd;
                  sqlTbl = sqlTblMapping.table;
               }

               joinTableGroupName = sqlTbl.getGroupName() + joinPrimExpr.getId().substring(rootId.length());
            }

            SQLTable tblMappingSqlTbl = null;
            JavaTypeMapping tblIdMapping = null;
            AbstractMemberMetaData tblMmd = null;

            while(iter.hasNext()) {
               String id = (String)iter.next();
               String[] ids = null;
               if (id.contains(".")) {
                  ids = StringUtils.split(id, ".");
               } else {
                  ids = new String[]{id};
               }

               for(int k = 0; k < ids.length; ++k) {
                  boolean lastComponent = k == ids.length - 1;
                  AbstractMemberMetaData mmd = cmd.getMetaDataForMember(ids[k]);
                  if (mmd == null) {
                     if (exprJoinType == JoinType.JOIN_LEFT_OUTER || exprJoinType == JoinType.JOIN_LEFT_OUTER_FETCH) {
                        String[] subclasses = mmgr.getSubclassesForClass(cmd.getFullClassName(), true);

                        for(int l = 0; l < subclasses.length; ++l) {
                           AbstractClassMetaData subCmd = mmgr.getMetaDataForClass(subclasses[l], this.clr);
                           if (subCmd != null) {
                              mmd = subCmd.getMetaDataForMember(ids[k]);
                              if (mmd != null) {
                                 cmd = subCmd;
                                 break;
                              }
                           }
                        }
                     }

                     if (mmd == null) {
                        throw new NucleusUserException("Query has " + joinPrimExpr.getId() + " yet " + ids[k] + " is not found. Fix your input");
                     }
                  }

                  tblMmd = null;
                  String aliasForJoin = null;
                  if (k == ids.length - 1 && !iter.hasNext()) {
                     aliasForJoin = joinAlias;
                  }

                  RelationType relationType = mmd.getRelationType(this.clr);
                  DatastoreClass relTable = null;
                  AbstractMemberMetaData relMmd = null;
                  if (relationType != RelationType.NONE && (exprJoinType == JoinType.JOIN_INNER_FETCH || exprJoinType == JoinType.JOIN_LEFT_OUTER_FETCH || exprJoinType == JoinType.JOIN_RIGHT_OUTER_FETCH)) {
                     String fgName = "QUERY_FETCH_" + mmd.getFullFieldName();
                     FetchGroupManager fetchGrpMgr = this.storeMgr.getNucleusContext().getFetchGroupManager();
                     if (fetchGrpMgr.getFetchGroupsWithName(fgName) == null) {
                        FetchGroup grp = new FetchGroup(this.storeMgr.getNucleusContext(), fgName, this.clr.classForName(cmd.getFullClassName()));
                        grp.addMember(mmd.getName());
                        fetchGrpMgr.addFetchGroup(grp);
                     }

                     this.fetchPlan.addGroup(fgName);
                  }

                  if (relationType == RelationType.ONE_TO_ONE_UNI) {
                     JavaTypeMapping otherMapping = null;
                     Object[] castDiscrimValues = null;
                     if (castCls != null && lastComponent) {
                        cmd = mmgr.getMetaDataForClass(castCls, this.clr);
                        if (cmd.hasDiscriminatorStrategy()) {
                           castDiscrimValues = this.getDiscriminatorValuesForCastClass(cmd);
                        }
                     } else {
                        cmd = mmgr.getMetaDataForClass(mmd.getType(), this.clr);
                     }

                     if (mmd.isEmbedded()) {
                        otherMapping = sqlTbl.getTable().getMemberMapping(mmd);
                     } else {
                        relTable = this.storeMgr.getDatastoreClass(mmd.getTypeName(), this.clr);
                        otherMapping = sqlTbl.getTable().getMemberMapping(mmd);
                        if (otherMapping == null && previousMapping != null && previousMapping instanceof EmbeddedMapping) {
                           EmbeddedMapping embMapping = (EmbeddedMapping)previousMapping;
                           otherMapping = embMapping.getJavaTypeMapping(mmd.getName());
                        }

                        if (otherMapping == null) {
                           String tblGroupName = sqlTbl.getGroupName();
                           SQLTableGroup grp = this.stmt.getTableGroup(tblGroupName);
                           SQLTable nextSqlTbl = null;
                           SQLTable[] grpTbls = grp.getTables();

                           for(SQLTable grpTbl : grpTbls) {
                              if (grpTbl.getTable().getMemberMapping(mmd) != null) {
                                 otherMapping = grpTbl.getTable().getMemberMapping(mmd);
                                 break;
                              }
                           }

                           SQLTable newSqlTbl = this.stmt.join(joinType, sqlTbl, otherMapping, relTable, aliasForJoin, relTable.getIdMapping(), (Object[])null, joinTableGroupName, false);
                           if (newSqlTbl != null) {
                              nextSqlTbl = newSqlTbl;
                           }

                           List<SQLStatement> unionStmts = this.stmt.getUnions();
                           if (unionStmts != null) {
                              for(SQLStatement unionStmt : unionStmts) {
                                 otherMapping = null;
                                 grp = unionStmt.getTableGroup(tblGroupName);
                                 SQLTable[] unionGrpTbls = grp.getTables();

                                 for(SQLTable grpTbl : unionGrpTbls) {
                                    if (grpTbl.getTable().getMemberMapping(mmd) != null) {
                                       otherMapping = grpTbl.getTable().getMemberMapping(mmd);
                                       break;
                                    }
                                 }

                                 newSqlTbl = unionStmt.join(joinType, sqlTbl, otherMapping, relTable, aliasForJoin, relTable.getIdMapping(), (Object[])null, joinTableGroupName, false);
                                 if (newSqlTbl != null) {
                                    nextSqlTbl = newSqlTbl;
                                 }
                              }
                           }

                           sqlTbl = nextSqlTbl;
                        } else {
                           sqlTbl = this.stmt.join(joinType, sqlTbl, otherMapping, relTable, aliasForJoin, relTable.getIdMapping(), castDiscrimValues, joinTableGroupName, true);
                        }
                     }

                     previousMapping = otherMapping;
                     tblIdMapping = sqlTbl.getTable().getIdMapping();
                     tblMappingSqlTbl = sqlTbl;
                  } else if (relationType == RelationType.ONE_TO_ONE_BI) {
                     JavaTypeMapping otherMapping = null;
                     Object[] castDiscrimValues = null;
                     if (castCls != null && lastComponent) {
                        cmd = mmgr.getMetaDataForClass(castCls, this.clr);
                        if (cmd.hasDiscriminatorStrategy()) {
                           castDiscrimValues = this.getDiscriminatorValuesForCastClass(cmd);
                        }
                     } else {
                        cmd = mmgr.getMetaDataForClass(mmd.getType(), this.clr);
                     }

                     if (mmd.isEmbedded()) {
                        otherMapping = sqlTbl.getTable().getMemberMapping(mmd);
                     } else {
                        relTable = this.storeMgr.getDatastoreClass(mmd.getTypeName(), this.clr);
                        if (mmd.getMappedBy() != null) {
                           relMmd = mmd.getRelatedMemberMetaData(this.clr)[0];
                           JavaTypeMapping relMapping = relTable.getMemberMapping(relMmd);
                           sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), relTable, aliasForJoin, relMapping, castDiscrimValues, joinTableGroupName, true);
                        } else {
                           otherMapping = sqlTbl.getTable().getMemberMapping(mmd);
                           if (otherMapping == null && previousMapping != null && previousMapping instanceof EmbeddedMapping) {
                              EmbeddedMapping embMapping = (EmbeddedMapping)previousMapping;
                              otherMapping = embMapping.getJavaTypeMapping(mmd.getName());
                           }

                           sqlTbl = this.stmt.join(joinType, sqlTbl, otherMapping, relTable, aliasForJoin, relTable.getIdMapping(), castDiscrimValues, joinTableGroupName, true);
                        }
                     }

                     previousMapping = otherMapping;
                     tblIdMapping = sqlTbl.getTable().getIdMapping();
                     tblMappingSqlTbl = sqlTbl;
                  } else if (relationType == RelationType.ONE_TO_MANY_BI) {
                     previousMapping = null;
                     if (mmd.hasCollection()) {
                        cmd = mmd.getCollection().getElementClassMetaData(this.clr, mmgr);
                        if (mmd.getCollection().isEmbeddedElement() && mmd.getJoinMetaData() != null) {
                           CollectionTable relEmbTable = (CollectionTable)this.storeMgr.getTable(mmd);
                           JavaTypeMapping relOwnerMapping = relEmbTable.getOwnerMapping();
                           sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), relEmbTable, aliasForJoin, relOwnerMapping, (Object[])null, joinTableGroupName, true);
                           tblMappingSqlTbl = sqlTbl;
                           tblIdMapping = relEmbTable.getElementMapping();
                        } else {
                           relTable = this.storeMgr.getDatastoreClass(mmd.getCollection().getElementType(), this.clr);
                           relMmd = mmd.getRelatedMemberMetaData(this.clr)[0];
                           if (mmd.getJoinMetaData() == null && relMmd.getJoinMetaData() == null) {
                              sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), relTable, aliasForJoin, relTable.getMemberMapping(relMmd), (Object[])null, joinTableGroupName, true);
                           } else {
                              ElementContainerTable joinTbl = (ElementContainerTable)this.storeMgr.getTable(mmd);
                              SQLTable joinSqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getOwnerMapping(), (Object[])null, (String)null, true);
                              sqlTbl = this.stmt.join(joinType, joinSqlTbl, joinTbl.getElementMapping(), relTable, aliasForJoin, relTable.getIdMapping(), (Object[])null, joinTableGroupName, true);
                           }

                           tblIdMapping = sqlTbl.getTable().getIdMapping();
                           tblMappingSqlTbl = sqlTbl;
                        }
                     } else if (mmd.hasMap()) {
                        MapMetaData mapmd = mmd.getMap();
                        tblMmd = mmd;
                        tblIdMapping = sqlTbl.getTable().getMemberMapping(mmd);
                        tblMappingSqlTbl = sqlTbl;
                        if (mapmd.getMapType() != MapType.MAP_TYPE_JOIN) {
                           if (mapmd.getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
                              DatastoreClass valTable = this.storeMgr.getDatastoreClass(mapmd.getValueType(), this.clr);
                              JavaTypeMapping mapTblOwnerMapping;
                              if (mmd.getMappedBy() != null) {
                                 mapTblOwnerMapping = valTable.getMemberMapping(mapmd.getValueClassMetaData(this.clr, mmgr).getMetaDataForMember(mmd.getMappedBy()));
                              } else {
                                 mapTblOwnerMapping = valTable.getExternalMapping(mmd, 5);
                              }

                              sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), valTable, aliasForJoin, mapTblOwnerMapping, (Object[])null, (String)null, true);
                           } else if (mapmd.getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
                              DatastoreClass keyTable = this.storeMgr.getDatastoreClass(mapmd.getKeyType(), this.clr);
                              JavaTypeMapping mapTblOwnerMapping;
                              if (mmd.getMappedBy() != null) {
                                 mapTblOwnerMapping = keyTable.getMemberMapping(mapmd.getKeyClassMetaData(this.clr, mmgr).getMetaDataForMember(mmd.getMappedBy()));
                              } else {
                                 mapTblOwnerMapping = keyTable.getExternalMapping(mmd, 5);
                              }

                              sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), keyTable, aliasForJoin, mapTblOwnerMapping, (Object[])null, (String)null, true);
                           }
                        } else {
                           boolean embeddedValue = mapmd.isEmbeddedValue() || mapmd.isSerializedValue();
                           MapTable joinTbl = (MapTable)this.storeMgr.getTable(mmd);
                           sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), joinTbl, aliasForJoin, joinTbl.getOwnerMapping(), (Object[])null, (String)null, true);
                           if (!embeddedValue) {
                              relTable = this.storeMgr.getDatastoreClass(mapmd.getValueType(), this.clr);
                              String valueTableAlias = aliasForJoin != null ? aliasForJoin + "_VALUE" : null;
                              this.stmt.join(joinType, sqlTbl, joinTbl.getValueMapping(), relTable, valueTableAlias, relTable.getIdMapping(), (Object[])null, joinTableGroupName, true);
                           }
                        }
                     }
                  } else if (relationType == RelationType.ONE_TO_MANY_UNI) {
                     previousMapping = null;
                     if (mmd.hasCollection()) {
                        cmd = mmd.getCollection().getElementClassMetaData(this.clr, mmgr);
                        if (mmd.getCollection().isEmbeddedElement() && mmd.getJoinMetaData() != null) {
                           CollectionTable relEmbTable = (CollectionTable)this.storeMgr.getTable(mmd);
                           JavaTypeMapping relOwnerMapping = relEmbTable.getOwnerMapping();
                           sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), relEmbTable, aliasForJoin, relOwnerMapping, (Object[])null, joinTableGroupName, true);
                           tblMappingSqlTbl = sqlTbl;
                           tblIdMapping = relEmbTable.getElementMapping();
                        } else {
                           relTable = this.storeMgr.getDatastoreClass(mmd.getCollection().getElementType(), this.clr);
                           if (mmd.getJoinMetaData() != null) {
                              ElementContainerTable joinTbl = (ElementContainerTable)this.storeMgr.getTable(mmd);
                              SQLTable joinSqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getOwnerMapping(), (Object[])null, (String)null, true);
                              sqlTbl = this.stmt.join(joinType, joinSqlTbl, joinTbl.getElementMapping(), relTable, aliasForJoin, relTable.getIdMapping(), (Object[])null, joinTableGroupName, true);
                           } else {
                              JavaTypeMapping relMapping = relTable.getExternalMapping(mmd, 5);
                              sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), relTable, aliasForJoin, relMapping, (Object[])null, joinTableGroupName, true);
                           }

                           tblMappingSqlTbl = sqlTbl;
                           tblIdMapping = sqlTbl.getTable().getIdMapping();
                        }
                     } else if (mmd.hasMap()) {
                        MapMetaData mapmd = mmd.getMap();
                        cmd = mapmd.getValueClassMetaData(this.clr, mmgr);
                        tblMmd = mmd;
                        tblIdMapping = sqlTbl.getTable().getMemberMapping(mmd);
                        tblMappingSqlTbl = sqlTbl;
                        if (mapmd.getMapType() != MapType.MAP_TYPE_JOIN) {
                           if (mapmd.getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
                              DatastoreClass valTable = this.storeMgr.getDatastoreClass(mapmd.getValueType(), this.clr);
                              JavaTypeMapping mapTblOwnerMapping;
                              if (mmd.getMappedBy() != null) {
                                 mapTblOwnerMapping = valTable.getMemberMapping(mapmd.getValueClassMetaData(this.clr, mmgr).getMetaDataForMember(mmd.getMappedBy()));
                              } else {
                                 mapTblOwnerMapping = valTable.getExternalMapping(mmd, 5);
                              }

                              sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), valTable, aliasForJoin, mapTblOwnerMapping, (Object[])null, (String)null, true);
                           } else if (mapmd.getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
                              DatastoreClass keyTable = this.storeMgr.getDatastoreClass(mapmd.getKeyType(), this.clr);
                              JavaTypeMapping mapTblOwnerMapping;
                              if (mmd.getMappedBy() != null) {
                                 mapTblOwnerMapping = keyTable.getMemberMapping(mapmd.getKeyClassMetaData(this.clr, mmgr).getMetaDataForMember(mmd.getMappedBy()));
                              } else {
                                 mapTblOwnerMapping = keyTable.getExternalMapping(mmd, 5);
                              }

                              sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), keyTable, aliasForJoin, mapTblOwnerMapping, (Object[])null, (String)null, true);
                           }
                        } else {
                           MapTable joinTbl = (MapTable)this.storeMgr.getTable(mmd);
                           boolean embeddedValue = mapmd.isEmbeddedValue() || mmd.getMap().isSerializedValue();
                           sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), joinTbl, aliasForJoin, joinTbl.getOwnerMapping(), (Object[])null, (String)null, true);
                           if (!embeddedValue) {
                              relTable = this.storeMgr.getDatastoreClass(mapmd.getValueType(), this.clr);
                              String valueTableAlias = aliasForJoin != null ? aliasForJoin + "_VALUE" : null;
                              this.stmt.join(joinType, sqlTbl, joinTbl.getValueMapping(), relTable, valueTableAlias, relTable.getIdMapping(), (Object[])null, joinTableGroupName, true);
                           }
                        }
                     }
                  } else if (relationType == RelationType.MANY_TO_MANY_BI) {
                     previousMapping = null;
                     relTable = this.storeMgr.getDatastoreClass(mmd.getCollection().getElementType(), this.clr);
                     cmd = mmd.getCollection().getElementClassMetaData(this.clr, mmgr);
                     relMmd = mmd.getRelatedMemberMetaData(this.clr)[0];
                     CollectionTable joinTbl = (CollectionTable)this.storeMgr.getTable(mmd);
                     SQLTable joinSqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getOwnerMapping(), (Object[])null, (String)null, true);
                     sqlTbl = this.stmt.join(joinType, joinSqlTbl, joinTbl.getElementMapping(), relTable, aliasForJoin, relTable.getIdMapping(), (Object[])null, joinTableGroupName, true);
                     tblMappingSqlTbl = sqlTbl;
                     tblIdMapping = sqlTbl.getTable().getIdMapping();
                  } else if (relationType != RelationType.MANY_TO_ONE_BI) {
                     previousMapping = null;
                     if (mmd.hasCollection()) {
                        cmd = null;
                        if (mmd.getJoinMetaData() == null) {
                           throw new NucleusUserException("FROM clause contains join to Collection field at " + mmd.getFullFieldName() + " yet this has no join table");
                        }

                        MapTable joinTbl = (MapTable)this.storeMgr.getTable(mmd);
                        sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), joinTbl, aliasForJoin, joinTbl.getOwnerMapping(), (Object[])null, (String)null, true);
                        tblMappingSqlTbl = sqlTbl;
                        tblIdMapping = sqlTbl.getTable().getIdMapping();
                     } else if (mmd.hasMap()) {
                        cmd = null;
                        MapMetaData mapmd = mmd.getMap();
                        tblMmd = mmd;
                        tblIdMapping = sqlTbl.getTable().getMemberMapping(mmd);
                        tblMappingSqlTbl = sqlTbl;
                        if (mapmd.getMapType() == MapType.MAP_TYPE_JOIN) {
                           MapTable joinTbl = (MapTable)this.storeMgr.getTable(mmd);
                           sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), joinTbl, aliasForJoin, joinTbl.getOwnerMapping(), (Object[])null, (String)null, true);
                        } else if (mapmd.getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
                           DatastoreClass valTable = this.storeMgr.getDatastoreClass(mapmd.getValueType(), this.clr);
                           AbstractClassMetaData valCmd = mapmd.getValueClassMetaData(this.clr, mmgr);
                           JavaTypeMapping mapTblOwnerMapping;
                           if (mmd.getMappedBy() != null) {
                              mapTblOwnerMapping = valTable.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
                           } else {
                              mapTblOwnerMapping = valTable.getExternalMapping(mmd, 5);
                           }

                           sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), valTable, aliasForJoin, mapTblOwnerMapping, (Object[])null, (String)null, true);
                        } else if (mapmd.getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
                           DatastoreClass keyTable = this.storeMgr.getDatastoreClass(mapmd.getKeyType(), this.clr);
                           AbstractClassMetaData keyCmd = mapmd.getKeyClassMetaData(this.clr, mmgr);
                           JavaTypeMapping mapTblOwnerMapping;
                           if (mmd.getMappedBy() != null) {
                              mapTblOwnerMapping = keyTable.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
                           } else {
                              mapTblOwnerMapping = keyTable.getExternalMapping(mmd, 5);
                           }

                           sqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), keyTable, aliasForJoin, mapTblOwnerMapping, (Object[])null, (String)null, true);
                        }
                     }
                  } else {
                     previousMapping = null;
                     relTable = this.storeMgr.getDatastoreClass(mmd.getTypeName(), this.clr);
                     Object[] castDiscrimValues = null;
                     if (castCls != null && lastComponent) {
                        cmd = mmgr.getMetaDataForClass(castCls, this.clr);
                        if (cmd.hasDiscriminatorStrategy()) {
                           castDiscrimValues = this.getDiscriminatorValuesForCastClass(cmd);
                        }
                     } else {
                        cmd = mmgr.getMetaDataForClass(mmd.getType(), this.clr);
                     }

                     relMmd = mmd.getRelatedMemberMetaData(this.clr)[0];
                     if (mmd.getJoinMetaData() == null && relMmd.getJoinMetaData() == null) {
                        JavaTypeMapping fkMapping = sqlTbl.getTable().getMemberMapping(mmd);
                        sqlTbl = this.stmt.join(joinType, sqlTbl, fkMapping, relTable, aliasForJoin, relTable.getIdMapping(), castDiscrimValues, joinTableGroupName, true);
                     } else {
                        CollectionTable joinTbl = (CollectionTable)this.storeMgr.getTable(relMmd);
                        SQLTable joinSqlTbl = this.stmt.join(joinType, sqlTbl, sqlTbl.getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getElementMapping(), (Object[])null, (String)null, true);
                        sqlTbl = this.stmt.join(joinType, joinSqlTbl, joinTbl.getOwnerMapping(), relTable, aliasForJoin, relTable.getIdMapping(), castDiscrimValues, joinTableGroupName, true);
                     }

                     tblMappingSqlTbl = sqlTbl;
                     tblIdMapping = sqlTbl.getTable().getIdMapping();
                  }
               }
            }

            if (joinAlias != null) {
               if (this.explicitJoinPrimaryByAlias == null) {
                  this.explicitJoinPrimaryByAlias = new HashMap();
               }

               this.explicitJoinPrimaryByAlias.put(joinAlias, joinPrimExpr.getId());
               SQLTableMapping tblMapping = null;
               if (tblMmd != null) {
                  tblMapping = new SQLTableMapping(tblMappingSqlTbl, tblMmd, tblIdMapping);
               } else {
                  tblMapping = new SQLTableMapping(tblMappingSqlTbl, cmd, tblIdMapping);
               }

               this.setSQLTableMappingForAlias(joinAlias, tblMapping);
            }

            if (joinOnExpr != null) {
               this.processingOnClause = true;
               joinOnExpr.evaluate(this);
               BooleanExpression joinOnSqlExpr = (BooleanExpression)this.stack.pop();
               this.processingOnClause = false;
               SQLJoin join = this.stmt.getJoinForTable(sqlTbl);
               join.addAndCondition(joinOnSqlExpr);
            }
         } else {
            previousMapping = null;
         }
      }

   }

   private Object[] getDiscriminatorValuesForCastClass(AbstractClassMetaData cmd) {
      Collection<String> castSubclassNames = this.storeMgr.getSubClassesForClass(cmd.getFullClassName(), true, this.clr);
      Object[] castDiscrimValues = new Object[1 + (castSubclassNames != null ? castSubclassNames.size() : 0)];
      int discNo = 0;
      castDiscrimValues[discNo++] = cmd.getDiscriminatorValue();
      if (castSubclassNames != null && !castSubclassNames.isEmpty()) {
         for(String castSubClassName : castSubclassNames) {
            AbstractClassMetaData castSubCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(castSubClassName, this.clr);
            castDiscrimValues[discNo++] = castSubCmd.getDiscriminatorValue();
         }
      }

      return castDiscrimValues;
   }

   public boolean processingOnClause() {
      return this.processingOnClause;
   }

   protected StatementNewObjectMapping getStatementMappingForNewObjectExpression(NewObjectExpression expr) {
      List argExprs = expr.getConstructorArgExpressions();
      StatementNewObjectMapping stmtMap = new StatementNewObjectMapping(expr.getNewClass());
      if (argExprs != null) {
         Iterator<SQLExpression> argIter = argExprs.iterator();

         for(int j = 0; argIter.hasNext(); ++j) {
            SQLExpression argExpr = (SQLExpression)argIter.next();
            if (argExpr instanceof SQLLiteral) {
               stmtMap.addConstructorArgMapping(j, ((SQLLiteral)argExpr).getValue());
            } else if (argExpr instanceof NewObjectExpression) {
               stmtMap.addConstructorArgMapping(j, this.getStatementMappingForNewObjectExpression((NewObjectExpression)argExpr));
            } else {
               StatementMappingIndex idx = new StatementMappingIndex(argExpr.getJavaTypeMapping());
               int[] cols = this.stmt.select(argExpr, (String)null);
               idx.setColumnPositions(cols);
               stmtMap.addConstructorArgMapping(j, idx);
            }
         }
      }

      return stmtMap;
   }

   protected Object processAndExpression(Expression expr) {
      SQLExpression rightExpr = (SQLExpression)this.stack.pop();
      SQLExpression leftExpr = (SQLExpression)this.stack.pop();
      if (!(rightExpr instanceof BooleanExpression)) {
         throw new NucleusUserException("Query has clause " + rightExpr + " used with AND. This is illegal, and should be a boolean expression");
      } else if (!(leftExpr instanceof BooleanExpression)) {
         throw new NucleusUserException("Query has clause " + leftExpr + " used with AND. This is illegal, and should be a boolean expression");
      } else {
         BooleanExpression right = (BooleanExpression)rightExpr;
         BooleanExpression left = (BooleanExpression)leftExpr;
         if (left.getSQLStatement() != null && right.getSQLStatement() != null && left.getSQLStatement() != right.getSQLStatement()) {
            if (left.getSQLStatement() == this.stmt && right.getSQLStatement().isChildStatementOf(this.stmt)) {
               right.getSQLStatement().whereAnd(right, true);
               this.stack.push(left);
               return left;
            }

            if (right.getSQLStatement() == this.stmt && left.getSQLStatement().isChildStatementOf(this.stmt)) {
               left.getSQLStatement().whereAnd(left, true);
               this.stack.push(right);
               return right;
            }
         }

         if (this.compileComponent == CompilationComponent.FILTER) {
            left = this.getBooleanExpressionForUseInFilter(left);
            right = this.getBooleanExpressionForUseInFilter(right);
         }

         BooleanExpression opExpr = left.and(right);
         this.stack.push(opExpr);
         return opExpr;
      }
   }

   protected Object processOrExpression(Expression expr) {
      SQLExpression rightExpr = (SQLExpression)this.stack.pop();
      SQLExpression leftExpr = (SQLExpression)this.stack.pop();
      if (!(rightExpr instanceof BooleanExpression)) {
         throw new NucleusUserException("Query has clause " + rightExpr + " used with AND. This is illegal, and should be a boolean expression");
      } else if (!(leftExpr instanceof BooleanExpression)) {
         throw new NucleusUserException("Query has clause " + leftExpr + " used with AND. This is illegal, and should be a boolean expression");
      } else {
         BooleanExpression right = (BooleanExpression)rightExpr;
         BooleanExpression left = (BooleanExpression)leftExpr;
         if (left.getSQLStatement() != null && right.getSQLStatement() != null && left.getSQLStatement() != right.getSQLStatement()) {
            if (left.getSQLStatement() == this.stmt && right.getSQLStatement().isChildStatementOf(this.stmt)) {
               right.getSQLStatement().whereAnd(right, true);
               this.stack.push(left);
               return left;
            }

            if (right.getSQLStatement() == this.stmt && left.getSQLStatement().isChildStatementOf(this.stmt)) {
               left.getSQLStatement().whereAnd(left, true);
               this.stack.push(right);
               return right;
            }
         }

         if (this.compileComponent == CompilationComponent.FILTER) {
            left = this.getBooleanExpressionForUseInFilter(left);
            right = this.getBooleanExpressionForUseInFilter(right);
         }

         left.encloseInParentheses();
         right.encloseInParentheses();
         BooleanExpression opExpr = left.ior(right);
         this.stack.push(opExpr);
         return opExpr;
      }
   }

   protected Object processBitAndExpression(Expression expr) {
      SQLExpression rightExpr = (SQLExpression)this.stack.pop();
      SQLExpression leftExpr = (SQLExpression)this.stack.pop();
      if (rightExpr instanceof BooleanExpression && leftExpr instanceof BooleanExpression) {
         this.stack.push(leftExpr);
         this.stack.push(rightExpr);
         return this.processAndExpression(expr);
      } else if (rightExpr instanceof NumericExpression && leftExpr instanceof NumericExpression && this.storeMgr.getDatastoreAdapter().supportsOption("BitwiseAndOperator")) {
         SQLExpression bitAndExpr = (new NumericExpression(leftExpr, Expression.OP_BIT_AND, rightExpr)).encloseInParentheses();
         this.stack.push(bitAndExpr);
         return bitAndExpr;
      } else {
         throw new NucleusUserException("Operation BITWISE AND is not supported for " + leftExpr + " and " + rightExpr + " for this datastore");
      }
   }

   protected Object processBitOrExpression(Expression expr) {
      SQLExpression rightExpr = (SQLExpression)this.stack.pop();
      SQLExpression leftExpr = (SQLExpression)this.stack.pop();
      if (rightExpr instanceof BooleanExpression && leftExpr instanceof BooleanExpression) {
         this.stack.push(leftExpr);
         this.stack.push(rightExpr);
         return this.processOrExpression(expr);
      } else if (rightExpr instanceof NumericExpression && leftExpr instanceof NumericExpression && this.storeMgr.getDatastoreAdapter().supportsOption("BitwiseOrOperator")) {
         SQLExpression bitExpr = (new NumericExpression(leftExpr, Expression.OP_BIT_OR, rightExpr)).encloseInParentheses();
         this.stack.push(bitExpr);
         return bitExpr;
      } else {
         throw new NucleusUserException("Operation BITWISE OR is not supported for " + leftExpr + " and " + rightExpr + " is not supported by this datastore");
      }
   }

   protected Object processBitXorExpression(Expression expr) {
      SQLExpression rightExpr = (SQLExpression)this.stack.pop();
      SQLExpression leftExpr = (SQLExpression)this.stack.pop();
      if (rightExpr instanceof BooleanExpression && leftExpr instanceof BooleanExpression) {
         this.stack.push(leftExpr);
         this.stack.push(rightExpr);
         return this.processOrExpression(expr);
      } else if (rightExpr instanceof NumericExpression && leftExpr instanceof NumericExpression && this.storeMgr.getDatastoreAdapter().supportsOption("BitwiseXOrOperator")) {
         SQLExpression bitExpr = (new NumericExpression(leftExpr, Expression.OP_BIT_XOR, rightExpr)).encloseInParentheses();
         this.stack.push(bitExpr);
         return bitExpr;
      } else {
         throw new NucleusUserException("Operation BITWISE XOR is not supported for " + leftExpr + " and " + rightExpr + " is not supported by this datastore");
      }
   }

   protected Object processEqExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (left instanceof ParameterLiteral && !(right instanceof ParameterLiteral)) {
         left = this.replaceParameterLiteral((ParameterLiteral)left, right.getJavaTypeMapping());
      } else if (right instanceof ParameterLiteral && !(left instanceof ParameterLiteral)) {
         right = this.replaceParameterLiteral((ParameterLiteral)right, left.getJavaTypeMapping());
      }

      if (left.isParameter() && right.isParameter()) {
         if (left.isParameter() && left instanceof SQLLiteral && ((SQLLiteral)left).getValue() != null) {
            this.useParameterExpressionAsLiteral((SQLLiteral)left);
         }

         if (right.isParameter() && right instanceof SQLLiteral && ((SQLLiteral)right).getValue() != null) {
            this.useParameterExpressionAsLiteral((SQLLiteral)right);
         }
      }

      ExpressionUtils.checkAndCorrectExpressionMappingsForBooleanComparison(left, right);
      if (left instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)left);
         left = (SQLExpression)this.stack.pop();
      }

      if (right instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)right);
         right = (SQLExpression)this.stack.pop();
      }

      if (!this.options.contains("EXPLICIT_JOINS")) {
         boolean leftIsCrossJoin = this.stmt.getJoinTypeForTable(left.getSQLTable()) == SQLJoin.JoinType.CROSS_JOIN;
         boolean rightIsCrossJoin = this.stmt.getJoinTypeForTable(right.getSQLTable()) == SQLJoin.JoinType.CROSS_JOIN;
         if (leftIsCrossJoin && !rightIsCrossJoin && !(right instanceof SQLLiteral)) {
            String varName = this.getAliasForSQLTable(left.getSQLTable());
            SQLJoin.JoinType joinType = this.getRequiredJoinTypeForAlias(varName);
            if (joinType != null) {
               NucleusLogger.QUERY.debug("QueryToSQL.eq variable " + varName + " is mapped to table " + left.getSQLTable() + " was previously bound as CROSS JOIN but changing to " + joinType);
               String leftTblAlias = this.stmt.removeCrossJoin(left.getSQLTable());
               if (joinType == SQLJoin.JoinType.LEFT_OUTER_JOIN) {
                  this.stmt.leftOuterJoin(right.getSQLTable(), right.getJavaTypeMapping(), left.getSQLTable().getTable(), leftTblAlias, left.getJavaTypeMapping(), (Object[])null, left.getSQLTable().getGroupName());
               } else {
                  this.stmt.innerJoin(right.getSQLTable(), right.getJavaTypeMapping(), left.getSQLTable().getTable(), leftTblAlias, left.getJavaTypeMapping(), (Object[])null, left.getSQLTable().getGroupName());
               }

               JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
               SQLExpression opExpr = this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, true));
               this.stack.push(opExpr);
               return opExpr;
            }
         } else if (!leftIsCrossJoin && rightIsCrossJoin && !(left instanceof SQLLiteral)) {
            String varName = this.getAliasForSQLTable(right.getSQLTable());
            SQLJoin.JoinType joinType = this.getRequiredJoinTypeForAlias(varName);
            if (joinType != null) {
               NucleusLogger.QUERY.debug("QueryToSQL.eq variable " + varName + " is mapped to table " + right.getSQLTable() + " was previously bound as CROSS JOIN but changing to " + joinType);
               String rightTblAlias = this.stmt.removeCrossJoin(right.getSQLTable());
               if (joinType == SQLJoin.JoinType.LEFT_OUTER_JOIN) {
                  this.stmt.leftOuterJoin(left.getSQLTable(), left.getJavaTypeMapping(), right.getSQLTable().getTable(), rightTblAlias, right.getJavaTypeMapping(), (Object[])null, right.getSQLTable().getGroupName());
               } else {
                  this.stmt.innerJoin(left.getSQLTable(), left.getJavaTypeMapping(), right.getSQLTable().getTable(), rightTblAlias, right.getJavaTypeMapping(), (Object[])null, right.getSQLTable().getGroupName());
               }

               JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
               SQLExpression opExpr = this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, true));
               this.stack.push(opExpr);
               return opExpr;
            }
         }
      }

      BooleanExpression opExpr = left.eq(right);
      this.stack.push(opExpr);
      return opExpr;
   }

   protected Object processNoteqExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (left instanceof ParameterLiteral && !(right instanceof ParameterLiteral)) {
         left = this.replaceParameterLiteral((ParameterLiteral)left, right.getJavaTypeMapping());
      } else if (right instanceof ParameterLiteral && !(left instanceof ParameterLiteral)) {
         right = this.replaceParameterLiteral((ParameterLiteral)right, left.getJavaTypeMapping());
      }

      if (left.isParameter() && right.isParameter()) {
         if (left.isParameter() && left instanceof SQLLiteral && ((SQLLiteral)left).getValue() != null) {
            this.useParameterExpressionAsLiteral((SQLLiteral)left);
         }

         if (right.isParameter() && right instanceof SQLLiteral && ((SQLLiteral)right).getValue() != null) {
            this.useParameterExpressionAsLiteral((SQLLiteral)right);
         }
      }

      ExpressionUtils.checkAndCorrectExpressionMappingsForBooleanComparison(left, right);
      if (left instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)left);
         left = (SQLExpression)this.stack.pop();
      }

      if (right instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)right);
         right = (SQLExpression)this.stack.pop();
      }

      BooleanExpression opExpr = left.ne(right);
      this.stack.push(opExpr);
      return opExpr;
   }

   protected Object processGteqExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (left instanceof ParameterLiteral && !(right instanceof ParameterLiteral)) {
         left = this.replaceParameterLiteral((ParameterLiteral)left, right.getJavaTypeMapping());
      } else if (right instanceof ParameterLiteral && !(left instanceof ParameterLiteral)) {
         right = this.replaceParameterLiteral((ParameterLiteral)right, left.getJavaTypeMapping());
      }

      ExpressionUtils.checkAndCorrectExpressionMappingsForBooleanComparison(left, right);
      if (left instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)left);
         left = (SQLExpression)this.stack.pop();
      }

      if (right instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)right);
         right = (SQLExpression)this.stack.pop();
      }

      BooleanExpression opExpr = left.ge(right);
      this.stack.push(opExpr);
      return opExpr;
   }

   protected Object processGtExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (left instanceof ParameterLiteral && !(right instanceof ParameterLiteral)) {
         left = this.replaceParameterLiteral((ParameterLiteral)left, right.getJavaTypeMapping());
      } else if (right instanceof ParameterLiteral && !(left instanceof ParameterLiteral)) {
         right = this.replaceParameterLiteral((ParameterLiteral)right, left.getJavaTypeMapping());
      }

      ExpressionUtils.checkAndCorrectExpressionMappingsForBooleanComparison(left, right);
      if (left instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)left);
         left = (SQLExpression)this.stack.pop();
      }

      if (right instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)right);
         right = (SQLExpression)this.stack.pop();
      }

      BooleanExpression opExpr = left.gt(right);
      this.stack.push(opExpr);
      return opExpr;
   }

   protected Object processLteqExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (left instanceof ParameterLiteral && !(right instanceof ParameterLiteral)) {
         left = this.replaceParameterLiteral((ParameterLiteral)left, right.getJavaTypeMapping());
      } else if (right instanceof ParameterLiteral && !(left instanceof ParameterLiteral)) {
         right = this.replaceParameterLiteral((ParameterLiteral)right, left.getJavaTypeMapping());
      }

      ExpressionUtils.checkAndCorrectExpressionMappingsForBooleanComparison(left, right);
      if (left instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)left);
         left = (SQLExpression)this.stack.pop();
      }

      if (right instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)right);
         right = (SQLExpression)this.stack.pop();
      }

      BooleanExpression opExpr = left.le(right);
      this.stack.push(opExpr);
      return opExpr;
   }

   protected Object processLtExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (left instanceof ParameterLiteral && !(right instanceof ParameterLiteral)) {
         left = this.replaceParameterLiteral((ParameterLiteral)left, right.getJavaTypeMapping());
      } else if (right instanceof ParameterLiteral && !(left instanceof ParameterLiteral)) {
         right = this.replaceParameterLiteral((ParameterLiteral)right, left.getJavaTypeMapping());
      }

      ExpressionUtils.checkAndCorrectExpressionMappingsForBooleanComparison(left, right);
      if (left instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)left);
         left = (SQLExpression)this.stack.pop();
      }

      if (right instanceof UnboundExpression) {
         this.processUnboundExpression((UnboundExpression)right);
         right = (SQLExpression)this.stack.pop();
      }

      BooleanExpression opExpr = left.lt(right);
      this.stack.push(opExpr);
      return opExpr;
   }

   protected Object processLiteral(Literal expr) {
      Object litValue = expr.getLiteral();
      SQLExpression sqlExpr = this.getSQLLiteralForLiteralValue(litValue);
      this.stack.push(sqlExpr);
      return sqlExpr;
   }

   protected SQLExpression getSQLLiteralForLiteralValue(Object litValue) {
      if (litValue instanceof Class) {
         litValue = ((Class)litValue).getName();
      } else if (litValue instanceof String) {
         String litStr = (String)litValue;
         if (litStr.startsWith("{d ") || litStr.startsWith("{t ") || litStr.startsWith("{ts ")) {
            JavaTypeMapping m = this.exprFactory.getMappingForType(Date.class, false);
            return this.exprFactory.newLiteral(this.stmt, m, litValue);
         }
      }

      JavaTypeMapping m = litValue != null ? this.exprFactory.getMappingForType(litValue.getClass(), false) : null;
      return this.exprFactory.newLiteral(this.stmt, m, litValue);
   }

   protected Object processPrimaryExpression(PrimaryExpression expr) {
      SQLExpression sqlExpr = null;
      if (expr.getLeft() == null) {
         SQLTableMapping sqlMapping = this.getSQLTableMappingForPrimaryExpression(this.stmt, (String)null, expr, (Boolean)null);
         if (sqlMapping == null) {
            throw new NucleusException("PrimaryExpression " + expr.getId() + " is not yet supported");
         } else {
            sqlExpr = this.exprFactory.newExpression(this.stmt, sqlMapping.table, sqlMapping.mapping);
            if (sqlMapping.mmd != null && sqlExpr instanceof MapExpression) {
               String alias = this.getAliasForSQLTableMapping(sqlMapping);
               if (alias == null && this.parentMapper != null) {
                  alias = this.parentMapper.getAliasForSQLTableMapping(sqlMapping);
               }

               ((MapExpression)sqlExpr).setAliasForMapTable(alias);
            }

            this.stack.push(sqlExpr);
            return sqlExpr;
         }
      } else if (expr.getLeft() instanceof DyadicExpression && expr.getLeft().getOperator() == Expression.OP_CAST) {
         String exprCastName = null;
         if (expr.getLeft().getLeft() instanceof PrimaryExpression) {
            exprCastName = "CAST_" + ((PrimaryExpression)expr.getLeft().getLeft()).getId();
         } else if (expr.getLeft().getLeft() instanceof VariableExpression) {
            exprCastName = "CAST_" + ((VariableExpression)expr.getLeft().getLeft()).getId();
         } else {
            if (!(expr.getLeft().getLeft() instanceof InvokeExpression)) {
               throw new NucleusException("Don't currently support cast of " + expr.getLeft().getLeft());
            }

            exprCastName = "CAST_" + expr.getLeft().getLeft();
         }

         expr.getLeft().getLeft().evaluate(this);
         sqlExpr = (SQLExpression)this.stack.pop();
         JavaTypeMapping mapping = sqlExpr.getJavaTypeMapping();
         if (!(mapping instanceof EmbeddedMapping)) {
            expr.getLeft().evaluate(this);
            sqlExpr = (SQLExpression)this.stack.pop();
            Literal castLitExpr = (Literal)expr.getLeft().getRight();
            AbstractClassMetaData castCmd = this.ec.getMetaDataManager().getMetaDataForClass(this.resolveClass((String)castLitExpr.getLiteral()), this.clr);
            SQLTableMapping tblMapping = new SQLTableMapping(sqlExpr.getSQLTable(), castCmd, sqlExpr.getJavaTypeMapping());
            this.setSQLTableMappingForAlias(exprCastName, tblMapping);
            SQLTableMapping sqlMapping = this.getSQLTableMappingForPrimaryExpression(this.stmt, exprCastName, expr, Boolean.FALSE);
            if (sqlMapping == null) {
               throw new NucleusException("PrimaryExpression " + expr + " is not yet supported");
            } else {
               sqlExpr = this.exprFactory.newExpression(this.stmt, sqlMapping.table, sqlMapping.mapping);
               this.stack.push(sqlExpr);
               return sqlExpr;
            }
         } else {
            Literal castLitExpr = (Literal)expr.getLeft().getRight();
            Class castType = this.resolveClass((String)castLitExpr.getLiteral());
            AbstractClassMetaData castCmd = this.ec.getMetaDataManager().getMetaDataForClass(castType, this.clr);
            JavaTypeMapping discMapping = ((EmbeddedMapping)mapping).getDiscriminatorMapping();
            if (discMapping != null) {
               AbstractClassMetaData fieldCmd = this.ec.getMetaDataManager().getMetaDataForClass(castType, this.clr);
               DiscriminatorMetaData dismd = fieldCmd.getDiscriminatorMetaDataRoot();
               SQLExpression discExpr = this.stmt.getSQLExpressionFactory().newExpression(this.stmt, sqlExpr.getSQLTable(), discMapping);
               SQLExpression discVal = null;
               if (dismd.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
                  discVal = this.stmt.getSQLExpressionFactory().newLiteral(this.stmt, discMapping, castCmd.getFullClassName());
               } else {
                  discVal = this.stmt.getSQLExpressionFactory().newLiteral(this.stmt, discMapping, castCmd.getDiscriminatorMetaData().getValue());
               }

               BooleanExpression discRestrictExpr = discExpr.eq(discVal);

               for(String subclassName : this.storeMgr.getSubClassesForClass(castType.getName(), true, this.clr)) {
                  AbstractClassMetaData subtypeCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(subclassName, this.clr);
                  if (dismd.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
                     discVal = this.stmt.getSQLExpressionFactory().newLiteral(this.stmt, discMapping, subtypeCmd.getFullClassName());
                  } else {
                     discVal = this.stmt.getSQLExpressionFactory().newLiteral(this.stmt, discMapping, subtypeCmd.getDiscriminatorMetaData().getValue());
                  }

                  BooleanExpression subtypeExpr = discExpr.eq(discVal);
                  discRestrictExpr = discRestrictExpr.ior(subtypeExpr);
               }

               this.stmt.whereAnd(discRestrictExpr, true);
            }

            SQLTableMapping tblMapping = new SQLTableMapping(sqlExpr.getSQLTable(), castCmd, sqlExpr.getJavaTypeMapping());
            this.setSQLTableMappingForAlias(exprCastName, tblMapping);
            SQLTableMapping sqlMapping = this.getSQLTableMappingForPrimaryExpression(this.stmt, exprCastName, expr, Boolean.FALSE);
            if (sqlMapping == null) {
               throw new NucleusException("PrimaryExpression " + expr + " is not yet supported");
            } else {
               sqlExpr = this.exprFactory.newExpression(this.stmt, sqlMapping.table, sqlMapping.mapping);
               this.stack.push(sqlExpr);
               return sqlExpr;
            }
         }
      } else if (!(expr.getLeft() instanceof ParameterExpression)) {
         if (expr.getLeft() instanceof VariableExpression) {
            VariableExpression varExpr = (VariableExpression)expr.getLeft();
            this.processVariableExpression(varExpr);
            SQLExpression varSqlExpr = (SQLExpression)this.stack.pop();
            if (varSqlExpr instanceof UnboundExpression) {
               this.processUnboundExpression((UnboundExpression)varSqlExpr);
               varSqlExpr = (SQLExpression)this.stack.pop();
            }

            Class varType = this.clr.classForName(varSqlExpr.getJavaTypeMapping().getType());
            if (varSqlExpr.getSQLStatement() == this.stmt.getParentStatement()) {
               SQLTableMapping sqlMapping = this.parentMapper.getSQLTableMappingForPrimaryExpression(this.stmt, (String)null, expr, Boolean.FALSE);
               if (sqlMapping == null) {
                  throw new NucleusException("PrimaryExpression " + expr.getId() + " is not yet supported");
               } else {
                  sqlExpr = this.exprFactory.newExpression(varSqlExpr.getSQLStatement(), sqlMapping.table, sqlMapping.mapping);
                  this.stack.push(sqlExpr);
                  return sqlExpr;
               }
            } else {
               SQLTableMapping varTblMapping = this.getSQLTableMappingForAlias(varExpr.getId());
               if (varTblMapping == null) {
                  throw new NucleusUserException("Variable " + varExpr.getId() + " is not yet bound, so cannot get field " + expr.getId());
               } else if (varTblMapping.cmd == null) {
                  throw new NucleusUserException("Variable " + varExpr.getId() + " of type " + varType.getName() + " cannot evaluate " + expr.getId());
               } else {
                  SQLTableMapping sqlMapping = this.getSQLTableMappingForPrimaryExpression(varSqlExpr.getSQLStatement(), varExpr.getId(), expr, Boolean.FALSE);
                  sqlExpr = this.exprFactory.newExpression(sqlMapping.table.getSQLStatement(), sqlMapping.table, sqlMapping.mapping);
                  this.stack.push(sqlExpr);
                  return sqlExpr;
               }
            }
         } else if (expr.getLeft() instanceof InvokeExpression) {
            this.processInvokeExpression((InvokeExpression)expr.getLeft());
            SQLExpression invokeSqlExpr = (SQLExpression)this.stack.pop();
            Table tbl = invokeSqlExpr.getSQLTable().getTable();
            if (expr.getTuples().size() > 1) {
               throw new NucleusUserException("Dont currently support evaluating " + expr.getId() + " on " + invokeSqlExpr);
            } else if (tbl instanceof DatastoreClass) {
               JavaTypeMapping mapping = ((DatastoreClass)tbl).getMemberMapping(expr.getId());
               if (mapping == null) {
                  throw new NucleusUserException("Dont currently support evaluating " + expr.getId() + " on " + invokeSqlExpr + ". The field " + expr.getId() + " doesnt exist in table " + tbl);
               } else {
                  sqlExpr = this.exprFactory.newExpression(this.stmt, invokeSqlExpr.getSQLTable(), mapping);
                  this.stack.push(sqlExpr);
                  return sqlExpr;
               }
            } else if (tbl instanceof JoinTable && invokeSqlExpr.getJavaTypeMapping() instanceof EmbeddedMapping) {
               EmbeddedMapping embMapping = (EmbeddedMapping)invokeSqlExpr.getJavaTypeMapping();
               JavaTypeMapping mapping = embMapping.getJavaTypeMapping(expr.getId());
               if (mapping == null) {
                  throw new NucleusUserException("Dont currently support evaluating " + expr.getId() + " on " + invokeSqlExpr + ". The field " + expr.getId() + " doesnt exist in table " + tbl);
               } else {
                  sqlExpr = this.exprFactory.newExpression(this.stmt, invokeSqlExpr.getSQLTable(), mapping);
                  this.stack.push(sqlExpr);
                  return sqlExpr;
               }
            } else {
               throw new NucleusUserException("Dont currently support evaluating " + expr.getId() + " on " + invokeSqlExpr + " with invoke having table of " + tbl);
            }
         } else {
            throw new NucleusUserException("Dont currently support PrimaryExpression with 'left' of " + expr.getLeft());
         }
      } else {
         this.setNotPrecompilable();
         ParameterExpression paramExpr = (ParameterExpression)expr.getLeft();
         Symbol paramSym = this.compilation.getSymbolTable().getSymbol(paramExpr.getId());
         if (paramSym.getValueType() != null && paramSym.getValueType().isArray()) {
            String first = (String)expr.getTuples().get(0);
            this.processParameterExpression(paramExpr, true);
            SQLExpression paramSqlExpr = (SQLExpression)this.stack.pop();
            sqlExpr = this.exprFactory.invokeMethod(this.stmt, "ARRAY", first, paramSqlExpr, (List)null);
            this.stack.push(sqlExpr);
            return sqlExpr;
         } else {
            this.processParameterExpression(paramExpr, true);
            SQLExpression paramSqlExpr = (SQLExpression)this.stack.pop();
            SQLLiteral lit = (SQLLiteral)paramSqlExpr;
            Object paramValue = lit.getValue();
            List<String> tuples = expr.getTuples();
            Iterator<String> tuplesIter = tuples.iterator();
            Object objValue = paramValue;

            while(tuplesIter.hasNext()) {
               String fieldName = (String)tuplesIter.next();
               if (objValue == null) {
                  NucleusLogger.QUERY.warn(">> Compilation of " + expr + " : need to direct through field \"" + fieldName + "\" on null value, hence not compilable!");
                  break;
               }

               objValue = this.getValueForObjectField(objValue, fieldName);
               this.setNotPrecompilable();
            }

            if (objValue == null) {
               sqlExpr = this.exprFactory.newLiteral(this.stmt, (JavaTypeMapping)null, (Object)null);
               this.stack.push(sqlExpr);
               return sqlExpr;
            } else {
               JavaTypeMapping m = this.exprFactory.getMappingForType(objValue.getClass(), false);
               sqlExpr = this.exprFactory.newLiteral(this.stmt, m, objValue);
               this.stack.push(sqlExpr);
               return sqlExpr;
            }
         }
      }
   }

   private SQLTableMapping getSQLTableMappingForPrimaryExpression(SQLStatement theStmt, String exprName, PrimaryExpression primExpr, Boolean forceJoin) {
      if (forceJoin == null && primExpr.getParent() != null && (primExpr.getParent().getOperator() == Expression.OP_IS || primExpr.getParent().getOperator() == Expression.OP_ISNOT)) {
         forceJoin = Boolean.TRUE;
      }

      SQLTableMapping sqlMapping = null;
      List<String> tuples = primExpr.getTuples();
      Iterator<String> iter = tuples.iterator();
      String first = (String)tuples.get(0);
      String primaryName = null;
      if (exprName != null) {
         sqlMapping = this.getSQLTableMappingForAlias(exprName);
         primaryName = exprName;
      } else {
         if (this.hasSQLTableMappingForAlias(first)) {
            sqlMapping = this.getSQLTableMappingForAlias(first);
            primaryName = first;
            iter.next();
         }

         if (sqlMapping == null && this.parentMapper != null && this.parentMapper.hasSQLTableMappingForAlias(first)) {
            sqlMapping = this.parentMapper.getSQLTableMappingForAlias(first);
            primaryName = first;
            iter.next();
            theStmt = sqlMapping.table.getSQLStatement();
         }

         if (sqlMapping == null) {
            sqlMapping = this.getSQLTableMappingForAlias(this.candidateAlias);
            primaryName = this.candidateAlias;
         }
      }

      AbstractClassMetaData cmd = sqlMapping.cmd;

      SQLTableMapping sqlMappingNew;
      for(JavaTypeMapping mapping = sqlMapping.mapping; iter.hasNext(); sqlMapping = sqlMappingNew) {
         String component = (String)iter.next();
         primaryName = primaryName + "." + component;
         sqlMappingNew = this.getSQLTableMappingForAlias(primaryName);
         if (sqlMappingNew == null) {
            AbstractMemberMetaData mmd = cmd.getMetaDataForMember(component);
            if (mmd == null) {
               throw new NucleusUserException(Localiser.msg("021062", new Object[]{component, cmd.getFullClassName()}));
            }

            if (mmd.getPersistenceModifier() != FieldPersistenceModifier.PERSISTENT) {
               throw new NucleusUserException("Field " + mmd.getFullFieldName() + " is not marked as persistent so cannot be queried");
            }

            SQLTable sqlTbl = null;
            if (mapping instanceof EmbeddedMapping) {
               sqlTbl = sqlMapping.table;
               mapping = ((EmbeddedMapping)mapping).getJavaTypeMapping(component);
            } else if (mapping instanceof PersistableMapping && cmd.isEmbeddedOnly()) {
               sqlTbl = sqlMapping.table;
               JavaTypeMapping[] subMappings = ((PersistableMapping)mapping).getJavaTypeMapping();
               if (subMappings.length == 1 && subMappings[0] instanceof EmbeddedPCMapping) {
                  mapping = ((EmbeddedPCMapping)subMappings[0]).getJavaTypeMapping(component);
               }
            } else {
               DatastoreClass table = this.storeMgr.getDatastoreClass(cmd.getFullClassName(), this.clr);
               if (table == null) {
                  AbstractClassMetaData[] subCmds = this.storeMgr.getClassesManagingTableForClass(cmd, this.clr);
                  if (subCmds.length != 1) {
                     throw new NucleusUserException("Unable to find table for primary " + primaryName + " since the class " + cmd.getFullClassName() + " is managed in multiple tables");
                  }

                  table = this.storeMgr.getDatastoreClass(subCmds[0].getFullClassName(), this.clr);
               }

               if (table == null) {
                  throw new NucleusUserException("Unable to find table for primary " + primaryName + " table for class=" + cmd.getFullClassName() + " is null : is the field correct? or using some inheritance pattern?");
               }

               mapping = table.getMemberMapping(mmd);
               sqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(theStmt, sqlMapping.table, mapping);
            }

            RelationType relationType = mmd.getRelationType(this.clr);
            if (relationType == RelationType.NONE) {
               sqlMappingNew = new SQLTableMapping(sqlTbl, cmd, mapping);
               cmd = sqlMappingNew.cmd;
               this.setSQLTableMappingForAlias(primaryName, sqlMappingNew);
            } else if (relationType != RelationType.ONE_TO_ONE_UNI && relationType != RelationType.ONE_TO_ONE_BI) {
               if (relationType == RelationType.MANY_TO_ONE_BI) {
                  AbstractMemberMetaData relMmd = mmd.getRelatedMemberMetaData(this.clr)[0];
                  DatastoreClass relTable = this.storeMgr.getDatastoreClass(mmd.getTypeName(), this.clr);
                  if (mmd.getJoinMetaData() == null && relMmd.getJoinMetaData() == null) {
                     sqlTbl = theStmt.getTable(relTable, primaryName);
                     if (sqlTbl == null) {
                        Expression.Operator op = primExpr.getParent() != null ? primExpr.getParent().getOperator() : null;
                        if (iter.hasNext() || op != Expression.OP_EQ && op != Expression.OP_GT && op != Expression.OP_LT && op != Expression.OP_GTEQ && op != Expression.OP_LTEQ && op != Expression.OP_NOTEQ) {
                           if (this.defaultJoinType == SQLJoin.JoinType.INNER_JOIN) {
                              sqlTbl = theStmt.innerJoin(sqlMapping.table, mapping, relTable, (String)null, relTable.getIdMapping(), (Object[])null, primaryName);
                           } else if (this.defaultJoinType == SQLJoin.JoinType.LEFT_OUTER_JOIN || this.defaultJoinType == null) {
                              sqlTbl = theStmt.leftOuterJoin(sqlMapping.table, mapping, relTable, (String)null, relTable.getIdMapping(), (Object[])null, primaryName);
                           }

                           sqlMappingNew = new SQLTableMapping(sqlTbl, relMmd.getAbstractClassMetaData(), relTable.getIdMapping());
                           cmd = sqlMappingNew.cmd;
                           this.setSQLTableMappingForAlias(primaryName, sqlMappingNew);
                        } else {
                           sqlMappingNew = new SQLTableMapping(sqlMapping.table, relMmd.getAbstractClassMetaData(), mapping);
                        }
                     } else {
                        sqlMappingNew = new SQLTableMapping(sqlTbl, relMmd.getAbstractClassMetaData(), relTable.getIdMapping());
                        cmd = sqlMappingNew.cmd;
                        this.setSQLTableMappingForAlias(primaryName, sqlMappingNew);
                     }
                  } else {
                     sqlTbl = theStmt.getTable(relTable, primaryName);
                     if (sqlTbl == null) {
                        CollectionTable joinTbl = (CollectionTable)this.storeMgr.getTable(relMmd);
                        if (this.defaultJoinType == SQLJoin.JoinType.INNER_JOIN) {
                           SQLTable joinSqlTbl = theStmt.innerJoin(sqlMapping.table, sqlMapping.table.getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getElementMapping(), (Object[])null, (String)null);
                           sqlTbl = theStmt.innerJoin(joinSqlTbl, joinTbl.getOwnerMapping(), relTable, (String)null, relTable.getIdMapping(), (Object[])null, primaryName);
                        } else if (this.defaultJoinType == SQLJoin.JoinType.LEFT_OUTER_JOIN || this.defaultJoinType == null) {
                           SQLTable joinSqlTbl = theStmt.leftOuterJoin(sqlMapping.table, sqlMapping.table.getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getElementMapping(), (Object[])null, (String)null);
                           sqlTbl = theStmt.leftOuterJoin(joinSqlTbl, joinTbl.getOwnerMapping(), relTable, (String)null, relTable.getIdMapping(), (Object[])null, primaryName);
                        }
                     }

                     sqlMappingNew = new SQLTableMapping(sqlTbl, relMmd.getAbstractClassMetaData(), relTable.getIdMapping());
                     cmd = sqlMappingNew.cmd;
                     this.setSQLTableMappingForAlias(primaryName, sqlMappingNew);
                  }
               } else if (RelationType.isRelationMultiValued(relationType)) {
                  sqlMappingNew = new SQLTableMapping(sqlTbl, cmd, mapping);
                  cmd = sqlMappingNew.cmd;
                  this.setSQLTableMappingForAlias(primaryName, sqlMappingNew);
               }
            } else if (mmd.getMappedBy() != null) {
               AbstractMemberMetaData relMmd = mmd.getRelatedMemberMetaData(this.clr)[0];
               if (relMmd.getAbstractClassMetaData().isEmbeddedOnly()) {
                  sqlMappingNew = sqlMapping;
                  cmd = relMmd.getAbstractClassMetaData();
               } else {
                  DatastoreClass relTable = this.storeMgr.getDatastoreClass(mmd.getTypeName(), this.clr);
                  JavaTypeMapping relMapping = relTable.getMemberMapping(relMmd);
                  sqlTbl = theStmt.getTable(relTable, primaryName);
                  if (sqlTbl == null) {
                     sqlTbl = SQLStatementHelper.addJoinForOneToOneRelation(theStmt, sqlMapping.table.getTable().getIdMapping(), sqlMapping.table, relMapping, relTable, (String)null, (Object[])null, primaryName, this.defaultJoinType);
                  }

                  if (iter.hasNext()) {
                     sqlMappingNew = new SQLTableMapping(sqlTbl, relMmd.getAbstractClassMetaData(), relTable.getIdMapping());
                     cmd = sqlMappingNew.cmd;
                  } else {
                     sqlMappingNew = new SQLTableMapping(sqlTbl, cmd, relTable.getIdMapping());
                     cmd = sqlMappingNew.cmd;
                  }
               }
            } else {
               if (forceJoin == null && !iter.hasNext() && primExpr.getParent() != null && primExpr.getParent().getOperator() == Expression.OP_CAST && !(mapping instanceof ReferenceMapping)) {
                  AbstractClassMetaData relCmd = this.ec.getMetaDataManager().getMetaDataForClass(mmd.getType(), this.clr);
                  if (relCmd != null && !relCmd.isEmbeddedOnly()) {
                     DatastoreClass relTable = this.storeMgr.getDatastoreClass(relCmd.getFullClassName(), this.clr);
                     if (relTable != null) {
                        forceJoin = Boolean.TRUE;
                     }
                  } else {
                     forceJoin = Boolean.TRUE;
                  }
               }

               if (!iter.hasNext() && !Boolean.TRUE.equals(forceJoin)) {
                  sqlMappingNew = new SQLTableMapping(sqlTbl, cmd, mapping);
                  cmd = sqlMappingNew.cmd;
               } else {
                  AbstractClassMetaData relCmd = null;
                  JavaTypeMapping relMapping = null;
                  DatastoreClass relTable = null;
                  if (relationType == RelationType.ONE_TO_ONE_BI) {
                     AbstractMemberMetaData relMmd = mmd.getRelatedMemberMetaData(this.clr)[0];
                     relCmd = relMmd.getAbstractClassMetaData();
                  } else {
                     relCmd = this.ec.getMetaDataManager().getMetaDataForClass(mmd.getTypeName(), this.clr);
                  }

                  if (relCmd != null && relCmd.isEmbeddedOnly()) {
                     sqlMappingNew = new SQLTableMapping(sqlTbl, relCmd, mapping);
                     cmd = relCmd;
                  } else {
                     relTable = this.storeMgr.getDatastoreClass(relCmd.getFullClassName(), this.clr);
                     if (relTable == null) {
                        Collection<String> relSubclassNames = this.storeMgr.getSubClassesForClass(relCmd.getFullClassName(), false, this.clr);
                        if (relSubclassNames != null && relSubclassNames.size() == 1) {
                           String relSubclassName = (String)relSubclassNames.iterator().next();
                           relTable = this.storeMgr.getDatastoreClass(relSubclassName, this.clr);
                           if (relTable != null) {
                              relCmd = this.ec.getMetaDataManager().getMetaDataForClass(relSubclassName, this.clr);
                           }
                        }

                        if (relTable == null) {
                           throw new NucleusUserException("Reference to PrimaryExpression " + primExpr + " yet this needs to join relation " + mmd.getFullFieldName() + " and the other type has no table (subclass-table?). Maybe use a CAST to the appropriate subclass?");
                        }
                     }

                     relMapping = relTable.getIdMapping();
                     sqlTbl = theStmt.getTable(relTable, primaryName);
                     if (sqlTbl == null) {
                        sqlTbl = SQLStatementHelper.addJoinForOneToOneRelation(theStmt, mapping, sqlMapping.table, relMapping, relTable, (String)null, (Object[])null, primaryName, this.defaultJoinType);
                     }

                     sqlMappingNew = new SQLTableMapping(sqlTbl, relCmd, relMapping);
                     cmd = sqlMappingNew.cmd;
                     this.setSQLTableMappingForAlias(primaryName, sqlMappingNew);
                  }
               }
            }
         } else {
            cmd = sqlMappingNew.cmd;
         }
      }

      return sqlMapping;
   }

   protected Object processParameterExpression(ParameterExpression expr) {
      return this.processParameterExpression(expr, false);
   }

   protected Object processParameterExpression(ParameterExpression expr, boolean asLiteral) {
      if (this.compileComponent == CompilationComponent.ORDERING || this.compileComponent == CompilationComponent.RESULT) {
         asLiteral = true;
      }

      if (expr.getPosition() >= 0) {
         if (this.paramNameByPosition == null) {
            this.paramNameByPosition = new HashMap();
         }

         this.paramNameByPosition.put(expr.getPosition(), expr.getId());
      }

      Object paramValue = null;
      boolean paramValueSet = false;
      if (this.parameters != null && this.parameters.size() > 0) {
         if (this.parameters.containsKey(expr.getId())) {
            paramValue = this.parameters.get(expr.getId());
            paramValueSet = true;
         } else if (this.parameterValueByName != null && this.parameterValueByName.containsKey(expr.getId())) {
            paramValue = this.parameterValueByName.get(expr.getId());
            paramValueSet = true;
         } else {
            int position = this.positionalParamNumber;
            if (this.positionalParamNumber < 0) {
               position = 0;
            }

            if (this.parameters.containsKey(position)) {
               paramValue = this.parameters.get(position);
               paramValueSet = true;
               this.positionalParamNumber = position + 1;
               if (this.parameterValueByName == null) {
                  this.parameterValueByName = new HashMap();
               }

               this.parameterValueByName.put(expr.getId(), paramValue);
            }
         }
      }

      JavaTypeMapping m = (JavaTypeMapping)this.paramMappingForName.get(expr.getId());
      if (m == null) {
         if (paramValue != null) {
            String className = this.storeMgr.getClassNameForObjectID(paramValue, this.clr, this.ec);
            if (className != null) {
               AbstractClassMetaData cmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(className, this.clr);
               if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                  Class cls = this.clr.classForName(className);
                  JavaTypeMapping var11 = this.exprFactory.getMappingForType(cls, false);
                  m = new PersistableIdMapping((PersistableMapping)var11);
               }
            }

            if (m == null) {
               try {
                  m = this.exprFactory.getMappingForType(paramValue.getClass(), false);
               } catch (NucleusUserException var9) {
                  m = this.exprFactory.getMappingForType(expr.getSymbol().getValueType(), false);
               }
            }

            if (expr.getSymbol() != null && expr.getSymbol().getValueType() != null) {
               if (!QueryUtils.queryParameterTypesAreCompatible(expr.getSymbol().getValueType(), paramValue.getClass())) {
                  throw new QueryCompilerSyntaxException(Localiser.msg("021118", new Object[]{expr.getId(), expr.getSymbol().getValueType().getName(), paramValue.getClass().getName()}));
               }

               if (expr.getSymbol().getValueType() != paramValue.getClass()) {
                  this.setNotPrecompilable();
               }
            }
         } else if (expr.getSymbol() != null && expr.getSymbol().getValueType() != null) {
            Class valueType = expr.getSymbol().getValueType();
            if (!paramValueSet && valueType.isInterface()) {
               String[] implNames = this.storeMgr.getMetaDataManager().getClassesImplementingInterface(valueType.getName(), this.clr);
               if (implNames != null && implNames.length > 0) {
                  valueType = this.clr.classForName(implNames[0]);
                  this.setNotPrecompilable();
               }
            }

            m = this.exprFactory.getMappingForType(valueType, false);
         }
      }

      if (asLiteral && m != null && !m.representableAsStringLiteralInStatement()) {
         asLiteral = false;
      }

      if (asLiteral) {
         if (this.isPrecompilable()) {
            NucleusLogger.QUERY.debug("Parameter " + expr + " is being resolved as a literal, so the query is no longer precompilable");
         }

         this.setNotPrecompilable();
      } else if (paramValue == null && expr.getSymbol() != null) {
         if (this.isPrecompilable()) {
            NucleusLogger.QUERY.debug("Parameter " + expr + " is set to null so this has to be resolved as a NullLiteral, and the query is no longer precompilable");
         }

         this.setNotPrecompilable();
      }

      SQLExpression sqlExpr = null;
      boolean nullParamValueUsesIsNull = this.options.contains("USE_IS_NULL_FOR_NULL_PARAM");
      if (paramValueSet && paramValue == null && nullParamValueUsesIsNull) {
         sqlExpr = this.exprFactory.newLiteral(this.stmt, (JavaTypeMapping)null, (Object)null);
      } else if (asLiteral) {
         sqlExpr = this.exprFactory.newLiteral(this.stmt, m, paramValue);
      } else {
         sqlExpr = this.exprFactory.newLiteralParameter(this.stmt, m, paramValue, expr.getId());
         if (sqlExpr instanceof ParameterLiteral) {
            ((ParameterLiteral)sqlExpr).setName(expr.getId());
         }

         if (this.expressionForParameter == null) {
            this.expressionForParameter = new HashMap();
         }

         this.expressionForParameter.put(expr.getId(), sqlExpr);
         this.paramMappingForName.put(expr.getId(), m);
      }

      this.stack.push(sqlExpr);
      return sqlExpr;
   }

   protected Object processInvokeExpression(InvokeExpression expr) {
      Expression invokedExpr = expr.getLeft();
      SQLExpression invokedSqlExpr = null;
      if (invokedExpr != null) {
         if (invokedExpr instanceof PrimaryExpression) {
            this.processPrimaryExpression((PrimaryExpression)invokedExpr);
            invokedSqlExpr = (SQLExpression)this.stack.pop();
         } else if (invokedExpr instanceof Literal) {
            this.processLiteral((Literal)invokedExpr);
            invokedSqlExpr = (SQLExpression)this.stack.pop();
         } else if (invokedExpr instanceof ParameterExpression) {
            this.processParameterExpression((ParameterExpression)invokedExpr, true);
            invokedSqlExpr = (SQLExpression)this.stack.pop();
         } else if (invokedExpr instanceof InvokeExpression) {
            this.processInvokeExpression((InvokeExpression)invokedExpr);
            invokedSqlExpr = (SQLExpression)this.stack.pop();
         } else if (invokedExpr instanceof VariableExpression) {
            this.processVariableExpression((VariableExpression)invokedExpr);
            invokedSqlExpr = (SQLExpression)this.stack.pop();
         } else if (invokedExpr instanceof ArrayExpression) {
            ArrayExpression arrExpr = (ArrayExpression)invokedExpr;
            SQLExpression[] arrSqlExprs = new SQLExpression[arrExpr.getArraySize()];

            for(int i = 0; i < arrExpr.getArraySize(); ++i) {
               Expression arrElemExpr = arrExpr.getElement(i);
               arrElemExpr.evaluate(this);
               arrSqlExprs[i] = (SQLExpression)this.stack.pop();
            }

            JavaTypeMapping m = this.exprFactory.getMappingForType(Object[].class, false);
            invokedSqlExpr = new org.datanucleus.store.rdbms.sql.expression.ArrayExpression(this.stmt, m, arrSqlExprs);
         } else {
            if (!(invokedExpr instanceof DyadicExpression)) {
               throw new NucleusException("Dont currently support invoke expression " + invokedExpr);
            }

            DyadicExpression dyExpr = (DyadicExpression)invokedExpr;
            dyExpr.evaluate(this);
            invokedSqlExpr = (SQLExpression)this.stack.pop();
         }
      }

      if (invokedSqlExpr instanceof NullLiteral) {
         NucleusLogger.QUERY.warn(">> Compilation of InvokeExpression needs to invoke method \"" + expr.getOperation() + "\" on " + invokedSqlExpr + " but not possible");
      }

      String operation = expr.getOperation();
      if (invokedSqlExpr instanceof MapExpression && operation.equals("contains") && this.compilation.getQueryLanguage().equalsIgnoreCase("JPQL")) {
         operation = "containsValue";
      }

      List args = expr.getArguments();
      List sqlExprArgs = null;
      if (args != null) {
         sqlExprArgs = new ArrayList();

         for(Expression argExpr : args) {
            if (argExpr instanceof PrimaryExpression) {
               this.processPrimaryExpression((PrimaryExpression)argExpr);
               SQLExpression argSqlExpr = (SQLExpression)this.stack.pop();
               if (this.compileComponent == CompilationComponent.RESULT && operation.equalsIgnoreCase("count") && this.stmt.getNumberOfTableGroups() > 1 && argSqlExpr.getSQLTable() == this.stmt.getPrimaryTable() && argSqlExpr.getJavaTypeMapping() == this.stmt.getPrimaryTable().getTable().getIdMapping()) {
                  argSqlExpr.distinct();
               }

               sqlExprArgs.add(argSqlExpr);
            } else if (argExpr instanceof ParameterExpression) {
               this.processParameterExpression((ParameterExpression)argExpr);
               sqlExprArgs.add(this.stack.pop());
            } else if (argExpr instanceof InvokeExpression) {
               this.processInvokeExpression((InvokeExpression)argExpr);
               sqlExprArgs.add(this.stack.pop());
            } else if (argExpr instanceof Literal) {
               this.processLiteral((Literal)argExpr);
               sqlExprArgs.add(this.stack.pop());
            } else if (argExpr instanceof DyadicExpression) {
               argExpr.evaluate(this);
               sqlExprArgs.add(this.stack.pop());
            } else if (argExpr instanceof VariableExpression) {
               this.processVariableExpression((VariableExpression)argExpr);
               sqlExprArgs.add(this.stack.pop());
            } else {
               if (!(argExpr instanceof CaseExpression)) {
                  throw new NucleusException("Dont currently support invoke expression argument " + argExpr);
               }

               this.processCaseExpression((CaseExpression)argExpr);
               sqlExprArgs.add(this.stack.pop());
            }
         }

         if (operation.equals("INDEX")) {
            List<Expression> indexArgs = expr.getArguments();
            if (indexArgs == null || indexArgs.size() > 1) {
               throw new NucleusException("Can only use INDEX with single argument");
            }

            PrimaryExpression indexExpr = (PrimaryExpression)indexArgs.get(0);
            String joinAlias = indexExpr.getId();
            String collExprName = joinAlias;
            if (this.explicitJoinPrimaryByAlias != null) {
               collExprName = (String)this.explicitJoinPrimaryByAlias.get(joinAlias);
               if (collExprName == null) {
                  throw new NucleusException("Unable to locate primary expression for alias " + joinAlias);
               }
            }

            List<String> tuples = new ArrayList();
            StringTokenizer primTokenizer = new StringTokenizer(collExprName, ".");

            while(primTokenizer.hasMoreTokens()) {
               String token = primTokenizer.nextToken();
               tuples.add(token);
            }

            PrimaryExpression collPrimExpr = new PrimaryExpression(tuples);
            this.processPrimaryExpression(collPrimExpr);
            SQLExpression collSqlExpr = (SQLExpression)this.stack.pop();
            sqlExprArgs.add(collSqlExpr);
         }
      }

      SQLExpression sqlExpr = null;
      if (invokedSqlExpr != null) {
         sqlExpr = invokedSqlExpr.invoke(operation, sqlExprArgs);
      } else {
         sqlExpr = this.exprFactory.invokeMethod(this.stmt, (String)null, operation, (SQLExpression)null, sqlExprArgs);
      }

      this.stack.push(sqlExpr);
      return sqlExpr;
   }

   protected Object processSubqueryExpression(SubqueryExpression expr) {
      String keyword = expr.getKeyword();
      Expression subqueryExpr = expr.getRight();
      if (!(subqueryExpr instanceof VariableExpression)) {
         throw new NucleusException("Dont currently support SubqueryExpression " + keyword + " for type " + subqueryExpr);
      } else {
         this.processVariableExpression((VariableExpression)subqueryExpr);
         SQLExpression subquerySqlExpr = (SQLExpression)this.stack.pop();
         if (keyword != null && keyword.equals("EXISTS")) {
            if (subquerySqlExpr instanceof org.datanucleus.store.rdbms.sql.expression.SubqueryExpression) {
               SQLStatement subStmt = ((org.datanucleus.store.rdbms.sql.expression.SubqueryExpression)subquerySqlExpr).getSubqueryStatement();
               subquerySqlExpr = new BooleanSubqueryExpression(this.stmt, keyword, subStmt);
            } else {
               SQLStatement subStmt = ((SubqueryExpressionComponent)subquerySqlExpr).getSubqueryStatement();
               subquerySqlExpr = new BooleanSubqueryExpression(this.stmt, keyword, subStmt);
            }
         } else if (subquerySqlExpr instanceof org.datanucleus.store.rdbms.sql.expression.SubqueryExpression) {
            SQLStatement subStmt = ((org.datanucleus.store.rdbms.sql.expression.SubqueryExpression)subquerySqlExpr).getSubqueryStatement();
            subquerySqlExpr = new BooleanSubqueryExpression(this.stmt, keyword, subStmt);
         } else if (subquerySqlExpr instanceof NumericSubqueryExpression) {
            if ((keyword.equalsIgnoreCase("SOME") || keyword.equalsIgnoreCase("ALL") || keyword.equalsIgnoreCase("ANY")) && !this.storeMgr.getDatastoreAdapter().supportsOption("SomeAllAnySubqueries")) {
               throw new NucleusException("'SOME|ALL|ANY{subquery}' not supported by this datastore");
            }

            ((NumericSubqueryExpression)subquerySqlExpr).setKeyword(keyword);
         }

         this.stack.push(subquerySqlExpr);
         return subquerySqlExpr;
      }
   }

   protected Object processAddExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (left instanceof ParameterLiteral && !(right instanceof ParameterLiteral)) {
         left = this.replaceParameterLiteral((ParameterLiteral)left, right.getJavaTypeMapping());
      } else if (right instanceof ParameterLiteral && !(left instanceof ParameterLiteral)) {
         right = this.replaceParameterLiteral((ParameterLiteral)right, left.getJavaTypeMapping());
      }

      SQLExpression resultExpr = left.add(right);
      this.stack.push(resultExpr);
      return resultExpr;
   }

   protected Object processDivExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      SQLExpression resultExpr = left.div(right);
      this.stack.push(resultExpr);
      return resultExpr;
   }

   protected Object processMulExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      SQLExpression resultExpr = left.mul(right);
      this.stack.push(resultExpr);
      return resultExpr;
   }

   protected Object processSubExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (left instanceof ParameterLiteral && !(right instanceof ParameterLiteral)) {
         left = this.replaceParameterLiteral((ParameterLiteral)left, right.getJavaTypeMapping());
      } else if (right instanceof ParameterLiteral && !(left instanceof ParameterLiteral)) {
         right = this.replaceParameterLiteral((ParameterLiteral)right, left.getJavaTypeMapping());
      }

      SQLExpression resultExpr = left.sub(right);
      this.stack.push(resultExpr);
      return resultExpr;
   }

   protected Object processDistinctExpression(Expression expr) {
      SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
      sqlExpr.distinct();
      this.stack.push(sqlExpr);
      return sqlExpr;
   }

   protected Object processComExpression(Expression expr) {
      SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
      SQLExpression resultExpr = sqlExpr.com();
      this.stack.push(resultExpr);
      return resultExpr;
   }

   protected Object processModExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      SQLExpression resultExpr = left.mod(right);
      this.stack.push(resultExpr);
      return resultExpr;
   }

   protected Object processNegExpression(Expression expr) {
      SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
      SQLExpression resultExpr = sqlExpr.neg();
      this.stack.push(resultExpr);
      return resultExpr;
   }

   protected Object processNotExpression(Expression expr) {
      SQLExpression sqlExpr = (SQLExpression)this.stack.pop();
      SQLExpression resultExpr = sqlExpr.not();
      this.stack.push(resultExpr);
      return resultExpr;
   }

   protected Object processCastExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      SQLExpression castExpr = left.cast(right);
      this.stack.push(castExpr);
      return castExpr;
   }

   protected Object processCaseExpression(CaseExpression expr) {
      boolean numericCase = false;
      boolean booleanCase = false;
      boolean stringCase = false;
      List<CaseExpression.ExpressionPair> conditions = expr.getConditions();
      Iterator<CaseExpression.ExpressionPair> whenExprIter = conditions.iterator();
      SQLExpression[] whenSqlExprs = new SQLExpression[conditions.size()];
      SQLExpression[] actionSqlExprs = new SQLExpression[conditions.size()];

      for(int i = 0; whenExprIter.hasNext(); ++i) {
         CaseExpression.ExpressionPair pair = (CaseExpression.ExpressionPair)whenExprIter.next();
         Expression whenExpr = pair.getWhenExpression();
         whenExpr.evaluate(this);
         whenSqlExprs[i] = (SQLExpression)this.stack.pop();
         if (!(whenSqlExprs[i] instanceof BooleanExpression)) {
            throw new QueryCompilerSyntaxException("IF/ELSE conditional expression should return boolean but doesn't : " + expr);
         }

         Expression actionExpr = pair.getActionExpression();
         actionExpr.evaluate(this);
         actionSqlExprs[i] = (SQLExpression)this.stack.pop();
         if (actionSqlExprs[i] instanceof NumericExpression) {
            numericCase = true;
         } else if (actionSqlExprs[i] instanceof BooleanExpression) {
            booleanCase = true;
         } else if (actionSqlExprs[i] instanceof StringExpression) {
            stringCase = true;
         }
      }

      Expression elseExpr = expr.getElseExpression();
      elseExpr.evaluate(this);
      SQLExpression elseActionSqlExpr = (SQLExpression)this.stack.pop();

      for(int j = 1; j < actionSqlExprs.length; ++j) {
         if (!this.checkCaseExpressionsConsistent(actionSqlExprs[0], actionSqlExprs[j])) {
            throw new QueryCompilerSyntaxException("IF/ELSE action expression " + actionSqlExprs[j] + " is of different type to first action " + actionSqlExprs[0] + " - must be consistent");
         }
      }

      if (!this.checkCaseExpressionsConsistent(actionSqlExprs[0], elseActionSqlExpr)) {
         throw new QueryCompilerSyntaxException("IF/ELSE action expression " + elseActionSqlExpr + " is of different type to first action " + actionSqlExprs[0] + " - must be consistent");
      } else {
         SQLExpression caseSqlExpr = null;
         if (numericCase) {
            caseSqlExpr = new CaseNumericExpression(whenSqlExprs, actionSqlExprs, elseActionSqlExpr);
         } else if (booleanCase) {
            caseSqlExpr = new CaseBooleanExpression(whenSqlExprs, actionSqlExprs, elseActionSqlExpr);
         } else if (stringCase) {
            caseSqlExpr = new CaseStringExpression(whenSqlExprs, actionSqlExprs, elseActionSqlExpr);
         } else {
            caseSqlExpr = new org.datanucleus.store.rdbms.sql.expression.CaseExpression(whenSqlExprs, actionSqlExprs, elseActionSqlExpr);
         }

         this.stack.push(caseSqlExpr);
         return caseSqlExpr;
      }
   }

   private boolean checkCaseExpressionsConsistent(SQLExpression expr1, SQLExpression expr2) {
      if (expr1 instanceof NumericExpression && expr2 instanceof NumericExpression) {
         return true;
      } else if (expr1 instanceof StringExpression && expr2 instanceof StringExpression) {
         return true;
      } else if (expr1 instanceof BooleanExpression && expr2 instanceof BooleanExpression) {
         return true;
      } else if (expr1 instanceof TemporalExpression && expr2 instanceof TemporalExpression) {
         return true;
      } else {
         return expr1.getClass().isAssignableFrom(expr2.getClass()) || expr2.getClass().isAssignableFrom(expr1.getClass());
      }
   }

   protected Object processIsExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      SQLExpression instanceofExpr = left.is(right, false);
      this.stack.push(instanceofExpr);
      return instanceofExpr;
   }

   protected Object processIsnotExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      SQLExpression instanceofExpr = left.is(right, true);
      this.stack.push(instanceofExpr);
      return instanceofExpr;
   }

   protected Object processInExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (right.getParameterName() != null) {
         this.setNotPrecompilable();
         if (right instanceof CollectionLiteral) {
            List<SQLExpression> sqlExprArgs = new ArrayList();
            sqlExprArgs.add(left);
            SQLExpression sqlExpr = right.invoke("contains", sqlExprArgs);
            this.stack.push(sqlExpr);
            return sqlExpr;
         } else {
            SQLExpression inExpr = new BooleanExpression(left, Expression.OP_EQ, right);
            this.stack.push(inExpr);
            return inExpr;
         }
      } else {
         SQLExpression inExpr = left.in(right, false);
         this.stack.push(inExpr);
         return inExpr;
      }
   }

   protected Object processNotInExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      if (right.getParameterName() != null) {
         this.setNotPrecompilable();
         if (right instanceof CollectionLiteral) {
            List<SQLExpression> sqlExprArgs = new ArrayList();
            sqlExprArgs.add(left);
            SQLExpression sqlExpr = right.invoke("contains", sqlExprArgs);
            sqlExpr.not();
            this.stack.push(sqlExpr);
            return sqlExpr;
         } else {
            SQLExpression inExpr = new BooleanExpression(left, Expression.OP_NOTEQ, right);
            this.stack.push(inExpr);
            return inExpr;
         }
      } else {
         SQLExpression inExpr = left.in(right, true);
         this.stack.push(inExpr);
         return inExpr;
      }
   }

   protected Object processCreatorExpression(CreatorExpression expr) {
      String className = expr.getId();
      Class cls = null;

      try {
         cls = this.clr.classForName(className);
      } catch (ClassNotResolvedException var11) {
         if (this.importsDefinition != null) {
            cls = this.importsDefinition.resolveClassDeclaration(className, this.clr, (ClassLoader)null);
         }
      }

      List<SQLExpression> ctrArgExprs = null;
      List args = expr.getArguments();
      if (args != null) {
         Class[] ctrArgTypes = new Class[args.size()];
         ctrArgExprs = new ArrayList(args.size());
         Iterator iter = args.iterator();

         for(int i = 0; iter.hasNext(); ++i) {
            Expression argExpr = (Expression)iter.next();
            SQLExpression sqlExpr = (SQLExpression)this.evaluate(argExpr);
            ctrArgExprs.add(sqlExpr);
            if (sqlExpr instanceof NewObjectExpression) {
               ctrArgTypes[i] = ((NewObjectExpression)sqlExpr).getNewClass();
            } else if (!(sqlExpr.getJavaTypeMapping() instanceof DatastoreIdMapping) && !(sqlExpr.getJavaTypeMapping() instanceof PersistableMapping)) {
               ctrArgTypes[i] = sqlExpr.getJavaTypeMapping().getJavaType();
            } else {
               ctrArgTypes[i] = this.clr.classForName(sqlExpr.getJavaTypeMapping().getType());
            }
         }

         Constructor ctr = ClassUtils.getConstructorWithArguments(cls, ctrArgTypes);
         if (ctr == null) {
            throw new NucleusUserException(Localiser.msg("021033", new Object[]{className, StringUtils.objectArrayToString(ctrArgTypes)}));
         }
      }

      NewObjectExpression newExpr = new NewObjectExpression(this.stmt, cls, ctrArgExprs);
      this.stack.push(newExpr);
      return newExpr;
   }

   protected Object processLikeExpression(Expression expr) {
      SQLExpression right = (SQLExpression)this.stack.pop();
      SQLExpression left = (SQLExpression)this.stack.pop();
      List args = new ArrayList();
      args.add(right);
      SQLExpression likeExpr = this.exprFactory.invokeMethod(this.stmt, String.class.getName(), "like", left, args);
      this.stack.push(likeExpr);
      return likeExpr;
   }

   protected Object processVariableExpression(VariableExpression expr) {
      String varName = expr.getId();
      Symbol varSym = expr.getSymbol();
      if (varSym != null) {
         varName = varSym.getQualifiedName();
      }

      if (this.hasSQLTableMappingForAlias(varName)) {
         SQLTableMapping tblMapping = this.getSQLTableMappingForAlias(varName);
         SQLExpression sqlExpr = this.exprFactory.newExpression(tblMapping.table.getSQLStatement(), tblMapping.table, tblMapping.mapping);
         this.stack.push(sqlExpr);
         return sqlExpr;
      } else if (this.compilation.getCompilationForSubquery(varName) != null) {
         QueryCompilation subCompilation = this.compilation.getCompilationForSubquery(varName);
         AbstractClassMetaData subCmd = this.ec.getMetaDataManager().getMetaDataForClass(subCompilation.getCandidateClass(), this.ec.getClassLoaderResolver());
         String subAlias = null;
         if (subCompilation.getCandidateAlias() != null && !subCompilation.getCandidateAlias().equals(this.candidateAlias)) {
            subAlias = subCompilation.getCandidateAlias();
         }

         StatementResultMapping subqueryResultMapping = new StatementResultMapping();
         SQLStatement subStmt = RDBMSQueryUtils.getStatementForCandidates(this.storeMgr, this.stmt, subCmd, (StatementClassMapping)null, this.ec, subCompilation.getCandidateClass(), true, "avg(something)", subAlias, (String)null);
         QueryToSQLMapper sqlMapper = new QueryToSQLMapper(subStmt, subCompilation, this.parameters, (StatementClassMapping)null, subqueryResultMapping, subCmd, true, this.fetchPlan, this.ec, this.importsDefinition, this.options, this.extensionsByName);
         sqlMapper.setParentMapper(this);
         sqlMapper.compile();
         if (subqueryResultMapping.getNumberOfResultExpressions() > 1) {
            throw new NucleusUserException("Number of result expressions in subquery should be 1");
         } else {
            SQLExpression subExpr = null;
            if (subqueryResultMapping.getNumberOfResultExpressions() == 0) {
               subExpr = new org.datanucleus.store.rdbms.sql.expression.SubqueryExpression(this.stmt, subStmt);
            } else {
               JavaTypeMapping subMapping = ((StatementMappingIndex)subqueryResultMapping.getMappingForResultExpression(0)).getMapping();
               if (subMapping instanceof TemporalMapping) {
                  subExpr = new TemporalSubqueryExpression(this.stmt, subStmt);
               } else if (subMapping instanceof StringMapping) {
                  subExpr = new StringSubqueryExpression(this.stmt, subStmt);
               } else {
                  subExpr = new NumericSubqueryExpression(this.stmt, subStmt);
               }

               if (subExpr.getJavaTypeMapping() == null) {
                  subExpr.setJavaTypeMapping(subMapping);
               }
            }

            this.stack.push(subExpr);
            return subExpr;
         }
      } else if (this.stmt.getParentStatement() != null && this.parentMapper != null && this.parentMapper.candidateAlias != null && this.parentMapper.candidateAlias.equals(varName)) {
         SQLExpression varExpr = this.exprFactory.newExpression(this.stmt.getParentStatement(), this.stmt.getParentStatement().getPrimaryTable(), this.stmt.getParentStatement().getPrimaryTable().getTable().getIdMapping());
         this.stack.push(varExpr);
         return varExpr;
      } else {
         NucleusLogger.QUERY.debug("QueryToSQL.processVariable (unbound) variable=" + varName + " is not yet bound so returning UnboundExpression");
         UnboundExpression unbExpr = new UnboundExpression(this.stmt, varName);
         this.stack.push(unbExpr);
         return unbExpr;
      }
   }

   protected SQLExpression processUnboundExpression(UnboundExpression expr) {
      String varName = expr.getVariableName();
      Symbol varSym = this.compilation.getSymbolTable().getSymbol(varName);
      SQLExpression sqlExpr = this.bindVariable(expr, varSym.getValueType());
      if (sqlExpr != null) {
         this.stack.push(sqlExpr);
         return sqlExpr;
      } else {
         throw new NucleusUserException("Variable '" + varName + "' is unbound and cannot be determined (is it a misspelled field name? or is not intended to be a variable?)");
      }
   }

   protected SQLExpression replaceParameterLiteral(ParameterLiteral paramLit, JavaTypeMapping mapping) {
      return this.exprFactory.newLiteralParameter(this.stmt, mapping, paramLit.getValue(), paramLit.getParameterName());
   }

   public void useParameterExpressionAsLiteral(SQLLiteral paramLiteral) {
      paramLiteral.setNotParameter();
      this.setNotPrecompilable();
   }

   public boolean hasExtension(String key) {
      return this.extensionsByName == null ? false : this.extensionsByName.containsKey(key);
   }

   public Object getValueForExtension(String key) {
      return this.extensionsByName == null ? null : this.extensionsByName.get(key);
   }

   public SQLJoin.JoinType getRequiredJoinTypeForAlias(String alias) {
      if (alias == null) {
         return null;
      } else if (alias.equals(this.candidateAlias)) {
         return null;
      } else {
         String extensionName = "datanucleus.query.jdoql." + alias + ".join";
         SQLJoin.JoinType joinType = null;
         if (this.hasExtension(extensionName)) {
            String joinValue = (String)this.getValueForExtension(extensionName);
            if (joinValue.equalsIgnoreCase("INNERJOIN")) {
               joinType = SQLJoin.JoinType.INNER_JOIN;
            } else if (joinValue.equalsIgnoreCase("LEFTOUTERJOIN")) {
               joinType = SQLJoin.JoinType.LEFT_OUTER_JOIN;
            }
         }

         return joinType;
      }
   }

   protected Object getValueForObjectField(Object obj, String fieldName) {
      if (obj != null) {
         Object paramFieldValue = null;
         if (this.ec.getApiAdapter().isPersistable(obj)) {
            ObjectProvider paramSM = this.ec.findObjectProvider(obj);
            AbstractClassMetaData paramCmd = this.ec.getMetaDataManager().getMetaDataForClass(obj.getClass(), this.clr);
            AbstractMemberMetaData paramFieldMmd = paramCmd.getMetaDataForMember(fieldName);
            if (paramSM != null) {
               paramSM.isLoaded(paramFieldMmd.getAbsoluteFieldNumber());
               paramFieldValue = paramSM.provideField(paramFieldMmd.getAbsoluteFieldNumber());
            } else {
               paramFieldValue = ClassUtils.getValueOfFieldByReflection(obj, fieldName);
            }
         } else {
            paramFieldValue = ClassUtils.getValueOfFieldByReflection(obj, fieldName);
         }

         return paramFieldValue;
      } else {
         return null;
      }
   }

   protected SQLTableMapping getSQLTableMappingForAlias(String alias) {
      if (alias == null) {
         return null;
      } else {
         return this.options.contains("CASE_INSENSITIVE") ? (SQLTableMapping)this.sqlTableByPrimary.get(alias.toUpperCase()) : (SQLTableMapping)this.sqlTableByPrimary.get(alias);
      }
   }

   public String getAliasForSQLTableMapping(SQLTableMapping tblMapping) {
      for(Map.Entry entry : this.sqlTableByPrimary.entrySet()) {
         if (entry.getValue() == tblMapping) {
            return (String)entry.getKey();
         }
      }

      return null;
   }

   public String getAliasForSQLTable(SQLTable tbl) {
      Iterator<Map.Entry<String, SQLTableMapping>> iter = this.sqlTableByPrimary.entrySet().iterator();
      String alias = null;

      while(iter.hasNext()) {
         Map.Entry<String, SQLTableMapping> entry = (Map.Entry)iter.next();
         if (((SQLTableMapping)entry.getValue()).table == tbl) {
            if (alias == null) {
               alias = (String)entry.getKey();
            } else if (((String)entry.getKey()).length() < alias.length()) {
               alias = (String)entry.getKey();
            }
         }
      }

      return alias;
   }

   protected void setSQLTableMappingForAlias(String alias, SQLTableMapping mapping) {
      if (alias != null) {
         if (this.options.contains("CASE_INSENSITIVE")) {
            this.sqlTableByPrimary.put(alias.toUpperCase(), mapping);
         } else {
            this.sqlTableByPrimary.put(alias, mapping);
         }

      }
   }

   protected boolean hasSQLTableMappingForAlias(String alias) {
      return this.options.contains("CASE_INSENSITIVE") ? this.sqlTableByPrimary.containsKey(alias.toUpperCase()) : this.sqlTableByPrimary.containsKey(alias);
   }

   public void bindVariable(String varName, AbstractClassMetaData cmd, SQLTable sqlTbl, JavaTypeMapping mapping) {
      SQLTableMapping m = this.getSQLTableMappingForAlias(varName);
      if (m != null) {
         throw new NucleusException("Variable " + varName + " is already bound to " + m.table + " yet attempting to bind to " + sqlTbl);
      } else {
         NucleusLogger.QUERY.debug("QueryToSQL.bindVariable variable " + varName + " being bound to table=" + sqlTbl + " mapping=" + mapping);
         m = new SQLTableMapping(sqlTbl, cmd, mapping);
         this.setSQLTableMappingForAlias(varName, m);
      }
   }

   public SQLExpression bindVariable(UnboundExpression expr, Class type) {
      String varName = expr.getVariableName();
      Symbol varSym = this.compilation.getSymbolTable().getSymbol(varName);
      if (varSym.getValueType() == null) {
         varSym.setValueType(type);
      }

      AbstractClassMetaData cmd = this.ec.getMetaDataManager().getMetaDataForClass(type, this.clr);
      if (cmd != null) {
         DatastoreClass varTable = this.storeMgr.getDatastoreClass(varSym.getValueType().getName(), this.clr);
         SQLTable varSqlTbl = this.stmt.crossJoin(varTable, "VAR_" + varName, (String)null);
         SQLTableMapping varSqlTblMapping = new SQLTableMapping(varSqlTbl, cmd, varTable.getIdMapping());
         this.setSQLTableMappingForAlias(varName, varSqlTblMapping);
         return this.exprFactory.newExpression(this.stmt, varSqlTbl, varTable.getIdMapping());
      } else {
         return null;
      }
   }

   public void bindParameter(String paramName, Class type) {
      Symbol paramSym = this.compilation.getSymbolTable().getSymbol(paramName);
      if (paramSym != null && paramSym.getValueType() == null) {
         paramSym.setValueType(type);
      }

   }

   public Class getTypeOfVariable(String varName) {
      Symbol sym = this.compilation.getSymbolTable().getSymbol(varName);
      return sym != null && sym.getValueType() != null ? sym.getValueType() : null;
   }

   public boolean hasExplicitJoins() {
      return this.options.contains("EXPLICIT_JOINS");
   }

   protected BooleanExpression getBooleanExpressionForUseInFilter(BooleanExpression expr) {
      if (this.compileComponent != CompilationComponent.FILTER) {
         return expr;
      } else {
         return !expr.hasClosure() ? new BooleanExpression(expr, Expression.OP_EQ, new BooleanLiteral(this.stmt, expr.getJavaTypeMapping(), Boolean.TRUE, (String)null)) : expr;
      }
   }

   public Class resolveClass(String className) {
      Class cls = null;

      try {
         cls = this.clr.classForName(className);
      } catch (ClassNotResolvedException var4) {
         if (this.importsDefinition != null) {
            cls = this.importsDefinition.resolveClassDeclaration(className, this.clr, (ClassLoader)null);
         }
      }

      if (cls == null && this.compilation.getQueryLanguage().equalsIgnoreCase("JPQL")) {
         AbstractClassMetaData cmd = this.ec.getMetaDataManager().getMetaDataForEntityName(className);
         if (cmd != null) {
            return this.clr.classForName(cmd.getFullClassName());
         }
      }

      return cls;
   }

   static class SQLTableMapping {
      SQLTable table;
      AbstractClassMetaData cmd;
      JavaTypeMapping mapping;
      AbstractMemberMetaData mmd;

      public SQLTableMapping(SQLTable tbl, AbstractClassMetaData cmd, JavaTypeMapping m) {
         this.table = tbl;
         this.cmd = cmd;
         this.mmd = null;
         this.mapping = m;
      }

      public SQLTableMapping(SQLTable tbl, AbstractMemberMetaData mmd, JavaTypeMapping m) {
         this.table = tbl;
         this.cmd = mmd.getAbstractClassMetaData();
         this.mmd = mmd;
         this.mapping = m;
      }

      public String toString() {
         return this.mmd != null ? "SQLTableMapping: tbl=" + this.table + " member=" + this.mmd.getFullFieldName() + " mapping=" + this.mapping : "SQLTableMapping: tbl=" + this.table + " class=" + (this.cmd != null ? this.cmd.getFullClassName() : "null") + " mapping=" + this.mapping;
      }
   }
}
