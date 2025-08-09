package scala;

import scala.collection.ArrayOps$;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2aa\u0001\u0003\u0002\u0002\u00111\u0001\"B\u0006\u0001\t\u0003i\u0001\"B\b\u0001\t\u0007\u0001\"!\u0006'poB\u0013\u0018n\u001c:jifLU\u000e\u001d7jG&$8O\r\u0006\u0002\u000b\u0005)1oY1mCN\u0011\u0001a\u0002\t\u0003\u0011%i\u0011\u0001B\u0005\u0003\u0015\u0011\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u00039\u0001\"\u0001\u0003\u0001\u0002=\r|\u0007/_!se\u0006LHk\\%n[V$\u0018M\u00197f\u0013:$W\r_3e'\u0016\fXCA\t\u001c)\t\u0011B\u0005E\u0002\u0014-eq!\u0001\u0003\u000b\n\u0005U!\u0011a\u00029bG.\fw-Z\u0005\u0003/a\u0011!\"\u00138eKb,GmU3r\u0015\t)B\u0001\u0005\u0002\u001b71\u0001A!\u0002\u000f\u0003\u0005\u0004i\"!\u0001+\u0012\u0005y\t\u0003C\u0001\u0005 \u0013\t\u0001CAA\u0004O_RD\u0017N\\4\u0011\u0005!\u0011\u0013BA\u0012\u0005\u0005\r\te.\u001f\u0005\u0006K\t\u0001\rAJ\u0001\u0003qN\u00042\u0001C\u0014\u001a\u0013\tACAA\u0003BeJ\f\u0017\u0010\u000b\u0004\u0003U5r\u0003'\r\t\u0003\u0011-J!\u0001\f\u0003\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0003=\n\u00111R5na2L7-\u001b;!G>tg/\u001a:tS>t7\u000f\t4s_6\u0004\u0013I\u001d:bs\u0002\"x\u000eI5n[V$\u0018M\u00197f]%sG-\u001a=fIN+\u0017\u000fI1sK\u0002JW\u000e\u001d7f[\u0016tG/\u001a3!Ef\u00043m\u001c9zS:<7\bI;tK\u0002\u0002Go\\%oI\u0016DX\rZ*fc\u0002\u0004S\r\u001f9mS\u000eLG\u000f\\=!S\u001a\u0004\u0013p\\;!o\u0006tG\u000f\t;pA\r|\u0007/\u001f\u0017!_J\u0004So]3!i\",\u0007%\\8sK\u0002*gMZ5dS\u0016tG\u000f\t8p]6\u001aw\u000e]=j]\u001e\u0004\u0013I\u001d:bsN+\u0017OL;og\u00064Wm\u0016:ba\u0006\u0013(/Y=\u0002\u000bMLgnY3\"\u0003I\naA\r\u00182g9\u0002\u0004"
)
public abstract class LowPriorityImplicits2 {
   /** @deprecated */
   public IndexedSeq copyArrayToImmutableIndexedSeq(final Object xs) {
      return xs == null ? null : ArrayOps$.MODULE$.toIndexedSeq$extension(xs);
   }
}
