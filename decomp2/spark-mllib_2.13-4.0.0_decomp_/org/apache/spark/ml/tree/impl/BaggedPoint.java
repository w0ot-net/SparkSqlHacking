package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf!B\n\u0015\u0001i\u0001\u0003\u0002C\u001b\u0001\u0005\u000b\u0007I\u0011\u0001\u001c\t\u0011\t\u0003!\u0011!Q\u0001\n]B\u0001b\u0011\u0001\u0003\u0006\u0004%\t\u0001\u0012\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005\u000b\"AA\n\u0001BC\u0002\u0013\u0005Q\n\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003O\u0011\u0015\u0011\u0006\u0001\"\u0001T\u000f\u0019IF\u0003#\u0001\u001b5\u001a11\u0003\u0006E\u00015mCQAU\u0005\u0005\u0002\rDQ\u0001Z\u0005\u0005\u0002\u0015D\u0011\"!\u0004\n#\u0003%\t!a\u0004\t\u0013\u00055\u0012\"%A\u0005\u0002\u0005=\u0002bBA\u001c\u0013\u0011%\u0011\u0011\b\u0005\b\u0003'JA\u0011BA+\u0011\u001d\t\t(\u0003C\u0005\u0003gB\u0011\"!#\n#\u0003%\t!a#\t\u0013\u0005M\u0015\"!A\u0005\n\u0005U%a\u0003\"bO\u001e,G\rU8j]RT!!\u0006\f\u0002\t%l\u0007\u000f\u001c\u0006\u0003/a\tA\u0001\u001e:fK*\u0011\u0011DG\u0001\u0003[2T!a\u0007\u000f\u0002\u000bM\u0004\u0018M]6\u000b\u0005uq\u0012AB1qC\u000eDWMC\u0001 \u0003\ry'oZ\u000b\u0003Ce\u001a2\u0001\u0001\u0012)!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fMB\u0011\u0011F\r\b\u0003UAr!aK\u0018\u000e\u00031R!!\f\u0018\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!J\u0005\u0003c\u0011\nq\u0001]1dW\u0006<W-\u0003\u00024i\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011\u0007J\u0001\u0006I\u0006$X/\\\u000b\u0002oA\u0011\u0001(\u000f\u0007\u0001\t\u0015Q\u0004A1\u0001<\u0005\u0015!\u0015\r^;n#\tat\b\u0005\u0002${%\u0011a\b\n\u0002\b\u001d>$\b.\u001b8h!\t\u0019\u0003)\u0003\u0002BI\t\u0019\u0011I\\=\u0002\r\u0011\fG/^7!\u0003=\u0019XOY:b[BdWmQ8v]R\u001cX#A#\u0011\u0007\r2\u0005*\u0003\u0002HI\t)\u0011I\u001d:bsB\u00111%S\u0005\u0003\u0015\u0012\u00121!\u00138u\u0003A\u0019XOY:b[BdWmQ8v]R\u001c\b%\u0001\u0007tC6\u0004H.Z,fS\u001eDG/F\u0001O!\t\u0019s*\u0003\u0002QI\t1Ai\\;cY\u0016\fQb]1na2,w+Z5hQR\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003U-^C\u0006cA+\u0001o5\tA\u0003C\u00036\u000f\u0001\u0007q\u0007C\u0003D\u000f\u0001\u0007Q\tC\u0004M\u000fA\u0005\t\u0019\u0001(\u0002\u0017\t\u000bwmZ3e!>Lg\u000e\u001e\t\u0003+&\u00192!\u0003\u0012]!\ti&-D\u0001_\u0015\ty\u0006-\u0001\u0002j_*\t\u0011-\u0001\u0003kCZ\f\u0017BA\u001a_)\u0005Q\u0016AE2p]Z,'\u000f\u001e+p\u0005\u0006<w-\u001a3S\t\u0012+\"AZ8\u0015\u0011\u001d\u00048/^<}\u0003\u0007\u00012\u0001[6n\u001b\u0005I'B\u00016\u001b\u0003\r\u0011H\rZ\u0005\u0003Y&\u00141A\u0015#E!\r)\u0006A\u001c\t\u0003q=$QAO\u0006C\u0002mBQ!]\u0006A\u0002I\fQ!\u001b8qkR\u00042\u0001[6o\u0011\u0015!8\u00021\u0001O\u0003=\u0019XOY:b[Bd\u0017N\\4SCR,\u0007\"\u0002<\f\u0001\u0004A\u0015!\u00048v[N+(m]1na2,7\u000fC\u0003y\u0017\u0001\u0007\u00110A\bxSRD'+\u001a9mC\u000e,W.\u001a8u!\t\u0019#0\u0003\u0002|I\t9!i\\8mK\u0006t\u0007bB?\f!\u0003\u0005\rA`\u0001\u0014Kb$(/Y2u'\u0006l\u0007\u000f\\3XK&<\u0007\u000e\u001e\t\u0005G}tg*C\u0002\u0002\u0002\u0011\u0012\u0011BR;oGRLwN\\\u0019\t\u0013\u0005\u00151\u0002%AA\u0002\u0005\u001d\u0011\u0001B:fK\u0012\u00042aIA\u0005\u0013\r\tY\u0001\n\u0002\u0005\u0019>tw-\u0001\u000fd_:4XM\u001d;U_\n\u000bwmZ3e%\u0012#E\u0005Z3gCVdG\u000fJ\u001b\u0016\t\u0005E\u0011\u0011D\u000b\u0003\u0003'QC!!\u0006\u0002\u001cA)1e`A\f\u001dB\u0019\u0001(!\u0007\u0005\u000bib!\u0019A\u001e,\u0005\u0005u\u0001\u0003BA\u0010\u0003Si!!!\t\u000b\t\u0005\r\u0012QE\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\n%\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003W\t\tCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAdY8om\u0016\u0014H\u000fV8CC\u001e<W\r\u001a*E\t\u0012\"WMZ1vYR$c'\u0006\u0003\u00022\u0005URCAA\u001aU\u0011\t9!a\u0007\u0005\u000bij!\u0019A\u001e\u0002Y\r|gN^3siR{')Y4hK\u0012\u0014F\tR*b[Bd\u0017N\\4XSRDw.\u001e;SKBd\u0017mY3nK:$X\u0003BA\u001e\u0003\u0007\"B\"!\u0010\u0002F\u0005%\u00131JA'\u0003#\u0002B\u0001[6\u0002@A!Q\u000bAA!!\rA\u00141\t\u0003\u0006u9\u0011\ra\u000f\u0005\u0007c:\u0001\r!a\u0012\u0011\t!\\\u0017\u0011\t\u0005\u0006i:\u0001\rA\u0014\u0005\u0006m:\u0001\r\u0001\u0013\u0005\u0007{:\u0001\r!a\u0014\u0011\u000b\rz\u0018\u0011\t(\t\u000f\u0005\u0015a\u00021\u0001\u0002\b\u0005I3m\u001c8wKJ$Hk\u001c\"bO\u001e,GM\u0015#E'\u0006l\u0007\u000f\\5oO^KG\u000f\u001b*fa2\f7-Z7f]R,B!a\u0016\u0002`Qa\u0011\u0011LA1\u0003K\nI'a\u001b\u0002pA!\u0001n[A.!\u0011)\u0006!!\u0018\u0011\u0007a\ny\u0006B\u0003;\u001f\t\u00071\b\u0003\u0004r\u001f\u0001\u0007\u00111\r\t\u0005Q.\fi\u0006\u0003\u0004\u0002h=\u0001\rAT\u0001\ngV\u00147/Y7qY\u0016DQA^\bA\u0002!Ca!`\bA\u0002\u00055\u0004#B\u0012\u0000\u0003;r\u0005bBA\u0003\u001f\u0001\u0007\u0011qA\u0001\"G>tg/\u001a:u)>\u0014\u0015mZ4fIJ#EiV5uQ>,HoU1na2LgnZ\u000b\u0005\u0003k\ni\b\u0006\u0005\u0002x\u0005}\u00141QAC!\u0011A7.!\u001f\u0011\tU\u0003\u00111\u0010\t\u0004q\u0005uD!\u0002\u001e\u0011\u0005\u0004Y\u0004BB9\u0011\u0001\u0004\t\t\t\u0005\u0003iW\u0006m\u0004\"\u0002<\u0011\u0001\u0004A\u0005BB?\u0011\u0001\u0004\t9\tE\u0003$\u007f\u0006md*A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0005\u0003\u001b\u000b\t*\u0006\u0002\u0002\u0010*\u001aa*a\u0007\u0005\u000bi\n\"\u0019A\u001e\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0005\u0003BAM\u0003?k!!a'\u000b\u0007\u0005u\u0005-\u0001\u0003mC:<\u0017\u0002BAQ\u00037\u0013aa\u00142kK\u000e$\b"
)
public class BaggedPoint implements Serializable {
   private final Object datum;
   private final int[] subsampleCounts;
   private final double sampleWeight;

   public static double $lessinit$greater$default$3() {
      return BaggedPoint$.MODULE$.$lessinit$greater$default$3();
   }

   public static long convertToBaggedRDD$default$6() {
      return BaggedPoint$.MODULE$.convertToBaggedRDD$default$6();
   }

   public static Function1 convertToBaggedRDD$default$5() {
      return BaggedPoint$.MODULE$.convertToBaggedRDD$default$5();
   }

   public static RDD convertToBaggedRDD(final RDD input, final double subsamplingRate, final int numSubsamples, final boolean withReplacement, final Function1 extractSampleWeight, final long seed) {
      return BaggedPoint$.MODULE$.convertToBaggedRDD(input, subsamplingRate, numSubsamples, withReplacement, extractSampleWeight, seed);
   }

   public Object datum() {
      return this.datum;
   }

   public int[] subsampleCounts() {
      return this.subsampleCounts;
   }

   public double sampleWeight() {
      return this.sampleWeight;
   }

   public BaggedPoint(final Object datum, final int[] subsampleCounts, final double sampleWeight) {
      this.datum = datum;
      this.subsampleCounts = subsampleCounts;
      this.sampleWeight = sampleWeight;
   }
}
