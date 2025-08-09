package org.apache.arrow.vector.dictionary;

import java.util.Objects;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.compare.VectorEqualsVisitor;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;

public class Dictionary {
   private final DictionaryEncoding encoding;
   private final FieldVector dictionary;

   public Dictionary(FieldVector dictionary, DictionaryEncoding encoding) {
      this.dictionary = dictionary;
      this.encoding = encoding;
   }

   public FieldVector getVector() {
      return this.dictionary;
   }

   public DictionaryEncoding getEncoding() {
      return this.encoding;
   }

   public ArrowType getVectorType() {
      return this.dictionary.getField().getType();
   }

   public String toString() {
      String var10000 = String.valueOf(this.encoding);
      return "Dictionary " + var10000 + " " + String.valueOf(this.dictionary);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Dictionary)) {
         return false;
      } else {
         Dictionary that = (Dictionary)o;
         boolean var10000;
         if (Objects.equals(this.encoding, that.encoding)) {
            new VectorEqualsVisitor();
            if (VectorEqualsVisitor.vectorEquals(that.dictionary, this.dictionary)) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.encoding, this.dictionary});
   }
}
