package org.apache.arrow.vector.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.VisibleForTesting;

public interface DictionaryProvider {
   Dictionary lookup(long var1);

   Set getDictionaryIds();

   public static class MapDictionaryProvider implements AutoCloseable, DictionaryProvider {
      private final Map map = new HashMap();

      public MapDictionaryProvider(Dictionary... dictionaries) {
         for(Dictionary dictionary : dictionaries) {
            this.put(dictionary);
         }

      }

      @VisibleForTesting
      public void copyStructureFrom(DictionaryProvider other, BufferAllocator allocator) {
         for(Long id : other.getDictionaryIds()) {
            Dictionary otherDict = other.lookup(id);
            Dictionary newDict = new Dictionary(otherDict.getVector().getField().createVector(allocator), otherDict.getEncoding());
            this.put(newDict);
         }

      }

      public void put(Dictionary dictionary) {
         this.map.put(dictionary.getEncoding().getId(), dictionary);
      }

      public final Set getDictionaryIds() {
         return this.map.keySet();
      }

      public Dictionary lookup(long id) {
         return (Dictionary)this.map.get(id);
      }

      public void close() {
         for(Dictionary dictionary : this.map.values()) {
            dictionary.getVector().close();
         }

      }
   }
}
