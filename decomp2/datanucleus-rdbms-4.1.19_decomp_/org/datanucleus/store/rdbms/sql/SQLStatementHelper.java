package org.datanucleus.store.rdbms.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.JoinMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.DiscriminatorLongMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableIdMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.DatastoreElementContainer;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.PersistableJoinTable;
import org.datanucleus.store.rdbms.table.SecondaryDatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.NucleusLogger;

public class SQLStatementHelper {
   public static PreparedStatement getPreparedStatementForSQLStatement(SQLStatement sqlStmt, ExecutionContext ec, ManagedConnection mconn, String resultSetType, String resultSetConcurrency) throws SQLException {
      SQLText sqlText = sqlStmt.getSelectStatement();
      SQLController sqlControl = sqlStmt.getRDBMSManager().getSQLController();
      PreparedStatement ps = sqlControl.getStatementForQuery(mconn, sqlText.toString(), resultSetType, resultSetConcurrency);
      boolean done = false;

      try {
         sqlText.applyParametersToStatement(ec, ps);
         done = true;
      } finally {
         if (!done) {
            sqlControl.closeStatement(mconn, ps);
         }

      }

      return ps;
   }

   public static void applyParametersToStatement(PreparedStatement ps, ExecutionContext ec, List parameters, Map paramNameByPosition, Map paramValuesByName) {
      if (parameters != null) {
         int num = 1;
         Map<String, Integer> paramNumberByName = null;
         int nextParamNumber = 0;

         for(SQLStatementParameter param : parameters) {
            JavaTypeMapping mapping = param.getMapping();
            RDBMSStoreManager storeMgr = mapping.getStoreManager();
            Object value = null;
            if (paramNumberByName != null) {
               Integer position = (Integer)paramNumberByName.get("" + param.getName());
               if (position == null) {
                  value = paramValuesByName.get(nextParamNumber);
                  paramNumberByName.put(param.getName(), nextParamNumber);
                  ++nextParamNumber;
               } else {
                  value = paramValuesByName.get(position);
               }
            } else if (paramValuesByName.containsKey(param.getName())) {
               value = paramValuesByName.get(param.getName());
            } else if (paramNameByPosition == null) {
               try {
                  value = paramValuesByName.get(Integer.valueOf(param.getName()));
               } catch (NumberFormatException var18) {
                  value = paramValuesByName.get(nextParamNumber);
                  paramNumberByName = new HashMap();
                  paramNumberByName.put(param.getName(), nextParamNumber);
                  ++nextParamNumber;
               }
            } else {
               int paramPosition = -1;
               Set<String> paramNamesEncountered = new HashSet();

               for(Map.Entry entry : paramNameByPosition.entrySet()) {
                  String paramName = (String)entry.getValue();
                  if (!paramNamesEncountered.contains(paramName)) {
                     ++paramPosition;
                     paramNamesEncountered.add(paramName);
                  }

                  if (paramName.equals(param.getName())) {
                     value = paramValuesByName.get(paramPosition);
                     break;
                  }
               }

               paramNamesEncountered.clear();
               paramNamesEncountered = null;
            }

            AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(mapping.getType(), ec.getClassLoaderResolver());
            if (param.getColumnNumber() >= 0 && cmd != null) {
               Object colValue = null;
               if (value != null) {
                  if (cmd.getIdentityType() == IdentityType.DATASTORE) {
                     colValue = mapping.getValueForDatastoreMapping(ec.getNucleusContext(), param.getColumnNumber(), value);
                  } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                     colValue = getValueForPrimaryKeyIndexOfObjectUsingReflection(value, param.getColumnNumber(), cmd, storeMgr, ec.getClassLoaderResolver());
                  }
               }

               mapping.getDatastoreMapping(param.getColumnNumber()).setObject(ps, num, colValue);
            } else if (ec.getApiAdapter().isPersistable(value)) {
               if (!ec.getApiAdapter().isPersistent(value) && !ec.getApiAdapter().isDetached(value)) {
                  mapping.setObject(ec, ps, MappingHelper.getMappingIndices(num, mapping), (Object)null);
               } else if (ec.getApiAdapter().isDetached(value)) {
                  Object id = ec.getApiAdapter().getIdForObject(value);
                  PersistableIdMapping idMapping = new PersistableIdMapping((PersistableMapping)mapping);
                  idMapping.setObject(ec, ps, MappingHelper.getMappingIndices(num, idMapping), id);
               } else {
                  mapping.setObject(ec, ps, MappingHelper.getMappingIndices(num, mapping), value);
               }
            } else if (mapping.getNumberOfDatastoreMappings() == 1) {
               mapping.setObject(ec, ps, MappingHelper.getMappingIndices(num, mapping), value);
            } else if (mapping.getNumberOfDatastoreMappings() > 1 && param.getColumnNumber() == mapping.getNumberOfDatastoreMappings() - 1) {
               mapping.setObject(ec, ps, MappingHelper.getMappingIndices(num - mapping.getNumberOfDatastoreMappings() + 1, mapping), value);
            }

            ++num;
         }
      }

   }

   public static Object getValueForPrimaryKeyIndexOfObjectUsingReflection(Object value, int index, AbstractClassMetaData cmd, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         throw new NucleusException("This method does not support datastore-identity");
      } else {
         int position = 0;
         int[] pkPositions = cmd.getPKMemberPositions();

         for(int i = 0; i < pkPositions.length; ++i) {
            AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkPositions[i]);
            Object memberValue = null;
            if (mmd instanceof FieldMetaData) {
               memberValue = ClassUtils.getValueOfFieldByReflection(value, mmd.getName());
            } else {
               memberValue = ClassUtils.getValueOfMethodByReflection(value, ClassUtils.getJavaBeanGetterName(mmd.getName(), false), new Object[0]);
            }

            if (storeMgr.getApiAdapter().isPersistable(mmd.getType())) {
               AbstractClassMetaData subCmd = storeMgr.getMetaDataManager().getMetaDataForClass(mmd.getType(), clr);
               DatastoreClass subTable = storeMgr.getDatastoreClass(mmd.getTypeName(), clr);
               JavaTypeMapping subMapping = subTable.getIdMapping();
               Object subValue = getValueForPrimaryKeyIndexOfObjectUsingReflection(memberValue, index - position, subCmd, storeMgr, clr);
               if (index < position + subMapping.getNumberOfDatastoreMappings()) {
                  return subValue;
               }

               position += subMapping.getNumberOfDatastoreMappings();
            } else {
               if (position == index) {
                  if (mmd instanceof FieldMetaData) {
                     return ClassUtils.getValueOfFieldByReflection(value, mmd.getName());
                  }

                  return ClassUtils.getValueOfMethodByReflection(value, ClassUtils.getJavaBeanGetterName(mmd.getName(), false), new Object[0]);
               }

               ++position;
            }
         }

         return null;
      }
   }

   public static SQLTable getSQLTableForMappingOfTable(SQLStatement stmt, SQLTable sqlTbl, JavaTypeMapping mapping) {
      Table table = sqlTbl.getTable();
      if (!(table instanceof SecondaryDatastoreClass) && !(table instanceof JoinTable)) {
         DatastoreClass sourceTbl = (DatastoreClass)sqlTbl.getTable();
         DatastoreClass mappingTbl = null;
         if (mapping.getTable() != null) {
            mappingTbl = (DatastoreClass)mapping.getTable();
         } else {
            mappingTbl = sourceTbl.getBaseDatastoreClassWithMember(mapping.getMemberMetaData());
         }

         if (mappingTbl == sourceTbl) {
            return sqlTbl;
         } else {
            SQLTable mappingSqlTbl = stmt.getTable(mappingTbl, sqlTbl.getGroupName());
            if (mappingSqlTbl == null) {
               boolean forceLeftOuter = false;
               SQLTableGroup tableGrp = stmt.getTableGroup(sqlTbl.getGroupName());
               if (tableGrp.getJoinType() == SQLJoin.JoinType.LEFT_OUTER_JOIN) {
                  forceLeftOuter = true;
               }

               if (mappingTbl instanceof SecondaryDatastoreClass) {
                  boolean innerJoin = true;
                  JoinMetaData joinmd = ((SecondaryDatastoreClass)mappingTbl).getJoinMetaData();
                  if (joinmd != null && joinmd.isOuter() && !forceLeftOuter) {
                     innerJoin = false;
                  }

                  if (innerJoin && !forceLeftOuter) {
                     mappingSqlTbl = stmt.innerJoin(sqlTbl, sqlTbl.getTable().getIdMapping(), mappingTbl, (String)null, mappingTbl.getIdMapping(), (Object[])null, sqlTbl.getGroupName());
                  } else {
                     mappingSqlTbl = stmt.leftOuterJoin(sqlTbl, sqlTbl.getTable().getIdMapping(), mappingTbl, (String)null, mappingTbl.getIdMapping(), (Object[])null, sqlTbl.getGroupName());
                  }
               } else if (forceLeftOuter) {
                  mappingSqlTbl = stmt.leftOuterJoin(sqlTbl, sqlTbl.getTable().getIdMapping(), mappingTbl, (String)null, mappingTbl.getIdMapping(), (Object[])null, sqlTbl.getGroupName());
               } else {
                  mappingSqlTbl = stmt.innerJoin(sqlTbl, sqlTbl.getTable().getIdMapping(), mappingTbl, (String)null, mappingTbl.getIdMapping(), (Object[])null, sqlTbl.getGroupName());
               }
            }

            return mappingSqlTbl;
         }
      } else {
         if (mapping.getTable() != null) {
            SQLTable mappingSqlTbl = stmt.getTable(mapping.getTable(), sqlTbl.getGroupName());
            if (mappingSqlTbl != null) {
               return mappingSqlTbl;
            }
         }

         return sqlTbl;
      }
   }

   public static void selectIdentityOfCandidateInStatement(SQLStatement stmt, StatementClassMapping mappingDefinition, AbstractClassMetaData candidateCmd) {
      DatastoreClass candidateTbl = (DatastoreClass)stmt.getPrimaryTable().getTable();
      if (candidateCmd.getIdentityType() == IdentityType.DATASTORE) {
         JavaTypeMapping idMapping = candidateTbl.getDatastoreIdMapping();
         int[] colNumbers = stmt.select(stmt.getPrimaryTable(), idMapping, "DN_DATASTOREID", false);
         if (mappingDefinition != null) {
            StatementMappingIndex datastoreIdIdx = new StatementMappingIndex(idMapping);
            datastoreIdIdx.setColumnPositions(colNumbers);
            mappingDefinition.addMappingForMember(-1, datastoreIdIdx);
         }
      } else if (candidateCmd.getIdentityType() == IdentityType.APPLICATION) {
         int[] pkPositions = candidateCmd.getPKMemberPositions();
         String alias = "DN_APPID";

         for(int i = 0; i < pkPositions.length; ++i) {
            AbstractMemberMetaData pkMmd = candidateCmd.getMetaDataForManagedMemberAtAbsolutePosition(pkPositions[i]);
            JavaTypeMapping pkMapping = candidateTbl.getMemberMapping(pkMmd);
            SQLTable sqlTbl = getSQLTableForMappingOfTable(stmt, stmt.getPrimaryTable(), pkMapping);
            if (pkPositions.length > 1) {
               alias = "DN_APPID" + i;
            }

            int[] colNumbers = stmt.select(sqlTbl, pkMapping, alias, false);
            if (mappingDefinition != null) {
               StatementMappingIndex appIdIdx = new StatementMappingIndex(pkMapping);
               appIdIdx.setColumnPositions(colNumbers);
               mappingDefinition.addMappingForMember(pkPositions[i], appIdIdx);
            }
         }
      }

      JavaTypeMapping verMapping = candidateTbl.getVersionMapping(true);
      if (verMapping != null) {
         SQLTable versionSqlTbl = getSQLTableForMappingOfTable(stmt, stmt.getPrimaryTable(), verMapping);
         int[] colNumbers = stmt.select(versionSqlTbl, verMapping, "DN_VERSION", false);
         if (mappingDefinition != null) {
            StatementMappingIndex versionIdx = new StatementMappingIndex(verMapping);
            versionIdx.setColumnPositions(colNumbers);
            mappingDefinition.addMappingForMember(-2, versionIdx);
         }
      }

      JavaTypeMapping discrimMapping = candidateTbl.getDiscriminatorMapping(true);
      if (discrimMapping != null) {
         SQLTable discrimSqlTbl = getSQLTableForMappingOfTable(stmt, stmt.getPrimaryTable(), discrimMapping);
         int[] colNumbers = stmt.select(discrimSqlTbl, discrimMapping, "DN_DISCRIM", false);
         if (mappingDefinition != null) {
            StatementMappingIndex discrimIdx = new StatementMappingIndex(discrimMapping);
            discrimIdx.setColumnPositions(colNumbers);
            mappingDefinition.addMappingForMember(-3, discrimIdx);
         }
      }

      List<SQLStatement> unionStmts = stmt.getUnions();
      if (unionStmts != null) {
         for(SQLStatement unionStmt : unionStmts) {
            selectIdentityOfCandidateInStatement(unionStmt, (StatementClassMapping)null, candidateCmd);
         }
      }

   }

   public static void selectFetchPlanOfCandidateInStatement(SQLStatement stmt, StatementClassMapping mappingDefinition, AbstractClassMetaData candidateCmd, FetchPlan fetchPlan, int maxFetchDepth) {
      selectFetchPlanOfSourceClassInStatement(stmt, mappingDefinition, fetchPlan, stmt.getPrimaryTable(), candidateCmd, maxFetchDepth);
   }

   public static void selectFetchPlanOfSourceClassInStatement(SQLStatement stmt, StatementClassMapping mappingDefinition, FetchPlan fetchPlan, SQLTable sourceSqlTbl, AbstractClassMetaData sourceCmd, int maxFetchDepth) {
      selectFetchPlanOfSourceClassInStatement(stmt, mappingDefinition, fetchPlan, sourceSqlTbl, sourceCmd, maxFetchDepth, (SQLJoin.JoinType)null);
   }

   public static void selectFetchPlanOfSourceClassInStatement(SQLStatement stmt, StatementClassMapping mappingDefinition, FetchPlan fetchPlan, SQLTable sourceSqlTbl, AbstractClassMetaData sourceCmd, int maxFetchDepth, SQLJoin.JoinType inputJoinType) {
      DatastoreClass sourceTbl = (DatastoreClass)sourceSqlTbl.getTable();
      int[] fieldNumbers;
      if (fetchPlan != null) {
         fieldNumbers = fetchPlan.getFetchPlanForClass(sourceCmd).getMemberNumbers();
      } else {
         fieldNumbers = sourceCmd.getDFGMemberPositions();
      }

      ClassLoaderResolver clr = stmt.getRDBMSManager().getNucleusContext().getClassLoaderResolver((ClassLoader)null);

      for(int i = 0; i < fieldNumbers.length; ++i) {
         AbstractMemberMetaData mmd = sourceCmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumbers[i]);
         selectMemberOfSourceInStatement(stmt, mappingDefinition, fetchPlan, sourceSqlTbl, mmd, clr, maxFetchDepth, inputJoinType);
      }

      if (sourceCmd.getIdentityType() == IdentityType.DATASTORE) {
         JavaTypeMapping idMapping = sourceTbl.getDatastoreIdMapping();
         int[] colNumbers = stmt.select(sourceSqlTbl, (JavaTypeMapping)idMapping, (String)null);
         if (mappingDefinition != null) {
            StatementMappingIndex datastoreIdIdx = new StatementMappingIndex(idMapping);
            datastoreIdIdx.setColumnPositions(colNumbers);
            mappingDefinition.addMappingForMember(-1, datastoreIdIdx);
         }
      }

      JavaTypeMapping verMapping = sourceTbl.getVersionMapping(true);
      if (verMapping != null) {
         SQLTable versionSqlTbl = getSQLTableForMappingOfTable(stmt, sourceSqlTbl, verMapping);
         int[] colNumbers = stmt.select(versionSqlTbl, (JavaTypeMapping)verMapping, (String)null);
         if (mappingDefinition != null) {
            StatementMappingIndex versionIdx = new StatementMappingIndex(verMapping);
            versionIdx.setColumnPositions(colNumbers);
            mappingDefinition.addMappingForMember(-2, versionIdx);
         }
      }

      JavaTypeMapping discrimMapping = sourceTbl.getDiscriminatorMapping(true);
      if (discrimMapping != null) {
         SQLTable discrimSqlTbl = getSQLTableForMappingOfTable(stmt, sourceSqlTbl, discrimMapping);
         int[] colNumbers = stmt.select(discrimSqlTbl, (JavaTypeMapping)discrimMapping, (String)null);
         if (mappingDefinition != null) {
            StatementMappingIndex discrimIdx = new StatementMappingIndex(discrimMapping);
            discrimIdx.setColumnPositions(colNumbers);
            mappingDefinition.addMappingForMember(-3, discrimIdx);
         }
      }

   }

   public static void selectMemberOfSourceInStatement(SQLStatement stmt, StatementClassMapping mappingDefinition, FetchPlan fetchPlan, SQLTable sourceSqlTbl, AbstractMemberMetaData mmd, ClassLoaderResolver clr, int maxFetchPlanLimit, SQLJoin.JoinType inputJoinType) {
      boolean selectSubobjects = false;
      if (maxFetchPlanLimit > 0) {
         selectSubobjects = true;
      }

      String tableGroupName = sourceSqlTbl.getGroupName() + "." + mmd.getName();
      JavaTypeMapping m = sourceSqlTbl.getTable().getMemberMapping(mmd);
      if (m != null && m.includeInFetchStatement()) {
         RelationType relationType = mmd.getRelationType(clr);
         RDBMSStoreManager storeMgr = stmt.getRDBMSManager();
         DatastoreAdapter dba = storeMgr.getDatastoreAdapter();
         if (!dba.validToSelectMappingInStatement(stmt, m)) {
            return;
         }

         MetaDataManager mmgr = storeMgr.getMetaDataManager();
         StatementMappingIndex stmtMapping = new StatementMappingIndex(m);
         if (m.getNumberOfDatastoreMappings() > 0) {
            SQLTable sqlTbl = getSQLTableForMappingOfTable(stmt, sourceSqlTbl, m);
            boolean selectFK = true;
            if (selectSubobjects && (relationType == RelationType.ONE_TO_ONE_UNI || relationType == RelationType.ONE_TO_ONE_BI && mmd.getMappedBy() == null) && !mmd.isSerialized() && !mmd.isEmbedded()) {
               selectFK = selectFetchPlanFieldsOfFKRelatedObject(stmt, mappingDefinition, fetchPlan, sourceSqlTbl, mmd, clr, maxFetchPlanLimit, m, tableGroupName, stmtMapping, sqlTbl, inputJoinType);
            } else if (selectSubobjects && !mmd.isEmbedded() && !mmd.isSerialized() && relationType == RelationType.MANY_TO_ONE_BI) {
               AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(clr);
               if (mmd.getJoinMetaData() == null && relatedMmds[0].getJoinMetaData() == null) {
                  selectFK = selectFetchPlanFieldsOfFKRelatedObject(stmt, mappingDefinition, fetchPlan, sourceSqlTbl, mmd, clr, maxFetchPlanLimit, m, tableGroupName, stmtMapping, sqlTbl, inputJoinType);
               } else {
                  Table joinTable = storeMgr.getTable(relatedMmds[0]);
                  DatastoreElementContainer collTable = (DatastoreElementContainer)joinTable;
                  JavaTypeMapping selectMapping = collTable.getOwnerMapping();
                  SQLTable joinSqlTbl = null;
                  if (stmt.getPrimaryTable().getTable() != joinTable) {
                     JavaTypeMapping referenceMapping = collTable.getElementMapping();
                     joinSqlTbl = stmt.leftOuterJoin(sourceSqlTbl, sourceSqlTbl.getTable().getIdMapping(), collTable, (String)null, referenceMapping, (Object[])null, tableGroupName);
                  } else {
                     joinSqlTbl = stmt.getPrimaryTable();
                  }

                  int[] colNumbers = stmt.select(joinSqlTbl, (JavaTypeMapping)selectMapping, (String)null);
                  stmtMapping.setColumnPositions(colNumbers);
               }
            }

            if (selectFK) {
               int[] colNumbers = stmt.select(sqlTbl, (JavaTypeMapping)m, (String)null);
               stmtMapping.setColumnPositions(colNumbers);
            }
         } else if (relationType == RelationType.ONE_TO_ONE_BI && mmd.getMappedBy() != null) {
            AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(clr);
            AbstractMemberMetaData relatedMmd = relatedMmds[0];
            String[] clsNames = null;
            if (mmd.getType().isInterface()) {
               if (mmd.getFieldTypes() != null && mmd.getFieldTypes().length == 1) {
                  Class fldTypeCls = clr.classForName(mmd.getFieldTypes()[0]);
                  if (fldTypeCls.isInterface()) {
                     clsNames = mmgr.getClassesImplementingInterface(mmd.getFieldTypes()[0], clr);
                  } else {
                     clsNames = new String[]{mmd.getFieldTypes()[0]};
                  }
               }

               if (clsNames == null) {
                  clsNames = mmgr.getClassesImplementingInterface(mmd.getTypeName(), clr);
               }
            } else {
               clsNames = new String[]{mmd.getTypeName()};
            }

            DatastoreClass relatedTbl = storeMgr.getDatastoreClass(clsNames[0], clr);
            JavaTypeMapping relatedMapping = relatedTbl.getMemberMapping(relatedMmd);
            JavaTypeMapping relatedDiscrimMapping = relatedTbl.getDiscriminatorMapping(true);
            Object[] discrimValues = null;
            JavaTypeMapping relatedTypeMapping = null;
            AbstractClassMetaData relatedCmd = relatedMmd.getAbstractClassMetaData();
            if (relatedDiscrimMapping != null && (relatedCmd.getSuperAbstractClassMetaData() != null || !relatedCmd.getFullClassName().equals(mmd.getTypeName()))) {
               List discValueList = null;

               for(int i = 0; i < clsNames.length; ++i) {
                  List values = getDiscriminatorValuesForMember(clsNames[i], relatedDiscrimMapping, storeMgr, clr);
                  if (discValueList == null) {
                     discValueList = values;
                  } else {
                     discValueList.addAll(values);
                  }
               }

               if (discValueList != null) {
                  discrimValues = discValueList.toArray(new Object[discValueList.size()]);
               }
            } else if (relatedTbl != relatedMapping.getTable()) {
               relatedTypeMapping = relatedTbl.getIdMapping();
            }

            SQLTable relatedSqlTbl = null;
            if (relatedTypeMapping == null) {
               SQLJoin.JoinType joinType = getJoinTypeForOneToOneRelationJoin(sourceSqlTbl.getTable().getIdMapping(), sourceSqlTbl, inputJoinType);
               if (joinType == SQLJoin.JoinType.LEFT_OUTER_JOIN || joinType == SQLJoin.JoinType.RIGHT_OUTER_JOIN) {
                  inputJoinType = joinType;
               }

               relatedSqlTbl = addJoinForOneToOneRelation(stmt, sourceSqlTbl.getTable().getIdMapping(), sourceSqlTbl, relatedMapping, relatedTbl, (String)null, discrimValues, tableGroupName, joinType);
               int[] colNumbers = stmt.select(relatedSqlTbl, (JavaTypeMapping)relatedTbl.getIdMapping(), (String)null);
               stmtMapping.setColumnPositions(colNumbers);
            } else {
               DatastoreClass relationTbl = (DatastoreClass)relatedMapping.getTable();
               if (relatedTbl != relatedMapping.getTable()) {
                  if (relatedMapping.isNullable()) {
                     relatedSqlTbl = stmt.leftOuterJoin(sourceSqlTbl, sourceSqlTbl.getTable().getIdMapping(), relatedMapping.getTable(), (String)null, relatedMapping, (Object[])null, tableGroupName);
                     relatedSqlTbl = stmt.innerJoin(relatedSqlTbl, relatedMapping.getTable().getIdMapping(), relatedTbl, (String)null, relatedTbl.getIdMapping(), (Object[])null, tableGroupName);
                  } else {
                     relatedSqlTbl = stmt.innerJoin(sourceSqlTbl, sourceSqlTbl.getTable().getIdMapping(), relatedMapping.getTable(), (String)null, relatedMapping, (Object[])null, tableGroupName);
                     relatedSqlTbl = stmt.innerJoin(relatedSqlTbl, relatedMapping.getTable().getIdMapping(), relatedTbl, (String)null, relatedTbl.getIdMapping(), (Object[])null, tableGroupName);
                  }
               } else {
                  SQLJoin.JoinType joinType = getJoinTypeForOneToOneRelationJoin(sourceSqlTbl.getTable().getIdMapping(), sourceSqlTbl, inputJoinType);
                  if (joinType == SQLJoin.JoinType.LEFT_OUTER_JOIN || joinType == SQLJoin.JoinType.RIGHT_OUTER_JOIN) {
                     inputJoinType = joinType;
                  }

                  relatedSqlTbl = addJoinForOneToOneRelation(stmt, sourceSqlTbl.getTable().getIdMapping(), sourceSqlTbl, relatedMapping, relationTbl, (String)null, (Object[])null, tableGroupName, joinType);
               }

               relatedSqlTbl = getSQLTableForMappingOfTable(stmt, relatedSqlTbl, relatedTbl.getIdMapping());
               int[] colNumbers = stmt.select(relatedSqlTbl, (JavaTypeMapping)relatedTbl.getIdMapping(), (String)null);
               stmtMapping.setColumnPositions(colNumbers);
            }

            if (selectSubobjects && !mmd.isSerialized() && !mmd.isEmbedded()) {
               StatementClassMapping subMappingDefinition = new StatementClassMapping((String)null, mmd.getName());
               selectFetchPlanOfSourceClassInStatement(stmt, subMappingDefinition, fetchPlan, relatedSqlTbl, relatedMmd.getAbstractClassMetaData(), maxFetchPlanLimit - 1, inputJoinType);
               if (mappingDefinition != null) {
                  mappingDefinition.addMappingDefinitionForMember(mmd.getAbsoluteFieldNumber(), subMappingDefinition);
               }
            }
         } else if (relationType == RelationType.MANY_TO_ONE_BI) {
            AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(clr);
            if (mmd.getJoinMetaData() != null || relatedMmds[0].getJoinMetaData() != null) {
               Table joinTable = storeMgr.getTable(relatedMmds[0]);
               DatastoreElementContainer collTable = (DatastoreElementContainer)joinTable;
               JavaTypeMapping selectMapping = collTable.getOwnerMapping();
               SQLTable joinSqlTbl = null;
               if (stmt.getPrimaryTable().getTable() != joinTable) {
                  JavaTypeMapping referenceMapping = collTable.getElementMapping();
                  if (referenceMapping instanceof ReferenceMapping) {
                     ReferenceMapping refMap = (ReferenceMapping)referenceMapping;
                     Class implType = clr.classForName(mmd.getClassName(true));
                     referenceMapping = refMap.getJavaTypeMappingForType(implType);
                  }

                  joinSqlTbl = stmt.leftOuterJoin(sourceSqlTbl, sourceSqlTbl.getTable().getIdMapping(), collTable, (String)null, referenceMapping, (Object[])null, tableGroupName + "_JOIN");
               } else {
                  joinSqlTbl = stmt.getPrimaryTable();
               }

               int[] colNumbers = stmt.select(joinSqlTbl, (JavaTypeMapping)selectMapping, (String)null);
               stmtMapping.setColumnPositions(colNumbers);
            }
         } else if (relationType == RelationType.MANY_TO_ONE_UNI) {
            PersistableJoinTable joinTable = (PersistableJoinTable)storeMgr.getTable(mmd);
            SQLTable joinSqlTbl = stmt.leftOuterJoin(sourceSqlTbl, sourceSqlTbl.getTable().getIdMapping(), joinTable, (String)null, joinTable.getOwnerMapping(), (Object[])null, tableGroupName + "_JOIN");
            int[] colNumbers = stmt.select(joinSqlTbl, (JavaTypeMapping)joinTable.getRelatedMapping(), (String)null);
            stmtMapping.setColumnPositions(colNumbers);
         }

         if (mappingDefinition != null) {
            mappingDefinition.addMappingForMember(mmd.getAbsoluteFieldNumber(), stmtMapping);
         }
      }

   }

   private static boolean selectFetchPlanFieldsOfFKRelatedObject(SQLStatement stmt, StatementClassMapping mappingDefinition, FetchPlan fetchPlan, SQLTable sourceSqlTbl, AbstractMemberMetaData mmd, ClassLoaderResolver clr, int maxFetchPlanLimit, JavaTypeMapping m, String tableGroupName, StatementMappingIndex stmtMapping, SQLTable sqlTbl, SQLJoin.JoinType inputJoinType) {
      boolean selectFK = true;
      if (!mmd.fetchFKOnly()) {
         RDBMSStoreManager storeMgr = stmt.getRDBMSManager();
         Class type = mmd.getType();
         if (m instanceof ReferenceMapping) {
            ReferenceMapping refMapping = (ReferenceMapping)m;
            if (refMapping.getMappingStrategy() == 0) {
               JavaTypeMapping[] subMappings = refMapping.getJavaTypeMapping();
               if (subMappings != null && subMappings.length == 1) {
                  type = clr.classForName(refMapping.getJavaTypeMapping()[0].getType());
               }
            }
         }

         AbstractClassMetaData relatedCmd = storeMgr.getMetaDataManager().getMetaDataForClass(type, clr);
         if (relatedCmd != null) {
            if (relatedCmd.isEmbeddedOnly()) {
               return true;
            }

            if (relatedCmd.getBaseAbstractClassMetaData().getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
               Collection<String> relSubclassNames = storeMgr.getSubClassesForClass(relatedCmd.getFullClassName(), true, clr);
               if (relatedCmd.isMappedSuperclass() && relSubclassNames.size() > 1) {
                  return true;
               }

               if (!relatedCmd.isMappedSuperclass() && relSubclassNames.size() > 0) {
                  return true;
               }
            }

            DatastoreClass relatedTbl = storeMgr.getDatastoreClass(relatedCmd.getFullClassName(), clr);
            if (relatedTbl == null) {
               AbstractClassMetaData[] ownerParentCmds = storeMgr.getClassesManagingTableForClass(relatedCmd, clr);
               if (ownerParentCmds.length > 1) {
                  NucleusLogger.QUERY.info("Relation (" + mmd.getFullFieldName() + ") with multiple related tables (using subclass-table). Not supported so selecting FK of related object only");
                  return true;
               }

               relatedTbl = storeMgr.getDatastoreClass(ownerParentCmds[0].getFullClassName(), clr);
            }

            String requiredGroupName = null;
            if (sourceSqlTbl.getGroupName() != null) {
               requiredGroupName = sourceSqlTbl.getGroupName() + "." + mmd.getName();
            }

            SQLTable relatedSqlTbl = stmt.getTable(relatedTbl, requiredGroupName);
            if (relatedSqlTbl == null) {
               SQLJoin.JoinType joinType = getJoinTypeForOneToOneRelationJoin(m, sqlTbl, inputJoinType);
               if (joinType == SQLJoin.JoinType.LEFT_OUTER_JOIN || joinType == SQLJoin.JoinType.RIGHT_OUTER_JOIN) {
                  inputJoinType = joinType;
               }

               relatedSqlTbl = addJoinForOneToOneRelation(stmt, m, sqlTbl, relatedTbl.getIdMapping(), relatedTbl, (String)null, (Object[])null, tableGroupName, joinType);
            }

            StatementClassMapping subMappingDefinition = new StatementClassMapping(mmd.getClassName(), mmd.getName());
            selectFetchPlanOfSourceClassInStatement(stmt, subMappingDefinition, fetchPlan, relatedSqlTbl, relatedCmd, maxFetchPlanLimit - 1, inputJoinType);
            if (mappingDefinition != null) {
               if (relatedCmd.getIdentityType() != IdentityType.APPLICATION) {
                  if (relatedCmd.getIdentityType() == IdentityType.DATASTORE) {
                     StatementMappingIndex pkIdx = subMappingDefinition.getMappingForMemberPosition(-1);
                     selectFK = false;
                     stmtMapping.setColumnPositions(pkIdx.getColumnPositions());
                  }
               } else {
                  int[] pkFields = relatedCmd.getPKMemberPositions();
                  int[] pkCols = new int[m.getNumberOfDatastoreMappings()];
                  int pkColNo = 0;

                  for(int i = 0; i < pkFields.length; ++i) {
                     StatementMappingIndex pkIdx = subMappingDefinition.getMappingForMemberPosition(pkFields[i]);
                     int[] pkColNumbers = pkIdx.getColumnPositions();

                     for(int j = 0; j < pkColNumbers.length; ++j) {
                        pkCols[pkColNo] = pkColNumbers[j];
                        ++pkColNo;
                     }
                  }

                  selectFK = false;
                  stmtMapping.setColumnPositions(pkCols);
               }

               mappingDefinition.addMappingDefinitionForMember(mmd.getAbsoluteFieldNumber(), subMappingDefinition);
            }
         }
      }

      return selectFK;
   }

   public static SQLTable addJoinForOneToOneRelation(SQLStatement stmt, JavaTypeMapping sourceMapping, SQLTable sourceSqlTbl, JavaTypeMapping targetMapping, Table targetTable, String targetAlias, Object[] discrimValues, String targetTablegroupName, SQLJoin.JoinType joinType) {
      if (joinType == null) {
         joinType = getJoinTypeForOneToOneRelationJoin(sourceMapping, sourceSqlTbl, joinType);
      }

      SQLTable targetSqlTbl = null;
      if (joinType == SQLJoin.JoinType.LEFT_OUTER_JOIN) {
         targetSqlTbl = stmt.leftOuterJoin(sourceSqlTbl, sourceMapping, targetTable, targetAlias, targetMapping, discrimValues, targetTablegroupName);
      } else if (joinType == SQLJoin.JoinType.INNER_JOIN) {
         targetSqlTbl = stmt.innerJoin(sourceSqlTbl, sourceMapping, targetTable, targetAlias, targetMapping, discrimValues, targetTablegroupName);
      } else if (joinType == SQLJoin.JoinType.RIGHT_OUTER_JOIN) {
         targetSqlTbl = stmt.rightOuterJoin(sourceSqlTbl, sourceMapping, targetTable, targetAlias, targetMapping, discrimValues, targetTablegroupName);
      } else if (joinType == SQLJoin.JoinType.CROSS_JOIN) {
         targetSqlTbl = stmt.crossJoin(targetTable, targetAlias, targetTablegroupName);
      }

      return targetSqlTbl;
   }

   public static SQLJoin.JoinType getJoinTypeForOneToOneRelationJoin(JavaTypeMapping sourceMapping, SQLTable sourceSqlTbl, SQLJoin.JoinType joinType) {
      if (joinType == null) {
         joinType = SQLJoin.JoinType.LEFT_OUTER_JOIN;
         if (sourceMapping != sourceSqlTbl.getTable().getIdMapping()) {
            joinType = sourceMapping.isNullable() ? SQLJoin.JoinType.LEFT_OUTER_JOIN : SQLJoin.JoinType.INNER_JOIN;
         }
      }

      return joinType;
   }

   public static BooleanExpression getExpressionForDiscriminatorForClass(SQLStatement stmt, String className, DiscriminatorMetaData dismd, JavaTypeMapping discriminatorMapping, SQLTable discrimSqlTbl, ClassLoaderResolver clr) {
      Object discriminatorValue = className;
      if (dismd.getStrategy() == DiscriminatorStrategy.VALUE_MAP) {
         NucleusContext nucleusCtx = stmt.getRDBMSManager().getNucleusContext();
         AbstractClassMetaData targetCmd = nucleusCtx.getMetaDataManager().getMetaDataForClass(className, clr);
         String strValue = null;
         if (targetCmd.getInheritanceMetaData() != null && targetCmd.getInheritanceMetaData().getDiscriminatorMetaData() != null) {
            strValue = targetCmd.getInheritanceMetaData().getDiscriminatorMetaData().getValue();
         }

         if (strValue == null) {
            strValue = className;
         }

         if (discriminatorMapping instanceof DiscriminatorLongMapping) {
            try {
               discriminatorValue = Integer.valueOf(strValue);
            } catch (NumberFormatException var11) {
               throw new NucleusUserException("Discriminator for " + className + " is not integer-based but needs to be!");
            }
         } else {
            discriminatorValue = strValue;
         }
      }

      SQLExpression discrExpr = stmt.getSQLExpressionFactory().newExpression(stmt, discrimSqlTbl, discriminatorMapping);
      SQLExpression discrVal = stmt.getSQLExpressionFactory().newLiteral(stmt, discriminatorMapping, discriminatorValue);
      return discrExpr.eq(discrVal);
   }

   public static List getDiscriminatorValuesForMember(String className, JavaTypeMapping discMapping, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      List discrimValues = new ArrayList();
      DiscriminatorStrategy strategy = discMapping.getTable().getDiscriminatorMetaData().getStrategy();
      if (strategy == DiscriminatorStrategy.CLASS_NAME) {
         discrimValues.add(className);
         Collection<String> subclasses = storeMgr.getSubClassesForClass(className, true, clr);
         if (subclasses != null && subclasses.size() > 0) {
            discrimValues.addAll(subclasses);
         }
      } else if (strategy == DiscriminatorStrategy.VALUE_MAP) {
         MetaDataManager mmgr = storeMgr.getMetaDataManager();
         AbstractClassMetaData cmd = mmgr.getMetaDataForClass(className, clr);
         Collection<String> subclasses = storeMgr.getSubClassesForClass(className, true, clr);
         discrimValues.add(cmd.getInheritanceMetaData().getDiscriminatorMetaData().getValue());
         if (subclasses != null && subclasses.size() > 0) {
            for(String subclassName : subclasses) {
               AbstractClassMetaData subclassCmd = mmgr.getMetaDataForClass(subclassName, clr);
               discrimValues.add(subclassCmd.getInheritanceMetaData().getDiscriminatorMetaData().getValue());
            }
         }
      }

      return discrimValues;
   }
}
