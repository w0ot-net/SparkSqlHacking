package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.PartitionDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.serde2.Deserializer;
import org.apache.hadoop.hive.serde2.avro.AvroSerdeUtils.AvroTableProperties;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.EmptyRDD;
import org.apache.spark.rdd.HadoopRDD;
import org.apache.spark.rdd.NewHadoopRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.UnionRDD;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.SQLConfHelper;
import org.apache.spark.sql.catalyst.analysis.CastSupport;
import org.apache.spark.sql.catalyst.expressions.Attribute;
import org.apache.spark.sql.catalyst.expressions.Cast;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.SpecificInternalRow;
import org.apache.spark.sql.internal.SQLConf;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.Utils.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tuh!B\u0012%\u0001\u0011r\u0003\u0002C&\u0001\u0005\u000b\u0007I\u0011B'\t\u0011\u0001\u0004!\u0011!Q\u0001\n9C\u0001\"\u001a\u0001\u0003\u0006\u0004%I!\u0014\u0005\tM\u0002\u0011\t\u0011)A\u0005\u001d\"A\u0001\u000e\u0001BC\u0002\u0013%\u0011\u000e\u0003\u0005v\u0001\t\u0005\t\u0015!\u0003k\u0011!9\bA!b\u0001\n\u0013A\b\u0002C?\u0001\u0005\u0003\u0005\u000b\u0011B=\t\u0013}\u0004!\u0011!Q\u0001\n\u0005\u0005\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\n\u0003;\u0001!\u0019!C\u0005\u0003?A\u0001\"a\n\u0001A\u0003%\u0011\u0011\u0005\u0005\n\u0003S\u0001!\u0019!C\u0005\u0003WA\u0001\"!\u0012\u0001A\u0003%\u0011Q\u0006\u0005\b\u0003\u000f\u0001A\u0011IA$\u0011\u001d\t\u0019\u0006\u0001C!\u0003+Bq!a\u0015\u0001\t\u0003\tI\bC\u0004\u0002F\u0002!\t%a2\t\u000f\u0005\u0015\u0007\u0001\"\u0001\u0002V\"9\u0011Q\u001e\u0001\u0005\n\u0005=\bb\u0002B\u0002\u0001\u0011%!Q\u0001\u0005\b\u0005\u0003\u0002A\u0011\u0002B\"\u0011\u001d\u0011\t\u0005\u0001C\u0005\u00057BqA!\u001b\u0001\t\u0013\u0011Y\u0007C\u0004\u0003j\u0001!IA!\u001d\t\u000f\t%\u0004\u0001\"\u0003\u0003x!9!q\u0013\u0001\u0005\n\te\u0005b\u0002BL\u0001\u0011%!q\u0014\u0005\b\u0005/\u0003A\u0011\u0002BT\u000f!\u0011Y\f\nE\u0001I\tufaB\u0012%\u0011\u0003!#q\u0018\u0005\b\u0003\u001byB\u0011\u0001Bd\u0011\u001d\u0011Im\bC\u0001\u0005\u0017DqA!6 \t\u0003\u00119NA\tIC\u0012|w\u000e\u001d+bE2,'+Z1eKJT!!\n\u0014\u0002\t!Lg/\u001a\u0006\u0003O!\n1a]9m\u0015\tI#&A\u0003ta\u0006\u00148N\u0003\u0002,Y\u00051\u0011\r]1dQ\u0016T\u0011!L\u0001\u0004_J<7C\u0002\u00010ke\nU\t\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VM\u001a\t\u0003m]j\u0011\u0001J\u0005\u0003q\u0011\u00121\u0002V1cY\u0016\u0014V-\u00193feB\u0011!hP\u0007\u0002w)\u0011A(P\u0001\tC:\fG._:jg*\u0011aHJ\u0001\tG\u0006$\u0018\r\\=ti&\u0011\u0001i\u000f\u0002\f\u0007\u0006\u001cHoU;qa>\u0014H\u000f\u0005\u0002C\u00076\tQ(\u0003\u0002E{\ti1+\u0015'D_:4\u0007*\u001a7qKJ\u0004\"AR%\u000e\u0003\u001dS!\u0001\u0013\u0015\u0002\u0011%tG/\u001a:oC2L!AS$\u0003\u000f1{wmZ5oO\u0006Q\u0011\r\u001e;sS\n,H/Z:\u0004\u0001U\ta\nE\u0002P/js!\u0001U+\u000f\u0005E#V\"\u0001*\u000b\u0005Mc\u0015A\u0002\u001fs_>$h(C\u00013\u0013\t1\u0016'A\u0004qC\u000e\\\u0017mZ3\n\u0005aK&aA*fc*\u0011a+\r\t\u00037zk\u0011\u0001\u0018\u0006\u0003;v\n1\"\u001a=qe\u0016\u001c8/[8og&\u0011q\f\u0018\u0002\n\u0003R$(/\u001b2vi\u0016\f1\"\u0019;ue&\u0014W\u000f^3tA!\u0012!A\u0019\t\u0003a\rL!\u0001Z\u0019\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018!\u00049beRLG/[8o\u0017\u0016L8/\u0001\bqCJ$\u0018\u000e^5p].+\u0017p\u001d\u0011)\u0005\u0011\u0011\u0017!\u0003;bE2,G)Z:d+\u0005Q\u0007CA6t\u001b\u0005a'BA7o\u0003\u0011\u0001H.\u00198\u000b\u0005=\u0004\u0018AA9m\u0015\t)\u0013O\u0003\u0002sU\u00051\u0001.\u00193p_BL!\u0001\u001e7\u0003\u0013Q\u000b'\r\\3EKN\u001c\u0017A\u0003;bE2,G)Z:dA!\u0012aAY\u0001\rgB\f'o[*fgNLwN\\\u000b\u0002sB\u0011!p_\u0007\u0002M%\u0011AP\n\u0002\r'B\f'o[*fgNLwN\\\u0001\u000egB\f'o[*fgNLwN\u001c\u0011)\u0005!\u0011\u0017A\u00035bI>|\u0007oQ8oMB!\u00111AA\u0005\u001b\t\t)AC\u0002\u0002\bE\fAaY8oM&!\u00111BA\u0003\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u00061A(\u001b8jiz\"B\"!\u0005\u0002\u0014\u0005U\u0011qCA\r\u00037\u0001\"A\u000e\u0001\t\u000b-S\u0001\u0019\u0001(\t\u000b\u0015T\u0001\u0019\u0001(\t\u000b!T\u0001\u0019\u00016\t\u000b]T\u0001\u0019A=\t\r}T\u0001\u0019AA\u0001\u0003AyV.\u001b8Ta2LGo\u001d)feJ#E)\u0006\u0002\u0002\"A\u0019\u0001'a\t\n\u0007\u0005\u0015\u0012GA\u0002J]R\f\u0011cX7j]N\u0003H.\u001b;t!\u0016\u0014(\u000b\u0012#!\u0003Yy&M]8bI\u000e\f7\u000f^3e\u0011\u0006$wn\u001c9D_:4WCAA\u0017!\u0019\ty#!\u000e\u0002:5\u0011\u0011\u0011\u0007\u0006\u0004\u0003gA\u0013!\u00032s_\u0006$7-Y:u\u0013\u0011\t9$!\r\u0003\u0013\t\u0013x.\u00193dCN$\b\u0003BA\u001e\u0003\u0003j!!!\u0010\u000b\u0007\u0005}\u0002&\u0001\u0003vi&d\u0017\u0002BA\"\u0003{\u0011\u0011dU3sS\u0006d\u0017N_1cY\u0016\u001cuN\u001c4jOV\u0014\u0018\r^5p]\u00069rL\u0019:pC\u0012\u001c\u0017m\u001d;fI\"\u000bGm\\8q\u0007>tg\rI\u000b\u0003\u0003\u0013\u0002B!a\u0013\u0002P5\u0011\u0011Q\n\u0006\u0003\u0011\u001aJA!!\u0015\u0002N\t91+\u0015'D_:4\u0017aD7bW\u0016\u0014F\t\u0012$peR\u000b'\r\\3\u0015\t\u0005]\u0013\u0011\u000e\t\u0007\u00033\ny&a\u0019\u000e\u0005\u0005m#bAA/Q\u0005\u0019!\u000f\u001a3\n\t\u0005\u0005\u00141\f\u0002\u0004%\u0012#\u0005c\u0001\"\u0002f%\u0019\u0011qM\u001f\u0003\u0017%sG/\u001a:oC2\u0014vn\u001e\u0005\b\u0003W\u0002\u0002\u0019AA7\u0003%A\u0017N^3UC\ndW\r\u0005\u0003\u0002p\u0005UTBAA9\u0015\r\t\u0019H\\\u0001\t[\u0016$\u0018\rZ1uC&!\u0011qOA9\u0005\u0015!\u0016M\u00197f)!\t9&a\u001f\u0002~\u0005=\u0006bBA6#\u0001\u0007\u0011Q\u000e\u0005\b\u0003\u007f\n\u0002\u0019AAA\u0003E!Wm]3sS\u0006d\u0017N_3s\u00072\f7o\u001d\u0019\u0005\u0003\u0007\u000b9\n\u0005\u0004\u0002\u0006\u00065\u00151\u0013\b\u0005\u0003\u000f\u000bI\t\u0005\u0002Rc%\u0019\u00111R\u0019\u0002\rA\u0013X\rZ3g\u0013\u0011\ty)!%\u0003\u000b\rc\u0017m]:\u000b\u0007\u0005-\u0015\u0007\u0005\u0003\u0002\u0016\u0006]E\u0002\u0001\u0003\r\u00033\u000bi(!A\u0001\u0002\u000b\u0005\u00111\u0014\u0002\u0004?\u0012\n\u0014\u0003BAO\u0003G\u00032\u0001MAP\u0013\r\t\t+\r\u0002\b\u001d>$\b.\u001b8h!\u0011\t)+a+\u000e\u0005\u0005\u001d&bAAUa\u000611/\u001a:eKJJA!!,\u0002(\naA)Z:fe&\fG.\u001b>fe\"9\u0011\u0011W\tA\u0002\u0005M\u0016!\u00034jYR,'o\u00149u!\u0015\u0001\u0014QWA]\u0013\r\t9,\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u0005m\u0016\u0011Y\u0007\u0003\u0003{S1!a0r\u0003\t17/\u0003\u0003\u0002D\u0006u&A\u0003)bi\"4\u0015\u000e\u001c;fe\u0006QR.Y6f%\u0012#ei\u001c:QCJ$\u0018\u000e^5p]\u0016$G+\u00192mKR!\u0011qKAe\u0011\u001d\tYM\u0005a\u0001\u0003\u001b\f!\u0002]1si&$\u0018n\u001c8t!\u0011yu+a4\u0011\t\u0005=\u0014\u0011[\u0005\u0005\u0003'\f\tHA\u0005QCJ$\u0018\u000e^5p]R1\u0011qKAl\u0003WDq!!7\u0014\u0001\u0004\tY.A\fqCJ$\u0018\u000e^5p]R{G)Z:fe&\fG.\u001b>feBA\u0011QQAo\u0003\u001f\f\t/\u0003\u0003\u0002`\u0006E%aA'baB\"\u00111]At!\u0019\t))!$\u0002fB!\u0011QSAt\t1\tI/a6\u0002\u0002\u0003\u0005)\u0011AAN\u0005\ryFE\r\u0005\b\u0003c\u001b\u0002\u0019AAZ\u0003M\t\u0007\u000f\u001d7z\r&dG/\u001a:JM:+W\rZ3e)\u0019\t\t0a>\u0003\u0002A!\u0011QQAz\u0013\u0011\t)0!%\u0003\rM#(/\u001b8h\u0011\u001d\tI\u0010\u0006a\u0001\u0003w\fA\u0001]1uQB!\u00111XA\u007f\u0013\u0011\ty0!0\u0003\tA\u000bG\u000f\u001b\u0005\b\u0003c#\u0002\u0019AAZ\u0003i\u0019w.\u001c9bi&\u0014G.Z,ji\"tUm\u001e%bI>|\u0007O\u0015#E)\u0011\u00119A!\u0004\u0011\u0007A\u0012I!C\u0002\u0003\fE\u0012qAQ8pY\u0016\fg\u000eC\u0004\u0003\u0010U\u0001\rA!\u0005\u0002\u0015%t\u0007/\u001e;DY\u0006\u001c8\u000f\r\u0003\u0003\u0014\t]\u0001CBAC\u0003\u001b\u0013)\u0002\u0005\u0003\u0002\u0016\n]A\u0001\u0004B\r\u0005\u001b\t\t\u0011!A\u0003\u0002\tm!aA0%gE!\u0011Q\u0014B\u000fa\u0019\u0011yB!\f\u0003>AA!\u0011\u0005B\u0014\u0005W\u0011Y$\u0004\u0002\u0003$)\u0019!QE9\u0002\r5\f\u0007O]3e\u0013\u0011\u0011ICa\t\u0003\u0017%s\u0007/\u001e;G_Jl\u0017\r\u001e\t\u0005\u0003+\u0013i\u0003\u0002\u0007\u00030\tE\u0012\u0011!A\u0001\u0006\u0003\u0011\u0019DA\u0002`IQ\"AB!\u0007\u0003\u000e\u0005\u0005\u0019\u0011!B\u0001\u00057\tB!!(\u00036A\u0019\u0001Ga\u000e\n\u0007\te\u0012GA\u0002B]f\u0004B!!&\u0003>\u0011a!q\bB\u0019\u0003\u0003\u0005\tQ!\u0001\u00034\t\u0019q\fJ\u001b\u0002\u001f\r\u0014X-\u0019;f\u0011\u0006$wn\u001c9S\t\u0012#bA!\u0012\u0003T\t]\u0003CBA-\u0003?\u00129\u0005\u0005\u0003\u0003J\t=SB\u0001B&\u0015\r\u0011i%]\u0001\u0003S>LAA!\u0015\u0003L\tAqK]5uC\ndW\r\u0003\u0004\u0003VY\u0001\rA[\u0001\u000fY>\u001c\u0017\r\u001c+bE2,G)Z:d\u0011\u001d\u0011IF\u0006a\u0001\u0003c\fA\"\u001b8qkR\u0004\u0016\r\u001e5TiJ$bA!\u0012\u0003^\t\u001d\u0004b\u0002B0/\u0001\u0007!\u0011M\u0001\u000ea\u0006\u0014H/\u001b;j_:$Um]2\u0011\u0007-\u0014\u0019'C\u0002\u0003f1\u0014Q\u0002U1si&$\u0018n\u001c8EKN\u001c\u0007b\u0002B-/\u0001\u0007\u0011\u0011_\u0001\u0013GJ,\u0017\r^3PY\u0012D\u0015\rZ8paJ#E\t\u0006\u0004\u0003F\t5$q\u000e\u0005\u0006Qb\u0001\rA\u001b\u0005\b\u0003sD\u0002\u0019AAy)\u0019\u0011)Ea\u001d\u0003v!9!qL\rA\u0002\t\u0005\u0004bBA}3\u0001\u0007\u0011\u0011\u001f\u000b\u0007\u0005\u000b\u0012IH!!\t\u000f\tm$\u00041\u0001\u0003~\u0005\u0001\u0012N\u001c9vi\u001a{'/\\1u\u00072\f7o\u001d\t\u0007\u0003\u000b\u000biIa \u0011\u0011\t\u0005\"q\u0005B$\u0005\u000fBqAa!\u001b\u0001\u0004\u0011))A\u000bj]&$\u0018.\u00197ju\u0016TuNY\"p]\u001a4UO\\2\u0011\u000fA\u00129Ia#\u0003\u0012&\u0019!\u0011R\u0019\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0003\u0002B\u0011\u0005\u001bKAAa$\u0003$\t9!j\u001c2D_:4\u0007c\u0001\u0019\u0003\u0014&\u0019!QS\u0019\u0003\tUs\u0017\u000e^\u0001\u0013GJ,\u0017\r^3OK^D\u0015\rZ8paJ#E\t\u0006\u0004\u0003F\tm%Q\u0014\u0005\u0006Qn\u0001\rA\u001b\u0005\b\u0003s\\\u0002\u0019AAy)\u0019\u0011)E!)\u0003&\"9!1\u0015\u000fA\u0002\t\u0005\u0014\u0001\u00039beR$Um]2\t\u000f\u0005eH\u00041\u0001\u0002rR1!Q\tBU\u0005oCqAa\u001f\u001e\u0001\u0004\u0011Y\u000b\u0005\u0004\u0002\u0006\u00065%Q\u0016\t\t\u0005_\u0013)La\u0012\u0003H5\u0011!\u0011\u0017\u0006\u0004\u0005g\u000b\u0018!C7baJ,G-^2f\u0013\u0011\u0011IC!-\t\u000f\teV\u00041\u0001\u0003\f\u00069!n\u001c2D_:4\u0017!\u0005%bI>|\u0007\u000fV1cY\u0016\u0014V-\u00193feB\u0011agH\n\u0006?=\u0012\t-\u0012\t\u0004m\t\r\u0017b\u0001BcI\tq\u0001*\u001b<f\u0013:\u001c\b/Z2u_J\u001cHC\u0001B_\u0003iIg.\u001b;jC2L'0\u001a'pG\u0006d'j\u001c2D_:4g)\u001e8d)\u0019\u0011iM!5\u0003TR!!\u0011\u0013Bh\u0011\u001d\u0011I,\ta\u0001\u0005\u0017Cq!!?\"\u0001\u0004\t\t\u0010C\u0003iC\u0001\u0007!.\u0001\u0006gS2dwJ\u00196fGR$BB!7\u0003`\n\u0015(\u0011\u001eB{\u0005s\u0004Ra\u0014Bn\u0003GJ1A!8Z\u0005!IE/\u001a:bi>\u0014\bb\u0002BqE\u0001\u0007!1]\u0001\tSR,'/\u0019;peB)qJa7\u0003H!9!q\u001d\u0012A\u0002\u0005\r\u0016\u0001\u0003:bo\u0012+7/\u001a:\t\u000f\t-(\u00051\u0001\u0003n\u0006!bn\u001c8QCJ$\u0018\u000e^5p].+\u00170\u0011;ueN\u0004BaT,\u0003pB1\u0001G!=[\u0003CI1Aa=2\u0005\u0019!V\u000f\u001d7fe!9!q\u001f\u0012A\u0002\u0005\r\u0014AC7vi\u0006\u0014G.\u001a*po\"9!1 \u0012A\u0002\u0005\r\u0016A\u0003;bE2,G)Z:fe\u0002"
)
public class HadoopTableReader implements TableReader, CastSupport, SQLConfHelper, Logging {
   private final transient Seq attributes;
   private final transient Seq partitionKeys;
   private final transient TableDesc tableDesc;
   private final transient SparkSession sparkSession;
   private final Configuration hadoopConf;
   private final int _minSplitsPerRDD;
   private final Broadcast _broadcastedHadoopConf;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Iterator fillObject(final Iterator iterator, final Deserializer rawDeser, final Seq nonPartitionKeyAttrs, final InternalRow mutableRow, final Deserializer tableDeser) {
      return HadoopTableReader$.MODULE$.fillObject(iterator, rawDeser, nonPartitionKeyAttrs, mutableRow, tableDeser);
   }

   public static void initializeLocalJobConfFunc(final String path, final TableDesc tableDesc, final JobConf jobConf) {
      HadoopTableReader$.MODULE$.initializeLocalJobConfFunc(path, tableDesc, jobConf);
   }

   public static HiveInspectors.typeInfoConversions typeInfoConversions(final DataType dt) {
      return HadoopTableReader$.MODULE$.typeInfoConversions(dt);
   }

   public static DataType inspectorToDataType(final ObjectInspector inspector) {
      return HadoopTableReader$.MODULE$.inspectorToDataType(inspector);
   }

   public static ObjectInspector toInspector(final Expression expr) {
      return HadoopTableReader$.MODULE$.toInspector(expr);
   }

   public static ObjectInspector toInspector(final DataType dataType) {
      return HadoopTableReader$.MODULE$.toInspector(dataType);
   }

   public static Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      return HadoopTableReader$.MODULE$.wrap(row, wrappers, cache);
   }

   public static Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return HadoopTableReader$.MODULE$.wrap(row, wrappers, cache, dataTypes);
   }

   public static Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return HadoopTableReader$.MODULE$.wrap(a, oi, dataType);
   }

   public static Function3 unwrapperFor(final StructField field) {
      return HadoopTableReader$.MODULE$.unwrapperFor(field);
   }

   public static Function1 unwrapperFor(final ObjectInspector objectInspector) {
      return HadoopTableReader$.MODULE$.unwrapperFor(objectInspector);
   }

   public static DataType javaTypeToDataType(final Type clz) {
      return HadoopTableReader$.MODULE$.javaTypeToDataType(clz);
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

   public Object withSQLConf(final Seq pairs, final Function0 f) {
      return SQLConfHelper.withSQLConf$(this, pairs, f);
   }

   public Cast cast(final Expression child, final DataType dataType) {
      return CastSupport.cast$(this, child, dataType);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Seq attributes() {
      return this.attributes;
   }

   private Seq partitionKeys() {
      return this.partitionKeys;
   }

   private TableDesc tableDesc() {
      return this.tableDesc;
   }

   private SparkSession sparkSession() {
      return this.sparkSession;
   }

   private int _minSplitsPerRDD() {
      return this._minSplitsPerRDD;
   }

   private Broadcast _broadcastedHadoopConf() {
      return this._broadcastedHadoopConf;
   }

   public SQLConf conf() {
      return this.sparkSession().sessionState().conf();
   }

   public RDD makeRDDForTable(final Table hiveTable) {
      return this.makeRDDForTable(hiveTable, .MODULE$.classForName(this.tableDesc().getSerdeClassName(), .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3()), scala.None..MODULE$);
   }

   public RDD makeRDDForTable(final Table hiveTable, final Class deserializerClass, final Option filterOpt) {
      scala.Predef..MODULE$.assert(!hiveTable.isPartitioned(), () -> "makeRDDForTable() cannot be called on a partitioned table, since input formats may differ across partitions. Use makeRDDForPartitionedTable() instead.");
      TableDesc localTableDesc = this.tableDesc();
      Broadcast broadcastedHadoopConf = this._broadcastedHadoopConf();
      Path tablePath = hiveTable.getPath();
      String inputPathStr = this.applyFilterIfNeeded(tablePath, filterOpt);
      RDD hadoopRDD = this.createHadoopRDD(localTableDesc, inputPathStr);
      Seq attrsWithIndex = (Seq)this.attributes().zipWithIndex();
      SpecificInternalRow mutableRow = new SpecificInternalRow((Seq)this.attributes().map((x$1) -> x$1.dataType()));
      RDD deserializedHadoopRDD = hadoopRDD.mapPartitions((iter) -> {
         Configuration hconf = ((SerializableConfiguration)broadcastedHadoopConf.value()).value();
         Deserializer deserializer = (Deserializer)deserializerClass.getConstructor().newInstance();
         synchronized(DeserializerLock$.MODULE$){}

         try {
            deserializer.initialize(hconf, localTableDesc.getProperties());
         } catch (Throwable var10) {
            throw var10;
         }

         return HadoopTableReader$.MODULE$.fillObject(iter, deserializer, attrsWithIndex, mutableRow, deserializer);
      }, hadoopRDD.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(InternalRow.class));
      return deserializedHadoopRDD;
   }

   public RDD makeRDDForPartitionedTable(final Seq partitions) {
      scala.collection.immutable.Map partitionToDeserializer = ((IterableOnceOps)partitions.map((part) -> new Tuple2(part, part.getDeserializer().getClass()))).toMap(scala..less.colon.less..MODULE$.refl());
      return this.makeRDDForPartitionedTable(partitionToDeserializer, scala.None..MODULE$);
   }

   public RDD makeRDDForPartitionedTable(final scala.collection.immutable.Map partitionToDeserializer, final Option filterOpt) {
      Seq hivePartitionRDDs = ((IterableOnceOps)partitionToDeserializer.map((x0$1) -> {
         if (x0$1 != null) {
            Partition partition = (Partition)x0$1._1();
            Class partDeserializer = (Class)x0$1._2();
            PartitionDesc partDesc = Utilities.getPartitionDescFromTableDesc(this.tableDesc(), partition, true);
            Path partPath = partition.getDataLocation();
            String inputPathStr = this.applyFilterIfNeeded(partPath, filterOpt);
            LinkedHashMap partSpec = partDesc.getPartSpec();
            Properties partProps = partDesc.getProperties();
            String partColsDelimited = partProps.getProperty("partition_columns");
            ArraySeq partCols = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(partColsDelimited.trim().split("/")).toImmutableArraySeq();
            String[] partValues = partSpec == null ? (String[])scala.Array..MODULE$.fill(partCols.size(), () -> new String(), scala.reflect.ClassTag..MODULE$.apply(String.class)) : (String[])partCols.map((col) -> new String((String)partSpec.get(col))).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
            Broadcast broadcastedHiveConf = this._broadcastedHadoopConf();
            SpecificInternalRow mutableRow = new SpecificInternalRow((Seq)this.attributes().map((x$2x) -> x$2x.dataType()));
            Tuple2 var20 = ((IterableOps)this.attributes().zipWithIndex()).partition((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$makeRDDForPartitionedTable$6(this, x0$2)));
            if (var20 != null) {
               Seq partitionKeyAttrs = (Seq)var20._1();
               Seq nonPartitionKeyAttrs = (Seq)var20._2();
               Tuple2 var19 = new Tuple2(partitionKeyAttrs, nonPartitionKeyAttrs);
               Seq partitionKeyAttrs = (Seq)var19._1();
               Seq nonPartitionKeyAttrsx = (Seq)var19._2();
               this.fillPartitionKeys$1(partValues, mutableRow, partitionKeyAttrs);
               Properties tableProperties = this.tableDesc().getProperties();
               Seq avroSchemaProperties = (Seq)(new scala.collection.immutable..colon.colon(AvroTableProperties.SCHEMA_LITERAL, new scala.collection.immutable..colon.colon(AvroTableProperties.SCHEMA_URL, scala.collection.immutable.Nil..MODULE$))).map((x$4) -> x$4.getPropName());
               TableDesc localTableDesc = this.tableDesc();
               RDD qual$1 = this.createHadoopRDD(partDesc, inputPathStr);
               Function1 x$1 = (iter) -> {
                  Configuration hconf = ((SerializableConfiguration)broadcastedHiveConf.value()).value();
                  Deserializer deserializer = (Deserializer)partDeserializer.getConstructor().newInstance();
                  Properties props = new Properties(tableProperties);
                  ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(partProps).asScala().filterNot((x0$3) -> BoxesRunTime.boxToBoolean($anonfun$makeRDDForPartitionedTable$10(avroSchemaProperties, tableProperties, x0$3)))).foreach((x0$4) -> {
                     if (x0$4 != null) {
                        String key = (String)x0$4._1();
                        String value = (String)x0$4._2();
                        return props.setProperty(key, value);
                     } else {
                        throw new MatchError(x0$4);
                     }
                  });
                  synchronized(DeserializerLock$.MODULE$){}

                  try {
                     deserializer.initialize(hconf, props);
                  } catch (Throwable var20) {
                     throw var20;
                  }

                  Deserializer tableSerDe = (Deserializer)localTableDesc.getDeserializerClass().getConstructor().newInstance();
                  synchronized(DeserializerLock$.MODULE$){}

                  try {
                     tableSerDe.initialize(hconf, tableProperties);
                  } catch (Throwable var19) {
                     throw var19;
                  }

                  return HadoopTableReader$.MODULE$.fillObject(iter, deserializer, nonPartitionKeyAttrsx, mutableRow, tableSerDe);
               };
               boolean x$2 = qual$1.mapPartitions$default$2();
               return qual$1.mapPartitions(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(InternalRow.class));
            } else {
               throw new MatchError(var20);
            }
         } else {
            throw new MatchError(x0$1);
         }
      })).toSeq();
      return (RDD)(hivePartitionRDDs.size() == 0 ? new EmptyRDD(this.sparkSession().sparkContext(), scala.reflect.ClassTag..MODULE$.apply(InternalRow.class)) : new UnionRDD(((RDD)hivePartitionRDDs.apply(0)).context(), hivePartitionRDDs, scala.reflect.ClassTag..MODULE$.apply(InternalRow.class)));
   }

   private String applyFilterIfNeeded(final Path path, final Option filterOpt) {
      if (filterOpt instanceof Some var5) {
         PathFilter filter = (PathFilter)var5.value();
         FileSystem fs = path.getFileSystem(this.hadoopConf);
         String[] filteredFiles = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fs.listStatus(path, filter)), (x$5) -> x$5.getPath().toString(), scala.reflect.ClassTag..MODULE$.apply(String.class));
         return scala.Predef..MODULE$.wrapRefArray((Object[])filteredFiles).mkString(",");
      } else if (scala.None..MODULE$.equals(filterOpt)) {
         return path.toString();
      } else {
         throw new MatchError(filterOpt);
      }
   }

   private boolean compatibleWithNewHadoopRDD(final Class inputClass) {
      return InputFormat.class.isAssignableFrom(inputClass) && !inputClass.getName().equalsIgnoreCase("org.apache.hadoop.hive.hbase.HiveHBaseTableInputFormat");
   }

   private RDD createHadoopRDD(final TableDesc localTableDesc, final String inputPathStr) {
      Class inputFormatClazz = localTableDesc.getInputFileFormatClass();
      return this.compatibleWithNewHadoopRDD(inputFormatClazz) ? this.createNewHadoopRDD(localTableDesc, inputPathStr) : this.createOldHadoopRDD(localTableDesc, inputPathStr);
   }

   private RDD createHadoopRDD(final PartitionDesc partitionDesc, final String inputPathStr) {
      Class inputFormatClazz = partitionDesc.getInputFileFormatClass();
      return this.compatibleWithNewHadoopRDD(inputFormatClazz) ? this.createNewHadoopRDD(partitionDesc, inputPathStr) : this.createOldHadoopRDD(partitionDesc, inputPathStr);
   }

   private RDD createOldHadoopRDD(final TableDesc tableDesc, final String path) {
      Function1 initializeJobConfFunc = (jobConf) -> {
         $anonfun$createOldHadoopRDD$1(path, tableDesc, jobConf);
         return BoxedUnit.UNIT;
      };
      Class inputFormatClass = tableDesc.getInputFileFormatClass();
      return this.createOldHadoopRDD(inputFormatClass, initializeJobConfFunc);
   }

   private RDD createOldHadoopRDD(final PartitionDesc partitionDesc, final String path) {
      TableDesc var4 = partitionDesc.getTableDesc();
      Function1 initializeJobConfFunc = (jobConf) -> {
         $anonfun$createOldHadoopRDD$2(path, var4, jobConf);
         return BoxedUnit.UNIT;
      };
      Class inputFormatClass = partitionDesc.getInputFileFormatClass();
      return this.createOldHadoopRDD(inputFormatClass, initializeJobConfFunc);
   }

   private RDD createOldHadoopRDD(final Class inputFormatClass, final Function1 initializeJobConfFunc) {
      HadoopRDD rdd = new HadoopRDD(this.sparkSession().sparkContext(), this._broadcastedHadoopConf(), new Some(initializeJobConfFunc), inputFormatClass, Writable.class, Writable.class, this._minSplitsPerRDD(), this.conf().ignoreCorruptFiles(), this.conf().ignoreMissingFiles());
      return rdd.map((x$6) -> (Writable)x$6._2(), scala.reflect.ClassTag..MODULE$.apply(Writable.class));
   }

   private RDD createNewHadoopRDD(final TableDesc tableDesc, final String path) {
      JobConf newJobConf = new JobConf(this.hadoopConf);
      HadoopTableReader$.MODULE$.initializeLocalJobConfFunc(path, tableDesc, newJobConf);
      Class inputFormatClass = tableDesc.getInputFileFormatClass();
      return this.createNewHadoopRDD(inputFormatClass, newJobConf);
   }

   private RDD createNewHadoopRDD(final PartitionDesc partDesc, final String path) {
      JobConf newJobConf = new JobConf(this.hadoopConf);
      HadoopTableReader$.MODULE$.initializeLocalJobConfFunc(path, partDesc.getTableDesc(), newJobConf);
      Class inputFormatClass = partDesc.getInputFileFormatClass();
      return this.createNewHadoopRDD(inputFormatClass, newJobConf);
   }

   private RDD createNewHadoopRDD(final Class inputFormatClass, final JobConf jobConf) {
      NewHadoopRDD rdd = new NewHadoopRDD(this.sparkSession().sparkContext(), inputFormatClass, Writable.class, Writable.class, jobConf, this.conf().ignoreCorruptFiles(), this.conf().ignoreMissingFiles());
      return rdd.map((x$7) -> (Writable)x$7._2(), scala.reflect.ClassTag..MODULE$.apply(Writable.class));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeRDDForPartitionedTable$6(final HadoopTableReader $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         Attribute attr = (Attribute)x0$2._1();
         return $this.partitionKeys().contains(attr);
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$makeRDDForPartitionedTable$7(final HadoopTableReader $this, final InternalRow row$1, final String[] rawPartValues$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Attribute attr = (Attribute)x0$1._1();
         int ordinal = x0$1._2$mcI$sp();
         int partOrdinal = $this.partitionKeys().indexOf(attr);
         row$1.update(ordinal, $this.cast(org.apache.spark.sql.catalyst.expressions.Literal..MODULE$.apply(rawPartValues$1[partOrdinal]), attr.dataType()).eval((InternalRow)null));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private final void fillPartitionKeys$1(final String[] rawPartValues, final InternalRow row, final Seq partitionKeyAttrs$1) {
      partitionKeyAttrs$1.foreach((x0$1) -> {
         $anonfun$makeRDDForPartitionedTable$7(this, row, rawPartValues, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeRDDForPartitionedTable$10(final Seq avroSchemaProperties$1, final Properties tableProperties$1, final Tuple2 x0$3) {
      if (x0$3 == null) {
         throw new MatchError(x0$3);
      } else {
         String k = (String)x0$3._1();
         return avroSchemaProperties$1.contains(k) && tableProperties$1.containsKey(k);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createOldHadoopRDD$1(final String path$1, final TableDesc tableDesc$1, final JobConf jobConf) {
      HadoopTableReader$.MODULE$.initializeLocalJobConfFunc(path$1, tableDesc$1, jobConf);
   }

   // $FF: synthetic method
   public static final void $anonfun$createOldHadoopRDD$2(final String path$2, final TableDesc eta$0$1$1, final JobConf jobConf) {
      HadoopTableReader$.MODULE$.initializeLocalJobConfFunc(path$2, eta$0$1$1, jobConf);
   }

   public HadoopTableReader(final Seq attributes, final Seq partitionKeys, final TableDesc tableDesc, final SparkSession sparkSession, final Configuration hadoopConf) {
      this.attributes = attributes;
      this.partitionKeys = partitionKeys;
      this.tableDesc = tableDesc;
      this.sparkSession = sparkSession;
      this.hadoopConf = hadoopConf;
      CastSupport.$init$(this);
      SQLConfHelper.$init$(this);
      Logging.$init$(this);
      this._minSplitsPerRDD = sparkSession.sparkContext().isLocal() ? 0 : scala.math.package..MODULE$.max(hadoopConf.getInt("mapreduce.job.maps", 1), sparkSession.sparkContext().defaultMinPartitions());
      org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().appendS3AndSparkHadoopHiveConfigurations(sparkSession.sparkContext().conf(), hadoopConf);
      this._broadcastedHadoopConf = sparkSession.sparkContext().broadcast(new SerializableConfiguration(hadoopConf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
