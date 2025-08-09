package spire.std;

import cats.kernel.Monoid;
import scala.Tuple17;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001\u0003\u000b\u0016!\u0003\r\taF\r\t\u000bQ\u0004A\u0011A;\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u00011\u0019!!\u0010\t\u000f\u0005\u0005\u0003Ab\u0001\u0002D!9\u0011q\t\u0001\u0007\u0004\u0005%\u0003bBA'\u0001\u0019\r\u0011q\n\u0005\b\u0003'\u0002a1AA+\u0011\u001d\tI\u0006\u0001C\u0001\u00037\u0012q\"T8o_&$\u0007K]8ek\u000e$\u0018g\u000e\u0006\u0003-]\t1a\u001d;e\u0015\u0005A\u0012!B:qSJ,WC\u0005\u000e8\u0003\u0012;%*\u0014)T-fcvLY3iW:\u001cB\u0001A\u000e\"aB\u0011AdH\u0007\u0002;)\ta$A\u0003tG\u0006d\u0017-\u0003\u0002!;\t1\u0011I\\=SK\u001a\u00042AI\u00183\u001d\t\u0019CF\u0004\u0002%U9\u0011Q%K\u0007\u0002M)\u0011q\u0005K\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001$\u0003\u0002,/\u00059\u0011\r\\4fEJ\f\u0017BA\u0017/\u0003\u001d\u0001\u0018mY6bO\u0016T!aK\f\n\u0005A\n$AB'p]>LGM\u0003\u0002.]A\u0019BdM\u001bA\u0007\u001aKEj\u0014*V1ns\u0016\rZ4k[&\u0011A'\b\u0002\b)V\u0004H.Z\u00198!\t1t\u0007\u0004\u0001\u0005\u000ba\u0002!\u0019A\u001d\u0003\u0003\u0005\u000b\"AO\u001f\u0011\u0005qY\u0014B\u0001\u001f\u001e\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\b \n\u0005}j\"aA!osB\u0011a'\u0011\u0003\u0006\u0005\u0002\u0011\r!\u000f\u0002\u0002\u0005B\u0011a\u0007\u0012\u0003\u0006\u000b\u0002\u0011\r!\u000f\u0002\u0002\u0007B\u0011ag\u0012\u0003\u0006\u0011\u0002\u0011\r!\u000f\u0002\u0002\tB\u0011aG\u0013\u0003\u0006\u0017\u0002\u0011\r!\u000f\u0002\u0002\u000bB\u0011a'\u0014\u0003\u0006\u001d\u0002\u0011\r!\u000f\u0002\u0002\rB\u0011a\u0007\u0015\u0003\u0006#\u0002\u0011\r!\u000f\u0002\u0002\u000fB\u0011ag\u0015\u0003\u0006)\u0002\u0011\r!\u000f\u0002\u0002\u0011B\u0011aG\u0016\u0003\u0006/\u0002\u0011\r!\u000f\u0002\u0002\u0013B\u0011a'\u0017\u0003\u00065\u0002\u0011\r!\u000f\u0002\u0002\u0015B\u0011a\u0007\u0018\u0003\u0006;\u0002\u0011\r!\u000f\u0002\u0002\u0017B\u0011ag\u0018\u0003\u0006A\u0002\u0011\r!\u000f\u0002\u0002\u0019B\u0011aG\u0019\u0003\u0006G\u0002\u0011\r!\u000f\u0002\u0002\u001bB\u0011a'\u001a\u0003\u0006M\u0002\u0011\r!\u000f\u0002\u0002\u001dB\u0011a\u0007\u001b\u0003\u0006S\u0002\u0011\r!\u000f\u0002\u0002\u001fB\u0011ag\u001b\u0003\u0006Y\u0002\u0011\r!\u000f\u0002\u0002!B\u0011aG\u001c\u0003\u0006_\u0002\u0011\r!\u000f\u0002\u0002#B\u0019\u0012O]\u001bA\u0007\u001aKEj\u0014*V1ns\u0016\rZ4k[6\tQ#\u0003\u0002t+\t\u00112+Z7jOJ|W\u000f\u001d)s_\u0012,8\r^\u00198\u0003\u0019!\u0013N\\5uIQ\ta\u000f\u0005\u0002\u001do&\u0011\u00010\b\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012a\u001f\t\u0004E=*\u0014AC:ueV\u001cG/\u001e:feU\ta\u0010E\u0002#_\u0001\u000b!b\u001d;sk\u000e$XO]34+\t\t\u0019\u0001E\u0002#_\r\u000b!b\u001d;sk\u000e$XO]35+\t\tI\u0001E\u0002#_\u0019\u000b!b\u001d;sk\u000e$XO]36+\t\ty\u0001E\u0002#_%\u000b!b\u001d;sk\u000e$XO]37+\t\t)\u0002E\u0002#_1\u000b!b\u001d;sk\u000e$XO]38+\t\tY\u0002E\u0002#_=\u000b!b\u001d;sk\u000e$XO]39+\t\t\t\u0003E\u0002#_I\u000b!b\u001d;sk\u000e$XO]3:+\t\t9\u0003E\u0002#_U\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u0011Q\u0006\t\u0004E=B\u0016aC:ueV\u001cG/\u001e:fcE*\"!a\r\u0011\u0007\tz3,A\u0006tiJ,8\r^;sKF\u0012TCAA\u001d!\r\u0011sFX\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002@A\u0019!eL1\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003\u000b\u00022AI\u0018e\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u0005-\u0003c\u0001\u00120O\u0006Y1\u000f\u001e:vGR,(/Z\u00197+\t\t\t\u0006E\u0002#_)\f1b\u001d;sk\u000e$XO]32oU\u0011\u0011q\u000b\t\u0004E=j\u0017!B3naRLX#\u0001\u001a"
)
public interface MonoidProduct17 extends Monoid, SemigroupProduct17 {
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

   // $FF: synthetic method
   static Tuple17 empty$(final MonoidProduct17 $this) {
      return $this.empty();
   }

   default Tuple17 empty() {
      return new Tuple17(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty(), this.structure14().empty(), this.structure15().empty(), this.structure16().empty(), this.structure17().empty());
   }

   static void $init$(final MonoidProduct17 $this) {
   }
}
