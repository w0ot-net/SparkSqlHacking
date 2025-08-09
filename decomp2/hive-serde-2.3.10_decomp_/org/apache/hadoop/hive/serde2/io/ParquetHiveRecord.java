package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.io.Writable;

public class ParquetHiveRecord implements Writable {
   public Object value;
   public StructObjectInspector inspector;

   public ParquetHiveRecord() {
      this((Object)null, (StructObjectInspector)null);
   }

   public ParquetHiveRecord(Object o, StructObjectInspector oi) {
      this.value = o;
      this.inspector = oi;
   }

   public StructObjectInspector getObjectInspector() {
      return this.inspector;
   }

   public Object getObject() {
      return this.value;
   }

   public void write(DataOutput dataOutput) throws IOException {
      throw new UnsupportedOperationException("Unsupported method call.");
   }

   public void readFields(DataInput dataInput) throws IOException {
      throw new UnsupportedOperationException("Unsupported method call.");
   }
}
