package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.query.PersistentClassROF;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.rdbms.query.StatementParameterMapping;
import org.datanucleus.store.rdbms.sql.DiscriminatorStatementGenerator;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.StatementGenerator;
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.ArrayTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;

public class JoinArrayStore extends AbstractArrayStore {
   public JoinArrayStore(AbstractMemberMetaData mmd, ArrayTable arrayTable, ClassLoaderResolver clr) {
      super(arrayTable.getStoreManager(), clr);
      this.containerTable = arrayTable;
      this.setOwner(arrayTable.getOwnerMemberMetaData());
      this.ownerMapping = arrayTable.getOwnerMapping();
      this.elementMapping = arrayTable.getElementMapping();
      this.orderMapping = arrayTable.getOrderMapping();
      this.relationDiscriminatorMapping = arrayTable.getRelationDiscriminatorMapping();
      this.relationDiscriminatorValue = arrayTable.getRelationDiscriminatorValue();
      this.elementType = arrayTable.getElementType();
      this.elementsAreEmbedded = arrayTable.isEmbeddedElement();
      this.elementsAreSerialised = arrayTable.isSerialisedElement();
      if (this.elementsAreSerialised) {
         this.elementInfo = null;
      } else {
         Class element_class = clr.classForName(this.elementType);
         if (ClassUtils.isReferenceType(element_class)) {
            String[] implNames = MetaDataUtils.getInstance().getImplementationNamesForReferenceField(this.ownerMemberMetaData, FieldRole.ROLE_ARRAY_ELEMENT, clr, this.storeMgr.getMetaDataManager());
            this.elementInfo = new ElementContainerStore.ElementInfo[implNames.length];

            for(int i = 0; i < implNames.length; ++i) {
               DatastoreClass table = this.storeMgr.getDatastoreClass(implNames[i], clr);
               AbstractClassMetaData cmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(implNames[i], clr);
               this.elementInfo[i] = new ElementContainerStore.ElementInfo(cmd, table);
            }
         } else {
            this.emd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(element_class, clr);
            if (this.emd != null) {
               this.elementType = this.emd.getFullClassName();
               if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
                  this.elementInfo = this.getElementInformationForClass();
                  if (this.elementInfo != null && this.elementInfo.length > 1) {
                     throw new NucleusUserException(Localiser.msg("056045", new Object[]{this.ownerMemberMetaData.getFullFieldName()}));
                  }
               } else {
                  this.elementInfo = null;
               }
            } else {
               this.elementInfo = null;
            }
         }
      }

   }

   public Iterator iterator(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      IteratorStatement iterStmt = this.getIteratorStatement(ec.getClassLoaderResolver(), ec.getFetchPlan(), true);
      SQLStatement sqlStmt = iterStmt.sqlStmt;
      StatementClassMapping iteratorMappingClass = iterStmt.stmtClassMapping;
      int inputParamNum = 1;
      StatementMappingIndex ownerIdx = new StatementMappingIndex(this.ownerMapping);
      if (sqlStmt.getNumberOfUnions() > 0) {
         for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
            int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < paramPositions.length; ++k) {
               paramPositions[k] = inputParamNum++;
            }

            ownerIdx.addParameterOccurrence(paramPositions);
         }
      } else {
         int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

         for(int k = 0; k < paramPositions.length; ++k) {
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

         ResultObjectFactory rof;
         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
            int numParams = ownerIdx.getNumberOfParameterOccurrences();

            for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
               ownerIdx.getMapping().setObject(ec, ps, ownerIdx.getParameterPositionsForOccurrence(paramInstance), ownerOP.getObject());
            }

            try {
               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
                     if (this.elementMapping instanceof ReferenceMapping) {
                        rof = new ArrayStoreIterator(ownerOP, rs, (ResultObjectFactory)null, this);
                        return rof;
                     }

                     rof = new PersistentClassROF(this.storeMgr, this.emd, iteratorMappingClass, false, (FetchPlan)null, this.clr.classForName(this.elementType));
                     ArrayStoreIterator var16 = new ArrayStoreIterator(ownerOP, rs, rof, this);
                     return var16;
                  }

                  rof = new ArrayStoreIterator(ownerOP, rs, (ResultObjectFactory)null, this);
               } finally {
                  rs.close();
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return rof;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
      } catch (MappedDatastoreException e) {
         throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
      }
   }

   public IteratorStatement getIteratorStatement(ClassLoaderResolver clr, FetchPlan fp, boolean addRestrictionOnOwner) {
      SQLStatement sqlStmt = null;
      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      StatementClassMapping iteratorMappingClass = null;
      if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
         if (this.elementMapping instanceof ReferenceMapping) {
            sqlStmt = new SQLStatement(this.storeMgr, this.containerTable, (DatastoreIdentifier)null, (String)null);
            sqlStmt.setClassLoaderResolver(clr);
            sqlStmt.select(sqlStmt.getPrimaryTable(), (JavaTypeMapping)this.elementMapping, (String)null);
         } else {
            iteratorMappingClass = new StatementClassMapping();

            for(int i = 0; i < this.elementInfo.length; ++i) {
               Class elementCls = clr.classForName(this.elementInfo[i].getClassName());
               SQLStatement elementStmt = null;
               if (this.elementInfo[i].getDiscriminatorStrategy() != null && this.elementInfo[i].getDiscriminatorStrategy() != DiscriminatorStrategy.NONE) {
                  String elementType = this.ownerMemberMetaData.getCollection().getElementType();
                  if (ClassUtils.isReferenceType(clr.classForName(elementType))) {
                     String[] clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(elementType, clr);
                     Class[] cls = new Class[clsNames.length];

                     for(int j = 0; j < clsNames.length; ++j) {
                        cls[j] = clr.classForName(clsNames[j]);
                     }

                     StatementGenerator stmtGen = new DiscriminatorStatementGenerator(this.storeMgr, clr, cls, true, (DatastoreIdentifier)null, (String)null, this.containerTable, (DatastoreIdentifier)null, this.elementMapping);
                     if (this.allowNulls) {
                        stmtGen.setOption("allowNulls");
                     }

                     elementStmt = stmtGen.getStatement();
                  } else {
                     StatementGenerator stmtGen = new DiscriminatorStatementGenerator(this.storeMgr, clr, elementCls, true, (DatastoreIdentifier)null, (String)null, this.containerTable, (DatastoreIdentifier)null, this.elementMapping);
                     if (this.allowNulls) {
                        stmtGen.setOption("allowNulls");
                     }

                     elementStmt = stmtGen.getStatement();
                  }

                  this.iterateUsingDiscriminator = true;
               } else {
                  StatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, elementCls, true, (DatastoreIdentifier)null, (String)null, this.containerTable, (DatastoreIdentifier)null, this.elementMapping);
                  stmtGen.setOption("selectNucleusType");
                  if (this.allowNulls) {
                     stmtGen.setOption("allowNulls");
                  }

                  iteratorMappingClass.setNucleusTypeColumnName("NUCLEUS_TYPE");
                  elementStmt = stmtGen.getStatement();
               }

               if (sqlStmt == null) {
                  sqlStmt = elementStmt;
               } else {
                  sqlStmt.union(elementStmt);
               }
            }

            SQLTable elementSqlTbl = sqlStmt.getTable(this.elementInfo[0].getDatastoreClass(), sqlStmt.getPrimaryTable().getGroupName());
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, iteratorMappingClass, fp, elementSqlTbl, this.emd, 0);
         }
      } else {
         sqlStmt = new SQLStatement(this.storeMgr, this.containerTable, (DatastoreIdentifier)null, (String)null);
         sqlStmt.setClassLoaderResolver(clr);
         sqlStmt.select(sqlStmt.getPrimaryTable(), (JavaTypeMapping)this.elementMapping, (String)null);
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
