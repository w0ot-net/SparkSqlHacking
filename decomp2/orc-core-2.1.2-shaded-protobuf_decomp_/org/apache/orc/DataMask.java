package org.apache.orc;

import java.util.ServiceLoader;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.impl.MaskDescriptionImpl;

public interface DataMask {
   void maskData(ColumnVector var1, ColumnVector var2, int var3, int var4);

   public static enum Standard {
      NULLIFY("nullify"),
      REDACT("redact"),
      SHA256("sha256");

      private final String name;

      private Standard(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public DataMaskDescription getDescription(String... params) {
         return new MaskDescriptionImpl(this.name, params);
      }

      // $FF: synthetic method
      private static Standard[] $values() {
         return new Standard[]{NULLIFY, REDACT, SHA256};
      }
   }

   public static class Factory {
      public static DataMask build(DataMaskDescription mask, TypeDescription schema, MaskOverrides overrides) {
         for(Provider provider : ServiceLoader.load(Provider.class)) {
            DataMask result = provider.build(mask, schema, overrides);
            if (result != null) {
               return result;
            }
         }

         throw new IllegalArgumentException("Can't find data mask - " + String.valueOf(mask));
      }
   }

   public interface MaskOverrides {
      DataMaskDescription hasOverride(TypeDescription var1);
   }

   public interface Provider {
      DataMask build(DataMaskDescription var1, TypeDescription var2, MaskOverrides var3);
   }
}
