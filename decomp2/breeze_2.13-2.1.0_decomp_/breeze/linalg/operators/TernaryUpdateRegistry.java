package breeze.linalg.operators;

import breeze.generic.MMRegistry3;
import breeze.generic.UFunc;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005UeaB\u0004\t!\u0003\r\ta\u0004\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006\u0017\u0002!\t\u0002\u0014\u0005\u0006'\u0002!\t\u0002\u0016\u0005\b\u0003\u0013\u0001A\u0011AA\u0006\u0011\u001d\t\u0019\u0002\u0001C\u0001\u0003+Aa\"!\u0017\u0001!\u0003\r\t\u0011!C\u0005\u00037\n\u0019JA\u000bUKJt\u0017M]=Va\u0012\fG/\u001a*fO&\u001cHO]=\u000b\u0005%Q\u0011!C8qKJ\fGo\u001c:t\u0015\tYA\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u001b\u00051!M]3fu\u0016\u001c\u0001!F\u0003\u0011[A\u001a4e\u0005\u0003\u0001#])\u0004C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\r\u0005\u0004\u0019=\u0005bsF\r\b\u00033qi\u0011A\u0007\u0006\u000371\tqaZ3oKJL7-\u0003\u0002\u001e5\u0005)QKR;oG&\u0011q\u0004\t\u0002\r\u0013:\u0004F.Y2f\u00136\u0004Hn\r\u0006\u0003;i\u0001\"AI\u0012\r\u0001\u0011)A\u0005\u0001b\u0001K\t\u0011q\n]\t\u0003M%\u0002\"AE\u0014\n\u0005!\u001a\"a\u0002(pi\"Lgn\u001a\t\u0003%)J!aK\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002#[\u0011)a\u0006\u0001b\u0001K\t\t\u0011\t\u0005\u0002#a\u0011)\u0011\u0007\u0001b\u0001K\t\t!\t\u0005\u0002#g\u0011)A\u0007\u0001b\u0001K\t\t1\tE\u0002\u001amaJ!a\u000e\u000e\u0003\u00175k%+Z4jgR\u0014\u0018p\r\u0019\u0005smz4\t\u0005\u0004\u0019=\u0005RdH\u0011\t\u0003Em\"\u0011\u0002\u0010\u0001\u0002\u0002\u0003\u0005)\u0011A\u001f\u0003\u0007}#\u0013'\u0005\u0002'YA\u0011!e\u0010\u0003\n\u0001\u0002\t\t\u0011!A\u0003\u0002\u0005\u00131a\u0018\u00133#\t1s\u0006\u0005\u0002#\u0007\u0012IA\tAA\u0001\u0002\u0003\u0015\t!\u0012\u0002\u0004?\u0012\u001a\u0014C\u0001\u00143\u0003\u0019!\u0013N\\5uIQ\t\u0001\n\u0005\u0002\u0013\u0013&\u0011!j\u0005\u0002\u0005+:LG/\u0001\bcS:$\u0017N\\4NSN\u001c\u0018N\\4\u0015\t!ku*\u0015\u0005\u0006\u001d\n\u0001\r\u0001L\u0001\u0002C\")\u0001K\u0001a\u0001_\u0005\t!\rC\u0003S\u0005\u0001\u0007!'A\u0001d\u0003=iW\u000f\u001c;ja2,w\n\u001d;j_:\u001cH#\u0002%V-^C\u0006\"\u0002(\u0004\u0001\u0004a\u0003\"\u0002)\u0004\u0001\u0004y\u0003\"\u0002*\u0004\u0001\u0004\u0011\u0004\"B-\u0004\u0001\u0004Q\u0016!A7\u0011\tm\u0013W-\u001f\b\u00039\u0002\u0004\"!X\n\u000e\u0003yS!a\u0018\b\u0002\rq\u0012xn\u001c;?\u0013\t\t7#\u0001\u0004Qe\u0016$WMZ\u0005\u0003G\u0012\u00141!T1q\u0015\t\t7\u0003E\u0003\u0013M\"|G/\u0003\u0002h'\t1A+\u001e9mKN\u0002$![7\u0011\u0007mSG.\u0003\u0002lI\n)1\t\\1tgB\u0011!%\u001c\u0003\n]b\u000b\t\u0011!A\u0003\u0002\u0015\u00121a\u0018\u00135a\t\u0001(\u000fE\u0002\\UF\u0004\"A\t:\u0005\u0013MD\u0016\u0011!A\u0001\u0006\u0003)#aA0%kA\u0012Qo\u001e\t\u00047*4\bC\u0001\u0012x\t%A\b,!A\u0001\u0002\u000b\u0005QEA\u0002`IY\u0002TA\u001f?\u0000\u0003\u000b\u0001r\u0001\u0007\u0010\"wz\f\u0019\u0001\u0005\u0002#y\u0012IQ\u0010WA\u0001\u0002\u0003\u0015\t!\u0010\u0002\u0004?\u0012:\u0004C\u0001\u0012\u0000\t)\t\t\u0001WA\u0001\u0002\u0003\u0015\t!\u0011\u0002\u0004?\u0012B\u0004c\u0001\u0012\u0002\u0006\u0011Q\u0011q\u0001-\u0002\u0002\u0003\u0005)\u0011A#\u0003\u0007}#\u0013(A\u0003baBd\u0017\u0010F\u0004I\u0003\u001b\ty!!\u0005\t\u000b9#\u0001\u0019\u0001\u0017\t\u000bA#\u0001\u0019A\u0018\t\u000bI#\u0001\u0019\u0001\u001a\u0002\u0011I,w-[:uKJ,\u0002\"a\u0006\u0002.\u0005e\u0012Q\t\u000b\u0005\u00033\tI\u0005F\u0004I\u00037\t\t$!\u0010\t\u000f\u0005uQ\u0001q\u0001\u0002 \u0005!Q.\u00198B!\u0019\t\t#a\n\u0002,5\u0011\u00111\u0005\u0006\u0004\u0003K\u0019\u0012a\u0002:fM2,7\r^\u0005\u0005\u0003S\t\u0019C\u0001\u0005DY\u0006\u001c8\u000fV1h!\r\u0011\u0013Q\u0006\u0003\u0007\u0003_)!\u0019A\u001f\u0003\u0005\u0005\u000b\u0005bBA\u001a\u000b\u0001\u000f\u0011QG\u0001\u0005[\u0006t'\t\u0005\u0004\u0002\"\u0005\u001d\u0012q\u0007\t\u0004E\u0005eBABA\u001e\u000b\t\u0007\u0011I\u0001\u0002C\u0005\"9\u0011qH\u0003A\u0004\u0005\u0005\u0013\u0001B7b]\u000e\u0003b!!\t\u0002(\u0005\r\u0003c\u0001\u0012\u0002F\u00111\u0011qI\u0003C\u0002\u0015\u0013!aQ\"\t\u000f\u0005-S\u00011\u0001\u0002N\u0005\u0011q\u000e\u001d\t\u000b\u0003\u001fr\u0012%a\u000b\u00028\u0005\rcbAA)99!\u00111KA,\u001d\ri\u0016QK\u0005\u0002\u001b%\u00111\u0004D\u0001\u000fgV\u0004XM\u001d\u0013sK\u001eL7\u000f^3s)%A\u0015QLA5\u0003k\n\t\t\u0003\u0004O\r\u0001\u0007\u0011q\f\u0019\u0005\u0003C\n)\u0007\u0005\u0003\\U\u0006\r\u0004c\u0001\u0012\u0002f\u0011Y\u0011qMA/\u0003\u0003\u0005\tQ!\u0001&\u0005\u0011yFEM\u001d\t\rA3\u0001\u0019AA6a\u0011\ti'!\u001d\u0011\tmS\u0017q\u000e\t\u0004E\u0005EDaCA:\u0003S\n\t\u0011!A\u0003\u0002\u0015\u0012Aa\u0018\u00134a!1!K\u0002a\u0001\u0003o\u0002D!!\u001f\u0002~A!1L[A>!\r\u0011\u0013Q\u0010\u0003\f\u0003\u007f\n)(!A\u0001\u0002\u000b\u0005QE\u0001\u0003`IM\n\u0004bBA&\r\u0001\u0007\u00111\u0011\u0019\t\u0003\u000b\u000bI)!$\u0002\u0012BI\u0001DH\u0011\u0002\b\u0006-\u0015q\u0012\t\u0004E\u0005%EA\u0003\u001f\u0002\u0002\u0006\u0005\t\u0011!B\u0001{A\u0019!%!$\u0005\u0015\u0001\u000b\t)!A\u0001\u0002\u000b\u0005\u0011\tE\u0002#\u0003##!\u0002RAA\u0003\u0003\u0005\tQ!\u0001F\u0013\r\t\u0019B\u000e"
)
public interface TernaryUpdateRegistry extends UFunc.InPlaceImpl3, MMRegistry3 {
   // $FF: synthetic method
   void breeze$linalg$operators$TernaryUpdateRegistry$$super$register(final Class a, final Class b, final Class c, final UFunc.InPlaceImpl3 op);

   // $FF: synthetic method
   static void bindingMissing$(final TernaryUpdateRegistry $this, final Object a, final Object b, final Object c) {
      $this.bindingMissing(a, b, c);
   }

   default void bindingMissing(final Object a, final Object b, final Object c) {
      throw new UnsupportedOperationException((new StringBuilder(17)).append("Types not found!").append(a).append(b).append(" ").append(this.ops()).toString());
   }

   // $FF: synthetic method
   static void multipleOptions$(final TernaryUpdateRegistry $this, final Object a, final Object b, final Object c, final Map m) {
      $this.multipleOptions(a, b, c, m);
   }

   default void multipleOptions(final Object a, final Object b, final Object c, final Map m) {
      throw new RuntimeException((new StringBuilder(30)).append("Multiple bindings for method: ").append(m).toString());
   }

   // $FF: synthetic method
   static void apply$(final TernaryUpdateRegistry $this, final Object a, final Object b, final Object c) {
      $this.apply(a, b, c);
   }

   default void apply(final Object a, final Object b, final Object c) {
      Class ac = a.getClass();
      Class bc = b.getClass();
      Class cc = c.getClass();
      Option cached = (Option)this.cache().get(new Tuple3(ac, bc, cc));
      if (cached != null) {
         if (.MODULE$.equals(cached)) {
            this.bindingMissing(a, b, c);
            BoxedUnit var4 = BoxedUnit.UNIT;
         } else {
            if (!(cached instanceof Some)) {
               throw new MatchError(cached);
            }

            Some var10 = (Some)cached;
            UFunc.InPlaceImpl3 m = (UFunc.InPlaceImpl3)var10.value();
            m.apply(a, b, c);
            BoxedUnit var17 = BoxedUnit.UNIT;
         }
      } else {
         Map options = this.resolve(ac, bc, cc);
         int var13 = options.size();
         switch (var13) {
            case 0:
               this.cache().put(new Tuple3(ac, bc, cc), .MODULE$);
               this.bindingMissing(a, b, c);
               break;
            case 1:
               UFunc.InPlaceImpl3 method = (UFunc.InPlaceImpl3)options.values().head();
               this.cache().put(new Tuple3(ac, bc, cc), new Some(method));
               method.apply(a, b, c);
               break;
            default:
               Map selected = this.selectBestOption(options);
               if (selected.size() != 1) {
                  this.multipleOptions(a, b, c, options);
               } else {
                  UFunc.InPlaceImpl3 method = (UFunc.InPlaceImpl3)selected.values().head();
                  this.cache().put(new Tuple3(ac, bc, cc), new Some(method));
                  method.apply(a, b, c);
               }
         }
      }

   }

   // $FF: synthetic method
   static void register$(final TernaryUpdateRegistry $this, final UFunc.InPlaceImpl3 op, final ClassTag manA, final ClassTag manB, final ClassTag manC) {
      $this.register(op, manA, manB, manC);
   }

   default void register(final UFunc.InPlaceImpl3 op, final ClassTag manA, final ClassTag manB, final ClassTag manC) {
      this.breeze$linalg$operators$TernaryUpdateRegistry$$super$register(manA.runtimeClass(), manB.runtimeClass(), manC.runtimeClass(), op);
   }

   static void $init$(final TernaryUpdateRegistry $this) {
   }
}
