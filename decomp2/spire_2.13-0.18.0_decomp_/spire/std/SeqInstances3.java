package spire.std;

import algebra.ring.Field;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005a3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\u0007TKFLen\u001d;b]\u000e,7o\r\u0006\u0003\u000b\u0019\t1a\u001d;e\u0015\u00059\u0011!B:qSJ,7\u0001A\n\u0004\u0001)\u0001\u0002CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g\r\u0005\u0002\u0012%5\tA!\u0003\u0002\u0014\t\ti1+Z9J]N$\u0018M\\2fgJ\na\u0001J5oSR$C#\u0001\f\u0011\u0005-9\u0012B\u0001\r\r\u0005\u0011)f.\u001b;\u0002)M+\u0017OT8s[\u0016$g+Z2u_J\u001c\u0006/Y2f+\rYR\b\n\u000b\u00059yr5\u000b\u0005\u0003\u001eA\tbT\"\u0001\u0010\u000b\u0005}1\u0011aB1mO\u0016\u0014'/Y\u0005\u0003Cy\u0011\u0011CT8s[\u0016$g+Z2u_J\u001c\u0006/Y2f!\r\u0019C\u0005\u0010\u0007\u0001\t\u0015)#A1\u0001'\u0005\t\u00195)\u0006\u0002(eE\u0011\u0001f\u000b\t\u0003\u0017%J!A\u000b\u0007\u0003\u000f9{G\u000f[5oOB)AfL\u00199w5\tQF\u0003\u0002/\u0019\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Aj#AB*fc>\u00038\u000f\u0005\u0002$e\u0011)1\u0007\nb\u0001i\t\t\u0011)\u0005\u0002)kA\u00111BN\u0005\u0003o1\u00111!\u00118z!\ta\u0013(\u0003\u0002;[\t\u00191+Z9\u0011\u0007\r\"\u0013\u0007\u0005\u0002${\u0011)1G\u0001b\u0001i!)qH\u0001a\u0002\u0001\u00061a-[3mIB\u00022!Q&=\u001d\t\u0011\u0015J\u0004\u0002D\u0011:\u0011AiR\u0007\u0002\u000b*\u0011a\tC\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dI!a\b\u0004\n\u0005)s\u0012a\u00029bG.\fw-Z\u0005\u0003\u00196\u0013QAR5fY\u0012T!A\u0013\u0010\t\u000b=\u0013\u00019\u0001)\u0002\r9\u0014xn\u001c;1!\ri\u0012\u000bP\u0005\u0003%z\u0011QA\u0014*p_RDQ\u0001\u0016\u0002A\u0004U\u000bAa\u00192gaA!AF\u0016\u001f#\u0013\t9VFA\u0004GC\u000e$xN]="
)
public interface SeqInstances3 extends SeqInstances2 {
   // $FF: synthetic method
   static NormedVectorSpace SeqNormedVectorSpace$(final SeqInstances3 $this, final Field field0, final NRoot nroot0, final Factory cbf0) {
      return $this.SeqNormedVectorSpace(field0, nroot0, cbf0);
   }

   default NormedVectorSpace SeqNormedVectorSpace(final Field field0, final NRoot nroot0, final Factory cbf0) {
      return this.SeqInnerProductSpace(field0, cbf0).normed(nroot0);
   }

   static void $init$(final SeqInstances3 $this) {
   }
}
