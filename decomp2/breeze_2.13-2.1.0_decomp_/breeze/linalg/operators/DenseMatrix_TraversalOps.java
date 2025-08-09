package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTransformValues;
import breeze.linalg.support.CanTransformValues$mcD$sp;
import breeze.linalg.support.CanTransformValues$mcF$sp;
import breeze.linalg.support.CanTransformValues$mcI$sp;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055ca\u0002\u0005\n!\u0003\r\t\u0001\u0005\u0005\u00067\u0001!\t\u0001\b\u0005\u0006A\u0001!\u0019!\t\u0005\u0006q\u0001!\u0019!\u000f\u0005\u0006\u000f\u0002!\u0019\u0001\u0013\u0005\u0006Q\u0002!\u0019!\u001b\u0005\u0006{\u0002!\u0019A \u0005\b\u0003k\u0001A1AA\u001c\u0005a!UM\\:f\u001b\u0006$(/\u001b=`)J\fg/\u001a:tC2|\u0005o\u001d\u0006\u0003\u0015-\t\u0011b\u001c9fe\u0006$xN]:\u000b\u00051i\u0011A\u00027j]\u0006dwMC\u0001\u000f\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\u0012/A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u0004\"\u0001G\r\u000e\u0003%I!AG\u0005\u0003\u001bQ+gn]8s\u0019><\bK]5p\u0003\u0019!\u0013N\\5uIQ\tQ\u0004\u0005\u0002\u0013=%\u0011qd\u0005\u0002\u0005+:LG/A\tdC:$&/\u0019<feN,g+\u00197vKN,\"AI\u0018\u0016\u0003\r\u0002B\u0001J\u0014*[5\tQE\u0003\u0002'\u0017\u000591/\u001e9q_J$\u0018B\u0001\u0015&\u0005E\u0019\u0015M\u001c+sCZ,'o]3WC2,Xm\u001d\t\u0004U-jS\"A\u0006\n\u00051Z!a\u0003#f]N,W*\u0019;sSb\u0004\"AL\u0018\r\u0001\u0011)\u0001G\u0001b\u0001c\t\ta+\u0005\u00023kA\u0011!cM\u0005\u0003iM\u0011qAT8uQ&tw\r\u0005\u0002\u0013m%\u0011qg\u0005\u0002\u0004\u0003:L\u0018aG2b]R\u0013\u0018M^3sg\u0016\\U-\u001f,bYV,\u0007+Y5sg~#U*\u0006\u0002;\u0001V\t1\bE\u0003%yy\nu(\u0003\u0002>K\tA2)\u00198Ue\u00064XM]:f\u0017\u0016Lh+\u00197vKB\u000b\u0017N]:\u0011\u0007)Zs\b\u0005\u0002/\u0001\u0012)\u0001g\u0001b\u0001cA!!C\u0011#E\u0013\t\u00195C\u0001\u0004UkBdWM\r\t\u0003%\u0015K!AR\n\u0003\u0007%sG/A\u000bdC:$&/\u00198tM>\u0014XNV1mk\u0016\u001cx\fR'\u0016\u0005%{U#\u0001&\u0011\t\u0011ZUJT\u0005\u0003\u0019\u0016\u0012!cQ1o)J\fgn\u001d4pe64\u0016\r\\;fgB\u0019!f\u000b(\u0011\u00059zE!\u0003\u0019\u0005A\u0003\u0005\tQ1\u00012Q\u0015y\u0015\u000b\u00160d!\t\u0011\"+\u0003\u0002T'\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019SK\u0016-X\u001d\t\u0011b+\u0003\u0002X'\u0005\u0019\u0011J\u001c;2\t\u0011JV\f\u0006\b\u00035vk\u0011a\u0017\u0006\u00039>\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b2\u000b\rz\u0006MY1\u000f\u0005I\u0001\u0017BA1\u0014\u0003\u00151En\\1uc\u0011!\u0013,\u0018\u000b2\u000b\r\"Wm\u001a4\u000f\u0005I)\u0017B\u00014\u0014\u0003\u0019!u.\u001e2mKF\"A%W/\u0015\u0003Y\u0019\u0017M\\'ba.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t?\u0012kUc\u00016qeR\u00111.\u001e\t\bI1t\u0017i\\9u\u0013\tiWEA\nDC:l\u0015\r]&fsZ\u000bG.^3QC&\u00148\u000fE\u0002+W=\u0004\"A\f9\u0005\u000bA*!\u0019A\u0019\u0011\u00059\u0012H!B:\u0006\u0005\u0004\t$!\u0001*\u0011\u0007)Z\u0013\u000fC\u0004w\u000b\u0005\u0005\t9A<\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007E\u0002ywFl\u0011!\u001f\u0006\u0003uN\tqA]3gY\u0016\u001cG/\u0003\u0002}s\nA1\t\\1tgR\u000bw-A\bdC:l\u0015\r\u001d,bYV,7o\u0018#N+\u0015y\u00181BA\u000f)\u0011\t\t!a\f\u0011\u0017\u0011\n\u0019!a\u0002\u0002\n\u0005m\u0011QF\u0005\u0004\u0003\u000b)#\u0001D\"b]6\u000b\u0007OV1mk\u0016\u001c\b\u0003\u0002\u0016,\u0003\u0013\u00012ALA\u0006\t%\u0001d\u0001)A\u0001\u0002\u000b\u0007\u0011\u0007K\u0005\u0002\fE\u000by!a\u0005\u0002\u0018E21%\u0016,\u0002\u0012]\u000bD\u0001J-^)E21e\u00181\u0002\u0016\u0005\fD\u0001J-^)E21\u0005Z3\u0002\u001a\u0019\fD\u0001J-^)A\u0019a&!\b\u0005\u0013M4\u0001\u0015!A\u0001\u0006\u0004\t\u0004&CA\u000f#\u0006\u0005\u0012QEA\u0015c\u0019\u0019SKVA\u0012/F\"A%W/\u0015c\u0019\u0019s\fYA\u0014CF\"A%W/\u0015c\u0019\u0019C-ZA\u0016MF\"A%W/\u0015!\u0011Q3&a\u0007\t\u0013\u0005Eb!!AA\u0004\u0005M\u0012AC3wS\u0012,gnY3%oA!\u0001p_A\u000e\u0003)\u0019\u0017M\\\"paf|F)T\u000b\u0005\u0003s\t)\u0005\u0006\u0003\u0002<\u0005\u001d\u0003#\u0002\u0013\u0002>\u0005\u0005\u0013bAA K\t91)\u00198D_BL\b\u0003\u0002\u0016,\u0003\u0007\u00022ALA#\t\u0015\u0001tA1\u00012\u0011%\tIeBA\u0001\u0002\b\tY%\u0001\u0006fm&$WM\\2fIa\u0002B\u0001_>\u0002D\u0001"
)
public interface DenseMatrix_TraversalOps extends TensorLowPrio {
   // $FF: synthetic method
   static CanTraverseValues canTraverseValues$(final DenseMatrix_TraversalOps $this) {
      return $this.canTraverseValues();
   }

   default CanTraverseValues canTraverseValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final DenseMatrix from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final DenseMatrix from, final CanTraverseValues.ValuesVisitor fn) {
            int idealMajorStride = from.isTranspose() ? from.cols() : from.rows();
            if (from.majorStride() == idealMajorStride) {
               fn.visitArray(from.data(), from.offset(), from.rows() * from.cols(), 1);
            } else {
               int index$macro$2 = 0;

               for(int limit$macro$4 = from.majorSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  fn.visitArray(from.data(), from.offset() + index$macro$2 * from.majorStride(), from.minorSize(), 1);
               }
            }

            return fn;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanTraverseKeyValuePairs canTraverseKeyValuePairs_DM$(final DenseMatrix_TraversalOps $this) {
      return $this.canTraverseKeyValuePairs_DM();
   }

   default CanTraverseKeyValuePairs canTraverseKeyValuePairs_DM() {
      return new CanTraverseKeyValuePairs() {
         public boolean isTraversableAgain(final DenseMatrix from) {
            return true;
         }

         public void traverse(final DenseMatrix from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            int idealMajorStride = from.isTranspose() ? from.cols() : from.rows();
            if (from.majorStride() == idealMajorStride) {
               fn.visitArray((index) -> $anonfun$traverse$4(from, BoxesRunTime.unboxToInt(index)), from.data(), from.offset(), from.rows() * from.cols(), 1);
            } else if (!from.isTranspose()) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = from.cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  fn.visitArray((index) -> $anonfun$traverse$5(from, BoxesRunTime.unboxToInt(index)), from.data(), from.offset() + index$macro$2 * from.majorStride(), from.rows(), 1);
               }
            } else {
               int index$macro$12 = 0;

               for(int limit$macro$14 = from.rows(); index$macro$12 < limit$macro$14; ++index$macro$12) {
                  int index$macro$7 = 0;

                  for(int limit$macro$9 = from.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                     ((i, j) -> fn.visit(new Tuple2.mcII.sp(i, j), from.apply(i, j))).apply$mcVII$sp(index$macro$12, index$macro$7);
                  }
               }
            }

         }

         // $FF: synthetic method
         public static final Tuple2 $anonfun$traverse$4(final DenseMatrix from$5, final int index) {
            return from$5.rowColumnFromLinearIndex(index);
         }

         // $FF: synthetic method
         public static final Tuple2 $anonfun$traverse$5(final DenseMatrix from$5, final int index) {
            return from$5.rowColumnFromLinearIndex(index);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanTransformValues canTransformValues_DM$(final DenseMatrix_TraversalOps $this) {
      return $this.canTransformValues_DM();
   }

   default CanTransformValues canTransformValues_DM() {
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

         public void transform(final DenseMatrix from, final Function1 fn) {
            if (from.isContiguous()) {
               Object d = from.data();
               int index$macro$2 = from.offset();

               for(int limit$macro$4 = from.offset() + from.size(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  .MODULE$.array_update(d, index$macro$2, fn.apply(.MODULE$.array_apply(d, index$macro$2)));
               }
            } else {
               this.slowPath(from, fn);
            }

         }

         private void slowPath(final DenseMatrix from, final Function1 fn) {
            int off = from.offset();
            Object d = from.data();
            int index$macro$7 = 0;

            for(int limit$macro$9 = from.majorSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = off;

               for(int limit$macro$4 = off + from.minorSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  .MODULE$.array_update(d, index$macro$2, fn.apply(.MODULE$.array_apply(d, index$macro$2)));
               }

               off += from.majorStride();
            }

         }

         public void transformActive(final DenseMatrix from, final Function1 fn) {
            this.transform(from, fn);
         }
      };
   }

   // $FF: synthetic method
   static CanMapKeyValuePairs canMapKeyValuePairs_DM$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$6) {
      return $this.canMapKeyValuePairs_DM(evidence$6);
   }

   default CanMapKeyValuePairs canMapKeyValuePairs_DM(final ClassTag evidence$6) {
      return new CanMapKeyValuePairs(evidence$6) {
         private final ClassTag evidence$6$1;

         public DenseMatrix map(final DenseMatrix from, final Function2 fn) {
            Object data = this.evidence$6$1.newArray(.MODULE$.array_length(from.data()));
            int off = 0;
            int index$macro$7 = 0;

            for(int limit$macro$9 = from.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = from.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  .MODULE$.array_update(data, off, fn.apply(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(index$macro$2)), BoxesRunTime.boxToInteger(index$macro$7)), from.apply(index$macro$2, index$macro$7)));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix mapActive(final DenseMatrix from, final Function2 fn) {
            return this.map(from, fn);
         }

         public {
            this.evidence$6$1 = evidence$6$1;
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM(evidence$7);
   }

   default CanMapValues canMapValues_DM(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$1;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.mapContiguous(from, fn) : this.mapGeneral(from, fn);
         }

         private DenseMatrix mapGeneral(final DenseMatrix from, final Function1 fn) {
            Object data = this.evidence$7$1.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  .MODULE$.array_update(data, off, fn.apply(from.apply(i, j)));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         private DenseMatrix mapContiguous(final DenseMatrix from, final Function1 fn) {
            Object data = this.evidence$7$1.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            Object fd = from.data();
            int index$macro$2 = 0;

            for(int limit$macro$4 = .MODULE$.array_length(data); index$macro$2 < limit$macro$4; ++index$macro$2) {
               .MODULE$.array_update(data, index$macro$2, fn.apply(.MODULE$.array_apply(fd, index$macro$2 + off)));
            }

            return DenseMatrix$.MODULE$.create(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public {
            this.evidence$7$1 = evidence$7$1;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanCopy canCopy_DM$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$8) {
      return $this.canCopy_DM(evidence$8);
   }

   default CanCopy canCopy_DM(final ClassTag evidence$8) {
      return (v1) -> v1.copy();
   }

   // $FF: synthetic method
   static CanTransformValues canTransformValues_DM$mDc$sp$(final DenseMatrix_TraversalOps $this) {
      return $this.canTransformValues_DM$mDc$sp();
   }

   default CanTransformValues canTransformValues_DM$mDc$sp() {
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

         public void transform(final DenseMatrix from, final Function1 fn) {
            this.transform$mcD$sp(from, fn);
         }

         public void breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$slowPath(final DenseMatrix from, final Function1 fn) {
            int off = from.offset();
            double[] d = from.data$mcD$sp();
            int index$macro$7 = 0;

            for(int limit$macro$9 = from.majorSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = off;

               for(int limit$macro$4 = off + from.minorSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  d[index$macro$2] = fn.apply$mcDD$sp(d[index$macro$2]);
               }

               off += from.majorStride();
            }

         }

         public void transformActive(final DenseMatrix from, final Function1 fn) {
            this.transformActive$mcD$sp(from, fn);
         }

         public void transform$mcD$sp(final DenseMatrix from, final Function1 fn) {
            if (from.isContiguous()) {
               double[] d = from.data$mcD$sp();
               int index$macro$2 = from.offset();

               for(int limit$macro$4 = from.offset() + from.size(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  d[index$macro$2] = fn.apply$mcDD$sp(d[index$macro$2]);
               }
            } else {
               this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$slowPath(from, fn);
            }

         }

         public void transformActive$mcD$sp(final DenseMatrix from, final Function1 fn) {
            this.transform$mcD$sp(from, fn);
         }
      };
   }

   // $FF: synthetic method
   static CanTransformValues canTransformValues_DM$mFc$sp$(final DenseMatrix_TraversalOps $this) {
      return $this.canTransformValues_DM$mFc$sp();
   }

   default CanTransformValues canTransformValues_DM$mFc$sp() {
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

         public void transform(final DenseMatrix from, final Function1 fn) {
            this.transform$mcF$sp(from, fn);
         }

         public void breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$slowPath(final DenseMatrix from, final Function1 fn) {
            int off = from.offset();
            float[] d = from.data$mcF$sp();
            int index$macro$7 = 0;

            for(int limit$macro$9 = from.majorSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = off;

               for(int limit$macro$4 = off + from.minorSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  d[index$macro$2] = fn.apply$mcFF$sp(d[index$macro$2]);
               }

               off += from.majorStride();
            }

         }

         public void transformActive(final DenseMatrix from, final Function1 fn) {
            this.transformActive$mcF$sp(from, fn);
         }

         public void transform$mcF$sp(final DenseMatrix from, final Function1 fn) {
            if (from.isContiguous()) {
               float[] d = from.data$mcF$sp();
               int index$macro$2 = from.offset();

               for(int limit$macro$4 = from.offset() + from.size(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  d[index$macro$2] = fn.apply$mcFF$sp(d[index$macro$2]);
               }
            } else {
               this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$slowPath(from, fn);
            }

         }

         public void transformActive$mcF$sp(final DenseMatrix from, final Function1 fn) {
            this.transform$mcF$sp(from, fn);
         }
      };
   }

   // $FF: synthetic method
   static CanTransformValues canTransformValues_DM$mIc$sp$(final DenseMatrix_TraversalOps $this) {
      return $this.canTransformValues_DM$mIc$sp();
   }

   default CanTransformValues canTransformValues_DM$mIc$sp() {
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

         public void transform(final DenseMatrix from, final Function1 fn) {
            this.transform$mcI$sp(from, fn);
         }

         public void breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$slowPath(final DenseMatrix from, final Function1 fn) {
            int off = from.offset();
            int[] d = from.data$mcI$sp();
            int index$macro$7 = 0;

            for(int limit$macro$9 = from.majorSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = off;

               for(int limit$macro$4 = off + from.minorSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  d[index$macro$2] = fn.apply$mcII$sp(d[index$macro$2]);
               }

               off += from.majorStride();
            }

         }

         public void transformActive(final DenseMatrix from, final Function1 fn) {
            this.transformActive$mcI$sp(from, fn);
         }

         public void transform$mcI$sp(final DenseMatrix from, final Function1 fn) {
            if (from.isContiguous()) {
               int[] d = from.data$mcI$sp();
               int index$macro$2 = from.offset();

               for(int limit$macro$4 = from.offset() + from.size(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  d[index$macro$2] = fn.apply$mcII$sp(d[index$macro$2]);
               }
            } else {
               this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$slowPath(from, fn);
            }

         }

         public void transformActive$mcI$sp(final DenseMatrix from, final Function1 fn) {
            this.transform$mcI$sp(from, fn);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mDDc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mDDc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mDDc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$2;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcDD$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            double[] data = (double[])this.evidence$7$2.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcDD$sp(from.apply$mcD$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mDc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            double[] data = (double[])this.evidence$7$2.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            double[] fd = from.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcDD$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mDc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcDD$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$2 = evidence$7$2;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mFDc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mFDc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mFDc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$3;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcDF$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            float[] data = (float[])this.evidence$7$3.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcFD$sp(from.apply$mcD$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mFc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            float[] data = (float[])this.evidence$7$3.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            double[] fd = from.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcFD$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mFc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcDF$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$3 = evidence$7$3;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mIDc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mIDc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mIDc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$4;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcDI$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            int[] data = (int[])this.evidence$7$4.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcID$sp(from.apply$mcD$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mIc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            int[] data = (int[])this.evidence$7$4.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            double[] fd = from.data$mcD$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcID$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mIc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcDI$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$4 = evidence$7$4;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mDFc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mDFc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mDFc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$5;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcFD$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            double[] data = (double[])this.evidence$7$5.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcDF$sp(from.apply$mcF$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mDc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            double[] data = (double[])this.evidence$7$5.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            float[] fd = from.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcDF$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mDc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcFD$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$5 = evidence$7$5;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mFFc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mFFc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mFFc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$6;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcFF$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            float[] data = (float[])this.evidence$7$6.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcFF$sp(from.apply$mcF$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mFc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            float[] data = (float[])this.evidence$7$6.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            float[] fd = from.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcFF$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mFc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcFF$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$6 = evidence$7$6;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mIFc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mIFc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mIFc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$7;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcFI$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            int[] data = (int[])this.evidence$7$7.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcIF$sp(from.apply$mcF$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mIc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            int[] data = (int[])this.evidence$7$7.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            float[] fd = from.data$mcF$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcIF$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mIc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcFI$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$7 = evidence$7$7;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mDIc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mDIc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mDIc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$8;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcID$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            double[] data = (double[])this.evidence$7$8.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcDI$sp(from.apply$mcI$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mDc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            double[] data = (double[])this.evidence$7$8.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            int[] fd = from.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcDI$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mDc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcID$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$8 = evidence$7$8;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mFIc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mFIc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mFIc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$9;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcII$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcIF$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            float[] data = (float[])this.evidence$7$9.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcFI$sp(from.apply$mcI$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mFc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            float[] data = (float[])this.evidence$7$9.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            int[] fd = from.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcFI$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mFc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcIF$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$9 = evidence$7$9;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues canMapValues_DM$mIIc$sp$(final DenseMatrix_TraversalOps $this, final ClassTag evidence$7) {
      return $this.canMapValues_DM$mIIc$sp(evidence$7);
   }

   default CanMapValues canMapValues_DM$mIIc$sp(final ClassTag evidence$7) {
      return new CanMapValues.DenseCanMapValues(evidence$7) {
         private final ClassTag evidence$7$10;

         public final Object mapActive(final Object from, final Function1 fn) {
            return CanMapValues.DenseCanMapValues.mapActive$(this, from, fn);
         }

         public Object map$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDD$sp$(this, from, fn);
         }

         public Object map$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDF$sp$(this, from, fn);
         }

         public Object map$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDI$sp$(this, from, fn);
         }

         public Object map$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcDJ$sp$(this, from, fn);
         }

         public Object map$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFD$sp$(this, from, fn);
         }

         public Object map$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFF$sp$(this, from, fn);
         }

         public Object map$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFI$sp$(this, from, fn);
         }

         public Object map$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcFJ$sp$(this, from, fn);
         }

         public Object map$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcID$sp$(this, from, fn);
         }

         public Object map$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIF$sp$(this, from, fn);
         }

         public Object map$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcIJ$sp$(this, from, fn);
         }

         public Object map$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJD$sp$(this, from, fn);
         }

         public Object map$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJF$sp$(this, from, fn);
         }

         public Object map$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJI$sp$(this, from, fn);
         }

         public Object map$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.map$mcJJ$sp$(this, from, fn);
         }

         public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
         }

         public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
         }

         public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
         }

         public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
         }

         public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
         }

         public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
         }

         public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
         }

         public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
         }

         public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcID$sp$(this, from, fn);
         }

         public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
         }

         public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcII$sp$(this, from, fn);
         }

         public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
         }

         public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
         }

         public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
         }

         public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
         }

         public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
            return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
         }

         public DenseMatrix map(final DenseMatrix from, final Function1 fn) {
            return this.map$mcII$sp(from, fn);
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(final DenseMatrix from, final Function1 fn) {
            int[] data = (int[])this.evidence$7$10.newArray(from.size());
            int j = 0;

            for(int off = 0; j < from.cols(); ++j) {
               for(int i = 0; i < from.rows(); ++i) {
                  data[off] = fn.apply$mcII$sp(from.apply$mcI$sp(i, j));
                  ++off;
               }
            }

            return DenseMatrix$.MODULE$.create$mIc$sp(from.rows(), from.cols(), data, 0, from.rows(), DenseMatrix$.MODULE$.create$default$6());
         }

         public DenseMatrix breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(final DenseMatrix from, final Function1 fn) {
            int[] data = (int[])this.evidence$7$10.newArray(from.size());
            boolean isTranspose = from.isTranspose();
            int off = from.offset();
            int[] fd = from.data$mcI$sp();
            int index$macro$2 = 0;

            for(int limit$macro$4 = data.length; index$macro$2 < limit$macro$4; ++index$macro$2) {
               data[index$macro$2] = fn.apply$mcII$sp(fd[index$macro$2 + off]);
            }

            return DenseMatrix$.MODULE$.create$mIc$sp(from.rows(), from.cols(), data, 0, isTranspose ? from.cols() : from.rows(), isTranspose);
         }

         public DenseMatrix map$mcII$sp(final DenseMatrix from, final Function1 fn) {
            return from.isContiguous() ? this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapContiguous(from, fn) : this.breeze$linalg$operators$DenseMatrix_TraversalOps$$anon$$mapGeneral(from, fn);
         }

         public {
            this.evidence$7$10 = evidence$7$10;
            CanMapValues.DenseCanMapValues.$init$(this);
         }
      };
   }

   static void $init$(final DenseMatrix_TraversalOps $this) {
   }
}
