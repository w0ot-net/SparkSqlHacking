package org.datanucleus.store.rdbms.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ForeignKeyAction;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.identifier.IdentifierType;
import org.datanucleus.store.rdbms.key.CandidateKey;
import org.datanucleus.store.rdbms.key.ForeignKey;
import org.datanucleus.store.rdbms.key.Index;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;

public class TableUtils {
   public static Collection getForeignKeysForReferenceField(JavaTypeMapping fieldMapping, AbstractMemberMetaData mmd, boolean autoMode, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      ReferenceMapping refMapping = (ReferenceMapping)fieldMapping;
      JavaTypeMapping[] refJavaTypeMappings = refMapping.getJavaTypeMapping();
      List fks = new ArrayList();

      for(int i = 0; i < refJavaTypeMappings.length; ++i) {
         JavaTypeMapping implMapping = refJavaTypeMappings[i];
         if (storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(implMapping.getType(), clr) != null && implMapping.getNumberOfDatastoreMappings() > 0) {
            DatastoreClass referencedTable = storeMgr.getDatastoreClass(implMapping.getType(), clr);
            if (referencedTable != null) {
               ForeignKeyMetaData fkmd = mmd.getForeignKeyMetaData();
               if (fkmd != null && fkmd.getDeleteAction() != ForeignKeyAction.NONE || autoMode) {
                  ForeignKey fk = new ForeignKey(implMapping, storeMgr.getDatastoreAdapter(), referencedTable, true);
                  fk.setForMetaData(fkmd);
                  fks.add(fk);
               }
            }
         }
      }

      return fks;
   }

   public static ForeignKey getForeignKeyForPCField(JavaTypeMapping fieldMapping, AbstractMemberMetaData mmd, boolean autoMode, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      DatastoreClass referencedTable = storeMgr.getDatastoreClass(mmd.getTypeName(), clr);
      if (referencedTable == null) {
         AbstractClassMetaData refCmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(mmd.getType(), clr);
         if (refCmd != null) {
            AbstractClassMetaData[] refCmds = storeMgr.getClassesManagingTableForClass(refCmd, clr);
            if (refCmds != null && refCmds.length == 1) {
               referencedTable = storeMgr.getDatastoreClass(refCmds[0].getFullClassName(), clr);
            }
         }
      }

      if (referencedTable != null) {
         ForeignKeyMetaData fkmd = mmd.getForeignKeyMetaData();
         if (fkmd != null && (fkmd.getDeleteAction() != ForeignKeyAction.NONE || fkmd.getFkDefinitionApplies()) || autoMode) {
            ForeignKey fk = new ForeignKey(fieldMapping, storeMgr.getDatastoreAdapter(), referencedTable, true);
            fk.setForMetaData(fkmd);
            if (fkmd != null && fkmd.getName() != null) {
               fk.setName(fkmd.getName());
            }

            return fk;
         }
      }

      return null;
   }

   public static Index getIndexForField(Table table, IndexMetaData imd, JavaTypeMapping fieldMapping) {
      if (fieldMapping.getNumberOfDatastoreMappings() == 0) {
         return null;
      } else if (!table.getStoreManager().getDatastoreAdapter().validToIndexMapping(fieldMapping)) {
         return null;
      } else {
         boolean unique = imd == null ? false : imd.isUnique();
         Index index = new Index(table, unique, imd != null ? imd.getValueForExtension("extended-setting") : null);
         if (imd != null && imd.getName() != null) {
            IdentifierFactory idFactory = table.getStoreManager().getIdentifierFactory();
            DatastoreIdentifier idxId = idFactory.newIdentifier(IdentifierType.INDEX, imd.getName());
            index.setName(idxId.toString());
         }

         int countFields = fieldMapping.getNumberOfDatastoreMappings();

         for(int j = 0; j < countFields; ++j) {
            index.addColumn(fieldMapping.getDatastoreMapping(j).getColumn());
         }

         return index;
      }
   }

   public static CandidateKey getCandidateKeyForField(Table table, UniqueMetaData umd, JavaTypeMapping fieldMapping) {
      CandidateKey ck = new CandidateKey(table);
      if (umd.getName() != null) {
         IdentifierFactory idFactory = table.getStoreManager().getIdentifierFactory();
         DatastoreIdentifier ckId = idFactory.newIdentifier(IdentifierType.CANDIDATE_KEY, umd.getName());
         ck.setName(ckId.toString());
      }

      int countFields = fieldMapping.getNumberOfDatastoreMappings();

      for(int j = 0; j < countFields; ++j) {
         ck.addColumn(fieldMapping.getDatastoreMapping(j).getColumn());
      }

      return ck;
   }
}
