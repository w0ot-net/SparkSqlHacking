package py4j.reflection;

public class TypeConverter {
   public static final int NO_CONVERSION = -1;
   public static final int DOUBLE_TO_FLOAT = 0;
   public static final int INT_TO_SHORT = 1;
   public static final int INT_TO_BYTE = 2;
   public static final int STRING_TO_CHAR = 3;
   public static final int NUM_TO_LONG = 4;
   private final int conversion;
   public static final TypeConverter NO_CONVERTER = new TypeConverter();
   public static final TypeConverter FLOAT_CONVERTER = new TypeConverter(0);
   public static final TypeConverter SHORT_CONVERTER = new TypeConverter(1);
   public static final TypeConverter BYTE_CONVERTER = new TypeConverter(2);
   public static final TypeConverter CHAR_CONVERTER = new TypeConverter(3);
   public static final TypeConverter LONG_CONVERTER = new TypeConverter(4);

   public TypeConverter() {
      this(-1);
   }

   public TypeConverter(int conversion) {
      this.conversion = conversion;
   }

   public Object convert(Object obj) {
      Object newObject = null;
      switch (this.conversion) {
         case -1:
            newObject = obj;
            break;
         case 0:
            newObject = ((Double)obj).floatValue();
            break;
         case 1:
            newObject = ((Integer)obj).shortValue();
            break;
         case 2:
            newObject = ((Integer)obj).byteValue();
            break;
         case 3:
            newObject = ((CharSequence)obj).charAt(0);
            break;
         case 4:
            newObject = Long.parseLong(obj.toString());
            break;
         default:
            newObject = null;
      }

      return newObject;
   }

   public int getConversion() {
      return this.conversion;
   }
}
