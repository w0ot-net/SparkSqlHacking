package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.MapOps;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.ListSet;
import scala.collection.immutable.Map;
import scala.collection.immutable.Queue;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.TreeMap;
import scala.collection.immutable.TreeSet;
import scala.collection.immutable.WrappedString;
import scala.math.BigDecimal;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.VolatileByteRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u000512Qa\u0001\u0003\u0003\t)AQa\u0006\u0001\u0005\u0002eAQa\u0007\u0001\u0005\u0002q\u0011q#\u00117m'\u000e\fG.\u0019*fO&\u001cHO]1s?Bz\u0016hX\u001b\u000b\u0005\u00151\u0011!B2iS2d'BA\u0004\t\u0003\u001d!x/\u001b;uKJT\u0011!C\u0001\u0004G>l7c\u0001\u0001\f'A\u0011A\"E\u0007\u0002\u001b)\u0011abD\u0001\u0005Y\u0006twMC\u0001\u0011\u0003\u0011Q\u0017M^1\n\u0005Ii!AB(cU\u0016\u001cG\u000f\u0005\u0002\u0015+5\tA!\u0003\u0002\u0017\t\tq\u0011j\u0013:z_J+w-[:ue\u0006\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003i\u0001\"\u0001\u0006\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"\u0001B+oSRDQ\u0001\n\u0002A\u0002\u0015\n\u0011a\u001b\t\u0003M%r!\u0001F\u0014\n\u0005!\"\u0011a\u00029bG.\fw-Z\u0005\u0003U-\u0012Aa\u0013:z_*\u0011\u0001\u0006\u0002"
)
public final class AllScalaRegistrar_0_9_5 implements IKryoRegistrar {
   public void apply(final Kryo k) {
      (new AllScalaRegistrar_0_9_2()).apply(k);
      (new AllScalaRegistrarCompat_0_9_5()).apply(k);
      package$ var10000 = package$.MODULE$;
      package$ var10001 = package$.MODULE$;
      package$ var10002 = package$.MODULE$;
      package$ var10003 = package$.MODULE$;
      package$ var10004 = package$.MODULE$;
      package$ var10005 = package$.MODULE$;
      package$ var10006 = package$.MODULE$;
      package$ var10007 = package$.MODULE$;
      package$ var10008 = package$.MODULE$;
      RichKryo qual$1 = package$.MODULE$.toRich(package$.MODULE$.toRich(k).registerClasses(.MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{byte[].class, short[].class, int[].class, long[].class, float[].class, double[].class, boolean[].class, char[].class, String[].class, Object[].class, Class.class, Object.class, scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Byte())).getClass(), scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Short())).getClass(), scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Int())).getClass(), scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Long())).getClass(), scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Float())).getClass(), scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Double())).getClass(), scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Boolean())).getClass(), scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Char())).getClass(), scala.collection.mutable.package..MODULE$.WrappedArray().make(scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(String.class))).getClass(), scala.None..MODULE$.getClass(), Queue.class, .MODULE$.Nil().getClass(), scala.collection.immutable..colon.colon.class, Range.class, WrappedString.class, TreeSet.class, TreeMap.class, scala.math.Ordering.Byte..MODULE$.getClass(), scala.math.Ordering.Short..MODULE$.getClass(), scala.math.Ordering.Int..MODULE$.getClass(), scala.math.Ordering.Long..MODULE$.getClass(), scala.math.Ordering.Float..MODULE$.getClass(), scala.math.Ordering.Double..MODULE$.getClass(), scala.math.Ordering.Boolean..MODULE$.getClass(), scala.math.Ordering.Char..MODULE$.getClass(), scala.math.Ordering.String..MODULE$.getClass()})))));
      Set x$1 = (Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$);
      boolean x$2 = qual$1.forConcreteTraversableClass$default$2();
      RichKryo qual$2 = var10008.toRich(qual$1.forConcreteTraversableClass(x$1, x$2, scala.collection.immutable.Set..MODULE$.iterableFactory()));
      ListSet x$3 = (ListSet)scala.collection.immutable.ListSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      boolean x$4 = qual$2.forConcreteTraversableClass$default$2();
      RichKryo qual$3 = var10007.toRich(qual$2.forConcreteTraversableClass(x$3, x$4, scala.collection.immutable.ListSet..MODULE$.iterableFactory()));
      ListSet x$5 = (ListSet)scala.collection.immutable.ListSet..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{apply<invokedynamic>()}));
      boolean x$6 = qual$3.forConcreteTraversableClass$default$2();
      RichKryo qual$4 = var10006.toRich(qual$3.forConcreteTraversableClass(x$5, x$6, scala.collection.immutable.ListSet..MODULE$.iterableFactory()));
      HashSet x$7 = (HashSet)scala.collection.immutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      boolean x$8 = qual$4.forConcreteTraversableClass$default$2();
      RichKryo qual$5 = var10005.toRich(qual$4.forConcreteTraversableClass(x$7, x$8, scala.collection.immutable.HashSet..MODULE$.iterableFactory()));
      HashSet x$9 = (HashSet)scala.collection.immutable.HashSet..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{apply<invokedynamic>()}));
      boolean x$10 = qual$5.forConcreteTraversableClass$default$2();
      RichKryo qual$6 = var10004.toRich(qual$5.forConcreteTraversableClass(x$9, x$10, scala.collection.immutable.HashSet..MODULE$.iterableFactory()));
      Map x$11 = (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      boolean x$12 = qual$6.forConcreteTraversableClass$default$2();
      RichKryo qual$7 = var10003.toRich(qual$6.forConcreteTraversableClass(x$11, x$12, scala.collection.immutable.Map..MODULE$.mapFactory()));
      HashMap x$13 = (HashMap)scala.collection.immutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      boolean x$14 = qual$7.forConcreteTraversableClass$default$2();
      RichKryo qual$8 = var10002.toRich(qual$7.forConcreteTraversableClass(x$13, x$14, scala.collection.immutable.HashMap..MODULE$.mapFactory()));
      HashMap x$15 = (HashMap)scala.collection.immutable.HashMap..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>())})));
      boolean x$16 = qual$8.forConcreteTraversableClass$default$2();
      RichKryo qual$9 = var10001.toRich(qual$8.forConcreteTraversableClass(x$15, x$16, scala.collection.immutable.HashMap..MODULE$.mapFactory()));
      ListMap x$17 = (ListMap)scala.collection.immutable.ListMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      boolean x$18 = qual$9.forConcreteTraversableClass$default$2();
      RichKryo qual$10 = var10000.toRich(qual$9.forConcreteTraversableClass(x$17, x$18, scala.collection.immutable.ListMap..MODULE$.mapFactory()));
      ListMap x$19 = (ListMap)scala.collection.immutable.ListMap..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>())})));
      boolean x$20 = qual$10.forConcreteTraversableClass$default$2();
      qual$10.forConcreteTraversableClass(x$19, x$20, scala.collection.immutable.ListMap..MODULE$.mapFactory());
      k.register(Stream.Cons.class, new StreamSerializer());
      k.register(.MODULE$.Stream().empty().getClass());
      package$.MODULE$.toRich(k).forClass(new VolatileByteRefSerializer(), scala.reflect.ClassTag..MODULE$.apply(VolatileByteRef.class));
      package$.MODULE$.toRich(k).forClass(new BigDecimalSerializer(), scala.reflect.ClassTag..MODULE$.apply(BigDecimal.class));
      k.register(scala.collection.immutable.Queue..MODULE$.empty().getClass());
      var10000 = package$.MODULE$;
      var10001 = package$.MODULE$;
      RichKryo qual$11 = package$.MODULE$.toRich(k);
      Map x$21 = ((MapOps)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(1)), BoxesRunTime.boxToInteger(2))})))).filterKeys((JFunction1.mcZI.sp)(x$1x) -> x$1x != 2).toMap(scala..less.colon.less..MODULE$.refl());
      boolean x$22 = qual$11.forConcreteTraversableClass$default$2();
      RichKryo qual$12 = var10001.toRich(qual$11.forConcreteTraversableClass(x$21, x$22, scala.collection.immutable.Map..MODULE$.mapFactory()));
      Map x$23 = ((MapOps)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(1)), BoxesRunTime.boxToInteger(2))})))).mapValues((JFunction1.mcII.sp)(x$2x) -> x$2x + 1).toMap(scala..less.colon.less..MODULE$.refl());
      boolean x$24 = qual$12.forConcreteTraversableClass$default$2();
      RichKryo qual$13 = var10000.toRich(qual$12.forConcreteTraversableClass(x$23, x$24, scala.collection.immutable.Map..MODULE$.mapFactory()));
      Set x$25 = ((scala.collection.immutable.MapOps)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(1)), BoxesRunTime.boxToInteger(2))})))).keySet();
      boolean x$26 = qual$13.forConcreteTraversableClass$default$2();
      qual$13.forConcreteTraversableClass(x$25, x$26, scala.collection.immutable.Set..MODULE$.iterableFactory());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
