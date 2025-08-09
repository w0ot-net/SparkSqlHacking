package org.apache.parquet.hadoop.metadata;

import java.util.Arrays;
import java.util.Set;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type.Repetition;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonIgnore;

public class ColumnChunkProperties {
   private static Canonicalizer properties = new Canonicalizer();
   private final CompressionCodecName codec;
   private final ColumnPath path;
   private final PrimitiveType type;
   private final Set encodings;

   /** @deprecated */
   @Deprecated
   public static ColumnChunkProperties get(ColumnPath path, PrimitiveType.PrimitiveTypeName type, CompressionCodecName codec, Set encodings) {
      return get(path, new PrimitiveType(Repetition.OPTIONAL, type, ""), codec, encodings);
   }

   public static ColumnChunkProperties get(ColumnPath path, PrimitiveType type, CompressionCodecName codec, Set encodings) {
      return (ColumnChunkProperties)properties.canonicalize(new ColumnChunkProperties(codec, path, type, encodings));
   }

   private ColumnChunkProperties(CompressionCodecName codec, ColumnPath path, PrimitiveType type, Set encodings) {
      this.codec = codec;
      this.path = path;
      this.type = type;
      this.encodings = encodings;
   }

   public CompressionCodecName getCodec() {
      return this.codec;
   }

   public ColumnPath getPath() {
      return this.path;
   }

   /** @deprecated */
   @Deprecated
   @JsonIgnore
   public PrimitiveType.PrimitiveTypeName getType() {
      return this.type.getPrimitiveTypeName();
   }

   public PrimitiveType getPrimitiveType() {
      return this.type;
   }

   public Set getEncodings() {
      return this.encodings;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ColumnChunkProperties)) {
         return false;
      } else {
         ColumnChunkProperties other = (ColumnChunkProperties)obj;
         return other.codec == this.codec && other.path.equals(this.path) && other.type.equals(this.type) && this.equals(other.encodings, this.encodings);
      }
   }

   private boolean equals(Set a, Set b) {
      return a.size() == b.size() && a.containsAll(b);
   }

   public int hashCode() {
      return this.codec.hashCode() ^ this.path.hashCode() ^ this.type.hashCode() ^ Arrays.hashCode(this.encodings.toArray());
   }

   public String toString() {
      return this.codec + " " + this.path + " " + this.type + "  " + this.encodings;
   }
}
