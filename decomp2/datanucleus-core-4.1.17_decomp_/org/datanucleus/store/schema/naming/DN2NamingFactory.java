package org.datanucleus.store.schema.naming;

import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.VersionMetaData;

public class DN2NamingFactory extends AbstractNamingFactory {
   public DN2NamingFactory(NucleusContext nucCtx) {
      super(nucCtx);
   }

   public String getTableName(AbstractMemberMetaData mmd) {
      String name = null;
      AbstractMemberMetaData[] relatedMmds = null;
      if (mmd.hasContainer()) {
         if (mmd.getTable() != null) {
            name = mmd.getTable();
         } else {
            relatedMmds = mmd.getRelatedMemberMetaData(this.clr);
            if (relatedMmds != null && relatedMmds[0].getTable() != null) {
               name = relatedMmds[0].getTable();
            }
         }
      }

      if (name == null) {
         String ownerClass = mmd.getClassName(false);
         name = ownerClass + this.wordSeparator + mmd.getName();
      }

      return this.prepareIdentifierNameForUse(name, SchemaComponent.TABLE);
   }

   public String getColumnName(AbstractClassMetaData cmd, ColumnType type) {
      String name = null;
      if (type == ColumnType.DISCRIMINATOR_COLUMN) {
         name = cmd.getDiscriminatorColumnName();
         if (name == null) {
            name = "DISCRIMINATOR";
         }
      } else if (type == ColumnType.VERSION_COLUMN) {
         VersionMetaData vermd = cmd.getVersionMetaData();
         if (vermd != null) {
            ColumnMetaData colmd = vermd.getColumnMetaData();
            if (colmd != null && colmd.getName() != null) {
               name = colmd.getName();
            }
         }

         if (name == null) {
            name = "VERSION";
         }
      } else if (type == ColumnType.DATASTOREID_COLUMN) {
         if (cmd.getIdentityMetaData() != null) {
            ColumnMetaData idcolmds = cmd.getIdentityMetaData().getColumnMetaData();
            if (idcolmds != null) {
               name = idcolmds.getName();
            }
         }

         if (name == null) {
            name = cmd.getName() + this.wordSeparator + "ID";
         }
      } else {
         if (type != ColumnType.MULTITENANCY_COLUMN) {
            throw new NucleusException("This method does not support columns of type " + type);
         }

         if (cmd.hasExtension("multitenancy-column-name")) {
            name = cmd.getValueForExtension("multitenancy-column-name");
         }

         if (name == null) {
            name = "TENANT" + this.wordSeparator + "ID";
         }
      }

      return this.prepareIdentifierNameForUse(name, SchemaComponent.COLUMN);
   }

   public String getColumnName(AbstractMemberMetaData mmd, ColumnType type, int position) {
      String name = null;
      if (type == ColumnType.COLUMN) {
         ColumnMetaData[] colmds = mmd.getColumnMetaData();
         if (colmds != null && colmds.length > position) {
            name = colmds[position].getName();
         } else if (mmd.hasCollection() && mmd.getElementMetaData() != null) {
            colmds = mmd.getElementMetaData().getColumnMetaData();
            if (colmds != null && colmds.length > position) {
               name = colmds[position].getName();
            }
         }

         if (name == null) {
            name = mmd.getName();
         }
      } else if (type == ColumnType.INDEX_COLUMN) {
         if (mmd.getOrderMetaData() != null) {
            ColumnMetaData[] colmds = mmd.getOrderMetaData().getColumnMetaData();
            if (colmds != null && colmds.length > position) {
               name = colmds[position].getName();
            }
         }

         if (name == null) {
            name = "IDX";
         }
      } else if (type == ColumnType.ADAPTER_COLUMN) {
         name = "IDX";
      } else {
         if (type == ColumnType.FK_COLUMN) {
            throw new NucleusException("This method does not support columns of type " + type);
         }

         if (type != ColumnType.JOIN_OWNER_COLUMN) {
            throw new NucleusException("This method does not support columns of type " + type);
         }

         if (mmd.hasContainer() && mmd.getJoinMetaData() != null) {
            ColumnMetaData[] colmds = mmd.getJoinMetaData().getColumnMetaData();
            if (colmds != null && colmds.length > position) {
               name = colmds[position].getName();
            }
         }

         if (name == null && mmd.hasContainer()) {
            name = mmd.getName() + this.wordSeparator + "ID_OID";
         }
      }

      return this.prepareIdentifierNameForUse(name, SchemaComponent.COLUMN);
   }
}
