package breeze.linalg;

import scala.Tuple1;
import scala.Tuple2;

public final class Options$ {
   public static final Options$ MODULE$ = new Options$();

   public Options.Dimensions1 intToDimensions1(final int n) {
      return new Options.Dimensions1(n);
   }

   public Options.Dimensions1 t1ToDimensions1(final Tuple1 n) {
      return new Options.Dimensions1(n._1$mcI$sp());
   }

   public Options.Dimensions2 t2ToDimensions2(final Tuple2 n) {
      return new Options.Dimensions2(n._1$mcI$sp(), n._2$mcI$sp());
   }

   public Options.OptPadMode tToOptModeValue(final Object n) {
      return new Options.Value(n);
   }

   private Options$() {
   }
}
