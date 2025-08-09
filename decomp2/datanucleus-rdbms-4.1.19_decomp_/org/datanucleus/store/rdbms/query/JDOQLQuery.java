package org.datanucleus.store.rdbms.query;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlanForClass;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.evaluator.JDOQLEvaluator;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.connection.ManagedConnectionResourceListener;
import org.datanucleus.store.query.AbstractJDOQLQuery;
import org.datanucleus.store.query.CandidateIdsQueryResult;
import org.datanucleus.store.query.QueryInterruptedException;
import org.datanucleus.store.query.QueryManager;
import org.datanucleus.store.query.QueryResult;
import org.datanucleus.store.query.QueryTimeoutException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.AbstractContainerMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.scostore.IteratorStatement;
import org.datanucleus.store.rdbms.sql.SQLJoin;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Imports;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class JDOQLQuery extends AbstractJDOQLQuery {
   private static final long serialVersionUID = -937448796638243699L;
   public static final String EXTENSION_USE_IS_NULL_WHEN_EQUALS_NULL_PARAM = "datanucleus.useIsNullWhenEqualsNullParameter";
   public static final String EXTENSION_FOR_UPDATE_NOWAIT = "datanucleus.forUpdateNowait";
   protected transient RDBMSQueryCompilation datastoreCompilation;
   boolean statementReturnsEmpty;

   public JDOQLQuery(StoreManager storeMgr, ExecutionContext ec) {
      this(storeMgr, ec, (JDOQLQuery)null);
   }

   public JDOQLQuery(StoreManager storeMgr, ExecutionContext ec, JDOQLQuery q) {
      super(storeMgr, ec, q);
      this.datastoreCompilation = null;
      this.statementReturnsEmpty = false;
   }

   public JDOQLQuery(StoreManager storeMgr, ExecutionContext ec, String query) {
      super(storeMgr, ec, query);
      this.datastoreCompilation = null;
      this.statementReturnsEmpty = false;
   }

   protected void discardCompiled() {
      super.discardCompiled();
      this.datastoreCompilation = null;
   }

   protected boolean isCompiled() {
      if (this.evaluateInMemory()) {
         return this.compilation != null;
      } else if (this.compilation != null && this.datastoreCompilation != null) {
         if (!this.datastoreCompilation.isPrecompilable()) {
            NucleusLogger.GENERAL.info("Query compiled but not precompilable so ditching datastore compilation");
            this.datastoreCompilation = null;
            return false;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean evaluateInMemory() {
      if (this.candidateCollection != null) {
         if (this.compilation != null && this.compilation.getSubqueryAliases() != null) {
            NucleusLogger.QUERY.warn("In-memory evaluator doesn't currently handle subqueries completely so evaluating in datastore");
            return false;
         } else {
            Object val = this.getExtension("datanucleus.query.evaluateInMemory");
            if (val == null) {
               return true;
            } else {
               Boolean bool = Boolean.valueOf((String)val);
               return bool == null || bool;
            }
         }
      } else {
         return super.evaluateInMemory();
      }
   }

   protected String getQueryCacheKey() {
      return this.getSerializeRead() != null && this.getSerializeRead() ? super.getQueryCacheKey() + " FOR UPDATE" : super.getQueryCacheKey();
   }

   protected synchronized void compileInternal(Map parameterValues) {
      if (!this.isCompiled()) {
         super.compileInternal(parameterValues);
         boolean inMemory = this.evaluateInMemory();
         if (this.candidateCollection == null || !inMemory) {
            RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
            if (this.candidateClass == null) {
               throw new NucleusUserException(Localiser.msg("021009", new Object[]{this.candidateClassName}));
            } else {
               this.ec.hasPersistenceInformationForClass(this.candidateClass);
               if (parameterValues != null) {
                  for(Map.Entry entry : parameterValues.entrySet()) {
                     Object paramName = entry.getKey();
                     if (paramName instanceof String) {
                        Symbol sym = this.compilation.getSymbolTable().getSymbol((String)paramName);
                        Object value = entry.getValue();
                        if (value == null && sym != null && sym.getValueType() != null && sym.getValueType().isPrimitive()) {
                           throw new NucleusUserException(Localiser.msg("021117", new Object[]{paramName, sym.getValueType().getName()}));
                        }
                     }
                  }
               }

               QueryManager qm = this.getQueryManager();
               String datastoreKey = storeMgr.getQueryCacheKey();
               String queryCacheKey = this.getQueryCacheKey();
               if (this.useCaching() && queryCacheKey != null) {
                  boolean nullParameter = false;
                  if (parameterValues != null) {
                     for(Object val : parameterValues.values()) {
                        if (val == null) {
                           nullParameter = true;
                           break;
                        }
                     }
                  }

                  if (!nullParameter) {
                     this.datastoreCompilation = (RDBMSQueryCompilation)qm.getDatastoreQueryCompilation(datastoreKey, this.getLanguage(), queryCacheKey);
                     if (this.datastoreCompilation != null) {
                        this.setResultDistinct(this.compilation.getResultDistinct());
                        return;
                     }
                  }
               }

               AbstractClassMetaData acmd = this.getCandidateClassMetaData();
               if (this.type == 1) {
                  this.datastoreCompilation = new RDBMSQueryCompilation();
                  this.compileQueryUpdate(parameterValues, acmd);
               } else if (this.type == 2) {
                  this.datastoreCompilation = new RDBMSQueryCompilation();
                  this.compileQueryDelete(parameterValues, acmd);
               } else {
                  this.datastoreCompilation = new RDBMSQueryCompilation();
                  synchronized(this.datastoreCompilation) {
                     if (inMemory) {
                        this.compileQueryToRetrieveCandidates(parameterValues, acmd);
                     } else {
                        this.compileQueryFull(parameterValues, acmd);
                        if (this.result != null) {
                           StatementResultMapping resultMapping = this.datastoreCompilation.getResultDefinition();

                           for(int i = 0; i < resultMapping.getNumberOfResultExpressions(); ++i) {
                              Object stmtMap = resultMapping.getMappingForResultExpression(i);
                              if (stmtMap instanceof StatementMappingIndex) {
                                 StatementMappingIndex idx = (StatementMappingIndex)stmtMap;
                                 AbstractMemberMetaData mmd = idx.getMapping().getMemberMetaData();
                                 if (mmd != null && idx.getMapping() instanceof AbstractContainerMapping && idx.getMapping().getNumberOfDatastoreMappings() != 1) {
                                    throw new NucleusUserException(Localiser.msg("021213"));
                                 }
                              }
                           }

                           if (this.resultClass != null) {
                              AccessController.doPrivileged(new PrivilegedAction() {
                                 public Object run() {
                                    StatementResultMapping resultMapping = JDOQLQuery.this.datastoreCompilation.getResultDefinition();
                                    if (QueryUtils.resultClassIsSimple(JDOQLQuery.this.resultClass.getName())) {
                                       if (resultMapping.getNumberOfResultExpressions() > 1) {
                                          throw new NucleusUserException(Localiser.msg("021201", new Object[]{JDOQLQuery.this.resultClass.getName()}));
                                       }

                                       Object stmtMap = resultMapping.getMappingForResultExpression(0);
                                       if (!(stmtMap instanceof StatementMappingIndex)) {
                                          throw new NucleusUserException("Don't support result clause of " + JDOQLQuery.this.result + " with resultClass of " + JDOQLQuery.this.resultClass.getName());
                                       }

                                       StatementMappingIndex idx = (StatementMappingIndex)stmtMap;
                                       Class exprType = idx.getMapping().getJavaType();
                                       boolean typeConsistent = false;
                                       if (exprType == JDOQLQuery.this.resultClass) {
                                          typeConsistent = true;
                                       } else if (exprType.isPrimitive()) {
                                          Class resultClassPrimitive = ClassUtils.getPrimitiveTypeForType(JDOQLQuery.this.resultClass);
                                          if (resultClassPrimitive == exprType) {
                                             typeConsistent = true;
                                          }
                                       }

                                       if (!typeConsistent) {
                                          throw new NucleusUserException(Localiser.msg("021202", new Object[]{JDOQLQuery.this.resultClass.getName(), exprType}));
                                       }
                                    } else if (QueryUtils.resultClassIsUserType(JDOQLQuery.this.resultClass.getName())) {
                                       Class[] ctrTypes = new Class[resultMapping.getNumberOfResultExpressions()];

                                       for(int i = 0; i < ctrTypes.length; ++i) {
                                          Object stmtMap = resultMapping.getMappingForResultExpression(i);
                                          if (stmtMap instanceof StatementMappingIndex) {
                                             ctrTypes[i] = ((StatementMappingIndex)stmtMap).getMapping().getJavaType();
                                          } else if (stmtMap instanceof StatementNewObjectMapping) {
                                          }
                                       }

                                       Constructor ctr = ClassUtils.getConstructorWithArguments(JDOQLQuery.this.resultClass, ctrTypes);
                                       if (ctr == null && !ClassUtils.hasDefaultConstructor(JDOQLQuery.this.resultClass)) {
                                          throw new NucleusUserException(Localiser.msg("021205", new Object[]{JDOQLQuery.this.resultClass.getName()}));
                                       }

                                       if (ctr == null) {
                                          for(int i = 0; i < resultMapping.getNumberOfResultExpressions(); ++i) {
                                             Object stmtMap = resultMapping.getMappingForResultExpression(i);
                                             if (stmtMap instanceof StatementMappingIndex) {
                                                StatementMappingIndex mapIdx = (StatementMappingIndex)stmtMap;
                                                AbstractMemberMetaData mmd = mapIdx.getMapping().getMemberMetaData();
                                                String fieldName = mapIdx.getColumnAlias();
                                                Class fieldType = mapIdx.getMapping().getJavaType();
                                                if (fieldName == null && mmd != null) {
                                                   fieldName = mmd.getName();
                                                }

                                                if (fieldName != null) {
                                                   Class resultFieldType = null;
                                                   boolean publicField = true;

                                                   try {
                                                      Field fld = JDOQLQuery.this.resultClass.getDeclaredField(fieldName);
                                                      resultFieldType = fld.getType();
                                                      if (!ClassUtils.typesAreCompatible(fieldType, resultFieldType) && !ClassUtils.typesAreCompatible(resultFieldType, fieldType)) {
                                                         throw new NucleusUserException(Localiser.msg("021211", new Object[]{fieldName, fieldType.getName(), resultFieldType.getName()}));
                                                      }

                                                      if (!Modifier.isPublic(fld.getModifiers())) {
                                                         publicField = false;
                                                      }
                                                   } catch (NoSuchFieldException var14) {
                                                      publicField = false;
                                                   }

                                                   if (!publicField) {
                                                      Method setMethod = QueryUtils.getPublicSetMethodForFieldOfResultClass(JDOQLQuery.this.resultClass, fieldName, resultFieldType);
                                                      if (setMethod == null) {
                                                         Method putMethod = QueryUtils.getPublicPutMethodForResultClass(JDOQLQuery.this.resultClass);
                                                         if (putMethod == null) {
                                                            throw new NucleusUserException(Localiser.msg("021212", new Object[]{JDOQLQuery.this.resultClass.getName(), fieldName}));
                                                         }
                                                      }
                                                   }
                                                }
                                             } else if (stmtMap instanceof StatementNewObjectMapping) {
                                             }
                                          }
                                       }
                                    }

                                    return null;
                                 }
                              });
                           }
                        }
                     }

                     if (NucleusLogger.QUERY.isDebugEnabled()) {
                        NucleusLogger.QUERY.debug(Localiser.msg("021085", new Object[]{this, this.datastoreCompilation.getSQL()}));
                     }

                     boolean hasParams = false;
                     if (this.explicitParameters != null) {
                        hasParams = true;
                     } else if (parameterValues != null && parameterValues.size() > 0) {
                        hasParams = true;
                     }

                     if (!this.statementReturnsEmpty && queryCacheKey != null) {
                        if (this.datastoreCompilation.isPrecompilable() && (this.datastoreCompilation.getSQL().indexOf(63) >= 0 || !hasParams)) {
                           qm.addDatastoreQueryCompilation(datastoreKey, this.getLanguage(), queryCacheKey, this.datastoreCompilation);
                        } else {
                           NucleusLogger.QUERY.debug(Localiser.msg("021075"));
                        }
                     }
                  }
               }

            }
         }
      }
   }

   public String getSQL() {
      return this.datastoreCompilation != null ? this.datastoreCompilation.getSQL() : null;
   }

   protected Object performExecute(Map parameters) {
      if (this.statementReturnsEmpty) {
         return Collections.EMPTY_LIST;
      } else {
         boolean inMemory = this.evaluateInMemory();
         if (this.candidateCollection != null) {
            if (this.candidateCollection.isEmpty()) {
               return Collections.EMPTY_LIST;
            }

            if (inMemory) {
               return (new JDOQLEvaluator(this, new ArrayList(this.candidateCollection), this.compilation, parameters, this.clr)).execute(true, true, true, true, true);
            }
         } else if (this.type == 0) {
            List<Object> cachedResults = this.getQueryManager().getQueryResult(this, parameters);
            if (cachedResults != null) {
               return new CandidateIdsQueryResult(this, cachedResults);
            }
         }

         Object results = null;
         RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
         final ManagedConnection mconn = storeMgr.getConnection(this.ec);

         Object var53;
         try {
            long startTime = System.currentTimeMillis();
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021046", new Object[]{this.getLanguage(), this.getSingleStringQuery(), null}));
            }

            AbstractClassMetaData acmd = this.ec.getMetaDataManager().getMetaDataForClass(this.candidateClass, this.clr);
            SQLController sqlControl = storeMgr.getSQLController();
            PreparedStatement ps = null;

            try {
               if (this.type == 0) {
                  ps = RDBMSQueryUtils.getPreparedStatementForQuery(mconn, this.datastoreCompilation.getSQL(), this);
                  SQLStatementHelper.applyParametersToStatement(ps, this.ec, this.datastoreCompilation.getStatementParameters(), this.datastoreCompilation.getParameterNameByPosition(), parameters);
                  RDBMSQueryUtils.prepareStatementForExecution(ps, this, true);
                  this.registerTask(ps);
                  ResultSet rs = null;

                  try {
                     rs = sqlControl.executeStatementQuery(this.ec, mconn, this.toString(), ps);
                  } finally {
                     this.deregisterTask();
                  }

                  final Object qr = null;

                  try {
                     if (inMemory) {
                        ResultObjectFactory rof = new PersistentClassROF(storeMgr, acmd, this.datastoreCompilation.getResultDefinitionForClass(), this.ignoreCache, this.getFetchPlan(), this.candidateClass);
                        List candidates = new ArrayList();

                        while(rs.next()) {
                           candidates.add(rof.getObject(this.ec, rs));
                        }

                        results = (new JDOQLEvaluator(this, candidates, this.compilation, parameters, this.clr)).execute(true, true, true, true, true);
                     } else {
                        ResultObjectFactory rof = null;
                        if (this.result != null) {
                           rof = new ResultClassROF(storeMgr, this.resultClass, this.datastoreCompilation.getResultDefinition());
                        } else if (this.resultClass != null && this.resultClass != this.candidateClass) {
                           rof = new ResultClassROF(storeMgr, this.resultClass, this.datastoreCompilation.getResultDefinitionForClass());
                        } else {
                           rof = new PersistentClassROF(storeMgr, acmd, this.datastoreCompilation.getResultDefinitionForClass(), this.ignoreCache, this.getFetchPlan(), this.candidateClass);
                        }

                        String resultSetType = RDBMSQueryUtils.getResultSetTypeForQuery(this);
                        if (!resultSetType.equals("scroll-insensitive") && !resultSetType.equals("scroll-sensitive")) {
                           qr = new ForwardQueryResult(this, rof, rs, this.getResultDistinct() ? null : this.candidateCollection);
                        } else {
                           qr = new ScrollableQueryResult(this, rof, rs, this.getResultDistinct() ? null : this.candidateCollection);
                        }

                        Map<String, IteratorStatement> scoIterStmts = this.datastoreCompilation.getSCOIteratorStatements();
                        if (scoIterStmts != null) {
                           for(Map.Entry stmtIterEntry : scoIterStmts.entrySet()) {
                              IteratorStatement iterStmt = (IteratorStatement)stmtIterEntry.getValue();
                              String iterStmtSQL = iterStmt.getSQLStatement().getSelectStatement().toSQL();
                              NucleusLogger.DATASTORE_RETRIEVE.debug(">> JDOQL Bulk-Fetch of " + iterStmt.getBackingStore().getOwnerMemberMetaData().getFullFieldName());

                              try {
                                 PreparedStatement psSco = sqlControl.getStatementForQuery(mconn, iterStmtSQL);
                                 if (this.datastoreCompilation.getStatementParameters() != null) {
                                    BulkFetchExistsHelper helper = new BulkFetchExistsHelper(this);
                                    helper.applyParametersToStatement(psSco, this.datastoreCompilation, iterStmt.getSQLStatement(), parameters);
                                 }

                                 ResultSet rsSCO = sqlControl.executeStatementQuery(this.ec, mconn, iterStmtSQL, psSco);
                                 ((AbstractRDBMSQueryResult)qr).registerMemberBulkResultSet(iterStmt, rsSCO);
                              } catch (SQLException e) {
                                 throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{iterStmtSQL}), e);
                              }
                           }
                        }

                        ((AbstractRDBMSQueryResult)qr).initialise();
                        ManagedConnectionResourceListener listener = new ManagedConnectionResourceListener() {
                           public void transactionFlushed() {
                           }

                           public void transactionPreClose() {
                              ((QueryResult)qr).disconnect();
                           }

                           public void managedConnectionPreClose() {
                              if (!JDOQLQuery.this.ec.getTransaction().isActive()) {
                                 ((QueryResult)qr).disconnect();
                              }

                           }

                           public void managedConnectionPostClose() {
                           }

                           public void resourcePostClose() {
                              mconn.removeListener(this);
                           }
                        };
                        mconn.addListener(listener);
                        ((AbstractRDBMSQueryResult)qr).addConnectionListener(listener);
                        results = qr;
                     }
                  } finally {
                     if (qr == null) {
                        rs.close();
                     }

                  }
               } else if (this.type == 1 || this.type == 2) {
                  long bulkResult = 0L;

                  for(RDBMSQueryCompilation.StatementCompilation stmtCompile : this.datastoreCompilation.getStatementCompilations()) {
                     ps = sqlControl.getStatementForUpdate(mconn, stmtCompile.getSQL(), false);
                     SQLStatementHelper.applyParametersToStatement(ps, this.ec, this.datastoreCompilation.getStatementParameters(), this.datastoreCompilation.getParameterNameByPosition(), parameters);
                     RDBMSQueryUtils.prepareStatementForExecution(ps, this, false);
                     int[] execResults = sqlControl.executeStatementUpdate(this.ec, mconn, this.toString(), ps, true);
                     if (stmtCompile.useInCount()) {
                        bulkResult += (long)execResults[0];
                     }
                  }

                  try {
                     this.ec.getNucleusContext().getLevel2Cache().evictAll(this.candidateClass, this.subclasses);
                  } catch (UnsupportedOperationException var42) {
                  }

                  results = bulkResult;
               }
            } catch (SQLException sqle) {
               if (storeMgr.getDatastoreAdapter().isStatementCancel(sqle)) {
                  throw new QueryInterruptedException("Query has been interrupted", sqle);
               }

               if (storeMgr.getDatastoreAdapter().isStatementTimeout(sqle)) {
                  throw new QueryTimeoutException("Query has been timed out", sqle);
               }

               throw new NucleusException(Localiser.msg("021042", new Object[]{this.datastoreCompilation.getSQL()}), sqle);
            }

            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021074", new Object[]{this.getLanguage(), "" + (System.currentTimeMillis() - startTime)}));
            }

            var53 = results;
         } finally {
            mconn.release();
         }

         return var53;
      }
   }

   protected void assertSupportsCancel() {
   }

   protected boolean cancelTaskObject(Object obj) {
      Statement ps = (Statement)obj;

      try {
         ps.cancel();
         return true;
      } catch (SQLException sqle) {
         NucleusLogger.DATASTORE_RETRIEVE.warn("Error cancelling query", sqle);
         return false;
      }
   }

   protected boolean supportsTimeout() {
      return true;
   }

   private void compileQueryFull(Map parameters, AbstractClassMetaData candidateCmd) {
      if (this.type == 0) {
         long startTime = 0L;
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            startTime = System.currentTimeMillis();
            NucleusLogger.QUERY.debug(Localiser.msg("021083", new Object[]{this.getLanguage(), this.toString()}));
         }

         if (this.result != null) {
            this.datastoreCompilation.setResultDefinition(new StatementResultMapping());
         } else {
            this.datastoreCompilation.setResultDefinitionForClass(new StatementClassMapping());
         }

         SQLStatement stmt = null;

         try {
            stmt = RDBMSQueryUtils.getStatementForCandidates((RDBMSStoreManager)this.getStoreManager(), (SQLStatement)null, candidateCmd, this.datastoreCompilation.getResultDefinitionForClass(), this.ec, this.candidateClass, this.subclasses, this.result, (String)null, (String)null);
         } catch (NucleusException ne) {
            NucleusLogger.QUERY.warn("Query for candidates of " + this.candidateClass.getName() + (this.subclasses ? " and subclasses" : "") + " resulted in no possible candidates", ne);
            this.statementReturnsEmpty = true;
            return;
         }

         Set<String> options = new HashSet();
         options.add("BULK_UPDATE_VERSION");
         if (this.getBooleanExtensionProperty("datanucleus.useIsNullWhenEqualsNullParameter", true)) {
            options.add("USE_IS_NULL_FOR_NULL_PARAM");
         }

         QueryToSQLMapper sqlMapper = new QueryToSQLMapper(stmt, this.compilation, parameters, this.datastoreCompilation.getResultDefinitionForClass(), this.datastoreCompilation.getResultDefinition(), candidateCmd, this.subclasses, this.getFetchPlan(), this.ec, this.getParsedImports(), options, this.extensions);
         sqlMapper.compile();
         this.datastoreCompilation.setParameterNameByPosition(sqlMapper.getParameterNameByPosition());
         this.datastoreCompilation.setPrecompilable(sqlMapper.isPrecompilable());
         if (!this.getResultDistinct() && stmt.isDistinct()) {
            this.setResultDistinct(true);
            this.compilation.setResultDistinct();
         }

         if (this.candidateCollection != null) {
            BooleanExpression candidateExpr = null;
            Iterator iter = this.candidateCollection.iterator();
            JavaTypeMapping idMapping = stmt.getPrimaryTable().getTable().getIdMapping();

            while(iter.hasNext()) {
               Object candidate = iter.next();
               SQLExpression idExpr = stmt.getSQLExpressionFactory().newExpression(stmt, stmt.getPrimaryTable(), idMapping);
               SQLExpression idVal = stmt.getSQLExpressionFactory().newLiteral(stmt, idMapping, candidate);
               if (candidateExpr == null) {
                  candidateExpr = idExpr.eq(idVal);
               } else {
                  candidateExpr = candidateExpr.ior(idExpr.eq(idVal));
               }
            }

            stmt.whereAnd(candidateExpr, true);
         }

         if (this.range != null) {
            long lower = this.fromInclNo;
            long upper = this.toExclNo;
            if (this.fromInclParam != null) {
               if (parameters.containsKey(this.fromInclParam)) {
                  lower = ((Number)parameters.get(this.fromInclParam)).longValue();
               } else {
                  lower = ((Number)parameters.get(parameters.size() - 2)).longValue();
               }
            }

            if (this.toExclParam != null) {
               if (parameters.containsKey(this.toExclParam)) {
                  upper = ((Number)parameters.get(this.toExclParam)).longValue();
               } else {
                  upper = ((Number)parameters.get(parameters.size() - 1)).longValue();
               }
            }

            stmt.setRange(lower, upper - lower);
         }

         boolean useUpdateLock = RDBMSQueryUtils.useUpdateLockForQuery(this);
         stmt.addExtension("lock-for-update", useUpdateLock);
         if (this.getBooleanExtensionProperty("datanucleus.forUpdateNowait", false)) {
            stmt.addExtension("for-update-nowait", Boolean.TRUE);
         }

         this.datastoreCompilation.setSQL(stmt.getSelectStatement().toString());
         this.datastoreCompilation.setStatementParameters(stmt.getSelectStatement().getParametersForStatement());
         if (this.result == null && (this.resultClass == null || this.resultClass == this.candidateClass)) {
            FetchPlanForClass fpc = this.getFetchPlan().getFetchPlanForClass(candidateCmd);
            int[] fpMembers = fpc.getMemberNumbers();

            for(int i = 0; i < fpMembers.length; ++i) {
               AbstractMemberMetaData fpMmd = candidateCmd.getMetaDataForManagedMemberAtAbsolutePosition(fpMembers[i]);
               RelationType fpRelType = fpMmd.getRelationType(this.clr);
               if (RelationType.isRelationMultiValued(fpRelType)) {
                  String multifetchType = this.getStringExtensionProperty("datanucleus.rdbms.query.multivaluedFetch", (String)null);
                  if (multifetchType == null) {
                     NucleusLogger.QUERY.debug("You have selected field " + fpMmd.getFullFieldName() + " for fetching by this query. We will fetch it using 'EXISTS'. To disable this set the query extension/hint '" + "datanucleus.rdbms.query.multivaluedFetch" + "' as 'none' or remove the field from the query FetchPlan. If this bulk-fetch generates an invalid or unoptimised query, please report it with a way of reproducing it");
                     multifetchType = "exists";
                  }

                  if (multifetchType.equalsIgnoreCase("exists")) {
                     if ((!fpMmd.hasCollection() || !SCOUtils.collectionHasSerialisedElements(fpMmd)) && (!fpMmd.hasMap() || !SCOUtils.mapHasSerialisedKeysAndValues(fpMmd))) {
                        BulkFetchExistsHelper helper = new BulkFetchExistsHelper(this);
                        IteratorStatement iterStmt = helper.getSQLStatementForContainerField(candidateCmd, parameters, fpMmd, this.datastoreCompilation, options);
                        if (iterStmt != null) {
                           this.datastoreCompilation.setSCOIteratorStatement(fpMmd.getFullFieldName(), iterStmt);
                        } else {
                           NucleusLogger.GENERAL.debug("Note that query has field " + fpMmd.getFullFieldName() + " marked in the FetchPlan, yet this is currently not fetched by this query");
                        }
                     }
                  } else {
                     NucleusLogger.GENERAL.debug("Note that query has field " + fpMmd.getFullFieldName() + " marked in the FetchPlan, yet this is not fetched by this query.");
                  }
               } else if (RelationType.isRelationSingleValued(fpRelType)) {
               }
            }
         }

         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021084", new Object[]{this.getLanguage(), System.currentTimeMillis() - startTime}));
         }

      }
   }

   private void compileQueryToRetrieveCandidates(Map parameters, AbstractClassMetaData candidateCmd) {
      if (this.type == 0) {
         StatementClassMapping resultsDef = new StatementClassMapping();
         this.datastoreCompilation.setResultDefinitionForClass(resultsDef);
         SQLStatement stmt = null;

         try {
            stmt = RDBMSQueryUtils.getStatementForCandidates((RDBMSStoreManager)this.getStoreManager(), (SQLStatement)null, candidateCmd, this.datastoreCompilation.getResultDefinitionForClass(), this.ec, this.candidateClass, this.subclasses, this.result, (String)null, (String)null);
         } catch (NucleusException ne) {
            NucleusLogger.QUERY.warn("Query for candidates of " + this.candidateClass.getName() + (this.subclasses ? " and subclasses" : "") + " resulted in no possible candidates", ne);
            this.statementReturnsEmpty = true;
            return;
         }

         if (stmt.allUnionsForSamePrimaryTable()) {
            SQLStatementHelper.selectFetchPlanOfCandidateInStatement(stmt, this.datastoreCompilation.getResultDefinitionForClass(), candidateCmd, this.getFetchPlan(), 1);
         } else {
            SQLStatementHelper.selectIdentityOfCandidateInStatement(stmt, this.datastoreCompilation.getResultDefinitionForClass(), candidateCmd);
         }

         this.datastoreCompilation.setSQL(stmt.getSelectStatement().toString());
         this.datastoreCompilation.setStatementParameters(stmt.getSelectStatement().getParametersForStatement());
      }
   }

   protected void compileQueryUpdate(Map parameterValues, AbstractClassMetaData candidateCmd) {
      Expression[] updateExprs = this.compilation.getExprUpdate();
      if (updateExprs != null && updateExprs.length != 0) {
         RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
         DatastoreClass candidateTbl = storeMgr.getDatastoreClass(candidateCmd.getFullClassName(), this.clr);
         if (candidateTbl == null) {
            throw new NucleusDataStoreException("Bulk update of " + candidateCmd.getFullClassName() + " not supported since candidate has no table of its own");
         } else {
            InheritanceStrategy inhStr = candidateCmd.getBaseAbstractClassMetaData().getInheritanceMetaData().getStrategy();
            List<BulkTable> tables = new ArrayList();
            tables.add(new BulkTable(candidateTbl, true));
            if (inhStr != InheritanceStrategy.COMPLETE_TABLE) {
               while(candidateTbl.getSuperDatastoreClass() != null) {
                  candidateTbl = candidateTbl.getSuperDatastoreClass();
                  tables.add(new BulkTable(candidateTbl, false));
               }
            }

            Collection<String> subclassNames = storeMgr.getSubClassesForClass(candidateCmd.getFullClassName(), true, this.clr);
            if (subclassNames != null && !subclassNames.isEmpty()) {
               for(String subclassName : subclassNames) {
                  DatastoreClass subclassTbl = storeMgr.getDatastoreClass(subclassName, this.clr);
                  if (candidateTbl != subclassTbl) {
                     tables.add(0, new BulkTable(subclassTbl, inhStr == InheritanceStrategy.COMPLETE_TABLE));
                  }
               }
            }

            List<SQLStatement> stmts = new ArrayList();
            List<Boolean> stmtCountFlags = new ArrayList();

            for(BulkTable bulkTable : tables) {
               DatastoreClass table = bulkTable.table;
               Map<String, Object> extensions = null;
               if (!storeMgr.getDatastoreAdapter().supportsOption("UpdateDeleteStmtAllowTableAliasInWhere")) {
                  extensions = new HashMap();
                  extensions.put("table-naming-strategy", "table-name");
               }

               SQLStatement stmt = new SQLStatement(storeMgr, table, (DatastoreIdentifier)null, (String)null, extensions);
               stmt.setClassLoaderResolver(this.clr);
               stmt.setCandidateClassName(candidateCmd.getFullClassName());
               if (table.getMultitenancyMapping() != null) {
                  JavaTypeMapping tenantMapping = table.getMultitenancyMapping();
                  SQLTable tenantSqlTbl = stmt.getPrimaryTable();
                  SQLExpression tenantExpr = stmt.getSQLExpressionFactory().newExpression(stmt, tenantSqlTbl, tenantMapping);
                  SQLExpression tenantVal = stmt.getSQLExpressionFactory().newLiteral(stmt, tenantMapping, storeMgr.getStringProperty("datanucleus.TenantID"));
                  stmt.whereAnd(tenantExpr.eq(tenantVal), true);
               }

               Set<String> options = new HashSet();
               if (this.getBooleanExtensionProperty("datanucleus.useIsNullWhenEqualsNullParameter", true)) {
                  options.add("USE_IS_NULL_FOR_NULL_PARAM");
               }

               QueryToSQLMapper sqlMapper = new QueryToSQLMapper(stmt, this.compilation, parameterValues, (StatementClassMapping)null, (StatementResultMapping)null, candidateCmd, this.subclasses, this.getFetchPlan(), this.ec, (Imports)null, options, extensions);
               sqlMapper.setDefaultJoinType(SQLJoin.JoinType.INNER_JOIN);
               sqlMapper.compile();
               if (stmt.hasUpdates()) {
                  stmts.add(stmt);
                  stmtCountFlags.add(bulkTable.useInCount);
                  this.datastoreCompilation.setStatementParameters(stmt.getUpdateStatement().getParametersForStatement());
               }
            }

            this.datastoreCompilation.clearStatements();
            Iterator<SQLStatement> stmtIter = stmts.iterator();

            SQLStatement stmt;
            Boolean useInCount;
            for(Iterator<Boolean> stmtCountFlagsIter = stmtCountFlags.iterator(); stmtIter.hasNext(); this.datastoreCompilation.addStatement(stmt, stmt.getUpdateStatement().toSQL(), useInCount)) {
               stmt = (SQLStatement)stmtIter.next();
               useInCount = (Boolean)stmtCountFlagsIter.next();
               if (stmts.size() == 1) {
                  useInCount = true;
               }
            }

         }
      }
   }

   protected void compileQueryDelete(Map parameterValues, AbstractClassMetaData candidateCmd) {
      RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
      DatastoreClass candidateTbl = storeMgr.getDatastoreClass(candidateCmd.getFullClassName(), this.clr);
      if (candidateTbl == null) {
         throw new NucleusDataStoreException("Bulk delete of " + candidateCmd.getFullClassName() + " not supported since candidate has no table of its own");
      } else {
         InheritanceStrategy inhStr = candidateCmd.getBaseAbstractClassMetaData().getInheritanceMetaData().getStrategy();
         List<BulkTable> tables = new ArrayList();
         tables.add(new BulkTable(candidateTbl, true));
         if (inhStr != InheritanceStrategy.COMPLETE_TABLE) {
            while(candidateTbl.getSuperDatastoreClass() != null) {
               candidateTbl = candidateTbl.getSuperDatastoreClass();
               tables.add(new BulkTable(candidateTbl, false));
            }
         }

         Collection<String> subclassNames = storeMgr.getSubClassesForClass(candidateCmd.getFullClassName(), true, this.clr);
         if (subclassNames != null && !subclassNames.isEmpty()) {
            for(String subclassName : subclassNames) {
               DatastoreClass subclassTbl = storeMgr.getDatastoreClass(subclassName, this.clr);
               if (candidateTbl != subclassTbl) {
                  tables.add(0, new BulkTable(subclassTbl, inhStr == InheritanceStrategy.COMPLETE_TABLE));
               }
            }
         }

         List<SQLStatement> stmts = new ArrayList();
         List<Boolean> stmtCountFlags = new ArrayList();

         for(BulkTable bulkTable : tables) {
            DatastoreClass table = bulkTable.table;
            Map<String, Object> extensions = null;
            if (!storeMgr.getDatastoreAdapter().supportsOption("UpdateDeleteStmtAllowTableAliasInWhere")) {
               extensions = new HashMap();
               extensions.put("table-naming-strategy", "table-name");
            }

            SQLStatement stmt = new SQLStatement(storeMgr, table, (DatastoreIdentifier)null, (String)null, extensions);
            stmt.setClassLoaderResolver(this.clr);
            stmt.setCandidateClassName(candidateCmd.getFullClassName());
            if (table.getMultitenancyMapping() != null) {
               JavaTypeMapping tenantMapping = table.getMultitenancyMapping();
               SQLTable tenantSqlTbl = stmt.getPrimaryTable();
               SQLExpression tenantExpr = stmt.getSQLExpressionFactory().newExpression(stmt, tenantSqlTbl, tenantMapping);
               SQLExpression tenantVal = stmt.getSQLExpressionFactory().newLiteral(stmt, tenantMapping, storeMgr.getStringProperty("datanucleus.TenantID"));
               stmt.whereAnd(tenantExpr.eq(tenantVal), true);
            }

            Set<String> options = new HashSet();
            if (this.getBooleanExtensionProperty("datanucleus.useIsNullWhenEqualsNullParameter", true)) {
               options.add("USE_IS_NULL_FOR_NULL_PARAM");
            }

            options.add("BULK_DELETE_NO_RESULT");
            QueryToSQLMapper sqlMapper = new QueryToSQLMapper(stmt, this.compilation, parameterValues, (StatementClassMapping)null, (StatementResultMapping)null, candidateCmd, this.subclasses, this.getFetchPlan(), this.ec, (Imports)null, options, extensions);
            sqlMapper.setDefaultJoinType(SQLJoin.JoinType.INNER_JOIN);
            sqlMapper.compile();
            stmts.add(stmt);
            stmtCountFlags.add(bulkTable.useInCount);
            this.datastoreCompilation.setStatementParameters(stmt.getDeleteStatement().getParametersForStatement());
         }

         this.datastoreCompilation.clearStatements();
         Iterator<SQLStatement> stmtIter = stmts.iterator();

         SQLStatement stmt;
         Boolean useInCount;
         for(Iterator<Boolean> stmtCountFlagsIter = stmtCountFlags.iterator(); stmtIter.hasNext(); this.datastoreCompilation.addStatement(stmt, stmt.getDeleteStatement().toSQL(), useInCount)) {
            stmt = (SQLStatement)stmtIter.next();
            useInCount = (Boolean)stmtCountFlagsIter.next();
            if (stmts.size() == 1) {
               useInCount = true;
            }
         }

      }
   }

   public Set getSupportedExtensions() {
      Set<String> supported = super.getSupportedExtensions();
      supported.add("datanucleus.rdbms.query.resultSetType");
      supported.add("datanucleus.rdbms.query.resultSetConcurrency");
      supported.add("datanucleus.rdbms.query.fetchDirection");
      return supported;
   }

   public boolean processesRangeInDatastoreQuery() {
      if (this.range == null) {
         return true;
      } else {
         RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
         DatastoreAdapter dba = storeMgr.getDatastoreAdapter();
         boolean using_limit_where_clause = dba.getRangeByLimitEndOfStatementClause(this.fromInclNo, this.toExclNo, !StringUtils.isWhitespace(this.ordering)).length() > 0;
         boolean using_rownum = dba.getRangeByRowNumberColumn().length() > 0 || dba.getRangeByRowNumberColumn2().length() > 0;
         return using_limit_where_clause || using_rownum;
      }
   }

   public void addExtension(String key, Object value) {
      if (key != null && key.equals("datanucleus.query.evaluateInMemory")) {
         this.datastoreCompilation = null;
         this.getQueryManager().deleteDatastoreQueryCompilation(this.getStoreManager().getQueryCacheKey(), this.getLanguage(), this.toString());
      }

      super.addExtension(key, value);
   }

   public void setExtensions(Map extensions) {
      if (extensions != null && extensions.containsKey("datanucleus.query.evaluateInMemory")) {
         this.datastoreCompilation = null;
         this.getQueryManager().deleteDatastoreQueryCompilation(this.getStoreManager().getQueryCacheKey(), this.getLanguage(), this.toString());
      }

      super.setExtensions(extensions);
   }

   public RDBMSQueryCompilation getDatastoreCompilation() {
      return this.datastoreCompilation;
   }

   public Object getNativeQuery() {
      return this.datastoreCompilation != null ? this.datastoreCompilation.getSQL() : super.getNativeQuery();
   }

   private class BulkTable {
      DatastoreClass table;
      boolean useInCount;

      public BulkTable(DatastoreClass tbl, boolean useInCount) {
         this.table = tbl;
         this.useInCount = useInCount;
      }

      public String toString() {
         return this.table.toString();
      }
   }
}
