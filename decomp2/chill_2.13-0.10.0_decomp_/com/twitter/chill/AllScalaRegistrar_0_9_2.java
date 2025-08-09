package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.java.Java8ClosureRegistrar;
import com.twitter.chill.java.PackageRegistrar;
import scala.Enumeration;
import scala.Symbol;
import scala.Symbol.;
import scala.reflect.ClassTag;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u000512Qa\u0001\u0003\u0003\t)AQa\u0006\u0001\u0005\u0002eAQa\u0007\u0001\u0005\u0002q\u0011q#\u00117m'\u000e\fG.\u0019*fO&\u001cHO]1s?Bz\u0016h\u0018\u001a\u000b\u0005\u00151\u0011!B2iS2d'BA\u0004\t\u0003\u001d!x/\u001b;uKJT\u0011!C\u0001\u0004G>l7c\u0001\u0001\f'A\u0011A\"E\u0007\u0002\u001b)\u0011abD\u0001\u0005Y\u0006twMC\u0001\u0011\u0003\u0011Q\u0017M^1\n\u0005Ii!AB(cU\u0016\u001cG\u000f\u0005\u0002\u0015+5\tA!\u0003\u0002\u0017\t\tq\u0011j\u0013:z_J+w-[:ue\u0006\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003i\u0001\"\u0001\u0006\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"\u0001B+oSRDQ\u0001\n\u0002A\u0002\u0015\n\u0011a\u001b\t\u0003M%r!\u0001F\u0014\n\u0005!\"\u0011a\u00029bG.\fw-Z\u0005\u0003U-\u0012Aa\u0013:z_*\u0011\u0001\u0006\u0002"
)
public final class AllScalaRegistrar_0_9_2 implements IKryoRegistrar {
   public void apply(final Kryo k) {
      (new ScalaCollectionsRegistrar()).apply(k);
      (new JavaWrapperCollectionRegistrar()).apply(k);
      ScalaTupleSerialization$.MODULE$.register().apply(k);
      package$.MODULE$.toRich(package$.MODULE$.toRich(package$.MODULE$.toRich(package$.MODULE$.toRich(package$.MODULE$.toRich(k).forClass(new Serializer() {
         public boolean isImmutable() {
            return true;
         }

         public void write(final Kryo k, final Output out, final Symbol obj) {
            out.writeString(obj.name());
         }

         public Symbol read(final Kryo k, final Input in, final Class cls) {
            return .MODULE$.apply(in.readString());
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Symbol.class))).forSubclass(new RegexSerializer(), scala.reflect.ClassTag..MODULE$.apply(Regex.class))).forClass(new ClassTagSerializer(), scala.reflect.ClassTag..MODULE$.apply(ClassTag.class))).forSubclass(new ManifestSerializer(), scala.reflect.ClassTag..MODULE$.apply(Manifest.class))).forSubclass(new EnumerationSerializer(), scala.reflect.ClassTag..MODULE$.apply(Enumeration.Value.class));
      BoxedUnit boxedUnit = BoxedUnit.UNIT;
      k.register(boxedUnit.getClass(), new SingletonSerializer(boxedUnit));
      PackageRegistrar.all().apply(k);
      (new Java8ClosureRegistrar()).apply(k);
   }
}
