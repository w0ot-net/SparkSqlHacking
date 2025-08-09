package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyVoidObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.NullWritable;

public class LazyVoid extends LazyPrimitive {
   LazyVoid(LazyVoidObjectInspector lazyVoidObjectInspector) {
      super((ObjectInspector)lazyVoidObjectInspector);
   }

   LazyVoid(LazyPrimitive copy) {
      super(copy);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
   }
}
