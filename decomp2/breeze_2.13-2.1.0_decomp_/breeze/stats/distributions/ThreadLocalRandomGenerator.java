package breeze.stats.distributions;

import java.io.Serializable;
import org.apache.commons.math3.random.RandomGenerator;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a\u0001B\b\u0011\u0001]A\u0001\u0002\u0010\u0001\u0003\u0002\u0013\u0006I!\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u0006'\u0002!\tA\u0017\u0005\u0006'\u0002!\t\u0001\u0019\u0005\u0006E\u0002!\ta\u0019\u0005\u0006E\u0002!\t\u0001\u001a\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006S\u0002!\tA\u001b\u0005\u0006]\u0002!\ta\u001c\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0006q\u0002!\t\u0001\u001e\u0005\ts\u0002A)\u0019!C\u0005u\nQB\u000b\u001b:fC\u0012dunY1m%\u0006tGm\\7HK:,'/\u0019;pe*\u0011\u0011CE\u0001\u000eI&\u001cHO]5ckRLwN\\:\u000b\u0005M!\u0012!B:uCR\u001c(\"A\u000b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019B\u0001\u0001\r!]A\u0011\u0011DH\u0007\u00025)\u00111\u0004H\u0001\u0005Y\u0006twMC\u0001\u001e\u0003\u0011Q\u0017M^1\n\u0005}Q\"AB(cU\u0016\u001cG\u000f\u0005\u0002\"Y5\t!E\u0003\u0002$I\u00051!/\u00198e_6T!!\n\u0014\u0002\u000b5\fG\u000f[\u001a\u000b\u0005\u001dB\u0013aB2p[6|gn\u001d\u0006\u0003S)\na!\u00199bG\",'\"A\u0016\u0002\u0007=\u0014x-\u0003\u0002.E\ty!+\u00198e_6<UM\\3sCR|'\u000f\u0005\u00020s9\u0011\u0001G\u000e\b\u0003cQj\u0011A\r\u0006\u0003gY\ta\u0001\u0010:p_Rt\u0014\"A\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]B\u0014a\u00029bG.\fw-\u001a\u0006\u0002k%\u0011!h\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003oa\n\u0001bZ3o)\",hn\u001b\t\u0004}}\u0002S\"\u0001\u001d\n\u0005\u0001C$\u0001\u0003\u001fcs:\fW.\u001a \u0002\rqJg.\u001b;?)\t\u0019U\t\u0005\u0002E\u00015\t\u0001\u0003\u0003\u0004=\u0005\u0011\u0005\r!P\u0001\n]\u0016DHOQ=uKN$\"\u0001S&\u0011\u0005yJ\u0015B\u0001&9\u0005\u0011)f.\u001b;\t\u000b1\u001b\u0001\u0019A'\u0002\u000b\tLH/Z:\u0011\u0007yr\u0005+\u0003\u0002Pq\t)\u0011I\u001d:bsB\u0011a(U\u0005\u0003%b\u0012AAQ=uK\u000691/\u001a;TK\u0016$GC\u0001%V\u0011\u00151F\u00011\u0001X\u0003\u0011\u0019X-\u001a3\u0011\u0005yB\u0016BA-9\u0005\u0011auN\\4\u0015\u0005![\u0006\"\u0002,\u0006\u0001\u0004a\u0006c\u0001 O;B\u0011aHX\u0005\u0003?b\u00121!\u00138u)\tA\u0015\rC\u0003W\r\u0001\u0007Q,A\u0004oKb$\u0018J\u001c;\u0015\u0003u#\"!X3\t\u000b\u0019D\u0001\u0019A/\u0002\u00039\f\u0001B\\3yi2{gn\u001a\u000b\u0002/\u0006Ya.\u001a=u\u0005>|G.Z1o)\u0005Y\u0007C\u0001 m\u0013\ti\u0007HA\u0004C_>dW-\u00198\u0002\u00139,\u0007\u0010\u001e$m_\u0006$H#\u00019\u0011\u0005y\n\u0018B\u0001:9\u0005\u00151En\\1u\u0003)qW\r\u001f;E_V\u0014G.\u001a\u000b\u0002kB\u0011aH^\u0005\u0003ob\u0012a\u0001R8vE2,\u0017\u0001\u00048fqR<\u0015-^:tS\u0006t\u0017!B4f]RcU#A>\u0013\u0007qthF\u0002\u0003~\u001d\u0001Y(\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004cA\r\u0000A%\u0019\u0011\u0011\u0001\u000e\u0003\u0017QC'/Z1e\u0019>\u001c\u0017\r\u001c\u0015\u0004\u001d\u0005\u0015\u0001c\u0001 \u0002\b%\u0019\u0011\u0011\u0002\u001d\u0003\u0013Q\u0014\u0018M\\:jK:$\bf\u0002\u0001\u0002\u000e\u0005M\u0011Q\u0003\t\u0004}\u0005=\u0011bAA\tq\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0003\u0001"
)
public class ThreadLocalRandomGenerator implements RandomGenerator, Serializable {
   private static final long serialVersionUID = 1L;
   private transient ThreadLocal genTL;
   public final Function0 breeze$stats$distributions$ThreadLocalRandomGenerator$$genThunk;
   private transient volatile boolean bitmap$trans$0;

   private ThreadLocal genTL$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.genTL = new Serializable() {
               // $FF: synthetic field
               private final ThreadLocalRandomGenerator $outer;

               public RandomGenerator initialValue() {
                  return (RandomGenerator)this.$outer.breeze$stats$distributions$ThreadLocalRandomGenerator$$genThunk.apply();
               }

               public {
                  if (ThreadLocalRandomGenerator.this == null) {
                     throw null;
                  } else {
                     this.$outer = ThreadLocalRandomGenerator.this;
                  }
               }
            };
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.genTL;
   }

   private ThreadLocal genTL() {
      return !this.bitmap$trans$0 ? this.genTL$lzycompute() : this.genTL;
   }

   public void nextBytes(final byte[] bytes) {
      ((RandomGenerator)this.genTL().get()).nextBytes(bytes);
   }

   public void setSeed(final long seed) {
      ((RandomGenerator)this.genTL().get()).setSeed(seed);
   }

   public void setSeed(final int[] seed) {
      ((RandomGenerator)this.genTL().get()).setSeed(seed);
   }

   public void setSeed(final int seed) {
      ((RandomGenerator)this.genTL().get()).setSeed(seed);
   }

   public int nextInt() {
      return ((RandomGenerator)this.genTL().get()).nextInt();
   }

   public int nextInt(final int n) {
      return ((RandomGenerator)this.genTL().get()).nextInt(n);
   }

   public long nextLong() {
      return ((RandomGenerator)this.genTL().get()).nextLong();
   }

   public boolean nextBoolean() {
      return ((RandomGenerator)this.genTL().get()).nextBoolean();
   }

   public float nextFloat() {
      return ((RandomGenerator)this.genTL().get()).nextFloat();
   }

   public double nextDouble() {
      return ((RandomGenerator)this.genTL().get()).nextDouble();
   }

   public double nextGaussian() {
      return ((RandomGenerator)this.genTL().get()).nextGaussian();
   }

   public ThreadLocalRandomGenerator(final Function0 genThunk) {
      this.breeze$stats$distributions$ThreadLocalRandomGenerator$$genThunk = genThunk;
   }
}
