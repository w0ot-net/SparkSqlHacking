package org.datanucleus.store.rdbms.scostore;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ArrayMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.query.PersistentClassROF;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.rdbms.query.StatementParameterMapping;
import org.datanucleus.store.rdbms.sql.DiscriminatorStatementGenerator;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class FKArrayStore extends AbstractArrayStore {
   private String clearNullifyStmt;
   private String updateFkStmt;

   public FKArrayStore(AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
      this.setOwner(mmd);
      ArrayMetaData arrmd = mmd.getArray();
      if (arrmd == null) {
         throw new NucleusUserException(Localiser.msg("056000", new Object[]{mmd.getFullFieldName()}));
      } else {
         this.elementType = mmd.getType().getComponentType().getName();
         Class element_class = clr.classForName(this.elementType);
         if (ClassUtils.isReferenceType(element_class)) {
            this.elementIsPersistentInterface = storeMgr.getNucleusContext().getMetaDataManager().isPersistentInterface(element_class.getName());
            if (this.elementIsPersistentInterface) {
               this.emd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForInterface(element_class, clr);
            } else {
               this.emd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForImplementationOfReference(element_class, (Object)null, clr);
            }
         } else {
            this.emd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(element_class, clr);
         }

         if (this.emd == null) {
            throw new NucleusUserException(Localiser.msg("056003", new Object[]{element_class.getName(), mmd.getFullFieldName()}));
         } else {
            this.elementInfo = this.getElementInformationForClass();
            if (this.elementInfo != null && this.elementInfo.length != 0) {
               if (this.elementInfo != null && this.elementInfo.length > 1) {
                  throw new NucleusUserException(Localiser.msg("056045", new Object[]{this.ownerMemberMetaData.getFullFieldName()}));
               } else {
                  this.elementMapping = this.elementInfo[0].getDatastoreClass().getIdMapping();
                  this.elementsAreEmbedded = false;
                  this.elementsAreSerialised = false;
                  String mappedByFieldName = mmd.getMappedBy();
                  if (mappedByFieldName != null) {
                     AbstractClassMetaData eoCmd = storeMgr.getMetaDataManager().getMetaDataForClass(element_class, clr);
                     AbstractMemberMetaData eofmd = eoCmd != null ? eoCmd.getMetaDataForMember(mappedByFieldName) : null;
                     if (eofmd == null) {
                        throw new NucleusUserException(Localiser.msg("056024", new Object[]{mmd.getFullFieldName(), mappedByFieldName, element_class.getName()}));
                     }

                     if (!clr.isAssignableFrom(eofmd.getType(), mmd.getAbstractClassMetaData().getFullClassName())) {
                        throw new NucleusUserException(Localiser.msg("056025", new Object[]{mmd.getFullFieldName(), eofmd.getFullFieldName(), eofmd.getTypeName(), mmd.getAbstractClassMetaData().getFullClassName()}));
                     }

                     String ownerFieldName = eofmd.getName();
                     this.ownerMapping = this.elementInfo[0].getDatastoreClass().getMemberMapping(eofmd);
                     if (this.ownerMapping == null) {
                        throw new NucleusUserException(Localiser.msg("056046", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.elementType, ownerFieldName}));
                     }

                     if (this.isEmbeddedMapping(this.ownerMapping)) {
                        throw new NucleusUserException(Localiser.msg("056026", new Object[]{ownerFieldName, this.elementType, eofmd.getTypeName(), mmd.getClassName()}));
                     }
                  } else {
                     this.ownerMapping = this.elementInfo[0].getDatastoreClass().getExternalMapping(mmd, 5);
                     if (this.ownerMapping == null) {
                        throw new NucleusUserException(Localiser.msg("056047", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.elementType}));
                     }
                  }

                  this.orderMapping = this.elementInfo[0].getDatastoreClass().getExternalMapping(mmd, 4);
                  if (this.orderMapping == null) {
                     throw new NucleusUserException(Localiser.msg("056048", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.elementType}));
                  } else {
                     this.relationDiscriminatorMapping = this.elementInfo[0].getDatastoreClass().getExternalMapping(mmd, 6);
                     if (this.relationDiscriminatorMapping != null) {
                        this.relationDiscriminatorValue = mmd.getValueForExtension("relation-discriminator-value");
                        if (this.relationDiscriminatorValue == null) {
                           this.relationDiscriminatorValue = mmd.getFullFieldName();
                        }
                     }

                     this.containerTable = this.elementInfo[0].getDatastoreClass();
                     if (mmd.getMappedBy() != null && this.ownerMapping.getTable() != this.containerTable) {
                        this.containerTable = this.ownerMapping.getTable();
                     }

                  }
               }
            } else {
               throw new NucleusUserException(Localiser.msg("056075", new Object[]{this.ownerMemberMetaData.getFullFieldName(), this.elementType}));
            }
         }
      }
   }

   private boolean updateElementFk(ObjectProvider ownerOP, Object element, Object owner, int index) {
      if (element == null) {
         return false;
      } else {
         String updateFkStmt = this.getUpdateFkStmt();
         ExecutionContext ec = ownerOP.getExecutionContext();

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            boolean retval;
            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, updateFkStmt, false);

               try {
                  int jdbcPosition = 1;
                  if (this.elementInfo.length > 1) {
                     DatastoreClass table = this.storeMgr.getDatastoreClass(element.getClass().getName(), this.clr);
                     if (table != null) {
                        ps.setString(jdbcPosition++, table.toString());
                     } else {
                        NucleusLogger.PERSISTENCE.info(">> FKArrayStore.updateElementFK : need to set table in statement but dont know table where to store " + element);
                     }
                  }

                  if (owner == null) {
                     this.ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, this.ownerMapping), (Object)null);
                     jdbcPosition += this.ownerMapping.getNumberOfDatastoreMappings();
                  } else {
                     jdbcPosition = BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
                  }

                  jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, index, jdbcPosition, this.orderMapping);
                  if (this.relationDiscriminatorMapping != null) {
                     jdbcPosition = BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                  }

                  BackingStoreHelper.populateElementInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
                  sqlControl.executeStatementUpdate(ec, mconn, updateFkStmt, ps, true);
                  retval = true;
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return retval;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056027", new Object[]{updateFkStmt}), e);
         }
      }
   }

   private String getUpdateFkStmt() {
      if (this.updateFkStmt == null) {
         synchronized(this) {
            StringBuilder stmt = new StringBuilder("UPDATE ");
            if (this.elementInfo.length > 1) {
               stmt.append("?");
            } else {
               stmt.append(this.elementInfo[0].getDatastoreClass().toString());
            }

            stmt.append(" SET ");

            for(int i = 0; i < this.ownerMapping.getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(",");
               }

               stmt.append(this.ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               stmt.append(" = ");
               stmt.append(((AbstractDatastoreMapping)this.ownerMapping.getDatastoreMapping(i)).getUpdateInputParameter());
            }

            for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
               stmt.append(",");
               stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               stmt.append(" = ");
               stmt.append(((AbstractDatastoreMapping)this.orderMapping.getDatastoreMapping(i)).getUpdateInputParameter());
            }

            if (this.relationDiscriminatorMapping != null) {
               for(int i = 0; i < this.relationDiscriminatorMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(",");
                  stmt.append(this.relationDiscriminatorMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
                  stmt.append(" = ");
                  stmt.append(((AbstractDatastoreMapping)this.relationDiscriminatorMapping.getDatastoreMapping(i)).getUpdateInputParameter());
               }
            }

            stmt.append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.elementMapping, (String)null, true);
            this.updateFkStmt = stmt.toString();
         }
      }

      return this.updateFkStmt;
   }

   public void clear(ObjectProvider ownerOP) {
      boolean deleteElements = false;
      if (this.ownerMemberMetaData.getArray().isDependentElement()) {
         NucleusLogger.DATASTORE.debug(Localiser.msg("056034"));
         deleteElements = true;
      } else if (this.ownerMapping.isNullable() && this.orderMapping.isNullable()) {
         NucleusLogger.DATASTORE.debug(Localiser.msg("056036"));
         deleteElements = false;
      } else {
         NucleusLogger.DATASTORE.debug(Localiser.msg("056035"));
         deleteElements = true;
      }

      if (deleteElements) {
         ownerOP.isLoaded(this.ownerMemberMetaData.getAbsoluteFieldNumber());
         Object[] value = ownerOP.provideField(this.ownerMemberMetaData.getAbsoluteFieldNumber());
         if (value != null && value.length > 0) {
            ownerOP.getExecutionContext().deleteObjects(value);
         }
      } else {
         String clearNullifyStmt = this.getClearNullifyStmt();

         try {
            ExecutionContext ec = ownerOP.getExecutionContext();
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, clearNullifyStmt, false);

               try {
                  int jdbcPosition = 1;
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
                  if (this.relationDiscriminatorMapping != null) {
                     BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                  }

                  sqlControl.executeStatementUpdate(ec, mconn, clearNullifyStmt, ps, true);
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056013", new Object[]{clearNullifyStmt}), e);
         }
      }

   }

   protected String getClearNullifyStmt() {
      if (this.clearNullifyStmt == null) {
         synchronized(this) {
            StringBuilder stmt = new StringBuilder("UPDATE ");
            if (this.elementInfo.length > 1) {
               stmt.append("?");
            } else {
               stmt.append(this.elementInfo[0].getDatastoreClass().toString());
            }

            stmt.append(" SET ");

            for(int i = 0; i < this.ownerMapping.getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(", ");
               }

               stmt.append(this.ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString() + " = NULL");
            }

            for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
               stmt.append(", ");
               stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString() + " = NULL");
            }

            if (this.relationDiscriminatorMapping != null) {
               for(int i = 0; i < this.relationDiscriminatorMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(", ");
                  stmt.append(this.relationDiscriminatorMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString() + " = NULL");
               }
            }

            stmt.append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.clearNullifyStmt = stmt.toString();
         }
      }

      return this.clearNullifyStmt;
   }

   public boolean set(ObjectProvider ownerOP, Object array) {
      if (array == null) {
         return true;
      } else {
         for(int i = 0; i < Array.getLength(array); ++i) {
            this.validateElementForWriting(ownerOP.getExecutionContext(), Array.get(array, i), (FieldValues)null);
         }

         int length = Array.getLength(array);

         for(int i = 0; i < length; ++i) {
            E obj = (E)Array.get(array, i);
            this.updateElementFk(ownerOP, obj, ownerOP.getObject(), i);
         }

         return true;
      }
   }

   public Iterator iterator(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      if (this.elementInfo != null && this.elementInfo.length != 0) {
         IteratorStatement iterStmt = this.getIteratorStatement(ownerOP.getExecutionContext().getClassLoaderResolver(), ownerOP.getExecutionContext().getFetchPlan(), true);
         SQLStatement sqlStmt = iterStmt.getSQLStatement();
         StatementClassMapping iteratorMappingDef = iterStmt.getStatementClassMapping();
         int inputParamNum = 1;
         StatementMappingIndex ownerIdx = new StatementMappingIndex(this.ownerMapping);
         if (sqlStmt.getNumberOfUnions() > 0) {
            for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
               int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

               for(int k = 0; k < this.ownerMapping.getNumberOfDatastoreMappings(); ++k) {
                  paramPositions[k] = inputParamNum++;
               }

               ownerIdx.addParameterOccurrence(paramPositions);
            }
         } else {
            int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < this.ownerMapping.getNumberOfDatastoreMappings(); ++k) {
               paramPositions[k] = inputParamNum++;
            }

            ownerIdx.addParameterOccurrence(paramPositions);
         }

         StatementParameterMapping iteratorMappingParams = new StatementParameterMapping();
         iteratorMappingParams.addMappingForParameter("owner", ownerIdx);
         if (ec.getTransaction().getSerializeRead() != null && ec.getTransaction().getSerializeRead()) {
            sqlStmt.addExtension("lock-for-update", true);
         }

         String stmt = sqlStmt.getSelectStatement().toSQL();

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            ArrayStoreIterator var16;
            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
               int numParams = ownerIdx.getNumberOfParameterOccurrences();

               for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
                  ownerIdx.getMapping().setObject(ec, ps, ownerIdx.getParameterPositionsForOccurrence(paramInstance), ownerOP.getObject());
               }

               try {
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

                  try {
                     ResultObjectFactory rof = null;
                     if (this.elementsAreEmbedded || this.elementsAreSerialised) {
                        throw new NucleusException("Cannot have FK array with non-persistent objects");
                     }

                     ResultObjectFactory var46 = new PersistentClassROF(this.storeMgr, this.emd, iteratorMappingDef, false, (FetchPlan)null, this.clr.classForName(this.elementType));
                     var16 = new ArrayStoreIterator(ownerOP, rs, var46, this);
                  } finally {
                     rs.close();
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return var16;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
         } catch (MappedDatastoreException e) {
            throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
         }
      } else {
         return null;
      }
   }

   public IteratorStatement getIteratorStatement(ClassLoaderResolver clr, FetchPlan fp, boolean addRestrictionOnOwner) {
      SQLStatement sqlStmt = null;
      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      StatementClassMapping iteratorMappingClass = new StatementClassMapping();
      if (this.elementInfo[0].getDatastoreClass().getDiscriminatorMetaData() != null && this.elementInfo[0].getDatastoreClass().getDiscriminatorMetaData().getStrategy() != DiscriminatorStrategy.NONE) {
         String elementType = this.ownerMemberMetaData.getArray().getElementType();
         if (!ClassUtils.isReferenceType(clr.classForName(elementType))) {
            sqlStmt = (new DiscriminatorStatementGenerator(this.storeMgr, clr, clr.classForName(this.elementInfo[0].getClassName()), true, (DatastoreIdentifier)null, (String)null)).getStatement();
         } else {
            String[] clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(elementType, clr);
            Class[] cls = new Class[clsNames.length];

            for(int i = 0; i < clsNames.length; ++i) {
               cls[i] = clr.classForName(clsNames[i]);
            }

            sqlStmt = (new DiscriminatorStatementGenerator(this.storeMgr, clr, cls, true, (DatastoreIdentifier)null, (String)null)).getStatement();
         }

         this.iterateUsingDiscriminator = true;
         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, iteratorMappingClass, fp, sqlStmt.getPrimaryTable(), this.emd, 0);
      } else {
         for(int i = 0; i < this.elementInfo.length; ++i) {
            Class elementCls = clr.classForName(this.elementInfo[i].getClassName());
            UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, elementCls, true, (DatastoreIdentifier)null, (String)null);
            stmtGen.setOption("selectNucleusType");
            iteratorMappingClass.setNucleusTypeColumnName("NUCLEUS_TYPE");
            SQLStatement subStmt = stmtGen.getStatement();
            if (sqlStmt == null) {
               if (this.elementInfo.length > 1) {
                  SQLStatementHelper.selectIdentityOfCandidateInStatement(subStmt, iteratorMappingClass, this.elementInfo[i].getAbstractClassMetaData());
               } else {
                  SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(subStmt, iteratorMappingClass, fp, subStmt.getPrimaryTable(), this.elementInfo[i].getAbstractClassMetaData(), 0);
               }
            } else if (this.elementInfo.length > 1) {
               SQLStatementHelper.selectIdentityOfCandidateInStatement(subStmt, (StatementClassMapping)null, this.elementInfo[i].getAbstractClassMetaData());
            } else {
               SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(subStmt, (StatementClassMapping)null, fp, subStmt.getPrimaryTable(), this.elementInfo[i].getAbstractClassMetaData(), 0);
            }

            if (sqlStmt == null) {
               sqlStmt = subStmt;
            } else {
               sqlStmt.union(subStmt);
            }
         }
      }

      if (addRestrictionOnOwner) {
         SQLTable ownerSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.ownerMapping);
         SQLExpression ownerExpr = exprFactory.newExpression(sqlStmt, ownerSqlTbl, this.ownerMapping);
         SQLExpression ownerVal = exprFactory.newLiteralParameter(sqlStmt, this.ownerMapping, (Object)null, "OWNER");
         sqlStmt.whereAnd(ownerExpr.eq(ownerVal), true);
      }

      if (this.relationDiscriminatorMapping != null) {
         SQLTable distSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.relationDiscriminatorMapping);
         SQLExpression distExpr = exprFactory.newExpression(sqlStmt, distSqlTbl, this.relationDiscriminatorMapping);
         SQLExpression distVal = exprFactory.newLiteral(sqlStmt, this.relationDiscriminatorMapping, this.relationDiscriminatorValue);
         sqlStmt.whereAnd(distExpr.eq(distVal), true);
      }

      if (this.orderMapping != null) {
         SQLTable orderSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.orderMapping);
         SQLExpression[] orderExprs = new SQLExpression[this.orderMapping.getNumberOfDatastoreMappings()];
         boolean[] descendingOrder = new boolean[this.orderMapping.getNumberOfDatastoreMappings()];
         orderExprs[0] = exprFactory.newExpression(sqlStmt, orderSqlTbl, this.orderMapping);
         sqlStmt.setOrdering(orderExprs, descendingOrder);
      }

      return new IteratorStatement(this, sqlStmt, iteratorMappingClass);
   }
}
