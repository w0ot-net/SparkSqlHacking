package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import scala.Function0;
import scala.Function1;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=q!B\u0007\u000f\u0011\u0003)b!B\f\u000f\u0011\u0003A\u0002\"B\u0010\u0002\t\u0003\u0001S\u0001B\u0011\u0002\u0001\t*A!K\u0001\u0001U\u0015!\u0011(\u0001\u0001;\u000b\u0011y\u0014\u0001\u0001!\t\u000b\t\u000bA1A\"\t\u000b-\u000bA1\u0001'\t\u000bU\u000bA1\u0001,\t\u000bU\u000bA1A1\t\u000b=\fA\u0011\u00019\t\r}\fA\u0011AA\u0001\u0003\u001d\u0001\u0018mY6bO\u0016T!a\u0004\t\u0002\u000b\rD\u0017\u000e\u001c7\u000b\u0005E\u0011\u0012a\u0002;xSR$XM\u001d\u0006\u0002'\u0005\u00191m\\7\u0004\u0001A\u0011a#A\u0007\u0002\u001d\t9\u0001/Y2lC\u001e,7CA\u0001\u001a!\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!\u0006\u0002\u0005\u0017JLx\u000e\u0005\u0002$Q5\tAE\u0003\u0002&M\u0005!1N]=p\u0015\t9##\u0001\tfg>$XM]5dg>4Go^1sK&\u0011\u0011\u0005\n\u0002\f\u0017N+'/[1mSj,'/\u0006\u0002,aA\u00191\u0005\f\u0018\n\u00055\"#AC*fe&\fG.\u001b>feB\u0011q\u0006\r\u0007\u0001\t\u0015\tDA1\u00013\u0005\u0005!\u0016CA\u001a7!\tQB'\u0003\u000267\t9aj\u001c;iS:<\u0007C\u0001\u000e8\u0013\tA4DA\u0002B]f\u0014Q!\u00138qkR\u0004\"a\u000f \u000e\u0003qR!!\u0010\u0013\u0002\u0005%|\u0017BA\u001d=\u0005\u0019yU\u000f\u001e9viB\u00111(Q\u0005\u0003\u007fq\na\u0001^8SS\u000eDGC\u0001#H!\t1R)\u0003\u0002G\u001d\tA!+[2i\u0017JLx\u000eC\u0003I\u000f\u0001\u0007\u0011*A\u0001l!\tQ5!D\u0001\u0002\u00039!x.\u00138ti\u0006tG/[1u_J$\"!\u0014)\u0011\u0005Yq\u0015BA(\u000f\u0005AY%/_8J]N$\u0018M\u001c;jCR|'\u000fC\u0003R\u0011\u0001\u0007!+\u0001\u0002g]B\u0019!dU%\n\u0005Q[\"!\u0003$v]\u000e$\u0018n\u001c81\u0003-!xNU3hSN$(/\u0019:\u0015\u0005]S\u0006C\u0001\fY\u0013\tIfB\u0001\bJ\u0017JLxNU3hSN$(/\u0019:\t\u000bEK\u0001\u0019A.\u0011\tia\u0016JX\u0005\u0003;n\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005iy\u0016B\u00011\u001c\u0005\u0011)f.\u001b;\u0015\u0005]\u0013\u0007\"B2\u000b\u0001\u0004!\u0017!B5uK6\u001c\bcA3m/:\u0011am\u001b\b\u0003O*l\u0011\u0001\u001b\u0006\u0003SR\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000f\n\u00055Y\u0012BA7o\u0005!IE/\u001a:bE2,'BA\u0007\u001c\u0003E\u0001(/\u001b8u\u0013\u001a\u0014VmZ5ti\u0016\u0014X\r\u001a\u000b\u0003/FDQA]\u0006A\u0002M\f1a\u00197ta\t!X\u0010E\u0002vsrt!A^<\u0011\u0005\u001d\\\u0012B\u0001=\u001c\u0003\u0019\u0001&/\u001a3fM&\u0011!p\u001f\u0002\u0006\u00072\f7o\u001d\u0006\u0003qn\u0001\"aL?\u0005\u0013y\f\u0018\u0011!A\u0001\u0006\u0003\u0011$aA0%c\u0005\u0019\u0012m]:feRtu\u000e\u001e*fO&\u001cH/\u001a:fIR\u0019q+a\u0001\t\rId\u0001\u0019AA\u0003a\u0011\t9!a\u0003\u0011\tUL\u0018\u0011\u0002\t\u0004_\u0005-AaCA\u0007\u0003\u0007\t\t\u0011!A\u0003\u0002I\u00121a\u0018\u00133\u0001"
)
public final class package {
   public static IKryoRegistrar assertNotRegistered(final Class cls) {
      return package$.MODULE$.assertNotRegistered(cls);
   }

   public static IKryoRegistrar printIfRegistered(final Class cls) {
      return package$.MODULE$.printIfRegistered(cls);
   }

   public static IKryoRegistrar toRegistrar(final Iterable items) {
      return package$.MODULE$.toRegistrar(items);
   }

   public static IKryoRegistrar toRegistrar(final Function1 fn) {
      return package$.MODULE$.toRegistrar(fn);
   }

   public static KryoInstantiator toInstantiator(final Function0 fn) {
      return package$.MODULE$.toInstantiator(fn);
   }

   public static RichKryo toRich(final Kryo k) {
      return package$.MODULE$.toRich(k);
   }
}
