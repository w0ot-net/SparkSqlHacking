package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.util.LookupCache;
import java.io.Serializable;

public interface CacheProvider extends Serializable {
   LookupCache forDeserializerCache(DeserializationConfig var1);

   LookupCache forSerializerCache(SerializationConfig var1);

   LookupCache forTypeFactory();
}
