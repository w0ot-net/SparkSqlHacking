package breeze.linalg;

import breeze.linalg.support.CanCreateZeros;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.Rand$;
import breeze.storage.Zero;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}fa\u0002\b\u0010!\u0003\r\t\u0001\u0006\u0005\u00069\u0001!\t!\b\u0005\u0006C\u00011\tA\t\u0005\u0006#\u00021\tA\u0015\u0005\u0006#\u0002!\t!\u001f\u0005\b\u0003\u001b\u0001A1AA\b\u0011\u001d\t\t\u0004\u0001C\u0001\u0003gA\u0011\"!\u0017\u0001#\u0003%\t!a\u0017\t\u000f\u0005u\u0004\u0001\"\u0001\u0002\u0000!9\u0011Q\u0010\u0001\u0005\u0002\u0005-\u0005bBAK\u0001\u0011\u0005\u0011q\u0013\u0005\n\u0003O\u0003\u0011\u0013!C\u0001\u0003SCq!!,\u0001\t\u0003\ty\u000bC\u0005\u0002:\u0002\t\n\u0011\"\u0001\u0002<\n\u0011b+Z2u_J\u001cuN\\:ueV\u001cGo\u001c:t\u0015\t\u0001\u0012#\u0001\u0004mS:\fGn\u001a\u0006\u0002%\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0002\u0016OM\u0011\u0001A\u0006\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005q\u0002CA\f \u0013\t\u0001\u0003D\u0001\u0003V]&$\u0018!\u0002>fe>\u001cXCA\u0012;)\t!C\nF\u0002&y\u0011\u00032AJ\u0014:\u0019\u0001!Q\u0001\u000b\u0001C\u0002%\u00121AV3d+\tQ3'\u0005\u0002,]A\u0011q\u0003L\u0005\u0003[a\u0011qAT8uQ&tw\rE\u00020aIj\u0011aD\u0005\u0003c=\u0011aAV3di>\u0014\bC\u0001\u00144\t\u0015!tE1\u00016\u0005\u0005!\u0016CA\u00167!\t9r'\u0003\u000291\t\u0019\u0011I\\=\u0011\u0005\u0019RD!B\u001e\u0003\u0005\u0004)$!\u0001,\t\u000fu\u0012\u0011\u0011!a\u0002}\u0005QQM^5eK:\u001cW\r\n\u001c\u0011\u0007}\u0012\u0015(D\u0001A\u0015\t\t\u0005$A\u0004sK\u001adWm\u0019;\n\u0005\r\u0003%\u0001C\"mCN\u001cH+Y4\t\u000f\u0015\u0013\u0011\u0011!a\u0002\r\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\u0007\u001dS\u0015(D\u0001I\u0015\tI\u0015#A\u0004ti>\u0014\u0018mZ3\n\u0005-C%\u0001\u0002.fe>DQ!\u0014\u0002A\u00029\u000bAa]5{KB\u0011qcT\u0005\u0003!b\u00111!\u00138u\u0003\u0015\t\u0007\u000f\u001d7z+\t\u0019f\u000b\u0006\u0002UiB\u0019aeJ+\u0011\u0005\u00192F!C\u001e\u0004A\u0003\u0005\tQ1\u00016Q\u00191\u0006lW3k_B\u0011q#W\u0005\u00035b\u00111b\u001d9fG&\fG.\u001b>fIF*1\u0005X/`=:\u0011q#X\u0005\u0003=b\ta\u0001R8vE2,\u0017\u0007\u0002\u0013aIfq!!\u00193\u000e\u0003\tT!aY\n\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0012'B\u0012gO&DgBA\fh\u0013\tA\u0007$A\u0002J]R\fD\u0001\n1e3E*1e\u001b7o[:\u0011q\u0003\\\u0005\u0003[b\tQA\u00127pCR\fD\u0001\n1e3E*1\u0005]9te:\u0011q#]\u0005\u0003eb\tA\u0001T8oOF\"A\u0005\u00193\u001a\u0011\u0015)8\u00011\u0001w\u0003\u00191\u0018\r\\;fgB\u0019qc^+\n\u0005aD\"!B!se\u0006LXC\u0001>\u007f)\rY\u0018Q\u0001\u000b\u0003y~\u00042AJ\u0014~!\t1c\u0010B\u0003<\t\t\u0007Q\u0007C\u0005\u0002\u0002\u0011\t\t\u0011q\u0001\u0002\u0004\u0005QQM^5eK:\u001cW\r\n\u001d\u0011\u0007}\u0012U\u0010\u0003\u0004v\t\u0001\u0007\u0011q\u0001\t\u0005/\u0005%Q0C\u0002\u0002\fa\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?\u00039\u0019\u0017M\\\"sK\u0006$XMW3s_N,B!!\u0005\u0002$Q1\u00111CA\u0013\u0003W\u0001r!!\u0006\u0002\u001c\u0005}a*\u0004\u0002\u0002\u0018)\u0019\u0011\u0011D\b\u0002\u000fM,\b\u000f]8si&!\u0011QDA\f\u00059\u0019\u0015M\\\"sK\u0006$XMW3s_N\u0004BAJ\u0014\u0002\"A\u0019a%a\t\u0005\u000bm*!\u0019A\u001b\t\u0013\u0005\u001dR!!AA\u0004\u0005%\u0012AC3wS\u0012,gnY3%sA!qHQA\u0011\u0011%\ti#BA\u0001\u0002\b\ty#A\u0006fm&$WM\\2fIE\u0002\u0004\u0003B$K\u0003C\tAA]1oIV!\u0011QGA\u001f)\u0019\t9$!\u0012\u0002HQ!\u0011\u0011HA !\u00111s%a\u000f\u0011\u0007\u0019\ni\u0004B\u00035\r\t\u0007Q\u0007C\u0005\u0002B\u0019\t\t\u0011q\u0001\u0002D\u0005YQM^5eK:\u001cW\rJ\u00192!\u0011y$)a\u000f\t\u000b53\u0001\u0019\u0001(\t\u0013\u0005Eb\u0001%AA\u0002\u0005%\u0003CBA&\u0003+\nY$\u0004\u0002\u0002N)!\u0011qJA)\u00035!\u0017n\u001d;sS\n,H/[8og*\u0019\u00111K\t\u0002\u000bM$\u0018\r^:\n\t\u0005]\u0013Q\n\u0002\u0005%\u0006tG-\u0001\bsC:$G\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u0005u\u00131P\u000b\u0003\u0003?RC!!\u0019\u0002jA1\u00111JA+\u0003G\u00022aFA3\u0013\r\t9\u0007\u0007\u0002\u0007\t>,(\r\\3,\u0005\u0005-\u0004\u0003BA7\u0003oj!!a\u001c\u000b\t\u0005E\u00141O\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u001e\u0019\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003s\nyGA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$Q\u0001N\u0004C\u0002U\nQA]1oO\u0016$b!!!\u0002\u0004\u0006\u001d\u0005c\u0001\u0014(\u001d\"1\u0011Q\u0011\u0005A\u00029\u000bQa\u001d;beRDa!!#\t\u0001\u0004q\u0015aA3oIRA\u0011\u0011QAG\u0003\u001f\u000b\t\n\u0003\u0004\u0002\u0006&\u0001\rA\u0014\u0005\u0007\u0003\u0013K\u0001\u0019\u0001(\t\r\u0005M\u0015\u00021\u0001O\u0003\u0011\u0019H/\u001a9\u0002\rI\fgnZ3G)!\tI*!)\u0002$\u0006\u0015\u0006\u0003\u0002\u0014(\u00037\u00032aFAO\u0013\r\ty\n\u0007\u0002\u0006\r2|\u0017\r\u001e\u0005\b\u0003\u000bS\u0001\u0019AAN\u0011\u001d\tII\u0003a\u0001\u00037C\u0011\"a%\u000b!\u0003\u0005\r!a'\u0002!I\fgnZ3GI\u0011,g-Y;mi\u0012\u001aTCAAVU\u0011\tY*!\u001b\u0002\rI\fgnZ3E)!\t\t,a-\u00026\u0006]\u0006\u0003\u0002\u0014(\u0003GBq!!\"\r\u0001\u0004\t\u0019\u0007C\u0004\u0002\n2\u0001\r!a\u0019\t\u0013\u0005ME\u0002%AA\u0002\u0005\r\u0014\u0001\u0005:b]\u001e,G\t\n3fM\u0006,H\u000e\u001e\u00134+\t\tiL\u000b\u0003\u0002d\u0005%\u0004"
)
public interface VectorConstructors {
   Vector zeros(final int size, final ClassTag evidence$6, final Zero evidence$7);

   Vector apply(final Object values);

   // $FF: synthetic method
   static Vector apply$(final VectorConstructors $this, final Seq values, final ClassTag evidence$8) {
      return $this.apply(values, evidence$8);
   }

   default Vector apply(final Seq values, final ClassTag evidence$8) {
      Vector var10000;
      label55: {
         ClassTag man = (ClassTag).MODULE$.implicitly(evidence$8);
         Object var4 = .MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.Double());
         if (man == null) {
            if (var4 == null) {
               break label55;
            }
         } else if (man.equals(var4)) {
            break label55;
         }

         label56: {
            Object var5 = .MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.Float());
            if (man == null) {
               if (var5 == null) {
                  break label56;
               }
            } else if (man.equals(var5)) {
               break label56;
            }

            label57: {
               Object var6 = .MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.Int());
               if (man == null) {
                  if (var6 == null) {
                     break label57;
                  }
               } else if (man.equals(var6)) {
                  break label57;
               }

               var10000 = this.apply(values.toArray(evidence$8));
               return var10000;
            }

            var10000 = this.apply$mIc$sp((int[])values.toArray(evidence$8));
            return var10000;
         }

         var10000 = this.apply$mFc$sp((float[])values.toArray(evidence$8));
         return var10000;
      }

      var10000 = this.apply$mDc$sp((double[])values.toArray(evidence$8));
      return var10000;
   }

   // $FF: synthetic method
   static CanCreateZeros canCreateZeros$(final VectorConstructors $this, final ClassTag evidence$9, final Zero evidence$10) {
      return $this.canCreateZeros(evidence$9, evidence$10);
   }

   default CanCreateZeros canCreateZeros(final ClassTag evidence$9, final Zero evidence$10) {
      return new CanCreateZeros(evidence$9, evidence$10) {
         // $FF: synthetic field
         private final VectorConstructors $outer;
         private final ClassTag evidence$9$1;
         private final Zero evidence$10$1;

         public Vector apply(final int d) {
            return this.$outer.zeros(d, this.evidence$9$1, this.evidence$10$1);
         }

         public {
            if (VectorConstructors.this == null) {
               throw null;
            } else {
               this.$outer = VectorConstructors.this;
               this.evidence$9$1 = evidence$9$1;
               this.evidence$10$1 = evidence$10$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static Vector rand$(final VectorConstructors $this, final int size, final Rand rand, final ClassTag evidence$11) {
      return $this.rand(size, rand, evidence$11);
   }

   default Vector rand(final int size, final Rand rand, final ClassTag evidence$11) {
      Object arr = evidence$11.newArray(size);

      for(int i = 0; i < scala.runtime.ScalaRunTime..MODULE$.array_length(arr); ++i) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(arr, i, rand.draw());
      }

      return this.apply(arr);
   }

   // $FF: synthetic method
   static Rand rand$default$2$(final VectorConstructors $this) {
      return $this.rand$default$2();
   }

   default Rand rand$default$2() {
      return Rand$.MODULE$.uniform();
   }

   // $FF: synthetic method
   static Vector range$(final VectorConstructors $this, final int start, final int end) {
      return $this.range(start, end);
   }

   default Vector range(final int start, final int end) {
      return this.range(start, end, 1);
   }

   // $FF: synthetic method
   static Vector range$(final VectorConstructors $this, final int start, final int end, final int step) {
      return $this.range(start, end, step);
   }

   default Vector range(final int start, final int end, final int step) {
      return this.apply(scala.Array..MODULE$.range(start, end, step));
   }

   // $FF: synthetic method
   static Vector rangeF$(final VectorConstructors $this, final float start, final float end, final float step) {
      return $this.rangeF(start, end, step);
   }

   default Vector rangeF(final float start, final float end, final float step) {
      .MODULE$.require(end > start);
      .MODULE$.require(end - start > step);
      int size = (int)scala.math.package..MODULE$.ceil((double)((end - start) / step));
      if (size > 0 && start + step * (float)(size - 1) >= end) {
         --size;
      }

      float[] data = new float[size];
      int index$macro$2 = 0;

      for(int limit$macro$4 = size; index$macro$2 < limit$macro$4; ++index$macro$2) {
         data[index$macro$2] = start + (float)index$macro$2 * step;
      }

      return this.apply$mFc$sp(data);
   }

   // $FF: synthetic method
   static float rangeF$default$3$(final VectorConstructors $this) {
      return $this.rangeF$default$3();
   }

   default float rangeF$default$3() {
      return 1.0F;
   }

   // $FF: synthetic method
   static Vector rangeD$(final VectorConstructors $this, final double start, final double end, final double step) {
      return $this.rangeD(start, end, step);
   }

   default Vector rangeD(final double start, final double end, final double step) {
      .MODULE$.require(end > start);
      .MODULE$.require(end - start > step);
      int size = (int)scala.math.package..MODULE$.ceil((end - start) / step);
      if (size > 0 && start + step * (double)(size - 1) >= end) {
         --size;
      }

      double[] data = new double[size];
      int index$macro$2 = 0;

      for(int limit$macro$4 = size; index$macro$2 < limit$macro$4; ++index$macro$2) {
         data[index$macro$2] = start + (double)index$macro$2 * step;
      }

      return this.apply$mDc$sp(data);
   }

   // $FF: synthetic method
   static double rangeD$default$3$(final VectorConstructors $this) {
      return $this.rangeD$default$3();
   }

   default double rangeD$default$3() {
      return (double)1.0F;
   }

   // $FF: synthetic method
   static Vector apply$mDc$sp$(final VectorConstructors $this, final double[] values) {
      return $this.apply$mDc$sp(values);
   }

   default Vector apply$mDc$sp(final double[] values) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Vector apply$mFc$sp$(final VectorConstructors $this, final float[] values) {
      return $this.apply$mFc$sp(values);
   }

   default Vector apply$mFc$sp(final float[] values) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Vector apply$mIc$sp$(final VectorConstructors $this, final int[] values) {
      return $this.apply$mIc$sp(values);
   }

   default Vector apply$mIc$sp(final int[] values) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   // $FF: synthetic method
   static Vector apply$mJc$sp$(final VectorConstructors $this, final long[] values) {
      return $this.apply$mJc$sp(values);
   }

   default Vector apply$mJc$sp(final long[] values) {
      throw new RuntimeException("Fatal error in code generation: this should never be called.");
   }

   static void $init$(final VectorConstructors $this) {
   }
}
