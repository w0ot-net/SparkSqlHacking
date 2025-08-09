package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import scala.Symbol;
import scala.collection.immutable.Vector;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512Qa\u0001\u0003\u0001\t)AQa\u0006\u0001\u0005\u0002eAQa\u0007\u0001\u0005Bq\u0011q#\u00117m'\u000e\fG.\u0019*fO&\u001cHO]1s\u0007>l\u0007/\u0019;\u000b\u0005\u00151\u0011!B2iS2d'BA\u0004\t\u0003\u001d!x/\u001b;uKJT\u0011!C\u0001\u0004G>l7c\u0001\u0001\f'A\u0011A\"E\u0007\u0002\u001b)\u0011abD\u0001\u0005Y\u0006twMC\u0001\u0011\u0003\u0011Q\u0017M^1\n\u0005Ii!AB(cU\u0016\u001cG\u000f\u0005\u0002\u0015+5\tA!\u0003\u0002\u0017\t\tq\u0011j\u0013:z_J+w-[:ue\u0006\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003i\u0001\"\u0001\u0006\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"\u0001B+oSRDQ\u0001\n\u0002A\u0002\u0015\nAA\\3x\u0017B\u0011a%\u000b\b\u0003)\u001dJ!\u0001\u000b\u0003\u0002\u000fA\f7m[1hK&\u0011!f\u000b\u0002\u0005\u0017JLxN\u0003\u0002)\t\u0001"
)
public class AllScalaRegistrarCompat implements IKryoRegistrar {
   public void apply(final Kryo newK) {
      package$ var10000 = package$.MODULE$;
      RichKryo qual$1 = package$.MODULE$.toRich(newK);
      Vector x$1 = (Vector).MODULE$.Vector().apply(scala.collection.immutable.Nil..MODULE$);
      boolean x$2 = qual$1.forConcreteTraversableClass$default$2();
      RichKryo qual$2 = var10000.toRich(qual$1.forConcreteTraversableClass(x$1, x$2, scala.collection.immutable.Vector..MODULE$.iterableFactory()));
      Vector x$3 = (Vector).MODULE$.Vector().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Symbol[]{apply<invokedynamic>()})));
      boolean x$4 = qual$2.forConcreteTraversableClass$default$2();
      qual$2.forConcreteTraversableClass(x$3, x$4, scala.collection.immutable.Vector..MODULE$.iterableFactory());
   }
}
