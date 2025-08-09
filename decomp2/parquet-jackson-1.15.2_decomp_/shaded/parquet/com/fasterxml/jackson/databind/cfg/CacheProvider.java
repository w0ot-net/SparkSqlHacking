package shaded.parquet.com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.util.LookupCache;

public interface CacheProvider extends Serializable {
   LookupCache forDeserializerCache(DeserializationConfig var1);

   LookupCache forSerializerCache(SerializationConfig var1);

   LookupCache forTypeFactory();
}
