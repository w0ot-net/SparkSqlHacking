package breeze.linalg.operators;

import breeze.generic.MMRegistry2;
import breeze.generic.UFunc;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.MapView;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uhaB\u0005\u000b!\u0003\r\t!\u0005\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u0002!\tB\u0014\u0005\u0006'\u0002!\t\u0002\u0016\u0005\n\u0003\u000f\u0001!\u0019!C\u0005\u0003\u0013Aq!a\u0014\u0001\t\u0003\t\t\u0006C\u0004\u0002X\u0001!I!!\u0017\t\u000f\u0005]\u0005\u0001\"\u0001\u0002\u001a\"q\u0011\u0011\u001b\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002T\u0006m(A\u0004\"j]\u0006\u0014\u0018PU3hSN$(/\u001f\u0006\u0003\u00171\t\u0011b\u001c9fe\u0006$xN]:\u000b\u00055q\u0011A\u00027j]\u0006dwMC\u0001\u0010\u0003\u0019\u0011'/Z3{K\u000e\u0001Q#\u0002\n0e\u0015*4\u0003\u0002\u0001\u00143]\u0002\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007C\u0002\u000e!G9\nDG\u0004\u0002\u001c=5\tAD\u0003\u0002\u001e\u001d\u00059q-\u001a8fe&\u001c\u0017BA\u0010\u001d\u0003\u0015)f)\u001e8d\u0013\t\t#E\u0001\u0004V\u00136\u0004HN\r\u0006\u0003?q\u0001\"\u0001J\u0013\r\u0001\u0011)a\u0005\u0001b\u0001O\t\u0011q\n]\t\u0003Q-\u0002\"\u0001F\u0015\n\u0005)*\"a\u0002(pi\"Lgn\u001a\t\u0003)1J!!L\u000b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002%_\u0011)\u0001\u0007\u0001b\u0001O\t\t\u0011\t\u0005\u0002%e\u0011)1\u0007\u0001b\u0001O\t\t!\t\u0005\u0002%k\u0011)a\u0007\u0001b\u0001O\t\t!\u000bE\u0002\u001cqiJ!!\u000f\u000f\u0003\u00175k%+Z4jgR\u0014\u0018P\r\u0019\u0005wu\nU\t\u0005\u0004\u001bA\rb\u0004\t\u0012\t\u0003Iu\"\u0011B\u0010\u0001\u0002\u0002\u0003\u0005)\u0011A \u0003\u0007}#\u0013'\u0005\u0002)]A\u0011A%\u0011\u0003\n\u0005\u0002\t\t\u0011!A\u0003\u0002\r\u00131a\u0018\u00133#\tA\u0013\u0007\u0005\u0002%\u000b\u0012Ia\tAA\u0001\u0002\u0003\u0015\ta\u0012\u0002\u0004?\u0012\u001a\u0014C\u0001\u00155\u0003\u0019!\u0013N\\5uIQ\t!\n\u0005\u0002\u0015\u0017&\u0011A*\u0006\u0002\u0005+:LG/\u0001\bcS:$\u0017N\\4NSN\u001c\u0018N\\4\u0015\u0007Qz\u0015\u000bC\u0003Q\u0005\u0001\u0007a&A\u0001b\u0011\u0015\u0011&\u00011\u00012\u0003\u0005\u0011\u0017aD7vYRL\u0007\u000f\\3PaRLwN\\:\u0015\t!*fk\u0016\u0005\u0006!\u000e\u0001\rA\f\u0005\u0006%\u000e\u0001\r!\r\u0005\u00061\u000e\u0001\r!W\u0001\u0002[B!!,\u00193t\u001d\tYv\f\u0005\u0002]+5\tQL\u0003\u0002_!\u00051AH]8pizJ!\u0001Y\u000b\u0002\rA\u0013X\rZ3g\u0013\t\u00117MA\u0002NCBT!\u0001Y\u000b\u0011\tQ)wM\\\u0005\u0003MV\u0011a\u0001V;qY\u0016\u0014\u0004G\u00015m!\rQ\u0016n[\u0005\u0003U\u000e\u0014Qa\u00117bgN\u0004\"\u0001\n7\u0005\u00135<\u0016\u0011!A\u0001\u0006\u00039#aA0%iA\u0012q.\u001d\t\u00045&\u0004\bC\u0001\u0013r\t%\u0011x+!A\u0001\u0002\u000b\u0005qEA\u0002`IU\u0002T\u0001^>\u007f\u0003\u0007\u0001r!\u001e\u0011$uv\f\tA\u0004\u0002w=9\u0011q/\u001f\b\u00039bL\u0011aD\u0005\u0003;9\u0001\"\u0001J>\u0005\u0013q<\u0016\u0011!A\u0001\u0006\u0003y$aA0%mA\u0011AE \u0003\n\u007f^\u000b\t\u0011!A\u0003\u0002\r\u00131a\u0018\u00138!\r!\u00131\u0001\u0003\u000b\u0003\u000b9\u0016\u0011!A\u0001\u0006\u00039%aA0%q\u00059A.M2bG\",WCAA\u0006!\u0019\ti!a\u0006\u0002\u001c5\u0011\u0011q\u0002\u0006\u0005\u0003#\t\u0019\"\u0001\u0003mC:<'BAA\u000b\u0003\u0011Q\u0017M^1\n\t\u0005e\u0011q\u0002\u0002\f)\"\u0014X-\u00193M_\u000e\fG\u000e\u0005\u0004\u0015K\u0006u\u00111\u0007\t\u0007)\u0015\fy\"!\u000b1\t\u0005\u0005\u0012Q\u0005\t\u00055&\f\u0019\u0003E\u0002%\u0003K!!\"a\n\u0005\u0003\u0003\u0005\tQ!\u0001(\u0005\ryF%\u000f\u0019\u0005\u0003W\ty\u0003\u0005\u0003[S\u00065\u0002c\u0001\u0013\u00020\u0011Q\u0011\u0011\u0007\u0003\u0002\u0002\u0003\u0005)\u0011A\u0014\u0003\t}#\u0013\u0007\r\t\u0006)\u0005U\u0012\u0011H\u0005\u0004\u0003o)\"AB(qi&|g\u000e\r\u0005\u0002<\u0005}\u0012QIA&!%)\beIA\u001f\u0003\u0007\nI\u0005E\u0002%\u0003\u007f!!\"!\u0011\u0005\u0003\u0003\u0005\tQ!\u0001@\u0005\u0011yF%M\u0019\u0011\u0007\u0011\n)\u0005\u0002\u0006\u0002H\u0011\t\t\u0011!A\u0003\u0002\r\u0013Aa\u0018\u00132eA\u0019A%a\u0013\u0005\u0015\u00055C!!A\u0001\u0002\u000b\u0005qI\u0001\u0003`IE\u001a\u0014!B1qa2LH#\u0002\u001b\u0002T\u0005U\u0003\"\u0002)\u0006\u0001\u0004q\u0003\"\u0002*\u0006\u0001\u0004\t\u0014\u0001C:m_^\u0004\u0016\r\u001e5\u0015\u0017Q\nY&!\u0018\u0002`\u0005=\u0014Q\u0010\u0005\u0006!\u001a\u0001\rA\f\u0005\u0006%\u001a\u0001\r!\r\u0005\b\u0003C2\u0001\u0019AA2\u0003\t\t7\r\r\u0003\u0002f\u0005%\u0004\u0003\u0002.j\u0003O\u00022\u0001JA5\t1\tY'a\u0018\u0002\u0002\u0003\u0005)\u0011AA7\u0005\u0011yF%M\u001d\u0012\u0005!\u001a\u0002bBA9\r\u0001\u0007\u00111O\u0001\u0003E\u000e\u0004D!!\u001e\u0002zA!!,[A<!\r!\u0013\u0011\u0010\u0003\r\u0003w\ny'!A\u0001\u0002\u000b\u0005\u0011Q\u000e\u0002\u0005?\u0012\u0012\u0004\u0007C\u0004\u0002\u0000\u0019\u0001\r!!!\u0002\tA\f\u0017N\u001d\t\u0007)\u0015\f\u0019)!$1\t\u0005\u0015\u0015\u0011\u0012\t\u00055&\f9\tE\u0002%\u0003\u0013#A\"a#\u0002~\u0005\u0005\t\u0011!B\u0001\u0003[\u0012Aa\u0018\u00133cA\"\u0011qRAJ!\u0011Q\u0016.!%\u0011\u0007\u0011\n\u0019\n\u0002\u0007\u0002\u0016\u0006u\u0014\u0011!A\u0001\u0006\u0003\tiG\u0001\u0003`II\u0012\u0014\u0001\u0003:fO&\u001cH/\u001a:\u0016\r\u0005m\u00151UAU)\u0011\ti*a1\u0015\r\u0005}\u0015QVA_!!)\beIAQ\u0003O#\u0004c\u0001\u0013\u0002$\u00121\u0011QU\u0004C\u0002}\u0012!!Q!\u0011\u0007\u0011\nI\u000b\u0002\u0004\u0002,\u001e\u0011\ra\u0011\u0002\u0003\u0005\nCq!a,\b\u0001\b\t\t,\u0001\u0002d\u0003B1\u00111WA]\u0003Ck!!!.\u000b\u0007\u0005]V#A\u0004sK\u001adWm\u0019;\n\t\u0005m\u0016Q\u0017\u0002\t\u00072\f7o\u001d+bO\"9\u0011qX\u0004A\u0004\u0005\u0005\u0017AA2C!\u0019\t\u0019,!/\u0002(\"9\u0011QY\u0004A\u0002\u0005\u001d\u0017AA8qa\u0011\tI-!4\u0011\u0013U\u00043%!)\u0002(\u0006-\u0007c\u0001\u0013\u0002N\u0012Y\u0011qZAb\u0003\u0003\u0005\tQ!\u0001H\u0005\u0011yFEM\u001c\u0002\u001dM,\b/\u001a:%e\u0016<\u0017n\u001d;feR9!*!6\u0002`\u0006%\bB\u0002)\t\u0001\u0004\t9\u000e\r\u0003\u0002Z\u0006u\u0007\u0003\u0002.j\u00037\u00042\u0001JAo\t)a\u0018Q[A\u0001\u0002\u0003\u0015\ta\n\u0005\u0007%\"\u0001\r!!91\t\u0005\r\u0018q\u001d\t\u00055&\f)\u000fE\u0002%\u0003O$!b`Ap\u0003\u0003\u0005\tQ!\u0001(\u0011\u001d\t)\r\u0003a\u0001\u0003W\u0004\u0004\"!<\u0002r\u0006U\u0018\u0011 \t\n5\u0001\u001a\u0013q^Az\u0003o\u00042\u0001JAy\t)q\u0014\u0011^A\u0001\u0002\u0003\u0015\ta\u0010\t\u0004I\u0005UHA\u0003\"\u0002j\u0006\u0005\t\u0011!B\u0001\u0007B\u0019A%!?\u0005\u0015\u0019\u000bI/!A\u0001\u0002\u000b\u0005q)C\u0002\u0002\u0018b\u0002"
)
public interface BinaryRegistry extends UFunc.UImpl2, MMRegistry2 {
   void breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(final ThreadLocal x$1);

   // $FF: synthetic method
   void breeze$linalg$operators$BinaryRegistry$$super$register(final Class a, final Class b, final UFunc.UImpl2 op);

   // $FF: synthetic method
   static Object bindingMissing$(final BinaryRegistry $this, final Object a, final Object b) {
      return $this.bindingMissing(a, b);
   }

   default Object bindingMissing(final Object a, final Object b) {
      throw new UnsupportedOperationException((new StringBuilder(17)).append("Types not found!").append(a).append(b).append(" ").append(this.ops()).toString());
   }

   // $FF: synthetic method
   static Nothing multipleOptions$(final BinaryRegistry $this, final Object a, final Object b, final Map m) {
      return $this.multipleOptions(a, b, m);
   }

   default Nothing multipleOptions(final Object a, final Object b, final Map m) {
      throw new RuntimeException((new StringBuilder(30)).append("Multiple bindings for method: ").append(m).toString());
   }

   ThreadLocal breeze$linalg$operators$BinaryRegistry$$l1cache();

   // $FF: synthetic method
   static Object apply$(final BinaryRegistry $this, final Object a, final Object b) {
      return $this.apply(a, b);
   }

   default Object apply(final Object a, final Object b) {
      Class ac = a.getClass();
      Class bc = b.getClass();
      Tuple2 pair = new Tuple2(ac, bc);
      Tuple2 firstLevelCached = (Tuple2)this.breeze$linalg$operators$BinaryRegistry$$l1cache().get();
      Object var10000;
      if (firstLevelCached != null) {
         label40: {
            Object var8 = firstLevelCached._1();
            if (pair == null) {
               if (var8 != null) {
                  break label40;
               }
            } else if (!pair.equals(var8)) {
               break label40;
            }

            Option var9 = (Option)firstLevelCached._2();
            Object var3;
            if (.MODULE$.equals(var9)) {
               var3 = this.bindingMissing(a, b);
            } else {
               if (!(var9 instanceof Some)) {
                  throw new MatchError(var9);
               }

               Some var10 = (Some)var9;
               UFunc.UImpl2 m = (UFunc.UImpl2)var10.value();
               var3 = m.apply(a, b);
            }

            var10000 = var3;
            return var10000;
         }
      }

      var10000 = this.slowPath(a, b, ac, bc, pair);
      return var10000;
   }

   private Object slowPath(final Object a, final Object b, final Class ac, final Class bc, final Tuple2 pair) {
      Option cached = (Option)this.cache().get(pair);
      Object var10000;
      if (cached != null) {
         Object var6;
         if (.MODULE$.equals(cached)) {
            var6 = this.bindingMissing(a, b);
         } else {
            if (!(cached instanceof Some)) {
               throw new MatchError(cached);
            }

            Some var9 = (Some)cached;
            UFunc.UImpl2 m = (UFunc.UImpl2)var9.value();
            this.breeze$linalg$operators$BinaryRegistry$$l1cache().set(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(pair), var9));
            var6 = m.apply(a, b);
         }

         var10000 = var6;
      } else {
         Map options = this.resolve(ac, bc);
         int var12 = options.size();
         switch (var12) {
            case 0:
               this.cache().put(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ac), bc), .MODULE$);
               var10000 = this.bindingMissing(a, b);
               break;
            case 1:
               UFunc.UImpl2 method = (UFunc.UImpl2)options.values().head();
               this.cache().put(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ac), bc), new Some(method));
               var10000 = method.apply(a, b);
               break;
            default:
               MapView selected = this.selectBestOption(options);
               if (selected.size() != 1) {
                  throw this.multipleOptions(a, b, options);
               }

               UFunc.UImpl2 method = (UFunc.UImpl2)selected.values().head();
               Some some = new Some(method);
               this.breeze$linalg$operators$BinaryRegistry$$l1cache().set(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(pair), some));
               this.cache().put(pair, some);
               var10000 = method.apply(a, b);
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static UFunc.UImpl2 register$(final BinaryRegistry $this, final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
      return $this.register(op, cA, cB);
   }

   default UFunc.UImpl2 register(final UFunc.UImpl2 op, final ClassTag cA, final ClassTag cB) {
      this.breeze$linalg$operators$BinaryRegistry$$super$register(cA.runtimeClass(), cB.runtimeClass(), op);
      return op;
   }

   static void $init$(final BinaryRegistry $this) {
      $this.breeze$linalg$operators$BinaryRegistry$_setter_$breeze$linalg$operators$BinaryRegistry$$l1cache_$eq(new ThreadLocal());
   }
}
