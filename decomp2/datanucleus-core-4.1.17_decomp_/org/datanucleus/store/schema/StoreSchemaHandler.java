package org.datanucleus.store.schema;

import java.util.Properties;
import java.util.Set;
import org.datanucleus.store.StoreManager;

public interface StoreSchemaHandler {
   StoreManager getStoreManager();

   boolean isAutoCreateSchema();

   boolean isAutoCreateTables();

   boolean isAutoCreateConstraints();

   boolean isAutoCreateColumns();

   boolean isAutoCreateWarnOnError();

   boolean isAutoDeleteColumns();

   boolean isValidateTables();

   boolean isValidateColumns();

   boolean isValidateConstraints();

   void clear();

   void createSchema(String var1, Properties var2, Object var3);

   void deleteSchema(String var1, Properties var2, Object var3);

   void createSchemaForClasses(Set var1, Properties var2, Object var3);

   void deleteSchemaForClasses(Set var1, Properties var2, Object var3);

   void validateSchema(Set var1, Properties var2, Object var3);

   StoreSchemaData getSchemaData(Object var1, String var2, Object[] var3);
}
