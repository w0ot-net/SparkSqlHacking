package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.collection.ClassTagIterableFactory;
import scala.collection.ClassTagSeqFactory;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedClassTagSeqFactory;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.GrowableBuilder;
import scala.math.Integral;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime$;

public final class ArraySeq$ implements StrictOptimizedClassTagSeqFactory {
   public static final ArraySeq$ MODULE$ = new ArraySeq$();
   private static final long serialVersionUID = 3L;
   private static ArraySeq.ofRef emptyImpl;
   private static final SeqFactory untagged;
   private static volatile boolean bitmap$0;

   static {
      ArraySeq$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      untagged = new ClassTagSeqFactory.AnySeqDelegate(MODULE$);
   }

   public final scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      return ClassTagSeqFactory.unapplySeq$(this, x);
   }

   public Object range(final Object start, final Object end, final Integral evidence$22, final ClassTag evidence$23) {
      return ClassTagIterableFactory.range$(this, start, end, evidence$22, evidence$23);
   }

   public Object range(final Object start, final Object end, final Object step, final Integral evidence$24, final ClassTag evidence$25) {
      return ClassTagIterableFactory.range$(this, start, end, step, evidence$24, evidence$25);
   }

   public Object fill(final int n1, final int n2, final Function0 elem, final ClassTag evidence$26) {
      return ClassTagIterableFactory.fill$(this, n1, n2, elem, evidence$26);
   }

   public Object fill(final int n1, final int n2, final int n3, final Function0 elem, final ClassTag evidence$27) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, elem, evidence$27);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final ClassTag evidence$28) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, n4, elem, evidence$28);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final ClassTag evidence$29) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, n4, n5, elem, evidence$29);
   }

   public Object tabulate(final int n1, final int n2, final Function2 f, final ClassTag evidence$30) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, f, evidence$30);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final Function3 f, final ClassTag evidence$31) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, f, evidence$31);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final ClassTag evidence$32) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, n4, f, evidence$32);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final ClassTag evidence$33) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, n4, n5, f, evidence$33);
   }

   public Object apply(final Seq xs, final Object evidence$7) {
      return EvidenceIterableFactory.apply$(this, xs, evidence$7);
   }

   public Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      return EvidenceIterableFactory.iterate$(this, start, len, f, evidence$10);
   }

   public Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      return EvidenceIterableFactory.unfold$(this, init, f, evidence$11);
   }

   public Factory evidenceIterableFactory(final Object evidence$13) {
      return EvidenceIterableFactory.evidenceIterableFactory$(this, evidence$13);
   }

   public SeqFactory untagged() {
      return untagged;
   }

   private ArraySeq.ofRef emptyImpl$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            emptyImpl = new ArraySeq.ofRef(new Object[0]);
            bitmap$0 = true;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return emptyImpl;
   }

   private ArraySeq.ofRef emptyImpl() {
      return !bitmap$0 ? this.emptyImpl$lzycompute() : emptyImpl;
   }

   public ArraySeq empty(final ClassTag evidence$1) {
      return this.emptyImpl();
   }

   public ArraySeq from(final IterableOnce it, final ClassTag tag) {
      return it instanceof ArraySeq ? (ArraySeq)it : this.unsafeWrapArray(Array$.MODULE$.from(it, tag));
   }

   public Builder newBuilder(final ClassTag evidence$2) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      return (new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      }).mapResult((b) -> MODULE$.unsafeWrapArray(b.toArray(evidence$2)));
   }

   public ArraySeq fill(final int n, final Function0 elem, final ClassTag evidence$3) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int tabulate_max_y = 0;
      int tabulate_ofDim_n1 = Math.max(n, tabulate_max_y);
      Object tabulate_elements = evidence$3.newArray(tabulate_ofDim_n1);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         ScalaRunTime$.MODULE$.array_update(tabulate_elements, tabulate_i, elem.apply());
      }

      return this.unsafeWrapArray(tabulate_elements);
   }

   public ArraySeq tabulate(final int n, final Function1 f, final ClassTag evidence$4) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      int ofDim_n1 = Math.max(n, max_y);
      Object elements = evidence$4.newArray(ofDim_n1);

      for(int i = 0; i < n; ++i) {
         ScalaRunTime$.MODULE$.array_update(elements, i, f.apply(i));
      }

      return this.unsafeWrapArray(elements);
   }

   public ArraySeq unsafeWrapArray(final Object x) {
      if (x == null) {
         return null;
      } else if (x instanceof Object[]) {
         Object[] var2 = x;
         return new ArraySeq.ofRef(var2);
      } else if (x instanceof int[]) {
         int[] var3 = (int[])x;
         return new ArraySeq.ofInt(var3);
      } else if (x instanceof double[]) {
         double[] var4 = (double[])x;
         return new ArraySeq.ofDouble(var4);
      } else if (x instanceof long[]) {
         long[] var5 = (long[])x;
         return new ArraySeq.ofLong(var5);
      } else if (x instanceof float[]) {
         float[] var6 = (float[])x;
         return new ArraySeq.ofFloat(var6);
      } else if (x instanceof char[]) {
         char[] var7 = (char[])x;
         return new ArraySeq.ofChar(var7);
      } else if (x instanceof byte[]) {
         byte[] var8 = (byte[])x;
         return new ArraySeq.ofByte(var8);
      } else if (x instanceof short[]) {
         short[] var9 = (short[])x;
         return new ArraySeq.ofShort(var9);
      } else if (x instanceof boolean[]) {
         boolean[] var10 = (boolean[])x;
         return new ArraySeq.ofBoolean(var10);
      } else if (x instanceof BoxedUnit[]) {
         BoxedUnit[] var11 = (BoxedUnit[])x;
         return new ArraySeq.ofUnit(var11);
      } else {
         throw new MatchError(x);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArraySeq$.class);
   }

   // $FF: synthetic method
   public static final Object $anonfun$fill$1(final Function0 elem$1, final int x$1) {
      return elem$1.apply();
   }

   private ArraySeq$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$fill$1$adapted(final Function0 elem$1, final Object x$1) {
      return $anonfun$fill$1(elem$1, BoxesRunTime.unboxToInt(x$1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
