package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.resource.ResourceProfileManager;
import org.apache.spark.scheduler.ExecutorDecommissionInfo;
import org.apache.spark.scheduler.ExecutorDecommissionInfo$;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerSpeculativeTaskSubmitted;
import org.apache.spark.scheduler.SparkListenerStageCompleted;
import org.apache.spark.scheduler.SparkListenerStageSubmitted;
import org.apache.spark.scheduler.SparkListenerTaskEnd;
import org.apache.spark.scheduler.SparkListenerTaskStart;
import org.apache.spark.scheduler.SparkListenerUnschedulableTaskSetAdded;
import org.apache.spark.scheduler.SparkListenerUnschedulableTaskSetRemoved;
import org.apache.spark.scheduler.TaskLocation;
import org.apache.spark.scheduler.dynalloc.ExecutorMonitor;
import org.apache.spark.util.Clock;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala..less.colon.less.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.Set;
import scala.collection.mutable.Shrinkable;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u001dd!CA#\u0003\u000f\u0002\u0011qIA*\u0011)\ti\u0007\u0001B\u0001B\u0003%\u0011\u0011\u000f\u0005\u000b\u0003s\u0002!\u0011!Q\u0001\n\u0005m\u0004BCAD\u0001\t\u0005\t\u0015!\u0003\u0002\n\"Q\u0011q\u0012\u0001\u0003\u0002\u0003\u0006I!!%\t\u0015\u0005u\u0005A!A!\u0002\u0013\ty\n\u0003\u0006\u0002,\u0002\u0011\t\u0011)A\u0005\u0003[C!\"!/\u0001\u0005\u0003\u0005\u000b\u0011BA^\u0011\u001d\t\t\r\u0001C\u0001\u0003\u0007D\u0011\"!6\u0001\u0005\u0004%I!a6\t\u0011\u0005}\u0007\u0001)A\u0005\u00033D\u0011\"!9\u0001\u0005\u0004%I!a6\t\u0011\u0005\r\b\u0001)A\u0005\u00033D\u0011\"!:\u0001\u0005\u0004%I!a6\t\u0011\u0005\u001d\b\u0001)A\u0005\u00033D\u0011\"!;\u0001\u0005\u0004%I!a;\t\u0011\u0005M\b\u0001)A\u0005\u0003[D\u0011\"!>\u0001\u0005\u0004%I!a;\t\u0011\u0005]\b\u0001)A\u0005\u0003[D\u0011\"!?\u0001\u0005\u0004%I!a?\t\u0011\u0005u\b\u0001)A\u0005\u0003wC\u0011\"a@\u0001\u0005\u0004%IA!\u0001\t\u0011\t%\u0001\u0001)A\u0005\u0005\u0007A\u0011Ba\u0003\u0001\u0005\u0004%I!a?\t\u0011\t5\u0001\u0001)A\u0005\u0003wC\u0011Ba\u0004\u0001\u0005\u0004%I!a6\t\u0011\tE\u0001\u0001)A\u0005\u00033D1Ba\u0005\u0001\u0005\u0004%\t!a\u0012\u0003\u0016!A!q\u0005\u0001!\u0002\u0013\u00119\u0002C\u0006\u0003*\u0001\u0011\r\u0011\"\u0001\u0002H\tU\u0001\u0002\u0003B\u0016\u0001\u0001\u0006IAa\u0006\t\u0013\t5\u0002\u00011A\u0005\n\u0005-\b\"\u0003B\u0018\u0001\u0001\u0007I\u0011\u0002B\u0019\u0011!\u0011i\u0004\u0001Q!\n\u00055\b\"\u0003B \u0001\t\u0007I\u0011BAv\u0011!\u0011\t\u0005\u0001Q\u0001\n\u00055\b\"\u0003B\"\u0001\t\u0007I\u0011\u0001B#\u0011!\u0019Y\u000f\u0001Q\u0001\n\t\u001d\u0003\"CBw\u0001\t\u0007I\u0011BBx\u0011!\u0019y\u0010\u0001Q\u0001\n\rE\b\"\u0003C\u0001\u0001\t\u0007I\u0011\u0001C\u0002\u0011!!Y\u0001\u0001Q\u0001\n\u0011\u0015\u0001\"\u0003C\u0007\u0001\t\u0007I\u0011\u0001C\b\u0011!!i\u0002\u0001Q\u0001\n\u0011E\u0001\"\u0003C\u0010\u0001\u0001\u0007I\u0011BA~\u0011%!\t\u0003\u0001a\u0001\n\u0013!\u0019\u0003\u0003\u0005\u0005(\u0001\u0001\u000b\u0015BA^\u0011%!\t\u0004\u0001a\u0001\n\u0013\u0011)\u0002C\u0005\u00054\u0001\u0001\r\u0011\"\u0003\u00056!AA\u0011\b\u0001!B\u0013\u00119\u0002C\u0005\u0005<\u0001\u0001\r\u0011\"\u0003\u0005>!IA\u0011\t\u0001A\u0002\u0013%A1\t\u0005\t\t\u000f\u0002\u0001\u0015)\u0003\u0005@!9A\u0011\n\u0001\u0005\n\r%\bb\u0002C&\u0001\u0011\u00051\u0011\u001e\u0005\b\t\u001b\u0002A\u0011ABu\u0011\u001d!y\u0005\u0001C\u0001\u0007SD\u0011\u0002\"\u0015\u0001\t\u0003\t9\u0005b\u0015\t\u000f\r\u0005\b\u0001\"\u0003\u0005X!9AQ\f\u0001\u0005\n\r%\bb\u0002C0\u0001\u0011%A\u0011\r\u0005\b\tO\u0002A\u0011\u0002C5\u0011\u001d)\t\u0001\u0001C\u0005\u000b\u0007Aq!b\u0003\u0001\t\u0013)i\u0001C\u0004\u0006 \u0001!I!\"\t\t\u000f\u0015-\u0002\u0001\"\u0003\u0006.!9Q1\u0007\u0001\u0005\n\u0015U\u0002bBC\u001f\u0001\u0011%Qq\b\u0005\b\u000b\u001f\u0002A\u0011BBu\u0011\u001d)\t\u0006\u0001C\u0005\u0007S4aAa\u0018\u0001\t\n\u0005\u0004B\u0003BA\r\nU\r\u0011\"\u0001\u0002X\"Q!1\u0011$\u0003\u0012\u0003\u0006I!!7\t\u0015\t\u0015eI!f\u0001\n\u0003\t9\u000e\u0003\u0006\u0003\b\u001a\u0013\t\u0012)A\u0005\u00033Dq!!1G\t\u0003\u0011I\tC\u0004\u0003\u0010\u001a#\tE!%\t\u0013\t\rf)!A\u0005\u0002\t\u0015\u0006\"\u0003BV\rF\u0005I\u0011\u0001BW\u0011%\u0011\u0019MRI\u0001\n\u0003\u0011i\u000bC\u0005\u0003F\u001a\u000b\t\u0011\"\u0011\u0003H\"I!q\u001b$\u0002\u0002\u0013\u0005\u0011q\u001b\u0005\n\u000534\u0015\u0011!C\u0001\u00057D\u0011B!:G\u0003\u0003%\tEa:\t\u0013\tEh)!A\u0005\u0002\tM\b\"\u0003B|\r\u0006\u0005I\u0011\tB}\u0011%\u0011iPRA\u0001\n\u0003\u0012y\u0010C\u0005\u0004\u0002\u0019\u000b\t\u0011\"\u0011\u0004\u0004\u001dIQ1\u000b\u0001\u0002\u0002#%QQ\u000b\u0004\n\u0005?\u0002\u0011\u0011!E\u0005\u000b/Bq!!1Z\t\u0003)Y\u0006C\u0005\u0003\u0010f\u000b\t\u0011\"\u0012\u00050\"IA1[-\u0002\u0002\u0013\u0005UQ\f\u0005\n\t7L\u0016\u0011!CA\u000bG2\u0001Ba\u0013\u0001\u0001\u0005\u001d#Q\n\u0005\b\u0003\u0003tF\u0011\u0001B+\u0011%\u00119F\u0018b\u0001\n\u0013\u0011I\u0006\u0003\u0005\u0004\by\u0003\u000b\u0011\u0002B.\u0011%\u0019IA\u0018b\u0001\n\u0013\u0011I\u0006\u0003\u0005\u0004\fy\u0003\u000b\u0011\u0002B.\u0011%\u0019iA\u0018b\u0001\n\u0013\u0019y\u0001\u0003\u0005\u0004\u001ay\u0003\u000b\u0011BB\t\u0011%\u0019YB\u0018b\u0001\n\u0013\u0019y\u0001\u0003\u0005\u0004\u001ey\u0003\u000b\u0011BB\t\u0011%\u0019yB\u0018b\u0001\n\u0013\u0019y\u0001\u0003\u0005\u0004\"y\u0003\u000b\u0011BB\t\u0011%\u0019\u0019C\u0018b\u0001\n\u0013\u0019)\u0003\u0003\u0005\u00040y\u0003\u000b\u0011BB\u0014\u0011%\u0019\tD\u0018b\u0001\n\u0013\u0019\u0019\u0004\u0003\u0005\u00048y\u0003\u000b\u0011BB\u001b\u0011%\u0019ID\u0018b\u0001\n\u0013\u0019Y\u0004\u0003\u0005\u0004Ly\u0003\u000b\u0011BB\u001f\u0011\u001d\u0019iE\u0018C!\u0007\u001fBqaa\u0017_\t\u0003\u001ai\u0006C\u0004\u0004jy#\tea\u001b\t\u000f\r]d\f\"\u0011\u0004z!91Q\u00110\u0005B\r\u001d\u0005bBBJ=\u0012\u00053Q\u0013\u0005\b\u0007CsF\u0011IBR\u0011\u001d\u0019yK\u0018C\u0001\u0007cCqaa._\t\u0003\u0019I\fC\u0004\u0004@z#\t!a?\t\u000f\r\u0005g\f\"\u0003\u0004D\"91\u0011\u001a0\u0005\u0002\r-\u0007bBBi=\u0012\u0005\u00111 \u0005\b\u0007'tF\u0011BBk\u0011\u001d\u0019IN\u0018C\u0001\u00077Dqaa8_\t\u0003\tY\u0010C\u0004\u0004bz#\taa9\t\u000f\r\u001dh\f\"\u0001\u0004j\u001eAA1PA$\u0011\u0013!iH\u0002\u0005\u0002F\u0005\u001d\u0003\u0012\u0002C@\u0011!\t\t-a\u0002\u0005\u0002\u0011\u0005\u0005B\u0003CB\u0003\u000f\u0011\r\u0011\"\u0001\u0002l\"IAQQA\u0004A\u0003%\u0011Q\u001e\u0004\n\t\u000f\u000b9\u0001QA$\t\u0013C1\u0002b#\u0002\u0010\tU\r\u0011\"\u0001\u0002X\"YAQRA\b\u0005#\u0005\u000b\u0011BAm\u0011-!y)a\u0004\u0003\u0016\u0004%\t!a6\t\u0017\u0011E\u0015q\u0002B\tB\u0003%\u0011\u0011\u001c\u0005\t\u0003\u0003\fy\u0001\"\u0001\u0005\u0014\"Q!1UA\b\u0003\u0003%\t\u0001\"(\t\u0015\t-\u0016qBI\u0001\n\u0003\u0011i\u000b\u0003\u0006\u0003D\u0006=\u0011\u0013!C\u0001\u0005[C!B!2\u0002\u0010\u0005\u0005I\u0011\tBd\u0011)\u00119.a\u0004\u0002\u0002\u0013\u0005\u0011q\u001b\u0005\u000b\u00053\fy!!A\u0005\u0002\u0011\r\u0006B\u0003Bs\u0003\u001f\t\t\u0011\"\u0011\u0003h\"Q!\u0011_A\b\u0003\u0003%\t\u0001b*\t\u0015\t]\u0018qBA\u0001\n\u0003\"Y\u000b\u0003\u0006\u0003~\u0006=\u0011\u0011!C!\u0005\u007fD!Ba$\u0002\u0010\u0005\u0005I\u0011\tCX\u0011)\u0019\t!a\u0004\u0002\u0002\u0013\u0005C\u0011W\u0004\r\tk\u000b9!!A\t\u0002\u0005\u001dCq\u0017\u0004\r\t\u000f\u000b9!!A\t\u0002\u0005\u001dC\u0011\u0018\u0005\t\u0003\u0003\f)\u0004\"\u0001\u0005R\"Q!qRA\u001b\u0003\u0003%)\u0005b,\t\u0015\u0011M\u0017QGA\u0001\n\u0003#)\u000e\u0003\u0006\u0005\\\u0006U\u0012\u0011!CA\t;D!\u0002b;\u00026\u0005\u0005I\u0011\u0002Cw\u0011)!)0a\u0002\u0012\u0002\u0013\u0005Aq\u001f\u0005\u000b\tw\f9!%A\u0005\u0002\u0011u(!G#yK\u000e,Ho\u001c:BY2|7-\u0019;j_:l\u0015M\\1hKJTA!!\u0013\u0002L\u0005)1\u000f]1sW*!\u0011QJA(\u0003\u0019\t\u0007/Y2iK*\u0011\u0011\u0011K\u0001\u0004_J<7#\u0002\u0001\u0002V\u0005\u0005\u0004\u0003BA,\u0003;j!!!\u0017\u000b\u0005\u0005m\u0013!B:dC2\f\u0017\u0002BA0\u00033\u0012a!\u00118z%\u00164\u0007\u0003BA2\u0003Sj!!!\u001a\u000b\t\u0005\u001d\u0014qI\u0001\tS:$XM\u001d8bY&!\u00111NA3\u0005\u001daunZ4j]\u001e\faa\u00197jK:$8\u0001\u0001\t\u0005\u0003g\n)(\u0004\u0002\u0002H%!\u0011qOA$\u0005a)\u00050Z2vi>\u0014\u0018\t\u001c7pG\u0006$\u0018n\u001c8DY&,g\u000e^\u0001\fY&\u001cH/\u001a8fe\n+8\u000f\u0005\u0003\u0002~\u0005\rUBAA@\u0015\u0011\t\t)a\u0012\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018\u0002BAC\u0003\u007f\u0012q\u0002T5wK2K7\u000f^3oKJ\u0014Uo]\u0001\u0005G>tg\r\u0005\u0003\u0002t\u0005-\u0015\u0002BAG\u0003\u000f\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\u000f\rdW-\u00198feB1\u0011qKAJ\u0003/KA!!&\u0002Z\t1q\n\u001d;j_:\u0004B!a\u001d\u0002\u001a&!\u00111TA$\u00059\u0019uN\u001c;fqR\u001cE.Z1oKJ\fQa\u00197pG.\u0004B!!)\u0002(6\u0011\u00111\u0015\u0006\u0005\u0003K\u000b9%\u0001\u0003vi&d\u0017\u0002BAU\u0003G\u0013Qa\u00117pG.\faC]3t_V\u00148-\u001a)s_\u001aLG.Z'b]\u0006<WM\u001d\t\u0005\u0003_\u000b),\u0004\u0002\u00022*!\u00111WA$\u0003!\u0011Xm]8ve\u000e,\u0017\u0002BA\\\u0003c\u0013aCU3t_V\u00148-\u001a)s_\u001aLG.Z'b]\u0006<WM]\u0001\u0017e\u0016d\u0017.\u00192mKNCWO\u001a4mKN#xN]1hKB!\u0011qKA_\u0013\u0011\ty,!\u0017\u0003\u000f\t{w\u000e\\3b]\u00061A(\u001b8jiz\"\u0002#!2\u0002H\u0006%\u00171ZAg\u0003\u001f\f\t.a5\u0011\u0007\u0005M\u0004\u0001C\u0004\u0002n!\u0001\r!!\u001d\t\u000f\u0005e\u0004\u00021\u0001\u0002|!9\u0011q\u0011\u0005A\u0002\u0005%\u0005\"CAH\u0011A\u0005\t\u0019AAI\u0011%\ti\n\u0003I\u0001\u0002\u0004\ty\nC\u0004\u0002,\"\u0001\r!!,\t\u000f\u0005e\u0006\u00021\u0001\u0002<\u0006yQ.\u001b8Ok6,\u00050Z2vi>\u00148/\u0006\u0002\u0002ZB!\u0011qKAn\u0013\u0011\ti.!\u0017\u0003\u0007%sG/\u0001\tnS:tU/\\#yK\u000e,Ho\u001c:tA\u0005yQ.\u0019=Ok6,\u00050Z2vi>\u00148/\u0001\tnCbtU/\\#yK\u000e,Ho\u001c:tA\u0005\u0019\u0012N\\5uS\u0006dg*^7Fq\u0016\u001cW\u000f^8sg\u0006!\u0012N\\5uS\u0006dg*^7Fq\u0016\u001cW\u000f^8sg\u0002\n\u0001d]2iK\u0012,H.\u001a:CC\u000e\\Gn\\4US6,w.\u001e;T+\t\ti\u000f\u0005\u0003\u0002X\u0005=\u0018\u0002BAy\u00033\u0012A\u0001T8oO\u0006I2o\u00195fIVdWM\u001d\"bG.dwn\u001a+j[\u0016|W\u000f^*!\u0003\u0005\u001aXo\u001d;bS:,GmU2iK\u0012,H.\u001a:CC\u000e\\Gn\\4US6,w.\u001e;T\u0003\t\u001aXo\u001d;bS:,GmU2iK\u0012,H.\u001a:CC\u000e\\Gn\\4US6,w.\u001e;TA\u00059A/Z:uS:<WCAA^\u0003!!Xm\u001d;j]\u001e\u0004\u0013aF3yK\u000e,Ho\u001c:BY2|7-\u0019;j_:\u0014\u0016\r^5p+\t\u0011\u0019\u0001\u0005\u0003\u0002X\t\u0015\u0011\u0002\u0002B\u0004\u00033\u0012a\u0001R8vE2,\u0017\u0001G3yK\u000e,Ho\u001c:BY2|7-\u0019;j_:\u0014\u0016\r^5pA\u0005\u0019B-Z2p[6L7o]5p]\u0016s\u0017M\u00197fI\u0006!B-Z2p[6L7o]5p]\u0016s\u0017M\u00197fI\u0002\n\u0001\u0003Z3gCVdG\u000f\u0015:pM&dW-\u00133\u0002#\u0011,g-Y;miB\u0013xNZ5mK&#\u0007%A\u0013ok6,\u00050Z2vi>\u00148\u000fV8BI\u0012\u0004VM\u001d*fg>,(oY3Qe>4\u0017\u000e\\3JIV\u0011!q\u0003\t\t\u00053\u0011\u0019#!7\u0002Z6\u0011!1\u0004\u0006\u0005\u0005;\u0011y\"A\u0004nkR\f'\r\\3\u000b\t\t\u0005\u0012\u0011L\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B\u0013\u00057\u0011q\u0001S1tQ6\u000b\u0007/\u0001\u0014ok6,\u00050Z2vi>\u00148\u000fV8BI\u0012\u0004VM\u001d*fg>,(oY3Qe>4\u0017\u000e\\3JI\u0002\naE\\;n\u000bb,7-\u001e;peN$\u0016M]4fiB+'OU3t_V\u00148-\u001a)s_\u001aLG.Z%e\u0003\u001drW/\\#yK\u000e,Ho\u001c:t)\u0006\u0014x-\u001a;QKJ\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\n\u001a\u0011\u0002\u000f\u0005$G\rV5nK\u0006Y\u0011\r\u001a3US6,w\fJ3r)\u0011\u0011\u0019D!\u000f\u0011\t\u0005]#QG\u0005\u0005\u0005o\tIF\u0001\u0003V]&$\b\"\u0003B\u001eA\u0005\u0005\t\u0019AAw\u0003\rAH%M\u0001\tC\u0012$G+[7fA\u0005q\u0011N\u001c;feZ\fG.T5mY&\u001c\u0018aD5oi\u0016\u0014h/\u00197NS2d\u0017n\u001d\u0011\u0002\u00111L7\u000f^3oKJ,\"Aa\u0012\u0011\u0007\t%c,D\u0001\u0001\u0005i)\u00050Z2vi>\u0014\u0018\t\u001c7pG\u0006$\u0018n\u001c8MSN$XM\\3s'\rq&q\n\t\u0005\u0003{\u0012\t&\u0003\u0003\u0003T\u0005}$!D*qCJ\\G*[:uK:,'\u000f\u0006\u0002\u0003H\u000512\u000f^1hK\u0006#H/Z7qiR{g*^7UCN\\7/\u0006\u0002\u0003\\AA!\u0011\u0004B\u0012\u0005;\nI\u000eE\u0002\u0003J\u0019\u0013Ab\u0015;bO\u0016\fE\u000f^3naR\u001crARA+\u0005G\u0012I\u0007\u0005\u0003\u0002X\t\u0015\u0014\u0002\u0002B4\u00033\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0003l\tmd\u0002\u0002B7\u0005orAAa\u001c\u0003v5\u0011!\u0011\u000f\u0006\u0005\u0005g\ny'\u0001\u0004=e>|GOP\u0005\u0003\u00037JAA!\u001f\u0002Z\u00059\u0001/Y2lC\u001e,\u0017\u0002\u0002B?\u0005\u007f\u0012AbU3sS\u0006d\u0017N_1cY\u0016TAA!\u001f\u0002Z\u000591\u000f^1hK&#\u0017\u0001C:uC\u001e,\u0017\n\u001a\u0011\u0002\u001dM$\u0018mZ3BiR,W\u000e\u001d;JI\u0006y1\u000f^1hK\u0006#H/Z7qi&#\u0007\u0005\u0006\u0004\u0003^\t-%Q\u0012\u0005\b\u0005\u0003[\u0005\u0019AAm\u0011\u001d\u0011)i\u0013a\u0001\u00033\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005'\u0003BA!&\u0003\u001e:!!q\u0013BM!\u0011\u0011y'!\u0017\n\t\tm\u0015\u0011L\u0001\u0007!J,G-\u001a4\n\t\t}%\u0011\u0015\u0002\u0007'R\u0014\u0018N\\4\u000b\t\tm\u0015\u0011L\u0001\u0005G>\u0004\u0018\u0010\u0006\u0004\u0003^\t\u001d&\u0011\u0016\u0005\n\u0005\u0003k\u0005\u0013!a\u0001\u00033D\u0011B!\"N!\u0003\u0005\r!!7\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!q\u0016\u0016\u0005\u00033\u0014\tl\u000b\u0002\u00034B!!Q\u0017B`\u001b\t\u00119L\u0003\u0003\u0003:\nm\u0016!C;oG\",7m[3e\u0015\u0011\u0011i,!\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003B\n]&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0003JB!!1\u001aBk\u001b\t\u0011iM\u0003\u0003\u0003P\nE\u0017\u0001\u00027b]\u001eT!Aa5\u0002\t)\fg/Y\u0005\u0005\u0005?\u0013i-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\tu'1\u001d\t\u0005\u0003/\u0012y.\u0003\u0003\u0003b\u0006e#aA!os\"I!1\b*\u0002\u0002\u0003\u0007\u0011\u0011\\\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!\u0011\u001e\t\u0007\u0005W\u0014iO!8\u000e\u0005\t}\u0011\u0002\u0002Bx\u0005?\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111\u0018B{\u0011%\u0011Y\u0004VA\u0001\u0002\u0004\u0011i.\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002Be\u0005wD\u0011Ba\u000fV\u0003\u0003\u0005\r!!7\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!7\u0002\r\u0015\fX/\u00197t)\u0011\tYl!\u0002\t\u0013\tmr+!AA\u0002\tu\u0017aF:uC\u001e,\u0017\t\u001e;f[B$Hk\u001c(v[R\u000b7o[:!\u0003q\u0019H/Y4f\u0003R$X-\u001c9u)>tU/\u001c*v]:Lgn\u001a+bg.\fQd\u001d;bO\u0016\fE\u000f^3naR$vNT;n%Vtg.\u001b8h)\u0006\u001c8\u000eI\u0001\u001agR\fw-Z!ui\u0016l\u0007\u000f\u001e+p)\u0006\u001c8.\u00138eS\u000e,7/\u0006\u0002\u0004\u0012AA!\u0011\u0004B\u0012\u0005;\u001a\u0019\u0002\u0005\u0004\u0003\u001a\rU\u0011\u0011\\\u0005\u0005\u0007/\u0011YBA\u0004ICND7+\u001a;\u00025M$\u0018mZ3BiR,W\u000e\u001d;U_R\u000b7o[%oI&\u001cWm\u001d\u0011\u0002IM$\u0018mZ3BiR,W\u000e\u001d;U_N\u0003XmY;mCRLg/\u001a+bg.Le\u000eZ5dKN\fQe\u001d;bO\u0016\fE\u000f^3naR$vn\u00159fGVd\u0017\r^5wKR\u000b7o[%oI&\u001cWm\u001d\u0011\u0002KM$\u0018mZ3BiR,W\u000e\u001d;U_B+g\u000eZ5oON\u0003XmY;mCRLg/\u001a+bg.\u001c\u0018AJ:uC\u001e,\u0017\t\u001e;f[B$Hk\u001c)f]\u0012LgnZ*qK\u000e,H.\u0019;jm\u0016$\u0016m]6tA\u0005y\"/Z:pkJ\u001cW\r\u0015:pM&dW-\u00133U_N#\u0018mZ3BiR,W\u000e\u001d;\u0016\u0005\r\u001d\u0002\u0003\u0003B\r\u0005G\tIn!\u000b\u0011\r\te11\u0006B/\u0013\u0011\u0019iCa\u0007\u0003\u0007M+G/\u0001\u0011sKN|WO]2f!J|g-\u001b7f\u0013\u0012$vn\u0015;bO\u0016\fE\u000f^3naR\u0004\u0013!F;og\u000eDW\rZ;mC\ndW\rV1tWN+Go]\u000b\u0003\u0007k\u0001bA!\u0007\u0004\u0016\tu\u0013AF;og\u000eDW\rZ;mC\ndW\rV1tWN+Go\u001d\u0011\u0002IM$\u0018mZ3BiR,W\u000e\u001d;U_\u0016CXmY;u_J\u0004F.Y2f[\u0016tG\u000fS5oiN,\"a!\u0010\u0011\u0011\te!1\u0005B/\u0007\u007f\u0001\"\"a\u0016\u0004B\u0005e7QIAm\u0013\u0011\u0019\u0019%!\u0017\u0003\rQ+\b\u000f\\34!!\u0011)ja\u0012\u0003\u0014\u0006e\u0017\u0002BB%\u0005C\u00131!T1q\u0003\u0015\u001aH/Y4f\u0003R$X-\u001c9u)>,\u00050Z2vi>\u0014\b\u000b\\1dK6,g\u000e\u001e%j]R\u001c\b%\u0001\tp]N#\u0018mZ3Tk\nl\u0017\u000e\u001e;fIR!!1GB)\u0011\u001d\u0019\u0019\u0006\u001da\u0001\u0007+\nab\u001d;bO\u0016\u001cVOY7jiR,G\r\u0005\u0003\u0002~\r]\u0013\u0002BB-\u0003\u007f\u00121d\u00159be.d\u0015n\u001d;f]\u0016\u00148\u000b^1hKN+(-\\5ui\u0016$\u0017\u0001E8o'R\fw-Z\"p[BdW\r^3e)\u0011\u0011\u0019da\u0018\t\u000f\r\u0005\u0014\u000f1\u0001\u0004d\u0005q1\u000f^1hK\u000e{W\u000e\u001d7fi\u0016$\u0007\u0003BA?\u0007KJAaa\u001a\u0002\u0000\tY2\u000b]1sW2K7\u000f^3oKJ\u001cF/Y4f\u0007>l\u0007\u000f\\3uK\u0012\f1b\u001c8UCN\\7\u000b^1siR!!1GB7\u0011\u001d\u0019yG\u001da\u0001\u0007c\n\u0011\u0002^1tWN#\u0018M\u001d;\u0011\t\u0005u41O\u0005\u0005\u0007k\nyH\u0001\fTa\u0006\u00148\u000eT5ti\u0016tWM\u001d+bg.\u001cF/\u0019:u\u0003%yg\u000eV1tW\u0016sG\r\u0006\u0003\u00034\rm\u0004bBB?g\u0002\u00071qP\u0001\bi\u0006\u001c8.\u00128e!\u0011\tih!!\n\t\r\r\u0015q\u0010\u0002\u0015'B\f'o\u001b'jgR,g.\u001a:UCN\\WI\u001c3\u00025=t7\u000b]3dk2\fG/\u001b<f)\u0006\u001c8nU;c[&$H/\u001a3\u0015\t\tM2\u0011\u0012\u0005\b\u0007\u0017#\b\u0019ABG\u0003=\u0019\b/Z2vY\u0006$\u0018N^3UCN\\\u0007\u0003BA?\u0007\u001fKAa!%\u0002\u0000\t)3\u000b]1sW2K7\u000f^3oKJ\u001c\u0006/Z2vY\u0006$\u0018N^3UCN\\7+\u001e2nSR$X\rZ\u0001\u001c_:,fn]2iK\u0012,H.\u00192mKR\u000b7o[*fi\u0006#G-\u001a3\u0015\t\tM2q\u0013\u0005\b\u00073+\b\u0019ABN\u0003e)hn]2iK\u0012,H.\u00192mKR\u000b7o[*fi\u0006#G-\u001a3\u0011\t\u0005u4QT\u0005\u0005\u0007?\u000byH\u0001\u0014Ta\u0006\u00148\u000eT5ti\u0016tWM]+og\u000eDW\rZ;mC\ndW\rV1tWN+G/\u00113eK\u0012\fQd\u001c8V]N\u001c\u0007.\u001a3vY\u0006\u0014G.\u001a+bg.\u001cV\r\u001e*f[>4X\r\u001a\u000b\u0005\u0005g\u0019)\u000bC\u0004\u0004(Z\u0004\ra!+\u00027Ut7o\u00195fIVd\u0017M\u00197f)\u0006\u001c8nU3u%\u0016lwN^3e!\u0011\tiha+\n\t\r5\u0016q\u0010\u0002)'B\f'o\u001b'jgR,g.\u001a:V]N\u001c\u0007.\u001a3vY\u0006\u0014G.\u001a+bg.\u001cV\r\u001e*f[>4X\rZ\u0001'e\u0016lwN^3Ti\u0006<WM\u0012:p[J+7o\\;sG\u0016\u0004&o\u001c4jY\u0016Le-\u00168vg\u0016$G\u0003\u0002B\u001a\u0007gCqa!.x\u0001\u0004\u0011i&\u0001\u0007ti\u0006<W-\u0011;uK6\u0004H/\u0001\u0010qK:$\u0017N\\4UCN\\7\u000fU3s%\u0016\u001cx.\u001e:dKB\u0013xNZ5mKR!\u0011\u0011\\B^\u0011\u001d\u0019i\f\u001fa\u0001\u00033\fAA\u001d9JI\u00061\u0002.Y:QK:$\u0017N\\4SK\u001e,H.\u0019:UCN\\7/A\thKR\u0004VM\u001c3j]\u001e$\u0016m]6Tk6$B!!7\u0004F\"91q\u0019>A\u0002\tu\u0013aB1ui\u0016l\u0007\u000f^\u0001*a\u0016tG-\u001b8h'B,7-\u001e7bi&4X\rV1tWN\u0004VM\u001d*fg>,(oY3Qe>4\u0017\u000e\\3\u0015\t\u0005e7Q\u001a\u0005\b\u0007\u001f\\\b\u0019AAm\u0003\t\u0011\b/\u0001\u000eiCN\u0004VM\u001c3j]\u001e\u001c\u0006/Z2vY\u0006$\u0018N^3UCN\\7/\u0001\u000fhKR\u0004VM\u001c3j]\u001e\u001c\u0006/Z2vY\u0006$\u0018N^3UCN\\7+^7\u0015\t\u0005e7q\u001b\u0005\b\u0007\u000fl\b\u0019\u0001B/\u00039\u0002XM\u001c3j]\u001e,fn]2iK\u0012,H.\u00192mKR\u000b7o[*fiN\u0004VM\u001d*fg>,(oY3Qe>4\u0017\u000e\\3\u0015\t\u0005e7Q\u001c\u0005\b\u0007\u001ft\b\u0019AAm\u0003=A\u0017m\u001d)f]\u0012Lgn\u001a+bg.\u001c\u0018a\t;pi\u0006d'+\u001e8oS:<G+Y:lgB+'OU3t_V\u00148-\u001a)s_\u001aLG.\u001a\u000b\u0005\u00033\u001c)\u000f\u0003\u0005\u0004P\u0006\u0005\u0001\u0019AAm\u0003q)\b\u000fZ1uK\u0016CXmY;u_J\u0004F.Y2f[\u0016tG\u000fS5oiN$\"Aa\r\u0002\u00131L7\u000f^3oKJ\u0004\u0013\u0001C3yK\u000e,Ho\u001c:\u0016\u0005\rE\b\u0003BBz\u0007wl!a!>\u000b\t\r]8\u0011`\u0001\u000bG>t7-\u001e:sK:$(\u0002BAS\u0005#LAa!@\u0004v\nA2k\u00195fIVdW\rZ#yK\u000e,Ho\u001c:TKJ4\u0018nY3\u0002\u0013\u0015DXmY;u_J\u0004\u0013aH3yK\u000e,Ho\u001c:BY2|7-\u0019;j_:l\u0015M\\1hKJ\u001cv.\u001e:dKV\u0011AQ\u0001\t\u0005\u0003g\"9!\u0003\u0003\u0005\n\u0005\u001d#aH#yK\u000e,Ho\u001c:BY2|7-\u0019;j_:l\u0015M\\1hKJ\u001cv.\u001e:dK\u0006\u0001S\r_3dkR|'/\u00117m_\u000e\fG/[8o\u001b\u0006t\u0017mZ3s'>,(oY3!\u0003=)\u00070Z2vi>\u0014Xj\u001c8ji>\u0014XC\u0001C\t!\u0011!\u0019\u0002\"\u0007\u000e\u0005\u0011U!\u0002\u0002C\f\u0003\u007f\n\u0001\u0002Z=oC2dwnY\u0005\u0005\t7!)BA\bFq\u0016\u001cW\u000f^8s\u001b>t\u0017\u000e^8s\u0003A)\u00070Z2vi>\u0014Xj\u001c8ji>\u0014\b%\u0001\u0007j]&$\u0018.\u00197ju&tw-\u0001\tj]&$\u0018.\u00197ju&twm\u0018\u0013fcR!!1\u0007C\u0013\u0011%\u0011Y$LA\u0001\u0002\u0004\tY,A\u0007j]&$\u0018.\u00197ju&tw\r\t\u0015\u0004]\u0011-\u0002\u0003BA,\t[IA\u0001b\f\u0002Z\tAao\u001c7bi&dW-A\u0015ok6dunY1mSRL\u0018i^1sKR\u000b7o[:QKJ\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\nZ\u0001.]VlGj\\2bY&$\u00180Q<be\u0016$\u0016m]6t!\u0016\u0014(+Z:pkJ\u001cW\r\u0015:pM&dW-\u00133`I\u0015\fH\u0003\u0002B\u001a\toA\u0011Ba\u000f1\u0003\u0003\u0005\rAa\u0006\u0002U9,X\u000eT8dC2LG/_!xCJ,G+Y:lgB+'OU3t_V\u00148-\u001a)s_\u001aLG.Z%eA\u0005Q\"\u000f]%e)>Dun\u001d;U_2{7-\u00197UCN\\7i\\;oiV\u0011Aq\b\t\t\u0005+\u001b9%!7\u0004F\u0005q\"\u000f]%e)>Dun\u001d;U_2{7-\u00197UCN\\7i\\;oi~#S-\u001d\u000b\u0005\u0005g!)\u0005C\u0005\u0003<M\n\t\u00111\u0001\u0005@\u0005Y\"\u000f]%e)>Dun\u001d;U_2{7-\u00197UCN\\7i\\;oi\u0002\n\u0001C^1mS\u0012\fG/Z*fiRLgnZ:\u0002\u000bM$\u0018M\u001d;\u0002\tM$x\u000e]\u0001\u0006e\u0016\u001cX\r^\u0001([\u0006Dh*^7Fq\u0016\u001cW\u000f^8sg:+W\rZ3e!\u0016\u0014(+Z:pkJ\u001cW\r\u0015:pM&dW\r\u0006\u0003\u0002Z\u0012U\u0003bBB_s\u0001\u0007\u0011\u0011\u001c\u000b\u0005\u00033$I\u0006C\u0004\u0005\\i\u0002\r!!7\u0002\u0005%$\u0017\u0001C:dQ\u0016$W\u000f\\3\u0002?U\u0004H-\u0019;f\u0003:$7+\u001f8d\u001dVlW\t_3dkR|'o\u001d+be\u001e,G\u000f\u0006\u0003\u0002Z\u0012\r\u0004b\u0002C3y\u0001\u0007\u0011Q^\u0001\u0004]><\u0018\u0001F1eI\u0016CXmY;u_J\u001cHk\u001c+be\u001e,G\u000f\u0006\u0005\u0002Z\u0012-Dq\u000eC9\u0011\u001d!i'\u0010a\u0001\u00033\f\u0011\"\\1y\u001d\u0016,G-\u001a3\t\u000f\ruV\b1\u0001\u0002Z\"9A1O\u001fA\u0002\u0011U\u0014!D;qI\u0006$Xm\u001d(fK\u0012,G\r\u0005\u0005\u0003\u001a\t\r\u0012\u0011\u001cC<!\u0011!I(a\u0004\u000f\t\u0005M\u0014QA\u0001\u001a\u000bb,7-\u001e;pe\u0006cGn\\2bi&|g.T1oC\u001e,'\u000f\u0005\u0003\u0002t\u0005\u001d1\u0003BA\u0004\u0003+\"\"\u0001\" \u0002\u000f9{EkX*F)\u0006Aaj\u0014+`'\u0016#\u0006E\u0001\tUCJ<W\r\u001e(v[V\u0003H-\u0019;fgNA\u0011qBA+\u0005G\u0012I'A\u0003eK2$\u0018-\u0001\u0004eK2$\u0018\rI\u0001\u0016_2$g*^7Fq\u0016\u001cW\u000f^8sgR\u000b'oZ3u\u0003YyG\u000e\u001a(v[\u0016CXmY;u_J\u001cH+\u0019:hKR\u0004CC\u0002CK\t3#Y\n\u0005\u0003\u0005\u0018\u0006=QBAA\u0004\u0011!!Y)!\u0007A\u0002\u0005e\u0007\u0002\u0003CH\u00033\u0001\r!!7\u0015\r\u0011UEq\u0014CQ\u0011)!Y)a\u0007\u0011\u0002\u0003\u0007\u0011\u0011\u001c\u0005\u000b\t\u001f\u000bY\u0002%AA\u0002\u0005eG\u0003\u0002Bo\tKC!Ba\u000f\u0002&\u0005\u0005\t\u0019AAm)\u0011\tY\f\"+\t\u0015\tm\u0012\u0011FA\u0001\u0002\u0004\u0011i\u000e\u0006\u0003\u0003J\u00125\u0006B\u0003B\u001e\u0003W\t\t\u00111\u0001\u0002ZR\u0011!\u0011\u001a\u000b\u0005\u0003w#\u0019\f\u0003\u0006\u0003<\u0005E\u0012\u0011!a\u0001\u0005;\f\u0001\u0003V1sO\u0016$h*^7Va\u0012\fG/Z:\u0011\t\u0011]\u0015QG\n\u0007\u0003k!Y\fb2\u0011\u0015\u0011uF1YAm\u00033$)*\u0004\u0002\u0005@*!A\u0011YA-\u0003\u001d\u0011XO\u001c;j[\u0016LA\u0001\"2\u0005@\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0011%GqZ\u0007\u0003\t\u0017TA\u0001\"4\u0003R\u0006\u0011\u0011n\\\u0005\u0005\u0005{\"Y\r\u0006\u0002\u00058\u0006)\u0011\r\u001d9msR1AQ\u0013Cl\t3D\u0001\u0002b#\u0002<\u0001\u0007\u0011\u0011\u001c\u0005\t\t\u001f\u000bY\u00041\u0001\u0002Z\u00069QO\\1qa2LH\u0003\u0002Cp\tO\u0004b!a\u0016\u0002\u0014\u0012\u0005\b\u0003CA,\tG\fI.!7\n\t\u0011\u0015\u0018\u0011\f\u0002\u0007)V\u0004H.\u001a\u001a\t\u0015\u0011%\u0018QHA\u0001\u0002\u0004!)*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"\u0001b<\u0011\t\t-G\u0011_\u0005\u0005\tg\u0014iM\u0001\u0004PE*,7\r^\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0011e(\u0006BAI\u0005c\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012*TC\u0001C\u0000U\u0011\tyJ!-\u00029\u0011,7M]3nK:$X\t_3dkR|'o\u001d$s_6$\u0016M]4fiRA\u0011\u0011\\C\u0003\u000b\u000f)I\u0001C\u0004\u0005ny\u0002\r!!7\t\u000f\ruf\b1\u0001\u0002Z\"9A1\u000f A\u0002\u0011U\u0014!E;qI\u0006$X\rV1sO\u0016$X\t_3dgRQ\u0011\u0011\\C\b\u000b3)Y\"\"\b\t\u000f\u0015Eq\b1\u0001\u0006\u0014\u0005qQ\u000f\u001d3bi\u0016$\u0016M]4fi\u001as\u0007CCA,\u000b+\tI.!7\u0002Z&!QqCA-\u0005%1UO\\2uS>t'\u0007C\u0004\u0005n}\u0002\r!!7\t\u000f\ruv\b1\u0001\u0002Z\"9A1O A\u0002\u0011U\u0014a\u00043p+B$\u0017\r^3SKF,Xm\u001d;\u0015\r\u0005eW1EC\u0015\u0011\u001d))\u0003\u0011a\u0001\u000bO\tq!\u001e9eCR,7\u000f\u0005\u0005\u0003\u0016\u000e\u001d\u0013\u0011\u001cC<\u0011\u001d!)\u0007\u0011a\u0001\u0003[\f!\u0003Z3de\u0016lWM\u001c;Fq\u0016\u001cW\u000f^8sgR1\u0011\u0011\\C\u0018\u000bcAq\u0001\"\u001cB\u0001\u0004\tI\u000eC\u0004\u0004>\u0006\u0003\r!!7\u0002\u0019\u0005$G-\u0012=fGV$xN]:\u0015\r\u0005eWqGC\u001e\u0011\u001d)ID\u0011a\u0001\u00033\fQ#\\1y\u001dVlW\t_3dkR|'o\u001d(fK\u0012,G\rC\u0004\u0004>\n\u0003\r!!7\u0002\u001fI,Wn\u001c<f\u000bb,7-\u001e;peN$B!\"\u0011\u0006HA1!1NC\"\u0005'KA!\"\u0012\u0003\u0000\t\u00191+Z9\t\u000f\u0015%3\t1\u0001\u0006L\u0005IQ\r_3dkR|'o\u001d\t\u0007\u0005W*\u0019%\"\u0014\u0011\u0011\u0005]C1\u001dBJ\u00033\fQc\u001c8TG\",G-\u001e7fe\n\u000b7m\u001b7pO\u001e,G-A\u000bp]N\u001b\u0007.\u001a3vY\u0016\u0014\u0018+^3vK\u0016k\u0007\u000f^=\u0002\u0019M#\u0018mZ3BiR,W\u000e\u001d;\u0011\u0007\t%\u0013lE\u0003Z\u000b3\"9\r\u0005\u0006\u0005>\u0012\r\u0017\u0011\\Am\u0005;\"\"!\"\u0016\u0015\r\tuSqLC1\u0011\u001d\u0011\t\t\u0018a\u0001\u00033DqA!\"]\u0001\u0004\tI\u000e\u0006\u0003\u0005`\u0016\u0015\u0004\"\u0003Cu;\u0006\u0005\t\u0019\u0001B/\u0001"
)
public class ExecutorAllocationManager implements Logging {
   private volatile StageAttempt$ StageAttempt$module;
   public final ExecutorAllocationClient org$apache$spark$ExecutorAllocationManager$$client;
   private final LiveListenerBus listenerBus;
   private final SparkConf conf;
   private final Option cleaner;
   private final Clock clock;
   private final ResourceProfileManager resourceProfileManager;
   private final boolean reliableShuffleStorage;
   private final int minNumExecutors;
   private final int maxNumExecutors;
   private final int org$apache$spark$ExecutorAllocationManager$$initialNumExecutors;
   private final long schedulerBacklogTimeoutS;
   private final long sustainedSchedulerBacklogTimeoutS;
   private final boolean testing;
   private final double executorAllocationRatio;
   private final boolean decommissionEnabled;
   private final int defaultProfileId;
   private final HashMap numExecutorsToAddPerResourceProfileId;
   private final HashMap numExecutorsTargetPerResourceProfileId;
   private long addTime;
   private final long intervalMillis;
   private final ExecutorAllocationListener listener;
   private final ScheduledExecutorService executor;
   private final ExecutorAllocationManagerSource executorAllocationManagerSource;
   private final ExecutorMonitor executorMonitor;
   private volatile boolean org$apache$spark$ExecutorAllocationManager$$initializing;
   private HashMap org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId;
   private Map org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Clock $lessinit$greater$default$5() {
      return ExecutorAllocationManager$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return ExecutorAllocationManager$.MODULE$.$lessinit$greater$default$4();
   }

   public static long NOT_SET() {
      return ExecutorAllocationManager$.MODULE$.NOT_SET();
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

   private StageAttempt$ StageAttempt() {
      if (this.StageAttempt$module == null) {
         this.StageAttempt$lzycompute$1();
      }

      return this.StageAttempt$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private int minNumExecutors() {
      return this.minNumExecutors;
   }

   private int maxNumExecutors() {
      return this.maxNumExecutors;
   }

   public int org$apache$spark$ExecutorAllocationManager$$initialNumExecutors() {
      return this.org$apache$spark$ExecutorAllocationManager$$initialNumExecutors;
   }

   private long schedulerBacklogTimeoutS() {
      return this.schedulerBacklogTimeoutS;
   }

   private long sustainedSchedulerBacklogTimeoutS() {
      return this.sustainedSchedulerBacklogTimeoutS;
   }

   private boolean testing() {
      return this.testing;
   }

   private double executorAllocationRatio() {
      return this.executorAllocationRatio;
   }

   private boolean decommissionEnabled() {
      return this.decommissionEnabled;
   }

   private int defaultProfileId() {
      return this.defaultProfileId;
   }

   public HashMap numExecutorsToAddPerResourceProfileId() {
      return this.numExecutorsToAddPerResourceProfileId;
   }

   public HashMap numExecutorsTargetPerResourceProfileId() {
      return this.numExecutorsTargetPerResourceProfileId;
   }

   private long addTime() {
      return this.addTime;
   }

   private void addTime_$eq(final long x$1) {
      this.addTime = x$1;
   }

   private long intervalMillis() {
      return this.intervalMillis;
   }

   public ExecutorAllocationListener listener() {
      return this.listener;
   }

   private ScheduledExecutorService executor() {
      return this.executor;
   }

   public ExecutorAllocationManagerSource executorAllocationManagerSource() {
      return this.executorAllocationManagerSource;
   }

   public ExecutorMonitor executorMonitor() {
      return this.executorMonitor;
   }

   private boolean initializing() {
      return this.org$apache$spark$ExecutorAllocationManager$$initializing;
   }

   public void org$apache$spark$ExecutorAllocationManager$$initializing_$eq(final boolean x$1) {
      this.org$apache$spark$ExecutorAllocationManager$$initializing = x$1;
   }

   public HashMap org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId() {
      return this.org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId;
   }

   public void org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId_$eq(final HashMap x$1) {
      this.org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId = x$1;
   }

   public Map org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount() {
      return this.org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount;
   }

   public void org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount_$eq(final Map x$1) {
      this.org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount = x$1;
   }

   private void validateSettings() {
      if (this.minNumExecutors() >= 0 && this.maxNumExecutors() >= 0) {
         if (this.maxNumExecutors() == 0) {
            throw new SparkException(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MAX_EXECUTORS().key() + " cannot be 0!");
         } else if (this.minNumExecutors() > this.maxNumExecutors()) {
            String var4 = org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS().key();
            throw new SparkException(var4 + " (" + this.minNumExecutors() + ") must be less than or equal to " + org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MAX_EXECUTORS().key() + " (" + this.maxNumExecutors() + ")!");
         } else if (this.schedulerBacklogTimeoutS() <= 0L) {
            throw new SparkException(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_SCHEDULER_BACKLOG_TIMEOUT().key() + " must be > 0!");
         } else if (this.sustainedSchedulerBacklogTimeoutS() <= 0L) {
            throw new SparkException("s" + org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_SUSTAINED_SCHEDULER_BACKLOG_TIMEOUT().key() + " must be > 0!");
         } else {
            boolean shuffleTrackingEnabled = BoxesRunTime.unboxToBoolean(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_SHUFFLE_TRACKING_ENABLED()));
            boolean shuffleDecommissionEnabled = this.decommissionEnabled() && BoxesRunTime.unboxToBoolean(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED()));
            if (!BoxesRunTime.unboxToBoolean(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SERVICE_ENABLED())) && !this.reliableShuffleStorage) {
               if (shuffleTrackingEnabled) {
                  this.logInfo((Function0)(() -> "Dynamic allocation is enabled without a shuffle service."));
               } else if (shuffleDecommissionEnabled) {
                  this.logInfo((Function0)(() -> "Shuffle data decommission is enabled without a shuffle service."));
               } else if (!this.testing()) {
                  String var3 = org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SERVICE_ENABLED().key();
                  throw new SparkException("Dynamic allocation of executors requires one of the following conditions: 1) enabling external shuffle service through " + var3 + ". 2) enabling shuffle tracking through " + org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_SHUFFLE_TRACKING_ENABLED().key() + ". 3) enabling shuffle blocks decommission through " + org.apache.spark.internal.config.package$.MODULE$.DECOMMISSION_ENABLED().key() + " and " + org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED().key() + ". 4) (Experimental) configuring " + org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_IO_PLUGIN_CLASS().key() + " to use a custom ShuffleDataIO who's ShuffleDriverComponents supports reliable storage.");
               }
            }

            if (shuffleTrackingEnabled && (shuffleDecommissionEnabled || this.reliableShuffleStorage)) {
               this.logWarning((Function0)(() -> "You are enabling both shuffle tracking and other DA supported mechanism, which will cause idle executors not to be released in a timely, please check the configurations."));
            }

            if (this.executorAllocationRatio() > (double)1.0F || this.executorAllocationRatio() <= (double)0.0F) {
               throw new SparkException(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_EXECUTOR_ALLOCATION_RATIO().key() + " must be > 0 and <= 1.0");
            }
         }
      } else {
         String var10002 = org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS().key();
         throw new SparkException(var10002 + " and " + org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MAX_EXECUTORS().key() + " must be positive!");
      }
   }

   public void start() {
      this.listenerBus.addToManagementQueue(this.listener());
      this.listenerBus.addToManagementQueue(this.executorMonitor());
      this.cleaner.foreach((x$1) -> {
         $anonfun$start$1(this, x$1);
         return BoxedUnit.UNIT;
      });
      Runnable scheduleTask = new Runnable() {
         // $FF: synthetic field
         private final ExecutorAllocationManager $outer;

         public void run() {
            Utils$.MODULE$.tryLog((JFunction0.mcV.sp)() -> this.$outer.org$apache$spark$ExecutorAllocationManager$$schedule());
         }

         public {
            if (ExecutorAllocationManager.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorAllocationManager.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      if (this.testing() && !BoxesRunTime.unboxToBoolean(this.conf.get(Tests$.MODULE$.TEST_DYNAMIC_ALLOCATION_SCHEDULE_ENABLED()))) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         this.executor().scheduleWithFixedDelay(scheduleTask, 0L, this.intervalMillis(), TimeUnit.MILLISECONDS);
      }

      synchronized(this){}

      Tuple2 var6;
      try {
         Map numTarget = this.numExecutorsTargetPerResourceProfileId().toMap(.MODULE$.refl());
         Map numLocality = this.org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId().toMap(.MODULE$.refl());
         var6 = new Tuple2(numTarget, numLocality);
      } catch (Throwable var14) {
         throw var14;
      }

      if (var6 != null) {
         Map numExecutorsTarget = (Map)var6._1();
         Map numLocalityAware = (Map)var6._2();
         Tuple2 var3 = new Tuple2(numExecutorsTarget, numLocalityAware);
         Map numExecutorsTarget = (Map)var3._1();
         Map numLocalityAware = (Map)var3._2();
         this.org$apache$spark$ExecutorAllocationManager$$client.requestTotalExecutors(numExecutorsTarget, numLocalityAware, this.org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount());
      } else {
         throw new MatchError(var6);
      }
   }

   public void stop() {
      ThreadUtils$.MODULE$.shutdown(this.executor(), scala.concurrent.duration.FiniteDuration..MODULE$.apply(10L, TimeUnit.SECONDS));
   }

   public synchronized void reset() {
      this.addTime_$eq(0L);
      this.numExecutorsTargetPerResourceProfileId().keys().foreach((JFunction1.mcVI.sp)(rpId) -> this.numExecutorsTargetPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(this.org$apache$spark$ExecutorAllocationManager$$initialNumExecutors())));
      this.numExecutorsToAddPerResourceProfileId().keys().foreach((JFunction1.mcVI.sp)(rpId) -> this.numExecutorsToAddPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(1)));
      this.executorMonitor().reset();
   }

   public int maxNumExecutorsNeededPerResourceProfile(final int rpId) {
      int pendingTask = this.listener().pendingTasksPerResourceProfile(rpId);
      int pendingSpeculative = this.listener().pendingSpeculativeTasksPerResourceProfile(rpId);
      int unschedulableTaskSets = this.listener().pendingUnschedulableTaskSetsPerResourceProfile(rpId);
      int running = this.listener().totalRunningTasksPerResourceProfile(rpId);
      int numRunningOrPendingTasks = pendingTask + pendingSpeculative + running;
      ResourceProfile rp = this.resourceProfileManager.resourceProfileFromId(rpId);
      int tasksPerExecutor = rp.maxTasksPerExecutor(this.conf);
      this.logDebug((Function0)(() -> "max needed for rpId: " + rpId + " numpending: " + numRunningOrPendingTasks + ", tasksperexecutor: " + tasksPerExecutor));
      int maxNeeded = (int)scala.math.package..MODULE$.ceil((double)numRunningOrPendingTasks * this.executorAllocationRatio() / (double)tasksPerExecutor);
      int maxNeededWithSpeculationLocalityOffset = tasksPerExecutor > 1 && maxNeeded == 1 && pendingSpeculative > 0 ? maxNeeded + 1 : maxNeeded;
      if (unschedulableTaskSets > 0) {
         int maxNeededForUnschedulables = (int)scala.math.package..MODULE$.ceil((double)unschedulableTaskSets * this.executorAllocationRatio() / (double)tasksPerExecutor);
         return scala.math.package..MODULE$.max(maxNeededWithSpeculationLocalityOffset, this.executorMonitor().executorCountWithResourceProfile(rpId) + maxNeededForUnschedulables);
      } else {
         return maxNeededWithSpeculationLocalityOffset;
      }
   }

   private synchronized int totalRunningTasksPerResourceProfile(final int id) {
      return this.listener().totalRunningTasksPerResourceProfile(id);
   }

   public void org$apache$spark$ExecutorAllocationManager$$schedule() {
      synchronized(this){}

      try {
         Seq executorIdsToBeRemoved = this.executorMonitor().timedOutExecutors();
         if (executorIdsToBeRemoved.nonEmpty()) {
            this.org$apache$spark$ExecutorAllocationManager$$initializing_$eq(false);
         }

         this.updateAndSyncNumExecutorsTarget(this.clock.nanoTime());
         if (executorIdsToBeRemoved.nonEmpty()) {
            this.removeExecutors(executorIdsToBeRemoved);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } catch (Throwable var4) {
         throw var4;
      }

   }

   private synchronized int updateAndSyncNumExecutorsTarget(final long now) {
      if (this.initializing()) {
         return 0;
      } else {
         HashMap updatesNeeded = new HashMap();
         this.numExecutorsTargetPerResourceProfileId().foreach((x0$1) -> {
            if (x0$1 != null) {
               int rpId = x0$1._1$mcI$sp();
               int targetExecs = x0$1._2$mcI$sp();
               int maxNeeded = this.maxNumExecutorsNeededPerResourceProfile(rpId);
               if (maxNeeded < targetExecs) {
                  return BoxesRunTime.boxToInteger(this.decrementExecutorsFromTarget(maxNeeded, rpId, updatesNeeded));
               } else {
                  return this.addTime() != ExecutorAllocationManager$.MODULE$.NOT_SET() && now >= this.addTime() ? BoxesRunTime.boxToInteger(this.addExecutorsToTarget(maxNeeded, rpId, updatesNeeded)) : BoxedUnit.UNIT;
               }
            } else {
               throw new MatchError(x0$1);
            }
         });
         return this.doUpdateRequest(updatesNeeded.toMap(.MODULE$.refl()), now);
      }
   }

   private int addExecutorsToTarget(final int maxNeeded, final int rpId, final HashMap updatesNeeded) {
      return this.updateTargetExecs((JFunction2.mcIII.sp)(maxNumExecutorsNeeded, rpIdx) -> this.addExecutors(maxNumExecutorsNeeded, rpIdx), maxNeeded, rpId, updatesNeeded);
   }

   private int decrementExecutorsFromTarget(final int maxNeeded, final int rpId, final HashMap updatesNeeded) {
      return this.updateTargetExecs((JFunction2.mcIII.sp)(maxNeededx, rpIdx) -> this.decrementExecutors(maxNeededx, rpIdx), maxNeeded, rpId, updatesNeeded);
   }

   private int updateTargetExecs(final Function2 updateTargetFn, final int maxNeeded, final int rpId, final HashMap updatesNeeded) {
      int oldNumExecutorsTarget = BoxesRunTime.unboxToInt(this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId)));
      int delta = updateTargetFn.apply$mcIII$sp(maxNeeded, rpId);
      if (delta != 0) {
         updatesNeeded.update(BoxesRunTime.boxToInteger(rpId), new TargetNumUpdates(delta, oldNumExecutorsTarget));
      }

      return delta;
   }

   private int doUpdateRequest(final Map updates, final long now) {
      if (updates.size() <= 0) {
         this.logDebug((Function0)(() -> "No change in number of executors"));
         return 0;
      } else {
         boolean var10000;
         try {
            this.logDebug((Function0)(() -> "requesting updates: " + updates));
            var10000 = this.testing() || this.org$apache$spark$ExecutorAllocationManager$$client.requestTotalExecutors(this.numExecutorsTargetPerResourceProfileId().toMap(.MODULE$.refl()), this.org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId().toMap(.MODULE$.refl()), this.org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount());
         } catch (Throwable var10) {
            if (var10 == null || !scala.util.control.NonFatal..MODULE$.apply(var10)) {
               throw var10;
            }

            this.logInfo((Function0)(() -> "Error reaching cluster manager."), var10);
            var10000 = false;
         }

         boolean requestAcknowledged = var10000;
         if (requestAcknowledged) {
            IntRef totalDelta = IntRef.create(0);
            updates.foreach((x0$1) -> {
               $anonfun$doUpdateRequest$3(this, totalDelta, now, x0$1);
               return BoxedUnit.UNIT;
            });
            return totalDelta.elem;
         } else {
            updates.foreach((x0$2) -> {
               $anonfun$doUpdateRequest$7(this, x0$2);
               return BoxedUnit.UNIT;
            });
            return 0;
         }
      }
   }

   private int decrementExecutors(final int maxNeeded, final int rpId) {
      int oldNumExecutorsTarget = BoxesRunTime.unboxToInt(this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId)));
      this.numExecutorsTargetPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(scala.math.package..MODULE$.max(maxNeeded, this.minNumExecutors())));
      this.numExecutorsToAddPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(1));
      return BoxesRunTime.unboxToInt(this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId))) - oldNumExecutorsTarget;
   }

   private int addExecutors(final int maxNumExecutorsNeeded, final int rpId) {
      int oldNumExecutorsTarget = BoxesRunTime.unboxToInt(this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId)));
      if (oldNumExecutorsTarget >= this.maxNumExecutors()) {
         this.logDebug((Function0)(() -> "Not adding executors because our current target total is already " + oldNumExecutorsTarget + " (limit " + this.maxNumExecutors() + ")"));
         this.numExecutorsToAddPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(1));
         return 0;
      } else {
         int numExecutorsTarget = scala.math.package..MODULE$.max(BoxesRunTime.unboxToInt(this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId))), this.executorMonitor().executorCountWithResourceProfile(rpId));
         numExecutorsTarget += BoxesRunTime.unboxToInt(this.numExecutorsToAddPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId)));
         numExecutorsTarget = scala.math.package..MODULE$.min(numExecutorsTarget, maxNumExecutorsNeeded);
         numExecutorsTarget = scala.math.package..MODULE$.max(scala.math.package..MODULE$.min(numExecutorsTarget, this.maxNumExecutors()), this.minNumExecutors());
         int delta = numExecutorsTarget - oldNumExecutorsTarget;
         this.numExecutorsTargetPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(numExecutorsTarget));
         if (delta == 0) {
            this.numExecutorsToAddPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(1));
         }

         return delta;
      }
   }

   private synchronized Seq removeExecutors(final Seq executors) {
      ArrayBuffer executorIdsToBeRemoved = new ArrayBuffer();
      this.logDebug((Function0)(() -> "Request to remove executorIds: " + executors.mkString(", ")));
      scala.collection.mutable.Map numExecutorsTotalPerRpId = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      executors.foreach((x0$1) -> {
         $anonfun$removeExecutors$2(this, numExecutorsTotalPerRpId, executorIdsToBeRemoved, x0$1);
         return BoxedUnit.UNIT;
      });
      if (executorIdsToBeRemoved.isEmpty()) {
         return (Seq)scala.package..MODULE$.Seq().empty();
      } else {
         Object var10000;
         if (this.testing()) {
            var10000 = executorIdsToBeRemoved;
         } else if (this.decommissionEnabled()) {
            Tuple2[] executorIdsWithoutHostLoss = (Tuple2[])((IterableOnceOps)executorIdsToBeRemoved.map((id) -> new Tuple2(id, new ExecutorDecommissionInfo("spark scale down", ExecutorDecommissionInfo$.MODULE$.apply$default$2())))).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            var10000 = this.org$apache$spark$ExecutorAllocationManager$$client.decommissionExecutors(executorIdsWithoutHostLoss, false, false);
         } else {
            var10000 = this.org$apache$spark$ExecutorAllocationManager$$client.killExecutors(executorIdsToBeRemoved.toSeq(), false, false, false);
         }

         scala.collection.Seq executorsRemoved = (scala.collection.Seq)var10000;
         this.org$apache$spark$ExecutorAllocationManager$$client.requestTotalExecutors(this.numExecutorsTargetPerResourceProfileId().toMap(.MODULE$.refl()), this.org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId().toMap(.MODULE$.refl()), this.org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount());
         if (!this.testing() && !executorsRemoved.nonEmpty()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to reach the cluster manager to kill executor/s "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, executorIdsToBeRemoved.mkString(","))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"or no executor eligible to kill!"})))).log(scala.collection.immutable.Nil..MODULE$))));
            return (Seq)scala.package..MODULE$.Seq().empty();
         } else {
            if (this.decommissionEnabled()) {
               this.executorMonitor().executorsDecommissioned(executorsRemoved.toSeq());
            } else {
               this.executorMonitor().executorsKilled(executorsRemoved.toSeq());
            }

            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executors ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, executorsRemoved.mkString(","))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"removed due to idle timeout."})))).log(scala.collection.immutable.Nil..MODULE$))));
            return executorsRemoved.toSeq();
         }
      }
   }

   public synchronized void org$apache$spark$ExecutorAllocationManager$$onSchedulerBacklogged() {
      if (this.addTime() == ExecutorAllocationManager$.MODULE$.NOT_SET()) {
         this.logDebug((Function0)(() -> "Starting timer to add executors because pending tasks are building up (to expire in " + this.schedulerBacklogTimeoutS() + " seconds)"));
         this.addTime_$eq(this.clock.nanoTime() + TimeUnit.SECONDS.toNanos(this.schedulerBacklogTimeoutS()));
      }
   }

   public void org$apache$spark$ExecutorAllocationManager$$onSchedulerQueueEmpty() {
      synchronized(this){}

      try {
         this.logDebug((Function0)(() -> "Clearing timer to add executors because there are no more pending tasks"));
         this.addTime_$eq(ExecutorAllocationManager$.MODULE$.NOT_SET());
         HashMap var10000 = (HashMap)this.numExecutorsToAddPerResourceProfileId().mapValuesInPlace((JFunction2.mcIII.sp)(x0$1, x1$1) -> {
            Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(x0$1, x1$1);
            if (var3 != null) {
               return 1;
            } else {
               throw new MatchError(var3);
            }
         });
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void StageAttempt$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.StageAttempt$module == null) {
            this.StageAttempt$module = new StageAttempt$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$start$1(final ExecutorAllocationManager $this, final ContextCleaner x$1) {
      x$1.attachListener($this.executorMonitor());
   }

   // $FF: synthetic method
   public static final void $anonfun$doUpdateRequest$3(final ExecutorAllocationManager $this, final IntRef totalDelta$1, final long now$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int rpId = x0$1._1$mcI$sp();
         TargetNumUpdates targetNum = (TargetNumUpdates)x0$1._2();
         int delta = targetNum.delta();
         totalDelta$1.elem += delta;
         if (delta > 0) {
            MessageWithContext executorsString = $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" new executor"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(delta > 1 ? $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"s"})))).log(scala.collection.immutable.Nil..MODULE$) : $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$));
            $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requesting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TARGET_NUM_EXECUTOR_DELTA..MODULE$, BoxesRunTime.boxToInteger(delta))}))).$plus(executorsString).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" because tasks are backlogged "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(new desired total will be"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TARGET_NUM_EXECUTOR..MODULE$, $this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId)))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for resource profile id: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(rpId))}))))));
            $this.numExecutorsToAddPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), delta == BoxesRunTime.unboxToInt($this.numExecutorsToAddPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId))) ? BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt($this.numExecutorsToAddPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId))) * 2) : BoxesRunTime.boxToInteger(1));
            $this.logDebug((Function0)(() -> "Starting timer to add more executors (to expire in " + $this.sustainedSchedulerBacklogTimeoutS() + " seconds)"));
            $this.addTime_$eq(now$2 + TimeUnit.SECONDS.toNanos($this.sustainedSchedulerBacklogTimeoutS()));
            BoxedUnit var11 = BoxedUnit.UNIT;
         } else {
            $this.logDebug((Function0)(() -> {
               Object var10000 = $this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId));
               return "Lowering target number of executors to " + var10000 + " (previously " + targetNum.oldNumExecutorsTarget() + " for resource profile id: " + rpId + ") because not all requested executors are actually needed";
            }));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$doUpdateRequest$7(final ExecutorAllocationManager $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         int rpId = x0$2._1$mcI$sp();
         TargetNumUpdates targetNum = (TargetNumUpdates)x0$2._2();
         $this.logWarning((Function0)(() -> "Unable to reach the cluster manager to request more executors!"));
         $this.numExecutorsTargetPerResourceProfileId().update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(targetNum.oldNumExecutorsTarget()));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$removeExecutors$2(final ExecutorAllocationManager $this, final scala.collection.mutable.Map numExecutorsTotalPerRpId$1, final ArrayBuffer executorIdsToBeRemoved$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String executorIdToBeRemoved = (String)x0$1._1();
         int rpId = x0$1._2$mcI$sp();
         if (rpId == ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID()) {
            if ($this.testing()) {
               throw new SparkException("ResourceProfile Id was UNKNOWN, this is not expected");
            } else {
               $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not removing executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, executorIdToBeRemoved)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because the ResourceProfile was UNKNOWN!"})))).log(scala.collection.immutable.Nil..MODULE$))));
               BoxedUnit var11 = BoxedUnit.UNIT;
            }
         } else {
            int newExecutorTotal = BoxesRunTime.unboxToInt(numExecutorsTotalPerRpId$1.getOrElseUpdate(BoxesRunTime.boxToInteger(rpId), (JFunction0.mcI.sp)() -> $this.executorMonitor().executorCountWithResourceProfile(rpId) - $this.executorMonitor().pendingRemovalCountPerResourceProfileId(rpId) - $this.executorMonitor().decommissioningPerResourceProfileId(rpId)));
            if (newExecutorTotal - 1 < $this.minNumExecutors()) {
               $this.logDebug((Function0)(() -> "Not removing idle executor " + executorIdToBeRemoved + " because there are only " + newExecutorTotal + " executor(s) left (minimum number of executor limit " + $this.minNumExecutors() + ")"));
               BoxedUnit var10 = BoxedUnit.UNIT;
            } else if (newExecutorTotal - 1 < BoxesRunTime.unboxToInt($this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId)))) {
               $this.logDebug((Function0)(() -> "Not removing idle executor " + executorIdToBeRemoved + " because there are only " + newExecutorTotal + " executor(s) left (number of executor target " + $this.numExecutorsTargetPerResourceProfileId().apply(BoxesRunTime.boxToInteger(rpId)) + ")"));
               BoxedUnit var9 = BoxedUnit.UNIT;
            } else {
               executorIdsToBeRemoved$1.$plus$eq(executorIdToBeRemoved);
               numExecutorsTotalPerRpId$1.update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(numExecutorsTotalPerRpId$1.apply(BoxesRunTime.boxToInteger(rpId))) - 1));
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   public ExecutorAllocationManager(final ExecutorAllocationClient client, final LiveListenerBus listenerBus, final SparkConf conf, final Option cleaner, final Clock clock, final ResourceProfileManager resourceProfileManager, final boolean reliableShuffleStorage) {
      this.org$apache$spark$ExecutorAllocationManager$$client = client;
      this.listenerBus = listenerBus;
      this.conf = conf;
      this.cleaner = cleaner;
      this.clock = clock;
      this.resourceProfileManager = resourceProfileManager;
      this.reliableShuffleStorage = reliableShuffleStorage;
      Logging.$init$(this);
      this.minNumExecutors = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS()));
      this.maxNumExecutors = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MAX_EXECUTORS()));
      this.org$apache$spark$ExecutorAllocationManager$$initialNumExecutors = Utils$.MODULE$.getDynamicAllocationInitialExecutors(conf);
      this.schedulerBacklogTimeoutS = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_SCHEDULER_BACKLOG_TIMEOUT()));
      this.sustainedSchedulerBacklogTimeoutS = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_SUSTAINED_SCHEDULER_BACKLOG_TIMEOUT()));
      this.testing = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_TESTING()));
      this.executorAllocationRatio = BoxesRunTime.unboxToDouble(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_EXECUTOR_ALLOCATION_RATIO()));
      this.decommissionEnabled = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.DECOMMISSION_ENABLED()));
      this.defaultProfileId = resourceProfileManager.defaultResourceProfile().id();
      this.validateSettings();
      this.numExecutorsToAddPerResourceProfileId = new HashMap();
      this.numExecutorsToAddPerResourceProfileId().update(BoxesRunTime.boxToInteger(this.defaultProfileId()), BoxesRunTime.boxToInteger(1));
      this.numExecutorsTargetPerResourceProfileId = new HashMap();
      this.numExecutorsTargetPerResourceProfileId().update(BoxesRunTime.boxToInteger(this.defaultProfileId()), BoxesRunTime.boxToInteger(this.org$apache$spark$ExecutorAllocationManager$$initialNumExecutors()));
      this.addTime = ExecutorAllocationManager$.MODULE$.NOT_SET();
      this.intervalMillis = 100L;
      this.listener = new ExecutorAllocationListener();
      this.executor = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("spark-dynamic-executor-allocation");
      this.executorAllocationManagerSource = new ExecutorAllocationManagerSource(this);
      this.executorMonitor = new ExecutorMonitor(conf, client, listenerBus, clock, this.executorAllocationManagerSource());
      this.org$apache$spark$ExecutorAllocationManager$$initializing = true;
      this.org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId = new HashMap();
      this.org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId().update(BoxesRunTime.boxToInteger(this.defaultProfileId()), BoxesRunTime.boxToInteger(0));
      this.org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount = scala.Predef..MODULE$.Map().empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class StageAttempt implements Product, Serializable {
      private final int stageId;
      private final int stageAttemptId;
      // $FF: synthetic field
      public final ExecutorAllocationManager $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int stageId() {
         return this.stageId;
      }

      public int stageAttemptId() {
         return this.stageAttemptId;
      }

      public String toString() {
         int var10000 = this.stageId();
         return "Stage " + var10000 + " (Attempt " + this.stageAttemptId() + ")";
      }

      public StageAttempt copy(final int stageId, final int stageAttemptId) {
         return this.org$apache$spark$ExecutorAllocationManager$StageAttempt$$$outer().new StageAttempt(stageId, stageAttemptId);
      }

      public int copy$default$1() {
         return this.stageId();
      }

      public int copy$default$2() {
         return this.stageAttemptId();
      }

      public String productPrefix() {
         return "StageAttempt";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.stageId());
            }
            case 1 -> {
               return BoxesRunTime.boxToInteger(this.stageAttemptId());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof StageAttempt;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "stageId";
            }
            case 1 -> {
               return "stageAttemptId";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.stageId());
         var1 = Statics.mix(var1, this.stageAttemptId());
         return Statics.finalizeHash(var1, 2);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label43: {
               if (x$1 instanceof StageAttempt && ((StageAttempt)x$1).org$apache$spark$ExecutorAllocationManager$StageAttempt$$$outer() == this.org$apache$spark$ExecutorAllocationManager$StageAttempt$$$outer()) {
                  StageAttempt var4 = (StageAttempt)x$1;
                  if (this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId() && var4.canEqual(this)) {
                     break label43;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public ExecutorAllocationManager org$apache$spark$ExecutorAllocationManager$StageAttempt$$$outer() {
         return this.$outer;
      }

      public StageAttempt(final int stageId, final int stageAttemptId) {
         this.stageId = stageId;
         this.stageAttemptId = stageAttemptId;
         if (ExecutorAllocationManager.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorAllocationManager.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class StageAttempt$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final ExecutorAllocationManager $outer;

      public final String toString() {
         return "StageAttempt";
      }

      public StageAttempt apply(final int stageId, final int stageAttemptId) {
         return this.$outer.new StageAttempt(stageId, stageAttemptId);
      }

      public Option unapply(final StageAttempt x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcII.sp(x$0.stageId(), x$0.stageAttemptId())));
      }

      public StageAttempt$() {
         if (ExecutorAllocationManager.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorAllocationManager.this;
            super();
         }
      }
   }

   public class ExecutorAllocationListener extends SparkListener {
      private final HashMap stageAttemptToNumTasks;
      private final HashMap stageAttemptToNumRunningTask;
      private final HashMap stageAttemptToTaskIndices;
      private final HashMap stageAttemptToSpeculativeTaskIndices;
      private final HashMap stageAttemptToPendingSpeculativeTasks;
      private final HashMap resourceProfileIdToStageAttempt;
      private final HashSet unschedulableTaskSets;
      private final HashMap stageAttemptToExecutorPlacementHints;
      // $FF: synthetic field
      public final ExecutorAllocationManager $outer;

      private HashMap stageAttemptToNumTasks() {
         return this.stageAttemptToNumTasks;
      }

      private HashMap stageAttemptToNumRunningTask() {
         return this.stageAttemptToNumRunningTask;
      }

      private HashMap stageAttemptToTaskIndices() {
         return this.stageAttemptToTaskIndices;
      }

      private HashMap stageAttemptToSpeculativeTaskIndices() {
         return this.stageAttemptToSpeculativeTaskIndices;
      }

      private HashMap stageAttemptToPendingSpeculativeTasks() {
         return this.stageAttemptToPendingSpeculativeTasks;
      }

      private HashMap resourceProfileIdToStageAttempt() {
         return this.resourceProfileIdToStageAttempt;
      }

      private HashSet unschedulableTaskSets() {
         return this.unschedulableTaskSets;
      }

      private HashMap stageAttemptToExecutorPlacementHints() {
         return this.stageAttemptToExecutorPlacementHints;
      }

      public void onStageSubmitted(final SparkListenerStageSubmitted stageSubmitted) {
         this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$initializing_$eq(false);
         int stageId = stageSubmitted.stageInfo().stageId();
         int stageAttemptId = stageSubmitted.stageInfo().attemptNumber();
         StageAttempt stageAttempt = this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().new StageAttempt(stageId, stageAttemptId);
         int numTasks = stageSubmitted.stageInfo().numTasks();
         synchronized(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer()){}

         try {
            this.stageAttemptToNumTasks().update(stageAttempt, BoxesRunTime.boxToInteger(numTasks));
            this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$onSchedulerBacklogged();
            int profId = stageSubmitted.stageInfo().resourceProfileId();
            this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().logDebug((Function0)(() -> "Stage resource profile id is: " + profId + " with numTasks: " + numTasks));
            ((Growable)this.resourceProfileIdToStageAttempt().getOrElseUpdate(BoxesRunTime.boxToInteger(profId), () -> new HashSet())).$plus$eq(stageAttempt);
            this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().numExecutorsToAddPerResourceProfileId().getOrElseUpdate(BoxesRunTime.boxToInteger(profId), (JFunction0.mcI.sp)() -> 1);
            IntRef numTasksPending = IntRef.create(0);
            HashMap hostToLocalTaskCountPerStage = new HashMap();
            stageSubmitted.stageInfo().taskLocalityPreferences().foreach((locality) -> {
               $anonfun$onStageSubmitted$4(numTasksPending, hostToLocalTaskCountPerStage, locality);
               return BoxedUnit.UNIT;
            });
            this.stageAttemptToExecutorPlacementHints().put(stageAttempt, new Tuple3(BoxesRunTime.boxToInteger(numTasksPending.elem), hostToLocalTaskCountPerStage.toMap(.MODULE$.refl()), BoxesRunTime.boxToInteger(profId)));
            this.updateExecutorPlacementHints();
            if (!this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().numExecutorsTargetPerResourceProfileId().contains(BoxesRunTime.boxToInteger(profId))) {
               this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().numExecutorsTargetPerResourceProfileId().put(BoxesRunTime.boxToInteger(profId), BoxesRunTime.boxToInteger(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$initialNumExecutors()));
               if (this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$initialNumExecutors() > 0) {
                  this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().logDebug((Function0)(() -> "requesting executors, rpId: " + profId + ", initial number is " + this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$initialNumExecutors()));
                  BoxesRunTime.boxToBoolean(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$client.requestTotalExecutors(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().numExecutorsTargetPerResourceProfileId().toMap(.MODULE$.refl()), this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId().toMap(.MODULE$.refl()), this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount()));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            } else {
               BoxedUnit var12 = BoxedUnit.UNIT;
            }
         } catch (Throwable var11) {
            throw var11;
         }

      }

      public void onStageCompleted(final SparkListenerStageCompleted stageCompleted) {
         int stageId = stageCompleted.stageInfo().stageId();
         int stageAttemptId = stageCompleted.stageInfo().attemptNumber();
         StageAttempt stageAttempt = this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().new StageAttempt(stageId, stageAttemptId);
         synchronized(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer()){}

         try {
            this.stageAttemptToNumTasks().$minus$eq(stageAttempt);
            this.stageAttemptToPendingSpeculativeTasks().$minus$eq(stageAttempt);
            this.stageAttemptToTaskIndices().$minus$eq(stageAttempt);
            this.stageAttemptToSpeculativeTaskIndices().$minus$eq(stageAttempt);
            this.stageAttemptToExecutorPlacementHints().$minus$eq(stageAttempt);
            this.removeStageFromResourceProfileIfUnused(stageAttempt);
            this.updateExecutorPlacementHints();
            if (this.stageAttemptToNumTasks().isEmpty() && this.stageAttemptToPendingSpeculativeTasks().isEmpty() && this.stageAttemptToSpeculativeTaskIndices().isEmpty()) {
               this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$onSchedulerQueueEmpty();
            }
         } catch (Throwable var7) {
            throw var7;
         }

      }

      public void onTaskStart(final SparkListenerTaskStart taskStart) {
         int stageId = taskStart.stageId();
         int stageAttemptId = taskStart.stageAttemptId();
         StageAttempt stageAttempt = this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().new StageAttempt(stageId, stageAttemptId);
         int taskIndex = taskStart.taskInfo().index();
         synchronized(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer()){}

         try {
            this.stageAttemptToNumRunningTask().update(stageAttempt, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.stageAttemptToNumRunningTask().getOrElse(stageAttempt, (JFunction0.mcI.sp)() -> 0)) + 1));
            if (taskStart.taskInfo().speculative()) {
               ((Growable)this.stageAttemptToSpeculativeTaskIndices().getOrElseUpdate(stageAttempt, () -> new HashSet())).$plus$eq(BoxesRunTime.boxToInteger(taskIndex));
               this.stageAttemptToPendingSpeculativeTasks().get(stageAttempt).foreach((x$3) -> BoxesRunTime.boxToBoolean($anonfun$onTaskStart$3(taskIndex, x$3)));
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               ((Growable)this.stageAttemptToTaskIndices().getOrElseUpdate(stageAttempt, () -> new HashSet())).$plus$eq(BoxesRunTime.boxToInteger(taskIndex));
            }

            if (!this.hasPendingTasks()) {
               this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$onSchedulerQueueEmpty();
            }
         } catch (Throwable var8) {
            throw var8;
         }

      }

      public void onTaskEnd(final SparkListenerTaskEnd taskEnd) {
         int stageId = taskEnd.stageId();
         int stageAttemptId = taskEnd.stageAttemptId();
         StageAttempt stageAttempt = this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().new StageAttempt(stageId, stageAttemptId);
         int taskIndex = taskEnd.taskInfo().index();
         synchronized(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer()){}

         try {
            if (this.stageAttemptToNumRunningTask().contains(stageAttempt)) {
               this.stageAttemptToNumRunningTask().update(stageAttempt, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.stageAttemptToNumRunningTask().apply(stageAttempt)) - 1));
               if (BoxesRunTime.unboxToInt(this.stageAttemptToNumRunningTask().apply(stageAttempt)) == 0) {
                  this.stageAttemptToNumRunningTask().$minus$eq(stageAttempt);
                  this.removeStageFromResourceProfileIfUnused(stageAttempt);
               }
            }

            if (taskEnd.taskInfo().speculative()) {
               this.stageAttemptToSpeculativeTaskIndices().get(stageAttempt).foreach((x$4) -> BoxesRunTime.boxToBoolean($anonfun$onTaskEnd$1(taskIndex, x$4)));
            }

            TaskEndReason var8 = taskEnd.reason();
            if (Success$.MODULE$.equals(var8)) {
               this.stageAttemptToPendingSpeculativeTasks().get(stageAttempt).foreach((x$5) -> BoxesRunTime.boxToBoolean($anonfun$onTaskEnd$2(taskIndex, x$5)));
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else if (var8 instanceof TaskKilled) {
               BoxedUnit var11 = BoxedUnit.UNIT;
            } else {
               if (!this.hasPendingTasks()) {
                  this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$onSchedulerBacklogged();
               }

               if (!taskEnd.taskInfo().speculative()) {
                  this.stageAttemptToTaskIndices().get(stageAttempt).foreach((x$6) -> BoxesRunTime.boxToBoolean($anonfun$onTaskEnd$3(taskIndex, x$6)));
                  BoxedUnit var12 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var13 = BoxedUnit.UNIT;
               }
            }
         } catch (Throwable var10) {
            throw var10;
         }

      }

      public void onSpeculativeTaskSubmitted(final SparkListenerSpeculativeTaskSubmitted speculativeTask) {
         int stageId = speculativeTask.stageId();
         int stageAttemptId = speculativeTask.stageAttemptId();
         StageAttempt stageAttempt = this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().new StageAttempt(stageId, stageAttemptId);
         int taskIndex = speculativeTask.taskIndex();
         synchronized(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer()){}

         try {
            ((HashSet)this.stageAttemptToPendingSpeculativeTasks().getOrElseUpdate(stageAttempt, () -> new HashSet())).add(BoxesRunTime.boxToInteger(taskIndex));
            this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$onSchedulerBacklogged();
         } catch (Throwable var8) {
            throw var8;
         }

      }

      public void onUnschedulableTaskSetAdded(final SparkListenerUnschedulableTaskSetAdded unschedulableTaskSetAdded) {
         int stageId = unschedulableTaskSetAdded.stageId();
         int stageAttemptId = unschedulableTaskSetAdded.stageAttemptId();
         StageAttempt stageAttempt = this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().new StageAttempt(stageId, stageAttemptId);
         synchronized(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer()){}

         try {
            this.unschedulableTaskSets().add(stageAttempt);
            this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$onSchedulerBacklogged();
         } catch (Throwable var7) {
            throw var7;
         }

      }

      public void onUnschedulableTaskSetRemoved(final SparkListenerUnschedulableTaskSetRemoved unschedulableTaskSetRemoved) {
         int stageId = unschedulableTaskSetRemoved.stageId();
         int stageAttemptId = unschedulableTaskSetRemoved.stageAttemptId();
         StageAttempt stageAttempt = this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().new StageAttempt(stageId, stageAttemptId);
         synchronized(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer()){}

         try {
            this.unschedulableTaskSets().remove(stageAttempt);
            this.removeStageFromResourceProfileIfUnused(stageAttempt);
         } catch (Throwable var7) {
            throw var7;
         }

      }

      public void removeStageFromResourceProfileIfUnused(final StageAttempt stageAttempt) {
         if (!this.stageAttemptToNumRunningTask().contains(stageAttempt) && !this.stageAttemptToNumTasks().contains(stageAttempt) && !this.stageAttemptToPendingSpeculativeTasks().contains(stageAttempt) && !this.stageAttemptToTaskIndices().contains(stageAttempt) && !this.stageAttemptToSpeculativeTaskIndices().contains(stageAttempt)) {
            Iterable rpForStage = ((MapOps)this.resourceProfileIdToStageAttempt().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$removeStageFromResourceProfileIfUnused$1(stageAttempt, x0$1)))).keys();
            if (rpForStage.size() == 1) {
               ((Shrinkable)this.resourceProfileIdToStageAttempt().apply(rpForStage.head())).$minus$eq(stageAttempt);
            } else {
               this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Should have exactly one resource profile for stage "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", but have "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT..MODULE$, stageAttempt)})))).$plus(this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_IDS..MODULE$, rpForStage)}))))));
            }
         }
      }

      public int pendingTasksPerResourceProfile(final int rpId) {
         Seq attempts = ((IterableOnceOps)this.resourceProfileIdToStageAttempt().getOrElse(BoxesRunTime.boxToInteger(rpId), () -> scala.Predef..MODULE$.Set().empty())).toSeq();
         return BoxesRunTime.unboxToInt(((IterableOnceOps)attempts.map((attempt) -> BoxesRunTime.boxToInteger($anonfun$pendingTasksPerResourceProfile$2(this, attempt)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      }

      public boolean hasPendingRegularTasks() {
         Iterable attemptSets = this.resourceProfileIdToStageAttempt().values();
         return attemptSets.exists((attempts) -> BoxesRunTime.boxToBoolean($anonfun$hasPendingRegularTasks$1(this, attempts)));
      }

      private int getPendingTaskSum(final StageAttempt attempt) {
         int numTotalTasks = BoxesRunTime.unboxToInt(this.stageAttemptToNumTasks().getOrElse(attempt, (JFunction0.mcI.sp)() -> 0));
         int numRunning = BoxesRunTime.unboxToInt(this.stageAttemptToTaskIndices().get(attempt).map((x$8) -> BoxesRunTime.boxToInteger($anonfun$getPendingTaskSum$2(x$8))).getOrElse((JFunction0.mcI.sp)() -> 0));
         return numTotalTasks - numRunning;
      }

      public int pendingSpeculativeTasksPerResourceProfile(final int rp) {
         Seq attempts = ((IterableOnceOps)this.resourceProfileIdToStageAttempt().getOrElse(BoxesRunTime.boxToInteger(rp), () -> scala.Predef..MODULE$.Set().empty())).toSeq();
         return BoxesRunTime.unboxToInt(((IterableOnceOps)attempts.map((attempt) -> BoxesRunTime.boxToInteger($anonfun$pendingSpeculativeTasksPerResourceProfile$2(this, attempt)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      }

      public boolean hasPendingSpeculativeTasks() {
         Iterable attemptSets = this.resourceProfileIdToStageAttempt().values();
         return attemptSets.exists((attempts) -> BoxesRunTime.boxToBoolean($anonfun$hasPendingSpeculativeTasks$1(this, attempts)));
      }

      private int getPendingSpeculativeTaskSum(final StageAttempt attempt) {
         return BoxesRunTime.unboxToInt(this.stageAttemptToPendingSpeculativeTasks().get(attempt).map((x$10) -> BoxesRunTime.boxToInteger($anonfun$getPendingSpeculativeTaskSum$1(x$10))).getOrElse((JFunction0.mcI.sp)() -> 0));
      }

      public int pendingUnschedulableTaskSetsPerResourceProfile(final int rp) {
         Seq attempts = ((IterableOnceOps)this.resourceProfileIdToStageAttempt().getOrElse(BoxesRunTime.boxToInteger(rp), () -> scala.Predef..MODULE$.Set().empty())).toSeq();
         return attempts.count((attempt) -> BoxesRunTime.boxToBoolean($anonfun$pendingUnschedulableTaskSetsPerResourceProfile$2(this, attempt)));
      }

      public boolean hasPendingTasks() {
         return this.hasPendingSpeculativeTasks() || this.hasPendingRegularTasks();
      }

      public int totalRunningTasksPerResourceProfile(final int rp) {
         Seq attempts = ((IterableOnceOps)this.resourceProfileIdToStageAttempt().getOrElse(BoxesRunTime.boxToInteger(rp), () -> scala.Predef..MODULE$.Set().empty())).toSeq();
         return BoxesRunTime.unboxToInt(((IterableOnceOps)attempts.map((attempt) -> BoxesRunTime.boxToInteger($anonfun$totalRunningTasksPerResourceProfile$2(this, attempt)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      }

      public void updateExecutorPlacementHints() {
         HashMap localityAwareTasksPerResourceProfileId = new HashMap();
         HashMap rplocalityToCount = new HashMap();
         this.stageAttemptToExecutorPlacementHints().values().foreach((x0$1) -> {
            $anonfun$updateExecutorPlacementHints$1(localityAwareTasksPerResourceProfileId, rplocalityToCount, x0$1);
            return BoxedUnit.UNIT;
         });
         this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$numLocalityAwareTasksPerResourceProfileId_$eq(localityAwareTasksPerResourceProfileId);
         this.org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer().org$apache$spark$ExecutorAllocationManager$$rpIdToHostToLocalTaskCount_$eq(rplocalityToCount.map((x0$3) -> {
            if (x0$3 != null) {
               int k = x0$3._1$mcI$sp();
               HashMap v = (HashMap)x0$3._2();
               return new Tuple2(BoxesRunTime.boxToInteger(k), v.toMap(.MODULE$.refl()));
            } else {
               throw new MatchError(x0$3);
            }
         }).toMap(.MODULE$.refl()));
      }

      // $FF: synthetic method
      public ExecutorAllocationManager org$apache$spark$ExecutorAllocationManager$ExecutorAllocationListener$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$onStageSubmitted$5(final HashMap hostToLocalTaskCountPerStage$1, final TaskLocation location) {
         int count = BoxesRunTime.unboxToInt(hostToLocalTaskCountPerStage$1.getOrElse(location.host(), (JFunction0.mcI.sp)() -> 0)) + 1;
         hostToLocalTaskCountPerStage$1.update(location.host(), BoxesRunTime.boxToInteger(count));
      }

      // $FF: synthetic method
      public static final void $anonfun$onStageSubmitted$4(final IntRef numTasksPending$1, final HashMap hostToLocalTaskCountPerStage$1, final Seq locality) {
         if (!locality.isEmpty()) {
            ++numTasksPending$1.elem;
            locality.foreach((location) -> {
               $anonfun$onStageSubmitted$5(hostToLocalTaskCountPerStage$1, location);
               return BoxedUnit.UNIT;
            });
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onTaskStart$3(final int taskIndex$1, final HashSet x$3) {
         return x$3.remove(BoxesRunTime.boxToInteger(taskIndex$1));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onTaskEnd$1(final int taskIndex$2, final HashSet x$4) {
         return x$4.remove(BoxesRunTime.boxToInteger(taskIndex$2));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onTaskEnd$2(final int taskIndex$2, final HashSet x$5) {
         return x$5.remove(BoxesRunTime.boxToInteger(taskIndex$2));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onTaskEnd$3(final int taskIndex$2, final HashSet x$6) {
         return x$6.remove(BoxesRunTime.boxToInteger(taskIndex$2));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$removeStageFromResourceProfileIfUnused$1(final StageAttempt stageAttempt$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            Set v = (Set)x0$1._2();
            return v.contains(stageAttempt$1);
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final int $anonfun$pendingTasksPerResourceProfile$2(final ExecutorAllocationListener $this, final StageAttempt attempt) {
         return $this.getPendingTaskSum(attempt);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hasPendingRegularTasks$2(final ExecutorAllocationListener $this, final StageAttempt x$7) {
         return $this.getPendingTaskSum(x$7) > 0;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hasPendingRegularTasks$1(final ExecutorAllocationListener $this, final Set attempts) {
         return attempts.exists((x$7) -> BoxesRunTime.boxToBoolean($anonfun$hasPendingRegularTasks$2($this, x$7)));
      }

      // $FF: synthetic method
      public static final int $anonfun$getPendingTaskSum$2(final HashSet x$8) {
         return x$8.size();
      }

      // $FF: synthetic method
      public static final int $anonfun$pendingSpeculativeTasksPerResourceProfile$2(final ExecutorAllocationListener $this, final StageAttempt attempt) {
         return $this.getPendingSpeculativeTaskSum(attempt);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hasPendingSpeculativeTasks$2(final ExecutorAllocationListener $this, final StageAttempt x$9) {
         return $this.getPendingSpeculativeTaskSum(x$9) > 0;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hasPendingSpeculativeTasks$1(final ExecutorAllocationListener $this, final Set attempts) {
         return attempts.exists((x$9) -> BoxesRunTime.boxToBoolean($anonfun$hasPendingSpeculativeTasks$2($this, x$9)));
      }

      // $FF: synthetic method
      public static final int $anonfun$getPendingSpeculativeTaskSum$1(final HashSet x$10) {
         return x$10.size();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$pendingUnschedulableTaskSetsPerResourceProfile$2(final ExecutorAllocationListener $this, final StageAttempt attempt) {
         return $this.unschedulableTaskSets().contains(attempt);
      }

      // $FF: synthetic method
      public static final int $anonfun$totalRunningTasksPerResourceProfile$2(final ExecutorAllocationListener $this, final StageAttempt attempt) {
         return BoxesRunTime.unboxToInt($this.stageAttemptToNumRunningTask().getOrElse(attempt, (JFunction0.mcI.sp)() -> 0));
      }

      // $FF: synthetic method
      public static final void $anonfun$updateExecutorPlacementHints$3(final HashMap rplocalityToCount$1, final int rpId$4, final Tuple2 x0$2) {
         if (x0$2 != null) {
            String hostname = (String)x0$2._1();
            int count = x0$2._2$mcI$sp();
            HashMap rpBasedHostToCount = (HashMap)rplocalityToCount$1.getOrElseUpdate(BoxesRunTime.boxToInteger(rpId$4), () -> new HashMap());
            int newUpdated = BoxesRunTime.unboxToInt(rpBasedHostToCount.getOrElse(hostname, (JFunction0.mcI.sp)() -> 0)) + count;
            rpBasedHostToCount.update(hostname, BoxesRunTime.boxToInteger(newUpdated));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$2);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$updateExecutorPlacementHints$1(final HashMap localityAwareTasksPerResourceProfileId$1, final HashMap rplocalityToCount$1, final Tuple3 x0$1) {
         if (x0$1 != null) {
            int numTasksPending = BoxesRunTime.unboxToInt(x0$1._1());
            Map localities = (Map)x0$1._2();
            int rpId = BoxesRunTime.unboxToInt(x0$1._3());
            int rpNumPending = BoxesRunTime.unboxToInt(localityAwareTasksPerResourceProfileId$1.getOrElse(BoxesRunTime.boxToInteger(rpId), (JFunction0.mcI.sp)() -> 0));
            localityAwareTasksPerResourceProfileId$1.update(BoxesRunTime.boxToInteger(rpId), BoxesRunTime.boxToInteger(rpNumPending + numTasksPending));
            localities.foreach((x0$2) -> {
               $anonfun$updateExecutorPlacementHints$3(rplocalityToCount$1, rpId, x0$2);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      }

      public ExecutorAllocationListener() {
         if (ExecutorAllocationManager.this == null) {
            throw null;
         } else {
            this.$outer = ExecutorAllocationManager.this;
            super();
            this.stageAttemptToNumTasks = new HashMap();
            this.stageAttemptToNumRunningTask = new HashMap();
            this.stageAttemptToTaskIndices = new HashMap();
            this.stageAttemptToSpeculativeTaskIndices = new HashMap();
            this.stageAttemptToPendingSpeculativeTasks = new HashMap();
            this.resourceProfileIdToStageAttempt = new HashMap();
            this.unschedulableTaskSets = new HashSet();
            this.stageAttemptToExecutorPlacementHints = new HashMap();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class TargetNumUpdates implements Product, Serializable {
      private final int delta;
      private final int oldNumExecutorsTarget;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int delta() {
         return this.delta;
      }

      public int oldNumExecutorsTarget() {
         return this.oldNumExecutorsTarget;
      }

      public TargetNumUpdates copy(final int delta, final int oldNumExecutorsTarget) {
         return new TargetNumUpdates(delta, oldNumExecutorsTarget);
      }

      public int copy$default$1() {
         return this.delta();
      }

      public int copy$default$2() {
         return this.oldNumExecutorsTarget();
      }

      public String productPrefix() {
         return "TargetNumUpdates";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.delta());
            }
            case 1 -> {
               return BoxesRunTime.boxToInteger(this.oldNumExecutorsTarget());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof TargetNumUpdates;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "delta";
            }
            case 1 -> {
               return "oldNumExecutorsTarget";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.delta());
         var1 = Statics.mix(var1, this.oldNumExecutorsTarget());
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label38: {
               if (x$1 instanceof TargetNumUpdates) {
                  TargetNumUpdates var4 = (TargetNumUpdates)x$1;
                  if (this.delta() == var4.delta() && this.oldNumExecutorsTarget() == var4.oldNumExecutorsTarget() && var4.canEqual(this)) {
                     break label38;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public TargetNumUpdates(final int delta, final int oldNumExecutorsTarget) {
         this.delta = delta;
         this.oldNumExecutorsTarget = oldNumExecutorsTarget;
         Product.$init$(this);
      }
   }

   public static class TargetNumUpdates$ extends AbstractFunction2 implements Serializable {
      public static final TargetNumUpdates$ MODULE$ = new TargetNumUpdates$();

      public final String toString() {
         return "TargetNumUpdates";
      }

      public TargetNumUpdates apply(final int delta, final int oldNumExecutorsTarget) {
         return new TargetNumUpdates(delta, oldNumExecutorsTarget);
      }

      public Option unapply(final TargetNumUpdates x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcII.sp(x$0.delta(), x$0.oldNumExecutorsTarget())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(TargetNumUpdates$.class);
      }
   }
}
