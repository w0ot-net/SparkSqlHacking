package org.apache.spark.sql.catalyst.encoders;

import java.lang.invoke.SerializedLambda;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.sql.errors.ExecutionErrors$;
import scala.Function0;
import scala.Option;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.util.matching.Regex;

public final class OuterScopes$ {
   public static final OuterScopes$ MODULE$ = new OuterScopes$();
   private static final ReferenceQueue queue = new ReferenceQueue();
   private static final ConcurrentHashMap outerScopes = new ConcurrentHashMap();
   private static final Regex REPLClass;
   private static final Regex AmmoniteREPLClass;

   static {
      REPLClass = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^(\\$line(?:\\d+)\\.\\$read)(?:\\$\\$iw)+$"));
      AmmoniteREPLClass = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^(ammonite\\.\\$sess\\.cmd(?:\\d+)\\$).*"));
   }

   private HashableWeakReference classLoaderRef(final Class c) {
      return new HashableWeakReference(c.getClassLoader(), queue);
   }

   private void cleanOuterScopes() {
      for(Reference entry = queue.poll(); entry != null; entry = queue.poll()) {
         outerScopes.remove(entry);
      }

   }

   public void addOuterScope(final Object outer) {
      this.cleanOuterScopes();
      Class clz = outer.getClass();
      ((ConcurrentHashMap)outerScopes.computeIfAbsent(this.classLoaderRef(clz), (x$1) -> new ConcurrentHashMap())).putIfAbsent(clz.getName(), new WeakReference(outer));
   }

   public Function0 getOuterScope(final Class innerCls) {
      if (!innerCls.isMemberClass()) {
         return null;
      } else {
         Class outerClass = innerCls.getDeclaringClass();
         String outerClassName = outerClass.getName();
         Object outer = scala.Option..MODULE$.apply(outerScopes.get(this.classLoaderRef(outerClass))).flatMap((map) -> scala.Option..MODULE$.apply(map.get(outerClassName))).map((x$2) -> x$2.get()).orNull(scala..less.colon.less..MODULE$.refl());
         if (outer == null) {
            if (outerClassName != null) {
               Option var7 = AmmoniteREPLClass.unapplySeq(outerClassName);
               if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(1) == 0) {
                  String cellClassName = (String)((LinearSeqOps)var7.get()).apply(0);
                  Class objClass = org.apache.spark.util.SparkClassUtils..MODULE$.classForName(cellClassName, org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$2(), org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$3());
                  Object objInstance = objClass.getField("MODULE$").get((Object)null);
                  Object obj = objClass.getMethod("instance").invoke(objInstance);
                  this.addOuterScope(obj);
                  return () -> obj;
               }
            }

            if (outerClassName != null) {
               Option var12 = REPLClass.unapplySeq(outerClassName);
               if (!var12.isEmpty() && var12.get() != null && ((List)var12.get()).lengthCompare(1) == 0) {
                  String baseClassName = (String)((LinearSeqOps)var12.get()).apply(0);
                  return () -> {
                     Class objClass = org.apache.spark.util.SparkClassUtils..MODULE$.classForName(baseClassName + "$", org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$2(), org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$3());
                     Object objInstance = objClass.getField("MODULE$").get((Object)null);
                     Object baseInstance = objClass.getMethod("INSTANCE").invoke(objInstance);
                     Class baseClass = org.apache.spark.util.SparkClassUtils..MODULE$.classForName(baseClassName, org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$2(), org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$3());
                     Method getter = MODULE$.iwGetter(baseClass);

                     Object obj;
                     for(obj = baseInstance; getter != null; getter = MODULE$.iwGetter(getter.getReturnType())) {
                        obj = getter.invoke(obj);
                     }

                     if (obj == null) {
                        throw ExecutionErrors$.MODULE$.cannotGetOuterPointerForInnerClassError(innerCls);
                     } else {
                        MODULE$.addOuterScope(obj);
                        return obj;
                     }
                  };
               }
            }

            return null;
         } else {
            return () -> outer;
         }
      }
   }

   private Method iwGetter(final Class cls) {
      Method var10000;
      try {
         var10000 = cls.getMethod("$iw");
      } catch (NoSuchMethodException var2) {
         var10000 = null;
      }

      return var10000;
   }

   private OuterScopes$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
