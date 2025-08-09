package org.datanucleus.store.schema;

import java.util.Properties;
import java.util.Set;
import org.datanucleus.store.StoreManager;

public abstract class AbstractStoreSchemaHandler implements StoreSchemaHandler {
   protected StoreManager storeMgr;
   protected final boolean autoCreateSchema;
   protected final boolean autoCreateTables;
   protected final boolean autoCreateColumns;
   protected final boolean autoCreateConstraints;
   protected final boolean autoCreateWarnOnError;
   protected final boolean autoDeleteColumns;
   protected final boolean validateTables;
   protected final boolean validateColumns;
   protected final boolean validateConstraints;

   public AbstractStoreSchemaHandler(StoreManager storeMgr) {
      this.storeMgr = storeMgr;
      boolean readOnlyDatastore = storeMgr.getBooleanProperty("datanucleus.readOnlyDatastore");
      if (readOnlyDatastore) {
         this.autoCreateSchema = false;
         this.autoCreateTables = false;
         this.autoCreateColumns = false;
         this.autoCreateConstraints = false;
      } else {
         boolean autoCreateAll = storeMgr.getBooleanProperty("datanucleus.schema.autoCreateAll");
         if (autoCreateAll) {
            this.autoCreateSchema = true;
            this.autoCreateTables = true;
            this.autoCreateColumns = true;
            this.autoCreateConstraints = true;
         } else {
            this.autoCreateSchema = storeMgr.getBooleanProperty("datanucleus.schema.autoCreateSchema");
            this.autoCreateTables = storeMgr.getBooleanProperty("datanucleus.schema.autoCreateTables");
            this.autoCreateColumns = storeMgr.getBooleanProperty("datanucleus.schema.autoCreateColumns");
            this.autoCreateConstraints = storeMgr.getBooleanProperty("datanucleus.schema.autoCreateConstraints");
         }
      }

      this.autoCreateWarnOnError = storeMgr.getBooleanProperty("datanucleus.schema.autoCreateWarnOnError");
      this.autoDeleteColumns = storeMgr.getBooleanProperty("datanucleus.schema.autoDeleteColumns");
      boolean validateAll = storeMgr.getBooleanProperty("datanucleus.schema.validateAll");
      if (validateAll) {
         this.validateTables = true;
         this.validateColumns = true;
         this.validateConstraints = true;
      } else {
         this.validateTables = storeMgr.getBooleanProperty("datanucleus.schema.validateTables");
         if (!this.validateTables) {
            this.validateColumns = false;
         } else {
            this.validateColumns = storeMgr.getBooleanProperty("datanucleus.schema.validateColumns");
         }

         this.validateConstraints = storeMgr.getBooleanProperty("datanucleus.schema.validateConstraints");
      }

   }

   public StoreManager getStoreManager() {
      return this.storeMgr;
   }

   public boolean isAutoCreateSchema() {
      return this.autoCreateSchema;
   }

   public boolean isAutoCreateTables() {
      return this.autoCreateTables;
   }

   public boolean isAutoCreateColumns() {
      return this.autoCreateColumns;
   }

   public boolean isAutoCreateConstraints() {
      return this.autoCreateConstraints;
   }

   public boolean isAutoCreateWarnOnError() {
      return this.autoCreateWarnOnError;
   }

   public boolean isAutoDeleteColumns() {
      return this.autoDeleteColumns;
   }

   public boolean isValidateTables() {
      return this.validateTables;
   }

   public boolean isValidateColumns() {
      return this.validateColumns;
   }

   public boolean isValidateConstraints() {
      return this.validateConstraints;
   }

   public void clear() {
   }

   public void createSchema(String schemaName, Properties props, Object connection) {
      throw new UnsupportedOperationException("This datastore doesn't support creation of schema");
   }

   public void deleteSchema(String schemaName, Properties props, Object connection) {
      throw new UnsupportedOperationException("This datastore doesn't support deletion of schema");
   }

   public void createSchemaForClasses(Set classNames, Properties props, Object connection) {
      throw new UnsupportedOperationException("This datastore doesn't support creation of schema for classes");
   }

   public void deleteSchemaForClasses(Set classNames, Properties props, Object connection) {
      throw new UnsupportedOperationException("This datastore doesn't support deletion of schema for classes");
   }

   public void validateSchema(Set classNames, Properties props, Object connection) {
      throw new UnsupportedOperationException("This datastore doesn't support validation of schema");
   }

   public StoreSchemaData getSchemaData(Object connection, String name, Object[] values) {
      return null;
   }
}
