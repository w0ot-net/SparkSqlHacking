package jodd.typeconverter.impl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManager;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class CollectionConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;
   protected final Class collectionType;
   protected final Class targetComponentType;

   public CollectionConverter(Class collectionType, Class targetComponentType) {
      this(TypeConverterManager.getDefaultTypeConverterManager(), collectionType, targetComponentType);
   }

   public CollectionConverter(TypeConverterManagerBean typeConverterManagerBean, Class collectionType, Class targetComponentType) {
      this.typeConverterManagerBean = typeConverterManagerBean;
      this.collectionType = collectionType;
      this.targetComponentType = targetComponentType;
   }

   public Collection convert(Object value) {
      if (value == null) {
         return null;
      } else {
         return !(value instanceof Collection) ? this.convertValueToCollection(value) : this.convertCollectionToCollection((Collection)value);
      }
   }

   protected Object convertType(Object value) {
      return this.typeConverterManagerBean.convertType(value, this.targetComponentType);
   }

   protected Collection createCollection(int length) {
      if (this.collectionType.isInterface()) {
         if (this.collectionType == List.class) {
            return length > 0 ? new ArrayList(length) : new ArrayList();
         } else if (this.collectionType == Set.class) {
            return length > 0 ? new HashSet(length) : new HashSet();
         } else {
            throw new TypeConversionException("Unknown collection: " + this.collectionType.getName());
         }
      } else {
         if (length > 0) {
            try {
               Constructor<Collection<T>> ctor = this.collectionType.getConstructor(Integer.TYPE);
               return (Collection)ctor.newInstance(length);
            } catch (Exception var4) {
            }
         }

         try {
            return (Collection)this.collectionType.newInstance();
         } catch (Exception ex) {
            throw new TypeConversionException(ex);
         }
      }
   }

   protected Collection convertToSingleElementCollection(Object value) {
      Collection<T> collection = this.createCollection(0);
      collection.add(value);
      return collection;
   }

   protected Collection convertValueToCollection(Object value) {
      if (value instanceof Iterable) {
         Iterable iterable = (Iterable)value;
         Collection<T> collection = this.createCollection(0);

         for(Object element : iterable) {
            collection.add(this.convertType(element));
         }

         return collection;
      } else {
         if (value instanceof CharSequence) {
            value = CsvUtil.toStringArray(value.toString());
         }

         Class type = value.getClass();
         if (!type.isArray()) {
            return this.convertToSingleElementCollection(value);
         } else {
            Class componentType = type.getComponentType();
            if (componentType.isPrimitive()) {
               return this.convertPrimitiveArrayToCollection(value, componentType);
            } else {
               Object[] array = value;
               Collection<T> result = this.createCollection(array.length);

               for(Object a : array) {
                  result.add(this.convertType(a));
               }

               return result;
            }
         }
      }
   }

   protected Collection convertCollectionToCollection(Collection value) {
      Collection<T> collection = this.createCollection(value.size());

      for(Object v : value) {
         collection.add(this.convertType(v));
      }

      return collection;
   }

   protected Collection convertPrimitiveArrayToCollection(Object value, Class primitiveComponentType) {
      Collection<T> result = null;
      if (primitiveComponentType == Integer.TYPE) {
         int[] array = (int[])value;
         result = this.createCollection(array.length);

         for(int a : array) {
            result.add(this.convertType(a));
         }
      } else if (primitiveComponentType == Long.TYPE) {
         long[] array = (long[])value;
         result = this.createCollection(array.length);

         for(long a : array) {
            result.add(this.convertType(a));
         }
      } else if (primitiveComponentType == Float.TYPE) {
         float[] array = (float[])value;
         result = this.createCollection(array.length);

         for(float a : array) {
            result.add(this.convertType(a));
         }
      } else if (primitiveComponentType == Double.TYPE) {
         double[] array = (double[])value;
         result = this.createCollection(array.length);

         for(double a : array) {
            result.add(this.convertType(a));
         }
      } else if (primitiveComponentType == Short.TYPE) {
         short[] array = (short[])value;
         result = this.createCollection(array.length);

         for(short a : array) {
            result.add(this.convertType(a));
         }
      } else if (primitiveComponentType == Byte.TYPE) {
         byte[] array = (byte[])value;
         result = this.createCollection(array.length);

         for(byte a : array) {
            result.add(this.convertType(a));
         }
      } else if (primitiveComponentType == Character.TYPE) {
         char[] array = (char[])value;
         result = this.createCollection(array.length);

         for(char a : array) {
            result.add(this.convertType(a));
         }
      } else if (primitiveComponentType == Boolean.TYPE) {
         boolean[] array = (boolean[])value;
         result = this.createCollection(array.length);

         for(boolean a : array) {
            result.add(this.convertType(a));
         }
      }

      return result;
   }
}
