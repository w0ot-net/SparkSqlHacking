package scala.util.parsing.input;

import scala.collection.SeqFactory;
import scala.collection.IndexedSeq.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2\u0011b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0004\u0017\t\u000bI\u0001A\u0011\u0001\u000b\t\u000ba\u0001A\u0011I\r\u00039M\u001b\u0017\r\\1WKJ\u001c\u0018n\u001c8Ta\u0016\u001c\u0017NZ5d!\u0006<W\rZ*fc*\u0011QAB\u0001\u0006S:\u0004X\u000f\u001e\u0006\u0003\u000f!\tq\u0001]1sg&twM\u0003\u0002\n\u0015\u0005!Q\u000f^5m\u0015\u0005Y\u0011!B:dC2\fWCA\u0007$'\t\u0001a\u0002\u0005\u0002\u0010!5\t!\"\u0003\u0002\u0012\u0015\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002+A\u0011qBF\u0005\u0003/)\u0011A!\u00168ji\u0006y\u0011\u000e^3sC\ndWMR1di>\u0014\u00180F\u0001\u001b!\rYb\u0004I\u0007\u00029)\u0011QDC\u0001\u000bG>dG.Z2uS>t\u0017BA\u0010\u001d\u0005)\u0019V-\u001d$bGR|'/\u001f\t\u00037\u0005J!A\t\u000f\u0003\u0015%sG-\u001a=fIN+\u0017\u000fB\u0003%\u0001\t\u0007QEA\u0001U#\t1\u0013\u0006\u0005\u0002\u0010O%\u0011\u0001F\u0003\u0002\b\u001d>$\b.\u001b8h!\ty!&\u0003\u0002,\u0015\t\u0019\u0011I\\=\u0011\u00075r\u0003'D\u0001\u0005\u0013\tyCA\u0001\u0005QC\u001e,GmU3r!\t\t4\u0005\u0004\u0001"
)
public interface ScalaVersionSpecificPagedSeq {
   // $FF: synthetic method
   static SeqFactory iterableFactory$(final ScalaVersionSpecificPagedSeq $this) {
      return $this.iterableFactory();
   }

   default SeqFactory iterableFactory() {
      return .MODULE$;
   }

   static void $init$(final ScalaVersionSpecificPagedSeq $this) {
   }
}
