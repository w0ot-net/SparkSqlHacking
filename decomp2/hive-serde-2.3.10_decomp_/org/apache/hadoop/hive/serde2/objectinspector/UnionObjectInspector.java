package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.List;

public interface UnionObjectInspector extends ObjectInspector {
   List getObjectInspectors();

   byte getTag(Object var1);

   Object getField(Object var1);
}
