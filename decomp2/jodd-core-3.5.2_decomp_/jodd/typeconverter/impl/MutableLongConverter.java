package jodd.typeconverter.impl;

import jodd.mutable.MutableLong;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;

public class MutableLongConverter implements TypeConverter {
   protected final TypeConverter typeConverter;

   public MutableLongConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverter = typeConverterManagerBean.lookup(Long.class);
   }

   public MutableLong convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return value.getClass() == MutableLong.class ? (MutableLong)value : new MutableLong((Number)this.typeConverter.convert(value));
      }
   }
}
