package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.arrow.flatbuf.Field;
import org.apache.arrow.flatbuf.KeyValue;

public class FBSerializables {
   private FBSerializables() {
   }

   public static int writeAllStructsToVector(FlatBufferBuilder builder, List all) {
      List<? extends FBSerializable> reversed = new ArrayList(all);
      Collections.reverse(reversed);

      for(FBSerializable element : reversed) {
         element.writeTo(builder);
      }

      return builder.endVector();
   }

   public static int writeKeyValues(FlatBufferBuilder builder, Map metaData) {
      int[] metadataOffsets = new int[metaData.size()];
      Iterator<Map.Entry<String, String>> metadataIterator = metaData.entrySet().iterator();

      for(int i = 0; i < metadataOffsets.length; ++i) {
         Map.Entry<String, String> kv = (Map.Entry)metadataIterator.next();
         int keyOffset = builder.createString((CharSequence)kv.getKey());
         int valueOffset = builder.createString((CharSequence)kv.getValue());
         KeyValue.startKeyValue(builder);
         KeyValue.addKey(builder, keyOffset);
         KeyValue.addValue(builder, valueOffset);
         metadataOffsets[i] = KeyValue.endKeyValue(builder);
      }

      return Field.createCustomMetadataVector(builder, metadataOffsets);
   }
}
