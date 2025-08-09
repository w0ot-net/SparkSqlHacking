package breeze.signal.support;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513AAC\u0006\u0001%!Aa\u0005\u0001BC\u0002\u0013\u0005q\u0005\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003)\u0011!y\u0003A!b\u0001\n\u00039\u0003\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u0011E\u0002!Q1A\u0005BIB\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\to\u0001\u0011)\u0019!C!q!AA\t\u0001B\u0001B\u0003%\u0011\bC\u0003F\u0001\u0011\u0005aIA\u0006J\u0013J[UM\u001d8fYF\"%B\u0001\u0007\u000e\u0003\u001d\u0019X\u000f\u001d9peRT!AD\b\u0002\rMLwM\\1m\u0015\u0005\u0001\u0012A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005MQ2C\u0001\u0001\u0015!\r)b\u0003G\u0007\u0002\u0017%\u0011qc\u0003\u0002\u000f\r&dG/\u001a:LKJtW\r\\\u0019E!\tI\"\u0004\u0004\u0001\u0005\u000bm\u0001!\u0019\u0001\u000f\u0003\u0003Q\u000b\"!H\u0012\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\u000f9{G\u000f[5oOB\u0011a\u0004J\u0005\u0003K}\u00111!\u00118z\u0003\u001dYWM\u001d8fY\u0006+\u0012\u0001\u000b\t\u0004S1BR\"\u0001\u0016\u000b\u0005-z\u0011A\u00027j]\u0006dw-\u0003\u0002.U\tYA)\u001a8tKZ+7\r^8s\u0003!YWM\u001d8fY\u0006\u0003\u0013aB6fe:,GNQ\u0001\tW\u0016\u0014h.\u001a7CA\u0005QQ.\u001e7uSBd\u0017.\u001a:\u0016\u0003M\u0002\"A\b\u001b\n\u0005Uz\"A\u0002#pk\ndW-A\u0006nk2$\u0018\u000e\u001d7jKJ\u0004\u0013A\u00033fg&<g\u000eV3yiV\t\u0011\b\u0005\u0002;\u0003:\u00111h\u0010\t\u0003y}i\u0011!\u0010\u0006\u0003}E\ta\u0001\u0010:p_Rt\u0014B\u0001! \u0003\u0019\u0001&/\u001a3fM&\u0011!i\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001{\u0012a\u00033fg&<g\u000eV3yi\u0002\na\u0001P5oSRtD#B$I\u0013*[\u0005cA\u000b\u00011!)a%\u0003a\u0001Q!)q&\u0003a\u0001Q!)\u0011'\u0003a\u0001g!)q'\u0003a\u0001s\u0001"
)
public class IIRKernel1D extends FilterKernel1D {
   private final DenseVector kernelA;
   private final DenseVector kernelB;
   private final double multiplier;
   private final String designText;

   public DenseVector kernelA() {
      return this.kernelA;
   }

   public DenseVector kernelB() {
      return this.kernelB;
   }

   public double multiplier() {
      return this.multiplier;
   }

   public String designText() {
      return this.designText;
   }

   public IIRKernel1D(final DenseVector kernelA, final DenseVector kernelB, final double multiplier, final String designText) {
      this.kernelA = kernelA;
      this.kernelB = kernelB;
      this.multiplier = multiplier;
      this.designText = designText;
   }
}
