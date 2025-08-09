package org.apache.hadoop.hive.serde2;

import java.util.List;

public interface StructObject {
   Object getField(int var1);

   List getFieldsAsList();
}
