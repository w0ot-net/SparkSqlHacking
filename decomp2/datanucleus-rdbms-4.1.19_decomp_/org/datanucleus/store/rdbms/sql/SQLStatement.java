package org.datanucleus.store.rdbms.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.NullOrderingType;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.query.QueryGenerator;
import org.datanucleus.store.rdbms.sql.expression.AggregateExpression;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.BooleanLiteral;
import org.datanucleus.store.rdbms.sql.expression.BooleanSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.ResultAliasExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class SQLStatement {
   public static final String EXTENSION_SQL_TABLE_NAMING_STRATEGY = "table-naming-strategy";
   protected static final Map tableNamerByName = new ConcurrentHashMap();
   protected SQLText sql;
   protected RDBMSStoreManager rdbmsMgr;
   protected ClassLoaderResolver clr;
   protected QueryGenerator queryGenerator;
   protected SQLTableNamer namer;
   protected String candidateClassName;
   protected boolean distinct;
   protected Map extensions;
   protected SQLStatement parent;
   protected List unions;
   protected List selectedItems;
   protected SQLExpression[] updates;
   protected boolean aggregated;
   protected SQLTable primaryTable;
   protected List joins;
   protected boolean requiresJoinReorder;
   protected Map tables;
   protected Map tableGroups;
   protected BooleanExpression where;
   protected List groupingExpressions;
   protected BooleanExpression having;
   protected SQLExpression[] orderingExpressions;
   protected boolean[] orderingDirections;
   protected NullOrderingType[] orderNullDirectives;
   protected long rangeOffset;
   protected long rangeCount;
   private int[] orderingColumnIndexes;

   public SQLStatement(RDBMSStoreManager rdbmsMgr, Table table, DatastoreIdentifier alias, String tableGroupName) {
      this((SQLStatement)null, rdbmsMgr, table, alias, tableGroupName, (Map)null);
   }

   public SQLStatement(RDBMSStoreManager rdbmsMgr, Table table, DatastoreIdentifier alias, String tableGroupName, Map extensions) {
      this((SQLStatement)null, rdbmsMgr, table, alias, tableGroupName, extensions);
   }

   public SQLStatement(SQLStatement parentStmt, RDBMSStoreManager rdbmsMgr, Table table, DatastoreIdentifier alias, String tableGroupName) {
      this(parentStmt, rdbmsMgr, table, alias, tableGroupName, (Map)null);
   }

   public SQLStatement(SQLStatement parentStmt, RDBMSStoreManager rdbmsMgr, Table table, DatastoreIdentifier alias, String tableGroupName, Map extensions) {
      this.sql = null;
      this.queryGenerator = null;
      this.namer = null;
      this.candidateClassName = null;
      this.distinct = false;
      this.parent = null;
      this.unions = null;
      this.selectedItems = new ArrayList();
      this.updates = null;
      this.aggregated = false;
      this.requiresJoinReorder = false;
      this.tableGroups = new HashMap();
      this.groupingExpressions = null;
      this.orderingExpressions = null;
      this.orderingDirections = null;
      this.orderNullDirectives = null;
      this.rangeOffset = -1L;
      this.rangeCount = -1L;
      this.parent = parentStmt;
      this.rdbmsMgr = rdbmsMgr;
      String namingStrategy = rdbmsMgr.getStringProperty("datanucleus.rdbms.sqlTableNamingStrategy");
      if (extensions != null && extensions.containsKey("table-naming-strategy")) {
         namingStrategy = (String)extensions.get("table-naming-strategy");
      }

      this.namer = this.getTableNamer(namingStrategy);
      String tableGrpName = tableGroupName != null ? tableGroupName : "Group0";
      if (alias == null) {
         alias = rdbmsMgr.getIdentifierFactory().newTableIdentifier(this.namer.getAliasForTable(this, table, tableGrpName));
      }

      this.primaryTable = new SQLTable(this, table, alias, tableGrpName);
      this.putSQLTableInGroup(this.primaryTable, tableGrpName, (SQLJoin.JoinType)null);
      if (parentStmt != null) {
         this.queryGenerator = parentStmt.getQueryGenerator();
      }

   }

   public RDBMSStoreManager getRDBMSManager() {
      return this.rdbmsMgr;
   }

   public void setClassLoaderResolver(ClassLoaderResolver clr) {
      this.clr = clr;
   }

   public ClassLoaderResolver getClassLoaderResolver() {
      if (this.clr == null) {
         this.clr = this.rdbmsMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null);
      }

      return this.clr;
   }

   public void setCandidateClassName(String name) {
      this.candidateClassName = name;
   }

   public String getCandidateClassName() {
      return this.candidateClassName;
   }

   public QueryGenerator getQueryGenerator() {
      return this.queryGenerator;
   }

   public void setQueryGenerator(QueryGenerator gen) {
      this.queryGenerator = gen;
   }

   public SQLExpressionFactory getSQLExpressionFactory() {
      return this.rdbmsMgr.getSQLExpressionFactory();
   }

   public DatastoreAdapter getDatastoreAdapter() {
      return this.rdbmsMgr.getDatastoreAdapter();
   }

   public SQLStatement getParentStatement() {
      return this.parent;
   }

   public boolean isChildStatementOf(SQLStatement stmt) {
      if (stmt != null && this.parent != null) {
         return stmt == this.parent ? true : this.isChildStatementOf(this.parent);
      } else {
         return false;
      }
   }

   public void addExtension(String key, Object value) {
      if (key != null) {
         this.invalidateStatement();
         if (key.equals("table-naming-strategy")) {
            this.namer = this.getTableNamer((String)value);
         } else {
            if (this.extensions == null) {
               this.extensions = new HashMap();
            }

            this.extensions.put(key, value);
         }
      }
   }

   public Object getValueForExtension(String key) {
      return this.extensions == null ? this.extensions : this.extensions.get(key);
   }

   public void union(SQLStatement stmt) {
      this.invalidateStatement();
      if (this.unions == null) {
         this.unions = new ArrayList();
      }

      this.unions.add(stmt);
   }

   public int getNumberOfUnions() {
      if (this.unions == null) {
         return 0;
      } else {
         int number = this.unions.size();

         for(SQLStatement unioned : this.unions) {
            number += unioned.getNumberOfUnions();
         }

         return number;
      }
   }

   public List getUnions() {
      return this.unions;
   }

   public boolean allUnionsForSamePrimaryTable() {
      if (this.unions != null) {
         for(SQLStatement unionStmt : this.unions) {
            if (!unionStmt.getPrimaryTable().equals(this.primaryTable)) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean isDistinct() {
      return this.distinct;
   }

   public void setDistinct(boolean distinct) {
      this.invalidateStatement();
      this.distinct = distinct;
   }

   public int getNumberOfSelects() {
      return this.selectedItems.size();
   }

   public int[] select(SQLExpression expr, String alias) {
      if (expr == null) {
         throw new NucleusException("Expression to select is null");
      } else {
         this.invalidateStatement();
         boolean primary = true;
         if (expr instanceof AggregateExpression) {
            this.aggregated = true;
            primary = false;
         } else if (expr.getSQLTable() == null || expr.getJavaTypeMapping() == null) {
            primary = false;
         }

         int[] selected = new int[expr.getNumberOfSubExpressions()];
         if (expr.getNumberOfSubExpressions() > 1) {
            for(int i = 0; i < expr.getNumberOfSubExpressions(); ++i) {
               selected[i] = this.selectItem(expr.getSubExpression(i).toSQLText(), alias != null ? alias + i : null, primary);
            }
         } else {
            selected[0] = this.selectItem(expr.toSQLText(), alias, primary);
         }

         if (this.unions != null) {
            for(SQLStatement stmt : this.unions) {
               stmt.select(expr, alias);
            }
         }

         return selected;
      }
   }

   public int[] select(SQLTable table, JavaTypeMapping mapping, String alias, boolean applyToUnions) {
      if (mapping == null) {
         throw new NucleusException("Mapping to select is null");
      } else {
         if (table == null) {
            table = this.primaryTable;
         }

         if (mapping.getTable() != table.getTable()) {
            throw new NucleusException("Table being selected from (\"" + table.getTable() + "\") is inconsistent with the column selected (\"" + mapping.getTable() + "\")");
         } else {
            this.invalidateStatement();
            DatastoreMapping[] mappings = mapping.getDatastoreMappings();
            int[] selected = new int[mappings.length];

            for(int i = 0; i < selected.length; ++i) {
               DatastoreIdentifier colAlias = null;
               if (alias != null) {
                  String name = selected.length > 1 ? alias + "_" + i : alias;
                  colAlias = this.rdbmsMgr.getIdentifierFactory().newColumnIdentifier(name);
               }

               SQLColumn col = new SQLColumn(table, mappings[i].getColumn(), colAlias);
               selected[i] = this.selectItem(new SQLText(col.getColumnSelectString()), alias != null ? colAlias.toString() : null, true);
            }

            if (applyToUnions && this.unions != null) {
               for(SQLStatement stmt : this.unions) {
                  stmt.select(table, mapping, alias);
               }
            }

            return selected;
         }
      }
   }

   public int[] select(SQLTable table, JavaTypeMapping mapping, String alias) {
      return this.select(table, mapping, alias, true);
   }

   public int select(SQLTable table, Column column, String alias) {
      if (column == null) {
         throw new NucleusException("Column to select is null");
      } else {
         if (table == null) {
            table = this.primaryTable;
         }

         if (column.getTable() != table.getTable()) {
            throw new NucleusException("Table being selected from (\"" + table.getTable() + "\") is inconsistent with the column selected (\"" + column.getTable() + "\")");
         } else {
            this.invalidateStatement();
            DatastoreIdentifier colAlias = null;
            if (alias != null) {
               colAlias = this.rdbmsMgr.getIdentifierFactory().newColumnIdentifier(alias);
            }

            SQLColumn col = new SQLColumn(table, column, colAlias);
            int position = this.selectItem(new SQLText(col.getColumnSelectString()), alias != null ? colAlias.toString() : null, true);
            if (this.unions != null) {
               for(SQLStatement stmt : this.unions) {
                  stmt.select(table, column, alias);
               }
            }

            return position;
         }
      }
   }

   private int selectItem(SQLText st, String alias, boolean primary) {
      SelectedItem item = new SelectedItem(st, alias, primary);
      if (this.selectedItems.contains(item)) {
         return this.selectedItems.indexOf(item) + 1;
      } else {
         int numberSelected = this.selectedItems.size();

         for(int i = 0; i < numberSelected; ++i) {
            SelectedItem selectedItem = (SelectedItem)this.selectedItems.get(i);
            if (selectedItem.getSQLText().equals(st)) {
               return i + 1;
            }
         }

         this.selectedItems.add(item);
         return this.selectedItems.indexOf(item) + 1;
      }
   }

   public void setUpdates(SQLExpression[] exprs) {
      this.invalidateStatement();
      this.updates = exprs;
   }

   public boolean hasUpdates() {
      if (this.updates == null) {
         return false;
      } else {
         for(int i = 0; i < this.updates.length; ++i) {
            if (this.updates[i] != null) {
               return true;
            }
         }

         return false;
      }
   }

   public SQLTable getPrimaryTable() {
      return this.primaryTable;
   }

   public SQLTable getTable(String alias) {
      if (alias.equals(this.primaryTable.alias.getName())) {
         return this.primaryTable;
      } else {
         return this.tables != null ? (SQLTable)this.tables.get(alias) : null;
      }
   }

   public SQLTable getTableForDatastoreContainer(Table table) {
      for(SQLTableGroup grp : this.tableGroups.values()) {
         SQLTable[] tbls = grp.getTables();

         for(int i = 0; i < tbls.length; ++i) {
            if (tbls[i].getTable() == table) {
               return tbls[i];
            }
         }
      }

      return null;
   }

   public SQLTable getTable(Table table, String groupName) {
      if (groupName == null) {
         return null;
      } else {
         SQLTableGroup tableGrp = (SQLTableGroup)this.tableGroups.get(groupName);
         if (tableGrp == null) {
            return null;
         } else {
            SQLTable[] tables = tableGrp.getTables();

            for(int i = 0; i < tables.length; ++i) {
               if (tables[i].getTable() == table) {
                  return tables[i];
               }
            }

            return null;
         }
      }
   }

   public SQLTableGroup getTableGroup(String groupName) {
      return (SQLTableGroup)this.tableGroups.get(groupName);
   }

   public int getNumberOfTableGroups() {
      return this.tableGroups.size();
   }

   public int getNumberOfTables() {
      return this.tables != null ? this.tables.size() : -1;
   }

   public SQLTable join(SQLJoin.JoinType joinType, SQLTable sourceTable, JavaTypeMapping sourceMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, Object[] discrimValues, String tableGrpName) {
      return this.join(joinType, sourceTable, sourceMapping, (JavaTypeMapping)null, target, targetAlias, targetMapping, (JavaTypeMapping)null, discrimValues, tableGrpName, true);
   }

   public SQLTable join(SQLJoin.JoinType joinType, SQLTable sourceTable, JavaTypeMapping sourceMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, Object[] discrimValues, String tableGrpName, boolean applyToUnions) {
      return this.join(joinType, sourceTable, sourceMapping, (JavaTypeMapping)null, target, targetAlias, targetMapping, (JavaTypeMapping)null, discrimValues, tableGrpName, applyToUnions);
   }

   public SQLTable join(SQLJoin.JoinType joinType, SQLTable sourceTable, JavaTypeMapping sourceMapping, JavaTypeMapping sourceParentMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, JavaTypeMapping targetParentMapping, Object[] discrimValues, String tableGrpName, boolean applyToUnions) {
      this.invalidateStatement();
      if (this.tables == null) {
         this.tables = new HashMap();
      }

      if (tableGrpName == null) {
         tableGrpName = "Group" + this.tableGroups.size();
      }

      if (targetAlias == null) {
         targetAlias = this.namer.getAliasForTable(this, target, tableGrpName);
      }

      if (sourceTable == null) {
         sourceTable = this.primaryTable;
      }

      DatastoreIdentifier targetId = this.rdbmsMgr.getIdentifierFactory().newTableIdentifier(targetAlias);
      SQLTable targetTbl = new SQLTable(this, target, targetId, tableGrpName);
      this.putSQLTableInGroup(targetTbl, tableGrpName, joinType);
      this.addJoin(joinType, sourceTable, sourceMapping, sourceParentMapping, targetTbl, targetMapping, targetParentMapping, discrimValues);
      if (this.unions != null && applyToUnions) {
         for(SQLStatement stmt : this.unions) {
            stmt.join(joinType, sourceTable, sourceMapping, sourceParentMapping, target, targetAlias, targetMapping, targetParentMapping, discrimValues, tableGrpName, true);
         }
      }

      return targetTbl;
   }

   public SQLTable innerJoin(SQLTable sourceTable, JavaTypeMapping sourceMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, Object[] discrimValues, String tableGrpName) {
      return this.join(SQLJoin.JoinType.INNER_JOIN, sourceTable, sourceMapping, (JavaTypeMapping)null, target, targetAlias, targetMapping, (JavaTypeMapping)null, discrimValues, tableGrpName, true);
   }

   public SQLTable innerJoin(SQLTable sourceTable, JavaTypeMapping sourceMapping, JavaTypeMapping sourceParentMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, JavaTypeMapping targetParentMapping, Object[] discrimValues, String tableGrpName) {
      return this.join(SQLJoin.JoinType.INNER_JOIN, sourceTable, sourceMapping, sourceParentMapping, target, targetAlias, targetMapping, targetParentMapping, discrimValues, tableGrpName, true);
   }

   public SQLTable leftOuterJoin(SQLTable sourceTable, JavaTypeMapping sourceMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, Object[] discrimValues, String tableGrpName) {
      return this.join(SQLJoin.JoinType.LEFT_OUTER_JOIN, sourceTable, sourceMapping, (JavaTypeMapping)null, target, targetAlias, targetMapping, (JavaTypeMapping)null, discrimValues, tableGrpName, true);
   }

   public SQLTable leftOuterJoin(SQLTable sourceTable, JavaTypeMapping sourceMapping, JavaTypeMapping sourceParentMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, JavaTypeMapping targetParentMapping, Object[] discrimValues, String tableGrpName) {
      return this.join(SQLJoin.JoinType.LEFT_OUTER_JOIN, sourceTable, sourceMapping, sourceParentMapping, target, targetAlias, targetMapping, targetParentMapping, discrimValues, tableGrpName, true);
   }

   public SQLTable rightOuterJoin(SQLTable sourceTable, JavaTypeMapping sourceMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, Object[] discrimValues, String tableGrpName) {
      return this.join(SQLJoin.JoinType.RIGHT_OUTER_JOIN, sourceTable, sourceMapping, (JavaTypeMapping)null, target, targetAlias, targetMapping, (JavaTypeMapping)null, discrimValues, tableGrpName, true);
   }

   public SQLTable rightOuterJoin(SQLTable sourceTable, JavaTypeMapping sourceMapping, JavaTypeMapping sourceParentMapping, Table target, String targetAlias, JavaTypeMapping targetMapping, JavaTypeMapping targetParentMapping, Object[] discrimValues, String tableGrpName) {
      return this.join(SQLJoin.JoinType.RIGHT_OUTER_JOIN, sourceTable, sourceMapping, sourceParentMapping, target, targetAlias, targetMapping, targetParentMapping, discrimValues, tableGrpName, true);
   }

   public SQLTable crossJoin(Table target, String targetAlias, String tableGrpName) {
      this.invalidateStatement();
      if (this.tables == null) {
         this.tables = new HashMap();
      }

      if (tableGrpName == null) {
         tableGrpName = "Group" + this.tableGroups.size();
      }

      if (targetAlias == null) {
         targetAlias = this.namer.getAliasForTable(this, target, tableGrpName);
      }

      DatastoreIdentifier targetId = this.rdbmsMgr.getIdentifierFactory().newTableIdentifier(targetAlias);
      SQLTable targetTbl = new SQLTable(this, target, targetId, tableGrpName);
      this.putSQLTableInGroup(targetTbl, tableGrpName, SQLJoin.JoinType.CROSS_JOIN);
      this.addJoin(SQLJoin.JoinType.CROSS_JOIN, this.primaryTable, (JavaTypeMapping)null, (JavaTypeMapping)null, targetTbl, (JavaTypeMapping)null, (JavaTypeMapping)null, (Object[])null);
      if (this.unions != null) {
         for(SQLStatement stmt : this.unions) {
            stmt.crossJoin(target, targetAlias, tableGrpName);
         }
      }

      return targetTbl;
   }

   public SQLJoin.JoinType getJoinTypeForTable(SQLTable sqlTbl) {
      if (this.joins == null) {
         return null;
      } else {
         for(SQLJoin join : this.joins) {
            if (join.getTable().equals(sqlTbl)) {
               return join.getType();
            }
         }

         return null;
      }
   }

   public SQLJoin getJoinForTable(SQLTable sqlTbl) {
      if (this.joins == null) {
         return null;
      } else {
         for(SQLJoin join : this.joins) {
            if (join.getTable().equals(sqlTbl)) {
               return join;
            }
         }

         return null;
      }
   }

   public String removeCrossJoin(SQLTable targetSqlTbl) {
      if (this.joins == null) {
         return null;
      } else {
         Iterator<SQLJoin> joinIter = this.joins.iterator();

         while(joinIter.hasNext()) {
            SQLJoin join = (SQLJoin)joinIter.next();
            if (join.getTable().equals(targetSqlTbl) && join.getType() == SQLJoin.JoinType.CROSS_JOIN) {
               joinIter.remove();
               this.requiresJoinReorder = true;
               this.tables.remove(join.getTable().alias.getName());
               String removedAliasName = join.getTable().alias.getName();
               if (this.unions != null) {
                  for(SQLStatement stmt : this.unions) {
                     stmt.removeCrossJoin(targetSqlTbl);
                  }
               }

               return removedAliasName;
            }
         }

         return null;
      }
   }

   private void putSQLTableInGroup(SQLTable sqlTbl, String groupName, SQLJoin.JoinType joinType) {
      SQLTableGroup tableGrp = (SQLTableGroup)this.tableGroups.get(groupName);
      if (tableGrp == null) {
         tableGrp = new SQLTableGroup(groupName, joinType);
      }

      tableGrp.addTable(sqlTbl);
      this.tableGroups.put(groupName, tableGrp);
   }

   protected void addJoin(SQLJoin.JoinType joinType, SQLTable sourceTable, JavaTypeMapping sourceMapping, JavaTypeMapping sourceParentMapping, SQLTable targetTable, JavaTypeMapping targetMapping, JavaTypeMapping targetParentMapping, Object[] discrimValues) {
      if (this.tables == null) {
         throw new NucleusException("tables not set in statement!");
      } else if (this.tables.containsValue(targetTable)) {
         NucleusLogger.DATASTORE.debug("Attempt to join to " + targetTable + " but join already exists");
      } else if (joinType == SQLJoin.JoinType.RIGHT_OUTER_JOIN && !this.rdbmsMgr.getDatastoreAdapter().supportsOption("RightOuterJoin")) {
         throw new NucleusUserException("RIGHT OUTER JOIN is not supported by this datastore");
      } else {
         this.tables.put(targetTable.alias.getName(), targetTable);
         BooleanExpression joinCondition = this.getJoinConditionForJoin(sourceTable, sourceMapping, sourceParentMapping, targetTable, targetMapping, targetParentMapping, discrimValues);
         if (this.rdbmsMgr.getDatastoreAdapter().supportsOption("ANSI_Join_Syntax")) {
            SQLJoin join = new SQLJoin(joinType, targetTable, sourceTable, joinCondition);
            if (this.joins == null) {
               this.joins = new ArrayList();
            }

            int position = -1;
            if (this.queryGenerator != null && this.queryGenerator.processingOnClause()) {
               if (this.primaryTable == sourceTable) {
                  if (this.joins.size() > 0) {
                     position = 0;
                  }
               } else {
                  int i = 1;

                  for(SQLJoin sqlJoin : this.joins) {
                     if (sqlJoin.getJoinedTable() == sourceTable) {
                        position = i;
                        break;
                     }

                     ++i;
                  }
               }
            }

            if (position >= 0) {
               this.joins.add(position, join);
            } else {
               this.joins.add(join);
            }
         } else {
            SQLJoin join = new SQLJoin(SQLJoin.JoinType.NON_ANSI_JOIN, targetTable, sourceTable, (BooleanExpression)null);
            if (this.joins == null) {
               this.joins = new ArrayList();
            }

            this.joins.add(join);
            this.whereAnd(joinCondition, false);
         }

      }
   }

   protected BooleanExpression getJoinConditionForJoin(SQLTable sourceTable, JavaTypeMapping sourceMapping, JavaTypeMapping sourceParentMapping, SQLTable targetTable, JavaTypeMapping targetMapping, JavaTypeMapping targetParentMapping, Object[] discrimValues) {
      BooleanExpression joinCondition = null;
      if (sourceMapping != null && targetMapping != null) {
         if (sourceMapping.getNumberOfDatastoreMappings() != targetMapping.getNumberOfDatastoreMappings()) {
            throw new NucleusException("Cannot join from " + sourceMapping + " to " + targetMapping + " since they have different numbers of datastore columns!");
         }

         SQLExpressionFactory factory = this.rdbmsMgr.getSQLExpressionFactory();
         SQLExpression sourceExpr = null;
         if (sourceParentMapping == null) {
            sourceExpr = factory.newExpression(this, sourceTable != null ? sourceTable : this.primaryTable, sourceMapping);
         } else {
            sourceExpr = factory.newExpression(this, sourceTable != null ? sourceTable : this.primaryTable, sourceMapping, sourceParentMapping);
         }

         SQLExpression targetExpr = null;
         if (targetParentMapping == null) {
            targetExpr = factory.newExpression(this, targetTable, targetMapping);
         } else {
            targetExpr = factory.newExpression(this, targetTable, targetMapping, targetParentMapping);
         }

         joinCondition = sourceExpr.eq(targetExpr);
         JavaTypeMapping discrimMapping = targetTable.getTable().getDiscriminatorMapping(false);
         if (discrimMapping != null && discrimValues != null) {
            SQLExpression discrimExpr = factory.newExpression(this, targetTable, discrimMapping);
            BooleanExpression discrimCondition = null;

            for(int i = 0; i < discrimValues.length; ++i) {
               SQLExpression discrimVal = factory.newLiteral(this, discrimMapping, discrimValues[i]);
               BooleanExpression condition = discrimExpr.eq(discrimVal);
               if (discrimCondition == null) {
                  discrimCondition = condition;
               } else {
                  discrimCondition = discrimCondition.ior(condition);
               }
            }

            if (discrimCondition != null) {
               discrimCondition.encloseInParentheses();
               joinCondition = joinCondition.and(discrimCondition);
            }
         }
      }

      return joinCondition;
   }

   protected synchronized SQLTableNamer getTableNamer(String namingSchema) {
      SQLTableNamer namer = (SQLTableNamer)tableNamerByName.get(namingSchema);
      if (namer == null) {
         try {
            namer = (SQLTableNamer)this.rdbmsMgr.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store.rdbms.sql_tablenamer", "name", namingSchema, "class", (Class[])null, (Object[])null);
         } catch (Exception e) {
            throw new NucleusException("Attempt to find/instantiate SQL table namer " + namingSchema + " threw an exception", e);
         }

         tableNamerByName.put(namingSchema, namer);
      }

      return namer;
   }

   public void whereAnd(BooleanExpression expr, boolean applyToUnions) {
      this.invalidateStatement();
      if (!(expr instanceof BooleanLiteral) || expr.isParameter() || !(Boolean)((BooleanLiteral)expr).getValue()) {
         if (this.where == null) {
            this.where = expr;
         } else {
            this.where = this.where.and(expr);
         }

         if (this.unions != null && applyToUnions) {
            for(SQLStatement stmt : this.unions) {
               stmt.whereAnd(expr, true);
            }
         }

      }
   }

   public void whereOr(BooleanExpression expr, boolean applyToUnions) {
      this.invalidateStatement();
      if (this.where == null) {
         this.where = expr;
      } else {
         this.where = this.where.ior(expr);
      }

      if (this.unions != null && applyToUnions) {
         for(SQLStatement stmt : this.unions) {
            stmt.whereOr(expr, true);
         }
      }

   }

   public void addGroupingExpression(SQLExpression expr) {
      this.invalidateStatement();
      if (this.groupingExpressions == null) {
         this.groupingExpressions = new ArrayList();
      }

      this.groupingExpressions.add(expr);
      this.aggregated = true;
      if (this.unions != null) {
         Iterator<SQLStatement> i = this.unions.iterator();

         while(i.hasNext()) {
            ((SQLStatement)i.next()).addGroupingExpression(expr);
         }
      }

   }

   public void setHaving(BooleanExpression expr) {
      this.invalidateStatement();
      this.having = expr;
      this.aggregated = true;
      if (this.unions != null) {
         Iterator<SQLStatement> i = this.unions.iterator();

         while(i.hasNext()) {
            ((SQLStatement)i.next()).setHaving(expr);
         }
      }

   }

   public void setOrdering(SQLExpression[] exprs, boolean[] descending) {
      this.setOrdering(exprs, descending, (NullOrderingType[])null);
   }

   public void setOrdering(SQLExpression[] exprs, boolean[] descending, NullOrderingType[] nullOrders) {
      if (exprs != null && descending != null && exprs.length != descending.length) {
         throw (new NucleusException(Localiser.msg("052503", new Object[]{"" + exprs.length, "" + descending.length}))).setFatal();
      } else {
         this.invalidateStatement();
         this.orderingExpressions = exprs;
         this.orderingDirections = descending;
         this.orderNullDirectives = nullOrders;
      }
   }

   public void setRange(long offset, long count) {
      this.invalidateStatement();
      this.rangeOffset = offset;
      this.rangeCount = count;
   }

   public synchronized SQLText getSelectStatement() {
      if (this.sql != null) {
         return this.sql;
      } else {
         DatastoreAdapter dba = this.getDatastoreAdapter();
         boolean lock = false;
         Boolean val = (Boolean)this.getValueForExtension("lock-for-update");
         if (val != null) {
            lock = val;
         }

         boolean addAliasToAllSelects = false;
         if ((this.rangeOffset > 0L || this.rangeCount > -1L) && dba.getRangeByRowNumberColumn2().length() > 0) {
            addAliasToAllSelects = true;
         }

         this.sql = new SQLText("SELECT ");
         if (this.distinct) {
            this.sql.append("DISTINCT ");
         }

         this.addOrderingColumnsToSelect();
         if (this.selectedItems.isEmpty()) {
            this.sql.append("*");
         } else {
            int autoAliasNum = 0;
            Iterator<SelectedItem> selectItemIter = this.selectedItems.iterator();

            while(selectItemIter.hasNext()) {
               SelectedItem selectedItem = (SelectedItem)selectItemIter.next();
               SQLText selectedST = selectedItem.getSQLText();
               this.sql.append(selectedST);
               if (selectedItem.getAlias() != null) {
                  this.sql.append(" AS ").append(this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase(selectedItem.getAlias()));
               } else if (addAliasToAllSelects) {
                  this.sql.append(" AS ").append(this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase("DN_" + autoAliasNum));
                  ++autoAliasNum;
               }

               if (selectItemIter.hasNext()) {
                  this.sql.append(',');
               }
            }

            if ((this.rangeOffset > -1L || this.rangeCount > -1L) && dba.getRangeByRowNumberColumn().length() > 0) {
               this.sql.append(',').append(dba.getRangeByRowNumberColumn()).append(" rn");
            }
         }

         this.sql.append(" FROM ");
         this.sql.append(this.primaryTable.toString());
         if (lock && dba.supportsOption("LockOptionAfterFromClause")) {
            this.sql.append(" WITH ").append(dba.getSelectWithLockOption());
         }

         if (this.joins != null) {
            this.sql.append(this.getSqlForJoins(lock));
         }

         if (this.where != null) {
            this.sql.append(" WHERE ").append(this.where.toSQLText());
         }

         if (this.groupingExpressions != null) {
            List<SQLText> groupBy = new ArrayList();

            for(SQLExpression expr : this.groupingExpressions) {
               boolean exists = false;
               String exprSQL = expr.toSQLText().toSQL();

               for(SQLText st : groupBy) {
                  String sql = st.toSQL();
                  if (sql.equals(exprSQL)) {
                     exists = true;
                     break;
                  }
               }

               if (!exists) {
                  groupBy.add(expr.toSQLText());
               }
            }

            if (dba.supportsOption("GroupByIncludesAllSelectPrimaries")) {
               for(SelectedItem selItem : this.selectedItems) {
                  if (selItem.isPrimary()) {
                     boolean exists = false;
                     String selItemSQL = selItem.getSQLText().toSQL();

                     for(SQLText st : groupBy) {
                        String sql = st.toSQL();
                        if (sql.equals(selItemSQL)) {
                           exists = true;
                           break;
                        }
                     }

                     if (!exists) {
                        groupBy.add(selItem.getSQLText());
                     }
                  }
               }
            }

            if (groupBy.size() > 0 && this.aggregated) {
               this.sql.append(" GROUP BY ");

               for(int i = 0; i < groupBy.size(); ++i) {
                  if (i > 0) {
                     this.sql.append(',');
                  }

                  this.sql.append((SQLText)groupBy.get(i));
               }
            }
         }

         if (this.having != null) {
            this.sql.append(" HAVING ").append(this.having.toSQLText());
         }

         if (this.unions != null) {
            if (!dba.supportsOption("Union_Syntax")) {
               throw (new NucleusException(Localiser.msg("052504", new Object[]{"UNION"}))).setFatal();
            }

            Iterator<SQLStatement> unionIter = this.unions.iterator();

            while(unionIter.hasNext()) {
               if (dba.supportsOption("UseUnionAll")) {
                  this.sql.append(" UNION ALL ");
               } else {
                  this.sql.append(" UNION ");
               }

               SQLStatement stmt = (SQLStatement)unionIter.next();
               SQLText unionSql = stmt.getSelectStatement();
               this.sql.append(unionSql);
            }
         }

         SQLText orderStmt = this.generateOrderingStatement();
         if (orderStmt != null) {
            this.sql.append(" ORDER BY ").append(orderStmt);
         }

         if (this.rangeOffset > -1L || this.rangeCount > -1L) {
            String limitClause = dba.getRangeByLimitEndOfStatementClause(this.rangeOffset, this.rangeCount, orderStmt != null);
            if (limitClause.length() > 0) {
               this.sql.append(" ").append(limitClause);
            }
         }

         if (lock && dba.supportsOption("LockWithSelectForUpdate")) {
            if (this.distinct && !dba.supportsOption("DistinctWithSelectForUpdate")) {
               NucleusLogger.QUERY.warn(Localiser.msg("052502"));
            } else if (this.groupingExpressions != null && !dba.supportsOption("GroupingWithSelectForUpdate")) {
               NucleusLogger.QUERY.warn(Localiser.msg("052506"));
            } else if (this.having != null && !dba.supportsOption("HavingWithSelectForUpdate")) {
               NucleusLogger.QUERY.warn(Localiser.msg("052507"));
            } else if (this.orderingExpressions != null && !dba.supportsOption("OrderingWithSelectForUpdate")) {
               NucleusLogger.QUERY.warn(Localiser.msg("052508"));
            } else if (this.joins != null && !this.joins.isEmpty() && !dba.supportsOption("MultipleTablesWithSelectForUpdate")) {
               NucleusLogger.QUERY.warn(Localiser.msg("052509"));
            } else {
               this.sql.append(" " + dba.getSelectForUpdateText());
               if (dba.supportsOption("SelectForUpdateNoWait")) {
                  Boolean nowait = (Boolean)this.getValueForExtension("for-update-nowait");
                  if (nowait != null) {
                     this.sql.append(" NOWAIT");
                  }
               }
            }
         }

         if (lock && !dba.supportsOption("LockWithSelectForUpdate") && !dba.supportsOption("LockOptionAfterFromClause") && !dba.supportsOption("LockOptionWithinJoinClause")) {
            NucleusLogger.QUERY.warn("Requested locking of query statement, but this RDBMS doesn't support a convenient mechanism");
         }

         if (this.rangeOffset > 0L || this.rangeCount > -1L) {
            if (dba.getRangeByRowNumberColumn2().length() > 0) {
               SQLText userSql = this.sql;
               SQLText innerSql = new SQLText("SELECT subq.*");
               innerSql.append(',').append(dba.getRangeByRowNumberColumn2()).append(" rn");
               innerSql.append(" FROM (").append(userSql).append(") subq ");
               SQLText outerSql = (new SQLText("SELECT * FROM (")).append(innerSql).append(") ");
               outerSql.append("WHERE ");
               if (this.rangeOffset > 0L) {
                  outerSql.append("rn > " + this.rangeOffset);
                  if (this.rangeCount > -1L) {
                     outerSql.append(" AND rn <= " + (this.rangeCount + this.rangeOffset));
                  }
               } else {
                  outerSql.append(" rn <= " + this.rangeCount);
               }

               this.sql = outerSql;
            } else if (dba.getRangeByRowNumberColumn().length() > 0) {
               SQLText userSql = this.sql;
               this.sql = new SQLText("SELECT ");
               Iterator<SelectedItem> selectedItemIter = this.selectedItems.iterator();

               while(selectedItemIter.hasNext()) {
                  SelectedItem selectedItemExpr = (SelectedItem)selectedItemIter.next();
                  this.sql.append("subq.");
                  String selectedCol = selectedItemExpr.getSQLText().toSQL();
                  if (selectedItemExpr.getAlias() != null) {
                     selectedCol = this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase(selectedItemExpr.getAlias());
                  } else {
                     int dotIndex = selectedCol.indexOf(".");
                     if (dotIndex > 0) {
                        selectedCol = selectedCol.substring(dotIndex + 1);
                     }
                  }

                  this.sql.append(selectedCol);
                  if (selectedItemIter.hasNext()) {
                     this.sql.append(',');
                  }
               }

               this.sql.append(" FROM (").append(userSql).append(") subq WHERE ");
               if (this.rangeOffset > 0L) {
                  this.sql.append("subq.rn").append(">").append("" + this.rangeOffset);
               }

               if (this.rangeCount > 0L) {
                  if (this.rangeOffset > 0L) {
                     this.sql.append(" AND ");
                  }

                  this.sql.append("subq.rn").append("<=").append("" + (this.rangeCount + this.rangeOffset));
               }
            }
         }

         return this.sql;
      }
   }

   private void reorderJoins(List knownJoins, List joinsToAdd) {
      if (joinsToAdd == null) {
         this.requiresJoinReorder = false;
      } else {
         while(joinsToAdd.size() > 0) {
            Iterator<SQLJoin> joinIter = joinsToAdd.iterator();
            int origSize = joinsToAdd.size();

            while(joinIter.hasNext()) {
               SQLJoin join = (SQLJoin)joinIter.next();
               if (join.getType() == SQLJoin.JoinType.CROSS_JOIN) {
                  knownJoins.add(join);
                  joinIter.remove();
               } else if (join.getType() == SQLJoin.JoinType.NON_ANSI_JOIN) {
                  knownJoins.add(join);
                  joinIter.remove();
               } else if (join.getJoinedTable().equals(this.primaryTable)) {
                  knownJoins.add(join);
                  joinIter.remove();
               } else {
                  Iterator<SQLJoin> knownJoinIter = knownJoins.iterator();
                  boolean valid = false;

                  while(knownJoinIter.hasNext()) {
                     SQLJoin currentJoin = (SQLJoin)knownJoinIter.next();
                     if (join.getJoinedTable().equals(currentJoin.getTable())) {
                        valid = true;
                        break;
                     }
                  }

                  if (valid) {
                     knownJoins.add(join);
                     joinIter.remove();
                  }
               }
            }

            if (joinsToAdd.size() == origSize) {
               throw new NucleusException("Unable to reorder joins for SQL statement since circular! Consider reordering the components in the WHERE clause : affected joins - " + StringUtils.collectionToString(joinsToAdd));
            }
         }

         this.requiresJoinReorder = false;
      }
   }

   private SQLText getSqlForJoins(boolean lock) {
      SQLText sql = new SQLText();
      DatastoreAdapter dba = this.getDatastoreAdapter();
      if (this.requiresJoinReorder) {
         List<SQLJoin> theJoins = new ArrayList(this.joins.size());
         this.reorderJoins(theJoins, this.joins);
         this.joins = theJoins;
      }

      for(SQLJoin join : this.joins) {
         if (join.getType() == SQLJoin.JoinType.CROSS_JOIN) {
            if (dba.supportsOption("ANSI_CrossJoin_Syntax")) {
               sql.append(" ").append(join.toSQLText(dba, lock));
            } else if (dba.supportsOption("ANSI_CrossJoinAsInner11_Syntax")) {
               sql.append(" INNER JOIN " + join.getTable() + " ON 1=1");
            } else {
               sql.append(",").append(join.getTable().toString());
            }
         } else if (dba.supportsOption("ANSI_Join_Syntax")) {
            sql.append(" ").append(join.toSQLText(dba, lock));
         } else {
            sql.append(",").append(join.toSQLText(dba, lock));
         }
      }

      return sql;
   }

   public synchronized SQLText getUpdateStatement() {
      if (this.sql != null) {
         return this.sql;
      } else {
         SQLText setSQL = new SQLText("SET ");
         if (this.updates != null && this.updates.length > 0) {
            for(int i = 0; i < this.updates.length; ++i) {
               if (this.updates[i] != null) {
                  if (i != 0) {
                     setSQL.append(",");
                  }

                  setSQL.append(this.updates[i].toSQLText());
               }
            }
         }

         this.sql = this.rdbmsMgr.getDatastoreAdapter().getUpdateTableStatement(this.primaryTable, setSQL);
         if (this.joins != null) {
            Iterator<SQLJoin> joinIter = this.joins.iterator();
            SQLJoin subJoin = (SQLJoin)joinIter.next();
            SQLStatement subStmt = new SQLStatement(this, this.rdbmsMgr, subJoin.getTable().getTable(), subJoin.getTable().getAlias(), subJoin.getTable().getGroupName());
            subStmt.whereAnd(subJoin.getCondition(), false);
            if (this.where != null) {
               subStmt.whereAnd(this.where, false);
            }

            while(joinIter.hasNext()) {
               SQLJoin join = (SQLJoin)joinIter.next();
               subStmt.joins.add(join);
            }

            BooleanExpression existsExpr = new BooleanSubqueryExpression(this, "EXISTS", subStmt);
            this.where = existsExpr;
         }

         if (this.where != null) {
            this.sql.append(" WHERE ").append(this.where.toSQLText());
         }

         return this.sql;
      }
   }

   public synchronized SQLText getDeleteStatement() {
      if (this.sql != null) {
         return this.sql;
      } else {
         this.sql = new SQLText(this.rdbmsMgr.getDatastoreAdapter().getDeleteTableStatement(this.primaryTable));
         if (this.joins != null) {
            Iterator<SQLJoin> joinIter = this.joins.iterator();
            SQLJoin subJoin = (SQLJoin)joinIter.next();
            SQLStatement subStmt = new SQLStatement(this, this.rdbmsMgr, subJoin.getTable().getTable(), subJoin.getTable().getAlias(), subJoin.getTable().getGroupName());
            subStmt.whereAnd(subJoin.getCondition(), false);
            if (this.where != null) {
               subStmt.whereAnd(this.where, false);
            }

            while(joinIter.hasNext()) {
               SQLJoin join = (SQLJoin)joinIter.next();
               subStmt.joins.add(join);
            }

            BooleanExpression existsExpr = new BooleanSubqueryExpression(this, "EXISTS", subStmt);
            this.where = existsExpr;
         }

         if (this.where != null) {
            this.sql.append(" WHERE ").append(this.where.toSQLText());
         }

         return this.sql;
      }
   }

   protected SQLText generateOrderingStatement() {
      SQLText orderStmt = null;
      if (this.orderingExpressions != null && this.orderingExpressions.length > 0) {
         DatastoreAdapter dba = this.getDatastoreAdapter();
         if (dba.supportsOption("OrderByUsingSelectColumnIndex")) {
            orderStmt = new SQLText();

            for(int i = 0; i < this.orderingExpressions.length; ++i) {
               if (i > 0) {
                  orderStmt.append(',');
               }

               orderStmt.append(Integer.toString(this.orderingColumnIndexes[i]));
               if (this.orderingDirections[i]) {
                  orderStmt.append(" DESC");
               }

               if (this.orderNullDirectives != null && this.orderNullDirectives[i] != null && dba.supportsOption("OrderByWithNullsDirectives")) {
                  orderStmt.append(" " + (this.orderNullDirectives[i] == NullOrderingType.NULLS_FIRST ? "NULLS FIRST" : "NULLS LAST"));
               }
            }
         } else {
            orderStmt = new SQLText();
            boolean needsSelect = dba.supportsOption("IncludeOrderByColumnsInSelect");
            if (this.parent != null) {
               needsSelect = false;
            }

            for(int i = 0; i < this.orderingExpressions.length; ++i) {
               SQLExpression orderExpr = this.orderingExpressions[i];
               boolean orderDirection = this.orderingDirections[i];
               NullOrderingType orderNullDirective = this.orderNullDirectives != null ? this.orderNullDirectives[i] : null;
               if (i > 0) {
                  orderStmt.append(',');
               }

               if (needsSelect && !this.aggregated) {
                  if (orderExpr instanceof ResultAliasExpression) {
                     String orderStr = this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase(((ResultAliasExpression)orderExpr).getResultAlias());
                     this.addOrderComponent(orderStmt, orderStr, orderExpr, orderDirection, orderNullDirective, dba);
                  } else {
                     String orderString = "NUCORDER" + i;
                     if (orderExpr.getNumberOfSubExpressions() == 1) {
                        String orderStr = this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase(orderString);
                        this.addOrderComponent(orderStmt, orderStr, orderExpr, orderDirection, orderNullDirective, dba);
                     } else {
                        DatastoreMapping[] mappings = orderExpr.getJavaTypeMapping().getDatastoreMappings();

                        for(int j = 0; j < mappings.length; ++j) {
                           String orderStr = this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase(orderString + "_" + j);
                           this.addOrderComponent(orderStmt, orderStr, orderExpr, orderDirection, orderNullDirective, dba);
                           if (j < mappings.length - 1) {
                              orderStmt.append(',');
                           }
                        }
                     }
                  }
               } else if (orderExpr instanceof ResultAliasExpression) {
                  String orderStr = this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase(((ResultAliasExpression)orderExpr).getResultAlias());
                  this.addOrderComponent(orderStmt, orderStr, orderExpr, orderDirection, orderNullDirective, dba);
               } else {
                  this.addOrderComponent(orderStmt, orderExpr.toSQLText().toSQL(), orderExpr, orderDirection, orderNullDirective, dba);
               }
            }
         }
      }

      return orderStmt;
   }

   protected void addOrderComponent(SQLText orderST, String orderString, SQLExpression orderExpr, boolean orderDirection, NullOrderingType orderNullDirective, DatastoreAdapter dba) {
      String orderParam = dba.getOrderString(this.rdbmsMgr, orderString, orderExpr);
      if (orderNullDirective != null) {
         if (dba.supportsOption("OrderByWithNullsDirectives")) {
            orderST.append(orderParam).append(orderDirection ? " DESC" : "");
            orderST.append(orderNullDirective == NullOrderingType.NULLS_FIRST ? " NULLS FIRST" : " NULLS LAST");
         } else if (dba.supportsOption("OrderByNullsUsingCaseNull")) {
            String caseWhenOrderParam = orderExpr.toSQLText().toSQL();
            if (orderExpr instanceof ResultAliasExpression) {
               SQLStatement orderExprStmt = orderExpr.getSQLStatement();
               String selectAlias = ((ResultAliasExpression)orderExpr).getResultAlias();

               for(int i = 0; i < orderExprStmt.selectedItems.size(); ++i) {
                  SelectedItem item = (SelectedItem)orderExprStmt.selectedItems.get(i);
                  if (selectAlias.equalsIgnoreCase(item.getAlias())) {
                     caseWhenOrderParam = item.getSQLText().toSQL();
                     break;
                  }
               }
            }

            orderST.append("(CASE WHEN " + caseWhenOrderParam + " IS NULL THEN 1 ELSE 0 END)").append(orderNullDirective == NullOrderingType.NULLS_FIRST ? " DESC" : " ASC");
            orderST.append(", " + orderParam).append(orderDirection ? " DESC" : " ASC");
         } else if (dba.supportsOption("OrderByNullsUsingColumnIsNull")) {
            if (orderNullDirective == NullOrderingType.NULLS_LAST && orderExpr.getSQLTable() != null) {
               orderST.append(orderParam).append(" IS NULL,");
            }

            orderST.append(orderParam);
            orderST.append(orderDirection ? " DESC" : "");
         } else if (dba.supportsOption("OrderByNullsUsingIsNull")) {
            if (orderNullDirective == NullOrderingType.NULLS_LAST && orderExpr.getSQLTable() != null) {
               orderST.append("ISNULL(").append(orderParam).append("),");
            }

            orderST.append(orderParam);
            orderST.append(orderDirection ? " DESC" : "");
         } else {
            NucleusLogger.DATASTORE_RETRIEVE.warn("Query contains NULLS directive yet this datastore doesn't provide any support for handling this. Nulls directive will be ignored");
         }
      } else {
         orderST.append(orderParam).append(orderDirection ? " DESC" : "");
      }

   }

   protected void addOrderingColumnsToSelect() {
      if (this.orderingExpressions != null && this.parent == null) {
         DatastoreAdapter dba = this.getDatastoreAdapter();
         if (dba.supportsOption("OrderByUsingSelectColumnIndex")) {
            this.orderingColumnIndexes = new int[this.orderingExpressions.length];

            for(int i = 0; i < this.orderingExpressions.length; ++i) {
               this.orderingColumnIndexes[i] = this.selectItem(this.orderingExpressions[i].toSQLText(), (String)null, !this.aggregated);
               if (this.unions != null) {
                  for(SQLStatement stmt : this.unions) {
                     stmt.selectItem(this.orderingExpressions[i].toSQLText(), (String)null, !this.aggregated);
                  }
               }
            }
         } else if (dba.supportsOption("IncludeOrderByColumnsInSelect")) {
            for(int i = 0; i < this.orderingExpressions.length; ++i) {
               if (!(this.orderingExpressions[i] instanceof ResultAliasExpression)) {
                  if (this.orderingExpressions[i].getNumberOfSubExpressions() != 1 && !this.aggregated) {
                     JavaTypeMapping m = this.orderingExpressions[i].getJavaTypeMapping();
                     DatastoreMapping[] mappings = m.getDatastoreMappings();

                     for(int j = 0; j < mappings.length; ++j) {
                        String alias = this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase("NUCORDER" + i + "_" + j);
                        DatastoreIdentifier aliasId = this.rdbmsMgr.getIdentifierFactory().newColumnIdentifier(alias);
                        SQLColumn col = new SQLColumn(this.orderingExpressions[i].getSQLTable(), mappings[j].getColumn(), aliasId);
                        this.selectItem(new SQLText(col.getColumnSelectString()), alias, !this.aggregated);
                        if (this.unions != null) {
                           for(SQLStatement stmt : this.unions) {
                              stmt.selectItem(new SQLText(col.getColumnSelectString()), alias, !this.aggregated);
                           }
                        }
                     }
                  } else {
                     String alias = this.rdbmsMgr.getIdentifierFactory().getIdentifierInAdapterCase("NUCORDER" + i);
                     if (this.unions != null) {
                        for(SQLStatement stmt : this.unions) {
                           stmt.selectItem(this.orderingExpressions[i].toSQLText(), this.aggregated ? null : alias, !this.aggregated);
                        }
                     }

                     this.selectItem(this.orderingExpressions[i].toSQLText(), this.aggregated ? null : alias, !this.aggregated);
                  }
               }
            }
         }
      }

   }

   protected void invalidateStatement() {
      this.sql = null;
   }

   public void log(NucleusLogger logger) {
      if (this.updates != null) {
         logger.debug("SQLStatement : " + this.getUpdateStatement().toSQL());
      } else {
         logger.debug("SQLStatement : " + this.getSelectStatement().toSQL());
      }

      for(String grpName : this.tableGroups.keySet()) {
         logger.debug("SQLStatement : TableGroup=" + this.tableGroups.get(grpName));
      }

   }

   protected class SelectedItem {
      SQLText sqlText;
      String alias;
      boolean primary = true;

      public SelectedItem(SQLText st, String alias, boolean primary) {
         this.sqlText = st;
         this.alias = alias;
         this.primary = primary;
      }

      public SQLText getSQLText() {
         return this.sqlText;
      }

      public String getAlias() {
         return this.alias;
      }

      public boolean isPrimary() {
         return this.primary;
      }

      public int hashCode() {
         return this.sqlText.hashCode() ^ (this.alias != null ? this.alias.hashCode() : 0);
      }

      public boolean equals(Object other) {
         if (other != null && other instanceof SelectedItem) {
            SelectedItem otherItem = (SelectedItem)other;
            if (!this.sqlText.equals(otherItem.sqlText)) {
               return false;
            } else {
               return (this.alias == null || this.alias.equals(otherItem.alias)) && (otherItem.alias == null || otherItem.alias.equals(this.alias));
            }
         } else {
            return false;
         }
      }
   }
}
