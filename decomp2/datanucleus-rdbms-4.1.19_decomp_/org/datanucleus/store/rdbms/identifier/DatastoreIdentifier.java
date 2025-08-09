package org.datanucleus.store.rdbms.identifier;

public interface DatastoreIdentifier {
   String getName();

   void setCatalogName(String var1);

   void setSchemaName(String var1);

   String getCatalogName();

   String getSchemaName();

   String getFullyQualifiedName(boolean var1);

   String toString();
}
