package jodd.typeconverter.impl;

import jodd.mutable.MutableFloat;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;

public class MutableFloatConverter implements TypeConverter {
   protected final TypeConverter typeConverter;

   public MutableFloatConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverter = typeConverterManagerBean.lookup(Float.class);
   }

   public MutableFloat convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return value.getClass() == MutableFloat.class ? (MutableFloat)value : new MutableFloat((Number)this.typeConverter.convert(value));
      }
   }
}
