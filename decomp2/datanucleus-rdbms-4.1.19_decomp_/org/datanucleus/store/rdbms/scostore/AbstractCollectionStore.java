package org.datanucleus.store.rdbms.scostore;

import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedElementPCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractCollectionStore extends ElementContainerStore implements CollectionStore {
   protected String containsStmt;

   protected AbstractCollectionStore(RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
   }

   public boolean updateEmbeddedElement(ObjectProvider op, Object element, int fieldNumber, Object value) {
      boolean modified = false;
      if (this.elementMapping != null && this.elementMapping instanceof EmbeddedElementPCMapping) {
         String fieldName = this.emd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber).getName();
         if (fieldName == null) {
            return false;
         }

         JavaTypeMapping fieldMapping = ((EmbeddedElementPCMapping)this.elementMapping).getJavaTypeMapping(fieldName);
         if (fieldMapping == null) {
            return false;
         }

         modified = this.updateEmbeddedElement(op, element, fieldNumber, value, fieldMapping);
      }

      return modified;
   }

   public void update(ObjectProvider op, Collection coll) {
      this.clear(op);
      this.addAll(op, coll, 0);
   }

   public boolean contains(ObjectProvider op, Object element) {
      if (!this.validateElementForReading(op, element)) {
         return false;
      } else {
         String stmt = this.getContainsStmt(element);

         try {
            ExecutionContext ec = op.getExecutionContext();
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            boolean retval;
            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);

               try {
                  int jdbcPosition = 1;
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                  jdbcPosition = BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
                  boolean usingJoinTable = this.usingJoinTable();
                  ElementContainerStore.ElementInfo elemInfo = this.getElementInfoForElement(element);
                  if (!usingJoinTable && elemInfo != null && elemInfo.getDiscriminatorMapping() != null) {
                     jdbcPosition = BackingStoreHelper.populateElementDiscriminatorInStatement(ec, ps, jdbcPosition, true, elemInfo, this.clr);
                  }

                  if (this.relationDiscriminatorMapping != null) {
                     BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                  }

                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

                  try {
                     retval = rs.next();
                     JDBCUtils.logWarnings(rs);
                  } finally {
                     rs.close();
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return retval;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056008", new Object[]{stmt}), e);
         }
      }
   }

   private String getContainsStmt(Object element) {
      if (this.containsStmt != null) {
         return this.containsStmt;
      } else {
         synchronized(this) {
            String stmt = this.getContainsStatementString(element);
            if (this.usingJoinTable()) {
               if (this.elementMapping instanceof ReferenceMapping && this.elementMapping.getNumberOfDatastoreMappings() > 1) {
                  return stmt;
               }

               this.containsStmt = stmt;
            }

            return stmt;
         }
      }
   }

   private String getContainsStatementString(Object element) {
      boolean elementsAreSerialised = this.isElementsAreSerialised();
      boolean usingJoinTable = this.usingJoinTable();
      Table selectTable = null;
      JavaTypeMapping ownerMapping = null;
      JavaTypeMapping elemMapping = null;
      JavaTypeMapping relDiscrimMapping = null;
      ElementContainerStore.ElementInfo elemInfo = null;
      if (usingJoinTable) {
         selectTable = this.containerTable;
         ownerMapping = this.ownerMapping;
         elemMapping = this.elementMapping;
         relDiscrimMapping = this.relationDiscriminatorMapping;
      } else {
         elemInfo = this.getElementInfoForElement(element);
         if (elemInfo != null) {
            selectTable = elemInfo.getDatastoreClass();
            elemMapping = elemInfo.getDatastoreClass().getIdMapping();
            if (this.ownerMemberMetaData.getMappedBy() != null) {
               ownerMapping = selectTable.getMemberMapping(elemInfo.getAbstractClassMetaData().getMetaDataForMember(this.ownerMemberMetaData.getMappedBy()));
            } else {
               ownerMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 5);
            }

            relDiscrimMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 6);
         }
      }

      StringBuilder stmt = new StringBuilder("SELECT ");
      String containerAlias = "THIS";
      String joinedElementAlias = "ELEM";

      for(int i = 0; i < ownerMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      stmt.append(" FROM ").append(selectTable.toString()).append(" ").append(containerAlias);
      boolean joinedDiscrim = false;
      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, ownerMapping, containerAlias, true);
      BackingStoreHelper.appendWhereClauseForElement(stmt, elemMapping, element, elementsAreSerialised, containerAlias, false);
      if (!usingJoinTable && elemInfo.getDiscriminatorMapping() != null) {
         StringBuilder discrimStr = new StringBuilder();
         Collection<String> classNames = this.storeMgr.getSubClassesForClass(elemInfo.getClassName(), true, this.clr);
         classNames.add(elemInfo.getClassName());

         for(String className : classNames) {
            Class cls = this.clr.classForName(className);
            if (!Modifier.isAbstract(cls.getModifiers())) {
               if (discrimStr.length() > 0) {
                  discrimStr.append(" OR ");
               }

               if (joinedDiscrim) {
                  discrimStr.append(joinedElementAlias);
               } else {
                  discrimStr.append(containerAlias);
               }

               discrimStr.append(".").append(elemInfo.getDiscriminatorMapping().getDatastoreMapping(0).getColumn().getIdentifier().toString());
               discrimStr.append(" = ");
               discrimStr.append(((AbstractDatastoreMapping)elemInfo.getDiscriminatorMapping().getDatastoreMapping(0)).getUpdateInputParameter());
            }
         }

         if (discrimStr.length() > 0) {
            stmt.append(" AND (").append(discrimStr.toString()).append(")");
         }
      }

      if (relDiscrimMapping != null) {
         BackingStoreHelper.appendWhereClauseForMapping(stmt, relDiscrimMapping, containerAlias, false);
      }

      return stmt.toString();
   }

   public boolean updateEmbeddedElement(ObjectProvider op, Object element, int fieldNumber, Object value, JavaTypeMapping fieldMapping) {
      boolean modified = false;
      String stmt = this.getUpdateEmbeddedElementStmt(fieldMapping);

      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, false);

            try {
               int jdbcPosition = 1;
               fieldMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, fieldMapping), value);
               jdbcPosition += fieldMapping.getNumberOfDatastoreMappings();
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               BackingStoreHelper.populateEmbeddedElementFieldsInStatement(op, element, ps, jdbcPosition, ((JoinTable)this.containerTable).getOwnerMemberMetaData(), this.elementMapping, this.emd, this);
               sqlControl.executeStatementUpdate(ec, mconn, stmt, ps, true);
               modified = true;
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return modified;
      } catch (SQLException e) {
         NucleusLogger.DATASTORE_PERSIST.error("Exception updating embedded element in collection", e);
         throw new NucleusDataStoreException(Localiser.msg("056009", new Object[]{stmt}), e);
      }
   }

   protected String getUpdateEmbeddedElementStmt(JavaTypeMapping fieldMapping) {
      JavaTypeMapping ownerMapping = this.getOwnerMapping();
      StringBuilder stmt = (new StringBuilder("UPDATE ")).append(this.containerTable.toString()).append(" SET ");

      for(int i = 0; i < fieldMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(fieldMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append(" = ");
         stmt.append(((AbstractDatastoreMapping)fieldMapping.getDatastoreMapping(i)).getUpdateInputParameter());
      }

      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, ownerMapping, (String)null, true);
      EmbeddedElementPCMapping embeddedMapping = (EmbeddedElementPCMapping)this.elementMapping;

      for(int i = 0; i < embeddedMapping.getNumberOfJavaTypeMappings(); ++i) {
         JavaTypeMapping m = embeddedMapping.getJavaTypeMapping(i);
         if (m != null) {
            for(int j = 0; j < m.getNumberOfDatastoreMappings(); ++j) {
               stmt.append(" AND ");
               stmt.append(m.getDatastoreMapping(j).getColumn().getIdentifier().toString());
               stmt.append(" = ");
               stmt.append(((AbstractDatastoreMapping)m.getDatastoreMapping(j)).getUpdateInputParameter());
            }
         }
      }

      return stmt.toString();
   }

   protected String getRemoveStmt(Object element) {
      if (this.elementMapping instanceof ReferenceMapping && this.elementMapping.getNumberOfDatastoreMappings() > 1) {
         return this.getRemoveStatementString(element);
      } else {
         if (this.removeStmt == null) {
            synchronized(this) {
               this.removeStmt = this.getRemoveStatementString(element);
            }
         }

         return this.removeStmt;
      }
   }

   private String getRemoveStatementString(Object element) {
      StringBuilder stmt = (new StringBuilder("DELETE FROM ")).append(this.containerTable.toString());
      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, this.containerTable.toString(), true);
      BackingStoreHelper.appendWhereClauseForElement(stmt, this.elementMapping, element, this.elementsAreSerialised, this.containerTable.toString(), false);
      if (this.relationDiscriminatorMapping != null) {
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, this.containerTable.toString(), false);
      }

      return stmt.toString();
   }
}
