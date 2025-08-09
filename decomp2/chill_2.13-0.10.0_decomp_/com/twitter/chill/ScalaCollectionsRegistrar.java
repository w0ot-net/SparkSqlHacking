package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import java.util.ArrayList;
import java.util.HashMap;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.StrictOptimizedLinearSeqOps;
import scala.collection.JavaConverters.;
import scala.collection.immutable.BitSet;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.List;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.ListSet;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.Queue;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.SortedMap;
import scala.collection.immutable.SortedSet;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u000512Aa\u0001\u0003\u0001\u0017!)\u0001\u0004\u0001C\u00013!)1\u0004\u0001C\u00019\tI2kY1mC\u000e{G\u000e\\3di&|gn\u001d*fO&\u001cHO]1s\u0015\t)a!A\u0003dQ&dGN\u0003\u0002\b\u0011\u00059Ao^5ui\u0016\u0014(\"A\u0005\u0002\u0007\r|Wn\u0001\u0001\u0014\u0007\u0001aA\u0003\u0005\u0002\u000e%5\taB\u0003\u0002\u0010!\u0005!A.\u00198h\u0015\u0005\t\u0012\u0001\u00026bm\u0006L!a\u0005\b\u0003\r=\u0013'.Z2u!\t)b#D\u0001\u0005\u0013\t9BA\u0001\bJ\u0017JLxNU3hSN$(/\u0019:\u0002\rqJg.\u001b;?)\u0005Q\u0002CA\u000b\u0001\u0003\u0015\t\u0007\u000f\u001d7z)\ti2\u0005\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sD\u0001\u0003V]&$\b\"\u0002\u0013\u0003\u0001\u0004)\u0013\u0001\u00028fo.\u0003\"AJ\u0015\u000f\u0005U9\u0013B\u0001\u0015\u0005\u0003\u001d\u0001\u0018mY6bO\u0016L!AK\u0016\u0003\t-\u0013\u0018p\u001c\u0006\u0003Q\u0011\u0001"
)
public class ScalaCollectionsRegistrar implements IKryoRegistrar {
   public void apply(final Kryo newK) {
      useField$1(.MODULE$.seqAsJavaListConverter((Seq)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{1, 2, 3}))).asJava().getClass(), newK);
      useField$1(.MODULE$.asJavaIteratorConverter(((StrictOptimizedLinearSeqOps)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{1, 2, 3}))).iterator()).asJava().getClass(), newK);
      useField$1(.MODULE$.mapAsJavaMapConverter((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(1)), BoxesRunTime.boxToInteger(2)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(4)), BoxesRunTime.boxToInteger(3))})))).asJava().getClass(), newK);
      useField$1(.MODULE$.asScalaBufferConverter(new ArrayList()).asScala().getClass(), newK);
      useField$1(.MODULE$.mapAsScalaMapConverter(new HashMap()).asScala().getClass(), newK);
      package$ var10000 = package$.MODULE$;
      package$ var10001 = package$.MODULE$;
      package$ var10002 = package$.MODULE$;
      package$ var10003 = package$.MODULE$;
      package$ var10004 = package$.MODULE$;
      package$ var10005 = package$.MODULE$;
      package$ var10006 = package$.MODULE$;
      package$ var10007 = package$.MODULE$;
      package$ var10008 = package$.MODULE$;
      package$ var10009 = package$.MODULE$;
      package$ var10010 = package$.MODULE$;
      package$ var10011 = package$.MODULE$;
      package$ var10012 = package$.MODULE$;
      package$ var10013 = package$.MODULE$;
      package$ var10014 = package$.MODULE$;
      package$ var10015 = package$.MODULE$;
      package$ var10016 = package$.MODULE$;
      package$ var10017 = package$.MODULE$;
      package$ var10018 = package$.MODULE$;
      package$ var10019 = package$.MODULE$;
      package$ var10020 = package$.MODULE$;
      package$ var10021 = package$.MODULE$;
      package$ var10022 = package$.MODULE$;
      package$ var10023 = package$.MODULE$;
      package$ var10024 = package$.MODULE$;
      package$ var10025 = package$.MODULE$;
      RichKryo qual$1 = package$.MODULE$.toRich(package$.MODULE$.toRich(package$.MODULE$.toRich(package$.MODULE$.toRich(package$.MODULE$.toRich(package$.MODULE$.toRich(package$.MODULE$.toRich(newK).forSubclass(new WrappedArraySerializer(), scala.reflect.ClassTag..MODULE$.apply(ArraySeq.class))).forSubclass(new BitSetSerializer(), scala.reflect.ClassTag..MODULE$.apply(BitSet.class))).forSubclass(new SortedSetSerializer(), scala.reflect.ClassTag..MODULE$.apply(SortedSet.class))).forClass(new SomeSerializer(), scala.reflect.ClassTag..MODULE$.apply(Some.class))).forClass(new LeftSerializer(), scala.reflect.ClassTag..MODULE$.apply(Left.class))).forClass(new RightSerializer(), scala.reflect.ClassTag..MODULE$.apply(Right.class)));
      Queue x$1 = scala.collection.immutable.Queue..MODULE$.empty();
      boolean x$2 = qual$1.forTraversableSubclass$default$2();
      RichKryo qual$2 = var10025.toRich(qual$1.forTraversableSubclass(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Queue.class), scala.collection.immutable.Queue..MODULE$.iterableFactory()));
      List x$3 = scala.package..MODULE$.List().empty();
      boolean x$4 = qual$2.forTraversableSubclass$default$2();
      RichKryo qual$3 = var10022.toRich(var10023.toRich(var10024.toRich(qual$2.forTraversableSubclass(x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(List.class), scala.collection.immutable.List..MODULE$.iterableFactory())).forTraversableSubclass(scala.collection.mutable.ListBuffer..MODULE$.empty(), false, scala.reflect.ClassTag..MODULE$.apply(ListBuffer.class), scala.collection.mutable.ListBuffer..MODULE$.iterableFactory())).forTraversableSubclass((Iterable)scala.collection.mutable.Buffer..MODULE$.empty(), false, scala.reflect.ClassTag..MODULE$.apply(Buffer.class), scala.collection.mutable.Buffer..MODULE$.iterableFactory()));
      Vector x$5 = scala.package..MODULE$.Vector().empty();
      boolean x$6 = qual$3.forTraversableClass$default$2();
      RichKryo qual$4 = var10021.toRich(qual$3.forTraversableClass(x$5, x$6, scala.reflect.ClassTag..MODULE$.apply(Vector.class), scala.collection.immutable.Vector..MODULE$.iterableFactory()));
      ListSet x$7 = scala.collection.immutable.ListSet..MODULE$.empty();
      boolean x$8 = qual$4.forTraversableSubclass$default$2();
      RichKryo qual$5 = var10020.toRich(qual$4.forTraversableSubclass(x$7, x$8, scala.reflect.ClassTag..MODULE$.apply(ListSet.class), scala.collection.immutable.ListSet..MODULE$.iterableFactory()));
      Set x$9 = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{apply<invokedynamic>()}));
      boolean x$10 = qual$5.forConcreteTraversableClass$default$2();
      RichKryo qual$6 = var10019.toRich(qual$5.forConcreteTraversableClass(x$9, x$10, scala.collection.immutable.Set..MODULE$.iterableFactory()));
      Set x$11 = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{apply<invokedynamic>(), apply<invokedynamic>()}));
      boolean x$12 = qual$6.forConcreteTraversableClass$default$2();
      RichKryo qual$7 = var10018.toRich(qual$6.forConcreteTraversableClass(x$11, x$12, scala.collection.immutable.Set..MODULE$.iterableFactory()));
      Set x$13 = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{apply<invokedynamic>(), apply<invokedynamic>(), apply<invokedynamic>()}));
      boolean x$14 = qual$7.forConcreteTraversableClass$default$2();
      RichKryo qual$8 = var10017.toRich(qual$7.forConcreteTraversableClass(x$13, x$14, scala.collection.immutable.Set..MODULE$.iterableFactory()));
      Set x$15 = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{apply<invokedynamic>(), apply<invokedynamic>(), apply<invokedynamic>(), apply<invokedynamic>()}));
      boolean x$16 = qual$8.forConcreteTraversableClass$default$2();
      RichKryo qual$9 = var10016.toRich(qual$8.forConcreteTraversableClass(x$15, x$16, scala.collection.immutable.Set..MODULE$.iterableFactory()));
      HashSet x$17 = (HashSet)scala.collection.immutable.HashSet..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{apply<invokedynamic>(), apply<invokedynamic>(), apply<invokedynamic>(), apply<invokedynamic>(), apply<invokedynamic>()}));
      boolean x$18 = qual$9.forConcreteTraversableClass$default$2();
      RichKryo qual$10 = var10015.toRich(qual$9.forConcreteTraversableClass(x$17, x$18, scala.collection.immutable.HashSet..MODULE$.iterableFactory()));
      scala.collection.immutable.Map x$19 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>())})));
      boolean x$20 = qual$10.forConcreteTraversableClass$default$2();
      RichKryo qual$11 = var10014.toRich(qual$10.forConcreteTraversableClass(x$19, x$20, scala.collection.immutable.Map..MODULE$.mapFactory()));
      scala.collection.immutable.Map x$21 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>())})));
      boolean x$22 = qual$11.forConcreteTraversableClass$default$2();
      RichKryo qual$12 = var10013.toRich(qual$11.forConcreteTraversableClass(x$21, x$22, scala.collection.immutable.Map..MODULE$.mapFactory()));
      scala.collection.immutable.Map x$23 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>())})));
      boolean x$24 = qual$12.forConcreteTraversableClass$default$2();
      RichKryo qual$13 = var10012.toRich(qual$12.forConcreteTraversableClass(x$23, x$24, scala.collection.immutable.Map..MODULE$.mapFactory()));
      scala.collection.immutable.Map x$25 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>())})));
      boolean x$26 = qual$13.forConcreteTraversableClass$default$2();
      RichKryo qual$14 = var10011.toRich(qual$13.forConcreteTraversableClass(x$25, x$26, scala.collection.immutable.Map..MODULE$.mapFactory()));
      scala.collection.immutable.HashMap x$27 = (scala.collection.immutable.HashMap)scala.collection.immutable.HashMap..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(apply<invokedynamic>()), apply<invokedynamic>())})));
      boolean x$28 = qual$14.forConcreteTraversableClass$default$2();
      RichKryo qual$15 = var10008.toRich(var10009.toRich(var10010.toRich(qual$14.forConcreteTraversableClass(x$27, x$28, scala.collection.immutable.HashMap..MODULE$.mapFactory())).registerClasses(scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Range.Inclusive.class, NumericRange.Inclusive.class, NumericRange.Exclusive.class}))))).forSubclass(new SortedMapSerializer(), scala.reflect.ClassTag..MODULE$.apply(SortedMap.class)));
      ListMap x$29 = scala.collection.immutable.ListMap..MODULE$.empty();
      boolean x$30 = qual$15.forTraversableSubclass$default$2();
      RichKryo qual$16 = var10007.toRich(qual$15.forTraversableSubclass(x$29, x$30, scala.reflect.ClassTag..MODULE$.apply(ListMap.class), scala.collection.immutable.ListMap..MODULE$.mapFactory()));
      scala.collection.immutable.HashMap x$31 = scala.collection.immutable.HashMap..MODULE$.empty();
      boolean x$32 = qual$16.forTraversableSubclass$default$2();
      RichKryo qual$17 = var10006.toRich(qual$16.forTraversableSubclass(x$31, x$32, scala.reflect.ClassTag..MODULE$.apply(scala.collection.immutable.HashMap.class), scala.collection.immutable.HashMap..MODULE$.mapFactory()));
      scala.collection.immutable.Map x$33 = scala.Predef..MODULE$.Map().empty();
      boolean x$34 = qual$17.forTraversableSubclass$default$2();
      var10000.toRich(var10001.toRich(var10002.toRich(var10003.toRich(var10004.toRich(var10005.toRich(qual$17.forTraversableSubclass(x$33, x$34, scala.reflect.ClassTag..MODULE$.apply(scala.collection.immutable.Map.class), scala.collection.immutable.Map..MODULE$.mapFactory())).forTraversableClass(scala.collection.mutable.BitSet..MODULE$.empty(), false, scala.reflect.ClassTag..MODULE$.apply(scala.collection.mutable.BitSet.class), scala.collection.mutable.BitSet..MODULE$.specificIterableFactory())).forTraversableClass(scala.collection.mutable.HashMap..MODULE$.empty(), false, scala.reflect.ClassTag..MODULE$.apply(scala.collection.mutable.HashMap.class), scala.collection.mutable.HashMap..MODULE$.mapFactory())).forTraversableClass(scala.collection.mutable.HashSet..MODULE$.empty(), false, scala.reflect.ClassTag..MODULE$.apply(scala.collection.mutable.HashSet.class), scala.collection.mutable.HashSet..MODULE$.iterableFactory())).forTraversableSubclass(scala.collection.mutable.Queue..MODULE$.empty(), false, scala.reflect.ClassTag..MODULE$.apply(scala.collection.mutable.Queue.class), scala.collection.mutable.Queue..MODULE$.iterableFactory())).forTraversableSubclass((Iterable)scala.collection.mutable.Map..MODULE$.empty(), false, scala.reflect.ClassTag..MODULE$.apply(scala.collection.mutable.Map.class), scala.collection.mutable.Map..MODULE$.mapFactory())).forTraversableSubclass((Iterable)scala.collection.mutable.Set..MODULE$.empty(), false, scala.reflect.ClassTag..MODULE$.apply(scala.collection.mutable.Set.class), scala.collection.mutable.Set..MODULE$.iterableFactory());
   }

   private static final void useField$1(final Class cls, final Kryo newK$1) {
      FieldSerializer fs = new FieldSerializer(newK$1, cls);
      fs.setIgnoreSyntheticFields(false);
      newK$1.register(cls, fs);
   }
}
