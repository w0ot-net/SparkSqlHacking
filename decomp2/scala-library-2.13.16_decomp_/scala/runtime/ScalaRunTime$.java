package scala.runtime;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import scala.Array$;
import scala.MatchError;
import scala.Product;
import scala.Product1;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.ArrayOps$;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.SortedOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StringOps;
import scala.collection.StringOps$;
import scala.collection.StringView;
import scala.collection.View;
import scala.collection.generic.IsIterable;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.ArraySeq$;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.Builder;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.package$;
import scala.util.hashing.MurmurHash3$;

public final class ScalaRunTime$ {
   public static final ScalaRunTime$ MODULE$ = new ScalaRunTime$();

   public boolean isArray(final Object x, final int atLevel) {
      return x != null && this.isArrayClass(x.getClass(), atLevel);
   }

   public int isArray$default$2() {
      return 1;
   }

   private boolean isArrayClass(final Class clazz, final int atLevel) {
      while(true) {
         if (clazz.isArray()) {
            if (atLevel != 1) {
               Class var10000 = clazz.getComponentType();
               --atLevel;
               clazz = var10000;
               continue;
            }

            return true;
         }

         return false;
      }
   }

   public Object drop(final Object coll, final int num, final IsIterable iterable) {
      return iterable.apply(coll).drop(num);
   }

   public Class arrayClass(final Class clazz) {
      Class var2 = Void.TYPE;
      if (clazz == null) {
         if (var2 == null) {
            return BoxedUnit[].class;
         }
      } else if (clazz.equals(var2)) {
         return BoxedUnit[].class;
      }

      return Array.newInstance(clazz, 0).getClass();
   }

   public Class anyValClass(final Object value, final ClassTag evidence$1) {
      package$ var10000 = package$.MODULE$;
      return evidence$1.runtimeClass();
   }

   public Object array_apply(final Object xs, final int idx) {
      if (xs instanceof Object[]) {
         return ((Object[])xs)[idx];
      } else if (xs instanceof int[]) {
         return ((int[])xs)[idx];
      } else if (xs instanceof double[]) {
         return ((double[])xs)[idx];
      } else if (xs instanceof long[]) {
         return ((long[])xs)[idx];
      } else if (xs instanceof float[]) {
         return ((float[])xs)[idx];
      } else if (xs instanceof char[]) {
         return ((char[])xs)[idx];
      } else if (xs instanceof byte[]) {
         return ((byte[])xs)[idx];
      } else if (xs instanceof short[]) {
         return ((short[])xs)[idx];
      } else if (xs instanceof boolean[]) {
         return ((boolean[])xs)[idx];
      } else if (xs == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError(xs);
      }
   }

   public void array_update(final Object xs, final int idx, final Object value) {
      if (xs instanceof Object[]) {
         ((Object[])xs)[idx] = value;
      } else if (xs instanceof int[]) {
         ((int[])xs)[idx] = BoxesRunTime.unboxToInt(value);
      } else if (xs instanceof double[]) {
         ((double[])xs)[idx] = BoxesRunTime.unboxToDouble(value);
      } else if (xs instanceof long[]) {
         ((long[])xs)[idx] = BoxesRunTime.unboxToLong(value);
      } else if (xs instanceof float[]) {
         ((float[])xs)[idx] = BoxesRunTime.unboxToFloat(value);
      } else if (xs instanceof char[]) {
         ((char[])xs)[idx] = BoxesRunTime.unboxToChar(value);
      } else if (xs instanceof byte[]) {
         ((byte[])xs)[idx] = BoxesRunTime.unboxToByte(value);
      } else if (xs instanceof short[]) {
         ((short[])xs)[idx] = BoxesRunTime.unboxToShort(value);
      } else if (xs instanceof boolean[]) {
         ((boolean[])xs)[idx] = BoxesRunTime.unboxToBoolean(value);
      } else if (xs == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError(xs);
      }
   }

   public int array_length(final Object xs) {
      return Array.getLength(xs);
   }

   public Object array_clone(final Object xs) {
      if (xs instanceof Object[]) {
         return ((Object[])xs).clone();
      } else if (xs instanceof int[]) {
         return ((int[])xs).clone();
      } else if (xs instanceof double[]) {
         return ((double[])xs).clone();
      } else if (xs instanceof long[]) {
         return ((long[])xs).clone();
      } else if (xs instanceof float[]) {
         return ((float[])xs).clone();
      } else if (xs instanceof char[]) {
         return ((char[])xs).clone();
      } else if (xs instanceof byte[]) {
         return ((byte[])xs).clone();
      } else if (xs instanceof short[]) {
         return ((short[])xs).clone();
      } else if (xs instanceof boolean[]) {
         return ((boolean[])xs).clone();
      } else if (xs == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError(xs);
      }
   }

   public Object[] toObjectArray(final Object src) {
      if (src instanceof Object[]) {
         return src;
      } else if (src instanceof int[]) {
         return copy$mIc$sp$1((int[])src);
      } else if (src instanceof double[]) {
         return copy$mDc$sp$1((double[])src);
      } else if (src instanceof long[]) {
         return copy$mJc$sp$1((long[])src);
      } else if (src instanceof float[]) {
         return copy$mFc$sp$1((float[])src);
      } else if (src instanceof char[]) {
         return copy$mCc$sp$1((char[])src);
      } else if (src instanceof byte[]) {
         return copy$mBc$sp$1((byte[])src);
      } else if (src instanceof short[]) {
         return copy$mSc$sp$1((short[])src);
      } else if (src instanceof boolean[]) {
         return copy$mZc$sp$1((boolean[])src);
      } else if (src == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError(src);
      }
   }

   public Object[] toArray(final Seq xs) {
      if (xs.isEmpty()) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] arr = new Object[xs.length()];
         Iterator it = xs.iterator();

         for(int i = 0; it.hasNext(); ++i) {
            arr[i] = it.next();
         }

         return arr;
      }
   }

   public Method ensureAccessible(final Method m) {
      return (Method)package$.MODULE$.ensureAccessible(m);
   }

   public String _toString(final Product x) {
      return x.productIterator().mkString((new StringBuilder(1)).append(x.productPrefix()).append("(").toString(), ",", ")");
   }

   public int _hashCode(final Product x) {
      return MurmurHash3$.MODULE$.productHash(x);
   }

   public Iterator typedProductIterator(final Product x) {
      return new AbstractIterator(x) {
         private int c;
         private final int cmax;
         private final Product x$2;

         public boolean hasNext() {
            return this.c < this.cmax;
         }

         public Object next() {
            Object result = this.x$2.productElement(this.c);
            ++this.c;
            return result;
         }

         public {
            this.x$2 = x$2;
            this.c = 0;
            this.cmax = x$2.productArity();
         }
      };
   }

   public String stringOf(final Object arg) {
      return this.stringOf(arg, Integer.MAX_VALUE);
   }

   public String stringOf(final Object arg, final int maxElements) {
      try {
         return this.inner$1(arg, maxElements);
      } catch (Throwable var4) {
         if (var4 instanceof UnsupportedOperationException ? true : var4 instanceof AssertionError) {
            return String.valueOf(arg);
         } else {
            throw var4;
         }
      }
   }

   public String replStringOf(final Object arg, final int maxElements) {
      String var3 = this.stringOf(arg, maxElements);
      switch (var3 == null ? 0 : var3.hashCode()) {
         case 0:
            if (var3 == null) {
               return "null toString";
            }
         default:
            return var3.indexOf(10) >= 0 ? (new StringBuilder(2)).append("\n").append(var3).append("\n").toString() : (new StringBuilder(1)).append(var3).append("\n").toString();
      }
   }

   public ArraySeq genericWrapArray(final Object xs) {
      return xs == null ? null : ArraySeq$.MODULE$.unsafeWrapArray(xs);
   }

   public ArraySeq wrapRefArray(final Object[] xs) {
      if (xs == null) {
         return null;
      } else {
         return (ArraySeq)(xs.length == 0 ? ArraySeq$.MODULE$.empty(ClassTag$.MODULE$.AnyRef()) : new ArraySeq.ofRef(xs));
      }
   }

   public ArraySeq wrapIntArray(final int[] xs) {
      return xs != null ? new ArraySeq.ofInt(xs) : null;
   }

   public ArraySeq wrapDoubleArray(final double[] xs) {
      return xs != null ? new ArraySeq.ofDouble(xs) : null;
   }

   public ArraySeq wrapLongArray(final long[] xs) {
      return xs != null ? new ArraySeq.ofLong(xs) : null;
   }

   public ArraySeq wrapFloatArray(final float[] xs) {
      return xs != null ? new ArraySeq.ofFloat(xs) : null;
   }

   public ArraySeq wrapCharArray(final char[] xs) {
      return xs != null ? new ArraySeq.ofChar(xs) : null;
   }

   public ArraySeq wrapByteArray(final byte[] xs) {
      return xs != null ? new ArraySeq.ofByte(xs) : null;
   }

   public ArraySeq wrapShortArray(final short[] xs) {
      return xs != null ? new ArraySeq.ofShort(xs) : null;
   }

   public ArraySeq wrapBooleanArray(final boolean[] xs) {
      return xs != null ? new ArraySeq.ofBoolean(xs) : null;
   }

   public ArraySeq wrapUnitArray(final BoxedUnit[] xs) {
      return xs != null ? new ArraySeq.ofUnit(xs) : null;
   }

   private static final Object[] copy$mZc$sp$1(final boolean[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$mBc$sp$1(final byte[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$mCc$sp$1(final char[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$mDc$sp$1(final double[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$mFc$sp$1(final float[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$mIc$sp$1(final int[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$mJc$sp$1(final long[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$mSc$sp$1(final short[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$mVc$sp$1(final BoxedUnit[] src) {
      int length = src.length;
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = src[i];
         }

         return dest;
      }
   }

   private static final Object[] copy$1(final Object src) {
      int length = Array.getLength(src);
      if (length == 0) {
         return Array$.MODULE$.emptyObjectArray();
      } else {
         Object[] dest = new Object[length];

         for(int i = 0; i < length; ++i) {
            dest[i] = MODULE$.array_apply(src, i);
         }

         return dest;
      }
   }

   private static final String packageOf$1(final Object x) {
      Package var1 = x.getClass().getPackage();
      return var1 == null ? "" : var1.getName();
   }

   private static final boolean isScalaClass$1(final Object x) {
      return packageOf$1(x).startsWith("scala.");
   }

   private static final boolean isScalaCompilerClass$1(final Object x) {
      return packageOf$1(x).startsWith("scala.tools.nsc.");
   }

   private static final boolean isTuple$1(final Object x) {
      return x != null && x.getClass().getName().startsWith("scala.Tuple");
   }

   private static final boolean isSubClassOf$1(final Class potentialSubClass, final String ofClass) {
      try {
         ClassLoader classLoader = potentialSubClass.getClassLoader();
         return Class.forName(ofClass, false, classLoader).isAssignableFrom(potentialSubClass);
      } catch (ClassNotFoundException var3) {
         return false;
      }
   }

   private static final boolean isXmlNode$1(final Class potentialSubClass) {
      return isSubClassOf$1(potentialSubClass, "scala.xml.Node");
   }

   private static final boolean isXmlMetaData$1(final Class potentialSubClass) {
      return isSubClassOf$1(potentialSubClass, "scala.xml.MetaData");
   }

   private static final boolean useOwnToString$1(final Object x) {
      if (x instanceof Range ? true : x instanceof NumericRange) {
         return true;
      } else if (x instanceof SortedOps) {
         return true;
      } else if (x instanceof StringView ? true : (x instanceof StringOps ? true : x instanceof scala.collection.mutable.StringBuilder)) {
         return true;
      } else if (x instanceof View) {
         return true;
      } else if (x instanceof Iterable) {
         Iterable var1 = (Iterable)x;
         return !(var1 instanceof StrictOptimizedIterableOps) || !isScalaClass$1(var1) || isScalaCompilerClass$1(var1) || isSubClassOf$1(var1.getClass(), "scala.xml.Node") || isSubClassOf$1(var1.getClass(), "scala.xml.MetaData");
      } else {
         return false;
      }
   }

   private final String mapInner$1(final Object arg, final int maxElements$1) {
      if (arg instanceof Tuple2) {
         Tuple2 var3 = (Tuple2)arg;
         Object k = var3._1();
         Object v = var3._2();
         return (new StringBuilder(4)).append(this.inner$1(k, maxElements$1)).append(" -> ").append(this.inner$1(v, maxElements$1)).toString();
      } else {
         return this.inner$1(arg, maxElements$1);
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$stringOf$1(final int x$1) {
      return "()";
   }

   private final String arrayToString$1(final Object x, final int maxElements$1) {
      Class var10000 = x.getClass().getComponentType();
      Class var3 = BoxedUnit.class;
      if (var10000 != null) {
         if (var10000.equals(var3)) {
            RichInt$ var15 = RichInt$.MODULE$;
            byte var4 = 0;
            scala.math.package$ var16 = scala.math.package$.MODULE$;
            int until$extension_end = Math.min(Array.getLength(x), maxElements$1);
            Range$ var17 = Range$.MODULE$;
            Range map_this = new Range.Exclusive(var4, until$extension_end, 1);
            map_this.scala$collection$immutable$Range$$validateMaxLength();
            Builder strictOptimizedMap_b = IndexedSeq$.MODULE$.newBuilder();

            Object var13;
            for(Iterator strictOptimizedMap_it = map_this.iterator(); strictOptimizedMap_it.hasNext(); var13 = null) {
               Integer var10001 = (Integer)strictOptimizedMap_it.next();
               String strictOptimizedMap_$plus$eq_elem = "()";
               if (strictOptimizedMap_b == null) {
                  throw null;
               }

               strictOptimizedMap_b.addOne(strictOptimizedMap_$plus$eq_elem);
            }

            var17 = (Range$)strictOptimizedMap_b.result();
            Object var12 = null;
            var13 = null;
            Object var11 = null;
            IndexedSeq var19 = (IndexedSeq)var17;
            map_this = null;
            return var19.mkString("Array(", ", ", ")");
         }
      }

      return ArrayOps$.MODULE$.iterator$extension(x).take(maxElements$1).map((arg) -> this.inner$1(arg, maxElements$1)).mkString("Array(", ", ", ")");
   }

   private final String inner$1(final Object arg, final int maxElements$1) {
      if (arg == null) {
         return "null";
      } else if ("".equals(arg)) {
         return "\"\"";
      } else if (arg instanceof String) {
         String var3 = (String)arg;
         RichChar$ var10000 = RichChar$.MODULE$;
         if (!Character.isWhitespace(StringOps$.MODULE$.head$extension(var3))) {
            var10000 = RichChar$.MODULE$;
            if (!Character.isWhitespace(StringOps$.MODULE$.last$extension(var3))) {
               return var3;
            }
         }

         return (new StringBuilder(2)).append("\"").append(var3).append("\"").toString();
      } else if (useOwnToString$1(arg)) {
         return arg.toString();
      } else if (this.isArray(arg, 1)) {
         return this.arrayToString$1(arg, maxElements$1);
      } else if (arg instanceof Map) {
         Map var4 = (Map)arg;
         return var4.iterator().take(maxElements$1).map((argx) -> this.mapInner$1(argx, maxElements$1)).mkString((new StringBuilder(1)).append(var4.className()).append("(").toString(), ", ", ")");
      } else if (arg instanceof Iterable) {
         Iterable var5 = (Iterable)arg;
         return var5.iterator().take(maxElements$1).map((argx) -> this.inner$1(argx, maxElements$1)).mkString((new StringBuilder(1)).append(var5.className()).append("(").toString(), ", ", ")");
      } else {
         if (arg instanceof Product1) {
            Product1 var6 = (Product1)arg;
            if (isTuple$1(var6)) {
               return (new StringBuilder(3)).append("(").append(this.inner$1(var6._1(), maxElements$1)).append(",)").toString();
            }
         }

         if (arg instanceof Product) {
            Product var7 = (Product)arg;
            if (isTuple$1(var7)) {
               return var7.productIterator().map((argx) -> this.inner$1(argx, maxElements$1)).mkString("(", ",", ")");
            }
         }

         return arg.toString();
      }
   }

   private ScalaRunTime$() {
   }

   // $FF: synthetic method
   public static final String $anonfun$stringOf$1$adapted(final Object x$1) {
      return $anonfun$stringOf$1(BoxesRunTime.unboxToInt(x$1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
