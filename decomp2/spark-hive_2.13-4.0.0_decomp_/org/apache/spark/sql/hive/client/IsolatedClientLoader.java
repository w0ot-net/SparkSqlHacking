package org.apache.spark.sql.hive.client;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.hive.HiveUtils$;
import org.apache.spark.sql.internal.NonClosableMutableURLClassLoader;
import org.apache.spark.util.MutableURLClassLoader;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t=tA\u0002\u001c8\u0011\u0003I4I\u0002\u0004Fo!\u0005\u0011H\u0012\u0005\u0006'\u0006!\t!\u0016\u0005\u0006-\u0006!\ta\u0016\u0005\n\u0003k\f\u0011\u0013!C\u0001\u0003oD\u0011B!\u0004\u0002#\u0003%\tAa\u0004\t\u0013\tM\u0011!%A\u0005\u0002\tU\u0001\"\u0003B\r\u0003E\u0005I\u0011\u0001B\u000b\u0011\u001d\u0011Y\"\u0001C\u0001\u0005;AqA!\t\u0002\t\u0003\u0011\u0019\u0003C\u0004\u0003(\u0005!IA!\u000b\t\u0013\tU\u0012A1A\u0005\n\t]\u0002\u0002\u0003B(\u0003\u0001\u0006IA!\u000f\t\u0013\tE\u0013!%A\u0005\u0002\tM\u0003\"\u0003B,\u0003E\u0005I\u0011AA|\u0011%\u0011I&AI\u0001\n\u0003\u0011Y\u0006C\u0005\u0003`\u0005\t\n\u0011\"\u0001\u0003b!I!QM\u0001\u0012\u0002\u0013\u0005!q\r\u0005\n\u0005W\n\u0011\u0013!C\u0001\u0005+A\u0011B!\u001c\u0002#\u0003%\tA!\u0006\u0007\u000b\u0015;\u0004!O-\t\u0011i#\"Q1A\u0005\u0002mC\u0001b\u0019\u000b\u0003\u0002\u0003\u0006I\u0001\u0018\u0005\tIR\u0011)\u0019!C\u0001K\"A!\u000e\u0006B\u0001B\u0003%a\r\u0003\u0005l)\t\u0015\r\u0011\"\u0001m\u0011!)HC!A!\u0002\u0013i\u0007\u0002\u0003<\u0015\u0005\u000b\u0007I\u0011A<\t\u0013\u0005]AC!A!\u0002\u0013A\bBCA\r)\t\u0015\r\u0011\"\u0001\u0002\u001c!Q\u00111\u0007\u000b\u0003\u0002\u0003\u0006I!!\b\t\u0015\u0005UBC!b\u0001\n\u0003\t9\u0004\u0003\u0006\u0002@Q\u0011\t\u0011)A\u0005\u0003sA!\"!\u0011\u0015\u0005\u0003\u0005\u000b\u0011BA\"\u0011)\tI\u0005\u0006BC\u0002\u0013\u0005\u00111\n\u0005\u000b\u00033\"\"\u0011!Q\u0001\n\u00055\u0003BCA.)\t\u0015\r\u0011\"\u0001\u0002^!Q\u0011\u0011\r\u000b\u0003\u0002\u0003\u0006I!a\u0018\t\u0015\u0005\rDC!b\u0001\n\u0003\ti\u0006\u0003\u0006\u0002fQ\u0011\t\u0011)A\u0005\u0003?Baa\u0015\u000b\u0005\u0002\u0005\u001d\u0004\"CA?)\t\u0007I\u0011AA\u001c\u0011!\ty\b\u0006Q\u0001\n\u0005e\u0002bBAA)\u0011E\u00111\u0011\u0005\b\u0003\u0017#B\u0011CAG\u0011\u001d\t\u0019\n\u0006C\t\u0003+Cq!!'\u0015\t#\tY\n\u0003\u0006\u0002 R\u0011\r\u0011\"\u0001:\u0003CC\u0001\"a,\u0015A\u0003%\u00111\u0015\u0005\t\u0003c#B\u0011A\u001d\u00024\"A\u0011q\u0018\u000b\u0005\u0002e\n\t\r\u0003\u0006\u0002JR\u0001\r\u0011\"\u0001:\u0003\u0017D!\"a5\u0015\u0001\u0004%\t!OAk\u0011!\tY\u000e\u0006Q!\n\u00055\u0017\u0001F%t_2\fG/\u001a3DY&,g\u000e\u001e'pC\u0012,'O\u0003\u00029s\u000511\r\\5f]RT!AO\u001e\u0002\t!Lg/\u001a\u0006\u0003yu\n1a]9m\u0015\tqt(A\u0003ta\u0006\u00148N\u0003\u0002A\u0003\u00061\u0011\r]1dQ\u0016T\u0011AQ\u0001\u0004_J<\u0007C\u0001#\u0002\u001b\u00059$\u0001F%t_2\fG/\u001a3DY&,g\u000e\u001e'pC\u0012,'oE\u0002\u0002\u000f6\u0003\"\u0001S&\u000e\u0003%S\u0011AS\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0019&\u0013a!\u00118z%\u00164\u0007C\u0001(R\u001b\u0005y%B\u0001)>\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001*P\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u0007\u0006Qam\u001c:WKJ\u001c\u0018n\u001c8\u0015#a\u000bi.!9\u0002f\u0006\u001d\u0018\u0011^Av\u0003c\f\u0019\u0010\u0005\u0002E)M\u0019AcR'\u0002\u000fY,'o]5p]V\tA\f\u0005\u0002^A:\u0011AIX\u0005\u0003?^\nq\u0001]1dW\u0006<W-\u0003\u0002bE\nY\u0001*\u001b<f-\u0016\u00148/[8o\u0015\tyv'\u0001\u0005wKJ\u001c\u0018n\u001c8!\u0003%\u0019\b/\u0019:l\u0007>tg-F\u0001g!\t9\u0007.D\u0001>\u0013\tIWHA\u0005Ta\u0006\u00148nQ8oM\u0006Q1\u000f]1sW\u000e{gN\u001a\u0011\u0002\u0015!\fGm\\8q\u0007>tg-F\u0001n!\tq7/D\u0001p\u0015\t\u0001\u0018/\u0001\u0003d_:4'B\u0001:@\u0003\u0019A\u0017\rZ8pa&\u0011Ao\u001c\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8\u0002\u0017!\fGm\\8q\u0007>tg\rI\u0001\tKb,7MS1sgV\t\u0001\u0010E\u0003z\u0003\u0003\t9A\u0004\u0002{\u007f:\u00111P`\u0007\u0002y*\u0011Q\u0010V\u0001\u0007yI|w\u000e\u001e \n\u0003)K!aX%\n\t\u0005\r\u0011Q\u0001\u0002\u0004'\u0016\f(BA0J!\u0011\tI!a\u0005\u000e\u0005\u0005-!\u0002BA\u0007\u0003\u001f\t1A\\3u\u0015\t\t\t\"\u0001\u0003kCZ\f\u0017\u0002BA\u000b\u0003\u0017\u00111!\u0016*M\u0003%)\u00070Z2KCJ\u001c\b%\u0001\u0004d_:4\u0017nZ\u000b\u0003\u0003;\u0001\u0002\"a\b\u0002(\u00055\u0012Q\u0006\b\u0005\u0003C\t\u0019\u0003\u0005\u0002|\u0013&\u0019\u0011QE%\u0002\rA\u0013X\rZ3g\u0013\u0011\tI#a\u000b\u0003\u00075\u000b\u0007OC\u0002\u0002&%\u0003B!a\b\u00020%!\u0011\u0011GA\u0016\u0005\u0019\u0019FO]5oO\u000691m\u001c8gS\u001e\u0004\u0013aC5t_2\fG/[8o\u001f:,\"!!\u000f\u0011\u0007!\u000bY$C\u0002\u0002>%\u0013qAQ8pY\u0016\fg.\u0001\u0007jg>d\u0017\r^5p]>s\u0007%A\u000ftKN\u001c\u0018n\u001c8Ti\u0006$X-S:pY\u0006$\u0018n\u001c8Pm\u0016\u0014(/\u001b3f!\u0015A\u0015QIA\u001d\u0013\r\t9%\u0013\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u001f\t\f7/Z\"mCN\u001cHj\\1eKJ,\"!!\u0014\u0011\t\u0005=\u0013QK\u0007\u0003\u0003#RA!a\u0015\u0002\u0010\u0005!A.\u00198h\u0013\u0011\t9&!\u0015\u0003\u0017\rc\u0017m]:M_\u0006$WM]\u0001\u0011E\u0006\u001cXm\u00117bgNdu.\u00193fe\u0002\nab\u001d5be\u0016$\u0007K]3gSb,7/\u0006\u0002\u0002`A)\u00110!\u0001\u0002.\u0005y1\u000f[1sK\u0012\u0004&/\u001a4jq\u0016\u001c\b%A\bcCJ\u0014\u0018.\u001a:Qe\u00164\u0017\u000e_3t\u0003A\u0011\u0017M\u001d:jKJ\u0004&/\u001a4jq\u0016\u001c\b\u0005F\u000bY\u0003S\nY'!\u001c\u0002p\u0005E\u00141OA;\u0003o\nI(a\u001f\t\u000biC\u0003\u0019\u0001/\t\u000b\u0011D\u0003\u0019\u00014\t\u000b-D\u0003\u0019A7\t\u000fYD\u0003\u0013!a\u0001q\"I\u0011\u0011\u0004\u0015\u0011\u0002\u0003\u0007\u0011Q\u0004\u0005\n\u0003kA\u0003\u0013!a\u0001\u0003sA\u0011\"!\u0011)!\u0003\u0005\r!a\u0011\t\u0013\u0005%\u0003\u0006%AA\u0002\u00055\u0003\"CA.QA\u0005\t\u0019AA0\u0011%\t\u0019\u0007\u000bI\u0001\u0002\u0004\ty&A\ftKN\u001c\u0018n\u001c8Ti\u0006$X-S:pY\u0006$\u0018n\u001c8P]\u0006A2/Z:tS>t7\u000b^1uK&\u001bx\u000e\\1uS>twJ\u001c\u0011\u0002\u000f\u0005dGNS1sgV\u0011\u0011Q\u0011\t\u0006\u0011\u0006\u001d\u0015qA\u0005\u0004\u0003\u0013K%!B!se\u0006L\u0018!D5t'\"\f'/\u001a3DY\u0006\u001c8\u000f\u0006\u0003\u0002:\u0005=\u0005bBAIY\u0001\u0007\u0011QF\u0001\u0005]\u0006lW-\u0001\bjg\n\u000b'O]5fe\u000ec\u0017m]:\u0015\t\u0005e\u0012q\u0013\u0005\b\u0003#k\u0003\u0019AA\u0017\u0003-\u0019G.Y:t)>\u0004\u0016\r\u001e5\u0015\t\u00055\u0012Q\u0014\u0005\b\u0003#s\u0003\u0019AA\u0017\u0003-\u0019G.Y:t\u0019>\fG-\u001a:\u0016\u0005\u0005\r\u0006\u0003BAS\u0003Wk!!a*\u000b\u0007\u0005%V(\u0001\u0003vi&d\u0017\u0002BAW\u0003O\u0013Q#T;uC\ndW-\u0016*M\u00072\f7o\u001d'pC\u0012,'/\u0001\u0007dY\u0006\u001c8\u000fT8bI\u0016\u0014\b%\u0001\u0004bI\u0012T\u0015M\u001d\u000b\u0005\u0003k\u000bY\fE\u0002I\u0003oK1!!/J\u0005\u0011)f.\u001b;\t\u000f\u0005u\u0016\u00071\u0001\u0002\b\u0005!\u0001/\u0019;i\u00031\u0019'/Z1uK\u000ec\u0017.\u001a8u)\t\t\u0019\rE\u0002E\u0003\u000bL1!a28\u0005)A\u0015N^3DY&,g\u000e^\u0001\u000bG\u0006\u001c\u0007.\u001a3ISZ,WCAAg!\rA\u0015qZ\u0005\u0004\u0003#L%aA!os\u0006q1-Y2iK\u0012D\u0015N^3`I\u0015\fH\u0003BA[\u0003/D\u0011\"!75\u0003\u0003\u0005\r!!4\u0002\u0007a$\u0013'A\u0006dC\u000eDW\r\u001a%jm\u0016\u0004\u0003bBAp\u0007\u0001\u0007\u0011QF\u0001\u0015Q&4X-T3uCN$xN]3WKJ\u001c\u0018n\u001c8\t\u000f\u0005\r8\u00011\u0001\u0002.\u0005i\u0001.\u00193p_B4VM]:j_:DQ\u0001Z\u0002A\u0002\u0019DQa[\u0002A\u00025D\u0011\"!\u0007\u0004!\u0003\u0005\r!!\b\t\u0013\u000558\u0001%AA\u0002\u0005=\u0018aB5wsB\u000bG\u000f\u001b\t\u0006\u0011\u0006\u0015\u0013Q\u0006\u0005\n\u00037\u001a\u0001\u0013!a\u0001\u0003?B\u0011\"a\u0019\u0004!\u0003\u0005\r!a\u0018\u0002)\u0019|'OV3sg&|g\u000e\n3fM\u0006,H\u000e\u001e\u00136+\t\tIP\u000b\u0003\u0002\u001e\u0005m8FAA\u007f!\u0011\tyP!\u0003\u000e\u0005\t\u0005!\u0002\u0002B\u0002\u0005\u000b\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t\u001d\u0011*\u0001\u0006b]:|G/\u0019;j_:LAAa\u0003\u0003\u0002\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002)\u0019|'OV3sg&|g\u000e\n3fM\u0006,H\u000e\u001e\u00137+\t\u0011\tB\u000b\u0003\u0002p\u0006m\u0018\u0001\u00064peZ+'o]5p]\u0012\"WMZ1vYR$s'\u0006\u0002\u0003\u0018)\"\u0011qLA~\u0003Q1wN\u001d,feNLwN\u001c\u0013eK\u001a\fW\u000f\u001c;%q\u0005Y\u0001.\u001b<f-\u0016\u00148/[8o)\ra&q\u0004\u0005\u00075\"\u0001\r!!\f\u00025M,\b\u000f]8siND\u0015\rZ8paNC\u0017\rZ3e\u00072LWM\u001c;\u0015\t\u0005e\"Q\u0005\u0005\b\u0003GL\u0001\u0019AA\u0017\u0003=!wn\u001e8m_\u0006$g+\u001a:tS>tG#\u0003=\u0003,\t5\"q\u0006B\u0019\u0011\u0015Q&\u00021\u0001]\u0011\u001d\t\u0019O\u0003a\u0001\u0003[Aq!!<\u000b\u0001\u0004\ty\u000fC\u0004\u00034)\u0001\r!!\f\u0002\u0017I,Wn\u001c;f%\u0016\u0004xn]\u0001\u0011e\u0016\u001cx\u000e\u001c<fIZ+'o]5p]N,\"A!\u000f\u0011\u000f\tm\"Q\tB%q6\u0011!Q\b\u0006\u0005\u0005\u007f\u0011\t%A\u0004nkR\f'\r\\3\u000b\u0007\t\r\u0013*\u0001\u0006d_2dWm\u0019;j_:LAAa\u0012\u0003>\t9\u0001*Y:i\u001b\u0006\u0004\bC\u0002%\u0003Lq\u000bi#C\u0002\u0003N%\u0013a\u0001V;qY\u0016\u0014\u0014!\u0005:fg>dg/\u001a3WKJ\u001c\u0018n\u001c8tA\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*\"A!\u0016+\u0007a\fY0A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\tu#\u0006BA\u001d\u0003w\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:TC\u0001B2U\u0011\t\u0019%a?\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00139+\t\u0011IG\u000b\u0003\u0002N\u0005m\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013(\u0001\u000f%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%\r\u0019"
)
public class IsolatedClientLoader implements Logging {
   private final package.HiveVersion version;
   private final SparkConf sparkConf;
   private final Configuration hadoopConf;
   private final Seq execJars;
   private final Map config;
   private final boolean isolationOn;
   private final ClassLoader baseClassLoader;
   private final Seq sharedPrefixes;
   private final Seq barrierPrefixes;
   private final boolean sessionStateIsolationOn;
   private final MutableURLClassLoader classLoader;
   private Object cachedHive;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Seq $lessinit$greater$default$10() {
      return IsolatedClientLoader$.MODULE$.$lessinit$greater$default$10();
   }

   public static Seq $lessinit$greater$default$9() {
      return IsolatedClientLoader$.MODULE$.$lessinit$greater$default$9();
   }

   public static ClassLoader $lessinit$greater$default$8() {
      return IsolatedClientLoader$.MODULE$.$lessinit$greater$default$8();
   }

   public static Option $lessinit$greater$default$7() {
      return IsolatedClientLoader$.MODULE$.$lessinit$greater$default$7();
   }

   public static boolean $lessinit$greater$default$6() {
      return IsolatedClientLoader$.MODULE$.$lessinit$greater$default$6();
   }

   public static Map $lessinit$greater$default$5() {
      return IsolatedClientLoader$.MODULE$.$lessinit$greater$default$5();
   }

   public static Seq $lessinit$greater$default$4() {
      return IsolatedClientLoader$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean supportsHadoopShadedClient(final String hadoopVersion) {
      return IsolatedClientLoader$.MODULE$.supportsHadoopShadedClient(hadoopVersion);
   }

   public static package.HiveVersion hiveVersion(final String version) {
      return IsolatedClientLoader$.MODULE$.hiveVersion(version);
   }

   public static Seq forVersion$default$8() {
      return IsolatedClientLoader$.MODULE$.forVersion$default$8();
   }

   public static Seq forVersion$default$7() {
      return IsolatedClientLoader$.MODULE$.forVersion$default$7();
   }

   public static Option forVersion$default$6() {
      return IsolatedClientLoader$.MODULE$.forVersion$default$6();
   }

   public static Map forVersion$default$5() {
      return IsolatedClientLoader$.MODULE$.forVersion$default$5();
   }

   public static IsolatedClientLoader forVersion(final String hiveMetastoreVersion, final String hadoopVersion, final SparkConf sparkConf, final Configuration hadoopConf, final Map config, final Option ivyPath, final Seq sharedPrefixes, final Seq barrierPrefixes) {
      return IsolatedClientLoader$.MODULE$.forVersion(hiveMetastoreVersion, hadoopVersion, sparkConf, hadoopConf, config, ivyPath, sharedPrefixes, barrierPrefixes);
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public package.HiveVersion version() {
      return this.version;
   }

   public SparkConf sparkConf() {
      return this.sparkConf;
   }

   public Configuration hadoopConf() {
      return this.hadoopConf;
   }

   public Seq execJars() {
      return this.execJars;
   }

   public Map config() {
      return this.config;
   }

   public boolean isolationOn() {
      return this.isolationOn;
   }

   public ClassLoader baseClassLoader() {
      return this.baseClassLoader;
   }

   public Seq sharedPrefixes() {
      return this.sharedPrefixes;
   }

   public Seq barrierPrefixes() {
      return this.barrierPrefixes;
   }

   public boolean sessionStateIsolationOn() {
      return this.sessionStateIsolationOn;
   }

   public URL[] allJars() {
      return (URL[])this.execJars().toArray(.MODULE$.apply(URL.class));
   }

   public boolean isSharedClass(final String name) {
      boolean isHadoopClass = name.startsWith("org.apache.hadoop.") && !name.startsWith("org.apache.hadoop.hive.");
      return name.startsWith("org.slf4j") || name.startsWith("org.apache.log4j") || name.startsWith("org.apache.logging.log4j") || name.startsWith("org.apache.spark.") || isHadoopClass || name.startsWith("scala.") || name.startsWith("java.") || name.startsWith("javax.sql.") || this.sharedPrefixes().exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$isSharedClass$1(name, x$1)));
   }

   public boolean isBarrierClass(final String name) {
      return name.startsWith(HiveClientImpl.class.getName()) || name.startsWith(Shim.class.getName()) || name.startsWith(ShimLoader.class.getName()) || this.barrierPrefixes().exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$isBarrierClass$1(name, x$1)));
   }

   public String classToPath(final String name) {
      return name.replaceAll("\\.", "/") + ".class";
   }

   public MutableURLClassLoader classLoader() {
      return this.classLoader;
   }

   public synchronized void addJar(final URL path) {
      this.classLoader().addURL(path);
   }

   public synchronized HiveClient createClient() {
      Option warehouseDir = scala.Option..MODULE$.apply(this.hadoopConf().get("hive.metastore.warehouse.dir"));
      if (!this.isolationOn()) {
         return new HiveClientImpl(this.version(), warehouseDir, this.sparkConf(), this.hadoopConf(), this.config(), this.baseClassLoader(), this);
      } else {
         this.logDebug((Function0)(() -> "Initializing the logger to avoid disaster..."));
         ClassLoader origLoader = Thread.currentThread().getContextClassLoader();
         Thread.currentThread().setContextClassLoader(this.classLoader());

         HiveClient var12;
         try {
            var12 = (HiveClient)((Constructor)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.classLoader().loadClass(HiveClientImpl.class.getName()).getConstructors()))).newInstance(this.version(), warehouseDir, this.sparkConf(), this.hadoopConf(), this.config(), this.classLoader(), this);
         } catch (InvocationTargetException var10) {
            Throwable var5 = var10.getCause();
            if (var5 instanceof NoClassDefFoundError) {
               NoClassDefFoundError var6 = (NoClassDefFoundError)var5;
               throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.loadHiveClientCausesNoClassDefFoundError(var6, this.execJars(), HiveUtils$.MODULE$.HIVE_METASTORE_JARS().key(), var10);
            }

            throw var10;
         } finally {
            var12 = Thread.currentThread();
            ((Thread)var12).setContextClassLoader(origLoader);
         }

         return var12;
      }
   }

   public Object cachedHive() {
      return this.cachedHive;
   }

   public void cachedHive_$eq(final Object x$1) {
      this.cachedHive = x$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isSharedClass$1(final String name$1, final String x$1) {
      return name$1.startsWith(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isBarrierClass$1(final String name$2, final String x$1) {
      return name$2.startsWith(x$1);
   }

   public IsolatedClientLoader(final package.HiveVersion version, final SparkConf sparkConf, final Configuration hadoopConf, final Seq execJars, final Map config, final boolean isolationOn, final Option sessionStateIsolationOverride, final ClassLoader baseClassLoader, final Seq sharedPrefixes, final Seq barrierPrefixes) {
      this.version = version;
      this.sparkConf = sparkConf;
      this.hadoopConf = hadoopConf;
      this.execJars = execJars;
      this.config = config;
      this.isolationOn = isolationOn;
      this.baseClassLoader = baseClassLoader;
      this.sharedPrefixes = sharedPrefixes;
      this.barrierPrefixes = barrierPrefixes;
      Logging.$init$(this);
      this.sessionStateIsolationOn = BoxesRunTime.unboxToBoolean(sessionStateIsolationOverride.getOrElse((JFunction0.mcZ.sp)() -> this.isolationOn()));
      Object var10001;
      if (isolationOn) {
         ClassLoader platformCL = (ClassLoader)ClassLoader.class.getMethod("getPlatformClassLoader").invoke((Object)null);
         scala.Predef..MODULE$.assert(scala.util.Try..MODULE$.apply(() -> platformCL.loadClass("org.apache.hadoop.hive.conf.HiveConf")).isFailure());
         var10001 = new URLClassLoader(platformCL) {
            // $FF: synthetic field
            private final IsolatedClientLoader $outer;

            public Class loadClass(final String name, final boolean resolve) {
               Class loaded = this.findLoadedClass(name);
               return loaded == null ? this.doLoadClass(name, resolve) : loaded;
            }

            public Class doLoadClass(final String name, final boolean resolve) {
               if (this.$outer.isBarrierClass(name)) {
                  byte[] bytes = (byte[])org.apache.spark.util.Utils..MODULE$.tryWithResource(() -> this.$outer.baseClassLoader().getResourceAsStream(this.$outer.classToPath(name)), (x$1) -> IOUtils.toByteArray(x$1));
                  this.$outer.logDebug((Function0)(() -> "custom defining: " + name + " - " + Arrays.hashCode(bytes)));
                  return this.defineClass(name, bytes, 0, bytes.length);
               } else if (!this.$outer.isSharedClass(name)) {
                  this.$outer.logDebug((Function0)(() -> "hive class: " + name + " - " + this.getResource(this.$outer.classToPath(name))));
                  return super.loadClass(name, resolve);
               } else {
                  this.$outer.logDebug((Function0)(() -> "shared class: " + name));

                  Class var10000;
                  try {
                     var10000 = this.$outer.baseClassLoader().loadClass(name);
                  } catch (ClassNotFoundException var4) {
                     var10000 = super.loadClass(name, resolve);
                  }

                  return var10000;
               }
            }

            public {
               if (IsolatedClientLoader.this == null) {
                  throw null;
               } else {
                  this.$outer = IsolatedClientLoader.this;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      } else {
         var10001 = baseClassLoader;
      }

      ClassLoader isolatedClassLoader = (ClassLoader)var10001;
      this.classLoader = new NonClosableMutableURLClassLoader(isolatedClassLoader);
      this.cachedHive = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
