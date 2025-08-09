package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.List;

public interface ListObjectInspector extends ObjectInspector {
   ObjectInspector getListElementObjectInspector();

   Object getListElement(Object var1, int var2);

   int getListLength(Object var1);

   List getList(Object var1);
}
