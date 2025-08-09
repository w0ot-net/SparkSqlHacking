package breeze.util;

import scala.Function1;
import scala.collection.Iterable;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3AAB\u0004\u0001\u0019!AA\u0003\u0001BC\u0002\u0013\u0005Q\u0003\u0003\u0005.\u0001\t\u0005\t\u0015!\u0003\u0017\u0011\u0015q\u0003\u0001\"\u00010\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u0015\u0019\u0004\u0001\"\u0001D\u00051!v\u000e]&Ji\u0016\u0014\u0018M\u00197f\u0015\tA\u0011\"\u0001\u0003vi&d'\"\u0001\u0006\u0002\r\t\u0014X-\u001a>f\u0007\u0001)\"!\u0004\u0013\u0014\u0005\u0001q\u0001CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g-\u0001\u0003tK24W#\u0001\f\u0011\u0007]y\"E\u0004\u0002\u0019;9\u0011\u0011\u0004H\u0007\u00025)\u00111dC\u0001\u0007yI|w\u000e\u001e \n\u0003EI!A\b\t\u0002\u000fA\f7m[1hK&\u0011\u0001%\t\u0002\t\u0013R,'/\u00192mK*\u0011a\u0004\u0005\t\u0003G\u0011b\u0001\u0001B\u0003&\u0001\t\u0007aEA\u0001U#\t9#\u0006\u0005\u0002\u0010Q%\u0011\u0011\u0006\u0005\u0002\b\u001d>$\b.\u001b8h!\ty1&\u0003\u0002-!\t\u0019\u0011I\\=\u0002\u000bM,GN\u001a\u0011\u0002\rqJg.\u001b;?)\t\u0001$\u0007E\u00022\u0001\tj\u0011a\u0002\u0005\u0006)\r\u0001\rAF\u0001\u0005i>\u00048\u000e\u0006\u00026}Q\u0011a'\u000f\t\u0004c]\u0012\u0013B\u0001\u001d\b\u0005\u0011!v\u000e]&\t\u000bi\"\u00019A\u001e\u0002\u0007=\u0014H\rE\u0002\u0018y\tJ!!P\u0011\u0003\u0011=\u0013H-\u001a:j]\u001eDQa\u0010\u0003A\u0002\u0001\u000b\u0011a\u001b\t\u0003\u001f\u0005K!A\u0011\t\u0003\u0007%sG/\u0006\u0002E\u0015R\u0019Q\tT'\u0015\u0005Y2\u0005\"B$\u0006\u0001\bA\u0015\u0001B;pe\u0012\u00042a\u0006\u001fJ!\t\u0019#\nB\u0003L\u000b\t\u0007aEA\u0001V\u0011\u0015yT\u00011\u0001A\u0011\u0015qU\u00011\u0001P\u0003\u001d\u00198m\u001c:f\r:\u0004Ba\u0004)#\u0013&\u0011\u0011\u000b\u0005\u0002\n\rVt7\r^5p]F\u0002"
)
public class TopKIterable {
   private final Iterable self;

   public Iterable self() {
      return this.self;
   }

   public TopK topk(final int k, final Ordering ord) {
      return TopK$.MODULE$.apply(k, this.self(), ord);
   }

   public TopK topk(final int k, final Function1 scoreFn, final Ordering uord) {
      return TopK$.MODULE$.apply(k, this.self(), scoreFn, uord);
   }

   public TopKIterable(final Iterable self) {
      this.self = self;
   }
}
