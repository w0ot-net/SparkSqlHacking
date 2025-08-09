package org.apache.arrow.vector.compare;

import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseLargeVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthViewVector;
import org.apache.arrow.vector.ExtensionTypeVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.LargeListViewVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.RunEndEncodedVector;
import org.apache.arrow.vector.complex.UnionVector;

public interface VectorVisitor {
   Object visit(BaseFixedWidthVector var1, Object var2);

   Object visit(BaseVariableWidthVector var1, Object var2);

   Object visit(BaseLargeVariableWidthVector var1, Object var2);

   Object visit(BaseVariableWidthViewVector var1, Object var2);

   Object visit(ListVector var1, Object var2);

   Object visit(FixedSizeListVector var1, Object var2);

   Object visit(LargeListVector var1, Object var2);

   Object visit(NonNullableStructVector var1, Object var2);

   Object visit(UnionVector var1, Object var2);

   Object visit(DenseUnionVector var1, Object var2);

   Object visit(NullVector var1, Object var2);

   Object visit(ExtensionTypeVector var1, Object var2);

   default Object visit(ListViewVector left, Object value) {
      throw new UnsupportedOperationException("VectorVisitor for ListViewVector is not supported.");
   }

   default Object visit(LargeListViewVector left, Object value) {
      throw new UnsupportedOperationException("VectorVisitor for LargeListViewVector is not supported.");
   }

   default Object visit(RunEndEncodedVector left, Object value) {
      throw new UnsupportedOperationException("VectorVisitor for LargeListViewVector is not supported.");
   }
}
