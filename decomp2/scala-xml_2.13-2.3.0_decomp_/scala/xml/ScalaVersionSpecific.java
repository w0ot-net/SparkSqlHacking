package scala.xml;

import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<aAC\u0006\t\u0002-yaAB\t\f\u0011\u0003Y!\u0003C\u0003\u0018\u0003\u0011\u0005\u0011$\u0002\u0003\u001b\u0003\u0001Yr!B\u001a\u0002\u0011\u0003!d!\u0002\u001c\u0002\u0011\u00039\u0004\"B\f\u0006\t\u00031\u0005\"B$\u0006\t\u0003A\u0005\"B)\u0006\t\u0003\u0011V\u0001\u0002.\u0002\u0001m\u000bAcU2bY\u00064VM]:j_:\u001c\u0006/Z2jM&\u001c'B\u0001\u0007\u000e\u0003\rAX\u000e\u001c\u0006\u0002\u001d\u0005)1oY1mCB\u0011\u0001#A\u0007\u0002\u0017\t!2kY1mCZ+'o]5p]N\u0003XmY5gS\u000e\u001c\"!A\n\u0011\u0005Q)R\"A\u0007\n\u0005Yi!AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005y!aA\"C\rV!A\u0004\n\u00182!\u0015i\u0002EI\u00171\u001b\u0005q\"BA\u0010\u000e\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003Cy\u0011\u0011BQ;jY\u00124%o\\7\u0011\u0005\r\"C\u0002\u0001\u0003\u0007K\rA)\u0019\u0001\u0014\u0003\t\u0019\u0013x.\\\t\u0003O)\u0002\"\u0001\u0006\u0015\n\u0005%j!a\u0002(pi\"Lgn\u001a\t\u0003)-J!\u0001L\u0007\u0003\u0007\u0005s\u0017\u0010\u0005\u0002$]\u00111qf\u0001EC\u0002\u0019\u0012\u0011!\u0011\t\u0003GE\"aAM\u0002\u0005\u0006\u00041#!A\"\u0002\u00159{G-Z*fc\u000e\u0013e\t\u0005\u00026\u000b5\t\u0011A\u0001\u0006O_\u0012,7+Z9D\u0005\u001a\u001b2!B\n9!\u0015i\u0002%\u000f!D!\tQTH\u0004\u0002\u0011w%\u0011AhC\u0001\b\u001d>$WmU3r\u0013\tqtH\u0001\u0003D_2d'B\u0001\u001f\f!\t\u0001\u0012)\u0003\u0002C\u0017\t!aj\u001c3f!\t\u0001B)\u0003\u0002F\u0017\t9aj\u001c3f'\u0016\fH#\u0001\u001b\u0002\u00159,wOQ;jY\u0012,'\u000f\u0006\u0002J\u001fB!!*\u0014!D\u001b\u0005Y%B\u0001'\u001f\u0003\u001diW\u000f^1cY\u0016L!AT&\u0003\u000f\t+\u0018\u000e\u001c3fe\")\u0001k\u0002a\u0001s\u0005!aM]8n\u000311'o\\7Ta\u0016\u001c\u0017NZ5d)\t\u0019\u0016\f\u0006\u0002D)\")Q\u000b\u0003a\u0001-\u0006\u0011\u0011\u000e\u001e\t\u0004;]\u0003\u0015B\u0001-\u001f\u00051IE/\u001a:bE2,wJ\\2f\u0011\u0015\u0001\u0006\u00021\u0001:\u0005E\u0019V-\u001d(pI\u0016,f.\u00199qYf\u001cV-\u001d\t\u00049~\u0003U\"A/\u000b\u0005ys\u0012!C5n[V$\u0018M\u00197f\u0013\t\u0001WLA\u0002TKF\u0004"
)
public final class ScalaVersionSpecific {
   public static class NodeSeqCBF$ implements BuildFrom {
      public static final NodeSeqCBF$ MODULE$ = new NodeSeqCBF$();

      static {
         BuildFrom.$init$(MODULE$);
      }

      /** @deprecated */
      public Builder apply(final Object from) {
         return BuildFrom.apply$(this, from);
      }

      public Factory toFactory(final Object from) {
         return BuildFrom.toFactory$(this, from);
      }

      public Builder newBuilder(final NodeSeq from) {
         return NodeSeq$.MODULE$.newBuilder();
      }

      public NodeSeq fromSpecific(final NodeSeq from, final IterableOnce it) {
         return (NodeSeq)((Builder)NodeSeq$.MODULE$.newBuilder().$plus$plus$eq(from)).result();
      }
   }
}
