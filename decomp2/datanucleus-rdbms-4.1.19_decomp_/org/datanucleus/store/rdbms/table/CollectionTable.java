package org.datanucleus.store.rdbms.table;

import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ElementMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class CollectionTable extends ElementContainerTable implements DatastoreElementContainer {
   public CollectionTable(DatastoreIdentifier tableName, AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr) {
      super(tableName, mmd, storeMgr);
   }

   public void initialize(ClassLoaderResolver clr) {
      super.initialize(clr);
      PrimaryKeyMetaData pkmd = this.mmd.getJoinMetaData() != null ? this.mmd.getJoinMetaData().getPrimaryKeyMetaData() : null;
      boolean pkColsSpecified = pkmd != null ? pkmd.getColumnMetaData() != null : false;
      boolean pkRequired = this.requiresPrimaryKey();
      boolean elementPC = this.mmd.hasCollection() && this.mmd.getCollection().elementIsPersistent();
      Class elementClass = clr.classForName(this.getElementType());
      if (!this.isSerialisedElement() && !this.isEmbeddedElementPC() && (!this.isEmbeddedElement() || elementPC) && !ClassUtils.isReferenceType(elementClass)) {
         ColumnMetaData[] elemColmd = null;
         AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(clr);
         ElementMetaData elemmd = this.mmd.getElementMetaData();
         if (elemmd != null && elemmd.getColumnMetaData() != null && elemmd.getColumnMetaData().length > 0) {
            elemColmd = elemmd.getColumnMetaData();
         } else if (relatedMmds != null && relatedMmds[0].getJoinMetaData() != null && relatedMmds[0].getJoinMetaData().getColumnMetaData() != null && relatedMmds[0].getJoinMetaData().getColumnMetaData().length > 0) {
            elemColmd = relatedMmds[0].getJoinMetaData().getColumnMetaData();
         }

         this.elementMapping = ColumnCreator.createColumnsForJoinTables(elementClass, this.mmd, elemColmd, this.storeMgr, this, false, false, FieldRole.ROLE_COLLECTION_ELEMENT, clr);
         RelationType relationType = this.mmd.getRelationType(clr);
         if (Boolean.TRUE.equals(this.mmd.getContainer().allowNulls()) && relationType != RelationType.MANY_TO_MANY_BI) {
            for(int i = 0; i < this.elementMapping.getNumberOfDatastoreMappings(); ++i) {
               Column elementCol = this.elementMapping.getDatastoreMapping(i).getColumn();
               elementCol.setNullable(true);
            }
         }

         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            this.logMapping(this.mmd.getFullFieldName() + ".[ELEMENT]", this.elementMapping);
         }
      } else {
         this.elementMapping = this.storeMgr.getMappingManager().getMapping(this, this.mmd, clr, FieldRole.ROLE_COLLECTION_ELEMENT);
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            this.logMapping(this.mmd.getFullFieldName() + ".[ELEMENT]", this.elementMapping);
         }
      }

      boolean orderRequired = false;
      if (this.mmd.getOrderMetaData() != null) {
         if (this.mmd.getOrderMetaData().isIndexedList()) {
            orderRequired = true;
            RelationType relType = this.mmd.getRelationType(clr);
            if (relType == RelationType.MANY_TO_MANY_BI) {
               throw (new NucleusUserException(Localiser.msg("020002", new Object[]{this.mmd.getFullFieldName()}))).setFatal();
            }
         }
      } else if (List.class.isAssignableFrom(this.mmd.getType())) {
         orderRequired = true;
      } else if (this.requiresPrimaryKey() && !pkColsSpecified) {
         if (this.isEmbeddedElementPC()) {
            if (this.mmd.getCollection().getElementClassMetaData(clr, this.storeMgr.getMetaDataManager()).getIdentityType() != IdentityType.APPLICATION) {
               orderRequired = true;
            }
         } else if (this.isSerialisedElement()) {
            orderRequired = true;
         } else if (this.elementMapping instanceof ReferenceMapping) {
            ReferenceMapping refMapping = (ReferenceMapping)this.elementMapping;
            if (refMapping.getJavaTypeMapping().length > 1) {
               orderRequired = true;
            }
         } else if (!(this.elementMapping instanceof PersistableMapping)) {
            Column elementCol = this.elementMapping.getDatastoreMapping(0).getColumn();
            if (!this.storeMgr.getDatastoreAdapter().isValidPrimaryKeyType(elementCol.getJdbcType())) {
               orderRequired = true;
            }
         }
      }

      if (orderRequired) {
         ColumnMetaData orderColmd = null;
         if (this.mmd.getOrderMetaData() != null && this.mmd.getOrderMetaData().getColumnMetaData() != null && this.mmd.getOrderMetaData().getColumnMetaData().length > 0) {
            orderColmd = this.mmd.getOrderMetaData().getColumnMetaData()[0];
            if (orderColmd.getName() == null) {
               orderColmd = new ColumnMetaData(orderColmd);
               DatastoreIdentifier id = this.storeMgr.getIdentifierFactory().newIndexFieldIdentifier(this.mmd);
               orderColmd.setName(id.getName());
            }
         } else {
            DatastoreIdentifier id = this.storeMgr.getIdentifierFactory().newIndexFieldIdentifier(this.mmd);
            orderColmd = new ColumnMetaData();
            orderColmd.setName(id.getName());
         }

         this.orderMapping = this.storeMgr.getMappingManager().getMapping(Integer.TYPE);
         ColumnCreator.createIndexColumn(this.orderMapping, this.storeMgr, clr, this, orderColmd, pkRequired && !pkColsSpecified);
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            this.logMapping(this.mmd.getFullFieldName() + ".[ORDER]", this.orderMapping);
         }
      }

      if (pkRequired) {
         if (pkColsSpecified) {
            this.applyUserPrimaryKeySpecification(pkmd);
         } else if (orderRequired) {
            this.orderMapping.getDatastoreMapping(0).getColumn().setPrimaryKey();
         } else {
            for(int i = 0; i < this.elementMapping.getNumberOfDatastoreMappings(); ++i) {
               this.elementMapping.getDatastoreMapping(i).getColumn().setPrimaryKey();
            }
         }
      }

      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057023", new Object[]{this}));
      }

      this.storeMgr.registerTableInitialized(this);
      this.state = 2;
   }

   public String getElementType() {
      return this.mmd.getCollection().getElementType();
   }

   public boolean isSerialisedElement() {
      return this.mmd.getCollection() != null && this.mmd.getCollection().isSerializedElement();
   }

   public boolean isEmbeddedElement() {
      if (this.mmd.getCollection() != null && this.mmd.getCollection().isSerializedElement()) {
         return false;
      } else {
         return this.mmd.getCollection() != null && this.mmd.getCollection().isEmbeddedElement();
      }
   }

   public boolean isSerialisedElementPC() {
      return this.mmd.getCollection() != null && this.mmd.getCollection().isSerializedElement() && this.mmd.getCollection().elementIsPersistent();
   }

   public boolean isEmbeddedElementPC() {
      if (this.mmd.getCollection() != null && this.mmd.getCollection().isSerializedElement()) {
         return false;
      } else {
         return this.mmd.getElementMetaData() != null && this.mmd.getElementMetaData().getEmbeddedMetaData() != null;
      }
   }

   protected boolean requiresPrimaryKey() {
      return this.mmd.getOrderMetaData() != null && !this.mmd.getOrderMetaData().isIndexedList() ? false : super.requiresPrimaryKey();
   }
}
