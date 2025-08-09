package org.datanucleus.store.rdbms.query;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.QueryResultMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.datanucleus.util.TypeConversionHelper;

public class ResultMetaDataROF implements ResultObjectFactory {
   RDBMSStoreManager storeMgr;
   QueryResultMetaData queryResultMetaData = null;
   String[] columnNames = null;
   private boolean ignoreCache = false;

   public ResultMetaDataROF(RDBMSStoreManager storeMgr, QueryResultMetaData qrmd) {
      this.storeMgr = storeMgr;
      this.queryResultMetaData = qrmd;
   }

   public Object getObject(ExecutionContext ec, ResultSet rs) {
      List returnObjects = new ArrayList();
      if (this.columnNames == null) {
         try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            this.columnNames = new String[columnCount];

            for(int i = 0; i < columnCount; ++i) {
               String colName = rsmd.getColumnName(i + 1);
               String colLabel = rsmd.getColumnLabel(i + 1);
               this.columnNames[i] = StringUtils.isWhitespace(colLabel) ? colName : colLabel;
            }
         } catch (SQLException ex) {
            throw new NucleusDataStoreException("Error obtaining objects", ex);
         }
      }

      QueryResultMetaData.PersistentTypeMapping[] persistentTypes = this.queryResultMetaData.getPersistentTypeMappings();
      if (persistentTypes != null) {
         int startColumnIndex = 0;

         for(int i = 0; i < persistentTypes.length; ++i) {
            Set columnsInThisType = new HashSet();
            AbstractMemberMetaData[] fmds = new AbstractMemberMetaData[this.columnNames.length];
            Map fieldColumns = new HashMap();
            DatastoreClass dc = this.storeMgr.getDatastoreClass(persistentTypes[i].getClassName(), ec.getClassLoaderResolver());
            AbstractClassMetaData acmd = ec.getMetaDataManager().getMetaDataForClass(persistentTypes[i].getClassName(), ec.getClassLoaderResolver());
            Object id = null;

            for(int j = startColumnIndex; j < this.columnNames.length; ++j) {
               if (columnsInThisType.contains(this.columnNames[j])) {
                  startColumnIndex = j;
                  break;
               }

               boolean found = false;
               if (acmd.getIdentityType() == IdentityType.DATASTORE) {
                  Column df = dc.getDatastoreIdMapping().getDatastoreMapping(0).getColumn();
                  if (df.getIdentifier().getName().equalsIgnoreCase(this.columnNames[j])) {
                     int datastoreIdentityExpressionIndex = j + 1;
                     if (dc.getDatastoreIdMapping() != null) {
                        id = dc.getDatastoreIdMapping().getObject(ec, rs, new int[]{datastoreIdentityExpressionIndex});
                     }

                     found = true;
                  }
               }

               for(int k = 0; k < acmd.getNoOfManagedMembers() + acmd.getNoOfInheritedManagedMembers() && !found; ++k) {
                  AbstractMemberMetaData apmd = acmd.getMetaDataForManagedMemberAtAbsolutePosition(k);
                  if (persistentTypes[i].getColumnForField(apmd.getName()) != null) {
                     if (persistentTypes[i].getColumnForField(apmd.getName()).equalsIgnoreCase(this.columnNames[j])) {
                        fieldColumns.put(this.columnNames[j], apmd);
                        columnsInThisType.add(this.columnNames[j]);
                        fmds[j] = apmd;
                        found = true;
                     }
                  } else {
                     JavaTypeMapping mapping = dc.getMemberMapping(apmd);

                     for(int l = 0; l < mapping.getDatastoreMappings().length && !found; ++l) {
                        Column df = mapping.getDatastoreMapping(l).getColumn();
                        if (df.getIdentifier().getName().equalsIgnoreCase(this.columnNames[j])) {
                           fieldColumns.put(this.columnNames[j], apmd);
                           columnsInThisType.add(this.columnNames[j]);
                           fmds[j] = apmd;
                           found = true;
                        }
                     }
                  }
               }

               if (!columnsInThisType.contains(this.columnNames[j])) {
                  startColumnIndex = j;
                  break;
               }
            }

            StatementMappingIndex[] stmtMappings = new StatementMappingIndex[acmd.getNoOfManagedMembers() + acmd.getNoOfInheritedManagedMembers()];
            Set fields = new HashSet();
            fields.addAll(fieldColumns.values());
            int[] fieldNumbers = new int[fields.size()];
            Iterator it = fields.iterator();

            for(int j = 0; it.hasNext(); ++j) {
               AbstractMemberMetaData apmd = (AbstractMemberMetaData)it.next();
               StatementMappingIndex stmtMapping = new StatementMappingIndex(dc.getMemberMapping(apmd));
               fieldNumbers[j] = apmd.getAbsoluteFieldNumber();
               List indexes = new ArrayList();

               for(int k = 0; k < fmds.length; ++k) {
                  if (fmds[k] == apmd) {
                     indexes.add(k);
                  }
               }

               int[] indxs = new int[indexes.size()];

               for(int k = 0; k < indxs.length; ++k) {
                  indxs[k] = (Integer)indexes.get(k) + 1;
               }

               stmtMapping.setColumnPositions(indxs);
               stmtMappings[fieldNumbers[j]] = stmtMapping;
            }

            Object obj = null;
            Class type = ec.getClassLoaderResolver().classForName(persistentTypes[i].getClassName());
            if (acmd.getIdentityType() == IdentityType.APPLICATION) {
               obj = this.getObjectForApplicationId(ec, rs, fieldNumbers, acmd, type, false, stmtMappings);
            } else if (acmd.getIdentityType() == IdentityType.DATASTORE) {
               obj = this.getObjectForDatastoreId(ec, rs, fieldNumbers, acmd, id, type, stmtMappings);
            }

            returnObjects.add(obj);
         }
      }

      String[] columns = this.queryResultMetaData.getScalarColumns();
      if (columns != null) {
         for(int i = 0; i < columns.length; ++i) {
            try {
               Object obj = this.getResultObject(rs, columns[i]);
               returnObjects.add(obj);
            } catch (SQLException sqe) {
               String msg = Localiser.msg("059027", new Object[]{sqe.getMessage()});
               NucleusLogger.QUERY.error(msg);
               throw new NucleusUserException(msg, sqe);
            }
         }
      }

      QueryResultMetaData.ConstructorTypeMapping[] ctrTypeMappings = this.queryResultMetaData.getConstructorTypeMappings();
      if (ctrTypeMappings != null) {
         for(int i = 0; i < ctrTypeMappings.length; ++i) {
            String ctrClassName = ctrTypeMappings[i].getClassName();
            Class ctrCls = ec.getClassLoaderResolver().classForName(ctrClassName);
            List<QueryResultMetaData.ConstructorTypeColumn> ctrColumns = ctrTypeMappings[i].getColumnsForConstructor();
            Class[] ctrArgTypes = null;
            Object[] ctrArgVals = null;
            if (ctrColumns != null && ctrColumns.size() > 0) {
               int j = 0;
               ctrArgTypes = new Class[ctrColumns.size()];
               ctrArgVals = new Object[ctrColumns.size()];

               for(QueryResultMetaData.ConstructorTypeColumn ctrCol : ctrColumns) {
                  try {
                     Object colVal = this.getResultObject(rs, ctrCol.getColumnName());
                     ctrArgTypes[j] = colVal.getClass();
                     if (ctrCol.getJavaType() != null) {
                        ctrArgTypes[j] = ctrCol.getJavaType();
                        ctrArgVals[j] = TypeConversionHelper.convertTo(colVal, ctrArgTypes[j]);
                     } else {
                        ctrArgTypes[j] = colVal.getClass();
                        ctrArgVals[j] = colVal;
                     }
                  } catch (SQLException var23) {
                  }

                  ++j;
               }
            }

            returnObjects.add(ClassUtils.newInstance(ctrCls, ctrArgTypes, ctrArgVals));
         }
      }

      if (returnObjects.size() == 0) {
         return null;
      } else {
         return returnObjects.size() == 1 ? returnObjects.get(0) : returnObjects.toArray(new Object[returnObjects.size()]);
      }
   }

   private Object getResultObject(ResultSet rs, String columnName) throws SQLException {
      return rs.getObject(columnName);
   }

   private Object getObjectForApplicationId(ExecutionContext ec, final ResultSet rs, final int[] fieldNumbers, AbstractClassMetaData cmd, Class pcClass, boolean requiresInheritanceCheck, StatementMappingIndex[] stmtMappings) {
      final StatementClassMapping resultMappings = new StatementClassMapping();

      for(int i = 0; i < fieldNumbers.length; ++i) {
         resultMappings.addMappingForMember(fieldNumbers[i], stmtMappings[fieldNumbers[i]]);
      }

      Object id = IdentityUtils.getApplicationIdentityForResultSetRow(ec, cmd, (Class)null, requiresInheritanceCheck, this.storeMgr.getFieldManagerForResultProcessing(ec, rs, resultMappings, cmd));
      return ec.findObject(id, new FieldValues() {
         public void fetchFields(ObjectProvider sm) {
            FieldManager fm = ResultMetaDataROF.this.storeMgr.getFieldManagerForResultProcessing(sm, rs, resultMappings);
            sm.replaceFields(fieldNumbers, fm, false);
         }

         public void fetchNonLoadedFields(ObjectProvider sm) {
            FieldManager fm = ResultMetaDataROF.this.storeMgr.getFieldManagerForResultProcessing(sm, rs, resultMappings);
            sm.replaceNonLoadedFields(fieldNumbers, fm);
         }

         public FetchPlan getFetchPlanForLoading() {
            return null;
         }
      }, pcClass, this.ignoreCache, false);
   }

   private Object getObjectForDatastoreId(final ExecutionContext ec, final ResultSet rs, final int[] fieldNumbers, AbstractClassMetaData cmd, Object oid, Class pcClass, StatementMappingIndex[] stmtMappings) {
      final StatementClassMapping resultMappings = new StatementClassMapping();

      for(int i = 0; i < fieldNumbers.length; ++i) {
         resultMappings.addMappingForMember(fieldNumbers[i], stmtMappings[fieldNumbers[i]]);
      }

      return ec.findObject(oid, new FieldValues() {
         public void fetchFields(ObjectProvider sm) {
            FieldManager fm = ResultMetaDataROF.this.storeMgr.getFieldManagerForResultProcessing(sm, rs, resultMappings);
            sm.replaceFields(fieldNumbers, fm, false);
         }

         public void fetchNonLoadedFields(ObjectProvider sm) {
            FieldManager fm = ResultMetaDataROF.this.storeMgr.getFieldManagerForResultProcessing(sm, rs, resultMappings);
            sm.replaceNonLoadedFields(fieldNumbers, fm);
         }

         public FetchPlan getFetchPlanForLoading() {
            return ec.getFetchPlan();
         }
      }, pcClass, this.ignoreCache, false);
   }
}
