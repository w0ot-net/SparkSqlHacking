package com.univocity.parsers.annotations.helpers;

import com.univocity.parsers.annotations.BooleanString;
import com.univocity.parsers.annotations.Convert;
import com.univocity.parsers.annotations.Copy;
import com.univocity.parsers.annotations.EnumOptions;
import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.HeaderTransformer;
import com.univocity.parsers.annotations.Headers;
import com.univocity.parsers.annotations.LowerCase;
import com.univocity.parsers.annotations.Nested;
import com.univocity.parsers.annotations.NullString;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Replace;
import com.univocity.parsers.annotations.Trim;
import com.univocity.parsers.annotations.UpperCase;
import com.univocity.parsers.annotations.Validate;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.beans.BeanHelper;
import com.univocity.parsers.common.beans.PropertyWrapper;
import com.univocity.parsers.common.input.CharAppender;
import com.univocity.parsers.common.input.DefaultCharAppender;
import com.univocity.parsers.conversions.BooleanConversion;
import com.univocity.parsers.conversions.Conversion;
import com.univocity.parsers.conversions.Conversions;
import com.univocity.parsers.conversions.EnumConversion;
import com.univocity.parsers.conversions.EnumSelector;
import com.univocity.parsers.conversions.FormattedConversion;
import com.univocity.parsers.conversions.NumericConversion;
import com.univocity.parsers.conversions.ObjectConversion;
import com.univocity.parsers.conversions.ToStringConversion;
import com.univocity.parsers.conversions.ValidatedConversion;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TimeZone;

public class AnnotationHelper {
   private static AnnotatedElement lastProcessedElement;
   private static Class lastProcessedAnnotationType;
   private static Annotation lastAnnotationFound;
   private static final Set javaLangAnnotationTypes = new HashSet();
   private static final Set customAnnotationTypes = new HashSet();

   private AnnotationHelper() {
   }

   private static String getNullValue(String defaultValue) {
      if ("null".equals(defaultValue)) {
         return null;
      } else {
         return "'null'".equals(defaultValue) ? "null" : defaultValue;
      }
   }

   public static String getNullWriteValue(AnnotatedElement target, Parsed parsed) {
      return parsed == null ? null : getNullValue((String)AnnotationRegistry.getValue(target, parsed, "defaultNullWrite", parsed.defaultNullWrite()));
   }

   public static String getNullReadValue(AnnotatedElement target, Parsed parsed) {
      return parsed == null ? null : getNullValue((String)AnnotationRegistry.getValue(target, parsed, "defaultNullRead", parsed.defaultNullRead()));
   }

   public static Conversion getConversion(Class classType, Annotation annotation) {
      return getConversion(classType, (AnnotatedElement)null, annotation, (String)null, (String)null);
   }

   public static EnumConversion createDefaultEnumConversion(Class fieldType, String nullRead, String nullWrite) {
      Enum nullReadValue = nullRead == null ? null : Enum.valueOf(fieldType, nullRead);
      return new EnumConversion(fieldType, nullReadValue, nullWrite, (String)null, new EnumSelector[]{EnumSelector.NAME, EnumSelector.ORDINAL, EnumSelector.STRING});
   }

   public static Conversion getConversion(Class fieldType, AnnotatedElement target, Annotation annotation, String nullRead, String nullWrite) {
      try {
         Class annType = annotation.annotationType();
         if (annType == NullString.class) {
            NullString nullString = (NullString)annotation;
            String[] nulls = (String[])AnnotationRegistry.getValue(target, nullString, "nulls", nullString.nulls());
            return Conversions.toNull(nulls);
         } else if (annType == Validate.class) {
            Validate validate = (Validate)annotation;
            boolean nullable = (Boolean)AnnotationRegistry.getValue(target, validate, "nullable", validate.nullable());
            boolean allowBlanks = (Boolean)AnnotationRegistry.getValue(target, validate, "allowBlanks", validate.allowBlanks());
            String[] oneOf = (String[])AnnotationRegistry.getValue(target, validate, "oneOf", validate.oneOf());
            String[] noneOf = (String[])AnnotationRegistry.getValue(target, validate, "noneOf", validate.noneOf());
            String matches = (String)AnnotationRegistry.getValue(target, validate, "matches", validate.matches());
            Class[] validators = (Class[])AnnotationRegistry.getValue(target, validate, "validators", validate.validators());
            return new ValidatedConversion(nullable, allowBlanks, oneOf, noneOf, matches, validators);
         } else if (annType == EnumOptions.class) {
            if (!fieldType.isEnum()) {
               if (target == null) {
                  throw new IllegalStateException("Invalid " + EnumOptions.class.getName() + " instance for converting class " + fieldType.getName() + ". Not an enum type.");
               } else {
                  throw new IllegalStateException("Invalid " + EnumOptions.class.getName() + " annotation on " + describeElement(target) + ". Attribute must be an enum type.");
               }
            } else {
               EnumOptions enumOptions = (EnumOptions)annotation;
               String customElement = (String)AnnotationRegistry.getValue(target, enumOptions, "customElement", enumOptions.customElement());
               String element = customElement.trim();
               if (element.isEmpty()) {
                  element = null;
               }

               Enum nullReadValue = nullRead == null ? null : Enum.valueOf(fieldType, nullRead);
               EnumSelector[] selectors = (EnumSelector[])AnnotationRegistry.getValue(target, enumOptions, "selectors", enumOptions.selectors());
               return new EnumConversion(fieldType, nullReadValue, nullWrite, element, selectors);
            }
         } else if (annType == Trim.class) {
            Trim trim = (Trim)annotation;
            int length = (Integer)AnnotationRegistry.getValue(target, trim, "length", trim.length());
            return length == -1 ? Conversions.trim() : Conversions.trim(length);
         } else if (annType == LowerCase.class) {
            return Conversions.toLowerCase();
         } else if (annType == UpperCase.class) {
            return Conversions.toUpperCase();
         } else if (annType == Replace.class) {
            Replace replace = (Replace)annotation;
            String expression = (String)AnnotationRegistry.getValue(target, replace, "expression", replace.expression());
            String replacement = (String)AnnotationRegistry.getValue(target, replace, "replacement", replace.replacement());
            return Conversions.replace(expression, replacement);
         } else if (annType == BooleanString.class) {
            if (fieldType != Boolean.TYPE && fieldType != Boolean.class) {
               if (target == null) {
                  throw new DataProcessingException("Invalid  usage of " + BooleanString.class.getName() + ". Got type " + fieldType.getName() + " instead of boolean.");
               } else {
                  throw new DataProcessingException("Invalid annotation: " + describeElement(target) + " has type " + fieldType.getName() + " instead of boolean.");
               }
            } else {
               BooleanString boolString = (BooleanString)annotation;
               String[] falseStrings = (String[])AnnotationRegistry.getValue(target, boolString, "falseStrings", boolString.falseStrings());
               String[] trueStrings = (String[])AnnotationRegistry.getValue(target, boolString, "trueStrings", boolString.trueStrings());
               Boolean valueForNull = nullRead == null ? null : BooleanConversion.getBoolean(nullRead, trueStrings, falseStrings);
               if (valueForNull == null && fieldType == Boolean.TYPE) {
                  valueForNull = Boolean.FALSE;
               }

               return Conversions.toBoolean(valueForNull, nullWrite, trueStrings, falseStrings);
            }
         } else {
            if (annType == Format.class) {
               Format format = (Format)annotation;
               String[] formats = (String[])AnnotationRegistry.getValue(target, format, "formats", format.formats());
               String[] options = (String[])AnnotationRegistry.getValue(target, format, "options", format.options());
               Locale locale = extractLocale(options);
               TimeZone timezone = extractTimeZone(options);
               Conversion conversion = null;
               if (fieldType == BigDecimal.class) {
                  BigDecimal defaultForNull = nullRead == null ? null : new BigDecimal(nullRead);
                  conversion = Conversions.formatToBigDecimal(defaultForNull, nullWrite, formats);
               } else if (!Number.class.isAssignableFrom(fieldType) && (!fieldType.isPrimitive() || fieldType == Boolean.TYPE || fieldType == Character.TYPE)) {
                  Date dateIfNull = null;
                  if (nullRead != null) {
                     if ("now".equalsIgnoreCase(nullRead)) {
                        dateIfNull = new Date();
                     } else {
                        if (formats.length == 0) {
                           throw new DataProcessingException("No format defined");
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat(formats[0], locale);
                        sdf.setTimeZone(timezone);
                        dateIfNull = sdf.parse(nullRead);
                     }
                  }

                  if (Date.class == fieldType) {
                     conversion = Conversions.toDate(timezone, locale, dateIfNull, nullWrite, formats);
                  } else if (Calendar.class == fieldType) {
                     Calendar calendarIfNull = null;
                     if (dateIfNull != null) {
                        calendarIfNull = Calendar.getInstance();
                        calendarIfNull.setTime(dateIfNull);
                        calendarIfNull.setTimeZone(timezone);
                     }

                     conversion = Conversions.toCalendar(timezone, locale, calendarIfNull, nullWrite, formats);
                  }
               } else {
                  conversion = Conversions.formatToNumber(formats);
                  ((NumericConversion)conversion).setNumberType(fieldType);
               }

               if (conversion != null) {
                  if (options.length > 0) {
                     if (!(conversion instanceof FormattedConversion)) {
                        throw new DataProcessingException("Options '" + Arrays.toString(options) + "' not supported by conversion of type '" + conversion.getClass() + "'. It must implement " + FormattedConversion.class);
                     }

                     Object[] formatters = ((FormattedConversion)conversion).getFormatterObjects();

                     for(Object formatter : formatters) {
                        applyFormatSettings(formatter, options);
                     }
                  }

                  return conversion;
               }
            } else if (annType == Convert.class) {
               Convert convert = (Convert)annotation;
               String[] args = (String[])AnnotationRegistry.getValue(target, convert, "args", convert.args());
               Class conversionClass = (Class)AnnotationRegistry.getValue(target, convert, "conversionClass", convert.conversionClass());
               return (Conversion)newInstance(Conversion.class, conversionClass, args);
            }

            return fieldType != String.class || nullRead == null && nullWrite == null ? null : new ToStringConversion(nullRead, nullWrite);
         }
      } catch (DataProcessingException ex) {
         throw ex;
      } catch (Throwable ex) {
         if (target == null) {
            throw new DataProcessingException("Unexpected error identifying conversions to apply over type " + fieldType, ex);
         } else {
            throw new DataProcessingException("Unexpected error identifying conversions to apply over " + describeElement(target), ex);
         }
      }
   }

   private static String extractOption(String[] options, String key) {
      for(int i = 0; i < options.length; ++i) {
         if (options[i] != null && options[i].trim().toLowerCase().startsWith(key)) {
            String out = options[i].split("=")[1].trim();
            options[i] = null;
            return out;
         }
      }

      return null;
   }

   private static TimeZone extractTimeZone(String[] options) {
      String code = extractOption(options, "timezone=");
      return code != null ? TimeZone.getTimeZone(code) : TimeZone.getDefault();
   }

   private static Locale extractLocale(String[] options) {
      String locale = extractOption(options, "locale=");
      if (locale == null) {
         return Locale.getDefault();
      } else {
         CharAppender appender = new DefaultCharAppender(100, "", 0);
         int j = 0;

         char ch;
         while(j < locale.length() && Character.isLetterOrDigit(ch = locale.charAt(j))) {
            ++j;
            appender.append(ch);
         }

         String languageCode = appender.getAndReset();
         ++j;

         while(j < locale.length() && Character.isLetterOrDigit(ch = locale.charAt(j))) {
            ++j;
            appender.append(ch);
         }

         String countryCode = appender.getAndReset();
         ++j;

         while(j < locale.length() && Character.isLetterOrDigit(ch = locale.charAt(j))) {
            ++j;
            appender.append(ch);
         }

         String variant = appender.getAndReset();
         return new Locale(languageCode, countryCode, variant);
      }
   }

   public static Object newInstance(Class parent, Class type, String[] args) {
      if (!parent.isAssignableFrom(type)) {
         throw new DataProcessingException("Not a valid " + parent.getSimpleName() + " class: '" + type.getSimpleName() + "' (" + type.getName() + ')');
      } else {
         try {
            Constructor constructor = type.getConstructor(String[].class);
            return constructor.newInstance(args);
         } catch (NoSuchMethodException e) {
            if (args.length == 0) {
               try {
                  return type.newInstance();
               } catch (Exception var5) {
                  throw new DataProcessingException("Unexpected error instantiating custom " + parent.getSimpleName() + " class '" + type.getSimpleName() + "' (" + type.getName() + ')', e);
               }
            } else {
               throw new DataProcessingException("Could not find a public constructor with a String[] parameter in custom " + parent.getSimpleName() + " class '" + type.getSimpleName() + "' (" + type.getName() + ')', e);
            }
         } catch (Exception e) {
            throw new DataProcessingException("Unexpected error instantiating custom " + parent.getSimpleName() + " class '" + type.getSimpleName() + "' (" + type.getName() + ')', e);
         }
      }
   }

   public static Conversion getDefaultConversion(Class fieldType, AnnotatedElement target, Parsed parsed) {
      String nullRead = getNullReadValue(target, parsed);
      Object valueIfStringIsNull = null;
      ObjectConversion conversion = null;
      if (fieldType != Boolean.class && fieldType != Boolean.TYPE) {
         if (fieldType != Character.class && fieldType != Character.TYPE) {
            if (fieldType != Byte.class && fieldType != Byte.TYPE) {
               if (fieldType != Short.class && fieldType != Short.TYPE) {
                  if (fieldType != Integer.class && fieldType != Integer.TYPE) {
                     if (fieldType != Long.class && fieldType != Long.TYPE) {
                        if (fieldType != Float.class && fieldType != Float.TYPE) {
                           if (fieldType != Double.class && fieldType != Double.TYPE) {
                              if (fieldType == BigInteger.class) {
                                 conversion = Conversions.toBigInteger();
                                 valueIfStringIsNull = nullRead == null ? null : new BigInteger(nullRead);
                              } else if (fieldType == BigDecimal.class) {
                                 conversion = Conversions.toBigDecimal();
                                 valueIfStringIsNull = nullRead == null ? null : new BigDecimal(nullRead);
                              } else if (Enum.class.isAssignableFrom(fieldType)) {
                                 conversion = Conversions.toEnum(fieldType);
                              }
                           } else {
                              conversion = Conversions.toDouble();
                              valueIfStringIsNull = nullRead == null ? null : Double.valueOf(nullRead);
                           }
                        } else {
                           conversion = Conversions.toFloat();
                           valueIfStringIsNull = nullRead == null ? null : Float.valueOf(nullRead);
                        }
                     } else {
                        conversion = Conversions.toLong();
                        valueIfStringIsNull = nullRead == null ? null : Long.valueOf(nullRead);
                     }
                  } else {
                     conversion = Conversions.toInteger();
                     valueIfStringIsNull = nullRead == null ? null : Integer.valueOf(nullRead);
                  }
               } else {
                  conversion = Conversions.toShort();
                  valueIfStringIsNull = nullRead == null ? null : Short.valueOf(nullRead);
               }
            } else {
               conversion = Conversions.toByte();
               valueIfStringIsNull = nullRead == null ? null : Byte.valueOf(nullRead);
            }
         } else {
            conversion = Conversions.toChar();
            if (nullRead != null && nullRead.length() > 1) {
               throw new DataProcessingException("Invalid default value for character '" + nullRead + "'. It should contain one character only.");
            }

            valueIfStringIsNull = nullRead == null ? null : nullRead.charAt(0);
         }
      } else {
         conversion = Conversions.toBoolean();
         valueIfStringIsNull = nullRead == null ? null : Boolean.valueOf(nullRead);
      }

      if (conversion != null) {
         conversion.setValueIfStringIsNull(valueIfStringIsNull);
         conversion.setValueIfObjectIsNull(getNullWriteValue(target, parsed));
      }

      return conversion;
   }

   public static Conversion getDefaultConversion(AnnotatedElement target) {
      Parsed parsed = (Parsed)findAnnotation(target, Parsed.class);
      return getDefaultConversion(getType(target), target, parsed);
   }

   public static void applyFormatSettings(Object formatter, String[] propertiesAndValues) {
      if (propertiesAndValues.length != 0) {
         Map<String, String> values = new HashMap();

         for(String setting : propertiesAndValues) {
            if (setting != null) {
               String[] pair = setting.split("=");
               if (pair.length != 2) {
                  throw new DataProcessingException("Illegal format setting '" + setting + "' among: " + Arrays.toString(propertiesAndValues));
               }

               values.put(pair[0], pair[1]);
            }
         }

         try {
            for(PropertyWrapper property : BeanHelper.getPropertyDescriptors(formatter.getClass())) {
               String name = property.getName();
               String value = (String)values.remove(name);
               if (value != null) {
                  invokeSetter(formatter, property, value);
               }

               if ("decimalFormatSymbols".equals(property.getName())) {
                  DecimalFormatSymbols modifiedDecimalSymbols = new DecimalFormatSymbols();
                  boolean modified = false;

                  try {
                     for(PropertyWrapper prop : BeanHelper.getPropertyDescriptors(modifiedDecimalSymbols.getClass())) {
                        value = (String)values.remove(prop.getName());
                        if (value != null) {
                           invokeSetter(modifiedDecimalSymbols, prop, value);
                           modified = true;
                        }
                     }

                     if (modified) {
                        Method writeMethod = property.getWriteMethod();
                        if (writeMethod == null) {
                           throw new IllegalStateException("No write method defined for property " + property.getName());
                        }

                        writeMethod.invoke(formatter, modifiedDecimalSymbols);
                     }
                  } catch (Throwable ex) {
                     throw new DataProcessingException("Error trying to configure decimal symbols of formatter '" + formatter.getClass() + '.', ex);
                  }
               }
            }
         } catch (Exception var16) {
         }

         if (!values.isEmpty()) {
            throw new DataProcessingException("Cannot find properties in formatter of type '" + formatter.getClass() + "': " + values);
         }
      }
   }

   private static void invokeSetter(Object formatter, PropertyWrapper property, String value) {
      Method writeMethod = property.getWriteMethod();
      if (writeMethod == null) {
         DataProcessingException exception = new DataProcessingException("Cannot set property '" + property.getName() + "' of formatter '" + formatter.getClass() + "' to '{value}'. No setter defined");
         exception.setValue(value);
         throw exception;
      } else {
         Class<?> parameterType = writeMethod.getParameterTypes()[0];
         Object parameterValue = null;
         if (parameterType == String.class) {
            parameterValue = value;
         } else if (parameterType != Integer.class && parameterType != Integer.TYPE) {
            if (parameterType != Character.class && parameterType != Character.TYPE) {
               if (parameterType == Currency.class) {
                  parameterValue = Currency.getInstance(value);
               } else if (parameterType != Boolean.class && parameterType != Boolean.TYPE) {
                  if (parameterType == TimeZone.class) {
                     parameterValue = TimeZone.getTimeZone(value);
                  } else if (parameterType == DateFormatSymbols.class) {
                     parameterValue = DateFormatSymbols.getInstance(new Locale(value));
                  }
               } else {
                  parameterValue = Boolean.valueOf(value);
               }
            } else {
               parameterValue = value.charAt(0);
            }
         } else {
            parameterValue = Integer.parseInt(value);
         }

         if (parameterValue == null) {
            DataProcessingException exception = new DataProcessingException("Cannot set property '" + property.getName() + "' of formatter '" + formatter.getClass() + ". Cannot convert '{value}' to instance of " + parameterType);
            exception.setValue(value);
            throw exception;
         } else {
            try {
               writeMethod.invoke(formatter, parameterValue);
            } catch (Throwable e) {
               DataProcessingException exception = new DataProcessingException("Error setting property '" + property.getName() + "' of formatter '" + formatter.getClass() + ", with '{parameterValue}' (converted from '{value}')", e);
               exception.setValue("parameterValue", parameterValue);
               exception.setValue(value);
               throw exception;
            }
         }
      }
   }

   private static boolean allFieldsIndexOrNameBased(boolean searchName, Class beanClass, MethodFilter filter) {
      boolean hasAnnotation = false;

      for(TransformedHeader header : getFieldSequence(beanClass, true, (HeaderTransformer)null, filter)) {
         if (header != null && header.getTarget() != null) {
            AnnotatedElement element = header.getTarget();
            if (!(element instanceof Method) || !filter.reject((Method)element)) {
               Parsed annotation = (Parsed)findAnnotation(element, Parsed.class);
               if (annotation != null) {
                  hasAnnotation = true;
                  int index = (Integer)AnnotationRegistry.getValue(element, annotation, "index", annotation.index());
                  if (index != -1 && searchName || index == -1 && !searchName) {
                     return false;
                  }
               }
            }
         }
      }

      return hasAnnotation;
   }

   public static boolean allFieldsIndexBasedForParsing(Class beanClass) {
      return allFieldsIndexOrNameBased(false, beanClass, MethodFilter.ONLY_SETTERS);
   }

   public static boolean allFieldsNameBasedForParsing(Class beanClass) {
      return allFieldsIndexOrNameBased(true, beanClass, MethodFilter.ONLY_SETTERS);
   }

   public static boolean allFieldsIndexBasedForWriting(Class beanClass) {
      return allFieldsIndexOrNameBased(false, beanClass, MethodFilter.ONLY_GETTERS);
   }

   public static boolean allFieldsNameBasedForWriting(Class beanClass) {
      return allFieldsIndexOrNameBased(true, beanClass, MethodFilter.ONLY_GETTERS);
   }

   public static Integer[] getSelectedIndexes(Class beanClass, MethodFilter filter) {
      List<Integer> indexes = new ArrayList();

      for(TransformedHeader header : getFieldSequence(beanClass, true, (HeaderTransformer)null, filter)) {
         if (header != null) {
            int index = header.getHeaderIndex();
            if (index != -1) {
               if (filter == MethodFilter.ONLY_GETTERS && indexes.contains(index)) {
                  throw new IllegalArgumentException("Duplicate field index '" + index + "' found in attribute '" + header.getTargetName() + "' of class " + beanClass.getName());
               }

               indexes.add(index);
            }
         }
      }

      return (Integer[])indexes.toArray(new Integer[indexes.size()]);
   }

   public static String[] deriveHeaderNamesFromFields(Class beanClass, MethodFilter filter) {
      List<TransformedHeader> sequence = getFieldSequence(beanClass, true, (HeaderTransformer)null, filter);
      List<String> out = new ArrayList(sequence.size());

      for(TransformedHeader field : sequence) {
         if (field == null) {
            return ArgumentUtils.EMPTY_STRING_ARRAY;
         }

         out.add(field.getHeaderName());
      }

      return (String[])out.toArray(new String[out.size()]);
   }

   public static Annotation findAnnotationInClass(Class beanClass, Class annotation) {
      Class<?> parent = beanClass;

      do {
         T out = (T)parent.getAnnotation(annotation);
         if (out != null) {
            return out;
         }

         for(Class iface : parent.getInterfaces()) {
            out = (T)findAnnotationInClass(iface, annotation);
            if (out != null) {
               return out;
            }
         }

         parent = parent.getSuperclass();
      } while(parent != null);

      return null;
   }

   public static Headers findHeadersAnnotation(Class beanClass) {
      return (Headers)findAnnotationInClass(beanClass, Headers.class);
   }

   public static Class getType(AnnotatedElement element) {
      if (element instanceof Field) {
         return ((Field)element).getType();
      } else {
         Method method = (Method)element;
         Class<?>[] params = method.getParameterTypes();
         if (params.length == 1) {
            return params[0];
         } else if (params.length > 1) {
            throw new IllegalArgumentException("Method " + describeElement(element) + " cannot have multiple parameters");
         } else {
            Class<?> returnType = method.getReturnType();
            if (returnType != Void.TYPE) {
               return returnType;
            } else {
               throw new IllegalArgumentException("Method " + describeElement(element) + " must return a value if it has no input parameter");
            }
         }
      }
   }

   public static Class getDeclaringClass(AnnotatedElement element) {
      return element instanceof Field ? ((Field)element).getDeclaringClass() : ((Method)element).getDeclaringClass();
   }

   public static String getName(AnnotatedElement element) {
      return element instanceof Field ? ((Field)element).getName() : ((Method)element).getName();
   }

   static String describeElement(AnnotatedElement element) {
      String description;
      if (element instanceof Field) {
         description = "attribute '" + ((Field)element).getName() + "'";
      } else {
         description = "method '" + ((Method)element).getName() + "'";
      }

      return description + " of class " + getDeclaringClass(element).getName();
   }

   private static void processAnnotations(AnnotatedElement element, boolean processNested, List indexes, List tmp, Map nestedReplacements, HeaderTransformer transformer, MethodFilter filter) {
      Parsed annotation = (Parsed)findAnnotation(element, Parsed.class);
      if (annotation != null) {
         TransformedHeader header = new TransformedHeader(element, transformer);
         if (filter == MethodFilter.ONLY_GETTERS && header.getHeaderIndex() >= 0 && indexes.contains(header.getHeaderIndex())) {
            throw new IllegalArgumentException("Duplicate field index '" + header.getHeaderIndex() + "' found in " + describeElement(element));
         }

         tmp.add(header);
         indexes.add(header.getHeaderIndex());
      }

      if (processNested) {
         Nested nested = (Nested)findAnnotation(element, Nested.class);
         if (nested != null) {
            tmp.add(new TransformedHeader(element, (HeaderTransformer)null));
            Class nestedBeanType = (Class)AnnotationRegistry.getValue(element, nested, "type", nested.type());
            if (nestedBeanType == Object.class) {
               nestedBeanType = getType(element);
            }

            Class<? extends HeaderTransformer> transformerType = (Class)AnnotationRegistry.getValue(element, nested, "headerTransformer", nested.headerTransformer());
            if (transformerType != HeaderTransformer.class) {
               String[] args = (String[])AnnotationRegistry.getValue(element, nested, "args", nested.args());
               HeaderTransformer innerTransformer = (HeaderTransformer)newInstance(HeaderTransformer.class, transformerType, args);
               nestedReplacements.put(element, getFieldSequence(nestedBeanType, true, indexes, innerTransformer, filter));
            } else {
               nestedReplacements.put(element, getFieldSequence(nestedBeanType, true, indexes, transformer, filter));
            }
         }
      }

   }

   public static List getFieldSequence(Class beanClass, boolean processNested, HeaderTransformer transformer, MethodFilter filter) {
      List<Integer> indexes = new ArrayList();
      List<TransformedHeader> tmp = getFieldSequence(beanClass, processNested, indexes, transformer, filter);
      Collections.sort(tmp, new Comparator() {
         public int compare(TransformedHeader t1, TransformedHeader t2) {
            int i1 = t1.getHeaderIndex();
            int i2 = t2.getHeaderIndex();
            return i1 < i2 ? -1 : (i1 == i2 ? 0 : 1);
         }
      });
      Collections.sort(indexes);
      int col = -1;

      for(int i : indexes) {
         ++col;
         if (i >= 0 && i != col) {
            while(i >= tmp.size()) {
               tmp.add((Object)null);
            }

            Collections.swap(tmp, i, col);
         }
      }

      return tmp;
   }

   private static List getFieldSequence(Class beanClass, boolean processNested, List indexes, HeaderTransformer transformer, MethodFilter filter) {
      List<TransformedHeader> tmp = new ArrayList();
      Map<AnnotatedElement, List<TransformedHeader>> nestedReplacements = new LinkedHashMap();

      for(Field field : getAllFields(beanClass).keySet()) {
         processAnnotations(field, processNested, indexes, tmp, nestedReplacements, transformer, filter);
      }

      for(Method method : getAnnotatedMethods(beanClass, filter)) {
         processAnnotations(method, processNested, indexes, tmp, nestedReplacements, transformer, filter);
      }

      if (!nestedReplacements.isEmpty()) {
         int size = tmp.size();

         for(int i = size - 1; i >= 0; --i) {
            TransformedHeader field = (TransformedHeader)tmp.get(i);
            List<TransformedHeader> nestedFields = (List)nestedReplacements.remove(field.getTarget());
            if (nestedFields != null) {
               tmp.remove(i);
               tmp.addAll(i, nestedFields);
               if (nestedReplacements.isEmpty()) {
                  break;
               }
            }
         }
      }

      return tmp;
   }

   public static Map getAllFields(Class beanClass) {
      Map<String, PropertyWrapper> properties = new LinkedHashMap();

      try {
         for(PropertyWrapper property : BeanHelper.getPropertyDescriptors(beanClass)) {
            String name = property.getName();
            if (name != null) {
               properties.put(name, property);
            }
         }
      } catch (Exception var10) {
      }

      Set<String> used = new HashSet();
      Class<?> clazz = beanClass;
      Map<Field, PropertyWrapper> out = new LinkedHashMap();

      do {
         Field[] declared = clazz.getDeclaredFields();

         for(Field field : declared) {
            if (!used.contains(field.getName())) {
               used.add(field.getName());
               out.put(field, properties.get(field.getName()));
            }
         }

         clazz = clazz.getSuperclass();
      } while(clazz != null && clazz != Object.class);

      return out;
   }

   public static List getAnnotatedMethods(Class beanClass, MethodFilter filter, Class annotationType) {
      List<Method> out = new ArrayList();
      Class clazz = beanClass;

      do {
         Method[] declared = clazz.getDeclaredMethods();

         for(Method method : declared) {
            if (!method.isSynthetic() && annotationType == NO_ANNOTATIONS.class) {
               if (!filter.reject(method)) {
                  out.add(method);
               }
            } else {
               Annotation[] annotations = method.getDeclaredAnnotations();

               for(Annotation annotation : annotations) {
                  if (annotationType == null && isCustomAnnotation(annotation) || annotationType == annotation.annotationType()) {
                     if (!filter.reject(method)) {
                        out.add(method);
                     }
                     break;
                  }
               }
            }
         }

         clazz = clazz.getSuperclass();
      } while(clazz != null && clazz != Object.class);

      return out;
   }

   public static List getAllMethods(Class beanClass, MethodFilter filter) {
      return getAnnotatedMethods(beanClass, filter, NO_ANNOTATIONS.class);
   }

   public static List getAnnotatedMethods(Class beanClass, MethodFilter filter) {
      return getAnnotatedMethods(beanClass, filter, (Class)null);
   }

   public static List getAnnotatedFields(Class beanClass) {
      return getAnnotatedFields(beanClass, (Class)null);
   }

   public static List getAnnotatedFields(Class beanClass, Class annotationType) {
      List<Field> out = new ArrayList();
      Class clazz = beanClass;

      do {
         Field[] declared = clazz.getDeclaredFields();

         for(Field field : declared) {
            Annotation[] annotations = field.getDeclaredAnnotations();

            for(Annotation annotation : annotations) {
               if (annotationType == null && isCustomAnnotation(annotation) || annotationType == annotation.annotationType()) {
                  out.add(field);
                  break;
               }
            }
         }

         clazz = clazz.getSuperclass();
      } while(clazz != null && clazz != Object.class);

      return out;
   }

   public static synchronized Annotation findAnnotation(AnnotatedElement annotatedElement, Class annotationType) {
      if (annotatedElement != null && annotationType != null) {
         if (annotatedElement.equals(lastProcessedElement) && annotationType == lastProcessedAnnotationType) {
            return lastAnnotationFound;
         } else {
            lastProcessedElement = annotatedElement;
            lastProcessedAnnotationType = annotationType;
            Stack<Annotation> path = new Stack();
            A annotation = (A)((Annotation)findAnnotation(annotatedElement, annotationType, new HashSet(), path));
            if (annotation != null && !path.isEmpty()) {
               while(!path.isEmpty()) {
                  Annotation parent = (Annotation)path.pop();
                  Annotation target = path.isEmpty() ? annotation : (Annotation)path.peek();

                  for(Method method : parent.annotationType().getDeclaredMethods()) {
                     Copy copy = (Copy)method.getAnnotation(Copy.class);
                     if (copy != null) {
                        Class targetClass = copy.to();
                        String targetProperty = copy.property();
                        if (targetProperty.trim().isEmpty()) {
                           targetProperty = method.getName();
                        }

                        Object existingValue = AnnotationRegistry.getValue(annotatedElement, target, method.getName());
                        Object value;
                        if (existingValue != null) {
                           value = existingValue;
                        } else {
                           value = invoke(parent, method);
                        }

                        Class sourceValueType = method.getReturnType();
                        Class<?> targetPropertyType = findAnnotationMethodType(targetClass, targetProperty);
                        if (targetPropertyType != null && targetPropertyType.isArray() && !value.getClass().isArray()) {
                           Object array = Array.newInstance(sourceValueType, 1);
                           Array.set(array, 0, value);
                           value = array;
                        }

                        if (targetClass == target.annotationType()) {
                           AnnotationRegistry.setValue(annotatedElement, annotation, targetProperty, value);
                        } else {
                           A ann = (A)((Annotation)findAnnotation(annotatedElement, targetClass, new HashSet(), new Stack()));
                           if (ann == null) {
                              throw new IllegalStateException("Can't process @Copy annotation on '" + method + "'. " + "Annotation '" + targetClass.getName() + "' not used in " + parent.annotationType().getName() + ". Unable to process field " + annotatedElement + "'");
                           }

                           AnnotationRegistry.setValue(annotatedElement, ann, targetProperty, value);
                        }
                     }
                  }
               }

               lastAnnotationFound = annotation;
               return annotation;
            } else {
               lastAnnotationFound = annotation;
               return annotation;
            }
         }
      } else {
         return null;
      }
   }

   private static Class findAnnotationMethodType(Class type, String methodName) {
      for(Method method : type.getDeclaredMethods()) {
         if (method.getName().equals(methodName)) {
            return method.getReturnType();
         }
      }

      return null;
   }

   private static Object invoke(Annotation annotation, Method method) {
      try {
         return method.invoke(annotation, (Object[])null);
      } catch (Exception e) {
         throw new IllegalStateException("Can't read value from annotation " + annotation, e);
      }
   }

   private static Object findAnnotation(AnnotatedElement annotatedElement, Class annotationType, Set visited, Stack path) {
      Annotation[] declaredAnnotations = annotatedElement.getDeclaredAnnotations();

      for(int i = 0; i < declaredAnnotations.length; ++i) {
         Annotation ann = declaredAnnotations[i];
         if (ann.annotationType() == annotationType) {
            return ann;
         }
      }

      for(int i = 0; i < declaredAnnotations.length; ++i) {
         Annotation ann = declaredAnnotations[i];
         if (isCustomAnnotation(ann) && visited.add(ann)) {
            A annotation = (A)findAnnotation(ann.annotationType(), annotationType, visited, path);
            path.push(ann);
            if (annotation != null) {
               return annotation;
            }
         }
      }

      return null;
   }

   private static boolean isCustomAnnotation(Annotation annotation) {
      Class annotationType = annotation.annotationType();
      if (customAnnotationTypes.contains(annotationType)) {
         return true;
      } else if (javaLangAnnotationTypes.contains(annotationType)) {
         return false;
      } else if (annotationType.getName().startsWith("java.lang.annotation")) {
         javaLangAnnotationTypes.add(annotationType);
         return false;
      } else {
         customAnnotationTypes.add(annotationType);
         return true;
      }
   }

   public static List findAllAnnotationsInPackage(AnnotatedElement annotatedElement, Package aPackage) {
      ArrayList<Annotation> found = new ArrayList();
      findAllAnnotationsInPackage(annotatedElement, aPackage, found, new HashSet());
      return found;
   }

   private static void findAllAnnotationsInPackage(AnnotatedElement annotatedElement, Package aPackage, ArrayList found, Set visited) {
      Annotation[] declaredAnnotations = annotatedElement.getDeclaredAnnotations();

      for(int i = 0; i < declaredAnnotations.length; ++i) {
         Annotation ann = declaredAnnotations[i];
         if (aPackage.equals(ann.annotationType().getPackage())) {
            found.add(ann);
         }

         if (isCustomAnnotation(ann) && visited.add(ann)) {
            findAllAnnotationsInPackage(ann.annotationType(), aPackage, found, visited);
         }
      }

   }

   public static final Object getDefaultPrimitiveValue(Class type) {
      if (type == Integer.TYPE) {
         return 0;
      } else if (type == Double.TYPE) {
         return (double)0.0F;
      } else if (type == Boolean.TYPE) {
         return Boolean.FALSE;
      } else if (type == Long.TYPE) {
         return 0L;
      } else if (type == Float.TYPE) {
         return 0.0F;
      } else if (type == Byte.TYPE) {
         return 0;
      } else if (type == Character.TYPE) {
         return '\u0000';
      } else {
         return type == Short.TYPE ? Short.valueOf((short)0) : null;
      }
   }

   private static final class NO_ANNOTATIONS implements Annotation {
      public Class annotationType() {
         return NO_ANNOTATIONS.class;
      }
   }
}
