package breeze.linalg.operators;

import breeze.linalg.CSCMatrix;
import breeze.linalg.CSCMatrix$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=faB\u0007\u000f!\u0003\r\t!\u0006\u0005\u0006G\u0001!\t\u0001\n\u0004\u0005Q\u0001\u0001\u0011\u0006\u0003\u0005^\u0005\t\r\t\u0015a\u0003_\u0011!!'AaA!\u0002\u0017)\u0007\"B6\u0003\t\u0003a\u0007\"\u0002:\u0003\t\u0003\u0019\b\"\u0002<\u0001\t\u00079\bbBA\f\u0001\u0011\r\u0011\u0011\u0004\u0005\b\u0003k\u0001A1AA\u001c\u0011\u001d\t)\u0007\u0001C\u0002\u0003OBq!!\u001f\u0001\t\u0007\tY\bC\u0004\u0002\f\u0002!\u0019!!$\u0003\u0019\r\u001b6)T1ue&Dx\n]:\u000b\u0005=\u0001\u0012!C8qKJ\fGo\u001c:t\u0015\t\t\"#\u0001\u0004mS:\fGn\u001a\u0006\u0002'\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0003\u0001-q\u0001\u0003CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g\r\u0005\u0002\u001e=5\ta\"\u0003\u0002 \u001d\t!2iU\"NCR\u0014\u0018\u000e_#ya\u0006tG-\u001a3PaN\u0004\"!H\u0011\n\u0005\tr!!E\"T\u00076\u000bGO]5y\u001fB\u001cxLU5oO\u00061A%\u001b8ji\u0012\"\u0012!\n\t\u0003/\u0019J!a\n\r\u0003\tUs\u0017\u000e\u001e\u0002\u0011\u0007\u0006t7i\u001c9z\u0007N\u001bU*\u0019;sSb,\"AK\u001c\u0014\u0007\t12\u0006E\u0002-_Ej\u0011!\f\u0006\u0003]A\tqa];qa>\u0014H/\u0003\u00021[\t91)\u00198D_BL\bc\u0001\u001a4k5\t\u0001#\u0003\u00025!\tI1iU\"NCR\u0014\u0018\u000e\u001f\t\u0003m]b\u0001\u0001B\u00059\u0005\u0001\u0006\t\u0011!b\u0001s\t\ta+\u0005\u0002;{A\u0011qcO\u0005\u0003ya\u0011qAT8uQ&tw\r\u0005\u0002\u0018}%\u0011q\b\u0007\u0002\u0004\u0003:L\bFB\u001cB\t:\u001b\u0006\f\u0005\u0002\u0018\u0005&\u00111\t\u0007\u0002\fgB,7-[1mSj,G-M\u0003$\u000b\u001aCuI\u0004\u0002\u0018\r&\u0011q\tG\u0001\u0007\t>,(\r\\32\t\u0011JU*\u0007\b\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019R\ta\u0001\u0010:p_Rt\u0014\"A\r2\u000b\rz\u0005KU)\u000f\u0005]\u0001\u0016BA)\u0019\u0003\rIe\u000e^\u0019\u0005I%k\u0015$M\u0003$)V;fK\u0004\u0002\u0018+&\u0011a\u000bG\u0001\u0006\r2|\u0017\r^\u0019\u0005I%k\u0015$M\u0003$3jc6L\u0004\u0002\u00185&\u00111\fG\u0001\u0005\u0019>tw-\r\u0003%\u00136K\u0012AC3wS\u0012,gnY3%cA\u0019qLY\u001b\u000e\u0003\u0001T!!\u0019\r\u0002\u000fI,g\r\\3di&\u00111\r\u0019\u0002\t\u00072\f7o\u001d+bO\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007\u0019LW'D\u0001h\u0015\tA'#A\u0004ti>\u0014\u0018mZ3\n\u0005)<'\u0001\u0002.fe>\fa\u0001P5oSRtD#A7\u0015\u00079\u0004\u0018\u000fE\u0002p\u0005Uj\u0011\u0001\u0001\u0005\u0006;\u0016\u0001\u001dA\u0018\u0005\u0006I\u0016\u0001\u001d!Z\u0001\u0006CB\u0004H.\u001f\u000b\u0003cQDQ!\u001e\u0004A\u0002E\n!A^\u0019\u0002\u0017\r\u001b6iX2b]\u000e{\u0007/_\u000b\u0003qn$R!_A\u0006\u0003#\u00012a\u001c\u0002{!\t14\u0010B\u00059\u000f\u0001\u0006\t\u0011!b\u0001s!B10Q?\u0000\u0003\u0007\t9!M\u0003$\u000b\u001asx)\r\u0003%\u00136K\u0012GB\u0012P!\u0006\u0005\u0011+\r\u0003%\u00136K\u0012GB\u0012U+\u0006\u0015a+\r\u0003%\u00136K\u0012GB\u0012Z5\u0006%1,\r\u0003%\u00136K\u0002\"CA\u0007\u000f\u0005\u0005\t9AA\b\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004?\nT\b\"CA\n\u000f\u0005\u0005\t9AA\u000b\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0004M&T\u0018AF\"T\u0007~\u001b\u0017M\\\"sK\u0006$XMW3s_Nd\u0015n[3\u0016\t\u0005m\u0011q\u0005\u000b\u0007\u0003;\tI#a\f\u0011\u000f1\ny\"a\t\u0002$%\u0019\u0011\u0011E\u0017\u0003%\r\u000bgn\u0011:fCR,',\u001a:pg2K7.\u001a\t\u0005eM\n)\u0003E\u00027\u0003O!Q\u0001\u000f\u0005C\u0002eB\u0011\"a\u000b\t\u0003\u0003\u0005\u001d!!\f\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0003`E\u0006\u0015\u0002\"CA\u0019\u0011\u0005\u0005\t9AA\u001a\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0005M&\f)#\u0001\tD'\u000e{6-\u00198NCB4\u0016\r\\;fgV1\u0011\u0011HA#\u0003\u0013\"b!a\u000f\u0002P\u0005U\u0003c\u0003\u0017\u0002>\u0005\u0005\u00131IA$\u0003\u001bJ1!a\u0010.\u00051\u0019\u0015M\\'baZ\u000bG.^3t!\u0011\u00114'a\u0011\u0011\u0007Y\n)\u0005B\u00039\u0013\t\u0007\u0011\bE\u00027\u0003\u0013\"a!a\u0013\n\u0005\u0004I$!\u0001*\u0011\tI\u001a\u0014q\t\u0005\n\u0003#J\u0011\u0011!a\u0002\u0003'\n!\"\u001a<jI\u0016t7-\u001a\u00138!\u0011y&-a\u0012\t\u0013\u0005]\u0013\"!AA\u0004\u0005e\u0013AC3wS\u0012,gnY3%qA1\u00111LA1\u0003\u000fj!!!\u0018\u000b\u0007\u0005}##\u0001\u0003nCRD\u0017\u0002BA2\u0003;\u0012\u0001bU3nSJLgnZ\u0001\r\u0007N\u001bul]2bY\u0006\u0014xJZ\u000b\u0005\u0003S\n)(\u0006\u0002\u0002lA9A&!\u001c\u0002r\u0005M\u0014bAA8[\tA1kY1mCJ|e\r\u0005\u00033g\u0005M\u0004c\u0001\u001c\u0002v\u00111\u0011q\u000f\u0006C\u0002e\u0012\u0011\u0001V\u0001\u0015\u0007N\u001bulY1o\u0013R,'/\u0019;f-\u0006dW/Z:\u0016\t\u0005u\u0014\u0011R\u000b\u0003\u0003\u007f\u0002r\u0001LAA\u0003\u000b\u000b9)C\u0002\u0002\u00046\u0012\u0011cQ1o)J\fg/\u001a:tKZ\u000bG.^3t!\u0011\u00114'a\"\u0011\u0007Y\nI\tB\u00039\u0017\t\u0007\u0011(\u0001\rD'\u000e{6-\u00198Ji\u0016\u0014\u0018\r^3LKf\u001ch+\u00197vKN,B!a$\u0002\u001cR!\u0011\u0011SAU!%a\u00131SAL\u0003;\u000bI*C\u0002\u0002\u00166\u0012\u0001dQ1o)J\fg/\u001a:tK.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t!\u0011\u00114'!'\u0011\u0007Y\nY\nB\u00039\u0019\t\u0007\u0011\bE\u0004\u0018\u0003?\u000b\u0019+a)\n\u0007\u0005\u0005\u0006D\u0001\u0004UkBdWM\r\t\u0004/\u0005\u0015\u0016bAAT1\t\u0019\u0011J\u001c;\t\u0013\u0005-F\"!AA\u0004\u00055\u0016AC3wS\u0012,gnY3%sA!a-[AM\u0001"
)
public interface CSCMatrixOps extends CSCMatrixExpandedOps {
   // $FF: synthetic method
   static CanCopyCSCMatrix CSC_canCopy$(final CSCMatrixOps $this, final ClassTag evidence$3, final Zero evidence$4) {
      return $this.CSC_canCopy(evidence$3, evidence$4);
   }

   default CanCopyCSCMatrix CSC_canCopy(final ClassTag evidence$3, final Zero evidence$4) {
      return new CanCopyCSCMatrix(evidence$3, evidence$4);
   }

   // $FF: synthetic method
   static CanCreateZerosLike CSC_canCreateZerosLike$(final CSCMatrixOps $this, final ClassTag evidence$5, final Zero evidence$6) {
      return $this.CSC_canCreateZerosLike(evidence$5, evidence$6);
   }

   default CanCreateZerosLike CSC_canCreateZerosLike(final ClassTag evidence$5, final Zero evidence$6) {
      return new CanCreateZerosLike(evidence$5, evidence$6) {
         private final ClassTag evidence$5$1;
         private final Zero evidence$6$1;

         public CSCMatrix apply(final CSCMatrix v1) {
            return CSCMatrix$.MODULE$.zeros(v1.rows(), v1.cols(), this.evidence$5$1, this.evidence$6$1);
         }

         public {
            this.evidence$5$1 = evidence$5$1;
            this.evidence$6$1 = evidence$6$1;
         }
      };
   }

   // $FF: synthetic method
   static CanMapValues CSC_canMapValues$(final CSCMatrixOps $this, final ClassTag evidence$7, final Semiring evidence$8) {
      return $this.CSC_canMapValues(evidence$7, evidence$8);
   }

   default CanMapValues CSC_canMapValues(final ClassTag evidence$7, final Semiring evidence$8) {
      Object z = ((Zero).MODULE$.implicitly(Zero$.MODULE$.zeroFromSemiring(evidence$8))).zero();
      return new CanMapValues(z, evidence$7, evidence$8) {
         private final Object z$1;
         private final ClassTag evidence$7$1;
         private final Semiring evidence$8$1;

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

         public CSCMatrix map(final CSCMatrix from, final Function1 fn) {
            Object fz = fn.apply(from.zero());
            boolean fzIsNotZero = !BoxesRunTime.equals(fz, this.z$1);
            CSCMatrix.Builder builder = new CSCMatrix.Builder(from.rows(), from.cols(), from.activeSize(), this.evidence$7$1, this.evidence$8$1, Zero$.MODULE$.zeroFromSemiring(this.evidence$8$1));

            for(int j = 0; j < from.cols(); ++j) {
               int ip = from.colPtrs()[j];

               int lastI;
               for(lastI = 0; ip < from.colPtrs()[j + 1]; ++ip) {
                  int i;
                  for(i = from.rowIndices()[ip]; fzIsNotZero && lastI < i; ++lastI) {
                     builder.add(lastI, j, fz);
                  }

                  ++lastI;
                  Object v = scala.runtime.ScalaRunTime..MODULE$.array_apply(from.data(), ip);
                  Object r = fn.apply(v);
                  if (!BoxesRunTime.equals(r, this.z$1)) {
                     builder.add(i, j, r);
                  }
               }

               while(fzIsNotZero && lastI < from.rows()) {
                  builder.add(lastI, j, fz);
                  ++lastI;
               }
            }

            return builder.result(builder.result$default$1(), builder.result$default$2());
         }

         public CSCMatrix mapActive(final CSCMatrix from, final Function1 fn) {
            BooleanRef zeroSeen = BooleanRef.create(false);
            Object newData = scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.genericArrayOps(from.data()), (v) -> this.ff$1(v, fn, zeroSeen), this.evidence$7$1);
            CSCMatrix r = new CSCMatrix(newData, from.rows(), from.cols(), (int[])from.colPtrs().clone(), from.activeSize(), (int[])from.rowIndices().clone(), Zero$.MODULE$.zeroFromSemiring(this.evidence$8$1));
            if (zeroSeen.elem) {
               r.compact();
            }

            return r;
         }

         private final Object ff$1(final Object v, final Function1 fn$1, final BooleanRef zeroSeen$1) {
            Object r = fn$1.apply(v);
            if (BoxesRunTime.equals(r, this.z$1)) {
               zeroSeen$1.elem = true;
            }

            return r;
         }

         public {
            this.z$1 = z$1;
            this.evidence$7$1 = evidence$7$1;
            this.evidence$8$1 = evidence$8$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static ScalarOf CSC_scalarOf$(final CSCMatrixOps $this) {
      return $this.CSC_scalarOf();
   }

   default ScalarOf CSC_scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   // $FF: synthetic method
   static CanTraverseValues CSC_canIterateValues$(final CSCMatrixOps $this) {
      return $this.CSC_canIterateValues();
   }

   default CanTraverseValues CSC_canIterateValues() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final CSCMatrix from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final CSCMatrix from, final CanTraverseValues.ValuesVisitor fn) {
            fn.zeros(from.size() - from.activeSize(), from.zero());
            fn.visitArray(from.data(), 0, from.activeSize(), 1);
            return fn;
         }

         public {
            CanTraverseValues.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CanTraverseKeyValuePairs CSC_canIterateKeysValues$(final CSCMatrixOps $this, final Zero evidence$9) {
      return $this.CSC_canIterateKeysValues(evidence$9);
   }

   default CanTraverseKeyValuePairs CSC_canIterateKeysValues(final Zero evidence$9) {
      return new CanTraverseKeyValuePairs(evidence$9) {
         private final Zero evidence$9$1;

         public boolean isTraversableAgain(final CSCMatrix from) {
            return true;
         }

         public void traverse(final CSCMatrix from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            Object zero = ((Zero).MODULE$.implicitly(this.evidence$9$1)).zero();
            fn.zeros(from.size() - from.activeSize(), from.iterator().collect(new Serializable(zero) {
               private static final long serialVersionUID = 0L;
               private final Object zero$1;

               public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                  Object var3;
                  if (x1 != null) {
                     Tuple2 k = (Tuple2)x1._1();
                     Object v = x1._2();
                     if (!BoxesRunTime.equals(v, this.zero$1)) {
                        var3 = k;
                        return var3;
                     }
                  }

                  var3 = default.apply(x1);
                  return var3;
               }

               public final boolean isDefinedAt(final Tuple2 x1) {
                  boolean var2;
                  if (x1 != null) {
                     Object v = x1._2();
                     if (!BoxesRunTime.equals(v, this.zero$1)) {
                        var2 = true;
                        return var2;
                     }
                  }

                  var2 = false;
                  return var2;
               }

               public {
                  this.zero$1 = zero$1;
               }
            }), zero);
            from.activeIterator().foreach(((k, a) -> {
               $anonfun$traverse$2(fn, k, a);
               return BoxedUnit.UNIT;
            }).tupled());
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$2(final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn$2, final Tuple2 k, final Object a) {
            fn$2.visit(k, a);
         }

         public {
            this.evidence$9$1 = evidence$9$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanCopyCSCMatrix CSC_canCopy$mDc$sp$(final CSCMatrixOps $this, final ClassTag evidence$3, final Zero evidence$4) {
      return $this.CSC_canCopy$mDc$sp(evidence$3, evidence$4);
   }

   default CanCopyCSCMatrix CSC_canCopy$mDc$sp(final ClassTag evidence$3, final Zero evidence$4) {
      return new CSCMatrixOps$CanCopyCSCMatrix$mcD$sp(this, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   static CanCopyCSCMatrix CSC_canCopy$mFc$sp$(final CSCMatrixOps $this, final ClassTag evidence$3, final Zero evidence$4) {
      return $this.CSC_canCopy$mFc$sp(evidence$3, evidence$4);
   }

   default CanCopyCSCMatrix CSC_canCopy$mFc$sp(final ClassTag evidence$3, final Zero evidence$4) {
      return new CSCMatrixOps$CanCopyCSCMatrix$mcF$sp(this, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   static CanCopyCSCMatrix CSC_canCopy$mIc$sp$(final CSCMatrixOps $this, final ClassTag evidence$3, final Zero evidence$4) {
      return $this.CSC_canCopy$mIc$sp(evidence$3, evidence$4);
   }

   default CanCopyCSCMatrix CSC_canCopy$mIc$sp(final ClassTag evidence$3, final Zero evidence$4) {
      return new CSCMatrixOps$CanCopyCSCMatrix$mcI$sp(this, evidence$3, evidence$4);
   }

   // $FF: synthetic method
   static CanCopyCSCMatrix CSC_canCopy$mJc$sp$(final CSCMatrixOps $this, final ClassTag evidence$3, final Zero evidence$4) {
      return $this.CSC_canCopy$mJc$sp(evidence$3, evidence$4);
   }

   default CanCopyCSCMatrix CSC_canCopy$mJc$sp(final ClassTag evidence$3, final Zero evidence$4) {
      return new CSCMatrixOps$CanCopyCSCMatrix$mcJ$sp(this, evidence$3, evidence$4);
   }

   static void $init$(final CSCMatrixOps $this) {
   }

   public class CanCopyCSCMatrix implements CanCopy {
      public final Zero evidence$2;
      // $FF: synthetic field
      public final CSCMatrixOps $outer;

      public CSCMatrix apply(final CSCMatrix v1) {
         return v1.copy();
      }

      public CSCMatrix apply$mcD$sp(final CSCMatrix v1) {
         return this.apply(v1);
      }

      public CSCMatrix apply$mcF$sp(final CSCMatrix v1) {
         return this.apply(v1);
      }

      public CSCMatrix apply$mcI$sp(final CSCMatrix v1) {
         return this.apply(v1);
      }

      public CSCMatrix apply$mcJ$sp(final CSCMatrix v1) {
         return this.apply(v1);
      }

      // $FF: synthetic method
      public CSCMatrixOps breeze$linalg$operators$CSCMatrixOps$CanCopyCSCMatrix$$$outer() {
         return this.$outer;
      }

      public CanCopyCSCMatrix(final ClassTag evidence$1, final Zero evidence$2) {
         this.evidence$2 = evidence$2;
         if (CSCMatrixOps.this == null) {
            throw null;
         } else {
            this.$outer = CSCMatrixOps.this;
            super();
         }
      }
   }
}
