package spire.syntax;

import algebra.ring.Semiring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQD\u0001\bTK6L'/\u001b8h'ftG/\u0019=\u000b\u0005\u00151\u0011AB:z]R\f\u0007PC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019B\u0001\u0001\u0006\u0011)A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\u0004\"!\u0005\n\u000e\u0003\u0011I!a\u0005\u0003\u0003/\u0005#G-\u001b;jm\u0016\u001cV-\\5he>,\boU=oi\u0006D\bCA\t\u0016\u0013\t1BAA\u000fNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3TK6LwM]8vaNKh\u000e^1y\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0004\u0005\u0002\f5%\u00111\u0004\u0004\u0002\u0005+:LG/A\u0006tK6L'/\u001b8h\u001fB\u001cXC\u0001\u0010&)\ty\u0002\t\u0006\u0002!]A\u0019\u0011#I\u0012\n\u0005\t\"!aC*f[&\u0014\u0018N\\4PaN\u0004\"\u0001J\u0013\r\u0001\u0011)aE\u0001b\u0001O\t\t\u0011)\u0005\u0002)WA\u00111\"K\u0005\u0003U1\u0011qAT8uQ&tw\r\u0005\u0002\fY%\u0011Q\u0006\u0004\u0002\u0004\u0003:L\bbB\u0018\u0003\u0003\u0003\u0005\u001d\u0001M\u0001\fKZLG-\u001a8dK\u0012\nT\u0007E\u00022{\rr!A\r\u001e\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c\t\u0003\u0019a$o\\8u}%\tq!\u0003\u0002:\r\u00059\u0011\r\\4fEJ\f\u0017BA\u001e=\u0003\u001d\u0001\u0018mY6bO\u0016T!!\u000f\u0004\n\u0005yz$\u0001C*f[&\u0014\u0018N\\4\u000b\u0005mb\u0004\"B!\u0003\u0001\u0004\u0019\u0013!A1"
)
public interface SemiringSyntax extends AdditiveSemigroupSyntax, MultiplicativeSemigroupSyntax {
   // $FF: synthetic method
   static SemiringOps semiringOps$(final SemiringSyntax $this, final Object a, final Semiring evidence$15) {
      return $this.semiringOps(a, evidence$15);
   }

   default SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
      return new SemiringOps(a, evidence$15);
   }

   static void $init$(final SemiringSyntax $this) {
   }
}
