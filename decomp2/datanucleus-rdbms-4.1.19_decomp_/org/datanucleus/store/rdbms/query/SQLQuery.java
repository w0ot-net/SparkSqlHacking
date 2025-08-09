package org.datanucleus.store.rdbms.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.ClassNotPersistableException;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.QueryResultMetaData;
import org.datanucleus.store.Extent;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.connection.ManagedConnectionResourceListener;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.query.QueryInterruptedException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public final class SQLQuery extends Query {
   private static final long serialVersionUID = -6820729188666657398L;
   protected final transient String inputSQL;
   protected transient String compiledSQL;
   protected QueryResultMetaData resultMetaData;
   protected transient boolean isCompiled;
   protected transient StatementMappingIndex[] stmtMappings;

   public SQLQuery(StoreManager storeMgr, ExecutionContext ec, SQLQuery query) {
      this(storeMgr, ec, query.inputSQL);
   }

   public SQLQuery(StoreManager storeMgr, ExecutionContext ec) {
      this(storeMgr, ec, (String)null);
   }

   public SQLQuery(StoreManager storeMgr, ExecutionContext ec, String queryString) {
      super(storeMgr, ec);
      this.compiledSQL = null;
      this.resultMetaData = null;
      this.isCompiled = false;
      this.candidateClass = null;
      this.filter = null;
      this.imports = null;
      this.explicitVariables = null;
      this.explicitParameters = null;
      this.ordering = null;
      if (queryString == null) {
         throw new NucleusUserException(Localiser.msg("059001"));
      } else {
         this.inputSQL = queryString.replace('\r', ' ').replace('\n', ' ').replace('\t', ' ').trim();
         String firstToken = this.inputSQL.trim().substring(0, 6).toUpperCase();
         if (firstToken.equals("SELECT")) {
            this.type = 0;
         } else if (firstToken.equals("DELETE")) {
            this.type = 2;
            this.unique = true;
         } else if (!firstToken.equals("UPDATE") && !firstToken.equals("INSERT") && !firstToken.startsWith("MERGE")) {
            this.type = 3;
            this.unique = true;
         } else {
            this.type = 1;
            this.unique = true;
         }

         if (ec.getApiAdapter().getName().equalsIgnoreCase("JDO")) {
            boolean allowAllSyntax = ec.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.query.sql.allowAll");
            if (ec.getProperty("datanucleus.query.sql.allowAll") != null) {
               allowAllSyntax = ec.getBooleanProperty("datanucleus.query.sql.allowAll");
            }

            if (!allowAllSyntax && !firstToken.equals("SELECT")) {
               throw new NucleusUserException(Localiser.msg("059002", new Object[]{this.inputSQL}));
            }
         }

      }
   }

   public String getLanguage() {
      return "SQL";
   }

   public void setCandidates(Extent pcs) {
      throw new NucleusUserException(Localiser.msg("059004"));
   }

   public void setCandidates(Collection pcs) {
      throw new NucleusUserException(Localiser.msg("059005"));
   }

   public void setResult(String result) {
      throw new NucleusUserException(Localiser.msg("059006"));
   }

   public void setResultMetaData(QueryResultMetaData qrmd) {
      this.resultMetaData = qrmd;
      super.setResultClass((Class)null);
   }

   public void setResultClass(Class result_cls) {
      super.setResultClass(result_cls);
      this.resultMetaData = null;
   }

   public void setRange(int fromIncl, int toExcl) {
      throw new NucleusUserException(Localiser.msg("059007"));
   }

   public void setSubclasses(boolean subclasses) {
      throw new NucleusUserException(Localiser.msg("059004"));
   }

   public void setFilter(String filter) {
      throw new NucleusUserException(Localiser.msg("059008"));
   }

   public void declareExplicitVariables(String variables) {
      throw new NucleusUserException(Localiser.msg("059009"));
   }

   public void declareExplicitParameters(String parameters) {
      throw new NucleusUserException(Localiser.msg("059016"));
   }

   public void declareImports(String imports) {
      throw new NucleusUserException(Localiser.msg("059026"));
   }

   public void setGrouping(String grouping) {
      throw new NucleusUserException(Localiser.msg("059010"));
   }

   public void setOrdering(String ordering) {
      throw new NucleusUserException(Localiser.msg("059011"));
   }

   protected long performDeletePersistentAll(Map parameters) {
      throw new NucleusUserException(Localiser.msg("059000"));
   }

   protected boolean shouldReturnSingleRow() {
      return this.unique;
   }

   public boolean processesRangeInDatastoreQuery() {
      return true;
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return obj instanceof SQLQuery && super.equals(obj) ? this.inputSQL.equals(((SQLQuery)obj).inputSQL) : false;
      }
   }

   public int hashCode() {
      return super.hashCode() ^ this.inputSQL.hashCode();
   }

   protected void discardCompiled() {
      super.discardCompiled();
      this.compiledSQL = null;
      this.isCompiled = false;
      this.stmtMappings = null;
   }

   protected boolean isCompiled() {
      return this.isCompiled;
   }

   public void compileInternal(Map parameterValues) {
      if (!this.isCompiled) {
         this.compiledSQL = this.inputSQL;
         if (this.candidateClass != null && this.getType() == 0) {
            RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
            ClassLoaderResolver clr = this.ec.getClassLoaderResolver();
            AbstractClassMetaData cmd = this.ec.getMetaDataManager().getMetaDataForClass(this.candidateClass, clr);
            if (cmd == null) {
               throw new ClassNotPersistableException(this.candidateClass.getName());
            }

            if (cmd.getPersistableSuperclass() != null) {
            }

            if (this.getResultClass() == null) {
               String selections = stripComments(this.compiledSQL.trim()).substring(7);
               int fromStart = selections.indexOf("FROM");
               if (fromStart == -1) {
                  fromStart = selections.indexOf("from");
               }

               selections = selections.substring(0, fromStart).trim();
               String[] selectedColumns = StringUtils.split(selections, ",");
               if (selectedColumns == null || selectedColumns.length == 0) {
                  throw new NucleusUserException(Localiser.msg("059003", new Object[]{this.compiledSQL}));
               }

               if (selectedColumns.length != 1 || !selectedColumns[0].trim().equals("*")) {
                  DatastoreClass table = storeMgr.getDatastoreClass(this.candidateClass.getName(), clr);
                  PersistableMapping idMapping = (PersistableMapping)table.getIdMapping();
                  String[] idColNames = new String[idMapping.getNumberOfDatastoreMappings()];
                  boolean[] idColMissing = new boolean[idMapping.getNumberOfDatastoreMappings()];

                  for(int i = 0; i < idMapping.getNumberOfDatastoreMappings(); ++i) {
                     idColNames[i] = idMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString();
                     idColMissing[i] = true;
                  }

                  String discriminatorColName = table.getDiscriminatorMapping(false) != null ? table.getDiscriminatorMapping(false).getDatastoreMapping(0).getColumn().getIdentifier().toString() : null;
                  String versionColName = table.getVersionMapping(false) != null ? table.getVersionMapping(false).getDatastoreMapping(0).getColumn().getIdentifier().toString() : null;
                  boolean discrimMissing = discriminatorColName != null;
                  boolean versionMissing = versionColName != null;
                  DatastoreAdapter dba = storeMgr.getDatastoreAdapter();
                  AbstractClassMetaData candidateCmd = this.ec.getMetaDataManager().getMetaDataForClass(this.candidateClass, clr);

                  for(int i = 0; i < selectedColumns.length; ++i) {
                     String colName = selectedColumns[i].trim();
                     if (colName.indexOf(" AS ") > 0) {
                        colName = colName.substring(colName.indexOf(" AS ") + 4).trim();
                     } else if (colName.indexOf(" as ") > 0) {
                        colName = colName.substring(colName.indexOf(" as ") + 4).trim();
                     }

                     if (candidateCmd.getIdentityType() == IdentityType.DATASTORE) {
                        if (columnNamesAreTheSame(dba, idColNames[0], colName)) {
                           idColMissing[0] = false;
                        }
                     } else if (candidateCmd.getIdentityType() == IdentityType.APPLICATION) {
                        for(int j = 0; j < idColNames.length; ++j) {
                           if (columnNamesAreTheSame(dba, idColNames[j], colName)) {
                              idColMissing[j] = false;
                           }
                        }
                     }

                     if (discrimMissing && columnNamesAreTheSame(dba, discriminatorColName, colName)) {
                        discrimMissing = false;
                     } else if (versionMissing && columnNamesAreTheSame(dba, versionColName, colName)) {
                        versionMissing = false;
                     }
                  }

                  if (discrimMissing) {
                     throw new NucleusUserException(Localiser.msg("059014", new Object[]{this.compiledSQL, this.candidateClass.getName(), discriminatorColName}));
                  }

                  if (versionMissing) {
                     throw new NucleusUserException(Localiser.msg("059015", new Object[]{this.compiledSQL, this.candidateClass.getName(), versionColName}));
                  }

                  for(int i = 0; i < idColMissing.length; ++i) {
                     if (idColMissing[i]) {
                        throw new NucleusUserException(Localiser.msg("059013", new Object[]{this.compiledSQL, this.candidateClass.getName(), idColNames[i]}));
                     }
                  }
               }
            }
         }

         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("059012", new Object[]{this.compiledSQL}));
         }

         this.isCompiled = true;
      }
   }

   protected Object performExecute(Map parameters) {
      if (parameters.size() != (this.parameterNames != null ? this.parameterNames.length : 0)) {
         throw new NucleusUserException(Localiser.msg("059019", new Object[]{"" + this.parameterNames.length, "" + parameters.size()}));
      } else if (this.type != 2 && this.type != 1) {
         if (this.type == 0) {
            final AbstractRDBMSQueryResult qr = null;

            try {
               RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
               final ManagedConnection mconn = storeMgr.getConnection(this.ec);
               SQLController sqlControl = storeMgr.getSQLController();

               try {
                  PreparedStatement ps = RDBMSQueryUtils.getPreparedStatementForQuery(mconn, this.compiledSQL, this);

                  try {
                     for(int i = 0; i < parameters.size(); ++i) {
                        ps.setObject(i + 1, parameters.get(i + 1));
                     }

                     RDBMSQueryUtils.prepareStatementForExecution(ps, this, true);
                     ResultSet rs = sqlControl.executeStatementQuery(this.ec, mconn, this.compiledSQL, ps);

                     try {
                        ResultObjectFactory rof = null;
                        if (this.resultMetaData != null) {
                           rof = new ResultMetaDataROF(storeMgr, this.resultMetaData);
                        } else if (this.resultClass == null && this.candidateClass != null) {
                           rof = this.getResultObjectFactoryForCandidateClass(rs);
                        } else {
                           rof = RDBMSQueryUtils.getResultObjectFactoryForNoCandidateClass(storeMgr, rs, this.resultClass);
                        }

                        String resultSetType = RDBMSQueryUtils.getResultSetTypeForQuery(this);
                        if (!resultSetType.equals("scroll-insensitive") && !resultSetType.equals("scroll-sensitive")) {
                           qr = new ForwardQueryResult(this, rof, rs, (Collection)null);
                        } else {
                           qr = new ScrollableQueryResult(this, rof, rs, (Collection)null);
                        }

                        qr.initialise();
                        mconn.addListener(new ManagedConnectionResourceListener() {
                           public void transactionFlushed() {
                           }

                           public void transactionPreClose() {
                              qr.disconnect();
                           }

                           public void managedConnectionPreClose() {
                              if (!SQLQuery.this.ec.getTransaction().isActive()) {
                                 qr.disconnect();
                              }

                           }

                           public void managedConnectionPostClose() {
                           }

                           public void resourcePostClose() {
                              mconn.removeListener(this);
                           }
                        });
                     } finally {
                        if (qr == null) {
                           rs.close();
                        }

                     }
                  } catch (QueryInterruptedException qie) {
                     ps.cancel();
                     throw qie;
                  } finally {
                     if (qr == null) {
                        sqlControl.closeStatement(mconn, ps);
                     }

                  }
               } finally {
                  mconn.release();
               }

               return qr;
            } catch (SQLException e) {
               throw new NucleusDataStoreException(Localiser.msg("059025", new Object[]{this.compiledSQL}), e);
            }
         } else {
            try {
               RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
               ManagedConnection mconn = storeMgr.getConnection(this.ec);
               SQLController sqlControl = storeMgr.getSQLController();

               try {
                  PreparedStatement ps = RDBMSQueryUtils.getPreparedStatementForQuery(mconn, this.compiledSQL, this);

                  try {
                     for(int i = 0; i < parameters.size(); ++i) {
                        ps.setObject(i + 1, parameters.get(i + 1));
                     }

                     RDBMSQueryUtils.prepareStatementForExecution(ps, this, true);
                     sqlControl.executeStatement(this.ec, mconn, this.compiledSQL, ps);
                  } catch (QueryInterruptedException qie) {
                     ps.cancel();
                     throw qie;
                  } finally {
                     sqlControl.closeStatement(mconn, ps);
                  }
               } finally {
                  mconn.release();
               }
            } catch (SQLException e) {
               throw new NucleusDataStoreException(Localiser.msg("059025", new Object[]{this.compiledSQL}), e);
            }

            return true;
         }
      } else {
         try {
            RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
            ManagedConnection mconn = storeMgr.getConnection(this.ec);
            SQLController sqlControl = storeMgr.getSQLController();

            Long var7;
            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, this.compiledSQL, false);

               try {
                  for(int i = 0; i < parameters.size(); ++i) {
                     ps.setObject(i + 1, parameters.get(i + 1));
                  }

                  int[] rcs = sqlControl.executeStatementUpdate(this.ec, mconn, this.compiledSQL, ps, true);
                  var7 = (long)rcs[0];
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return var7;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("059025", new Object[]{this.compiledSQL}), e);
         }
      }
   }

   public Object executeWithArray(Object[] parameters) {
      Map parameterMap = new HashMap();
      if (parameters != null) {
         for(int i = 0; i < parameters.length; ++i) {
            parameterMap.put(i + 1, parameters[i]);
         }
      }

      Map executionMap = this.prepareForExecution(parameterMap);
      return super.executeQuery(executionMap);
   }

   public Object executeWithMap(Map executeParameters) {
      Map executionMap = this.prepareForExecution(executeParameters);
      return super.executeQuery(executionMap);
   }

   protected Map prepareForExecution(Map executeParameters) {
      Map params = new HashMap();
      if (this.implicitParameters != null) {
         params.putAll(this.implicitParameters);
      }

      if (executeParameters != null) {
         params.putAll(executeParameters);
      }

      this.compileInternal(executeParameters);
      List paramNames = new ArrayList();
      Collection expectedParams = new ArrayList();
      boolean complete = false;
      int charPos = 0;
      char[] statement = this.compiledSQL.toCharArray();
      StringBuilder paramName = null;
      int paramPos = 0;
      boolean colonParam = true;
      StringBuilder runtimeJdbcText = new StringBuilder();

      while(!complete) {
         char c = statement[charPos];
         boolean endOfParam = false;
         if (c == '?') {
            colonParam = false;
            ++paramPos;
            paramName = new StringBuilder();
         } else if (c == ':') {
            if (charPos > 0) {
               char prev = statement[charPos - 1];
               if (!Character.isLetterOrDigit(prev)) {
                  colonParam = true;
                  ++paramPos;
                  paramName = new StringBuilder();
               }
            } else {
               colonParam = true;
               ++paramPos;
               paramName = new StringBuilder();
            }
         } else if (paramName != null) {
            if (Character.isLetterOrDigit(c)) {
               paramName.append(c);
            } else {
               endOfParam = true;
            }
         }

         if (paramName != null) {
            if (endOfParam) {
               runtimeJdbcText.append('?');
               runtimeJdbcText.append(c);
            }
         } else {
            runtimeJdbcText.append(c);
         }

         ++charPos;
         complete = charPos == this.compiledSQL.length();
         if (complete && paramName != null && !endOfParam) {
            runtimeJdbcText.append('?');
         }

         if (paramName != null && (complete || endOfParam)) {
            if (paramName.length() > 0) {
               if (colonParam) {
                  expectedParams.add(paramName.toString());
               } else {
                  try {
                     Integer num = Integer.valueOf(paramName.toString());
                     expectedParams.add(num);
                  } catch (NumberFormatException var15) {
                     throw new NucleusUserException("SQL query " + this.inputSQL + " contains an invalid parameter specification " + paramName.toString());
                  }
               }
            } else if (!colonParam) {
               expectedParams.add(paramPos);
            }

            paramName = null;
         }
      }

      this.compiledSQL = runtimeJdbcText.toString();
      if (expectedParams.size() > 0 && params.isEmpty()) {
         throw new NucleusUserException(Localiser.msg("059028", new Object[]{this.inputSQL, "" + expectedParams.size()}));
      } else {
         Map executeMap = new HashMap();
         paramPos = 1;

         for(Object key : expectedParams) {
            if (!params.containsKey(key)) {
               throw new NucleusUserException(Localiser.msg("059031", new Object[]{"" + key, this.inputSQL}));
            }

            executeMap.put(paramPos, params.get(key));
            paramNames.add("" + paramPos);
            ++paramPos;
         }

         this.parameterNames = (String[])paramNames.toArray(new String[paramNames.size()]);
         return executeMap;
      }
   }

   protected void assertSupportsCancel() {
   }

   protected boolean supportsTimeout() {
      return true;
   }

   protected ResultObjectFactory getResultObjectFactoryForCandidateClass(ResultSet rs) throws SQLException {
      ClassLoaderResolver clr = this.ec.getClassLoaderResolver();
      RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.getStoreManager();
      DatastoreAdapter dba = storeMgr.getDatastoreAdapter();
      AbstractClassMetaData candidateCmd = this.ec.getMetaDataManager().getMetaDataForClass(this.candidateClass, clr);
      int fieldCount = candidateCmd.getNoOfManagedMembers() + candidateCmd.getNoOfInheritedManagedMembers();
      Map columnFieldNumberMap = new HashMap();
      this.stmtMappings = new StatementMappingIndex[fieldCount];
      DatastoreClass tbl = storeMgr.getDatastoreClass(this.candidateClass.getName(), clr);

      for(int fieldNumber = 0; fieldNumber < fieldCount; ++fieldNumber) {
         AbstractMemberMetaData mmd = candidateCmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         String fieldName = mmd.getName();
         Class fieldType = mmd.getType();
         JavaTypeMapping m = null;
         if (mmd.getPersistenceModifier() != FieldPersistenceModifier.NONE) {
            if (tbl != null) {
               m = tbl.getMemberMapping(mmd);
            } else {
               m = storeMgr.getMappingManager().getMappingWithDatastoreMapping(fieldType, false, false, clr);
            }

            if (m.includeInFetchStatement()) {
               String columnName = null;
               if (mmd.getColumnMetaData() != null && mmd.getColumnMetaData().length > 0) {
                  for(int colNum = 0; colNum < mmd.getColumnMetaData().length; ++colNum) {
                     columnName = mmd.getColumnMetaData()[colNum].getName();
                     columnFieldNumberMap.put(columnName, fieldNumber);
                  }
               } else {
                  columnName = storeMgr.getIdentifierFactory().newColumnIdentifier(fieldName, this.ec.getNucleusContext().getTypeManager().isDefaultEmbeddedType(fieldType), FieldRole.ROLE_NONE, false).getName();
                  columnFieldNumberMap.put(columnName, fieldNumber);
               }
            }
         }

         this.stmtMappings[fieldNumber] = new StatementMappingIndex(m);
      }

      if (columnFieldNumberMap.size() == 0) {
         throw (new NucleusUserException(Localiser.msg("059030", new Object[]{this.candidateClass.getName()}))).setFatal();
      } else {
         DatastoreClass table = storeMgr.getDatastoreClass(this.candidateClass.getName(), clr);
         if (table == null) {
            AbstractClassMetaData[] cmds = storeMgr.getClassesManagingTableForClass(candidateCmd, clr);
            if (cmds == null || cmds.length != 1) {
               throw new NucleusUserException("SQL query specified with class " + this.candidateClass.getName() + " but this doesn't have its own table, or is mapped to multiple tables. Unsupported");
            }

            table = storeMgr.getDatastoreClass(cmds[0].getFullClassName(), clr);
         }

         PersistableMapping idMapping = (PersistableMapping)table.getIdMapping();
         String[] idColNames = new String[idMapping.getNumberOfDatastoreMappings()];

         for(int i = 0; i < idMapping.getNumberOfDatastoreMappings(); ++i) {
            idColNames[i] = idMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString();
         }

         String discrimColName = table.getDiscriminatorMapping(false) != null ? table.getDiscriminatorMapping(false).getDatastoreMapping(0).getColumn().getIdentifier().toString() : null;
         String versionColName = table.getVersionMapping(false) != null ? table.getVersionMapping(false).getDatastoreMapping(0).getColumn().getIdentifier().toString() : null;
         ResultSetMetaData rsmd = rs.getMetaData();
         HashSet remainingColumnNames = new HashSet(columnFieldNumberMap.size());
         int colCount = rsmd.getColumnCount();
         int[] datastoreIndex = null;
         int[] versionIndex = null;
         int[] discrimIndex = null;
         int[] matchedFieldNumbers = new int[colCount];
         int fieldNumberPosition = 0;

         for(int colNum = 1; colNum <= colCount; ++colNum) {
            String colName = rsmd.getColumnName(colNum);
            int fieldNumber = -1;
            Integer fieldNum = (Integer)columnFieldNumberMap.get(colName);
            if (fieldNum == null) {
               fieldNum = (Integer)columnFieldNumberMap.get(colName.toLowerCase());
               if (fieldNum == null) {
                  fieldNum = (Integer)columnFieldNumberMap.get(colName.toUpperCase());
               }
            }

            if (fieldNum != null) {
               fieldNumber = fieldNum;
            }

            if (fieldNumber >= 0) {
               int[] exprIndices = null;
               if (this.stmtMappings[fieldNumber].getColumnPositions() != null) {
                  exprIndices = new int[this.stmtMappings[fieldNumber].getColumnPositions().length + 1];

                  for(int i = 0; i < this.stmtMappings[fieldNumber].getColumnPositions().length; ++i) {
                     exprIndices[i] = this.stmtMappings[fieldNumber].getColumnPositions()[i];
                  }

                  exprIndices[exprIndices.length - 1] = colNum;
               } else {
                  exprIndices = new int[]{colNum};
               }

               this.stmtMappings[fieldNumber].setColumnPositions(exprIndices);
               remainingColumnNames.remove(colName);
               matchedFieldNumbers[fieldNumberPosition++] = fieldNumber;
            }

            if (discrimColName != null && colName.equals(discrimColName)) {
               discrimIndex = new int[]{colNum};
            }

            if (versionColName != null && colName.equals(versionColName)) {
               versionIndex = new int[]{colNum};
            }

            if (candidateCmd.getIdentityType() == IdentityType.DATASTORE && columnNamesAreTheSame(dba, idColNames[0], colName)) {
               datastoreIndex = new int[]{colNum};
            }
         }

         int[] fieldNumbers = new int[fieldNumberPosition];

         for(int i = 0; i < fieldNumberPosition; ++i) {
            fieldNumbers[i] = matchedFieldNumbers[i];
         }

         StatementClassMapping mappingDefinition = new StatementClassMapping();

         for(int i = 0; i < fieldNumbers.length; ++i) {
            mappingDefinition.addMappingForMember(fieldNumbers[i], this.stmtMappings[fieldNumbers[i]]);
         }

         if (datastoreIndex != null) {
            StatementMappingIndex datastoreMappingIdx = new StatementMappingIndex(table.getDatastoreIdMapping());
            datastoreMappingIdx.setColumnPositions(datastoreIndex);
            mappingDefinition.addMappingForMember(-1, datastoreMappingIdx);
         }

         if (discrimIndex != null) {
            StatementMappingIndex discrimMappingIdx = new StatementMappingIndex(table.getDiscriminatorMapping(true));
            discrimMappingIdx.setColumnPositions(discrimIndex);
            mappingDefinition.addMappingForMember(-3, discrimMappingIdx);
         }

         if (versionIndex != null) {
            StatementMappingIndex versionMappingIdx = new StatementMappingIndex(table.getVersionMapping(true));
            versionMappingIdx.setColumnPositions(versionIndex);
            mappingDefinition.addMappingForMember(-2, versionMappingIdx);
         }

         return new PersistentClassROF(storeMgr, candidateCmd, mappingDefinition, this.ignoreCache, this.getFetchPlan(), this.getCandidateClass());
      }
   }

   public static boolean columnNamesAreTheSame(DatastoreAdapter dba, String name1, String name2) {
      return name1.equalsIgnoreCase(name2) || name1.equalsIgnoreCase(dba.getIdentifierQuoteString() + name2 + dba.getIdentifierQuoteString());
   }

   private static String stripComments(String sql) {
      return sql.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "");
   }
}
