package org.datanucleus.store.rdbms.table;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ElementMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ArrayTable extends ElementContainerTable implements DatastoreElementContainer {
   public ArrayTable(DatastoreIdentifier tableName, AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr) {
      super(tableName, mmd, storeMgr);
   }

   public void initialize(ClassLoaderResolver clr) {
      super.initialize(clr);
      PrimaryKeyMetaData pkmd = this.mmd.getJoinMetaData() != null ? this.mmd.getJoinMetaData().getPrimaryKeyMetaData() : null;
      boolean pkColsSpecified = pkmd != null ? pkmd.getColumnMetaData() != null : false;
      boolean pkRequired = this.requiresPrimaryKey();
      boolean elementPC = this.mmd.hasArray() && this.mmd.getArray().elementIsPersistent();
      if (!this.isSerialisedElementPC() && !this.isEmbeddedElementPC() && (!this.isEmbeddedElement() || elementPC) && !ClassUtils.isReferenceType(this.mmd.getType().getComponentType())) {
         ColumnMetaData[] elemColmd = null;
         ElementMetaData elemmd = this.mmd.getElementMetaData();
         if (elemmd != null && elemmd.getColumnMetaData() != null && elemmd.getColumnMetaData().length > 0) {
            elemColmd = elemmd.getColumnMetaData();
         }

         this.elementMapping = ColumnCreator.createColumnsForJoinTables(this.mmd.getType().getComponentType(), this.mmd, elemColmd, this.storeMgr, this, false, true, FieldRole.ROLE_ARRAY_ELEMENT, clr);
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            this.logMapping(this.mmd.getFullFieldName() + ".[ELEMENT]", this.elementMapping);
         }
      } else {
         this.elementMapping = this.storeMgr.getMappingManager().getMapping(this, this.mmd, clr, FieldRole.ROLE_ARRAY_ELEMENT);
         if (Boolean.TRUE.equals(this.mmd.getContainer().allowNulls())) {
            for(int i = 0; i < this.elementMapping.getNumberOfDatastoreMappings(); ++i) {
               Column elementCol = this.elementMapping.getDatastoreMapping(i).getColumn();
               elementCol.setNullable(true);
            }
         }

         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            this.logMapping(this.mmd.getFullFieldName() + ".[ELEMENT]", this.elementMapping);
         }
      }

      ColumnMetaData colmd = null;
      if (this.mmd.getOrderMetaData() != null && this.mmd.getOrderMetaData().getColumnMetaData() != null && this.mmd.getOrderMetaData().getColumnMetaData().length > 0) {
         colmd = this.mmd.getOrderMetaData().getColumnMetaData()[0];
      } else {
         DatastoreIdentifier id = this.storeMgr.getIdentifierFactory().newIndexFieldIdentifier(this.mmd);
         colmd = new ColumnMetaData();
         colmd.setName(id.getName());
      }

      this.orderMapping = this.storeMgr.getMappingManager().getMapping(Integer.TYPE);
      ColumnCreator.createIndexColumn(this.orderMapping, this.storeMgr, clr, this, colmd, pkRequired && !pkColsSpecified);
      if (NucleusLogger.DATASTORE.isDebugEnabled()) {
         this.logMapping(this.mmd.getFullFieldName() + ".[ORDER]", this.orderMapping);
      }

      if (pkRequired && pkColsSpecified) {
         this.applyUserPrimaryKeySpecification(pkmd);
      }

      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057023", new Object[]{this}));
      }

      this.storeMgr.registerTableInitialized(this);
      this.state = 2;
   }

   public String getElementType() {
      return this.mmd.getType().getComponentType().getName();
   }

   public boolean isSerialisedElement() {
      return this.mmd.getArray() != null && this.mmd.getArray().isSerializedElement();
   }

   public boolean isEmbeddedElement() {
      if (this.mmd.getArray() != null && this.mmd.getArray().isSerializedElement()) {
         return false;
      } else {
         return this.mmd.getArray() != null && this.mmd.getArray().isEmbeddedElement();
      }
   }

   public boolean isSerialisedElementPC() {
      return this.mmd.getArray() != null && this.mmd.getArray().isSerializedElement();
   }

   public boolean isEmbeddedElementPC() {
      if (this.mmd.getArray() != null && this.mmd.getArray().isSerializedElement()) {
         return false;
      } else {
         return this.mmd.getElementMetaData() != null && this.mmd.getElementMetaData().getEmbeddedMetaData() != null;
      }
   }
}
