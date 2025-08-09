package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipAndTraverseValues;
import breeze.util.WideningConversion;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dw!B\t\u0013\u0011\u00039b!B\r\u0013\u0011\u0003Q\u0002\"B\u0014\u0002\t\u0003Ac\u0001B\u0015\u0002\u0001)B\u0001\"E\u0002\u0003\u0006\u0004%\t\u0001\f\u0005\t}\r\u0011\t\u0011)A\u0005[!Aqh\u0001B\u0001B\u0003%\u0001\t\u0003\u0005D\u0007\t\u0005\t\u0015!\u0003A\u0011!!5A!A!\u0002\u0013\u0001\u0005BB\u0014\u0004\t\u0003!R\t\u0003\u0005M\u0007!\u0015\r\u0011\"\u0001N\u0011\u0015y\u0015\u0001b\u0001Q\u0011\u0015\u0019\u0017\u0001b\u0001e\u0011\u001d\t9!\u0001C\u0002\u0003\u0013Aq!a\u0017\u0002\t\u0007\ti\u0006C\u0004\u0002x\u0005!\u0019!!\u001f\t\u000f\u0005\u0015\u0016\u0001b\u0001\u0002(\u0006!\u0001.[:u\u0015\t\u0019B#A\u0003ti\u0006$8OC\u0001\u0016\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\r\u0002\u001b\u0005\u0011\"\u0001\u00025jgR\u001c2!A\u000e\"!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fMB\u0011!%J\u0007\u0002G)\u0011A\u0005F\u0001\bO\u0016tWM]5d\u0013\t13EA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002/\tI\u0001*[:u_\u001e\u0014\u0018-\\\u000b\u0003WU\u001a\"aA\u000e\u0016\u00035\u00022AL\u00194\u001b\u0005y#B\u0001\u0019\u0015\u0003\u0019a\u0017N\\1mO&\u0011!g\f\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u00025k1\u0001A!\u0002\u001c\u0004\u0005\u00049$!A*\u0012\u0005aZ\u0004C\u0001\u000f:\u0013\tQTDA\u0004O_RD\u0017N\\4\u0011\u0005qa\u0014BA\u001f\u001e\u0005\r\te._\u0001\u0006Q&\u001cH\u000fI\u0001\u0006gR\f'\u000f\u001e\t\u00039\u0005K!AQ\u000f\u0003\r\u0011{WO\u00197f\u0003\r)g\u000eZ\u0001\u0005E&t7\u000fF\u0003G\u0011&S5\nE\u0002H\u0007Mj\u0011!\u0001\u0005\u0006#%\u0001\r!\f\u0005\u0006\u007f%\u0001\r\u0001\u0011\u0005\u0006\u0007&\u0001\r\u0001\u0011\u0005\u0006\t&\u0001\r\u0001Q\u0001\tE&tW\tZ4fgV\ta\nE\u0002/c\u0001\u000b1\u0002Z3gCVdG\u000fS5tiV\u0019\u0011K\u0016.\u0015\u0005I[\u0006\u0003B$T+bK!\u0001V\u0013\u0003\t%k\u0007\u000f\u001c\t\u0003iY#QaV\u0006C\u0002]\u0012\u0011\u0001\u0016\t\u0004\u000f\u000eI\u0006C\u0001\u001b[\t\u001514B1\u00018\u0011\u0015a6\u0002q\u0001^\u0003%IgN\\3s\u00136\u0004H\u000eE\u0003H=V\u0003\u0007,\u0003\u0002`K\t)\u0011*\u001c9meA\u0011A$Y\u0005\u0003Ev\u00111!\u00138u\u0003=!WMZ1vYRD\u0015n\u001d;CS:\u001cXcA3iiR!aM[;~!\u00159el\u001a1j!\t!\u0004\u000eB\u0003X\u0019\t\u0007q\u0007E\u0002H\u0007\u0001DQa\u001b\u0007A\u00041\f!!\\7\u0011\t5\u001cv\r\u001d\b\u0003]9L!a\\\u0018\u0002\r5Lg.T1y!\u0011a\u0012o]:\n\u0005Il\"A\u0002+va2,'\u0007\u0005\u00025i\u0012)a\u0007\u0004b\u0001o!)a\u000f\u0004a\u0002o\u0006!1m\u001c8w!\u0011A8p\u001d!\u000e\u0003eT!A\u001f\u000b\u0002\tU$\u0018\u000e\\\u0005\u0003yf\u0014!cV5eK:LgnZ\"p]Z,'o]5p]\")a\u0010\u0004a\u0002\u007f\u0006)\u0011.\u001c9mgAAq)!\u0001hA\u0006\u0015\u0011.C\u0002\u0002\u0004\u0015\u0012Q!S7qYN\u0002B\u0001H9A\u0001\u0006)2-\u00198Ue\u00064XM]:f-\u0006dW/Z:J[BdWCBA\u0006\u0003#\t)\u0003\u0006\u0004\u0002\u000e\u0005M\u0011q\u000b\t\n\u000f\u0006\u0005\u0011q\u00021\u0002\u0006%\u00042\u0001NA\t\t\u00159VB1\u00018\u0011\u001d\t)\"\u0004a\u0002\u0003/\tA!\u001b;feBA\u0011\u0011DA\u0010\u0003\u001f\t\u0019#\u0004\u0002\u0002\u001c)\u0019\u0011QD\u0018\u0002\u000fM,\b\u000f]8si&!\u0011\u0011EA\u000e\u0005E\u0019\u0015M\u001c+sCZ,'o]3WC2,Xm\u001d\t\u0004i\u0005\u0015B!\u0003\u001c\u000eA\u0003\u0005\tQ1\u00018Q)\t)#!\u000b\u00020\u0005\r\u0013Q\n\t\u00049\u0005-\u0012bAA\u0017;\tY1\u000f]3dS\u0006d\u0017N_3ec%\u0019\u0013\u0011GA\u001a\u0003o\t)DD\u0002\u001d\u0003gI1!!\u000e\u001e\u0003\rIe\u000e^\u0019\u0007I\u0005e\u0012\u0011\t\u0010\u000f\t\u0005m\u0012\u0011I\u0007\u0003\u0003{Q1!a\u0010\u0017\u0003\u0019a$o\\8u}%\ta$M\u0005$\u0003\u000b\n9%a\u0013\u0002J9\u0019A$a\u0012\n\u0007\u0005%S$A\u0003GY>\fG/\r\u0004%\u0003s\t\tEH\u0019\nG\u0005=\u0013\u0011KA+\u0003'r1\u0001HA)\u0013\r\t\u0019&H\u0001\u0007\t>,(\r\\32\r\u0011\nI$!\u0011\u001f\u0011\u00191X\u0002q\u0001\u0002ZA)\u0001p_A\u0012\u0001\u0006\u0011B-\u001a4bk2$\b*[:u/\u0016Lw\r\u001b;t+!\ty&!\u001a\u0002j\u0005ED\u0003BA1\u0003g\u0002\u0002b\u00120\u0002d\u0005\u001d\u0014Q\u000e\t\u0004i\u0005\u0015D!B,\u000f\u0005\u00049\u0004c\u0001\u001b\u0002j\u00111\u00111\u000e\bC\u0002]\u0012\u0011!\u0016\t\u0005\u000f\u000e\ty\u0007E\u00025\u0003c\"QA\u000e\bC\u0002]Ba\u0001\u0018\bA\u0004\u0005U\u0004CC$\u0002\u0002\u0005\r\u0004-a\u001a\u0002n\u00051B-\u001a4bk2$\b*[:u\u0005&t7oV3jO\"$8/\u0006\u0006\u0002|\u0005\u0005\u0015QQAP\u0003\u0017#\u0002\"! \u0002\u0010\u0006]\u0015\u0011\u0015\t\u000b\u000f\u0006\u0005\u0011q\u00101\u0002\u0004\u0006\u001d\u0005c\u0001\u001b\u0002\u0002\u0012)qk\u0004b\u0001oA\u0019A'!\"\u0005\r\u0005-tB1\u00018!\u001195!!#\u0011\u0007Q\nY\t\u0002\u0004\u0002\u000e>\u0011\ra\u000e\u0002\u0002%\"1Al\u0004a\u0002\u0003#\u0003BbRAJ\u0003\u007f\u0002\u0017QAAB\u0003\u000fK1!!&&\u0005\u0015IU\u000e\u001d75\u0011\u0019Yw\u0002q\u0001\u0002\u001aB1QnUA@\u00037\u0003b\u0001H9\u0002\u001e\u0006u\u0005c\u0001\u001b\u0002 \u0012)ag\u0004b\u0001o!1ao\u0004a\u0002\u0003G\u0003R\u0001_>\u0002\u001e\u0002\u000bQdY1o)J\fg/\u001a:tKZ\u000bG.^3t\u00136\u0004HnV3jO\"$X\rZ\u000b\t\u0003S\u000by+a-\u0002BR1\u00111VA\\\u0003\u0007\u0004BbRAJ\u0003[\u0003\u0017QAAY\u0003k\u00032\u0001NAX\t\u00159\u0006C1\u00018!\r!\u00141\u0017\u0003\u0007\u0003W\u0002\"\u0019A\u001c\u0011\u0007\u001d\u001b\u0001\tC\u0004\u0002\u0016A\u0001\u001d!!/\u0011\u0017\u0005e\u00111XAW\u0003c\u000by\fQ\u0005\u0005\u0003{\u000bYBA\fDC:T\u0016\u000e]!oIR\u0013\u0018M^3sg\u00164\u0016\r\\;fgB\u0019A'!1\u0005\u000bY\u0002\"\u0019A\u001c\t\rY\u0004\u00029AAc!\u0015A80a0A\u0001"
)
public final class hist {
   public static UFunc.UImpl4 canTraverseValuesImplWeighted(final CanZipAndTraverseValues iter, final WideningConversion conv) {
      return hist$.MODULE$.canTraverseValuesImplWeighted(iter, conv);
   }

   public static UFunc.UImpl3 defaultHistBinsWeights(final UFunc.UImpl4 innerImpl, final UFunc.UImpl mm, final WideningConversion conv) {
      return hist$.MODULE$.defaultHistBinsWeights(innerImpl, mm, conv);
   }

   public static UFunc.UImpl2 defaultHistWeights(final UFunc.UImpl3 innerImpl) {
      return hist$.MODULE$.defaultHistWeights(innerImpl);
   }

   public static UFunc.UImpl3 canTraverseValuesImpl(final CanTraverseValues iter, final WideningConversion conv) {
      return hist$.MODULE$.canTraverseValuesImpl(iter, conv);
   }

   public static UFunc.UImpl2 defaultHistBins(final UFunc.UImpl mm, final WideningConversion conv, final UFunc.UImpl3 impl3) {
      return hist$.MODULE$.defaultHistBins(mm, conv, impl3);
   }

   public static UFunc.UImpl defaultHist(final UFunc.UImpl2 innerImpl) {
      return hist$.MODULE$.defaultHist(innerImpl);
   }

   public static Object withSink(final Object s) {
      return hist$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return hist$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return hist$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return hist$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return hist$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return hist$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return hist$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return hist$.MODULE$.apply(v, impl);
   }

   public static class Histogram {
      private DenseVector binEdges;
      private final DenseVector hist;
      private final double start;
      private final double end;
      private final double bins;
      private volatile boolean bitmap$0;

      public DenseVector hist() {
         return this.hist;
      }

      private DenseVector binEdges$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.binEdges = (DenseVector)DenseVector$.MODULE$.rangeD(this.start, this.end + (this.end - this.start) / this.bins, (this.end - this.start) / this.bins);
               this.bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.binEdges;
      }

      public DenseVector binEdges() {
         return !this.bitmap$0 ? this.binEdges$lzycompute() : this.binEdges;
      }

      public Histogram(final DenseVector hist, final double start, final double end, final double bins) {
         this.hist = hist;
         this.start = start;
         this.end = end;
         this.bins = bins;
      }
   }
}
