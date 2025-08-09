package org.datanucleus.store.rdbms.scostore;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.mapping.java.DatastoreIdMapping;
import org.datanucleus.store.rdbms.mapping.java.InterfaceMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.scostore.Store;

public abstract class BaseContainerStore implements Store {
   protected RDBMSStoreManager storeMgr;
   protected DatastoreAdapter dba;
   protected JavaTypeMapping ownerMapping;
   protected AbstractMemberMetaData ownerMemberMetaData;
   protected RelationType relationType;
   protected boolean allowNulls = false;
   protected ClassLoaderResolver clr;

   protected BaseContainerStore(RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      this.storeMgr = storeMgr;
      this.dba = this.storeMgr.getDatastoreAdapter();
      this.clr = clr;
   }

   protected void setOwner(AbstractMemberMetaData mmd) {
      this.ownerMemberMetaData = mmd;
      if (Boolean.TRUE.equals(this.ownerMemberMetaData.getContainer().allowNulls())) {
         this.allowNulls = true;
      }

      this.relationType = this.ownerMemberMetaData.getRelationType(this.clr);
   }

   public RDBMSStoreManager getStoreManager() {
      return this.storeMgr;
   }

   public JavaTypeMapping getOwnerMapping() {
      return this.ownerMapping;
   }

   public RelationType getRelationType() {
      return this.relationType;
   }

   public AbstractMemberMetaData getOwnerMemberMetaData() {
      return this.ownerMemberMetaData;
   }

   public DatastoreAdapter getDatastoreAdapter() {
      return this.dba;
   }

   protected boolean isEmbeddedMapping(JavaTypeMapping mapping) {
      return !InterfaceMapping.class.isAssignableFrom(mapping.getClass()) && !DatastoreIdMapping.class.isAssignableFrom(mapping.getClass()) && !PersistableMapping.class.isAssignableFrom(mapping.getClass());
   }

   public ObjectProvider getObjectProviderForEmbeddedPCObject(ObjectProvider op, Object obj, AbstractMemberMetaData ownerMmd, short pcType) {
      ExecutionContext ec = op.getExecutionContext();
      ObjectProvider objOP = ec.findObjectProvider(obj);
      if (objOP == null) {
         objOP = ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, obj, false, op, ownerMmd.getAbsoluteFieldNumber());
      }

      objOP.setPcObjectType(pcType);
      return objOP;
   }

   protected boolean allowsBatching() {
      return this.storeMgr.allowsBatching();
   }
}
