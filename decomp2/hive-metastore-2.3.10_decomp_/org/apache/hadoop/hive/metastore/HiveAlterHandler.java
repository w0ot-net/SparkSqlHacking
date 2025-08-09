package org.apache.hadoop.hive.metastore;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.AlreadyExistsException;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsDesc;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.InvalidInputException;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.events.AlterPartitionEvent;
import org.apache.hadoop.hive.metastore.events.AlterTableEvent;
import org.apache.hadoop.hive.metastore.messaging.EventMessage;
import org.apache.hadoop.ipc.RemoteException;
import org.apache.hive.common.util.HiveStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveAlterHandler implements AlterHandler {
   protected Configuration hiveConf;
   private static final Logger LOG = LoggerFactory.getLogger(HiveAlterHandler.class.getName());

   public Configuration getConf() {
      return this.hiveConf;
   }

   public void setConf(Configuration conf) {
      this.hiveConf = conf;
   }

   public void alterTable(RawStore msdb, Warehouse wh, String dbname, String name, Table newt, EnvironmentContext environmentContext) throws InvalidOperationException, MetaException {
      this.alterTable(msdb, wh, dbname, name, newt, environmentContext, (HiveMetaStore.HMSHandler)null);
   }

   public void alterTable(RawStore msdb, Warehouse wh, String dbname, String name, Table newt, EnvironmentContext environmentContext, HiveMetaStore.HMSHandler handler) throws InvalidOperationException, MetaException {
      boolean cascade = environmentContext != null && environmentContext.isSetProperties() && "true".equals(environmentContext.getProperties().get("CASCADE"));
      if (newt == null) {
         throw new InvalidOperationException("New table is invalid: " + newt);
      } else if (!MetaStoreUtils.validateName(newt.getTableName(), this.hiveConf)) {
         throw new InvalidOperationException(newt.getTableName() + " is not a valid object name");
      } else {
         String validate = MetaStoreUtils.validateTblColumns(newt.getSd().getCols());
         if (validate != null) {
            throw new InvalidOperationException("Invalid column " + validate);
         } else {
            Path srcPath = null;
            FileSystem srcFs = null;
            Path destPath = null;
            FileSystem destFs = null;
            boolean success = false;
            boolean dataWasMoved = false;
            boolean rename = false;
            Table oldt = null;
            List<MetaStoreEventListener> transactionalListeners = null;
            List<MetaStoreEventListener> listeners = null;
            Map<String, String> txnAlterTableEventResponses = Collections.emptyMap();
            if (handler != null) {
               transactionalListeners = handler.getTransactionalListeners();
               listeners = handler.getListeners();
            }

            try {
               String expectedKey = environmentContext != null && environmentContext.getProperties() != null ? (String)environmentContext.getProperties().get("expected_parameter_key") : null;
               String expectedValue = environmentContext != null && environmentContext.getProperties() != null ? (String)environmentContext.getProperties().get("expected_parameter_value") : null;
               msdb.openTransaction();
               name = name.toLowerCase();
               dbname = dbname.toLowerCase();
               if (!newt.getTableName().equalsIgnoreCase(name) || !newt.getDbName().equalsIgnoreCase(dbname)) {
                  if (msdb.getTable(newt.getDbName(), newt.getTableName()) != null) {
                     throw new InvalidOperationException("new table " + newt.getDbName() + "." + newt.getTableName() + " already exists");
                  }

                  rename = true;
               }

               oldt = msdb.getTable(dbname, name);
               if (oldt == null) {
                  throw new InvalidOperationException("table " + dbname + "." + name + " doesn't exist");
               }

               if (expectedKey != null && expectedValue != null) {
                  String newValue = (String)newt.getParameters().get(expectedKey);
                  if (newValue == null) {
                     throw new MetaException(String.format("New value for expected key %s is not set", expectedKey));
                  }

                  if (!expectedValue.equals(oldt.getParameters().get(expectedKey))) {
                     throw new MetaException("The table has been modified. The parameter value for key '" + expectedKey + "' is '" + (String)oldt.getParameters().get(expectedKey) + "'. The expected was value was '" + expectedValue + "'");
                  }

                  long affectedRows = msdb.updateParameterWithExpectedValue(oldt, expectedKey, expectedValue, newValue);
                  if (affectedRows != 1L) {
                     throw new MetaException("The table has been modified. The parameter value for key '" + expectedKey + "' is different");
                  }
               }

               if (HiveConf.getBoolVar(this.hiveConf, ConfVars.METASTORE_DISALLOW_INCOMPATIBLE_COL_TYPE_CHANGES, false) && !oldt.getTableType().equals(TableType.VIRTUAL_VIEW.toString())) {
                  MetaStoreUtils.throwExceptionIfIncompatibleColTypeChange(oldt.getSd().getCols(), newt.getSd().getCols());
               }

               if (cascade) {
                  if (MetaStoreUtils.isCascadeNeededInAlterTable(oldt, newt)) {
                     for(Partition part : msdb.getPartitions(dbname, name, -1)) {
                        List<FieldSchema> oldCols = part.getSd().getCols();
                        part.getSd().setCols(newt.getSd().getCols());
                        String oldPartName = Warehouse.makePartName(oldt.getPartitionKeys(), part.getValues());
                        this.updatePartColumnStatsForAlterColumns(msdb, part, oldPartName, part.getValues(), oldCols, part);
                        msdb.alterPartition(dbname, name, part.getValues(), part);
                     }
                  } else {
                     LOG.warn("Alter table does not cascade changes to its partitions.");
                  }
               }

               boolean partKeysPartiallyEqual = this.checkPartialPartKeysEqual(oldt.getPartitionKeys(), newt.getPartitionKeys());
               if (!oldt.getTableType().equals(TableType.VIRTUAL_VIEW.toString()) && (oldt.getPartitionKeys().size() != newt.getPartitionKeys().size() || !partKeysPartiallyEqual)) {
                  throw new InvalidOperationException("partition keys can not be changed.");
               }

               if (rename && !oldt.getTableType().equals(TableType.VIRTUAL_VIEW.toString()) && (oldt.getSd().getLocation().compareTo(newt.getSd().getLocation()) == 0 || StringUtils.isEmpty(newt.getSd().getLocation())) && !MetaStoreUtils.isExternalTable(oldt)) {
                  Database olddb = msdb.getDatabase(dbname);
                  srcPath = new Path(oldt.getSd().getLocation());
                  String oldtRelativePath = (new Path(olddb.getLocationUri())).toUri().relativize(srcPath.toUri()).toString();
                  boolean tableInSpecifiedLoc = !oldtRelativePath.equalsIgnoreCase(name) && !oldtRelativePath.equalsIgnoreCase(name + "/");
                  if (!tableInSpecifiedLoc) {
                     srcFs = wh.getFs(srcPath);
                     Database db = msdb.getDatabase(newt.getDbName());
                     Path databasePath = this.constructRenamedPath(wh.getDatabasePath(db), srcPath);
                     destPath = new Path(databasePath, newt.getTableName().toLowerCase());
                     destFs = wh.getFs(destPath);
                     newt.getSd().setLocation(destPath.toString());
                     if (!FileUtils.equalsFileSystem(srcFs, destFs)) {
                        throw new InvalidOperationException("table new location " + destPath + " is on a different file system than the old location " + srcPath + ". This operation is not supported");
                     }

                     try {
                        if (destFs.exists(destPath)) {
                           throw new InvalidOperationException("New location for this table " + newt.getDbName() + "." + newt.getTableName() + " already exists : " + destPath);
                        }

                        if (srcFs.exists(srcPath) && srcFs.rename(srcPath, destPath)) {
                           dataWasMoved = true;
                        }
                     } catch (IOException e) {
                        LOG.error("Alter Table operation for " + dbname + "." + name + " failed.", e);
                        throw new InvalidOperationException("Alter Table operation for " + dbname + "." + name + " failed to move data due to: '" + this.getSimpleMessage(e) + "' See hive log file for details.");
                     }

                     String oldTblLocPath = srcPath.toUri().getPath();
                     String newTblLocPath = destPath.toUri().getPath();

                     for(Partition part : msdb.getPartitions(dbname, name, -1)) {
                        String oldPartLoc = part.getSd().getLocation();
                        if (oldPartLoc.contains(oldTblLocPath)) {
                           URI oldUri = (new Path(oldPartLoc)).toUri();
                           String newPath = oldUri.getPath().replace(oldTblLocPath, newTblLocPath);
                           Path newPartLocPath = new Path(oldUri.getScheme(), oldUri.getAuthority(), newPath);
                           part.getSd().setLocation(newPartLocPath.toString());
                           String oldPartName = Warehouse.makePartName(oldt.getPartitionKeys(), part.getValues());

                           try {
                              msdb.deletePartitionColumnStatistics(dbname, name, oldPartName, part.getValues(), (String)null);
                           } catch (InvalidInputException iie) {
                              throw new InvalidOperationException("Unable to update partition stats in table rename." + iie);
                           }

                           msdb.alterPartition(dbname, name, part.getValues(), part);
                        }
                     }
                  }
               } else if (MetaStoreUtils.requireCalStats(this.hiveConf, (Partition)null, (Partition)null, newt, environmentContext) && newt.getPartitionKeysSize() == 0) {
                  Database db = msdb.getDatabase(newt.getDbName());
                  MetaStoreUtils.updateTableStatsFast(db, newt, wh, false, true, environmentContext);
               }

               this.alterTableUpdateTableColumnStats(msdb, oldt, newt);
               if (transactionalListeners != null && !transactionalListeners.isEmpty()) {
                  txnAlterTableEventResponses = MetaStoreListenerNotifier.notifyEvent(transactionalListeners, EventMessage.EventType.ALTER_TABLE, new AlterTableEvent(oldt, newt, true, handler), environmentContext);
               }

               success = msdb.commitTransaction();
            } catch (InvalidObjectException e) {
               LOG.debug("Failed to get object from Metastore ", e);
               throw new InvalidOperationException("Unable to change partition or table. Check metastore logs for detailed stack." + e.getMessage());
            } catch (NoSuchObjectException e) {
               LOG.debug("Object not found in metastore ", e);
               throw new InvalidOperationException("Unable to change partition or table. Database " + dbname + " does not exist Check metastore logs for detailed stack." + e.getMessage());
            } finally {
               if (!success) {
                  LOG.error("Failed to alter table " + dbname + "." + name);
                  msdb.rollbackTransaction();
                  if (dataWasMoved) {
                     try {
                        if (destFs.exists(destPath) && !destFs.rename(destPath, srcPath)) {
                           LOG.error("Failed to restore data from " + destPath + " to " + srcPath + " in alter table failure. Manual restore is needed.");
                        }
                     } catch (IOException var49) {
                        LOG.error("Failed to restore data from " + destPath + " to " + srcPath + " in alter table failure. Manual restore is needed.");
                     }
                  }
               }

               if (!listeners.isEmpty()) {
                  MetaStoreListenerNotifier.notifyEvent(listeners, EventMessage.EventType.ALTER_TABLE, new AlterTableEvent(oldt, newt, success, handler), environmentContext, txnAlterTableEventResponses, msdb);
               }

            }

         }
      }
   }

   String getSimpleMessage(IOException ex) {
      if (ex instanceof RemoteException) {
         String msg = ex.getMessage();
         return msg != null && msg.contains("\n") ? msg.substring(0, msg.indexOf(10)) : msg;
      } else {
         return ex.getMessage();
      }
   }

   public Partition alterPartition(RawStore msdb, Warehouse wh, String dbname, String name, List part_vals, Partition new_part, EnvironmentContext environmentContext) throws InvalidOperationException, InvalidObjectException, AlreadyExistsException, MetaException {
      return this.alterPartition(msdb, wh, dbname, name, part_vals, new_part, environmentContext, (HiveMetaStore.HMSHandler)null);
   }

   public Partition alterPartition(RawStore msdb, Warehouse wh, String dbname, String name, List part_vals, Partition new_part, EnvironmentContext environmentContext, HiveMetaStore.HMSHandler handler) throws InvalidOperationException, InvalidObjectException, AlreadyExistsException, MetaException {
      boolean success = false;
      Path srcPath = null;
      Path destPath = null;
      FileSystem srcFs = null;
      Partition oldPart = null;
      String oldPartLoc = null;
      String newPartLoc = null;
      List<MetaStoreEventListener> transactionalListeners = null;
      if (handler != null) {
         transactionalListeners = handler.getTransactionalListeners();
      }

      if (new_part.getParameters() == null || new_part.getParameters().get("transient_lastDdlTime") == null || Integer.parseInt((String)new_part.getParameters().get("transient_lastDdlTime")) == 0) {
         new_part.putToParameters("transient_lastDdlTime", Long.toString(System.currentTimeMillis() / 1000L));
      }

      Table tbl = msdb.getTable(dbname, name);
      if (tbl == null) {
         throw new InvalidObjectException("Unable to alter partition because table or database does not exist.");
      } else if (part_vals != null && part_vals.size() != 0) {
         boolean var58 = false;

         try {
            var58 = true;
            msdb.openTransaction();

            try {
               oldPart = msdb.getPartition(dbname, name, part_vals);
            } catch (NoSuchObjectException var65) {
               throw new InvalidObjectException("Unable to rename partition because old partition does not exist");
            }

            Partition check_part;
            try {
               check_part = msdb.getPartition(dbname, name, new_part.getValues());
            } catch (NoSuchObjectException var64) {
               check_part = null;
            }

            if (check_part != null) {
               throw new AlreadyExistsException("Partition already exists:" + dbname + "." + name + "." + new_part.getValues());
            }

            if (tbl.getTableType().equals(TableType.EXTERNAL_TABLE.toString())) {
               new_part.getSd().setLocation(oldPart.getSd().getLocation());
               String oldPartName = Warehouse.makePartName(tbl.getPartitionKeys(), oldPart.getValues());

               try {
                  msdb.deletePartitionColumnStatistics(dbname, name, oldPartName, oldPart.getValues(), (String)null);
               } catch (NoSuchObjectException var62) {
               } catch (InvalidInputException iie) {
                  throw new InvalidOperationException("Unable to update partition stats in table rename." + iie);
               }

               msdb.alterPartition(dbname, name, part_vals, new_part);
            } else {
               try {
                  destPath = wh.getPartitionPath(msdb.getDatabase(dbname), tbl, new_part.getValues());
                  destPath = this.constructRenamedPath(destPath, new Path(new_part.getSd().getLocation()));
               } catch (NoSuchObjectException e) {
                  LOG.debug("Didn't find object in metastore ", e);
                  throw new InvalidOperationException("Unable to change partition or table. Database " + dbname + " does not exist Check metastore logs for detailed stack." + e.getMessage());
               }

               if (destPath != null) {
                  newPartLoc = destPath.toString();
                  oldPartLoc = oldPart.getSd().getLocation();
                  LOG.info("srcPath:" + oldPartLoc);
                  LOG.info("descPath:" + newPartLoc);
                  srcPath = new Path(oldPartLoc);
                  srcFs = wh.getFs(srcPath);
                  FileSystem destFs = wh.getFs(destPath);
                  if (!FileUtils.equalsFileSystem(srcFs, destFs)) {
                     throw new InvalidOperationException("New table location " + destPath + " is on a different file system than the old location " + srcPath + ". This operation is not supported.");
                  }

                  try {
                     srcFs.exists(srcPath);
                     if (newPartLoc.compareTo(oldPartLoc) != 0 && destFs.exists(destPath)) {
                        throw new InvalidOperationException("New location for this table " + tbl.getDbName() + "." + tbl.getTableName() + " already exists : " + destPath);
                     }
                  } catch (IOException var70) {
                     throw new InvalidOperationException("Unable to access new location " + destPath + " for partition " + tbl.getDbName() + "." + tbl.getTableName() + " " + new_part.getValues());
                  }

                  new_part.getSd().setLocation(newPartLoc);
                  if (MetaStoreUtils.requireCalStats(this.hiveConf, oldPart, new_part, tbl, environmentContext)) {
                     MetaStoreUtils.updatePartitionStatsFast(new_part, wh, false, true, environmentContext);
                  }

                  String oldPartName = Warehouse.makePartName(tbl.getPartitionKeys(), oldPart.getValues());

                  try {
                     msdb.deletePartitionColumnStatistics(dbname, name, oldPartName, oldPart.getValues(), (String)null);
                  } catch (NoSuchObjectException var59) {
                  } catch (InvalidInputException iie) {
                     throw new InvalidOperationException("Unable to update partition stats in table rename." + iie);
                  }

                  msdb.alterPartition(dbname, name, part_vals, new_part);
               }
            }

            if (transactionalListeners != null && !transactionalListeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(transactionalListeners, EventMessage.EventType.ALTER_PARTITION, new AlterPartitionEvent(oldPart, new_part, tbl, true, handler), environmentContext);
            }

            success = msdb.commitTransaction();
            var58 = false;
         } finally {
            if (var58) {
               if (!success) {
                  msdb.rollbackTransaction();
               }

               if (success && newPartLoc != null && newPartLoc.compareTo(oldPartLoc) != 0) {
                  try {
                     if (srcFs.exists(srcPath)) {
                        Path destParentPath = destPath.getParent();
                        if (!wh.mkdirs(destParentPath, true)) {
                           throw new IOException("Unable to create path " + destParentPath);
                        }

                        wh.renameDir(srcPath, destPath, true);
                        LOG.info("Partition directory rename from " + srcPath + " to " + destPath + " done.");
                     }
                  } catch (IOException ex) {
                     LOG.error("Cannot rename partition directory from " + srcPath + " to " + destPath, ex);
                     boolean revertMetaDataTransaction = false;

                     try {
                        msdb.openTransaction();
                        msdb.alterPartition(dbname, name, new_part.getValues(), oldPart);
                        if (transactionalListeners != null && !transactionalListeners.isEmpty()) {
                           MetaStoreListenerNotifier.notifyEvent(transactionalListeners, EventMessage.EventType.ALTER_PARTITION, new AlterPartitionEvent(new_part, oldPart, tbl, success, handler), environmentContext);
                        }

                        revertMetaDataTransaction = msdb.commitTransaction();
                     } catch (Exception ex2) {
                        LOG.error("Attempt to revert partition metadata change failed. The revert was attempted because associated filesystem rename operation failed with exception " + ex.getMessage(), ex2);
                        if (!revertMetaDataTransaction) {
                           msdb.rollbackTransaction();
                        }
                     }

                     throw new InvalidOperationException("Unable to access old location " + srcPath + " for partition " + tbl.getDbName() + "." + tbl.getTableName() + " " + part_vals);
                  }
               }

            }
         }

         if (!success) {
            msdb.rollbackTransaction();
         }

         if (success && newPartLoc != null && newPartLoc.compareTo(oldPartLoc) != 0) {
            try {
               if (srcFs.exists(srcPath)) {
                  Path destParentPath = destPath.getParent();
                  if (!wh.mkdirs(destParentPath, true)) {
                     throw new IOException("Unable to create path " + destParentPath);
                  }

                  wh.renameDir(srcPath, destPath, true);
                  LOG.info("Partition directory rename from " + srcPath + " to " + destPath + " done.");
               }
            } catch (IOException ex) {
               LOG.error("Cannot rename partition directory from " + srcPath + " to " + destPath, ex);
               boolean revertMetaDataTransaction = false;

               try {
                  msdb.openTransaction();
                  msdb.alterPartition(dbname, name, new_part.getValues(), oldPart);
                  if (transactionalListeners != null && !transactionalListeners.isEmpty()) {
                     MetaStoreListenerNotifier.notifyEvent(transactionalListeners, EventMessage.EventType.ALTER_PARTITION, new AlterPartitionEvent(new_part, oldPart, tbl, success, handler), environmentContext);
                  }

                  revertMetaDataTransaction = msdb.commitTransaction();
               } catch (Exception ex2) {
                  LOG.error("Attempt to revert partition metadata change failed. The revert was attempted because associated filesystem rename operation failed with exception " + ex.getMessage(), ex2);
                  if (!revertMetaDataTransaction) {
                     msdb.rollbackTransaction();
                  }
               }

               throw new InvalidOperationException("Unable to access old location " + srcPath + " for partition " + tbl.getDbName() + "." + tbl.getTableName() + " " + part_vals);
            }
         }

         return oldPart;
      } else {
         try {
            msdb.openTransaction();
            oldPart = msdb.getPartition(dbname, name, new_part.getValues());
            if (MetaStoreUtils.requireCalStats(this.hiveConf, oldPart, new_part, tbl, environmentContext)) {
               if (MetaStoreUtils.isFastStatsSame(oldPart, new_part)) {
                  MetaStoreUtils.updateBasicState(environmentContext, new_part.getParameters());
               } else {
                  MetaStoreUtils.updatePartitionStatsFast(new_part, wh, false, true, environmentContext);
               }
            }

            this.updatePartColumnStats(msdb, dbname, name, new_part.getValues(), new_part);
            msdb.alterPartition(dbname, name, new_part.getValues(), new_part);
            if (transactionalListeners != null && !transactionalListeners.isEmpty()) {
               MetaStoreListenerNotifier.notifyEvent(transactionalListeners, EventMessage.EventType.ALTER_PARTITION, new AlterPartitionEvent(oldPart, new_part, tbl, true, handler), environmentContext);
            }

            success = msdb.commitTransaction();
         } catch (InvalidObjectException var72) {
            throw new InvalidOperationException("alter is not possible");
         } catch (NoSuchObjectException var73) {
            throw new InvalidOperationException("alter is not possible");
         } finally {
            if (!success) {
               msdb.rollbackTransaction();
            }

         }

         return oldPart;
      }
   }

   public List alterPartitions(RawStore msdb, Warehouse wh, String dbname, String name, List new_parts, EnvironmentContext environmentContext) throws InvalidOperationException, InvalidObjectException, AlreadyExistsException, MetaException {
      return this.alterPartitions(msdb, wh, dbname, name, new_parts, environmentContext, (HiveMetaStore.HMSHandler)null);
   }

   public List alterPartitions(RawStore msdb, Warehouse wh, String dbname, String name, List new_parts, EnvironmentContext environmentContext, HiveMetaStore.HMSHandler handler) throws InvalidOperationException, InvalidObjectException, AlreadyExistsException, MetaException {
      List<Partition> oldParts = new ArrayList();
      List<List<String>> partValsList = new ArrayList();
      List<MetaStoreEventListener> transactionalListeners = null;
      if (handler != null) {
         transactionalListeners = handler.getTransactionalListeners();
      }

      Table tbl = msdb.getTable(dbname, name);
      if (tbl == null) {
         throw new InvalidObjectException("Unable to alter partitions because table or database does not exist.");
      } else {
         boolean success = false;

         try {
            msdb.openTransaction();

            for(Partition tmpPart : new_parts) {
               if (tmpPart.getParameters() == null || tmpPart.getParameters().get("transient_lastDdlTime") == null || Integer.parseInt((String)tmpPart.getParameters().get("transient_lastDdlTime")) == 0) {
                  tmpPart.putToParameters("transient_lastDdlTime", Long.toString(System.currentTimeMillis() / 1000L));
               }

               Partition oldTmpPart = msdb.getPartition(dbname, name, tmpPart.getValues());
               oldParts.add(oldTmpPart);
               partValsList.add(tmpPart.getValues());
               if (MetaStoreUtils.requireCalStats(this.hiveConf, oldTmpPart, tmpPart, tbl, environmentContext)) {
                  if (MetaStoreUtils.isFastStatsSame(oldTmpPart, tmpPart)) {
                     MetaStoreUtils.updateBasicState(environmentContext, tmpPart.getParameters());
                  } else {
                     MetaStoreUtils.updatePartitionStatsFast(tmpPart, wh, false, true, environmentContext);
                  }
               }

               this.updatePartColumnStats(msdb, dbname, name, oldTmpPart.getValues(), tmpPart);
            }

            msdb.alterPartitions(dbname, name, partValsList, new_parts);
            Iterator<Partition> oldPartsIt = oldParts.iterator();

            for(Partition newPart : new_parts) {
               if (!oldPartsIt.hasNext()) {
                  throw new InvalidOperationException("Missing old partition corresponding to new partition when invoking MetaStoreEventListener for alterPartitions event.");
               }

               Partition oldPart = (Partition)oldPartsIt.next();
               if (transactionalListeners != null && !transactionalListeners.isEmpty()) {
                  MetaStoreListenerNotifier.notifyEvent(transactionalListeners, EventMessage.EventType.ALTER_PARTITION, new AlterPartitionEvent(oldPart, newPart, tbl, true, handler));
               }
            }

            success = msdb.commitTransaction();
         } catch (NoSuchObjectException | InvalidObjectException e) {
            throw new InvalidOperationException("Alter partition operation failed: " + e);
         } finally {
            if (!success) {
               msdb.rollbackTransaction();
            }

         }

         return oldParts;
      }
   }

   private boolean checkPartialPartKeysEqual(List oldPartKeys, List newPartKeys) {
      if (newPartKeys != null && oldPartKeys != null) {
         if (oldPartKeys.size() != newPartKeys.size()) {
            return false;
         } else {
            Iterator<FieldSchema> oldPartKeysIter = oldPartKeys.iterator();
            Iterator<FieldSchema> newPartKeysIter = newPartKeys.iterator();

            while(oldPartKeysIter.hasNext()) {
               FieldSchema oldFs = (FieldSchema)oldPartKeysIter.next();
               FieldSchema newFs = (FieldSchema)newPartKeysIter.next();
               if (!oldFs.getName().equals(newFs.getName())) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return oldPartKeys == newPartKeys;
      }
   }

   private Path constructRenamedPath(Path defaultNewPath, Path currentPath) {
      URI currentUri = currentPath.toUri();
      return new Path(currentUri.getScheme(), currentUri.getAuthority(), defaultNewPath.toUri().getPath());
   }

   private void updatePartColumnStatsForAlterColumns(RawStore msdb, Partition oldPartition, String oldPartName, List partVals, List oldCols, Partition newPart) throws MetaException, InvalidObjectException {
      String dbName = oldPartition.getDbName();
      String tableName = oldPartition.getTableName();

      try {
         List<String> oldPartNames = Lists.newArrayList(new String[]{oldPartName});
         List<String> oldColNames = new ArrayList(oldCols.size());

         for(FieldSchema oldCol : oldCols) {
            oldColNames.add(oldCol.getName());
         }

         List<FieldSchema> newCols = newPart.getSd().getCols();
         List<ColumnStatistics> partsColStats = msdb.getPartitionColumnStatistics(dbName, tableName, oldPartNames, oldColNames);

         assert partsColStats.size() <= 1;

         for(ColumnStatistics partColStats : partsColStats) {
            List<ColumnStatisticsObj> statsObjs = partColStats.getStatsObj();
            List<String> deletedCols = new ArrayList();

            for(ColumnStatisticsObj statsObj : statsObjs) {
               boolean found = false;

               for(FieldSchema newCol : newCols) {
                  if (statsObj.getColName().equalsIgnoreCase(newCol.getName()) && statsObj.getColType().equalsIgnoreCase(newCol.getType())) {
                     found = true;
                     break;
                  }
               }

               if (!found) {
                  msdb.deletePartitionColumnStatistics(dbName, tableName, oldPartName, partVals, statsObj.getColName());
                  deletedCols.add(statsObj.getColName());
               }
            }

            StatsSetupConst.removeColumnStatsState(newPart.getParameters(), deletedCols);
         }
      } catch (NoSuchObjectException nsoe) {
         LOG.debug("Could not find db entry." + nsoe);
      } catch (InvalidInputException iie) {
         throw new InvalidObjectException("Invalid input to update partition column stats in alter table change columns" + iie);
      }

   }

   private void updatePartColumnStats(RawStore msdb, String dbName, String tableName, List partVals, Partition newPart) throws MetaException, InvalidObjectException {
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      tableName = HiveStringUtils.normalizeIdentifier(tableName);
      String newDbName = HiveStringUtils.normalizeIdentifier(newPart.getDbName());
      String newTableName = HiveStringUtils.normalizeIdentifier(newPart.getTableName());
      Table oldTable = msdb.getTable(dbName, tableName);
      if (oldTable != null) {
         try {
            String oldPartName = Warehouse.makePartName(oldTable.getPartitionKeys(), partVals);
            String newPartName = Warehouse.makePartName(oldTable.getPartitionKeys(), newPart.getValues());
            if (dbName.equals(newDbName) && tableName.equals(newTableName) && oldPartName.equals(newPartName)) {
               Partition oldPartition = msdb.getPartition(dbName, tableName, partVals);
               if (oldPartition == null) {
                  return;
               }

               if (oldPartition.getSd() != null && newPart.getSd() != null) {
                  List<FieldSchema> oldCols = oldPartition.getSd().getCols();
                  if (!MetaStoreUtils.columnsIncluded(oldCols, newPart.getSd().getCols())) {
                     this.updatePartColumnStatsForAlterColumns(msdb, oldPartition, oldPartName, partVals, oldCols, newPart);
                  }
               }
            } else {
               msdb.deletePartitionColumnStatistics(dbName, tableName, oldPartName, partVals, (String)null);
            }
         } catch (NoSuchObjectException nsoe) {
            LOG.debug("Could not find db entry." + nsoe);
         } catch (InvalidInputException iie) {
            throw new InvalidObjectException("Invalid input to update partition column stats." + iie);
         }

      }
   }

   @VisibleForTesting
   void alterTableUpdateTableColumnStats(RawStore msdb, Table oldTable, Table newTable) throws MetaException, InvalidObjectException {
      String dbName = oldTable.getDbName().toLowerCase();
      String tableName = HiveStringUtils.normalizeIdentifier(oldTable.getTableName());
      String newDbName = newTable.getDbName().toLowerCase();
      String newTableName = HiveStringUtils.normalizeIdentifier(newTable.getTableName());

      try {
         List<FieldSchema> oldCols = oldTable.getSd().getCols();
         List<FieldSchema> newCols = newTable.getSd().getCols();
         List<ColumnStatisticsObj> newStatsObjs = new ArrayList();
         ColumnStatistics colStats = null;
         boolean updateColumnStats = true;
         if (newDbName.equals(dbName) && newTableName.equals(tableName) && MetaStoreUtils.columnsIncluded(oldCols, newCols)) {
            updateColumnStats = false;
         }

         if (updateColumnStats) {
            List<String> oldColNames = new ArrayList(oldCols.size());

            for(FieldSchema oldCol : oldCols) {
               oldColNames.add(oldCol.getName());
            }

            colStats = msdb.getTableColumnStatistics(dbName, tableName, oldColNames);
            if (colStats == null) {
               updateColumnStats = false;
            } else {
               List<ColumnStatisticsObj> statsObjs = colStats.getStatsObj();
               if (statsObjs != null) {
                  List<String> deletedCols = new ArrayList();

                  for(ColumnStatisticsObj statsObj : statsObjs) {
                     boolean found = false;

                     for(FieldSchema newCol : newCols) {
                        if (statsObj.getColName().equalsIgnoreCase(newCol.getName()) && statsObj.getColType().equalsIgnoreCase(newCol.getType())) {
                           found = true;
                           break;
                        }
                     }

                     if (found) {
                        if (!newDbName.equals(dbName) || !newTableName.equals(tableName)) {
                           msdb.deleteTableColumnStatistics(dbName, tableName, statsObj.getColName());
                           newStatsObjs.add(statsObj);
                           deletedCols.add(statsObj.getColName());
                        }
                     } else {
                        msdb.deleteTableColumnStatistics(dbName, tableName, statsObj.getColName());
                        deletedCols.add(statsObj.getColName());
                     }
                  }

                  StatsSetupConst.removeColumnStatsState(newTable.getParameters(), deletedCols);
               }
            }
         }

         msdb.alterTable(dbName, tableName, newTable);
         if (updateColumnStats && !newStatsObjs.isEmpty()) {
            ColumnStatisticsDesc statsDesc = colStats.getStatsDesc();
            statsDesc.setDbName(newDbName);
            statsDesc.setTableName(newTableName);
            colStats.setStatsObj(newStatsObjs);
            msdb.updateTableColumnStatistics(colStats);
         }
      } catch (NoSuchObjectException nsoe) {
         LOG.debug("Could not find db entry." + nsoe);
      } catch (InvalidInputException e) {
         throw new InvalidObjectException("Invalid inputs to update table column stats: " + e);
      }

   }
}
