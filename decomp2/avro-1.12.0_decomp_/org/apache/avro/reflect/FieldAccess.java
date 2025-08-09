package org.apache.avro.reflect;

import java.lang.reflect.Field;

abstract class FieldAccess {
   protected static final int INT_DEFAULT_VALUE = 0;
   protected static final float FLOAT_DEFAULT_VALUE = 0.0F;
   protected static final short SHORT_DEFAULT_VALUE = 0;
   protected static final byte BYTE_DEFAULT_VALUE = 0;
   protected static final boolean BOOLEAN_DEFAULT_VALUE = false;
   protected static final char CHAR_DEFAULT_VALUE = '\u0000';
   protected static final long LONG_DEFAULT_VALUE = 0L;
   protected static final double DOUBLE_DEFAULT_VALUE = (double)0.0F;

   protected abstract FieldAccessor getAccessor(Field field);
}
