package org.apache.spark.metrics.source;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A;Qa\u0004\t\t\u0002m1Q!\b\t\t\u0002yAQ\u0001K\u0001\u0005\u0002%BqAK\u0001C\u0002\u0013\u00053\u0006\u0003\u00048\u0003\u0001\u0006I\u0001\f\u0005\bq\u0005\u0011\r\u0011\"\u0011:\u0011\u0019\u0019\u0015\u0001)A\u0005u!9A)\u0001b\u0001\n\u0003)\u0005BB%\u0002A\u0003%a\tC\u0004K\u0003\t\u0007I\u0011A#\t\r-\u000b\u0001\u0015!\u0003G\u0011\u001da\u0015A1A\u0005\u0002\u0015Ca!T\u0001!\u0002\u00131\u0005b\u0002(\u0002\u0005\u0004%\t!\u0012\u0005\u0007\u001f\u0006\u0001\u000b\u0011\u0002$\u0002\u001d\r{G-Z4f]6+GO]5dg*\u0011\u0011CE\u0001\u0007g>,(oY3\u000b\u0005M!\u0012aB7fiJL7m\u001d\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sO\u000e\u0001\u0001C\u0001\u000f\u0002\u001b\u0005\u0001\"AD\"pI\u0016<WM\\'fiJL7m]\n\u0004\u0003})\u0003C\u0001\u0011$\u001b\u0005\t#\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n#AB!osJ+g\r\u0005\u0002\u001dM%\u0011q\u0005\u0005\u0002\u0007'>,(oY3\u0002\rqJg.\u001b;?)\u0005Y\u0012AC:pkJ\u001cWMT1nKV\tA\u0006\u0005\u0002.i9\u0011aF\r\t\u0003_\u0005j\u0011\u0001\r\u0006\u0003ci\ta\u0001\u0010:p_Rt\u0014BA\u001a\"\u0003\u0019\u0001&/\u001a3fM&\u0011QG\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005M\n\u0013aC:pkJ\u001cWMT1nK\u0002\na\"\\3ue&\u001c'+Z4jgR\u0014\u00180F\u0001;!\tY\u0014)D\u0001=\u0015\t\u0019RH\u0003\u0002?\u007f\u0005A1m\u001c3bQ\u0006dWMC\u0001A\u0003\r\u0019w.\\\u0005\u0003\u0005r\u0012a\"T3ue&\u001c'+Z4jgR\u0014\u00180A\bnKR\u0014\u0018n\u0019*fO&\u001cHO]=!\u0003]iU\t\u0016*J\u0007~\u001bv*\u0016*D\u000b~\u001bu\nR#`'&SV)F\u0001G!\tYt)\u0003\u0002Iy\tI\u0001*[:u_\u001e\u0014\u0018-\\\u0001\u0019\u001b\u0016#&+S\"`'>+&kQ#`\u0007>#UiX*J5\u0016\u0003\u0013aF'F)JK5iX\"P\u001bBKE*\u0011+J\u001f:{F+S'F\u0003aiU\t\u0016*J\u0007~\u001bu*\u0014)J\u0019\u0006#\u0016j\u0014(`)&kU\tI\u0001%\u001b\u0016#&+S\"`\u000f\u0016sUIU!U\u000b\u0012{6\tT!T'~\u0013\u0015\fV#D\u001f\u0012+ulU%[\u000b\u0006)S*\u0012+S\u0013\u000e{v)\u0012(F%\u0006#V\tR0D\u0019\u0006\u001b6k\u0018\"Z)\u0016\u001bu\nR#`'&SV\tI\u0001&\u001b\u0016#&+S\"`\u000f\u0016sUIU!U\u000b\u0012{V*\u0012+I\u001f\u0012{&)\u0017+F\u0007>#UiX*J5\u0016\u000ba%T#U%&\u001bulR#O\u000bJ\u000bE+\u0012#`\u001b\u0016#\u0006j\u0014#`\u0005f#ViQ(E\u000b~\u001b\u0016JW#!\u0001"
)
public final class CodegenMetrics {
   public static Histogram METRIC_GENERATED_METHOD_BYTECODE_SIZE() {
      return CodegenMetrics$.MODULE$.METRIC_GENERATED_METHOD_BYTECODE_SIZE();
   }

   public static Histogram METRIC_GENERATED_CLASS_BYTECODE_SIZE() {
      return CodegenMetrics$.MODULE$.METRIC_GENERATED_CLASS_BYTECODE_SIZE();
   }

   public static Histogram METRIC_COMPILATION_TIME() {
      return CodegenMetrics$.MODULE$.METRIC_COMPILATION_TIME();
   }

   public static Histogram METRIC_SOURCE_CODE_SIZE() {
      return CodegenMetrics$.MODULE$.METRIC_SOURCE_CODE_SIZE();
   }

   public static MetricRegistry metricRegistry() {
      return CodegenMetrics$.MODULE$.metricRegistry();
   }

   public static String sourceName() {
      return CodegenMetrics$.MODULE$.sourceName();
   }
}
