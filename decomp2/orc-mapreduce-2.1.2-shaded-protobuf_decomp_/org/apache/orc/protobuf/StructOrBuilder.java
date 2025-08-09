package org.apache.orc.protobuf;

import java.util.Map;

public interface StructOrBuilder extends MessageOrBuilder {
   int getFieldsCount();

   boolean containsFields(String key);

   /** @deprecated */
   @Deprecated
   Map getFields();

   Map getFieldsMap();

   Value getFieldsOrDefault(String key, Value defaultValue);

   Value getFieldsOrThrow(String key);
}
