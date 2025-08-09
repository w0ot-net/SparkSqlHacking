package org.apache.spark.mllib.tree.configuration;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y;QAC\u0006\t\u0002a1QAG\u0006\t\u0002mAQAI\u0001\u0005\u0002\r*AAG\u0001\u0001I!9\u0011'\u0001b\u0001\n\u0003\u0011\u0004B\u0002\u001b\u0002A\u0003%A\u0005C\u00047\u0003\t\u0007I\u0011\u0001\u001a\t\ra\n\u0001\u0015!\u0003%\u0011\u0019Q\u0014\u0001\"\u0001\u0010w!9!*AA\u0001\n\u0013Y\u0015\u0001B!mO>T!\u0001D\u0007\u0002\u001b\r|gNZ5hkJ\fG/[8o\u0015\tqq\"\u0001\u0003ue\u0016,'B\u0001\t\u0012\u0003\u0015iG\u000e\\5c\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7\u0001\u0001\t\u00033\u0005i\u0011a\u0003\u0002\u0005\u00032<wn\u0005\u0002\u00029A\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\tYQI\\;nKJ\fG/[8o\u0003\u0019a\u0014N\\5u}Q\t\u0001\u0004\u0005\u0002&M5\t\u0011!\u0003\u0002(A\t)a+\u00197vK\"\u001a1!K\u0018\u0011\u0005)jS\"A\u0016\u000b\u00051\n\u0012AC1o]>$\u0018\r^5p]&\u0011af\u000b\u0002\u0006'&t7-Z\u0011\u0002a\u0005)\u0011G\f\u0019/a\u0005q1\t\\1tg&4\u0017nY1uS>tW#\u0001\u0013)\u0007\u0011Is&A\bDY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8!Q\r)\u0011fL\u0001\u000b%\u0016<'/Z:tS>t\u0007f\u0001\u0004*_\u0005Y!+Z4sKN\u001c\u0018n\u001c8!Q\r9\u0011fL\u0001\u000bMJ|Wn\u0015;sS:<GC\u0001\u001f>!\t)3\u0001C\u0003?\u0011\u0001\u0007q(\u0001\u0003oC6,\u0007C\u0001!H\u001d\t\tU\t\u0005\u0002C=5\t1I\u0003\u0002E/\u00051AH]8pizJ!A\u0012\u0010\u0002\rA\u0013X\rZ3g\u0013\tA\u0015J\u0001\u0004TiJLgn\u001a\u0006\u0003\rz\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012\u0001\u0014\t\u0003\u001bJk\u0011A\u0014\u0006\u0003\u001fB\u000bA\u0001\\1oO*\t\u0011+\u0001\u0003kCZ\f\u0017BA*O\u0005\u0019y%M[3di\"\u001a\u0011!K\u0018)\u0007\u0001Is\u0006"
)
public final class Algo {
   public static Enumeration.Value Regression() {
      return Algo$.MODULE$.Regression();
   }

   public static Enumeration.Value Classification() {
      return Algo$.MODULE$.Classification();
   }

   public static Enumeration.ValueSet ValueSet() {
      return Algo$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return Algo$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return Algo$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return Algo$.MODULE$.apply(x);
   }

   public static int maxId() {
      return Algo$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return Algo$.MODULE$.values();
   }

   public static String toString() {
      return Algo$.MODULE$.toString();
   }
}
