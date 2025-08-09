package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.store.rdbms.table.ColumnCreator;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class SubclassPCMapping extends MultiPersistableMapping {
   public void initialize(AbstractMemberMetaData fmd, Table table, ClassLoaderResolver clr) {
      super.initialize(fmd, table, clr);
      this.prepareDatastoreMapping(clr);
   }

   protected void prepareDatastoreMapping(ClassLoaderResolver clr) {
      if (this.roleForMember != FieldRole.ROLE_ARRAY_ELEMENT && this.roleForMember != FieldRole.ROLE_COLLECTION_ELEMENT && this.roleForMember != FieldRole.ROLE_MAP_KEY && this.roleForMember != FieldRole.ROLE_MAP_VALUE) {
         AbstractClassMetaData refCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(this.mmd.getType(), clr);
         if (refCmd.getInheritanceMetaData().getStrategy() != InheritanceStrategy.SUBCLASS_TABLE) {
            throw new NucleusUserException(Localiser.msg("020185", new Object[]{this.mmd.getFullFieldName()}));
         }

         AbstractClassMetaData[] subclassCmds = this.storeMgr.getClassesManagingTableForClass(refCmd, clr);
         boolean pk = false;
         if (subclassCmds.length > 1) {
            pk = false;
         }

         boolean nullable = true;
         if (subclassCmds.length > 1) {
            nullable = true;
         }

         int colPos = 0;

         for(int i = 0; i < subclassCmds.length; ++i) {
            Class type = clr.classForName(subclassCmds[i].getFullClassName());
            DatastoreClass dc = this.storeMgr.getDatastoreClass(subclassCmds[i].getFullClassName(), clr);
            JavaTypeMapping m = dc.getIdMapping();
            ColumnMetaData[] columnMetaDataForType = null;
            if (this.mmd.getColumnMetaData() != null && this.mmd.getColumnMetaData().length > 0) {
               if (this.mmd.getColumnMetaData().length < colPos + m.getNumberOfDatastoreMappings()) {
                  throw new NucleusUserException(Localiser.msg("020186", new Object[]{this.mmd.getFullFieldName(), "" + this.mmd.getColumnMetaData().length, "" + (colPos + m.getNumberOfDatastoreMappings())}));
               }

               columnMetaDataForType = new ColumnMetaData[m.getNumberOfDatastoreMappings()];
               System.arraycopy(this.mmd.getColumnMetaData(), colPos, columnMetaDataForType, 0, columnMetaDataForType.length);
               colPos += columnMetaDataForType.length;
            }

            ColumnCreator.createColumnsForField(type, this, this.table, this.storeMgr, this.mmd, pk, nullable, false, false, FieldRole.ROLE_FIELD, columnMetaDataForType, clr, true);
            if (NucleusLogger.DATASTORE.isInfoEnabled()) {
               NucleusLogger.DATASTORE.info(Localiser.msg("020187", new Object[]{type, this.mmd.getName()}));
            }
         }
      }

   }

   public Class getJavaType() {
      return null;
   }
}
