package org.apache.spark.sql;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.annotation.Stable;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.sql.catalyst.util.DateFormatter;
import org.apache.spark.sql.catalyst.util.DateFormatter$;
import org.apache.spark.sql.catalyst.util.SparkDateTimeUtils$;
import org.apache.spark.sql.catalyst.util.TimestampFormatter;
import org.apache.spark.sql.catalyst.util.TimestampFormatter$;
import org.apache.spark.sql.catalyst.util.UDTUtils$;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.BinaryType$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Decimal;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.StringType$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.unsafe.types.CalendarInterval;
import org.apache.spark.util.ArrayImplicits.;
import org.json4s.JArray;
import org.json4s.JDecimal;
import org.json4s.JDouble;
import org.json4s.JLong;
import org.json4s.JObject;
import org.json4s.JString;
import org.json4s.JValue;
import scala.MatchError;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Statics;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\r}r!\u0002\u001c8\u0011\u0003\u0001e!\u0002\"8\u0011\u0003\u0019\u0005\"\u0002*\u0002\t\u0003\u0019\u0006\"\u0002+\u0002\t\u0003)\u0006bBA\u0002\u0003\u0011\u0005!q\u001d\u0005\b\u0005g\fA\u0011\u0001B{\u0011\u001d\u0011I0\u0001C\u0001\u0005wDqaa\u0002\u0002\t\u0003\u0019I\u0001C\u0005\u0004&\u0005\u0011\r\u0011\"\u0001\u0004(!91\u0011F\u0001!\u0002\u0013Q\u0007\"CB\u0016\u0003\u0005\u0005I\u0011BB\u0017\r\u001d\u0011u\u0007%A\u0002\u0002-DQA\\\u0006\u0005\u0002=DQa]\u0006\u0005\u0002QDQ\u0001_\u0006\u0007\u0002QDQ!_\u0006\u0005\u0002iDq!a\u0001\f\t\u0003\t)\u0001C\u0004\u0002\f-1\t!!\u0004\t\u000f\u0005E1\u0002\"\u0001\u0002\u0014!9\u0011QD\u0006\u0005\u0002\u0005}\u0001bBA\u0012\u0017\u0011\u0005\u0011Q\u0005\u0005\b\u0003_YA\u0011AA\u0019\u0011\u001d\tYd\u0003C\u0001\u0003{Aq!!\u0011\f\t\u0003\t\u0019\u0005C\u0004\u0002N-!\t!a\u0014\t\u000f\u0005e3\u0002\"\u0001\u0002\\!9\u0011QM\u0006\u0005\u0002\u0005\u001d\u0004bBA>\u0017\u0011\u0005\u0011Q\u0010\u0005\b\u0003\u001b[A\u0011AAH\u0011\u001d\tij\u0003C\u0001\u0003?Cq!a,\f\t\u0003\t\t\fC\u0004\u0002<.!\t!!0\t\u000f\u0005\u001d7\u0002\"\u0001\u0002J\"9\u0011\u0011]\u0006\u0005\u0002\u0005\r\bbBA}\u0017\u0011\u0005\u00111 \u0005\b\u00053YA\u0011\u0001B\u000e\u0011\u001d\u0011ic\u0003C\u0001\u0005_AqAa\r\f\t\u0003\u0011)\u0004C\u0004\u00034-!\tAa\u0010\t\u000f\t-3\u0002\"\u0001\u0003N!9!1K\u0006\u0005\u0002\tU\u0003b\u0002B4\u0017\u0011\u0005#\u0011\u000e\u0005\b\u0005WZa\u0011\u0001B7\u0011\u001d\u0011yg\u0003C\u0001\u0005cBqAa\u001d\f\t\u0003\u0012)\bC\u0004\u0003|-!\tE! \t\u000f\t}4\u0002\"\u0001\u0003\u0002\"9!1Q\u0006\u0005\u0002\t\u0015\u0005b\u0002BB\u0017\u0011\u0005!q\u0011\u0005\b\u0005\u0007[A\u0011\u0001BG\u0011\u001d\u0011Ij\u0003C\u0005\u00057CqA!,\f\t\u0003\u0011)\tC\u0004\u0003>.!\tA!\"\t\u0011\t\u00057\u0002\"\u00018\u0005\u0007\f1AU8x\u0015\tA\u0014(A\u0002tc2T!AO\u001e\u0002\u000bM\u0004\u0018M]6\u000b\u0005qj\u0014AB1qC\u000eDWMC\u0001?\u0003\ry'oZ\u0002\u0001!\t\t\u0015!D\u00018\u0005\r\u0011vn^\n\u0004\u0003\u0011S\u0005CA#I\u001b\u00051%\"A$\u0002\u000bM\u001c\u0017\r\\1\n\u0005%3%AB!osJ+g\r\u0005\u0002L!6\tAJ\u0003\u0002N\u001d\u0006\u0011\u0011n\u001c\u0006\u0002\u001f\u0006!!.\u0019<b\u0013\t\tFJ\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0001\u0006QQO\\1qa2L8+Z9\u0015\u0005YC\u0007cA#X3&\u0011\u0001L\u0012\u0002\u0005'>lW\rE\u0002[E\u0016t!a\u00171\u000f\u0005q{V\"A/\u000b\u0005y{\u0014A\u0002\u001fs_>$h(C\u0001H\u0013\t\tg)A\u0004qC\u000e\\\u0017mZ3\n\u0005\r$'aA*fc*\u0011\u0011M\u0012\t\u0003\u000b\u001aL!a\u001a$\u0003\u0007\u0005s\u0017\u0010C\u0003j\u0007\u0001\u0007!.A\u0002s_^\u0004\"!Q\u0006\u0014\u0007-!E\u000e\u0005\u0002[[&\u0011\u0011\u000bZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003A\u0004\"!R9\n\u0005I4%\u0001B+oSR\fAa]5{KV\tQ\u000f\u0005\u0002Fm&\u0011qO\u0012\u0002\u0004\u0013:$\u0018A\u00027f]\u001e$\b.\u0001\u0004tG\",W.Y\u000b\u0002wB\u0011Ap`\u0007\u0002{*\u0011apN\u0001\u0006if\u0004Xm]\u0005\u0004\u0003\u0003i(AC*ueV\u001cG\u000fV=qK\u0006)\u0011\r\u001d9msR\u0019Q-a\u0002\t\r\u0005%\u0001\u00031\u0001v\u0003\u0005I\u0017aA4fiR\u0019Q-a\u0004\t\r\u0005%\u0011\u00031\u0001v\u0003!I7OT;mY\u0006#H\u0003BA\u000b\u00037\u00012!RA\f\u0013\r\tIB\u0012\u0002\b\u0005>|G.Z1o\u0011\u0019\tIA\u0005a\u0001k\u0006Qq-\u001a;C_>dW-\u00198\u0015\t\u0005U\u0011\u0011\u0005\u0005\u0007\u0003\u0013\u0019\u0002\u0019A;\u0002\u000f\u001d,GOQ=uKR!\u0011qEA\u0017!\r)\u0015\u0011F\u0005\u0004\u0003W1%\u0001\u0002\"zi\u0016Da!!\u0003\u0015\u0001\u0004)\u0018\u0001C4fiNCwN\u001d;\u0015\t\u0005M\u0012\u0011\b\t\u0004\u000b\u0006U\u0012bAA\u001c\r\n)1\u000b[8si\"1\u0011\u0011B\u000bA\u0002U\faaZ3u\u0013:$HcA;\u0002@!1\u0011\u0011\u0002\fA\u0002U\fqaZ3u\u0019>tw\r\u0006\u0003\u0002F\u0005-\u0003cA#\u0002H%\u0019\u0011\u0011\n$\u0003\t1{gn\u001a\u0005\u0007\u0003\u00139\u0002\u0019A;\u0002\u0011\u001d,GO\u00127pCR$B!!\u0015\u0002XA\u0019Q)a\u0015\n\u0007\u0005UcIA\u0003GY>\fG\u000f\u0003\u0004\u0002\na\u0001\r!^\u0001\nO\u0016$Hi\\;cY\u0016$B!!\u0018\u0002dA\u0019Q)a\u0018\n\u0007\u0005\u0005dI\u0001\u0004E_V\u0014G.\u001a\u0005\u0007\u0003\u0013I\u0002\u0019A;\u0002\u0013\u001d,Go\u0015;sS:<G\u0003BA5\u0003s\u0002B!a\u001b\u0002t9!\u0011QNA8!\taf)C\u0002\u0002r\u0019\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA;\u0003o\u0012aa\u0015;sS:<'bAA9\r\"1\u0011\u0011\u0002\u000eA\u0002U\f!bZ3u\t\u0016\u001c\u0017.\\1m)\u0011\ty(a#\u0011\t\u0005\u0005\u0015qQ\u0007\u0003\u0003\u0007S1!!\"O\u0003\u0011i\u0017\r\u001e5\n\t\u0005%\u00151\u0011\u0002\u000b\u0005&<G)Z2j[\u0006d\u0007BBA\u00057\u0001\u0007Q/A\u0004hKR$\u0015\r^3\u0015\t\u0005E\u00151\u0014\t\u0005\u0003'\u000b9*\u0004\u0002\u0002\u0016*\u0011\u0001HT\u0005\u0005\u00033\u000b)J\u0001\u0003ECR,\u0007BBA\u00059\u0001\u0007Q/\u0001\u0007hKRdunY1m\t\u0006$X\r\u0006\u0003\u0002\"\u00065\u0006\u0003BAR\u0003Sk!!!*\u000b\u0007\u0005\u001df*\u0001\u0003uS6,\u0017\u0002BAV\u0003K\u0013\u0011\u0002T8dC2$\u0015\r^3\t\r\u0005%Q\u00041\u0001v\u000319W\r\u001e+j[\u0016\u001cH/Y7q)\u0011\t\u0019,!/\u0011\t\u0005M\u0015QW\u0005\u0005\u0003o\u000b)JA\u0005US6,7\u000f^1na\"1\u0011\u0011\u0002\u0010A\u0002U\f!bZ3u\u0013:\u001cH/\u00198u)\u0011\ty,!2\u0011\t\u0005\r\u0016\u0011Y\u0005\u0005\u0003\u0007\f)KA\u0004J]N$\u0018M\u001c;\t\r\u0005%q\u00041\u0001v\u0003\u00199W\r^*fcV!\u00111ZAj)\u0011\ti-a8\u0011\ti\u0013\u0017q\u001a\t\u0005\u0003#\f\u0019\u000e\u0004\u0001\u0005\u000f\u0005U\u0007E1\u0001\u0002X\n\tA+E\u0002\u0002Z\u0016\u00042!RAn\u0013\r\tiN\u0012\u0002\b\u001d>$\b.\u001b8h\u0011\u0019\tI\u0001\ta\u0001k\u00069q-\u001a;MSN$X\u0003BAs\u0003k$B!a:\u0002xB1\u0011\u0011^Ax\u0003gl!!a;\u000b\u0007\u00055h*\u0001\u0003vi&d\u0017\u0002BAy\u0003W\u0014A\u0001T5tiB!\u0011\u0011[A{\t\u001d\t).\tb\u0001\u0003/Da!!\u0003\"\u0001\u0004)\u0018AB4fi6\u000b\u0007/\u0006\u0004\u0002~\n5!1\u0003\u000b\u0005\u0003\u007f\u00149\u0002\u0005\u0005\u0003\u0002\t\u001d!1\u0002B\t\u001b\t\u0011\u0019AC\u0002\u0003\u0006\u0019\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011IAa\u0001\u0003\u00075\u000b\u0007\u000f\u0005\u0003\u0002R\n5Aa\u0002B\bE\t\u0007\u0011q\u001b\u0002\u0002\u0017B!\u0011\u0011\u001bB\n\t\u001d\u0011)B\tb\u0001\u0003/\u0014\u0011A\u0016\u0005\u0007\u0003\u0013\u0011\u0003\u0019A;\u0002\u0015\u001d,GOS1wC6\u000b\u0007/\u0006\u0004\u0003\u001e\t\u0015\"\u0011\u0006\u000b\u0005\u0005?\u0011Y\u0003\u0005\u0005\u0002j\n\u0005\"1\u0005B\u0014\u0013\u0011\u0011I!a;\u0011\t\u0005E'Q\u0005\u0003\b\u0005\u001f\u0019#\u0019AAl!\u0011\t\tN!\u000b\u0005\u000f\tU1E1\u0001\u0002X\"1\u0011\u0011B\u0012A\u0002U\f\u0011bZ3u'R\u0014Xo\u0019;\u0015\u0007)\u0014\t\u0004\u0003\u0004\u0002\n\u0011\u0002\r!^\u0001\u0006O\u0016$\u0018i]\u000b\u0005\u0005o\u0011Y\u0004\u0006\u0003\u0003:\tu\u0002\u0003BAi\u0005w!q!!6&\u0005\u0004\t9\u000e\u0003\u0004\u0002\n\u0015\u0002\r!^\u000b\u0005\u0005\u0003\u0012)\u0005\u0006\u0003\u0003D\t\u001d\u0003\u0003BAi\u0005\u000b\"q!!6'\u0005\u0004\t9\u000eC\u0004\u0003J\u0019\u0002\r!!\u001b\u0002\u0013\u0019LW\r\u001c3OC6,\u0017A\u00034jK2$\u0017J\u001c3fqR\u0019QOa\u0014\t\u000f\tEs\u00051\u0001\u0002j\u0005!a.Y7f\u000319W\r\u001e,bYV,7/T1q+\u0011\u00119Fa\u0018\u0015\t\te#\u0011\r\t\t\u0003W\u0012Y&!\u001b\u0003^%!!\u0011BA<!\u0011\t\tNa\u0018\u0005\u000f\u0005U\u0007F1\u0001\u0002X\"9!1\r\u0015A\u0002\t\u0015\u0014A\u00034jK2$g*Y7fgB!!LYA5\u0003!!xn\u0015;sS:<GCAA5\u0003\u0011\u0019w\u000e]=\u0015\u0003)\fq!\u00198z\u001dVdG.\u0006\u0002\u0002\u0016\u00051Q-];bYN$B!!\u0006\u0003x!1!\u0011\u0010\u0017A\u0002\u0015\f\u0011a\\\u0001\tQ\u0006\u001c\bnQ8eKR\tQ/A\u0003u_N+\u0017/F\u0001Z\u0003!i7n\u0015;sS:<WCAA5)\u0011\tIG!#\t\u000f\t-\u0005\u00071\u0001\u0002j\u0005\u00191/\u001a9\u0015\u0011\u0005%$q\u0012BJ\u0005+CqA!%2\u0001\u0004\tI'A\u0003ti\u0006\u0014H\u000fC\u0004\u0003\fF\u0002\r!!\u001b\t\u000f\t]\u0015\u00071\u0001\u0002j\u0005\u0019QM\u001c3\u0002\u0017\u001d,G/\u00118z-\u0006d\u0017i]\u000b\u0005\u0005;\u0013\t\u000b\u0006\u0003\u0003 \n-\u0006\u0003BAi\u0005C#q!!63\u0005\u0004\u0011\u0019+\u0005\u0003\u0002Z\n\u0015\u0006cA#\u0003(&\u0019!\u0011\u0016$\u0003\r\u0005s\u0017PV1m\u0011\u0019\tIA\ra\u0001k\u0006!!n]8oQ\r\u0019$\u0011\u0017\t\u0005\u0005g\u0013I,\u0004\u0002\u00036*\u0019!qW\u001d\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003<\nU&\u0001C+ogR\f'\r\\3\u0002\u0015A\u0014X\r\u001e;z\u0015N|g\u000eK\u00025\u0005c\u000b\u0011B[:p]Z\u000bG.^3\u0016\u0005\t\u0015\u0007\u0003\u0002Bd\u00053tAA!3\u0003T:!!1\u001aBh\u001d\ra&QZ\u0005\u0002}%\u0019!\u0011[\u001f\u0002\r)\u001cxN\u001c\u001bt\u0013\u0011\u0011)Na6\u0002\u000f)\u001bxN\\!T)*\u0019!\u0011[\u001f\n\t\tm'Q\u001c\u0002\u0007\u0015Z\u000bG.^3\u000b\t\tU'q\u001b\u0015\u0004\u0017\t\u0005\b\u0003\u0002BZ\u0005GLAA!:\u00036\n11\u000b^1cY\u0016$2A\u001bBu\u0011\u001d\u0011Y\u000f\u0002a\u0001\u0005[\faA^1mk\u0016\u001c\b\u0003B#\u0003p\u0016L1A!=G\u0005)a$/\u001a9fCR,GMP\u0001\bMJ|WnU3r)\rQ'q\u001f\u0005\u0007\u0005W,\u0001\u0019A-\u0002\u0013\u0019\u0014x.\u001c+va2,Gc\u00016\u0003~\"9!q \u0004A\u0002\r\u0005\u0011!\u0002;va2,\u0007cA#\u0004\u0004%\u00191Q\u0001$\u0003\u000fA\u0013x\u000eZ;di\u0006)Q.\u001a:hKR\u0019!na\u0003\t\u000f\r5q\u00011\u0001\u0004\u0010\u0005!!o\\<t!\u0011)%q\u001e6)\u0017\u001d\u0019\u0019b!\u0007\u0004\u001c\r}1\u0011\u0005\t\u0004\u000b\u000eU\u0011bAB\f\r\nQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u00121QD\u0001B)\"L7\u000fI7fi\"|G\rI5tA\u0011,\u0007O]3dCR,G\rI1oI\u0002:\u0018\u000e\u001c7!E\u0016\u0004#/Z7pm\u0016$\u0007%\u001b8!MV$XO]3!m\u0016\u00148/[8og:\nQa]5oG\u0016\f#aa\t\u0002\u000bMr\u0003G\f\u0019\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003)\fa!Z7qif\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAB\u0018!\u0011\u0019\tda\u000e\u000e\u0005\rM\"bAB\u001b\u001d\u0006!A.\u00198h\u0013\u0011\u0019Ida\r\u0003\r=\u0013'.Z2uQ\r\t!\u0011\u001d\u0015\u0004\u0001\t\u0005\b"
)
public interface Row extends Serializable {
   static Row empty() {
      return Row$.MODULE$.empty();
   }

   /** @deprecated */
   static Row merge(final Seq rows) {
      return Row$.MODULE$.merge(rows);
   }

   static Row fromTuple(final Product tuple) {
      return Row$.MODULE$.fromTuple(tuple);
   }

   static Row fromSeq(final Seq values) {
      return Row$.MODULE$.fromSeq(values);
   }

   static Some unapplySeq(final Row row) {
      return Row$.MODULE$.unapplySeq(row);
   }

   // $FF: synthetic method
   static int size$(final Row $this) {
      return $this.size();
   }

   default int size() {
      return this.length();
   }

   int length();

   // $FF: synthetic method
   static StructType schema$(final Row $this) {
      return $this.schema();
   }

   default StructType schema() {
      return null;
   }

   // $FF: synthetic method
   static Object apply$(final Row $this, final int i) {
      return $this.apply(i);
   }

   default Object apply(final int i) {
      return this.get(i);
   }

   Object get(final int i);

   // $FF: synthetic method
   static boolean isNullAt$(final Row $this, final int i) {
      return $this.isNullAt(i);
   }

   default boolean isNullAt(final int i) {
      return this.get(i) == null;
   }

   // $FF: synthetic method
   static boolean getBoolean$(final Row $this, final int i) {
      return $this.getBoolean(i);
   }

   default boolean getBoolean(final int i) {
      return BoxesRunTime.unboxToBoolean(this.getAnyValAs(i));
   }

   // $FF: synthetic method
   static byte getByte$(final Row $this, final int i) {
      return $this.getByte(i);
   }

   default byte getByte(final int i) {
      return BoxesRunTime.unboxToByte(this.getAnyValAs(i));
   }

   // $FF: synthetic method
   static short getShort$(final Row $this, final int i) {
      return $this.getShort(i);
   }

   default short getShort(final int i) {
      return BoxesRunTime.unboxToShort(this.getAnyValAs(i));
   }

   // $FF: synthetic method
   static int getInt$(final Row $this, final int i) {
      return $this.getInt(i);
   }

   default int getInt(final int i) {
      return BoxesRunTime.unboxToInt(this.getAnyValAs(i));
   }

   // $FF: synthetic method
   static long getLong$(final Row $this, final int i) {
      return $this.getLong(i);
   }

   default long getLong(final int i) {
      return BoxesRunTime.unboxToLong(this.getAnyValAs(i));
   }

   // $FF: synthetic method
   static float getFloat$(final Row $this, final int i) {
      return $this.getFloat(i);
   }

   default float getFloat(final int i) {
      return BoxesRunTime.unboxToFloat(this.getAnyValAs(i));
   }

   // $FF: synthetic method
   static double getDouble$(final Row $this, final int i) {
      return $this.getDouble(i);
   }

   default double getDouble(final int i) {
      return BoxesRunTime.unboxToDouble(this.getAnyValAs(i));
   }

   // $FF: synthetic method
   static String getString$(final Row $this, final int i) {
      return $this.getString(i);
   }

   default String getString(final int i) {
      return (String)this.getAs(i);
   }

   // $FF: synthetic method
   static BigDecimal getDecimal$(final Row $this, final int i) {
      return $this.getDecimal(i);
   }

   default BigDecimal getDecimal(final int i) {
      return (BigDecimal)this.getAs(i);
   }

   // $FF: synthetic method
   static Date getDate$(final Row $this, final int i) {
      return $this.getDate(i);
   }

   default Date getDate(final int i) {
      return (Date)this.getAs(i);
   }

   // $FF: synthetic method
   static LocalDate getLocalDate$(final Row $this, final int i) {
      return $this.getLocalDate(i);
   }

   default LocalDate getLocalDate(final int i) {
      return (LocalDate)this.getAs(i);
   }

   // $FF: synthetic method
   static Timestamp getTimestamp$(final Row $this, final int i) {
      return $this.getTimestamp(i);
   }

   default Timestamp getTimestamp(final int i) {
      return (Timestamp)this.getAs(i);
   }

   // $FF: synthetic method
   static Instant getInstant$(final Row $this, final int i) {
      return $this.getInstant(i);
   }

   default Instant getInstant(final int i) {
      return (Instant)this.getAs(i);
   }

   // $FF: synthetic method
   static Seq getSeq$(final Row $this, final int i) {
      return $this.getSeq(i);
   }

   default Seq getSeq(final int i) {
      scala.collection.Seq var3 = (scala.collection.Seq)this.getAs(i);
      if (var3 instanceof ArraySeq var4) {
         return .MODULE$.SparkArrayOps(var4.array()).toImmutableArraySeq();
      } else {
         return var3 != null ? var3.toSeq() : null;
      }
   }

   // $FF: synthetic method
   static List getList$(final Row $this, final int i) {
      return $this.getList(i);
   }

   default List getList(final int i) {
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(this.getSeq(i)).asJava();
   }

   // $FF: synthetic method
   static Map getMap$(final Row $this, final int i) {
      return $this.getMap(i);
   }

   default Map getMap(final int i) {
      return (Map)this.getAs(i);
   }

   // $FF: synthetic method
   static java.util.Map getJavaMap$(final Row $this, final int i) {
      return $this.getJavaMap(i);
   }

   default java.util.Map getJavaMap(final int i) {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.getMap(i)).asJava();
   }

   // $FF: synthetic method
   static Row getStruct$(final Row $this, final int i) {
      return $this.getStruct(i);
   }

   default Row getStruct(final int i) {
      return (Row)this.getAs(i);
   }

   // $FF: synthetic method
   static Object getAs$(final Row $this, final int i) {
      return $this.getAs(i);
   }

   default Object getAs(final int i) {
      return this.get(i);
   }

   // $FF: synthetic method
   static Object getAs$(final Row $this, final String fieldName) {
      return $this.getAs(fieldName);
   }

   default Object getAs(final String fieldName) {
      return this.getAs(this.fieldIndex(fieldName));
   }

   // $FF: synthetic method
   static int fieldIndex$(final Row $this, final String name) {
      return $this.fieldIndex(name);
   }

   default int fieldIndex(final String name) {
      throw DataTypeErrors$.MODULE$.fieldIndexOnRowWithoutSchemaError(name);
   }

   // $FF: synthetic method
   static scala.collection.immutable.Map getValuesMap$(final Row $this, final Seq fieldNames) {
      return $this.getValuesMap(fieldNames);
   }

   default scala.collection.immutable.Map getValuesMap(final Seq fieldNames) {
      return ((IterableOnceOps)fieldNames.map((name) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), this.getAs(name)))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   static String toString$(final Row $this) {
      return $this.toString();
   }

   default String toString() {
      return this.mkString("[", ",", "]");
   }

   Row copy();

   // $FF: synthetic method
   static boolean anyNull$(final Row $this) {
      return $this.anyNull();
   }

   default boolean anyNull() {
      int len = this.length();

      for(int i = 0; i < len; ++i) {
         if (this.isNullAt(i)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static boolean equals$(final Row $this, final Object o) {
      return $this.equals(o);
   }

   default boolean equals(final Object o) {
      if (!(o instanceof Row other)) {
         return false;
      } else if (other == null) {
         return false;
      } else if (this.length() != other.length()) {
         return false;
      } else {
         for(int i = 0; i < this.length(); ++i) {
            if (this.isNullAt(i) != other.isNullAt(i)) {
               return false;
            }

            if (!this.isNullAt(i)) {
               Object o1 = this.get(i);
               Object o2 = other.get(i);
               if (o1 instanceof byte[]) {
                  byte[] var8 = (byte[])o1;
                  if (!(o2 instanceof byte[]) || !Arrays.equals(var8, (byte[])o2)) {
                     return false;
                  }

                  BoxedUnit var16 = BoxedUnit.UNIT;
               } else {
                  if (o1 instanceof Float) {
                     float var9 = BoxesRunTime.unboxToFloat(o1);
                     if (Float.isNaN(var9)) {
                        if (!(o2 instanceof Float) || !Float.isNaN(BoxesRunTime.unboxToFloat(o2))) {
                           return false;
                        }

                        BoxedUnit var15 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (o1 instanceof Double) {
                     double var10 = BoxesRunTime.unboxToDouble(o1);
                     if (Double.isNaN(var10)) {
                        if (!(o2 instanceof Double) || !Double.isNaN(BoxesRunTime.unboxToDouble(o2))) {
                           return false;
                        }

                        BoxedUnit var14 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (o1 instanceof BigDecimal) {
                     BigDecimal var12 = (BigDecimal)o1;
                     if (o2 instanceof BigDecimal) {
                        if (var12.compareTo((BigDecimal)o2) != 0) {
                           return false;
                        }

                        BoxedUnit var13 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (!BoxesRunTime.equals(o1, o2)) {
                     return false;
                  }

                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            }
         }

         return true;
      }
   }

   // $FF: synthetic method
   static int hashCode$(final Row $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      int n = 0;
      int h = scala.util.hashing.MurmurHash3..MODULE$.seqSeed();

      for(int len = this.length(); n < len; ++n) {
         h = scala.util.hashing.MurmurHash3..MODULE$.mix(h, Statics.anyHash(this.apply(n)));
      }

      return scala.util.hashing.MurmurHash3..MODULE$.finalizeHash(h, n);
   }

   // $FF: synthetic method
   static Seq toSeq$(final Row $this) {
      return $this.toSeq();
   }

   default Seq toSeq() {
      int n = this.length();
      Object[] values = new Object[n];

      for(int i = 0; i < n; ++i) {
         values[i] = this.get(i);
      }

      return .MODULE$.SparkArrayOps(values).toImmutableArraySeq();
   }

   // $FF: synthetic method
   static String mkString$(final Row $this) {
      return $this.mkString();
   }

   default String mkString() {
      return this.mkString("");
   }

   // $FF: synthetic method
   static String mkString$(final Row $this, final String sep) {
      return $this.mkString(sep);
   }

   default String mkString(final String sep) {
      return this.mkString("", sep, "");
   }

   // $FF: synthetic method
   static String mkString$(final Row $this, final String start, final String sep, final String end) {
      return $this.mkString(start, sep, end);
   }

   default String mkString(final String start, final String sep, final String end) {
      int n = this.length();
      StringBuilder builder = new StringBuilder();
      builder.append(start);
      if (n > 0) {
         builder.append(this.get(0));

         for(int i = 1; i < n; ++i) {
            builder.append(sep);
            builder.append(this.get(i));
         }
      }

      builder.append(end);
      return builder.toString();
   }

   private Object getAnyValAs(final int i) {
      if (this.isNullAt(i)) {
         throw DataTypeErrors$.MODULE$.valueIsNullError(i);
      } else {
         return this.getAs(i);
      }
   }

   // $FF: synthetic method
   static String json$(final Row $this) {
      return $this.json();
   }

   @Unstable
   default String json() {
      return org.json4s.jackson.JsonMethods..MODULE$.compact(this.jsonValue());
   }

   // $FF: synthetic method
   static String prettyJson$(final Row $this) {
      return $this.prettyJson();
   }

   @Unstable
   default String prettyJson() {
      return org.json4s.jackson.JsonMethods..MODULE$.pretty(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   // $FF: synthetic method
   static JValue jsonValue$(final Row $this) {
      return $this.jsonValue();
   }

   default JValue jsonValue() {
      LazyRef zoneId$lzy = new LazyRef();
      LazyRef dateFormatter$lzy = new LazyRef();
      LazyRef timestampFormatter$lzy = new LazyRef();
      scala.Predef..MODULE$.require(this.schema() != null, () -> "JSON serialization requires a non-null schema.");
      return this.toJson$1(this, this.schema(), dateFormatter$lzy, timestampFormatter$lzy, zoneId$lzy);
   }

   // $FF: synthetic method
   private static ZoneId zoneId$lzycompute$1(final LazyRef zoneId$lzy$1) {
      synchronized(zoneId$lzy$1){}

      ZoneId var2;
      try {
         var2 = zoneId$lzy$1.initialized() ? (ZoneId)zoneId$lzy$1.value() : (ZoneId)zoneId$lzy$1.initialize(SparkDateTimeUtils$.MODULE$.getZoneId(SqlApiConf$.MODULE$.get().sessionLocalTimeZone()));
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private static ZoneId zoneId$1(final LazyRef zoneId$lzy$1) {
      return zoneId$lzy$1.initialized() ? (ZoneId)zoneId$lzy$1.value() : zoneId$lzycompute$1(zoneId$lzy$1);
   }

   // $FF: synthetic method
   private static DateFormatter dateFormatter$lzycompute$1(final LazyRef dateFormatter$lzy$1) {
      synchronized(dateFormatter$lzy$1){}

      DateFormatter var2;
      try {
         var2 = dateFormatter$lzy$1.initialized() ? (DateFormatter)dateFormatter$lzy$1.value() : (DateFormatter)dateFormatter$lzy$1.initialize(DateFormatter$.MODULE$.apply());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private static DateFormatter dateFormatter$1(final LazyRef dateFormatter$lzy$1) {
      return dateFormatter$lzy$1.initialized() ? (DateFormatter)dateFormatter$lzy$1.value() : dateFormatter$lzycompute$1(dateFormatter$lzy$1);
   }

   // $FF: synthetic method
   private static TimestampFormatter timestampFormatter$lzycompute$1(final LazyRef timestampFormatter$lzy$1, final LazyRef zoneId$lzy$1) {
      synchronized(timestampFormatter$lzy$1){}

      TimestampFormatter var3;
      try {
         var3 = timestampFormatter$lzy$1.initialized() ? (TimestampFormatter)timestampFormatter$lzy$1.value() : (TimestampFormatter)timestampFormatter$lzy$1.initialize(TimestampFormatter$.MODULE$.apply(zoneId$1(zoneId$lzy$1)));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private static TimestampFormatter timestampFormatter$1(final LazyRef timestampFormatter$lzy$1, final LazyRef zoneId$lzy$1) {
      return timestampFormatter$lzy$1.initialized() ? (TimestampFormatter)timestampFormatter$lzy$1.value() : timestampFormatter$lzycompute$1(timestampFormatter$lzy$1, zoneId$lzy$1);
   }

   private JArray iteratorToJsonArray$1(final Iterator iterator, final DataType elementType, final LazyRef dateFormatter$lzy$1, final LazyRef timestampFormatter$lzy$1, final LazyRef zoneId$lzy$1) {
      return new JArray(iterator.map((x$2) -> this.toJson$1(x$2, elementType, dateFormatter$lzy$1, timestampFormatter$lzy$1, zoneId$lzy$1)).toList());
   }

   private JValue toJson$1(final Object value, final DataType dataType, final LazyRef dateFormatter$lzy$1, final LazyRef timestampFormatter$lzy$1, final LazyRef zoneId$lzy$1) {
      while(true) {
         Tuple2 var8 = new Tuple2(value, dataType);
         if (var8 != null) {
            Object var9 = var8._1();
            if (var9 == null) {
               return org.json4s.JNull..MODULE$;
            }
         }

         if (var8 != null) {
            Object b = var8._1();
            if (b instanceof Boolean) {
               boolean var11 = BoxesRunTime.unboxToBoolean(b);
               return org.json4s.JBool..MODULE$.apply(var11);
            }
         }

         if (var8 != null) {
            Object b = var8._1();
            if (b instanceof Byte) {
               byte var13 = BoxesRunTime.unboxToByte(b);
               return new JLong((long)var13);
            }
         }

         if (var8 != null) {
            Object s = var8._1();
            if (s instanceof Short) {
               short var15 = BoxesRunTime.unboxToShort(s);
               return new JLong((long)var15);
            }
         }

         if (var8 != null) {
            Object i = var8._1();
            if (i instanceof Integer) {
               int var17 = BoxesRunTime.unboxToInt(i);
               return new JLong((long)var17);
            }
         }

         if (var8 != null) {
            Object l = var8._1();
            if (l instanceof Long) {
               long var19 = BoxesRunTime.unboxToLong(l);
               return new JLong(var19);
            }
         }

         if (var8 != null) {
            Object f = var8._1();
            if (f instanceof Float) {
               float var22 = BoxesRunTime.unboxToFloat(f);
               return new JDouble((double)var22);
            }
         }

         if (var8 != null) {
            Object d = var8._1();
            if (d instanceof Double) {
               double var24 = BoxesRunTime.unboxToDouble(d);
               return new JDouble(var24);
            }
         }

         if (var8 != null) {
            Object d = var8._1();
            if (d instanceof scala.math.BigDecimal) {
               scala.math.BigDecimal var27 = (scala.math.BigDecimal)d;
               return new JDecimal(var27);
            }
         }

         if (var8 != null) {
            Object d = var8._1();
            if (d instanceof BigDecimal) {
               BigDecimal var29 = (BigDecimal)d;
               return new JDecimal(scala.math.BigDecimal..MODULE$.javaBigDecimal2bigDecimal(var29));
            }
         }

         if (var8 != null) {
            Object d = var8._1();
            if (d instanceof Decimal) {
               Decimal var31 = (Decimal)d;
               return new JDecimal(var31.toBigDecimal());
            }
         }

         if (var8 != null) {
            Object s = var8._1();
            if (s instanceof String) {
               String var33 = (String)s;
               return new JString(var33);
            }
         }

         if (var8 != null) {
            Object b = var8._1();
            DataType var35 = (DataType)var8._2();
            if (b instanceof byte[]) {
               byte[] var36 = (byte[])b;
               if (BinaryType$.MODULE$.equals(var35)) {
                  return new JString(Base64.getEncoder().encodeToString(var36));
               }
            }
         }

         if (var8 != null) {
            Object d = var8._1();
            if (d instanceof LocalDate) {
               LocalDate var38 = (LocalDate)d;
               return new JString(dateFormatter$1(dateFormatter$lzy$1).format(var38));
            }
         }

         if (var8 != null) {
            Object d = var8._1();
            if (d instanceof Date) {
               Date var40 = (Date)d;
               return new JString(dateFormatter$1(dateFormatter$lzy$1).format((java.util.Date)var40));
            }
         }

         if (var8 != null) {
            Object i = var8._1();
            if (i instanceof Instant) {
               Instant var42 = (Instant)i;
               return new JString(timestampFormatter$1(timestampFormatter$lzy$1, zoneId$lzy$1).format(var42));
            }
         }

         if (var8 != null) {
            Object t = var8._1();
            if (t instanceof Timestamp) {
               Timestamp var44 = (Timestamp)t;
               return new JString(timestampFormatter$1(timestampFormatter$lzy$1, zoneId$lzy$1).format(var44));
            }
         }

         if (var8 != null) {
            Object d = var8._1();
            if (d instanceof LocalDateTime) {
               LocalDateTime var46 = (LocalDateTime)d;
               return new JString(timestampFormatter$1(timestampFormatter$lzy$1, zoneId$lzy$1).format(var46));
            }
         }

         if (var8 != null) {
            Object i = var8._1();
            if (i instanceof CalendarInterval) {
               CalendarInterval var48 = (CalendarInterval)i;
               return new JString(var48.toString());
            }
         }

         if (var8 != null) {
            Object a = var8._1();
            DataType var50 = (DataType)var8._2();
            if (scala.runtime.ScalaRunTime..MODULE$.isArray(a, 1) && var50 instanceof ArrayType) {
               ArrayType var52 = (ArrayType)var50;
               DataType elementType = var52.elementType();
               return this.iteratorToJsonArray$1(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(a)), elementType, dateFormatter$lzy$1, timestampFormatter$lzy$1, zoneId$lzy$1);
            }
         }

         if (var8 != null) {
            Object a = var8._1();
            DataType var55 = (DataType)var8._2();
            if (a instanceof ArraySeq) {
               ArraySeq var56 = (ArraySeq)a;
               if (var55 instanceof ArrayType) {
                  ArrayType var57 = (ArrayType)var55;
                  DataType elementType = var57.elementType();
                  return this.iteratorToJsonArray$1(var56.iterator(), elementType, dateFormatter$lzy$1, timestampFormatter$lzy$1, zoneId$lzy$1);
               }
            }
         }

         if (var8 != null) {
            Object s = var8._1();
            DataType var60 = (DataType)var8._2();
            if (s instanceof Seq) {
               Seq var61 = (Seq)s;
               if (var60 instanceof ArrayType) {
                  ArrayType var62 = (ArrayType)var60;
                  DataType elementType = var62.elementType();
                  return this.iteratorToJsonArray$1(var61.iterator(), elementType, dateFormatter$lzy$1, timestampFormatter$lzy$1, zoneId$lzy$1);
               }
            }
         }

         if (var8 != null) {
            Object m = var8._1();
            DataType var65 = (DataType)var8._2();
            if (m instanceof scala.collection.immutable.Map) {
               scala.collection.immutable.Map var66 = (scala.collection.immutable.Map)m;
               if (var65 instanceof MapType) {
                  MapType var67 = (MapType)var65;
                  DataType var68 = var67.keyType();
                  DataType valueType = var67.valueType();
                  if (StringType$.MODULE$.equals(var68)) {
                     return new JObject(((scala.collection.immutable.List)var66.toList().sortBy((x$3) -> (String)x$3._1(), scala.math.Ordering.String..MODULE$)).map((x0$1) -> {
                        if (x0$1 != null) {
                           String k = (String)x0$1._1();
                           Object v = x0$1._2();
                           return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k), this.toJson$1(v, valueType, dateFormatter$lzy$1, timestampFormatter$lzy$1, zoneId$lzy$1));
                        } else {
                           throw new MatchError(x0$1);
                        }
                     }));
                  }
               }
            }
         }

         if (var8 != null) {
            Object m = var8._1();
            DataType var71 = (DataType)var8._2();
            if (m instanceof scala.collection.immutable.Map) {
               scala.collection.immutable.Map var72 = (scala.collection.immutable.Map)m;
               if (var71 instanceof MapType) {
                  MapType var73 = (MapType)var71;
                  DataType keyType = var73.keyType();
                  DataType valueType = var73.valueType();
                  return new JArray(var72.iterator().map((x0$2) -> {
                     if (x0$2 != null) {
                        Object k = x0$2._1();
                        Object v = x0$2._2();
                        Tuple2 var11 = scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("key"), this.toJson$1(k, keyType, dateFormatter$lzy$1, timestampFormatter$lzy$1, zoneId$lzy$1));
                        Tuple2 var12 = scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("value"), this.toJson$1(v, valueType, dateFormatter$lzy$1, timestampFormatter$lzy$1, zoneId$lzy$1));
                        return new JObject(scala.collection.immutable.Nil..MODULE$.$colon$colon(var12).$colon$colon(var11));
                     } else {
                        throw new MatchError(x0$2);
                     }
                  }).toList());
               }
            }
         }

         if (var8 != null) {
            Object row = var8._1();
            DataType schema = (DataType)var8._2();
            if (row instanceof Row) {
               Row var78 = (Row)row;
               if (schema instanceof StructType) {
                  StructType var79 = (StructType)schema;
                  int n = 0;
                  ListBuffer elements = new ListBuffer();

                  for(int len = var78.length(); n < len; ++n) {
                     StructField field = var79.apply(n);
                     elements.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(field.name()), this.toJson$1(var78.apply(n), field.dataType(), dateFormatter$lzy$1, timestampFormatter$lzy$1, zoneId$lzy$1)));
                  }

                  return new JObject(elements.toList());
               }
            }
         }

         if (var8 != null) {
            Object v = var8._1();
            DataType udt = (DataType)var8._2();
            if (v instanceof Object && udt instanceof UserDefinedType) {
               UserDefinedType var87 = (UserDefinedType)udt;
               Object var10000 = UDTUtils$.MODULE$.toRow(v, var87);
               dataType = var87.sqlType();
               value = var10000;
               continue;
            }
         }

         throw new SparkIllegalArgumentException("FAILED_ROW_TO_JSON", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("value"), DataTypeErrors$.MODULE$.toSQLValue(value.toString())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), value.getClass().toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sqlType"), DataTypeErrors$.MODULE$.toSQLType(dataType.toString()))}))));
      }
   }

   static void $init$(final Row $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
