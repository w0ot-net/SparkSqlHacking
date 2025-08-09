package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Product2;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tmeA\u0002\f\u0018\u0003\u0003I\u0012\u0005\u0003\u0005=\u0001\t\u0005\t\u0015!\u0003>\u0011!!\u0006AaA!\u0002\u0017)\u0006\u0002C.\u0001\u0005\u0007\u0005\u000b1\u0002/\t\u000b\u0001\u0004A\u0011A1\t\u000b\u001d\u0004a\u0011\u00015\t\u000bE\u0004a\u0011\u0001:\t\u000f\u0005\r\u0001A\"\u0001\u0002\u0006!9\u00111\u0004\u0001\u0005\u0002\u0005u\u0001bBA+\u0001\u0011\u0005\u0011q\u000b\u0005\b\u0003K\u0002A\u0011AA4\u0011\u001d\t)\u0007\u0001C\u0001\u0003[Bq!! \u0001\t\u0003\ty\bC\u0004\u0002\u0004\u0002!\t!!\"\t\u000f\u0005\r\u0005\u0001\"\u0001\u00028\"9\u0011\u0011\u001d\u0001\u0005\u0002\u0005\r\bbBAq\u0001\u0011\u0005!1\u0002\u0005\b\u0005s\u0001A\u0011\u0001B\u001e\u0011\u001d\u0011\u0019\u0006\u0001C\u0001\u0005+BqA!\u0018\u0001\t\u0003\u0011y\u0006C\u0004\u0003~\u0001!\tAa \t\u000f\t\u0005\u0005\u0001b\u0003\u0003\u0004\n1b+\u001a:uKb\u0004\u0016M\u001d;ji&|gNQ1tK>\u00038O\u0003\u0002\u00193\u0005!\u0011.\u001c9m\u0015\tQ2$\u0001\u0004he\u0006\u0004\b\u000e\u001f\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sOV\u0019!EU \u0014\t\u0001\u0019\u0013F\u000e\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005)\u001adBA\u00162\u001d\ta\u0003'D\u0001.\u0015\tqs&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u00051\u0013B\u0001\u001a&\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001N\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005I*\u0003CA\u001c;\u001b\u0005A$BA\u001d\u001c\u0003!Ig\u000e^3s]\u0006d\u0017BA\u001e9\u0005\u001daunZ4j]\u001e\fAa]3mMB\u0019ahP)\r\u0001\u0011)\u0001\t\u0001b\u0001\u0003\n!1+\u001a7g+\t\u00115*\u0005\u0002D\rB\u0011A\u0005R\u0005\u0003\u000b\u0016\u0012qAT8uQ&tw\rE\u0002H\u0011*k\u0011aF\u0005\u0003\u0013^\u00111CV3si\u0016D\b+\u0019:uSRLwN\u001c\"bg\u0016\u0004\"AP&\u0005\u000b1{$\u0019A'\u0003\u0003a\u000b\"a\u0011(\u0011\u0005\u0011z\u0015B\u0001)&\u0005\r\te.\u001f\t\u0003}I#Qa\u0015\u0001C\u00025\u0013!A\u0016#\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002W3Fk\u0011a\u0016\u0006\u00031\u0016\nqA]3gY\u0016\u001cG/\u0003\u0002[/\nA1\t\\1tgR\u000bw-\u0001\u0006fm&$WM\\2fII\u00022aR/`\u0013\tqvCA\u0011WKJ$X\r\u001f)beRLG/[8o\u0005\u0006\u001cXm\u00149t\u0007>t7\u000f\u001e:vGR|'\u000f\u0005\u0002?\u007f\u00051A(\u001b8jiz\"\"A\u00194\u0015\u0007\r$W\r\u0005\u0003H\u0001E{\u0006\"\u0002+\u0005\u0001\b)\u0006\"B.\u0005\u0001\ba\u0006\"\u0002\u001f\u0005\u0001\u0004i\u0014!C<ji\"Le\u000eZ3y)\ti\u0014\u000eC\u0003k\u000b\u0001\u00071.A\u0003j]\u0012,\u0007\u0010\u0005\u0002m]:\u0011q)\\\u0005\u0003e]I!a\u001c9\u0003%Y+'\u000f^3y\u0013\u0012$v.\u00138eKbl\u0015\r\u001d\u0006\u0003e]\t!b^5uQZ\u000bG.^3t+\t\u0019x\u000f\u0006\u0002uyR\u0011Q/\u001f\t\u0004}}2\bC\u0001 x\t\u0015AhA1\u0001N\u0005\r1FI\r\u0005\bu\u001a\t\t\u0011q\u0001|\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004-f3\b\"B?\u0007\u0001\u0004q\u0018A\u0002<bYV,7\u000fE\u0002%\u007fZL1!!\u0001&\u0005\u0015\t%O]1z\u0003!9\u0018\u000e\u001e5NCN\\GcA\u001f\u0002\b!9\u0011\u0011B\u0004A\u0002\u0005-\u0011\u0001B7bg.\u0004B!!\u0004\u0002\u00185\u0011\u0011q\u0002\u0006\u0005\u0003#\t\u0019\"\u0001\u0006d_2dWm\u0019;j_:T1!!\u0006\u001c\u0003\u0011)H/\u001b7\n\t\u0005e\u0011q\u0002\u0002\u0007\u0005&$8+\u001a;\u0002\u00075\f\u0007/\u0006\u0003\u0002 \u0005\u001dB\u0003BA\u0011\u0003_!B!a\t\u0002*A!ahPA\u0013!\rq\u0014q\u0005\u0003\u0006q\"\u0011\r!\u0014\u0005\n\u0003WA\u0011\u0011!a\u0002\u0003[\t!\"\u001a<jI\u0016t7-\u001a\u00135!\u00111\u0016,!\n\t\u000f\u0005E\u0002\u00021\u0001\u00024\u0005\ta\r\u0005\u0005%\u0003k\tI$UA\u0013\u0013\r\t9$\n\u0002\n\rVt7\r^5p]J\u0002B!a\u000f\u0002P9!\u0011QHA'\u001d\u0011\ty$a\u0013\u000f\t\u0005\u0005\u0013\u0011\n\b\u0005\u0003\u0007\n9ED\u0002-\u0003\u000bJ\u0011\u0001I\u0005\u0003=}I!\u0001H\u000f\n\u0005iY\u0012B\u0001\u001a\u001a\u0013\u0011\t\t&a\u0015\u0003\u0011Y+'\u000f^3y\u0013\u0012T!AM\r\u0002\r\u0019LG\u000e^3s)\ri\u0014\u0011\f\u0005\b\u00037J\u0001\u0019AA/\u0003\u0011\u0001(/\u001a3\u0011\u0011\u0011\n)$!\u000fR\u0003?\u00022\u0001JA1\u0013\r\t\u0019'\n\u0002\b\u0005>|G.Z1o\u0003\u0015i\u0017N\\;t)\ri\u0014\u0011\u000e\u0005\u0007\u0003WR\u0001\u0019A\u001f\u0002\u000b=$\b.\u001a:\u0015\u0007u\ny\u0007C\u0004\u0002l-\u0001\r!!\u001d\u0011\u000b)\n\u0019(a\u001e\n\u0007\u0005UTG\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\u0019!\u0013\u0011PA\u001d#&\u0019\u00111P\u0013\u0003\rQ+\b\u000f\\33\u0003\u0011!\u0017N\u001a4\u0015\u0007u\n\t\t\u0003\u0004\u0002l1\u0001\r!P\u0001\tY\u00164GOS8j]V1\u0011qQAO\u0003##B!!#\u00024R!\u00111RAS)\u0019\ti)!&\u0002 B!ahPAH!\rq\u0014\u0011\u0013\u0003\u0007\u0003'k!\u0019A'\u0003\u0007Y#5\u0007C\u0005\u0002\u00186\t\t\u0011q\u0001\u0002\u001a\u0006QQM^5eK:\u001cW\rJ\u001b\u0011\tYK\u00161\u0014\t\u0004}\u0005uE!\u0002=\u000e\u0005\u0004i\u0005\"CAQ\u001b\u0005\u0005\t9AAR\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0005-f\u000by\tC\u0004\u000225\u0001\r!a*\u0011\u0015\u0011\nI+!\u000fR\u0003[\u000by)C\u0002\u0002,\u0016\u0012\u0011BR;oGRLwN\\\u001a\u0011\u000b\u0011\ny+a'\n\u0007\u0005EVE\u0001\u0004PaRLwN\u001c\u0005\b\u0003Wj\u0001\u0019AA[!\u0011qt(a'\u0016\r\u0005e\u0016QZAb)\u0011\tY,a7\u0015\t\u0005u\u0016Q\u001b\u000b\u0007\u0003\u007f\u000b)-a4\u0011\tyz\u0014\u0011\u0019\t\u0004}\u0005\rGABAJ\u001d\t\u0007Q\nC\u0005\u0002H:\t\t\u0011q\u0001\u0002J\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\tYK\u00161\u001a\t\u0004}\u00055G!\u0002=\u000f\u0005\u0004i\u0005\"CAi\u001d\u0005\u0005\t9AAj\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0005-f\u000b\t\rC\u0004\u000229\u0001\r!a6\u0011\u0015\u0011\nI+!\u000fR\u00033\f\t\rE\u0003%\u0003_\u000bY\rC\u0004\u0002l9\u0001\r!!8\u0011\u000b)\n\u0019(a8\u0011\u000f\u0011\nI(!\u000f\u0002L\u0006I\u0011N\u001c8fe*{\u0017N\\\u000b\u0007\u0003K\fI0a<\u0015\t\u0005\u001d(q\u0001\u000b\u0005\u0003S\u0014\u0019\u0001\u0006\u0004\u0002l\u0006E\u0018Q \t\u0005}}\ni\u000fE\u0002?\u0003_$Q\u0001_\bC\u00025C\u0011\"a=\u0010\u0003\u0003\u0005\u001d!!>\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\b\u0005\u0003W3\u0006]\bc\u0001 \u0002z\u00121\u00111`\bC\u00025\u0013\u0011!\u0016\u0005\n\u0003\u007f|\u0011\u0011!a\u0002\u0005\u0003\t1\"\u001a<jI\u0016t7-\u001a\u00132aA!a+WAw\u0011\u001d\t\td\u0004a\u0001\u0005\u000b\u0001\"\u0002JAU\u0003s\t\u0016q_Aw\u0011\u001d\tYg\u0004a\u0001\u0005\u0013\u0001BAP \u0002xV1!Q\u0002B\u0011\u0005/!BAa\u0004\u0003.Q!!\u0011\u0003B\u0015)\u0019\u0011\u0019B!\u0007\u0003$A!ah\u0010B\u000b!\rq$q\u0003\u0003\u0006qB\u0011\r!\u0014\u0005\n\u00057\u0001\u0012\u0011!a\u0002\u0005;\t1\"\u001a<jI\u0016t7-\u001a\u00132cA!a+\u0017B\u0010!\rq$\u0011\u0005\u0003\u0007\u0003w\u0004\"\u0019A'\t\u0013\t\u0015\u0002#!AA\u0004\t\u001d\u0012aC3wS\u0012,gnY3%cI\u0002BAV-\u0003\u0016!9\u0011\u0011\u0007\tA\u0002\t-\u0002C\u0003\u0013\u0002*\u0006e\u0012Ka\b\u0003\u0016!9!q\u0006\tA\u0002\tE\u0012\u0001B5uKJ\u0004RAKA:\u0005g\u0001r\u0001\nB\u001b\u0003s\u0011y\"C\u0002\u00038\u0015\u0012\u0001\u0002\u0015:pIV\u001cGOM\u0001\u0011GJ,\u0017\r^3Vg&tw-\u00138eKb,BA!\u0010\u0003FQ!!q\bB')\u0011\u0011\tEa\u0012\u0011\tyz$1\t\t\u0004}\t\u0015C!\u0002=\u0012\u0005\u0004i\u0005\"\u0003B%#\u0005\u0005\t9\u0001B&\u0003-)g/\u001b3f]\u000e,G%M\u001a\u0011\tYK&1\t\u0005\b\u0005_\t\u0002\u0019\u0001B(!\u0015Q\u00131\u000fB)!\u001d!#QGA\u001d\u0005\u0007\n\u0011#\u001b8oKJTu.\u001b8LK\u0016\u0004H*\u001a4u)\ri$q\u000b\u0005\b\u0005_\u0011\u0002\u0019\u0001B-!\u0015Q\u00131\u000fB.!\u0019!#QGA\u001d#\u0006\u0019\u0012mZ4sK\u001e\fG/Z+tS:<\u0017J\u001c3fqV!!\u0011\rB5)\u0019\u0011\u0019G!\u001d\u0003xQ!!Q\rB6!\u0011qtHa\u001a\u0011\u0007y\u0012I\u0007B\u0003y'\t\u0007Q\nC\u0005\u0003nM\t\t\u0011q\u0001\u0003p\u0005YQM^5eK:\u001cW\rJ\u00195!\u00111\u0016La\u001a\t\u000f\t=2\u00031\u0001\u0003tA)!&a\u001d\u0003vA9AE!\u000e\u0002:\t\u001d\u0004b\u0002B='\u0001\u0007!1P\u0001\u000be\u0016$WoY3Gk:\u001c\u0007#\u0003\u0013\u00026\t\u001d$q\rB4\u0003\u001d\u0011X-\u001b8eKb$\u0012!P\u0001\u0006i>|\u0005o]\u000b\u0005\u0005\u000b\u0013i\t\u0006\u0003\u0003\b\nUE\u0003\u0002BE\u0005\u001f\u0003Ra\u0012\u0001\u0003\f~\u00032A\u0010BG\t\u0015AXC1\u0001N\u0011%\u0011\t*FA\u0001\u0002\b\u0011\u0019*A\u0006fm&$WM\\2fIE*\u0004\u0003\u0002,Z\u0005\u0017CqAa&\u0016\u0001\u0004\u0011I*A\u0005qCJ$\u0018\u000e^5p]B!ah\u0010BF\u0001"
)
public abstract class VertexPartitionBaseOps implements Serializable, Logging {
   private final VertexPartitionBase self;
   private final ClassTag evidence$1;
   private final VertexPartitionBaseOpsConstructor evidence$2;
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

   public abstract VertexPartitionBase withIndex(final OpenHashSet index);

   public abstract VertexPartitionBase withValues(final Object values, final ClassTag evidence$3);

   public abstract VertexPartitionBase withMask(final BitSet mask);

   public VertexPartitionBase map(final Function2 f, final ClassTag evidence$4) {
      Object newValues = evidence$4.newArray(this.self.capacity());

      for(int i = this.self.mask().nextSetBit(0); i >= 0; i = this.self.mask().nextSetBit(i + 1)) {
         .MODULE$.array_update(newValues, i, f.apply(BoxesRunTime.boxToLong(this.self.index().getValue$mcJ$sp(i)), .MODULE$.array_apply(this.self.values(), i)));
      }

      return this.withValues(newValues, evidence$4);
   }

   public VertexPartitionBase filter(final Function2 pred) {
      BitSet newMask = new BitSet(this.self.capacity());

      for(int i = this.self.mask().nextSetBit(0); i >= 0; i = this.self.mask().nextSetBit(i + 1)) {
         if (BoxesRunTime.unboxToBoolean(pred.apply(BoxesRunTime.boxToLong(this.self.index().getValue$mcJ$sp(i)), .MODULE$.array_apply(this.self.values(), i)))) {
            newMask.set(i);
         }
      }

      return this.withMask(newMask);
   }

   public VertexPartitionBase minus(final VertexPartitionBase other) {
      label14: {
         OpenHashSet var10000 = this.self.index();
         OpenHashSet var2 = other.index();
         if (var10000 == null) {
            if (var2 != null) {
               break label14;
            }
         } else if (!var10000.equals(var2)) {
            break label14;
         }

         return this.toOps(this.self, this.evidence$1).withMask(this.self.mask().andNot(other.mask()));
      }

      this.logWarning((Function0)(() -> "Minus operations on two VertexPartitions with different indexes is slow."));
      return this.minus(this.createUsingIndex(other.iterator(), this.evidence$1));
   }

   public VertexPartitionBase minus(final Iterator other) {
      return this.minus(this.createUsingIndex(other, this.evidence$1));
   }

   public VertexPartitionBase diff(final VertexPartitionBase other) {
      label30: {
         OpenHashSet var10000 = this.self.index();
         OpenHashSet var2 = other.index();
         if (var10000 == null) {
            if (var2 != null) {
               break label30;
            }
         } else if (!var10000.equals(var2)) {
            break label30;
         }

         BitSet newMask = this.self.mask().$amp(other.mask());

         for(int i = newMask.nextSetBit(0); i >= 0; i = newMask.nextSetBit(i + 1)) {
            if (BoxesRunTime.equals(.MODULE$.array_apply(this.self.values(), i), .MODULE$.array_apply(other.values(), i))) {
               newMask.unset(i);
            }
         }

         return this.toOps(this.withValues(other.values(), this.evidence$1), this.evidence$1).withMask(newMask);
      }

      this.logWarning((Function0)(() -> "Diffing two VertexPartitions with different indexes is slow."));
      return this.diff(this.createUsingIndex(other.iterator(), this.evidence$1));
   }

   public VertexPartitionBase leftJoin(final VertexPartitionBase other, final Function3 f, final ClassTag evidence$5, final ClassTag evidence$6) {
      label33: {
         OpenHashSet var10000 = this.self.index();
         OpenHashSet var5 = other.index();
         if (var10000 == null) {
            if (var5 != null) {
               break label33;
            }
         } else if (!var10000.equals(var5)) {
            break label33;
         }

         Object newValues = evidence$6.newArray(this.self.capacity());

         for(int i = this.self.mask().nextSetBit(0); i >= 0; i = this.self.mask().nextSetBit(i + 1)) {
            Option otherV = (Option)(other.mask().get(i) ? new Some(.MODULE$.array_apply(other.values(), i)) : scala.None..MODULE$);
            .MODULE$.array_update(newValues, i, f.apply(BoxesRunTime.boxToLong(this.self.index().getValue$mcJ$sp(i)), .MODULE$.array_apply(this.self.values(), i), otherV));
         }

         return this.withValues(newValues, evidence$6);
      }

      this.logWarning((Function0)(() -> "Joining two VertexPartitions with different indexes is slow."));
      return this.leftJoin(this.createUsingIndex(other.iterator(), evidence$5), f, evidence$5, evidence$6);
   }

   public VertexPartitionBase leftJoin(final Iterator other, final Function3 f, final ClassTag evidence$7, final ClassTag evidence$8) {
      return this.leftJoin(this.createUsingIndex(other, evidence$7), f, evidence$7, evidence$8);
   }

   public VertexPartitionBase innerJoin(final VertexPartitionBase other, final Function3 f, final ClassTag evidence$9, final ClassTag evidence$10) {
      label25: {
         OpenHashSet var10000 = this.self.index();
         OpenHashSet var5 = other.index();
         if (var10000 == null) {
            if (var5 != null) {
               break label25;
            }
         } else if (!var10000.equals(var5)) {
            break label25;
         }

         BitSet newMask = this.self.mask().$amp(other.mask());
         Object newValues = evidence$10.newArray(this.self.capacity());

         for(int i = newMask.nextSetBit(0); i >= 0; i = newMask.nextSetBit(i + 1)) {
            .MODULE$.array_update(newValues, i, f.apply(BoxesRunTime.boxToLong(this.self.index().getValue$mcJ$sp(i)), .MODULE$.array_apply(this.self.values(), i), .MODULE$.array_apply(other.values(), i)));
         }

         return this.toOps(this.withValues(newValues, evidence$10), evidence$10).withMask(newMask);
      }

      this.logWarning((Function0)(() -> "Joining two VertexPartitions with different indexes is slow."));
      return this.innerJoin(this.createUsingIndex(other.iterator(), evidence$9), f, evidence$9, evidence$10);
   }

   public VertexPartitionBase innerJoin(final Iterator iter, final Function3 f, final ClassTag evidence$11, final ClassTag evidence$12) {
      return this.innerJoin(this.createUsingIndex(iter, evidence$11), f, evidence$11, evidence$12);
   }

   public VertexPartitionBase createUsingIndex(final Iterator iter, final ClassTag evidence$13) {
      BitSet newMask = new BitSet(this.self.capacity());
      Object newValues = evidence$13.newArray(this.self.capacity());
      iter.foreach((pair) -> {
         $anonfun$createUsingIndex$1(this, newMask, newValues, pair);
         return BoxedUnit.UNIT;
      });
      return this.toOps(this.withValues(newValues, evidence$13), evidence$13).withMask(newMask);
   }

   public VertexPartitionBase innerJoinKeepLeft(final Iterator iter) {
      BitSet newMask = new BitSet(this.self.capacity());
      Object newValues = this.evidence$1.newArray(this.self.capacity());
      System.arraycopy(this.self.values(), 0, newValues, 0, .MODULE$.array_length(newValues));
      iter.foreach((pair) -> {
         $anonfun$innerJoinKeepLeft$1(this, newMask, newValues, pair);
         return BoxedUnit.UNIT;
      });
      return this.toOps(this.withValues(newValues, this.evidence$1), this.evidence$1).withMask(newMask);
   }

   public VertexPartitionBase aggregateUsingIndex(final Iterator iter, final Function2 reduceFunc, final ClassTag evidence$14) {
      BitSet newMask = new BitSet(this.self.capacity());
      Object newValues = evidence$14.newArray(this.self.capacity());
      iter.foreach((product) -> {
         $anonfun$aggregateUsingIndex$1(this, newMask, newValues, reduceFunc, product);
         return BoxedUnit.UNIT;
      });
      return this.toOps(this.withValues(newValues, evidence$14), evidence$14).withMask(newMask);
   }

   public VertexPartitionBase reindex() {
      GraphXPrimitiveKeyOpenHashMap hashMap = new GraphXPrimitiveKeyOpenHashMap(scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), this.evidence$1);
      Function2 arbitraryMerge = (a, b) -> a;
      this.self.iterator().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$reindex$2(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$reindex$3(hashMap, arbitraryMerge, x$1);
         return BoxedUnit.UNIT;
      });
      return this.toOps(this.toOps(this.withIndex(hashMap.keySet$mcJ$sp()), this.evidence$1).withValues(hashMap._values(), this.evidence$1), this.evidence$1).withMask(hashMap.keySet$mcJ$sp().getBitSet());
   }

   private VertexPartitionBaseOps toOps(final VertexPartitionBase partition, final ClassTag evidence$15) {
      return ((VertexPartitionBaseOpsConstructor)scala.Predef..MODULE$.implicitly(this.evidence$2)).toOps(partition, evidence$15);
   }

   // $FF: synthetic method
   public static final void $anonfun$createUsingIndex$1(final VertexPartitionBaseOps $this, final BitSet newMask$1, final Object newValues$1, final Product2 pair) {
      int pos = $this.self.index().getPos$mcJ$sp(pair._1$mcJ$sp());
      if (pos >= 0) {
         newMask$1.set(pos);
         .MODULE$.array_update(newValues$1, pos, pair._2());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$innerJoinKeepLeft$1(final VertexPartitionBaseOps $this, final BitSet newMask$2, final Object newValues$2, final Product2 pair) {
      int pos = $this.self.index().getPos$mcJ$sp(pair._1$mcJ$sp());
      if (pos >= 0) {
         newMask$2.set(pos);
         .MODULE$.array_update(newValues$2, pos, pair._2());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$aggregateUsingIndex$1(final VertexPartitionBaseOps $this, final BitSet newMask$3, final Object newValues$3, final Function2 reduceFunc$1, final Product2 product) {
      long vid = product._1$mcJ$sp();
      Object vdata = product._2();
      int pos = $this.self.index().getPos$mcJ$sp(vid);
      if (pos >= 0) {
         if (newMask$3.get(pos)) {
            .MODULE$.array_update(newValues$3, pos, reduceFunc$1.apply(.MODULE$.array_apply(newValues$3, pos), vdata));
         } else {
            newMask$3.set(pos);
            .MODULE$.array_update(newValues$3, pos, vdata);
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reindex$2(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$reindex$3(final GraphXPrimitiveKeyOpenHashMap hashMap$1, final Function2 arbitraryMerge$1, final Tuple2 x$1) {
      if (x$1 != null) {
         long k = x$1._1$mcJ$sp();
         Object v = x$1._2();
         hashMap$1.setMerge(BoxesRunTime.boxToLong(k), v, arbitraryMerge$1);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$1);
      }
   }

   public VertexPartitionBaseOps(final VertexPartitionBase self, final ClassTag evidence$1, final VertexPartitionBaseOpsConstructor evidence$2) {
      this.self = self;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
