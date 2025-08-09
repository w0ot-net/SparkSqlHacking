package com.univocity.parsers.conversions;

import com.univocity.parsers.common.DataProcessingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class EnumConversion extends ObjectConversion {
   private final Class enumType;
   private final Field customEnumField;
   private final Method customEnumMethod;
   private final EnumSelector[] selectors;
   private final Map[] conversions;

   public EnumConversion(Class enumType) {
      this(enumType, EnumSelector.NAME, EnumSelector.ORDINAL, EnumSelector.STRING);
   }

   public EnumConversion(Class enumType, EnumSelector... selectors) {
      this(enumType, (Enum)null, (String)null, (String)null, selectors);
   }

   public EnumConversion(Class enumType, String customEnumElement, EnumSelector... selectors) {
      this(enumType, (Enum)null, (String)null, customEnumElement);
   }

   public EnumConversion(Class enumType, Enum valueIfStringIsNull, String valueIfEnumIsNull, String customEnumElement, EnumSelector... selectors) {
      super(valueIfStringIsNull, valueIfEnumIsNull);
      this.enumType = enumType;
      if (customEnumElement != null) {
         customEnumElement = customEnumElement.trim();
         if (customEnumElement.isEmpty()) {
            customEnumElement = null;
         }
      }

      LinkedHashSet<EnumSelector> selectorSet = new LinkedHashSet();
      Collections.addAll(selectorSet, selectors);
      if ((selectorSet.contains(EnumSelector.CUSTOM_FIELD) || selectorSet.contains(EnumSelector.CUSTOM_METHOD)) && customEnumElement == null) {
         throw new IllegalArgumentException("Cannot create custom enum conversion without a field name to use");
      } else {
         Field field = null;
         Method method = null;
         if (customEnumElement != null) {
            IllegalStateException fieldError = null;
            IllegalStateException methodError = null;

            try {
               field = enumType.getDeclaredField(customEnumElement);
               if (!field.isAccessible()) {
                  field.setAccessible(true);
               }
            } catch (Throwable e) {
               fieldError = new IllegalStateException("Unable to access custom field '" + customEnumElement + "' in enumeration type " + enumType.getName(), e);
            }

            if (field == null) {
               try {
                  try {
                     method = enumType.getDeclaredMethod(customEnumElement);
                  } catch (NoSuchMethodException var13) {
                     method = enumType.getDeclaredMethod(customEnumElement, String.class);
                     if (!Modifier.isStatic(method.getModifiers())) {
                        throw new IllegalArgumentException("Custom method '" + customEnumElement + "' in enumeration type " + enumType.getName() + " must be static");
                     }

                     if (method.getReturnType() != enumType) {
                        throw new IllegalArgumentException("Custom method '" + customEnumElement + "' in enumeration type " + enumType.getName() + " must return " + enumType.getName());
                     }
                  }

                  if (!method.isAccessible()) {
                     method.setAccessible(true);
                  }
               } catch (Throwable e) {
                  methodError = new IllegalStateException("Unable to access custom method '" + customEnumElement + "' in enumeration type " + enumType.getName(), e);
               }

               if (method != null) {
                  if (method.getReturnType() == Void.TYPE) {
                     throw new IllegalArgumentException("Custom method '" + customEnumElement + "' in enumeration type " + enumType.getName() + " must return a value");
                  }

                  if (!selectorSet.contains(EnumSelector.CUSTOM_METHOD)) {
                     selectorSet.add(EnumSelector.CUSTOM_METHOD);
                  }
               }
            } else if (!selectorSet.contains(EnumSelector.CUSTOM_FIELD)) {
               selectorSet.add(EnumSelector.CUSTOM_FIELD);
            }

            if (selectorSet.contains(EnumSelector.CUSTOM_FIELD) && fieldError != null) {
               throw fieldError;
            }

            if (selectorSet.contains(EnumSelector.CUSTOM_METHOD) && methodError != null) {
               throw methodError;
            }

            if (field == null && method == null) {
               throw new IllegalStateException("No method/field named '" + customEnumElement + "' found in enumeration type " + enumType.getName());
            }
         }

         if (selectorSet.contains(EnumSelector.CUSTOM_FIELD) && selectorSet.contains(EnumSelector.CUSTOM_METHOD)) {
            throw new IllegalArgumentException("Cannot create custom enum conversion using both method and field values");
         } else if (selectorSet.isEmpty()) {
            throw new IllegalArgumentException("Selection of enum conversion types cannot be empty.");
         } else {
            this.customEnumField = field;
            this.customEnumMethod = method;
            this.selectors = (EnumSelector[])selectorSet.toArray(new EnumSelector[selectorSet.size()]);
            this.conversions = new Map[selectorSet.size()];
            this.initializeMappings(selectorSet);
         }
      }
   }

   private void initializeMappings(Set conversionTypes) {
      T[] constants = (T[])((Enum[])this.enumType.getEnumConstants());
      int i = 0;

      for(EnumSelector conversionType : conversionTypes) {
         Map<String, T> map = new HashMap(constants.length);
         this.conversions[i++] = map;

         for(Enum constant : constants) {
            String key = this.getKey(constant, conversionType);
            if (key != null) {
               if (map.containsKey(key)) {
                  throw new IllegalArgumentException("Enumeration element type " + conversionType + " does not uniquely identify elements of " + this.enumType.getName() + ". Got duplicate value '" + key + "' from constants '" + constant + "' and '" + map.get(key) + "'.");
               }

               map.put(key, constant);
            }
         }
      }

   }

   private String getKey(Enum constant, EnumSelector conversionType) {
      switch (conversionType) {
         case NAME:
            return constant.name();
         case ORDINAL:
            return String.valueOf(constant.ordinal());
         case STRING:
            return constant.toString();
         case CUSTOM_FIELD:
            try {
               return String.valueOf(this.customEnumField.get(constant));
            } catch (Throwable e) {
               throw new IllegalStateException("Error reading custom field '" + this.customEnumField.getName() + "' from enumeration constant '" + constant + "' of type " + this.enumType.getName(), e);
            }
         case CUSTOM_METHOD:
            try {
               if (this.customEnumMethod.getParameterTypes().length == 0) {
                  return String.valueOf(this.customEnumMethod.invoke(constant));
               }

               return null;
            } catch (Throwable e) {
               throw new IllegalStateException("Error reading custom method '" + this.customEnumMethod.getName() + "' from enumeration constant '" + constant + "' of type " + this.enumType.getName(), e);
            }
         default:
            throw new IllegalStateException("Unsupported enumeration selector type " + conversionType);
      }
   }

   public String revert(Enum input) {
      return input == null ? super.revert((Object)null) : this.getKey(input, this.selectors[0]);
   }

   protected Enum fromString(String input) {
      for(Map conversion : this.conversions) {
         T value = (T)((Enum)conversion.get(input));
         if (value != null) {
            return value;
         }
      }

      DataProcessingException exception = null;
      if (this.customEnumMethod != null && this.customEnumMethod.getParameterTypes().length == 1) {
         try {
            T out = (T)((Enum)this.customEnumMethod.invoke((Object)null, input));
            return out;
         } catch (Exception e) {
            exception = new DataProcessingException("Cannot convert '{value}' to enumeration of type " + this.enumType.getName() + " using method " + this.customEnumMethod.getName(), e);
         }
      }

      if (exception == null) {
         exception = new DataProcessingException("Cannot convert '{value}' to enumeration of type " + this.enumType.getName());
      }

      exception.setValue(input);
      exception.markAsNonFatal();
      throw exception;
   }
}
