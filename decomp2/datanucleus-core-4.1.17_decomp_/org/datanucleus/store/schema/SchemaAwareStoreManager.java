package org.datanucleus.store.schema;

import java.util.Properties;
import java.util.Set;

public interface SchemaAwareStoreManager {
   void createSchema(String var1, Properties var2);

   void createSchemaForClasses(Set var1, Properties var2);

   void deleteSchema(String var1, Properties var2);

   void deleteSchemaForClasses(Set var1, Properties var2);

   void validateSchemaForClasses(Set var1, Properties var2);
}
