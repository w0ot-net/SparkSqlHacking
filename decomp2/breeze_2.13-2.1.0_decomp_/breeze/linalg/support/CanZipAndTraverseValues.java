package breeze.linalg.support;

import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t4q!\u0003\u0006\u0011\u0002G\u0005\u0011\u0003C\u0003\u001a\u0001\u0019\u0005!dB\u0003>\u0015!\u0005aHB\u0003\n\u0015!\u0005\u0001\tC\u0003B\u0007\u0011\u0005!IB\u0004D\u0007A\u0005\u0019\u0011\u0001#\t\u000b\u0019+A\u0011A$\t\u000b!+a\u0011A%\t\u000bQ+A\u0011A+\u0003/\r\u000bgNW5q\u0003:$GK]1wKJ\u001cXMV1mk\u0016\u001c(BA\u0006\r\u0003\u001d\u0019X\u000f\u001d9peRT!!\u0004\b\u0002\r1Lg.\u00197h\u0015\u0005y\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u000bI\u0011cfX1\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g-\u0001\u0005ue\u00064XM]:f)\u0011Ybd\u000b\u0019\u0011\u0005Qa\u0012BA\u000f\u0016\u0005\u0011)f.\u001b;\t\u000b}\t\u0001\u0019\u0001\u0011\u0002\u000b\u0019\u0014x.\\\u0019\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\u0006G\u0001\u0011\r\u0001\n\u0002\u0005\rJ|W.\u0005\u0002&QA\u0011ACJ\u0005\u0003OU\u0011qAT8uQ&tw\r\u0005\u0002\u0015S%\u0011!&\u0006\u0002\u0004\u0003:L\b\"\u0002\u0017\u0002\u0001\u0004i\u0013!\u00024s_6\u0014\u0004CA\u0011/\t\u0015y\u0003A1\u0001%\u0005\u00151%o\\73\u0011\u0015\t\u0014\u00011\u00013\u0003\t1g\u000e\u0005\u00034\u000by\u0003gB\u0001\u001b\u0003\u001d\t)DH\u0004\u00027w9\u0011qGO\u0007\u0002q)\u0011\u0011\bE\u0001\u0007yI|w\u000e\u001e \n\u0003=I!!\u0004\b\n\u0005-a\u0011aF\"b]jK\u0007/\u00118e)J\fg/\u001a:tKZ\u000bG.^3t!\ty4!D\u0001\u000b'\t\u00191#\u0001\u0004=S:LGO\u0010\u000b\u0002}\t\t\u0002+Y5s-\u0006dW/Z:WSNLGo\u001c:\u0016\u0007\u0015k%k\u0005\u0002\u0006'\u00051A%\u001b8ji\u0012\"\u0012aG\u0001\u0006m&\u001c\u0018\u000e\u001e\u000b\u00047){\u0005\"B&\b\u0001\u0004a\u0015!A1\u0011\u0005\u0005jE!\u0002(\u0006\u0005\u0004!#!A!\t\u000bA;\u0001\u0019A)\u0002\u0003\t\u0004\"!\t*\u0005\u000bM+!\u0019\u0001\u0013\u0003\u0003\t\u000b!B^5tSR\f%O]1z)\rYbk\u0017\u0005\u0006/\"\u0001\r\u0001W\u0001\u0004CJ\u0014\bc\u0001\u000bZ\u0019&\u0011!,\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\u00069\"\u0001\r!X\u0001\u0005CJ\u0014(\u0007E\u0002\u00153F\u0003\"!I0\u0005\u000b9\u0003!\u0019\u0001\u0013\u0011\u0005\u0005\nG!B*\u0001\u0005\u0004!\u0003"
)
public interface CanZipAndTraverseValues {
   void traverse(final Object from1, final Object from2, final PairValuesVisitor fn);

   public interface PairValuesVisitor {
      void visit(final Object a, final Object b);

      // $FF: synthetic method
      static void visitArray$(final PairValuesVisitor $this, final Object arr, final Object arr2) {
         $this.visitArray(arr, arr2);
      }

      default void visitArray(final Object arr, final Object arr2) {
         if (.MODULE$.array_length(arr) != .MODULE$.array_length(arr2)) {
            throw new IllegalArgumentException("Arrays to be visited must have same size");
         } else {
            int index$macro$2 = 0;

            for(int limit$macro$4 = .MODULE$.array_length(arr); index$macro$2 < limit$macro$4; ++index$macro$2) {
               this.visit(.MODULE$.array_apply(arr, index$macro$2), .MODULE$.array_apply(arr2, index$macro$2));
            }

         }
      }

      static void $init$(final PairValuesVisitor $this) {
      }
   }
}
