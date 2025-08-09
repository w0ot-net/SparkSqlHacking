package org.apache.spark.sql.catalyst;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoder;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders$;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.Metadata$;
import org.apache.spark.sql.types.SQLUserDefinedType;
import org.apache.spark.sql.types.UDTRegistration$;
import org.apache.spark.sql.types.UserDefinedType;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.runtime.BoxesRunTime;

public final class JavaTypeInference$ {
   public static final JavaTypeInference$ MODULE$ = new JavaTypeInference$();

   public Tuple2 inferDataType(final Type beanType) {
      AgnosticEncoder encoder = this.encoderFor(beanType);
      return new Tuple2(encoder.dataType(), BoxesRunTime.boxToBoolean(encoder.nullable()));
   }

   public AgnosticEncoder encoderFor(final Class cls) {
      return this.encoderFor((Type)cls);
   }

   public AgnosticEncoder encoderFor(final Type beanType) {
      return this.encoderFor(beanType, .MODULE$.Set().empty(), this.encoderFor$default$3());
   }

   private AgnosticEncoder encoderFor(final Type t, final Set seenTypeSet, final Map typeVariables) {
      while(true) {
         boolean var6 = false;
         Class var7 = null;
         if (t instanceof Class) {
            var6 = true;
            var7 = (Class)t;
            Class var9 = Boolean.TYPE;
            if (var7 == null) {
               if (var9 == null) {
                  break;
               }
            } else if (var7.equals(var9)) {
               break;
            }
         }

         if (var6) {
            Class var10 = Byte.TYPE;
            if (var7 == null) {
               if (var10 == null) {
                  return AgnosticEncoders.PrimitiveByteEncoder$.MODULE$;
               }
            } else if (var7.equals(var10)) {
               return AgnosticEncoders.PrimitiveByteEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var11 = Short.TYPE;
            if (var7 == null) {
               if (var11 == null) {
                  return AgnosticEncoders.PrimitiveShortEncoder$.MODULE$;
               }
            } else if (var7.equals(var11)) {
               return AgnosticEncoders.PrimitiveShortEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var12 = Integer.TYPE;
            if (var7 == null) {
               if (var12 == null) {
                  return AgnosticEncoders.PrimitiveIntEncoder$.MODULE$;
               }
            } else if (var7.equals(var12)) {
               return AgnosticEncoders.PrimitiveIntEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var13 = Long.TYPE;
            if (var7 == null) {
               if (var13 == null) {
                  return AgnosticEncoders.PrimitiveLongEncoder$.MODULE$;
               }
            } else if (var7.equals(var13)) {
               return AgnosticEncoders.PrimitiveLongEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var14 = Float.TYPE;
            if (var7 == null) {
               if (var14 == null) {
                  return AgnosticEncoders.PrimitiveFloatEncoder$.MODULE$;
               }
            } else if (var7.equals(var14)) {
               return AgnosticEncoders.PrimitiveFloatEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var15 = Double.TYPE;
            if (var7 == null) {
               if (var15 == null) {
                  return AgnosticEncoders.PrimitiveDoubleEncoder$.MODULE$;
               }
            } else if (var7.equals(var15)) {
               return AgnosticEncoders.PrimitiveDoubleEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var16 = Boolean.class;
            if (var7 == null) {
               if (var16 == null) {
                  return AgnosticEncoders.BoxedBooleanEncoder$.MODULE$;
               }
            } else if (var7.equals(var16)) {
               return AgnosticEncoders.BoxedBooleanEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var17 = Byte.class;
            if (var7 == null) {
               if (var17 == null) {
                  return AgnosticEncoders.BoxedByteEncoder$.MODULE$;
               }
            } else if (var7.equals(var17)) {
               return AgnosticEncoders.BoxedByteEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var18 = Short.class;
            if (var7 == null) {
               if (var18 == null) {
                  return AgnosticEncoders.BoxedShortEncoder$.MODULE$;
               }
            } else if (var7.equals(var18)) {
               return AgnosticEncoders.BoxedShortEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var19 = Integer.class;
            if (var7 == null) {
               if (var19 == null) {
                  return AgnosticEncoders.BoxedIntEncoder$.MODULE$;
               }
            } else if (var7.equals(var19)) {
               return AgnosticEncoders.BoxedIntEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var20 = Long.class;
            if (var7 == null) {
               if (var20 == null) {
                  return AgnosticEncoders.BoxedLongEncoder$.MODULE$;
               }
            } else if (var7.equals(var20)) {
               return AgnosticEncoders.BoxedLongEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var21 = Float.class;
            if (var7 == null) {
               if (var21 == null) {
                  return AgnosticEncoders.BoxedFloatEncoder$.MODULE$;
               }
            } else if (var7.equals(var21)) {
               return AgnosticEncoders.BoxedFloatEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var22 = Double.class;
            if (var7 == null) {
               if (var22 == null) {
                  return AgnosticEncoders.BoxedDoubleEncoder$.MODULE$;
               }
            } else if (var7.equals(var22)) {
               return AgnosticEncoders.BoxedDoubleEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var23 = String.class;
            if (var7 == null) {
               if (var23 == null) {
                  return AgnosticEncoders.StringEncoder$.MODULE$;
               }
            } else if (var7.equals(var23)) {
               return AgnosticEncoders.StringEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var24 = byte[].class;
            if (var7 == null) {
               if (var24 == null) {
                  return AgnosticEncoders.BinaryEncoder$.MODULE$;
               }
            } else if (var7.equals(var24)) {
               return AgnosticEncoders.BinaryEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var25 = BigDecimal.class;
            if (var7 == null) {
               if (var25 == null) {
                  return AgnosticEncoders$.MODULE$.DEFAULT_JAVA_DECIMAL_ENCODER();
               }
            } else if (var7.equals(var25)) {
               return AgnosticEncoders$.MODULE$.DEFAULT_JAVA_DECIMAL_ENCODER();
            }
         }

         if (var6) {
            Class var26 = BigInteger.class;
            if (var7 == null) {
               if (var26 == null) {
                  return AgnosticEncoders.JavaBigIntEncoder$.MODULE$;
               }
            } else if (var7.equals(var26)) {
               return AgnosticEncoders.JavaBigIntEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var27 = LocalDate.class;
            if (var7 == null) {
               if (var27 == null) {
                  return AgnosticEncoders$.MODULE$.STRICT_LOCAL_DATE_ENCODER();
               }
            } else if (var7.equals(var27)) {
               return AgnosticEncoders$.MODULE$.STRICT_LOCAL_DATE_ENCODER();
            }
         }

         if (var6) {
            Class var28 = Date.class;
            if (var7 == null) {
               if (var28 == null) {
                  return AgnosticEncoders$.MODULE$.STRICT_DATE_ENCODER();
               }
            } else if (var7.equals(var28)) {
               return AgnosticEncoders$.MODULE$.STRICT_DATE_ENCODER();
            }
         }

         if (var6) {
            Class var29 = Instant.class;
            if (var7 == null) {
               if (var29 == null) {
                  return AgnosticEncoders$.MODULE$.STRICT_INSTANT_ENCODER();
               }
            } else if (var7.equals(var29)) {
               return AgnosticEncoders$.MODULE$.STRICT_INSTANT_ENCODER();
            }
         }

         if (var6) {
            Class var30 = Timestamp.class;
            if (var7 == null) {
               if (var30 == null) {
                  return AgnosticEncoders$.MODULE$.STRICT_TIMESTAMP_ENCODER();
               }
            } else if (var7.equals(var30)) {
               return AgnosticEncoders$.MODULE$.STRICT_TIMESTAMP_ENCODER();
            }
         }

         if (var6) {
            Class var31 = LocalDateTime.class;
            if (var7 == null) {
               if (var31 == null) {
                  return AgnosticEncoders.LocalDateTimeEncoder$.MODULE$;
               }
            } else if (var7.equals(var31)) {
               return AgnosticEncoders.LocalDateTimeEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var32 = Duration.class;
            if (var7 == null) {
               if (var32 == null) {
                  return AgnosticEncoders.DayTimeIntervalEncoder$.MODULE$;
               }
            } else if (var7.equals(var32)) {
               return AgnosticEncoders.DayTimeIntervalEncoder$.MODULE$;
            }
         }

         if (var6) {
            Class var33 = Period.class;
            if (var7 == null) {
               if (var33 == null) {
                  return AgnosticEncoders.YearMonthIntervalEncoder$.MODULE$;
               }
            } else if (var7.equals(var33)) {
               return AgnosticEncoders.YearMonthIntervalEncoder$.MODULE$;
            }
         }

         if (var6 && var7.isEnum()) {
            return new AgnosticEncoders.JavaEnumEncoder(scala.reflect.ClassTag..MODULE$.apply(var7));
         }

         if (var6 && var7.isAnnotationPresent(SQLUserDefinedType.class)) {
            UserDefinedType udt = (UserDefinedType)((SQLUserDefinedType)var7.getAnnotation(SQLUserDefinedType.class)).udt().getConstructor().newInstance();
            Class udtClass = ((SQLUserDefinedType)udt.userClass().getAnnotation(SQLUserDefinedType.class)).udt();
            return new AgnosticEncoders.UDTEncoder(udt, udtClass);
         }

         if (var6 && UDTRegistration$.MODULE$.exists(var7.getName())) {
            UserDefinedType udt = (UserDefinedType)((Class)UDTRegistration$.MODULE$.getUDTFor(var7.getName()).get()).getConstructor().newInstance();
            return new AgnosticEncoders.UDTEncoder(udt, udt.getClass());
         }

         if (var6 && var7.isArray()) {
            AgnosticEncoder elementEncoder = this.encoderFor(var7.getComponentType(), seenTypeSet, typeVariables);
            return new AgnosticEncoders.ArrayEncoder(elementEncoder, elementEncoder.nullable());
         }

         if (var6 && List.class.isAssignableFrom(var7)) {
            AgnosticEncoder element = this.encoderFor(((TypeVariable[]).MODULE$.wrapRefArray((Object[])var7.getTypeParameters()).array())[0], seenTypeSet, typeVariables);
            return new AgnosticEncoders.IterableEncoder(scala.reflect.ClassTag..MODULE$.apply(var7), element, element.nullable(), false);
         }

         if (var6 && java.util.Set.class.isAssignableFrom(var7)) {
            AgnosticEncoder element = this.encoderFor(((TypeVariable[]).MODULE$.wrapRefArray((Object[])var7.getTypeParameters()).array())[0], seenTypeSet, typeVariables);
            return new AgnosticEncoders.IterableEncoder(scala.reflect.ClassTag..MODULE$.apply(var7), element, element.nullable(), false);
         }

         if (var6 && java.util.Map.class.isAssignableFrom(var7)) {
            AgnosticEncoder keyEncoder = this.encoderFor(((TypeVariable[]).MODULE$.wrapRefArray((Object[])var7.getTypeParameters()).array())[0], seenTypeSet, typeVariables);
            AgnosticEncoder valueEncoder = this.encoderFor(((TypeVariable[]).MODULE$.wrapRefArray((Object[])var7.getTypeParameters()).array())[1], seenTypeSet, typeVariables);
            return new AgnosticEncoders.MapEncoder(scala.reflect.ClassTag..MODULE$.apply(var7), keyEncoder, valueEncoder, valueEncoder.nullable());
         }

         if (t instanceof TypeVariable var42) {
            Type var47 = (Type)typeVariables.apply(var42);
            typeVariables = typeVariables;
            seenTypeSet = seenTypeSet;
            t = var47;
         } else {
            if (!(t instanceof ParameterizedType)) {
               if (var6) {
                  if (seenTypeSet.contains(var7)) {
                     throw ExecutionErrors$.MODULE$.cannotHaveCircularReferencesInBeanClassError(var7);
                  }

                  PropertyDescriptor[] properties = this.getJavaBeanReadableProperties(var7);
                  Map classTV = (Map)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(TypeUtils.getTypeArguments(var7, Object.class)).asScala().toMap(scala..less.colon.less..MODULE$.refl()).$plus$plus(typeVariables);
                  AgnosticEncoders.EncoderField[] fields = (AgnosticEncoders.EncoderField[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])properties), (property) -> {
                     Method readMethod = property.getReadMethod();
                     AgnosticEncoder encoder = MODULE$.encoderFor(readMethod.getGenericReturnType(), (Set)seenTypeSet.$plus(var7), classTV);
                     boolean hasNonNull = readMethod.isAnnotationPresent(Nonnull.class);
                     return new AgnosticEncoders.EncoderField(property.getName(), encoder, encoder.nullable() && !hasNonNull, Metadata$.MODULE$.empty(), scala.Option..MODULE$.apply(readMethod.getName()), scala.Option..MODULE$.apply(property.getWriteMethod()).map((x$1) -> x$1.getName()));
                  }, scala.reflect.ClassTag..MODULE$.apply(AgnosticEncoders.EncoderField.class));
                  return new AgnosticEncoders.JavaBeanEncoder(scala.reflect.ClassTag..MODULE$.apply(var7), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(fields).toImmutableArraySeq());
               }

               throw ExecutionErrors$.MODULE$.cannotFindEncoderForTypeError(t.toString());
            }

            ParameterizedType var43 = (ParameterizedType)t;
            Type var10000 = var43.getRawType();
            typeVariables = scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(TypeUtils.getTypeArguments(var43)).asScala().toMap(scala..less.colon.less..MODULE$.refl());
            seenTypeSet = seenTypeSet;
            t = var10000;
         }
      }

      return AgnosticEncoders.PrimitiveBooleanEncoder$.MODULE$;
   }

   private Map encoderFor$default$3() {
      return .MODULE$.Map().empty();
   }

   public PropertyDescriptor[] getJavaBeanReadableProperties(final Class beanClass) {
      BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
      return (PropertyDescriptor[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filterNot$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filterNot$extension(.MODULE$.refArrayOps((Object[])beanInfo.getPropertyDescriptors()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$getJavaBeanReadableProperties$1(x$2)))), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$getJavaBeanReadableProperties$2(x$3)))), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$getJavaBeanReadableProperties$3(x$4)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getJavaBeanReadableProperties$1(final PropertyDescriptor x$2) {
      boolean var2;
      label23: {
         String var10000 = x$2.getName();
         String var1 = "class";
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getJavaBeanReadableProperties$2(final PropertyDescriptor x$3) {
      boolean var2;
      label23: {
         String var10000 = x$3.getName();
         String var1 = "declaringClass";
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getJavaBeanReadableProperties$3(final PropertyDescriptor x$4) {
      return x$4.getReadMethod() != null;
   }

   private JavaTypeInference$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
