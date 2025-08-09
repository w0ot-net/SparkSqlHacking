package org.datanucleus.store.rdbms.query;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.scostore.BaseContainerStore;
import org.datanucleus.store.rdbms.scostore.FKArrayStore;
import org.datanucleus.store.rdbms.scostore.FKListStore;
import org.datanucleus.store.rdbms.scostore.FKMapStore;
import org.datanucleus.store.rdbms.scostore.FKSetStore;
import org.datanucleus.store.rdbms.scostore.IteratorStatement;
import org.datanucleus.store.rdbms.scostore.JoinArrayStore;
import org.datanucleus.store.rdbms.scostore.JoinListStore;
import org.datanucleus.store.rdbms.scostore.JoinMapStore;
import org.datanucleus.store.rdbms.scostore.JoinSetStore;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLStatementParameter;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.BooleanSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.scostore.Store;

public class BulkFetchExistsHelper {
   Query query;

   public BulkFetchExistsHelper(Query q) {
      this.query = q;
   }

   public IteratorStatement getSQLStatementForContainerField(AbstractClassMetaData candidateCmd, Map parameters, AbstractMemberMetaData mmd, RDBMSQueryCompilation datastoreCompilation, Set mapperOptions) {
      IteratorStatement iterStmt = null;
      ExecutionContext ec = this.query.getExecutionContext();
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      RDBMSStoreManager storeMgr = (RDBMSStoreManager)this.query.getStoreManager();
      Store backingStore = storeMgr.getBackingStoreForField(clr, mmd, (Class)null);
      if (backingStore instanceof JoinSetStore) {
         iterStmt = ((JoinSetStore)backingStore).getIteratorStatement(clr, ec.getFetchPlan(), false);
      } else if (backingStore instanceof FKSetStore) {
         iterStmt = ((FKSetStore)backingStore).getIteratorStatement(clr, ec.getFetchPlan(), false);
      } else if (backingStore instanceof JoinListStore) {
         iterStmt = ((JoinListStore)backingStore).getIteratorStatement(clr, ec.getFetchPlan(), false, -1, -1);
      } else if (backingStore instanceof FKListStore) {
         iterStmt = ((FKListStore)backingStore).getIteratorStatement(clr, ec.getFetchPlan(), false, -1, -1);
      } else if (backingStore instanceof JoinArrayStore) {
         iterStmt = ((JoinArrayStore)backingStore).getIteratorStatement(clr, ec.getFetchPlan(), false);
      } else if (backingStore instanceof FKArrayStore) {
         iterStmt = ((FKArrayStore)backingStore).getIteratorStatement(clr, ec.getFetchPlan(), false);
      } else {
         if (backingStore instanceof JoinMapStore) {
            return null;
         }

         if (backingStore instanceof FKMapStore) {
            return null;
         }
      }

      if (!(backingStore instanceof JoinSetStore) && !(backingStore instanceof JoinListStore) && !(backingStore instanceof JoinArrayStore)) {
         if (!(backingStore instanceof FKSetStore) && !(backingStore instanceof FKListStore) && !(backingStore instanceof FKArrayStore)) {
            if (!(backingStore instanceof JoinMapStore) && backingStore instanceof FKMapStore) {
            }
         } else {
            SQLStatement sqlStmt = iterStmt.getSQLStatement();
            SQLStatement existsStmt = RDBMSQueryUtils.getStatementForCandidates(storeMgr, sqlStmt, candidateCmd, datastoreCompilation.getResultDefinitionForClass(), ec, this.query.getCandidateClass(), this.query.isSubclasses(), this.query.getResult(), (String)null, (String)null);
            Set<String> options = new HashSet();
            if (mapperOptions != null) {
               options.addAll(mapperOptions);
            }

            options.add("RESULT_CANDIDATE_ID");
            QueryToSQLMapper sqlMapper = new QueryToSQLMapper(existsStmt, this.query.getCompilation(), parameters, (StatementClassMapping)null, (StatementResultMapping)null, candidateCmd, this.query.isSubclasses(), this.query.getFetchPlan(), ec, this.query.getParsedImports(), options, this.query.getExtensions());
            sqlMapper.compile();
            existsStmt.setOrdering((SQLExpression[])null, (boolean[])null);
            BooleanExpression existsExpr = new BooleanSubqueryExpression(sqlStmt, "EXISTS", existsStmt);
            sqlStmt.whereAnd(existsExpr, true);
            SQLExpression elemTblOwnerExpr = sqlStmt.getRDBMSManager().getSQLExpressionFactory().newExpression(sqlStmt, sqlStmt.getPrimaryTable(), ((BaseContainerStore)backingStore).getOwnerMapping());
            SQLExpression existsOwnerExpr = sqlStmt.getRDBMSManager().getSQLExpressionFactory().newExpression(existsStmt, existsStmt.getPrimaryTable(), existsStmt.getPrimaryTable().getTable().getIdMapping());
            existsStmt.whereAnd(elemTblOwnerExpr.eq(existsOwnerExpr), true);
            int[] ownerColIndexes = sqlStmt.select(elemTblOwnerExpr, (String)null);
            StatementMappingIndex ownerMapIdx = new StatementMappingIndex(existsStmt.getPrimaryTable().getTable().getIdMapping());
            ownerMapIdx.setColumnPositions(ownerColIndexes);
            iterStmt.setOwnerMapIndex(ownerMapIdx);
         }
      } else {
         SQLStatement sqlStmt = iterStmt.getSQLStatement();
         JoinTable joinTbl = (JoinTable)sqlStmt.getPrimaryTable().getTable();
         JavaTypeMapping joinOwnerMapping = joinTbl.getOwnerMapping();
         SQLStatement existsStmt = RDBMSQueryUtils.getStatementForCandidates(storeMgr, sqlStmt, candidateCmd, datastoreCompilation.getResultDefinitionForClass(), ec, this.query.getCandidateClass(), this.query.isSubclasses(), this.query.getResult(), (String)null, (String)null);
         Set<String> options = new HashSet();
         if (mapperOptions != null) {
            options.addAll(mapperOptions);
         }

         options.add("RESULT_CANDIDATE_ID");
         QueryToSQLMapper sqlMapper = new QueryToSQLMapper(existsStmt, this.query.getCompilation(), parameters, (StatementClassMapping)null, (StatementResultMapping)null, candidateCmd, this.query.isSubclasses(), this.query.getFetchPlan(), ec, this.query.getParsedImports(), options, this.query.getExtensions());
         sqlMapper.compile();
         existsStmt.setOrdering((SQLExpression[])null, (boolean[])null);
         BooleanExpression existsExpr = new BooleanSubqueryExpression(sqlStmt, "EXISTS", existsStmt);
         sqlStmt.whereAnd(existsExpr, true);
         SQLExpression joinTblOwnerExpr = sqlStmt.getRDBMSManager().getSQLExpressionFactory().newExpression(sqlStmt, sqlStmt.getPrimaryTable(), joinOwnerMapping);
         SQLExpression existsOwnerExpr = sqlStmt.getRDBMSManager().getSQLExpressionFactory().newExpression(existsStmt, existsStmt.getPrimaryTable(), existsStmt.getPrimaryTable().getTable().getIdMapping());
         existsStmt.whereAnd(joinTblOwnerExpr.eq(existsOwnerExpr), true);
         int[] ownerColIndexes = sqlStmt.select(joinTblOwnerExpr, (String)null);
         StatementMappingIndex ownerMapIdx = new StatementMappingIndex(existsStmt.getPrimaryTable().getTable().getIdMapping());
         ownerMapIdx.setColumnPositions(ownerColIndexes);
         iterStmt.setOwnerMapIndex(ownerMapIdx);
      }

      return iterStmt;
   }

   public void applyParametersToStatement(PreparedStatement ps, RDBMSQueryCompilation datastoreCompilation, SQLStatement sqlStmt, Map parameters) {
      Map<Integer, String> stmtParamNameByPosition = null;
      List<SQLStatementParameter> stmtParams = null;
      if (datastoreCompilation.getStatementParameters() != null) {
         int numUnions = sqlStmt.getNumberOfUnions();
         stmtParams = new ArrayList();
         stmtParams.addAll(datastoreCompilation.getStatementParameters());

         for(int i = 0; i < numUnions; ++i) {
            stmtParams.addAll(datastoreCompilation.getStatementParameters());
         }

         if (datastoreCompilation.getParameterNameByPosition() != null && datastoreCompilation.getParameterNameByPosition().size() > 0) {
            stmtParamNameByPosition = new HashMap();
            stmtParamNameByPosition.putAll(datastoreCompilation.getParameterNameByPosition());
            int numParams = stmtParamNameByPosition.size();

            for(int i = 0; i < numUnions; ++i) {
               for(Map.Entry paramEntry : datastoreCompilation.getParameterNameByPosition().entrySet()) {
                  stmtParamNameByPosition.put(numParams * (i + 1) + (Integer)paramEntry.getKey(), paramEntry.getValue());
               }
            }
         }

         SQLStatementHelper.applyParametersToStatement(ps, this.query.getExecutionContext(), stmtParams, stmtParamNameByPosition, parameters);
      }

   }
}
