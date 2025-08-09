package org.apache.spark.streaming.api.java;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.function.Function0;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.StreamingContext$;
import scala.Option.;

public final class JavaStreamingContext$ {
   public static final JavaStreamingContext$ MODULE$ = new JavaStreamingContext$();

   public JavaStreamingContext getOrCreate(final String checkpointPath, final Function0 creatingFunc) {
      StreamingContext ssc = StreamingContext$.MODULE$.getOrCreate(checkpointPath, () -> ((JavaStreamingContext)creatingFunc.call()).ssc(), StreamingContext$.MODULE$.getOrCreate$default$3(), StreamingContext$.MODULE$.getOrCreate$default$4());
      return new JavaStreamingContext(ssc);
   }

   public JavaStreamingContext getOrCreate(final String checkpointPath, final Function0 creatingFunc, final Configuration hadoopConf) {
      StreamingContext ssc = StreamingContext$.MODULE$.getOrCreate(checkpointPath, () -> ((JavaStreamingContext)creatingFunc.call()).ssc(), hadoopConf, StreamingContext$.MODULE$.getOrCreate$default$4());
      return new JavaStreamingContext(ssc);
   }

   public JavaStreamingContext getOrCreate(final String checkpointPath, final Function0 creatingFunc, final Configuration hadoopConf, final boolean createOnError) {
      StreamingContext ssc = StreamingContext$.MODULE$.getOrCreate(checkpointPath, () -> ((JavaStreamingContext)creatingFunc.call()).ssc(), hadoopConf, createOnError);
      return new JavaStreamingContext(ssc);
   }

   public String[] jarOfClass(final Class cls) {
      return (String[]).MODULE$.option2Iterable(org.apache.spark.SparkContext..MODULE$.jarOfClass(cls)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   private JavaStreamingContext$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
