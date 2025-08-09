package org.datanucleus.store.schema;

import java.util.List;

public interface ListStoreSchemaData extends StoreSchemaData {
   void setParent(StoreSchemaData var1);

   StoreSchemaData getParent();

   void addChild(StoreSchemaData var1);

   void clearChildren();

   List getChildren();

   StoreSchemaData getChild(int var1);

   int getNumberOfChildren();
}
