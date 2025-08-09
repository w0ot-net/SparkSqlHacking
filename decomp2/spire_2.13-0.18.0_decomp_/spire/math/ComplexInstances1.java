package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\tD_6\u0004H.\u001a=J]N$\u0018M\\2fgFR!!\u0002\u0004\u0002\t5\fG\u000f\u001b\u0006\u0002\u000f\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\u000b!A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\u0004\"!\u0005\n\u000e\u0003\u0011I!a\u0005\u0003\u0003#\r{W\u000e\u001d7fq&s7\u000f^1oG\u0016\u001c\b'\u0001\u0004%S:LG\u000f\n\u000b\u0002-A\u00111bF\u0005\u000311\u0011A!\u00168ji\u0006q1i\\7qY\u0016DxJ\u001c$jK2$WCA\u000e\")\u0011a\"\u0006P!\u0011\u0007Eir$\u0003\u0002\u001f\t\tq1i\\7qY\u0016DxJ\u001c$jK2$\u0007C\u0001\u0011\"\u0019\u0001!QA\t\u0002C\u0002\r\u0012\u0011!Q\t\u0003I\u001d\u0002\"aC\u0013\n\u0005\u0019b!a\u0002(pi\"Lgn\u001a\t\u0003\u0017!J!!\u000b\u0007\u0003\u0007\u0005s\u0017\u0010C\u0004,\u0005\u0005\u0005\t9\u0001\u0017\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007E\u0002.s}q!A\f\u001c\u000f\u0005=\"dB\u0001\u00194\u001b\u0005\t$B\u0001\u001a\t\u0003\u0019a$o\\8u}%\tq!\u0003\u00026\r\u00059\u0011\r\\4fEJ\f\u0017BA\u001c9\u0003\u001d\u0001\u0018mY6bO\u0016T!!\u000e\u0004\n\u0005iZ$!\u0002$jK2$'BA\u001c9\u0011\u001di$!!AA\u0004y\n!\"\u001a<jI\u0016t7-\u001a\u00138!\rishH\u0005\u0003\u0001n\u0012Qa\u0014:eKJDqA\u0011\u0002\u0002\u0002\u0003\u000f1)\u0001\u0006fm&$WM\\2fIa\u00022!\f# \u0013\t)5H\u0001\u0004TS\u001etW\r\u001a"
)
public interface ComplexInstances1 extends ComplexInstances0 {
   // $FF: synthetic method
   static ComplexOnField ComplexOnField$(final ComplexInstances1 $this, final Field evidence$6, final Order evidence$7, final Signed evidence$8) {
      return $this.ComplexOnField(evidence$6, evidence$7, evidence$8);
   }

   default ComplexOnField ComplexOnField(final Field evidence$6, final Order evidence$7, final Signed evidence$8) {
      return new ComplexOnFieldImpl(evidence$6, evidence$7, evidence$8);
   }

   static void $init$(final ComplexInstances1 $this) {
   }
}
