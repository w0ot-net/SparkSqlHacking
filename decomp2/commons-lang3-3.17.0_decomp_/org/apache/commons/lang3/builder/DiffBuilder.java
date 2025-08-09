package org.apache.commons.lang3.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;

public class DiffBuilder implements org.apache.commons.lang3.builder.Builder {
   static final String TO_STRING_FORMAT = "%s differs from %s";
   private final List diffs;
   private final boolean equals;
   private final Object left;
   private final Object right;
   private final ToStringStyle style;
   private final String toStringFormat;

   public static Builder builder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public DiffBuilder(Object left, Object right, ToStringStyle style) {
      this(left, right, style, true);
   }

   /** @deprecated */
   @Deprecated
   public DiffBuilder(Object left, Object right, ToStringStyle style, boolean testObjectsEquals) {
      this(left, right, style, testObjectsEquals, "%s differs from %s");
   }

   private DiffBuilder(Object left, Object right, ToStringStyle style, boolean testObjectsEquals, String toStringFormat) {
      this.left = Objects.requireNonNull(left, "left");
      this.right = Objects.requireNonNull(right, "right");
      this.diffs = new ArrayList();
      this.toStringFormat = toStringFormat;
      this.style = style != null ? style : ToStringStyle.DEFAULT_STYLE;
      this.equals = testObjectsEquals && Objects.equals(left, right);
   }

   private DiffBuilder add(String fieldName, Supplier left, Supplier right, Class type) {
      this.diffs.add(new SDiff(fieldName, left, right, type));
      return this;
   }

   public DiffBuilder append(String fieldName, boolean lhs, boolean rhs) {
      return !this.equals && lhs != rhs ? this.add(fieldName, () -> lhs, () -> rhs, Boolean.class) : this;
   }

   public DiffBuilder append(String fieldName, boolean[] lhs, boolean[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> ArrayUtils.toObject(lhs), () -> ArrayUtils.toObject(rhs), Boolean[].class) : this;
   }

   public DiffBuilder append(String fieldName, byte lhs, byte rhs) {
      return !this.equals && lhs != rhs ? this.add(fieldName, () -> lhs, () -> rhs, Byte.class) : this;
   }

   public DiffBuilder append(String fieldName, byte[] lhs, byte[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> ArrayUtils.toObject(lhs), () -> ArrayUtils.toObject(rhs), Byte[].class) : this;
   }

   public DiffBuilder append(String fieldName, char lhs, char rhs) {
      return !this.equals && lhs != rhs ? this.add(fieldName, () -> lhs, () -> rhs, Character.class) : this;
   }

   public DiffBuilder append(String fieldName, char[] lhs, char[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> ArrayUtils.toObject(lhs), () -> ArrayUtils.toObject(rhs), Character[].class) : this;
   }

   public DiffBuilder append(String fieldName, DiffResult diffResult) {
      Objects.requireNonNull(diffResult, "diffResult");
      if (this.equals) {
         return this;
      } else {
         diffResult.getDiffs().forEach((diff) -> this.append(fieldName + "." + diff.getFieldName(), diff.getLeft(), diff.getRight()));
         return this;
      }
   }

   public DiffBuilder append(String fieldName, double lhs, double rhs) {
      return !this.equals && Double.doubleToLongBits(lhs) != Double.doubleToLongBits(rhs) ? this.add(fieldName, () -> lhs, () -> rhs, Double.class) : this;
   }

   public DiffBuilder append(String fieldName, double[] lhs, double[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> ArrayUtils.toObject(lhs), () -> ArrayUtils.toObject(rhs), Double[].class) : this;
   }

   public DiffBuilder append(String fieldName, float lhs, float rhs) {
      return !this.equals && Float.floatToIntBits(lhs) != Float.floatToIntBits(rhs) ? this.add(fieldName, () -> lhs, () -> rhs, Float.class) : this;
   }

   public DiffBuilder append(String fieldName, float[] lhs, float[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> ArrayUtils.toObject(lhs), () -> ArrayUtils.toObject(rhs), Float[].class) : this;
   }

   public DiffBuilder append(String fieldName, int lhs, int rhs) {
      return !this.equals && lhs != rhs ? this.add(fieldName, () -> lhs, () -> rhs, Integer.class) : this;
   }

   public DiffBuilder append(String fieldName, int[] lhs, int[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> ArrayUtils.toObject(lhs), () -> ArrayUtils.toObject(rhs), Integer[].class) : this;
   }

   public DiffBuilder append(String fieldName, long lhs, long rhs) {
      return !this.equals && lhs != rhs ? this.add(fieldName, () -> lhs, () -> rhs, Long.class) : this;
   }

   public DiffBuilder append(String fieldName, long[] lhs, long[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> ArrayUtils.toObject(lhs), () -> ArrayUtils.toObject(rhs), Long[].class) : this;
   }

   public DiffBuilder append(String fieldName, Object lhs, Object rhs) {
      if (!this.equals && lhs != rhs) {
         Object test = lhs != null ? lhs : rhs;
         if (ObjectUtils.isArray(test)) {
            if (test instanceof boolean[]) {
               return this.append(fieldName, (boolean[])lhs, (boolean[])rhs);
            } else if (test instanceof byte[]) {
               return this.append(fieldName, (byte[])lhs, (byte[])rhs);
            } else if (test instanceof char[]) {
               return this.append(fieldName, (char[])lhs, (char[])rhs);
            } else if (test instanceof double[]) {
               return this.append(fieldName, (double[])lhs, (double[])rhs);
            } else if (test instanceof float[]) {
               return this.append(fieldName, (float[])lhs, (float[])rhs);
            } else if (test instanceof int[]) {
               return this.append(fieldName, (int[])lhs, (int[])rhs);
            } else if (test instanceof long[]) {
               return this.append(fieldName, (long[])lhs, (long[])rhs);
            } else {
               return test instanceof short[] ? this.append(fieldName, (short[])lhs, (short[])rhs) : this.append(fieldName, lhs, rhs);
            }
         } else {
            return Objects.equals(lhs, rhs) ? this : this.add(fieldName, () -> lhs, () -> rhs, Object.class);
         }
      } else {
         return this;
      }
   }

   public DiffBuilder append(String fieldName, Object[] lhs, Object[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> lhs, () -> rhs, Object[].class) : this;
   }

   public DiffBuilder append(String fieldName, short lhs, short rhs) {
      return !this.equals && lhs != rhs ? this.add(fieldName, () -> lhs, () -> rhs, Short.class) : this;
   }

   public DiffBuilder append(String fieldName, short[] lhs, short[] rhs) {
      return !this.equals && !Arrays.equals(lhs, rhs) ? this.add(fieldName, () -> ArrayUtils.toObject(lhs), () -> ArrayUtils.toObject(rhs), Short[].class) : this;
   }

   public DiffResult build() {
      return new DiffResult(this.left, this.right, this.diffs, this.style, this.toStringFormat);
   }

   Object getLeft() {
      return this.left;
   }

   Object getRight() {
      return this.right;
   }

   public static final class Builder {
      private Object left;
      private Object right;
      private ToStringStyle style;
      private boolean testObjectsEquals = true;
      private String toStringFormat = "%s differs from %s";

      public DiffBuilder build() {
         return new DiffBuilder(this.left, this.right, this.style, this.testObjectsEquals, this.toStringFormat);
      }

      public Builder setLeft(Object left) {
         this.left = left;
         return this;
      }

      public Builder setRight(Object right) {
         this.right = right;
         return this;
      }

      public Builder setStyle(ToStringStyle style) {
         this.style = style != null ? style : ToStringStyle.DEFAULT_STYLE;
         return this;
      }

      public Builder setTestObjectsEquals(boolean testObjectsEquals) {
         this.testObjectsEquals = testObjectsEquals;
         return this;
      }

      public Builder setToStringFormat(String toStringFormat) {
         this.toStringFormat = toStringFormat != null ? toStringFormat : "%s differs from %s";
         return this;
      }
   }

   private static final class SDiff extends Diff {
      private static final long serialVersionUID = 1L;
      private final transient Supplier leftSupplier;
      private final transient Supplier rightSupplier;

      private SDiff(String fieldName, Supplier leftSupplier, Supplier rightSupplier, Class type) {
         super(fieldName, type);
         this.leftSupplier = (Supplier)Objects.requireNonNull(leftSupplier);
         this.rightSupplier = (Supplier)Objects.requireNonNull(rightSupplier);
      }

      public Object getLeft() {
         return this.leftSupplier.get();
      }

      public Object getRight() {
         return this.rightSupplier.get();
      }
   }
}
