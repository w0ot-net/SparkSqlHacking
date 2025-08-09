package org.datanucleus.store.rdbms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.AbstractPersistenceHandler;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.rdbms.fieldmanager.DynamicSchemaFieldManager;
import org.datanucleus.store.rdbms.request.DeleteRequest;
import org.datanucleus.store.rdbms.request.FetchRequest;
import org.datanucleus.store.rdbms.request.InsertRequest;
import org.datanucleus.store.rdbms.request.LocateBulkRequest;
import org.datanucleus.store.rdbms.request.LocateRequest;
import org.datanucleus.store.rdbms.request.Request;
import org.datanucleus.store.rdbms.request.RequestIdentifier;
import org.datanucleus.store.rdbms.request.RequestType;
import org.datanucleus.store.rdbms.request.UpdateRequest;
import org.datanucleus.store.rdbms.table.ClassView;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.SecondaryDatastoreClass;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.SoftValueMap;

public class RDBMSPersistenceHandler extends AbstractPersistenceHandler {
   private Map requestsByID = Collections.synchronizedMap(new SoftValueMap());

   public RDBMSPersistenceHandler(StoreManager storeMgr) {
      super(storeMgr);
   }

   public void close() {
      this.requestsByID.clear();
      this.requestsByID = null;
   }

   private DatastoreClass getDatastoreClass(String className, ClassLoaderResolver clr) {
      return ((RDBMSStoreManager)this.storeMgr).getDatastoreClass(className, clr);
   }

   public void insertObjects(ObjectProvider... ops) {
      super.insertObjects(ops);
   }

   public void insertObject(ObjectProvider op) {
      this.assertReadOnlyForUpdateOfObject(op);
      this.checkForSchemaUpdatesForFieldsOfObject(op, op.getLoadedFieldNumbers());
      ExecutionContext ec = op.getExecutionContext();
      ClassLoaderResolver clr = op.getExecutionContext().getClassLoaderResolver();
      String className = op.getClassMetaData().getFullClassName();
      DatastoreClass dc = this.getDatastoreClass(className, clr);
      if (dc == null) {
         if (op.getClassMetaData().getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
            throw new NucleusUserException(Localiser.msg("032013", new Object[]{className}));
         } else {
            throw (new NucleusException(Localiser.msg("032014", new Object[]{className, op.getClassMetaData().getInheritanceMetaData().getStrategy()}))).setFatal();
         }
      } else {
         if (ec.getStatistics() != null) {
            ec.getStatistics().incrementInsertCount();
         }

         this.insertObjectInTable(dc, op, clr);
      }
   }

   private void insertObjectInTable(DatastoreClass table, ObjectProvider op, ClassLoaderResolver clr) {
      if (table instanceof ClassView) {
         throw new NucleusUserException("Cannot perform InsertRequest on RDBMS view " + table);
      } else {
         DatastoreClass supertable = table.getSuperDatastoreClass();
         if (supertable != null) {
            this.insertObjectInTable(supertable, op, clr);
         }

         this.getInsertRequest(table, op.getClassMetaData(), clr).execute(op);
         Collection<SecondaryDatastoreClass> secondaryTables = table.getSecondaryDatastoreClasses();
         if (secondaryTables != null) {
            Iterator<SecondaryDatastoreClass> tablesIter = secondaryTables.iterator();

            while(tablesIter.hasNext()) {
               this.insertObjectInTable((DatastoreClass)tablesIter.next(), op, clr);
            }
         }

      }
   }

   private Request getInsertRequest(DatastoreClass table, AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      RequestIdentifier reqID = new RequestIdentifier(table, (AbstractMemberMetaData[])null, RequestType.INSERT, cmd.getFullClassName());
      Request req = (Request)this.requestsByID.get(reqID);
      if (req == null) {
         req = new InsertRequest(table, cmd, clr);
         this.requestsByID.put(reqID, req);
      }

      return req;
   }

   public void fetchObject(ObjectProvider op, int[] memberNumbers) {
      ExecutionContext ec = op.getExecutionContext();
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      AbstractMemberMetaData[] mmds = null;
      if (memberNumbers != null && memberNumbers.length > 0) {
         int[] memberNumbersToProcess = memberNumbers;
         AbstractClassMetaData cmd = op.getClassMetaData();
         if (this.storeMgr.getBooleanProperty("datanucleus.rdbms.fetchUnloadedAutomatically") && !op.getLifecycleState().isDeleted()) {
            boolean fetchPerformsSelect = false;

            for(int i = 0; i < memberNumbers.length; ++i) {
               AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(memberNumbers[i]);
               RelationType relationType = mmd.getRelationType(clr);
               if (relationType != RelationType.ONE_TO_MANY_UNI && relationType != RelationType.ONE_TO_MANY_BI && relationType != RelationType.MANY_TO_MANY_BI) {
                  fetchPerformsSelect = true;
                  break;
               }
            }

            if (fetchPerformsSelect) {
               List<Integer> memberNumberList = new ArrayList();

               for(int i = 0; i < memberNumbers.length; ++i) {
                  memberNumberList.add(memberNumbers[i]);
               }

               boolean[] loadedFlags = op.getLoadedFields();

               for(int i = 0; i < loadedFlags.length; ++i) {
                  boolean requested = false;

                  for(int j = 0; j < memberNumbers.length; ++j) {
                     if (memberNumbers[j] == i) {
                        requested = true;
                        break;
                     }
                  }

                  if (!requested && !loadedFlags[i]) {
                     AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(i);
                     RelationType relType = mmd.getRelationType(clr);
                     if (relType == RelationType.NONE || relType == RelationType.ONE_TO_ONE_BI || relType == RelationType.ONE_TO_ONE_UNI) {
                        memberNumberList.add(i);
                     }
                  }
               }

               memberNumbersToProcess = new int[memberNumberList.size()];
               int i = 0;

               for(Iterator<Integer> fieldNumberIter = memberNumberList.iterator(); fieldNumberIter.hasNext(); memberNumbersToProcess[i++] = (Integer)fieldNumberIter.next()) {
               }
            }
         }

         mmds = new AbstractMemberMetaData[memberNumbersToProcess.length];

         for(int i = 0; i < mmds.length; ++i) {
            mmds[i] = cmd.getMetaDataForManagedMemberAtAbsolutePosition(memberNumbersToProcess[i]);
         }
      }

      if (op.isEmbedded()) {
         StringBuilder str = new StringBuilder();
         if (mmds != null) {
            for(int i = 0; i < mmds.length; ++i) {
               if (i > 0) {
                  str.append(',');
               }

               str.append(mmds[i].getName());
            }
         }

         NucleusLogger.PERSISTENCE.info("Request to load fields \"" + str.toString() + "\" of class " + op.getClassMetaData().getFullClassName() + " but object is embedded, so ignored");
      } else {
         if (ec.getStatistics() != null) {
            ec.getStatistics().incrementFetchCount();
         }

         DatastoreClass table = this.getDatastoreClass(op.getClassMetaData().getFullClassName(), clr);
         Request req = this.getFetchRequest(table, mmds, op.getClassMetaData(), clr);
         req.execute(op);
      }

   }

   private Request getFetchRequest(DatastoreClass table, AbstractMemberMetaData[] mmds, AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      RequestIdentifier reqID = new RequestIdentifier(table, mmds, RequestType.FETCH, cmd.getFullClassName());
      Request req = (Request)this.requestsByID.get(reqID);
      if (req == null) {
         req = new FetchRequest(table, mmds, cmd, clr);
         this.requestsByID.put(reqID, req);
      }

      return req;
   }

   public void updateObject(ObjectProvider op, int[] fieldNumbers) {
      this.assertReadOnlyForUpdateOfObject(op);
      this.checkForSchemaUpdatesForFieldsOfObject(op, fieldNumbers);
      AbstractMemberMetaData[] mmds = null;
      if (fieldNumbers != null && fieldNumbers.length > 0) {
         ExecutionContext ec = op.getExecutionContext();
         mmds = new AbstractMemberMetaData[fieldNumbers.length];

         for(int i = 0; i < mmds.length; ++i) {
            mmds[i] = op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumbers[i]);
         }

         if (ec.getStatistics() != null) {
            ec.getStatistics().incrementUpdateCount();
         }

         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         DatastoreClass dc = this.getDatastoreClass(op.getObject().getClass().getName(), clr);
         this.updateObjectInTable(dc, op, clr, mmds);
      }

   }

   private void updateObjectInTable(DatastoreClass table, ObjectProvider op, ClassLoaderResolver clr, AbstractMemberMetaData[] mmds) {
      if (table instanceof ClassView) {
         throw new NucleusUserException("Cannot perform UpdateRequest on RDBMS view " + table);
      } else {
         DatastoreClass supertable = table.getSuperDatastoreClass();
         if (supertable != null) {
            this.updateObjectInTable(supertable, op, clr, mmds);
         }

         this.getUpdateRequest(table, mmds, op.getClassMetaData(), clr).execute(op);
         Collection<SecondaryDatastoreClass> secondaryTables = table.getSecondaryDatastoreClasses();
         if (secondaryTables != null) {
            Iterator<SecondaryDatastoreClass> tablesIter = secondaryTables.iterator();

            while(tablesIter.hasNext()) {
               this.updateObjectInTable((DatastoreClass)tablesIter.next(), op, clr, mmds);
            }
         }

      }
   }

   private Request getUpdateRequest(DatastoreClass table, AbstractMemberMetaData[] mmds, AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      RequestIdentifier reqID = new RequestIdentifier(table, mmds, RequestType.UPDATE, cmd.getFullClassName());
      Request req = (Request)this.requestsByID.get(reqID);
      if (req == null) {
         req = new UpdateRequest(table, mmds, cmd, clr);
         this.requestsByID.put(reqID, req);
      }

      return req;
   }

   public void deleteObject(ObjectProvider op) {
      this.assertReadOnlyForUpdateOfObject(op);
      ExecutionContext ec = op.getExecutionContext();
      if (ec.getStatistics() != null) {
         ec.getStatistics().incrementDeleteCount();
      }

      ClassLoaderResolver clr = op.getExecutionContext().getClassLoaderResolver();
      DatastoreClass dc = this.getDatastoreClass(op.getClassMetaData().getFullClassName(), clr);
      this.deleteObjectFromTable(dc, op, clr);
   }

   private void deleteObjectFromTable(DatastoreClass table, ObjectProvider sm, ClassLoaderResolver clr) {
      if (table instanceof ClassView) {
         throw new NucleusUserException("Cannot perform DeleteRequest on RDBMS view " + table);
      } else {
         Collection<SecondaryDatastoreClass> secondaryTables = table.getSecondaryDatastoreClasses();
         if (secondaryTables != null) {
            Iterator<SecondaryDatastoreClass> tablesIter = secondaryTables.iterator();

            while(tablesIter.hasNext()) {
               this.deleteObjectFromTable((DatastoreClass)tablesIter.next(), sm, clr);
            }
         }

         this.getDeleteRequest(table, sm.getClassMetaData(), clr).execute(sm);
         DatastoreClass supertable = table.getSuperDatastoreClass();
         if (supertable != null) {
            this.deleteObjectFromTable(supertable, sm, clr);
         }

      }
   }

   private Request getDeleteRequest(DatastoreClass table, AbstractClassMetaData acmd, ClassLoaderResolver clr) {
      RequestIdentifier reqID = new RequestIdentifier(table, (AbstractMemberMetaData[])null, RequestType.DELETE, acmd.getFullClassName());
      Request req = (Request)this.requestsByID.get(reqID);
      if (req == null) {
         req = new DeleteRequest(table, acmd, clr);
         this.requestsByID.put(reqID, req);
      }

      return req;
   }

   public void locateObjects(ObjectProvider[] ops) {
      if (ops != null && ops.length != 0) {
         ClassLoaderResolver clr = ops[0].getExecutionContext().getClassLoaderResolver();
         Map<DatastoreClass, List<ObjectProvider>> opsByTable = new HashMap();

         for(int i = 0; i < ops.length; ++i) {
            AbstractClassMetaData cmd = ops[i].getClassMetaData();
            DatastoreClass table = this.getDatastoreClass(cmd.getFullClassName(), clr);
            table = table.getBaseDatastoreClass();
            List<ObjectProvider> opList = (List)opsByTable.get(table);
            if (opList == null) {
               opList = new ArrayList();
            }

            opList.add(ops[i]);
            opsByTable.put(table, opList);
         }

         for(Map.Entry entry : opsByTable.entrySet()) {
            DatastoreClass table = (DatastoreClass)entry.getKey();
            List<ObjectProvider> tableOps = (List)entry.getValue();
            LocateBulkRequest req = new LocateBulkRequest(table);
            req.execute((ObjectProvider[])tableOps.toArray(new ObjectProvider[tableOps.size()]));
         }

      }
   }

   public void locateObject(ObjectProvider op) {
      ClassLoaderResolver clr = op.getExecutionContext().getClassLoaderResolver();
      DatastoreClass table = this.getDatastoreClass(op.getObject().getClass().getName(), clr);
      this.getLocateRequest(table, op.getObject().getClass().getName()).execute(op);
   }

   private Request getLocateRequest(DatastoreClass table, String className) {
      RequestIdentifier reqID = new RequestIdentifier(table, (AbstractMemberMetaData[])null, RequestType.LOCATE, className);
      Request req = (Request)this.requestsByID.get(reqID);
      if (req == null) {
         req = new LocateRequest(table);
         this.requestsByID.put(reqID, req);
      }

      return req;
   }

   public Object findObject(ExecutionContext ec, Object id) {
      return null;
   }

   public void removeAllRequests() {
      synchronized(this.requestsByID) {
         this.requestsByID.clear();
      }
   }

   public void removeRequestsForTable(DatastoreClass table) {
      synchronized(this.requestsByID) {
         for(RequestIdentifier reqId : new HashSet(this.requestsByID.keySet())) {
            if (reqId.getTable() == table) {
               this.requestsByID.remove(reqId);
            }
         }

      }
   }

   private void checkForSchemaUpdatesForFieldsOfObject(ObjectProvider sm, int[] fieldNumbers) {
      if (this.storeMgr.getBooleanObjectProperty("datanucleus.rdbms.dynamicSchemaUpdates")) {
         DynamicSchemaFieldManager dynamicSchemaFM = new DynamicSchemaFieldManager((RDBMSStoreManager)this.storeMgr, sm);
         sm.provideFields(fieldNumbers, dynamicSchemaFM);
         if (dynamicSchemaFM.hasPerformedSchemaUpdates()) {
            this.requestsByID.clear();
         }
      }

   }
}
