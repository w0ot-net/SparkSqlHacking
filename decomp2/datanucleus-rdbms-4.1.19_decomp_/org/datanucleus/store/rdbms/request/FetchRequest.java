package org.datanucleus.store.rdbms.request;

import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.sql.SQLJoin;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.AbstractClassTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class FetchRequest extends Request {
   private String statementUnlocked;
   private String statementLocked;
   private int[] memberNumbersToFetch = null;
   private StatementClassMapping mappingDefinition;
   private final MappingCallbacks[] callbacks;
   private int numberOfFieldsToFetch = 0;
   private final String fieldsToFetch;
   private boolean fetchingSurrogateVersion = false;
   private String versionFieldName = null;

   public FetchRequest(DatastoreClass classTable, AbstractMemberMetaData[] mmds, AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      super(classTable);
      RDBMSStoreManager storeMgr = classTable.getStoreManager();
      boolean found = false;
      DatastoreClass candidateTable = classTable;
      if (mmds != null) {
         label119:
         while(candidateTable != null) {
            int i = 0;

            while(true) {
               if (i < mmds.length) {
                  JavaTypeMapping m = candidateTable.getMemberMappingInDatastoreClass(mmds[i]);
                  if (m == null) {
                     ++i;
                     continue;
                  }

                  found = true;
               }

               if (found) {
                  break label119;
               }

               candidateTable = candidateTable.getSuperDatastoreClass();
               break;
            }
         }
      }

      if (candidateTable == null) {
         candidateTable = classTable;
      }

      this.table = candidateTable;
      this.key = ((AbstractClassTable)this.table).getPrimaryKey();

      for(DatastoreClass currentTable = this.table; currentTable != null; currentTable = currentTable.getSuperDatastoreClass()) {
         VersionMetaData currentVermd = currentTable.getVersionMetaData();
         if (currentVermd != null) {
            if (currentVermd.getFieldName() == null) {
               this.fetchingSurrogateVersion = true;
            } else {
               this.versionFieldName = currentVermd.getFieldName();
            }
         }
      }

      SQLStatement sqlStatement = new SQLStatement(storeMgr, this.table, (DatastoreIdentifier)null, (String)null);
      this.mappingDefinition = new StatementClassMapping();
      Collection<MappingCallbacks> fetchCallbacks = new HashSet();
      this.numberOfFieldsToFetch = this.processMembersOfClass(sqlStatement, mmds, this.table, sqlStatement.getPrimaryTable(), this.mappingDefinition, fetchCallbacks, clr);
      this.callbacks = (MappingCallbacks[])fetchCallbacks.toArray(new MappingCallbacks[fetchCallbacks.size()]);
      this.memberNumbersToFetch = this.mappingDefinition.getMemberNumbers();
      int inputParamNum = 1;
      SQLExpressionFactory exprFactory = storeMgr.getSQLExpressionFactory();
      if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         JavaTypeMapping datastoreIdMapping = this.table.getDatastoreIdMapping();
         SQLExpression expr = exprFactory.newExpression(sqlStatement, sqlStatement.getPrimaryTable(), datastoreIdMapping);
         SQLExpression val = exprFactory.newLiteralParameter(sqlStatement, datastoreIdMapping, (Object)null, "ID");
         sqlStatement.whereAnd(expr.eq(val), true);
         StatementMappingIndex datastoreIdx = this.mappingDefinition.getMappingForMemberPosition(-1);
         if (datastoreIdx == null) {
            datastoreIdx = new StatementMappingIndex(datastoreIdMapping);
            this.mappingDefinition.addMappingForMember(-1, datastoreIdx);
         }

         datastoreIdx.addParameterOccurrence(new int[]{inputParamNum});
      } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
         int[] pkNums = cmd.getPKMemberPositions();

         for(int i = 0; i < pkNums.length; ++i) {
            AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkNums[i]);
            JavaTypeMapping pkMapping = this.table.getMemberMapping(mmd);
            SQLExpression expr = exprFactory.newExpression(sqlStatement, sqlStatement.getPrimaryTable(), pkMapping);
            SQLExpression val = exprFactory.newLiteralParameter(sqlStatement, pkMapping, (Object)null, "PK" + i);
            sqlStatement.whereAnd(expr.eq(val), true);
            StatementMappingIndex pkIdx = this.mappingDefinition.getMappingForMemberPosition(pkNums[i]);
            if (pkIdx == null) {
               pkIdx = new StatementMappingIndex(pkMapping);
               this.mappingDefinition.addMappingForMember(pkNums[i], pkIdx);
            }

            int[] inputParams = new int[pkMapping.getNumberOfDatastoreMappings()];

            for(int j = 0; j < pkMapping.getNumberOfDatastoreMappings(); ++j) {
               inputParams[j] = inputParamNum++;
            }

            pkIdx.addParameterOccurrence(inputParams);
         }
      }

      if (this.table.getMultitenancyMapping() != null) {
         JavaTypeMapping tenantMapping = this.table.getMultitenancyMapping();
         SQLExpression tenantExpr = exprFactory.newExpression(sqlStatement, sqlStatement.getPrimaryTable(), tenantMapping);
         SQLExpression tenantVal = exprFactory.newLiteral(sqlStatement, tenantMapping, storeMgr.getStringProperty("datanucleus.TenantID"));
         sqlStatement.whereAnd(tenantExpr.eq(tenantVal), true);
      }

      StringBuilder str = new StringBuilder();
      if (mmds != null) {
         for(int i = 0; i < mmds.length; ++i) {
            if (!mmds[i].isPrimaryKey()) {
               if (str.length() > 0) {
                  str.append(',');
               }

               str.append(mmds[i].getName());
            }
         }
      }

      if (this.fetchingSurrogateVersion) {
         if (str.length() > 0) {
            str.append(",");
         }

         str.append("[VERSION]");
      }

      if (!this.fetchingSurrogateVersion && this.numberOfFieldsToFetch == 0) {
         this.fieldsToFetch = null;
         SQLStatement var25 = null;
         this.mappingDefinition = null;
      } else {
         this.fieldsToFetch = str.toString();
         this.statementUnlocked = sqlStatement.getSelectStatement().toSQL();
         sqlStatement.addExtension("lock-for-update", Boolean.TRUE);
         this.statementLocked = sqlStatement.getSelectStatement().toSQL();
      }

   }

   public void execute(ObjectProvider op) {
      if (this.fieldsToFetch != null && NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("052218", new Object[]{op.getObjectAsPrintable(), this.fieldsToFetch, this.table}));
      }

      if ((!this.isFetchingVersionOnly() || !this.isVersionLoaded(op)) && this.statementLocked != null) {
         ExecutionContext ec = op.getExecutionContext();
         RDBMSStoreManager storeMgr = this.table.getStoreManager();
         boolean locked = ec.getSerializeReadForClass(op.getClassMetaData().getFullClassName());
         short lockType = ec.getLockManager().getLockMode(op.getInternalObjectId());
         if (lockType != 0 && (lockType == 3 || lockType == 4)) {
            locked = true;
         }

         String statement = locked ? this.statementLocked : this.statementUnlocked;
         StatementClassMapping mappingDef = this.mappingDefinition;

         try {
            ManagedConnection mconn = storeMgr.getConnection(ec);
            SQLController sqlControl = storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, statement);
               AbstractClassMetaData cmd = op.getClassMetaData();

               try {
                  if (cmd.getIdentityType() == IdentityType.DATASTORE) {
                     StatementMappingIndex datastoreIdx = mappingDef.getMappingForMemberPosition(-1);

                     for(int i = 0; i < datastoreIdx.getNumberOfParameterOccurrences(); ++i) {
                        this.table.getDatastoreIdMapping().setObject(ec, ps, datastoreIdx.getParameterPositionsForOccurrence(i), op.getInternalObjectId());
                     }
                  } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                     op.provideFields(cmd.getPKMemberPositions(), storeMgr.getFieldManagerForStatementGeneration(op, ps, mappingDef));
                  }

                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, statement, ps);

                  try {
                     if (!rs.next()) {
                        if (NucleusLogger.DATASTORE_RETRIEVE.isInfoEnabled()) {
                           NucleusLogger.DATASTORE_RETRIEVE.info(Localiser.msg("050018", new Object[]{op.getInternalObjectId()}));
                        }

                        throw new NucleusObjectNotFoundException("No such database row", op.getInternalObjectId());
                     }

                     op.replaceFields(this.memberNumbersToFetch, storeMgr.getFieldManagerForResultProcessing(op, rs, mappingDef));
                     if (op.getTransactionalVersion() == null) {
                        Object datastoreVersion = null;
                        if (this.fetchingSurrogateVersion) {
                           StatementMappingIndex verIdx = mappingDef.getMappingForMemberPosition(-2);
                           datastoreVersion = this.table.getVersionMapping(true).getObject(ec, rs, verIdx.getColumnPositions());
                        } else if (this.versionFieldName != null) {
                           datastoreVersion = op.provideField(cmd.getAbsolutePositionOfMember(this.versionFieldName));
                        }

                        op.setVersion(datastoreVersion);
                     }
                  } finally {
                     rs.close();
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }
         } catch (SQLException var33) {
            SQLException sqle = var33;
            String msg = Localiser.msg("052219", new Object[]{op.getObjectAsPrintable(), statement, var33.getMessage()});
            NucleusLogger.DATASTORE_RETRIEVE.warn(msg);
            List exceptions = new ArrayList();
            exceptions.add(var33);

            while((sqle = sqle.getNextException()) != null) {
               exceptions.add(sqle);
            }

            throw new NucleusDataStoreException(msg, (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
         }
      }

      for(int i = 0; i < this.callbacks.length; ++i) {
         this.callbacks[i].postFetch(op);
      }

   }

   private boolean isVersionLoaded(ObjectProvider op) {
      return op.getObject() != null && op.getVersion() != null;
   }

   private boolean isFetchingVersionOnly() {
      return (this.fetchingSurrogateVersion || this.versionFieldName != null) && this.numberOfFieldsToFetch == 0;
   }

   protected int processMembersOfClass(SQLStatement sqlStatement, AbstractMemberMetaData[] mmds, DatastoreClass table, SQLTable sqlTbl, StatementClassMapping mappingDef, Collection fetchCallbacks, ClassLoaderResolver clr) {
      int number = 0;
      if (mmds != null) {
         for(int i = 0; i < mmds.length; ++i) {
            AbstractMemberMetaData mmd = mmds[i];
            JavaTypeMapping mapping = table.getMemberMapping(mmd);
            if (mapping != null) {
               if (!mmd.isPrimaryKey() && mapping.includeInFetchStatement()) {
                  int depth = 0;
                  if (mapping instanceof PersistableMapping) {
                     depth = 1;
                     if (Modifier.isAbstract(mmd.getType().getModifiers())) {
                        DatastoreClass relTable = table.getStoreManager().getDatastoreClass(mmd.getTypeName(), clr);
                        if (relTable != null && relTable.getDiscriminatorMapping(false) == null) {
                           String[] subclasses = table.getStoreManager().getMetaDataManager().getSubclassesForClass(mmd.getTypeName(), false);
                           if (subclasses != null && subclasses.length > 0) {
                              depth = 0;
                           }
                        }
                     }
                  } else if (mapping instanceof ReferenceMapping) {
                     ReferenceMapping refMapping = (ReferenceMapping)mapping;
                     if (refMapping.getMappingStrategy() == 0) {
                        JavaTypeMapping[] subMappings = refMapping.getJavaTypeMapping();
                        if (subMappings != null && subMappings.length == 1) {
                           depth = 1;
                        }
                     }
                  }

                  SQLStatementHelper.selectMemberOfSourceInStatement(sqlStatement, mappingDef, (FetchPlan)null, sqlTbl, mmd, clr, depth, (SQLJoin.JoinType)null);
                  ++number;
               }

               if (mapping instanceof MappingCallbacks) {
                  fetchCallbacks.add(mapping);
               }
            }
         }
      }

      JavaTypeMapping verMapping = table.getVersionMapping(true);
      if (verMapping != null) {
         StatementMappingIndex verMapIdx = new StatementMappingIndex(verMapping);
         SQLTable verSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStatement, sqlTbl, verMapping);
         int[] cols = sqlStatement.select(verSqlTbl, (JavaTypeMapping)verMapping, (String)null);
         verMapIdx.setColumnPositions(cols);
         mappingDef.addMappingForMember(-2, verMapIdx);
      }

      return number;
   }
}
