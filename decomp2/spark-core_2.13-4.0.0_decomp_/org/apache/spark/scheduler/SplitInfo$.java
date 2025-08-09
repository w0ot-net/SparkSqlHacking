package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.mapred.InputSplit;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;

public final class SplitInfo$ {
   public static final SplitInfo$ MODULE$ = new SplitInfo$();

   public Seq toSplitInfo(final Class inputFormatClazz, final String path, final InputSplit mapredSplit) {
      ArrayBuffer retval = new ArrayBuffer();
      long length = mapredSplit.getLength();
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])mapredSplit.getLocations()), (host) -> (ArrayBuffer)retval.$plus$eq(new SplitInfo(inputFormatClazz, host, path, length, mapredSplit)));
      return retval.toSeq();
   }

   public Seq toSplitInfo(final Class inputFormatClazz, final String path, final org.apache.hadoop.mapreduce.InputSplit mapreduceSplit) {
      ArrayBuffer retval = new ArrayBuffer();
      long length = mapreduceSplit.getLength();
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])mapreduceSplit.getLocations()), (host) -> (ArrayBuffer)retval.$plus$eq(new SplitInfo(inputFormatClazz, host, path, length, mapreduceSplit)));
      return retval.toSeq();
   }

   private SplitInfo$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
