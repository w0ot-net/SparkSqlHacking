package io.netty.util;

import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class ResourceLeakDetector {
   private static final String PROP_LEVEL_OLD = "io.netty.leakDetectionLevel";
   private static final String PROP_LEVEL = "io.netty.leakDetection.level";
   private static final Level DEFAULT_LEVEL;
   private static final String PROP_TARGET_RECORDS = "io.netty.leakDetection.targetRecords";
   private static final int DEFAULT_TARGET_RECORDS = 4;
   private static final String PROP_SAMPLING_INTERVAL = "io.netty.leakDetection.samplingInterval";
   private static final int DEFAULT_SAMPLING_INTERVAL = 128;
   private static final int TARGET_RECORDS;
   static final int SAMPLING_INTERVAL;
   private static Level level;
   private static final InternalLogger logger;
   private final Set allLeaks;
   private final ReferenceQueue refQueue;
   private final Set reportedLeaks;
   private final String resourceType;
   private final int samplingInterval;
   private volatile LeakListener leakListener;
   private static final AtomicReference excludedMethods;

   /** @deprecated */
   @Deprecated
   public static void setEnabled(boolean enabled) {
      setLevel(enabled ? ResourceLeakDetector.Level.SIMPLE : ResourceLeakDetector.Level.DISABLED);
   }

   public static boolean isEnabled() {
      return getLevel().ordinal() > ResourceLeakDetector.Level.DISABLED.ordinal();
   }

   public static void setLevel(Level level) {
      ResourceLeakDetector.level = (Level)ObjectUtil.checkNotNull(level, "level");
   }

   public static Level getLevel() {
      return level;
   }

   /** @deprecated */
   @Deprecated
   public ResourceLeakDetector(Class resourceType) {
      this(StringUtil.simpleClassName(resourceType));
   }

   /** @deprecated */
   @Deprecated
   public ResourceLeakDetector(String resourceType) {
      this((String)resourceType, 128, Long.MAX_VALUE);
   }

   /** @deprecated */
   @Deprecated
   public ResourceLeakDetector(Class resourceType, int samplingInterval, long maxActive) {
      this(resourceType, samplingInterval);
   }

   public ResourceLeakDetector(Class resourceType, int samplingInterval) {
      this(StringUtil.simpleClassName(resourceType), samplingInterval, Long.MAX_VALUE);
   }

   /** @deprecated */
   @Deprecated
   public ResourceLeakDetector(String resourceType, int samplingInterval, long maxActive) {
      this.allLeaks = Collections.newSetFromMap(new ConcurrentHashMap());
      this.refQueue = new ReferenceQueue();
      this.reportedLeaks = Collections.newSetFromMap(new ConcurrentHashMap());
      this.resourceType = (String)ObjectUtil.checkNotNull(resourceType, "resourceType");
      this.samplingInterval = samplingInterval;
   }

   /** @deprecated */
   @Deprecated
   public final ResourceLeak open(Object obj) {
      return this.track0(obj, false);
   }

   public final ResourceLeakTracker track(Object obj) {
      return this.track0(obj, false);
   }

   public ResourceLeakTracker trackForcibly(Object obj) {
      return this.track0(obj, true);
   }

   private DefaultResourceLeak track0(Object obj, boolean force) {
      Level level = ResourceLeakDetector.level;
      if (!force && level != ResourceLeakDetector.Level.PARANOID && (level == ResourceLeakDetector.Level.DISABLED || PlatformDependent.threadLocalRandom().nextInt(this.samplingInterval) != 0)) {
         return null;
      } else {
         this.reportLeak();
         return new DefaultResourceLeak(obj, this.refQueue, this.allLeaks, this.getInitialHint(this.resourceType));
      }
   }

   private void clearRefQueue() {
      while(true) {
         DefaultResourceLeak ref = (DefaultResourceLeak)this.refQueue.poll();
         if (ref == null) {
            return;
         }

         ref.dispose();
      }
   }

   protected boolean needReport() {
      return logger.isErrorEnabled();
   }

   private void reportLeak() {
      if (!this.needReport()) {
         this.clearRefQueue();
      } else {
         while(true) {
            DefaultResourceLeak ref = (DefaultResourceLeak)this.refQueue.poll();
            if (ref == null) {
               return;
            }

            if (ref.dispose()) {
               String records = ref.getReportAndClearRecords();
               if (this.reportedLeaks.add(records)) {
                  if (records.isEmpty()) {
                     this.reportUntracedLeak(this.resourceType);
                  } else {
                     this.reportTracedLeak(this.resourceType, records);
                  }

                  LeakListener listener = this.leakListener;
                  if (listener != null) {
                     listener.onLeak(this.resourceType, records);
                  }
               }
            }
         }
      }
   }

   protected void reportTracedLeak(String resourceType, String records) {
      logger.error("LEAK: {}.release() was not called before it's garbage-collected. See https://netty.io/wiki/reference-counted-objects.html for more information.{}", resourceType, records);
   }

   protected void reportUntracedLeak(String resourceType) {
      logger.error("LEAK: {}.release() was not called before it's garbage-collected. Enable advanced leak reporting to find out where the leak occurred. To enable advanced leak reporting, specify the JVM option '-D{}={}' or call {}.setLevel() See https://netty.io/wiki/reference-counted-objects.html for more information.", resourceType, "io.netty.leakDetection.level", ResourceLeakDetector.Level.ADVANCED.name().toLowerCase(), StringUtil.simpleClassName((Object)this));
   }

   /** @deprecated */
   @Deprecated
   protected void reportInstancesLeak(String resourceType) {
   }

   protected Object getInitialHint(String resourceType) {
      return null;
   }

   public void setLeakListener(LeakListener leakListener) {
      this.leakListener = leakListener;
   }

   public static void addExclusions(Class clz, String... methodNames) {
      Set<String> nameSet = new HashSet(Arrays.asList(methodNames));

      for(Method method : clz.getDeclaredMethods()) {
         if (nameSet.remove(method.getName()) && nameSet.isEmpty()) {
            break;
         }
      }

      if (!nameSet.isEmpty()) {
         throw new IllegalArgumentException("Can't find '" + nameSet + "' in " + clz.getName());
      } else {
         String[] oldMethods;
         String[] newMethods;
         do {
            oldMethods = (String[])excludedMethods.get();
            newMethods = (String[])Arrays.copyOf(oldMethods, oldMethods.length + 2 * methodNames.length);

            for(int i = 0; i < methodNames.length; ++i) {
               newMethods[oldMethods.length + i * 2] = clz.getName();
               newMethods[oldMethods.length + i * 2 + 1] = methodNames[i];
            }
         } while(!excludedMethods.compareAndSet(oldMethods, newMethods));

      }
   }

   static {
      DEFAULT_LEVEL = ResourceLeakDetector.Level.SIMPLE;
      logger = InternalLoggerFactory.getInstance(ResourceLeakDetector.class);
      boolean disabled;
      if (SystemPropertyUtil.get("io.netty.noResourceLeakDetection") != null) {
         disabled = SystemPropertyUtil.getBoolean("io.netty.noResourceLeakDetection", false);
         logger.debug("-Dio.netty.noResourceLeakDetection: {}", (Object)disabled);
         logger.warn("-Dio.netty.noResourceLeakDetection is deprecated. Use '-D{}={}' instead.", "io.netty.leakDetection.level", ResourceLeakDetector.Level.DISABLED.name().toLowerCase());
      } else {
         disabled = false;
      }

      Level defaultLevel = disabled ? ResourceLeakDetector.Level.DISABLED : DEFAULT_LEVEL;
      String levelStr = SystemPropertyUtil.get("io.netty.leakDetectionLevel", defaultLevel.name());
      levelStr = SystemPropertyUtil.get("io.netty.leakDetection.level", levelStr);
      Level level = ResourceLeakDetector.Level.parseLevel(levelStr);
      TARGET_RECORDS = SystemPropertyUtil.getInt("io.netty.leakDetection.targetRecords", 4);
      SAMPLING_INTERVAL = SystemPropertyUtil.getInt("io.netty.leakDetection.samplingInterval", 128);
      ResourceLeakDetector.level = level;
      if (logger.isDebugEnabled()) {
         logger.debug("-D{}: {}", "io.netty.leakDetection.level", level.name().toLowerCase());
         logger.debug("-D{}: {}", "io.netty.leakDetection.targetRecords", TARGET_RECORDS);
      }

      excludedMethods = new AtomicReference(EmptyArrays.EMPTY_STRINGS);
   }

   public static enum Level {
      DISABLED,
      SIMPLE,
      ADVANCED,
      PARANOID;

      static Level parseLevel(String levelStr) {
         String trimmedLevelStr = levelStr.trim();

         for(Level l : values()) {
            if (trimmedLevelStr.equalsIgnoreCase(l.name()) || trimmedLevelStr.equals(String.valueOf(l.ordinal()))) {
               return l;
            }
         }

         return ResourceLeakDetector.DEFAULT_LEVEL;
      }
   }

   private static final class DefaultResourceLeak extends WeakReference implements ResourceLeakTracker, ResourceLeak {
      private static final AtomicReferenceFieldUpdater headUpdater = AtomicReferenceFieldUpdater.newUpdater(DefaultResourceLeak.class, TraceRecord.class, "head");
      private static final AtomicIntegerFieldUpdater droppedRecordsUpdater = AtomicIntegerFieldUpdater.newUpdater(DefaultResourceLeak.class, "droppedRecords");
      private volatile TraceRecord head;
      private volatile int droppedRecords;
      private final Set allLeaks;
      private final int trackedHash;

      DefaultResourceLeak(Object referent, ReferenceQueue refQueue, Set allLeaks, Object initialHint) {
         super(referent, refQueue);

         assert referent != null;

         this.allLeaks = allLeaks;
         this.trackedHash = System.identityHashCode(referent);
         allLeaks.add(this);
         headUpdater.set(this, initialHint == null ? new TraceRecord(ResourceLeakDetector.TraceRecord.BOTTOM) : new TraceRecord(ResourceLeakDetector.TraceRecord.BOTTOM, initialHint));
      }

      public void record() {
         this.record0((Object)null);
      }

      public void record(Object hint) {
         this.record0(hint);
      }

      private void record0(Object hint) {
         if (ResourceLeakDetector.TARGET_RECORDS > 0) {
            TraceRecord oldHead;
            TraceRecord newHead;
            boolean dropped;
            do {
               TraceRecord prevHead;
               if ((prevHead = oldHead = (TraceRecord)headUpdater.get(this)) == null) {
                  return;
               }

               int numElements = oldHead.pos + 1;
               if (numElements >= ResourceLeakDetector.TARGET_RECORDS) {
                  int backOffFactor = Math.min(numElements - ResourceLeakDetector.TARGET_RECORDS, 30);
                  if (dropped = PlatformDependent.threadLocalRandom().nextInt(1 << backOffFactor) != 0) {
                     prevHead = oldHead.next;
                  }
               } else {
                  dropped = false;
               }

               newHead = hint != null ? new TraceRecord(prevHead, hint) : new TraceRecord(prevHead);
            } while(!headUpdater.compareAndSet(this, oldHead, newHead));

            if (dropped) {
               droppedRecordsUpdater.incrementAndGet(this);
            }
         }

      }

      boolean dispose() {
         this.clear();
         return this.allLeaks.remove(this);
      }

      public boolean close() {
         if (this.allLeaks.remove(this)) {
            this.clear();
            headUpdater.set(this, (Object)null);
            return true;
         } else {
            return false;
         }
      }

      public boolean close(Object trackedObject) {
         assert this.trackedHash == System.identityHashCode(trackedObject);

         boolean var2;
         try {
            var2 = this.close();
         } finally {
            reachabilityFence0(trackedObject);
         }

         return var2;
      }

      private static void reachabilityFence0(Object ref) {
         if (ref != null) {
            synchronized(ref) {
               ;
            }
         }

      }

      public String toString() {
         TraceRecord oldHead = (TraceRecord)headUpdater.get(this);
         return this.generateReport(oldHead);
      }

      String getReportAndClearRecords() {
         TraceRecord oldHead = (TraceRecord)headUpdater.getAndSet(this, (Object)null);
         return this.generateReport(oldHead);
      }

      private String generateReport(TraceRecord oldHead) {
         if (oldHead == null) {
            return "";
         } else {
            int dropped = droppedRecordsUpdater.get(this);
            int duped = 0;
            int present = oldHead.pos + 1;
            StringBuilder buf = (new StringBuilder(present * 2048)).append(StringUtil.NEWLINE);
            buf.append("Recent access records: ").append(StringUtil.NEWLINE);
            int i = 1;

            for(Set<String> seen = new HashSet(present); oldHead != ResourceLeakDetector.TraceRecord.BOTTOM; oldHead = oldHead.next) {
               String s = oldHead.toString();
               if (seen.add(s)) {
                  if (oldHead.next == ResourceLeakDetector.TraceRecord.BOTTOM) {
                     buf.append("Created at:").append(StringUtil.NEWLINE).append(s);
                  } else {
                     buf.append('#').append(i++).append(':').append(StringUtil.NEWLINE).append(s);
                  }
               } else {
                  ++duped;
               }
            }

            if (duped > 0) {
               buf.append(": ").append(duped).append(" leak records were discarded because they were duplicates").append(StringUtil.NEWLINE);
            }

            if (dropped > 0) {
               buf.append(": ").append(dropped).append(" leak records were discarded because the leak record count is targeted to ").append(ResourceLeakDetector.TARGET_RECORDS).append(". Use system property ").append("io.netty.leakDetection.targetRecords").append(" to increase the limit.").append(StringUtil.NEWLINE);
            }

            buf.setLength(buf.length() - StringUtil.NEWLINE.length());
            return buf.toString();
         }
      }
   }

   private static class TraceRecord extends Throwable {
      private static final long serialVersionUID = 6065153674892850720L;
      private static final TraceRecord BOTTOM = new TraceRecord() {
         private static final long serialVersionUID = 7396077602074694571L;

         public Throwable fillInStackTrace() {
            return this;
         }
      };
      private final String hintString;
      private final TraceRecord next;
      private final int pos;

      TraceRecord(TraceRecord next, Object hint) {
         this.hintString = hint instanceof ResourceLeakHint ? ((ResourceLeakHint)hint).toHintString() : hint.toString();
         this.next = next;
         this.pos = next.pos + 1;
      }

      TraceRecord(TraceRecord next) {
         this.hintString = null;
         this.next = next;
         this.pos = next.pos + 1;
      }

      private TraceRecord() {
         this.hintString = null;
         this.next = null;
         this.pos = -1;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(2048);
         if (this.hintString != null) {
            buf.append("\tHint: ").append(this.hintString).append(StringUtil.NEWLINE);
         }

         StackTraceElement[] array = this.getStackTrace();

         label30:
         for(int i = 3; i < array.length; ++i) {
            StackTraceElement element = array[i];
            String[] exclusions = (String[])ResourceLeakDetector.excludedMethods.get();

            for(int k = 0; k < exclusions.length; k += 2) {
               if (exclusions[k].equals(element.getClassName()) && exclusions[k + 1].equals(element.getMethodName())) {
                  continue label30;
               }
            }

            buf.append('\t');
            buf.append(element.toString());
            buf.append(StringUtil.NEWLINE);
         }

         return buf.toString();
      }
   }

   public interface LeakListener {
      void onLeak(String var1, String var2);
   }
}
