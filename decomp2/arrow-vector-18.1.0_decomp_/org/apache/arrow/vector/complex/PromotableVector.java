package org.apache.arrow.vector.complex;

import org.apache.arrow.vector.AddOrGetResult;
import org.apache.arrow.vector.types.pojo.FieldType;

public interface PromotableVector {
   AddOrGetResult addOrGetVector(FieldType var1);

   UnionVector promoteToUnion();
}
