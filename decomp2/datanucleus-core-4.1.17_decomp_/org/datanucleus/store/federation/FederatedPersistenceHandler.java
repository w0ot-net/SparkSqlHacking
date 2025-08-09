package org.datanucleus.store.federation;

import org.datanucleus.ExecutionContext;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.PersistenceBatchType;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.StorePersistenceHandler;

public class FederatedPersistenceHandler implements StorePersistenceHandler {
   FederatedStoreManager storeMgr;

   public FederatedPersistenceHandler(StoreManager storeMgr) {
      this.storeMgr = (FederatedStoreManager)storeMgr;
   }

   public void close() {
   }

   public boolean useReferentialIntegrity() {
      return false;
   }

   public void insertObjects(ObjectProvider... ops) {
      for(int i = 0; i < ops.length; ++i) {
         this.insertObject(ops[i]);
      }

   }

   public void deleteObjects(ObjectProvider... ops) {
      for(int i = 0; i < ops.length; ++i) {
         this.deleteObject(ops[i]);
      }

   }

   public void batchStart(ExecutionContext ec, PersistenceBatchType batchType) {
   }

   public void batchEnd(ExecutionContext ec, PersistenceBatchType type) {
   }

   public void insertObject(ObjectProvider op) {
      StoreManager classStoreMgr = this.storeMgr.getStoreManagerForClass(op.getClassMetaData());
      classStoreMgr.getPersistenceHandler().insertObject(op);
   }

   public void updateObject(ObjectProvider op, int[] fieldNumbers) {
      StoreManager classStoreMgr = this.storeMgr.getStoreManagerForClass(op.getClassMetaData());
      classStoreMgr.getPersistenceHandler().updateObject(op, fieldNumbers);
   }

   public void deleteObject(ObjectProvider op) {
      StoreManager classStoreMgr = this.storeMgr.getStoreManagerForClass(op.getClassMetaData());
      classStoreMgr.getPersistenceHandler().deleteObject(op);
   }

   public void fetchObject(ObjectProvider op, int[] fieldNumbers) {
      StoreManager classStoreMgr = this.storeMgr.getStoreManagerForClass(op.getClassMetaData());
      classStoreMgr.getPersistenceHandler().fetchObject(op, fieldNumbers);
   }

   public void locateObject(ObjectProvider op) {
      StoreManager classStoreMgr = this.storeMgr.getStoreManagerForClass(op.getClassMetaData());
      classStoreMgr.getPersistenceHandler().locateObject(op);
   }

   public void locateObjects(ObjectProvider[] ops) {
      StoreManager classStoreMgr = this.storeMgr.getStoreManagerForClass(ops[0].getClassMetaData());
      classStoreMgr.getPersistenceHandler().locateObjects(ops);
   }

   public Object findObject(ExecutionContext ec, Object id) {
      return null;
   }

   public Object[] findObjects(ExecutionContext ec, Object[] ids) {
      return null;
   }
}
