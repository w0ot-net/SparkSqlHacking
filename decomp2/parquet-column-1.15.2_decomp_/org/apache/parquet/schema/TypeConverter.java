package org.apache.parquet.schema;

import java.util.List;

public interface TypeConverter {
   Object convertPrimitiveType(List var1, PrimitiveType var2);

   Object convertGroupType(List var1, GroupType var2, List var3);

   Object convertMessageType(MessageType var1, List var2);
}
