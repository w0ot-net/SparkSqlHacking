package org.apache.spark.streaming.util;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.SeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.parallel.ExecutionContextTaskSupport;
import scala.collection.parallel.immutable.ParVector;
import scala.concurrent.ExecutionContext;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

public final class FileBasedWriteAheadLog$ {
   public static final FileBasedWriteAheadLog$ MODULE$ = new FileBasedWriteAheadLog$();
   private static final Regex logFileRegex;

   static {
      logFileRegex = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("log-(\\d+)-(\\d+)"));
   }

   public Regex logFileRegex() {
      return logFileRegex;
   }

   public String timeToLogFile(final long startTime, final long stopTime) {
      return "log-" + startTime + "-" + stopTime;
   }

   public Option getCallerName() {
      Seq ignoreList = new scala.collection.immutable..colon.colon("WriteAheadLog", new scala.collection.immutable..colon.colon("Logging", new scala.collection.immutable..colon.colon("java.lang", new scala.collection.immutable..colon.colon("scala.", scala.collection.immutable.Nil..MODULE$))));
      return scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])Thread.currentThread().getStackTrace()), (x$8) -> x$8.getClassName(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (c) -> BoxesRunTime.boxToBoolean($anonfun$getCallerName$2(ignoreList, c))).flatMap((x$9) -> scala.collection.ArrayOps..MODULE$.lastOption$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$9.split("\\.")))).flatMap((x$10) -> scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$10.split("\\$\\$"))));
   }

   public Seq logFilesTologInfo(final Seq files) {
      return (Seq)((SeqOps)files.flatMap((file) -> {
         Option var3 = MODULE$.logFileRegex().findFirstIn(file.getName());
         if (var3 instanceof Some var4) {
            String var5 = (String)var4.value();
            if (var5 != null) {
               Option var6 = MODULE$.logFileRegex().unapplySeq(var5);
               if (!var6.isEmpty() && var6.get() != null && ((List)var6.get()).lengthCompare(2) == 0) {
                  String startTimeStr = (String)((LinearSeqOps)var6.get()).apply(0);
                  String stopTimeStr = (String)((LinearSeqOps)var6.get()).apply(1);
                  long startTime = .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(startTimeStr));
                  long stopTime = .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(stopTimeStr));
                  return new Some(new FileBasedWriteAheadLog.LogInfo(startTime, stopTime, file.toString()));
               }
            }
         }

         if (scala.None..MODULE$.equals(var3) ? true : var3 instanceof Some) {
            return scala.None..MODULE$;
         } else {
            throw new MatchError(var3);
         }
      })).sortBy((x$11) -> BoxesRunTime.boxToLong($anonfun$logFilesTologInfo$2(x$11)), scala.math.Ordering.Long..MODULE$);
   }

   public Iterator seqToParIterator(final ExecutionContext executionContext, final Seq source, final Function1 handler) {
      ExecutionContextTaskSupport taskSupport = new ExecutionContextTaskSupport(executionContext);
      int groupSize = scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(taskSupport.parallelismLevel()), 8);
      return source.grouped(groupSize).flatMap((group) -> {
         ParVector parallelCollection = new ParVector(group.toVector());
         parallelCollection.tasksupport_$eq(taskSupport);
         return (ParVector)parallelCollection.map(handler);
      }).flatten(scala.Predef..MODULE$.$conforms());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCallerName$3(final String c$1, final CharSequence x$1) {
      return c$1.contains(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCallerName$2(final Seq ignoreList$1, final String c) {
      return !ignoreList$1.exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getCallerName$3(c, x$1)));
   }

   // $FF: synthetic method
   public static final long $anonfun$logFilesTologInfo$2(final FileBasedWriteAheadLog.LogInfo x$11) {
      return x$11.startTime();
   }

   private FileBasedWriteAheadLog$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
