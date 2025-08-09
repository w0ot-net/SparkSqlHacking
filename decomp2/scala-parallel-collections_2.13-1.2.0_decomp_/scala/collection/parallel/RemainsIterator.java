package scala.collection.parallel;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005qa\u0003\u0005\u0006I\u0001!\t!\n\u0005\u0006S\u00011\tA\u000b\u0005\u0006]\u0001!\ta\f\u0002\u0010%\u0016l\u0017-\u001b8t\u0013R,'/\u0019;pe*\u0011aaB\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011\u0001\"C\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0006\u0002\u000bM\u001c\u0017\r\\1\u0016\u00051Q2c\u0001\u0001\u000e#A\u0011abD\u0007\u0002\u0013%\u0011\u0001#\u0003\u0002\u0007\u0003:L(+\u001a4\u0011\u0007I)\u0002D\u0004\u0002\u000f'%\u0011A#C\u0001\ba\u0006\u001c7.Y4f\u0013\t1rC\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\t!\u0012\u0002\u0005\u0002\u001a51\u0001AAB\u000e\u0001\t\u000b\u0007QDA\u0001U\u0007\u0001\t\"AH\u0011\u0011\u00059y\u0012B\u0001\u0011\n\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0004\u0012\n\u0005\rJ!aA!os\u00061A%\u001b8ji\u0012\"\u0012A\n\t\u0003\u001d\u001dJ!\u0001K\u0005\u0003\tUs\u0017\u000e^\u0001\ne\u0016l\u0017-\u001b8j]\u001e,\u0012a\u000b\t\u0003\u001d1J!!L\u0005\u0003\u0007%sG/\u0001\tjgJ+W.Y5oS:<7\t[3baV\t\u0001\u0007\u0005\u0002\u000fc%\u0011!'\u0003\u0002\b\u0005>|G.Z1o\u0001"
)
public interface RemainsIterator extends Iterator {
   int remaining();

   // $FF: synthetic method
   static boolean isRemainingCheap$(final RemainsIterator $this) {
      return $this.isRemainingCheap();
   }

   default boolean isRemainingCheap() {
      return true;
   }

   static void $init$(final RemainsIterator $this) {
   }
}
