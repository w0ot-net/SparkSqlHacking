package org.apache.spark.sql.hive.thriftserver.ui;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hive.service.server.HiveServer2;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext.;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerApplicationEnd;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.scheduler.SparkListenerJobStart;
import org.apache.spark.sql.hive.thriftserver.HiveThriftServer2;
import org.apache.spark.status.ElementTrackingStore;
import org.apache.spark.status.LiveEntity;
import org.apache.spark.util.kvstore.KVStoreView;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t%e!\u0002\u0017.\u0001=Z\u0004\u0002\u0003%\u0001\u0005\u0003\u0005\u000b\u0011\u0002&\t\u0011A\u0003!\u0011!Q\u0001\nEC\u0001\"\u0016\u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\tI\u0002\u0011\t\u0011)A\u0005K\")\u0001\u000e\u0001C\u0001S\"9\u0001\u000f\u0001b\u0001\n\u0013\t\bbBA\u000b\u0001\u0001\u0006IA\u001d\u0005\n\u0003/\u0001!\u0019!C\u0005\u00033A\u0001\"a\t\u0001A\u0003%\u00111\u0004\u0005\r\u0003K\u0001\u0001\u0013!A\u0002B\u0003%\u0011q\u0005\u0005\n\u0003g\u0001!\u0019!C\u0005\u0003kA\u0001\"a\u000e\u0001A\u0003%\u0011Q\u0006\u0005\n\u0003s\u0001!\u0019!C\u0005\u0003kA\u0001\"a\u000f\u0001A\u0003%\u0011Q\u0006\u0005\n\u0003{\u0001!\u0019!C\u0005\u0003\u007fA\u0001\"a\u0012\u0001A\u0003%\u0011\u0011\t\u0005\t\u0003\u0013\u0002A\u0011A\u0018\u0002L!9\u0011Q\n\u0001\u0005B\u0005=\u0003bBA1\u0001\u0011\u0005\u00131\r\u0005\b\u0003_\u0002A\u0011BA9\u0011\u001d\tY\b\u0001C!\u0003{Bq!!#\u0001\t\u0013\tY\tC\u0004\u0002\u0018\u0002!I!!'\t\u000f\u0005\r\u0006\u0001\"\u0003\u0002&\"9\u0011q\u0016\u0001\u0005\n\u0005E\u0006bBA^\u0001\u0011%\u0011Q\u0018\u0005\b\u0003\u000f\u0004A\u0011BAe\u0011\u001d\t\u0019\u000e\u0001C\u0005\u0003+Dq!a8\u0001\t\u0013\t\t\u000fC\u0004\u0002l\u0002!I!!<\t\u000f\u0005]\b\u0001\"\u0001\u0002z\"9!Q\u0001\u0001\u0005\u0002\t\u001d\u0001\"\u0003B\b\u0001E\u0005I\u0011\u0001B\t\u0011\u001d\u00119\u0003\u0001C\u0005\u0005SAqA!\u000e\u0001\t\u0013\u00119\u0004C\u0004\u0003J\u0001!IAa\u0013\t\u000f\t}\u0003\u0001\"\u0003\u0003b!9!q\r\u0001\u0005\n\t%\u0004b\u0002B7\u0001\u0011%!qN\u0004\u000b\u0005sj\u0013\u0011!E\u0001_\tmd!\u0003\u0017.\u0003\u0003E\ta\fB?\u0011\u0019A\u0017\u0006\"\u0001\u0003\u0006\"I!qQ\u0015\u0012\u0002\u0013\u0005!\u0011\u0003\u0002\u001a\u0011&4X\r\u00165sS\u001a$8+\u001a:wKJ\u0014D*[:uK:,'O\u0003\u0002/_\u0005\u0011Q/\u001b\u0006\u0003aE\nA\u0002\u001e5sS\u001a$8/\u001a:wKJT!AM\u001a\u0002\t!Lg/\u001a\u0006\u0003iU\n1a]9m\u0015\t1t'A\u0003ta\u0006\u00148N\u0003\u00029s\u00051\u0011\r]1dQ\u0016T\u0011AO\u0001\u0004_J<7c\u0001\u0001=\u0005B\u0011Q\bQ\u0007\u0002})\u0011q(N\u0001\ng\u000eDW\rZ;mKJL!!\u0011 \u0003\u001bM\u0003\u0018M]6MSN$XM\\3s!\t\u0019e)D\u0001E\u0015\t)U'\u0001\u0005j]R,'O\\1m\u0013\t9EIA\u0004M_\u001e<\u0017N\\4\u0002\u000f-48\u000f^8sK\u000e\u0001\u0001CA&O\u001b\u0005a%BA'6\u0003\u0019\u0019H/\u0019;vg&\u0011q\n\u0014\u0002\u0015\u000b2,W.\u001a8u)J\f7m[5oON#xN]3\u0002\u0013M\u0004\u0018M]6D_:4\u0007C\u0001*T\u001b\u0005)\u0014B\u0001+6\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0004tKJ4XM\u001d\t\u0004/jcV\"\u0001-\u000b\u0003e\u000bQa]2bY\u0006L!a\u0017-\u0003\r=\u0003H/[8o!\ti&-D\u0001_\u0015\t)vL\u0003\u0002aC\u000691/\u001a:wS\u000e,'B\u0001\u001a8\u0013\t\u0019gLA\u0006ISZ,7+\u001a:wKJ\u0014\u0014\u0001\u00027jm\u0016\u0004\"a\u00164\n\u0005\u001dD&a\u0002\"p_2,\u0017M\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b)dWN\\8\u0011\u0005-\u0004Q\"A\u0017\t\u000b!+\u0001\u0019\u0001&\t\u000bA+\u0001\u0019A)\t\u000bU+\u0001\u0019\u0001,\t\u000f\u0011,\u0001\u0013!a\u0001K\u0006Y1/Z:tS>tG*[:u+\u0005\u0011\b#B:{y\u0006=Q\"\u0001;\u000b\u0005U4\u0018AC2p]\u000e,(O]3oi*\u0011q\u000f_\u0001\u0005kRLGNC\u0001z\u0003\u0011Q\u0017M^1\n\u0005m$(!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baB\u0019Q0!\u0003\u000f\u0007y\f)\u0001\u0005\u0002\u000016\u0011\u0011\u0011\u0001\u0006\u0004\u0003\u0007I\u0015A\u0002\u001fs_>$h(C\u0002\u0002\ba\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA\u0006\u0003\u001b\u0011aa\u0015;sS:<'bAA\u00041B\u00191.!\u0005\n\u0007\u0005MQFA\bMSZ,7+Z:tS>tG)\u0019;b\u00031\u0019Xm]:j_:d\u0015n\u001d;!\u00035)\u00070Z2vi&|g\u000eT5tiV\u0011\u00111\u0004\t\u0006gjd\u0018Q\u0004\t\u0004W\u0006}\u0011bAA\u0011[\t\tB*\u001b<f\u000bb,7-\u001e;j_:$\u0015\r^1\u0002\u001d\u0015DXmY;uS>tG*[:uA\u0005\u0019\u0001\u0010J\u0019\u0011\u000f]\u000bI#!\f\u0002.%\u0019\u00111\u0006-\u0003\rQ+\b\u000f\\33!\r9\u0016qF\u0005\u0004\u0003cA&aA%oi\u0006\u0011\"/\u001a;bS:,Gm\u0015;bi\u0016lWM\u001c;t+\t\ti#A\nsKR\f\u0017N\\3e'R\fG/Z7f]R\u001c\b%\u0001\tsKR\f\u0017N\\3e'\u0016\u001c8/[8og\u0006\t\"/\u001a;bS:,GmU3tg&|gn\u001d\u0011\u0002%1Lg/Z+qI\u0006$X\rU3sS>$gj]\u000b\u0003\u0003\u0003\u00022aVA\"\u0013\r\t)\u0005\u0017\u0002\u0005\u0019>tw-A\nmSZ,W\u000b\u001d3bi\u0016\u0004VM]5pI:\u001b\b%\u0001\u0006o_2Kg/\u001a#bi\u0006$\u0012!Z\u0001\u0011_:\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8F]\u0012$B!!\u0015\u0002XA\u0019q+a\u0015\n\u0007\u0005U\u0003L\u0001\u0003V]&$\bbBA-%\u0001\u0007\u00111L\u0001\u000fCB\u0004H.[2bi&|g.\u00128e!\ri\u0014QL\u0005\u0004\u0003?r$aG*qCJ\\G*[:uK:,'/\u00119qY&\u001c\u0017\r^5p]\u0016sG-\u0001\u0006p]*{'m\u0015;beR$B!!\u0015\u0002f!9\u0011qM\nA\u0002\u0005%\u0014\u0001\u00036pEN#\u0018M\u001d;\u0011\u0007u\nY'C\u0002\u0002ny\u0012Qc\u00159be.d\u0015n\u001d;f]\u0016\u0014(j\u001c2Ti\u0006\u0014H/\u0001\tva\u0012\fG/\u001a&pE\u0012+G/Y5mgR1\u0011\u0011KA:\u0003oBa!!\u001e\u0015\u0001\u0004a\u0018!\u00026pE&#\u0007BBA=)\u0001\u0007A0A\u0004he>,\b/\u00133\u0002\u0019=tw\n\u001e5fe\u00163XM\u001c;\u0015\t\u0005E\u0013q\u0010\u0005\b\u0003\u0003+\u0002\u0019AAB\u0003\u0015)g/\u001a8u!\ri\u0014QQ\u0005\u0004\u0003\u000fs$AE*qCJ\\G*[:uK:,'/\u0012<f]R\f\u0001c\u001c8TKN\u001c\u0018n\u001c8De\u0016\fG/\u001a3\u0015\t\u0005E\u0013Q\u0012\u0005\b\u0003\u001f3\u0002\u0019AAI\u0003\u0005)\u0007cA6\u0002\u0014&\u0019\u0011QS\u0017\u0003OM\u0003\u0018M]6MSN$XM\\3s)\"\u0014\u0018N\u001a;TKJ4XM]*fgNLwN\\\"sK\u0006$X\rZ\u0001\u0010_:\u001cVm]:j_:\u001cEn\\:fIR!\u0011\u0011KAN\u0011\u001d\tyi\u0006a\u0001\u0003;\u00032a[AP\u0013\r\t\t+\f\u0002''B\f'o\u001b'jgR,g.\u001a:UQJLg\r^*feZ,'oU3tg&|gn\u00117pg\u0016$\u0017\u0001E8o\u001fB,'/\u0019;j_:\u001cF/\u0019:u)\u0011\t\t&a*\t\u000f\u0005=\u0005\u00041\u0001\u0002*B\u00191.a+\n\u0007\u00055VFA\u0014Ta\u0006\u00148\u000eT5ti\u0016tWM\u001d+ie&4GoU3sm\u0016\u0014x\n]3sCRLwN\\*uCJ$\u0018!E8o\u001fB,'/\u0019;j_:\u0004\u0016M]:fIR!\u0011\u0011KAZ\u0011\u001d\ty)\u0007a\u0001\u0003k\u00032a[A\\\u0013\r\tI,\f\u0002)'B\f'o\u001b'jgR,g.\u001a:UQJLg\r^*feZ,'o\u00149fe\u0006$\u0018n\u001c8QCJ\u001cX\rZ\u0001\u0014_:|\u0005/\u001a:bi&|gnQ1oG\u0016dW\r\u001a\u000b\u0005\u0003#\ny\fC\u0004\u0002\u0010j\u0001\r!!1\u0011\u0007-\f\u0019-C\u0002\u0002F6\u0012!f\u00159be.d\u0015n\u001d;f]\u0016\u0014H\u000b\u001b:jMR\u001cVM\u001d<fe>\u0003XM]1uS>t7)\u00198dK2,G-\u0001\np]>\u0003XM]1uS>tG+[7f_V$H\u0003BA)\u0003\u0017Dq!a$\u001c\u0001\u0004\ti\rE\u0002l\u0003\u001fL1!!5.\u0005%\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]RKW.Z8vi\u0006\u0001rN\\(qKJ\fG/[8o\u000bJ\u0014xN\u001d\u000b\u0005\u0003#\n9\u000eC\u0004\u0002\u0010r\u0001\r!!7\u0011\u0007-\fY.C\u0002\u0002^6\u0012qe\u00159be.d\u0015n\u001d;f]\u0016\u0014H\u000b\u001b:jMR\u001cVM\u001d<fe>\u0003XM]1uS>tWI\u001d:pe\u0006\u0019rN\\(qKJ\fG/[8o\r&t\u0017n\u001d5fIR!\u0011\u0011KAr\u0011\u001d\ty)\ba\u0001\u0003K\u00042a[At\u0013\r\tI/\f\u0002)'B\f'o\u001b'jgR,g.\u001a:UQJLg\r^*feZ,'o\u00149fe\u0006$\u0018n\u001c8GS:L7\u000f[\u0001\u0012_:|\u0005/\u001a:bi&|gn\u00117pg\u0016$G\u0003BA)\u0003_Dq!a$\u001f\u0001\u0004\t\t\u0010E\u0002l\u0003gL1!!>.\u0005!\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]\u000ecwn]3e\u0003u)\b\u000fZ1uKN#xN]3XSRDGK]5hO\u0016\u0014XI\\1cY\u0016$G\u0003BA)\u0003wDq!!@ \u0001\u0004\ty0\u0001\u0004f]RLG/\u001f\t\u0004\u0017\n\u0005\u0011b\u0001B\u0002\u0019\nQA*\u001b<f\u000b:$\u0018\u000e^=\u0002\u001fU\u0004H-\u0019;f\u0019&4Xm\u0015;pe\u0016$b!!\u0015\u0003\n\t-\u0001bBA\u007fA\u0001\u0007\u0011q \u0005\t\u0005\u001b\u0001\u0003\u0013!a\u0001K\u00069AO]5hO\u0016\u0014\u0018!G;qI\u0006$X\rT5wKN#xN]3%I\u00164\u0017-\u001e7uII*\"Aa\u0005+\u0007\u0015\u0014)b\u000b\u0002\u0003\u0018A!!\u0011\u0004B\u0012\u001b\t\u0011YB\u0003\u0003\u0003\u001e\t}\u0011!C;oG\",7m[3e\u0015\r\u0011\t\u0003W\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B\u0013\u00057\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u00151G.^:i)\u0011\t\tFa\u000b\t\u000f\t5\"\u00051\u0001\u00030\u0005yQM\u001c;jif4E.^:i\rVt7\rE\u0004X\u0005c\ty0!\u0015\n\u0007\tM\u0002LA\u0005Gk:\u001cG/[8oc\u0005\u0011r-\u001a;Pe\u000e\u0013X-\u0019;f'\u0016\u001c8/[8o))\tyA!\u000f\u0003>\t\u0005#Q\t\u0005\u0007\u0005w\u0019\u0003\u0019\u0001?\u0002\u0013M,7o]5p]&#\u0007b\u0002B G\u0001\u0007\u0011\u0011I\u0001\ngR\f'\u000f\u001e+j[\u0016DaAa\u0011$\u0001\u0004a\u0018AA5q\u0011\u0019\u00119e\ta\u0001y\u0006AQo]3s]\u0006lW-\u0001\u000bhKR|%o\u0011:fCR,W\t_3dkRLwN\u001c\u000b\r\u0003;\u0011iE!\u0015\u0003V\t]#1\f\u0005\u0007\u0005\u001f\"\u0003\u0019\u0001?\u0002\r\u0015DXmY%e\u0011\u0019\u0011\u0019\u0006\na\u0001y\u0006I1\u000f^1uK6,g\u000e\u001e\u0005\u0007\u0005w!\u0003\u0019\u0001?\t\u000f\teC\u00051\u0001\u0002B\u0005q1\u000f^1siRKW.Z:uC6\u0004\bB\u0002B/I\u0001\u0007A0\u0001\u0005vg\u0016\u0014h*Y7f\u0003E\u0019G.Z1okB,\u00050Z2vi&|gn\u001d\u000b\u0005\u0003#\u0012\u0019\u0007C\u0004\u0003f\u0015\u0002\r!!\u0011\u0002\u000b\r|WO\u001c;\u0002\u001d\rdW-\u00198vaN+7o]5p]R!\u0011\u0011\u000bB6\u0011\u001d\u0011)G\na\u0001\u0003\u0003\nqcY1mGVd\u0017\r^3Ok6\u0014WM\u001d+p%\u0016lwN^3\u0015\r\u0005\u0005#\u0011\u000fB;\u0011\u001d\u0011\u0019h\na\u0001\u0003\u0003\n\u0001\u0002Z1uCNK'0\u001a\u0005\b\u0005o:\u0003\u0019AA!\u00031\u0011X\r^1j]\u0016$7+\u001b>f\u0003eA\u0015N^3UQJLg\r^*feZ,'O\r'jgR,g.\u001a:\u0011\u0005-L3cA\u0015\u0003\u0000A\u0019qK!!\n\u0007\t\r\u0005L\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0005w\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"\u0004"
)
public class HiveThriftServer2Listener extends SparkListener implements Logging {
   private final ElementTrackingStore kvstore;
   private final Option server;
   private final boolean live;
   private final ConcurrentHashMap sessionList;
   private final ConcurrentHashMap executionList;
   // $FF: synthetic field
   private final Tuple2 x$1;
   private final int retainedStatements;
   private final int retainedSessions;
   private final long liveUpdatePeriodNs;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$4() {
      return HiveThriftServer2Listener$.MODULE$.$lessinit$greater$default$4();
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

   private ConcurrentHashMap sessionList() {
      return this.sessionList;
   }

   private ConcurrentHashMap executionList() {
      return this.executionList;
   }

   private int retainedStatements() {
      return this.retainedStatements;
   }

   private int retainedSessions() {
      return this.retainedSessions;
   }

   private long liveUpdatePeriodNs() {
      return this.liveUpdatePeriodNs;
   }

   public boolean noLiveData() {
      return this.sessionList().isEmpty() && this.executionList().isEmpty();
   }

   public void onApplicationEnd(final SparkListenerApplicationEnd applicationEnd) {
      if (this.live) {
         this.server.foreach((x$2) -> {
            $anonfun$onApplicationEnd$1(x$2);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void onJobStart(final SparkListenerJobStart jobStart) {
      Properties properties = jobStart.properties();
      if (properties != null) {
         String groupId = properties.getProperty(.MODULE$.SPARK_JOB_GROUP_ID());
         if (groupId != null) {
            this.updateJobDetails(Integer.toString(jobStart.jobId()), groupId);
         }
      }
   }

   private void updateJobDetails(final String jobId, final String groupId) {
      Seq execList = ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.executionList().values()).asScala().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$updateJobDetails$1(groupId, x$3)))).toSeq();
      if (execList.nonEmpty()) {
         execList.foreach((exec) -> {
            $anonfun$updateJobDetails$2(this, jobId, exec);
            return BoxedUnit.UNIT;
         });
      } else {
         Seq storeExecInfo = org.apache.spark.status.KVUtils..MODULE$.viewToSeq(this.kvstore.view(ExecutionInfo.class), Integer.MAX_VALUE, (x$4) -> BoxesRunTime.boxToBoolean($anonfun$updateJobDetails$3(groupId, x$4)));
         storeExecInfo.foreach((exec) -> {
            LiveExecutionData liveExec = this.getOrCreateExecution(exec.execId(), exec.statement(), exec.sessionId(), exec.startTimestamp(), exec.userName());
            liveExec.jobId().$plus$eq(jobId);
            this.updateStoreWithTriggerEnabled(liveExec);
            return (LiveExecutionData)this.executionList().remove(liveExec.execId());
         });
      }
   }

   public void onOtherEvent(final SparkListenerEvent event) {
      if (event instanceof SparkListenerThriftServerSessionCreated var4) {
         this.onSessionCreated(var4);
         BoxedUnit var21 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerThriftServerSessionClosed var5) {
         this.onSessionClosed(var5);
         BoxedUnit var20 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerThriftServerOperationStart var6) {
         this.onOperationStart(var6);
         BoxedUnit var19 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerThriftServerOperationParsed var7) {
         this.onOperationParsed(var7);
         BoxedUnit var18 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerThriftServerOperationCanceled var8) {
         this.onOperationCanceled(var8);
         BoxedUnit var17 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerThriftServerOperationTimeout var9) {
         this.onOperationTimeout(var9);
         BoxedUnit var16 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerThriftServerOperationError var10) {
         this.onOperationError(var10);
         BoxedUnit var15 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerThriftServerOperationFinish var11) {
         this.onOperationFinished(var11);
         BoxedUnit var14 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerThriftServerOperationClosed var12) {
         this.onOperationClosed(var12);
         BoxedUnit var13 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   private void onSessionCreated(final SparkListenerThriftServerSessionCreated e) {
      LiveSessionData session = this.getOrCreateSession(e.sessionId(), e.startTime(), e.ip(), e.userName());
      this.sessionList().put(e.sessionId(), session);
      this.updateLiveStore(session, this.updateLiveStore$default$2());
   }

   private void onSessionClosed(final SparkListenerThriftServerSessionClosed e) {
      Option var3 = scala.Option..MODULE$.apply(this.sessionList().get(e.sessionId()));
      if (var3 instanceof Some var4) {
         LiveSessionData sessionData = (LiveSessionData)var4.value();
         sessionData.finishTimestamp_$eq(e.finishTime());
         this.updateStoreWithTriggerEnabled(sessionData);
         this.sessionList().remove(e.sessionId());
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"onSessionClosed called with unknown session id: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SESSION_ID..MODULE$, e.sessionId())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   private void onOperationStart(final SparkListenerThriftServerOperationStart e) {
      LiveExecutionData executionData = this.getOrCreateExecution(e.id(), e.statement(), e.sessionId(), e.startTime(), e.userName());
      executionData.state_$eq(HiveThriftServer2.ExecutionState$.MODULE$.STARTED());
      this.executionList().put(e.id(), executionData);
      executionData.groupId_$eq(e.groupId());
      this.updateLiveStore(executionData, this.updateLiveStore$default$2());
      Option var4 = scala.Option..MODULE$.apply(this.sessionList().get(e.sessionId()));
      if (var4 instanceof Some var5) {
         LiveSessionData sessionData = (LiveSessionData)var5.value();
         sessionData.totalExecution_$eq(sessionData.totalExecution() + 1);
         this.updateLiveStore(sessionData, this.updateLiveStore$default$2());
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var4)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"onOperationStart called with unknown session id: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SESSION_ID..MODULE$, e.sessionId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Regardless, the operation has been registered."})))).log(scala.collection.immutable.Nil..MODULE$))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var4);
      }
   }

   private void onOperationParsed(final SparkListenerThriftServerOperationParsed e) {
      Option var3 = scala.Option..MODULE$.apply(this.executionList().get(e.id()));
      if (var3 instanceof Some var4) {
         LiveExecutionData executionData = (LiveExecutionData)var4.value();
         executionData.executePlan_$eq(e.executionPlan());
         executionData.state_$eq(HiveThriftServer2.ExecutionState$.MODULE$.COMPILED());
         this.updateLiveStore(executionData, this.updateLiveStore$default$2());
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"onOperationParsed called with unknown operation id: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, e.id())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   private void onOperationCanceled(final SparkListenerThriftServerOperationCanceled e) {
      Option var3 = scala.Option..MODULE$.apply(this.executionList().get(e.id()));
      if (var3 instanceof Some var4) {
         LiveExecutionData executionData = (LiveExecutionData)var4.value();
         executionData.finishTimestamp_$eq(e.finishTime());
         executionData.state_$eq(HiveThriftServer2.ExecutionState$.MODULE$.CANCELED());
         this.updateLiveStore(executionData, this.updateLiveStore$default$2());
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"onOperationCanceled called with unknown operation id: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, e.id())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   private void onOperationTimeout(final SparkListenerThriftServerOperationTimeout e) {
      Option var3 = scala.Option..MODULE$.apply(this.executionList().get(e.id()));
      if (var3 instanceof Some var4) {
         LiveExecutionData executionData = (LiveExecutionData)var4.value();
         executionData.finishTimestamp_$eq(e.finishTime());
         executionData.state_$eq(HiveThriftServer2.ExecutionState$.MODULE$.TIMEDOUT());
         this.updateLiveStore(executionData, this.updateLiveStore$default$2());
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"onOperationCanceled called with unknown operation id: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, e.id())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   private void onOperationError(final SparkListenerThriftServerOperationError e) {
      Option var3 = scala.Option..MODULE$.apply(this.executionList().get(e.id()));
      if (var3 instanceof Some var4) {
         LiveExecutionData executionData = (LiveExecutionData)var4.value();
         executionData.finishTimestamp_$eq(e.finishTime());
         executionData.detail_$eq(e.errorMsg());
         executionData.state_$eq(HiveThriftServer2.ExecutionState$.MODULE$.FAILED());
         this.updateLiveStore(executionData, this.updateLiveStore$default$2());
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"onOperationError called with unknown operation id: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, e.id())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   private void onOperationFinished(final SparkListenerThriftServerOperationFinish e) {
      Option var3 = scala.Option..MODULE$.apply(this.executionList().get(e.id()));
      if (var3 instanceof Some var4) {
         LiveExecutionData executionData = (LiveExecutionData)var4.value();
         executionData.finishTimestamp_$eq(e.finishTime());
         executionData.state_$eq(HiveThriftServer2.ExecutionState$.MODULE$.FINISHED());
         this.updateLiveStore(executionData, this.updateLiveStore$default$2());
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"onOperationFinished called with unknown operation id: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, e.id())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   private void onOperationClosed(final SparkListenerThriftServerOperationClosed e) {
      Option var3 = scala.Option..MODULE$.apply(this.executionList().get(e.id()));
      if (var3 instanceof Some var4) {
         LiveExecutionData executionData = (LiveExecutionData)var4.value();
         executionData.closeTimestamp_$eq(e.closeTime());
         executionData.state_$eq(HiveThriftServer2.ExecutionState$.MODULE$.CLOSED());
         this.updateStoreWithTriggerEnabled(executionData);
         this.executionList().remove(e.id());
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"onOperationClosed called with unknown operation id: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, e.id())})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   public void updateStoreWithTriggerEnabled(final LiveEntity entity) {
      entity.write(this.kvstore, System.nanoTime(), true);
   }

   public void updateLiveStore(final LiveEntity entity, final boolean trigger) {
      long now = System.nanoTime();
      if (this.live && this.liveUpdatePeriodNs() >= 0L && now - entity.lastWriteTime() > this.liveUpdatePeriodNs()) {
         entity.write(this.kvstore, now, trigger);
      }
   }

   public boolean updateLiveStore$default$2() {
      return false;
   }

   private void flush(final Function1 entityFlushFunc) {
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.sessionList().values()).asScala().foreach(entityFlushFunc);
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.executionList().values()).asScala().foreach(entityFlushFunc);
   }

   private LiveSessionData getOrCreateSession(final String sessionId, final long startTime, final String ip, final String username) {
      return (LiveSessionData)this.sessionList().computeIfAbsent(sessionId, (x$5) -> new LiveSessionData(sessionId, startTime, ip, username));
   }

   private LiveExecutionData getOrCreateExecution(final String execId, final String statement, final String sessionId, final long startTimestamp, final String userName) {
      return (LiveExecutionData)this.executionList().computeIfAbsent(execId, (x$6) -> new LiveExecutionData(execId, statement, sessionId, startTimestamp, userName));
   }

   private void cleanupExecutions(final long count) {
      long countToDelete = this.calculateNumberToRemove(count, (long)this.retainedStatements());
      if (countToDelete > 0L) {
         KVStoreView view = this.kvstore.view(ExecutionInfo.class).index("finishTime").first(BoxesRunTime.boxToLong(0L));
         Seq toDelete = org.apache.spark.status.KVUtils..MODULE$.viewToSeq(view, (int)countToDelete, (j) -> BoxesRunTime.boxToBoolean($anonfun$cleanupExecutions$1(j)));
         toDelete.foreach((j) -> {
            $anonfun$cleanupExecutions$2(this, j);
            return BoxedUnit.UNIT;
         });
      }
   }

   private void cleanupSession(final long count) {
      long countToDelete = this.calculateNumberToRemove(count, (long)this.retainedSessions());
      if (countToDelete > 0L) {
         KVStoreView view = this.kvstore.view(SessionInfo.class).index("finishTime").first(BoxesRunTime.boxToLong(0L));
         Seq toDelete = org.apache.spark.status.KVUtils..MODULE$.viewToSeq(view, (int)countToDelete, (j) -> BoxesRunTime.boxToBoolean($anonfun$cleanupSession$1(j)));
         toDelete.foreach((j) -> {
            $anonfun$cleanupSession$2(this, j);
            return BoxedUnit.UNIT;
         });
      }
   }

   private long calculateNumberToRemove(final long dataSize, final long retainedSize) {
      return dataSize > retainedSize ? scala.math.package..MODULE$.max(retainedSize / 10L, dataSize - retainedSize) : 0L;
   }

   // $FF: synthetic method
   public static final void $anonfun$new$4(final HiveThriftServer2Listener $this, final LiveEntity entity) {
      $this.updateStoreWithTriggerEnabled(entity);
   }

   // $FF: synthetic method
   public static final void $anonfun$onApplicationEnd$1(final HiveServer2 x$2) {
      x$2.stop();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateJobDetails$1(final String groupId$1, final LiveExecutionData x$3) {
      boolean var3;
      label23: {
         String var10000 = x$3.groupId();
         if (var10000 == null) {
            if (groupId$1 == null) {
               break label23;
            }
         } else if (var10000.equals(groupId$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$updateJobDetails$2(final HiveThriftServer2Listener $this, final String jobId$1, final LiveExecutionData exec) {
      exec.jobId().$plus$eq(jobId$1);
      $this.updateLiveStore(exec, $this.updateLiveStore$default$2());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateJobDetails$3(final String groupId$1, final ExecutionInfo x$4) {
      boolean var3;
      label23: {
         String var10000 = x$4.groupId();
         if (var10000 == null) {
            if (groupId$1 == null) {
               break label23;
            }
         } else if (var10000.equals(groupId$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupExecutions$1(final ExecutionInfo j) {
      return j.finishTimestamp() != 0L;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupExecutions$2(final HiveThriftServer2Listener $this, final ExecutionInfo j) {
      $this.kvstore.delete(j.getClass(), j.execId());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupSession$1(final SessionInfo j) {
      return j.finishTimestamp() != 0L;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupSession$2(final HiveThriftServer2Listener $this, final SessionInfo j) {
      $this.kvstore.delete(j.getClass(), j.sessionId());
   }

   public HiveThriftServer2Listener(final ElementTrackingStore kvstore, final SparkConf sparkConf, final Option server, final boolean live) {
      this.kvstore = kvstore;
      this.server = server;
      this.live = live;
      Logging.$init$(this);
      this.sessionList = new ConcurrentHashMap();
      this.executionList = new ConcurrentHashMap();
      Tuple2.mcII.sp var6 = new Tuple2.mcII.sp(BoxesRunTime.unboxToInt(sparkConf.get(org.apache.spark.sql.internal.SQLConf..MODULE$.THRIFTSERVER_UI_STATEMENT_LIMIT())), BoxesRunTime.unboxToInt(sparkConf.get(org.apache.spark.sql.internal.SQLConf..MODULE$.THRIFTSERVER_UI_SESSION_LIMIT())));
      if (var6 != null) {
         int retainedStatements = ((Tuple2)var6)._1$mcI$sp();
         int retainedSessions = ((Tuple2)var6)._2$mcI$sp();
         if (true && true) {
            this.x$1 = new Tuple2.mcII.sp(retainedStatements, retainedSessions);
            this.retainedStatements = this.x$1._1$mcI$sp();
            this.retainedSessions = this.x$1._2$mcI$sp();
            this.liveUpdatePeriodNs = live ? BoxesRunTime.unboxToLong(sparkConf.get(org.apache.spark.internal.config.Status..MODULE$.LIVE_ENTITY_UPDATE_PERIOD())) : -1L;
            kvstore.addTrigger(SessionInfo.class, (long)this.retainedSessions(), (JFunction1.mcVJ.sp)(count) -> this.cleanupSession(count));
            kvstore.addTrigger(ExecutionInfo.class, (long)this.retainedStatements(), (JFunction1.mcVJ.sp)(count) -> this.cleanupExecutions(count));
            kvstore.onFlush((JFunction0.mcV.sp)() -> {
               if (!this.live) {
                  this.flush((entity) -> {
                     $anonfun$new$4(this, entity);
                     return BoxedUnit.UNIT;
                  });
               }
            });
            return;
         }
      }

      throw new MatchError(var6);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
