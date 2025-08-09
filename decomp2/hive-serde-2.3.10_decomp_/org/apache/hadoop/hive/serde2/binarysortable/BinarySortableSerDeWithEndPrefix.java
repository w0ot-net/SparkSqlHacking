package org.apache.hadoop.hive.serde2.binarysortable;

import java.util.List;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public class BinarySortableSerDeWithEndPrefix extends BinarySortableSerDe {
   public static void serializeStruct(ByteStream.Output byteStream, Object[] fieldData, List fieldOis, boolean endPrefix) throws SerDeException {
      for(int i = 0; i < fieldData.length; ++i) {
         serialize(byteStream, fieldData[i], (ObjectInspector)fieldOis.get(i), false, (byte)0, (byte)1);
      }

      if (endPrefix) {
         if (fieldData[fieldData.length - 1] != null) {
            ++byteStream.getData()[byteStream.getLength() - 1];
         } else {
            byte[] var5 = byteStream.getData();
            int var6 = byteStream.getLength() - 1;
            var5[var6] = (byte)(var5[var6] + 2);
         }
      }

   }
}
