package org.apache.spark.util.kvstore;

import java.io.Closeable;
import java.util.Collection;
import org.apache.spark.annotation.Private;

@Private
public interface KVStore extends Closeable {
   Object getMetadata(Class var1) throws Exception;

   void setMetadata(Object var1) throws Exception;

   Object read(Class var1, Object var2) throws Exception;

   void write(Object var1) throws Exception;

   void delete(Class var1, Object var2) throws Exception;

   KVStoreView view(Class var1) throws Exception;

   long count(Class var1) throws Exception;

   long count(Class var1, String var2, Object var3) throws Exception;

   boolean removeAllByIndexValues(Class var1, String var2, Collection var3) throws Exception;
}
