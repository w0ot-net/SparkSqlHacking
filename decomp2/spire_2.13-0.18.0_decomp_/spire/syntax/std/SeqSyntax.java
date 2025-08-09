package spire.syntax.std;

import scala.collection.Iterable;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0011\r\u0011\u0004C\u0003E\u0001\u0011\rQIA\u0005TKF\u001c\u0016P\u001c;bq*\u0011aaB\u0001\u0004gR$'B\u0001\u0005\n\u0003\u0019\u0019\u0018P\u001c;bq*\t!\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001i\u0001C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002+A\u0011aBF\u0005\u0003/=\u0011A!\u00168ji\u000611/Z9PaN,2AG\u00110)\tY\u0012\t\u0005\u0003\u001d;}qS\"A\u0003\n\u0005y)!AB*fc>\u00038\u000f\u0005\u0002!C1\u0001A!\u0003\u0012\u0003A\u0003\u0005\tQ1\u0001$\u0005\u0005\t\u0015C\u0001\u0013(!\tqQ%\u0003\u0002'\u001f\t9aj\u001c;iS:<\u0007C\u0001\b)\u0013\tIsBA\u0002B]fD#!I\u0016\u0011\u00059a\u0013BA\u0017\u0010\u0005-\u0019\b/Z2jC2L'0\u001a3\u0011\u0005\u0001zC!\u0002\u0019\u0003\u0005\u0004\t$AA\"D+\t\u0011\u0004)\u0005\u0002%gA\u0019A\u0007P \u000f\u0005URdB\u0001\u001c:\u001b\u00059$B\u0001\u001d\f\u0003\u0019a$o\\8u}%\t\u0001#\u0003\u0002<\u001f\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u0005!IE/\u001a:bE2,'BA\u001e\u0010!\t\u0001\u0003\tB\u0003#_\t\u00071\u0005C\u0003C\u0005\u0001\u00071)A\u0002mQN\u00042\u0001I\u0018 \u00035Ig\u000eZ3yK\u0012\u001cV-](qgV\u0019ai\u0013(\u0015\u0005\u001d3\u0006\u0003\u0002\u000fI\u00156K!!S\u0003\u0003\u001b%sG-\u001a=fIN+\u0017o\u00149t!\t\u00013\nB\u0005#\u0007\u0001\u0006\t\u0011!b\u0001G!\u00121j\u000b\t\u0003A9#Q\u0001M\u0002C\u0002=+\"\u0001U+\u0012\u0005\u0011\n\u0006c\u0001\u001bS)&\u00111K\u0010\u0002\u000b\u0013:$W\r_3e'\u0016\f\bC\u0001\u0011V\t\u0015\u0011cJ1\u0001$\u0011\u0015\u00115\u00011\u0001X!\r\u0001cJ\u0013"
)
public interface SeqSyntax {
   // $FF: synthetic method
   static SeqOps seqOps$(final SeqSyntax $this, final Iterable lhs) {
      return $this.seqOps(lhs);
   }

   default SeqOps seqOps(final Iterable lhs) {
      return new SeqOps(lhs);
   }

   // $FF: synthetic method
   static IndexedSeqOps indexedSeqOps$(final SeqSyntax $this, final IndexedSeq lhs) {
      return $this.indexedSeqOps(lhs);
   }

   default IndexedSeqOps indexedSeqOps(final IndexedSeq lhs) {
      return new IndexedSeqOps(lhs);
   }

   static void $init$(final SeqSyntax $this) {
   }
}
