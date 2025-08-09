package org.datanucleus.store.rdbms.table;

import java.util.Collection;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ColumnMetaDataContainer;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.DuplicateColumnException;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.mapping.CorrespondentColumnsMapper;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.util.Localiser;

public final class ColumnCreator {
   private ColumnCreator() {
   }

   public static Column createIndexColumn(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, ClassLoaderResolver clr, Table table, ColumnMetaData colmd, boolean pk) {
      DatastoreIdentifier identifier = null;
      if (colmd != null && colmd.getName() != null) {
         identifier = storeMgr.getIdentifierFactory().newColumnIdentifier(colmd.getName());
      } else {
         identifier = storeMgr.getIdentifierFactory().newAdapterIndexFieldIdentifier();
      }

      Column column = table.addColumn(mapping.getType(), identifier, mapping, colmd);
      storeMgr.getMappingManager().createDatastoreMapping(mapping, column, mapping.getJavaType().getName());
      if (pk) {
         column.setPrimaryKey();
      }

      return column;
   }

   public static JavaTypeMapping createColumnsForJoinTables(Class javaType, AbstractMemberMetaData mmd, ColumnMetaData[] columnMetaData, RDBMSStoreManager storeMgr, Table table, boolean primaryKey, boolean nullable, FieldRole fieldRole, ClassLoaderResolver clr) {
      JavaTypeMapping mapping = storeMgr.getMappingManager().getMapping(javaType, false, false, mmd.getFullFieldName());
      mapping.setTable(table);
      createColumnsForField(javaType, mapping, table, storeMgr, mmd, primaryKey, nullable, false, false, fieldRole, columnMetaData, clr, false);
      return mapping;
   }

   public static JavaTypeMapping createColumnsForField(Class javaType, JavaTypeMapping mapping, Table table, RDBMSStoreManager storeMgr, AbstractMemberMetaData mmd, boolean isPrimaryKey, boolean isNullable, boolean serialised, boolean embedded, FieldRole fieldRole, ColumnMetaData[] columnMetaData, ClassLoaderResolver clr, boolean isReferenceField) {
      IdentifierFactory idFactory = storeMgr.getIdentifierFactory();
      if (!(mapping instanceof ReferenceMapping) && !(mapping instanceof PersistableMapping)) {
         Column column = null;
         ColumnMetaData colmd = null;
         if (columnMetaData != null && columnMetaData.length > 0) {
            colmd = columnMetaData[0];
         }

         DatastoreIdentifier identifier = null;
         if (colmd != null && colmd.getName() != null) {
            identifier = idFactory.newColumnIdentifier(colmd.getName());
         } else {
            identifier = idFactory.newJoinTableFieldIdentifier(mmd, (AbstractMemberMetaData)null, (DatastoreIdentifier)null, storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(javaType), fieldRole);
         }

         column = table.addColumn(javaType.getName(), identifier, mapping, colmd);
         storeMgr.getMappingManager().createDatastoreMapping(mapping, column, mapping.getJavaTypeForDatastoreMapping(0));
         if (isNullable) {
            column.setNullable(true);
         }
      } else {
         JavaTypeMapping container = mapping;
         if (mapping instanceof ReferenceMapping) {
            container = storeMgr.getMappingManager().getMapping(javaType, serialised, embedded, mmd != null ? mmd.getFullFieldName() : null);
            ((ReferenceMapping)mapping).addJavaTypeMapping(container);
         }

         DatastoreClass destinationTable = storeMgr.getDatastoreClass(javaType.getName(), clr);
         if (destinationTable == null) {
            AbstractClassMetaData ownerCmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(javaType, clr);
            if (ownerCmd.getBaseAbstractClassMetaData().getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
               Collection<String> ownerSubclassNames = storeMgr.getSubClassesForClass(javaType.getName(), true, clr);
               if (ownerSubclassNames != null && ownerSubclassNames.size() > 0) {
                  for(String ownerSubclassName : ownerSubclassNames) {
                     storeMgr.getMetaDataManager().getMetaDataForClass(ownerSubclassName, clr);

                     try {
                        destinationTable = storeMgr.getDatastoreClass(ownerSubclassName, clr);
                     } catch (NoTableManagedException var24) {
                     }

                     if (destinationTable != null) {
                        break;
                     }
                  }
               }
            } else {
               AbstractClassMetaData[] ownerCmds = storeMgr.getClassesManagingTableForClass(ownerCmd, clr);
               if (ownerCmds == null || ownerCmds.length == 0) {
                  throw (new NucleusUserException(Localiser.msg("057023", new Object[]{javaType.getName()}))).setFatal();
               }

               destinationTable = storeMgr.getDatastoreClass(ownerCmds[0].getFullClassName(), clr);
            }
         }

         if (destinationTable != null) {
            JavaTypeMapping m = destinationTable.getIdMapping();
            ColumnMetaDataContainer columnContainer = null;
            if (columnMetaData != null && columnMetaData.length > 0) {
               columnContainer = (ColumnMetaDataContainer)columnMetaData[0].getParent();
            }

            CorrespondentColumnsMapper correspondentColumnsMapping = new CorrespondentColumnsMapper(columnContainer, columnMetaData, m, true);

            for(int i = 0; i < m.getNumberOfDatastoreMappings(); ++i) {
               JavaTypeMapping refDatastoreMapping = storeMgr.getMappingManager().getMapping(m.getDatastoreMapping(i).getJavaTypeMapping().getJavaType());
               ColumnMetaData colmd = correspondentColumnsMapping.getColumnMetaDataByIdentifier(m.getDatastoreMapping(i).getColumn().getIdentifier());

               try {
                  DatastoreIdentifier identifier = null;
                  if (colmd.getName() == null) {
                     if (isReferenceField) {
                        identifier = idFactory.newReferenceFieldIdentifier(mmd, storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(javaType, clr), m.getDatastoreMapping(i).getColumn().getIdentifier(), storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(javaType), fieldRole);
                     } else {
                        AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(clr);
                        identifier = idFactory.newJoinTableFieldIdentifier(mmd, relatedMmds != null ? relatedMmds[0] : null, m.getDatastoreMapping(i).getColumn().getIdentifier(), storeMgr.getNucleusContext().getTypeManager().isDefaultEmbeddedType(javaType), fieldRole);
                     }
                  } else {
                     identifier = idFactory.newColumnIdentifier(colmd.getName());
                  }

                  Column column = table.addColumn(javaType.getName(), identifier, refDatastoreMapping, colmd);
                  m.getDatastoreMapping(i).getColumn().copyConfigurationTo(column);
                  if (isPrimaryKey) {
                     column.setPrimaryKey();
                  }

                  if (isNullable) {
                     column.setNullable(true);
                  }

                  storeMgr.getMappingManager().createDatastoreMapping(refDatastoreMapping, column, m.getDatastoreMapping(i).getJavaTypeMapping().getJavaTypeForDatastoreMapping(i));
               } catch (DuplicateColumnException ex) {
                  throw new NucleusUserException("Cannot create column for field " + mmd.getFullFieldName() + " column metadata " + colmd, ex);
               }

               ((PersistableMapping)container).addJavaTypeMapping(refDatastoreMapping);
            }
         }
      }

      return mapping;
   }
}
