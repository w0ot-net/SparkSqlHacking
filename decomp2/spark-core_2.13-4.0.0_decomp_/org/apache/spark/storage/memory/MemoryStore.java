package org.apache.spark.storage.memory;

import java.lang.invoke.SerializedLambda;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.memory.MemoryManager;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockInfoManager;
import org.apache.spark.storage.RDDBlockId;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.LongRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\tUf!B\u0015+\u00019\"\u0004\u0002C!\u0001\u0005\u0003\u0005\u000b\u0011B\"\t\u0011\u001d\u0003!\u0011!Q\u0001\n!C\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006I!\u0014\u0005\t'\u0002\u0011\t\u0011)A\u0005)\"A\u0011\f\u0001B\u0001B\u0003%!\fC\u0003_\u0001\u0011\u0005q\fC\u0004g\u0001\t\u0007I\u0011B4\t\rm\u0004\u0001\u0015!\u0003i\u0011%\t9\u0001\u0001b\u0001\n\u0013\tI\u0001\u0003\u0005\u0002\"\u0001\u0001\u000b\u0011BA\u0006\u0011%\t\u0019\u0003\u0001b\u0001\n\u0013\tI\u0001\u0003\u0005\u0002&\u0001\u0001\u000b\u0011BA\u0006\u0011%\t9\u0003\u0001b\u0001\n\u0013\tI\u0003\u0003\u0005\u0002,\u0001\u0001\u000b\u0011BA\u000e\u0011\u001d\ti\u0003\u0001C\u0005\u0003SAq!a\f\u0001\t\u0013\tI\u0003C\u0004\u00022\u0001!I!!\u000b\t\u000f\u0005M\u0002\u0001\"\u0001\u00026!9\u00111\b\u0001\u0005\u0002\u0005u\u0002bBAD\u0001\u0011%\u0011\u0011\u0012\u0005\t\u0003\u000f\u0004A\u0011\u0001\u0017\u0002J\"A\u0011Q\u001d\u0001\u0005\u00021\n9\u000fC\u0004\u0003\u0004\u0001!\tA!\u0002\t\u000f\t=\u0001\u0001\"\u0001\u0003\u0012!9!\u0011\u0005\u0001\u0005\u0002\t\r\u0002b\u0002B!\u0001\u0011\u0005!1\t\u0005\b\u0005\u000f\u0002A\u0011\u0001B%\u0011\u001d\u0011Y\u0005\u0001C\u0005\u0005\u001bB\u0001B!\u0017\u0001\t\u0003q#1\f\u0005\b\u0005O\u0002A\u0011\u0003B5\u0011\u001d\u0011i\u0007\u0001C\u0001\u0005_BqAa\u001d\u0001\t\u0013\u0011)\bC\u0004\u0003x\u0001!\tA!\u001f\t\u000f\t\u0005\u0005\u0001\"\u0001\u0003\u0004\"I!\u0011\u0012\u0001\u0012\u0002\u0013\u0005!1\u0012\u0005\b\u0005C\u0003A\u0011AA\u0015\u0011\u001d\u0011\u0019\u000b\u0001C\u0001\u0003SAqA!*\u0001\t\u0013\u00119\u000bC\u0004\u0003*\u0002!IA!\u0013\t\u000f\t-\u0006\u0001\"\u0003\u0003.\nYQ*Z7pef\u001cFo\u001c:f\u0015\tYC&\u0001\u0004nK6|'/\u001f\u0006\u0003[9\nqa\u001d;pe\u0006<WM\u0003\u00020a\u0005)1\u000f]1sW*\u0011\u0011GM\u0001\u0007CB\f7\r[3\u000b\u0003M\n1a\u001c:h'\r\u0001Qg\u000f\t\u0003mej\u0011a\u000e\u0006\u0002q\u0005)1oY1mC&\u0011!h\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\u0005qzT\"A\u001f\u000b\u0005yr\u0013\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0001k$a\u0002'pO\u001eLgnZ\u0001\u0005G>tgm\u0001\u0001\u0011\u0005\u0011+U\"\u0001\u0018\n\u0005\u0019s#!C*qCJ\\7i\u001c8g\u0003A\u0011Gn\\2l\u0013:4w.T1oC\u001e,'\u000f\u0005\u0002J\u00156\tA&\u0003\u0002LY\t\u0001\"\t\\8dW&sgm\\'b]\u0006<WM]\u0001\u0012g\u0016\u0014\u0018.\u00197ju\u0016\u0014X*\u00198bO\u0016\u0014\bC\u0001(R\u001b\u0005y%B\u0001)/\u0003)\u0019XM]5bY&TXM]\u0005\u0003%>\u0013\u0011cU3sS\u0006d\u0017N_3s\u001b\u0006t\u0017mZ3s\u00035iW-\\8ss6\u000bg.Y4feB\u0011QkV\u0007\u0002-*\u00111FL\u0005\u00031Z\u0013Q\"T3n_JLX*\u00198bO\u0016\u0014\u0018\u0001\u00062m_\u000e\\WI^5di&|g\u000eS1oI2,'\u000f\u0005\u0002\\96\t!&\u0003\u0002^U\t!\"\t\\8dW\u00163\u0018n\u0019;j_:D\u0015M\u001c3mKJ\fa\u0001P5oSRtDC\u00021bE\u000e$W\r\u0005\u0002\\\u0001!)\u0011I\u0002a\u0001\u0007\")qI\u0002a\u0001\u0011\")AJ\u0002a\u0001\u001b\")1K\u0002a\u0001)\")\u0011L\u0002a\u00015\u00069QM\u001c;sS\u0016\u001cX#\u00015\u0011\t%t\u0007o]\u0007\u0002U*\u00111\u000e\\\u0001\u0005kRLGNC\u0001n\u0003\u0011Q\u0017M^1\n\u0005=T'!\u0004'j].,G\rS1tQ6\u000b\u0007\u000f\u0005\u0002Jc&\u0011!\u000f\f\u0002\b\u00052|7m[%ea\t!\u0018\u0010E\u0002\\k^L!A\u001e\u0016\u0003\u00175+Wn\u001c:z\u000b:$(/\u001f\t\u0003qfd\u0001\u0001B\u0005{\u0011\u0005\u0005\t\u0011!B\u0001y\n\u0019q\fJ\u0019\u0002\u0011\u0015tGO]5fg\u0002\n2!`A\u0001!\t1d0\u0003\u0002\u0000o\t9aj\u001c;iS:<\u0007c\u0001\u001c\u0002\u0004%\u0019\u0011QA\u001c\u0003\u0007\u0005s\u00170A\u000bp]\"+\u0017\r]+oe>dG.T3n_JLX*\u00199\u0016\u0005\u0005-\u0001\u0003CA\u0007\u0003/\tY\"a\u0007\u000e\u0005\u0005=!\u0002BA\t\u0003'\tq!\\;uC\ndWMC\u0002\u0002\u0016]\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI\"a\u0004\u0003\u000f!\u000b7\u000f['baB\u0019a'!\b\n\u0007\u0005}qG\u0001\u0003M_:<\u0017AF8o\u0011\u0016\f\u0007/\u00168s_2dW*Z7pefl\u0015\r\u001d\u0011\u0002-=4g\rS3baVs'o\u001c7m\u001b\u0016lwN]=NCB\fqc\u001c4g\u0011\u0016\f\u0007/\u00168s_2dW*Z7pefl\u0015\r\u001d\u0011\u0002+Ut'o\u001c7m\u001b\u0016lwN]=UQJ,7\u000f[8mIV\u0011\u00111D\u0001\u0017k:\u0014x\u000e\u001c7NK6|'/\u001f+ie\u0016\u001c\bn\u001c7eA\u0005IQ.\u0019=NK6|'/_\u0001\u000b[\u0016lwN]=Vg\u0016$\u0017\u0001\u00052m_\u000e\\7/T3n_JLXk]3e\u0003\u001d9W\r^*ju\u0016$B!a\u0007\u00028!1\u0011\u0011\b\nA\u0002A\fqA\u00197pG.LE-\u0001\u0005qkR\u0014\u0015\u0010^3t+\u0011\ty$a\u0017\u0015\u0015\u0005\u0005\u0013qLA1\u0003K\ny\u0007\u0006\u0003\u0002D\u0005%\u0003c\u0001\u001c\u0002F%\u0019\u0011qI\u001c\u0003\u000f\t{w\u000e\\3b]\"I\u00111J\n\u0002\u0002\u0003\u000f\u0011QJ\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004CBA(\u0003+\nI&\u0004\u0002\u0002R)\u0019\u00111K\u001c\u0002\u000fI,g\r\\3di&!\u0011qKA)\u0005!\u0019E.Y:t)\u0006<\u0007c\u0001=\u0002\\\u00111\u0011QL\nC\u0002q\u0014\u0011\u0001\u0016\u0005\u0007\u0003s\u0019\u0002\u0019\u00019\t\u000f\u0005\r4\u00031\u0001\u0002\u001c\u0005!1/\u001b>f\u0011\u001d\t9g\u0005a\u0001\u0003S\n!\"\\3n_JLXj\u001c3f!\r)\u00161N\u0005\u0004\u0003[2&AC'f[>\u0014\u00180T8eK\"9\u0011\u0011O\nA\u0002\u0005M\u0014AB0csR,7\u000fE\u00037\u0003k\nI(C\u0002\u0002x]\u0012\u0011BR;oGRLwN\u001c\u0019\u0011\t\u0005m\u00141Q\u0007\u0003\u0003{RA!a \u0002\u0002\u0006\u0011\u0011n\u001c\u0006\u0003W:JA!!\"\u0002~\t\t2\t[;oW\u0016$')\u001f;f\u0005V4g-\u001a:\u0002\u0017A,H/\u0013;fe\u0006$xN]\u000b\u0005\u0003\u0017\u000b\u0019\f\u0006\u0007\u0002\u000e\u0006\u0015\u0016qUA[\u0003w\u000bi\f\u0005\u0005\u0002\u0010\u0006}\u00151DA\u000e\u001d\u0011\t\t*a'\u000f\t\u0005M\u0015\u0011T\u0007\u0003\u0003+S1!a&C\u0003\u0019a$o\\8u}%\t\u0001(C\u0002\u0002\u001e^\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\"\u0006\r&AB#ji\",'OC\u0002\u0002\u001e^Ba!!\u000f\u0015\u0001\u0004\u0001\bbBAU)\u0001\u0007\u00111V\u0001\u0007m\u0006dW/Z:\u0011\r\u0005=\u0015QVAY\u0013\u0011\ty+a)\u0003\u0011%#XM]1u_J\u00042\u0001_AZ\t\u0019\ti\u0006\u0006b\u0001y\"9\u0011q\u0017\u000bA\u0002\u0005e\u0016\u0001C2mCN\u001cH+Y4\u0011\r\u0005=\u0013QKAY\u0011\u001d\t9\u0007\u0006a\u0001\u0003SBq!a0\u0015\u0001\u0004\t\t-\u0001\u0007wC2,Xm\u001d%pY\u0012,'\u000fE\u0003\\\u0003\u0007\f\t,C\u0002\u0002F*\u0012ABV1mk\u0016\u001c\bj\u001c7eKJ\f1\u0003];u\u0013R,'/\u0019;pe\u0006\u001bh+\u00197vKN,B!a3\u0002XRQ\u0011QZAm\u00037\fy.!9\u0011\u0011\u0005=\u0015qTAh\u00037\u0001RaWAi\u0003+L1!a5+\u0005e\u0001\u0016M\u001d;jC2d\u00170\u00168s_2dW\rZ%uKJ\fGo\u001c:\u0011\u0007a\f9\u000e\u0002\u0004\u0002^U\u0011\r\u0001 \u0005\u0007\u0003s)\u0002\u0019\u00019\t\u000f\u0005%V\u00031\u0001\u0002^B1\u0011qRAW\u0003+Dq!a\u001a\u0016\u0001\u0004\tI\u0007C\u0004\u00028V\u0001\r!a9\u0011\r\u0005=\u0013QKAk\u0003I\u0001X\u000f^%uKJ\fGo\u001c:Bg\nKH/Z:\u0016\t\u0005%\u0018Q\u001f\u000b\u000b\u0003W\f90!?\u0002~\n\u0005\u0001\u0003CAH\u0003?\u000bi/a\u0007\u0011\u000bm\u000by/a=\n\u0007\u0005E(F\u0001\rQCJ$\u0018.\u00197msN+'/[1mSj,GM\u00117pG.\u00042\u0001_A{\t\u0019\tiF\u0006b\u0001y\"1\u0011\u0011\b\fA\u0002ADq!!+\u0017\u0001\u0004\tY\u0010\u0005\u0004\u0002\u0010\u00065\u00161\u001f\u0005\b\u0003o3\u0002\u0019AA\u0000!\u0019\ty%!\u0016\u0002t\"9\u0011q\r\fA\u0002\u0005%\u0014\u0001C4fi\nKH/Z:\u0015\t\t\u001d!Q\u0002\t\u0006m\t%\u0011\u0011P\u0005\u0004\u0005\u00179$AB(qi&|g\u000e\u0003\u0004\u0002:]\u0001\r\u0001]\u0001\nO\u0016$h+\u00197vKN$BAa\u0005\u0003 A)aG!\u0003\u0003\u0016A\"!q\u0003B\u000e!\u0019\ty)!,\u0003\u001aA\u0019\u0001Pa\u0007\u0005\u0015\tu\u0001$!A\u0001\u0002\u000b\u0005APA\u0002`IIBa!!\u000f\u0019\u0001\u0004\u0001\u0018a\u00044sK\u0016lU-\\8ss\u0016sGO]=\u0016\t\t\u0015\"1\u0007\u000b\u0005\u0005O\u0011i\u0003E\u00027\u0005SI1Aa\u000b8\u0005\u0011)f.\u001b;\t\u000f\t=\u0012\u00041\u0001\u00032\u0005)QM\u001c;ssB\u0019\u0001Pa\r\u0005\u000f\u0005u\u0013D1\u0001\u00036E\u0019QPa\u000e1\t\te\"Q\b\t\u00057V\u0014Y\u0004E\u0002y\u0005{!1Ba\u0010\u00034\u0005\u0005\t\u0011!B\u0001y\n\u0019q\fJ\u001a\u0002\rI,Wn\u001c<f)\u0011\t\u0019E!\u0012\t\r\u0005e\"\u00041\u0001q\u0003\u0015\u0019G.Z1s)\t\u00119#\u0001\u0005hKR\u0014F\rZ%e)\u0011\u0011yEa\u0016\u0011\u000bY\u0012IA!\u0015\u0011\u0007Y\u0012\u0019&C\u0002\u0003V]\u00121!\u00138u\u0011\u0019\tI\u0004\ba\u0001a\u00061RM^5di\ncwnY6t)>4%/Z3Ta\u0006\u001cW\r\u0006\u0005\u0002\u001c\tu#\u0011\rB3\u0011\u001d\tI$\ba\u0001\u0005?\u0002BA\u000eB\u0005a\"9!1M\u000fA\u0002\u0005m\u0011!B:qC\u000e,\u0007bBA4;\u0001\u0007\u0011\u0011N\u0001\u0010C\u001a$XM\u001d#s_B\f5\r^5p]R!!q\u0005B6\u0011\u0019\tID\ba\u0001a\u0006A1m\u001c8uC&t7\u000f\u0006\u0003\u0002D\tE\u0004BBA\u001d?\u0001\u0007\u0001/\u0001\u000bdkJ\u0014XM\u001c;UCN\\\u0017\t\u001e;f[B$\u0018\n\u001a\u000b\u0003\u00037\taD]3tKJ4X-\u00168s_2dW*Z7pef4uN\u001d+iSN$\u0016m]6\u0015\u0011\u0005\r#1\u0010B?\u0005\u007fBa!!\u000f\"\u0001\u0004\u0001\bBB\u0016\"\u0001\u0004\tY\u0002C\u0004\u0002h\u0005\u0002\r!!\u001b\u0002=I,G.Z1tKVs'o\u001c7m\u001b\u0016lwN]=G_J$\u0006.[:UCN\\GC\u0002B\u0014\u0005\u000b\u00139\tC\u0004\u0002h\t\u0002\r!!\u001b\t\u0011-\u0012\u0003\u0013!a\u0001\u00037\t\u0001F]3mK\u0006\u001cX-\u00168s_2dW*Z7pef4uN\u001d+iSN$\u0016m]6%I\u00164\u0017-\u001e7uII*\"A!$+\t\u0005m!qR\u0016\u0003\u0005#\u0003BAa%\u0003\u001e6\u0011!Q\u0013\u0006\u0005\u0005/\u0013I*A\u0005v]\u000eDWmY6fI*\u0019!1T\u001c\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003 \nU%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u00192-\u001e:sK:$XK\u001c:pY2lU-\\8ss\u0006q2-\u001e:sK:$XK\u001c:pY2lU-\\8ss\u001a{'\u000f\u00165jgR\u000b7o[\u0001\u0012]VlG+Y:lgVs'o\u001c7mS:<WC\u0001B)\u00039awnZ'f[>\u0014\u00180V:bO\u0016\fq\u0003\\8h+:\u0014x\u000e\u001c7GC&dWO]3NKN\u001c\u0018mZ3\u0015\r\t\u001d\"q\u0016BY\u0011\u0019\tI\u0004\u000ba\u0001a\"9!1\u0017\u0015A\u0002\u0005m\u0011a\u00044j]\u0006dg+Z2u_J\u001c\u0016N_3"
)
public class MemoryStore implements Logging {
   private final SparkConf conf;
   private final BlockInfoManager blockInfoManager;
   private final SerializerManager serializerManager;
   private final MemoryManager memoryManager;
   private final BlockEvictionHandler blockEvictionHandler;
   private final LinkedHashMap entries;
   private final HashMap onHeapUnrollMemoryMap;
   private final HashMap offHeapUnrollMemoryMap;
   private final long unrollMemoryThreshold;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   private LinkedHashMap entries() {
      return this.entries;
   }

   private HashMap onHeapUnrollMemoryMap() {
      return this.onHeapUnrollMemoryMap;
   }

   private HashMap offHeapUnrollMemoryMap() {
      return this.offHeapUnrollMemoryMap;
   }

   private long unrollMemoryThreshold() {
      return this.unrollMemoryThreshold;
   }

   private long maxMemory() {
      return this.memoryManager.maxOnHeapStorageMemory() + this.memoryManager.maxOffHeapStorageMemory();
   }

   private long memoryUsed() {
      return this.memoryManager.storageMemoryUsed();
   }

   private long blocksMemoryUsed() {
      synchronized(this.memoryManager){}

      long var2;
      try {
         var2 = this.memoryUsed() - this.currentUnrollMemory();
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   public long getSize(final BlockId blockId) {
      synchronized(this.entries()){}

      long var3;
      try {
         var3 = ((MemoryEntry)this.entries().get(blockId)).size();
      } catch (Throwable var6) {
         throw var6;
      }

      return var3;
   }

   public boolean putBytes(final BlockId blockId, final long size, final MemoryMode memoryMode, final Function0 _bytes, final ClassTag evidence$2) {
      .MODULE$.require(!this.contains(blockId), () -> "Block " + blockId + " is already present in the MemoryStore");
      if (this.memoryManager.acquireStorageMemory(blockId, size, memoryMode)) {
         ChunkedByteBuffer bytes = (ChunkedByteBuffer)_bytes.apply();
         .MODULE$.assert(bytes.size() == size);
         SerializedMemoryEntry entry = new SerializedMemoryEntry(bytes, memoryMode, (ClassTag).MODULE$.implicitly(evidence$2));
         synchronized(this.entries()){}

         try {
            MemoryEntry var10000 = (MemoryEntry)this.entries().put(blockId, entry);
         } catch (Throwable var11) {
            throw var11;
         }

         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " stored as bytes in memory "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(estimated size ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIZE..MODULE$, Utils$.MODULE$.bytesToString(size))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"free ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.maxMemory() - this.blocksMemoryUsed()))}))))));
         return true;
      } else {
         return false;
      }
   }

   private Either putIterator(final BlockId blockId, final Iterator values, final ClassTag classTag, final MemoryMode memoryMode, final ValuesHolder valuesHolder) {
      .MODULE$.require(!this.contains(blockId), () -> "Block " + blockId + " is already present in the MemoryStore");
      int elementsUnrolled = 0;
      boolean keepUnrolling = true;
      long initialMemoryThreshold = this.unrollMemoryThreshold();
      long memoryCheckPeriod = BoxesRunTime.unboxToLong(this.conf.get(package$.MODULE$.UNROLL_MEMORY_CHECK_PERIOD()));
      long memoryThreshold = initialMemoryThreshold;
      double memoryGrowthFactor = BoxesRunTime.unboxToDouble(this.conf.get(package$.MODULE$.UNROLL_MEMORY_GROWTH_FACTOR()));
      long unrollMemoryUsedByThisBlock = 0L;
      keepUnrolling = this.reserveUnrollMemoryForThisTask(blockId, initialMemoryThreshold, memoryMode);
      if (!keepUnrolling) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to reserve initial memory threshold of "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(initialMemoryThreshold))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for computing block ", " in memory."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))))));
      } else {
         unrollMemoryUsedByThisBlock += initialMemoryThreshold;
      }

      boolean shouldCheckThreadInterruption;
      for(shouldCheckThreadInterruption = scala.Option..MODULE$.apply(TaskContext$.MODULE$.get()).isDefined(); values.hasNext() && keepUnrolling && (!shouldCheckThreadInterruption || !Thread.currentThread().isInterrupted()); ++elementsUnrolled) {
         valuesHolder.storeValue(values.next());
         if ((long)elementsUnrolled % memoryCheckPeriod == 0L) {
            long currentSize = valuesHolder.estimatedSize();
            if (currentSize >= memoryThreshold) {
               long amountToRequest = (long)((double)currentSize * memoryGrowthFactor - (double)memoryThreshold);
               keepUnrolling = this.reserveUnrollMemoryForThisTask(blockId, amountToRequest, memoryMode);
               if (keepUnrolling) {
                  unrollMemoryUsedByThisBlock += amountToRequest;
               }

               memoryThreshold += amountToRequest;
            }
         }
      }

      if (shouldCheckThreadInterruption && Thread.currentThread().isInterrupted()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to unroll block=", " since thread interrupt was received"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
         return new Left(BoxesRunTime.boxToLong(unrollMemoryUsedByThisBlock));
      } else if (keepUnrolling) {
         MemoryEntryBuilder entryBuilder = valuesHolder.getBuilder();
         long size = entryBuilder.preciseSize();
         if (size > unrollMemoryUsedByThisBlock) {
            long amountToRequest = size - unrollMemoryUsedByThisBlock;
            keepUnrolling = this.reserveUnrollMemoryForThisTask(blockId, amountToRequest, memoryMode);
            if (keepUnrolling) {
               unrollMemoryUsedByThisBlock += amountToRequest;
            }
         }

         if (keepUnrolling) {
            MemoryEntry entry = entryBuilder.build();
            synchronized(this.memoryManager){}

            try {
               this.releaseUnrollMemoryForThisTask(memoryMode, unrollMemoryUsedByThisBlock);
               boolean success = this.memoryManager.acquireStorageMemory(blockId, entry.size(), memoryMode);
               .MODULE$.assert(success, () -> "transferring unroll memory to storage memory failed");
            } catch (Throwable var37) {
               throw var37;
            }

            synchronized(this.entries()){}

            try {
               MemoryEntry var10000 = (MemoryEntry)this.entries().put(blockId, entry);
            } catch (Throwable var36) {
               throw var36;
            }

            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Block ", " stored as values in memory "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(estimated size ", ", free "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(entry.size()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FREE_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.maxMemory() - this.blocksMemoryUsed()))}))))));
            return new Right(BoxesRunTime.boxToLong(entry.size()));
         } else {
            this.logUnrollFailureMessage(blockId, entryBuilder.preciseSize());
            return new Left(BoxesRunTime.boxToLong(unrollMemoryUsedByThisBlock));
         }
      } else {
         this.logUnrollFailureMessage(blockId, valuesHolder.estimatedSize());
         return new Left(BoxesRunTime.boxToLong(unrollMemoryUsedByThisBlock));
      }
   }

   public Either putIteratorAsValues(final BlockId blockId, final Iterator values, final MemoryMode memoryMode, final ClassTag classTag) {
      DeserializedValuesHolder valuesHolder = new DeserializedValuesHolder(classTag, memoryMode);
      Either var7 = this.putIterator(blockId, values, classTag, memoryMode, valuesHolder);
      if (var7 instanceof Right var8) {
         long storedSize = BoxesRunTime.unboxToLong(var8.value());
         return new Right(BoxesRunTime.boxToLong(storedSize));
      } else if (var7 instanceof Left var11) {
         long unrollMemoryUsedByThisBlock = BoxesRunTime.unboxToLong(var11.value());
         Iterator unrolledIterator = valuesHolder.vector() != null ? valuesHolder.vector().iterator() : scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.genericArrayOps(valuesHolder.arrayValues()));
         return new Left(new PartiallyUnrolledIterator(this, memoryMode, unrollMemoryUsedByThisBlock, unrolledIterator, values));
      } else {
         throw new MatchError(var7);
      }
   }

   public Either putIteratorAsBytes(final BlockId blockId, final Iterator values, final ClassTag classTag, final MemoryMode memoryMode) {
      .MODULE$.require(!this.contains(blockId), () -> "Block " + blockId + " is already present in the MemoryStore");
      long initialMemoryThreshold = this.unrollMemoryThreshold();
      int var10000;
      if (initialMemoryThreshold > 2147483632L) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initial memory threshold of "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(initialMemoryThreshold))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is too large to be set as chunk size. Chunk size has been capped to "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_SIZE..MODULE$, Utils$.MODULE$.bytesToString(2147483632L))}))))));
         var10000 = 2147483632;
      } else {
         var10000 = (int)initialMemoryThreshold;
      }

      int chunkSize = var10000;
      SerializedValuesHolder valuesHolder = new SerializedValuesHolder(blockId, chunkSize, classTag, memoryMode, this.serializerManager);
      Either var11 = this.putIterator(blockId, values, classTag, memoryMode, valuesHolder);
      Object var18;
      if (var11 instanceof Right var12) {
         long storedSize = BoxesRunTime.unboxToLong(var12.value());
         var18 = new Right(BoxesRunTime.boxToLong(storedSize));
      } else {
         if (!(var11 instanceof Left)) {
            throw new MatchError(var11);
         }

         Left var15 = (Left)var11;
         long unrollMemoryUsedByThisBlock = BoxesRunTime.unboxToLong(var15.value());
         var18 = new Left(new PartiallySerializedBlock(this, this.serializerManager, blockId, valuesHolder.serializationStream(), valuesHolder.redirectableStream(), unrollMemoryUsedByThisBlock, memoryMode, valuesHolder.bbos(), values, classTag));
      }

      Either res = (Either)var18;
      scala.Option..MODULE$.apply(TaskContext$.MODULE$.get()).foreach((x$1) -> {
         $anonfun$putIteratorAsBytes$3(x$1);
         return BoxedUnit.UNIT;
      });
      return res;
   }

   public Option getBytes(final BlockId blockId) {
      synchronized(this.entries()){}

      MemoryEntry var5;
      try {
         var5 = (MemoryEntry)this.entries().get(blockId);
      } catch (Throwable var10) {
         throw var10;
      }

      if (var5 == null) {
         return scala.None..MODULE$;
      } else if (var5 instanceof DeserializedMemoryEntry) {
         throw org.apache.spark.SparkException..MODULE$.internalError("should only call getBytes on serialized blocks", "STORAGE");
      } else if (var5 instanceof SerializedMemoryEntry) {
         SerializedMemoryEntry var7 = (SerializedMemoryEntry)var5;
         ChunkedByteBuffer bytes = var7.buffer();
         return new Some(bytes);
      } else {
         throw new MatchError(var5);
      }
   }

   public Option getValues(final BlockId blockId) {
      synchronized(this.entries()){}

      MemoryEntry var5;
      try {
         var5 = (MemoryEntry)this.entries().get(blockId);
      } catch (Throwable var11) {
         throw var11;
      }

      if (var5 == null) {
         return scala.None..MODULE$;
      } else if (var5 instanceof SerializedMemoryEntry) {
         throw org.apache.spark.SparkException..MODULE$.internalError("should only call getValues on deserialized blocks", "STORAGE");
      } else if (var5 instanceof DeserializedMemoryEntry) {
         DeserializedMemoryEntry var7 = (DeserializedMemoryEntry)var5;
         Object values = var7.value();
         Some x = new Some(values);
         return x.map((x$2) -> scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.genericArrayOps(x$2)));
      } else {
         throw new MatchError(var5);
      }
   }

   public void freeMemoryEntry(final MemoryEntry entry) {
      if (entry instanceof SerializedMemoryEntry var4) {
         ChunkedByteBuffer buffer = var4.buffer();
         buffer.dispose();
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (entry instanceof DeserializedMemoryEntry var6) {
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.genericArrayOps(var6.value()), (x0$1) -> {
            $anonfun$freeMemoryEntry$1(this, x0$1);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(entry);
      }
   }

   public boolean remove(final BlockId blockId) {
      boolean var3;
      synchronized(this.memoryManager) {
         synchronized(this.entries()){}

         MemoryEntry var6;
         try {
            var6 = (MemoryEntry)this.entries().remove(blockId);
         } catch (Throwable var9) {
            throw var9;
         }

         boolean var10000;
         if (var6 != null) {
            this.freeMemoryEntry(var6);
            this.memoryManager.releaseStorageMemory(var6.size(), var6.memoryMode());
            this.logDebug((Function0)(() -> "Block " + blockId + " of size " + var6.size() + " dropped from memory (free " + (this.maxMemory() - this.blocksMemoryUsed()) + ")"));
            var10000 = true;
         } else {
            var10000 = false;
         }

         var3 = var10000;
      }

      return var3;
   }

   public void clear() {
      synchronized(this.memoryManager) {
         synchronized(this.entries()){}

         try {
            scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.entries().values()).asScala().foreach((entry) -> {
               $anonfun$clear$1(this, entry);
               return BoxedUnit.UNIT;
            });
            this.entries().clear();
         } catch (Throwable var5) {
            throw var5;
         }

         this.onHeapUnrollMemoryMap().clear();
         this.offHeapUnrollMemoryMap().clear();
         this.memoryManager.releaseAllStorageMemory();
         this.logInfo((Function0)(() -> "MemoryStore cleared"));
      }

   }

   private Option getRddId(final BlockId blockId) {
      return blockId.asRDDId().map((x$3) -> BoxesRunTime.boxToInteger($anonfun$getRddId$1(x$3)));
   }

   public long evictBlocksToFreeSpace(final Option blockId, final long space, final MemoryMode memoryMode) {
      .MODULE$.assert(space > 0L);
      long var6;
      synchronized(this.memoryManager) {
         LongRef freedMemory = LongRef.create(0L);
         Option rddToAdd = blockId.flatMap((blockIdx) -> this.getRddId(blockIdx));
         ArrayBuffer selectedBlocks = new ArrayBuffer();
         synchronized(this.entries()){}

         try {
            java.util.Iterator iterator = this.entries().entrySet().iterator();

            while(freedMemory.elem < space && iterator.hasNext()) {
               Map.Entry pair = (Map.Entry)iterator.next();
               BlockId blockId = (BlockId)pair.getKey();
               MemoryEntry entry = (MemoryEntry)pair.getValue();
               if (this.blockIsEvictable$1(blockId, entry, memoryMode, rddToAdd) && this.blockInfoManager.lockForWriting(blockId, false).isDefined()) {
                  selectedBlocks.$plus$eq(blockId);
                  freedMemory.elem += entry.size();
               }
            }
         } catch (Throwable var25) {
            throw var25;
         }

         long var10000;
         if (freedMemory.elem >= space) {
            IntRef lastSuccessfulBlock = IntRef.create(-1);

            try {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " blocks selected for dropping "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BLOCKS..MODULE$, BoxesRunTime.boxToInteger(selectedBlocks.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", " bytes)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(freedMemory.elem))}))))));
               selectedBlocks.indices().foreach$mVc$sp((JFunction1.mcVI.sp)(idx) -> {
                  BlockId blockId = (BlockId)selectedBlocks.apply(idx);
                  synchronized(this.entries()){}

                  MemoryEntry var7;
                  try {
                     var7 = (MemoryEntry)this.entries().get(blockId);
                  } catch (Throwable var9) {
                     throw var9;
                  }

                  if (var7 != null) {
                     this.dropBlock$1(blockId, var7);
                     this.afterDropAction(blockId);
                  }

                  lastSuccessfulBlock.elem = idx;
               });
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"After dropping ", " blocks, free memory is"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BLOCKS..MODULE$, BoxesRunTime.boxToInteger(selectedBlocks.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FREE_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.maxMemory() - this.blocksMemoryUsed()))}))))));
               var10000 = freedMemory.elem;
            } finally {
               if (lastSuccessfulBlock.elem != selectedBlocks.size() - 1) {
                  scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(lastSuccessfulBlock.elem + 1), selectedBlocks.size()).foreach$mVc$sp((JFunction1.mcVI.sp)(idx) -> {
                     BlockId blockId = (BlockId)selectedBlocks.apply(idx);
                     this.blockInfoManager.unlock(blockId, this.blockInfoManager.unlock$default$2());
                  });
               }

            }
         } else {
            blockId.foreach((id) -> {
               $anonfun$evictBlocksToFreeSpace$7(this, id);
               return BoxedUnit.UNIT;
            });
            selectedBlocks.foreach((id) -> {
               $anonfun$evictBlocksToFreeSpace$9(this, id);
               return BoxedUnit.UNIT;
            });
            var10000 = 0L;
         }

         var6 = var10000;
      }

      return var6;
   }

   public void afterDropAction(final BlockId blockId) {
   }

   public boolean contains(final BlockId blockId) {
      synchronized(this.entries()){}

      boolean var3;
      try {
         var3 = this.entries().containsKey(blockId);
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private long currentTaskAttemptId() {
      return BoxesRunTime.unboxToLong(scala.Option..MODULE$.apply(TaskContext$.MODULE$.get()).map((x$4) -> BoxesRunTime.boxToLong($anonfun$currentTaskAttemptId$1(x$4))).getOrElse((JFunction0.mcJ.sp)() -> -1L));
   }

   public boolean reserveUnrollMemoryForThisTask(final BlockId blockId, final long memory, final MemoryMode memoryMode) {
      synchronized(this.memoryManager){}

      boolean var7;
      try {
         boolean success = this.memoryManager.acquireUnrollMemory(blockId, memory, memoryMode);
         if (success) {
            long taskAttemptId = this.currentTaskAttemptId();
            HashMap var10000;
            if (MemoryMode.ON_HEAP.equals(memoryMode)) {
               var10000 = this.onHeapUnrollMemoryMap();
            } else {
               if (!MemoryMode.OFF_HEAP.equals(memoryMode)) {
                  throw new MatchError(memoryMode);
               }

               var10000 = this.offHeapUnrollMemoryMap();
            }

            HashMap unrollMemoryMap = var10000;
            unrollMemoryMap.update(BoxesRunTime.boxToLong(taskAttemptId), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(unrollMemoryMap.getOrElse(BoxesRunTime.boxToLong(taskAttemptId), (JFunction0.mcJ.sp)() -> 0L)) + memory));
         }

         var7 = success;
      } catch (Throwable var14) {
         throw var14;
      }

      return var7;
   }

   public void releaseUnrollMemoryForThisTask(final MemoryMode memoryMode, final long memory) {
      long taskAttemptId = this.currentTaskAttemptId();
      synchronized(this.memoryManager){}

      try {
         HashMap var10000;
         if (MemoryMode.ON_HEAP.equals(memoryMode)) {
            var10000 = this.onHeapUnrollMemoryMap();
         } else {
            if (!MemoryMode.OFF_HEAP.equals(memoryMode)) {
               throw new MatchError(memoryMode);
            }

            var10000 = this.offHeapUnrollMemoryMap();
         }

         HashMap unrollMemoryMap = var10000;
         if (unrollMemoryMap.contains(BoxesRunTime.boxToLong(taskAttemptId))) {
            long memoryToRelease = scala.math.package..MODULE$.min(memory, BoxesRunTime.unboxToLong(unrollMemoryMap.apply(BoxesRunTime.boxToLong(taskAttemptId))));
            if (memoryToRelease > 0L) {
               unrollMemoryMap.update(BoxesRunTime.boxToLong(taskAttemptId), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(unrollMemoryMap.apply(BoxesRunTime.boxToLong(taskAttemptId))) - memoryToRelease));
               this.memoryManager.releaseUnrollMemory(memoryToRelease, memoryMode);
            }

            if (BoxesRunTime.unboxToLong(unrollMemoryMap.apply(BoxesRunTime.boxToLong(taskAttemptId))) == 0L) {
               unrollMemoryMap.remove(BoxesRunTime.boxToLong(taskAttemptId));
            } else {
               BoxedUnit var14 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var15 = BoxedUnit.UNIT;
         }
      } catch (Throwable var13) {
         throw var13;
      }

   }

   public long releaseUnrollMemoryForThisTask$default$2() {
      return Long.MAX_VALUE;
   }

   public long currentUnrollMemory() {
      synchronized(this.memoryManager){}

      long var2;
      try {
         var2 = BoxesRunTime.unboxToLong(this.onHeapUnrollMemoryMap().values().sum(scala.math.Numeric.LongIsIntegral..MODULE$)) + BoxesRunTime.unboxToLong(this.offHeapUnrollMemoryMap().values().sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   public long currentUnrollMemoryForThisTask() {
      synchronized(this.memoryManager){}

      long var2;
      try {
         var2 = BoxesRunTime.unboxToLong(this.onHeapUnrollMemoryMap().getOrElse(BoxesRunTime.boxToLong(this.currentTaskAttemptId()), (JFunction0.mcJ.sp)() -> 0L)) + BoxesRunTime.unboxToLong(this.offHeapUnrollMemoryMap().getOrElse(BoxesRunTime.boxToLong(this.currentTaskAttemptId()), (JFunction0.mcJ.sp)() -> 0L));
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   private int numTasksUnrolling() {
      synchronized(this.memoryManager){}

      int var2;
      try {
         var2 = ((IterableOnceOps)this.onHeapUnrollMemoryMap().keys().$plus$plus(this.offHeapUnrollMemoryMap().keys())).toSet().size();
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private void logMemoryUsage() {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Memory use = ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CURRENT_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.blocksMemoryUsed()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(blocks) + ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FREE_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.currentUnrollMemory()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(scratch space shared across ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(this.numTasksUnrolling()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"tasks(s)) = ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STORAGE_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.memoryUsed()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Storage limit = ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.maxMemory()))}))))));
   }

   private void logUnrollFailureMessage(final BlockId blockId, final long finalVectorSize) {
      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not enough space to cache ", " in memory! "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(computed ", " so far)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(finalVectorSize))}))))));
      this.logMemoryUsage();
   }

   // $FF: synthetic method
   public static final void $anonfun$putIteratorAsBytes$3(final TaskContext x$1) {
      x$1.killTaskIfInterrupted();
   }

   // $FF: synthetic method
   public static final void $anonfun$freeMemoryEntry$1(final MemoryStore $this, final Object x0$1) {
      if (x0$1 instanceof AutoCloseable var5) {
         try {
            var5.close();
            BoxedUnit var12 = BoxedUnit.UNIT;
         } catch (Throwable var9) {
            if (var9 == null || !scala.util.control.NonFatal..MODULE$.apply(var9)) {
               throw var9;
            }

            $this.logWarning((Function0)(() -> "Fail to close a memory entry"), var9);
            BoxedUnit var10 = BoxedUnit.UNIT;
            var10 = BoxedUnit.UNIT;
         }

      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$clear$1(final MemoryStore $this, final MemoryEntry entry) {
      $this.freeMemoryEntry(entry);
   }

   // $FF: synthetic method
   public static final int $anonfun$getRddId$1(final RDDBlockId x$3) {
      return x$3.rddId();
   }

   private final boolean blockIsEvictable$1(final BlockId blockId, final MemoryEntry entry, final MemoryMode memoryMode$1, final Option rddToAdd$1) {
      boolean var7;
      label36: {
         label31: {
            MemoryMode var10000 = entry.memoryMode();
            if (var10000 == null) {
               if (memoryMode$1 != null) {
                  break label31;
               }
            } else if (!var10000.equals(memoryMode$1)) {
               break label31;
            }

            if (rddToAdd$1.isEmpty()) {
               break label36;
            }

            Option var6 = this.getRddId(blockId);
            if (rddToAdd$1 == null) {
               if (var6 != null) {
                  break label36;
               }
            } else if (!rddToAdd$1.equals(var6)) {
               break label36;
            }
         }

         var7 = false;
         return var7;
      }

      var7 = true;
      return var7;
   }

   private final void dropBlock$1(final BlockId blockId, final MemoryEntry entry) {
      Object var10000;
      if (entry instanceof DeserializedMemoryEntry var6) {
         Object values = var6.value();
         var10000 = new Left(values);
      } else {
         if (!(entry instanceof SerializedMemoryEntry)) {
            throw new MatchError(entry);
         }

         SerializedMemoryEntry var8 = (SerializedMemoryEntry)entry;
         ChunkedByteBuffer buffer = var8.buffer();
         var10000 = new Right(buffer);
      }

      Either data = (Either)var10000;
      StorageLevel newEffectiveStorageLevel = this.blockEvictionHandler.dropFromMemory(blockId, () -> data, entry.classTag());
      if (newEffectiveStorageLevel.isValid()) {
         this.blockInfoManager.unlock(blockId, this.blockInfoManager.unlock$default$2());
      } else {
         this.blockInfoManager.removeBlock(blockId);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$evictBlocksToFreeSpace$7(final MemoryStore $this, final BlockId id) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Will not store ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, id)})))));
   }

   // $FF: synthetic method
   public static final void $anonfun$evictBlocksToFreeSpace$9(final MemoryStore $this, final BlockId id) {
      $this.blockInfoManager.unlock(id, $this.blockInfoManager.unlock$default$2());
   }

   // $FF: synthetic method
   public static final long $anonfun$currentTaskAttemptId$1(final TaskContext x$4) {
      return x$4.taskAttemptId();
   }

   public MemoryStore(final SparkConf conf, final BlockInfoManager blockInfoManager, final SerializerManager serializerManager, final MemoryManager memoryManager, final BlockEvictionHandler blockEvictionHandler) {
      this.conf = conf;
      this.blockInfoManager = blockInfoManager;
      this.serializerManager = serializerManager;
      this.memoryManager = memoryManager;
      this.blockEvictionHandler = blockEvictionHandler;
      Logging.$init$(this);
      this.entries = new LinkedHashMap(32, 0.75F, true);
      this.onHeapUnrollMemoryMap = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.offHeapUnrollMemoryMap = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.unrollMemoryThreshold = BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.STORAGE_UNROLL_MEMORY_THRESHOLD()));
      if (this.maxMemory() < this.unrollMemoryThreshold()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Max memory ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(this.maxMemory()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is less than the initial memory "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"threshold ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.unrollMemoryThreshold()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"needed to store a block in memory. Please configure Spark with more memory."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"MemoryStore started with capacity "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this.maxMemory()))}))))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
