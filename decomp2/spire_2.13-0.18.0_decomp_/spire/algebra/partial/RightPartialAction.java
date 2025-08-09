package spire.algebra.partial;

import scala.reflect.ScalaSignature;
import spire.algebra.RightAction;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014q!\u0003\u0006\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0003\u001f\u0001\u0011\u0005q\u0004C\u00033\u0001\u0019\u00051gB\u0003=\u0015!\u0005QHB\u0003\n\u0015!\u0005q\bC\u0003D\u000b\u0011\u0005A\tC\u0003F\u000b\u0011\u0015a\tC\u0003T\u000b\u0011\rAK\u0001\nSS\u001eDG\u000fU1si&\fG.Q2uS>t'BA\u0006\r\u0003\u001d\u0001\u0018M\u001d;jC2T!!\u0004\b\u0002\u000f\u0005dw-\u001a2sC*\tq\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0007I9\u0003g\u0005\u0002\u0001'A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t\u0019\u0011I\\=\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0002C\u0001\u000b\u001d\u0013\tiRC\u0001\u0003V]&$\u0018!D1diJL5\u000fR3gS:,G\rF\u0002!G5\u0002\"\u0001F\u0011\n\u0005\t*\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006I\t\u0001\r!J\u0001\u0002aB\u0011ae\n\u0007\u0001\t\u0015A\u0003A1\u0001*\u0005\u0005\u0001\u0016C\u0001\u0016\u0014!\t!2&\u0003\u0002-+\t9aj\u001c;iS:<\u0007\"\u0002\u0018\u0003\u0001\u0004y\u0013!A4\u0011\u0005\u0019\u0002D!B\u0019\u0001\u0005\u0004I#!A$\u0002\u0017A\f'\u000f^5bY\u0006\u001bGO\u001d\u000b\u0004iiZ\u0004cA\u001b9K5\taG\u0003\u00028\u001d\u0005!Q\u000f^5m\u0013\tIdGA\u0002PaRDQ\u0001J\u0002A\u0002\u0015BQAL\u0002A\u0002=\n!CU5hQR\u0004\u0016M\u001d;jC2\f5\r^5p]B\u0011a(B\u0007\u0002\u0015M\u0011Q\u0001\u0011\t\u0003)\u0005K!AQ\u000b\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tQ(A\u0003baBd\u00170F\u0002H\u00152#\"\u0001S'\u0011\ty\u0002\u0011j\u0013\t\u0003M)#Q\u0001K\u0004C\u0002%\u0002\"A\n'\u0005\u000bE:!\u0019A\u0015\t\u000b9;\u00019\u0001%\u0002\u0003\u001dC#a\u0002)\u0011\u0005Q\t\u0016B\u0001*\u0016\u0005\u0019Ig\u000e\\5oK\u0006yaM]8n%&<\u0007\u000e^!di&|g.F\u0002V1j#\"AV.\u0011\ty\u0002q+\u0017\t\u0003Ma#Q\u0001\u000b\u0005C\u0002%\u0002\"A\n.\u0005\u000bEB!\u0019A\u0015\t\u000b9C\u00019\u0001/\u0011\tusv+W\u0007\u0002\u0019%\u0011q\f\u0004\u0002\f%&<\u0007\u000e^!di&|g\u000e"
)
public interface RightPartialAction {
   static RightPartialAction fromRightAction(final RightAction G) {
      return RightPartialAction$.MODULE$.fromRightAction(G);
   }

   static RightPartialAction apply(final RightPartialAction G) {
      return RightPartialAction$.MODULE$.apply(G);
   }

   // $FF: synthetic method
   static boolean actrIsDefined$(final RightPartialAction $this, final Object p, final Object g) {
      return $this.actrIsDefined(p, g);
   }

   default boolean actrIsDefined(final Object p, final Object g) {
      return .MODULE$.nonEmpty$extension(this.partialActr(p, g));
   }

   Object partialActr(final Object p, final Object g);

   static void $init$(final RightPartialAction $this) {
   }
}
