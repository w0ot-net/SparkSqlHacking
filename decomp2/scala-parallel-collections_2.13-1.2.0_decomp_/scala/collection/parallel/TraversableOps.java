package scala.collection.parallel;

import scala.Function0;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3qAC\u0006\u0011\u0002\u0007\u0005!\u0003C\u0003\u0019\u0001\u0011\u0005\u0011DB\u0004\u001e\u0001A\u0005\u0019\u0013\u0001\u0010\t\u000b\u0001\u0012a\u0011A\u0011\t\u000bI\u0002a\u0011A\u001a\t\u000b]\u0002a\u0011A\u001a\t\u000ba\u0002a\u0011A\u001d\t\u000b\u0005\u0003a\u0011A\u001a\t\u000b\t\u0003a\u0011A\"\t\u000b\u001d\u0003a\u0011\u0001%\u0003\u001dQ\u0013\u0018M^3sg\u0006\u0014G.Z(qg*\u0011A\"D\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011abD\u0001\u000bG>dG.Z2uS>t'\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u00111cP\n\u0003\u0001Q\u0001\"!\u0006\f\u000e\u0003=I!aF\b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0004\u0005\u0002\u00167%\u0011Ad\u0004\u0002\u0005+:LGOA\u0005Pi\",'o^5tKV\u0011q\u0004J\n\u0003\u0005Q\t\u0011b\u001c;iKJ<\u0018n]3\u0015\u0005\tj\u0003CA\u0012%\u0019\u0001!Q!\n\u0002C\u0002\u0019\u0012\u0011AU\t\u0003O)\u0002\"!\u0006\u0015\n\u0005%z!a\u0002(pi\"Lgn\u001a\t\u0003+-J!\u0001L\b\u0003\u0007\u0005s\u0017\u0010\u0003\u0004/\u0007\u0011\u0005\raL\u0001\b]>$(m\u001c3z!\r)\u0002GI\u0005\u0003c=\u0011\u0001\u0002\u00102z]\u0006lWMP\u0001\u000bSN\u0004\u0016M]1mY\u0016dW#\u0001\u001b\u0011\u0005U)\u0014B\u0001\u001c\u0010\u0005\u001d\u0011un\u001c7fC:\fQ\"[:QCJLE/\u001a:bE2,\u0017!D1t!\u0006\u0014\u0018\n^3sC\ndW-F\u0001;!\rYDHP\u0007\u0002\u0017%\u0011Qh\u0003\u0002\f!\u0006\u0014\u0018\n^3sC\ndW\r\u0005\u0002$\u007f\u0011)\u0001\t\u0001b\u0001M\t\tA+\u0001\u0005jgB\u000b'oU3r\u0003!\t7\u000fU1s'\u0016\fX#\u0001#\u0011\u0007m*e(\u0003\u0002G\u0017\t1\u0001+\u0019:TKF\f\u0001\"\u001b4QCJ\u001cV-]\u000b\u0003\u00136#\"A\u0013(\u0011\u0007-\u0013A*D\u0001\u0001!\t\u0019S\nB\u0003&\u0013\t\u0007a\u0005C\u0003P\u0013\u0001\u0007\u0001+\u0001\u0004jg\n|G-\u001f\t\u0005+E#E*\u0003\u0002S\u001f\tIa)\u001e8di&|g.\r"
)
public interface TraversableOps {
   boolean isParallel();

   boolean isParIterable();

   ParIterable asParIterable();

   boolean isParSeq();

   ParSeq asParSeq();

   Otherwise ifParSeq(final Function1 isbody);

   static void $init$(final TraversableOps $this) {
   }

   public interface Otherwise {
      Object otherwise(final Function0 notbody);
   }
}
