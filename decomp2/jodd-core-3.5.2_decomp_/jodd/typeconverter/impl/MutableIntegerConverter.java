package jodd.typeconverter.impl;

import jodd.mutable.MutableInteger;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;

public class MutableIntegerConverter implements TypeConverter {
   protected final TypeConverter typeConverter;

   public MutableIntegerConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverter = typeConverterManagerBean.lookup(Integer.class);
   }

   public MutableInteger convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return value.getClass() == MutableInteger.class ? (MutableInteger)value : new MutableInteger((Number)this.typeConverter.convert(value));
      }
   }
}
