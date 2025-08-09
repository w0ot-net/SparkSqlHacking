package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.serde2.Deserializer;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.FloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.TimestampObjectInspector;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Attribute;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.unsafe.types.UTF8String;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

public final class HadoopTableReader$ implements HiveInspectors, Logging {
   public static final HadoopTableReader$ MODULE$ = new HadoopTableReader$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      HiveInspectors.$init$(MODULE$);
      Logging.$init$(MODULE$);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public DataType javaTypeToDataType(final Type clz) {
      return HiveInspectors.javaTypeToDataType$(this, clz);
   }

   public Function1 wrapperFor(final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrapperFor$(this, oi, dataType);
   }

   public Function1 unwrapperFor(final ObjectInspector objectInspector) {
      return HiveInspectors.unwrapperFor$(this, (ObjectInspector)objectInspector);
   }

   public Function3 unwrapperFor(final StructField field) {
      return HiveInspectors.unwrapperFor$(this, (StructField)field);
   }

   public Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrap$(this, (Object)a, (ObjectInspector)oi, (DataType)dataType);
   }

   public Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return HiveInspectors.wrap$(this, row, wrappers, cache, dataTypes);
   }

   public Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      return HiveInspectors.wrap$(this, (Seq)row, (Function1[])wrappers, (Object[])cache);
   }

   public ObjectInspector toInspector(final DataType dataType) {
      return HiveInspectors.toInspector$(this, (DataType)dataType);
   }

   public ObjectInspector toInspector(final Expression expr) {
      return HiveInspectors.toInspector$(this, (Expression)expr);
   }

   public DataType inspectorToDataType(final ObjectInspector inspector) {
      return HiveInspectors.inspectorToDataType$(this, inspector);
   }

   public HiveInspectors.typeInfoConversions typeInfoConversions(final DataType dt) {
      return HiveInspectors.typeInfoConversions$(this, dt);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public void initializeLocalJobConfFunc(final String path, final TableDesc tableDesc, final JobConf jobConf) {
      FileInputFormat.setInputPaths(jobConf, (Path[])(new .colon.colon(new Path(path), scala.collection.immutable.Nil..MODULE$)).toArray(scala.reflect.ClassTag..MODULE$.apply(Path.class)));
      if (tableDesc != null) {
         HiveTableUtil$.MODULE$.configureJobPropertiesForStorageHandler(tableDesc, jobConf, true);
         Utilities.copyTableJobPropertiesToConf(tableDesc, jobConf);
      }

      String bufferSize = System.getProperty("spark.buffer.size", "65536");
      jobConf.set("io.file.buffer.size", bufferSize);
   }

   public Iterator fillObject(final Iterator iterator, final Deserializer rawDeser, final Seq nonPartitionKeyAttrs, final InternalRow mutableRow, final Deserializer tableDeser) {
      StructObjectInspector soi = rawDeser.getObjectInspector().equals(tableDeser.getObjectInspector()) ? (StructObjectInspector)rawDeser.getObjectInspector() : (StructObjectInspector)ObjectInspectorConverters.getConvertedOI(rawDeser.getObjectInspector(), tableDeser.getObjectInspector());
      this.logDebug((Function0)(() -> soi.toString()));
      Tuple2 var9 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(((IterableOnceOps)nonPartitionKeyAttrs.map((x0$1) -> {
         if (x0$1 != null) {
            Attribute attr = (Attribute)x0$1._1();
            int ordinal = x0$1._2$mcI$sp();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(soi.getStructFieldRef(attr.name())), BoxesRunTime.boxToInteger(ordinal));
         } else {
            throw new MatchError(x0$1);
         }
      })).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.apply(StructField.class), scala.reflect.ClassTag..MODULE$.Int());
      if (var9 != null) {
         StructField[] fieldRefs = (StructField[])var9._1();
         int[] fieldOrdinals = (int[])var9._2();
         Tuple2 var8 = new Tuple2(fieldRefs, fieldOrdinals);
         StructField[] fieldRefs = (StructField[])var8._1();
         int[] fieldOrdinals = (int[])var8._2();
         ArraySeq unwrappers = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fieldRefs), (x$9) -> {
            ObjectInspector var2 = x$9.getFieldObjectInspector();
            if (var2 instanceof BooleanObjectInspector var3) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$4(var3, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof ByteObjectInspector var4) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$5(var4, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof ShortObjectInspector var5) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$6(var5, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof IntObjectInspector var6) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$7(var6, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof LongObjectInspector var7) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$8(var7, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof FloatObjectInspector var8) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$9(var8, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof DoubleObjectInspector var9) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$10(var9, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof HiveVarcharObjectInspector var10) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$11(var10, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof HiveCharObjectInspector var11) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$12(var11, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof HiveDecimalObjectInspector var12) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$13(var12, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof TimestampObjectInspector var13) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$14(var13, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof DateObjectInspector var14) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$15(var14, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else if (var2 instanceof BinaryObjectInspector var15) {
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$16(var15, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            } else {
               Function1 unwrapper = MODULE$.unwrapperFor(var2);
               return (value, row, ordinal) -> {
                  $anonfun$fillObject$17(unwrapper, value, row, BoxesRunTime.unboxToInt(ordinal));
                  return BoxedUnit.UNIT;
               };
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Function3.class))).toImmutableArraySeq();
         ObjectInspectorConverters.Converter converter = ObjectInspectorConverters.getConverter(rawDeser.getObjectInspector(), soi);
         return iterator.map((value) -> {
            Object raw = converter.convert(rawDeser.deserialize(value));
            IntRef i = IntRef.create(0);
            int length = fieldRefs.length;

            while(i.elem < length) {
               try {
                  Object fieldValue = soi.getStructFieldData(raw, fieldRefs[i.elem]);
                  if (fieldValue == null) {
                     mutableRow.setNullAt(fieldOrdinals[i.elem]);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     ((Function3)unwrappers.apply(i.elem)).apply(fieldValue, mutableRow, BoxesRunTime.boxToInteger(fieldOrdinals[i.elem]));
                  }

                  ++i.elem;
               } catch (Throwable var13) {
                  MODULE$.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception thrown in field <", ">"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FIELD_NAME..MODULE$, fieldRefs[i.elem].getFieldName())})))), var13);
                  throw var13;
               }
            }

            return mutableRow;
         });
      } else {
         throw new MatchError(var9);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$4(final BooleanObjectInspector x2$1, final Object value, final InternalRow row, final int ordinal) {
      row.setBoolean(ordinal, x2$1.get(value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$5(final ByteObjectInspector x3$1, final Object value, final InternalRow row, final int ordinal) {
      row.setByte(ordinal, x3$1.get(value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$6(final ShortObjectInspector x4$1, final Object value, final InternalRow row, final int ordinal) {
      row.setShort(ordinal, x4$1.get(value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$7(final IntObjectInspector x5$1, final Object value, final InternalRow row, final int ordinal) {
      row.setInt(ordinal, x5$1.get(value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$8(final LongObjectInspector x6$1, final Object value, final InternalRow row, final int ordinal) {
      row.setLong(ordinal, x6$1.get(value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$9(final FloatObjectInspector x7$1, final Object value, final InternalRow row, final int ordinal) {
      row.setFloat(ordinal, x7$1.get(value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$10(final DoubleObjectInspector x8$1, final Object value, final InternalRow row, final int ordinal) {
      row.setDouble(ordinal, x8$1.get(value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$11(final HiveVarcharObjectInspector x9$1, final Object value, final InternalRow row, final int ordinal) {
      row.update(ordinal, UTF8String.fromString(x9$1.getPrimitiveJavaObject(value).getValue()));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$12(final HiveCharObjectInspector x10$1, final Object value, final InternalRow row, final int ordinal) {
      row.update(ordinal, UTF8String.fromString(x10$1.getPrimitiveJavaObject(value).getValue()));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$13(final HiveDecimalObjectInspector x11$1, final Object value, final InternalRow row, final int ordinal) {
      row.update(ordinal, HiveShim$.MODULE$.toCatalystDecimal(x11$1, value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$14(final TimestampObjectInspector x12$1, final Object value, final InternalRow row, final int ordinal) {
      row.setLong(ordinal, org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.fromJavaTimestamp(x12$1.getPrimitiveJavaObject(value)));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$15(final DateObjectInspector x13$1, final Object value, final InternalRow row, final int ordinal) {
      row.setInt(ordinal, org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.fromJavaDate(x13$1.getPrimitiveJavaObject(value)));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$16(final BinaryObjectInspector x14$1, final Object value, final InternalRow row, final int ordinal) {
      row.update(ordinal, x14$1.getPrimitiveJavaObject(value));
   }

   // $FF: synthetic method
   public static final void $anonfun$fillObject$17(final Function1 unwrapper$1, final Object value, final InternalRow row, final int ordinal) {
      row.update(ordinal, unwrapper$1.apply(value));
   }

   private HadoopTableReader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
