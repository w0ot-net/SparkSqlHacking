package org.apache.spark.sql.util;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.types.DateUnit;
import org.apache.arrow.vector.types.FloatingPointPrecision;
import org.apache.arrow.vector.types.IntervalUnit;
import org.apache.arrow.vector.types.TimeUnit;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.types.pojo.ArrowType.Binary;
import org.apache.arrow.vector.types.pojo.ArrowType.Bool;
import org.apache.arrow.vector.types.pojo.ArrowType.LargeBinary;
import org.apache.arrow.vector.types.pojo.ArrowType.LargeUtf8;
import org.apache.arrow.vector.types.pojo.ArrowType.List;
import org.apache.arrow.vector.types.pojo.ArrowType.Null;
import org.apache.arrow.vector.types.pojo.ArrowType.Struct;
import org.apache.arrow.vector.types.pojo.ArrowType.Utf8;
import org.apache.spark.SparkException.;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.BinaryType$;
import org.apache.spark.sql.types.BooleanType$;
import org.apache.spark.sql.types.ByteType$;
import org.apache.spark.sql.types.CalendarIntervalType$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DateType$;
import org.apache.spark.sql.types.DayTimeIntervalType;
import org.apache.spark.sql.types.DayTimeIntervalType$;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.DoubleType$;
import org.apache.spark.sql.types.FloatType$;
import org.apache.spark.sql.types.IntegerType$;
import org.apache.spark.sql.types.LongType$;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.NullType$;
import org.apache.spark.sql.types.ShortType$;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StringType$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructField$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.TimestampNTZType$;
import org.apache.spark.sql.types.TimestampType$;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.sql.types.VariantType;
import org.apache.spark.sql.types.VariantType$;
import org.apache.spark.sql.types.YearMonthIntervalType;
import org.apache.spark.sql.types.YearMonthIntervalType$;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.mutable.Buffer;
import scala.runtime.BoxesRunTime;

public final class ArrowUtils$ {
   public static final ArrowUtils$ MODULE$ = new ArrowUtils$();
   private static final RootAllocator rootAllocator = new RootAllocator(Long.MAX_VALUE);

   public RootAllocator rootAllocator() {
      return rootAllocator;
   }

   public ArrowType toArrowType(final DataType dt, final String timeZoneId, final boolean largeVarTypes) {
      boolean var5 = false;
      Object var6 = null;
      boolean var7 = false;
      Object var8 = null;
      boolean var9 = false;
      Object var10 = null;
      if (BooleanType$.MODULE$.equals(dt)) {
         return Bool.INSTANCE;
      } else if (ByteType$.MODULE$.equals(dt)) {
         return new ArrowType.Int(8, true);
      } else if (ShortType$.MODULE$.equals(dt)) {
         return new ArrowType.Int(16, true);
      } else if (IntegerType$.MODULE$.equals(dt)) {
         return new ArrowType.Int(32, true);
      } else if (LongType$.MODULE$.equals(dt)) {
         return new ArrowType.Int(64, true);
      } else if (FloatType$.MODULE$.equals(dt)) {
         return new ArrowType.FloatingPoint(FloatingPointPrecision.SINGLE);
      } else if (DoubleType$.MODULE$.equals(dt)) {
         return new ArrowType.FloatingPoint(FloatingPointPrecision.DOUBLE);
      } else {
         if (dt instanceof StringType) {
            var5 = true;
            StringType var16 = (StringType)dt;
            if (!largeVarTypes) {
               return Utf8.INSTANCE;
            }
         }

         if (BinaryType$.MODULE$.equals(dt)) {
            var7 = true;
            if (!largeVarTypes) {
               return Binary.INSTANCE;
            }
         }

         if (var5 && largeVarTypes) {
            return LargeUtf8.INSTANCE;
         } else if (var7 && largeVarTypes) {
            return LargeBinary.INSTANCE;
         } else {
            if (dt instanceof DecimalType) {
               DecimalType var12 = (DecimalType)dt;
               Option var13 = DecimalType.Fixed$.MODULE$.unapply(var12);
               if (!var13.isEmpty()) {
                  int precision = ((Tuple2)var13.get())._1$mcI$sp();
                  int scale = ((Tuple2)var13.get())._2$mcI$sp();
                  return new ArrowType.Decimal(precision, scale, 128);
               }
            }

            if (DateType$.MODULE$.equals(dt)) {
               return new ArrowType.Date(DateUnit.DAY);
            } else {
               if (TimestampType$.MODULE$.equals(dt)) {
                  var9 = true;
                  if (timeZoneId == null) {
                     throw .MODULE$.internalError("Missing timezoneId where it is mandatory.");
                  }
               }

               if (var9) {
                  return new ArrowType.Timestamp(TimeUnit.MICROSECOND, timeZoneId);
               } else if (TimestampNTZType$.MODULE$.equals(dt)) {
                  return new ArrowType.Timestamp(TimeUnit.MICROSECOND, (String)null);
               } else if (NullType$.MODULE$.equals(dt)) {
                  return Null.INSTANCE;
               } else if (dt instanceof YearMonthIntervalType) {
                  return new ArrowType.Interval(IntervalUnit.YEAR_MONTH);
               } else if (dt instanceof DayTimeIntervalType) {
                  return new ArrowType.Duration(TimeUnit.MICROSECOND);
               } else if (CalendarIntervalType$.MODULE$.equals(dt)) {
                  return new ArrowType.Interval(IntervalUnit.MONTH_DAY_NANO);
               } else {
                  throw ExecutionErrors$.MODULE$.unsupportedDataTypeError(dt);
               }
            }
         }
      }
   }

   public boolean toArrowType$default$3() {
      return false;
   }

   public DataType fromArrowType(final ArrowType dt) {
      boolean var3 = false;
      ArrowType.Int var4 = null;
      boolean var5 = false;
      ArrowType.FloatingPoint var6 = null;
      boolean var7 = false;
      ArrowType.Timestamp var8 = null;
      boolean var9 = false;
      ArrowType.Interval var10 = null;
      ArrowType.Bool var10000 = Bool.INSTANCE;
      if (var10000 == null) {
         if (dt == null) {
            return BooleanType$.MODULE$;
         }
      } else if (var10000.equals(dt)) {
         return BooleanType$.MODULE$;
      }

      if (dt instanceof ArrowType.Int) {
         var3 = true;
         var4 = (ArrowType.Int)dt;
         if (var4.getIsSigned() && var4.getBitWidth() == 8) {
            return ByteType$.MODULE$;
         }
      }

      if (var3 && var4.getIsSigned() && var4.getBitWidth() == 16) {
         return ShortType$.MODULE$;
      } else if (var3 && var4.getIsSigned() && var4.getBitWidth() == 32) {
         return IntegerType$.MODULE$;
      } else if (var3 && var4.getIsSigned() && var4.getBitWidth() == 64) {
         return LongType$.MODULE$;
      } else {
         if (dt instanceof ArrowType.FloatingPoint) {
            var5 = true;
            var6 = (ArrowType.FloatingPoint)dt;
            FloatingPointPrecision var29 = var6.getPrecision();
            FloatingPointPrecision var13 = FloatingPointPrecision.SINGLE;
            if (var29 == null) {
               if (var13 == null) {
                  return FloatType$.MODULE$;
               }
            } else if (var29.equals(var13)) {
               return FloatType$.MODULE$;
            }
         }

         if (var5) {
            FloatingPointPrecision var30 = var6.getPrecision();
            FloatingPointPrecision var14 = FloatingPointPrecision.DOUBLE;
            if (var30 == null) {
               if (var14 == null) {
                  return DoubleType$.MODULE$;
               }
            } else if (var30.equals(var14)) {
               return DoubleType$.MODULE$;
            }
         }

         ArrowType.Utf8 var31 = Utf8.INSTANCE;
         if (var31 == null) {
            if (dt == null) {
               return StringType$.MODULE$;
            }
         } else if (var31.equals(dt)) {
            return StringType$.MODULE$;
         }

         ArrowType.Binary var32 = Binary.INSTANCE;
         if (var32 == null) {
            if (dt == null) {
               return BinaryType$.MODULE$;
            }
         } else if (var32.equals(dt)) {
            return BinaryType$.MODULE$;
         }

         ArrowType.LargeUtf8 var33 = LargeUtf8.INSTANCE;
         if (var33 == null) {
            if (dt == null) {
               return StringType$.MODULE$;
            }
         } else if (var33.equals(dt)) {
            return StringType$.MODULE$;
         }

         ArrowType.LargeBinary var34 = LargeBinary.INSTANCE;
         if (var34 == null) {
            if (dt == null) {
               return BinaryType$.MODULE$;
            }
         } else if (var34.equals(dt)) {
            return BinaryType$.MODULE$;
         }

         if (dt instanceof ArrowType.Decimal) {
            ArrowType.Decimal var19 = (ArrowType.Decimal)dt;
            return new DecimalType(var19.getPrecision(), var19.getScale());
         } else {
            if (dt instanceof ArrowType.Date) {
               ArrowType.Date var20 = (ArrowType.Date)dt;
               DateUnit var35 = var20.getUnit();
               DateUnit var21 = DateUnit.DAY;
               if (var35 == null) {
                  if (var21 == null) {
                     return DateType$.MODULE$;
                  }
               } else if (var35.equals(var21)) {
                  return DateType$.MODULE$;
               }
            }

            if (dt instanceof ArrowType.Timestamp) {
               label182: {
                  var7 = true;
                  var8 = (ArrowType.Timestamp)dt;
                  TimeUnit var36 = var8.getUnit();
                  TimeUnit var22 = TimeUnit.MICROSECOND;
                  if (var36 == null) {
                     if (var22 != null) {
                        break label182;
                     }
                  } else if (!var36.equals(var22)) {
                     break label182;
                  }

                  if (var8.getTimezone() == null) {
                     return TimestampNTZType$.MODULE$;
                  }
               }
            }

            if (var7) {
               TimeUnit var37 = var8.getUnit();
               TimeUnit var23 = TimeUnit.MICROSECOND;
               if (var37 == null) {
                  if (var23 == null) {
                     return TimestampType$.MODULE$;
                  }
               } else if (var37.equals(var23)) {
                  return TimestampType$.MODULE$;
               }
            }

            ArrowType.Null var38 = Null.INSTANCE;
            if (var38 == null) {
               if (dt == null) {
                  return NullType$.MODULE$;
               }
            } else if (var38.equals(dt)) {
               return NullType$.MODULE$;
            }

            if (dt instanceof ArrowType.Interval) {
               var9 = true;
               var10 = (ArrowType.Interval)dt;
               IntervalUnit var39 = var10.getUnit();
               IntervalUnit var25 = IntervalUnit.YEAR_MONTH;
               if (var39 == null) {
                  if (var25 == null) {
                     return YearMonthIntervalType$.MODULE$.apply();
                  }
               } else if (var39.equals(var25)) {
                  return YearMonthIntervalType$.MODULE$.apply();
               }
            }

            if (dt instanceof ArrowType.Duration) {
               ArrowType.Duration var26 = (ArrowType.Duration)dt;
               TimeUnit var40 = var26.getUnit();
               TimeUnit var27 = TimeUnit.MICROSECOND;
               if (var40 == null) {
                  if (var27 == null) {
                     return DayTimeIntervalType$.MODULE$.apply();
                  }
               } else if (var40.equals(var27)) {
                  return DayTimeIntervalType$.MODULE$.apply();
               }
            }

            if (var9) {
               IntervalUnit var41 = var10.getUnit();
               IntervalUnit var28 = IntervalUnit.MONTH_DAY_NANO;
               if (var41 == null) {
                  if (var28 == null) {
                     return CalendarIntervalType$.MODULE$;
                  }
               } else if (var41.equals(var28)) {
                  return CalendarIntervalType$.MODULE$;
               }
            }

            throw ExecutionErrors$.MODULE$.unsupportedArrowTypeError(dt);
         }
      }
   }

   public Field toArrowField(final String name, final DataType dt, final boolean nullable, final String timeZoneId, final boolean largeVarTypes) {
      while(!(dt instanceof ArrayType)) {
         if (dt instanceof StructType var13) {
            StructField[] fields = var13.fields();
            FieldType fieldType = new FieldType(nullable, Struct.INSTANCE, (DictionaryEncoding)null);
            return new Field(name, fieldType, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(fields), (field) -> MODULE$.toArrowField(field.name(), field.dataType(), field.nullable(), timeZoneId, largeVarTypes), scala.reflect.ClassTag..MODULE$.apply(Field.class))).toImmutableArraySeq()).asJava());
         }

         if (dt instanceof MapType var16) {
            DataType keyType = var16.keyType();
            DataType valueType = var16.valueType();
            boolean valueContainsNull = var16.valueContainsNull();
            FieldType mapType = new FieldType(nullable, new ArrowType.Map(false), (DictionaryEncoding)null);
            return new Field(name, mapType, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon(this.toArrowField("entries", (new StructType()).add("key", keyType, false).add("value", valueType, valueContainsNull), false, timeZoneId, largeVarTypes), scala.collection.immutable.Nil..MODULE$)).asJava());
         }

         if (!(dt instanceof UserDefinedType)) {
            if (dt instanceof VariantType) {
               FieldType fieldType = new FieldType(nullable, Struct.INSTANCE, (DictionaryEncoding)null);
               FieldType metadataFieldType = new FieldType(false, this.toArrowType(BinaryType$.MODULE$, timeZoneId, largeVarTypes), (DictionaryEncoding)null, scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("variant"), "true")})))).asJava());
               return new Field(name, fieldType, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon(this.toArrowField("value", BinaryType$.MODULE$, false, timeZoneId, largeVarTypes), new scala.collection.immutable..colon.colon(new Field("metadata", metadataFieldType, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((Seq)scala.package..MODULE$.Seq().empty()).asJava()), scala.collection.immutable.Nil..MODULE$))).asJava());
            }

            FieldType fieldType = new FieldType(nullable, this.toArrowType(dt, timeZoneId, largeVarTypes), (DictionaryEncoding)null);
            return new Field(name, fieldType, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((Seq)scala.package..MODULE$.Seq().empty()).asJava());
         }

         UserDefinedType var21 = (UserDefinedType)dt;
         DataType var10001 = var21.sqlType();
         largeVarTypes = largeVarTypes;
         timeZoneId = timeZoneId;
         nullable = nullable;
         dt = var10001;
         name = name;
      }

      ArrayType var9 = (ArrayType)dt;
      DataType elementType = var9.elementType();
      boolean containsNull = var9.containsNull();
      FieldType fieldType = new FieldType(nullable, List.INSTANCE, (DictionaryEncoding)null);
      return new Field(name, fieldType, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon(this.toArrowField("element", elementType, containsNull, timeZoneId, largeVarTypes), scala.collection.immutable.Nil..MODULE$)).asJava());
   }

   public boolean toArrowField$default$5() {
      return false;
   }

   public boolean isVariantField(final Field field) {
      scala.Predef..MODULE$.assert(field.getType() instanceof ArrowType.Struct);
      return scala.jdk.CollectionConverters..MODULE$.BufferHasAsJava((Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(field.getChildren()).asScala().map((x$1) -> x$1.getName())).asJava().containsAll(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon("value", new scala.collection.immutable..colon.colon("metadata", scala.collection.immutable.Nil..MODULE$))).asJava()) && scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(field.getChildren()).asScala().exists((child) -> BoxesRunTime.boxToBoolean($anonfun$isVariantField$2(child)));
   }

   public DataType fromArrowField(final Field field) {
      boolean var3 = false;
      Object var4 = null;
      ArrowType var5 = field.getType();
      if (var5 instanceof ArrowType.Map) {
         Field elementField = (Field)field.getChildren().get(0);
         DataType keyType = this.fromArrowField((Field)elementField.getChildren().get(0));
         DataType valueType = this.fromArrowField((Field)elementField.getChildren().get(1));
         return new MapType(keyType, valueType, ((Field)elementField.getChildren().get(1)).isNullable());
      } else {
         label44: {
            ArrowType.List var10000 = List.INSTANCE;
            if (var10000 == null) {
               if (var5 == null) {
                  break label44;
               }
            } else if (var10000.equals(var5)) {
               break label44;
            }

            label33: {
               ArrowType.Struct var14 = Struct.INSTANCE;
               if (var14 == null) {
                  if (var5 != null) {
                     break label33;
                  }
               } else if (!var14.equals(var5)) {
                  break label33;
               }

               var3 = true;
               if (this.isVariantField(field)) {
                  return VariantType$.MODULE$;
               }
            }

            if (var3) {
               Buffer fields = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(field.getChildren()).asScala().map((child) -> {
                  DataType dt = MODULE$.fromArrowField(child);
                  return new StructField(child.getName(), dt, child.isNullable(), StructField$.MODULE$.apply$default$4());
               });
               return new StructType((StructField[])fields.toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
            }

            return this.fromArrowType(var5);
         }

         Field elementField = (Field)field.getChildren().get(0);
         DataType elementType = this.fromArrowField(elementField);
         return new ArrayType(elementType, elementField.isNullable());
      }
   }

   public Schema toArrowSchema(final StructType schema, final String timeZoneId, final boolean errorOnDuplicatedFieldNames, final boolean largeVarTypes) {
      return new Schema(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((Seq)schema.map((field) -> MODULE$.toArrowField(field.name(), MODULE$.deduplicateFieldNames(field.dataType(), errorOnDuplicatedFieldNames), field.nullable(), timeZoneId, largeVarTypes))).asJava());
   }

   public StructType fromArrowSchema(final Schema schema) {
      return new StructType((StructField[])((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(schema.getFields()).asScala().map((field) -> {
         DataType dt = MODULE$.fromArrowField(field);
         return new StructField(field.getName(), dt, field.isNullable(), StructField$.MODULE$.apply$default$4());
      })).toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   private DataType deduplicateFieldNames(final DataType dt, final boolean errorOnDuplicatedFieldNames) {
      while(dt instanceof UserDefinedType) {
         UserDefinedType var6 = (UserDefinedType)dt;
         DataType var10000 = var6.sqlType();
         errorOnDuplicatedFieldNames = errorOnDuplicatedFieldNames;
         dt = var10000;
      }

      if (dt instanceof StructType var7) {
         StructField[] fields = var7.fields();
         String[] var19;
         if (scala.Predef..MODULE$.wrapRefArray((Object[])var7.names()).toSet().size() == var7.names().length) {
            var19 = var7.names();
         } else {
            if (errorOnDuplicatedFieldNames) {
               throw ExecutionErrors$.MODULE$.duplicatedFieldNameInArrowStructError(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(var7.names()).toImmutableArraySeq());
            }

            scala.collection.immutable.Map genNawName = (scala.collection.immutable.Map)scala.collection.ArrayOps..MODULE$.groupBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])var7.names()), (x) -> (String)scala.Predef..MODULE$.identity(x)).map((x0$1) -> {
               if (x0$1 != null) {
                  String name = (String)x0$1._1();
                  String[] names = (String[])x0$1._2();
                  if (names.length > 1) {
                     AtomicInteger i = new AtomicInteger();
                     return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), (Function0)() -> name + "_" + i.getAndIncrement());
                  }
               }

               if (x0$1 != null) {
                  String name = (String)x0$1._1();
                  return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), (Function0)() -> name);
               } else {
                  throw new MatchError(x0$1);
               }
            });
            var19 = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])var7.names()), (x$2) -> (String)((Function0)genNawName.apply(x$2)).apply(), scala.reflect.ClassTag..MODULE$.apply(String.class));
         }

         String[] newNames = var19;
         StructField[] newFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(fields), scala.Predef..MODULE$.wrapRefArray((Object[])newNames))), (x0$2) -> {
            if (x0$2 != null) {
               StructField var4 = (StructField)x0$2._1();
               String name = (String)x0$2._2();
               if (var4 != null) {
                  DataType dataType = var4.dataType();
                  boolean nullable = var4.nullable();
                  Metadata metadata = var4.metadata();
                  return new StructField(name, MODULE$.deduplicateFieldNames(dataType, errorOnDuplicatedFieldNames), nullable, metadata);
               }
            }

            throw new MatchError(x0$2);
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return new StructType(newFields);
      } else if (dt instanceof ArrayType var12) {
         DataType elementType = var12.elementType();
         boolean containsNull = var12.containsNull();
         return new ArrayType(this.deduplicateFieldNames(elementType, errorOnDuplicatedFieldNames), containsNull);
      } else if (dt instanceof MapType var15) {
         DataType keyType = var15.keyType();
         DataType valueType = var15.valueType();
         boolean valueContainsNull = var15.valueContainsNull();
         return new MapType(this.deduplicateFieldNames(keyType, errorOnDuplicatedFieldNames), this.deduplicateFieldNames(valueType, errorOnDuplicatedFieldNames), valueContainsNull);
      } else {
         return dt;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isVariantField$2(final Field child) {
      boolean var4;
      label32: {
         label24: {
            String var10000 = child.getName();
            String var1 = "metadata";
            if (var10000 == null) {
               if (var1 != null) {
                  break label24;
               }
            } else if (!var10000.equals(var1)) {
               break label24;
            }

            Object var3 = child.getMetadata().getOrDefault("variant", "false");
            String var2 = "true";
            if (var3 == null) {
               if (var2 == null) {
                  break label32;
               }
            } else if (var3.equals(var2)) {
               break label32;
            }
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   private ArrowUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
