package breeze.signal.support;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A<Q\u0001E\t\t\u0002a1QAG\t\t\u0002mAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005\u0002\u00152AAG\t\u0001Q!A\u0001\b\u0002BC\u0002\u0013\u0005\u0011\b\u0003\u0005A\t\t\u0005\t\u0015!\u0003;\u0011!\tEA!b\u0001\n\u0003\u0012\u0005\u0002\u0003$\u0005\u0005\u0003\u0005\u000b\u0011B\"\t\u0011\u001d#!Q1A\u0005B!C\u0001\u0002\u0016\u0003\u0003\u0002\u0003\u0006I!\u0013\u0005\u0006E\u0011!\t!\u0016\u0005\t5\u0012A)\u0019!C\u00017\"Aq\f\u0002EC\u0002\u0013\u00051\f\u0003\u0005a\t!\u0015\r\u0011\"\u0001\\\u0011\u0015\tG\u0001\"\u0011c\u0003-1\u0015JU&fe:,G.\r#\u000b\u0005I\u0019\u0012aB:vaB|'\u000f\u001e\u0006\u0003)U\taa]5h]\u0006d'\"\u0001\f\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"!G\u0001\u000e\u0003E\u00111BR%S\u0017\u0016\u0014h.\u001a72\tN\u0011\u0011\u0001\b\t\u0003;\u0001j\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005A\u0012!B1qa2LXC\u0001\u0014l)\u00119CN\\8\u0011\u0007e!!.\u0006\u0002*_M\u0011AA\u000b\t\u00043-j\u0013B\u0001\u0017\u0012\u000591\u0015\u000e\u001c;fe.+'O\\3mc\u0011\u0003\"AL\u0018\r\u0001\u0011)\u0001\u0007\u0002b\u0001c\t\tA+\u0005\u00023kA\u0011QdM\u0005\u0003iy\u0011qAT8uQ&tw\r\u0005\u0002\u001em%\u0011qG\b\u0002\u0004\u0003:L\u0018AB6fe:,G.F\u0001;!\rYd(L\u0007\u0002y)\u0011Q(F\u0001\u0007Y&t\u0017\r\\4\n\u0005}b$a\u0003#f]N,g+Z2u_J\fqa[3s]\u0016d\u0007%\u0001\u0006nk2$\u0018\u000e\u001d7jKJ,\u0012a\u0011\t\u0003;\u0011K!!\u0012\u0010\u0003\r\u0011{WO\u00197f\u0003-iW\u000f\u001c;ja2LWM\u001d\u0011\u0002\u0015\u0011,7/[4o)\u0016DH/F\u0001J!\tQ\u0015K\u0004\u0002L\u001fB\u0011AJH\u0007\u0002\u001b*\u0011ajF\u0001\u0007yI|w\u000e\u001e \n\u0005As\u0012A\u0002)sK\u0012,g-\u0003\u0002S'\n11\u000b\u001e:j]\u001eT!\u0001\u0015\u0010\u0002\u0017\u0011,7/[4o)\u0016DH\u000f\t\u000b\u0005-^C\u0016\fE\u0002\u001a\t5BQ\u0001O\u0006A\u0002iBQ!Q\u0006A\u0002\rCQaR\u0006A\u0002%\u000ba\u0001\\3oORDW#\u0001/\u0011\u0005ui\u0016B\u00010\u001f\u0005\rIe\u000e^\u0001\f_Z,'\u000f[1oOB\u0013X-\u0001\u0007pm\u0016\u0014\b.\u00198h!>\u001cH/\u0001\u0005u_N#(/\u001b8h)\u0005\u0019\u0007C\u00013j\u001b\u0005)'B\u00014h\u0003\u0011a\u0017M\\4\u000b\u0003!\fAA[1wC&\u0011!+\u001a\t\u0003]-$Q\u0001M\u0002C\u0002EBQ\u0001O\u0002A\u00025\u00042a\u000f k\u0011\u0015\t5\u00011\u0001D\u0011\u001595\u00011\u0001J\u0001"
)
public class FIRKernel1D extends FilterKernel1D {
   private int length;
   private int overhangPre;
   private int overhangPost;
   private final DenseVector kernel;
   private final double multiplier;
   private final String designText;
   private volatile byte bitmap$0;

   public static FIRKernel1D apply(final DenseVector kernel, final double multiplier, final String designText) {
      return FIRKernel1D$.MODULE$.apply(kernel, multiplier, designText);
   }

   public DenseVector kernel() {
      return this.kernel;
   }

   public double multiplier() {
      return this.multiplier;
   }

   public String designText() {
      return this.designText;
   }

   private int length$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.length = this.kernel().length();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.length;
   }

   public int length() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.length$lzycompute() : this.length;
   }

   private int overhangPre$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.overhangPre = (this.length() - 1) / 2;
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.overhangPre;
   }

   public int overhangPre() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.overhangPre$lzycompute() : this.overhangPre;
   }

   private int overhangPost$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.overhangPost = this.length() - 1 - this.overhangPre();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.overhangPost;
   }

   public int overhangPost() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.overhangPost$lzycompute() : this.overhangPost;
   }

   public String toString() {
      return (new StringBuilder(15)).append(this.getClass().toString()).append(" multiplier: ").append(this.multiplier()).append(": ").append(this.designText()).toString();
   }

   public FIRKernel1D(final DenseVector kernel, final double multiplier, final String designText) {
      this.kernel = kernel;
      this.multiplier = multiplier;
      this.designText = designText;
   }
}
