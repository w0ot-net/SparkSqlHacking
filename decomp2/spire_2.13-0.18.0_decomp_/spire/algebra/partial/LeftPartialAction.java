package spire.algebra.partial;

import scala.reflect.ScalaSignature;
import spire.algebra.LeftAction;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014q!\u0003\u0006\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0003\u001f\u0001\u0011\u0005q\u0004C\u00033\u0001\u0019\u00051gB\u0003=\u0015!\u0005QHB\u0003\n\u0015!\u0005q\bC\u0003D\u000b\u0011\u0005A\tC\u0003F\u000b\u0011\u0015a\tC\u0003T\u000b\u0011\rAKA\tMK\u001a$\b+\u0019:uS\u0006d\u0017i\u0019;j_:T!a\u0003\u0007\u0002\u000fA\f'\u000f^5bY*\u0011QBD\u0001\bC2<WM\u0019:b\u0015\u0005y\u0011!B:qSJ,7\u0001A\u000b\u0004%A:3C\u0001\u0001\u0014!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\r\te._\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003m\u0001\"\u0001\u0006\u000f\n\u0005u)\"\u0001B+oSR\fQ\"Y2uY&\u001bH)\u001a4j]\u0016$Gc\u0001\u0011$[A\u0011A#I\u0005\u0003EU\u0011qAQ8pY\u0016\fg\u000eC\u0003%\u0005\u0001\u0007Q%A\u0001h!\t1s\u0005\u0004\u0001\u0005\u000b!\u0002!\u0019A\u0015\u0003\u0003\u001d\u000b\"AK\n\u0011\u0005QY\u0013B\u0001\u0017\u0016\u0005\u001dqu\u000e\u001e5j]\u001eDQA\f\u0002A\u0002=\n\u0011\u0001\u001d\t\u0003MA\"Q!\r\u0001C\u0002%\u0012\u0011\u0001U\u0001\fa\u0006\u0014H/[1m\u0003\u000e$H\u000eF\u00025um\u00022!\u000e\u001d0\u001b\u00051$BA\u001c\u000f\u0003\u0011)H/\u001b7\n\u0005e2$aA(qi\")Ae\u0001a\u0001K!)af\u0001a\u0001_\u0005\tB*\u001a4u!\u0006\u0014H/[1m\u0003\u000e$\u0018n\u001c8\u0011\u0005y*Q\"\u0001\u0006\u0014\u0005\u0015\u0001\u0005C\u0001\u000bB\u0013\t\u0011UC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003u\nQ!\u00199qYf,2a\u0012&M)\tAU\n\u0005\u0003?\u0001%[\u0005C\u0001\u0014K\t\u0015\ttA1\u0001*!\t1C\nB\u0003)\u000f\t\u0007\u0011\u0006C\u0003O\u000f\u0001\u000f\u0001*A\u0001HQ\t9\u0001\u000b\u0005\u0002\u0015#&\u0011!+\u0006\u0002\u0007S:d\u0017N\\3\u0002\u001d\u0019\u0014x.\u001c'fMR\f5\r^5p]V\u0019Q\u000b\u0017.\u0015\u0005Y[\u0006\u0003\u0002 \u0001/f\u0003\"A\n-\u0005\u000bEB!\u0019A\u0015\u0011\u0005\u0019RF!\u0002\u0015\t\u0005\u0004I\u0003\"\u0002(\t\u0001\ba\u0006\u0003B/_/fk\u0011\u0001D\u0005\u0003?2\u0011!\u0002T3gi\u0006\u001bG/[8o\u0001"
)
public interface LeftPartialAction {
   static LeftPartialAction fromLeftAction(final LeftAction G) {
      return LeftPartialAction$.MODULE$.fromLeftAction(G);
   }

   static LeftPartialAction apply(final LeftPartialAction G) {
      return LeftPartialAction$.MODULE$.apply(G);
   }

   // $FF: synthetic method
   static boolean actlIsDefined$(final LeftPartialAction $this, final Object g, final Object p) {
      return $this.actlIsDefined(g, p);
   }

   default boolean actlIsDefined(final Object g, final Object p) {
      return .MODULE$.nonEmpty$extension(this.partialActl(g, p));
   }

   Object partialActl(final Object g, final Object p);

   static void $init$(final LeftPartialAction $this) {
   }
}
