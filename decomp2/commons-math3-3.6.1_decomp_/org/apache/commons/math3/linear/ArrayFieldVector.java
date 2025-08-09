package org.apache.commons.math3.linear;

import [Lorg.apache.commons.math3.FieldElement;;
import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class ArrayFieldVector implements FieldVector, Serializable {
   private static final long serialVersionUID = 7648186910365927050L;
   private FieldElement[] data;
   private final Field field;

   public ArrayFieldVector(Field field) {
      this(field, 0);
   }

   public ArrayFieldVector(Field field, int size) {
      this.field = field;
      this.data = (FieldElement[])MathArrays.buildArray(field, size);
   }

   public ArrayFieldVector(int size, FieldElement preset) {
      this(preset.getField(), size);
      Arrays.fill(this.data, preset);
   }

   public ArrayFieldVector(FieldElement[] d) throws NullArgumentException, ZeroException {
      MathUtils.checkNotNull(d);

      try {
         this.field = d[0].getField();
         this.data = (FieldElement[])((FieldElement;)d).clone();
      } catch (ArrayIndexOutOfBoundsException var3) {
         throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
      }
   }

   public ArrayFieldVector(Field field, FieldElement[] d) throws NullArgumentException {
      MathUtils.checkNotNull(d);
      this.field = field;
      this.data = (FieldElement[])((FieldElement;)d).clone();
   }

   public ArrayFieldVector(FieldElement[] d, boolean copyArray) throws NullArgumentException, ZeroException {
      MathUtils.checkNotNull(d);
      if (d.length == 0) {
         throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
      } else {
         this.field = d[0].getField();
         this.data = copyArray ? (FieldElement[])((FieldElement;)d).clone() : d;
      }
   }

   public ArrayFieldVector(Field field, FieldElement[] d, boolean copyArray) throws NullArgumentException {
      MathUtils.checkNotNull(d);
      this.field = field;
      this.data = copyArray ? (FieldElement[])((FieldElement;)d).clone() : d;
   }

   public ArrayFieldVector(FieldElement[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
      MathUtils.checkNotNull(d);
      if (d.length < pos + size) {
         throw new NumberIsTooLargeException(pos + size, d.length, true);
      } else {
         this.field = d[0].getField();
         this.data = (FieldElement[])MathArrays.buildArray(this.field, size);
         System.arraycopy(d, pos, this.data, 0, size);
      }
   }

   public ArrayFieldVector(Field field, FieldElement[] d, int pos, int size) throws NullArgumentException, NumberIsTooLargeException {
      MathUtils.checkNotNull(d);
      if (d.length < pos + size) {
         throw new NumberIsTooLargeException(pos + size, d.length, true);
      } else {
         this.field = field;
         this.data = (FieldElement[])MathArrays.buildArray(field, size);
         System.arraycopy(d, pos, this.data, 0, size);
      }
   }

   public ArrayFieldVector(FieldVector v) throws NullArgumentException {
      MathUtils.checkNotNull(v);
      this.field = v.getField();
      this.data = (FieldElement[])MathArrays.buildArray(this.field, v.getDimension());

      for(int i = 0; i < this.data.length; ++i) {
         this.data[i] = v.getEntry(i);
      }

   }

   public ArrayFieldVector(ArrayFieldVector v) throws NullArgumentException {
      MathUtils.checkNotNull(v);
      this.field = v.getField();
      this.data = (FieldElement[])v.data.clone();
   }

   public ArrayFieldVector(ArrayFieldVector v, boolean deep) throws NullArgumentException {
      MathUtils.checkNotNull(v);
      this.field = v.getField();
      this.data = deep ? (FieldElement[])v.data.clone() : v.data;
   }

   /** @deprecated */
   @Deprecated
   public ArrayFieldVector(ArrayFieldVector v1, ArrayFieldVector v2) throws NullArgumentException {
      this((FieldVector)v1, (FieldVector)v2);
   }

   public ArrayFieldVector(FieldVector v1, FieldVector v2) throws NullArgumentException {
      MathUtils.checkNotNull(v1);
      MathUtils.checkNotNull(v2);
      this.field = v1.getField();
      T[] v1Data = (T[])(v1 instanceof ArrayFieldVector ? ((ArrayFieldVector)v1).data : v1.toArray());
      T[] v2Data = (T[])(v2 instanceof ArrayFieldVector ? ((ArrayFieldVector)v2).data : v2.toArray());
      this.data = (FieldElement[])MathArrays.buildArray(this.field, v1Data.length + v2Data.length);
      System.arraycopy(v1Data, 0, this.data, 0, v1Data.length);
      System.arraycopy(v2Data, 0, this.data, v1Data.length, v2Data.length);
   }

   /** @deprecated */
   @Deprecated
   public ArrayFieldVector(ArrayFieldVector v1, FieldElement[] v2) throws NullArgumentException {
      this((FieldVector)v1, (FieldElement[])v2);
   }

   public ArrayFieldVector(FieldVector v1, FieldElement[] v2) throws NullArgumentException {
      MathUtils.checkNotNull(v1);
      MathUtils.checkNotNull(v2);
      this.field = v1.getField();
      T[] v1Data = (T[])(v1 instanceof ArrayFieldVector ? ((ArrayFieldVector)v1).data : v1.toArray());
      this.data = (FieldElement[])MathArrays.buildArray(this.field, v1Data.length + v2.length);
      System.arraycopy(v1Data, 0, this.data, 0, v1Data.length);
      System.arraycopy(v2, 0, this.data, v1Data.length, v2.length);
   }

   /** @deprecated */
   @Deprecated
   public ArrayFieldVector(FieldElement[] v1, ArrayFieldVector v2) throws NullArgumentException {
      this((FieldElement[])v1, (FieldVector)v2);
   }

   public ArrayFieldVector(FieldElement[] v1, FieldVector v2) throws NullArgumentException {
      MathUtils.checkNotNull(v1);
      MathUtils.checkNotNull(v2);
      this.field = v2.getField();
      T[] v2Data = (T[])(v2 instanceof ArrayFieldVector ? ((ArrayFieldVector)v2).data : v2.toArray());
      this.data = (FieldElement[])MathArrays.buildArray(this.field, v1.length + v2Data.length);
      System.arraycopy(v1, 0, this.data, 0, v1.length);
      System.arraycopy(v2Data, 0, this.data, v1.length, v2Data.length);
   }

   public ArrayFieldVector(FieldElement[] v1, FieldElement[] v2) throws NullArgumentException, ZeroException {
      MathUtils.checkNotNull(v1);
      MathUtils.checkNotNull(v2);
      if (v1.length + v2.length == 0) {
         throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
      } else {
         this.data = (FieldElement[])MathArrays.buildArray(v1[0].getField(), v1.length + v2.length);
         System.arraycopy(v1, 0, this.data, 0, v1.length);
         System.arraycopy(v2, 0, this.data, v1.length, v2.length);
         this.field = this.data[0].getField();
      }
   }

   public ArrayFieldVector(Field field, FieldElement[] v1, FieldElement[] v2) throws NullArgumentException, ZeroException {
      MathUtils.checkNotNull(v1);
      MathUtils.checkNotNull(v2);
      if (v1.length + v2.length == 0) {
         throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
      } else {
         this.data = (FieldElement[])MathArrays.buildArray(field, v1.length + v2.length);
         System.arraycopy(v1, 0, this.data, 0, v1.length);
         System.arraycopy(v2, 0, this.data, v1.length, v2.length);
         this.field = field;
      }
   }

   public Field getField() {
      return this.field;
   }

   public FieldVector copy() {
      return new ArrayFieldVector(this, true);
   }

   public FieldVector add(FieldVector v) throws DimensionMismatchException {
      try {
         return this.add((ArrayFieldVector)v);
      } catch (ClassCastException var5) {
         this.checkVectorDimensions(v);
         T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

         for(int i = 0; i < this.data.length; ++i) {
            out[i] = (FieldElement)this.data[i].add(v.getEntry(i));
         }

         return new ArrayFieldVector(this.field, out, false);
      }
   }

   public ArrayFieldVector add(ArrayFieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.data.length);
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

      for(int i = 0; i < this.data.length; ++i) {
         out[i] = (FieldElement)this.data[i].add(v.data[i]);
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector subtract(FieldVector v) throws DimensionMismatchException {
      try {
         return this.subtract((ArrayFieldVector)v);
      } catch (ClassCastException var5) {
         this.checkVectorDimensions(v);
         T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

         for(int i = 0; i < this.data.length; ++i) {
            out[i] = (FieldElement)this.data[i].subtract(v.getEntry(i));
         }

         return new ArrayFieldVector(this.field, out, false);
      }
   }

   public ArrayFieldVector subtract(ArrayFieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.data.length);
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

      for(int i = 0; i < this.data.length; ++i) {
         out[i] = (FieldElement)this.data[i].subtract(v.data[i]);
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector mapAdd(FieldElement d) throws NullArgumentException {
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

      for(int i = 0; i < this.data.length; ++i) {
         out[i] = (FieldElement)this.data[i].add(d);
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector mapAddToSelf(FieldElement d) throws NullArgumentException {
      for(int i = 0; i < this.data.length; ++i) {
         this.data[i] = (FieldElement)this.data[i].add(d);
      }

      return this;
   }

   public FieldVector mapSubtract(FieldElement d) throws NullArgumentException {
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

      for(int i = 0; i < this.data.length; ++i) {
         out[i] = (FieldElement)this.data[i].subtract(d);
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector mapSubtractToSelf(FieldElement d) throws NullArgumentException {
      for(int i = 0; i < this.data.length; ++i) {
         this.data[i] = (FieldElement)this.data[i].subtract(d);
      }

      return this;
   }

   public FieldVector mapMultiply(FieldElement d) throws NullArgumentException {
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

      for(int i = 0; i < this.data.length; ++i) {
         out[i] = (FieldElement)this.data[i].multiply(d);
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector mapMultiplyToSelf(FieldElement d) throws NullArgumentException {
      for(int i = 0; i < this.data.length; ++i) {
         this.data[i] = (FieldElement)this.data[i].multiply(d);
      }

      return this;
   }

   public FieldVector mapDivide(FieldElement d) throws NullArgumentException, MathArithmeticException {
      MathUtils.checkNotNull(d);
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

      for(int i = 0; i < this.data.length; ++i) {
         out[i] = (FieldElement)this.data[i].divide(d);
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector mapDivideToSelf(FieldElement d) throws NullArgumentException, MathArithmeticException {
      MathUtils.checkNotNull(d);

      for(int i = 0; i < this.data.length; ++i) {
         this.data[i] = (FieldElement)this.data[i].divide(d);
      }

      return this;
   }

   public FieldVector mapInv() throws MathArithmeticException {
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));
      T one = (T)((FieldElement)this.field.getOne());

      for(int i = 0; i < this.data.length; ++i) {
         try {
            out[i] = (FieldElement)one.divide(this.data[i]);
         } catch (MathArithmeticException var5) {
            throw new MathArithmeticException(LocalizedFormats.INDEX, new Object[]{i});
         }
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector mapInvToSelf() throws MathArithmeticException {
      T one = (T)((FieldElement)this.field.getOne());

      for(int i = 0; i < this.data.length; ++i) {
         try {
            this.data[i] = (FieldElement)one.divide(this.data[i]);
         } catch (MathArithmeticException var4) {
            throw new MathArithmeticException(LocalizedFormats.INDEX, new Object[]{i});
         }
      }

      return this;
   }

   public FieldVector ebeMultiply(FieldVector v) throws DimensionMismatchException {
      try {
         return this.ebeMultiply((ArrayFieldVector)v);
      } catch (ClassCastException var5) {
         this.checkVectorDimensions(v);
         T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

         for(int i = 0; i < this.data.length; ++i) {
            out[i] = (FieldElement)this.data[i].multiply(v.getEntry(i));
         }

         return new ArrayFieldVector(this.field, out, false);
      }
   }

   public ArrayFieldVector ebeMultiply(ArrayFieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.data.length);
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

      for(int i = 0; i < this.data.length; ++i) {
         out[i] = (FieldElement)this.data[i].multiply(v.data[i]);
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector ebeDivide(FieldVector v) throws DimensionMismatchException, MathArithmeticException {
      try {
         return this.ebeDivide((ArrayFieldVector)v);
      } catch (ClassCastException var7) {
         this.checkVectorDimensions(v);
         T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

         for(int i = 0; i < this.data.length; ++i) {
            try {
               out[i] = (FieldElement)this.data[i].divide(v.getEntry(i));
            } catch (MathArithmeticException var6) {
               throw new MathArithmeticException(LocalizedFormats.INDEX, new Object[]{i});
            }
         }

         return new ArrayFieldVector(this.field, out, false);
      }
   }

   public ArrayFieldVector ebeDivide(ArrayFieldVector v) throws DimensionMismatchException, MathArithmeticException {
      this.checkVectorDimensions(v.data.length);
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length));

      for(int i = 0; i < this.data.length; ++i) {
         try {
            out[i] = (FieldElement)this.data[i].divide(v.data[i]);
         } catch (MathArithmeticException var5) {
            throw new MathArithmeticException(LocalizedFormats.INDEX, new Object[]{i});
         }
      }

      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldElement[] getData() {
      return (FieldElement[])this.data.clone();
   }

   public FieldElement[] getDataRef() {
      return this.data;
   }

   public FieldElement dotProduct(FieldVector v) throws DimensionMismatchException {
      try {
         return this.dotProduct((ArrayFieldVector)v);
      } catch (ClassCastException var5) {
         this.checkVectorDimensions(v);
         T dot = (T)((FieldElement)this.field.getZero());

         for(int i = 0; i < this.data.length; ++i) {
            dot = (T)((FieldElement)dot.add(this.data[i].multiply(v.getEntry(i))));
         }

         return dot;
      }
   }

   public FieldElement dotProduct(ArrayFieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.data.length);
      T dot = (T)((FieldElement)this.field.getZero());

      for(int i = 0; i < this.data.length; ++i) {
         dot = (T)((FieldElement)dot.add(this.data[i].multiply(v.data[i])));
      }

      return dot;
   }

   public FieldVector projection(FieldVector v) throws DimensionMismatchException, MathArithmeticException {
      return v.mapMultiply((FieldElement)this.dotProduct(v).divide(v.dotProduct(v)));
   }

   public ArrayFieldVector projection(ArrayFieldVector v) throws DimensionMismatchException, MathArithmeticException {
      return (ArrayFieldVector)v.mapMultiply((FieldElement)this.dotProduct(v).divide(v.dotProduct(v)));
   }

   public FieldMatrix outerProduct(FieldVector v) {
      try {
         return this.outerProduct((ArrayFieldVector)v);
      } catch (ClassCastException var8) {
         int m = this.data.length;
         int n = v.getDimension();
         FieldMatrix<T> out = new Array2DRowFieldMatrix(this.field, m, n);

         for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
               out.setEntry(i, j, (FieldElement)this.data[i].multiply(v.getEntry(j)));
            }
         }

         return out;
      }
   }

   public FieldMatrix outerProduct(ArrayFieldVector v) {
      int m = this.data.length;
      int n = v.data.length;
      FieldMatrix<T> out = new Array2DRowFieldMatrix(this.field, m, n);

      for(int i = 0; i < m; ++i) {
         for(int j = 0; j < n; ++j) {
            out.setEntry(i, j, (FieldElement)this.data[i].multiply(v.data[j]));
         }
      }

      return out;
   }

   public FieldElement getEntry(int index) {
      return this.data[index];
   }

   public int getDimension() {
      return this.data.length;
   }

   public FieldVector append(FieldVector v) {
      try {
         return this.append((ArrayFieldVector)v);
      } catch (ClassCastException var3) {
         return new ArrayFieldVector(this, new ArrayFieldVector(v));
      }
   }

   public ArrayFieldVector append(ArrayFieldVector v) {
      return new ArrayFieldVector(this, v);
   }

   public FieldVector append(FieldElement in) {
      T[] out = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.data.length + 1));
      System.arraycopy(this.data, 0, out, 0, this.data.length);
      out[this.data.length] = in;
      return new ArrayFieldVector(this.field, out, false);
   }

   public FieldVector getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
      if (n < 0) {
         throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
      } else {
         ArrayFieldVector<T> out = new ArrayFieldVector(this.field, n);

         try {
            System.arraycopy(this.data, index, out.data, 0, n);
         } catch (IndexOutOfBoundsException var5) {
            this.checkIndex(index);
            this.checkIndex(index + n - 1);
         }

         return out;
      }
   }

   public void setEntry(int index, FieldElement value) {
      try {
         this.data[index] = value;
      } catch (IndexOutOfBoundsException var4) {
         this.checkIndex(index);
      }

   }

   public void setSubVector(int index, FieldVector v) throws OutOfRangeException {
      try {
         try {
            this.set(index, (ArrayFieldVector)v);
         } catch (ClassCastException var5) {
            for(int i = index; i < index + v.getDimension(); ++i) {
               this.data[i] = v.getEntry(i - index);
            }
         }
      } catch (IndexOutOfBoundsException var6) {
         this.checkIndex(index);
         this.checkIndex(index + v.getDimension() - 1);
      }

   }

   public void set(int index, ArrayFieldVector v) throws OutOfRangeException {
      try {
         System.arraycopy(v.data, 0, this.data, index, v.data.length);
      } catch (IndexOutOfBoundsException var4) {
         this.checkIndex(index);
         this.checkIndex(index + v.data.length - 1);
      }

   }

   public void set(FieldElement value) {
      Arrays.fill(this.data, value);
   }

   public FieldElement[] toArray() {
      return (FieldElement[])this.data.clone();
   }

   protected void checkVectorDimensions(FieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.getDimension());
   }

   protected void checkVectorDimensions(int n) throws DimensionMismatchException {
      if (this.data.length != n) {
         throw new DimensionMismatchException(this.data.length, n);
      }
   }

   public FieldElement walkInDefaultOrder(FieldVectorPreservingVisitor visitor) {
      int dim = this.getDimension();
      visitor.start(dim, 0, dim - 1);

      for(int i = 0; i < dim; ++i) {
         visitor.visit(i, this.getEntry(i));
      }

      return visitor.end();
   }

   public FieldElement walkInDefaultOrder(FieldVectorPreservingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
      this.checkIndices(start, end);
      visitor.start(this.getDimension(), start, end);

      for(int i = start; i <= end; ++i) {
         visitor.visit(i, this.getEntry(i));
      }

      return visitor.end();
   }

   public FieldElement walkInOptimizedOrder(FieldVectorPreservingVisitor visitor) {
      return this.walkInDefaultOrder(visitor);
   }

   public FieldElement walkInOptimizedOrder(FieldVectorPreservingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
      return this.walkInDefaultOrder(visitor, start, end);
   }

   public FieldElement walkInDefaultOrder(FieldVectorChangingVisitor visitor) {
      int dim = this.getDimension();
      visitor.start(dim, 0, dim - 1);

      for(int i = 0; i < dim; ++i) {
         this.setEntry(i, visitor.visit(i, this.getEntry(i)));
      }

      return visitor.end();
   }

   public FieldElement walkInDefaultOrder(FieldVectorChangingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
      this.checkIndices(start, end);
      visitor.start(this.getDimension(), start, end);

      for(int i = start; i <= end; ++i) {
         this.setEntry(i, visitor.visit(i, this.getEntry(i)));
      }

      return visitor.end();
   }

   public FieldElement walkInOptimizedOrder(FieldVectorChangingVisitor visitor) {
      return this.walkInDefaultOrder(visitor);
   }

   public FieldElement walkInOptimizedOrder(FieldVectorChangingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
      return this.walkInDefaultOrder(visitor, start, end);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other == null) {
         return false;
      } else {
         try {
            FieldVector<T> rhs = (FieldVector)other;
            if (this.data.length != rhs.getDimension()) {
               return false;
            } else {
               for(int i = 0; i < this.data.length; ++i) {
                  if (!this.data[i].equals(rhs.getEntry(i))) {
                     return false;
                  }
               }

               return true;
            }
         } catch (ClassCastException var4) {
            return false;
         }
      }
   }

   public int hashCode() {
      int h = 3542;

      for(FieldElement a : this.data) {
         h ^= a.hashCode();
      }

      return h;
   }

   private void checkIndex(int index) throws OutOfRangeException {
      if (index < 0 || index >= this.getDimension()) {
         throw new OutOfRangeException(LocalizedFormats.INDEX, index, 0, this.getDimension() - 1);
      }
   }

   private void checkIndices(int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
      int dim = this.getDimension();
      if (start >= 0 && start < dim) {
         if (end >= 0 && end < dim) {
            if (end < start) {
               throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, end, start, false);
            }
         } else {
            throw new OutOfRangeException(LocalizedFormats.INDEX, end, 0, dim - 1);
         }
      } else {
         throw new OutOfRangeException(LocalizedFormats.INDEX, start, 0, dim - 1);
      }
   }
}
