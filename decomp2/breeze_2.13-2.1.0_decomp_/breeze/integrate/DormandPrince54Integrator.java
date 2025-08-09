package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3A!\u0004\b\u0001'!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001a\u0011!\u0001\u0003A!A!\u0002\u0013\t\u0003\u0002C\u0014\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u000b!\u0002A\u0011A\u0015\u0006\t=\u0002\u0001\u0001\r\u0005\u0006\u007f\u0001!)\u0002Q\u0004\b\u0003:\t\t\u0011#\u0001C\r\u001dia\"!A\t\u0002\rCQ\u0001K\u0005\u0005\u0002\u001dCq\u0001S\u0005\u0012\u0002\u0013\u0005\u0011\nC\u0004U\u0013E\u0005I\u0011A%\u00033\u0011{'/\\1oIB\u0013\u0018N\\2fkQJe\u000e^3he\u0006$xN\u001d\u0006\u0003\u001fA\t\u0011\"\u001b8uK\u001e\u0014\u0018\r^3\u000b\u0003E\taA\u0019:fKj,7\u0001A\n\u0003\u0001Q\u0001\"!\u0006\f\u000e\u00039I!a\u0006\b\u00039\u0005\u0003\u0018m\u00195f\u0003\u0012\f\u0007\u000f^5wKN#X\r]%oi\u0016<'/\u0019;pe\u00069Q.\u001b8Ti\u0016\u0004\bC\u0001\u000e\u001e\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"A\u0002#pk\ndW-A\u0004nCb\u001cF/\u001a9\u0002\rI,G\u000eV8m!\r\u0011S%G\u0007\u0002G)\u0011A\u0005E\u0001\u0007Y&t\u0017\r\\4\n\u0005\u0019\u001a#a\u0003#f]N,g+Z2u_J\fa!\u00192t)>d\u0017A\u0002\u001fj]&$h\bF\u0003+W1jc\u0006\u0005\u0002\u0016\u0001!)\u0001$\u0002a\u00013!)q$\u0002a\u00013!9\u0001%\u0002I\u0001\u0002\u0004\t\u0003bB\u0014\u0006!\u0003\u0005\r!\t\u0002\u0002)B\u0011\u0011GP\u0007\u0002e)\u00111\u0007N\u0001\t]>t7\u000f^5gM*\u0011QGN\u0001\u0004_\u0012,'BA\u001c9\u0003\u0015i\u0017\r\u001e54\u0015\tI$(A\u0004d_6lwN\\:\u000b\u0005mb\u0014AB1qC\u000eDWMC\u0001>\u0003\ry'oZ\u0005\u0003\u001bI\naa\u0019:fCR,W#\u0001\u0019\u00023\u0011{'/\\1oIB\u0013\u0018N\\2fkQJe\u000e^3he\u0006$xN\u001d\t\u0003+%\u0019\"!\u0003#\u0011\u0005i)\u0015B\u0001$\u001c\u0005\u0019\te.\u001f*fMR\t!)A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0002\u0015*\u0012\u0011eS\u0016\u0002\u0019B\u0011QJU\u0007\u0002\u001d*\u0011q\nU\u0001\nk:\u001c\u0007.Z2lK\u0012T!!U\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002T\u001d\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0001"
)
public class DormandPrince54Integrator extends ApacheAdaptiveStepIntegrator {
   private final double minStep;
   private final double maxStep;

   public static DenseVector $lessinit$greater$default$4() {
      return DormandPrince54Integrator$.MODULE$.$lessinit$greater$default$4();
   }

   public static DenseVector $lessinit$greater$default$3() {
      return DormandPrince54Integrator$.MODULE$.$lessinit$greater$default$3();
   }

   public final org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator create() {
      return new org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator(this.minStep, this.maxStep, ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol(), ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol());
   }

   public DormandPrince54Integrator(final double minStep, final double maxStep, final DenseVector relTol, final DenseVector absTol) {
      super(relTol, absTol);
      this.minStep = minStep;
      this.maxStep = maxStep;
   }
}
