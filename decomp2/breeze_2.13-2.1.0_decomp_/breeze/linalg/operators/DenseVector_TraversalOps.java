package breeze.linalg.operators;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTransformValues$mcD$sp;
import breeze.linalg.support.CanTransformValues$mcF$sp;
import breeze.linalg.support.CanTransformValues$mcI$sp;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipAndTraverseValues;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055aaB\u0004\t!\u0003\r\ta\u0004\u0005\u00065\u0001!\ta\u0007\u0005\u0006?\u0001!\u0019\u0001\t\u0005\u0006o\u0001!\u0019\u0001\u000f\u0005\u0006\t\u0002!\u0019!\u0012\u0005\u0006!\u0002!\u0019!\u0015\u0005\u0006c\u0002!\u0019A\u001d\u0002\u0019\t\u0016t7/\u001a,fGR|'o\u0018+sCZ,'o]1m\u001fB\u001c(BA\u0005\u000b\u0003%y\u0007/\u001a:bi>\u00148O\u0003\u0002\f\u0019\u00051A.\u001b8bY\u001eT\u0011!D\u0001\u0007EJ,WM_3\u0004\u0001M\u0019\u0001\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\t9\u0002$D\u0001\t\u0013\tI\u0002BA\nWK\u000e$xN]0Ue\u00064XM]:bY>\u00038/\u0001\u0004%S:LG\u000f\n\u000b\u00029A\u0011\u0011#H\u0005\u0003=I\u0011A!\u00168ji\u0006\u0019BIV0dC:LE/\u001a:bi\u00164\u0016\r\\;fgV\u0011\u0011EL\u000b\u0002EA!1E\n\u0015-\u001b\u0005!#BA\u0013\u000b\u0003\u001d\u0019X\u000f\u001d9peRL!a\n\u0013\u0003#\r\u000bg\u000e\u0016:bm\u0016\u00148/\u001a,bYV,7\u000fE\u0002*U1j\u0011AC\u0005\u0003W)\u00111\u0002R3og\u00164Vm\u0019;peB\u0011QF\f\u0007\u0001\t\u0015y#A1\u00011\u0005\u00051\u0016CA\u00195!\t\t\"'\u0003\u00024%\t9aj\u001c;iS:<\u0007CA\t6\u0013\t1$CA\u0002B]f\fq\u0003\u0012,`G\u0006tGK]1wKJ\u001cXMW5q-\u0006dW/Z:\u0016\u0007ez$)F\u0001;!\u0019\u00193(\u0010!?\u0003&\u0011A\b\n\u0002\u0018\u0007\u0006t',\u001b9B]\u0012$&/\u0019<feN,g+\u00197vKN\u00042!\u000b\u0016?!\tis\bB\u00030\u0007\t\u0007\u0001\u0007E\u0002*U\u0005\u0003\"!\f\"\u0005\u000b\r\u001b!\u0019\u0001\u0019\u0003\u0003]\u000b1\u0004\u0012,`G\u0006tGK]1wKJ\u001cXmS3z-\u0006dW/\u001a)bSJ\u001cXC\u0001$M+\u00059\u0005#B\u0012I\u00156[\u0015BA%%\u0005a\u0019\u0015M\u001c+sCZ,'o]3LKf4\u0016\r\\;f!\u0006L'o\u001d\t\u0004S)Z\u0005CA\u0017M\t\u0015yCA1\u00011!\t\tb*\u0003\u0002P%\t\u0019\u0011J\u001c;\u0002+\u00113vlY1o)J\fgn\u001d4pe64\u0016\r\\;fgV\u0011!\u000bW\u000b\u0002'B!1\u0005\u0016,X\u0013\t)FE\u0001\nDC:$&/\u00198tM>\u0014XNV1mk\u0016\u001c\bcA\u0015+/B\u0011Q\u0006\u0017\u0003\n_\u0015\u0001\u000b\u0011!AC\u0002ABS\u0001\u0017.^O2\u0004\"!E.\n\u0005q\u0013\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t0`C\u0002t!!E0\n\u0005\u0001\u0014\u0012aA%oiF\"AE\u00194\u0014\u001d\t\u0019g-D\u0001e\u0015\t)g\"\u0001\u0004=e>|GOP\u0005\u0002'E*1\u0005[5lU:\u0011\u0011#[\u0005\u0003UJ\tQA\u00127pCR\fD\u0001\n2g'E*1%\u001c8q_:\u0011\u0011C\\\u0005\u0003_J\ta\u0001R8vE2,\u0017\u0007\u0002\u0013cMN\t1bY1o\u001b\u0006\u0004\b+Y5sgV\u00191/_>\u0015\u0005Qt\bcB\u0012vo6C(0`\u0005\u0003m\u0012\u00121cQ1o\u001b\u0006\u00048*Z=WC2,X\rU1jeN\u00042!\u000b\u0016y!\ti\u0013\u0010B\u00030\r\t\u0007\u0001\u0007\u0005\u0002.w\u0012)AP\u0002b\u0001a\t\u0011aK\r\t\u0004S)R\bBB@\u0007\u0001\b\t\t!A\u0002nC:\u0004R!a\u0001\u0002\nil!!!\u0002\u000b\u0007\u0005\u001d!#A\u0004sK\u001adWm\u0019;\n\t\u0005-\u0011Q\u0001\u0002\t\u00072\f7o\u001d+bO\u0002"
)
public interface DenseVector_TraversalOps extends Vector_TraversalOps {
   // $FF: synthetic method
   static CanTraverseValues DV_canIterateValues$(final DenseVector_TraversalOps $this) {
      return $this.DV_canIterateValues();
   }

   default CanTraverseValues DV_canIterateValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final DenseVector from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final DenseVector from, final CanTraverseValues.ValuesVisitor fn) {
            fn.visitArray(from.data(), from.offset(), from.length(), from.stride());
            return fn;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanZipAndTraverseValues DV_canTraverseZipValues$(final DenseVector_TraversalOps $this) {
      return $this.DV_canTraverseZipValues();
   }

   default CanZipAndTraverseValues DV_canTraverseZipValues() {
      return new CanZipAndTraverseValues() {
         public void traverse(final DenseVector from1, final DenseVector from2, final CanZipAndTraverseValues.PairValuesVisitor fn) {
            if (from1.size() != from2.size()) {
               throw new IllegalArgumentException("Vectors to be zipped must have same size");
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = from1.size(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  fn.visit(from1.apply(index$macro$2), from2.apply(index$macro$2));
               }

            }
         }
      };
   }

   // $FF: synthetic method
   static CanTraverseKeyValuePairs DV_canTraverseKeyValuePairs$(final DenseVector_TraversalOps $this) {
      return $this.DV_canTraverseKeyValuePairs();
   }

   default CanTraverseKeyValuePairs DV_canTraverseKeyValuePairs() {
      return new CanTraverseKeyValuePairs() {
         public boolean isTraversableAgain(final DenseVector from) {
            return true;
         }

         public void traverse(final DenseVector from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            fn.visitArray((JFunction1.mcII.sp)(ind) -> (ind - from.offset()) / from.stride(), from.data(), from.offset(), from.length(), from.stride());
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanTransformValues DV_canTransformValues$(final DenseVector_TraversalOps $this) {
      return $this.DV_canTransformValues();
   }

   default CanTransformValues DV_canTransformValues() {
      return new CanTransformValues() {
         public void transform$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcD$sp$(this, from, fn);
         }

         public void transform$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcF$sp$(this, from, fn);
         }

         public void transform$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcI$sp$(this, from, fn);
         }

         public void transformActive$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcD$sp$(this, from, fn);
         }

         public void transformActive$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcF$sp$(this, from, fn);
         }

         public void transformActive$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcI$sp$(this, from, fn);
         }

         public void transform(final DenseVector from, final Function1 fn) {
            Object data = from.data();
            int length = from.length();
            int stride = from.stride();
            int offset = from.offset();
            if (stride == 1) {
               int index$macro$2 = offset;

               for(int limit$macro$4 = offset + length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  .MODULE$.array_update(data, index$macro$2, fn.apply(.MODULE$.array_apply(data, index$macro$2)));
               }
            } else {
               this.slowPath(fn, data, length, stride, offset);
            }

         }

         private void slowPath(final Function1 fn, final Object data, final int length, final int stride, final int offset) {
            int end = offset + stride * length;

            for(int j = offset; j != end; j += stride) {
               .MODULE$.array_update(data, j, fn.apply(.MODULE$.array_apply(data, j)));
            }

         }

         public void transformActive(final DenseVector from, final Function1 fn) {
            this.transform(from, fn);
         }
      };
   }

   // $FF: synthetic method
   static CanMapKeyValuePairs canMapPairs$(final DenseVector_TraversalOps $this, final ClassTag man) {
      return $this.canMapPairs(man);
   }

   default CanMapKeyValuePairs canMapPairs(final ClassTag man) {
      return new CanMapKeyValuePairs(man) {
         private final ClassTag man$2;

         public DenseVector map(final DenseVector from, final Function2 fn) {
            Object arr = this.man$2.newArray(from.length());
            Object d = from.data();
            int stride = from.stride();
            int i = 0;

            for(int j = from.offset(); i < .MODULE$.array_length(arr); j += stride) {
               .MODULE$.array_update(arr, i, fn.apply(BoxesRunTime.boxToInteger(i), .MODULE$.array_apply(d, j)));
               ++i;
            }

            return DenseVector$.MODULE$.apply(arr);
         }

         public DenseVector mapActive(final DenseVector from, final Function2 fn) {
            return this.map(from, fn);
         }

         public {
            this.man$2 = man$2;
         }
      };
   }

   // $FF: synthetic method
   static CanTransformValues DV_canTransformValues$mDc$sp$(final DenseVector_TraversalOps $this) {
      return $this.DV_canTransformValues$mDc$sp();
   }

   default CanTransformValues DV_canTransformValues$mDc$sp() {
      return new CanTransformValues$mcD$sp() {
         public void transform$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcF$sp$(this, from, fn);
         }

         public void transform$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcI$sp$(this, from, fn);
         }

         public void transformActive$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcF$sp$(this, from, fn);
         }

         public void transformActive$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcI$sp$(this, from, fn);
         }

         public void transform(final DenseVector from, final Function1 fn) {
            this.transform$mcD$sp(from, fn);
         }

         public void breeze$linalg$operators$DenseVector_TraversalOps$$anon$$slowPath(final Function1 fn, final double[] data, final int length, final int stride, final int offset) {
            int end = offset + stride * length;

            for(int j = offset; j != end; j += stride) {
               data[j] = fn.apply$mcDD$sp(data[j]);
            }

         }

         public void transformActive(final DenseVector from, final Function1 fn) {
            this.transformActive$mcD$sp(from, fn);
         }

         public void transform$mcD$sp(final DenseVector from, final Function1 fn) {
            double[] data = from.data$mcD$sp();
            int length = from.length();
            int stride = from.stride();
            int offset = from.offset();
            if (stride == 1) {
               int index$macro$2 = offset;

               for(int limit$macro$4 = offset + length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  data[index$macro$2] = fn.apply$mcDD$sp(data[index$macro$2]);
               }
            } else {
               this.breeze$linalg$operators$DenseVector_TraversalOps$$anon$$slowPath(fn, data, length, stride, offset);
            }

         }

         public void transformActive$mcD$sp(final DenseVector from, final Function1 fn) {
            this.transform$mcD$sp(from, fn);
         }
      };
   }

   // $FF: synthetic method
   static CanTransformValues DV_canTransformValues$mFc$sp$(final DenseVector_TraversalOps $this) {
      return $this.DV_canTransformValues$mFc$sp();
   }

   default CanTransformValues DV_canTransformValues$mFc$sp() {
      return new CanTransformValues$mcF$sp() {
         public void transform$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcD$sp$(this, from, fn);
         }

         public void transform$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcI$sp$(this, from, fn);
         }

         public void transformActive$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcD$sp$(this, from, fn);
         }

         public void transformActive$mcI$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcI$sp$(this, from, fn);
         }

         public void transform(final DenseVector from, final Function1 fn) {
            this.transform$mcF$sp(from, fn);
         }

         public void breeze$linalg$operators$DenseVector_TraversalOps$$anon$$slowPath(final Function1 fn, final float[] data, final int length, final int stride, final int offset) {
            int end = offset + stride * length;

            for(int j = offset; j != end; j += stride) {
               data[j] = fn.apply$mcFF$sp(data[j]);
            }

         }

         public void transformActive(final DenseVector from, final Function1 fn) {
            this.transformActive$mcF$sp(from, fn);
         }

         public void transform$mcF$sp(final DenseVector from, final Function1 fn) {
            float[] data = from.data$mcF$sp();
            int length = from.length();
            int stride = from.stride();
            int offset = from.offset();
            if (stride == 1) {
               int index$macro$2 = offset;

               for(int limit$macro$4 = offset + length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  data[index$macro$2] = fn.apply$mcFF$sp(data[index$macro$2]);
               }
            } else {
               this.breeze$linalg$operators$DenseVector_TraversalOps$$anon$$slowPath(fn, data, length, stride, offset);
            }

         }

         public void transformActive$mcF$sp(final DenseVector from, final Function1 fn) {
            this.transform$mcF$sp(from, fn);
         }
      };
   }

   // $FF: synthetic method
   static CanTransformValues DV_canTransformValues$mIc$sp$(final DenseVector_TraversalOps $this) {
      return $this.DV_canTransformValues$mIc$sp();
   }

   default CanTransformValues DV_canTransformValues$mIc$sp() {
      return new CanTransformValues$mcI$sp() {
         public void transform$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcD$sp$(this, from, fn);
         }

         public void transform$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transform$mcF$sp$(this, from, fn);
         }

         public void transformActive$mcD$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcD$sp$(this, from, fn);
         }

         public void transformActive$mcF$sp(final Object from, final Function1 fn) {
            CanTransformValues.transformActive$mcF$sp$(this, from, fn);
         }

         public void transform(final DenseVector from, final Function1 fn) {
            this.transform$mcI$sp(from, fn);
         }

         public void breeze$linalg$operators$DenseVector_TraversalOps$$anon$$slowPath(final Function1 fn, final int[] data, final int length, final int stride, final int offset) {
            int end = offset + stride * length;

            for(int j = offset; j != end; j += stride) {
               data[j] = fn.apply$mcII$sp(data[j]);
            }

         }

         public void transformActive(final DenseVector from, final Function1 fn) {
            this.transformActive$mcI$sp(from, fn);
         }

         public void transform$mcI$sp(final DenseVector from, final Function1 fn) {
            int[] data = from.data$mcI$sp();
            int length = from.length();
            int stride = from.stride();
            int offset = from.offset();
            if (stride == 1) {
               int index$macro$2 = offset;

               for(int limit$macro$4 = offset + length; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  data[index$macro$2] = fn.apply$mcII$sp(data[index$macro$2]);
               }
            } else {
               this.breeze$linalg$operators$DenseVector_TraversalOps$$anon$$slowPath(fn, data, length, stride, offset);
            }

         }

         public void transformActive$mcI$sp(final DenseVector from, final Function1 fn) {
            this.transform$mcI$sp(from, fn);
         }
      };
   }

   static void $init$(final DenseVector_TraversalOps $this) {
   }
}
