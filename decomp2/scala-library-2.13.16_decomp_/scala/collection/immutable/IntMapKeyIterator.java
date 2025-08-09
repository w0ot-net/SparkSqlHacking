package scala.collection.immutable;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2Q\u0001B\u0003\u0001\u000b-A\u0001\"\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\u0006K\u0001!\tA\n\u0005\u0006S\u0001!\tA\u000b\u0002\u0012\u0013:$X*\u00199LKfLE/\u001a:bi>\u0014(B\u0001\u0004\b\u0003%IW.\\;uC\ndWM\u0003\u0002\t\u0013\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003)\tQa]2bY\u0006,\"\u0001D\n\u0014\u0005\u0001i\u0001\u0003\u0002\b\u0010#yi\u0011!B\u0005\u0003!\u0015\u0011a\"\u00138u\u001b\u0006\u0004\u0018\n^3sCR|'\u000f\u0005\u0002\u0013'1\u0001A!\u0002\u000b\u0001\u0005\u00041\"!\u0001,\u0004\u0001E\u0011qc\u0007\t\u00031ei\u0011!C\u0005\u00035%\u0011qAT8uQ&tw\r\u0005\u0002\u00199%\u0011Q$\u0003\u0002\u0004\u0003:L\bC\u0001\r \u0013\t\u0001\u0013BA\u0002J]R\f!!\u001b;\u0011\u00079\u0019\u0013#\u0003\u0002%\u000b\t1\u0011J\u001c;NCB\fa\u0001P5oSRtDCA\u0014)!\rq\u0001!\u0005\u0005\u0006C\t\u0001\rAI\u0001\bm\u0006dW/Z(g)\tq2\u0006C\u0003-\u0007\u0001\u0007Q&A\u0002uSB\u00042AL\u0019\u0012\u001d\tqq&\u0003\u00021\u000b\u00051\u0011J\u001c;NCBL!AM\u001a\u0003\u0007QK\u0007O\u0003\u00021\u000b\u0001"
)
public class IntMapKeyIterator extends IntMapIterator {
   public int valueOf(final IntMap.Tip tip) {
      return tip.key();
   }

   public IntMapKeyIterator(final IntMap it) {
      super(it);
   }
}
