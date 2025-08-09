package jodd.typeconverter.impl;

import jodd.mutable.MutableShort;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;

public class MutableShortConverter implements TypeConverter {
   protected final TypeConverter typeConverter;

   public MutableShortConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverter = typeConverterManagerBean.lookup(Short.class);
   }

   public MutableShort convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return value.getClass() == MutableShort.class ? (MutableShort)value : new MutableShort((Number)this.typeConverter.convert(value));
      }
   }
}
