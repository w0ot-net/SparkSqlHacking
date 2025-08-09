package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;
import spire.algebra.FieldAssociativeAlgebra;

@ScalaSignature(
   bytes = "\u0006\u0005)4\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006!\u00021\u0019!\u0015\u0005\u0006'\u00021\u0019\u0001\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u0006;\u0002!\tE\u0018\u0005\u0006I\u0002!\t!\u001a\u0002\u000f\u0007>l\u0007\u000f\\3y\u001f:4\u0015.\u001a7e\u0015\tI!\"\u0001\u0003nCRD'\"A\u0006\u0002\u000bM\u0004\u0018N]3\u0016\u00055Q2#\u0002\u0001\u000f)]:\u0005CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rE\u0002\u0016-ai\u0011\u0001C\u0005\u0003/!\u0011abQ8na2,\u0007p\u00148D%&tw\r\u0005\u0002\u001a51\u0001A!C\u000e\u0001A\u0003\u0005\tQ1\u0001\u001e\u0005\u0005\t5\u0001A\t\u0003=\u0005\u0002\"aD\u0010\n\u0005\u0001\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001f\tJ!a\t\t\u0003\u0007\u0005s\u0017\u0010\u000b\u0003\u001bK!\u0012\u0004CA\b'\u0013\t9\u0003CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012*U1ZcBA\b+\u0013\tY\u0003#A\u0003GY>\fG/\r\u0003%[E\nbB\u0001\u00182\u001b\u0005y#B\u0001\u0019\u001d\u0003\u0019a$o\\8u}%\t\u0011#M\u0003$gQ2TG\u0004\u0002\u0010i%\u0011Q\u0007E\u0001\u0007\t>,(\r\\32\t\u0011j\u0013'\u0005\t\u0004q\u0005#eBA\u001d?\u001d\tQDH\u0004\u0002/w%\t1\"\u0003\u0002>\u0015\u00059\u0011\r\\4fEJ\f\u0017BA A\u0003\u001d\u0001\u0018mY6bO\u0016T!!\u0010\u0006\n\u0005\t\u001b%!\u0002$jK2$'BA A!\r)R\tG\u0005\u0003\r\"\u0011qaQ8na2,\u0007\u0010\u0005\u0003I\u0013\u0012CR\"\u0001!\n\u0005)\u0003%a\u0006$jK2$\u0017i]:pG&\fG/\u001b<f\u00032<WM\u0019:b\u0003\u0019!\u0013N\\5uIQ\tQ\n\u0005\u0002\u0010\u001d&\u0011q\n\u0005\u0002\u0005+:LG/\u0001\u0004tG\u0006d\u0017M]\u000b\u0002%B\u0019\u0001(\u0011\r\u0002\rMLwM\\3e+\u0005)\u0006c\u0001\u001dW1%\u0011qk\u0011\u0002\u0007'&<g.\u001a3\u0002\u000b=\u0014H-\u001a:\u0016\u0003i\u00032\u0001O.\u0019\u0013\ta6IA\u0003Pe\u0012,'/\u0001\u0006ge>lGi\\;cY\u0016$\"\u0001R0\t\u000b\u0001,\u0001\u0019A1\u0002\u00039\u0004\"a\u00042\n\u0005\r\u0004\"A\u0002#pk\ndW-A\u0002eSZ$2\u0001\u00124i\u0011\u00159g\u00011\u0001E\u0003\u0005\t\u0007\"B5\u0007\u0001\u0004!\u0015!\u00012"
)
public interface ComplexOnField extends ComplexOnCRing, Field, FieldAssociativeAlgebra {
   Field scalar();

   Signed signed();

   Order order();

   // $FF: synthetic method
   static Complex fromDouble$(final ComplexOnField $this, final double n) {
      return $this.fromDouble(n);
   }

   default Complex fromDouble(final double n) {
      return Complex$.MODULE$.apply(this.scalar().fromDouble(n), (CommutativeRing)this.scalar());
   }

   // $FF: synthetic method
   static Complex div$(final ComplexOnField $this, final Complex a, final Complex b) {
      return $this.div(a, b);
   }

   default Complex div(final Complex a, final Complex b) {
      return a.$div(b, this.scalar(), this.order(), this.signed());
   }

   // $FF: synthetic method
   static Field scalar$mcD$sp$(final ComplexOnField $this) {
      return $this.scalar$mcD$sp();
   }

   default Field scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Field scalar$mcF$sp$(final ComplexOnField $this) {
      return $this.scalar$mcF$sp();
   }

   default Field scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Signed signed$mcD$sp$(final ComplexOnField $this) {
      return $this.signed$mcD$sp();
   }

   default Signed signed$mcD$sp() {
      return this.signed();
   }

   // $FF: synthetic method
   static Signed signed$mcF$sp$(final ComplexOnField $this) {
      return $this.signed$mcF$sp();
   }

   default Signed signed$mcF$sp() {
      return this.signed();
   }

   // $FF: synthetic method
   static Order order$mcD$sp$(final ComplexOnField $this) {
      return $this.order$mcD$sp();
   }

   default Order order$mcD$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcF$sp$(final ComplexOnField $this) {
      return $this.order$mcF$sp();
   }

   default Order order$mcF$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Complex fromDouble$mcD$sp$(final ComplexOnField $this, final double n) {
      return $this.fromDouble$mcD$sp(n);
   }

   default Complex fromDouble$mcD$sp(final double n) {
      return this.fromDouble(n);
   }

   // $FF: synthetic method
   static Complex fromDouble$mcF$sp$(final ComplexOnField $this, final double n) {
      return $this.fromDouble$mcF$sp(n);
   }

   default Complex fromDouble$mcF$sp(final double n) {
      return this.fromDouble(n);
   }

   // $FF: synthetic method
   static Complex div$mcD$sp$(final ComplexOnField $this, final Complex a, final Complex b) {
      return $this.div$mcD$sp(a, b);
   }

   default Complex div$mcD$sp(final Complex a, final Complex b) {
      return this.div(a, b);
   }

   // $FF: synthetic method
   static Complex div$mcF$sp$(final ComplexOnField $this, final Complex a, final Complex b) {
      return $this.div$mcF$sp(a, b);
   }

   default Complex div$mcF$sp(final Complex a, final Complex b) {
      return this.div(a, b);
   }

   static void $init$(final ComplexOnField $this) {
   }
}
