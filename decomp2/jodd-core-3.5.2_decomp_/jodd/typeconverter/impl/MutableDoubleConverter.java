package jodd.typeconverter.impl;

import jodd.mutable.MutableDouble;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;

public class MutableDoubleConverter implements TypeConverter {
   protected final TypeConverter typeConverter;

   public MutableDoubleConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverter = typeConverterManagerBean.lookup(Double.class);
   }

   public MutableDouble convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return value.getClass() == MutableDouble.class ? (MutableDouble)value : new MutableDouble((Number)this.typeConverter.convert(value));
      }
   }
}
