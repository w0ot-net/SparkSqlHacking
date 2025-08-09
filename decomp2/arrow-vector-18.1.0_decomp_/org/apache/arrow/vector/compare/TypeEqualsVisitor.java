package org.apache.arrow.vector.compare;

import java.util.List;
import java.util.Objects;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseLargeVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthViewVector;
import org.apache.arrow.vector.ExtensionTypeVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.LargeListViewVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.RunEndEncodedVector;
import org.apache.arrow.vector.complex.UnionVector;
import org.apache.arrow.vector.types.pojo.Field;

public class TypeEqualsVisitor implements VectorVisitor {
   private final ValueVector right;
   private final boolean checkName;
   private final boolean checkMetadata;

   public TypeEqualsVisitor(ValueVector right) {
      this(right, true, true);
   }

   public TypeEqualsVisitor(ValueVector right, boolean checkName, boolean checkMetadata) {
      this.right = right;
      this.checkName = checkName;
      this.checkMetadata = checkMetadata;
   }

   public boolean equals(ValueVector left) {
      return (Boolean)left.accept(this, (Object)null);
   }

   public Boolean visit(BaseFixedWidthVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(BaseVariableWidthVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(BaseLargeVariableWidthVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(BaseVariableWidthViewVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(ListVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(FixedSizeListVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(LargeListVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(NonNullableStructVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(UnionVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(DenseUnionVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(NullVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(ExtensionTypeVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(ListViewVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(LargeListViewVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   public Boolean visit(RunEndEncodedVector left, Void value) {
      return this.compareField(left.getField(), this.right.getField());
   }

   private boolean compareField(Field leftField, Field rightField) {
      if (leftField == rightField) {
         return true;
      } else {
         return (!this.checkName || Objects.equals(leftField.getName(), rightField.getName())) && Objects.equals(leftField.isNullable(), rightField.isNullable()) && Objects.equals(leftField.getType(), rightField.getType()) && Objects.equals(leftField.getDictionary(), rightField.getDictionary()) && (!this.checkMetadata || Objects.equals(leftField.getMetadata(), rightField.getMetadata())) && this.compareChildren(leftField.getChildren(), rightField.getChildren());
      }
   }

   private boolean compareChildren(List leftChildren, List rightChildren) {
      if (leftChildren.size() != rightChildren.size()) {
         return false;
      } else {
         for(int i = 0; i < leftChildren.size(); ++i) {
            if (!this.compareField((Field)leftChildren.get(i), (Field)rightChildren.get(i))) {
               return false;
            }
         }

         return true;
      }
   }
}
