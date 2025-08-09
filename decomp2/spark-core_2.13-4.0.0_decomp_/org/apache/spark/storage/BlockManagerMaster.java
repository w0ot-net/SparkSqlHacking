package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.BuildFrom;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tUg!\u0002\u00192\u0001MJ\u0004\u0002\u0003$\u0001\u0005\u0003\u0007I\u0011\u0001%\t\u0011=\u0003!\u00111A\u0005\u0002AC\u0001B\u0016\u0001\u0003\u0002\u0003\u0006K!\u0013\u0005\t/\u0002\u0011\t\u0019!C\u0001\u0011\"A\u0001\f\u0001BA\u0002\u0013\u0005\u0011\f\u0003\u0005\\\u0001\t\u0005\t\u0015)\u0003J\u0011!a\u0006A!A!\u0002\u0013i\u0006\u0002C1\u0001\u0005\u0003\u0005\u000b\u0011\u00022\t\u000b\u0015\u0004A\u0011\u00014\t\u000f5\u0004!\u0019!C\u0001]\"1!\u000f\u0001Q\u0001\n=DQa\u001d\u0001\u0005\u0002QDq!!\u0002\u0001\t\u0003\t9\u0001C\u0004\u0002 \u0001!\t!!\t\t\u000f\u00055\u0003\u0001\"\u0001\u0002P!9\u00111\u000b\u0001\u0005\u0002\u0005U\u0003\"CA>\u0001E\u0005I\u0011AA?\u0011\u001d\t\u0019\n\u0001C\u0001\u0003+Cq!!.\u0001\t\u0003\t9\fC\u0004\u0002F\u0002!\t!a2\t\u000f\u0005=\u0007\u0001\"\u0001\u0002R\"9\u0011Q\u001b\u0001\u0005\u0002\u0005]\u0007bBAo\u0001\u0011\u0005\u0011q\u001c\u0005\b\u0003+\u0004A\u0011AAz\u0011\u001d\u0011\t\u0001\u0001C\u0001\u0005\u0007AqAa\u0002\u0001\t\u0003\u0011I\u0001C\u0004\u0003\u000e\u0001!\tAa\u0004\t\u000f\t\u0015\u0002\u0001\"\u0001\u0003(!9!Q\u0006\u0001\u0005\u0002\t=\u0002b\u0002B\u001c\u0001\u0011\u0005!\u0011\b\u0005\b\u0005{\u0001A\u0011\u0001B \u0011\u001d\u0011I\u0005\u0001C\u0001\u0005\u0017BqAa\u0015\u0001\t\u0003\u0011)\u0006C\u0004\u0003b\u0001!\tAa\u0019\t\u000f\tE\u0004\u0001\"\u0001\u0003t!9!Q\u0010\u0001\u0005\u0002\t}\u0004\"\u0003BH\u0001E\u0005I\u0011AA?\u0011\u001d\u0011\t\n\u0001C\u0001\u0005'CqAa)\u0001\t\u0003\u0011)\u000bC\u0004\u0003(\u0002!IA!+\b\u0011\tU\u0016\u0007#\u00014\u0005o3q\u0001M\u0019\t\u0002M\u0012I\f\u0003\u0004fU\u0011\u0005!1\u0018\u0005\n\u0005{S#\u0019!C\u0001\u0005\u007fC\u0001Ba4+A\u0003%!\u0011\u0019\u0005\n\u0005#T#\u0019!C\u0001\u0005\u007fC\u0001Ba5+A\u0003%!\u0011\u0019\u0002\u0013\u00052|7m['b]\u0006<WM]'bgR,'O\u0003\u00023g\u000591\u000f^8sC\u001e,'B\u0001\u001b6\u0003\u0015\u0019\b/\u0019:l\u0015\t1t'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002q\u0005\u0019qN]4\u0014\u0007\u0001Q\u0004\t\u0005\u0002<}5\tAHC\u0001>\u0003\u0015\u00198-\u00197b\u0013\tyDH\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007N\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u000b\n\u0013q\u0001T8hO&tw-\u0001\bee&4XM]#oIB|\u0017N\u001c;\u0004\u0001U\t\u0011\n\u0005\u0002K\u001b6\t1J\u0003\u0002Mg\u0005\u0019!\u000f]2\n\u00059[%A\u0004*qG\u0016sG\r]8j]R\u0014VMZ\u0001\u0013IJLg/\u001a:F]\u0012\u0004x.\u001b8u?\u0012*\u0017\u000f\u0006\u0002R)B\u00111HU\u0005\u0003'r\u0012A!\u00168ji\"9QKAA\u0001\u0002\u0004I\u0015a\u0001=%c\u0005yAM]5wKJ,e\u000e\u001a9pS:$\b%A\fee&4XM\u001d%fCJ$(-Z1u\u000b:$\u0007k\\5oi\u0006YBM]5wKJDU-\u0019:uE\u0016\fG/\u00128e!>Lg\u000e^0%KF$\"!\u0015.\t\u000fU+\u0011\u0011!a\u0001\u0013\u0006ABM]5wKJDU-\u0019:uE\u0016\fG/\u00128e!>Lg\u000e\u001e\u0011\u0002\t\r|gN\u001a\t\u0003=~k\u0011aM\u0005\u0003AN\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\u0011%\u001cHI]5wKJ\u0004\"aO2\n\u0005\u0011d$a\u0002\"p_2,\u0017M\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\u001dL'n\u001b7\u0011\u0005!\u0004Q\"A\u0019\t\u000b\u0019K\u0001\u0019A%\t\u000b]K\u0001\u0019A%\t\u000bqK\u0001\u0019A/\t\u000b\u0005L\u0001\u0019\u00012\u0002\u000fQLW.Z8viV\tq\u000e\u0005\u0002Ka&\u0011\u0011o\u0013\u0002\u000b%B\u001cG+[7f_V$\u0018\u0001\u0003;j[\u0016|W\u000f\u001e\u0011\u0002\u001dI,Wn\u001c<f\u000bb,7-\u001e;peR\u0011\u0011+\u001e\u0005\u0006m2\u0001\ra^\u0001\u0007Kb,7-\u00133\u0011\u0005a|hBA=~!\tQH(D\u0001|\u0015\tax)\u0001\u0004=e>|GOP\u0005\u0003}r\na\u0001\u0015:fI\u00164\u0017\u0002BA\u0001\u0003\u0007\u0011aa\u0015;sS:<'B\u0001@=\u0003e!WmY8n[&\u001c8/[8o\u00052|7m['b]\u0006<WM]:\u0015\u0007E\u000bI\u0001C\u0004\u0002\f5\u0001\r!!\u0004\u0002\u0017\u0015DXmY;u_JLEm\u001d\t\u0006\u0003\u001f\tIb\u001e\b\u0005\u0003#\t)BD\u0002{\u0003'I\u0011!P\u0005\u0004\u0003/a\u0014a\u00029bG.\fw-Z\u0005\u0005\u00037\tiBA\u0002TKFT1!a\u0006=\u0003q9W\r\u001e*fa2L7-\u0019;f\u0013:4wNR8s%\u0012#%\t\\8dWN$B!a\t\u0002DA1\u0011qBA\r\u0003K\u0001B!a\n\u0002>9!\u0011\u0011FA\u001d\u001d\u0011\tY#a\u000e\u000f\t\u00055\u0012Q\u0007\b\u0005\u0003_\t\u0019DD\u0002{\u0003cI\u0011\u0001O\u0005\u0003m]J!\u0001N\u001b\n\u0005I\u001a\u0014bAA\u001ec\u0005!\"\t\\8dW6\u000bg.Y4fe6+7o]1hKNLA!a\u0010\u0002B\tq!+\u001a9mS\u000e\fG/\u001a\"m_\u000e\\'bAA\u001ec!9\u0011Q\t\bA\u0002\u0005\u001d\u0013A\u00042m_\u000e\\W*\u00198bO\u0016\u0014\u0018\n\u001a\t\u0004Q\u0006%\u0013bAA&c\tq!\t\\8dW6\u000bg.Y4fe&#\u0017a\u0005:f[>4X-\u0012=fGV$xN]!ts:\u001cGcA)\u0002R!)ao\u0004a\u0001o\u0006!\"/Z4jgR,'O\u00117pG.l\u0015M\\1hKJ$b\"a\u0012\u0002X\u0005m\u0013QMA8\u0003g\n9\bC\u0004\u0002ZA\u0001\r!a\u0012\u0002\u0005%$\u0007bBA/!\u0001\u0007\u0011qL\u0001\nY>\u001c\u0017\r\u001c#jeN\u0004BaOA1o&\u0019\u00111\r\u001f\u0003\u000b\u0005\u0013(/Y=\t\u000f\u0005\u001d\u0004\u00031\u0001\u0002j\u0005\u0001R.\u0019=P]\"+\u0017\r]'f[NK'0\u001a\t\u0004w\u0005-\u0014bAA7y\t!Aj\u001c8h\u0011\u001d\t\t\b\u0005a\u0001\u0003S\n\u0011#\\1y\u001f\u001a4\u0007*Z1q\u001b\u0016l7+\u001b>f\u0011\u0019\t)\b\u0005a\u0001\u0013\u0006y1\u000f^8sC\u001e,WI\u001c3q_&tG\u000f\u0003\u0005\u0002zA\u0001\n\u00111\u0001c\u00031I7OU3SK\u001eL7\u000f^3s\u0003y\u0011XmZ5ti\u0016\u0014(\t\\8dW6\u000bg.Y4fe\u0012\"WMZ1vYR$c'\u0006\u0002\u0002\u0000)\u001a!-!!,\u0005\u0005\r\u0005\u0003BAC\u0003\u001fk!!a\"\u000b\t\u0005%\u00151R\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!$=\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003#\u000b9IA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fq\"\u001e9eCR,'\t\\8dW&sgm\u001c\u000b\fE\u0006]\u0015\u0011TAR\u0003[\u000b\t\fC\u0004\u0002FI\u0001\r!a\u0012\t\u000f\u0005m%\u00031\u0001\u0002\u001e\u00069!\r\\8dW&#\u0007c\u00015\u0002 &\u0019\u0011\u0011U\u0019\u0003\u000f\tcwnY6JI\"9\u0011Q\u0015\nA\u0002\u0005\u001d\u0016\u0001D:u_J\fw-\u001a'fm\u0016d\u0007c\u00015\u0002*&\u0019\u00111V\u0019\u0003\u0019M#xN]1hK2+g/\u001a7\t\u000f\u0005=&\u00031\u0001\u0002j\u00059Q.Z7TSj,\u0007bBAZ%\u0001\u0007\u0011\u0011N\u0001\tI&\u001c8nU5{K\u00061R\u000f\u001d3bi\u0016\u0014F\t\u0012\"m_\u000e\\G+Y:l\u0013:4w\u000eF\u0003R\u0003s\u000b\t\rC\u0004\u0002\u001cN\u0001\r!a/\u0011\u0007!\fi,C\u0002\u0002@F\u0012!B\u0015#E\u00052|7m[%e\u0011\u001d\t\u0019m\u0005a\u0001\u0003S\na\u0001^1tW&#\u0017\u0001G;qI\u0006$XM\u0015#E\u00052|7m\u001b,jg&\u0014\u0017\u000e\\5usR)\u0011+!3\u0002L\"9\u00111\u0019\u000bA\u0002\u0005%\u0004BBAg)\u0001\u0007!-A\u0004wSNL'\r\\3\u0002#%\u001c(\u000b\u0012#CY>\u001c7NV5tS\ndW\rF\u0002c\u0003'Dq!a'\u0016\u0001\u0004\tY,\u0001\u0007hKRdunY1uS>t7\u000f\u0006\u0003\u0002Z\u0006m\u0007CBA\b\u00033\t9\u0005C\u0004\u0002\u001cZ\u0001\r!!(\u0002+\u001d,G\u000fT8dCRLwN\\:B]\u0012\u001cF/\u0019;vgR1\u0011\u0011]Aw\u0003_\u0004RaOAr\u0003OL1!!:=\u0005\u0019y\u0005\u000f^5p]B!\u0011qEAu\u0013\u0011\tY/!\u0011\u0003/\tcwnY6M_\u000e\fG/[8og\u0006sGm\u0015;biV\u001c\bbBAN/\u0001\u0007\u0011Q\u0014\u0005\u0007\u0003c<\u0002\u0019A<\u0002\u001bI,\u0017/^3ti\u0016\u0014\bj\\:u)\u0011\t)0a?\u0011\r\u0005=\u0011q_Am\u0013\u0011\tI0!\b\u0003\u0015%sG-\u001a=fIN+\u0017\u000fC\u0004\u0002~b\u0001\r!a@\u0002\u0011\tdwnY6JIN\u0004RaOA1\u0003;\u000b\u0001bY8oi\u0006Lgn\u001d\u000b\u0004E\n\u0015\u0001bBAN3\u0001\u0007\u0011QT\u0001\tO\u0016$\b+Z3sgR!\u0011\u0011\u001cB\u0006\u0011\u001d\t)E\u0007a\u0001\u0003\u000f\nQdZ3u'\",hM\u001a7f!V\u001c\b.T3sO\u0016\u0014Hj\\2bi&|gn\u001d\u000b\u0007\u00033\u0014\tBa\u0007\t\u000f\tM1\u00041\u0001\u0003\u0016\u0005\u0001b.^7NKJ<WM]:OK\u0016$W\r\u001a\t\u0004w\t]\u0011b\u0001B\ry\t\u0019\u0011J\u001c;\t\u000f\tu1\u00041\u0001\u0003 \u0005i\u0001n\\:ugR{g)\u001b7uKJ\u0004B\u0001\u001fB\u0011o&!!1EA\u0002\u0005\r\u0019V\r^\u0001 e\u0016lwN^3TQV4g\r\\3QkNDW*\u001a:hKJdunY1uS>tGcA)\u0003*!1!1\u0006\u000fA\u0002]\fA\u0001[8ti\u00061r-\u001a;Fq\u0016\u001cW\u000f^8s\u000b:$\u0007o\\5oiJ+g\r\u0006\u0003\u00032\tM\u0002\u0003B\u001e\u0002d&CaA!\u000e\u001e\u0001\u00049\u0018AC3yK\u000e,Ho\u001c:JI\u0006Y!/Z7pm\u0016\u0014En\\2l)\r\t&1\b\u0005\b\u00037s\u0002\u0019AAO\u0003%\u0011X-\\8wKJ#G\rF\u0003R\u0005\u0003\u0012)\u0005C\u0004\u0003D}\u0001\rA!\u0006\u0002\u000bI$G-\u00133\t\r\t\u001ds\u00041\u0001c\u0003!\u0011Gn\\2lS:<\u0017!\u0004:f[>4Xm\u00155vM\u001adW\rF\u0003R\u0005\u001b\u0012\t\u0006C\u0004\u0003P\u0001\u0002\rA!\u0006\u0002\u0013MDWO\u001a4mK&#\u0007B\u0002B$A\u0001\u0007!-A\bsK6|g/\u001a\"s_\u0006$7-Y:u)\u001d\t&q\u000bB.\u0005?BqA!\u0017\"\u0001\u0004\tI'A\u0006ce>\fGmY1ti&#\u0007B\u0002B/C\u0001\u0007!-\u0001\tsK6|g/\u001a$s_6l\u0015m\u001d;fe\"1!qI\u0011A\u0002\t\fqbZ3u\u001b\u0016lwN]=Ti\u0006$Xo]\u000b\u0003\u0005K\u0002r\u0001\u001fB4\u0003\u000f\u0012Y'\u0003\u0003\u0003j\u0005\r!aA'baB91H!\u001c\u0002j\u0005%\u0014b\u0001B8y\t1A+\u001e9mKJ\n\u0001cZ3u'R|'/Y4f'R\fG/^:\u0016\u0005\tU\u0004#B\u001e\u0002b\t]\u0004c\u00015\u0003z%\u0019!1P\u0019\u0003\u001bM#xN]1hKN#\u0018\r^;t\u000399W\r\u001e\"m_\u000e\\7\u000b^1ukN$bA!!\u0003\n\n-\u0005c\u0002=\u0003h\u0005\u001d#1\u0011\t\u0004Q\n\u0015\u0015b\u0001BDc\tY!\t\\8dWN#\u0018\r^;t\u0011\u001d\tY\n\na\u0001\u0003;C\u0001B!$%!\u0003\u0005\rAY\u0001\u0014CN\\7\u000b^8sC\u001e,WI\u001c3q_&tGo]\u0001\u0019O\u0016$(\t\\8dWN#\u0018\r^;tI\u0011,g-Y;mi\u0012\u0012\u0014aE4fi6\u000bGo\u00195j]\u001e\u0014En\\2l\u0013\u0012\u001cHC\u0002BK\u0005/\u0013\t\u000b\u0005\u0004\u0002\u0010\u0005e\u0011Q\u0014\u0005\b\u000533\u0003\u0019\u0001BN\u0003\u00191\u0017\u000e\u001c;feB11H!(\u0002\u001e\nL1Aa(=\u0005%1UO\\2uS>t\u0017\u0007\u0003\u0004\u0003\u000e\u001a\u0002\rAY\u0001\u0005gR|\u0007\u000fF\u0001R\u0003\u0011!X\r\u001c7\u0015\u0007E\u0013Y\u000bC\u0004\u0003.\"\u0002\rAa,\u0002\u000f5,7o]1hKB\u00191H!-\n\u0007\tMFHA\u0002B]f\f!C\u00117pG.l\u0015M\\1hKJl\u0015m\u001d;feB\u0011\u0001NK\n\u0003Ui\"\"Aa.\u0002)\u0011\u0013\u0016JV#S?\u0016sE\tU(J\u001dR{f*Q'F+\t\u0011\t\r\u0005\u0003\u0003D\n5WB\u0001Bc\u0015\u0011\u00119M!3\u0002\t1\fgn\u001a\u0006\u0003\u0005\u0017\fAA[1wC&!\u0011\u0011\u0001Bc\u0003U!%+\u0013,F%~+e\n\u0012)P\u0013:#vLT!N\u000b\u0002\na\u0004\u0012*J-\u0016\u0013v\fS#B%R\u0013U)\u0011+`\u000b:#\u0005kT%O)~s\u0015)T#\u0002?\u0011\u0013\u0016JV#S?\"+\u0015I\u0015+C\u000b\u0006#v,\u0012(E!>Ke\nV0O\u00036+\u0005\u0005"
)
public class BlockManagerMaster implements Logging {
   private RpcEndpointRef driverEndpoint;
   private RpcEndpointRef driverHeartbeatEndPoint;
   private final boolean isDriver;
   private final RpcTimeout timeout;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String DRIVER_HEARTBEAT_ENDPOINT_NAME() {
      return BlockManagerMaster$.MODULE$.DRIVER_HEARTBEAT_ENDPOINT_NAME();
   }

   public static String DRIVER_ENDPOINT_NAME() {
      return BlockManagerMaster$.MODULE$.DRIVER_ENDPOINT_NAME();
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public RpcEndpointRef driverEndpoint() {
      return this.driverEndpoint;
   }

   public void driverEndpoint_$eq(final RpcEndpointRef x$1) {
      this.driverEndpoint = x$1;
   }

   public RpcEndpointRef driverHeartbeatEndPoint() {
      return this.driverHeartbeatEndPoint;
   }

   public void driverHeartbeatEndPoint_$eq(final RpcEndpointRef x$1) {
      this.driverHeartbeatEndPoint = x$1;
   }

   public RpcTimeout timeout() {
      return this.timeout;
   }

   public void removeExecutor(final String execId) {
      this.tell(new BlockManagerMessages.RemoveExecutor(execId));
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removed ", " successfully in removeExecutor"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execId)})))));
   }

   public void decommissionBlockManagers(final Seq executorIds) {
      this.driverEndpoint().ask(new BlockManagerMessages.DecommissionBlockManagers(executorIds), scala.reflect.ClassTag..MODULE$.Boolean());
   }

   public Seq getReplicateInfoForRDDBlocks(final BlockManagerId blockManagerId) {
      return (Seq)this.driverEndpoint().askSync(new BlockManagerMessages.GetReplicateInfoForRDDBlocks(blockManagerId), scala.reflect.ClassTag..MODULE$.apply(Seq.class));
   }

   public void removeExecutorAsync(final String execId) {
      this.driverEndpoint().ask(new BlockManagerMessages.RemoveExecutor(execId), scala.reflect.ClassTag..MODULE$.Boolean());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removal of executor ", " requested"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, execId)})))));
   }

   public BlockManagerId registerBlockManager(final BlockManagerId id, final String[] localDirs, final long maxOnHeapMemSize, final long maxOffHeapMemSize, final RpcEndpointRef storageEndpoint, final boolean isReRegister) {
      BlockManagerId updatedId;
      label23: {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering BlockManager ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, id)})))));
         updatedId = (BlockManagerId)this.driverEndpoint().askSync(new BlockManagerMessages.RegisterBlockManager(id, localDirs, maxOnHeapMemSize, maxOffHeapMemSize, storageEndpoint, isReRegister), scala.reflect.ClassTag..MODULE$.apply(BlockManagerId.class));
         String var10000 = updatedId.executorId();
         String var10 = BlockManagerId$.MODULE$.INVALID_EXECUTOR_ID();
         if (var10000 == null) {
            if (var10 == null) {
               break label23;
            }
         } else if (var10000.equals(var10)) {
            break label23;
         }

         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registered BlockManager ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, updatedId)})))));
         return updatedId;
      }

      scala.Predef..MODULE$.assert(isReRegister, () -> "Got invalid executor id from non re-register case");
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Re-register BlockManager ", " failed"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, id)})))));
      return updatedId;
   }

   public boolean registerBlockManager$default$6() {
      return false;
   }

   public boolean updateBlockInfo(final BlockManagerId blockManagerId, final BlockId blockId, final StorageLevel storageLevel, final long memSize, final long diskSize) {
      boolean res = BoxesRunTime.unboxToBoolean(this.driverEndpoint().askSync(new BlockManagerMessages.UpdateBlockInfo(blockManagerId, blockId, storageLevel, memSize, diskSize), scala.reflect.ClassTag..MODULE$.Boolean()));
      this.logDebug((Function0)(() -> "Updated info of block " + blockId));
      return res;
   }

   public void updateRDDBlockTaskInfo(final RDDBlockId blockId, final long taskId) {
      this.driverEndpoint().askSync(new BlockManagerMessages.UpdateRDDBlockTaskInfo(blockId, taskId), scala.reflect.ClassTag..MODULE$.Unit());
   }

   public void updateRDDBlockVisibility(final long taskId, final boolean visible) {
      this.driverEndpoint().ask(new BlockManagerMessages.UpdateRDDBlockVisibility(taskId, visible), scala.reflect.ClassTag..MODULE$.Unit());
   }

   public boolean isRDDBlockVisible(final RDDBlockId blockId) {
      return BoxesRunTime.unboxToBoolean(this.driverEndpoint().askSync(new BlockManagerMessages.GetRDDBlockVisibility(blockId), scala.reflect.ClassTag..MODULE$.Boolean()));
   }

   public Seq getLocations(final BlockId blockId) {
      return (Seq)this.driverEndpoint().askSync(new BlockManagerMessages.GetLocations(blockId), scala.reflect.ClassTag..MODULE$.apply(Seq.class));
   }

   public Option getLocationsAndStatus(final BlockId blockId, final String requesterHost) {
      return (Option)this.driverEndpoint().askSync(new BlockManagerMessages.GetLocationsAndStatus(blockId, requesterHost), scala.reflect.ClassTag..MODULE$.apply(Option.class));
   }

   public IndexedSeq getLocations(final BlockId[] blockIds) {
      return (IndexedSeq)this.driverEndpoint().askSync(new BlockManagerMessages.GetLocationsMultipleBlockIds(blockIds), scala.reflect.ClassTag..MODULE$.apply(IndexedSeq.class));
   }

   public boolean contains(final BlockId blockId) {
      return this.getLocations(blockId).nonEmpty();
   }

   public Seq getPeers(final BlockManagerId blockManagerId) {
      return (Seq)this.driverEndpoint().askSync(new BlockManagerMessages.GetPeers(blockManagerId), scala.reflect.ClassTag..MODULE$.apply(Seq.class));
   }

   public Seq getShufflePushMergerLocations(final int numMergersNeeded, final Set hostsToFilter) {
      return (Seq)this.driverEndpoint().askSync(new BlockManagerMessages.GetShufflePushMergerLocations(numMergersNeeded, hostsToFilter), scala.reflect.ClassTag..MODULE$.apply(Seq.class));
   }

   public void removeShufflePushMergerLocation(final String host) {
      this.driverEndpoint().askSync(new BlockManagerMessages.RemoveShufflePushMergerLocation(host), scala.reflect.ClassTag..MODULE$.Unit());
   }

   public Option getExecutorEndpointRef(final String executorId) {
      return (Option)this.driverEndpoint().askSync(new BlockManagerMessages.GetExecutorEndpointRef(executorId), scala.reflect.ClassTag..MODULE$.apply(Option.class));
   }

   public void removeBlock(final BlockId blockId) {
      this.driverEndpoint().askSync(new BlockManagerMessages.RemoveBlock(blockId), scala.reflect.ClassTag..MODULE$.Boolean());
   }

   public void removeRdd(final int rddId, final boolean blocking) {
      Future future = (Future)this.driverEndpoint().askSync(new BlockManagerMessages.RemoveRdd(rddId), scala.reflect.ClassTag..MODULE$.apply(Future.class));
      future.failed().foreach((e) -> {
         $anonfun$removeRdd$1(this, rddId, e);
         return BoxedUnit.UNIT;
      }, ThreadUtils$.MODULE$.sameThread());
      if (blocking) {
         RpcUtils$.MODULE$.INFINITE_TIMEOUT().awaitResult(future);
      }
   }

   public void removeShuffle(final int shuffleId, final boolean blocking) {
      Future future = (Future)this.driverEndpoint().askSync(new BlockManagerMessages.RemoveShuffle(shuffleId), scala.reflect.ClassTag..MODULE$.apply(Future.class));
      future.failed().foreach((e) -> {
         $anonfun$removeShuffle$1(this, shuffleId, e);
         return BoxedUnit.UNIT;
      }, ThreadUtils$.MODULE$.sameThread());
      if (blocking) {
         RpcUtils$.MODULE$.INFINITE_TIMEOUT().awaitResult(future);
      }
   }

   public void removeBroadcast(final long broadcastId, final boolean removeFromMaster, final boolean blocking) {
      Future future = (Future)this.driverEndpoint().askSync(new BlockManagerMessages.RemoveBroadcast(broadcastId, removeFromMaster), scala.reflect.ClassTag..MODULE$.apply(Future.class));
      future.failed().foreach((e) -> {
         $anonfun$removeBroadcast$1(this, broadcastId, removeFromMaster, e);
         return BoxedUnit.UNIT;
      }, ThreadUtils$.MODULE$.sameThread());
      if (blocking) {
         RpcUtils$.MODULE$.INFINITE_TIMEOUT().awaitResult(future);
      }
   }

   public scala.collection.immutable.Map getMemoryStatus() {
      return this.driverEndpoint() == null ? scala.Predef..MODULE$.Map().empty() : (scala.collection.immutable.Map)this.driverEndpoint().askSync(BlockManagerMessages.GetMemoryStatus$.MODULE$, scala.reflect.ClassTag..MODULE$.apply(scala.collection.immutable.Map.class));
   }

   public StorageStatus[] getStorageStatus() {
      return this.driverEndpoint() == null ? (StorageStatus[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(StorageStatus.class)) : (StorageStatus[])this.driverEndpoint().askSync(BlockManagerMessages.GetStorageStatus$.MODULE$, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(StorageStatus.class)));
   }

   public scala.collection.immutable.Map getBlockStatus(final BlockId blockId, final boolean askStorageEndpoints) {
      BlockManagerMessages.GetBlockStatus msg = new BlockManagerMessages.GetBlockStatus(blockId, askStorageEndpoints);
      scala.collection.immutable.Map response = (scala.collection.immutable.Map)this.driverEndpoint().askSync(msg, scala.reflect.ClassTag..MODULE$.apply(scala.collection.immutable.Map.class));
      Tuple2 var7 = response.unzip(scala.Predef..MODULE$.$conforms());
      if (var7 != null) {
         Iterable blockManagerIds = (Iterable)var7._1();
         Iterable futures = (Iterable)var7._2();
         Tuple2 var6 = new Tuple2(blockManagerIds, futures);
         Iterable blockManagerIds = (Iterable)var6._1();
         Iterable futures = (Iterable)var6._2();
         BuildFrom cbf = (BuildFrom)scala.Predef..MODULE$.implicitly(scala.collection.BuildFrom..MODULE$.buildFromIterableOps());
         Iterable blockStatus = (Iterable)this.timeout().awaitResult(scala.concurrent.Future..MODULE$.sequence(futures, cbf, ThreadUtils$.MODULE$.sameThread()));
         if (blockStatus == null) {
            throw SparkCoreErrors$.MODULE$.blockStatusQueryReturnedNullError(blockId);
         } else {
            return ((IterableOnceOps)((IterableOps)blockManagerIds.zip(blockStatus)).flatMap((x0$1) -> {
               if (x0$1 != null) {
                  BlockManagerId blockManagerId = (BlockManagerId)x0$1._1();
                  Option status = (Option)x0$1._2();
                  return status.map((s) -> new Tuple2(blockManagerId, s));
               } else {
                  throw new MatchError(x0$1);
               }
            })).toMap(scala..less.colon.less..MODULE$.refl());
         }
      } else {
         throw new MatchError(var7);
      }
   }

   public boolean getBlockStatus$default$2() {
      return true;
   }

   public Seq getMatchingBlockIds(final Function1 filter, final boolean askStorageEndpoints) {
      BlockManagerMessages.GetMatchingBlockIds msg = new BlockManagerMessages.GetMatchingBlockIds(filter, askStorageEndpoints);
      Future future = (Future)this.driverEndpoint().askSync(msg, scala.reflect.ClassTag..MODULE$.apply(Future.class));
      return (Seq)this.timeout().awaitResult(future);
   }

   public void stop() {
      if (this.driverEndpoint() != null && this.isDriver) {
         this.tell(BlockManagerMessages.StopBlockManagerMaster$.MODULE$);
         this.driverEndpoint_$eq((RpcEndpointRef)null);
         if (BoxesRunTime.unboxToBoolean(this.driverHeartbeatEndPoint().askSync(BlockManagerMessages.StopBlockManagerMaster$.MODULE$, scala.reflect.ClassTag..MODULE$.Boolean()))) {
            this.driverHeartbeatEndPoint_$eq((RpcEndpointRef)null);
         } else {
            this.logWarning((Function0)(() -> "Failed to stop BlockManagerMasterHeartbeatEndpoint"));
         }

         this.logInfo((Function0)(() -> "BlockManagerMaster stopped"));
      }
   }

   private void tell(final Object message) {
      if (!BoxesRunTime.unboxToBoolean(this.driverEndpoint().askSync(message, scala.reflect.ClassTag..MODULE$.Boolean()))) {
         throw SparkCoreErrors$.MODULE$.unexpectedBlockManagerMasterEndpointResultError();
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$removeRdd$1(final BlockManagerMaster $this, final int rddId$1, final Throwable e) {
      $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to remove RDD ", " - "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, BoxesRunTime.boxToInteger(rddId$1))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, e.getMessage())}))))), e);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeShuffle$1(final BlockManagerMaster $this, final int shuffleId$1, final Throwable e) {
      $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to remove shuffle ", " - "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId$1))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, e.getMessage())}))))), e);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeBroadcast$1(final BlockManagerMaster $this, final long broadcastId$1, final boolean removeFromMaster$1, final Throwable e) {
      $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to remove broadcast ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_ID..MODULE$, BoxesRunTime.boxToLong(broadcastId$1))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with removeFromMaster = ", " - "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REMOVE_FROM_MASTER..MODULE$, BoxesRunTime.boxToBoolean(removeFromMaster$1))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, e.getMessage())}))))), e);
   }

   public BlockManagerMaster(final RpcEndpointRef driverEndpoint, final RpcEndpointRef driverHeartbeatEndPoint, final SparkConf conf, final boolean isDriver) {
      this.driverEndpoint = driverEndpoint;
      this.driverHeartbeatEndPoint = driverHeartbeatEndPoint;
      this.isDriver = isDriver;
      super();
      Logging.$init$(this);
      this.timeout = RpcUtils$.MODULE$.askRpcTimeout(conf);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
