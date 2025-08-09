package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Map.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub!\u0002\u000e\u001c\u0001m)\u0003\u0002C \u0001\u0005\u000b\u0007I\u0011\u0001!\t\u0011\u0011\u0003!\u0011!Q\u0001\n\u0005C\u0001\"\u0012\u0001\u0003\u0006\u0004%\tA\u0012\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005\u000f\")1\n\u0001C\u0001\u0019\")\u0011\u000b\u0001C\u0001%\")a\r\u0001C\u0005O\u001e)An\u0007E\u0005[\u001a)!d\u0007E\u0005]\")1*\u0003C\u0001m\u001a!q/\u0003\u0001y\u0011!I8B!b\u0001\n\u0003Q\b\u0002\u0003@\f\u0005\u0003\u0005\u000b\u0011B>\t\u0011}\\!Q1A\u0005\u0002\u0019C\u0011\"!\u0001\f\u0005\u0003\u0005\u000b\u0011B$\t\r-[A\u0011BA\u0002\u0011\u001d\tia\u0003C\u0001\u0003\u001fAq!!\u0006\f\t\u0003\t9bB\u0004\u0002\u001a%A\t!a\u0007\u0007\r]L\u0001\u0012AA\u000f\u0011\u0019YE\u0003\"\u0001\u0002 !I\u0011\u0011\u0005\u000bC\u0002\u0013\u0005\u00111\u0005\u0005\t\u0003K!\u0002\u0015!\u0003\u0002\u0006!I\u0011q\u0005\u000b\u0002\u0002\u0013%\u0011\u0011\u0006\u0005\n\u0003OI\u0011\u0011!C\u0005\u0003S\u0011q\u0002T8dC2\u0004&/\u001a4jqN\u0003\u0018M\u001c\u0006\u00039u\t1A\u001a9n\u0015\tqr$A\u0003nY2L'M\u0003\u0002!C\u0005)1\u000f]1sW*\u0011!eI\u0001\u0007CB\f7\r[3\u000b\u0003\u0011\n1a\u001c:h'\u0011\u0001a\u0005\f\u001a\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\r\u0005s\u0017PU3g!\ti\u0003'D\u0001/\u0015\tys$\u0001\u0005j]R,'O\\1m\u0013\t\tdFA\u0004M_\u001e<\u0017N\\4\u0011\u0005MbdB\u0001\u001b;\u001d\t)\u0014(D\u00017\u0015\t9\u0004(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005I\u0013BA\u001e)\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mB\u0013\u0001C7j]\u000e{WO\u001c;\u0016\u0003\u0005\u0003\"a\n\"\n\u0005\rC#\u0001\u0002'p]\u001e\f\u0011\"\\5o\u0007>,h\u000e\u001e\u0011\u0002!5\f\u0007\u0010U1ui\u0016\u0014h\u000eT3oORDW#A$\u0011\u0005\u001dB\u0015BA%)\u0005\rIe\u000e^\u0001\u0012[\u0006D\b+\u0019;uKJtG*\u001a8hi\"\u0004\u0013A\u0002\u001fj]&$h\bF\u0002N\u001fB\u0003\"A\u0014\u0001\u000e\u0003mAQaP\u0003A\u0002\u0005CQ!R\u0003A\u0002\u001d\u000b1A];o)\t\u0019F\fE\u00024)ZK!!\u0016 \u0003\u0011%#XM]1u_J\u0004BaJ,Z\u0003&\u0011\u0001\f\u000b\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007\u001dRv)\u0003\u0002\\Q\t)\u0011I\u001d:bs\")QL\u0002a\u0001=\u0006I\u0001o\\:uM&DXm\u001d\t\u0004Oi{\u0006C\u00011d\u001d\tq\u0015-\u0003\u0002c7\u0005Q\u0001K]3gSb\u001c\u0006/\u00198\n\u0005\u0011,'a\u0002)pgR4\u0017\u000e\u001f\u0006\u0003En\tqbZ3o\rJ,\u0017\u000fU1ui\u0016\u0014hn\u001d\u000b\u0006Q\u0006]\u00121\b\t\u0004gQK\u0007\u0003B\u0014XU\u0006\u0003\"a[\u0006\u000f\u00059C\u0011a\u0004'pG\u0006d\u0007K]3gSb\u001c\u0006/\u00198\u0011\u00059K1cA\u0005'_B\u0011\u0001/^\u0007\u0002c*\u0011!o]\u0001\u0003S>T\u0011\u0001^\u0001\u0005U\u00064\u0018-\u0003\u0002>cR\tQN\u0001\bSKZ,'o]3e!J,g-\u001b=\u0014\u0007-1#'A\u0003ji\u0016l7/F\u0001|!\r\u0019DpR\u0005\u0003{z\u0012A\u0001T5ti\u00061\u0011\u000e^3ng\u0002\na\u0001\\3oORD\u0017a\u00027f]\u001e$\b\u000e\t\u000b\u0007\u0003\u000b\tI!a\u0003\u0011\u0007\u0005\u001d1\"D\u0001\n\u0011\u0015I\b\u00031\u0001|\u0011\u0015y\b\u00031\u0001H\u0003-!3m\u001c7p]\u0012\u0002H.^:\u0015\t\u0005\u0015\u0011\u0011\u0003\u0005\u0007\u0003'\t\u0002\u0019A$\u0002\t%$X-\\\u0001\u000bi>\u001cV-];f]\u000e,W#A-\u0002\u001dI+g/\u001a:tK\u0012\u0004&/\u001a4jqB\u0019\u0011q\u0001\u000b\u0014\u0007Q1s\u000e\u0006\u0002\u0002\u001c\u0005)Q-\u001c9usV\u0011\u0011QA\u0001\u0007K6\u0004H/\u001f\u0011\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005-\u0002\u0003BA\u0017\u0003gi!!a\f\u000b\u0007\u0005E2/\u0001\u0003mC:<\u0017\u0002BA\u001b\u0003_\u0011aa\u00142kK\u000e$\bBBA\u001d\u000f\u0001\u0007!.\u0001\u0004qe\u00164\u0017\u000e\u001f\u0005\u0006;\u001e\u0001\rA\u0018"
)
public class LocalPrefixSpan implements Logging, Serializable {
   private final long minCount;
   private final int maxPatternLength;
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

   public long minCount() {
      return this.minCount;
   }

   public int maxPatternLength() {
      return this.maxPatternLength;
   }

   public Iterator run(final PrefixSpan.Postfix[] postfixes) {
      return this.genFreqPatterns(LocalPrefixSpan.ReversedPrefix$.MODULE$.empty(), postfixes).map((x0$1) -> {
         if (x0$1 != null) {
            ReversedPrefix prefix = (ReversedPrefix)x0$1._1();
            long count = x0$1._2$mcJ$sp();
            return new Tuple2(prefix.toSequence(), BoxesRunTime.boxToLong(count));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   private Iterator genFreqPatterns(final ReversedPrefix prefix, final PrefixSpan.Postfix[] postfixes) {
      if (this.maxPatternLength() != prefix.length() && (long)postfixes.length >= this.minCount()) {
         scala.collection.mutable.Map counts = ((scala.collection.mutable.Map).MODULE$.empty()).withDefaultValue(BoxesRunTime.boxToLong(0L));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(postfixes), (postfix) -> {
            $anonfun$genFreqPatterns$1(counts, postfix);
            return BoxedUnit.UNIT;
         });
         Seq freqItems = (Seq)((SeqOps)counts.toSeq().filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$genFreqPatterns$3(this, x0$2)))).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Long..MODULE$));
         return freqItems.iterator().flatMap((x0$3) -> {
            if (x0$3 != null) {
               int item = x0$3._1$mcI$sp();
               long count = x0$3._2$mcJ$sp();
               ReversedPrefix newPrefix = prefix.$colon$plus(item);
               return scala.package..MODULE$.Iterator().single(new Tuple2(newPrefix, BoxesRunTime.boxToLong(count))).$plus$plus(() -> {
                  PrefixSpan.Postfix[] projected = (PrefixSpan.Postfix[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(postfixes), (x$1) -> x$1.project(item), scala.reflect.ClassTag..MODULE$.apply(PrefixSpan.Postfix.class))), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$genFreqPatterns$7(x$2)));
                  return this.genFreqPatterns(newPrefix, projected);
               });
            } else {
               throw new MatchError(x0$3);
            }
         });
      } else {
         return scala.package..MODULE$.Iterator().empty();
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$genFreqPatterns$2(final scala.collection.mutable.Map counts$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int x = x0$1._1$mcI$sp();
         counts$1.update(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(counts$1.apply(BoxesRunTime.boxToInteger(x))) + 1L));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$genFreqPatterns$1(final scala.collection.mutable.Map counts$1, final PrefixSpan.Postfix postfix) {
      postfix.genPrefixItems().foreach((x0$1) -> {
         $anonfun$genFreqPatterns$2(counts$1, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$genFreqPatterns$3(final LocalPrefixSpan $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         long count = x0$2._2$mcJ$sp();
         return count >= $this.minCount();
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$genFreqPatterns$7(final PrefixSpan.Postfix x$2) {
      return x$2.nonEmpty();
   }

   public LocalPrefixSpan(final long minCount, final int maxPatternLength) {
      this.minCount = minCount;
      this.maxPatternLength = maxPatternLength;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class ReversedPrefix implements Serializable {
      private final List items;
      private final int length;

      public List items() {
         return this.items;
      }

      public int length() {
         return this.length;
      }

      public ReversedPrefix $colon$plus(final int item) {
         scala.Predef..MODULE$.require(item != 0);
         if (item < 0) {
            int var2 = -item;
            return new ReversedPrefix(this.items().$colon$colon(BoxesRunTime.boxToInteger(var2)), this.length() + 1);
         } else {
            return new ReversedPrefix(this.items().$colon$colon(BoxesRunTime.boxToInteger(0)).$colon$colon(BoxesRunTime.boxToInteger(item)), this.length() + 1);
         }
      }

      public int[] toSequence() {
         return (int[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.intArrayOps((int[])this.items().$colon$colon(BoxesRunTime.boxToInteger(0)).toArray(scala.reflect.ClassTag..MODULE$.Int())));
      }

      public ReversedPrefix(final List items, final int length) {
         this.items = items;
         this.length = length;
      }
   }

   public static class ReversedPrefix$ implements Serializable {
      public static final ReversedPrefix$ MODULE$ = new ReversedPrefix$();
      private static final ReversedPrefix empty;

      static {
         empty = new ReversedPrefix(scala.package..MODULE$.List().empty(), 0);
      }

      public ReversedPrefix empty() {
         return empty;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ReversedPrefix$.class);
      }
   }
}
