package breeze.numerics;

import breeze.linalg.scaleAdd$;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054q\u0001E\t\u0011\u0002\u0007\u0005a\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007i\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000bM\u0002A\u0011\u0001\u001b\t\u000bY\u0002A\u0011A\u001c\t\u000bq\u0002A\u0011A\u001f\t\u000b\t\u0003A\u0011A\"\t\u000b1\u0003A\u0011A'\t\u000bA\u0003A\u0011A)\t\u000bU\u0003A\u0011\u0001,\b\u000be\u000b\u0002\u0012\u0001.\u0007\u000bA\t\u0002\u0012\u0001/\t\u000bycA\u0011A0\t\u000f\tb!\u0019!C\u0001G!1\u0001\r\u0004Q\u0001\n\u0011\u0012qaU2bY&twM\u0003\u0002\u0013'\u0005Aa.^7fe&\u001c7OC\u0001\u0015\u0003\u0019\u0011'/Z3{K\u000e\u00011C\u0001\u0001\u0018!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012a\b\t\u00031\u0001J!!I\r\u0003\tUs\u0017\u000e^\u0001\u000eg\u000e\fG.Z\"p]N$\u0018M\u001c;\u0016\u0003\u0011\u0002\"\u0001G\u0013\n\u0005\u0019J\"aA%oi\u0006Q1oY1mK\u0006\u0013(/Y=\u0015\u0007\u0011J\u0013\u0007C\u0003+\u0007\u0001\u00071&\u0001\u0004tG>\u0014Xm\u001d\t\u000411r\u0013BA\u0017\u001a\u0005\u0015\t%O]1z!\tAr&\u0003\u000213\t1Ai\\;cY\u0016DQAM\u0002A\u0002\u0011\nAbY;se\u0016tGoU2bY\u0016\f\u0011cY8naV$XmU2bY\u0016$U\r\u001c;b)\t!S\u0007C\u0003+\t\u0001\u00071&\u0001\beKR,'/\\5oKN\u001b\u0017\r\\3\u0015\u0007\u0011B$\bC\u0003:\u000b\u0001\u0007a&A\u0003tG>\u0014X\rC\u0003<\u000b\u0001\u0007A%\u0001\u0005pY\u0012\u001c6-\u00197f\u0003E\u00198-\u00197f\u0003J\u0014\u0018-\u001f+p'\u000e\fG.\u001a\u000b\u0005?yz\u0004\tC\u0003+\r\u0001\u00071\u0006C\u00033\r\u0001\u0007A\u0005C\u0003B\r\u0001\u0007A%A\u0006uCJ<W\r^*dC2,\u0017!C:v[\u0006\u0013(/Y=t)\u0015!CI\u0012%K\u0011\u0015)u\u00011\u0001,\u0003\r\u0019(o\u0019\u0005\u0006\u000f\u001e\u0001\r\u0001J\u0001\tgJ\u001c7kY1mK\")\u0011j\u0002a\u0001W\u0005!A-Z:u\u0011\u0015Yu\u00011\u0001%\u0003%!Wm\u001d;TG\u0006dW-\u0001\u0007v]N\u001c\u0017\r\\3WC2,X\rF\u0002/\u001d>CQ!\u000f\u0005A\u00029BQA\r\u0005A\u0002\u0011\n!b]2bY\u00164\u0016\r\\;f)\u0011q#k\u0015+\t\u000beJ\u0001\u0019\u0001\u0018\t\u000bIJ\u0001\u0019\u0001\u0013\t\u000b\u0005K\u0001\u0019\u0001\u0013\u0002\u0015Q|Gj\\4Ta\u0006\u001cW\rF\u0002//bCQ!\u000f\u0006A\u00029BQA\r\u0006A\u0002\u0011\nqaU2bY&tw\r\u0005\u0002\\\u00195\t\u0011cE\u0002\r/u\u0003\"a\u0017\u0001\u0002\rqJg.\u001b;?)\u0005Q\u0016AD:dC2,7i\u001c8ti\u0006tG\u000f\t"
)
public interface Scaling {
   int scaleConstant();

   // $FF: synthetic method
   static int scaleArray$(final Scaling $this, final double[] scores, final int currentScale) {
      return $this.scaleArray(scores, currentScale);
   }

   default int scaleArray(final double[] scores, final int currentScale) {
      int scaleDelta = this.computeScaleDelta(scores);
      if (scaleDelta != 0) {
         for(int i = 0; i < scores.length; ++i) {
            scores[i] = Math.scalb(scores[i], -scaleDelta);
         }
      }

      return currentScale + scaleDelta;
   }

   // $FF: synthetic method
   static int computeScaleDelta$(final Scaling $this, final double[] scores) {
      return $this.computeScaleDelta(scores);
   }

   default int computeScaleDelta(final double[] scores) {
      int maxScale = -10000;

      for(int i = 0; i < scores.length; ++i) {
         double score = scores[i];
         if (score != (double)0.0F) {
            int exp = Math.getExponent(score);
            maxScale = .MODULE$.max(maxScale, exp);
         }
      }

      return maxScale == -10000 ? 0 : (maxScale > this.scaleConstant() ? this.scaleConstant() * (maxScale / this.scaleConstant()) : (maxScale < -this.scaleConstant() ? this.scaleConstant() * (maxScale / this.scaleConstant()) : 0));
   }

   // $FF: synthetic method
   static int determineScale$(final Scaling $this, final double score, final int oldScale) {
      return $this.determineScale(score, oldScale);
   }

   default int determineScale(final double score, final int oldScale) {
      int var10000;
      if (score != (double)0.0F) {
         int maxScale = Math.getExponent(score);
         var10000 = maxScale == -10000 ? oldScale : (maxScale > this.scaleConstant() ? oldScale + this.scaleConstant() * (maxScale / this.scaleConstant()) : (maxScale < -this.scaleConstant() ? oldScale + this.scaleConstant() * (maxScale / this.scaleConstant()) : oldScale));
      } else {
         var10000 = Integer.MIN_VALUE;
      }

      return var10000;
   }

   // $FF: synthetic method
   static void scaleArrayToScale$(final Scaling $this, final double[] scores, final int currentScale, final int targetScale) {
      $this.scaleArrayToScale(scores, currentScale, targetScale);
   }

   default void scaleArrayToScale(final double[] scores, final int currentScale, final int targetScale) {
      int scaleDelta = targetScale - currentScale;
      if (scaleDelta != 0) {
         for(int i = 0; i < scores.length; ++i) {
            scores[i] = Math.scalb(scores[i], -scaleDelta);
         }
      }

   }

   // $FF: synthetic method
   static int sumArrays$(final Scaling $this, final double[] src, final int srcScale, final double[] dest, final int destScale) {
      return $this.sumArrays(src, srcScale, dest, destScale);
   }

   default int sumArrays(final double[] src, final int srcScale, final double[] dest, final int destScale) {
      int var10000;
      if (destScale == srcScale) {
         breeze.linalg.package$.MODULE$.axpy(BoxesRunTime.boxToDouble((double)1.0F), src, dest, scaleAdd$.MODULE$.scaleAddArray_Double());
         var10000 = destScale;
      } else if (destScale - srcScale > 53 + 2 * this.scaleConstant()) {
         var10000 = destScale;
      } else if (srcScale - destScale > 53 + 2 * this.scaleConstant()) {
         System.arraycopy(src, 0, dest, 0, dest.length);
         var10000 = srcScale;
      } else if (srcScale > destScale) {
         this.scaleArrayToScale(dest, destScale, srcScale);

         for(int i = 0; i < dest.length; ++i) {
            dest[i] += src[i];
         }

         var10000 = srcScale;
      } else {
         int scaleDelta = destScale - srcScale;

         for(int i = 0; i < dest.length; ++i) {
            dest[i] += Math.scalb(src[i], -scaleDelta);
         }

         var10000 = destScale;
      }

      return var10000;
   }

   // $FF: synthetic method
   static double unscaleValue$(final Scaling $this, final double score, final int currentScale) {
      return $this.unscaleValue(score, currentScale);
   }

   default double unscaleValue(final double score, final int currentScale) {
      return Math.scalb(score, currentScale);
   }

   // $FF: synthetic method
   static double scaleValue$(final Scaling $this, final double score, final int currentScale, final int targetScale) {
      return $this.scaleValue(score, currentScale, targetScale);
   }

   default double scaleValue(final double score, final int currentScale, final int targetScale) {
      return Math.scalb(score, currentScale - targetScale);
   }

   // $FF: synthetic method
   static double toLogSpace$(final Scaling $this, final double score, final int currentScale) {
      return $this.toLogSpace(score, currentScale);
   }

   default double toLogSpace(final double score, final int currentScale) {
      return package.log$.MODULE$.apply$mDDc$sp(score, package$log$logDoubleImpl$.MODULE$) + (double)currentScale * package.log$.MODULE$.apply$mDDc$sp((double)2.0F, package$log$logDoubleImpl$.MODULE$);
   }

   static void $init$(final Scaling $this) {
   }
}
