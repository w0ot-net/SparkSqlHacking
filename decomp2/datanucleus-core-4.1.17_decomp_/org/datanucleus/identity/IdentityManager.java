package org.datanucleus.identity;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractClassMetaData;

public interface IdentityManager {
   Class getDatastoreIdClass();

   IdentityStringTranslator getIdentityStringTranslator();

   IdentityKeyTranslator getIdentityKeyTranslator();

   DatastoreId getDatastoreId(String var1, Object var2);

   DatastoreId getDatastoreId(long var1);

   DatastoreId getDatastoreId(String var1);

   SingleFieldId getSingleFieldId(Class var1, Class var2, Object var3);

   Object getApplicationId(ClassLoaderResolver var1, AbstractClassMetaData var2, String var3);

   Object getApplicationId(Object var1, AbstractClassMetaData var2);

   Object getApplicationId(Class var1, Object var2);
}
