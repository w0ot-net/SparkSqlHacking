package org.apache.spark.ml;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext.;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.sql.Dataset;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055c\u0001\u0003\u0005\n!\u0003\r\t!C\t\t\u000by\u0001A\u0011\u0001\u0011\t\u000b\u0011\u0002A\u0011B\u0013\t\u000b1\u0002A\u0011A\u0017\t\u000bQ\u0002A\u0011A\u001b\t\u000bu\u0003A\u0011\u00010\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u0011q\u0007\u0001\u0005\u0002\u0005e\"\u0001C'M\u000bZ,g\u000e^:\u000b\u0005)Y\u0011AA7m\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000f\u000e\u0003iQ!aG\u0006\u0002\u0011%tG/\u001a:oC2L!!\b\u000e\u0003\u000f1{wmZ5oO\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\"!\t\u0019\"%\u0003\u0002$)\t!QK\\5u\u0003-a\u0017n\u001d;f]\u0016\u0014()^:\u0016\u0003\u0019\u0002\"a\n\u0016\u000e\u0003!R!!K\u0006\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018BA\u0016)\u0005=a\u0015N^3MSN$XM\\3s\u0005V\u001c\u0018\u0001\u00037pO\u00163XM\u001c;\u0015\u0005\u0005r\u0003\"B\u0018\u0004\u0001\u0004\u0001\u0014!B3wK:$\bCA\u00193\u001b\u0005I\u0011BA\u001a\n\u0005\u001diE*\u0012<f]R\fAb^5uQ\u001aKG/\u0012<f]R,\"A\u000e\u001e\u0015\u0007]BU\n\u0006\u00029\u0007B\u0011\u0011H\u000f\u0007\u0001\t\u0015YDA1\u0001=\u0005\u0005i\u0015CA\u001fA!\t\u0019b(\u0003\u0002@)\t9aj\u001c;iS:<\u0007cA\u0019Bq%\u0011!)\u0003\u0002\u0006\u001b>$W\r\u001c\u0005\u0007\t\u0012!\t\u0019A#\u0002\t\u0019,hn\u0019\t\u0004'\u0019C\u0014BA$\u0015\u0005!a$-\u001f8b[\u0016t\u0004\"B%\u0005\u0001\u0004Q\u0015!C3ti&l\u0017\r^8s!\r\t4\nO\u0005\u0003\u0019&\u0011\u0011\"R:uS6\fGo\u001c:\t\u000b9#\u0001\u0019A(\u0002\u000f\u0011\fG/Y:fiB\u0012\u0001k\u0016\t\u0004#R3V\"\u0001*\u000b\u0005M[\u0011aA:rY&\u0011QK\u0015\u0002\b\t\u0006$\u0018m]3u!\tIt\u000bB\u0005Y\u001b\u0006\u0005\t\u0011!B\u00013\n\u0019q\f\n\u001b\u0012\u0005uR\u0006CA\n\\\u0013\taFCA\u0002B]f\f!c^5uQR\u0013\u0018M\\:g_JlWI^3oiR\u0019q\f^=\u0015\u0005\u0001\u0014\bCA1p\u001d\t\u0011WN\u0004\u0002dY:\u0011Am\u001b\b\u0003K*t!AZ5\u000e\u0003\u001dT!\u0001[\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012B\u0001\b\u0010\u0013\taQ\"\u0003\u0002T\u0017%\u0011aNU\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0018OA\u0005ECR\fgI]1nK*\u0011aN\u0015\u0005\u0007\t\u0016!\t\u0019A:\u0011\u0007M1\u0005\rC\u0003v\u000b\u0001\u0007a/A\u0006ue\u0006t7OZ8s[\u0016\u0014\bCA\u0019x\u0013\tA\u0018BA\u0006Ue\u0006t7OZ8s[\u0016\u0014\b\"\u0002>\u0006\u0001\u0004Y\u0018!B5oaV$\bG\u0001?\u007f!\r\tF+ \t\u0003sy$\u0011b`=\u0002\u0002\u0003\u0005)\u0011A-\u0003\u0007}#S'A\u000bxSRDGj\\1e\u0013:\u001cH/\u00198dK\u00163XM\u001c;\u0016\t\u0005\u0015\u00111\u0002\u000b\u0007\u0003\u000f\t\u0019\"a\t\u0015\t\u0005%\u0011q\u0002\t\u0004s\u0005-AABA\u0007\r\t\u0007\u0011LA\u0001U\u0011\u001d!e\u0001\"a\u0001\u0003#\u0001Ba\u0005$\u0002\n!9\u0011Q\u0003\u0004A\u0002\u0005]\u0011A\u0002:fC\u0012,'\u000f\u0005\u0004\u0002\u001a\u0005}\u0011\u0011B\u0007\u0003\u00037Q1!!\b\n\u0003\u0011)H/\u001b7\n\t\u0005\u0005\u00121\u0004\u0002\t\u001b2\u0013V-\u00193fe\"9\u0011Q\u0005\u0004A\u0002\u0005\u001d\u0012\u0001\u00029bi\"\u0004B!!\u000b\u000229!\u00111FA\u0017!\t1G#C\u0002\u00020Q\ta\u0001\u0015:fI\u00164\u0017\u0002BA\u001a\u0003k\u0011aa\u0015;sS:<'bAA\u0018)\u0005)r/\u001b;i'\u00064X-\u00138ti\u0006t7-Z#wK:$HCBA\u001e\u0003\u0003\nY\u0005F\u0002\"\u0003{Aq\u0001R\u0004\u0005\u0002\u0004\ty\u0004E\u0002\u0014\r\u0006Bq!a\u0011\b\u0001\u0004\t)%\u0001\u0004xe&$XM\u001d\t\u0005\u00033\t9%\u0003\u0003\u0002J\u0005m!\u0001C'M/JLG/\u001a:\t\u000f\u0005\u0015r\u00011\u0001\u0002(\u0001"
)
public interface MLEvents extends Logging {
   private LiveListenerBus listenerBus() {
      return .MODULE$.getOrCreate().listenerBus();
   }

   // $FF: synthetic method
   static void logEvent$(final MLEvents $this, final MLEvent event) {
      $this.logEvent(event);
   }

   default void logEvent(final MLEvent event) {
      this.logDebug(() -> "Sending an MLEvent: " + event);
   }

   // $FF: synthetic method
   static Model withFitEvent$(final MLEvents $this, final Estimator estimator, final Dataset dataset, final Function0 func) {
      return $this.withFitEvent(estimator, dataset, func);
   }

   default Model withFitEvent(final Estimator estimator, final Dataset dataset, final Function0 func) {
      FitStart startEvent = new FitStart();
      startEvent.estimator_$eq(estimator);
      startEvent.dataset_$eq(dataset);
      this.logEvent(startEvent);
      this.listenerBus().post(startEvent);
      Model model = (Model)func.apply();
      FitEnd endEvent = new FitEnd();
      endEvent.estimator_$eq(estimator);
      endEvent.model_$eq(model);
      this.logEvent(endEvent);
      this.listenerBus().post(endEvent);
      return model;
   }

   // $FF: synthetic method
   static Dataset withTransformEvent$(final MLEvents $this, final Transformer transformer, final Dataset input, final Function0 func) {
      return $this.withTransformEvent(transformer, input, func);
   }

   default Dataset withTransformEvent(final Transformer transformer, final Dataset input, final Function0 func) {
      TransformStart startEvent = new TransformStart();
      startEvent.transformer_$eq(transformer);
      startEvent.input_$eq(input);
      this.logEvent(startEvent);
      this.listenerBus().post(startEvent);
      Dataset output = (Dataset)func.apply();
      TransformEnd endEvent = new TransformEnd();
      endEvent.transformer_$eq(transformer);
      endEvent.output_$eq(output);
      this.logEvent(endEvent);
      this.listenerBus().post(endEvent);
      return output;
   }

   // $FF: synthetic method
   static Object withLoadInstanceEvent$(final MLEvents $this, final MLReader reader, final String path, final Function0 func) {
      return $this.withLoadInstanceEvent(reader, path, func);
   }

   default Object withLoadInstanceEvent(final MLReader reader, final String path, final Function0 func) {
      LoadInstanceStart startEvent = new LoadInstanceStart(path);
      startEvent.reader_$eq(reader);
      this.logEvent(startEvent);
      this.listenerBus().post(startEvent);
      Object instance = func.apply();
      LoadInstanceEnd endEvent = new LoadInstanceEnd();
      endEvent.reader_$eq(reader);
      endEvent.instance_$eq(instance);
      this.logEvent(endEvent);
      this.listenerBus().post(endEvent);
      return instance;
   }

   // $FF: synthetic method
   static void withSaveInstanceEvent$(final MLEvents $this, final MLWriter writer, final String path, final Function0 func) {
      $this.withSaveInstanceEvent(writer, path, func);
   }

   default void withSaveInstanceEvent(final MLWriter writer, final String path, final Function0 func) {
      SaveInstanceStart startEvent = new SaveInstanceStart(path);
      startEvent.writer_$eq(writer);
      this.logEvent(startEvent);
      this.listenerBus().post(startEvent);
      func.apply$mcV$sp();
      SaveInstanceEnd endEvent = new SaveInstanceEnd(path);
      endEvent.writer_$eq(writer);
      this.logEvent(endEvent);
      this.listenerBus().post(endEvent);
   }

   static void $init$(final MLEvents $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
