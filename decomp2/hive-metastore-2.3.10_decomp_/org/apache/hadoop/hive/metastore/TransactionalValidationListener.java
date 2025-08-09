package org.apache.hadoop.hive.metastore;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.events.PreAlterTableEvent;
import org.apache.hadoop.hive.metastore.events.PreCreateTableEvent;
import org.apache.hadoop.hive.metastore.events.PreEventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TransactionalValidationListener extends MetaStorePreEventListener {
   public static final Logger LOG = LoggerFactory.getLogger(TransactionalValidationListener.class);
   public static final String DEFAULT_TRANSACTIONAL_PROPERTY = "default";
   public static final String LEGACY_TRANSACTIONAL_PROPERTY = "legacy";

   TransactionalValidationListener(Configuration conf) {
      super(conf);
   }

   public void onEvent(PreEventContext context) throws MetaException, NoSuchObjectException, InvalidOperationException {
      switch (context.getEventType()) {
         case CREATE_TABLE:
            this.handle((PreCreateTableEvent)context);
            break;
         case ALTER_TABLE:
            this.handle((PreAlterTableEvent)context);
      }

   }

   private void handle(PreAlterTableEvent context) throws MetaException {
      this.handleAlterTableTransactionalProp(context);
   }

   private void handle(PreCreateTableEvent context) throws MetaException {
      this.handleCreateTableTransactionalProp(context);
   }

   private void handleAlterTableTransactionalProp(PreAlterTableEvent context) throws MetaException {
      Table newTable = context.getNewTable();
      Map<String, String> parameters = newTable.getParameters();
      if (parameters != null && !parameters.isEmpty()) {
         Set<String> keys = new HashSet(parameters.keySet());
         String transactionalValue = null;
         boolean transactionalValuePresent = false;
         boolean isTransactionalPropertiesPresent = false;
         String transactionalPropertiesValue = null;
         boolean hasValidTransactionalValue = false;

         for(String key : keys) {
            if ("transactional".equalsIgnoreCase(key)) {
               transactionalValuePresent = true;
               transactionalValue = (String)parameters.get(key);
               parameters.remove(key);
            }

            if ("transactional_properties".equalsIgnoreCase(key)) {
               isTransactionalPropertiesPresent = true;
               transactionalPropertiesValue = (String)parameters.get(key);
            }
         }

         if (transactionalValuePresent) {
            parameters.put("transactional", transactionalValue);
         }

         if ("true".equalsIgnoreCase(transactionalValue)) {
            if (!this.conformToAcid(newTable)) {
               throw new MetaException("The table must be bucketed and stored using an ACID compliant format (such as ORC)");
            }

            if (newTable.getTableType().equals(TableType.EXTERNAL_TABLE.toString())) {
               throw new MetaException(newTable.getDbName() + "." + newTable.getTableName() + " cannot be declared transactional because it's an external table");
            }

            hasValidTransactionalValue = true;
         }

         Table oldTable = context.getOldTable();
         String oldTransactionalValue = null;
         String oldTransactionalPropertiesValue = null;

         for(String key : oldTable.getParameters().keySet()) {
            if ("transactional".equalsIgnoreCase(key)) {
               oldTransactionalValue = (String)oldTable.getParameters().get(key);
            }

            if ("transactional_properties".equalsIgnoreCase(key)) {
               oldTransactionalPropertiesValue = (String)oldTable.getParameters().get(key);
            }
         }

         label72: {
            if (oldTransactionalValue == null) {
               if (transactionalValue != null) {
                  break label72;
               }
            } else if (!oldTransactionalValue.equalsIgnoreCase(transactionalValue)) {
               break label72;
            }

            hasValidTransactionalValue = true;
         }

         if (!hasValidTransactionalValue) {
            throw new MetaException("TBLPROPERTIES with 'transactional'='true' cannot be unset");
         } else {
            if (isTransactionalPropertiesPresent) {
               if (oldTransactionalValue == null) {
                  this.initializeTransactionalProperties(newTable);
               } else if (oldTransactionalPropertiesValue == null || !oldTransactionalPropertiesValue.equalsIgnoreCase(transactionalPropertiesValue)) {
                  throw new MetaException("TBLPROPERTIES with 'transactional_properties' cannot be altered after the table is created");
               }
            }

         }
      }
   }

   private void handleCreateTableTransactionalProp(PreCreateTableEvent context) throws MetaException {
      Table newTable = context.getTable();
      Map<String, String> parameters = newTable.getParameters();
      if (parameters != null && !parameters.isEmpty()) {
         String transactionalValue = null;
         boolean transactionalPropFound = false;

         for(String key : new HashSet(parameters.keySet())) {
            if ("transactional".equalsIgnoreCase(key)) {
               transactionalPropFound = true;
               transactionalValue = (String)parameters.get(key);
               parameters.remove(key);
            }
         }

         if (transactionalPropFound) {
            if ("false".equalsIgnoreCase(transactionalValue)) {
               LOG.info("'transactional'='false' is no longer a valid property and will be ignored");
            } else if ("true".equalsIgnoreCase(transactionalValue)) {
               if (!this.conformToAcid(newTable)) {
                  throw new MetaException("The table must be bucketed and stored using an ACID compliant format (such as ORC)");
               } else if (newTable.getTableType().equals(TableType.EXTERNAL_TABLE.toString())) {
                  throw new MetaException(newTable.getDbName() + "." + newTable.getTableName() + " cannot be declared transactional because it's an external table");
               } else {
                  parameters.put("transactional", Boolean.TRUE.toString());
                  this.initializeTransactionalProperties(newTable);
               }
            } else {
               throw new MetaException("'transactional' property of TBLPROPERTIES may only have value 'true'");
            }
         }
      }
   }

   private boolean conformToAcid(Table table) throws MetaException {
      StorageDescriptor sd = table.getSd();
      if (sd.getBucketColsSize() < 1) {
         return false;
      } else {
         try {
            Class inputFormatClass = Class.forName(sd.getInputFormat());
            Class outputFormatClass = Class.forName(sd.getOutputFormat());
            return inputFormatClass != null && outputFormatClass != null && Class.forName("org.apache.hadoop.hive.ql.io.AcidInputFormat").isAssignableFrom(inputFormatClass) && Class.forName("org.apache.hadoop.hive.ql.io.AcidOutputFormat").isAssignableFrom(outputFormatClass);
         } catch (ClassNotFoundException var5) {
            throw new MetaException("Invalid input/output format for table");
         }
      }
   }

   private void initializeTransactionalProperties(Table table) throws MetaException {
      String tableTransactionalProperties = null;
      Map<String, String> parameters = table.getParameters();
      if (parameters != null) {
         for(String key : parameters.keySet()) {
            if ("transactional_properties".equalsIgnoreCase(key)) {
               tableTransactionalProperties = ((String)parameters.get(key)).toLowerCase();
               parameters.remove(key);
               String validationError = this.validateTransactionalProperties(tableTransactionalProperties);
               if (validationError != null) {
                  throw new MetaException("Invalid transactional properties specified for the table with the error " + validationError);
               }
               break;
            }
         }
      }

      if (tableTransactionalProperties != null) {
         parameters.put("transactional_properties", tableTransactionalProperties);
      }

   }

   private String validateTransactionalProperties(String transactionalProperties) {
      boolean isValid = false;
      switch (transactionalProperties) {
         case "default":
         case "legacy":
            isValid = true;
            break;
         default:
            isValid = false;
      }

      return !isValid ? "unknown value " + transactionalProperties + " for transactional_properties" : null;
   }
}
