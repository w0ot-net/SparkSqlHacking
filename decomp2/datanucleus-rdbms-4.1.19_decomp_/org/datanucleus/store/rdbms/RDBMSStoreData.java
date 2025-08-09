package org.datanucleus.store.rdbms;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ImplementsMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.rdbms.table.ViewImpl;
import org.datanucleus.util.Localiser;

public class RDBMSStoreData extends StoreData {
   protected String tableName = null;
   protected DatastoreIdentifier tableIdentifier = null;
   protected boolean tableOwner = true;

   public RDBMSStoreData(String name, String tableName, boolean tableOwner, int type, String interfaceName) {
      super(name, (MetaData)null, type, interfaceName);
      this.tableName = tableName;
      this.tableOwner = tableOwner;
   }

   public RDBMSStoreData(ClassMetaData cmd, Table table, boolean tableOwner) {
      super(cmd.getFullClassName(), cmd, 1, (String)null);
      this.tableOwner = tableOwner;
      if (table != null) {
         this.table = table;
         this.tableName = table.toString();
         this.tableIdentifier = table.getIdentifier();
      }

      String interfaces = null;
      ImplementsMetaData[] implMds = cmd.getImplementsMetaData();
      if (implMds != null) {
         for(int i = 0; i < cmd.getImplementsMetaData().length; ++i) {
            if (interfaces == null) {
               interfaces = "";
            } else {
               interfaces = interfaces + ",";
            }

            interfaces = interfaces + cmd.getImplementsMetaData()[i].getName();
         }

         this.interfaceName = interfaces;
      }

   }

   public RDBMSStoreData(AbstractMemberMetaData mmd, Table table) {
      super(mmd.getFullFieldName(), mmd, 2, (String)null);
      if (table == null) {
         throw new NullPointerException("table should not be null");
      } else {
         this.table = table;
         this.tableName = table.toString();
         this.tableOwner = true;
         this.tableIdentifier = table.getIdentifier();
         String interfaceName = table.getStoreManager().getMetaDataManager().isPersistentInterface(mmd.getType().getName()) ? mmd.getType().getName() : null;
         if (interfaceName != null) {
            this.interfaceName = interfaceName;
         }

      }
   }

   public boolean mapsToView() {
      return this.table != null ? this.table instanceof ViewImpl : false;
   }

   public String getTableName() {
      return this.tableName;
   }

   public boolean isTableOwner() {
      return this.tableOwner;
   }

   public boolean hasTable() {
      return this.tableName != null;
   }

   public DatastoreIdentifier getDatastoreIdentifier() {
      return this.tableIdentifier;
   }

   public void setDatastoreContainerObject(DatastoreClass table) {
      if (table != null) {
         this.table = table;
         this.tableName = table.toString();
         this.tableIdentifier = table.getIdentifier();
      }

   }

   public String toString() {
      MetaData metadata = this.getMetaData();
      if (metadata instanceof ClassMetaData) {
         ClassMetaData cmd = (ClassMetaData)metadata;
         return Localiser.msg("035004", new Object[]{this.name, this.tableName != null ? this.tableName : "(none)", cmd.getInheritanceMetaData().getStrategy().toString()});
      } else {
         return metadata instanceof AbstractMemberMetaData ? Localiser.msg("035005", new Object[]{this.name, this.tableName}) : Localiser.msg("035004", new Object[]{this.name, this.tableName});
      }
   }
}
