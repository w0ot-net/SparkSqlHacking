package spire.syntax;

import algebra.ring.Ring;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005e4A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001d\u0001\u0006!!A\u0005BECq!\u0016\u0001\u0002\u0002\u0013\u0005ckB\u0004]\u001d\u0005\u0005\t\u0012A/\u0007\u000f5q\u0011\u0011!E\u0001=\")\u0001\u0005\u0003C\u0001E\")1\r\u0003C\u0003I\"9\u0011\u000fCA\u0001\n\u000b\u0011\bb\u0002;\t\u0003\u0003%)!\u001e\u0002 \u0019&$XM]1m\u0019>tw-\u00113eSRLg/Z*f[&<'o\\;q\u001fB\u001c(BA\b\u0011\u0003\u0019\u0019\u0018P\u001c;bq*\t\u0011#A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001!\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osZ\u000bG.A\u0002mQN,\u0012\u0001\b\t\u0003+uI!A\b\f\u0003\t1{gnZ\u0001\u0005Y\"\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0003E\u0011\u0002\"a\t\u0001\u000e\u00039AQAG\u0002A\u0002q\tQ\u0001\n9mkN,\"aJ\u0016\u0015\u0005!rEcA\u00155\rB\u0011!f\u000b\u0007\u0001\t\u0015aCA1\u0001.\u0005\u0005\t\u0015C\u0001\u00182!\t)r&\u0003\u00021-\t9aj\u001c;iS:<\u0007CA\u000b3\u0013\t\u0019dCA\u0002B]fDQ!\u000e\u0003A\u0004Y\n!!\u001a<\u0011\u0007]\u001a\u0015F\u0004\u00029\u0001:\u0011\u0011H\u0010\b\u0003uuj\u0011a\u000f\u0006\u0003yI\ta\u0001\u0010:p_Rt\u0014\"A\t\n\u0005}\u0002\u0012aB1mO\u0016\u0014'/Y\u0005\u0003\u0003\n\u000bq\u0001]1dW\u0006<WM\u0003\u0002@!%\u0011A)\u0012\u0002\u0005%&twM\u0003\u0002B\u0005\")q\t\u0002a\u0002\u0011\u0006\t1\rE\u0002J\u0019&j\u0011A\u0013\u0006\u0003\u0017B\tA!\\1uQ&\u0011QJ\u0013\u0002\u000e\u0007>tg/\u001a:uC\ndW\rV8\t\u000b=#\u0001\u0019A\u0015\u0002\u0007ID7/\u0001\u0005iCND7i\u001c3f)\u0005\u0011\u0006CA\u000bT\u0013\t!fCA\u0002J]R\fa!Z9vC2\u001cHCA,[!\t)\u0002,\u0003\u0002Z-\t9!i\\8mK\u0006t\u0007bB.\u0007\u0003\u0003\u0005\r!M\u0001\u0004q\u0012\n\u0014a\b'ji\u0016\u0014\u0018\r\u001c'p]\u001e\fE\rZ5uSZ,7+Z7jOJ|W\u000f](qgB\u00111\u0005C\n\u0003\u0011}\u0003\"!\u00061\n\u0005\u00054\"AB!osJ+g\rF\u0001^\u0003=!\u0003\u000f\\;tI\u0015DH/\u001a8tS>tWCA3j)\t1w\u000e\u0006\u0002h]R\u0019\u0001N\u001b7\u0011\u0005)JG!\u0002\u0017\u000b\u0005\u0004i\u0003\"B\u001b\u000b\u0001\bY\u0007cA\u001cDQ\")qI\u0003a\u0002[B\u0019\u0011\n\u00145\t\u000b=S\u0001\u0019\u00015\t\u000bAT\u0001\u0019\u0001\u0012\u0002\u000b\u0011\"\b.[:\u0002%!\f7\u000f[\"pI\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0003#NDQ\u0001]\u0006A\u0002\t\n\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005YDHCA,x\u0011\u001dYF\"!AA\u0002EBQ\u0001\u001d\u0007A\u0002\t\u0002"
)
public final class LiteralLongAdditiveSemigroupOps {
   private final long lhs;

   public static boolean equals$extension(final long $this, final Object x$1) {
      return LiteralLongAdditiveSemigroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final long $this) {
      return LiteralLongAdditiveSemigroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $plus$extension(final long $this, final Object rhs, final Ring ev, final ConvertableTo c) {
      return LiteralLongAdditiveSemigroupOps$.MODULE$.$plus$extension($this, rhs, ev, c);
   }

   public long lhs() {
      return this.lhs;
   }

   public Object $plus(final Object rhs, final Ring ev, final ConvertableTo c) {
      return LiteralLongAdditiveSemigroupOps$.MODULE$.$plus$extension(this.lhs(), rhs, ev, c);
   }

   public int hashCode() {
      return LiteralLongAdditiveSemigroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralLongAdditiveSemigroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralLongAdditiveSemigroupOps(final long lhs) {
      this.lhs = lhs;
   }
}
