package spire.std;

import algebra.ring.EuclideanRing;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.math.BitString;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u0005%3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007Iq\u0001\r\t\u000f\t\u0002!\u0019!C\u0004G!9A\t\u0001b\u0001\n\u000f)%AD*i_J$\u0018J\\:uC:\u001cWm\u001d\u0006\u0003\u000f!\t1a\u001d;e\u0015\u0005I\u0011!B:qSJ,7\u0001A\n\u0003\u00011\u0001\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0015!\tiQ#\u0003\u0002\u0017\u001d\t!QK\\5u\u00039\u0019\u0006n\u001c:u\u0005&$8\u000b\u001e:j]\u001e,\u0012!\u0007\t\u00045uyR\"A\u000e\u000b\u0005qA\u0011\u0001B7bi\"L!AH\u000e\u0003\u0013\tKGo\u0015;sS:<\u0007CA\u0007!\u0013\t\tcBA\u0003TQ>\u0014H/\u0001\u0007TQ>\u0014H/\u00117hK\n\u0014\u0018-F\u0001%%\u0019)seN\u001e?\u0003\u001a!a\u0005\u0001\u0001%\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rACg\b\b\u0003SEr!AK\u0018\u000f\u0005-rS\"\u0001\u0017\u000b\u00055R\u0011A\u0002\u001fs_>$h(C\u0001\n\u0013\t\u0001\u0004\"A\u0004bY\u001e,'M]1\n\u0005I\u001a\u0014a\u00029bG.\fw-\u001a\u0006\u0003a!I!!\u000e\u001c\u0003\u001b\u0015+8\r\\5eK\u0006t'+\u001b8h\u0015\t\u00114\u0007E\u00029s}i\u0011aM\u0005\u0003uM\u0012!\"S:J]R,wM]1m!\rAChH\u0005\u0003{Y\u0012a\u0003\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0007JKgn\u001a\t\u0004Q}z\u0012B\u0001!7\u0005\u0019\u0019\u0016n\u001a8fIB\u0019\u0001FQ\u0010\n\u0005\r3$!B(sI\u0016\u0014\u0018\u0001C*i_J$H+Y4\u0016\u0003\u0019\u00032AG$ \u0013\tA5DA\u0005Ok6\u0014WM\u001d+bO\u0002"
)
public interface ShortInstances {
   void spire$std$ShortInstances$_setter_$ShortBitString_$eq(final BitString x$1);

   void spire$std$ShortInstances$_setter_$ShortAlgebra_$eq(final EuclideanRing x$1);

   void spire$std$ShortInstances$_setter_$ShortTag_$eq(final NumberTag x$1);

   BitString ShortBitString();

   EuclideanRing ShortAlgebra();

   NumberTag ShortTag();

   static void $init$(final ShortInstances $this) {
      $this.spire$std$ShortInstances$_setter_$ShortBitString_$eq(new ShortIsBitString());
      $this.spire$std$ShortInstances$_setter_$ShortAlgebra_$eq(new ShortAlgebra());
      $this.spire$std$ShortInstances$_setter_$ShortTag_$eq(new NumberTag.BuiltinIntTag(BoxesRunTime.boxToShort((short)0), BoxesRunTime.boxToShort((short)Short.MIN_VALUE), BoxesRunTime.boxToShort((short)32767)));
   }
}
