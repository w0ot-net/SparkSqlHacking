package jodd.typeconverter.impl;

import jodd.mutable.MutableByte;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;

public class MutableByteConverter implements TypeConverter {
   protected final TypeConverter typeConverter;

   public MutableByteConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverter = typeConverterManagerBean.lookup(Byte.class);
   }

   public MutableByte convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return value.getClass() == MutableByte.class ? (MutableByte)value : new MutableByte((Number)this.typeConverter.convert(value));
      }
   }
}
