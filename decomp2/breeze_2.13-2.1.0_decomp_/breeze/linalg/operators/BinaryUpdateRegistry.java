package breeze.linalg.operators;

import breeze.generic.MMRegistry2;
import breeze.generic.UFunc;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Predef.ArrowAssoc.;
import scala.collection.MapView;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ucaB\u0004\t!\u0003\r\ta\u0004\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u0002!\tb\u0013\u0005\u0006!\u0002!\t\"\u0015\u0005\u0006q\u0002!\t!\u001f\u0005\u0006y\u0002!\t! \u0005\u000f\u0003g\u0001\u0001\u0013aA\u0001\u0002\u0013%\u0011QGA.\u0005Q\u0011\u0015N\\1ssV\u0003H-\u0019;f%\u0016<\u0017n\u001d;ss*\u0011\u0011BC\u0001\n_B,'/\u0019;peNT!a\u0003\u0007\u0002\r1Lg.\u00197h\u0015\u0005i\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\tAq#gI\n\u0005\u0001E9\u0002\b\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VM\u001a\t\u00061y\tS&\r\b\u00033qi\u0011A\u0007\u0006\u000371\tqaZ3oKJL7-\u0003\u0002\u001e5\u0005)QKR;oG&\u0011q\u0004\t\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HN\r\u0006\u0003;i\u0001\"AI\u0012\r\u0001\u0011)A\u0005\u0001b\u0001K\t\u0011q\n]\t\u0003M%\u0002\"AE\u0014\n\u0005!\u001a\"a\u0002(pi\"Lgn\u001a\t\u0003U-j\u0011\u0001C\u0005\u0003Y!\u0011aa\u00149UsB,\u0007C\u0001\u0012/\t\u0015y\u0003A1\u00011\u0005\u0005\t\u0015C\u0001\u0014\u0012!\t\u0011#\u0007B\u00034\u0001\t\u0007AGA\u0001C#\t1S\u0007\u0005\u0002\u0013m%\u0011qg\u0005\u0002\u0004\u0003:L\bcA\r:w%\u0011!H\u0007\u0002\f\u001b6\u0013VmZ5tiJL(\u0007M\u0002=}\t\u0003R\u0001\u0007\u0010\"{\u0005\u0003\"A\t \u0005\u0013}\u0002\u0011\u0011!A\u0001\u0006\u0003\u0001%aA0%cE\u0011a%\f\t\u0003E\t#\u0011b\u0011\u0001\u0002\u0002\u0003\u0005)\u0011\u0001#\u0003\u0007}##'\u0005\u0002'c\u00051A%\u001b8ji\u0012\"\u0012a\u0012\t\u0003%!K!!S\n\u0003\tUs\u0017\u000e^\u0001\u000fE&tG-\u001b8h\u001b&\u001c8/\u001b8h)\r9EJ\u0014\u0005\u0006\u001b\n\u0001\r!L\u0001\u0002C\")qJ\u0001a\u0001c\u0005\t!-A\bnk2$\u0018\u000e\u001d7f\u001fB$\u0018n\u001c8t)\u00119%k\u0015+\t\u000b5\u001b\u0001\u0019A\u0017\t\u000b=\u001b\u0001\u0019A\u0019\t\u000bU\u001b\u0001\u0019\u0001,\u0002\u00035\u0004Ba\u00160ba:\u0011\u0001\f\u0018\t\u00033Ni\u0011A\u0017\u0006\u00037:\ta\u0001\u0010:p_Rt\u0014BA/\u0014\u0003\u0019\u0001&/\u001a3fM&\u0011q\f\u0019\u0002\u0004\u001b\u0006\u0004(BA/\u0014!\u0011\u0011\"\rZ6\n\u0005\r\u001c\"A\u0002+va2,'\u0007\r\u0002fSB\u0019qK\u001a5\n\u0005\u001d\u0004'!B\"mCN\u001c\bC\u0001\u0012j\t%QG+!A\u0001\u0002\u000b\u0005AGA\u0002`IM\u0002$\u0001\u001c8\u0011\u0007]3W\u000e\u0005\u0002#]\u0012Iq\u000eVA\u0001\u0002\u0003\u0015\t\u0001\u000e\u0002\u0004?\u0012\"\u0004gA9tmB)\u0001DH\u0011skB\u0011!e\u001d\u0003\niR\u000b\t\u0011!A\u0003\u0002\u0001\u00131a\u0018\u00136!\t\u0011c\u000fB\u0005x)\u0006\u0005\t\u0011!B\u0001\t\n\u0019q\f\n\u001c\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u001dS8\u0010C\u0003N\t\u0001\u0007Q\u0006C\u0003P\t\u0001\u0007\u0011'\u0001\u0005sK\u001eL7\u000f^3s+\u0015q\u0018qBA\u000b)\ry\u0018q\u0006\u000b\u0007\u0003\u0003\tI\"!\u000b\u0011\u0011\u0005\ra$IA\u0007\u0003'q1!!\u0002\u001d\u001d\u0011\t9!a\u0003\u000f\u0007e\u000bI!C\u0001\u000e\u0013\tYB\u0002E\u0002#\u0003\u001f!a!!\u0005\u0006\u0005\u0004\u0001%AA!B!\r\u0011\u0013Q\u0003\u0003\u0007\u0003/)!\u0019\u0001#\u0003\u0005\t\u0013\u0005bBA\u000e\u000b\u0001\u000f\u0011QD\u0001\u0003G\u0006\u0003b!a\b\u0002&\u00055QBAA\u0011\u0015\r\t\u0019cE\u0001\be\u00164G.Z2u\u0013\u0011\t9#!\t\u0003\u0011\rc\u0017m]:UC\u001eDq!a\u000b\u0006\u0001\b\ti#\u0001\u0002d\u0005B1\u0011qDA\u0013\u0003'Aq!!\r\u0006\u0001\u0004\t\t!\u0001\u0002pa\u0006q1/\u001e9fe\u0012\u0012XmZ5ti\u0016\u0014HcB$\u00028\u0005\u0005\u0013Q\n\u0005\u0007\u001b\u001a\u0001\r!!\u000f1\t\u0005m\u0012q\b\t\u0005/\u001a\fi\u0004E\u0002#\u0003\u007f!!b^A\u001c\u0003\u0003\u0005\tQ!\u00015\u0011\u0019ye\u00011\u0001\u0002DA\"\u0011QIA%!\u00119f-a\u0012\u0011\u0007\t\nI\u0005B\u0006\u0002L\u0005\u0005\u0013\u0011!A\u0001\u0006\u0003!$aA0%o!9\u0011\u0011\u0007\u0004A\u0002\u0005=\u0003GBA)\u0003+\nI\u0006E\u0004\u0019=\u0005\n\u0019&a\u0016\u0011\u0007\t\n)\u0006\u0002\u0006@\u0003\u001b\n\t\u0011!A\u0003\u0002\u0001\u00032AIA-\t)\u0019\u0015QJA\u0001\u0002\u0003\u0015\t\u0001R\u0005\u0003yf\u0002"
)
public interface BinaryUpdateRegistry extends UFunc.InPlaceImpl2, MMRegistry2 {
   // $FF: synthetic method
   void breeze$linalg$operators$BinaryUpdateRegistry$$super$register(final Class a, final Class b, final UFunc.InPlaceImpl2 op);

   // $FF: synthetic method
   static void bindingMissing$(final BinaryUpdateRegistry $this, final Object a, final Object b) {
      $this.bindingMissing(a, b);
   }

   default void bindingMissing(final Object a, final Object b) {
      throw new UnsupportedOperationException((new StringBuilder(17)).append("Types not found!").append(a).append(b).append(" ").append(this.ops()).toString());
   }

   // $FF: synthetic method
   static void multipleOptions$(final BinaryUpdateRegistry $this, final Object a, final Object b, final Map m) {
      $this.multipleOptions(a, b, m);
   }

   default void multipleOptions(final Object a, final Object b, final Map m) {
      throw new RuntimeException((new StringBuilder(30)).append("Multiple bindings for method: ").append(m).toString());
   }

   // $FF: synthetic method
   static void apply$(final BinaryUpdateRegistry $this, final Object a, final Object b) {
      $this.apply(a, b);
   }

   default void apply(final Object a, final Object b) {
      Class ac = a.getClass();
      Class bc = b.getClass();
      Option cached = (Option)this.cache().get(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ac), bc));
      if (cached != null) {
         if (scala.None..MODULE$.equals(cached)) {
            this.bindingMissing(a, b);
            BoxedUnit var3 = BoxedUnit.UNIT;
         } else {
            if (!(cached instanceof Some)) {
               throw new MatchError(cached);
            }

            Some var8 = (Some)cached;
            UFunc.InPlaceImpl2 m = (UFunc.InPlaceImpl2)var8.value();
            m.apply(a, b);
            BoxedUnit var15 = BoxedUnit.UNIT;
         }
      } else {
         Map options = this.resolve(ac, bc);
         int var11 = options.size();
         switch (var11) {
            case 0:
               this.cache().put(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ac), bc), scala.None..MODULE$);
               this.bindingMissing(a, b);
               break;
            case 1:
               UFunc.InPlaceImpl2 method = (UFunc.InPlaceImpl2)options.values().head();
               this.cache().put(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ac), bc), new Some(method));
               method.apply(a, b);
               break;
            default:
               MapView selected = this.selectBestOption(options);
               if (selected.size() != 1) {
                  this.multipleOptions(a, b, options);
               } else {
                  UFunc.InPlaceImpl2 method = (UFunc.InPlaceImpl2)selected.values().head();
                  this.cache().put(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(ac), bc), new Some(method));
                  method.apply(a, b);
               }
         }
      }

   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 register$(final BinaryUpdateRegistry $this, final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
      return $this.register(op, cA, cB);
   }

   default UFunc.InPlaceImpl2 register(final UFunc.InPlaceImpl2 op, final ClassTag cA, final ClassTag cB) {
      this.breeze$linalg$operators$BinaryUpdateRegistry$$super$register(cA.runtimeClass(), cB.runtimeClass(), op);
      return op;
   }

   static void $init$(final BinaryUpdateRegistry $this) {
   }
}
