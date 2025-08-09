package org.sparkproject.jetty.util;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Index {
   Object get(String var1);

   Object get(ByteBuffer var1);

   Object get(String var1, int var2, int var3);

   Object get(ByteBuffer var1, int var2, int var3);

   Object getBest(String var1, int var2, int var3);

   Object getBest(String var1);

   Object getBest(ByteBuffer var1, int var2, int var3);

   default Object getBest(ByteBuffer b) {
      return this.getBest((ByteBuffer)b, 0, b.remaining());
   }

   Object getBest(byte[] var1, int var2, int var3);

   default Object getBest(byte[] b) {
      return this.getBest((byte[])b, 0, b.length);
   }

   boolean isEmpty();

   int size();

   Set keySet();

   static Mutable buildMutableVisibleAsciiAlphabet(boolean caseSensitive, int maxCapacity) {
      if (maxCapacity >= 0 && maxCapacity <= ArrayTrie.MAX_CAPACITY) {
         return (Mutable)(maxCapacity == 0 ? EmptyTrie.instance(caseSensitive) : new ArrayTrie(caseSensitive, maxCapacity));
      } else {
         return new TreeTrie(caseSensitive);
      }
   }

   static Index empty(boolean caseSensitive) {
      return EmptyTrie.instance(caseSensitive);
   }

   public static class Builder {
      Map contents;
      boolean caseSensitive;

      public Builder() {
         this.caseSensitive = false;
         this.contents = null;
      }

      Builder(boolean caseSensitive, Map contents) {
         this.caseSensitive = caseSensitive;
         this.contents = contents;
      }

      private Map contents() {
         if (this.contents == null) {
            this.contents = new LinkedHashMap();
         }

         return this.contents;
      }

      public Builder caseSensitive(boolean caseSensitive) {
         this.caseSensitive = caseSensitive;
         return this;
      }

      public Builder withAll(Object[] values, Function keyFunction) {
         for(Object value : values) {
            String key = (String)keyFunction.apply(value);
            this.contents().put(key, value);
         }

         return this;
      }

      public Builder withAll(Supplier entriesSupplier) {
         Map<String, V> map = (Map)entriesSupplier.get();
         this.contents().putAll(map);
         return this;
      }

      public Builder with(Object value) {
         this.contents().put(value.toString(), value);
         return this;
      }

      public Builder with(String key, Object value) {
         this.contents().put(key, value);
         return this;
      }

      public Mutable.Builder mutable() {
         return new Mutable.Builder(this.caseSensitive, this.contents);
      }

      public Index build() {
         if (this.contents == null) {
            return EmptyTrie.instance(this.caseSensitive);
         } else {
            int capacity = AbstractTrie.requiredCapacity(this.contents.keySet(), this.caseSensitive);
            AbstractTrie<V> trie = ArrayTrie.from(capacity, this.caseSensitive, this.contents);
            if (trie != null) {
               return trie;
            } else {
               trie = TreeTrie.from(this.caseSensitive, this.contents);
               if (trie != null) {
                  return trie;
               } else {
                  throw new IllegalStateException("No suitable Trie implementation : " + String.valueOf(this));
               }
            }
         }
      }

      public String toString() {
         return String.format("%s{c=%d,cs=%b}", super.toString(), this.contents == null ? 0 : this.contents.size(), this.caseSensitive);
      }
   }

   public interface Mutable extends Index {
      boolean put(String var1, Object var2);

      boolean put(Object var1);

      Object remove(String var1);

      void clear();

      public static class Builder extends Builder {
         private int maxCapacity = -1;

         Builder(boolean caseSensitive, Map contents) {
            super(caseSensitive, contents);
         }

         public Builder maxCapacity(int capacity) {
            this.maxCapacity = capacity;
            return this;
         }

         public Builder mutable() {
            return this;
         }

         public Mutable build() {
            if (this.maxCapacity == 0) {
               return EmptyTrie.instance(this.caseSensitive);
            } else {
               int capacity = this.contents == null ? 0 : AbstractTrie.requiredCapacity(this.contents.keySet(), this.caseSensitive);
               if (this.maxCapacity >= 0 && capacity > this.maxCapacity) {
                  throw new IllegalStateException("Insufficient maxCapacity for contents");
               } else {
                  AbstractTrie<V> trie = ArrayTrie.from(this.maxCapacity, this.caseSensitive, this.contents);
                  if (trie != null) {
                     return trie;
                  } else {
                     trie = TreeTrie.from(this.caseSensitive, this.contents);
                     if (trie != null) {
                        return trie;
                     } else {
                        throw new IllegalStateException("No suitable Trie implementation: " + String.valueOf(this));
                     }
                  }
               }
            }
         }
      }
   }
}
