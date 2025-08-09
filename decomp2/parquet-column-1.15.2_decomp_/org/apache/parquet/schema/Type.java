package org.apache.parquet.schema;

import java.util.List;
import java.util.Objects;
import org.apache.parquet.io.InvalidRecordException;

public abstract class Type {
   private final String name;
   private final Repetition repetition;
   private final LogicalTypeAnnotation logicalTypeAnnotation;
   private final ID id;

   /** @deprecated */
   @Deprecated
   public Type(String name, Repetition repetition) {
      this(name, repetition, (LogicalTypeAnnotation)((LogicalTypeAnnotation)null), (ID)null);
   }

   /** @deprecated */
   @Deprecated
   public Type(String name, Repetition repetition, OriginalType originalType) {
      this(name, repetition, (OriginalType)originalType, (ID)null);
   }

   Type(String name, Repetition repetition, OriginalType originalType, ID id) {
      this(name, repetition, originalType, (DecimalMetadata)null, id);
   }

   Type(String name, Repetition repetition, OriginalType originalType, DecimalMetadata decimalMetadata, ID id) {
      this.name = (String)Objects.requireNonNull(name, "name cannot be null");
      this.repetition = (Repetition)Objects.requireNonNull(repetition, "repetition cannot be null");
      this.logicalTypeAnnotation = originalType == null ? null : LogicalTypeAnnotation.fromOriginalType(originalType, decimalMetadata);
      this.id = id;
   }

   Type(String name, Repetition repetition, LogicalTypeAnnotation logicalTypeAnnotation) {
      this(name, repetition, (LogicalTypeAnnotation)logicalTypeAnnotation, (ID)null);
   }

   Type(String name, Repetition repetition, LogicalTypeAnnotation logicalTypeAnnotation, ID id) {
      this.name = (String)Objects.requireNonNull(name, "name cannot be null");
      this.repetition = (Repetition)Objects.requireNonNull(repetition, "repetition cannot be null");
      this.logicalTypeAnnotation = logicalTypeAnnotation;
      this.id = id;
   }

   public abstract Type withId(int var1);

   public String getName() {
      return this.name;
   }

   public boolean isRepetition(Repetition rep) {
      return this.repetition == rep;
   }

   public Repetition getRepetition() {
      return this.repetition;
   }

   public ID getId() {
      return this.id;
   }

   public LogicalTypeAnnotation getLogicalTypeAnnotation() {
      return this.logicalTypeAnnotation;
   }

   public OriginalType getOriginalType() {
      return this.logicalTypeAnnotation == null ? null : this.logicalTypeAnnotation.toOriginalType();
   }

   public abstract boolean isPrimitive();

   public GroupType asGroupType() {
      if (this.isPrimitive()) {
         throw new ClassCastException(this + " is not a group");
      } else {
         return (GroupType)this;
      }
   }

   public PrimitiveType asPrimitiveType() {
      if (!this.isPrimitive()) {
         throw new ClassCastException(this + " is not primitive");
      } else {
         return (PrimitiveType)this;
      }
   }

   public abstract void writeToStringBuilder(StringBuilder var1, String var2);

   public abstract void accept(TypeVisitor var1);

   /** @deprecated */
   @Deprecated
   protected abstract int typeHashCode();

   /** @deprecated */
   @Deprecated
   protected abstract boolean typeEquals(Type var1);

   public int hashCode() {
      int c = this.repetition.hashCode();
      c = 31 * c + this.name.hashCode();
      if (this.logicalTypeAnnotation != null) {
         c = 31 * c + this.logicalTypeAnnotation.hashCode();
      }

      if (this.id != null) {
         c = 31 * c + this.id.hashCode();
      }

      return c;
   }

   protected boolean equals(Type other) {
      return this.name.equals(other.name) && this.repetition == other.repetition && this.eqOrBothNull(this.repetition, other.repetition) && this.eqOrBothNull(this.id, other.id) && this.eqOrBothNull(this.logicalTypeAnnotation, other.logicalTypeAnnotation);
   }

   public boolean equals(Object other) {
      return other instanceof Type && other != null ? this.equals((Type)other) : false;
   }

   protected boolean eqOrBothNull(Object o1, Object o2) {
      return o1 == null && o2 == null || o1 != null && o1.equals(o2);
   }

   protected abstract int getMaxRepetitionLevel(String[] var1, int var2);

   protected abstract int getMaxDefinitionLevel(String[] var1, int var2);

   protected abstract Type getType(String[] var1, int var2);

   protected abstract List getPaths(int var1);

   protected abstract boolean containsPath(String[] var1, int var2);

   protected abstract Type union(Type var1);

   protected abstract Type union(Type var1, boolean var2);

   public String toString() {
      StringBuilder sb = new StringBuilder();
      this.writeToStringBuilder(sb, "");
      return sb.toString();
   }

   void checkContains(Type subType) {
      if (!this.name.equals(subType.name) || this.repetition != subType.repetition) {
         throw new InvalidRecordException(subType + " found: expected " + this);
      }
   }

   abstract Object convert(List var1, TypeConverter var2);

   public static final class ID {
      private final int id;

      public ID(int id) {
         this.id = id;
      }

      /** @deprecated */
      @Deprecated
      public int getId() {
         return this.id;
      }

      public int intValue() {
         return this.id;
      }

      public boolean equals(Object obj) {
         return obj instanceof ID && ((ID)obj).id == this.id;
      }

      public int hashCode() {
         return this.id;
      }

      public String toString() {
         return String.valueOf(this.id);
      }
   }

   public static enum Repetition {
      REQUIRED {
         public boolean isMoreRestrictiveThan(Repetition other) {
            return other != REQUIRED;
         }
      },
      OPTIONAL {
         public boolean isMoreRestrictiveThan(Repetition other) {
            return other == REPEATED;
         }
      },
      REPEATED {
         public boolean isMoreRestrictiveThan(Repetition other) {
            return false;
         }
      };

      private Repetition() {
      }

      public abstract boolean isMoreRestrictiveThan(Repetition var1);

      public static Repetition leastRestrictive(Repetition... repetitions) {
         boolean hasOptional = false;

         for(Repetition repetition : repetitions) {
            if (repetition == REPEATED) {
               return REPEATED;
            }

            if (repetition == OPTIONAL) {
               hasOptional = true;
            }
         }

         if (hasOptional) {
            return OPTIONAL;
         } else {
            return REQUIRED;
         }
      }
   }
}
