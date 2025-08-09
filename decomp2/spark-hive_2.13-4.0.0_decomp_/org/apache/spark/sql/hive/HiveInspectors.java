package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.SettableStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardConstantListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardConstantMapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
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
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaBinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaBooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaDateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaDoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaFloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaHiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaHiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaHiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaHiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaHiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaIntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaLongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaTimestampObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaVoidObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.TimestampObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.VoidObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableBinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableBooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantBinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantBooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantDateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantDoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantFloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantHiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantHiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantHiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantHiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantHiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantIntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantLongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableConstantTimestampObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableDateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableDoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableFloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableHiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableIntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableLongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableTimestampObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableVoidObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.FoldableUnevaluable;
import org.apache.spark.sql.catalyst.expressions.GenericInternalRow;
import org.apache.spark.sql.catalyst.expressions.Literal;
import org.apache.spark.sql.catalyst.util.ArrayBasedMapData;
import org.apache.spark.sql.catalyst.util.ArrayData;
import org.apache.spark.sql.catalyst.util.GenericArrayData;
import org.apache.spark.sql.catalyst.util.MapData;
import org.apache.spark.sql.execution.datasources.DaysWritable;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DayTimeIntervalType;
import org.apache.spark.sql.types.Decimal;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.sql.types.YearMonthIntervalType;
import org.apache.spark.sql.types.DoubleType.;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Array;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\r%a\u0001\u0003\u001a4!\u0003\r\taM\u001f\t\u000b\u0011\u0003A\u0011\u0001$\t\u000b)\u0003A\u0011A&\t\u000by\u0003A\u0011B0\t\u000by\u0004A\u0011B@\t\u000f\u0005-\u0001\u0001\"\u0005\u0002\u000e!9\u0011Q\u0006\u0001\u0005\u0002\u0005=\u0002bBA\u0017\u0001\u0011\u0005\u0011Q\u0007\u0005\b\u00033\u0002A\u0011AA.\u0011\u001d\tI\u0006\u0001C\u0001\u0003KBq!!\u0017\u0001\t\u0003\t\t\tC\u0004\u0002\u001c\u0002!\t!!(\t\u000f\u0005m\u0005\u0001\"\u0001\u0002\"\"9\u00111\u0017\u0001\u0005\u0002\u0005U\u0006bBA^\u0001\u0011%\u0011Q\u0018\u0005\b\u0003\u001b\u0004A\u0011BAh\u0011\u001d\t)\u000e\u0001C\u0005\u0003/Dq!a7\u0001\t\u0013\ti\u000eC\u0004\u0002b\u0002!I!a9\t\u000f\u0005\u001d\b\u0001\"\u0003\u0002j\"9\u0011Q\u001e\u0001\u0005\n\u0005=\bbBAz\u0001\u0011%\u0011Q\u001f\u0005\b\u0003s\u0004A\u0011BA~\u0011\u001d\ty\u0010\u0001C\u0005\u0005\u0003AqA!\u0002\u0001\t\u0013\u00119\u0001C\u0004\u0003\f\u0001!IA!\u0004\t\u000f\tE\u0001\u0001\"\u0003\u0003\u0014!9!q\u0003\u0001\u0005\n\te\u0001b\u0002B\u000e\u0001\u0011%!\u0011\u0004\u0005\b\u0005;\u0001A\u0011\u0002B\r\u0011\u001d\u0011y\u0002\u0001C\u0005\u0005CAqA!\r\u0001\t\u0013\u0011\u0019\u0004C\u0004\u0003>\u0001!IAa\u0010\t\u000f\t5\u0003\u0001\"\u0003\u0003P!9!\u0011\f\u0001\u0005\n\tm\u0003b\u0002B3\u0001\u0011%!q\r\u0005\b\u0005c\u0002A\u0011\u0002B:\u0011\u001d\u0011i\b\u0001C\u0005\u0005\u007fBqA!#\u0001\t\u0013\u0011Y\tC\u0004\u0003\u0016\u0002!IAa&\t\u000f\t-\u0006\u0001\"\u0003\u0003.\"9!q\u0017\u0001\u0005\n\te\u0006b\u0002Bb\u0001\u0011%!Q\u0019\u0005\b\u0005\u001f\u0004A\u0011\u0002Bi\r\u0019\u0011Y\u000eA\u0001\u0003^\"I!q\u001c\u0017\u0003\u0002\u0003\u0006I\u0001\u0014\u0005\b\u0005CdC\u0011\u0001Br\u0011\u001d\u0011Y\u000f\fC\u0005\u0005[DqAa@-\t\u0003\u0019\t\u0001C\u0005\u0004\u0004\u0001\t\t\u0011b\u0001\u0004\u0006\tq\u0001*\u001b<f\u0013:\u001c\b/Z2u_J\u001c(B\u0001\u001b6\u0003\u0011A\u0017N^3\u000b\u0005Y:\u0014aA:rY*\u0011\u0001(O\u0001\u0006gB\f'o\u001b\u0006\u0003um\na!\u00199bG\",'\"\u0001\u001f\u0002\u0007=\u0014xm\u0005\u0002\u0001}A\u0011qHQ\u0007\u0002\u0001*\t\u0011)A\u0003tG\u0006d\u0017-\u0003\u0002D\u0001\n1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002\u000fB\u0011q\bS\u0005\u0003\u0013\u0002\u0013A!\u00168ji\u0006\u0011\".\u0019<b)f\u0004X\rV8ECR\fG+\u001f9f)\ta%\u000b\u0005\u0002N!6\taJ\u0003\u0002Pk\u0005)A/\u001f9fg&\u0011\u0011K\u0014\u0002\t\t\u0006$\u0018\rV=qK\")1K\u0001a\u0001)\u0006\u00191\r\u001c>\u0011\u0005UcV\"\u0001,\u000b\u0005]C\u0016a\u0002:fM2,7\r\u001e\u0006\u00033j\u000bA\u0001\\1oO*\t1,\u0001\u0003kCZ\f\u0017BA/W\u0005\u0011!\u0016\u0010]3\u0002\u0019%\u001c8+\u001e2DY\u0006\u001c8o\u00144\u0015\u0007\u0001\u001cW\r\u0005\u0002@C&\u0011!\r\u0011\u0002\b\u0005>|G.Z1o\u0011\u0015!7\u00011\u0001U\u0003\u0005!\b\"\u00024\u0004\u0001\u00049\u0017A\u00029be\u0016tG\u000f\r\u0002ikB\u0019\u0011\u000e]:\u000f\u0005)t\u0007CA6A\u001b\u0005a'BA7F\u0003\u0019a$o\\8u}%\u0011q\u000eQ\u0001\u0007!J,G-\u001a4\n\u0005E\u0014(!B\"mCN\u001c(BA8A!\t!X\u000f\u0004\u0001\u0005\u0013Y,\u0017\u0011!A\u0001\u0006\u00039(aA0%oE\u0011\u0001p\u001f\t\u0003\u007feL!A\u001f!\u0003\u000f9{G\u000f[5oOB\u0011q\b`\u0005\u0003{\u0002\u00131!\u00118z\u000319\u0018\u000e\u001e5Ok2d7+\u00194f)\u0011\t\t!a\u0002\u0011\u000b}\n\u0019a_>\n\u0007\u0005\u0015\u0001IA\u0005Gk:\u001cG/[8oc!9\u0011\u0011\u0002\u0003A\u0002\u0005\u0005\u0011!\u00014\u0002\u0015]\u0014\u0018\r\u001d9fe\u001a{'\u000f\u0006\u0004\u0002\u0002\u0005=\u0011\u0011\u0006\u0005\b\u0003#)\u0001\u0019AA\n\u0003\ty\u0017\u000e\u0005\u0003\u0002\u0016\u0005\u0015RBAA\f\u0015\u0011\tI\"a\u0007\u0002\u001f=\u0014'.Z2uS:\u001c\b/Z2u_JTA!!\b\u0002 \u000511/\u001a:eKJR1\u0001NA\u0011\u0015\r\t\u0019#O\u0001\u0007Q\u0006$wn\u001c9\n\t\u0005\u001d\u0012q\u0003\u0002\u0010\u001f\nTWm\u0019;J]N\u0004Xm\u0019;pe\"1\u00111F\u0003A\u00021\u000b\u0001\u0002Z1uCRK\b/Z\u0001\rk:<(/\u00199qKJ4uN\u001d\u000b\u0005\u0003\u0003\t\t\u0004C\u0004\u00024\u0019\u0001\r!a\u0005\u0002\u001f=\u0014'.Z2u\u0013:\u001c\b/Z2u_J$B!a\u000e\u0002PAIq(!\u000f|\u0003{\tIeR\u0005\u0004\u0003w\u0001%!\u0003$v]\u000e$\u0018n\u001c84!\u0011\ty$!\u0012\u000e\u0005\u0005\u0005#bAA\"k\u0005A1-\u0019;bYf\u001cH/\u0003\u0003\u0002H\u0005\u0005#aC%oi\u0016\u0014h.\u00197S_^\u00042aPA&\u0013\r\ti\u0005\u0011\u0002\u0004\u0013:$\bbBA)\u000f\u0001\u0007\u00111K\u0001\u0006M&,G\u000e\u001a\t\u0005\u0003+\t)&\u0003\u0003\u0002X\u0005]!aC*ueV\u001cGOR5fY\u0012\fAa\u001e:baR9a(!\u0018\u0002b\u0005\r\u0004BBA0\u0011\u0001\u000710A\u0001b\u0011\u001d\t\t\u0002\u0003a\u0001\u0003'Aa!a\u000b\t\u0001\u0004aECCA4\u0003[\n\t(a\u001e\u0002|A!q(!\u001b?\u0013\r\tY\u0007\u0011\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0003_J\u0001\u0019AA\u001f\u0003\r\u0011xn\u001e\u0005\b\u0003gJ\u0001\u0019AA;\u0003!9(/\u00199qKJ\u001c\b#B \u0002j\u0005\u0005\u0001bBA=\u0013\u0001\u0007\u0011qM\u0001\u0006G\u0006\u001c\u0007.\u001a\u0005\b\u0003{J\u0001\u0019AA@\u0003%!\u0017\r^1UsB,7\u000f\u0005\u0003@\u0003SbE\u0003CA4\u0003\u0007\u000b9*!'\t\u000f\u0005=$\u00021\u0001\u0002\u0006B)\u0011qQAIw:!\u0011\u0011RAG\u001d\rY\u00171R\u0005\u0002\u0003&\u0019\u0011q\u0012!\u0002\u000fA\f7m[1hK&!\u00111SAK\u0005\r\u0019V-\u001d\u0006\u0004\u0003\u001f\u0003\u0005bBA:\u0015\u0001\u0007\u0011Q\u000f\u0005\b\u0003sR\u0001\u0019AA4\u0003-!x.\u00138ta\u0016\u001cGo\u001c:\u0015\t\u0005M\u0011q\u0014\u0005\u0007\u0003WY\u0001\u0019\u0001'\u0015\t\u0005M\u00111\u0015\u0005\b\u0003Kc\u0001\u0019AAT\u0003\u0011)\u0007\u0010\u001d:\u0011\t\u0005%\u0016qV\u0007\u0003\u0003WSA!!,\u0002B\u0005YQ\r\u001f9sKN\u001c\u0018n\u001c8t\u0013\u0011\t\t,a+\u0003\u0015\u0015C\bO]3tg&|g.A\nj]N\u0004Xm\u0019;peR{G)\u0019;b)f\u0004X\rF\u0002M\u0003oCq!!/\u000e\u0001\u0004\t\u0019\"A\u0005j]N\u0004Xm\u0019;pe\u0006IB-Z2j[\u0006dG+\u001f9f\u0013:4w\u000eV8DCR\fG._:u)\u0011\ty,!2\u0011\u00075\u000b\t-C\u0002\u0002D:\u00131\u0002R3dS6\fG\u000eV=qK\"9\u0011\u0011\u0018\bA\u0002\u0005\u001d\u0007\u0003BA\u000b\u0003\u0013LA!a3\u0002\u0018\tA\u0002K]5nSRLg/Z(cU\u0016\u001cG/\u00138ta\u0016\u001cGo\u001c:\u0002Q\u001d,Go\u0015;sS:<wK]5uC\ndWmQ8ogR\fg\u000e^(cU\u0016\u001cG/\u00138ta\u0016\u001cGo\u001c:\u0015\t\u0005M\u0011\u0011\u001b\u0005\u0007\u0003'|\u0001\u0019A>\u0002\u000bY\fG.^3\u0002K\u001d,G/\u00138u/JLG/\u00192mK\u000e{gn\u001d;b]R|%M[3di&s7\u000f]3di>\u0014H\u0003BA\n\u00033Da!a5\u0011\u0001\u0004Y\u0018\u0001K4fi\u0012{WO\u00197f/JLG/\u00192mK\u000e{gn\u001d;b]R|%M[3di&s7\u000f]3di>\u0014H\u0003BA\n\u0003?Da!a5\u0012\u0001\u0004Y\u0018!K4fi\n{w\u000e\\3b]^\u0013\u0018\u000e^1cY\u0016\u001cuN\\:uC:$xJ\u00196fGRLen\u001d9fGR|'\u000f\u0006\u0003\u0002\u0014\u0005\u0015\bBBAj%\u0001\u000710\u0001\u0014hKRduN\\4Xe&$\u0018M\u00197f\u0007>t7\u000f^1oi>\u0013'.Z2u\u0013:\u001c\b/Z2u_J$B!a\u0005\u0002l\"1\u00111[\nA\u0002m\fqeZ3u\r2|\u0017\r^,sSR\f'\r\\3D_:\u001cH/\u00198u\u001f\nTWm\u0019;J]N\u0004Xm\u0019;peR!\u00111CAy\u0011\u0019\t\u0019\u000e\u0006a\u0001w\u00069s-\u001a;TQ>\u0014Ho\u0016:ji\u0006\u0014G.Z\"p]N$\u0018M\u001c;PE*,7\r^%ogB,7\r^8s)\u0011\t\u0019\"a>\t\r\u0005MW\u00031\u0001|\u0003\u0019:W\r\u001e\"zi\u0016<&/\u001b;bE2,7i\u001c8ti\u0006tGo\u00142kK\u000e$\u0018J\\:qK\u000e$xN\u001d\u000b\u0005\u0003'\ti\u0010\u0003\u0004\u0002TZ\u0001\ra_\u0001)O\u0016$()\u001b8bef<&/\u001b;bE2,7i\u001c8ti\u0006tGo\u00142kK\u000e$\u0018J\\:qK\u000e$xN\u001d\u000b\u0005\u0003'\u0011\u0019\u0001\u0003\u0004\u0002T^\u0001\ra_\u0001'O\u0016$H)\u0019;f/JLG/\u00192mK\u000e{gn\u001d;b]R|%M[3di&s7\u000f]3di>\u0014H\u0003BA\n\u0005\u0013Aa!a5\u0019\u0001\u0004Y\u0018aK4fiRKW.Z:uC6\u0004xK]5uC\ndWmQ8ogR\fg\u000e^(cU\u0016\u001cG/\u00138ta\u0016\u001cGo\u001c:\u0015\t\u0005M!q\u0002\u0005\u0007\u0003'L\u0002\u0019A>\u0002S\u001d,G\u000fR3dS6\fGn\u0016:ji\u0006\u0014G.Z\"p]N$\u0018M\u001c;PE*,7\r^%ogB,7\r^8s)\u0011\t\u0019B!\u0006\t\r\u0005M'\u00041\u0001|\u0003=:W\r\u001e)sS6LG/\u001b<f\u001dVdGn\u0016:ji\u0006\u0014G.Z\"p]N$\u0018M\u001c;PE*,7\r^%ogB,7\r^8s+\t\t\u0019\"A\u001bhKRD\u0015N^3J]R,'O^1m\t\u0006LH+[7f/JLG/\u00192mK\u000e{gn\u001d;b]R|%M[3di&s7\u000f]3di>\u0014\u0018aN4fi\"Kg/Z%oi\u0016\u0014h/\u00197ZK\u0006\u0014Xj\u001c8uQ^\u0013\u0018\u000e^1cY\u0016\u001cuN\\:uC:$xJ\u00196fGRLen\u001d9fGR|'/A\thKR\u001cFO]5oO^\u0013\u0018\u000e^1cY\u0016$BAa\t\u00030A!!Q\u0005B\u0016\u001b\t\u00119C\u0003\u0003\u0003*\u0005\u0005\u0012AA5p\u0013\u0011\u0011iCa\n\u0003\tQ+\u0007\u0010\u001e\u0005\u0007\u0003't\u0002\u0019A>\u0002\u001d\u001d,G/\u00138u/JLG/\u00192mKR!!Q\u0007B\u001e!\u0011\u0011)Ca\u000e\n\t\te\"q\u0005\u0002\f\u0013:$xK]5uC\ndW\r\u0003\u0004\u0002T~\u0001\ra_\u0001\u0012O\u0016$Hi\\;cY\u0016<&/\u001b;bE2,G\u0003\u0002B!\u0005\u0017\u0002BAa\u0011\u0003H5\u0011!Q\t\u0006\u0005\u0005S\tY\"\u0003\u0003\u0003J\t\u0015#A\u0004#pk\ndWm\u0016:ji\u0006\u0014G.\u001a\u0005\u0007\u0003'\u0004\u0003\u0019A>\u0002%\u001d,GOQ8pY\u0016\fgn\u0016:ji\u0006\u0014G.\u001a\u000b\u0005\u0005#\u00129\u0006\u0005\u0003\u0003&\tM\u0013\u0002\u0002B+\u0005O\u0011qBQ8pY\u0016\fgn\u0016:ji\u0006\u0014G.\u001a\u0005\u0007\u0003'\f\u0003\u0019A>\u0002\u001f\u001d,G\u000fT8oO^\u0013\u0018\u000e^1cY\u0016$BA!\u0018\u0003dA!!Q\u0005B0\u0013\u0011\u0011\tGa\n\u0003\u00191{gnZ,sSR\f'\r\\3\t\r\u0005M'\u00051\u0001|\u0003A9W\r\u001e$m_\u0006$xK]5uC\ndW\r\u0006\u0003\u0003j\t=\u0004\u0003\u0002B\u0013\u0005WJAA!\u001c\u0003(\tia\t\\8bi^\u0013\u0018\u000e^1cY\u0016Da!a5$\u0001\u0004Y\u0018\u0001E4fiNCwN\u001d;Xe&$\u0018M\u00197f)\u0011\u0011)Ha\u001f\u0011\t\t\r#qO\u0005\u0005\u0005s\u0012)EA\u0007TQ>\u0014Ho\u0016:ji\u0006\u0014G.\u001a\u0005\u0007\u0003'$\u0003\u0019A>\u0002\u001f\u001d,GOQ=uK^\u0013\u0018\u000e^1cY\u0016$BA!!\u0003\bB!!1\tBB\u0013\u0011\u0011)I!\u0012\u0003\u0019\tKH/Z,sSR\f'\r\\3\t\r\u0005MW\u00051\u0001|\u0003E9W\r\u001e\"j]\u0006\u0014\u0018p\u0016:ji\u0006\u0014G.\u001a\u000b\u0005\u0005\u001b\u0013\u0019\n\u0005\u0003\u0003&\t=\u0015\u0002\u0002BI\u0005O\u0011QBQ=uKN<&/\u001b;bE2,\u0007BBAjM\u0001\u000710A\bhKR$\u0015\r^3Xe&$\u0018M\u00197f)\u0011\u0011IJ!+\u0011\t\tm%QU\u0007\u0003\u0005;SAAa(\u0003\"\u0006YA-\u0019;bg>,(oY3t\u0015\r\u0011\u0019+N\u0001\nKb,7-\u001e;j_:LAAa*\u0003\u001e\naA)Y=t/JLG/\u00192mK\"1\u00111[\u0014A\u0002m\fAcZ3u)&lWm\u001d;b[B<&/\u001b;bE2,G\u0003\u0002BX\u0005k\u0003BAa\u0011\u00032&!!1\u0017B#\u0005E!\u0016.\\3ti\u0006l\u0007o\u0016:ji\u0006\u0014G.\u001a\u0005\u0007\u0003'D\u0003\u0019A>\u0002=\u001d,G\u000fS5wK&sG/\u001a:wC2$\u0015-\u001f+j[\u0016<&/\u001b;bE2,G\u0003\u0002B^\u0005\u0003\u0004BAa\u0011\u0003>&!!q\u0018B#\u0005mA\u0015N^3J]R,'O^1m\t\u0006LH+[7f/JLG/\u00192mK\"1\u00111[\u0015A\u0002m\f\u0001eZ3u\u0011&4X-\u00138uKJ4\u0018\r\\-fCJluN\u001c;i/JLG/\u00192mKR!!q\u0019Bg!\u0011\u0011\u0019E!3\n\t\t-'Q\t\u0002\u001e\u0011&4X-\u00138uKJ4\u0018\r\\-fCJluN\u001c;i/JLG/\u00192mK\"1\u00111\u001b\u0016A\u0002m\f!cZ3u\t\u0016\u001c\u0017.\\1m/JLG/\u00192mKR!!1\u001bBm!\u0011\u0011\u0019E!6\n\t\t]'Q\t\u0002\u0014\u0011&4X\rR3dS6\fGn\u0016:ji\u0006\u0014G.\u001a\u0005\u0007\u0003'\\\u0003\u0019A>\u0003'QL\b/Z%oM>\u001cuN\u001c<feNLwN\\:\u0014\u00051r\u0014A\u00013u\u0003\u0019a\u0014N\\5u}Q!!Q\u001dBu!\r\u00119\u000fL\u0007\u0002\u0001!1!q\u001c\u0018A\u00021\u000bq\u0002Z3dS6\fG\u000eV=qK&sgm\u001c\u000b\u0005\u0005_\u0014Y\u0010\u0005\u0003\u0003r\n]XB\u0001Bz\u0015\u0011\u0011)0a\u0007\u0002\u0011QL\b/Z5oM>LAA!?\u0003t\nAA+\u001f9f\u0013:4w\u000eC\u0004\u0003~>\u0002\r!a0\u0002\u0017\u0011,7-[7bYRK\b/Z\u0001\u000bi>$\u0016\u0010]3J]\u001a|WC\u0001Bx\u0003M!\u0018\u0010]3J]\u001a|7i\u001c8wKJ\u001c\u0018n\u001c8t)\u0011\u0011)oa\u0002\t\r\t}\u0017\u00071\u0001M\u0001"
)
public interface HiveInspectors {
   // $FF: synthetic method
   static DataType javaTypeToDataType$(final HiveInspectors $this, final Type clz) {
      return $this.javaTypeToDataType(clz);
   }

   default DataType javaTypeToDataType(final Type clz) {
      boolean var5 = false;
      Class var6 = null;
      boolean var7 = false;
      ParameterizedType var8 = null;
      if (clz instanceof Class) {
         var5 = true;
         var6 = (Class)clz;
         Class var10 = DoubleWritable.class;
         if (var6 == null) {
            if (var10 == null) {
               return .MODULE$;
            }
         } else if (var6.equals(var10)) {
            return .MODULE$;
         }
      }

      if (var5) {
         Class var11 = org.apache.hadoop.hive.serde2.io.DoubleWritable.class;
         if (var6 == null) {
            if (var11 == null) {
               return .MODULE$;
            }
         } else if (var6.equals(var11)) {
            return .MODULE$;
         }
      }

      if (var5) {
         Class var12 = HiveDecimalWritable.class;
         if (var6 == null) {
            if (var12 == null) {
               return org.apache.spark.sql.types.DecimalType..MODULE$.SYSTEM_DEFAULT();
            }
         } else if (var6.equals(var12)) {
            return org.apache.spark.sql.types.DecimalType..MODULE$.SYSTEM_DEFAULT();
         }
      }

      if (var5) {
         Class var13 = ByteWritable.class;
         if (var6 == null) {
            if (var13 == null) {
               return org.apache.spark.sql.types.ByteType..MODULE$;
            }
         } else if (var6.equals(var13)) {
            return org.apache.spark.sql.types.ByteType..MODULE$;
         }
      }

      if (var5) {
         Class var14 = ShortWritable.class;
         if (var6 == null) {
            if (var14 == null) {
               return org.apache.spark.sql.types.ShortType..MODULE$;
            }
         } else if (var6.equals(var14)) {
            return org.apache.spark.sql.types.ShortType..MODULE$;
         }
      }

      if (var5) {
         Class var15 = DateWritable.class;
         if (var6 == null) {
            if (var15 == null) {
               return org.apache.spark.sql.types.DateType..MODULE$;
            }
         } else if (var6.equals(var15)) {
            return org.apache.spark.sql.types.DateType..MODULE$;
         }
      }

      if (var5) {
         Class var16 = TimestampWritable.class;
         if (var6 == null) {
            if (var16 == null) {
               return org.apache.spark.sql.types.TimestampType..MODULE$;
            }
         } else if (var6.equals(var16)) {
            return org.apache.spark.sql.types.TimestampType..MODULE$;
         }
      }

      if (var5) {
         Class var17 = Text.class;
         if (var6 == null) {
            if (var17 == null) {
               return org.apache.spark.sql.types.StringType..MODULE$;
            }
         } else if (var6.equals(var17)) {
            return org.apache.spark.sql.types.StringType..MODULE$;
         }
      }

      if (var5) {
         Class var18 = IntWritable.class;
         if (var6 == null) {
            if (var18 == null) {
               return org.apache.spark.sql.types.IntegerType..MODULE$;
            }
         } else if (var6.equals(var18)) {
            return org.apache.spark.sql.types.IntegerType..MODULE$;
         }
      }

      if (var5) {
         Class var19 = LongWritable.class;
         if (var6 == null) {
            if (var19 == null) {
               return org.apache.spark.sql.types.LongType..MODULE$;
            }
         } else if (var6.equals(var19)) {
            return org.apache.spark.sql.types.LongType..MODULE$;
         }
      }

      if (var5) {
         Class var20 = FloatWritable.class;
         if (var6 == null) {
            if (var20 == null) {
               return org.apache.spark.sql.types.FloatType..MODULE$;
            }
         } else if (var6.equals(var20)) {
            return org.apache.spark.sql.types.FloatType..MODULE$;
         }
      }

      if (var5) {
         Class var21 = BooleanWritable.class;
         if (var6 == null) {
            if (var21 == null) {
               return org.apache.spark.sql.types.BooleanType..MODULE$;
            }
         } else if (var6.equals(var21)) {
            return org.apache.spark.sql.types.BooleanType..MODULE$;
         }
      }

      if (var5) {
         Class var22 = BytesWritable.class;
         if (var6 == null) {
            if (var22 == null) {
               return org.apache.spark.sql.types.BinaryType..MODULE$;
            }
         } else if (var6.equals(var22)) {
            return org.apache.spark.sql.types.BinaryType..MODULE$;
         }
      }

      if (var5) {
         Class var23 = String.class;
         if (var6 == null) {
            if (var23 == null) {
               return org.apache.spark.sql.types.StringType..MODULE$;
            }
         } else if (var6.equals(var23)) {
            return org.apache.spark.sql.types.StringType..MODULE$;
         }
      }

      if (var5) {
         Class var24 = Date.class;
         if (var6 == null) {
            if (var24 == null) {
               return org.apache.spark.sql.types.DateType..MODULE$;
            }
         } else if (var6.equals(var24)) {
            return org.apache.spark.sql.types.DateType..MODULE$;
         }
      }

      if (var5) {
         Class var25 = Timestamp.class;
         if (var6 == null) {
            if (var25 == null) {
               return org.apache.spark.sql.types.TimestampType..MODULE$;
            }
         } else if (var6.equals(var25)) {
            return org.apache.spark.sql.types.TimestampType..MODULE$;
         }
      }

      if (var5) {
         Class var26 = HiveDecimal.class;
         if (var6 == null) {
            if (var26 == null) {
               return org.apache.spark.sql.types.DecimalType..MODULE$.SYSTEM_DEFAULT();
            }
         } else if (var6.equals(var26)) {
            return org.apache.spark.sql.types.DecimalType..MODULE$.SYSTEM_DEFAULT();
         }
      }

      if (var5) {
         Class var27 = BigDecimal.class;
         if (var6 == null) {
            if (var27 == null) {
               return org.apache.spark.sql.types.DecimalType..MODULE$.SYSTEM_DEFAULT();
            }
         } else if (var6.equals(var27)) {
            return org.apache.spark.sql.types.DecimalType..MODULE$.SYSTEM_DEFAULT();
         }
      }

      if (var5) {
         Class var28 = byte[].class;
         if (var6 == null) {
            if (var28 == null) {
               return org.apache.spark.sql.types.BinaryType..MODULE$;
            }
         } else if (var6.equals(var28)) {
            return org.apache.spark.sql.types.BinaryType..MODULE$;
         }
      }

      if (var5) {
         Class var29 = Short.class;
         if (var6 == null) {
            if (var29 == null) {
               return org.apache.spark.sql.types.ShortType..MODULE$;
            }
         } else if (var6.equals(var29)) {
            return org.apache.spark.sql.types.ShortType..MODULE$;
         }
      }

      if (var5) {
         Class var30 = Integer.class;
         if (var6 == null) {
            if (var30 == null) {
               return org.apache.spark.sql.types.IntegerType..MODULE$;
            }
         } else if (var6.equals(var30)) {
            return org.apache.spark.sql.types.IntegerType..MODULE$;
         }
      }

      if (var5) {
         Class var31 = Long.class;
         if (var6 == null) {
            if (var31 == null) {
               return org.apache.spark.sql.types.LongType..MODULE$;
            }
         } else if (var6.equals(var31)) {
            return org.apache.spark.sql.types.LongType..MODULE$;
         }
      }

      if (var5) {
         Class var32 = Double.class;
         if (var6 == null) {
            if (var32 == null) {
               return .MODULE$;
            }
         } else if (var6.equals(var32)) {
            return .MODULE$;
         }
      }

      if (var5) {
         Class var33 = Byte.class;
         if (var6 == null) {
            if (var33 == null) {
               return org.apache.spark.sql.types.ByteType..MODULE$;
            }
         } else if (var6.equals(var33)) {
            return org.apache.spark.sql.types.ByteType..MODULE$;
         }
      }

      if (var5) {
         Class var34 = Float.class;
         if (var6 == null) {
            if (var34 == null) {
               return org.apache.spark.sql.types.FloatType..MODULE$;
            }
         } else if (var6.equals(var34)) {
            return org.apache.spark.sql.types.FloatType..MODULE$;
         }
      }

      if (var5) {
         Class var35 = Boolean.class;
         if (var6 == null) {
            if (var35 == null) {
               return org.apache.spark.sql.types.BooleanType..MODULE$;
            }
         } else if (var6.equals(var35)) {
            return org.apache.spark.sql.types.BooleanType..MODULE$;
         }
      }

      if (var5) {
         Class var36 = Short.TYPE;
         if (var6 == null) {
            if (var36 == null) {
               return org.apache.spark.sql.types.ShortType..MODULE$;
            }
         } else if (var6.equals(var36)) {
            return org.apache.spark.sql.types.ShortType..MODULE$;
         }
      }

      if (var5) {
         Class var37 = Integer.TYPE;
         if (var6 == null) {
            if (var37 == null) {
               return org.apache.spark.sql.types.IntegerType..MODULE$;
            }
         } else if (var6.equals(var37)) {
            return org.apache.spark.sql.types.IntegerType..MODULE$;
         }
      }

      if (var5) {
         Class var38 = Long.TYPE;
         if (var6 == null) {
            if (var38 == null) {
               return org.apache.spark.sql.types.LongType..MODULE$;
            }
         } else if (var6.equals(var38)) {
            return org.apache.spark.sql.types.LongType..MODULE$;
         }
      }

      if (var5) {
         Class var39 = Double.TYPE;
         if (var6 == null) {
            if (var39 == null) {
               return .MODULE$;
            }
         } else if (var6.equals(var39)) {
            return .MODULE$;
         }
      }

      if (var5) {
         Class var40 = Byte.TYPE;
         if (var6 == null) {
            if (var40 == null) {
               return org.apache.spark.sql.types.ByteType..MODULE$;
            }
         } else if (var6.equals(var40)) {
            return org.apache.spark.sql.types.ByteType..MODULE$;
         }
      }

      if (var5) {
         Class var41 = Float.TYPE;
         if (var6 == null) {
            if (var41 == null) {
               return org.apache.spark.sql.types.FloatType..MODULE$;
            }
         } else if (var6.equals(var41)) {
            return org.apache.spark.sql.types.FloatType..MODULE$;
         }
      }

      if (var5) {
         Class var42 = Boolean.TYPE;
         if (var6 == null) {
            if (var42 == null) {
               return org.apache.spark.sql.types.BooleanType..MODULE$;
            }
         } else if (var6.equals(var42)) {
            return org.apache.spark.sql.types.BooleanType..MODULE$;
         }
      }

      if (var5 && var6.isArray()) {
         return org.apache.spark.sql.types.ArrayType..MODULE$.apply(this.javaTypeToDataType(var6.getComponentType()));
      } else {
         if (var5) {
            Class var43 = Object.class;
            if (var6 == null) {
               if (var43 == null) {
                  return org.apache.spark.sql.types.NullType..MODULE$;
               }
            } else if (var6.equals(var43)) {
               return org.apache.spark.sql.types.NullType..MODULE$;
            }
         }

         if (clz instanceof ParameterizedType) {
            var7 = true;
            var8 = (ParameterizedType)clz;
            if (this.isSubClassOf(var8.getRawType(), List.class)) {
               Type[] var45 = var8.getActualTypeArguments();
               if (var45 != null) {
                  Object var46 = scala.Array..MODULE$.unapplySeq(var45);
                  if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var46) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var46)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var46), 1) == 0) {
                     Type elementType = (Type)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var46), 0);
                     return org.apache.spark.sql.types.ArrayType..MODULE$.apply(this.javaTypeToDataType(elementType));
                  }
               }

               throw new MatchError(var45);
            }
         }

         if (var7 && this.isSubClassOf(var8.getRawType(), Map.class)) {
            Type[] var49 = var8.getActualTypeArguments();
            if (var49 != null) {
               Object var50 = scala.Array..MODULE$.unapplySeq(var49);
               if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var50) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var50)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var50), 2) == 0) {
                  Type keyType = (Type)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var50), 0);
                  Type valueType = (Type)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var50), 1);
                  Tuple2 var48 = new Tuple2(keyType, valueType);
                  Type keyType = (Type)var48._1();
                  Type valueType = (Type)var48._2();
                  return org.apache.spark.sql.types.MapType..MODULE$.apply(this.javaTypeToDataType(keyType), this.javaTypeToDataType(valueType));
               }
            }

            throw new MatchError(var49);
         } else if (var5 && this.isSubClassOf(var6, List.class)) {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3090", scala.Predef..MODULE$.Map().empty());
         } else if (var5 && this.isSubClassOf(var6, Map.class)) {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3091", scala.Predef..MODULE$.Map().empty());
         } else if (clz instanceof WildcardType) {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3092", scala.Predef..MODULE$.Map().empty());
         } else {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3093", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("c"), clz.toString())}))));
         }
      }
   }

   private boolean isSubClassOf(final Type t, final Class parent) {
      if (t instanceof Class var5) {
         return parent.isAssignableFrom(var5);
      } else {
         return false;
      }
   }

   private Function1 withNullSafe(final Function1 f) {
      return (input) -> input == null ? null : f.apply(input);
   }

   // $FF: synthetic method
   static Function1 wrapperFor$(final HiveInspectors $this, final ObjectInspector oi, final DataType dataType) {
      return $this.wrapperFor(oi, dataType);
   }

   default Function1 wrapperFor(final ObjectInspector oi, final DataType dataType) {
      if (dataType instanceof UserDefinedType) {
         DataType sqlType = ((UserDefinedType)dataType).sqlType();
         return this.wrapperFor(oi, sqlType);
      } else if (oi instanceof ConstantObjectInspector) {
         ConstantObjectInspector var7 = (ConstantObjectInspector)oi;
         return (o) -> var7.getWritableConstantValue();
      } else if (oi instanceof PrimitiveObjectInspector) {
         PrimitiveObjectInspector var8 = (PrimitiveObjectInspector)oi;
         boolean var9 = false;
         Object var10 = null;
         boolean var11 = false;
         Object var12 = null;
         boolean var13 = false;
         Object var14 = null;
         boolean var15 = false;
         Object var16 = null;
         boolean var17 = false;
         Object var18 = null;
         boolean var19 = false;
         Object var20 = null;
         boolean var21 = false;
         Object var22 = null;
         boolean var23 = false;
         Object var24 = null;
         boolean var25 = false;
         Object var26 = null;
         boolean var27 = false;
         Object var28 = null;
         boolean var29 = false;
         Object var30 = null;
         boolean var31 = false;
         Object var32 = null;
         boolean var33 = false;
         Object var34 = null;
         boolean var35 = false;
         Object var36 = null;
         boolean var37 = false;
         Object var38 = null;
         boolean var39 = false;
         Object var40 = null;
         if (var8 instanceof StringObjectInspector) {
            var9 = true;
            StringObjectInspector var58 = (StringObjectInspector)var8;
            if (var8.preferWritable()) {
               return this.withNullSafe((o) -> this.getStringWritable(o));
            }
         }

         if (var9) {
            return this.withNullSafe((o) -> ((UTF8String)o).toString());
         } else {
            if (var8 instanceof IntObjectInspector) {
               var11 = true;
               IntObjectInspector var59 = (IntObjectInspector)var8;
               if (var8.preferWritable()) {
                  return this.withNullSafe((o) -> this.getIntWritable(o));
               }
            }

            if (var11) {
               return this.withNullSafe((o) -> (Integer)o);
            } else {
               if (var8 instanceof BooleanObjectInspector) {
                  var13 = true;
                  BooleanObjectInspector var60 = (BooleanObjectInspector)var8;
                  if (var8.preferWritable()) {
                     return this.withNullSafe((o) -> this.getBooleanWritable(o));
                  }
               }

               if (var13) {
                  return this.withNullSafe((o) -> (Boolean)o);
               } else {
                  if (var8 instanceof FloatObjectInspector) {
                     var15 = true;
                     FloatObjectInspector var61 = (FloatObjectInspector)var8;
                     if (var8.preferWritable()) {
                        return this.withNullSafe((o) -> this.getFloatWritable(o));
                     }
                  }

                  if (var15) {
                     return this.withNullSafe((o) -> (Float)o);
                  } else {
                     if (var8 instanceof DoubleObjectInspector) {
                        var17 = true;
                        DoubleObjectInspector var62 = (DoubleObjectInspector)var8;
                        if (var8.preferWritable()) {
                           return this.withNullSafe((o) -> this.getDoubleWritable(o));
                        }
                     }

                     if (var17) {
                        return this.withNullSafe((o) -> (Double)o);
                     } else {
                        if (var8 instanceof LongObjectInspector) {
                           var19 = true;
                           LongObjectInspector var63 = (LongObjectInspector)var8;
                           if (var8.preferWritable()) {
                              return this.withNullSafe((o) -> this.getLongWritable(o));
                           }
                        }

                        if (var19) {
                           return this.withNullSafe((o) -> (Long)o);
                        } else {
                           if (var8 instanceof ShortObjectInspector) {
                              var21 = true;
                              ShortObjectInspector var64 = (ShortObjectInspector)var8;
                              if (var8.preferWritable()) {
                                 return this.withNullSafe((o) -> this.getShortWritable(o));
                              }
                           }

                           if (var21) {
                              return this.withNullSafe((o) -> (Short)o);
                           } else {
                              if (var8 instanceof ByteObjectInspector) {
                                 var23 = true;
                                 ByteObjectInspector var65 = (ByteObjectInspector)var8;
                                 if (var8.preferWritable()) {
                                    return this.withNullSafe((o) -> this.getByteWritable(o));
                                 }
                              }

                              if (var23) {
                                 return this.withNullSafe((o) -> (Byte)o);
                              } else {
                                 if (var8 instanceof HiveVarcharObjectInspector) {
                                    var25 = true;
                                    HiveVarcharObjectInspector var66 = (HiveVarcharObjectInspector)var8;
                                    if (var8.preferWritable()) {
                                       return this.withNullSafe((o) -> this.getStringWritable(o));
                                    }
                                 }

                                 if (var25) {
                                    return this.withNullSafe((o) -> {
                                       String s = ((UTF8String)o).toString();
                                       return new HiveVarchar(s, s.length());
                                    });
                                 } else {
                                    if (var8 instanceof HiveCharObjectInspector) {
                                       var27 = true;
                                       HiveCharObjectInspector var67 = (HiveCharObjectInspector)var8;
                                       if (var8.preferWritable()) {
                                          return this.withNullSafe((o) -> this.getStringWritable(o));
                                       }
                                    }

                                    if (var27) {
                                       return this.withNullSafe((o) -> {
                                          String s = ((UTF8String)o).toString();
                                          return new HiveChar(s, s.length());
                                       });
                                    } else if (var8 instanceof JavaHiveDecimalObjectInspector) {
                                       return this.withNullSafe((o) -> HiveDecimal.create(((Decimal)o).toJavaBigDecimal()));
                                    } else if (var8 instanceof JavaDateObjectInspector) {
                                       return this.withNullSafe((o) -> org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.toJavaDate(BoxesRunTime.unboxToInt(o)));
                                    } else if (var8 instanceof JavaTimestampObjectInspector) {
                                       return this.withNullSafe((o) -> org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.toJavaTimestamp(BoxesRunTime.unboxToLong(o)));
                                    } else {
                                       if (var8 instanceof HiveDecimalObjectInspector) {
                                          var29 = true;
                                          HiveDecimalObjectInspector var68 = (HiveDecimalObjectInspector)var8;
                                          if (var8.preferWritable()) {
                                             return this.withNullSafe((o) -> this.getDecimalWritable((Decimal)o));
                                          }
                                       }

                                       if (var29) {
                                          return this.withNullSafe((o) -> HiveDecimal.create(((Decimal)o).toJavaBigDecimal()));
                                       } else {
                                          if (var8 instanceof BinaryObjectInspector) {
                                             var31 = true;
                                             BinaryObjectInspector var69 = (BinaryObjectInspector)var8;
                                             if (var8.preferWritable()) {
                                                return this.withNullSafe((o) -> this.getBinaryWritable(o));
                                             }
                                          }

                                          if (var31) {
                                             return this.withNullSafe((o) -> (byte[])o);
                                          } else {
                                             if (var8 instanceof DateObjectInspector) {
                                                var33 = true;
                                                DateObjectInspector var70 = (DateObjectInspector)var8;
                                                if (var8.preferWritable()) {
                                                   return this.withNullSafe((o) -> this.getDateWritable(o));
                                                }
                                             }

                                             if (var33) {
                                                return this.withNullSafe((o) -> org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.toJavaDate(BoxesRunTime.unboxToInt(o)));
                                             } else {
                                                if (var8 instanceof TimestampObjectInspector) {
                                                   var35 = true;
                                                   TimestampObjectInspector var71 = (TimestampObjectInspector)var8;
                                                   if (var8.preferWritable()) {
                                                      return this.withNullSafe((o) -> this.getTimestampWritable(o));
                                                   }
                                                }

                                                if (var35) {
                                                   return this.withNullSafe((o) -> org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.toJavaTimestamp(BoxesRunTime.unboxToLong(o)));
                                                } else {
                                                   if (var8 instanceof HiveIntervalDayTimeObjectInspector) {
                                                      var37 = true;
                                                      HiveIntervalDayTimeObjectInspector var72 = (HiveIntervalDayTimeObjectInspector)var8;
                                                      if (var8.preferWritable()) {
                                                         return this.withNullSafe((o) -> this.getHiveIntervalDayTimeWritable(o));
                                                      }
                                                   }

                                                   if (var37) {
                                                      return this.withNullSafe((o) -> {
                                                         Duration duration = org.apache.spark.sql.catalyst.util.IntervalUtils..MODULE$.microsToDuration(BoxesRunTime.unboxToLong(o));
                                                         return new HiveIntervalDayTime(duration.getSeconds(), duration.getNano());
                                                      });
                                                   } else {
                                                      if (var8 instanceof HiveIntervalYearMonthObjectInspector) {
                                                         var39 = true;
                                                         HiveIntervalYearMonthObjectInspector var73 = (HiveIntervalYearMonthObjectInspector)var8;
                                                         if (var8.preferWritable()) {
                                                            return this.withNullSafe((o) -> this.getHiveIntervalYearMonthWritable(o));
                                                         }
                                                      }

                                                      if (var39) {
                                                         return this.withNullSafe((o) -> new HiveIntervalYearMonth(BoxesRunTime.unboxToInt(o)));
                                                      } else if (var8 instanceof VoidObjectInspector) {
                                                         return (x$2) -> null;
                                                      } else {
                                                         throw new MatchError(var8);
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      } else if (oi instanceof StandardStructObjectInspector) {
         StandardStructObjectInspector var42 = (StandardStructObjectInspector)oi;
         StructType schema = (StructType)dataType;
         Buffer wrappers = (Buffer)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var42.getAllStructFieldRefs()).asScala().zip(scala.Predef..MODULE$.wrapRefArray((Object[])schema.fields()))).map((x0$1) -> {
            if (x0$1 != null) {
               StructField ref = (StructField)x0$1._1();
               org.apache.spark.sql.types.StructField field = (org.apache.spark.sql.types.StructField)x0$1._2();
               return this.wrapperFor(ref.getFieldObjectInspector(), field.dataType());
            } else {
               throw new MatchError(x0$1);
            }
         });
         return this.withNullSafe((o) -> {
            Object struct = var42.create();
            InternalRow row = (InternalRow)o;
            ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var42.getAllStructFieldRefs()).asScala().zip(wrappers)).zipWithIndex()).foreach((x0$2) -> {
               if (x0$2 != null) {
                  Tuple2 var7 = (Tuple2)x0$2._1();
                  int i = x0$2._2$mcI$sp();
                  if (var7 != null) {
                     StructField field = (StructField)var7._1();
                     Function1 wrapper = (Function1)var7._2();
                     return var42.setStructFieldData(struct, field, wrapper.apply(row.get(i, schema.apply(i).dataType())));
                  }
               }

               throw new MatchError(x0$2);
            });
            return struct;
         });
      } else if (oi instanceof SettableStructObjectInspector) {
         SettableStructObjectInspector var45 = (SettableStructObjectInspector)oi;
         StructType structType = (StructType)dataType;
         Buffer wrappers = (Buffer)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var45.getAllStructFieldRefs()).asScala().zip(structType)).map((x0$3) -> {
            if (x0$3 != null) {
               StructField ref = (StructField)x0$3._1();
               org.apache.spark.sql.types.StructField tpe = (org.apache.spark.sql.types.StructField)x0$3._2();
               return this.wrapperFor(ref.getFieldObjectInspector(), tpe.dataType());
            } else {
               throw new MatchError(x0$3);
            }
         });
         return this.withNullSafe((o) -> {
            InternalRow row = (InternalRow)o;
            Object result = var45.create();
            ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var45.getAllStructFieldRefs()).asScala().zip(wrappers)).zipWithIndex()).foreach((x0$4) -> {
               if (x0$4 != null) {
                  Tuple2 var7 = (Tuple2)x0$4._1();
                  int i = x0$4._2$mcI$sp();
                  if (var7 != null) {
                     StructField field = (StructField)var7._1();
                     Function1 wrapper = (Function1)var7._2();
                     DataType tpe = structType.apply(i).dataType();
                     return var45.setStructFieldData(result, field, wrapper.apply(row.get(i, tpe)));
                  }
               }

               throw new MatchError(x0$4);
            });
            return result;
         });
      } else if (oi instanceof StructObjectInspector) {
         StructObjectInspector var48 = (StructObjectInspector)oi;
         StructType structType = (StructType)dataType;
         Buffer wrappers = (Buffer)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var48.getAllStructFieldRefs()).asScala().zip(structType)).map((x0$5) -> {
            if (x0$5 != null) {
               StructField ref = (StructField)x0$5._1();
               org.apache.spark.sql.types.StructField tpe = (org.apache.spark.sql.types.StructField)x0$5._2();
               return this.wrapperFor(ref.getFieldObjectInspector(), tpe.dataType());
            } else {
               throw new MatchError(x0$5);
            }
         });
         return this.withNullSafe((o) -> {
            InternalRow row = (InternalRow)o;
            ArrayList result = new ArrayList(wrappers.size());
            ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var48.getAllStructFieldRefs()).asScala().zip(wrappers)).zipWithIndex()).foreach((x0$6) -> BoxesRunTime.boxToBoolean($anonfun$wrapperFor$46(structType, result, row, x0$6)));
            return result;
         });
      } else if (oi instanceof ListObjectInspector) {
         ListObjectInspector var51 = (ListObjectInspector)oi;
         DataType elementType = ((ArrayType)dataType).elementType();
         Function1 wrapper = this.wrapperFor(var51.getListElementObjectInspector(), elementType);
         return this.withNullSafe((o) -> {
            ArrayData array = (ArrayData)o;
            ArrayList values = new ArrayList(array.numElements());
            array.foreach(elementType, (x$3, e) -> {
               $anonfun$wrapperFor$48(values, wrapper, BoxesRunTime.unboxToInt(x$3), e);
               return BoxedUnit.UNIT;
            });
            return values;
         });
      } else if (oi instanceof MapObjectInspector) {
         MapObjectInspector var54 = (MapObjectInspector)oi;
         MapType mt = (MapType)dataType;
         Function1 keyWrapper = this.wrapperFor(var54.getMapKeyObjectInspector(), mt.keyType());
         Function1 valueWrapper = this.wrapperFor(var54.getMapValueObjectInspector(), mt.valueType());
         return this.withNullSafe((o) -> {
            MapData map = (MapData)o;
            HashMap jmap = new HashMap(map.numElements());
            map.foreach(mt.keyType(), mt.valueType(), (k, v) -> {
               $anonfun$wrapperFor$50(jmap, keyWrapper, valueWrapper, k, v);
               return BoxedUnit.UNIT;
            });
            return jmap;
         });
      } else {
         return (x) -> scala.Predef..MODULE$.identity(x);
      }
   }

   // $FF: synthetic method
   static Function1 unwrapperFor$(final HiveInspectors $this, final ObjectInspector objectInspector) {
      return $this.unwrapperFor(objectInspector);
   }

   default Function1 unwrapperFor(final ObjectInspector objectInspector) {
      if (objectInspector instanceof ConstantObjectInspector var5) {
         if (var5.getWritableConstantValue() == null) {
            return (x$4) -> null;
         }
      }

      if (objectInspector instanceof WritableConstantStringObjectInspector var6) {
         UTF8String constant = UTF8String.fromString(var6.getWritableConstantValue().toString());
         return (x$5) -> constant;
      } else if (objectInspector instanceof WritableConstantHiveVarcharObjectInspector var8) {
         UTF8String constant = UTF8String.fromString(var8.getWritableConstantValue().getHiveVarchar().getValue());
         return (x$6) -> constant;
      } else if (objectInspector instanceof WritableConstantHiveCharObjectInspector var10) {
         UTF8String constant = UTF8String.fromString(var10.getWritableConstantValue().getHiveChar().getValue());
         return (x$7) -> constant;
      } else if (objectInspector instanceof WritableConstantHiveDecimalObjectInspector var12) {
         Decimal constant = HiveShim$.MODULE$.toCatalystDecimal(PrimitiveObjectInspectorFactory.javaHiveDecimalObjectInspector, var12.getWritableConstantValue().getHiveDecimal());
         return (x$8) -> constant;
      } else if (objectInspector instanceof WritableConstantTimestampObjectInspector var14) {
         TimestampWritable t = var14.getWritableConstantValue();
         long constant = org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.fromJavaTimestamp(t.getTimestamp());
         return (x$9) -> BoxesRunTime.boxToLong($anonfun$unwrapperFor$6(constant, x$9));
      } else if (objectInspector instanceof WritableConstantIntObjectInspector var18) {
         int constant = var18.getWritableConstantValue().get();
         return (x$10) -> BoxesRunTime.boxToInteger($anonfun$unwrapperFor$7(constant, x$10));
      } else if (objectInspector instanceof WritableConstantDoubleObjectInspector var20) {
         double constant = var20.getWritableConstantValue().get();
         return (x$11) -> BoxesRunTime.boxToDouble($anonfun$unwrapperFor$8(constant, x$11));
      } else if (objectInspector instanceof WritableConstantBooleanObjectInspector var23) {
         boolean constant = var23.getWritableConstantValue().get();
         return (x$12) -> BoxesRunTime.boxToBoolean($anonfun$unwrapperFor$9(constant, x$12));
      } else if (objectInspector instanceof WritableConstantLongObjectInspector var25) {
         long constant = var25.getWritableConstantValue().get();
         return (x$13) -> BoxesRunTime.boxToLong($anonfun$unwrapperFor$10(constant, x$13));
      } else if (objectInspector instanceof WritableConstantFloatObjectInspector var28) {
         float constant = var28.getWritableConstantValue().get();
         return (x$14) -> BoxesRunTime.boxToFloat($anonfun$unwrapperFor$11(constant, x$14));
      } else if (objectInspector instanceof WritableConstantShortObjectInspector var30) {
         short constant = var30.getWritableConstantValue().get();
         return (x$15) -> BoxesRunTime.boxToShort($anonfun$unwrapperFor$12(constant, x$15));
      } else if (objectInspector instanceof WritableConstantByteObjectInspector var32) {
         byte constant = var32.getWritableConstantValue().get();
         return (x$16) -> BoxesRunTime.boxToByte($anonfun$unwrapperFor$13(constant, x$16));
      } else if (objectInspector instanceof WritableConstantBinaryObjectInspector var34) {
         BytesWritable writable = var34.getWritableConstantValue();
         byte[] constant = new byte[writable.getLength()];
         System.arraycopy(writable.getBytes(), 0, constant, 0, constant.length);
         return (x$17) -> constant;
      } else if (objectInspector instanceof WritableConstantDateObjectInspector var37) {
         int constant = org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.fromJavaDate(var37.getWritableConstantValue().get());
         return (x$18) -> BoxesRunTime.boxToInteger($anonfun$unwrapperFor$15(constant, x$18));
      } else if (objectInspector instanceof StandardConstantMapObjectInspector var39) {
         Function1 keyUnwrapper = this.unwrapperFor(var39.getMapKeyObjectInspector());
         Function1 valueUnwrapper = this.unwrapperFor(var39.getMapValueObjectInspector());
         Map keyValues = var39.getWritableConstantValue();
         ArrayBasedMapData constant = org.apache.spark.sql.catalyst.util.ArrayBasedMapData..MODULE$.apply(keyValues, keyUnwrapper, valueUnwrapper);
         return (x$19) -> constant;
      } else if (objectInspector instanceof StandardConstantListObjectInspector var44) {
         Function1 unwrapper = this.unwrapperFor(var44.getListElementObjectInspector());
         Object[] values = ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var44.getWritableConstantValue()).asScala().map(unwrapper)).toArray(scala.reflect.ClassTag..MODULE$.Any());
         GenericArrayData constant = new GenericArrayData(values);
         return (x$20) -> constant;
      } else if (objectInspector instanceof VoidObjectInspector) {
         return (x$21) -> null;
      } else if (objectInspector instanceof WritableConstantHiveIntervalDayTimeObjectInspector) {
         WritableConstantHiveIntervalDayTimeObjectInspector var48 = (WritableConstantHiveIntervalDayTimeObjectInspector)objectInspector;
         HiveIntervalDayTime constant = (HiveIntervalDayTime)var48.getWritableConstantValue();
         return (x$22) -> BoxesRunTime.boxToLong($anonfun$unwrapperFor$19(constant, x$22));
      } else if (objectInspector instanceof WritableConstantHiveIntervalYearMonthObjectInspector) {
         WritableConstantHiveIntervalYearMonthObjectInspector var50 = (WritableConstantHiveIntervalYearMonthObjectInspector)objectInspector;
         HiveIntervalYearMonth constant = (HiveIntervalYearMonth)var50.getWritableConstantValue();
         return (x$23) -> BoxesRunTime.boxToInteger($anonfun$unwrapperFor$20(constant, x$23));
      } else if (objectInspector instanceof PrimitiveObjectInspector) {
         PrimitiveObjectInspector var52 = (PrimitiveObjectInspector)objectInspector;
         boolean var53 = false;
         HiveVarcharObjectInspector var54 = null;
         boolean var55 = false;
         HiveCharObjectInspector var56 = null;
         boolean var57 = false;
         StringObjectInspector var58 = null;
         boolean var59 = false;
         DateObjectInspector var60 = null;
         boolean var61 = false;
         TimestampObjectInspector var62 = null;
         boolean var63 = false;
         HiveIntervalDayTimeObjectInspector var64 = null;
         boolean var65 = false;
         HiveIntervalYearMonthObjectInspector var66 = null;
         if (var52 instanceof HiveVarcharObjectInspector) {
            var53 = true;
            var54 = (HiveVarcharObjectInspector)var52;
            if (var54.preferWritable()) {
               return (data) -> data != null ? UTF8String.fromString(var54.getPrimitiveWritableObject(data).getHiveVarchar().getValue()) : null;
            }
         }

         if (var53) {
            return (data) -> data != null ? UTF8String.fromString(var54.getPrimitiveJavaObject(data).getValue()) : null;
         } else {
            if (var52 instanceof HiveCharObjectInspector) {
               var55 = true;
               var56 = (HiveCharObjectInspector)var52;
               if (var56.preferWritable()) {
                  return (data) -> data != null ? UTF8String.fromString(var56.getPrimitiveWritableObject(data).getHiveChar().getValue()) : null;
               }
            }

            if (var55) {
               return (data) -> data != null ? UTF8String.fromString(var56.getPrimitiveJavaObject(data).getValue()) : null;
            } else {
               if (var52 instanceof StringObjectInspector) {
                  var57 = true;
                  var58 = (StringObjectInspector)var52;
                  if (var58.preferWritable()) {
                     return (data) -> {
                        if (data != null) {
                           Text wObj = var58.getPrimitiveWritableObject(data);
                           byte[] result = wObj.copyBytes();
                           return UTF8String.fromBytes(result, 0, result.length);
                        } else {
                           return null;
                        }
                     };
                  }
               }

               if (var57) {
                  return (data) -> data != null ? UTF8String.fromString(var58.getPrimitiveJavaObject(data)) : null;
               } else {
                  if (var52 instanceof IntObjectInspector) {
                     IntObjectInspector var68 = (IntObjectInspector)var52;
                     if (var68.preferWritable()) {
                        return (data) -> data != null ? BoxesRunTime.boxToInteger(var68.get(data)) : null;
                     }
                  }

                  if (var52 instanceof BooleanObjectInspector) {
                     BooleanObjectInspector var69 = (BooleanObjectInspector)var52;
                     if (var69.preferWritable()) {
                        return (data) -> data != null ? BoxesRunTime.boxToBoolean(var69.get(data)) : null;
                     }
                  }

                  if (var52 instanceof FloatObjectInspector) {
                     FloatObjectInspector var70 = (FloatObjectInspector)var52;
                     if (var70.preferWritable()) {
                        return (data) -> data != null ? BoxesRunTime.boxToFloat(var70.get(data)) : null;
                     }
                  }

                  if (var52 instanceof DoubleObjectInspector) {
                     DoubleObjectInspector var71 = (DoubleObjectInspector)var52;
                     if (var71.preferWritable()) {
                        return (data) -> data != null ? BoxesRunTime.boxToDouble(var71.get(data)) : null;
                     }
                  }

                  if (var52 instanceof LongObjectInspector) {
                     LongObjectInspector var72 = (LongObjectInspector)var52;
                     if (var72.preferWritable()) {
                        return (data) -> data != null ? BoxesRunTime.boxToLong(var72.get(data)) : null;
                     }
                  }

                  if (var52 instanceof ShortObjectInspector) {
                     ShortObjectInspector var73 = (ShortObjectInspector)var52;
                     if (var73.preferWritable()) {
                        return (data) -> data != null ? BoxesRunTime.boxToShort(var73.get(data)) : null;
                     }
                  }

                  if (var52 instanceof ByteObjectInspector) {
                     ByteObjectInspector var74 = (ByteObjectInspector)var52;
                     if (var74.preferWritable()) {
                        return (data) -> data != null ? BoxesRunTime.boxToByte(var74.get(data)) : null;
                     }
                  }

                  if (var52 instanceof HiveDecimalObjectInspector) {
                     HiveDecimalObjectInspector var75 = (HiveDecimalObjectInspector)var52;
                     return (data) -> data != null ? HiveShim$.MODULE$.toCatalystDecimal(var75, data) : null;
                  } else {
                     if (var52 instanceof BinaryObjectInspector) {
                        BinaryObjectInspector var76 = (BinaryObjectInspector)var52;
                        if (var76.preferWritable()) {
                           return (data) -> data != null ? var76.getPrimitiveWritableObject(data).copyBytes() : null;
                        }
                     }

                     if (var52 instanceof DateObjectInspector) {
                        var59 = true;
                        var60 = (DateObjectInspector)var52;
                        if (var60.preferWritable()) {
                           return (data) -> data != null ? BoxesRunTime.boxToInteger((new DaysWritable(var60.getPrimitiveWritableObject(data))).gregorianDays()) : null;
                        }
                     }

                     if (var59) {
                        return (data) -> data != null ? BoxesRunTime.boxToInteger(org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.fromJavaDate(var60.getPrimitiveJavaObject(data))) : null;
                     } else {
                        if (var52 instanceof TimestampObjectInspector) {
                           var61 = true;
                           var62 = (TimestampObjectInspector)var52;
                           if (var62.preferWritable()) {
                              return (data) -> data != null ? BoxesRunTime.boxToLong(org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.fromJavaTimestamp(var62.getPrimitiveWritableObject(data).getTimestamp())) : null;
                           }
                        }

                        if (var61) {
                           return (data) -> data != null ? BoxesRunTime.boxToLong(org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.fromJavaTimestamp(var62.getPrimitiveJavaObject(data))) : null;
                        } else {
                           if (var52 instanceof HiveIntervalDayTimeObjectInspector) {
                              var63 = true;
                              var64 = (HiveIntervalDayTimeObjectInspector)var52;
                              if (var64.preferWritable()) {
                                 return (data) -> {
                                    if (data != null) {
                                       HiveIntervalDayTime dayTime = var64.getPrimitiveWritableObject(data).getHiveIntervalDayTime();
                                       return BoxesRunTime.boxToLong(org.apache.spark.sql.catalyst.util.IntervalUtils..MODULE$.durationToMicros(Duration.ofSeconds(dayTime.getTotalSeconds()).plusNanos((long)dayTime.getNanos())));
                                    } else {
                                       return null;
                                    }
                                 };
                              }
                           }

                           if (var63) {
                              return (data) -> {
                                 if (data != null) {
                                    HiveIntervalDayTime dayTime = var64.getPrimitiveJavaObject(data);
                                    return BoxesRunTime.boxToLong(org.apache.spark.sql.catalyst.util.IntervalUtils..MODULE$.durationToMicros(Duration.ofSeconds(dayTime.getTotalSeconds()).plusNanos((long)dayTime.getNanos())));
                                 } else {
                                    return null;
                                 }
                              };
                           } else {
                              if (var52 instanceof HiveIntervalYearMonthObjectInspector) {
                                 var65 = true;
                                 var66 = (HiveIntervalYearMonthObjectInspector)var52;
                                 if (var66.preferWritable()) {
                                    return (data) -> data != null ? BoxesRunTime.boxToInteger(var66.getPrimitiveWritableObject(data).getHiveIntervalYearMonth().getTotalMonths()) : null;
                                 }
                              }

                              return var65 ? (data) -> data != null ? BoxesRunTime.boxToInteger(var66.getPrimitiveJavaObject(data).getTotalMonths()) : null : (data) -> data != null ? var52.getPrimitiveJavaObject(data) : null;
                           }
                        }
                     }
                  }
               }
            }
         }
      } else if (objectInspector instanceof ListObjectInspector) {
         ListObjectInspector var77 = (ListObjectInspector)objectInspector;
         Function1 unwrapper = this.unwrapperFor(var77.getListElementObjectInspector());
         return (data) -> data != null ? scala.Option..MODULE$.apply(var77.getList(data)).map((l) -> {
               Object[] values = ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(l).asScala().map(unwrapper)).toArray(scala.reflect.ClassTag..MODULE$.Any());
               return new GenericArrayData(values);
            }).orNull(scala..less.colon.less..MODULE$.refl()) : null;
      } else if (objectInspector instanceof MapObjectInspector) {
         MapObjectInspector var79 = (MapObjectInspector)objectInspector;
         Function1 keyUnwrapper = this.unwrapperFor(var79.getMapKeyObjectInspector());
         Function1 valueUnwrapper = this.unwrapperFor(var79.getMapValueObjectInspector());
         return (data) -> {
            if (data != null) {
               Map map = var79.getMap(data);
               return map == null ? null : org.apache.spark.sql.catalyst.util.ArrayBasedMapData..MODULE$.apply(map, keyUnwrapper, valueUnwrapper);
            } else {
               return null;
            }
         };
      } else if (objectInspector instanceof StructObjectInspector) {
         StructObjectInspector var82 = (StructObjectInspector)objectInspector;
         Buffer fields = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var82.getAllStructFieldRefs()).asScala();
         Buffer unwrappers = (Buffer)fields.map((field) -> {
            Function1 unwrapper = this.unwrapperFor(field.getFieldObjectInspector());
            return (data) -> unwrapper.apply(var82.getStructFieldData(data, field));
         });
         return (data) -> data != null ? new GenericInternalRow(((IterableOnceOps)unwrappers.map((x$24) -> x$24.apply(data))).toArray(scala.reflect.ClassTag..MODULE$.Any())) : null;
      } else {
         throw new MatchError(objectInspector);
      }
   }

   // $FF: synthetic method
   static Function3 unwrapperFor$(final HiveInspectors $this, final StructField field) {
      return $this.unwrapperFor(field);
   }

   default Function3 unwrapperFor(final StructField field) {
      ObjectInspector var3 = field.getFieldObjectInspector();
      if (var3 instanceof BooleanObjectInspector var4) {
         return (value, row, ordinal) -> {
            $anonfun$unwrapperFor$52(var4, value, row, BoxesRunTime.unboxToInt(ordinal));
            return BoxedUnit.UNIT;
         };
      } else if (var3 instanceof ByteObjectInspector var5) {
         return (value, row, ordinal) -> {
            $anonfun$unwrapperFor$53(var5, value, row, BoxesRunTime.unboxToInt(ordinal));
            return BoxedUnit.UNIT;
         };
      } else if (var3 instanceof ShortObjectInspector var6) {
         return (value, row, ordinal) -> {
            $anonfun$unwrapperFor$54(var6, value, row, BoxesRunTime.unboxToInt(ordinal));
            return BoxedUnit.UNIT;
         };
      } else if (var3 instanceof IntObjectInspector var7) {
         return (value, row, ordinal) -> {
            $anonfun$unwrapperFor$55(var7, value, row, BoxesRunTime.unboxToInt(ordinal));
            return BoxedUnit.UNIT;
         };
      } else if (var3 instanceof LongObjectInspector var8) {
         return (value, row, ordinal) -> {
            $anonfun$unwrapperFor$56(var8, value, row, BoxesRunTime.unboxToInt(ordinal));
            return BoxedUnit.UNIT;
         };
      } else if (var3 instanceof FloatObjectInspector var9) {
         return (value, row, ordinal) -> {
            $anonfun$unwrapperFor$57(var9, value, row, BoxesRunTime.unboxToInt(ordinal));
            return BoxedUnit.UNIT;
         };
      } else if (var3 instanceof DoubleObjectInspector var10) {
         return (value, row, ordinal) -> {
            $anonfun$unwrapperFor$58(var10, value, row, BoxesRunTime.unboxToInt(ordinal));
            return BoxedUnit.UNIT;
         };
      } else {
         Function1 unwrapper = this.unwrapperFor(var3);
         return (value, row, ordinal) -> {
            $anonfun$unwrapperFor$59(unwrapper, value, row, BoxesRunTime.unboxToInt(ordinal));
            return BoxedUnit.UNIT;
         };
      }
   }

   // $FF: synthetic method
   static Object wrap$(final HiveInspectors $this, final Object a, final ObjectInspector oi, final DataType dataType) {
      return $this.wrap(a, oi, dataType);
   }

   default Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return this.wrapperFor(oi, dataType).apply(a);
   }

   // $FF: synthetic method
   static Object[] wrap$(final HiveInspectors $this, final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return $this.wrap(row, wrappers, cache, dataTypes);
   }

   default Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      int i = 0;

      for(int length = wrappers.length; i < length; ++i) {
         cache[i] = wrappers[i].apply(row.get(i, dataTypes[i]));
      }

      return cache;
   }

   // $FF: synthetic method
   static Object[] wrap$(final HiveInspectors $this, final Seq row, final Function1[] wrappers, final Object[] cache) {
      return $this.wrap(row, wrappers, cache);
   }

   default Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      int i = 0;

      for(int length = wrappers.length; i < length; ++i) {
         cache[i] = wrappers[i].apply(row.apply(i));
      }

      return cache;
   }

   // $FF: synthetic method
   static ObjectInspector toInspector$(final HiveInspectors $this, final DataType dataType) {
      return $this.toInspector(dataType);
   }

   default ObjectInspector toInspector(final DataType dataType) {
      if (dataType instanceof ArrayType var4) {
         DataType tpe = var4.elementType();
         return ObjectInspectorFactory.getStandardListObjectInspector(this.toInspector(tpe));
      } else if (dataType instanceof MapType var6) {
         DataType keyType = var6.keyType();
         DataType valueType = var6.valueType();
         return ObjectInspectorFactory.getStandardMapObjectInspector(this.toInspector(keyType), this.toInspector(valueType));
      } else if (org.apache.spark.sql.types.StringType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
      } else if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
      } else if (.MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaDoubleObjectInspector;
      } else if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaBooleanObjectInspector;
      } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaLongObjectInspector;
      } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaFloatObjectInspector;
      } else if (org.apache.spark.sql.types.ShortType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaShortObjectInspector;
      } else if (org.apache.spark.sql.types.ByteType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaByteObjectInspector;
      } else if (org.apache.spark.sql.types.NullType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaVoidObjectInspector;
      } else if (org.apache.spark.sql.types.BinaryType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaByteArrayObjectInspector;
      } else if (org.apache.spark.sql.types.DateType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaDateObjectInspector;
      } else if (org.apache.spark.sql.types.TimestampType..MODULE$.equals(dataType)) {
         return PrimitiveObjectInspectorFactory.javaTimestampObjectInspector;
      } else if (dataType instanceof DayTimeIntervalType) {
         return PrimitiveObjectInspectorFactory.javaHiveIntervalDayTimeObjectInspector;
      } else if (dataType instanceof YearMonthIntervalType) {
         return PrimitiveObjectInspectorFactory.javaHiveIntervalYearMonthObjectInspector;
      } else if (dataType != null && org.apache.spark.sql.types.DecimalType..MODULE$.unapply(dataType)) {
         return PrimitiveObjectInspectorFactory.javaHiveDecimalObjectInspector;
      } else if (dataType instanceof StructType) {
         StructType var9 = (StructType)dataType;
         org.apache.spark.sql.types.StructField[] fields = var9.fields();
         return ObjectInspectorFactory.getStandardStructObjectInspector(Arrays.asList(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fields), (f) -> f.name(), scala.reflect.ClassTag..MODULE$.apply(String.class))), Arrays.asList(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fields), (f) -> this.toInspector(f.dataType()), scala.reflect.ClassTag..MODULE$.apply(ObjectInspector.class))));
      } else if (dataType instanceof UserDefinedType) {
         DataType sqlType = ((UserDefinedType)dataType).sqlType();
         return this.toInspector(sqlType);
      } else {
         throw new MatchError(dataType);
      }
   }

   // $FF: synthetic method
   static ObjectInspector toInspector$(final HiveInspectors $this, final Expression expr) {
      return $this.toInspector(expr);
   }

   default ObjectInspector toInspector(final Expression expr) {
      boolean var3 = false;
      Literal var4 = null;
      if (expr instanceof Literal) {
         var3 = true;
         var4 = (Literal)expr;
         Object value = var4.value();
         DataType var7 = var4.dataType();
         if (org.apache.spark.sql.types.StringType..MODULE$.equals(var7)) {
            return this.getStringWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var9 = var4.dataType();
         if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(var9)) {
            return this.getIntWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var11 = var4.dataType();
         if (.MODULE$.equals(var11)) {
            return this.getDoubleWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var13 = var4.dataType();
         if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(var13)) {
            return this.getBooleanWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var15 = var4.dataType();
         if (org.apache.spark.sql.types.LongType..MODULE$.equals(var15)) {
            return this.getLongWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var17 = var4.dataType();
         if (org.apache.spark.sql.types.FloatType..MODULE$.equals(var17)) {
            return this.getFloatWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var19 = var4.dataType();
         if (org.apache.spark.sql.types.ShortType..MODULE$.equals(var19)) {
            return this.getShortWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var21 = var4.dataType();
         if (org.apache.spark.sql.types.ByteType..MODULE$.equals(var21)) {
            return this.getByteWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var23 = var4.dataType();
         if (org.apache.spark.sql.types.BinaryType..MODULE$.equals(var23)) {
            return this.getBinaryWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var25 = var4.dataType();
         if (org.apache.spark.sql.types.DateType..MODULE$.equals(var25)) {
            return this.getDateWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var27 = var4.dataType();
         if (org.apache.spark.sql.types.TimestampType..MODULE$.equals(var27)) {
            return this.getTimestampWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         Object value = var4.value();
         DataType var29 = var4.dataType();
         if (var29 != null && org.apache.spark.sql.types.DecimalType..MODULE$.unapply(var29)) {
            return this.getDecimalWritableConstantObjectInspector(value);
         }
      }

      if (var3) {
         DataType var30 = var4.dataType();
         if (org.apache.spark.sql.types.NullType..MODULE$.equals(var30)) {
            return this.getPrimitiveNullWritableConstantObjectInspector();
         }
      }

      if (var3 && var4.dataType() instanceof DayTimeIntervalType) {
         return this.getHiveIntervalDayTimeWritableConstantObjectInspector();
      } else if (var3 && var4.dataType() instanceof YearMonthIntervalType) {
         return this.getHiveIntervalYearMonthWritableConstantObjectInspector();
      } else {
         if (var3) {
            Object value = var4.value();
            DataType var32 = var4.dataType();
            if (var32 instanceof ArrayType) {
               ArrayType var33 = (ArrayType)var32;
               DataType dt = var33.elementType();
               ObjectInspector listObjectInspector = this.toInspector(dt);
               if (value == null) {
                  return ObjectInspectorFactory.getStandardConstantListObjectInspector(listObjectInspector, (List)null);
               }

               ArrayList list = new ArrayList();
               ((ArrayData)value).foreach(dt, (x$25, e) -> {
                  $anonfun$toInspector$3(this, list, listObjectInspector, dt, BoxesRunTime.unboxToInt(x$25), e);
                  return BoxedUnit.UNIT;
               });
               return ObjectInspectorFactory.getStandardConstantListObjectInspector(listObjectInspector, list);
            }
         }

         if (var3) {
            Object value = var4.value();
            DataType var38 = var4.dataType();
            if (var38 instanceof MapType) {
               MapType var39 = (MapType)var38;
               DataType keyType = var39.keyType();
               DataType valueType = var39.valueType();
               ObjectInspector keyOI = this.toInspector(keyType);
               ObjectInspector valueOI = this.toInspector(valueType);
               if (value == null) {
                  return ObjectInspectorFactory.getStandardConstantMapObjectInspector(keyOI, valueOI, (Map)null);
               }

               MapData map = (MapData)value;
               HashMap jmap = new HashMap(map.numElements());
               map.foreach(keyType, valueType, (k, v) -> {
                  $anonfun$toInspector$4(this, jmap, keyOI, keyType, valueOI, valueType, k, v);
                  return BoxedUnit.UNIT;
               });
               return ObjectInspectorFactory.getStandardConstantMapObjectInspector(keyOI, valueOI, jmap);
            }
         }

         if (var3) {
            DataType dt = var4.dataType();
            if (dt instanceof StructType) {
               StructType var47 = (StructType)dt;
               return this.toInspector((DataType)var47);
            }
         }

         if (var3) {
            DataType dt = var4.dataType();
            if (dt instanceof UserDefinedType) {
               UserDefinedType var49 = (UserDefinedType)dt;
               return this.toInspector(var49.sqlType());
            }
         }

         if (var3) {
            DataType dt = var4.dataType();
            throw org.apache.spark.SparkException..MODULE$.internalError("Hive doesn't support the constant type [" + dt + "].");
         } else if (expr.collectFirst(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Object applyOrElse(final Expression x1, final Function1 default) {
               if (x1 instanceof FoldableUnevaluable var5) {
                  return var5;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Expression x1) {
               return x1 instanceof FoldableUnevaluable;
            }
         }).isDefined()) {
            return this.toInspector(expr.dataType());
         } else {
            return expr.foldable() ? this.toInspector((Expression)org.apache.spark.sql.catalyst.expressions.Literal..MODULE$.create(expr.eval(expr.eval$default$1()), expr.dataType())) : this.toInspector(expr.dataType());
         }
      }
   }

   // $FF: synthetic method
   static DataType inspectorToDataType$(final HiveInspectors $this, final ObjectInspector inspector) {
      return $this.inspectorToDataType(inspector);
   }

   default DataType inspectorToDataType(final ObjectInspector inspector) {
      if (inspector instanceof StructObjectInspector var4) {
         return new StructType((org.apache.spark.sql.types.StructField[])((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var4.getAllStructFieldRefs()).asScala().map((f) -> new org.apache.spark.sql.types.StructField(f.getFieldName(), this.inspectorToDataType(f.getFieldObjectInspector()), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()))).toArray(scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.sql.types.StructField.class)));
      } else if (inspector instanceof ListObjectInspector var5) {
         return org.apache.spark.sql.types.ArrayType..MODULE$.apply(this.inspectorToDataType(var5.getListElementObjectInspector()));
      } else if (inspector instanceof MapObjectInspector var6) {
         return org.apache.spark.sql.types.MapType..MODULE$.apply(this.inspectorToDataType(var6.getMapKeyObjectInspector()), this.inspectorToDataType(var6.getMapValueObjectInspector()));
      } else if (inspector instanceof WritableStringObjectInspector) {
         return org.apache.spark.sql.types.StringType..MODULE$;
      } else if (inspector instanceof JavaStringObjectInspector) {
         return org.apache.spark.sql.types.StringType..MODULE$;
      } else if (inspector instanceof WritableHiveVarcharObjectInspector) {
         return org.apache.spark.sql.types.StringType..MODULE$;
      } else if (inspector instanceof JavaHiveVarcharObjectInspector) {
         return org.apache.spark.sql.types.StringType..MODULE$;
      } else if (inspector instanceof WritableHiveCharObjectInspector) {
         return org.apache.spark.sql.types.StringType..MODULE$;
      } else if (inspector instanceof JavaHiveCharObjectInspector) {
         return org.apache.spark.sql.types.StringType..MODULE$;
      } else if (inspector instanceof WritableIntObjectInspector) {
         return org.apache.spark.sql.types.IntegerType..MODULE$;
      } else if (inspector instanceof JavaIntObjectInspector) {
         return org.apache.spark.sql.types.IntegerType..MODULE$;
      } else if (inspector instanceof WritableDoubleObjectInspector) {
         return .MODULE$;
      } else if (inspector instanceof JavaDoubleObjectInspector) {
         return .MODULE$;
      } else if (inspector instanceof WritableBooleanObjectInspector) {
         return org.apache.spark.sql.types.BooleanType..MODULE$;
      } else if (inspector instanceof JavaBooleanObjectInspector) {
         return org.apache.spark.sql.types.BooleanType..MODULE$;
      } else if (inspector instanceof WritableLongObjectInspector) {
         return org.apache.spark.sql.types.LongType..MODULE$;
      } else if (inspector instanceof JavaLongObjectInspector) {
         return org.apache.spark.sql.types.LongType..MODULE$;
      } else if (inspector instanceof WritableShortObjectInspector) {
         return org.apache.spark.sql.types.ShortType..MODULE$;
      } else if (inspector instanceof JavaShortObjectInspector) {
         return org.apache.spark.sql.types.ShortType..MODULE$;
      } else if (inspector instanceof WritableByteObjectInspector) {
         return org.apache.spark.sql.types.ByteType..MODULE$;
      } else if (inspector instanceof JavaByteObjectInspector) {
         return org.apache.spark.sql.types.ByteType..MODULE$;
      } else if (inspector instanceof WritableFloatObjectInspector) {
         return org.apache.spark.sql.types.FloatType..MODULE$;
      } else if (inspector instanceof JavaFloatObjectInspector) {
         return org.apache.spark.sql.types.FloatType..MODULE$;
      } else if (inspector instanceof WritableBinaryObjectInspector) {
         return org.apache.spark.sql.types.BinaryType..MODULE$;
      } else if (inspector instanceof JavaBinaryObjectInspector) {
         return org.apache.spark.sql.types.BinaryType..MODULE$;
      } else if (inspector instanceof WritableHiveDecimalObjectInspector) {
         WritableHiveDecimalObjectInspector var7 = (WritableHiveDecimalObjectInspector)inspector;
         return this.decimalTypeInfoToCatalyst(var7);
      } else if (inspector instanceof JavaHiveDecimalObjectInspector) {
         JavaHiveDecimalObjectInspector var8 = (JavaHiveDecimalObjectInspector)inspector;
         return this.decimalTypeInfoToCatalyst(var8);
      } else if (inspector instanceof WritableDateObjectInspector) {
         return org.apache.spark.sql.types.DateType..MODULE$;
      } else if (inspector instanceof JavaDateObjectInspector) {
         return org.apache.spark.sql.types.DateType..MODULE$;
      } else if (inspector instanceof WritableTimestampObjectInspector) {
         return org.apache.spark.sql.types.TimestampType..MODULE$;
      } else if (inspector instanceof JavaTimestampObjectInspector) {
         return org.apache.spark.sql.types.TimestampType..MODULE$;
      } else if (inspector instanceof WritableHiveIntervalDayTimeObjectInspector) {
         return org.apache.spark.sql.types.DayTimeIntervalType..MODULE$.apply();
      } else if (inspector instanceof JavaHiveIntervalDayTimeObjectInspector) {
         return org.apache.spark.sql.types.DayTimeIntervalType..MODULE$.apply();
      } else if (inspector instanceof WritableHiveIntervalYearMonthObjectInspector) {
         return org.apache.spark.sql.types.YearMonthIntervalType..MODULE$.apply();
      } else if (inspector instanceof JavaHiveIntervalYearMonthObjectInspector) {
         return org.apache.spark.sql.types.YearMonthIntervalType..MODULE$.apply();
      } else if (inspector instanceof WritableVoidObjectInspector) {
         return org.apache.spark.sql.types.NullType..MODULE$;
      } else if (inspector instanceof JavaVoidObjectInspector) {
         return org.apache.spark.sql.types.NullType..MODULE$;
      } else {
         throw new MatchError(inspector);
      }
   }

   private DecimalType decimalTypeInfoToCatalyst(final PrimitiveObjectInspector inspector) {
      DecimalTypeInfo info = (DecimalTypeInfo)inspector.getTypeInfo();
      return new DecimalType(info.precision(), info.scale());
   }

   private ObjectInspector getStringWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.stringTypeInfo, this.getStringWritable(value));
   }

   private ObjectInspector getIntWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.intTypeInfo, this.getIntWritable(value));
   }

   private ObjectInspector getDoubleWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.doubleTypeInfo, this.getDoubleWritable(value));
   }

   private ObjectInspector getBooleanWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.booleanTypeInfo, this.getBooleanWritable(value));
   }

   private ObjectInspector getLongWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.longTypeInfo, this.getLongWritable(value));
   }

   private ObjectInspector getFloatWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.floatTypeInfo, this.getFloatWritable(value));
   }

   private ObjectInspector getShortWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.shortTypeInfo, this.getShortWritable(value));
   }

   private ObjectInspector getByteWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.byteTypeInfo, this.getByteWritable(value));
   }

   private ObjectInspector getBinaryWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.binaryTypeInfo, this.getBinaryWritable(value));
   }

   private ObjectInspector getDateWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.dateTypeInfo, this.getDateWritable(value));
   }

   private ObjectInspector getTimestampWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.timestampTypeInfo, this.getTimestampWritable(value));
   }

   private ObjectInspector getDecimalWritableConstantObjectInspector(final Object value) {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.decimalTypeInfo, this.getDecimalWritable(value));
   }

   private ObjectInspector getPrimitiveNullWritableConstantObjectInspector() {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.voidTypeInfo, (Object)null);
   }

   private ObjectInspector getHiveIntervalDayTimeWritableConstantObjectInspector() {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.intervalDayTimeTypeInfo, (Object)null);
   }

   private ObjectInspector getHiveIntervalYearMonthWritableConstantObjectInspector() {
      return PrimitiveObjectInspectorFactory.getPrimitiveWritableConstantObjectInspector(TypeInfoFactory.intervalYearMonthTypeInfo, (Object)null);
   }

   private Text getStringWritable(final Object value) {
      return value == null ? null : new Text(((UTF8String)value).getBytes());
   }

   private IntWritable getIntWritable(final Object value) {
      return value == null ? null : new IntWritable(BoxesRunTime.unboxToInt(value));
   }

   private org.apache.hadoop.hive.serde2.io.DoubleWritable getDoubleWritable(final Object value) {
      return value == null ? null : new org.apache.hadoop.hive.serde2.io.DoubleWritable(BoxesRunTime.unboxToDouble(value));
   }

   private BooleanWritable getBooleanWritable(final Object value) {
      return value == null ? null : new BooleanWritable(BoxesRunTime.unboxToBoolean(value));
   }

   private LongWritable getLongWritable(final Object value) {
      return value == null ? null : new LongWritable(BoxesRunTime.unboxToLong(value));
   }

   private FloatWritable getFloatWritable(final Object value) {
      return value == null ? null : new FloatWritable(BoxesRunTime.unboxToFloat(value));
   }

   private ShortWritable getShortWritable(final Object value) {
      return value == null ? null : new ShortWritable(BoxesRunTime.unboxToShort(value));
   }

   private ByteWritable getByteWritable(final Object value) {
      return value == null ? null : new ByteWritable(BoxesRunTime.unboxToByte(value));
   }

   private BytesWritable getBinaryWritable(final Object value) {
      return value == null ? null : new BytesWritable((byte[])value);
   }

   private DaysWritable getDateWritable(final Object value) {
      return value == null ? null : new DaysWritable(BoxesRunTime.unboxToInt(value));
   }

   private TimestampWritable getTimestampWritable(final Object value) {
      return value == null ? null : new TimestampWritable(org.apache.spark.sql.catalyst.util.DateTimeUtils..MODULE$.toJavaTimestamp(BoxesRunTime.unboxToLong(value)));
   }

   private HiveIntervalDayTimeWritable getHiveIntervalDayTimeWritable(final Object value) {
      if (value == null) {
         return null;
      } else {
         Duration duration = org.apache.spark.sql.catalyst.util.IntervalUtils..MODULE$.microsToDuration(BoxesRunTime.unboxToLong(value));
         return new HiveIntervalDayTimeWritable(new HiveIntervalDayTime(duration.getSeconds(), duration.getNano()));
      }
   }

   private HiveIntervalYearMonthWritable getHiveIntervalYearMonthWritable(final Object value) {
      return value == null ? null : new HiveIntervalYearMonthWritable(new HiveIntervalYearMonth(BoxesRunTime.unboxToInt(value)));
   }

   private HiveDecimalWritable getDecimalWritable(final Object value) {
      return value == null ? null : new HiveDecimalWritable(HiveDecimal.create(((Decimal)value).toJavaBigDecimal()));
   }

   // $FF: synthetic method
   static typeInfoConversions typeInfoConversions$(final HiveInspectors $this, final DataType dt) {
      return $this.typeInfoConversions(dt);
   }

   default typeInfoConversions typeInfoConversions(final DataType dt) {
      return new typeInfoConversions(dt);
   }

   // $FF: synthetic method
   static boolean $anonfun$wrapperFor$46(final StructType structType$2, final ArrayList result$2, final InternalRow row$3, final Tuple2 x0$6) {
      if (x0$6 != null) {
         Tuple2 var6 = (Tuple2)x0$6._1();
         int i = x0$6._2$mcI$sp();
         if (var6 != null) {
            Function1 wrapper = (Function1)var6._2();
            DataType tpe = structType$2.apply(i).dataType();
            return result$2.add(wrapper.apply(row$3.get(i, tpe)));
         }
      }

      throw new MatchError(x0$6);
   }

   // $FF: synthetic method
   static void $anonfun$wrapperFor$48(final ArrayList values$1, final Function1 wrapper$1, final int x$3, final Object e) {
      values$1.add(wrapper$1.apply(e));
   }

   // $FF: synthetic method
   static void $anonfun$wrapperFor$50(final HashMap jmap$1, final Function1 keyWrapper$1, final Function1 valueWrapper$1, final Object k, final Object v) {
      jmap$1.put(keyWrapper$1.apply(k), valueWrapper$1.apply(v));
   }

   // $FF: synthetic method
   static long $anonfun$unwrapperFor$6(final long constant$5, final Object x$9) {
      return constant$5;
   }

   // $FF: synthetic method
   static int $anonfun$unwrapperFor$7(final int constant$6, final Object x$10) {
      return constant$6;
   }

   // $FF: synthetic method
   static double $anonfun$unwrapperFor$8(final double constant$7, final Object x$11) {
      return constant$7;
   }

   // $FF: synthetic method
   static boolean $anonfun$unwrapperFor$9(final boolean constant$8, final Object x$12) {
      return constant$8;
   }

   // $FF: synthetic method
   static long $anonfun$unwrapperFor$10(final long constant$9, final Object x$13) {
      return constant$9;
   }

   // $FF: synthetic method
   static float $anonfun$unwrapperFor$11(final float constant$10, final Object x$14) {
      return constant$10;
   }

   // $FF: synthetic method
   static short $anonfun$unwrapperFor$12(final short constant$11, final Object x$15) {
      return constant$11;
   }

   // $FF: synthetic method
   static byte $anonfun$unwrapperFor$13(final byte constant$12, final Object x$16) {
      return constant$12;
   }

   // $FF: synthetic method
   static int $anonfun$unwrapperFor$15(final int constant$14, final Object x$18) {
      return constant$14;
   }

   // $FF: synthetic method
   static long $anonfun$unwrapperFor$19(final HiveIntervalDayTime constant$17, final Object x$22) {
      return org.apache.spark.sql.catalyst.util.IntervalUtils..MODULE$.durationToMicros(Duration.ofSeconds(constant$17.getTotalSeconds()).plusNanos((long)constant$17.getNanos()));
   }

   // $FF: synthetic method
   static int $anonfun$unwrapperFor$20(final HiveIntervalYearMonth constant$18, final Object x$23) {
      return constant$18.getTotalMonths();
   }

   // $FF: synthetic method
   static void $anonfun$unwrapperFor$52(final BooleanObjectInspector x2$3, final Object value, final InternalRow row, final int ordinal) {
      row.setBoolean(ordinal, x2$3.get(value));
   }

   // $FF: synthetic method
   static void $anonfun$unwrapperFor$53(final ByteObjectInspector x3$1, final Object value, final InternalRow row, final int ordinal) {
      row.setByte(ordinal, x3$1.get(value));
   }

   // $FF: synthetic method
   static void $anonfun$unwrapperFor$54(final ShortObjectInspector x4$3, final Object value, final InternalRow row, final int ordinal) {
      row.setShort(ordinal, x4$3.get(value));
   }

   // $FF: synthetic method
   static void $anonfun$unwrapperFor$55(final IntObjectInspector x5$2, final Object value, final InternalRow row, final int ordinal) {
      row.setInt(ordinal, x5$2.get(value));
   }

   // $FF: synthetic method
   static void $anonfun$unwrapperFor$56(final LongObjectInspector x6$3, final Object value, final InternalRow row, final int ordinal) {
      row.setLong(ordinal, x6$3.get(value));
   }

   // $FF: synthetic method
   static void $anonfun$unwrapperFor$57(final FloatObjectInspector x7$1, final Object value, final InternalRow row, final int ordinal) {
      row.setFloat(ordinal, x7$1.get(value));
   }

   // $FF: synthetic method
   static void $anonfun$unwrapperFor$58(final DoubleObjectInspector x8$2, final Object value, final InternalRow row, final int ordinal) {
      row.setDouble(ordinal, x8$2.get(value));
   }

   // $FF: synthetic method
   static void $anonfun$unwrapperFor$59(final Function1 unwrapper$3, final Object value, final InternalRow row, final int ordinal) {
      row.update(ordinal, unwrapper$3.apply(value));
   }

   // $FF: synthetic method
   static void $anonfun$toInspector$3(final HiveInspectors $this, final ArrayList list$1, final ObjectInspector listObjectInspector$1, final DataType dt$1, final int x$25, final Object e) {
      list$1.add($this.wrap(e, listObjectInspector$1, dt$1));
   }

   // $FF: synthetic method
   static void $anonfun$toInspector$4(final HiveInspectors $this, final HashMap jmap$2, final ObjectInspector keyOI$1, final DataType keyType$1, final ObjectInspector valueOI$1, final DataType valueType$1, final Object k, final Object v) {
      jmap$2.put($this.wrap(k, keyOI$1, keyType$1), $this.wrap(v, valueOI$1, valueType$1));
   }

   static void $init$(final HiveInspectors $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class typeInfoConversions {
      private final DataType dt;
      // $FF: synthetic field
      public final HiveInspectors $outer;

      private TypeInfo decimalTypeInfo(final DecimalType decimalType) {
         if (decimalType != null) {
            Option var4 = org.apache.spark.sql.types.DecimalType.Fixed..MODULE$.unapply(decimalType);
            if (!var4.isEmpty()) {
               int precision = ((Tuple2)var4.get())._1$mcI$sp();
               int scale = ((Tuple2)var4.get())._2$mcI$sp();
               return new DecimalTypeInfo(precision, scale);
            }
         }

         throw new AnalysisException("_LEGACY_ERROR_TEMP_3094", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("dt"), org.apache.spark.sql.errors.DataTypeErrors..MODULE$.toSQLType(decimalType))}))));
      }

      public TypeInfo toTypeInfo() {
         DataType var2 = this.dt;
         if (var2 instanceof ArrayType var3) {
            DataType elemType = var3.elementType();
            return TypeInfoFactory.getListTypeInfo(this.org$apache$spark$sql$hive$HiveInspectors$typeInfoConversions$$$outer().typeInfoConversions(elemType).toTypeInfo());
         } else if (var2 instanceof StructType var5) {
            org.apache.spark.sql.types.StructField[] fields = var5.fields();
            return TypeInfoFactory.getStructTypeInfo(Arrays.asList(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fields), (x$26) -> x$26.name(), scala.reflect.ClassTag..MODULE$.apply(String.class))), Arrays.asList(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fields), (x$27) -> this.org$apache$spark$sql$hive$HiveInspectors$typeInfoConversions$$$outer().typeInfoConversions(x$27.dataType()).toTypeInfo(), scala.reflect.ClassTag..MODULE$.apply(TypeInfo.class))));
         } else if (var2 instanceof MapType var7) {
            DataType keyType = var7.keyType();
            DataType valueType = var7.valueType();
            return TypeInfoFactory.getMapTypeInfo(this.org$apache$spark$sql$hive$HiveInspectors$typeInfoConversions$$$outer().typeInfoConversions(keyType).toTypeInfo(), this.org$apache$spark$sql$hive$HiveInspectors$typeInfoConversions$$$outer().typeInfoConversions(valueType).toTypeInfo());
         } else if (org.apache.spark.sql.types.BinaryType..MODULE$.equals(var2)) {
            return TypeInfoFactory.binaryTypeInfo;
         } else if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(var2)) {
            return TypeInfoFactory.booleanTypeInfo;
         } else if (org.apache.spark.sql.types.ByteType..MODULE$.equals(var2)) {
            return TypeInfoFactory.byteTypeInfo;
         } else if (.MODULE$.equals(var2)) {
            return TypeInfoFactory.doubleTypeInfo;
         } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(var2)) {
            return TypeInfoFactory.floatTypeInfo;
         } else if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(var2)) {
            return TypeInfoFactory.intTypeInfo;
         } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(var2)) {
            return TypeInfoFactory.longTypeInfo;
         } else if (org.apache.spark.sql.types.ShortType..MODULE$.equals(var2)) {
            return TypeInfoFactory.shortTypeInfo;
         } else if (org.apache.spark.sql.types.StringType..MODULE$.equals(var2)) {
            return TypeInfoFactory.stringTypeInfo;
         } else if (var2 instanceof DecimalType) {
            DecimalType var10 = (DecimalType)var2;
            return this.decimalTypeInfo(var10);
         } else if (org.apache.spark.sql.types.DateType..MODULE$.equals(var2)) {
            return TypeInfoFactory.dateTypeInfo;
         } else if (org.apache.spark.sql.types.TimestampType..MODULE$.equals(var2)) {
            return TypeInfoFactory.timestampTypeInfo;
         } else if (org.apache.spark.sql.types.NullType..MODULE$.equals(var2)) {
            return TypeInfoFactory.voidTypeInfo;
         } else if (var2 instanceof DayTimeIntervalType) {
            return TypeInfoFactory.intervalDayTimeTypeInfo;
         } else if (var2 instanceof YearMonthIntervalType) {
            return TypeInfoFactory.intervalYearMonthTypeInfo;
         } else {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3095", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("dt"), org.apache.spark.sql.errors.DataTypeErrors..MODULE$.toSQLType(var2))}))));
         }
      }

      // $FF: synthetic method
      public HiveInspectors org$apache$spark$sql$hive$HiveInspectors$typeInfoConversions$$$outer() {
         return this.$outer;
      }

      public typeInfoConversions(final DataType dt) {
         this.dt = dt;
         if (HiveInspectors.this == null) {
            throw null;
         } else {
            this.$outer = HiveInspectors.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
