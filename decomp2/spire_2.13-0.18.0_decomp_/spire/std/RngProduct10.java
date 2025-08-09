package spire.std;

import algebra.ring.Rng;
import scala.Tuple10;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4\u0001\"\u0004\b\u0011\u0002\u0007\u0005\u0001C\u0005\u0005\u00061\u0002!\t!\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u00021\u0019a\u001a\u0005\u0006S\u00021\u0019A\u001b\u0005\u0006Y\u00021\u0019!\u001c\u0005\u0006_\u00021\u0019\u0001\u001d\u0005\u0006e\u00021\u0019a\u001d\u0005\u0006k\u00021\u0019A\u001e\u0005\u0006q\u00021\u0019!\u001f\u0005\u0006w\u0002!\t\u0001 \u0002\r%:<\u0007K]8ek\u000e$\u0018\u0007\r\u0006\u0003\u001fA\t1a\u001d;e\u0015\u0005\t\u0012!B:qSJ,WcC\n1uu\u00025IR%M\u001fJ\u001bB\u0001\u0001\u000b\u001b)B\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u00042a\u0007\u0015,\u001d\taRE\u0004\u0002\u001eG9\u0011aDI\u0007\u0002?)\u0011\u0001%I\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0011#\u0003\u0002%!\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0014(\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\n\t\n\u0005%R#a\u0001*oO*\u0011ae\n\t\r+1r\u0013\bP C\u000b\"[e*U\u0005\u0003[Y\u0011q\u0001V;qY\u0016\f\u0004\u0007\u0005\u00020a1\u0001A!B\u0019\u0001\u0005\u0004\u0011$!A!\u0012\u0005M2\u0004CA\u000b5\u0013\t)dCA\u0004O_RD\u0017N\\4\u0011\u0005U9\u0014B\u0001\u001d\u0017\u0005\r\te.\u001f\t\u0003_i\"Qa\u000f\u0001C\u0002I\u0012\u0011A\u0011\t\u0003_u\"QA\u0010\u0001C\u0002I\u0012\u0011a\u0011\t\u0003_\u0001#Q!\u0011\u0001C\u0002I\u0012\u0011\u0001\u0012\t\u0003_\r#Q\u0001\u0012\u0001C\u0002I\u0012\u0011!\u0012\t\u0003_\u0019#Qa\u0012\u0001C\u0002I\u0012\u0011A\u0012\t\u0003_%#QA\u0013\u0001C\u0002I\u0012\u0011a\u0012\t\u0003_1#Q!\u0014\u0001C\u0002I\u0012\u0011\u0001\u0013\t\u0003_=#Q\u0001\u0015\u0001C\u0002I\u0012\u0011!\u0013\t\u0003_I#Qa\u0015\u0001C\u0002I\u0012\u0011A\u0013\t\r+Zs\u0013\bP C\u000b\"[e*U\u0007\u0002\u001d%\u0011qK\u0004\u0002\u0012'\u0016l\u0017N]5oOB\u0013x\u000eZ;diF\u0002\u0014A\u0002\u0013j]&$H\u0005F\u0001[!\t)2,\u0003\u0002]-\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002?B\u00191\u0004\u000b\u0018\u0002\u0015M$(/^2ukJ,''F\u0001c!\rY\u0002&O\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A3\u0011\u0007mAC(\u0001\u0006tiJ,8\r^;sKR*\u0012\u0001\u001b\t\u00047!z\u0014AC:ueV\u001cG/\u001e:fkU\t1\u000eE\u0002\u001cQ\t\u000b!b\u001d;sk\u000e$XO]37+\u0005q\u0007cA\u000e)\u000b\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0003E\u00042a\u0007\u0015I\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002iB\u00191\u0004K&\u0002\u0015M$(/^2ukJ,\u0017(F\u0001x!\rY\u0002FT\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'F\u0001{!\rY\u0002&U\u0001\u0007]\u0016<\u0017\r^3\u0015\u0005-j\b\"\u0002@\r\u0001\u0004Y\u0013A\u0001=1\u0001"
)
public interface RngProduct10 extends Rng, SemiringProduct10 {
   Rng structure1();

   Rng structure2();

   Rng structure3();

   Rng structure4();

   Rng structure5();

   Rng structure6();

   Rng structure7();

   Rng structure8();

   Rng structure9();

   Rng structure10();

   // $FF: synthetic method
   static Tuple10 negate$(final RngProduct10 $this, final Tuple10 x0) {
      return $this.negate(x0);
   }

   default Tuple10 negate(final Tuple10 x0) {
      return new Tuple10(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()), this.structure3().negate(x0._3()), this.structure4().negate(x0._4()), this.structure5().negate(x0._5()), this.structure6().negate(x0._6()), this.structure7().negate(x0._7()), this.structure8().negate(x0._8()), this.structure9().negate(x0._9()), this.structure10().negate(x0._10()));
   }

   static void $init$(final RngProduct10 $this) {
   }
}
