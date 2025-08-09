package org.apache.spark.sql.hive.orc;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.io.orc.OrcSerde;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.orc.OrcConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.SpecificInternalRow;
import org.apache.spark.sql.catalyst.expressions.UnsafeProjection;
import org.apache.spark.sql.catalyst.expressions.UnsafeProjection.;
import org.apache.spark.sql.hive.HiveInspectors;
import org.apache.spark.sql.hive.HiveShim$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class OrcFileFormat$ implements HiveInspectors, Logging, Serializable {
   public static final OrcFileFormat$ MODULE$ = new OrcFileFormat$();
   private static final String SARG_PUSHDOWN;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      HiveInspectors.$init$(MODULE$);
      Logging.$init$(MODULE$);
      SARG_PUSHDOWN = "sarg.pushdown";
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

   public String SARG_PUSHDOWN() {
      return SARG_PUSHDOWN;
   }

   public Iterator unwrapOrcStructs(final Configuration conf, final StructType dataSchema, final StructType requiredSchema, final Option maybeStructOI, final Iterator iterator) {
      OrcSerde deserializer = new OrcSerde();
      SpecificInternalRow mutableRow = new SpecificInternalRow((Seq)requiredSchema.map((x$5) -> x$5.dataType()));
      UnsafeProjection unsafeProjection = .MODULE$.create(requiredSchema);
      boolean forcePositionalEvolution = OrcConf.FORCE_POSITIONAL_EVOLUTION.getBoolean(conf);
      return (Iterator)maybeStructOI.map((oi) -> unwrap$1(oi, requiredSchema, forcePositionalEvolution, dataSchema, iterator, deserializer, mutableRow, unsafeProjection)).getOrElse(() -> scala.package..MODULE$.Iterator().empty());
   }

   public void setRequiredColumns(final Configuration conf, final StructType dataSchema, final StructType requestedSchema) {
      Seq ids = (Seq)requestedSchema.map((a) -> scala.Predef..MODULE$.int2Integer(dataSchema.fieldIndex(a.name())));
      Tuple2 var7 = ((IterableOps)((SeqOps)ids.zip(scala.Predef..MODULE$.wrapRefArray((Object[])requestedSchema.fieldNames()))).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms()), scala.math.Ordering.String..MODULE$))).unzip(scala.Predef..MODULE$.$conforms());
      if (var7 != null) {
         Seq sortedIDs = (Seq)var7._1();
         Seq sortedNames = (Seq)var7._2();
         Tuple2 var6 = new Tuple2(sortedIDs, sortedNames);
         Seq sortedIDs = (Seq)var6._1();
         Seq sortedNames = (Seq)var6._2();
         HiveShim$.MODULE$.appendReadColumns(conf, sortedIDs, sortedNames);
      } else {
         throw new MatchError(var7);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OrcFileFormat$.class);
   }

   private static final Iterator unwrap$1(final StructObjectInspector oi, final StructType requiredSchema$2, final boolean forcePositionalEvolution$1, final StructType dataSchema$2, final Iterator iterator$1, final OrcSerde deserializer$1, final SpecificInternalRow mutableRow$1, final UnsafeProjection unsafeProjection$1) {
      Tuple2 var10 = ((IterableOps)((IterableOps)requiredSchema$2.zipWithIndex()).map((x0$1) -> {
         if (x0$1 != null) {
            org.apache.spark.sql.types.StructField field = (org.apache.spark.sql.types.StructField)x0$1._1();
            int ordinal = x0$1._2$mcI$sp();
            StructField ref = null;
            if (forcePositionalEvolution$1) {
               ref = (StructField)oi.getAllStructFieldRefs().get(dataSchema$2.fieldIndex(field.name()));
            } else {
               ref = oi.getStructFieldRef(field.name());
               if (ref == null) {
                  int var10001 = dataSchema$2.fieldIndex(field.name());
                  ref = oi.getStructFieldRef("_col" + var10001);
               }
            }

            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ref), BoxesRunTime.boxToInteger(ordinal));
         } else {
            throw new MatchError(x0$1);
         }
      })).unzip(scala.Predef..MODULE$.$conforms());
      if (var10 != null) {
         Seq fieldRefs = (Seq)var10._1();
         Seq fieldOrdinals = (Seq)var10._2();
         Tuple2 var9 = new Tuple2(fieldRefs, fieldOrdinals);
         Seq fieldRefs = (Seq)var9._1();
         Seq fieldOrdinals = (Seq)var9._2();
         Seq unwrappers = (Seq)fieldRefs.map((r) -> r == null ? null : MODULE$.unwrapperFor(r));
         return iterator$1.map((value) -> {
            Object raw = deserializer$1.deserialize(value);
            int i = 0;

            for(int length = fieldRefs.length(); i < length; ++i) {
               StructField fieldRef = (StructField)fieldRefs.apply(i);
               Object fieldValue = fieldRef == null ? null : oi.getStructFieldData(raw, fieldRef);
               if (fieldValue == null) {
                  mutableRow$1.setNullAt(BoxesRunTime.unboxToInt(fieldOrdinals.apply(i)));
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  ((Function3)unwrappers.apply(i)).apply(fieldValue, mutableRow$1, fieldOrdinals.apply(i));
               }
            }

            return unsafeProjection$1.apply(mutableRow$1);
         });
      } else {
         throw new MatchError(var10);
      }
   }

   private OrcFileFormat$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
