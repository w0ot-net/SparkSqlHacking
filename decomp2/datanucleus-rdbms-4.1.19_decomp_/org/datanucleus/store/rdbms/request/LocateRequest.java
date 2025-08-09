package org.datanucleus.store.rdbms.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class LocateRequest extends Request {
   private String statementUnlocked;
   private String statementLocked;
   private StatementClassMapping mappingDefinition;

   public LocateRequest(DatastoreClass table) {
      super(table);
      RDBMSStoreManager storeMgr = table.getStoreManager();
      ClassLoaderResolver clr = storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      SQLStatement sqlStatement = new SQLStatement(storeMgr, table, (DatastoreIdentifier)null, (String)null);
      this.mappingDefinition = new StatementClassMapping();
      SQLExpressionFactory exprFactory = storeMgr.getSQLExpressionFactory();
      JavaTypeMapping m = storeMgr.getMappingManager().getMapping(Integer.class);
      sqlStatement.select(exprFactory.newLiteral(sqlStatement, m, 1), (String)null);
      AbstractClassMetaData cmd = storeMgr.getMetaDataManager().getMetaDataForClass(table.getType(), clr);
      int inputParamNum = 1;
      if (table.getIdentityType() == IdentityType.DATASTORE) {
         JavaTypeMapping datastoreIdMapping = table.getDatastoreIdMapping();
         SQLExpression expr = exprFactory.newExpression(sqlStatement, sqlStatement.getPrimaryTable(), datastoreIdMapping);
         SQLExpression val = exprFactory.newLiteralParameter(sqlStatement, datastoreIdMapping, (Object)null, "ID");
         sqlStatement.whereAnd(expr.eq(val), true);
         StatementMappingIndex datastoreIdx = this.mappingDefinition.getMappingForMemberPosition(-1);
         if (datastoreIdx == null) {
            datastoreIdx = new StatementMappingIndex(datastoreIdMapping);
            this.mappingDefinition.addMappingForMember(-1, datastoreIdx);
         }

         datastoreIdx.addParameterOccurrence(new int[]{inputParamNum});
      } else if (table.getIdentityType() == IdentityType.APPLICATION) {
         int[] pkNums = cmd.getPKMemberPositions();

         for(int i = 0; i < pkNums.length; ++i) {
            AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkNums[i]);
            JavaTypeMapping pkMapping = table.getMemberMappingInDatastoreClass(mmd);
            if (pkMapping == null) {
               pkMapping = table.getMemberMapping(mmd);
            }

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

      if (table.getMultitenancyMapping() != null) {
         JavaTypeMapping tenantMapping = table.getMultitenancyMapping();
         SQLExpression tenantExpr = exprFactory.newExpression(sqlStatement, sqlStatement.getPrimaryTable(), tenantMapping);
         SQLExpression tenantVal = exprFactory.newLiteral(sqlStatement, tenantMapping, storeMgr.getStringProperty("datanucleus.TenantID"));
         sqlStatement.whereAnd(tenantExpr.eq(tenantVal), true);
      }

      this.statementUnlocked = sqlStatement.getSelectStatement().toSQL();
      sqlStatement.addExtension("lock-for-update", Boolean.TRUE);
      this.statementLocked = sqlStatement.getSelectStatement().toSQL();
   }

   public void execute(ObjectProvider op) {
      if (this.statementLocked != null) {
         ExecutionContext ec = op.getExecutionContext();
         RDBMSStoreManager storeMgr = this.table.getStoreManager();
         boolean locked = ec.getSerializeReadForClass(op.getClassMetaData().getFullClassName());
         short lockType = ec.getLockManager().getLockMode(op.getInternalObjectId());
         if (lockType != 0 && (lockType == 3 || lockType == 4)) {
            locked = true;
         }

         String statement = locked ? this.statementLocked : this.statementUnlocked;

         try {
            ManagedConnection mconn = storeMgr.getConnection(ec);
            SQLController sqlControl = storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, statement);
               AbstractClassMetaData cmd = op.getClassMetaData();

               try {
                  if (cmd.getIdentityType() == IdentityType.DATASTORE) {
                     StatementMappingIndex datastoreIdx = this.mappingDefinition.getMappingForMemberPosition(-1);

                     for(int i = 0; i < datastoreIdx.getNumberOfParameterOccurrences(); ++i) {
                        this.table.getDatastoreIdMapping().setObject(ec, ps, datastoreIdx.getParameterPositionsForOccurrence(i), op.getInternalObjectId());
                     }
                  } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                     op.provideFields(cmd.getPKMemberPositions(), storeMgr.getFieldManagerForStatementGeneration(op, ps, this.mappingDefinition));
                  }

                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, statement, ps);

                  try {
                     if (!rs.next()) {
                        NucleusLogger.DATASTORE_RETRIEVE.info(Localiser.msg("050018", new Object[]{op.getInternalObjectId()}));
                        throw new NucleusObjectNotFoundException("No such database row", op.getInternalObjectId());
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
         } catch (SQLException var31) {
            SQLException sqle = var31;
            String msg = Localiser.msg("052220", new Object[]{op.getObjectAsPrintable(), statement, var31.getMessage()});
            NucleusLogger.DATASTORE_RETRIEVE.warn(msg);
            List exceptions = new ArrayList();
            exceptions.add(var31);

            while((sqle = sqle.getNextException()) != null) {
               exceptions.add(sqle);
            }

            throw new NucleusDataStoreException(msg, (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
         }
      }

   }
}
