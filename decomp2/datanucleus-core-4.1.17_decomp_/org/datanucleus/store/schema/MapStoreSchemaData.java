package org.datanucleus.store.schema;

import java.util.Map;

public interface MapStoreSchemaData extends StoreSchemaData {
   void setParent(StoreSchemaData var1);

   StoreSchemaData getParent();

   void addChild(StoreSchemaData var1);

   void clearChildren();

   Map getChildren();

   StoreSchemaData getChild(String var1);

   int getNumberOfChildren();
}
