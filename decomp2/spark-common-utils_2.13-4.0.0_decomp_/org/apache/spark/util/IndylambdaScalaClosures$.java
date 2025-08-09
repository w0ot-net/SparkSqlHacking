package org.apache.spark.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import org.apache.commons.lang3.ClassUtils;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.xbean.asm9.ClassReader;
import org.apache.xbean.asm9.Handle;
import org.apache.xbean.asm9.MethodVisitor;
import org.apache.xbean.asm9.Type;
import org.apache.xbean.asm9.tree.ClassNode;
import org.apache.xbean.asm9.tree.MethodNode;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Queue;
import scala.collection.mutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class IndylambdaScalaClosures$ implements Logging {
   public static final IndylambdaScalaClosures$ MODULE$ = new IndylambdaScalaClosures$();
   private static final String LambdaMetafactoryClassName;
   private static final String LambdaMetafactoryMethodName;
   private static final String LambdaMetafactoryMethodDesc;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      LambdaMetafactoryClassName = "java/lang/invoke/LambdaMetafactory";
      LambdaMetafactoryMethodName = "altMetafactory";
      LambdaMetafactoryMethodDesc = "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;";
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

   public String LambdaMetafactoryClassName() {
      return LambdaMetafactoryClassName;
   }

   public String LambdaMetafactoryMethodName() {
      return LambdaMetafactoryMethodName;
   }

   public String LambdaMetafactoryMethodDesc() {
      return LambdaMetafactoryMethodDesc;
   }

   public Option getSerializationProxy(final Object maybeClosure) {
      Class var3 = maybeClosure.getClass();
      if (var3.isSynthetic() && maybeClosure instanceof Serializable) {
         if (isClosureCandidate$1(var3)) {
            Object var10000;
            try {
               var10000 = .MODULE$.apply(this.inspect(maybeClosure)).filter((lambdaProxy) -> BoxesRunTime.boxToBoolean($anonfun$getSerializationProxy$2(lambdaProxy)));
            } catch (Exception var5) {
               this.logDebug((Function0)(() -> "The given reference is not an indylambda Scala closure."), var5);
               var10000 = scala.None..MODULE$;
            }

            return (Option)var10000;
         } else {
            return scala.None..MODULE$;
         }
      } else {
         return scala.None..MODULE$;
      }
   }

   public boolean isIndylambdaScalaClosure(final SerializedLambda lambdaProxy) {
      return lambdaProxy.getImplMethodKind() == 6 && lambdaProxy.getImplMethodName().contains("$anonfun$");
   }

   public SerializedLambda inspect(final Object closure) {
      Method writeReplace = closure.getClass().getDeclaredMethod("writeReplace");
      writeReplace.setAccessible(true);
      return (SerializedLambda)writeReplace.invoke(closure);
   }

   public boolean isLambdaMetafactory(final Handle bsmHandle) {
      boolean var7;
      label41: {
         label36: {
            String var10000 = bsmHandle.getOwner();
            String var2 = this.LambdaMetafactoryClassName();
            if (var10000 == null) {
               if (var2 != null) {
                  break label36;
               }
            } else if (!var10000.equals(var2)) {
               break label36;
            }

            var10000 = bsmHandle.getName();
            String var3 = this.LambdaMetafactoryMethodName();
            if (var10000 == null) {
               if (var3 != null) {
                  break label36;
               }
            } else if (!var10000.equals(var3)) {
               break label36;
            }

            var10000 = bsmHandle.getDesc();
            String var4 = this.LambdaMetafactoryMethodDesc();
            if (var10000 == null) {
               if (var4 == null) {
                  break label41;
               }
            } else if (var10000.equals(var4)) {
               break label41;
            }
         }

         var7 = false;
         return var7;
      }

      var7 = true;
      return var7;
   }

   public boolean isLambdaBodyCapturingOuter(final Handle handle, final String ownerInternalName) {
      boolean var4;
      if (handle.getTag() == 6 && handle.getName().contains("$anonfun$")) {
         label20: {
            String var10000 = handle.getOwner();
            if (var10000 == null) {
               if (ownerInternalName != null) {
                  break label20;
               }
            } else if (!var10000.equals(ownerInternalName)) {
               break label20;
            }

            if (handle.getDesc().startsWith("(L" + ownerInternalName + ";")) {
               var4 = true;
               return var4;
            }
         }
      }

      var4 = false;
      return var4;
   }

   public boolean isInnerClassCtorCapturingOuter(final int op, final String owner, final String name, final String desc, final String callerInternalName) {
      boolean var10000;
      if (op == 183) {
         label19: {
            String var6 = "<init>";
            if (name == null) {
               if (var6 != null) {
                  break label19;
               }
            } else if (!name.equals(var6)) {
               break label19;
            }

            if (desc.startsWith("(L" + callerInternalName + ";")) {
               var10000 = true;
               return var10000;
            }
         }
      }

      var10000 = false;
      return var10000;
   }

   public void findAccessedFields(final SerializedLambda lambdaProxy, final ClassLoader lambdaClassLoader, final scala.collection.mutable.Map accessedFields, final scala.collection.mutable.Map accessedAmmCmdFields, final scala.collection.mutable.Map ammCmdInstances, final boolean findTransitively) {
      scala.collection.mutable.Map classInfoByInternalName = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      scala.collection.mutable.Map methodNodeById = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      String implClassInternalName = lambdaProxy.getImplClass();
      Tuple2 var12 = org$apache$spark$util$IndylambdaScalaClosures$$getOrUpdateClassInfo$1(implClassInternalName, classInfoByInternalName, lambdaClassLoader, methodNodeById);
      if (var12 == null) {
         throw new MatchError(var12);
      } else {
         Class implClass = (Class)var12._1();
         MethodIdentifier implMethodId = new MethodIdentifier(implClass, lambdaProxy.getImplMethodName(), lambdaProxy.getImplMethodSignature());
         Set trackedClassInternalNames = (Set)scala.collection.mutable.Set..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{implClassInternalName})));
         Set visited = (Set)scala.collection.mutable.Set..MODULE$.empty();
         Queue queue = (Queue)scala.collection.mutable.Queue..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MethodIdentifier[]{implMethodId}));

         while(queue.nonEmpty()) {
            MethodIdentifier currentId = (MethodIdentifier)queue.dequeue();
            visited.$plus$eq(currentId);
            Class currentClass = currentId.cls();
            addAmmoniteCommandFieldsToTracking$1(currentClass, lambdaProxy, ammCmdInstances, accessedAmmCmdFields);
            MethodNode currentMethodNode = (MethodNode)methodNodeById.apply(currentId);
            this.logTrace((Function0)(() -> {
               String var10000 = currentId.cls().getName();
               return "  scanning " + var10000 + "." + currentId.name() + currentId.desc();
            }));
            currentMethodNode.accept(new MethodVisitor(currentClass, accessedFields, accessedAmmCmdFields, trackedClassInternalNames, findTransitively, visited, queue, classInfoByInternalName, lambdaClassLoader, methodNodeById) {
               private final String currentClassName;
               private final String currentClassInternalName;
               private final Class currentClass$1;
               private final scala.collection.mutable.Map accessedFields$6;
               private final scala.collection.mutable.Map accessedAmmCmdFields$2;
               private final Set trackedClassInternalNames$1;
               private final boolean findTransitively$1;
               private final Set visited$1;
               private final Queue queue$1;
               private final scala.collection.mutable.Map classInfoByInternalName$1;
               private final ClassLoader lambdaClassLoader$1;
               private final scala.collection.mutable.Map methodNodeById$1;

               public String currentClassName() {
                  return this.currentClassName;
               }

               public String currentClassInternalName() {
                  return this.currentClassInternalName;
               }

               public void visitFieldInsn(final int op, final String owner, final String name, final String desc) {
                  if (op == 180 || op == 181) {
                     String ownerExternalName = owner.replace('/', '.');
                     this.accessedFields$6.keys().withFilter((cl) -> BoxesRunTime.boxToBoolean($anonfun$visitFieldInsn$1(ownerExternalName, cl))).foreach((cl) -> {
                        IndylambdaScalaClosures$.MODULE$.logTrace((Function0)(() -> "    found field access " + name + " on " + ownerExternalName));
                        return (Set)((Growable)this.accessedFields$6.apply(cl)).$plus$eq(name);
                     });
                     this.accessedAmmCmdFields$2.keys().withFilter((cl) -> BoxesRunTime.boxToBoolean($anonfun$visitFieldInsn$4(ownerExternalName, cl))).foreach((cl) -> {
                        IndylambdaScalaClosures$.MODULE$.logTrace((Function0)(() -> "    found Ammonite command field access " + name + " on " + ownerExternalName));
                        return (Set)((Growable)this.accessedAmmCmdFields$2.apply(cl)).$plus$eq(name);
                     });
                  }
               }

               public void visitMethodInsn(final int op, final String owner, final String name, final String desc, final boolean itf) {
                  String ownerExternalName;
                  label36: {
                     ownerExternalName = owner.replace('/', '.');
                     String var8 = this.currentClassInternalName();
                     if (owner == null) {
                        if (var8 == null) {
                           break label36;
                        }
                     } else if (owner.equals(var8)) {
                        break label36;
                     }

                     if (owner.startsWith("ammonite/$sess/cmd")) {
                        Tuple2 classInfo = IndylambdaScalaClosures$.org$apache$spark$util$IndylambdaScalaClosures$$getOrUpdateClassInfo$1(owner, this.classInfoByInternalName$1, this.lambdaClassLoader$1, this.methodNodeById$1);
                        IndylambdaScalaClosures$.org$apache$spark$util$IndylambdaScalaClosures$$pushIfNotVisited$1(new MethodIdentifier((Class)classInfo._1(), name, desc), this.visited$1, this.queue$1);
                        return;
                     }

                     if (IndylambdaScalaClosures$.MODULE$.isInnerClassCtorCapturingOuter(op, owner, name, desc, this.currentClassInternalName())) {
                        IndylambdaScalaClosures$.MODULE$.logDebug((Function0)(() -> "    found inner class " + ownerExternalName));
                        Tuple2 innerClassInfo = IndylambdaScalaClosures$.org$apache$spark$util$IndylambdaScalaClosures$$getOrUpdateClassInfo$1(owner, this.classInfoByInternalName$1, this.lambdaClassLoader$1, this.methodNodeById$1);
                        Class innerClass = (Class)innerClassInfo._1();
                        ClassNode innerClassNode = (ClassNode)innerClassInfo._2();
                        this.trackedClassInternalNames$1.$plus$eq(owner);
                        scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(innerClassNode.methods).asScala().foreach((m) -> {
                           $anonfun$visitMethodInsn$3(this, innerClass, m);
                           return BoxedUnit.UNIT;
                        });
                        return;
                     }

                     if (this.findTransitively$1 && this.trackedClassInternalNames$1.contains(owner)) {
                        IndylambdaScalaClosures$.MODULE$.logTrace((Function0)(() -> "    found call to outer " + ownerExternalName + "." + name + desc));
                        Tuple2 var14 = IndylambdaScalaClosures$.org$apache$spark$util$IndylambdaScalaClosures$$getOrUpdateClassInfo$1(owner, this.classInfoByInternalName$1, this.lambdaClassLoader$1, this.methodNodeById$1);
                        if (var14 != null) {
                           Class calleeClass = (Class)var14._1();
                           IndylambdaScalaClosures$.org$apache$spark$util$IndylambdaScalaClosures$$pushIfNotVisited$1(new MethodIdentifier(calleeClass, name, desc), this.visited$1, this.queue$1);
                           return;
                        }

                        throw new MatchError(var14);
                     }

                     IndylambdaScalaClosures$.MODULE$.logTrace((Function0)(() -> "    ignoring call to " + ownerExternalName + "." + name + desc));
                     return;
                  }

                  IndylambdaScalaClosures$.MODULE$.logTrace((Function0)(() -> "    found intra class call to " + ownerExternalName + "." + name + desc));
                  IndylambdaScalaClosures$.org$apache$spark$util$IndylambdaScalaClosures$$pushIfNotVisited$1(new MethodIdentifier(this.currentClass$1, name, desc), this.visited$1, this.queue$1);
               }

               public void visitInvokeDynamicInsn(final String name, final String desc, final Handle bsmHandle, final Seq bsmArgs) {
                  IndylambdaScalaClosures$.MODULE$.logTrace((Function0)(() -> "    invokedynamic: " + name + desc + ", bsmHandle=" + bsmHandle + ", bsmArgs=" + bsmArgs));
                  if (name.startsWith("apply")) {
                     if (Type.getReturnType(desc).getDescriptor().startsWith("Lscala/Function")) {
                        if (IndylambdaScalaClosures$.MODULE$.isLambdaMetafactory(bsmHandle)) {
                           Handle targetHandle = (Handle)bsmArgs.apply(1);
                           if (IndylambdaScalaClosures$.MODULE$.isLambdaBodyCapturingOuter(targetHandle, this.currentClassInternalName())) {
                              IndylambdaScalaClosures$.MODULE$.logDebug((Function0)(() -> "    found inner closure " + targetHandle));
                              MethodIdentifier calleeMethodId = new MethodIdentifier(this.currentClass$1, targetHandle.getName(), targetHandle.getDesc());
                              IndylambdaScalaClosures$.org$apache$spark$util$IndylambdaScalaClosures$$pushIfNotVisited$1(calleeMethodId, this.visited$1, this.queue$1);
                           }
                        }
                     }
                  }
               }

               public void visitInvokeDynamicInsn(final String name, final String desc, final Handle bsmHandle, final Object[] bsmArgs) {
                  this.visitInvokeDynamicInsn(name, desc, bsmHandle, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(bsmArgs));
               }

               // $FF: synthetic method
               public static final boolean $anonfun$visitFieldInsn$1(final String ownerExternalName$1, final Class cl) {
                  boolean var3;
                  label23: {
                     String var10000 = cl.getName();
                     if (var10000 == null) {
                        if (ownerExternalName$1 == null) {
                           break label23;
                        }
                     } else if (var10000.equals(ownerExternalName$1)) {
                        break label23;
                     }

                     var3 = false;
                     return var3;
                  }

                  var3 = true;
                  return var3;
               }

               // $FF: synthetic method
               public static final boolean $anonfun$visitFieldInsn$4(final String ownerExternalName$1, final Class cl) {
                  boolean var3;
                  label23: {
                     String var10000 = cl.getName();
                     if (var10000 == null) {
                        if (ownerExternalName$1 == null) {
                           break label23;
                        }
                     } else if (var10000.equals(ownerExternalName$1)) {
                        break label23;
                     }

                     var3 = false;
                     return var3;
                  }

                  var3 = true;
                  return var3;
               }

               // $FF: synthetic method
               public static final void $anonfun$visitMethodInsn$3(final Object $this, final Class innerClass$1, final MethodNode m) {
                  IndylambdaScalaClosures$.MODULE$.logTrace((Function0)(() -> "      found method " + m.name + m.desc));
                  IndylambdaScalaClosures$.org$apache$spark$util$IndylambdaScalaClosures$$pushIfNotVisited$1(new MethodIdentifier(innerClass$1, m.name, m.desc), $this.visited$1, $this.queue$1);
               }

               public {
                  this.currentClass$1 = currentClass$1;
                  this.accessedFields$6 = accessedFields$6;
                  this.accessedAmmCmdFields$2 = accessedAmmCmdFields$2;
                  this.trackedClassInternalNames$1 = trackedClassInternalNames$1;
                  this.findTransitively$1 = findTransitively$1;
                  this.visited$1 = visited$1;
                  this.queue$1 = queue$1;
                  this.classInfoByInternalName$1 = classInfoByInternalName$1;
                  this.lambdaClassLoader$1 = lambdaClassLoader$1;
                  this.methodNodeById$1 = methodNodeById$1;
                  this.currentClassName = currentClass$1.getName();
                  this.currentClassInternalName = this.currentClassName().replace('.', '/');
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return Class.lambdaDeserialize<invokedynamic>(var0);
               }
            });
         }

      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getSerializationProxy$1(final Class x$11) {
      return x$11.getName().startsWith("scala.Function");
   }

   private static final boolean isClosureCandidate$1(final Class cls) {
      Buffer implementedInterfaces = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(ClassUtils.getAllInterfaces(cls)).asScala();
      return implementedInterfaces.exists((x$11) -> BoxesRunTime.boxToBoolean($anonfun$getSerializationProxy$1(x$11)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getSerializationProxy$2(final SerializedLambda lambdaProxy) {
      return MODULE$.isIndylambdaScalaClosure(lambdaProxy);
   }

   private static final ClassNode getClassNode$1(final Class clazz) {
      ClassNode classNode = new ClassNode();
      ClassReader classReader = ClosureCleaner$.MODULE$.getClassReader(clazz);
      classReader.accept(classNode, 0);
      return classNode;
   }

   // $FF: synthetic method
   public static final void $anonfun$findAccessedFields$2(final scala.collection.mutable.Map methodNodeById$1, final Class clazz$1, final MethodNode m) {
      methodNodeById$1.update(new MethodIdentifier(clazz$1, m.name, m.desc), m);
   }

   public static final Tuple2 org$apache$spark$util$IndylambdaScalaClosures$$getOrUpdateClassInfo$1(final String classInternalName, final scala.collection.mutable.Map classInfoByInternalName$1, final ClassLoader lambdaClassLoader$1, final scala.collection.mutable.Map methodNodeById$1) {
      Tuple2 classInfo = (Tuple2)classInfoByInternalName$1.getOrElseUpdate(classInternalName, () -> {
         String classExternalName = classInternalName.replace('/', '.');
         Class clazz = Class.forName(classExternalName, false, lambdaClassLoader$1);

         for(Class curClazz = clazz; curClazz != null; curClazz = curClazz.getSuperclass()) {
            scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(getClassNode$1(curClazz).methods).asScala().foreach((m) -> {
               $anonfun$findAccessedFields$2(methodNodeById$1, clazz, m);
               return BoxedUnit.UNIT;
            });
         }

         return new Tuple2(clazz, getClassNode$1(clazz));
      });
      return classInfo;
   }

   public static final void org$apache$spark$util$IndylambdaScalaClosures$$pushIfNotVisited$1(final MethodIdentifier methodId, final Set visited$1, final Queue queue$1) {
      if (!visited$1.contains(methodId)) {
         queue$1.enqueue(methodId);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findAccessedFields$4(final Field otherCmdField) {
      return ClosureCleaner$.MODULE$.isAmmoniteCommandOrHelper(otherCmdField.getType());
   }

   // $FF: synthetic method
   public static final void $anonfun$findAccessedFields$5(final Object cmdInstance$1, final scala.collection.mutable.Map ammCmdInstances$2, final scala.collection.mutable.Map accessedAmmCmdFields$2, final Field otherCmdField) {
      otherCmdField.setAccessible(true);
      Object otherCmdHelperRef = otherCmdField.get(cmdInstance$1);
      Class otherCmdClass = otherCmdField.getType();
      if (otherCmdHelperRef != null && !ammCmdInstances$2.contains(otherCmdClass)) {
         MODULE$.logTrace((Function0)(() -> "      started tracking " + otherCmdClass.getName() + " Ammonite object"));
         ammCmdInstances$2.update(otherCmdClass, otherCmdHelperRef);
         accessedAmmCmdFields$2.update(otherCmdClass, scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$findAccessedFields$3(final scala.collection.mutable.Map ammCmdInstances$2, final scala.collection.mutable.Map accessedAmmCmdFields$2, final Object cmdInstance) {
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])cmdInstance.getClass().getDeclaredFields()), (otherCmdField) -> BoxesRunTime.boxToBoolean($anonfun$findAccessedFields$4(otherCmdField))).foreach((otherCmdField) -> {
         $anonfun$findAccessedFields$5(cmdInstance, ammCmdInstances$2, accessedAmmCmdFields$2, otherCmdField);
         return BoxedUnit.UNIT;
      });
   }

   private static final void addAmmoniteCommandFieldsToTracking$1(final Class currentClass, final SerializedLambda lambdaProxy$1, final scala.collection.mutable.Map ammCmdInstances$2, final scala.collection.mutable.Map accessedAmmCmdFields$2) {
      Object var10000;
      label17: {
         label16: {
            Class var5 = lambdaProxy$1.getCapturedArg(0).getClass();
            if (currentClass == null) {
               if (var5 == null) {
                  break label16;
               }
            } else if (currentClass.equals(var5)) {
               break label16;
            }

            var10000 = ammCmdInstances$2.get(currentClass);
            break label17;
         }

         var10000 = new Some(lambdaProxy$1.getCapturedArg(0));
      }

      Option currentInstance = (Option)var10000;
      currentInstance.foreach((cmdInstance) -> {
         $anonfun$findAccessedFields$3(ammCmdInstances$2, accessedAmmCmdFields$2, cmdInstance);
         return BoxedUnit.UNIT;
      });
   }

   private IndylambdaScalaClosures$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
