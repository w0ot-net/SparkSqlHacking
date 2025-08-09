package org.apache.spark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.xbean.asm9.ClassReader;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.ArrayOps;
import scala.collection.IterableOnceOps;
import scala.collection.SetOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Set;
import scala.collection.mutable.Stack;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import sun.reflect.ReflectionFactory;

public final class ClosureCleaner$ implements Logging {
   public static final ClosureCleaner$ MODULE$ = new ClosureCleaner$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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
      Logging.logInfo$(this, (Function0)msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, (LogEntry)entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, (LogEntry)entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, (Function0)msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, (LogEntry)entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, (LogEntry)entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, (Function0)msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, (LogEntry)entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, (LogEntry)entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, (Function0)msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, (LogEntry)entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, (LogEntry)entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, (Function0)msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, (LogEntry)entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, (LogEntry)entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, (Function0)msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, (Function0)msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, (Function0)msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, (Function0)msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, (Function0)msg, throwable);
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

   public ClassReader getClassReader(final Class cls) {
      String var10000 = cls.getName();
      String className = var10000.replaceFirst("^.*\\.", "") + ".class";
      InputStream resourceStream = cls.getResourceAsStream(className);
      if (resourceStream == null) {
         return null;
      } else {
         ByteArrayOutputStream baos = new ByteArrayOutputStream(128);
         SparkStreamUtils$.MODULE$.copyStream(resourceStream, baos, true, SparkStreamUtils$.MODULE$.copyStream$default$4());
         return new ClassReader(new ByteArrayInputStream(baos.toByteArray()));
      }
   }

   public boolean isAmmoniteCommandOrHelper(final Class clazz) {
      return clazz.getName().matches("^ammonite\\.\\$sess\\.cmd[0-9]*(\\$Helper\\$?)?");
   }

   public boolean isDefinedInAmmonite(final Class clazz) {
      return clazz.getName().matches("^ammonite\\.\\$sess\\.cmd[0-9]*.*");
   }

   private boolean isClosure(final Class cls) {
      return cls.getName().contains("$anonfun$");
   }

   private Tuple2 getOuterClassesAndObjects(final Object obj) {
      Object var2 = new Object();

      Tuple2 var10000;
      try {
         .MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])obj.getClass().getDeclaredFields()), (f) -> BoxesRunTime.boxToBoolean($anonfun$getOuterClassesAndObjects$1(f))).foreach((f) -> {
            $anonfun$getOuterClassesAndObjects$2(obj, var2, f);
            return BoxedUnit.UNIT;
         });
         var10000 = new Tuple2(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$);
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = (Tuple2)var4.value();
      }

      return var10000;
   }

   private List getInnerClosureClasses(final Object obj) {
      Set seen = (Set)scala.collection.mutable.Set..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{obj.getClass()})));
      Stack stack = (Stack)scala.collection.mutable.Stack..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{obj.getClass()})));

      while(!stack.isEmpty()) {
         ClassReader cr = this.getClassReader((Class)stack.pop());
         if (cr != null) {
            Set set = (Set)scala.collection.mutable.Set..MODULE$.empty();
            cr.accept(new InnerClosureFinder(set), 0);
            set.diff(seen).foreach((cls) -> {
               seen.$plus$eq(cls);
               return stack.push(cls);
            });
         }
      }

      return seen.diff((scala.collection.Set)scala.collection.mutable.Set..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{obj.getClass()})))).toList();
   }

   private void initAccessedFields(final scala.collection.mutable.Map accessedFields, final Seq outerClasses) {
      outerClasses.foreach((cls) -> {
         $anonfun$initAccessedFields$1(accessedFields, cls);
         return BoxedUnit.UNIT;
      });
   }

   private void setAccessedFields(final Class outerClass, final Object clone, final Object obj, final scala.collection.mutable.Map accessedFields) {
      ((IterableOnceOps)accessedFields.apply(outerClass)).foreach((fieldName) -> {
         $anonfun$setAccessedFields$1(outerClass, obj, clone, fieldName);
         return BoxedUnit.UNIT;
      });
   }

   private Object cloneAndSetFields(final Object parent, final Object obj, final Class outerClass, final scala.collection.mutable.Map accessedFields) {
      Object clone = this.instantiateClass(outerClass, parent);
      Class currentClass = outerClass;
      scala.Predef..MODULE$.assert(outerClass != null, () -> "The outer class can't be null.");

      while(currentClass != null) {
         this.setAccessedFields(currentClass, clone, obj, accessedFields);
         currentClass = currentClass.getSuperclass();
      }

      return clone;
   }

   public boolean clean(final Object func, final boolean cleanTransitively, final scala.collection.mutable.Map accessedFields) {
      Object var4 = new Object();

      boolean var10000;
      try {
         Option maybeIndylambdaProxy = IndylambdaScalaClosures$.MODULE$.getSerializationProxy(func);
         if (!this.isClosure(func.getClass()) && maybeIndylambdaProxy.isEmpty()) {
            this.logDebug((Function0)(() -> "Expected a closure; got " + func.getClass().getName()));
            return false;
         }

         if (func == null) {
            return false;
         }

         if (maybeIndylambdaProxy.isEmpty()) {
            this.cleanNonIndyLambdaClosure(func, cleanTransitively, accessedFields);
         } else {
            SerializedLambda lambdaProxy = (SerializedLambda)maybeIndylambdaProxy.get();
            String implMethodName = lambdaProxy.getImplMethodName();
            this.logDebug((Function0)(() -> "Cleaning indylambda closure: " + implMethodName));
            String capturingClassName = lambdaProxy.getCapturingClass().replace('/', '.');
            ClassLoader classLoader = func.getClass().getClassLoader();
            Class capturingClass = Class.forName(capturingClassName, false, classLoader);
            ClassReader capturingClassReader = this.getClassReader(capturingClass);
            capturingClassReader.accept(new ReturnStatementFinder(scala.Option..MODULE$.apply(implMethodName)), 0);
            if (lambdaProxy.getCapturedArgCount() <= 0) {
               return false;
            }

            Object outerThis = scala.Option..MODULE$.apply(lambdaProxy.getCapturedArg(0)).getOrElse(() -> {
               throw new NonLocalReturnControl.mcZ.sp(var4, false);
            });
            if (this.isDefinedInAmmonite(outerThis.getClass())) {
               IndylambdaScalaClosures$.MODULE$.getSerializationProxy(outerThis).foreach((x$1) -> {
                  throw new NonLocalReturnControl.mcZ.sp(var4, MODULE$.clean(outerThis, cleanTransitively, accessedFields));
               });
               this.cleanupAmmoniteReplClosure(func, lambdaProxy, outerThis, cleanTransitively);
            } else {
               boolean isClosureDeclaredInScalaRepl = capturingClassName.startsWith("$line") && capturingClassName.endsWith("$iw");
               if (isClosureDeclaredInScalaRepl) {
                  label49: {
                     String var17 = outerThis.getClass().getName();
                     if (var17 == null) {
                        if (capturingClassName != null) {
                           break label49;
                        }
                     } else if (!var17.equals(capturingClassName)) {
                        break label49;
                     }

                     scala.Predef..MODULE$.assert(accessedFields.isEmpty());
                     this.cleanupScalaReplClosure(func, lambdaProxy, outerThis, cleanTransitively);
                  }
               }
            }

            this.logDebug((Function0)(() -> " +++ indylambda closure (" + implMethodName + ") is now cleaned +++"));
         }

         var10000 = true;
      } catch (NonLocalReturnControl var16) {
         if (var16.key() != var4) {
            throw var16;
         }

         var10000 = var16.value$mcZ$sp();
      }

      return var10000;
   }

   private void cleanNonIndyLambdaClosure(final Object func, final boolean cleanTransitively, final scala.collection.mutable.Map accessedFields) {
      this.logDebug((Function0)(() -> "+++ Cleaning closure " + func + " (" + func.getClass().getName() + ") +++"));
      List innerClasses = this.getInnerClosureClasses(func);
      Tuple2 var7 = this.getOuterClassesAndObjects(func);
      if (var7 != null) {
         List outerClasses = (List)var7._1();
         List outerObjects = (List)var7._2();
         Tuple2 var6 = new Tuple2(outerClasses, outerObjects);
         List outerClasses = (List)var6._1();
         List outerObjects = (List)var6._2();
         Field[] declaredFields = func.getClass().getDeclaredFields();
         Method[] declaredMethods = func.getClass().getDeclaredMethods();
         if (this.log().isDebugEnabled()) {
            this.logDebug((Function0)(() -> {
               ArrayOps var10000 = .MODULE$;
               return " + declared fields: " + var10000.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])declaredFields));
            }));
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])declaredFields), (f) -> {
               $anonfun$cleanNonIndyLambdaClosure$3(f);
               return BoxedUnit.UNIT;
            });
            this.logDebug((Function0)(() -> {
               ArrayOps var10000 = .MODULE$;
               return " + declared methods: " + var10000.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])declaredMethods));
            }));
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])declaredMethods), (m) -> {
               $anonfun$cleanNonIndyLambdaClosure$6(m);
               return BoxedUnit.UNIT;
            });
            this.logDebug((Function0)(() -> " + inner classes: " + innerClasses.size()));
            innerClasses.foreach((c) -> {
               $anonfun$cleanNonIndyLambdaClosure$9(c);
               return BoxedUnit.UNIT;
            });
            this.logDebug((Function0)(() -> " + outer classes: " + outerClasses.size()));
            outerClasses.foreach((c) -> {
               $anonfun$cleanNonIndyLambdaClosure$12(c);
               return BoxedUnit.UNIT;
            });
         }

         this.getClassReader(func.getClass()).accept(new ReturnStatementFinder(ReturnStatementFinder$.MODULE$.$lessinit$greater$default$1()), 0);
         if (accessedFields.isEmpty()) {
            this.logDebug((Function0)(() -> " + populating accessed fields because this is the starting closure"));
            this.initAccessedFields(accessedFields, outerClasses);
            Class var14 = func.getClass();
            innerClasses.$colon$colon(var14).foreach((cls) -> {
               $anonfun$cleanNonIndyLambdaClosure$15(accessedFields, cleanTransitively, cls);
               return BoxedUnit.UNIT;
            });
         }

         this.logDebug((Function0)(() -> " + fields accessed by starting closure: " + accessedFields.size() + " classes"));
         accessedFields.foreach((f) -> {
            $anonfun$cleanNonIndyLambdaClosure$17(f);
            return BoxedUnit.UNIT;
         });
         List outerPairs = ((List)outerClasses.zip(outerObjects)).reverse();
         ObjectRef parent = ObjectRef.create((Object)null);
         if (outerPairs.nonEmpty()) {
            Class outermostClass = (Class)((Tuple2)outerPairs.head())._1();
            Object outermostObject = ((Tuple2)outerPairs.head())._2();
            if (this.isClosure(outermostClass)) {
               this.logDebug((Function0)(() -> " + outermost object is a closure, so we clone it: " + outermostClass));
            } else if (outermostClass.getName().startsWith("$line")) {
               this.logDebug((Function0)(() -> " + outermost object is a REPL line object, so we clone it: " + outermostClass));
            } else {
               this.logDebug((Function0)(() -> " + outermost object is not a closure or REPL line object, so do not clone it: " + outermostClass));
               parent.elem = outermostObject;
               outerPairs = (List)outerPairs.tail();
            }
         } else {
            this.logDebug((Function0)(() -> " + there are no enclosing objects!"));
         }

         outerPairs.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$cleanNonIndyLambdaClosure$23(check$ifrefutable$1))).foreach((x$3) -> {
            $anonfun$cleanNonIndyLambdaClosure$24(parent, accessedFields, cleanTransitively, x$3);
            return BoxedUnit.UNIT;
         });
         if (parent.elem != null) {
            Field field = func.getClass().getDeclaredField("$outer");
            field.setAccessible(true);
            if (accessedFields.contains(func.getClass()) && !((SetOps)accessedFields.apply(func.getClass())).contains("$outer")) {
               this.logDebug((Function0)(() -> " + the starting closure doesn't actually need " + parent.elem + ", so we null it out"));
               field.set(func, (Object)null);
            } else {
               field.set(func, parent.elem);
            }
         }

         this.logDebug((Function0)(() -> " +++ closure " + func + " (" + func.getClass().getName() + ") is now cleaned +++"));
      } else {
         throw new MatchError(var7);
      }
   }

   private void cleanupScalaReplClosure(final Object func, final SerializedLambda lambdaProxy, final Object outerThis, final boolean cleanTransitively) {
      Class capturingClass = outerThis.getClass();
      scala.collection.mutable.Map accessedFields = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      this.initAccessedFields(accessedFields, new scala.collection.immutable..colon.colon(capturingClass, scala.collection.immutable.Nil..MODULE$));
      IndylambdaScalaClosures$.MODULE$.findAccessedFields(lambdaProxy, func.getClass().getClassLoader(), accessedFields, (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty(), (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty(), cleanTransitively);
      this.logDebug((Function0)(() -> " + fields accessed by starting closure: " + accessedFields.size() + " classes"));
      accessedFields.foreach((f) -> {
         $anonfun$cleanupScalaReplClosure$2(f);
         return BoxedUnit.UNIT;
      });
      if (((IterableOnceOps)accessedFields.apply(capturingClass)).size() < capturingClass.getDeclaredFields().length) {
         this.logDebug((Function0)(() -> " + cloning instance of REPL class " + capturingClass.getName()));
         Object clonedOuterThis = this.cloneAndSetFields((Object)null, outerThis, capturingClass, accessedFields);
         Field outerField = func.getClass().getDeclaredField("arg$1");
         this.setFieldAndIgnoreModifiers(func, outerField, clonedOuterThis);
      }
   }

   private void cleanupAmmoniteReplClosure(final Object func, final SerializedLambda lambdaProxy, final Object outerThis, final boolean cleanTransitively) {
      scala.collection.mutable.Map accessedFields = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      this.initAccessedFields(accessedFields, new scala.collection.immutable..colon.colon(outerThis.getClass(), scala.collection.immutable.Nil..MODULE$));
      scala.collection.mutable.Map ammCmdInstances = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      scala.collection.mutable.Map accessedAmmCmdFields = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      if (this.isAmmoniteCommandOrHelper(outerThis.getClass())) {
         ammCmdInstances.update(outerThis.getClass(), outerThis);
         accessedAmmCmdFields.update(outerThis.getClass(), scala.collection.mutable.Set..MODULE$.empty());
      }

      IndylambdaScalaClosures$.MODULE$.findAccessedFields(lambdaProxy, func.getClass().getClassLoader(), accessedFields, accessedAmmCmdFields, ammCmdInstances, cleanTransitively);
      this.logTrace((Function0)(() -> " + command fields accessed by starting closure: " + accessedAmmCmdFields.size() + " classes"));
      accessedAmmCmdFields.foreach((f) -> {
         $anonfun$cleanupAmmoniteReplClosure$2(f);
         return BoxedUnit.UNIT;
      });
      scala.collection.mutable.Map cmdClones = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      ammCmdInstances.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$cleanupAmmoniteReplClosure$4(check$ifrefutable$2))).withFilter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$cleanupAmmoniteReplClosure$5(x$4))).foreach((x$5) -> {
         $anonfun$cleanupAmmoniteReplClosure$6(cmdClones, x$5);
         return BoxedUnit.UNIT;
      });
      ammCmdInstances.withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$cleanupAmmoniteReplClosure$8(check$ifrefutable$3))).withFilter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$cleanupAmmoniteReplClosure$9(x$7))).foreach((x$8) -> {
         $anonfun$cleanupAmmoniteReplClosure$10(cmdClones, x$8);
         return BoxedUnit.UNIT;
      });
      cmdClones.withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$cleanupAmmoniteReplClosure$15(check$ifrefutable$4))).foreach((x$9) -> {
         $anonfun$cleanupAmmoniteReplClosure$16(accessedAmmCmdFields, cmdClones, ammCmdInstances, x$9);
         return BoxedUnit.UNIT;
      });
      Object var10000;
      if (!this.isAmmoniteCommandOrHelper(outerThis.getClass())) {
         this.logDebug((Function0)(() -> " + Cloning instance of lambda capturing class " + outerThis.getClass().getName()));
         Object clone = this.cloneAndSetFields((Object)null, outerThis, outerThis.getClass(), accessedFields);
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])outerThis.getClass().getDeclaredFields()), (field) -> {
            $anonfun$cleanupAmmoniteReplClosure$21(cmdClones, clone, field);
            return BoxedUnit.UNIT;
         });
         var10000 = clone;
      } else {
         var10000 = cmdClones.apply(outerThis.getClass());
      }

      Object outerThisClone = var10000;
      Field outerField = func.getClass().getDeclaredField("arg$1");
      this.setFieldAndIgnoreModifiers(func, outerField, outerThisClone);
   }

   private void setFieldAndIgnoreModifiers(final Object obj, final Field field, final Object value) {
      Option modifiersField = this.getFinalModifiersFieldForJava17(field);
      modifiersField.foreach((m) -> {
         $anonfun$setFieldAndIgnoreModifiers$1(field, m);
         return BoxedUnit.UNIT;
      });
      field.setAccessible(true);
      field.set(obj, value);
      modifiersField.foreach((m) -> {
         $anonfun$setFieldAndIgnoreModifiers$2(field, m);
         return BoxedUnit.UNIT;
      });
   }

   private Option getFinalModifiersFieldForJava17(final Field field) {
      if (Modifier.isFinal(field.getModifiers())) {
         Method methodGetDeclaredFields0 = Class.class.getDeclaredMethod("getDeclaredFields0", Boolean.TYPE);
         methodGetDeclaredFields0.setAccessible(true);
         Field[] fields = (Field[])methodGetDeclaredFields0.invoke(Field.class, BoxesRunTime.boxToBoolean(false));
         Option modifiersFieldOption = .MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])fields), (fieldx) -> BoxesRunTime.boxToBoolean($anonfun$getFinalModifiersFieldForJava17$1(fieldx)));
         scala.Predef..MODULE$.require(modifiersFieldOption.isDefined());
         modifiersFieldOption.foreach((x$10) -> {
            $anonfun$getFinalModifiersFieldForJava17$2(x$10);
            return BoxedUnit.UNIT;
         });
         return modifiersFieldOption;
      } else {
         return scala.None..MODULE$;
      }
   }

   private Object instantiateClass(final Class cls, final Object enclosingObject) {
      ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
      Constructor parentCtor = Object.class.getDeclaredConstructor();
      Constructor newCtor = rf.newConstructorForSerialization(cls, parentCtor);
      Object obj = newCtor.newInstance();
      if (enclosingObject != null) {
         Field field = cls.getDeclaredField("$outer");
         field.setAccessible(true);
         field.set(obj, enclosingObject);
      }

      return obj;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getOuterClassesAndObjects$1(final Field f) {
      boolean var2;
      label23: {
         String var10000 = f.getName();
         String var1 = "$outer";
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final void $anonfun$getOuterClassesAndObjects$2(final Object obj$1, final Object nonLocalReturnKey1$1, final Field f) {
      f.setAccessible(true);
      Object outer = f.get(obj$1);
      if (outer != null) {
         if (MODULE$.isClosure(f.getType())) {
            Tuple2 recurRet = MODULE$.getOuterClassesAndObjects(outer);
            Class var5 = f.getType();
            throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Tuple2(((List)recurRet._1()).$colon$colon(var5), ((List)recurRet._2()).$colon$colon(outer)));
         } else {
            Class var7 = f.getType();
            throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Tuple2(scala.collection.immutable.Nil..MODULE$.$colon$colon(var7), scala.collection.immutable.Nil..MODULE$.$colon$colon(outer)));
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$initAccessedFields$1(final scala.collection.mutable.Map accessedFields$1, final Class cls) {
      Class currentClass = cls;
      scala.Predef..MODULE$.assert(cls != null, () -> "The outer class can't be null.");

      while(currentClass != null) {
         accessedFields$1.update(currentClass, scala.collection.mutable.Set..MODULE$.empty());
         currentClass = currentClass.getSuperclass();
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$setAccessedFields$1(final Class outerClass$1, final Object obj$2, final Object clone$1, final String fieldName) {
      Field field = outerClass$1.getDeclaredField(fieldName);
      field.setAccessible(true);
      Object value = field.get(obj$2);
      field.set(clone$1, value);
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanNonIndyLambdaClosure$3(final Field f) {
      MODULE$.logDebug((Function0)(() -> "     " + f));
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanNonIndyLambdaClosure$6(final Method m) {
      MODULE$.logDebug((Function0)(() -> "     " + m));
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanNonIndyLambdaClosure$9(final Class c) {
      MODULE$.logDebug((Function0)(() -> "     " + c.getName()));
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanNonIndyLambdaClosure$12(final Class c) {
      MODULE$.logDebug((Function0)(() -> "     " + c.getName()));
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanNonIndyLambdaClosure$15(final scala.collection.mutable.Map accessedFields$3, final boolean cleanTransitively$2, final Class cls) {
      MODULE$.getClassReader(cls).accept(new FieldAccessFinder(accessedFields$3, cleanTransitively$2, FieldAccessFinder$.MODULE$.$lessinit$greater$default$3(), FieldAccessFinder$.MODULE$.$lessinit$greater$default$4()), 0);
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanNonIndyLambdaClosure$17(final Tuple2 f) {
      MODULE$.logDebug((Function0)(() -> "     " + f));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanNonIndyLambdaClosure$23(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanNonIndyLambdaClosure$24(final ObjectRef parent$1, final scala.collection.mutable.Map accessedFields$3, final boolean cleanTransitively$2, final Tuple2 x$3) {
      if (x$3 == null) {
         throw new MatchError(x$3);
      } else {
         Class cls = (Class)x$3._1();
         Object obj = x$3._2();
         MODULE$.logDebug((Function0)(() -> " + cloning instance of class " + cls.getName()));
         Object clone = MODULE$.cloneAndSetFields(parent$1.elem, obj, cls, accessedFields$3);
         if (cleanTransitively$2 && MODULE$.isClosure(clone.getClass())) {
            MODULE$.logDebug((Function0)(() -> " + cleaning cloned closure recursively (" + cls.getName() + ")"));
            BoxesRunTime.boxToBoolean(MODULE$.clean(clone, cleanTransitively$2, accessedFields$3));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         parent$1.elem = clone;
         BoxedUnit var9 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupScalaReplClosure$2(final Tuple2 f) {
      MODULE$.logDebug((Function0)(() -> "     " + f));
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupAmmoniteReplClosure$2(final Tuple2 f) {
      MODULE$.logTrace((Function0)(() -> "     " + f));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupAmmoniteReplClosure$4(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupAmmoniteReplClosure$5(final Tuple2 x$4) {
      if (x$4 != null) {
         Class cmdClass = (Class)x$4._1();
         return !cmdClass.getName().contains("Helper");
      } else {
         throw new MatchError(x$4);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupAmmoniteReplClosure$6(final scala.collection.mutable.Map cmdClones$1, final Tuple2 x$5) {
      if (x$5 != null) {
         Class cmdClass = (Class)x$5._1();
         MODULE$.logDebug((Function0)(() -> " + Cloning instance of Ammonite command class " + cmdClass.getName()));
         cmdClones$1.update(cmdClass, MODULE$.instantiateClass(cmdClass, (Object)null));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$5);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupAmmoniteReplClosure$8(final Tuple2 check$ifrefutable$3) {
      return check$ifrefutable$3 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupAmmoniteReplClosure$9(final Tuple2 x$7) {
      if (x$7 != null) {
         Class cmdHelperClass = (Class)x$7._1();
         return cmdHelperClass.getName().contains("Helper");
      } else {
         throw new MatchError(x$7);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupAmmoniteReplClosure$11(final Field x$6) {
      boolean var2;
      label23: {
         String var10000 = x$6.getName();
         String var1 = "$outer";
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupAmmoniteReplClosure$10(final scala.collection.mutable.Map cmdClones$1, final Tuple2 x$8) {
      if (x$8 != null) {
         Class cmdHelperClass = (Class)x$8._1();
         Object cmdHelperInstance = x$8._2();
         Option cmdHelperOuter = .MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])cmdHelperClass.getDeclaredFields()), (x$6) -> BoxesRunTime.boxToBoolean($anonfun$cleanupAmmoniteReplClosure$11(x$6))).map((field) -> {
            field.setAccessible(true);
            return field.get(cmdHelperInstance);
         });
         Object outerClone = cmdHelperOuter.flatMap((o) -> cmdClones$1.get(o.getClass())).orNull(scala..less.colon.less..MODULE$.refl());
         MODULE$.logDebug((Function0)(() -> " + Cloning instance of Ammonite command helper class " + cmdHelperClass.getName()));
         cmdClones$1.update(cmdHelperClass, MODULE$.instantiateClass(cmdHelperClass, outerClone));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$8);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupAmmoniteReplClosure$15(final Tuple2 check$ifrefutable$4) {
      return check$ifrefutable$4 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupAmmoniteReplClosure$17(final Set accessedFields$5, final Field field) {
      boolean var3;
      label25: {
         if (accessedFields$5.contains(field.getName())) {
            String var10000 = field.getName();
            String var2 = "$outer";
            if (var10000 == null) {
               if (var2 != null) {
                  break label25;
               }
            } else if (!var10000.equals(var2)) {
               break label25;
            }
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupAmmoniteReplClosure$18(final scala.collection.mutable.Map cmdClones$1, final scala.collection.mutable.Map ammCmdInstances$1, final Class cmdClass$2, final Object cmdClone$1, final Field field) {
      Object value = cmdClones$1.getOrElse(field.getType(), () -> {
         field.setAccessible(true);
         return field.get(ammCmdInstances$1.apply(cmdClass$2));
      });
      MODULE$.setFieldAndIgnoreModifiers(cmdClone$1, field, value);
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupAmmoniteReplClosure$16(final scala.collection.mutable.Map accessedAmmCmdFields$1, final scala.collection.mutable.Map cmdClones$1, final scala.collection.mutable.Map ammCmdInstances$1, final Tuple2 x$9) {
      if (x$9 != null) {
         Object cmdClone = x$9._2();
         Class cmdClass = cmdClone.getClass();
         Set accessedFields = (Set)accessedAmmCmdFields$1.apply(cmdClass);
         .MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])cmdClone.getClass().getDeclaredFields()), (field) -> BoxesRunTime.boxToBoolean($anonfun$cleanupAmmoniteReplClosure$17(accessedFields, field))).foreach((field) -> {
            $anonfun$cleanupAmmoniteReplClosure$18(cmdClones$1, ammCmdInstances$1, cmdClass, cmdClone, field);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$9);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupAmmoniteReplClosure$22(final Object clone$2, final Field field$2, final Object value) {
      MODULE$.setFieldAndIgnoreModifiers(clone$2, field$2, value);
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupAmmoniteReplClosure$21(final scala.collection.mutable.Map cmdClones$1, final Object clone$2, final Field field) {
      field.setAccessible(true);
      cmdClones$1.get(field.getType()).foreach((value) -> {
         $anonfun$cleanupAmmoniteReplClosure$22(clone$2, field, value);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$setFieldAndIgnoreModifiers$1(final Field field$3, final Field m) {
      m.setInt(field$3, field$3.getModifiers() & -17);
   }

   // $FF: synthetic method
   public static final void $anonfun$setFieldAndIgnoreModifiers$2(final Field field$3, final Field m) {
      m.setInt(field$3, field$3.getModifiers() | 16);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getFinalModifiersFieldForJava17$1(final Field field) {
      return "modifiers".equals(field.getName());
   }

   // $FF: synthetic method
   public static final void $anonfun$getFinalModifiersFieldForJava17$2(final Field x$10) {
      x$10.setAccessible(true);
   }

   private ClosureCleaner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
