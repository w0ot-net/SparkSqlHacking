package spire.std;

import algebra.ring.CommutativeRing;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import spire.NotGiven;

@ScalaSignature(
   bytes = "\u0006\u0005e3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\u0007TKFLen\u001d;b]\u000e,7\u000f\r\u0006\u0003\u000b\u0019\t1a\u001d;e\u0015\u00059\u0011!B:qSJ,7\u0001A\n\u0003\u0001)\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0013!\tY1#\u0003\u0002\u0015\u0019\t!QK\\5u\u0003)\u0019V-]\"N_\u0012,H.Z\u000b\u0004/yAC\u0003\u0002\r9\u0015>\u0003B!\u0007\u000e\u001dO5\tA!\u0003\u0002\u001c\t\tQ1+Z9D\u001b>$W\u000f\\3\u0011\u0005uqB\u0002\u0001\u0003\u0006?\t\u0011\r\u0001\t\u0002\u0002\u0003F\u0011\u0011\u0005\n\t\u0003\u0017\tJ!a\t\u0007\u0003\u000f9{G\u000f[5oOB\u00111\"J\u0005\u0003M1\u00111!\u00118z!\ri\u0002\u0006\b\u0003\u0006S\t\u0011\rA\u000b\u0002\u0003\u0007\u000e+\"aK\u001a\u0012\u0005\u0005b\u0003#B\u00171eQ:T\"\u0001\u0018\u000b\u0005=b\u0011AC2pY2,7\r^5p]&\u0011\u0011G\f\u0002\u0007'\u0016\fx\n]:\u0011\u0005u\u0019D!B\u0010)\u0005\u0004\u0001\u0003CA\u00176\u0013\t1dFA\u0002TKF\u00042!\b\u00153\u0011\u0015I$\u0001q\u0001;\u0003\u0015\u0011\u0018N\\41!\rYt\t\b\b\u0003y\u0011s!!\u0010\"\u000f\u0005y\nU\"A \u000b\u0005\u0001C\u0011A\u0002\u001fs_>$h(C\u0001\b\u0013\t\u0019e!A\u0004bY\u001e,'M]1\n\u0005\u00153\u0015a\u00029bG.\fw-\u001a\u0006\u0003\u0007\u001aI!\u0001S%\u0003\u000b\r\u0013\u0016N\\4\u000b\u0005\u00153\u0005\"B&\u0003\u0001\ba\u0015\u0001B2cMB\u0002B!L'\u001dO%\u0011aJ\f\u0002\b\r\u0006\u001cGo\u001c:z\u0011\u0015\u0001&\u0001q\u0001R\u0003\t)g\u000fE\u0002S'Vk\u0011AB\u0005\u0003)\u001a\u0011\u0001BT8u\u000f&4XM\u001c\t\u0005-^;C$D\u0001G\u0013\tAfIA\u0006WK\u000e$xN]*qC\u000e,\u0007"
)
public interface SeqInstances0 {
   // $FF: synthetic method
   static SeqCModule SeqCModule$(final SeqInstances0 $this, final CommutativeRing ring0, final Factory cbf0, final NotGiven ev) {
      return $this.SeqCModule(ring0, cbf0, ev);
   }

   default SeqCModule SeqCModule(final CommutativeRing ring0, final Factory cbf0, final NotGiven ev) {
      return new SeqCModule(ring0, cbf0);
   }

   static void $init$(final SeqInstances0 $this) {
   }
}
