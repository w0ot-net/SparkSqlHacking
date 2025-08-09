package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConverterManagerBean;

public class ClassArrayConverter extends ArrayConverter {
   public ClassArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      super(typeConverterManagerBean, Class.class);
   }

   protected Class[] createArray(int length) {
      return new Class[length];
   }
}
