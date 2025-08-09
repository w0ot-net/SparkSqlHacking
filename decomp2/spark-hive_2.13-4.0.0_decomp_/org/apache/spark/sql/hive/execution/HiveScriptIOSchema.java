package org.apache.spark.sql.hive.execution;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.execution.ScriptTransformationIOSchema;
import org.apache.spark.sql.hive.HiveInspectors;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Function3;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}s!B\u0005\u000b\u0011\u00039b!B\r\u000b\u0011\u0003Q\u0002\"B\u0013\u0002\t\u00031\u0003\"B\u0014\u0002\t\u0003A\u0003\"B.\u0002\t\u0003a\u0006\"\u00023\u0002\t\u0013)\u0007\"B=\u0002\t\u0003Q\bbBA\u0006\u0003\u0011\u0005\u0011Q\u0002\u0005\b\u0003\u000b\nA\u0011AA$\u0003IA\u0015N^3TGJL\u0007\u000f^%P'\u000eDW-\\1\u000b\u0005-a\u0011!C3yK\u000e,H/[8o\u0015\tia\"\u0001\u0003iSZ,'BA\b\u0011\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sO\u000e\u0001\u0001C\u0001\r\u0002\u001b\u0005Q!A\u0005%jm\u0016\u001c6M]5qi&{5k\u00195f[\u0006\u001c2!A\u000e\"!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fMB\u0011!eI\u0007\u0002\u0019%\u0011A\u0005\u0004\u0002\u000f\u0011&4X-\u00138ta\u0016\u001cGo\u001c:t\u0003\u0019a\u0014N\\5u}Q\tq#\u0001\bj]&$\u0018J\u001c9viN+'\u000fR3\u0015\u0007%rT\tE\u0002\u001dU1J!aK\u000f\u0003\r=\u0003H/[8o!\u0011aRf\f\u001d\n\u00059j\"A\u0002+va2,'\u0007\u0005\u00021m5\t\u0011G\u0003\u00023g\u000511/\u001a:eKJR!!\u0004\u001b\u000b\u0005U\u0012\u0012A\u00025bI>|\u0007/\u0003\u00028c\ti\u0011IY:ue\u0006\u001cGoU3s\t\u0016\u0004\"!\u000f\u001f\u000e\u0003iR!aO\u0019\u0002\u001f=\u0014'.Z2uS:\u001c\b/Z2u_JL!!\u0010\u001e\u0003+M#(/^2u\u001f\nTWm\u0019;J]N\u0004Xm\u0019;pe\")qh\u0001a\u0001\u0001\u0006A\u0011n\\:dQ\u0016l\u0017\r\u0005\u0002B\u00076\t!I\u0003\u0002\f\u001d%\u0011AI\u0011\u0002\u001d'\u000e\u0014\u0018\u000e\u001d;Ue\u0006t7OZ8s[\u0006$\u0018n\u001c8J\u001fN\u001b\u0007.Z7b\u0011\u001515\u00011\u0001H\u0003\u0015Ig\u000e];u!\rA\u0005k\u0015\b\u0003\u0013:s!AS'\u000e\u0003-S!\u0001\u0014\f\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0012BA(\u001e\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0015*\u0003\u0007M+\u0017O\u0003\u0002P;A\u0011A+W\u0007\u0002+*\u0011akV\u0001\fKb\u0004(/Z:tS>t7O\u0003\u0002Y\u001d\u0005A1-\u0019;bYf\u001cH/\u0003\u0002[+\nQQ\t\u001f9sKN\u001c\u0018n\u001c8\u0002\u001f%t\u0017\u000e^(viB,HoU3s\t\u0016$2!K/_\u0011\u0015yD\u00011\u0001A\u0011\u0015yF\u00011\u0001a\u0003\u0019yW\u000f\u001e9viB\u0019\u0001\nU1\u0011\u0005Q\u0013\u0017BA2V\u0005%\tE\u000f\u001e:jEV$X-\u0001\u0006qCJ\u001cX-\u0011;ueN$\"AZ<\u0011\tqis\r\u001d\t\u0004\u0011BC\u0007CA5n\u001d\tQ7\u000e\u0005\u0002K;%\u0011A.H\u0001\u0007!J,G-\u001a4\n\u00059|'AB*ue&twM\u0003\u0002m;A\u0019\u0001\nU9\u0011\u0005I,X\"A:\u000b\u0005Qt\u0011!\u0002;za\u0016\u001c\u0018B\u0001<t\u0005!!\u0015\r^1UsB,\u0007\"\u0002=\u0006\u0001\u00049\u0015!B1uiJ\u001c\u0018!C5oSR\u001cVM\u001d#f)\u0019y30`@\u0002\u0004!)AP\u0002a\u0001Q\u0006q1/\u001a:eK\u000ec\u0017m]:OC6,\u0007\"\u0002@\u0007\u0001\u00049\u0017aB2pYVlgn\u001d\u0005\u0007\u0003\u00031\u0001\u0019\u00019\u0002\u0017\r|G.^7o)f\u0004Xm\u001d\u0005\b\u0003\u000b1\u0001\u0019AA\u0004\u0003)\u0019XM\u001d3f!J|\u0007o\u001d\t\u0005\u0011B\u000bI\u0001\u0005\u0003\u001d[!D\u0017\u0001\u0004:fG>\u0014HMU3bI\u0016\u0014H\u0003CA\b\u0003C\t\u0019#a\u000e\u0011\tqQ\u0013\u0011\u0003\t\u0005\u0003'\ti\"\u0004\u0002\u0002\u0016)!\u0011qCA\r\u0003\u0011)\u00070Z2\u000b\u0007\u0005m1'\u0001\u0002rY&!\u0011qDA\u000b\u00051\u0011VmY8sIJ+\u0017\rZ3s\u0011\u0015yt\u00011\u0001A\u0011\u001d\t)c\u0002a\u0001\u0003O\t1\"\u001b8qkR\u001cFO]3b[B!\u0011\u0011FA\u001a\u001b\t\tYC\u0003\u0003\u0002.\u0005=\u0012AA5p\u0015\t\t\t$\u0001\u0003kCZ\f\u0017\u0002BA\u001b\u0003W\u00111\"\u00138qkR\u001cFO]3b[\"9\u0011\u0011H\u0004A\u0002\u0005m\u0012\u0001B2p]\u001a\u0004B!!\u0010\u0002B5\u0011\u0011q\b\u0006\u0004\u0003s!\u0014\u0002BA\"\u0003\u007f\u0011QbQ8oM&<WO]1uS>t\u0017\u0001\u0004:fG>\u0014Hm\u0016:ji\u0016\u0014H\u0003CA%\u0003#\n\u0019&!\u0018\u0011\tqQ\u00131\n\t\u0005\u0003'\ti%\u0003\u0003\u0002P\u0005U!\u0001\u0004*fG>\u0014Hm\u0016:ji\u0016\u0014\b\"B \t\u0001\u0004\u0001\u0005bBA+\u0011\u0001\u0007\u0011qK\u0001\r_V$\b/\u001e;TiJ,\u0017-\u001c\t\u0005\u0003S\tI&\u0003\u0003\u0002\\\u0005-\"\u0001D(viB,Ho\u0015;sK\u0006l\u0007bBA\u001d\u0011\u0001\u0007\u00111\b"
)
public final class HiveScriptIOSchema {
   public static Option recordWriter(final ScriptTransformationIOSchema ioschema, final OutputStream outputStream, final Configuration conf) {
      return HiveScriptIOSchema$.MODULE$.recordWriter(ioschema, outputStream, conf);
   }

   public static Option recordReader(final ScriptTransformationIOSchema ioschema, final InputStream inputStream, final Configuration conf) {
      return HiveScriptIOSchema$.MODULE$.recordReader(ioschema, inputStream, conf);
   }

   public static AbstractSerDe initSerDe(final String serdeClassName, final Seq columns, final Seq columnTypes, final Seq serdeProps) {
      return HiveScriptIOSchema$.MODULE$.initSerDe(serdeClassName, columns, columnTypes, serdeProps);
   }

   public static Option initOutputSerDe(final ScriptTransformationIOSchema ioschema, final Seq output) {
      return HiveScriptIOSchema$.MODULE$.initOutputSerDe(ioschema, output);
   }

   public static Option initInputSerDe(final ScriptTransformationIOSchema ioschema, final Seq input) {
      return HiveScriptIOSchema$.MODULE$.initInputSerDe(ioschema, input);
   }

   public static HiveInspectors.typeInfoConversions typeInfoConversions(final DataType dt) {
      return HiveScriptIOSchema$.MODULE$.typeInfoConversions(dt);
   }

   public static DataType inspectorToDataType(final ObjectInspector inspector) {
      return HiveScriptIOSchema$.MODULE$.inspectorToDataType(inspector);
   }

   public static ObjectInspector toInspector(final Expression expr) {
      return HiveScriptIOSchema$.MODULE$.toInspector(expr);
   }

   public static ObjectInspector toInspector(final DataType dataType) {
      return HiveScriptIOSchema$.MODULE$.toInspector(dataType);
   }

   public static Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      return HiveScriptIOSchema$.MODULE$.wrap(row, wrappers, cache);
   }

   public static Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return HiveScriptIOSchema$.MODULE$.wrap(row, wrappers, cache, dataTypes);
   }

   public static Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return HiveScriptIOSchema$.MODULE$.wrap(a, oi, dataType);
   }

   public static Function3 unwrapperFor(final StructField field) {
      return HiveScriptIOSchema$.MODULE$.unwrapperFor(field);
   }

   public static Function1 unwrapperFor(final ObjectInspector objectInspector) {
      return HiveScriptIOSchema$.MODULE$.unwrapperFor(objectInspector);
   }

   public static DataType javaTypeToDataType(final Type clz) {
      return HiveScriptIOSchema$.MODULE$.javaTypeToDataType(clz);
   }
}
