package spire.std;

import cats.kernel.Monoid;
import scala.Tuple20;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de\u0001C\f\u0019!\u0003\r\tA\u0007\u000f\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u00111\u0002\u0001\u0007\u0004\u00055\u0001bBA\t\u0001\u0019\r\u00111\u0003\u0005\b\u0003/\u0001a1AA\r\u0011\u001d\ti\u0002\u0001D\u0002\u0003?Aq!a\t\u0001\r\u0007\t)\u0003C\u0004\u0002*\u00011\u0019!a\u000b\t\u000f\u0005=\u0002Ab\u0001\u00022!9\u0011Q\u0007\u0001\u0007\u0004\u0005]\u0002bBA\u001e\u0001\u0019\r\u0011Q\b\u0005\b\u0003\u0003\u0002a1AA\"\u0011\u001d\t9\u0005\u0001D\u0002\u0003\u0013Bq!!\u0014\u0001\r\u0007\ty\u0005C\u0004\u0002T\u00011\u0019!!\u0016\t\u000f\u0005e\u0003Ab\u0001\u0002\\!9\u0011q\f\u0001\u0007\u0004\u0005\u0005\u0004bBA3\u0001\u0019\r\u0011q\r\u0005\b\u0003W\u0002a1AA7\u0011\u001d\t\t\b\u0001D\u0002\u0003gBq!a\u001e\u0001\r\u0007\tI\bC\u0004\u0002~\u00011\u0019!a \t\u000f\u0005\r\u0005\u0001\"\u0001\u0002\u0006\nyQj\u001c8pS\u0012\u0004&o\u001c3vGR\u0014\u0004G\u0003\u0002\u001a5\u0005\u00191\u000f\u001e3\u000b\u0003m\tQa\u001d9je\u0016,R#\b\u001eE\u000f*k\u0005k\u0015,Z9~\u0013W\r[6ocR<(p\u0005\u0003\u0001=\u0011b\bCA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g\rE\u0002&eUr!AJ\u0018\u000f\u0005\u001djcB\u0001\u0015-\u001b\u0005I#B\u0001\u0016,\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u000e\n\u00059R\u0012aB1mO\u0016\u0014'/Y\u0005\u0003aE\nq\u0001]1dW\u0006<WM\u0003\u0002/5%\u00111\u0007\u000e\u0002\u0007\u001b>tw.\u001b3\u000b\u0005A\n\u0004CF\u00107q\r3\u0015\nT(S+b[f,\u00193hU6\u00048O^=\n\u0005]\u0002#a\u0002+va2,'\u0007\r\t\u0003sib\u0001\u0001B\u0003<\u0001\t\u0007AHA\u0001B#\ti\u0004\t\u0005\u0002 }%\u0011q\b\t\u0002\b\u001d>$\b.\u001b8h!\ty\u0012)\u0003\u0002CA\t\u0019\u0011I\\=\u0011\u0005e\"E!B#\u0001\u0005\u0004a$!\u0001\"\u0011\u0005e:E!\u0002%\u0001\u0005\u0004a$!A\"\u0011\u0005eRE!B&\u0001\u0005\u0004a$!\u0001#\u0011\u0005ejE!\u0002(\u0001\u0005\u0004a$!A#\u0011\u0005e\u0002F!B)\u0001\u0005\u0004a$!\u0001$\u0011\u0005e\u001aF!\u0002+\u0001\u0005\u0004a$!A$\u0011\u0005e2F!B,\u0001\u0005\u0004a$!\u0001%\u0011\u0005eJF!\u0002.\u0001\u0005\u0004a$!A%\u0011\u0005ebF!B/\u0001\u0005\u0004a$!\u0001&\u0011\u0005ezF!\u00021\u0001\u0005\u0004a$!A&\u0011\u0005e\u0012G!B2\u0001\u0005\u0004a$!\u0001'\u0011\u0005e*G!\u00024\u0001\u0005\u0004a$!A'\u0011\u0005eBG!B5\u0001\u0005\u0004a$!\u0001(\u0011\u0005eZG!\u00027\u0001\u0005\u0004a$!A(\u0011\u0005erG!B8\u0001\u0005\u0004a$!\u0001)\u0011\u0005e\nH!\u0002:\u0001\u0005\u0004a$!A)\u0011\u0005e\"H!B;\u0001\u0005\u0004a$!\u0001*\u0011\u0005e:H!\u0002=\u0001\u0005\u0004a$!A*\u0011\u0005eRH!B>\u0001\u0005\u0004a$!\u0001+\u0011-ut\bh\u0011$J\u0019>\u0013V\u000bW._C\u0012<'.\u001c9tmfl\u0011\u0001G\u0005\u0003\u007fb\u0011!cU3nS\u001e\u0014x.\u001e9Qe>$Wo\u0019;3a\u00051A%\u001b8ji\u0012\"\"!!\u0002\u0011\u0007}\t9!C\u0002\u0002\n\u0001\u0012A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0005\u0005=\u0001cA\u00133q\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0005\u0005U\u0001cA\u00133\u0007\u0006Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0005\u0005m\u0001cA\u00133\r\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0005\u0005\u0005\u0002cA\u00133\u0013\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0005\u0005\u001d\u0002cA\u00133\u0019\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0005\u00055\u0002cA\u00133\u001f\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005M\u0002cA\u00133%\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005e\u0002cA\u00133+\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005}\u0002cA\u001331\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t)\u0005E\u0002&em\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\n\t\u0004KIr\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0015\u0011\u0007\u0015\u0012\u0014-A\u0006tiJ,8\r^;sKF\u001aTCAA,!\r)#\u0007Z\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002^A\u0019QEM4\u0002\u0017M$(/^2ukJ,\u0017'N\u000b\u0003\u0003G\u00022!\n\u001ak\u0003-\u0019HO];diV\u0014X-\r\u001c\u0016\u0005\u0005%\u0004cA\u00133[\u0006Y1\u000f\u001e:vGR,(/Z\u00198+\t\ty\u0007E\u0002&eA\f1b\u001d;sk\u000e$XO]32qU\u0011\u0011Q\u000f\t\u0004KI\u001a\u0018aC:ueV\u001cG/\u001e:fce*\"!a\u001f\u0011\u0007\u0015\u0012d/A\u0006tiJ,8\r^;sKJ\u0002TCAAA!\r)#'_\u0001\u0006K6\u0004H/_\u000b\u0002k\u0001"
)
public interface MonoidProduct20 extends Monoid, SemigroupProduct20 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   Monoid structure7();

   Monoid structure8();

   Monoid structure9();

   Monoid structure10();

   Monoid structure11();

   Monoid structure12();

   Monoid structure13();

   Monoid structure14();

   Monoid structure15();

   Monoid structure16();

   Monoid structure17();

   Monoid structure18();

   Monoid structure19();

   Monoid structure20();

   // $FF: synthetic method
   static Tuple20 empty$(final MonoidProduct20 $this) {
      return $this.empty();
   }

   default Tuple20 empty() {
      return new Tuple20(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty(), this.structure14().empty(), this.structure15().empty(), this.structure16().empty(), this.structure17().empty(), this.structure18().empty(), this.structure19().empty(), this.structure20().empty());
   }

   static void $init$(final MonoidProduct20 $this) {
   }
}
