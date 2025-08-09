package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Locale;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.parser.DataTypeParser$;
import org.apache.spark.sql.catalyst.parser.LegacyTypeStringParser$;
import org.apache.spark.sql.errors.DataTypeErrors$;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.util.Try.;

@Stable
public final class StructType$ extends AbstractDataType implements Serializable {
   public static final StructType$ MODULE$ = new StructType$();
   private static final String SQL_FUNCTION_DEFAULT_METADATA_KEY = "default";

   public String SQL_FUNCTION_DEFAULT_METADATA_KEY() {
      return SQL_FUNCTION_DEFAULT_METADATA_KEY;
   }

   public DataType defaultConcreteType() {
      return new StructType();
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof StructType;
   }

   public String simpleString() {
      return "struct";
   }

   public StructType fromString(final String raw) {
      DataType var3 = (DataType).MODULE$.apply(() -> DataType$.MODULE$.fromJson(raw)).getOrElse(() -> LegacyTypeStringParser$.MODULE$.parseString(raw));
      if (var3 instanceof StructType var4) {
         return var4;
      } else {
         throw DataTypeErrors$.MODULE$.failedParsingStructTypeError(raw);
      }
   }

   public StructType fromDDL(final String ddl) {
      return DataTypeParser$.MODULE$.parseTableSchema(ddl);
   }

   public StructType apply(final Seq fields) {
      return new StructType((StructField[])fields.toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public StructType apply(final List fields) {
      return new StructType((StructField[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(fields).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public DataType removeMetadata(final String key, final DataType dt) {
      if (dt instanceof StructType var5) {
         StructField[] fields = var5.fields();
         StructField[] newFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(fields), (f) -> {
            MetadataBuilder mb = new MetadataBuilder();
            DataType x$1 = MODULE$.removeMetadata(key, f.dataType());
            Metadata x$2 = mb.withMetadata(f.metadata()).remove(key).build();
            String x$3 = f.copy$default$1();
            boolean x$4 = f.copy$default$3();
            return f.copy(x$3, x$1, x$4, x$2);
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return new StructType(newFields);
      } else {
         return dt;
      }
   }

   public DataType unionLikeMerge(final DataType left, final DataType right) {
      return this.mergeInternal(left, right, (s1, s2) -> {
         StructField[] leftFields = s1.fields();
         StructField[] rightFields = s2.fields();
         scala.Predef..MODULE$.require(leftFields.length == rightFields.length, () -> "To merge nullability, two structs must have same number of fields.");
         StructField[] newFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(leftFields), scala.Predef..MODULE$.wrapRefArray(rightFields))), (x0$1) -> {
            if (x0$1 == null) {
               throw new MatchError(x0$1);
            } else {
               StructField leftField = (StructField)x0$1._1();
               StructField rightField = (StructField)x0$1._2();
               DataType x$1 = MODULE$.unionLikeMerge(leftField.dataType(), rightField.dataType());
               boolean x$2 = leftField.nullable() || rightField.nullable();
               String x$3 = leftField.copy$default$1();
               Metadata x$4 = leftField.copy$default$4();
               return leftField.copy(x$3, x$1, x$2, x$4);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return new StructType(newFields);
      });
   }

   public DataType merge(final DataType left, final DataType right, final boolean caseSensitive) {
      return this.mergeInternal(left, right, (s1, s2) -> {
         StructField[] leftFields = s1.fields();
         StructField[] rightFields = s2.fields();
         ArrayBuffer newFields = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
         Map rightMapped = MODULE$.fieldsMap(rightFields, caseSensitive);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(leftFields), (x0$1) -> {
            $anonfun$merge$2(rightMapped, newFields, caseSensitive, x0$1);
            return BoxedUnit.UNIT;
         });
         Map leftMapped = MODULE$.fieldsMap(leftFields, caseSensitive);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps(rightFields), (f) -> BoxesRunTime.boxToBoolean($anonfun$merge$6(leftMapped, caseSensitive, f)))), (f) -> (ArrayBuffer)newFields.$plus$eq(f));
         return new StructType((StructField[])newFields.toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
      });
   }

   public boolean merge$default$3() {
      return true;
   }

   private DataType mergeInternal(final DataType left, final DataType right, final Function2 mergeStruct) {
      Tuple2 var5 = new Tuple2(left, right);
      if (var5 != null) {
         DataType var6 = (DataType)var5._1();
         DataType var7 = (DataType)var5._2();
         if (var6 instanceof ArrayType) {
            ArrayType var8 = (ArrayType)var6;
            DataType leftElementType = var8.elementType();
            boolean leftContainsNull = var8.containsNull();
            if (var7 instanceof ArrayType) {
               ArrayType var11 = (ArrayType)var7;
               DataType rightElementType = var11.elementType();
               boolean rightContainsNull = var11.containsNull();
               return new ArrayType(this.mergeInternal(leftElementType, rightElementType, mergeStruct), leftContainsNull || rightContainsNull);
            }
         }
      }

      if (var5 != null) {
         DataType var14 = (DataType)var5._1();
         DataType var15 = (DataType)var5._2();
         if (var14 instanceof MapType) {
            MapType var16 = (MapType)var14;
            DataType leftKeyType = var16.keyType();
            DataType leftValueType = var16.valueType();
            boolean leftContainsNull = var16.valueContainsNull();
            if (var15 instanceof MapType) {
               MapType var20 = (MapType)var15;
               DataType rightKeyType = var20.keyType();
               DataType rightValueType = var20.valueType();
               boolean rightContainsNull = var20.valueContainsNull();
               return new MapType(this.mergeInternal(leftKeyType, rightKeyType, mergeStruct), this.mergeInternal(leftValueType, rightValueType, mergeStruct), leftContainsNull || rightContainsNull);
            }
         }
      }

      if (var5 != null) {
         DataType s1 = (DataType)var5._1();
         DataType s2 = (DataType)var5._2();
         if (s1 instanceof StructType) {
            StructType var26 = (StructType)s1;
            if (s2 instanceof StructType) {
               StructType var27 = (StructType)s2;
               return (DataType)mergeStruct.apply(var26, var27);
            }
         }
      }

      if (var5 != null) {
         DataType var28 = (DataType)var5._1();
         DataType var29 = (DataType)var5._2();
         if (var28 instanceof DecimalType) {
            DecimalType var30 = (DecimalType)var28;
            Option var31 = DecimalType.Fixed$.MODULE$.unapply(var30);
            if (!var31.isEmpty()) {
               int leftPrecision = ((Tuple2)var31.get())._1$mcI$sp();
               int leftScale = ((Tuple2)var31.get())._2$mcI$sp();
               if (var29 instanceof DecimalType) {
                  DecimalType var34 = (DecimalType)var29;
                  Option var35 = DecimalType.Fixed$.MODULE$.unapply(var34);
                  if (!var35.isEmpty()) {
                     int rightPrecision = ((Tuple2)var35.get())._1$mcI$sp();
                     int rightScale = ((Tuple2)var35.get())._2$mcI$sp();
                     if (leftScale == rightScale) {
                        return new DecimalType(scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(leftPrecision), rightPrecision), leftScale);
                     }

                     throw DataTypeErrors$.MODULE$.cannotMergeDecimalTypesWithIncompatibleScaleError(leftScale, rightScale);
                  }
               }
            }
         }
      }

      if (var5 != null) {
         DataType leftUdt = (DataType)var5._1();
         DataType rightUdt = (DataType)var5._2();
         if (leftUdt instanceof UserDefinedType) {
            UserDefinedType var40 = (UserDefinedType)leftUdt;
            if (rightUdt instanceof UserDefinedType) {
               UserDefinedType var41 = (UserDefinedType)rightUdt;
               Class var10000 = var40.userClass();
               Class var42 = var41.userClass();
               if (var10000 == null) {
                  if (var42 == null) {
                     return var40;
                  }
               } else if (var10000.equals(var42)) {
                  return var40;
               }
            }
         }
      }

      if (var5 != null) {
         DataType var43 = (DataType)var5._1();
         DataType var44 = (DataType)var5._2();
         if (var43 instanceof YearMonthIntervalType) {
            YearMonthIntervalType var45 = (YearMonthIntervalType)var43;
            byte lstart = var45.startField();
            byte lend = var45.endField();
            if (var44 instanceof YearMonthIntervalType) {
               YearMonthIntervalType var48 = (YearMonthIntervalType)var44;
               byte rstart = var48.startField();
               byte rend = var48.endField();
               return new YearMonthIntervalType((byte)Math.min(lstart, rstart), (byte)Math.max(lend, rend));
            }
         }
      }

      if (var5 != null) {
         DataType var51 = (DataType)var5._1();
         DataType var52 = (DataType)var5._2();
         if (var51 instanceof DayTimeIntervalType) {
            DayTimeIntervalType var53 = (DayTimeIntervalType)var51;
            byte lstart = var53.startField();
            byte lend = var53.endField();
            if (var52 instanceof DayTimeIntervalType) {
               DayTimeIntervalType var56 = (DayTimeIntervalType)var52;
               byte rstart = var56.startField();
               byte rend = var56.endField();
               return new DayTimeIntervalType((byte)Math.min(lstart, rstart), (byte)Math.max(lend, rend));
            }
         }
      }

      if (var5 != null) {
         DataType leftType = (DataType)var5._1();
         DataType rightType = (DataType)var5._2();
         if (leftType == null) {
            if (rightType == null) {
               return leftType;
            }
         } else if (leftType.equals(rightType)) {
            return leftType;
         }
      }

      throw DataTypeErrors$.MODULE$.cannotMergeIncompatibleDataTypesError(left, right);
   }

   public Map fieldsMap(final StructField[] fields, final boolean caseSensitive) {
      scala.collection.mutable.Map map = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      map.sizeHint(fields.length);
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(fields), (s) -> caseSensitive ? map.put(s.name(), s) : map.put(s.name().toLowerCase(Locale.ROOT), s));
      return map;
   }

   public boolean fieldsMap$default$2() {
      return true;
   }

   public Option findMissingFields(final StructType source, final StructType target, final Function2 resolver) {
      ArrayBuffer newFields = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(target.fields()), (field) -> {
         Option found = scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(source.fields()), (f) -> BoxesRunTime.boxToBoolean($anonfun$findMissingFields$2(resolver, field, f)));
         if (found.isEmpty()) {
            return newFields.$plus$eq(field);
         } else {
            return bothStructType$1(((StructField)found.get()).dataType(), field.dataType()) && !((StructField)found.get()).dataType().sameType(field.dataType()) ? MODULE$.findMissingFields((StructType)((StructField)found.get()).dataType(), (StructType)field.dataType(), resolver).map((missingType) -> {
               StructField qual$1 = (StructField)found.get();
               String x$2 = qual$1.copy$default$1();
               boolean x$3 = qual$1.copy$default$3();
               Metadata x$4 = qual$1.copy$default$4();
               return (ArrayBuffer)newFields.$plus$eq(qual$1.copy(x$2, missingType, x$3, x$4));
            }) : BoxedUnit.UNIT;
         }
      });
      return (Option)(newFields.isEmpty() ? scala.None..MODULE$ : new Some(new StructType((StructField[])newFields.toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)))));
   }

   public StructType apply(final StructField[] fields) {
      return new StructType(fields);
   }

   public Option unapply(final StructType x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.fields()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StructType$.class);
   }

   private static final String normalize$1(final String name, final boolean caseSensitive$1) {
      return caseSensitive$1 ? name : name.toLowerCase(Locale.ROOT);
   }

   // $FF: synthetic method
   public static final void $anonfun$merge$2(final Map rightMapped$1, final ArrayBuffer newFields$1, final boolean caseSensitive$1, final StructField x0$1) {
      if (x0$1 != null) {
         String leftName = x0$1.name();
         DataType leftType = x0$1.dataType();
         boolean leftNullable = x0$1.nullable();
         rightMapped$1.get(normalize$1(leftName, caseSensitive$1)).map((x0$2) -> {
            if (x0$2 != null) {
               DataType rightType = x0$2.dataType();
               boolean rightNullable = x0$2.nullable();

               try {
                  DataType x$1 = MODULE$.merge(leftType, rightType, MODULE$.merge$default$3());
                  boolean x$2 = leftNullable || rightNullable;
                  String x$3 = x0$1.copy$default$1();
                  Metadata x$4 = x0$1.copy$default$4();
                  return x0$1.copy(x$3, x$1, x$2, x$4);
               } catch (Throwable var16) {
                  if (var16 != null && scala.util.control.NonFatal..MODULE$.apply(var16)) {
                     throw DataTypeErrors$.MODULE$.cannotMergeIncompatibleDataTypesError(leftType, rightType);
                  } else {
                     throw var16;
                  }
               }
            } else {
               throw new MatchError(x0$2);
            }
         }).orElse(() -> new Some(x0$1)).foreach((x$9) -> (ArrayBuffer)newFields$1.$plus$eq(x$9));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$merge$6(final Map leftMapped$1, final boolean caseSensitive$1, final StructField f) {
      return leftMapped$1.contains(normalize$1(f.name(), caseSensitive$1));
   }

   private static final boolean bothStructType$1(final DataType dt1, final DataType dt2) {
      return dt1 instanceof StructType && dt2 instanceof StructType;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findMissingFields$2(final Function2 resolver$2, final StructField field$1, final StructField f) {
      return BoxesRunTime.unboxToBoolean(resolver$2.apply(field$1.name(), f.name()));
   }

   private StructType$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
