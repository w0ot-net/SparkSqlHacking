package org.datanucleus.store.rdbms.sql;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.NullLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.Table;

public class DiscriminatorStatementGenerator extends AbstractStatementGenerator {
   Class[] candidates;

   public DiscriminatorStatementGenerator(RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Class candidateType, boolean includeSubclasses, DatastoreIdentifier candidateTableAlias, String candidateTableGroupName) {
      super(storeMgr, clr, candidateType, includeSubclasses, candidateTableAlias, candidateTableGroupName);
      this.candidates = null;
      this.setOption("restrictDiscriminator");
   }

   public DiscriminatorStatementGenerator(RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Class[] candidateTypes, boolean includeSubclasses, DatastoreIdentifier candidateTableAlias, String candidateTableGroupName) {
      this(storeMgr, clr, candidateTypes[0], includeSubclasses, candidateTableAlias, candidateTableGroupName);
      this.candidates = candidateTypes;
   }

   public DiscriminatorStatementGenerator(RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Class candidateType, boolean includeSubclasses, DatastoreIdentifier candidateTableAlias, String candidateTableGroupName, Table joinTable, DatastoreIdentifier joinTableAlias, JavaTypeMapping joinElementMapping) {
      super(storeMgr, clr, candidateType, includeSubclasses, candidateTableAlias, candidateTableGroupName, joinTable, joinTableAlias, joinElementMapping);
      this.candidates = null;
      this.setOption("restrictDiscriminator");
   }

   public DiscriminatorStatementGenerator(RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Class[] candidateTypes, boolean includeSubclasses, DatastoreIdentifier candidateTableAlias, String candidateTableGroupName, Table joinTable, DatastoreIdentifier joinTableAlias, JavaTypeMapping joinElementMapping) {
      this(storeMgr, clr, candidateTypes[0], includeSubclasses, candidateTableAlias, candidateTableGroupName, joinTable, joinTableAlias, joinElementMapping);
      this.candidates = candidateTypes;
   }

   public void setParentStatement(SQLStatement stmt) {
      this.parentStmt = stmt;
   }

   public SQLStatement getStatement() {
      SQLStatement stmt = null;
      SQLTable discrimSqlTbl = null;
      if (this.joinTable == null) {
         stmt = new SQLStatement(this.parentStmt, this.storeMgr, this.candidateTable, this.candidateTableAlias, this.candidateTableGroupName);
         stmt.setClassLoaderResolver(this.clr);
         discrimSqlTbl = stmt.getPrimaryTable();
      } else {
         stmt = new SQLStatement(this.parentStmt, this.storeMgr, this.joinTable, this.joinTableAlias, this.candidateTableGroupName);
         stmt.setClassLoaderResolver(this.clr);
         JavaTypeMapping candidateIdMapping = this.candidateTable.getIdMapping();
         if (this.hasOption("allowNulls")) {
            discrimSqlTbl = stmt.leftOuterJoin((SQLTable)null, this.joinElementMapping, this.candidateTable, (String)null, candidateIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
         } else {
            discrimSqlTbl = stmt.innerJoin((SQLTable)null, this.joinElementMapping, this.candidateTable, (String)null, candidateIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
         }
      }

      JavaTypeMapping discMapping = this.candidateTable.getDiscriminatorMapping(true);
      if (discMapping != null) {
         discrimSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(stmt, discrimSqlTbl, discMapping);
      }

      DiscriminatorMetaData dismd = discrimSqlTbl.getTable().getDiscriminatorMetaData();
      boolean hasDiscriminator = discMapping != null && dismd != null && dismd.getStrategy() != DiscriminatorStrategy.NONE;
      boolean restrictDiscriminator = this.hasOption("restrictDiscriminator");
      if (hasDiscriminator && restrictDiscriminator) {
         boolean multipleCandidates = false;
         BooleanExpression discExpr = null;
         if (this.candidates != null) {
            if (this.candidates.length > 1) {
               multipleCandidates = true;
            }

            for(int i = 0; i < this.candidates.length; ++i) {
               if (!Modifier.isAbstract(this.candidates[i].getModifiers())) {
                  BooleanExpression discExprCandidate = SQLStatementHelper.getExpressionForDiscriminatorForClass(stmt, this.candidates[i].getName(), dismd, discMapping, discrimSqlTbl, this.clr);
                  if (discExpr != null) {
                     discExpr = discExpr.ior(discExprCandidate);
                  } else {
                     discExpr = discExprCandidate;
                  }

                  if (this.includeSubclasses) {
                     Collection<String> subclassNames = this.storeMgr.getSubClassesForClass(this.candidateType.getName(), true, this.clr);
                     Iterator<String> subclassIter = subclassNames.iterator();
                     if (!multipleCandidates) {
                        multipleCandidates = subclassNames.size() > 0;
                     }

                     while(subclassIter.hasNext()) {
                        String subclassName = (String)subclassIter.next();
                        BooleanExpression discExprSub = SQLStatementHelper.getExpressionForDiscriminatorForClass(stmt, subclassName, dismd, discMapping, discrimSqlTbl, this.clr);
                        discExpr = discExpr.ior(discExprSub);
                     }
                  }
               }
            }
         } else {
            if (!Modifier.isAbstract(this.candidateType.getModifiers())) {
               discExpr = SQLStatementHelper.getExpressionForDiscriminatorForClass(stmt, this.candidateType.getName(), dismd, discMapping, discrimSqlTbl, this.clr);
            }

            if (this.includeSubclasses) {
               Collection<String> subclassNames = this.storeMgr.getSubClassesForClass(this.candidateType.getName(), true, this.clr);
               Iterator<String> subclassIter = subclassNames.iterator();
               multipleCandidates = subclassNames.size() > 0;

               while(subclassIter.hasNext()) {
                  String subclassName = (String)subclassIter.next();
                  Class subclass = this.clr.classForName(subclassName);
                  if (!Modifier.isAbstract(subclass.getModifiers())) {
                     BooleanExpression discExprCandidate = SQLStatementHelper.getExpressionForDiscriminatorForClass(stmt, subclassName, dismd, discMapping, discrimSqlTbl, this.clr);
                     if (discExpr == null) {
                        discExpr = discExprCandidate;
                     } else {
                        discExpr = discExpr.ior(discExprCandidate);
                     }
                  }
               }
            }

            if (discExpr == null) {
               SQLExpressionFactory exprFactory = stmt.getSQLExpressionFactory();
               JavaTypeMapping m = exprFactory.getMappingForType(Boolean.TYPE, true);
               discExpr = exprFactory.newLiteral(stmt, m, true).eq(exprFactory.newLiteral(stmt, m, false));
            }
         }

         if (this.hasOption("allowNulls")) {
            SQLExpression expr = stmt.getSQLExpressionFactory().newExpression(stmt, discrimSqlTbl, discMapping);
            SQLExpression val = new NullLiteral(stmt, (JavaTypeMapping)null, (Object)null, (String)null);
            BooleanExpression nullDiscExpr = expr.eq(val);
            discExpr = discExpr.ior(nullDiscExpr);
            if (!multipleCandidates) {
               multipleCandidates = true;
            }
         }

         if (multipleCandidates) {
            discExpr.encloseInParentheses();
         }

         stmt.whereAnd(discExpr, true);
      }

      if (this.candidateTable.getMultitenancyMapping() != null) {
         JavaTypeMapping tenantMapping = this.candidateTable.getMultitenancyMapping();
         SQLTable tenantSqlTbl = stmt.getTable(tenantMapping.getTable(), discrimSqlTbl.getGroupName());
         SQLExpression tenantExpr = stmt.getSQLExpressionFactory().newExpression(stmt, tenantSqlTbl, tenantMapping);
         SQLExpression tenantVal = stmt.getSQLExpressionFactory().newLiteral(stmt, tenantMapping, this.storeMgr.getStringProperty("datanucleus.TenantID"));
         stmt.whereAnd(tenantExpr.eq(tenantVal), true);
      }

      return stmt;
   }
}
