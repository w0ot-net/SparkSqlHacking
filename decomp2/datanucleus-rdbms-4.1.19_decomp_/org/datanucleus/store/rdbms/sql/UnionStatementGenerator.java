package org.datanucleus.store.rdbms.sql;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.NullLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class UnionStatementGenerator extends AbstractStatementGenerator {
   public static final String NUC_TYPE_COLUMN = "NUCLEUS_TYPE";
   private int maxClassNameLength = -1;

   public UnionStatementGenerator(RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Class candidateType, boolean includeSubclasses, DatastoreIdentifier candidateTableAlias, String candidateTableGroupName) {
      super(storeMgr, clr, candidateType, includeSubclasses, candidateTableAlias, candidateTableGroupName);
   }

   public UnionStatementGenerator(RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Class candidateType, boolean includeSubclasses, DatastoreIdentifier candidateTableAlias, String candidateTableGroupName, Table joinTable, DatastoreIdentifier joinTableAlias, JavaTypeMapping joinElementMapping) {
      super(storeMgr, clr, candidateType, includeSubclasses, candidateTableAlias, candidateTableGroupName, joinTable, joinTableAlias, joinElementMapping);
   }

   public void setParentStatement(SQLStatement stmt) {
      this.parentStmt = stmt;
   }

   public SQLStatement getStatement() {
      Collection<String> candidateClassNames = new ArrayList();
      AbstractClassMetaData acmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(this.candidateType, this.clr);
      candidateClassNames.add(acmd.getFullClassName());
      if (this.includeSubclasses) {
         Collection<String> subclasses = this.storeMgr.getSubClassesForClass(this.candidateType.getName(), true, this.clr);
         candidateClassNames.addAll(subclasses);
      }

      Iterator<String> iter = candidateClassNames.iterator();

      while(iter.hasNext()) {
         String className = (String)iter.next();

         try {
            Class cls = this.clr.classForName(className);
            if (Modifier.isAbstract(cls.getModifiers())) {
               iter.remove();
            }
         } catch (Exception var7) {
            iter.remove();
         }
      }

      if (this.hasOption("selectNucleusType")) {
         for(String className : candidateClassNames) {
            if (className.length() > this.maxClassNameLength) {
               this.maxClassNameLength = className.length();
            }
         }
      }

      if (candidateClassNames.isEmpty()) {
         throw new NucleusException("Attempt to generate SQL statement using UNIONs for " + this.candidateType.getName() + " yet there are no concrete classes with their own table available");
      } else {
         SQLStatement stmt = null;

         for(String candidateClassName : candidateClassNames) {
            SQLStatement candidateStmt = null;
            if (this.joinTable == null) {
               candidateStmt = this.getSQLStatementForCandidate(candidateClassName);
            } else {
               candidateStmt = this.getSQLStatementForCandidateViaJoin(candidateClassName);
            }

            if (candidateStmt != null) {
               if (stmt == null) {
                  stmt = candidateStmt;
               } else {
                  stmt.union(candidateStmt);
               }
            }
         }

         return stmt;
      }
   }

   protected SQLStatement getSQLStatementForCandidate(String className) {
      DatastoreClass table = this.storeMgr.getDatastoreClass(className, this.clr);
      if (table == null) {
         NucleusLogger.GENERAL.info("Generation of statement to retrieve objects of type " + this.candidateType.getName() + (this.includeSubclasses ? " including subclasses " : "") + " attempted to include " + className + " but this has no table of its own; ignored");
         return null;
      } else {
         SQLStatement stmt = new SQLStatement(this.parentStmt, this.storeMgr, this.candidateTable, this.candidateTableAlias, this.candidateTableGroupName);
         stmt.setClassLoaderResolver(this.clr);
         stmt.setCandidateClassName(className);
         String tblGroupName = stmt.getPrimaryTable().getGroupName();
         if (table != this.candidateTable) {
            JavaTypeMapping candidateIdMapping = this.candidateTable.getIdMapping();
            JavaTypeMapping tableIdMapping = table.getIdMapping();
            SQLTable tableSqlTbl = stmt.innerJoin((SQLTable)null, candidateIdMapping, table, (String)null, tableIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
            tblGroupName = tableSqlTbl.getGroupName();
         }

         SQLExpressionFactory factory = this.storeMgr.getSQLExpressionFactory();
         JavaTypeMapping discriminatorMapping = table.getDiscriminatorMapping(false);
         DiscriminatorMetaData discriminatorMetaData = table.getDiscriminatorMetaData();
         if (discriminatorMapping != null && discriminatorMetaData.getStrategy() != DiscriminatorStrategy.NONE) {
            String discriminatorValue = className;
            if (discriminatorMetaData.getStrategy() == DiscriminatorStrategy.VALUE_MAP) {
               AbstractClassMetaData targetCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(className, this.clr);
               discriminatorValue = targetCmd.getInheritanceMetaData().getDiscriminatorMetaData().getValue();
            }

            SQLExpression discExpr = factory.newExpression(stmt, stmt.getPrimaryTable(), discriminatorMapping);
            SQLExpression discVal = factory.newLiteral(stmt, discriminatorMapping, discriminatorValue);
            stmt.whereAnd(discExpr.eq(discVal), false);
         }

         if (table.getMultitenancyMapping() != null) {
            JavaTypeMapping tenantMapping = table.getMultitenancyMapping();
            SQLTable tenantSqlTbl = stmt.getTable(tenantMapping.getTable(), tblGroupName);
            SQLExpression tenantExpr = stmt.getSQLExpressionFactory().newExpression(stmt, tenantSqlTbl, tenantMapping);
            SQLExpression tenantVal = stmt.getSQLExpressionFactory().newLiteral(stmt, tenantMapping, this.storeMgr.getStringProperty("datanucleus.TenantID"));
            stmt.whereAnd(tenantExpr.eq(tenantVal), true);
         }

         for(String subclassName : this.storeMgr.getSubClassesForClass(className, false, this.clr)) {
            DatastoreClass[] subclassTables = null;
            DatastoreClass subclassTable = this.storeMgr.getDatastoreClass(subclassName, this.clr);
            if (subclassTable == null) {
               AbstractClassMetaData targetSubCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(subclassName, this.clr);
               AbstractClassMetaData[] targetSubCmds = this.storeMgr.getClassesManagingTableForClass(targetSubCmd, this.clr);
               subclassTables = new DatastoreClass[targetSubCmds.length];

               for(int i = 0; i < targetSubCmds.length; ++i) {
                  subclassTables[i] = this.storeMgr.getDatastoreClass(targetSubCmds[i].getFullClassName(), this.clr);
               }
            } else {
               subclassTables = new DatastoreClass[]{subclassTable};
            }

            for(int i = 0; i < subclassTables.length; ++i) {
               if (subclassTables[i] != table) {
                  JavaTypeMapping tableIdMapping = table.getIdMapping();
                  JavaTypeMapping subclassIdMapping = subclassTables[i].getIdMapping();
                  SQLTable sqlTableSubclass = stmt.leftOuterJoin((SQLTable)null, tableIdMapping, subclassTables[i], (String)null, subclassIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
                  SQLExpression subclassIdExpr = factory.newExpression(stmt, sqlTableSubclass, subclassIdMapping);
                  SQLExpression nullExpr = new NullLiteral(stmt, (JavaTypeMapping)null, (Object)null, (String)null);
                  stmt.whereAnd(subclassIdExpr.eq(nullExpr), false);
               }
            }
         }

         if (this.hasOption("selectNucleusType")) {
            this.addTypeSelectForClass(stmt, className);
         }

         return stmt;
      }
   }

   protected SQLStatement getSQLStatementForCandidateViaJoin(String className) {
      DatastoreClass table = this.storeMgr.getDatastoreClass(className, this.clr);
      if (table == null) {
      }

      SQLStatement stmt = new SQLStatement(this.parentStmt, this.storeMgr, this.joinTable, this.joinTableAlias, this.candidateTableGroupName);
      stmt.setClassLoaderResolver(this.clr);
      stmt.setCandidateClassName(className);
      SQLTable candidateSQLTable = null;
      if (this.candidateTable != null) {
         JavaTypeMapping candidateIdMapping = this.candidateTable.getIdMapping();
         if (this.hasOption("allowNulls")) {
            candidateSQLTable = stmt.leftOuterJoin((SQLTable)null, this.joinElementMapping, this.candidateTable, (String)null, candidateIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
         } else {
            candidateSQLTable = stmt.innerJoin((SQLTable)null, this.joinElementMapping, this.candidateTable, (String)null, candidateIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
         }

         if (table != this.candidateTable) {
            stmt.innerJoin(candidateSQLTable, candidateIdMapping, table, (String)null, table.getIdMapping(), (Object[])null, stmt.getPrimaryTable().getGroupName());
         }
      } else {
         JavaTypeMapping candidateIdMapping = table.getIdMapping();
         if (this.hasOption("allowNulls")) {
            stmt.leftOuterJoin((SQLTable)null, this.joinElementMapping, table, (String)null, candidateIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
         } else {
            stmt.innerJoin((SQLTable)null, this.joinElementMapping, table, (String)null, candidateIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
         }
      }

      SQLExpressionFactory factory = this.storeMgr.getSQLExpressionFactory();
      JavaTypeMapping discriminatorMapping = table.getDiscriminatorMapping(false);
      DiscriminatorMetaData discriminatorMetaData = table.getDiscriminatorMetaData();
      if (discriminatorMapping != null && discriminatorMetaData.getStrategy() != DiscriminatorStrategy.NONE) {
         BooleanExpression discExpr = SQLStatementHelper.getExpressionForDiscriminatorForClass(stmt, className, discriminatorMetaData, discriminatorMapping, stmt.getPrimaryTable(), this.clr);
         stmt.whereAnd(discExpr, false);
      }

      for(String subclassName : this.storeMgr.getSubClassesForClass(className, false, this.clr)) {
         DatastoreClass[] subclassTables = null;
         DatastoreClass subclassTable = this.storeMgr.getDatastoreClass(subclassName, this.clr);
         if (subclassTable == null) {
            AbstractClassMetaData targetSubCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(subclassName, this.clr);
            AbstractClassMetaData[] targetSubCmds = this.storeMgr.getClassesManagingTableForClass(targetSubCmd, this.clr);
            subclassTables = new DatastoreClass[targetSubCmds.length];

            for(int i = 0; i < targetSubCmds.length; ++i) {
               subclassTables[i] = this.storeMgr.getDatastoreClass(targetSubCmds[i].getFullClassName(), this.clr);
            }
         } else {
            subclassTables = new DatastoreClass[]{subclassTable};
         }

         for(int i = 0; i < subclassTables.length; ++i) {
            if (subclassTables[i] != table) {
               JavaTypeMapping subclassIdMapping = subclassTables[i].getIdMapping();
               SQLTable sqlTableSubclass = stmt.leftOuterJoin((SQLTable)null, this.joinElementMapping, subclassTables[i], (String)null, subclassIdMapping, (Object[])null, stmt.getPrimaryTable().getGroupName());
               SQLExpression subclassIdExpr = factory.newExpression(stmt, sqlTableSubclass, subclassIdMapping);
               SQLExpression nullExpr = new NullLiteral(stmt, (JavaTypeMapping)null, (Object)null, (String)null);
               stmt.whereAnd(subclassIdExpr.eq(nullExpr), false);
            }
         }
      }

      if (this.hasOption("selectNucleusType")) {
         this.addTypeSelectForClass(stmt, className);
      }

      return stmt;
   }

   private void addTypeSelectForClass(SQLStatement stmt, String className) {
      if (this.hasOption("selectNucleusType")) {
         JavaTypeMapping m = this.storeMgr.getMappingManager().getMapping(String.class);
         String nuctypeName = className;
         if (this.maxClassNameLength > className.length()) {
            nuctypeName = StringUtils.leftAlignedPaddedString(className, this.maxClassNameLength);
         }

         StringLiteral lit = new StringLiteral(stmt, m, nuctypeName, (String)null);
         stmt.select(lit, "NUCLEUS_TYPE");
      }

   }
}
