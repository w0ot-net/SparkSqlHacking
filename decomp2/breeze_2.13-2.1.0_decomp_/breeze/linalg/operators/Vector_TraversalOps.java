package breeze.linalg.operators;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.HashVector;
import breeze.linalg.HashVector$;
import breeze.linalg.SparseVector;
import breeze.linalg.SparseVector$;
import breeze.linalg.Vector;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseKeyValuePairs;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.storage.Zero;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015ha\u0002\n\u0014!\u0003\r\tA\u0007\u0005\u0006C\u0001!\tA\t\u0004\u0005M\u0001\u0001q\u0005\u0003\u0005[\u0005\t\r\t\u0015a\u0003\\\u0011\u0015\t'\u0001\"\u0001c\u0011\u00159'\u0001\"\u0001i\u0011\u0015\t(\u0001\"\u0001s\u0011\u0015a\b\u0001b\u0001~\u0011\u001d\tI\u0003\u0001C\u0002\u0003W1a!!\u0011\u0001\u0001\u0005\r\u0003BCAA\u0013\t\r\t\u0015a\u0003\u0002\u0004\"1\u0011-\u0003C\u0001\u0003\u000bCaaZ\u0005\u0005\u0002\u00055\u0005BB9\n\t\u0003\t\u0019\nC\u0004\u0002\"&!\t%a)\t\u000f\u0005-\u0006\u0001b\u0001\u0002.\"9\u0011\u0011\u0019\u0001\u0005\u0004\u0005\r\u0007bBAj\u0001\u0011\r\u0011Q\u001b\u0002\u0014-\u0016\u001cGo\u001c:`)J\fg/\u001a:tC2|\u0005o\u001d\u0006\u0003)U\t\u0011b\u001c9fe\u0006$xN]:\u000b\u0005Y9\u0012A\u00027j]\u0006dwMC\u0001\u0019\u0003\u0019\u0011'/Z3{K\u000e\u00011C\u0001\u0001\u001c!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012a\t\t\u00039\u0011J!!J\u000f\u0003\tUs\u0017\u000e\u001e\u0002\u0016\u0007\u0006t',\u001b9NCB4\u0016\r\\;fgZ+7\r^8s+\rASGU\n\u0004\u0005mI\u0003C\u0002\u0016._M\n\u0016,D\u0001,\u0015\taS#A\u0004tkB\u0004xN\u001d;\n\u00059Z#aD\"b]jK\u0007/T1q-\u0006dW/Z:\u0011\u0007A\n4'D\u0001\u0016\u0013\t\u0011TC\u0001\u0004WK\u000e$xN\u001d\t\u0003iUb\u0001\u0001B\u00057\u0005\u0001\u0006\t\u0011!b\u0001o\t\ta+\u0005\u00029wA\u0011A$O\u0005\u0003uu\u0011qAT8uQ&tw\r\u0005\u0002\u001dy%\u0011Q(\b\u0002\u0004\u0003:L\b\u0006B\u001b@\u00052\u0003\"\u0001\b!\n\u0005\u0005k\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\"E\r\u0016s!\u0001\b#\n\u0005\u0015k\u0012aA%oiF\"AeR&\u001f\u001d\tA5*D\u0001J\u0015\tQ\u0015$\u0001\u0004=e>|GOP\u0005\u0002=E*1%\u0014(Q\u001f:\u0011ADT\u0005\u0003\u001fv\ta\u0001R8vE2,\u0017\u0007\u0002\u0013H\u0017z\u0001\"\u0001\u000e*\u0005\u0013M\u0013\u0001\u0015!A\u0001\u0006\u00049$A\u0001*WQ\u0011\u0011v(V,2\u000b\r\u001aEIV#2\t\u0011:5JH\u0019\u0006G5s\u0005lT\u0019\u0005I\u001d[e\u0004E\u00021cE\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\rav,U\u0007\u0002;*\u0011a,H\u0001\be\u00164G.Z2u\u0013\t\u0001WL\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q\t1\r\u0006\u0002eMB!QMA\u001aR\u001b\u0005\u0001\u0001\"\u0002.\u0005\u0001\bY\u0016AB2sK\u0006$X\r\u0006\u0002jYB\u0019\u0001G[)\n\u0005-,\"a\u0003#f]N,g+Z2u_JDQ!\\\u0003A\u00029\fa\u0001\\3oORD\u0007C\u0001\u000fp\u0013\t\u0001XDA\u0002J]R\f1!\\1q)\u0011I7/^<\t\u000bQ4\u0001\u0019A\u0018\u0002\t\u0019\u0014x.\u001c\u0005\u0006m\u001a\u0001\raL\u0001\u0006MJ|WN\r\u0005\u0006q\u001a\u0001\r!_\u0001\u0003M:\u0004R\u0001\b>4gEK!a_\u000f\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0014AD2b]6\u000b\u0007OV1mk\u0016\u001cxLV\u000b\u0006}\u0006%\u0011Q\u0002\u000b\u0006\u007f\u0006M\u00111\u0005\t\fU\u0005\u0005\u0011QAA\u0004\u0003\u0017\t\t\"C\u0002\u0002\u0004-\u0012AbQ1o\u001b\u0006\u0004h+\u00197vKN\u0004B\u0001M\u0019\u0002\bA\u0019A'!\u0003\u0005\u000bY:!\u0019A\u001c\u0011\u0007Q\ni\u0001\u0002\u0004\u0002\u0010\u001d\u0011\ra\u000e\u0002\u0003-J\u0002B\u0001M\u0019\u0002\f!I\u0011QC\u0004\u0002\u0002\u0003\u000f\u0011qC\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004CBA\r\u0003?\tY!\u0004\u0002\u0002\u001c)\u0019\u0011QD\f\u0002\u000fM$xN]1hK&!\u0011\u0011EA\u000e\u0005\u0011QVM]8\t\u000f\u0005\u0015r\u0001q\u0001\u0002(\u0005\u0019Q.\u00198\u0011\tq{\u00161B\u0001\u0012G\u0006t',\u001b9NCB4\u0016\r\\;fg~3VCBA\u0017\u0003g\t9\u0004\u0006\u0003\u00020\u0005m\u0002CB3\u0003\u0003c\t)\u0004E\u00025\u0003g!QA\u000e\u0005C\u0002]\u00022\u0001NA\u001c\t\u0019\tI\u0004\u0003b\u0001o\t\t!\u000bC\u0005\u0002>!\t\t\u0011q\u0001\u0002@\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\tq{\u0016Q\u0007\u0002\u0019\u0007\u0006t',\u001b9NCB\\U-\u001f,bYV,7OV3di>\u0014XCBA#\u0003#\n\u0019h\u0005\u0003\n7\u0005\u001d\u0003\u0003\u0004\u0016\u0002J\u00055c.a\u0014\u0002r\u0005}\u0014bAA&W\t\u00112)\u00198[SBl\u0015\r]&fsZ\u000bG.^3t!\u0011\u0001\u0014'a\u0014\u0011\u0007Q\n\t\u0006B\u00057\u0013\u0001\u0006\t\u0011!b\u0001o!Z\u0011\u0011K \u0002V\u0005e\u0013QLA4c\u0019\u0019SJTA,\u001fF\"AeR&\u001fc\u0019\u00193\tRA.\u000bF\"AeR&\u001fc%\u0019\u0013qLA1\u0003K\n\u0019GD\u0002\u001d\u0003CJ1!a\u0019\u001e\u0003\u00151En\\1uc\u0011!si\u0013\u00102\u0013\r\nI'a\u001b\u0002p\u00055db\u0001\u000f\u0002l%\u0019\u0011QN\u000f\u0002\t1{gnZ\u0019\u0005I\u001d[e\u0004E\u00025\u0003g\"\u0011bU\u0005!\u0002\u0003\u0005)\u0019A\u001c)\u000f\u0005Mt(a\u001e\u0002|E21e\u0011#\u0002z\u0015\u000bD\u0001J$L=E21%\u0014(\u0002~=\u000bD\u0001J$L=A!\u0001'MA9\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u00059~\u000b\t\b\u0006\u0002\u0002\bR!\u0011\u0011RAF!\u0019)\u0017\"a\u0014\u0002r!9\u0011\u0011Q\u0006A\u0004\u0005\rE\u0003BAH\u0003#\u0003B\u0001\r6\u0002r!)Q\u000e\u0004a\u0001]RA\u0011qPAK\u0003/\u000bI\n\u0003\u0004u\u001b\u0001\u0007\u0011Q\n\u0005\u0007m6\u0001\r!!\u0014\t\ral\u0001\u0019AAN!)a\u0012Q\u00148\u0002P\u0005=\u0013\u0011O\u0005\u0004\u0003?k\"!\u0003$v]\u000e$\u0018n\u001c84\u0003%i\u0017\r]!di&4X\r\u0006\u0005\u0002\u0000\u0005\u0015\u0016qUAU\u0011\u0019!h\u00021\u0001\u0002N!1aO\u0004a\u0001\u0003\u001bBa\u0001\u001f\bA\u0002\u0005m\u0015A\u0003>ja6\u000b\u0007o\u0013,`-V1\u0011qVA[\u0003s#B!!-\u0002<B1Q-CAZ\u0003o\u00032\u0001NA[\t\u00151tB1\u00018!\r!\u0014\u0011\u0018\u0003\u0007\u0003sy!\u0019A\u001c\t\u0013\u0005uv\"!AA\u0004\u0005}\u0016AC3wS\u0012,gnY3%kA!AlXA\\\u0003I\u0019\u0017M\\%uKJ\fG/\u001a,bYV,7o\u0018,\u0016\t\u0005\u0015\u0017\u0011[\u000b\u0003\u0003\u000f\u0004rAKAe\u0003\u001b\fy-C\u0002\u0002L.\u0012\u0011cQ1o)J\fg/\u001a:tKZ\u000bG.^3t!\u0011\u0001\u0014'a4\u0011\u0007Q\n\t\u000eB\u00037!\t\u0007q'\u0001\u000edC:$&/\u0019<feN,7*Z=WC2,X\rU1jeN|f+\u0006\u0003\u0002X\u0006\rXCAAm!!Q\u00131\\Ap]\u0006\u0005\u0018bAAoW\tA2)\u00198Ue\u00064XM]:f\u0017\u0016Lh+\u00197vKB\u000b\u0017N]:\u0011\tA\n\u0014\u0011\u001d\t\u0004i\u0005\rH!\u0002\u001c\u0012\u0005\u00049\u0004"
)
public interface Vector_TraversalOps {
   // $FF: synthetic method
   static CanMapValues canMapValues_V$(final Vector_TraversalOps $this, final Zero evidence$2, final ClassTag man) {
      return $this.canMapValues_V(evidence$2, man);
   }

   default CanMapValues canMapValues_V(final Zero evidence$2, final ClassTag man) {
      return new CanMapValues(man, evidence$2) {
         private final ClassTag man$1;
         private final Zero evidence$2$1;

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

         public Vector map(final Vector from, final Function1 fn) {
            Object var3;
            if (from instanceof SparseVector) {
               SparseVector var5 = (SparseVector)from;
               var3 = (Vector)var5.mapValues(fn, SparseVector$.MODULE$.canMapValues(this.man$1, this.evidence$2$1));
            } else if (from instanceof HashVector) {
               HashVector var6 = (HashVector)from;
               var3 = (Vector)var6.mapValues(fn, HashVector$.MODULE$.canMapValues(this.man$1, this.evidence$2$1));
            } else if (from instanceof DenseVector) {
               DenseVector var7 = (DenseVector)from;
               var3 = (Vector)var7.mapValues(fn, DenseVector$.MODULE$.DV_canMapValues(this.man$1));
            } else {
               var3 = DenseVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$map$2(fn, from, BoxesRunTime.unboxToInt(i)), this.man$1);
            }

            return (Vector)var3;
         }

         public Vector mapActive(final Vector from, final Function1 fn) {
            Object var3;
            if (from instanceof SparseVector) {
               SparseVector var5 = (SparseVector)from;
               var3 = (Vector)var5.mapActiveValues(fn, SparseVector$.MODULE$.canMapValues(this.man$1, this.evidence$2$1));
            } else if (from instanceof HashVector) {
               HashVector var6 = (HashVector)from;
               var3 = (Vector)var6.mapActiveValues(fn, HashVector$.MODULE$.canMapValues(this.man$1, this.evidence$2$1));
            } else if (from instanceof DenseVector) {
               DenseVector var7 = (DenseVector)from;
               var3 = (Vector)var7.mapActiveValues(fn, DenseVector$.MODULE$.DV_canMapValues(this.man$1));
            } else {
               var3 = DenseVector$.MODULE$.tabulate(from.length(), (i) -> $anonfun$mapActive$1(fn, from, BoxesRunTime.unboxToInt(i)), this.man$1);
            }

            return (Vector)var3;
         }

         // $FF: synthetic method
         public static final Object $anonfun$map$2(final Function1 fn$1, final Vector from$1, final int i) {
            return fn$1.apply(from$1.apply(BoxesRunTime.boxToInteger(i)));
         }

         // $FF: synthetic method
         public static final Object $anonfun$mapActive$1(final Function1 fn$2, final Vector from$2, final int i) {
            return fn$2.apply(from$2.apply(BoxesRunTime.boxToInteger(i)));
         }

         public {
            this.man$1 = man$1;
            this.evidence$2$1 = evidence$2$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanZipMapValuesVector canZipMapValues_V$(final Vector_TraversalOps $this, final ClassTag evidence$3) {
      return $this.canZipMapValues_V(evidence$3);
   }

   default CanZipMapValuesVector canZipMapValues_V(final ClassTag evidence$3) {
      return new CanZipMapValuesVector(evidence$3);
   }

   // $FF: synthetic method
   static CanZipMapKeyValuesVector zipMapKV_V$(final Vector_TraversalOps $this, final ClassTag evidence$5) {
      return $this.zipMapKV_V(evidence$5);
   }

   default CanZipMapKeyValuesVector zipMapKV_V(final ClassTag evidence$5) {
      return new CanZipMapKeyValuesVector(evidence$5);
   }

   // $FF: synthetic method
   static CanTraverseValues canIterateValues_V$(final Vector_TraversalOps $this) {
      return $this.canIterateValues_V();
   }

   default CanTraverseValues canIterateValues_V() {
      return new CanTraverseValues() {
         public Object foldLeft(final Object from, final Object b, final Function2 fn) {
            return CanTraverseValues.foldLeft$(this, from, b, fn);
         }

         public boolean isTraversableAgain(final Vector from) {
            return true;
         }

         public CanTraverseValues.ValuesVisitor traverse(final Vector from, final CanTraverseValues.ValuesVisitor fn) {
            from.valuesIterator().foreach((v) -> {
               $anonfun$traverse$1(fn, v);
               return BoxedUnit.UNIT;
            });
            return fn;
         }

         // $FF: synthetic method
         public static final void $anonfun$traverse$1(final CanTraverseValues.ValuesVisitor fn$3, final Object v) {
            fn$3.visit(v);
         }

         public {
            CanTraverseValues.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanTraverseKeyValuePairs canTraverseKeyValuePairs_V$(final Vector_TraversalOps $this) {
      return $this.canTraverseKeyValuePairs_V();
   }

   default CanTraverseKeyValuePairs canTraverseKeyValuePairs_V() {
      return new CanTraverseKeyValuePairs() {
         public boolean isTraversableAgain(final Vector from) {
            return true;
         }

         public void traverse(final Vector from, final CanTraverseKeyValuePairs.KeyValuePairsVisitor fn) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> fn.visit(BoxesRunTime.boxToInteger(i), from.apply(BoxesRunTime.boxToInteger(i))));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final Vector_TraversalOps $this) {
   }

   public class CanZipMapValuesVector implements CanZipMapValues {
      public final ClassTag breeze$linalg$operators$Vector_TraversalOps$CanZipMapValuesVector$$evidence$1;
      // $FF: synthetic field
      public final Vector_TraversalOps $outer;

      public Object map$mcDD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDD$sp$(this, from, from2, fn);
      }

      public Object map$mcFD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFD$sp$(this, from, from2, fn);
      }

      public Object map$mcID$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcID$sp$(this, from, from2, fn);
      }

      public Object map$mcJD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJD$sp$(this, from, from2, fn);
      }

      public Object map$mcDF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDF$sp$(this, from, from2, fn);
      }

      public Object map$mcFF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFF$sp$(this, from, from2, fn);
      }

      public Object map$mcIF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIF$sp$(this, from, from2, fn);
      }

      public Object map$mcJF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJF$sp$(this, from, from2, fn);
      }

      public Object map$mcDI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDI$sp$(this, from, from2, fn);
      }

      public Object map$mcFI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFI$sp$(this, from, from2, fn);
      }

      public Object map$mcII$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcII$sp$(this, from, from2, fn);
      }

      public Object map$mcJI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJI$sp$(this, from, from2, fn);
      }

      public Object map$mcDJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDJ$sp$(this, from, from2, fn);
      }

      public Object map$mcFJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFJ$sp$(this, from, from2, fn);
      }

      public Object map$mcIJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIJ$sp$(this, from, from2, fn);
      }

      public Object map$mcJJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJJ$sp$(this, from, from2, fn);
      }

      public DenseVector create(final int length) {
         return DenseVector$.MODULE$.apply(this.breeze$linalg$operators$Vector_TraversalOps$CanZipMapValuesVector$$evidence$1.newArray(length));
      }

      public DenseVector map(final Vector from, final Vector from2, final Function2 fn) {
         scala.Predef..MODULE$.require(from.length() == from2.length(), () -> "Vector lengths must match!");
         DenseVector result = this.create(from.length());
         int index$macro$2 = 0;

         for(int limit$macro$4 = from.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            scala.runtime.ScalaRunTime..MODULE$.array_update(result.data(), index$macro$2, fn.apply(from.apply(BoxesRunTime.boxToInteger(index$macro$2)), from2.apply(BoxesRunTime.boxToInteger(index$macro$2))));
         }

         return result;
      }

      public DenseVector create$mcD$sp(final int length) {
         return this.create(length);
      }

      public DenseVector create$mcI$sp(final int length) {
         return this.create(length);
      }

      public DenseVector map$mcDD$sp(final Vector from, final Vector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcID$sp(final Vector from, final Vector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcDI$sp(final Vector from, final Vector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector map$mcII$sp(final Vector from, final Vector from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      // $FF: synthetic method
      public Vector_TraversalOps breeze$linalg$operators$Vector_TraversalOps$CanZipMapValuesVector$$$outer() {
         return this.$outer;
      }

      public CanZipMapValuesVector(final ClassTag evidence$1) {
         this.breeze$linalg$operators$Vector_TraversalOps$CanZipMapValuesVector$$evidence$1 = evidence$1;
         if (Vector_TraversalOps.this == null) {
            throw null;
         } else {
            this.$outer = Vector_TraversalOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class CanZipMapKeyValuesVector implements CanZipMapKeyValues {
      public final ClassTag breeze$linalg$operators$Vector_TraversalOps$CanZipMapKeyValuesVector$$evidence$4;
      // $FF: synthetic field
      public final Vector_TraversalOps $outer;

      public DenseVector create(final int length) {
         return DenseVector$.MODULE$.apply(this.breeze$linalg$operators$Vector_TraversalOps$CanZipMapKeyValuesVector$$evidence$4.newArray(length));
      }

      public Vector map(final Vector from, final Vector from2, final Function3 fn) {
         scala.Predef..MODULE$.require(from.length() == from2.length(), () -> "Vector lengths must match!");
         DenseVector result = this.create(from.length());

         for(int i = 0; i < from.length(); ++i) {
            scala.runtime.ScalaRunTime..MODULE$.array_update(result.data(), i, fn.apply(BoxesRunTime.boxToInteger(i), from.apply(BoxesRunTime.boxToInteger(i)), from2.apply(BoxesRunTime.boxToInteger(i))));
         }

         return result;
      }

      public Vector mapActive(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseVector create$mcD$sp(final int length) {
         return this.create(length);
      }

      public DenseVector create$mcI$sp(final int length) {
         return this.create(length);
      }

      public Vector map$mcDD$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public Vector map$mcID$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public Vector map$mcDF$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public Vector map$mcIF$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public Vector map$mcDI$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public Vector map$mcII$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public Vector map$mcDJ$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public Vector map$mcIJ$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public Vector mapActive$mcDD$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public Vector mapActive$mcID$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public Vector mapActive$mcDF$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public Vector mapActive$mcIF$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public Vector mapActive$mcDI$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public Vector mapActive$mcII$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public Vector mapActive$mcDJ$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public Vector mapActive$mcIJ$sp(final Vector from, final Vector from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      // $FF: synthetic method
      public Vector_TraversalOps breeze$linalg$operators$Vector_TraversalOps$CanZipMapKeyValuesVector$$$outer() {
         return this.$outer;
      }

      public CanZipMapKeyValuesVector(final ClassTag evidence$4) {
         this.breeze$linalg$operators$Vector_TraversalOps$CanZipMapKeyValuesVector$$evidence$4 = evidence$4;
         if (Vector_TraversalOps.this == null) {
            throw null;
         } else {
            this.$outer = Vector_TraversalOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
