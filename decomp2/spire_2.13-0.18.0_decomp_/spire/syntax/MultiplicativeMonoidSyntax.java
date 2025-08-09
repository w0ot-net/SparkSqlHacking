package spire.syntax;

import algebra.ring.MultiplicativeMonoid;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\u000eNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3N_:|\u0017\u000eZ*z]R\f\u0007P\u0003\u0002\u0006\r\u000511/\u001f8uCbT\u0011aB\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001!\u0002\u0005\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\u0011R\"\u0001\u0003\n\u0005M!!!H'vYRL\u0007\u000f\\5dCRLg/Z*f[&<'o\\;q'ftG/\u0019=\u0002\r\u0011Jg.\u001b;%)\u00051\u0002CA\u0006\u0018\u0013\tABB\u0001\u0003V]&$\u0018aF7vYRL\u0007\u000f\\5dCRLg/Z'p]>LGm\u00149t+\tY\"\u0005\u0006\u0002\u001d{Q\u0011Qd\u000b\t\u0004#y\u0001\u0013BA\u0010\u0005\u0005]iU\u000f\u001c;ja2L7-\u0019;jm\u0016luN\\8jI>\u00038\u000f\u0005\u0002\"E1\u0001A!B\u0012\u0003\u0005\u0004!#!A!\u0012\u0005\u0015B\u0003CA\u0006'\u0013\t9CBA\u0004O_RD\u0017N\\4\u0011\u0005-I\u0013B\u0001\u0016\r\u0005\r\te.\u001f\u0005\u0006Y\t\u0001\u001d!L\u0001\u0003KZ\u00042A\f\u001e!\u001d\tysG\u0004\u00021k9\u0011\u0011\u0007N\u0007\u0002e)\u00111\u0007C\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dI!A\u000e\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0001(O\u0001\ba\u0006\u001c7.Y4f\u0015\t1d!\u0003\u0002<y\t!R*\u001e7uSBd\u0017nY1uSZ,Wj\u001c8pS\u0012T!\u0001O\u001d\t\u000by\u0012\u0001\u0019\u0001\u0011\u0002\u0003\u0005\u0004"
)
public interface MultiplicativeMonoidSyntax extends MultiplicativeSemigroupSyntax {
   // $FF: synthetic method
   static MultiplicativeMonoidOps multiplicativeMonoidOps$(final MultiplicativeMonoidSyntax $this, final Object a, final MultiplicativeMonoid ev) {
      return $this.multiplicativeMonoidOps(a, ev);
   }

   default MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
      return new MultiplicativeMonoidOps(a, ev);
   }

   static void $init$(final MultiplicativeMonoidSyntax $this) {
   }
}
