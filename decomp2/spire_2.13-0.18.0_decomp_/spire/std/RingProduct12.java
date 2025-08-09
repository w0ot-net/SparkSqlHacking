package spire.std;

import algebra.ring.Ring;
import scala.Tuple12;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001db\u0001\u0003\t\u0012!\u0003\r\taE\u000b\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\u0019\u0004a1A4\t\u000b%\u0004a1\u00016\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001A\u0011IA\f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003K\u0011QBU5oOB\u0013x\u000eZ;diF\u0012$B\u0001\n\u0014\u0003\r\u0019H\u000f\u001a\u0006\u0002)\u0005)1\u000f]5sKViacM\u001fA\u0007\u001aKEj\u0014*V1n\u001bB\u0001A\f\u001e;B\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u00042AH\u0016/\u001d\ty\u0002F\u0004\u0002!M9\u0011\u0011%J\u0007\u0002E)\u00111\u0005J\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tA#\u0003\u0002('\u00059\u0011\r\\4fEJ\f\u0017BA\u0015+\u0003\u001d\u0001\u0018mY6bO\u0016T!aJ\n\n\u00051j#\u0001\u0002*j]\u001eT!!\u000b\u0016\u0011\u001day\u0013\u0007P C\u000b\"[e*\u0015+X5&\u0011\u0001'\u0007\u0002\b)V\u0004H.Z\u00193!\t\u00114\u0007\u0004\u0001\u0005\u000bQ\u0002!\u0019A\u001b\u0003\u0003\u0005\u000b\"AN\u001d\u0011\u0005a9\u0014B\u0001\u001d\u001a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0007\u001e\n\u0005mJ\"aA!osB\u0011!'\u0010\u0003\u0006}\u0001\u0011\r!\u000e\u0002\u0002\u0005B\u0011!\u0007\u0011\u0003\u0006\u0003\u0002\u0011\r!\u000e\u0002\u0002\u0007B\u0011!g\u0011\u0003\u0006\t\u0002\u0011\r!\u000e\u0002\u0002\tB\u0011!G\u0012\u0003\u0006\u000f\u0002\u0011\r!\u000e\u0002\u0002\u000bB\u0011!'\u0013\u0003\u0006\u0015\u0002\u0011\r!\u000e\u0002\u0002\rB\u0011!\u0007\u0014\u0003\u0006\u001b\u0002\u0011\r!\u000e\u0002\u0002\u000fB\u0011!g\u0014\u0003\u0006!\u0002\u0011\r!\u000e\u0002\u0002\u0011B\u0011!G\u0015\u0003\u0006'\u0002\u0011\r!\u000e\u0002\u0002\u0013B\u0011!'\u0016\u0003\u0006-\u0002\u0011\r!\u000e\u0002\u0002\u0015B\u0011!\u0007\u0017\u0003\u00063\u0002\u0011\r!\u000e\u0002\u0002\u0017B\u0011!g\u0017\u0003\u00069\u0002\u0011\r!\u000e\u0002\u0002\u0019BqalX\u0019=\u007f\t+\u0005j\u0013(R)^SV\"A\t\n\u0005\u0001\f\"\u0001\u0004*oOB\u0013x\u000eZ;diF\u0012\u0014A\u0002\u0013j]&$H\u0005F\u0001d!\tAB-\u0003\u0002f3\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002QB\u0019adK\u0019\u0002\u0015M$(/^2ukJ,''F\u0001l!\rq2\u0006P\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#\u00018\u0011\u0007yYs(\u0001\u0006tiJ,8\r^;sKR*\u0012!\u001d\t\u0004=-\u0012\u0015AC:ueV\u001cG/\u001e:fkU\tA\u000fE\u0002\u001fW\u0015\u000b!b\u001d;sk\u000e$XO]37+\u00059\bc\u0001\u0010,\u0011\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0003i\u00042AH\u0016L\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002{B\u0019ad\u000b(\u0002\u0015M$(/^2ukJ,\u0017(\u0006\u0002\u0002\u0002A\u0019adK)\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003\u000f\u00012AH\u0016U\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u00055\u0001c\u0001\u0010,/\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\t\u0019\u0002E\u0002\u001fWi\u000bqA\u001a:p[&sG\u000fF\u0002/\u00033Aq!a\u0007\u000f\u0001\u0004\ti\"\u0001\u0002yaA\u0019\u0001$a\b\n\u0007\u0005\u0005\u0012DA\u0002J]R\f1a\u001c8f+\u0005q\u0003"
)
public interface RingProduct12 extends Ring, RngProduct12 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   Ring structure6();

   Ring structure7();

   Ring structure8();

   Ring structure9();

   Ring structure10();

   Ring structure11();

   Ring structure12();

   // $FF: synthetic method
   static Tuple12 fromInt$(final RingProduct12 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple12 fromInt(final int x0) {
      return new Tuple12(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0), this.structure12().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple12 one$(final RingProduct12 $this) {
      return $this.one();
   }

   default Tuple12 one() {
      return new Tuple12(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one(), this.structure12().one());
   }

   static void $init$(final RingProduct12 $this) {
   }
}
