package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512Aa\u0001\u0003\u0001\u0017!)\u0001\u0004\u0001C\u00013!)1\u0004\u0001C\u00019\t\t\u0012\t\u001c7TG\u0006d\u0017MU3hSN$(/\u0019:\u000b\u0005\u00151\u0011!B2iS2d'BA\u0004\t\u0003\u001d!x/\u001b;uKJT\u0011!C\u0001\u0004G>l7\u0001A\n\u0004\u00011!\u0002CA\u0007\u0013\u001b\u0005q!BA\b\u0011\u0003\u0011a\u0017M\\4\u000b\u0003E\tAA[1wC&\u00111C\u0004\u0002\u0007\u001f\nTWm\u0019;\u0011\u0005U1R\"\u0001\u0003\n\u0005]!!AD%Lef|'+Z4jgR\u0014\u0018M]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003i\u0001\"!\u0006\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"\u0001B+oSRDQ\u0001\n\u0002A\u0002\u0015\n\u0011a\u001b\t\u0003M%r!!F\u0014\n\u0005!\"\u0011a\u00029bG.\fw-Z\u0005\u0003U-\u0012Aa\u0013:z_*\u0011\u0001\u0006\u0002"
)
public class AllScalaRegistrar implements IKryoRegistrar {
   public void apply(final Kryo k) {
      (new AllScalaRegistrar_0_9_5()).apply(k);
      (new AllScalaRegistrarCompat()).apply(k);
   }
}
