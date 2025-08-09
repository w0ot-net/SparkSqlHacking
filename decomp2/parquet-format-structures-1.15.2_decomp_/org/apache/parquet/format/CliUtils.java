package org.apache.parquet.format;

import java.io.IOException;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TSerializer;
import shaded.parquet.org.apache.thrift.protocol.TSimpleJSONProtocol;

public class CliUtils {
   public static String toJson(TBase tbase) throws IOException {
      try {
         TSerializer serializer = new TSerializer(new TSimpleJSONProtocol.Factory());
         return serializer.toString(tbase);
      } catch (TException e) {
         throw new IOException(e);
      }
   }

   private CliUtils() {
   }
}
