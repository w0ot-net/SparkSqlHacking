package spire.math;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.IsIntegral;

@ScalaSignature(
   bytes = "\u0006\u0005e2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006;\u0001!\ta\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006U\u0001!\ta\u000b\u0002\u000b+&sG/S:SK\u0006d'B\u0001\u0004\b\u0003\u0011i\u0017\r\u001e5\u000b\u0003!\tQa\u001d9je\u0016\u001cB\u0001\u0001\u0006\u00115A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\u00042!\u0005\u000b\u0017\u001b\u0005\u0011\"BA\n\b\u0003\u001d\tGnZ3ce\u0006L!!\u0006\n\u0003\u0015%\u001b\u0018J\u001c;fOJ\fG\u000e\u0005\u0002\u001815\tQ!\u0003\u0002\u001a\u000b\t!Q+\u00138u!\t92$\u0003\u0002\u001d\u000b\t)R+\u00138u)J,hnY1uK\u0012$\u0015N^5tS>t\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003\u0001\u0002\"aC\u0011\n\u0005\tb!\u0001B+oSR\f\u0001\u0002^8E_V\u0014G.\u001a\u000b\u0003K!\u0002\"a\u0003\u0014\n\u0005\u001db!A\u0002#pk\ndW\rC\u0003*\u0005\u0001\u0007a#A\u0001o\u0003!!xNQ5h\u0013:$HC\u0001\u00179!\tiSG\u0004\u0002/g9\u0011qFM\u0007\u0002a)\u0011\u0011GH\u0001\u0007yI|w\u000e\u001e \n\u00035I!\u0001\u000e\u0007\u0002\u000fA\f7m[1hK&\u0011ag\u000e\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005Qb\u0001\"B\u0015\u0004\u0001\u00041\u0002"
)
public interface UIntIsReal extends IsIntegral, UIntTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final UIntIsReal $this, final int n) {
      return $this.toDouble(n);
   }

   default double toDouble(final int n) {
      return UInt$.MODULE$.toDouble$extension(n);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final UIntIsReal $this, final int n) {
      return $this.toBigInt(n);
   }

   default BigInt toBigInt(final int n) {
      return UInt$.MODULE$.toBigInt$extension(n);
   }

   static void $init$(final UIntIsReal $this) {
   }
}
