package scala.collection.parallel.immutable;

import scala.;
import scala.Tuple2;
import scala.collection.generic.GenericParMapCompanion;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005ba\u0002\u0005\n!\u0003\r\tA\u0005\u0005\u00063\u0002!\tA\u0017\u0005\u0006=\u00021\ta\u0018\u0005\u0006M\u00021\ta\u001a\u0005\u0006Q\u0002!\t%\u001b\u0005\u0006q\u0002!\t%\u001f\u0005\b\u0003\u0013\u0001a\u0011AA\u0006\u0011\u001d\tY\u0002\u0001D\u0001\u0003;\u0011!\u0002U1s\u001b\u0006\u0004H*[6f\u0015\tQ1\"A\u0005j[6,H/\u00192mK*\u0011A\"D\u0001\ta\u0006\u0014\u0018\r\u001c7fY*\u0011abD\u0001\u000bG>dG.Z2uS>t'\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U11#H\u0014+s\t\u001bB\u0001\u0001\u000b\u0019!B\u0011QCF\u0007\u0002\u001f%\u0011qc\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u000feQ2DJ\u00159\u00036\t1\"\u0003\u0002\t\u0017A\u0011A$\b\u0007\u0001\t\u0015q\u0002A1\u0001 \u0005\u0005Y\u0015C\u0001\u0011$!\t)\u0012%\u0003\u0002#\u001f\t9aj\u001c;iS:<\u0007CA\u000b%\u0013\t)sBA\u0002B]f\u0004\"\u0001H\u0014\u0005\r!\u0002AQ1\u0001 \u0005\u00051\u0006C\u0001\u000f+\t\u0019Y\u0003\u0001\"b\u0001Y\t\u00111iQ\u000b\u0004[M2\u0014C\u0001\u0011/!\u0011y\u0003GM\u001b\u000e\u0003%I!!M\u0005\u0003\rA\u000b'/T1q!\ta2\u0007B\u00035U\t\u0007qDA\u0001Y!\tab\u0007B\u00038U\t\u0007qDA\u0001Z!\ta\u0012\b\u0002\u0004;\u0001\u0011\u0015\ra\u000f\u0002\u0005%\u0016\u0004(/\u0005\u0002!yI\u0019QhP(\u0007\ty\u0002\u0001\u0001\u0010\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\b_\u0001Yb\u0005\u0011\u001dB!\ty\u0003\u0007\u0005\u0002\u001d\u0005\u001211\t\u0001CC\u0002\u0011\u0013!bU3rk\u0016tG/[1m#\t\u0001SIE\u0002G\u000f.3AA\u0010\u0001\u0001\u000bB!\u0001*S\u000e'\u001b\u0005i\u0011B\u0001&\u000e\u0005\ri\u0015\r\u001d\t\u0007\u00112[bET!\n\u00055k!AB'ba>\u00038\u000f\u0005\u0002I\u0013B!q\u0006M\u000e'!\u0019I\u0012k\u0015,9\u0003&\u0011!k\u0003\u0002\u0010!\u0006\u0014\u0018\n^3sC\ndW\rT5lKB!Q\u0003V\u000e'\u0013\t)vB\u0001\u0004UkBdWM\r\t\u0003_]K!\u0001W\u0005\u0003\u0017A\u000b'/\u0013;fe\u0006\u0014G.Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003m\u0003\"!\u0006/\n\u0005u{!\u0001B+oSR\fA\"\\1q\u0007>l\u0007/\u00198j_:,\u0012\u0001\u0019\t\u0004C\u0012LS\"\u00012\u000b\u0005\rl\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003K\n\u0014acR3oKJL7\rU1s\u001b\u0006\u00048i\\7qC:LwN\\\u0001\u0006K6\u0004H/_\u000b\u0002q\u0005)Ao\\'baV\u0019!.\u001c9\u0015\u0005-\u0014\b\u0003B\u00181Y>\u0004\"\u0001H7\u0005\u000b9$!\u0019A\u0010\u0003\u0003A\u0003\"\u0001\b9\u0005\u000bE$!\u0019A\u0010\u0003\u0003ECQa\u001d\u0003A\u0004Q\f!!\u001a<\u0011\tU)8k^\u0005\u0003m>\u0011\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0011\tU!Fn\\\u0001\bkB$\u0017\r^3e+\tQX\u0010F\u0003|\u0003\u0003\t)\u0001\u0005\u0003\u001dUma\bC\u0001\u000f~\t\u0015qXA1\u0001\u0000\u0005\u0005)\u0016C\u0001\u0014$\u0011\u0019\t\u0019!\u0002a\u00017\u0005\u00191.Z=\t\r\u0005\u001dQ\u00011\u0001}\u0003\u00151\u0018\r\\;f\u0003\u0015!\u0003\u000f\\;t+\u0011\ti!a\u0005\u0015\t\u0005=\u0011Q\u0003\t\u00069)Z\u0012\u0011\u0003\t\u00049\u0005MA!\u0002@\u0007\u0005\u0004y\bbBA\f\r\u0001\u0007\u0011\u0011D\u0001\u0003WZ\u0004R!\u0006+\u001c\u0003#\ta\u0001J7j]V\u001cHc\u0001\u001d\u0002 !1\u00111A\u0004A\u0002m\u0001"
)
public interface ParMapLike extends scala.collection.parallel.ParMapLike {
   GenericParMapCompanion mapCompanion();

   ParMap empty();

   // $FF: synthetic method
   static ParMap toMap$(final ParMapLike $this, final .less.colon.less ev) {
      return $this.toMap(ev);
   }

   default ParMap toMap(final .less.colon.less ev) {
      return (ParMap)this;
   }

   // $FF: synthetic method
   static ParMap updated$(final ParMapLike $this, final Object key, final Object value) {
      return $this.updated(key, value);
   }

   default ParMap updated(final Object key, final Object value) {
      return this.$plus(new Tuple2(key, value));
   }

   ParMap $plus(final Tuple2 kv);

   ParMap $minus(final Object key);

   static void $init$(final ParMapLike $this) {
   }
}
