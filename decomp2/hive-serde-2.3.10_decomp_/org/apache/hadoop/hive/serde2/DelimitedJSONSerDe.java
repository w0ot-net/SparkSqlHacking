package org.apache.hadoop.hive.serde2;

import java.io.IOException;
import org.apache.hadoop.hive.serde2.lazy.LazySerDeParameters;
import org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelimitedJSONSerDe extends LazySimpleSerDe {
   public static final Logger LOG = LoggerFactory.getLogger(DelimitedJSONSerDe.class.getName());

   public DelimitedJSONSerDe() throws SerDeException {
   }

   public Object doDeserialize(Writable field) throws SerDeException {
      LOG.error("DelimitedJSONSerDe cannot deserialize.");
      throw new SerDeException("DelimitedJSONSerDe cannot deserialize.");
   }

   protected void serializeField(ByteStream.Output out, Object obj, ObjectInspector objInspector, LazySerDeParameters serdeParams) throws SerDeException {
      if (objInspector.getCategory().equals(ObjectInspector.Category.PRIMITIVE) && !objInspector.getTypeName().equalsIgnoreCase("binary")) {
         super.serializeField(out, obj, objInspector, serdeParams);
      } else {
         try {
            serialize(out, SerDeUtils.getJSONString(obj, objInspector, serdeParams.getNullSequence().toString()), PrimitiveObjectInspectorFactory.javaStringObjectInspector, serdeParams.getSeparators(), 1, serdeParams.getNullSequence(), serdeParams.isEscaped(), serdeParams.getEscapeChar(), serdeParams.getNeedsEscape());
         } catch (IOException e) {
            throw new SerDeException(e);
         }
      }

   }
}
