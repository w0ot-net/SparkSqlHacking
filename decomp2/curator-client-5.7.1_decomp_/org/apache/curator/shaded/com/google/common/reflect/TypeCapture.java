package org.apache.curator.shaded.com.google.common.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
abstract class TypeCapture {
   final Type capture() {
      Type superclass = this.getClass().getGenericSuperclass();
      Preconditions.checkArgument(superclass instanceof ParameterizedType, "%s isn't parameterized", (Object)superclass);
      return ((ParameterizedType)superclass).getActualTypeArguments()[0];
   }
}
