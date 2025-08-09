package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;

public class SparseFieldVector implements FieldVector, Serializable {
   private static final long serialVersionUID = 7841233292190413362L;
   private final Field field;
   private final OpenIntToFieldHashMap entries;
   private final int virtualSize;

   public SparseFieldVector(Field field) {
      this((Field)field, 0);
   }

   public SparseFieldVector(Field field, int dimension) {
      this.field = field;
      this.virtualSize = dimension;
      this.entries = new OpenIntToFieldHashMap(field);
   }

   protected SparseFieldVector(SparseFieldVector v, int resize) {
      this.field = v.field;
      this.virtualSize = v.getDimension() + resize;
      this.entries = new OpenIntToFieldHashMap(v.entries);
   }

   public SparseFieldVector(Field field, int dimension, int expectedSize) {
      this.field = field;
      this.virtualSize = dimension;
      this.entries = new OpenIntToFieldHashMap(field, expectedSize);
   }

   public SparseFieldVector(Field field, FieldElement[] values) throws NullArgumentException {
      MathUtils.checkNotNull(values);
      this.field = field;
      this.virtualSize = values.length;
      this.entries = new OpenIntToFieldHashMap(field);

      for(int key = 0; key < values.length; ++key) {
         T value = (T)values[key];
         this.entries.put(key, value);
      }

   }

   public SparseFieldVector(SparseFieldVector v) {
      this.field = v.field;
      this.virtualSize = v.getDimension();
      this.entries = new OpenIntToFieldHashMap(v.getEntries());
   }

   private OpenIntToFieldHashMap getEntries() {
      return this.entries;
   }

   public FieldVector add(SparseFieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.getDimension());
      SparseFieldVector<T> res = (SparseFieldVector)this.copy();
      OpenIntToFieldHashMap<T>.Iterator iter = v.getEntries().iterator();

      while(iter.hasNext()) {
         iter.advance();
         int key = iter.key();
         T value = (T)iter.value();
         if (this.entries.containsKey(key)) {
            res.setEntry(key, (FieldElement)this.entries.get(key).add(value));
         } else {
            res.setEntry(key, value);
         }
      }

      return res;
   }

   public FieldVector append(SparseFieldVector v) {
      SparseFieldVector<T> res = new SparseFieldVector(this, v.getDimension());
      OpenIntToFieldHashMap<T>.Iterator iter = v.entries.iterator();

      while(iter.hasNext()) {
         iter.advance();
         res.setEntry(iter.key() + this.virtualSize, iter.value());
      }

      return res;
   }

   public FieldVector append(FieldVector v) {
      if (v instanceof SparseFieldVector) {
         return this.append((SparseFieldVector)v);
      } else {
         int n = v.getDimension();
         FieldVector<T> res = new SparseFieldVector(this, n);

         for(int i = 0; i < n; ++i) {
            res.setEntry(i + this.virtualSize, v.getEntry(i));
         }

         return res;
      }
   }

   public FieldVector append(FieldElement d) throws NullArgumentException {
      MathUtils.checkNotNull(d);
      FieldVector<T> res = new SparseFieldVector(this, 1);
      res.setEntry(this.virtualSize, d);
      return res;
   }

   public FieldVector copy() {
      return new SparseFieldVector(this);
   }

   public FieldElement dotProduct(FieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.getDimension());
      T res = (T)((FieldElement)this.field.getZero());

      for(OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator(); iter.hasNext(); res = (T)((FieldElement)res.add(v.getEntry(iter.key()).multiply(iter.value())))) {
         iter.advance();
      }

      return res;
   }

   public FieldVector ebeDivide(FieldVector v) throws DimensionMismatchException, MathArithmeticException {
      this.checkVectorDimensions(v.getDimension());
      SparseFieldVector<T> res = new SparseFieldVector(this);
      OpenIntToFieldHashMap<T>.Iterator iter = res.entries.iterator();

      while(iter.hasNext()) {
         iter.advance();
         res.setEntry(iter.key(), (FieldElement)iter.value().divide(v.getEntry(iter.key())));
      }

      return res;
   }

   public FieldVector ebeMultiply(FieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.getDimension());
      SparseFieldVector<T> res = new SparseFieldVector(this);
      OpenIntToFieldHashMap<T>.Iterator iter = res.entries.iterator();

      while(iter.hasNext()) {
         iter.advance();
         res.setEntry(iter.key(), (FieldElement)iter.value().multiply(v.getEntry(iter.key())));
      }

      return res;
   }

   /** @deprecated */
   @Deprecated
   public FieldElement[] getData() {
      return this.toArray();
   }

   public int getDimension() {
      return this.virtualSize;
   }

   public FieldElement getEntry(int index) throws OutOfRangeException {
      this.checkIndex(index);
      return this.entries.get(index);
   }

   public Field getField() {
      return this.field;
   }

   public FieldVector getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
      if (n < 0) {
         throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
      } else {
         this.checkIndex(index);
         this.checkIndex(index + n - 1);
         SparseFieldVector<T> res = new SparseFieldVector(this.field, n);
         int end = index + n;
         OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();

         while(iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (key >= index && key < end) {
               res.setEntry(key - index, iter.value());
            }
         }

         return res;
      }
   }

   public FieldVector mapAdd(FieldElement d) throws NullArgumentException {
      return this.copy().mapAddToSelf(d);
   }

   public FieldVector mapAddToSelf(FieldElement d) throws NullArgumentException {
      for(int i = 0; i < this.virtualSize; ++i) {
         this.setEntry(i, (FieldElement)this.getEntry(i).add(d));
      }

      return this;
   }

   public FieldVector mapDivide(FieldElement d) throws NullArgumentException, MathArithmeticException {
      return this.copy().mapDivideToSelf(d);
   }

   public FieldVector mapDivideToSelf(FieldElement d) throws NullArgumentException, MathArithmeticException {
      OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();

      while(iter.hasNext()) {
         iter.advance();
         this.entries.put(iter.key(), (FieldElement)iter.value().divide(d));
      }

      return this;
   }

   public FieldVector mapInv() throws MathArithmeticException {
      return this.copy().mapInvToSelf();
   }

   public FieldVector mapInvToSelf() throws MathArithmeticException {
      for(int i = 0; i < this.virtualSize; ++i) {
         this.setEntry(i, (FieldElement)((FieldElement)this.field.getOne()).divide(this.getEntry(i)));
      }

      return this;
   }

   public FieldVector mapMultiply(FieldElement d) throws NullArgumentException {
      return this.copy().mapMultiplyToSelf(d);
   }

   public FieldVector mapMultiplyToSelf(FieldElement d) throws NullArgumentException {
      OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();

      while(iter.hasNext()) {
         iter.advance();
         this.entries.put(iter.key(), (FieldElement)iter.value().multiply(d));
      }

      return this;
   }

   public FieldVector mapSubtract(FieldElement d) throws NullArgumentException {
      return this.copy().mapSubtractToSelf(d);
   }

   public FieldVector mapSubtractToSelf(FieldElement d) throws NullArgumentException {
      return this.mapAddToSelf((FieldElement)((FieldElement)this.field.getZero()).subtract(d));
   }

   public FieldMatrix outerProduct(SparseFieldVector v) {
      int n = v.getDimension();
      SparseFieldMatrix<T> res = new SparseFieldMatrix(this.field, this.virtualSize, n);
      OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();

      while(iter.hasNext()) {
         iter.advance();
         OpenIntToFieldHashMap<T>.Iterator iter2 = v.entries.iterator();

         while(iter2.hasNext()) {
            iter2.advance();
            res.setEntry(iter.key(), iter2.key(), (FieldElement)iter.value().multiply(iter2.value()));
         }
      }

      return res;
   }

   public FieldMatrix outerProduct(FieldVector v) {
      if (v instanceof SparseFieldVector) {
         return this.outerProduct((SparseFieldVector)v);
      } else {
         int n = v.getDimension();
         FieldMatrix<T> res = new SparseFieldMatrix(this.field, this.virtualSize, n);
         OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();

         while(iter.hasNext()) {
            iter.advance();
            int row = iter.key();
            FieldElement<T> value = iter.value();

            for(int col = 0; col < n; ++col) {
               res.setEntry(row, col, (FieldElement)value.multiply(v.getEntry(col)));
            }
         }

         return res;
      }
   }

   public FieldVector projection(FieldVector v) throws DimensionMismatchException, MathArithmeticException {
      this.checkVectorDimensions(v.getDimension());
      return v.mapMultiply((FieldElement)this.dotProduct(v).divide(v.dotProduct(v)));
   }

   public void set(FieldElement value) {
      MathUtils.checkNotNull(value);

      for(int i = 0; i < this.virtualSize; ++i) {
         this.setEntry(i, value);
      }

   }

   public void setEntry(int index, FieldElement value) throws NullArgumentException, OutOfRangeException {
      MathUtils.checkNotNull(value);
      this.checkIndex(index);
      this.entries.put(index, value);
   }

   public void setSubVector(int index, FieldVector v) throws OutOfRangeException {
      this.checkIndex(index);
      this.checkIndex(index + v.getDimension() - 1);
      int n = v.getDimension();

      for(int i = 0; i < n; ++i) {
         this.setEntry(i + index, v.getEntry(i));
      }

   }

   public SparseFieldVector subtract(SparseFieldVector v) throws DimensionMismatchException {
      this.checkVectorDimensions(v.getDimension());
      SparseFieldVector<T> res = (SparseFieldVector)this.copy();
      OpenIntToFieldHashMap<T>.Iterator iter = v.getEntries().iterator();

      while(iter.hasNext()) {
         iter.advance();
         int key = iter.key();
         if (this.entries.containsKey(key)) {
            res.setEntry(key, (FieldElement)this.entries.get(key).subtract(iter.value()));
         } else {
            res.setEntry(key, (FieldElement)((FieldElement)this.field.getZero()).subtract(iter.value()));
         }
      }

      return res;
   }

   public FieldVector subtract(FieldVector v) throws DimensionMismatchException {
      if (v instanceof SparseFieldVector) {
         return this.subtract((SparseFieldVector)v);
      } else {
         int n = v.getDimension();
         this.checkVectorDimensions(n);
         SparseFieldVector<T> res = new SparseFieldVector(this);

         for(int i = 0; i < n; ++i) {
            if (this.entries.containsKey(i)) {
               res.setEntry(i, (FieldElement)this.entries.get(i).subtract(v.getEntry(i)));
            } else {
               res.setEntry(i, (FieldElement)((FieldElement)this.field.getZero()).subtract(v.getEntry(i)));
            }
         }

         return res;
      }
   }

   public FieldElement[] toArray() {
      T[] res = (T[])((FieldElement[])MathArrays.buildArray(this.field, this.virtualSize));

      for(OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator(); iter.hasNext(); res[iter.key()] = iter.value()) {
         iter.advance();
      }

      return res;
   }

   private void checkIndex(int index) throws OutOfRangeException {
      if (index < 0 || index >= this.getDimension()) {
         throw new OutOfRangeException(index, 0, this.getDimension() - 1);
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

   protected void checkVectorDimensions(int n) throws DimensionMismatchException {
      if (this.getDimension() != n) {
         throw new DimensionMismatchException(this.getDimension(), n);
      }
   }

   public FieldVector add(FieldVector v) throws DimensionMismatchException {
      if (v instanceof SparseFieldVector) {
         return this.add((SparseFieldVector)v);
      } else {
         int n = v.getDimension();
         this.checkVectorDimensions(n);
         SparseFieldVector<T> res = new SparseFieldVector(this.field, this.getDimension());

         for(int i = 0; i < n; ++i) {
            res.setEntry(i, (FieldElement)v.getEntry(i).add(this.getEntry(i)));
         }

         return res;
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

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.field == null ? 0 : this.field.hashCode());
      result = 31 * result + this.virtualSize;

      int temp;
      for(OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator(); iter.hasNext(); result = 31 * result + temp) {
         iter.advance();
         temp = iter.value().hashCode();
      }

      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof SparseFieldVector)) {
         return false;
      } else {
         SparseFieldVector<T> other = (SparseFieldVector)obj;
         if (this.field == null) {
            if (other.field != null) {
               return false;
            }
         } else if (!this.field.equals(other.field)) {
            return false;
         }

         if (this.virtualSize != other.virtualSize) {
            return false;
         } else {
            OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();

            while(iter.hasNext()) {
               iter.advance();
               T test = (T)other.getEntry(iter.key());
               if (!test.equals(iter.value())) {
                  return false;
               }
            }

            iter = other.getEntries().iterator();

            while(iter.hasNext()) {
               iter.advance();
               T test = (T)iter.value();
               if (!test.equals(this.getEntry(iter.key()))) {
                  return false;
               }
            }

            return true;
         }
      }
   }
}
