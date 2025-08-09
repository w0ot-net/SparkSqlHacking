package org.apache.spark.storage;

import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u00059;Q!\u0002\u0004\t\u0002=1Q!\u0005\u0004\t\u0002IAQ!G\u0001\u0005\u0002iAQaG\u0001\u0005\nqAQ\u0001O\u0001\u0005\u0002e\nQC\u00117pG.\u0014V\r\u001d7jG\u0006$\u0018n\u001c8Vi&d7O\u0003\u0002\b\u0011\u000591\u000f^8sC\u001e,'BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0004\u0001A\u0011\u0001#A\u0007\u0002\r\t)\"\t\\8dWJ+\u0007\u000f\\5dCRLwN\\+uS2\u001c8CA\u0001\u0014!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012aD\u0001\rO\u0016$8+Y7qY\u0016LEm\u001d\u000b\u0005;1r\u0003\u0007E\u0002\u001fM%r!a\b\u0013\u000f\u0005\u0001\u001aS\"A\u0011\u000b\u0005\tr\u0011A\u0002\u001fs_>$h(C\u0001\u0017\u0013\t)S#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dB#\u0001\u0002'jgRT!!J\u000b\u0011\u0005QQ\u0013BA\u0016\u0016\u0005\rIe\u000e\u001e\u0005\u0006[\r\u0001\r!K\u0001\u0002]\")qf\u0001a\u0001S\u0005\tQ\u000eC\u00032\u0007\u0001\u0007!'A\u0001s!\t\u0019d'D\u00015\u0015\t)T#\u0001\u0003vi&d\u0017BA\u001c5\u0005\u0019\u0011\u0016M\u001c3p[\u0006yq-\u001a;SC:$w.\\*b[BdW-\u0006\u0002;}Q!1h\u0012'N!\rqb\u0005\u0010\t\u0003{yb\u0001\u0001B\u0003@\t\t\u0007\u0001IA\u0001U#\t\tE\t\u0005\u0002\u0015\u0005&\u00111)\u0006\u0002\b\u001d>$\b.\u001b8h!\t!R)\u0003\u0002G+\t\u0019\u0011I\\=\t\u000b!#\u0001\u0019A%\u0002\u000b\u0015dW-\\:\u0011\u0007yQE(\u0003\u0002LQ\t\u00191+Z9\t\u000b=\"\u0001\u0019A\u0015\t\u000bE\"\u0001\u0019\u0001\u001a"
)
public final class BlockReplicationUtils {
   public static List getRandomSample(final Seq elems, final int m, final Random r) {
      return BlockReplicationUtils$.MODULE$.getRandomSample(elems, m, r);
   }
}
