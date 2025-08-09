package org.apache.parquet.column;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class EncodingStats {
   final Map dictStats;
   final Map dataStats;
   private final boolean usesV2Pages;

   private EncodingStats(Map dictStats, Map dataStats, boolean usesV2Pages) {
      this.dictStats = dictStats;
      this.dataStats = dataStats;
      this.usesV2Pages = usesV2Pages;
   }

   public Set getDictionaryEncodings() {
      return this.dictStats.keySet();
   }

   public Set getDataEncodings() {
      return this.dataStats.keySet();
   }

   public int getNumDictionaryPagesEncodedAs(Encoding enc) {
      Number pageCount = (Number)this.dictStats.get(enc);
      return pageCount == null ? 0 : pageCount.intValue();
   }

   public int getNumDataPagesEncodedAs(Encoding enc) {
      Number pageCount = (Number)this.dataStats.get(enc);
      return pageCount == null ? 0 : pageCount.intValue();
   }

   public boolean hasDictionaryPages() {
      return !this.dictStats.isEmpty();
   }

   public boolean hasDictionaryEncodedPages() {
      Set<Encoding> encodings = this.dataStats.keySet();
      return encodings.contains(Encoding.RLE_DICTIONARY) || encodings.contains(Encoding.PLAIN_DICTIONARY);
   }

   public boolean hasNonDictionaryEncodedPages() {
      if (this.dataStats.isEmpty()) {
         return false;
      } else {
         Set<Encoding> encodings = new HashSet(this.dataStats.keySet());
         if (!encodings.remove(Encoding.RLE_DICTIONARY) && !encodings.remove(Encoding.PLAIN_DICTIONARY)) {
            return true;
         } else {
            return !encodings.isEmpty();
         }
      }
   }

   public boolean usesV2Pages() {
      return this.usesV2Pages;
   }

   public static class Builder {
      private final Map dictStats = new LinkedHashMap();
      private final Map dataStats = new LinkedHashMap();
      private boolean usesV2Pages = false;

      public Builder clear() {
         this.usesV2Pages = false;
         this.dictStats.clear();
         this.dataStats.clear();
         return this;
      }

      public Builder withV2Pages() {
         this.usesV2Pages = true;
         return this;
      }

      public Builder addDictEncoding(Encoding encoding) {
         return this.addDictEncoding(encoding, 1);
      }

      public Builder addDictEncoding(Encoding encoding, int numPages) {
         ((AtomicInteger)this.dictStats.computeIfAbsent(encoding, (enc) -> new AtomicInteger(0))).addAndGet(numPages);
         return this;
      }

      public Builder addDataEncodings(Collection encodings) {
         for(Encoding encoding : encodings) {
            this.addDataEncoding(encoding);
         }

         return this;
      }

      public Builder addDataEncoding(Encoding encoding) {
         return this.addDataEncoding(encoding, 1);
      }

      public Builder addDataEncoding(Encoding encoding, int numPages) {
         ((AtomicInteger)this.dataStats.computeIfAbsent(encoding, (enc) -> new AtomicInteger(0))).addAndGet(numPages);
         return this;
      }

      public EncodingStats build() {
         return new EncodingStats(Collections.unmodifiableMap(new LinkedHashMap(this.dictStats)), Collections.unmodifiableMap(new LinkedHashMap(this.dataStats)), this.usesV2Pages);
      }
   }
}
