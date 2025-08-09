package scala.util.parsing.input;

import java.nio.CharBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;QAC\u0006\t\u0002Q1QAF\u0006\t\u0002]AQ\u0001H\u0001\u0005\u0002uAqAH\u0001C\u0002\u0013\u0015q\u0004\u0003\u0004#\u0003\u0001\u0006i\u0001\t\u0005\bG\u0005\t\n\u0011\"\u0001%\r\u001112\u0002\u0001\u001a\t\u0011Y2!\u0011!Q\u0001\n]B\u0011\"\u0010\u0004\u0003\u0002\u0003\u0006IA\n \t\u000bq1A\u0011\u0001!\u0002\u001f\rC\u0017M]!se\u0006L(+Z1eKJT!\u0001D\u0007\u0002\u000b%t\u0007/\u001e;\u000b\u00059y\u0011a\u00029beNLgn\u001a\u0006\u0003!E\tA!\u001e;jY*\t!#A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005U\tQ\"A\u0006\u0003\u001f\rC\u0017M]!se\u0006L(+Z1eKJ\u001c\"!\u0001\r\u0011\u0005eQR\"A\t\n\u0005m\t\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002)\u0005)Qi\u001c4DQV\t\u0001eD\u0001\"9\u0005Q\u0012AB#pM\u000eC\u0007%A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0002K)\u0012a%\u000b\t\u00033\u001dJ!\u0001K\t\u0003\u0007%sGoK\u0001+!\tY\u0003'D\u0001-\u0015\tic&A\u0005v]\u000eDWmY6fI*\u0011q&E\u0001\u000bC:tw\u000e^1uS>t\u0017BA\u0019-\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\n\u0003\rM\u0002\"!\u0006\u001b\n\u0005UZ!AE\"iCJ\u001cV-];f]\u000e,'+Z1eKJ\fQa\u00195beN\u00042!\u0007\u001d;\u0013\tI\u0014CA\u0003BeJ\f\u0017\u0010\u0005\u0002\u001aw%\u0011A(\u0005\u0002\u0005\u0007\"\f'/A\u0003j]\u0012,\u00070\u0003\u0002@i\u00051qN\u001a4tKR$2!\u0011\"D!\t)b\u0001C\u00037\u0013\u0001\u0007q\u0007C\u0004>\u0013A\u0005\t\u0019\u0001\u0014"
)
public class CharArrayReader extends CharSequenceReader {
   public static int $lessinit$greater$default$2() {
      return CharArrayReader$.MODULE$.$lessinit$greater$default$2();
   }

   public static char EofCh() {
      return CharArrayReader$.MODULE$.EofCh();
   }

   public CharArrayReader(final char[] chars, final int index) {
      super(CharBuffer.wrap(chars), index);
   }
}
