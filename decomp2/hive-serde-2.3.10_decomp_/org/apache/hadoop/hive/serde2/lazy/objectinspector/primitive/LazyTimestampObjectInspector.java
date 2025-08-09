package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import java.sql.Timestamp;
import java.util.List;
import org.apache.hadoop.hive.serde2.lazy.LazyTimestamp;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.TimestampObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hive.common.util.TimestampParser;

public class LazyTimestampObjectInspector extends AbstractPrimitiveLazyObjectInspector implements TimestampObjectInspector {
   protected List timestampFormats = null;
   protected TimestampParser timestampParser = null;

   LazyTimestampObjectInspector() {
      super(TypeInfoFactory.timestampTypeInfo);
      this.timestampParser = new TimestampParser();
   }

   LazyTimestampObjectInspector(List tsFormats) {
      super(TypeInfoFactory.timestampTypeInfo);
      this.timestampFormats = tsFormats;
      this.timestampParser = new TimestampParser(tsFormats);
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyTimestamp((LazyTimestamp)o);
   }

   public Timestamp getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((LazyTimestamp)o).getWritableObject().getTimestamp();
   }

   public List getTimestampFormats() {
      return this.timestampFormats;
   }

   public TimestampParser getTimestampParser() {
      return this.timestampParser;
   }
}
