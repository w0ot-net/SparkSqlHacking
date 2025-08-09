package org.apache.spark.mllib.random;

import org.apache.commons.math3.distribution.GammaDistribution;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3Aa\u0003\u0007\u0001/!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u00051\u0001\t\u0005\t\u0015!\u0003#\u0011!\u0011\u0004A!b\u0001\n\u00031\u0003\u0002\u0003\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0012\t\u000bY\u0002A\u0011A\u001c\t\u000fy\u0002!\u0019!C\u0005\u007f!1!\n\u0001Q\u0001\n\u0001CQa\u0013\u0001\u0005B1CQA\u0014\u0001\u0005B=CQ!\u0017\u0001\u0005Bi\u0013abR1n[\u0006<UM\\3sCR|'O\u0003\u0002\u000e\u001d\u00051!/\u00198e_6T!a\u0004\t\u0002\u000b5dG.\u001b2\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c\u0001aE\u0002\u00011y\u0001\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0007cA\u0010!E5\tA\"\u0003\u0002\"\u0019\t\u0019\"+\u00198e_6$\u0015\r^1HK:,'/\u0019;peB\u0011\u0011dI\u0005\u0003Ii\u0011a\u0001R8vE2,\u0017!B:iCB,W#\u0001\u0012)\u0007\u0005Ac\u0006\u0005\u0002*Y5\t!F\u0003\u0002,!\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00055R#!B*j]\u000e,\u0017%A\u0018\u0002\u000bEr3G\f\u0019\u0002\rMD\u0017\r]3!Q\r\u0011\u0001FL\u0001\u0006g\u000e\fG.\u001a\u0015\u0004\u0007!r\u0013AB:dC2,\u0007\u0005K\u0002\u0005Q9\na\u0001P5oSRtDc\u0001\u001d:wA\u0011q\u0004\u0001\u0005\u0006K\u0015\u0001\rA\t\u0015\u0004s!r\u0003\"\u0002\u001a\u0006\u0001\u0004\u0011\u0003fA\u001e)]!\u001aQ\u0001\u000b\u0018\u0002\u0007Itw-F\u0001A!\t\t\u0005*D\u0001C\u0015\t\u0019E)\u0001\u0007eSN$(/\u001b2vi&|gN\u0003\u0002F\r\u0006)Q.\u0019;ig)\u0011qIE\u0001\bG>lWn\u001c8t\u0013\tI%IA\tHC6l\u0017\rR5tiJL'-\u001e;j_:\fAA\u001d8hA\u0005Ia.\u001a=u-\u0006dW/\u001a\u000b\u0002E!\u001a\u0001\u0002\u000b\u0018\u0002\u000fM,GoU3fIR\u0011\u0001k\u0015\t\u00033EK!A\u0015\u000e\u0003\tUs\u0017\u000e\u001e\u0005\u0006)&\u0001\r!V\u0001\u0005g\u0016,G\r\u0005\u0002\u001a-&\u0011qK\u0007\u0002\u0005\u0019>tw\rK\u0002\nQ9\nAaY8qsR\t\u0001\bK\u0002\u000bQ9B3\u0001\u0001\u0015/\u0001"
)
public class GammaGenerator implements RandomDataGenerator {
   private final double shape;
   private final double scale;
   private final GammaDistribution rng;

   public double shape() {
      return this.shape;
   }

   public double scale() {
      return this.scale;
   }

   private GammaDistribution rng() {
      return this.rng;
   }

   public double nextValue() {
      return this.rng().sample();
   }

   public void setSeed(final long seed) {
      this.rng().reseedRandomGenerator(seed);
   }

   public GammaGenerator copy() {
      return new GammaGenerator(this.shape(), this.scale());
   }

   public GammaGenerator(final double shape, final double scale) {
      this.shape = shape;
      this.scale = scale;
      this.rng = new GammaDistribution(shape, scale);
   }
}
