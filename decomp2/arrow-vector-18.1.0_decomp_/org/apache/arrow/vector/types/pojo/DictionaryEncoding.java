package org.apache.arrow.vector.types.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class DictionaryEncoding {
   private final long id;
   private final boolean ordered;
   private final ArrowType.Int indexType;

   @JsonCreator
   public DictionaryEncoding(@JsonProperty("id") long id, @JsonProperty("isOrdered") boolean ordered, @JsonProperty("indexType") ArrowType.Int indexType) {
      this.id = id;
      this.ordered = ordered;
      this.indexType = indexType == null ? new ArrowType.Int(32, true) : indexType;
   }

   public long getId() {
      return this.id;
   }

   @JsonGetter("isOrdered")
   public boolean isOrdered() {
      return this.ordered;
   }

   public ArrowType.Int getIndexType() {
      return this.indexType;
   }

   public String toString() {
      long var10000 = this.id;
      return "DictionaryEncoding[id=" + var10000 + ",ordered=" + this.ordered + ",indexType=" + String.valueOf(this.indexType) + "]";
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof DictionaryEncoding)) {
         return false;
      } else {
         DictionaryEncoding that = (DictionaryEncoding)o;
         return this.id == that.id && this.ordered == that.ordered && Objects.equals(this.indexType, that.indexType);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.id, this.ordered, this.indexType});
   }
}
