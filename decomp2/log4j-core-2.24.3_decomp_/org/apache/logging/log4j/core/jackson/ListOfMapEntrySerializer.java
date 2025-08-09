package org.apache.logging.log4j.core.jackson;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class ListOfMapEntrySerializer extends StdSerializer {
   private static final long serialVersionUID = 1L;

   protected ListOfMapEntrySerializer() {
      super(Map.class, false);
   }

   public void serialize(final Map map, final JsonGenerator jgen, final SerializerProvider provider) throws IOException, JsonGenerationException {
      Set<Map.Entry<String, String>> entrySet = map.entrySet();
      MapEntry[] pairs = new MapEntry[entrySet.size()];
      int i = 0;

      for(Map.Entry entry : entrySet) {
         pairs[i++] = new MapEntry((String)entry.getKey(), (String)entry.getValue());
      }

      jgen.writeObject(pairs);
   }
}
