package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.broadcast.BroadcastManager;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.MergeStatus;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t}f\u0001B\u001c9\t}B\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006I!\u0014\u0005\t!\u0002\u0011\t\u0011)A\u0005\u001b\")\u0011\u000b\u0001C\u0001%\"Qq\u000b\u0001I\u0001\u0002\u0007\u0005\u000b\u0011\u0002-\t\u000f1\u0004!\u0019!C\u0005[\"1a\u000e\u0001Q\u0001\nmCqa\u001c\u0001C\u0002\u0013%\u0001\u000f\u0003\u0004r\u0001\u0001\u0006I!\u001b\u0005\u0006e\u0002!Ia\u001d\u0005\b\u0003\u0017\u0001A\u0011BA\u0007\u0011%\tI\u0002\u0001b\u0001\n\u0003\tY\u0002\u0003\u0005\u00020\u0001\u0001\u000b\u0011BA\u000f\u0011%\t\t\u0004\u0001b\u0001\n\u0003\tY\u0002\u0003\u0005\u00024\u0001\u0001\u000b\u0011BA\u000f\u0011%\t)\u0004\u0001b\u0001\n\u0003\t9\u0004\u0003\u0005\u0002B\u0001\u0001\u000b\u0011BA\u001d\u0011-\t\u0019\u0005\u0001a\u0001\u0002\u0003\u0006K!!\u0012\t\u0019\u00055\u0003\u00011AA\u0002\u0013\u0005\u0001(a\u0014\t\u0019\u0005}\u0003\u00011AA\u0002\u0013\u0005\u0001(!\u0019\t\u0017\u0005-\u0004\u00011A\u0001B\u0003&\u0011\u0011\u000b\u0005\f\u0003[\u0002\u0001\u0019!A!B\u0013\t)\u0005C\u0006\u0002p\u0001\u0001\r\u0011!Q!\n\u0005E\u0003bBA9\u0001\u0001\u0006K!\u0014\u0005\b\u0003g\u0002\u0001\u0015)\u0003N\u0011!\t)\b\u0001Q!\n\u0005]\u0004BCAN\u0001\t\u0007I\u0011\u0001\u001d\u0002\u001e\"A\u0011Q\u0017\u0001!\u0002\u0013\ty\nC\u0004\u00028\u0002!\t!!/\t\u000f\u0005\r\u0007\u0001\"\u0001\u0002F\"9\u0011\u0011\u001b\u0001\u0005\u0002\u0005M\u0007bBAn\u0001\u0011\u0005\u0011Q\u001c\u0005\b\u0003G\u0004A\u0011AAs\u0011\u001d\ti\u000f\u0001C\u0001\u0003_Dq!!>\u0001\t\u0003\t9\u0010C\u0004\u0002z\u0002!\t!a?\t\u000f\t\u0005\u0001\u0001\"\u0001\u0003\u0004!9!\u0011\u0004\u0001\u0005\u0002\tm\u0001b\u0002B\u0011\u0001\u0011\u0005!1\u0005\u0005\b\u0005k\u0001A\u0011\u0001B\u001c\u0011\u001d\u0011Y\u0004\u0001C\u0001\u0005{AqAa\u0010\u0001\t\u0003\u0011i\u0004C\u0004\u0003B\u0001!\tAa\u0011\t\u000f\t\u001d\u0003\u0001\"\u0001\u0003J!9!q\r\u0001\u0005\u0002\t%\u0004b\u0002B;\u0001\u0011\u0005!q\u000f\u0005\b\u0005s\u0002A\u0011\u0001B>\u0011\u001d\u0011I\t\u0001C\u0001\u0005\u0017CqAa&\u0001\t\u0003\u0011I\nC\u0004\u0003\u001c\u0002!\t!a>\t\u000f\tu\u0005\u0001\"\u0001\u0002x\u001eI!q\u0014\u001d\u0002\u0002#%!\u0011\u0015\u0004\toa\n\t\u0011#\u0003\u0003$\"1\u0011\u000b\u000eC\u0001\u0005KC\u0011Ba*5#\u0003%\tA!+\u0003\u001bMCWO\u001a4mKN#\u0018\r^;t\u0015\tI$(A\u0003ta\u0006\u00148N\u0003\u0002<y\u00051\u0011\r]1dQ\u0016T\u0011!P\u0001\u0004_J<7\u0001A\n\u0004\u0001\u00013\u0005CA!E\u001b\u0005\u0011%\"A\"\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0013%AB!osJ+g\r\u0005\u0002H\u00156\t\u0001J\u0003\u0002Jq\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002L\u0011\n9Aj\\4hS:<\u0017!\u00048v[B\u000b'\u000f^5uS>t7\u000f\u0005\u0002B\u001d&\u0011qJ\u0011\u0002\u0004\u0013:$\u0018a\u00038v[J+G-^2feN\fa\u0001P5oSRtDcA*V-B\u0011A\u000bA\u0007\u0002q!)Aj\u0001a\u0001\u001b\"9\u0001k\u0001I\u0001\u0002\u0004i\u0015a\u0001=%cA!\u0011)W.j\u0013\tQ&I\u0001\u0004UkBdWM\r\t\u00039\u001el\u0011!\u0018\u0006\u0003=~\u000baCU3f]R\u0014\u0018M\u001c;SK\u0006$wK]5uK2{7m\u001b\u0006\u0003A\u0006\fQ\u0001\\8dWNT!AY2\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002eK\u0006!Q\u000f^5m\u0015\u00051\u0017\u0001\u00026bm\u0006L!\u0001[/\u0003\u0011I+\u0017\r\u001a'pG.\u0004\"\u0001\u00186\n\u0005-l&!C,sSR,Gj\\2l\u0003!\u0011X-\u00193M_\u000e\\W#A.\u0002\u0013I,\u0017\r\u001a'pG.\u0004\u0013!C<sSR,Gj\\2l+\u0005I\u0017AC<sSR,Gj\\2lA\u0005aq/\u001b;i%\u0016\fG\rT8dWV\u0011Ao\u001e\u000b\u0004k\u0006\u0005\u0001C\u0001<x\u0019\u0001!Q\u0001_\u0005C\u0002e\u0014\u0011AQ\t\u0003uv\u0004\"!Q>\n\u0005q\u0014%a\u0002(pi\"Lgn\u001a\t\u0003\u0003zL!a \"\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\u0004%!\t\u0019AA\u0003\u0003\t1g\u000e\u0005\u0003B\u0003\u000f)\u0018bAA\u0005\u0005\nAAHY=oC6,g(A\u0007xSRDwK]5uK2{7m[\u000b\u0005\u0003\u001f\t\u0019\u0002\u0006\u0003\u0002\u0012\u0005U\u0001c\u0001<\u0002\u0014\u0011)\u0001P\u0003b\u0001s\"A\u00111\u0001\u0006\u0005\u0002\u0004\t9\u0002E\u0003B\u0003\u000f\t\t\"A\u0006nCB\u001cF/\u0019;vg\u0016\u001cXCAA\u000f!\u0015\t\u0015qDA\u0012\u0013\r\t\tC\u0011\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0005\u0003K\tY#\u0004\u0002\u0002()\u0019\u0011\u0011\u0006\u001d\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018\u0002BA\u0017\u0003O\u0011\u0011\"T1q'R\fG/^:\u0002\u00195\f\u0007o\u0015;biV\u001cXm\u001d\u0011\u0002%5\f\u0007o\u0015;biV\u001cXm\u001d#fY\u0016$X\rZ\u0001\u0014[\u0006\u00048\u000b^1ukN,7\u000fR3mKR,G\rI\u0001\u000e[\u0016\u0014x-Z*uCR,8/Z:\u0016\u0005\u0005e\u0002#B!\u0002 \u0005m\u0002\u0003BA\u0013\u0003{IA!a\u0010\u0002(\tYQ*\u001a:hKN#\u0018\r^;t\u00039iWM]4f'R\fG/^:fg\u0002\n\u0011dY1dQ\u0016$7+\u001a:jC2L'0\u001a3NCB\u001cF/\u0019;vgB)\u0011)a\b\u0002HA\u0019\u0011)!\u0013\n\u0007\u0005-#I\u0001\u0003CsR,\u0017!G2bG\",GmU3sS\u0006d\u0017N_3e\u0005J|\u0017\rZ2bgR,\"!!\u0015\u0011\r\u0005M\u0013\u0011LA/\u001b\t\t)FC\u0002\u0002Xa\n\u0011B\u0019:pC\u0012\u001c\u0017m\u001d;\n\t\u0005m\u0013Q\u000b\u0002\n\u0005J|\u0017\rZ2bgR\u0004R!QA\u0010\u0003\u000b\nQdY1dQ\u0016$7+\u001a:jC2L'0\u001a3Ce>\fGmY1ti~#S-\u001d\u000b\u0005\u0003G\nI\u0007E\u0002B\u0003KJ1!a\u001aC\u0005\u0011)f.\u001b;\t\u0011]\u001b\u0012\u0011!a\u0001\u0003#\n!dY1dQ\u0016$7+\u001a:jC2L'0\u001a3Ce>\fGmY1ti\u0002\n1dY1dQ\u0016$7+\u001a:jC2L'0\u001a3NKJ<Wm\u0015;biV\u001c\u0018\u0001J2bG\",GmU3sS\u0006d\u0017N_3e\u0005J|\u0017\rZ2bgRlUM]4f'R\fG/^:\u0002/}sW/\\!wC&d\u0017M\u00197f\u001b\u0006\u0004x*\u001e;qkR\u001c\u0018!G0ok6\fe/Y5mC\ndW-T3sO\u0016\u0014Vm];miN\f!d\u001d5vM\u001adW\rU;tQ6+'oZ3s\u0019>\u001c\u0017\r^5p]N\u0004b!!\u001f\u0002\n\u0006=e\u0002BA>\u0003\u000bsA!! \u0002\u00046\u0011\u0011q\u0010\u0006\u0004\u0003\u0003s\u0014A\u0002\u001fs_>$h(C\u0001D\u0013\r\t9IQ\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tY)!$\u0003\u0007M+\u0017OC\u0002\u0002\b\n\u0003B!!%\u0002\u00186\u0011\u00111\u0013\u0006\u0004\u0003+C\u0014aB:u_J\fw-Z\u0005\u0005\u00033\u000b\u0019J\u0001\bCY>\u001c7.T1oC\u001e,'/\u00133\u0002\u001f5\f\u0007/\u00133U_6\u000b\u0007/\u00138eKb,\"!a(\u0011\u000f\u0005\u0005\u00161VAX\u001b6\u0011\u00111\u0015\u0006\u0005\u0003K\u000b9+A\u0004nkR\f'\r\\3\u000b\u0007\u0005%&)\u0001\u0006d_2dWm\u0019;j_:LA!!,\u0002$\n9\u0001*Y:i\u001b\u0006\u0004\bcA!\u00022&\u0019\u00111\u0017\"\u0003\t1{gnZ\u0001\u0011[\u0006\u0004\u0018\n\u001a+p\u001b\u0006\u0004\u0018J\u001c3fq\u0002\nA\"\u00193e\u001b\u0006\u0004x*\u001e;qkR$b!a\u0019\u0002<\u0006}\u0006BBA_9\u0001\u0007Q*\u0001\u0005nCBLe\u000eZ3y\u0011\u001d\t\t\r\ba\u0001\u0003G\taa\u001d;biV\u001c\u0018\u0001D4fi6\u000b\u0007o\u0015;biV\u001cH\u0003BAd\u0003\u001b\u0004R!QAe\u0003GI1!a3C\u0005\u0019y\u0005\u000f^5p]\"9\u0011qZ\u000fA\u0002\u0005=\u0016!B7ba&#\u0017aD;qI\u0006$X-T1q\u001fV$\b/\u001e;\u0015\r\u0005\r\u0014Q[Al\u0011\u001d\tyM\ba\u0001\u0003_Cq!!7\u001f\u0001\u0004\ty)A\u0005c[\u0006#GM]3tg\u0006y!/Z7pm\u0016l\u0015\r](viB,H\u000f\u0006\u0004\u0002d\u0005}\u0017\u0011\u001d\u0005\u0007\u0003{{\u0002\u0019A'\t\u000f\u0005ew\u00041\u0001\u0002\u0010\u0006q\u0011\r\u001a3NKJ<WMU3tk2$HCBA2\u0003O\fY\u000f\u0003\u0004\u0002j\u0002\u0002\r!T\u0001\te\u0016$WoY3JI\"9\u0011\u0011\u0019\u0011A\u0002\u0005m\u0012A\b:fO&\u001cH/\u001a:TQV4g\r\\3NKJ<WM\u001d'pG\u0006$\u0018n\u001c8t)\u0011\t\u0019'!=\t\u000f\u0005M\u0018\u00051\u0001\u0002x\u0005q1\u000f[;gM2,W*\u001a:hKJ\u001c\u0018\u0001\b:f[>4Xm\u00155vM\u001adW-T3sO\u0016\u0014Hj\\2bi&|gn\u001d\u000b\u0003\u0003G\n\u0011C]3n_Z,W*\u001a:hKJ+7/\u001e7u)\u0019\t\u0019'!@\u0002\u0000\"1\u0011\u0011^\u0012A\u00025Cq!!7$\u0001\u0004\ty)A\nsK6|g/Z(viB,Ho](o\u0011>\u001cH\u000f\u0006\u0003\u0002d\t\u0015\u0001b\u0002B\u0004I\u0001\u0007!\u0011B\u0001\u0005Q>\u001cH\u000f\u0005\u0003\u0003\f\tMa\u0002\u0002B\u0007\u0005\u001f\u00012!! C\u0013\r\u0011\tBQ\u0001\u0007!J,G-\u001a4\n\t\tU!q\u0003\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\tE!)A\fsK6|g/Z(viB,Ho](o\u000bb,7-\u001e;peR!\u00111\rB\u000f\u0011\u001d\u0011y\"\na\u0001\u0005\u0013\ta!\u001a=fG&#\u0017!\u0006:f[>4XmT;uaV$8OQ=GS2$XM\u001d\u000b\u0005\u0003G\u0012)\u0003C\u0004\u0003(\u0019\u0002\rA!\u000b\u0002\u0003\u0019\u0004r!\u0011B\u0016\u0003\u001f\u0013y#C\u0002\u0003.\t\u0013\u0011BR;oGRLwN\\\u0019\u0011\u0007\u0005\u0013\t$C\u0002\u00034\t\u0013qAQ8pY\u0016\fg.\u0001\u000esK6|g/Z'fe\u001e,'+Z:vYR\u001c()\u001f$jYR,'\u000f\u0006\u0003\u0002d\te\u0002b\u0002B\u0014O\u0001\u0007!\u0011F\u0001\u0017]Vl\u0017I^1jY\u0006\u0014G.Z'ba>+H\u000f];ugV\tQ*\u0001\rok6\fe/Y5mC\ndW-T3sO\u0016\u0014Vm];miN\fQCZ5oI6K7o]5oOB\u000b'\u000f^5uS>t7\u000f\u0006\u0002\u0003FA)\u0011\u0011PAE\u001b\u0006\u00192/\u001a:jC2L'0\u001a3NCB\u001cF/\u0019;vgRQ\u0011Q\tB&\u0005+\u0012IF!\u0018\t\u000f\t53\u00061\u0001\u0003P\u0005\u0001\"M]8bI\u000e\f7\u000f^'b]\u0006<WM\u001d\t\u0005\u0003'\u0012\t&\u0003\u0003\u0003T\u0005U#\u0001\u0005\"s_\u0006$7-Y:u\u001b\u0006t\u0017mZ3s\u0011\u001d\u00119f\u000ba\u0001\u0005_\tq![:M_\u000e\fG\u000e\u0003\u0004\u0003\\-\u0002\r!T\u0001\u0011[&t'I]8bI\u000e\f7\u000f^*ju\u0016DqAa\u0018,\u0001\u0004\u0011\t'\u0001\u0003d_:4\u0007c\u0001+\u0003d%\u0019!Q\r\u001d\u0003\u0013M\u0003\u0018M]6D_:4\u0017aG:fe&\fG.\u001b>fI6\u000b\u0007/\u00118e\u001b\u0016\u0014x-Z*uCR,8\u000f\u0006\u0006\u0003l\t5$q\u000eB9\u0005g\u0002b!Q-\u0002F\u0005\u0015\u0003b\u0002B'Y\u0001\u0007!q\n\u0005\b\u0005/b\u0003\u0019\u0001B\u0018\u0011\u0019\u0011Y\u0006\fa\u0001\u001b\"9!q\f\u0017A\u0002\t\u0005\u0014\u0001\b5bg\u000e\u000b7\r[3e'\u0016\u0014\u0018.\u00197ju\u0016$'I]8bI\u000e\f7\u000f^\u000b\u0003\u0005_\tqb^5uQ6\u000b\u0007o\u0015;biV\u001cXm]\u000b\u0005\u0005{\u0012\t\t\u0006\u0003\u0003\u0000\t\u0015\u0005c\u0001<\u0003\u0002\u00121!1\u0011\u0018C\u0002e\u0014\u0011\u0001\u0016\u0005\b\u0005Oq\u0003\u0019\u0001BD!\u001d\t%1FA\u000f\u0005\u007f\n\u0011c^5uQ6+'oZ3Ti\u0006$Xo]3t+\u0011\u0011iI!%\u0015\t\t=%1\u0013\t\u0004m\nEEA\u0002BB_\t\u0007\u0011\u0010C\u0004\u0003(=\u0002\rA!&\u0011\u000f\u0005\u0013Y#!\u000f\u0003\u0010\u0006ir-\u001a;TQV4g\r\\3QkNDW*\u001a:hKJdunY1uS>t7/\u0006\u0002\u0002x\u0005A\u0013N\u001c<bY&$\u0017\r^3TKJL\u0017\r\\5{K\u0012l\u0015\r](viB,Ho\u0015;biV\u001c8)Y2iK\u0006Q\u0013N\u001c<bY&$\u0017\r^3TKJL\u0017\r\\5{K\u0012lUM]4f\u001fV$\b/\u001e;Ti\u0006$Xo]\"bG\",\u0017!D*ik\u001a4G.Z*uCR,8\u000f\u0005\u0002UiM\u0011A\u0007\u0011\u000b\u0003\u0005C\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012TC\u0001BVU\ri%QV\u0016\u0003\u0005_\u0003BA!-\u0003<6\u0011!1\u0017\u0006\u0005\u0005k\u00139,A\u0005v]\u000eDWmY6fI*\u0019!\u0011\u0018\"\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003>\nM&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0002"
)
public class ShuffleStatus implements Logging {
   private final int numPartitions;
   // $FF: synthetic field
   private final Tuple2 x$1;
   private final ReentrantReadWriteLock.ReadLock readLock;
   private final ReentrantReadWriteLock.WriteLock writeLock;
   private final MapStatus[] mapStatuses;
   private final MapStatus[] mapStatusesDeleted;
   private final MergeStatus[] mergeStatuses;
   private byte[] cachedSerializedMapStatus;
   private Broadcast cachedSerializedBroadcast;
   private byte[] cachedSerializedMergeStatus;
   private Broadcast cachedSerializedBroadcastMergeStatus;
   private int _numAvailableMapOutputs;
   private int _numAvailableMergeResults;
   private Seq shufflePushMergerLocations;
   private final HashMap mapIdToMapIndex;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static int $lessinit$greater$default$2() {
      return ShuffleStatus$.MODULE$.$lessinit$greater$default$2();
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

   private ReentrantReadWriteLock.ReadLock readLock() {
      return this.readLock;
   }

   private ReentrantReadWriteLock.WriteLock writeLock() {
      return this.writeLock;
   }

   private Object withReadLock(final Function0 fn) {
      this.readLock().lock();

      Object var10000;
      try {
         var10000 = fn.apply();
      } finally {
         this.readLock().unlock();
      }

      return var10000;
   }

   private Object withWriteLock(final Function0 fn) {
      this.writeLock().lock();

      Object var10000;
      try {
         var10000 = fn.apply();
      } finally {
         this.writeLock().unlock();
      }

      return var10000;
   }

   public MapStatus[] mapStatuses() {
      return this.mapStatuses;
   }

   public MapStatus[] mapStatusesDeleted() {
      return this.mapStatusesDeleted;
   }

   public MergeStatus[] mergeStatuses() {
      return this.mergeStatuses;
   }

   public Broadcast cachedSerializedBroadcast() {
      return this.cachedSerializedBroadcast;
   }

   public void cachedSerializedBroadcast_$eq(final Broadcast x$1) {
      this.cachedSerializedBroadcast = x$1;
   }

   public HashMap mapIdToMapIndex() {
      return this.mapIdToMapIndex;
   }

   public void addMapOutput(final int mapIndex, final MapStatus status) {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         MapStatus currentMapStatus = this.mapStatuses()[mapIndex];
         if (currentMapStatus == null) {
            ++this._numAvailableMapOutputs;
            this.invalidateSerializedMapOutputStatusCache();
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            this.mapIdToMapIndex().remove(BoxesRunTime.boxToLong(currentMapStatus.mapId()));
         }

         this.mapStatuses()[mapIndex] = status;
         this.mapIdToMapIndex().update(BoxesRunTime.boxToLong(status.mapId()), BoxesRunTime.boxToInteger(mapIndex));
      });
   }

   public Option getMapStatus(final long mapId) {
      return (Option)this.withReadLock(() -> {
         Option var4 = this.mapIdToMapIndex().get(BoxesRunTime.boxToLong(mapId)).map((x$6) -> $anonfun$getMapStatus$2(this, BoxesRunTime.unboxToInt(x$6)));
         if (var4 instanceof Some var5) {
            MapStatus var6 = (MapStatus)var5.value();
            if (var6 == null) {
               return .MODULE$;
            }
         }

         return var4;
      });
   }

   public void updateMapOutput(final long mapId, final BlockManagerId bmAddress) {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         try {
            Option mapIndex = this.mapIdToMapIndex().get(BoxesRunTime.boxToLong(mapId));
            Option mapStatusOpt = mapIndex.map((x$7) -> $anonfun$updateMapOutput$2(this, BoxesRunTime.unboxToInt(x$7))).flatMap((x$8) -> scala.Option..MODULE$.apply(x$8));
            if (mapStatusOpt instanceof Some var8) {
               MapStatus mapStatus = (MapStatus)var8.value();
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Updating map output for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAP_ID..MODULE$, BoxesRunTime.boxToLong(mapId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, bmAddress)}))))));
               mapStatus.updateLocation(bmAddress);
               this.invalidateSerializedMapOutputStatusCache();
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               if (!.MODULE$.equals(mapStatusOpt)) {
                  throw new MatchError(mapStatusOpt);
               }

               Object qual$1 = scala.Predef..MODULE$.refArrayOps(this.mapStatusesDeleted());
               Function1 x$1 = (x) -> BoxesRunTime.boxToBoolean($anonfun$updateMapOutput$5(mapId, x));
               int x$2 = scala.collection.ArrayOps..MODULE$.indexWhere$default$2$extension(qual$1);
               int index = scala.collection.ArrayOps..MODULE$.indexWhere$extension(qual$1, x$1, x$2);
               if (index >= 0 && this.mapStatuses()[index] == null) {
                  MapStatus mapStatus = this.mapStatusesDeleted()[index];
                  mapStatus.updateLocation(bmAddress);
                  this.mapStatuses()[index] = mapStatus;
                  ++this._numAvailableMapOutputs;
                  this.invalidateSerializedMapOutputStatusCache();
                  this.mapStatusesDeleted()[index] = null;
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Recover ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAP_ID..MODULE$, BoxesRunTime.boxToLong(mapStatus.mapId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_MANAGER_ID..MODULE$, mapStatus.location())}))))));
                  BoxedUnit var18 = BoxedUnit.UNIT;
               } else {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to update map output ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAP_ID..MODULE$, BoxesRunTime.boxToLong(mapId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for untracked map status."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  BoxedUnit var17 = BoxedUnit.UNIT;
               }
            }
         } catch (NullPointerException var16) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to update map output for ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAP_ID..MODULE$, BoxesRunTime.boxToLong(mapId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"status removed in-flight"})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

      });
   }

   public void removeMapOutput(final int mapIndex, final BlockManagerId bmAddress) {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         MapStatus currentMapStatus;
         label16: {
            this.logDebug((Function0)(() -> "Removing existing map output " + mapIndex + " " + bmAddress));
            currentMapStatus = this.mapStatuses()[mapIndex];
            if (currentMapStatus != null) {
               BlockManagerId var10000 = currentMapStatus.location();
               if (var10000 == null) {
                  if (bmAddress == null) {
                     break label16;
                  }
               } else if (var10000.equals(bmAddress)) {
                  break label16;
               }
            }

            return;
         }

         --this._numAvailableMapOutputs;
         this.mapIdToMapIndex().remove(BoxesRunTime.boxToLong(currentMapStatus.mapId()));
         this.mapStatusesDeleted()[mapIndex] = currentMapStatus;
         this.mapStatuses()[mapIndex] = null;
         this.invalidateSerializedMapOutputStatusCache();
      });
   }

   public void addMergeResult(final int reduceId, final MergeStatus status) {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         label14: {
            MergeStatus var10000 = this.mergeStatuses()[reduceId];
            if (var10000 == null) {
               if (status == null) {
                  break label14;
               }
            } else if (var10000.equals(status)) {
               break label14;
            }

            ++this._numAvailableMergeResults;
            this.invalidateSerializedMergeOutputStatusCache();
         }

         this.mergeStatuses()[reduceId] = status;
      });
   }

   public void registerShuffleMergerLocations(final Seq shuffleMergers) {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         if (this.shufflePushMergerLocations.isEmpty()) {
            this.shufflePushMergerLocations = shuffleMergers;
         }
      });
   }

   public void removeShuffleMergerLocations() {
      this.withWriteLock((JFunction0.mcV.sp)() -> this.shufflePushMergerLocations = scala.collection.immutable.Nil..MODULE$);
   }

   public void removeMergeResult(final int reduceId, final BlockManagerId bmAddress) {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         label16: {
            if (this.mergeStatuses()[reduceId] != null) {
               BlockManagerId var10000 = this.mergeStatuses()[reduceId].location();
               if (var10000 == null) {
                  if (bmAddress == null) {
                     break label16;
                  }
               } else if (var10000.equals(bmAddress)) {
                  break label16;
               }
            }

            return;
         }

         --this._numAvailableMergeResults;
         this.mergeStatuses()[reduceId] = null;
         this.invalidateSerializedMergeOutputStatusCache();
      });
   }

   public void removeOutputsOnHost(final String host) {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         this.logDebug((Function0)(() -> "Removing outputs for host " + host));
         this.removeOutputsByFilter((x) -> BoxesRunTime.boxToBoolean($anonfun$removeOutputsOnHost$3(host, x)));
         this.removeMergeResultsByFilter((x) -> BoxesRunTime.boxToBoolean($anonfun$removeOutputsOnHost$4(host, x)));
      });
   }

   public void removeOutputsOnExecutor(final String execId) {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         this.logDebug((Function0)(() -> "Removing outputs for execId " + execId));
         this.removeOutputsByFilter((x) -> BoxesRunTime.boxToBoolean($anonfun$removeOutputsOnExecutor$3(execId, x)));
      });
   }

   public void removeOutputsByFilter(final Function1 f) {
      this.withWriteLock((JFunction0.mcV.sp)() -> scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.mapStatuses())).foreach$mVc$sp((JFunction1.mcVI.sp)(mapIndex) -> {
            MapStatus currentMapStatus = this.mapStatuses()[mapIndex];
            if (currentMapStatus != null && BoxesRunTime.unboxToBoolean(f.apply(currentMapStatus.location()))) {
               --this._numAvailableMapOutputs;
               this.mapIdToMapIndex().remove(BoxesRunTime.boxToLong(currentMapStatus.mapId()));
               this.mapStatusesDeleted()[mapIndex] = currentMapStatus;
               this.mapStatuses()[mapIndex] = null;
               this.invalidateSerializedMapOutputStatusCache();
            }
         }));
   }

   public void removeMergeResultsByFilter(final Function1 f) {
      this.withWriteLock((JFunction0.mcV.sp)() -> scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.mergeStatuses())).foreach$mVc$sp((JFunction1.mcVI.sp)(reduceId) -> {
            if (this.mergeStatuses()[reduceId] != null && BoxesRunTime.unboxToBoolean(f.apply(this.mergeStatuses()[reduceId].location()))) {
               --this._numAvailableMergeResults;
               this.mergeStatuses()[reduceId] = null;
               this.invalidateSerializedMergeOutputStatusCache();
            }
         }));
   }

   public int numAvailableMapOutputs() {
      return BoxesRunTime.unboxToInt(this.withReadLock((JFunction0.mcI.sp)() -> this._numAvailableMapOutputs));
   }

   public int numAvailableMergeResults() {
      return BoxesRunTime.unboxToInt(this.withReadLock((JFunction0.mcI.sp)() -> this._numAvailableMergeResults));
   }

   public Seq findMissingPartitions() {
      return (Seq)this.withReadLock(() -> {
         IndexedSeq missing = (IndexedSeq)scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.numPartitions).filter((JFunction1.mcZI.sp)(id) -> this.mapStatuses()[id] == null);
         scala.Predef..MODULE$.assert(missing.size() == this.numPartitions - this._numAvailableMapOutputs, () -> {
            int var10000 = missing.size();
            return var10000 + " missing, expected " + (this.numPartitions - this._numAvailableMapOutputs);
         });
         return missing;
      });
   }

   public byte[] serializedMapStatus(final BroadcastManager broadcastManager, final boolean isLocal, final int minBroadcastSize, final SparkConf conf) {
      ObjectRef result = ObjectRef.create((Object)null);
      this.withReadLock((JFunction0.mcV.sp)() -> {
         if (this.cachedSerializedMapStatus != null) {
            result.elem = this.cachedSerializedMapStatus;
         }
      });
      if ((byte[])result.elem == null) {
         this.withWriteLock((JFunction0.mcV.sp)() -> {
            if (this.cachedSerializedMapStatus == null) {
               Tuple2 serResult = MapOutputTracker$.MODULE$.serializeOutputStatuses(this.mapStatuses(), broadcastManager, isLocal, minBroadcastSize, conf);
               this.cachedSerializedMapStatus = (byte[])serResult._1();
               this.cachedSerializedBroadcast_$eq((Broadcast)serResult._2());
            }

            result.elem = this.cachedSerializedMapStatus;
         });
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return (byte[])result.elem;
   }

   public Tuple2 serializedMapAndMergeStatus(final BroadcastManager broadcastManager, final boolean isLocal, final int minBroadcastSize, final SparkConf conf) {
      byte[] mapStatusesBytes = this.serializedMapStatus(broadcastManager, isLocal, minBroadcastSize, conf);
      ObjectRef mergeStatusesBytes = ObjectRef.create((Object)null);
      this.withReadLock((JFunction0.mcV.sp)() -> {
         if (this.cachedSerializedMergeStatus != null) {
            mergeStatusesBytes.elem = this.cachedSerializedMergeStatus;
         }
      });
      if ((byte[])mergeStatusesBytes.elem == null) {
         this.withWriteLock((JFunction0.mcV.sp)() -> {
            if (this.cachedSerializedMergeStatus == null) {
               Tuple2 serResult = MapOutputTracker$.MODULE$.serializeOutputStatuses(this.mergeStatuses(), broadcastManager, isLocal, minBroadcastSize, conf);
               this.cachedSerializedMergeStatus = (byte[])serResult._1();
               this.cachedSerializedBroadcastMergeStatus = (Broadcast)serResult._2();
            }

            mergeStatusesBytes.elem = this.cachedSerializedMergeStatus;
         });
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return new Tuple2(mapStatusesBytes, (byte[])mergeStatusesBytes.elem);
   }

   public boolean hasCachedSerializedBroadcast() {
      return BoxesRunTime.unboxToBoolean(this.withReadLock((JFunction0.mcZ.sp)() -> this.cachedSerializedBroadcast() != null));
   }

   public Object withMapStatuses(final Function1 f) {
      return this.withReadLock(() -> f.apply(this.mapStatuses()));
   }

   public Object withMergeStatuses(final Function1 f) {
      return this.withReadLock(() -> f.apply(this.mergeStatuses()));
   }

   public Seq getShufflePushMergerLocations() {
      return (Seq)this.withReadLock(() -> this.shufflePushMergerLocations);
   }

   public void invalidateSerializedMapOutputStatusCache() {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         if (this.cachedSerializedBroadcast() != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.cachedSerializedBroadcast().destroy());
            this.cachedSerializedBroadcast_$eq((Broadcast)null);
         }

         this.cachedSerializedMapStatus = null;
      });
   }

   public void invalidateSerializedMergeOutputStatusCache() {
      this.withWriteLock((JFunction0.mcV.sp)() -> {
         if (this.cachedSerializedBroadcastMergeStatus != null) {
            Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.cachedSerializedBroadcastMergeStatus.destroy());
            this.cachedSerializedBroadcastMergeStatus = null;
         }

         this.cachedSerializedMergeStatus = null;
      });
   }

   // $FF: synthetic method
   public static final MapStatus $anonfun$getMapStatus$2(final ShuffleStatus $this, final int x$6) {
      return $this.mapStatuses()[x$6];
   }

   // $FF: synthetic method
   public static final MapStatus $anonfun$updateMapOutput$2(final ShuffleStatus $this, final int x$7) {
      return $this.mapStatuses()[x$7];
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateMapOutput$5(final long mapId$2, final MapStatus x) {
      return x != null && x.mapId() == mapId$2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeOutputsOnHost$3(final String host$1, final BlockManagerId x) {
      boolean var3;
      label23: {
         String var10000 = x.host();
         if (var10000 == null) {
            if (host$1 == null) {
               break label23;
            }
         } else if (var10000.equals(host$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeOutputsOnHost$4(final String host$1, final BlockManagerId x) {
      boolean var3;
      label23: {
         String var10000 = x.host();
         if (var10000 == null) {
            if (host$1 == null) {
               break label23;
            }
         } else if (var10000.equals(host$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeOutputsOnExecutor$3(final String execId$1, final BlockManagerId x) {
      boolean var3;
      label23: {
         String var10000 = x.executorId();
         if (var10000 == null) {
            if (execId$1 == null) {
               break label23;
            }
         } else if (var10000.equals(execId$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public ShuffleStatus(final int numPartitions, final int numReducers) {
      this.numPartitions = numPartitions;
      Logging.$init$(this);
      ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
      Tuple2 var4 = new Tuple2(lock.readLock(), lock.writeLock());
      if (var4 != null) {
         ReentrantReadWriteLock.ReadLock readLock = (ReentrantReadWriteLock.ReadLock)var4._1();
         ReentrantReadWriteLock.WriteLock writeLock = (ReentrantReadWriteLock.WriteLock)var4._2();
         this.x$1 = new Tuple2(readLock, writeLock);
         this.readLock = (ReentrantReadWriteLock.ReadLock)this.x$1._1();
         this.writeLock = (ReentrantReadWriteLock.WriteLock)this.x$1._2();
         this.mapStatuses = new MapStatus[numPartitions];
         this.mapStatusesDeleted = new MapStatus[numPartitions];
         this.mergeStatuses = numReducers > 0 ? new MergeStatus[numReducers] : (MergeStatus[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(MergeStatus.class));
         this._numAvailableMapOutputs = 0;
         this._numAvailableMergeResults = 0;
         this.shufflePushMergerLocations = (Seq)scala.package..MODULE$.Seq().empty();
         this.mapIdToMapIndex = new HashMap();
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
