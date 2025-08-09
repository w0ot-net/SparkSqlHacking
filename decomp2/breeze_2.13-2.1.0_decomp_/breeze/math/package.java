package breeze.math;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U;Q!\u0005\n\t\u0002]1Q!\u0007\n\t\u0002iAQ!I\u0001\u0005\u0002\tBqaI\u0001C\u0002\u0013\u0005A\u0005\u0003\u0004)\u0003\u0001\u0006I!\n\u0004\u0005S\u0005\t!\u0006\u0003\u0005,\u000b\t\u0005\t\u0015!\u0003-\u0011\u0015\tS\u0001\"\u00010\u0011\u0015\u0019T\u0001\"\u00015\u0011\u00159T\u0001\"\u00019\u0011\u0015QT\u0001\"\u0001<\u0011\u0015iT\u0001\"\u0001?\u0011\u0015\u0001U\u0001\"\u0001B\u0011\u0015\u0019U\u0001\"\u0001E\u0011\u001d1\u0015!!A\u0005\u0004\u001dCQ!S\u0001\u0005\u0004)CQaT\u0001\u0005\u0004A\u000bq\u0001]1dW\u0006<WM\u0003\u0002\u0014)\u0005!Q.\u0019;i\u0015\u0005)\u0012A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005a\tQ\"\u0001\n\u0003\u000fA\f7m[1hKN\u0011\u0011a\u0007\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u00059\u0012!A5\u0016\u0003\u0015\u0002\"\u0001\u0007\u0014\n\u0005\u001d\u0012\"aB\"p[BdW\r_\u0001\u0003S\u0002\u0012\u0011BU5dQ\u001aKW\r\u001c3\u0014\u0005\u0015Y\u0012!\u0002<bYV,\u0007C\u0001\u000f.\u0013\tqSD\u0001\u0004E_V\u0014G.\u001a\u000b\u0003aI\u0002\"!M\u0003\u000e\u0003\u0005AQaK\u0004A\u00021\nQ\u0001\n9mkN$\"!J\u001b\t\u000bYB\u0001\u0019A\u0013\u0002\u0003\r\fa\u0001J7j]V\u001cHCA\u0013:\u0011\u00151\u0014\u00021\u0001&\u0003\u0019!C/[7fgR\u0011Q\u0005\u0010\u0005\u0006m)\u0001\r!J\u0001\u0005I\u0011Lg\u000f\u0006\u0002&\u007f!)ag\u0003a\u0001K\u0005AA\u0005]3sG\u0016tG\u000f\u0006\u0002&\u0005\")a\u0007\u0004a\u0001K\u0005\u0019\u0001o\\<\u0015\u0005\u0015*\u0005\"\u0002\u001c\u000e\u0001\u0004)\u0013!\u0003*jG\"4\u0015.\u001a7e)\t\u0001\u0004\nC\u0003,\u001d\u0001\u0007A&A\u0004sS\u000eD\u0017J\u001c;\u0015\u0005AZ\u0005\"B\u0016\u0010\u0001\u0004a\u0005C\u0001\u000fN\u0013\tqUDA\u0002J]R\f\u0011B]5dQ\u001acw.\u0019;\u0015\u0005A\n\u0006\"B\u0016\u0011\u0001\u0004\u0011\u0006C\u0001\u000fT\u0013\t!VDA\u0003GY>\fG\u000f"
)
public final class package {
   public static RichField richFloat(final float value) {
      return package$.MODULE$.richFloat(value);
   }

   public static RichField richInt(final int value) {
      return package$.MODULE$.richInt(value);
   }

   public static RichField RichField(final double value) {
      return package$.MODULE$.RichField(value);
   }

   public static Complex i() {
      return package$.MODULE$.i();
   }

   public static class RichField {
      private final double value;

      public Complex $plus(final Complex c) {
         return (new Complex(this.value, (double)0.0F)).$plus(c);
      }

      public Complex $minus(final Complex c) {
         return (new Complex(this.value, (double)0.0F)).$minus(c);
      }

      public Complex $times(final Complex c) {
         return (new Complex(this.value, (double)0.0F)).$times(c);
      }

      public Complex $div(final Complex c) {
         return (new Complex(this.value, (double)0.0F)).$div(c);
      }

      public Complex $percent(final Complex c) {
         return (new Complex(this.value, (double)0.0F)).$percent(c);
      }

      public Complex pow(final Complex c) {
         return (new Complex(this.value, (double)0.0F)).pow(c);
      }

      public RichField(final double value) {
         this.value = value;
      }
   }
}
