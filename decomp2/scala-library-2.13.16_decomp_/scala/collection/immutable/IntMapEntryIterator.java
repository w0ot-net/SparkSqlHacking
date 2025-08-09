package scala.collection.immutable;

import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2Q\u0001B\u0003\u0001\u000b-A\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006Y\u0001!\t!\f\u0002\u0014\u0013:$X*\u00199F]R\u0014\u00180\u0013;fe\u0006$xN\u001d\u0006\u0003\r\u001d\t\u0011\"[7nkR\f'\r\\3\u000b\u0005!I\u0011AC2pY2,7\r^5p]*\t!\"A\u0003tG\u0006d\u0017-\u0006\u0002\r'M\u0011\u0001!\u0004\t\u0005\u001d=\tb$D\u0001\u0006\u0013\t\u0001RA\u0001\bJ]Rl\u0015\r]%uKJ\fGo\u001c:\u0011\u0005I\u0019B\u0002\u0001\u0003\u0006)\u0001\u0011\rA\u0006\u0002\u0002-\u000e\u0001\u0011CA\f\u001c!\tA\u0012$D\u0001\n\u0013\tQ\u0012BA\u0004O_RD\u0017N\\4\u0011\u0005aa\u0012BA\u000f\n\u0005\r\te.\u001f\t\u00051}\t\u0013#\u0003\u0002!\u0013\t1A+\u001e9mKJ\u0002\"\u0001\u0007\u0012\n\u0005\rJ!aA%oi\u0006\u0011\u0011\u000e\u001e\t\u0004\u001d\u0019\n\u0012BA\u0014\u0006\u0005\u0019Ie\u000e^'ba\u00061A(\u001b8jiz\"\"AK\u0016\u0011\u00079\u0001\u0011\u0003C\u0003%\u0005\u0001\u0007Q%A\u0004wC2,Xm\u00144\u0015\u0005yq\u0003\"B\u0018\u0004\u0001\u0004\u0001\u0014a\u0001;jaB\u0019\u0011\u0007N\t\u000f\u00059\u0011\u0014BA\u001a\u0006\u0003\u0019Ie\u000e^'ba&\u0011QG\u000e\u0002\u0004)&\u0004(BA\u001a\u0006\u0001"
)
public class IntMapEntryIterator extends IntMapIterator {
   public Tuple2 valueOf(final IntMap.Tip tip) {
      return new Tuple2(tip.key(), tip.value());
   }

   public IntMapEntryIterator(final IntMap it) {
      super(it);
   }
}
