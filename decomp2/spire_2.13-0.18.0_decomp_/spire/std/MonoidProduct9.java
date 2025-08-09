package spire.std;

import cats.kernel.Monoid;
import scala.Tuple9;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005q\"\u0005\u0005\u0006)\u0002!\t!\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u00021\u0019a\u0019\u0005\u0006K\u00021\u0019A\u001a\u0005\u0006Q\u00021\u0019!\u001b\u0005\u0006W\u00021\u0019\u0001\u001c\u0005\u0006]\u00021\u0019a\u001c\u0005\u0006c\u00021\u0019A\u001d\u0005\u0006i\u0002!\t!\u001e\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;:\u0015\tqq\"A\u0002ti\u0012T\u0011\u0001E\u0001\u0006gBL'/Z\u000b\u000b%=JDh\u0010\"F\u0011.s5\u0003\u0002\u0001\u00143A\u0003\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007c\u0001\u000e(U9\u00111\u0004\n\b\u00039\tr!!H\u0011\u000e\u0003yQ!a\b\u0011\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001E\u0005\u0003G=\tq!\u00197hK\n\u0014\u0018-\u0003\u0002&M\u00059\u0001/Y2lC\u001e,'BA\u0012\u0010\u0013\tA\u0013F\u0001\u0004N_:|\u0017\u000e\u001a\u0006\u0003K\u0019\u00022\u0002F\u0016.qmr\u0014\tR$K\u001b&\u0011A&\u0006\u0002\u0007)V\u0004H.Z\u001d\u0011\u00059zC\u0002\u0001\u0003\u0006a\u0001\u0011\r!\r\u0002\u0002\u0003F\u0011!'\u000e\t\u0003)MJ!\u0001N\u000b\u0003\u000f9{G\u000f[5oOB\u0011ACN\u0005\u0003oU\u00111!\u00118z!\tq\u0013\bB\u0003;\u0001\t\u0007\u0011GA\u0001C!\tqC\bB\u0003>\u0001\t\u0007\u0011GA\u0001D!\tqs\bB\u0003A\u0001\t\u0007\u0011GA\u0001E!\tq#\tB\u0003D\u0001\t\u0007\u0011GA\u0001F!\tqS\tB\u0003G\u0001\t\u0007\u0011GA\u0001G!\tq\u0003\nB\u0003J\u0001\t\u0007\u0011GA\u0001H!\tq3\nB\u0003M\u0001\t\u0007\u0011GA\u0001I!\tqc\nB\u0003P\u0001\t\u0007\u0011GA\u0001J!-\t&+\f\u001d<}\u0005#uIS'\u000e\u00035I!aU\u0007\u0003#M+W.[4s_V\u0004\bK]8ek\u000e$\u0018(\u0001\u0004%S:LG\u000f\n\u000b\u0002-B\u0011AcV\u0005\u00031V\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003m\u00032AG\u0014.\u0003)\u0019HO];diV\u0014XMM\u000b\u0002=B\u0019!d\n\u001d\u0002\u0015M$(/^2ukJ,7'F\u0001b!\rQreO\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u00013\u0011\u0007i9c(\u0001\u0006tiJ,8\r^;sKV*\u0012a\u001a\t\u00045\u001d\n\u0015AC:ueV\u001cG/\u001e:fmU\t!\u000eE\u0002\u001bO\u0011\u000b!b\u001d;sk\u000e$XO]38+\u0005i\u0007c\u0001\u000e(\u000f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003A\u00042AG\u0014K\u0003)\u0019HO];diV\u0014X-O\u000b\u0002gB\u0019!dJ'\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003)\u0002"
)
public interface MonoidProduct9 extends Monoid, SemigroupProduct9 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   Monoid structure7();

   Monoid structure8();

   Monoid structure9();

   // $FF: synthetic method
   static Tuple9 empty$(final MonoidProduct9 $this) {
      return $this.empty();
   }

   default Tuple9 empty() {
      return new Tuple9(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty());
   }

   static void $init$(final MonoidProduct9 $this) {
   }
}
