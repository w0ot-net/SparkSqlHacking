package spire.std;

import cats.kernel.Group;
import scala.Tuple17;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d\u0001\u0003\u000b\u0016!\u0003\r\taF\r\t\u000bQ\u0004A\u0011A;\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u00011\u0019!!\u0010\t\u000f\u0005\u0005\u0003Ab\u0001\u0002D!9\u0011q\t\u0001\u0007\u0004\u0005%\u0003bBA'\u0001\u0019\r\u0011q\n\u0005\b\u0003'\u0002a1AA+\u0011\u001d\tI\u0006\u0001C\u0001\u00037\u0012ab\u0012:pkB\u0004&o\u001c3vGR\ftG\u0003\u0002\u0017/\u0005\u00191\u000f\u001e3\u000b\u0003a\tQa\u001d9je\u0016,\"CG\u001cB\t\u001eSU\nU*W3r{&-\u001a5l]N!\u0001aG\u0011q!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fMB\u0019!e\f\u001a\u000f\u0005\rbcB\u0001\u0013+\u001d\t)\u0013&D\u0001'\u0015\t9\u0003&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005A\u0012BA\u0016\u0018\u0003\u001d\tGnZ3ce\u0006L!!\f\u0018\u0002\u000fA\f7m[1hK*\u00111fF\u0005\u0003aE\u0012Qa\u0012:pkBT!!\f\u0018\u0011'q\u0019T\u0007Q\"G\u00132{%+\u0016-\\=\u0006$wM[7\n\u0005Qj\"a\u0002+va2,\u0017g\u000e\t\u0003m]b\u0001\u0001B\u00039\u0001\t\u0007\u0011HA\u0001B#\tQT\b\u0005\u0002\u001dw%\u0011A(\b\u0002\b\u001d>$\b.\u001b8h!\tab(\u0003\u0002@;\t\u0019\u0011I\\=\u0011\u0005Y\nE!\u0002\"\u0001\u0005\u0004I$!\u0001\"\u0011\u0005Y\"E!B#\u0001\u0005\u0004I$!A\"\u0011\u0005Y:E!\u0002%\u0001\u0005\u0004I$!\u0001#\u0011\u0005YRE!B&\u0001\u0005\u0004I$!A#\u0011\u0005YjE!\u0002(\u0001\u0005\u0004I$!\u0001$\u0011\u0005Y\u0002F!B)\u0001\u0005\u0004I$!A$\u0011\u0005Y\u001aF!\u0002+\u0001\u0005\u0004I$!\u0001%\u0011\u0005Y2F!B,\u0001\u0005\u0004I$!A%\u0011\u0005YJF!\u0002.\u0001\u0005\u0004I$!\u0001&\u0011\u0005YbF!B/\u0001\u0005\u0004I$!A&\u0011\u0005YzF!\u00021\u0001\u0005\u0004I$!\u0001'\u0011\u0005Y\u0012G!B2\u0001\u0005\u0004I$!A'\u0011\u0005Y*G!\u00024\u0001\u0005\u0004I$!\u0001(\u0011\u0005YBG!B5\u0001\u0005\u0004I$!A(\u0011\u0005YZG!\u00027\u0001\u0005\u0004I$!\u0001)\u0011\u0005YrG!B8\u0001\u0005\u0004I$!A)\u0011'E\u0014X\u0007Q\"G\u00132{%+\u0016-\\=\u0006$wM[7\u000e\u0003UI!a]\u000b\u0003\u001f5{gn\\5e!J|G-^2uc]\na\u0001J5oSR$C#\u0001<\u0011\u0005q9\u0018B\u0001=\u001e\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001|!\r\u0011s&N\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#\u0001@\u0011\u0007\tz\u0003)\u0001\u0006tiJ,8\r^;sKN*\"!a\u0001\u0011\u0007\tz3)\u0001\u0006tiJ,8\r^;sKR*\"!!\u0003\u0011\u0007\tzc)\u0001\u0006tiJ,8\r^;sKV*\"!a\u0004\u0011\u0007\tz\u0013*\u0001\u0006tiJ,8\r^;sKZ*\"!!\u0006\u0011\u0007\tzC*\u0001\u0006tiJ,8\r^;sK^*\"!a\u0007\u0011\u0007\tzs*\u0001\u0006tiJ,8\r^;sKb*\"!!\t\u0011\u0007\tz#+\u0001\u0006tiJ,8\r^;sKf*\"!a\n\u0011\u0007\tzS+A\u0006tiJ,8\r^;sKF\u0002TCAA\u0017!\r\u0011s\u0006W\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u00024A\u0019!eL.\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u0003s\u00012AI\u0018_\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005}\u0002c\u0001\u00120C\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\t)\u0005E\u0002#_\u0011\f1b\u001d;sk\u000e$XO]32kU\u0011\u00111\n\t\u0004E=:\u0017aC:ueV\u001cG/\u001e:fcY*\"!!\u0015\u0011\u0007\tz#.A\u0006tiJ,8\r^;sKF:TCAA,!\r\u0011s&\\\u0001\bS:4XM]:f)\r\u0011\u0014Q\f\u0005\u0007\u0003?\u001a\u0002\u0019\u0001\u001a\u0002\u0005a\u0004\u0004"
)
public interface GroupProduct17 extends Group, MonoidProduct17 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   Group structure8();

   Group structure9();

   Group structure10();

   Group structure11();

   Group structure12();

   Group structure13();

   Group structure14();

   Group structure15();

   Group structure16();

   Group structure17();

   // $FF: synthetic method
   static Tuple17 inverse$(final GroupProduct17 $this, final Tuple17 x0) {
      return $this.inverse(x0);
   }

   default Tuple17 inverse(final Tuple17 x0) {
      return new Tuple17(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()), this.structure14().inverse(x0._14()), this.structure15().inverse(x0._15()), this.structure16().inverse(x0._16()), this.structure17().inverse(x0._17()));
   }

   static void $init$(final GroupProduct17 $this) {
   }
}
