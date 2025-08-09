package org.apache.parquet.column;

import java.util.Arrays;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;

public class ColumnDescriptor implements Comparable {
   private final String[] path;
   private final PrimitiveType type;
   private final int maxRep;
   private final int maxDef;

   /** @deprecated */
   @Deprecated
   public ColumnDescriptor(String[] path, PrimitiveType.PrimitiveTypeName type, int maxRep, int maxDef) {
      this(path, type, 0, maxRep, maxDef);
   }

   /** @deprecated */
   @Deprecated
   public ColumnDescriptor(String[] path, PrimitiveType.PrimitiveTypeName type, int typeLength, int maxRep, int maxDef) {
      this(path, new PrimitiveType(Type.Repetition.OPTIONAL, type, typeLength, ""), maxRep, maxDef);
   }

   public ColumnDescriptor(String[] path, PrimitiveType type, int maxRep, int maxDef) {
      this.path = path;
      this.type = type;
      this.maxRep = maxRep;
      this.maxDef = maxDef;
   }

   public String[] getPath() {
      return this.path;
   }

   public int getMaxRepetitionLevel() {
      return this.maxRep;
   }

   public int getMaxDefinitionLevel() {
      return this.maxDef;
   }

   /** @deprecated */
   @Deprecated
   public PrimitiveType.PrimitiveTypeName getType() {
      return this.type.getPrimitiveTypeName();
   }

   /** @deprecated */
   @Deprecated
   public int getTypeLength() {
      return this.type.getTypeLength();
   }

   public PrimitiveType getPrimitiveType() {
      return this.type;
   }

   public int hashCode() {
      return Arrays.hashCode(this.path);
   }

   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (!(other instanceof ColumnDescriptor)) {
         return false;
      } else {
         ColumnDescriptor descriptor = (ColumnDescriptor)other;
         return Arrays.equals(this.path, descriptor.path);
      }
   }

   public int compareTo(ColumnDescriptor o) {
      int length = this.path.length < o.path.length ? this.path.length : o.path.length;

      for(int i = 0; i < length; ++i) {
         int compareTo = this.path[i].compareTo(o.path[i]);
         if (compareTo != 0) {
            return compareTo;
         }
      }

      return this.path.length - o.path.length;
   }

   public String toString() {
      return Arrays.toString(this.path) + " " + this.type;
   }
}
