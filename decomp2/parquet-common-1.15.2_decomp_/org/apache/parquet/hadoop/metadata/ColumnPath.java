package org.apache.parquet.hadoop.metadata;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class ColumnPath implements Iterable, Serializable {
   private static Canonicalizer paths = new Canonicalizer() {
      protected ColumnPath toCanonical(ColumnPath value) {
         String[] path = new String[value.p.length];

         for(int i = 0; i < value.p.length; ++i) {
            path[i] = value.p[i].intern();
         }

         return new ColumnPath(path);
      }
   };
   private final String[] p;

   public static ColumnPath fromDotString(String path) {
      Objects.requireNonNull(path, "path cannot be null");
      return get(path.split("\\."));
   }

   public static ColumnPath get(String... path) {
      return (ColumnPath)paths.canonicalize(new ColumnPath(path));
   }

   private ColumnPath(String[] path) {
      this.p = path;
   }

   public boolean equals(Object obj) {
      return obj instanceof ColumnPath ? Arrays.equals(this.p, ((ColumnPath)obj).p) : false;
   }

   public int hashCode() {
      return Arrays.hashCode(this.p);
   }

   public String toDotString() {
      return String.join(".", this.p);
   }

   public String toString() {
      return Arrays.toString(this.p);
   }

   public Iterator iterator() {
      return Arrays.asList(this.p).iterator();
   }

   public int size() {
      return this.p.length;
   }

   public String[] toArray() {
      return (String[])this.p.clone();
   }

   public List toList() {
      return Collections.unmodifiableList(Arrays.asList(this.p));
   }
}
