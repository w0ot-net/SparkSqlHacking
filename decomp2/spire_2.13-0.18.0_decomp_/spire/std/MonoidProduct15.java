package spire.std;

import cats.kernel.Monoid;
import scala.Tuple15;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001\u0003\n\u0014!\u0003\r\t!F\f\t\u000b1\u0004A\u0011A7\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001A\u0011AA \u0005=iuN\\8jIB\u0013x\u000eZ;diF*$B\u0001\u000b\u0016\u0003\r\u0019H\u000f\u001a\u0006\u0002-\u0005)1\u000f]5sKV\u0001\u0002$N C\u000b\"[e*\u0015+X5v\u00037MZ\n\u0005\u0001ey\u0002\u000e\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0004A5\u0002dBA\u0011+\u001d\t\u0011\u0003F\u0004\u0002$O5\tAE\u0003\u0002&M\u00051AH]8piz\u001a\u0001!C\u0001\u0017\u0013\tIS#A\u0004bY\u001e,'M]1\n\u0005-b\u0013a\u00029bG.\fw-\u001a\u0006\u0003SUI!AL\u0018\u0003\r5{gn\\5e\u0015\tYC\u0006E\t\u001bcMr\u0014\tR$K\u001bB\u001bf+\u0017/`E\u0016L!AM\u000e\u0003\u000fQ+\b\u000f\\32kA\u0011A'\u000e\u0007\u0001\t\u00151\u0004A1\u00018\u0005\u0005\t\u0015C\u0001\u001d<!\tQ\u0012(\u0003\u0002;7\t9aj\u001c;iS:<\u0007C\u0001\u000e=\u0013\ti4DA\u0002B]f\u0004\"\u0001N \u0005\u000b\u0001\u0003!\u0019A\u001c\u0003\u0003\t\u0003\"\u0001\u000e\"\u0005\u000b\r\u0003!\u0019A\u001c\u0003\u0003\r\u0003\"\u0001N#\u0005\u000b\u0019\u0003!\u0019A\u001c\u0003\u0003\u0011\u0003\"\u0001\u000e%\u0005\u000b%\u0003!\u0019A\u001c\u0003\u0003\u0015\u0003\"\u0001N&\u0005\u000b1\u0003!\u0019A\u001c\u0003\u0003\u0019\u0003\"\u0001\u000e(\u0005\u000b=\u0003!\u0019A\u001c\u0003\u0003\u001d\u0003\"\u0001N)\u0005\u000bI\u0003!\u0019A\u001c\u0003\u0003!\u0003\"\u0001\u000e+\u0005\u000bU\u0003!\u0019A\u001c\u0003\u0003%\u0003\"\u0001N,\u0005\u000ba\u0003!\u0019A\u001c\u0003\u0003)\u0003\"\u0001\u000e.\u0005\u000bm\u0003!\u0019A\u001c\u0003\u0003-\u0003\"\u0001N/\u0005\u000by\u0003!\u0019A\u001c\u0003\u00031\u0003\"\u0001\u000e1\u0005\u000b\u0005\u0004!\u0019A\u001c\u0003\u00035\u0003\"\u0001N2\u0005\u000b\u0011\u0004!\u0019A\u001c\u0003\u00039\u0003\"\u0001\u000e4\u0005\u000b\u001d\u0004!\u0019A\u001c\u0003\u0003=\u0003\u0012#\u001b64}\u0005#uIS'Q'ZKFl\u00182f\u001b\u0005\u0019\u0012BA6\u0014\u0005I\u0019V-\\5he>,\b\u000f\u0015:pIV\u001cG/M\u001b\u0002\r\u0011Jg.\u001b;%)\u0005q\u0007C\u0001\u000ep\u0013\t\u00018D\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t1\u000fE\u0002![M\n!b\u001d;sk\u000e$XO]33+\u00051\bc\u0001\u0011.}\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003e\u00042\u0001I\u0017B\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002yB\u0019\u0001%\f#\u0002\u0015M$(/^2ukJ,W'F\u0001\u0000!\r\u0001SfR\u0001\u000bgR\u0014Xo\u0019;ve\u00164TCAA\u0003!\r\u0001SFS\u0001\u000bgR\u0014Xo\u0019;ve\u0016<TCAA\u0006!\r\u0001S&T\u0001\u000bgR\u0014Xo\u0019;ve\u0016DTCAA\t!\r\u0001S\u0006U\u0001\u000bgR\u0014Xo\u0019;ve\u0016LTCAA\f!\r\u0001SfU\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'\u0006\u0002\u0002\u001eA\u0019\u0001%\f,\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003G\u00012\u0001I\u0017Z\u0003-\u0019HO];diV\u0014X-\r\u001a\u0016\u0005\u0005%\u0002c\u0001\u0011.9\u0006Y1\u000f\u001e:vGR,(/Z\u00194+\t\ty\u0003E\u0002![}\u000b1b\u001d;sk\u000e$XO]32iU\u0011\u0011Q\u0007\t\u0004A5\u0012\u0017aC:ueV\u001cG/\u001e:fcU*\"!a\u000f\u0011\u0007\u0001jS-A\u0003f[B$\u00180F\u00011\u0001"
)
public interface MonoidProduct15 extends Monoid, SemigroupProduct15 {
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

   // $FF: synthetic method
   static Tuple15 empty$(final MonoidProduct15 $this) {
      return $this.empty();
   }

   default Tuple15 empty() {
      return new Tuple15(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty(), this.structure14().empty(), this.structure15().empty());
   }

   static void $init$(final MonoidProduct15 $this) {
   }
}
