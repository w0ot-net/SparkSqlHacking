package org.datanucleus.store.rdbms.mapping;

import java.util.HashMap;
import java.util.Map;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ColumnMetaDataContainer;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.MultiMapping;
import org.datanucleus.util.Localiser;

public class CorrespondentColumnsMapper {
   private final Map columnMetaDataBySideBIdentifier = new HashMap();
   private final String columnsName;

   public CorrespondentColumnsMapper(ColumnMetaDataContainer columnContainer, ColumnMetaData[] colmds, JavaTypeMapping mappingSideB, boolean updateContainer) {
      if (columnContainer != null && colmds != null) {
         int noOfUserColumns = colmds.length;
         StringBuilder str = new StringBuilder("Columns [");

         for(int i = 0; i < noOfUserColumns; ++i) {
            str.append(colmds[i].getName());
            if (i < noOfUserColumns - 1) {
               str.append(", ");
            }
         }

         str.append("]");
         this.columnsName = str.toString();
         if (noOfUserColumns > mappingSideB.getNumberOfDatastoreMappings()) {
            throw (new NucleusUserException(Localiser.msg("020003", new Object[]{this.columnsName, "" + noOfUserColumns, "" + mappingSideB.getNumberOfDatastoreMappings()}))).setFatal();
         }

         DatastoreIdentifier[] sideBidentifiers = new DatastoreIdentifier[mappingSideB.getNumberOfDatastoreMappings()];
         boolean[] sideButilised = new boolean[mappingSideB.getNumberOfDatastoreMappings()];

         for(int i = 0; i < mappingSideB.getNumberOfDatastoreMappings(); ++i) {
            sideBidentifiers[i] = mappingSideB.getDatastoreMapping(i).getColumn().getIdentifier();
            sideButilised[i] = false;
         }

         JavaTypeMapping[] sideBidMappings = ((MultiMapping)mappingSideB).getJavaTypeMapping();

         for(int i = 0; i < noOfUserColumns; ++i) {
            String targetColumnName = colmds[i].getTarget();
            if (targetColumnName == null) {
               String targetFieldName = colmds[i].getTargetMember();
               if (targetFieldName != null) {
                  for(int j = 0; j < sideBidMappings.length; ++j) {
                     if (sideBidMappings[j].getMemberMetaData().getName().equals(targetFieldName)) {
                        targetColumnName = sideBidMappings[j].getDatastoreMapping(0).getColumn().getIdentifier().getName();
                        break;
                     }
                  }
               }
            }

            if (targetColumnName != null) {
               boolean targetExists = false;

               for(int j = 0; j < sideBidentifiers.length; ++j) {
                  if (sideBidentifiers[j].getName().equalsIgnoreCase(targetColumnName) && !sideButilised[j]) {
                     this.putColumn(sideBidentifiers[j], colmds[i]);
                     sideButilised[j] = true;
                     targetExists = true;
                     break;
                  }
               }

               if (!targetExists) {
                  throw (new NucleusUserException(Localiser.msg("020004", new Object[]{this.columnsName, colmds[i].getName(), targetColumnName}))).setFatal();
               }
            }
         }

         for(int i = 0; i < colmds.length; ++i) {
            if (colmds[i].getTarget() == null) {
               for(int j = 0; j < sideBidentifiers.length; ++j) {
                  if (!sideButilised[j]) {
                     this.putColumn(sideBidentifiers[j], colmds[i]);
                     sideButilised[j] = true;
                     break;
                  }
               }
            }
         }

         for(int i = colmds.length; i < mappingSideB.getNumberOfDatastoreMappings(); ++i) {
            DatastoreIdentifier sideBidentifier = null;

            for(int j = 0; j < sideBidentifiers.length; ++j) {
               if (!sideButilised[j]) {
                  sideBidentifier = sideBidentifiers[j];
                  sideButilised[j] = true;
                  break;
               }
            }

            if (sideBidentifier == null) {
               throw (new NucleusUserException(Localiser.msg("020005", new Object[]{this.columnsName, "" + i}))).setFatal();
            }

            ColumnMetaData colmd = new ColumnMetaData();
            if (updateContainer) {
               columnContainer.addColumn(colmd);
            }

            this.putColumn(sideBidentifier, colmd);
         }
      } else {
         this.columnsName = null;

         for(int i = 0; i < mappingSideB.getNumberOfDatastoreMappings(); ++i) {
            DatastoreIdentifier sideBidentifier = mappingSideB.getDatastoreMapping(i).getColumn().getIdentifier();
            ColumnMetaData colmd = new ColumnMetaData();
            this.putColumn(sideBidentifier, colmd);
         }
      }

   }

   public CorrespondentColumnsMapper(ColumnMetaDataContainer columnContainer, JavaTypeMapping mappingSideB, boolean updateContainer) {
      if (columnContainer != null) {
         int noOfUserColumns = columnContainer.getColumnMetaData().length;
         ColumnMetaData[] colmds = columnContainer.getColumnMetaData();
         StringBuilder str = new StringBuilder("Columns [");

         for(int i = 0; i < noOfUserColumns; ++i) {
            str.append(colmds[i].getName());
            if (i < noOfUserColumns - 1) {
               str.append(", ");
            }
         }

         str.append("]");
         this.columnsName = str.toString();
         if (noOfUserColumns > mappingSideB.getNumberOfDatastoreMappings()) {
            throw (new NucleusUserException(Localiser.msg("020003", new Object[]{this.columnsName, "" + noOfUserColumns, "" + mappingSideB.getNumberOfDatastoreMappings()}))).setFatal();
         }

         DatastoreIdentifier[] sideBidentifiers = new DatastoreIdentifier[mappingSideB.getNumberOfDatastoreMappings()];
         boolean[] sideButilised = new boolean[mappingSideB.getNumberOfDatastoreMappings()];

         for(int i = 0; i < mappingSideB.getNumberOfDatastoreMappings(); ++i) {
            sideBidentifiers[i] = mappingSideB.getDatastoreMapping(i).getColumn().getIdentifier();
            sideButilised[i] = false;
         }

         JavaTypeMapping[] sideBidMappings = ((MultiMapping)mappingSideB).getJavaTypeMapping();

         for(int i = 0; i < noOfUserColumns; ++i) {
            String targetColumnName = colmds[i].getTarget();
            if (targetColumnName == null) {
               String targetFieldName = colmds[i].getTargetMember();
               if (targetFieldName != null) {
                  for(int j = 0; j < sideBidMappings.length; ++j) {
                     if (sideBidMappings[j].getMemberMetaData().getName().equals(targetFieldName)) {
                        targetColumnName = sideBidMappings[j].getDatastoreMapping(0).getColumn().getIdentifier().getName();
                        break;
                     }
                  }
               }
            }

            if (targetColumnName != null) {
               boolean targetExists = false;

               for(int j = 0; j < sideBidentifiers.length; ++j) {
                  if (sideBidentifiers[j].getName().equalsIgnoreCase(targetColumnName) && !sideButilised[j]) {
                     this.putColumn(sideBidentifiers[j], colmds[i]);
                     sideButilised[j] = true;
                     targetExists = true;
                     break;
                  }
               }

               if (!targetExists) {
                  throw (new NucleusUserException(Localiser.msg("020004", new Object[]{this.columnsName, colmds[i].getName(), targetColumnName}))).setFatal();
               }
            }
         }

         for(int i = 0; i < colmds.length; ++i) {
            if (colmds[i].getTarget() == null) {
               for(int j = 0; j < sideBidentifiers.length; ++j) {
                  if (!sideButilised[j]) {
                     this.putColumn(sideBidentifiers[j], colmds[i]);
                     sideButilised[j] = true;
                     break;
                  }
               }
            }
         }

         for(int i = colmds.length; i < mappingSideB.getNumberOfDatastoreMappings(); ++i) {
            DatastoreIdentifier sideBidentifier = null;

            for(int j = 0; j < sideBidentifiers.length; ++j) {
               if (!sideButilised[j]) {
                  sideBidentifier = sideBidentifiers[j];
                  sideButilised[j] = true;
                  break;
               }
            }

            if (sideBidentifier == null) {
               throw (new NucleusUserException(Localiser.msg("020005", new Object[]{this.columnsName, "" + i}))).setFatal();
            }

            ColumnMetaData colmd = new ColumnMetaData();
            if (updateContainer) {
               columnContainer.addColumn(colmd);
            }

            this.putColumn(sideBidentifier, colmd);
         }
      } else {
         this.columnsName = null;

         for(int i = 0; i < mappingSideB.getNumberOfDatastoreMappings(); ++i) {
            DatastoreIdentifier sideBidentifier = mappingSideB.getDatastoreMapping(i).getColumn().getIdentifier();
            ColumnMetaData colmd = new ColumnMetaData();
            this.putColumn(sideBidentifier, colmd);
         }
      }

   }

   public ColumnMetaData getColumnMetaDataByIdentifier(DatastoreIdentifier name) {
      return (ColumnMetaData)this.columnMetaDataBySideBIdentifier.get(name);
   }

   private void putColumn(DatastoreIdentifier identifier, ColumnMetaData colmd) {
      if (this.columnMetaDataBySideBIdentifier.put(identifier, colmd) != null) {
         throw (new NucleusUserException(Localiser.msg("020006", new Object[]{identifier, this.columnsName}))).setFatal();
      }
   }
}
