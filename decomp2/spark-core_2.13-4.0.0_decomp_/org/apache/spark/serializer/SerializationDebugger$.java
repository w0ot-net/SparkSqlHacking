package org.apache.spark.serializer;

import java.io.NotSerializableException;
import java.io.ObjectStreamClass;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.SparkClassUtils.;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.runtime.BoxesRunTime;

public final class SerializationDebugger$ implements Logging {
   public static final SerializationDebugger$ MODULE$ = new SerializationDebugger$();
   private static boolean enableDebugging;
   private static final SerializationDebugger.ObjectStreamClassReflection org$apache$spark$serializer$SerializationDebugger$$reflect;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      MethodHandles.Lookup lookup = MethodHandles.lookup();
      Class clazz = .MODULE$.classForName("sun.security.action.GetBooleanAction", .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3());
      Constructor constructor = clazz.getConstructor(String.class);
      MethodHandle mh = lookup.unreflectConstructor(constructor);
      PrivilegedAction action = (PrivilegedAction)mh.invoke("sun.io.serialization.extendedDebugInfo");
      enableDebugging = !scala.Predef..MODULE$.boolean2Boolean(BoxesRunTime.unboxToBoolean(AccessController.doPrivileged(action)));
      org$apache$spark$serializer$SerializationDebugger$$reflect = MODULE$.liftedTree1$1();
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

   public NotSerializableException improveException(final Object obj, final NotSerializableException e) {
      if (this.enableDebugging() && this.org$apache$spark$serializer$SerializationDebugger$$reflect() != null) {
         NotSerializableException var10000;
         try {
            String var10002 = e.getMessage();
            var10000 = new NotSerializableException(var10002 + "\nSerialization stack:\n" + this.find(obj).map((x$1) -> "\t- " + x$1).mkString("\n"));
         } catch (Throwable var7) {
            if (var7 == null || !scala.util.control.NonFatal..MODULE$.apply(var7)) {
               throw var7;
            }

            this.logWarning((Function0)(() -> "Exception in serialization debugger"), var7);
            var10000 = e;
         }

         return var10000;
      } else {
         return e;
      }
   }

   public List find(final Object obj) {
      return (new SerializationDebugger.SerializationDebugger()).visit(obj, scala.package..MODULE$.List().empty());
   }

   public boolean enableDebugging() {
      return enableDebugging;
   }

   public void enableDebugging_$eq(final boolean x$1) {
      enableDebugging = x$1;
   }

   public Tuple2 org$apache$spark$serializer$SerializationDebugger$$findObjectAndDescriptor(final Object o) {
      while(true) {
         Class cl = o.getClass();
         ObjectStreamClass desc = ObjectStreamClass.lookupAny(cl);
         if (!SerializationDebugger.ObjectStreamClassMethods$.MODULE$.hasWriteReplaceMethod$extension(this.ObjectStreamClassMethods(desc))) {
            return new Tuple2(o, desc);
         }

         Object replaced = SerializationDebugger.ObjectStreamClassMethods$.MODULE$.invokeWriteReplace$extension(this.ObjectStreamClassMethods(desc), o);
         Class var10000 = replaced.getClass();
         Class var6 = o.getClass();
         if (var10000 == null) {
            if (var6 == null) {
               return new Tuple2(replaced, desc);
            }
         } else if (var10000.equals(var6)) {
            return new Tuple2(replaced, desc);
         }

         o = replaced;
      }
   }

   public ObjectStreamClass ObjectStreamClassMethods(final ObjectStreamClass desc) {
      return desc;
   }

   public SerializationDebugger.ObjectStreamClassReflection org$apache$spark$serializer$SerializationDebugger$$reflect() {
      return org$apache$spark$serializer$SerializationDebugger$$reflect;
   }

   // $FF: synthetic method
   private final SerializationDebugger.ObjectStreamClassReflection liftedTree1$1() {
      SerializationDebugger.ObjectStreamClassReflection var10000;
      try {
         var10000 = new SerializationDebugger.ObjectStreamClassReflection();
      } catch (Exception var2) {
         this.logWarning((Function0)(() -> "Cannot find private methods using reflection"), var2);
         var10000 = null;
      }

      return var10000;
   }

   private SerializationDebugger$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
