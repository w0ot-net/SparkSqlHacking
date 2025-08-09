package breeze.integrate.quasimontecarlo;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2qa\u0002\u0005\u0011\u0002\u0007\u0005q\u0002C\u0003\u0017\u0001\u0011\u0005q\u0003C\u0004\u001c\u0001\t\u0007i\u0011\u0001\u000f\t\u000b\u0001\u0002a\u0011A\u0011\t\u000b!\u0002A\u0011A\u0011\t\u000b%\u0002A\u0011\u0001\u0016\t\u000b5\u0002a\u0011\u0001\u0018\u00031E+\u0018m]5N_:$XmQ1sY><UM\\3sCR|'O\u0003\u0002\n\u0015\u0005y\u0011/^1tS6|g\u000e^3dCJdwN\u0003\u0002\f\u0019\u0005I\u0011N\u001c;fOJ\fG/\u001a\u0006\u0002\u001b\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0002\u0001!A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\r\u0011\u0005EI\u0012B\u0001\u000e\u0013\u0005\u0011)f.\u001b;\u0002\u0013\u0011LW.\u001a8tS>tW#A\u000f\u0011\u0005Eq\u0012BA\u0010\u0013\u0005\rIe\u000e^\u0001\u000eO\u0016$h*\u001a=u+:\u001c\u0018MZ3\u0016\u0003\t\u00022!E\u0012&\u0013\t!#CA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0012M%\u0011qE\u0005\u0002\u0007\t>,(\r\\3\u0002\u000f\u001d,GOT3yi\u0006Yq-\u001a;OKb$\u0018J\u001c;p)\tA2\u0006C\u0003-\u000b\u0001\u0007!%\u0001\u0002u_\u0006aa.^7HK:,'/\u0019;fIV\tq\u0006\u0005\u0002\u0012a%\u0011\u0011G\u0005\u0002\u0005\u0019>tw\r"
)
public interface QuasiMonteCarloGenerator {
   int dimension();

   double[] getNextUnsafe();

   // $FF: synthetic method
   static double[] getNext$(final QuasiMonteCarloGenerator $this) {
      return $this.getNext();
   }

   default double[] getNext() {
      return (double[])this.getNextUnsafe().clone();
   }

   // $FF: synthetic method
   static void getNextInto$(final QuasiMonteCarloGenerator $this, final double[] to) {
      $this.getNextInto(to);
   }

   default void getNextInto(final double[] to) {
      System.arraycopy(this.getNextUnsafe(), 0, to, 0, this.dimension());
   }

   long numGenerated();

   static void $init$(final QuasiMonteCarloGenerator $this) {
   }
}
