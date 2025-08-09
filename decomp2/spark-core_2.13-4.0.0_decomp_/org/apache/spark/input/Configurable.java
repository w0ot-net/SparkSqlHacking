package org.apache.spark.input;

import org.apache.hadoop.conf.Configuration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0004\u0005\u0006?\u0001!\t!\t\u0005\n7\u0001\u0001\r\u00111A\u0005\n!B\u0011\u0002\f\u0001A\u0002\u0003\u0007I\u0011B\u0017\t\u000bA\u0002A\u0011A\u0019\t\u000bQ\u0002A\u0011A\u001b\u0003\u0019\r{gNZ5hkJ\f'\r\\3\u000b\u0005!I\u0011!B5oaV$(B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0014\u0007\u0001\u0001\u0002\u0004\u0005\u0002\u0012-5\t!C\u0003\u0002\u0014)\u0005!A.\u00198h\u0015\u0005)\u0012\u0001\u00026bm\u0006L!a\u0006\n\u0003\r=\u0013'.Z2u!\tIb$D\u0001\u001b\u0015\tYB$\u0001\u0003d_:4'BA\u000f\f\u0003\u0019A\u0017\rZ8pa&\u0011aAG\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t!\u0005\u0005\u0002$M5\tAEC\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9CE\u0001\u0003V]&$X#A\u0015\u0011\u0005eQ\u0013BA\u0016\u001b\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u0006A1m\u001c8g?\u0012*\u0017\u000f\u0006\u0002#]!9qfAA\u0001\u0002\u0004I\u0013a\u0001=%c\u000591/\u001a;D_:4GC\u0001\u00123\u0011\u0015\u0019D\u00011\u0001*\u0003\u0005\u0019\u0017aB4fi\u000e{gN\u001a\u000b\u0002S\u0001"
)
public interface Configurable extends org.apache.hadoop.conf.Configurable {
   Configuration org$apache$spark$input$Configurable$$conf();

   void org$apache$spark$input$Configurable$$conf_$eq(final Configuration x$1);

   // $FF: synthetic method
   static void setConf$(final Configurable $this, final Configuration c) {
      $this.setConf(c);
   }

   default void setConf(final Configuration c) {
      this.org$apache$spark$input$Configurable$$conf_$eq(c);
   }

   // $FF: synthetic method
   static Configuration getConf$(final Configurable $this) {
      return $this.getConf();
   }

   default Configuration getConf() {
      return this.org$apache$spark$input$Configurable$$conf();
   }

   static void $init$(final Configurable $this) {
   }
}
