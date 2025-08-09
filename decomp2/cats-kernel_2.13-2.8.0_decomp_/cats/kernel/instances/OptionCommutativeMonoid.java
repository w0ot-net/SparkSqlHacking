package cats.kernel.instances;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2Aa\u0001\u0003\u0005\u0017!Aa\u0005\u0001B\u0001B\u0003-q\u0005C\u0003+\u0001\u0011\u00051FA\fPaRLwN\\\"p[6,H/\u0019;jm\u0016luN\\8jI*\u0011QAB\u0001\nS:\u001cH/\u00198dKNT!a\u0002\u0005\u0002\r-,'O\\3m\u0015\u0005I\u0011\u0001B2biN\u001c\u0001!\u0006\u0002\r'M\u0019\u0001!D\u0010\u0011\u00079y\u0011#D\u0001\u0005\u0013\t\u0001BA\u0001\u0007PaRLwN\\'p]>LG\r\u0005\u0002\u0013'1\u0001A!\u0002\u000b\u0001\u0005\u0004)\"!A!\u0012\u0005Ya\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"a\u0002(pi\"Lgn\u001a\t\u0003/uI!A\b\r\u0003\u0007\u0005s\u0017\u0010E\u0002!C\rj\u0011AB\u0005\u0003E\u0019\u0011\u0011cQ8n[V$\u0018\r^5wK6{gn\\5e!\r9B%E\u0005\u0003Ka\u0011aa\u00149uS>t\u0017!A!\u0011\u0007\u0001B\u0013#\u0003\u0002*\r\t!2i\\7nkR\fG/\u001b<f'\u0016l\u0017n\u001a:pkB\fa\u0001P5oSRtD#\u0001\u0017\u0015\u00055r\u0003c\u0001\b\u0001#!)aE\u0001a\u0002O\u0001"
)
public class OptionCommutativeMonoid extends OptionMonoid implements CommutativeMonoid {
   public CommutativeMonoid reverse() {
      return CommutativeMonoid.reverse$(this);
   }

   public CommutativeMonoid reverse$mcD$sp() {
      return CommutativeMonoid.reverse$mcD$sp$(this);
   }

   public CommutativeMonoid reverse$mcF$sp() {
      return CommutativeMonoid.reverse$mcF$sp$(this);
   }

   public CommutativeMonoid reverse$mcI$sp() {
      return CommutativeMonoid.reverse$mcI$sp$(this);
   }

   public CommutativeMonoid reverse$mcJ$sp() {
      return CommutativeMonoid.reverse$mcJ$sp$(this);
   }

   public CommutativeSemigroup intercalate(final Object middle) {
      return CommutativeSemigroup.intercalate$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
   }

   public OptionCommutativeMonoid(final CommutativeSemigroup A) {
      super(A);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
   }
}
