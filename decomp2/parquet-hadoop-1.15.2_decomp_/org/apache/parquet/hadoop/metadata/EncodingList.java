package org.apache.parquet.hadoop.metadata;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.parquet.column.Encoding;

public class EncodingList implements Iterable {
   private static Canonicalizer encodingLists = new Canonicalizer();
   private final List encodings;

   public static EncodingList getEncodingList(List encodings) {
      return (EncodingList)encodingLists.canonicalize(new EncodingList(encodings));
   }

   private EncodingList(List encodings) {
      this.encodings = Collections.unmodifiableList(encodings);
   }

   public boolean equals(Object obj) {
      if (obj instanceof EncodingList) {
         List<Encoding> other = ((EncodingList)obj).encodings;
         int size = other.size();
         if (size != this.encodings.size()) {
            return false;
         } else {
            for(int i = 0; i < size; ++i) {
               if (!((Encoding)other.get(i)).equals(this.encodings.get(i))) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = 1;

      for(Encoding element : this.encodings) {
         result = 31 * result + (element == null ? 0 : element.hashCode());
      }

      return result;
   }

   public List toList() {
      return this.encodings;
   }

   public Iterator iterator() {
      return this.encodings.iterator();
   }

   public int size() {
      return this.encodings.size();
   }
}
