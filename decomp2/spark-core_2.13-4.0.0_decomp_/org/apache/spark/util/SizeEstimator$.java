package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import javax.management.MBeanServer;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.util.collection.OpenHashSet;
import org.apache.spark.util.collection.OpenHashSet$mcI$sp;
import org.slf4j.Logger;
import org.sparkproject.guava.collect.MapMaker;
import scala.Function0;
import scala.StringContext;
import scala.collection.immutable.List;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;

@DeveloperApi
public final class SizeEstimator$ implements Logging {
   public static final SizeEstimator$ MODULE$ = new SizeEstimator$();
   private static final int BYTE_SIZE;
   private static final int BOOLEAN_SIZE;
   private static final int CHAR_SIZE;
   private static final int SHORT_SIZE;
   private static final int INT_SIZE;
   private static final int LONG_SIZE;
   private static final int FLOAT_SIZE;
   private static final int DOUBLE_SIZE;
   private static final List fieldSizes;
   private static final int ALIGN_SIZE;
   private static final ConcurrentMap classInfos;
   private static boolean is64bit;
   private static boolean isCompressedOops;
   private static int pointerSize;
   private static int objectSize;
   private static final int ARRAY_SIZE_FOR_SAMPLING;
   private static final int ARRAY_SAMPLE_SIZE;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      BYTE_SIZE = 1;
      BOOLEAN_SIZE = 1;
      CHAR_SIZE = 2;
      SHORT_SIZE = 2;
      INT_SIZE = 4;
      LONG_SIZE = 8;
      FLOAT_SIZE = 4;
      DOUBLE_SIZE = 8;
      fieldSizes = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{8, 4, 2, 1}));
      ALIGN_SIZE = 8;
      classInfos = (new MapMaker()).weakKeys().makeMap();
      is64bit = false;
      isCompressedOops = false;
      pointerSize = 4;
      objectSize = 8;
      MODULE$.initialize();
      ARRAY_SIZE_FOR_SAMPLING = 400;
      ARRAY_SAMPLE_SIZE = 100;
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public long estimate(final Object obj) {
      return this.estimate(obj, new IdentityHashMap());
   }

   private int BYTE_SIZE() {
      return BYTE_SIZE;
   }

   private int BOOLEAN_SIZE() {
      return BOOLEAN_SIZE;
   }

   private int CHAR_SIZE() {
      return CHAR_SIZE;
   }

   private int SHORT_SIZE() {
      return SHORT_SIZE;
   }

   private int INT_SIZE() {
      return INT_SIZE;
   }

   private int LONG_SIZE() {
      return LONG_SIZE;
   }

   private int FLOAT_SIZE() {
      return FLOAT_SIZE;
   }

   private int DOUBLE_SIZE() {
      return DOUBLE_SIZE;
   }

   private List fieldSizes() {
      return fieldSizes;
   }

   private int ALIGN_SIZE() {
      return ALIGN_SIZE;
   }

   private ConcurrentMap classInfos() {
      return classInfos;
   }

   private boolean is64bit() {
      return is64bit;
   }

   private void is64bit_$eq(final boolean x$1) {
      is64bit = x$1;
   }

   private boolean isCompressedOops() {
      return isCompressedOops;
   }

   private void isCompressedOops_$eq(final boolean x$1) {
      isCompressedOops = x$1;
   }

   private int pointerSize() {
      return pointerSize;
   }

   private void pointerSize_$eq(final int x$1) {
      pointerSize = x$1;
   }

   private int objectSize() {
      return objectSize;
   }

   private void objectSize_$eq(final int x$1) {
      objectSize = x$1;
   }

   private void initialize() {
      String arch = System.getProperty("os.arch");
      this.is64bit_$eq(arch.contains("64") || arch.contains("s390x"));
      this.isCompressedOops_$eq(this.getIsCompressedOops());
      this.objectSize_$eq(!this.is64bit() ? 8 : (!this.isCompressedOops() ? 16 : 12));
      this.pointerSize_$eq(this.is64bit() && !this.isCompressedOops() ? 8 : 4);
      this.classInfos().clear();
      this.classInfos().put(Object.class, new SizeEstimator.ClassInfo((long)this.objectSize(), scala.collection.immutable.Nil..MODULE$));
   }

   private boolean getIsCompressedOops() {
      if (System.getProperty(Tests$.MODULE$.TEST_USE_COMPRESSED_OOPS_KEY()) != null) {
         return scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(System.getProperty(Tests$.MODULE$.TEST_USE_COMPRESSED_OOPS_KEY())));
      } else {
         String javaVendor = System.getProperty("java.vendor");
         if (!javaVendor.contains("IBM") && !javaVendor.contains("OpenJ9")) {
            boolean var10000;
            try {
               String hotSpotMBeanName = "com.sun.management:type=HotSpotDiagnostic";
               MBeanServer server = ManagementFactory.getPlatformMBeanServer();
               Class hotSpotMBeanClass = Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
               Method getVMMethod = hotSpotMBeanClass.getDeclaredMethod("getVMOption", Class.forName("java.lang.String"));
               Object bean = ManagementFactory.newPlatformMXBeanProxy(server, hotSpotMBeanName, hotSpotMBeanClass);
               var10000 = getVMMethod.invoke(bean, "UseCompressedOops").toString().contains("true");
            } catch (Exception var8) {
               boolean guess = Runtime.getRuntime().maxMemory() < 34359738368L;
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to check whether UseCompressedOops is set; "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"assuming "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(guess ? MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"yes"})))).log(scala.collection.immutable.Nil..MODULE$) : MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"not"})))).log(scala.collection.immutable.Nil..MODULE$))));
               var10000 = guess;
            }

            return var10000;
         } else {
            return System.getProperty("java.vm.info").contains("Compressed Ref");
         }
      }
   }

   private long estimate(final Object obj, final IdentityHashMap visited) {
      SizeEstimator.SearchState state = new SizeEstimator.SearchState(visited);
      state.enqueue(obj);

      while(!state.isFinished()) {
         this.visitSingleObject(state.dequeue(), state);
      }

      return state.size();
   }

   private void visitSingleObject(final Object obj, final SizeEstimator.SearchState state) {
      Class cls = obj.getClass();
      if (cls.isArray()) {
         this.visitArray(obj, cls, state);
      } else if (!cls.getName().startsWith("scala.reflect")) {
         if (!(obj instanceof ClassLoader) && !(obj instanceof Class)) {
            if (obj instanceof KnownSizeEstimation) {
               KnownSizeEstimation var6 = (KnownSizeEstimation)obj;
               state.size_$eq(state.size() + var6.estimatedSize());
               BoxedUnit var8 = BoxedUnit.UNIT;
            } else {
               SizeEstimator.ClassInfo classInfo = this.getClassInfo(cls);
               state.size_$eq(state.size() + this.alignSize(classInfo.shellSize()));
               classInfo.pointerFields().foreach((field) -> {
                  $anonfun$visitSingleObject$1(state, obj, field);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }
      }
   }

   private int ARRAY_SIZE_FOR_SAMPLING() {
      return ARRAY_SIZE_FOR_SAMPLING;
   }

   private int ARRAY_SAMPLE_SIZE() {
      return ARRAY_SAMPLE_SIZE;
   }

   private void visitArray(final Object array, final Class arrayClass, final SizeEstimator.SearchState state) {
      int length = scala.runtime.ScalaRunTime..MODULE$.array_length(array);
      Class elementClass = arrayClass.getComponentType();
      long arrSize = this.alignSize((long)(this.objectSize() + this.INT_SIZE()));
      if (elementClass.isPrimitive()) {
         arrSize += this.alignSize((long)length * (long)this.primitiveSize(elementClass));
         state.size_$eq(state.size() + arrSize);
      } else {
         arrSize += this.alignSize((long)length * (long)this.pointerSize());
         state.size_$eq(state.size() + arrSize);
         if (length > this.ARRAY_SIZE_FOR_SAMPLING()) {
            Random rand = new Random(42L);
            OpenHashSet drawn = new OpenHashSet$mcI$sp(2 * this.ARRAY_SAMPLE_SIZE(), scala.reflect.ClassTag..MODULE$.Int());
            long s1 = this.sampleArray(array, state, rand, drawn, length);
            long s2 = this.sampleArray(array, state, rand, drawn, length);
            long size = scala.math.package..MODULE$.min(s1, s2);
            state.size_$eq(state.size() + scala.math.package..MODULE$.max(s1, s2) + size * (long)((length - this.ARRAY_SAMPLE_SIZE()) / this.ARRAY_SAMPLE_SIZE()));
         } else {
            for(int arrayIndex = 0; arrayIndex < length; ++arrayIndex) {
               state.enqueue(scala.runtime.ScalaRunTime..MODULE$.array_apply(array, arrayIndex));
            }

         }
      }
   }

   private long sampleArray(final Object array, final SizeEstimator.SearchState state, final Random rand, final OpenHashSet drawn, final int length) {
      LongRef size = LongRef.create(0L);
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.ARRAY_SAMPLE_SIZE()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         int index = 0;

         do {
            index = rand.nextInt(length);
         } while(drawn.contains$mcI$sp(index));

         drawn.add$mcI$sp(index);
         Object obj = scala.runtime.ScalaRunTime..MODULE$.array_apply(array, index);
         if (obj != null) {
            size.elem += MODULE$.estimate(obj, state.visited());
         }
      });
      return size.elem;
   }

   private int primitiveSize(final Class cls) {
      Class var2 = Byte.TYPE;
      if (cls == null) {
         if (var2 == null) {
            return this.BYTE_SIZE();
         }
      } else if (cls.equals(var2)) {
         return this.BYTE_SIZE();
      }

      Class var3 = Boolean.TYPE;
      if (cls == null) {
         if (var3 == null) {
            return this.BOOLEAN_SIZE();
         }
      } else if (cls.equals(var3)) {
         return this.BOOLEAN_SIZE();
      }

      Class var4 = Character.TYPE;
      if (cls == null) {
         if (var4 == null) {
            return this.CHAR_SIZE();
         }
      } else if (cls.equals(var4)) {
         return this.CHAR_SIZE();
      }

      Class var5 = Short.TYPE;
      if (cls == null) {
         if (var5 == null) {
            return this.SHORT_SIZE();
         }
      } else if (cls.equals(var5)) {
         return this.SHORT_SIZE();
      }

      Class var6 = Integer.TYPE;
      if (cls == null) {
         if (var6 == null) {
            return this.INT_SIZE();
         }
      } else if (cls.equals(var6)) {
         return this.INT_SIZE();
      }

      Class var7 = Long.TYPE;
      if (cls == null) {
         if (var7 == null) {
            return this.LONG_SIZE();
         }
      } else if (cls.equals(var7)) {
         return this.LONG_SIZE();
      }

      Class var8 = Float.TYPE;
      if (cls == null) {
         if (var8 == null) {
            return this.FLOAT_SIZE();
         }
      } else if (cls.equals(var8)) {
         return this.FLOAT_SIZE();
      }

      Class var9 = Double.TYPE;
      if (cls == null) {
         if (var9 == null) {
            return this.DOUBLE_SIZE();
         }
      } else if (cls.equals(var9)) {
         return this.DOUBLE_SIZE();
      }

      throw new IllegalArgumentException("Non-primitive class " + cls + " passed to primitiveSize()");
   }

   private SizeEstimator.ClassInfo getClassInfo(final Class cls) {
      SizeEstimator.ClassInfo info = (SizeEstimator.ClassInfo)this.classInfos().get(cls);
      if (info != null) {
         return info;
      } else {
         SizeEstimator.ClassInfo parent = this.getClassInfo(cls.getSuperclass());
         LongRef shellSize = LongRef.create(parent.shellSize());
         ObjectRef pointerFields = ObjectRef.create(parent.pointerFields());
         int[] sizeCount = (int[])scala.Array..MODULE$.ofDim(BoxesRunTime.unboxToInt(this.fieldSizes().max(scala.math.Ordering.Int..MODULE$)) + 1, scala.reflect.ClassTag..MODULE$.Int());
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])cls.getDeclaredFields()), (field) -> {
            $anonfun$getClassInfo$1(sizeCount, pointerFields, field);
            return BoxedUnit.UNIT;
         });
         LongRef alignedSize = LongRef.create(shellSize.elem);
         this.fieldSizes().withFilter((JFunction1.mcZI.sp)(size) -> sizeCount[size] > 0).foreach((JFunction1.mcVI.sp)(size) -> {
            long count = (long)sizeCount[size];
            alignedSize.elem = scala.math.package..MODULE$.max(alignedSize.elem, MODULE$.alignSizeUp(shellSize.elem, size) + (long)size * count);
            shellSize.elem += (long)size * count;
         });
         shellSize.elem = this.alignSizeUp(alignedSize.elem, this.pointerSize());
         SizeEstimator.ClassInfo newInfo = new SizeEstimator.ClassInfo(shellSize.elem, (List)pointerFields.elem);
         this.classInfos().put(cls, newInfo);
         return newInfo;
      }
   }

   private long alignSize(final long size) {
      return this.alignSizeUp(size, this.ALIGN_SIZE());
   }

   private long alignSizeUp(final long size, final int alignSize) {
      return size + (long)alignSize - 1L & (long)(~(alignSize - 1));
   }

   // $FF: synthetic method
   public static final void $anonfun$visitSingleObject$1(final SizeEstimator.SearchState state$1, final Object obj$1, final Field field) {
      state$1.enqueue(field.get(obj$1));
   }

   // $FF: synthetic method
   public static final void $anonfun$getClassInfo$1(final int[] sizeCount$1, final ObjectRef pointerFields$1, final Field field) {
      if (!Modifier.isStatic(field.getModifiers())) {
         Class fieldClass = field.getType();
         if (fieldClass.isPrimitive()) {
            int var4 = MODULE$.primitiveSize(fieldClass);
            int var8 = sizeCount$1[var4]++;
         } else {
            try {
               if (field.trySetAccessible()) {
                  pointerFields$1.elem = ((List)pointerFields$1.elem).$colon$colon(field);
               }
            } catch (SecurityException var7) {
            }

            int var6 = MODULE$.pointerSize();
            int var10002 = sizeCount$1[var6]++;
         }
      }
   }

   private SizeEstimator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
