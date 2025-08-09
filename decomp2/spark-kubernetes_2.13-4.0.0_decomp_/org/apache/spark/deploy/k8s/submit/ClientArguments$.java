package org.apache.spark.deploy.k8s.submit;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Array;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;

public final class ClientArguments$ implements Serializable {
   public static final ClientArguments$ MODULE$ = new ClientArguments$();

   public ClientArguments fromCommandLineArgs(final String[] args) {
      ObjectRef mainAppResource = ObjectRef.create(new JavaMainAppResource(.MODULE$));
      ObjectRef mainClass = ObjectRef.create(.MODULE$);
      ArrayBuffer driverArgs = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
      ObjectRef proxyUser = ObjectRef.create(.MODULE$);
      scala.collection.ArrayOps..MODULE$.sliding$extension(scala.Predef..MODULE$.refArrayOps((Object[])args), 2, 2).toList().foreach((x0$1) -> {
         if (x0$1 != null) {
            Object var7 = scala.Array..MODULE$.unapplySeq(x0$1);
            if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var7) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 2) == 0) {
               String var8 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 0);
               String primaryJavaResource = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 1);
               if ("--primary-java-resource".equals(var8) && primaryJavaResource != null) {
                  mainAppResource.elem = new JavaMainAppResource(new Some(primaryJavaResource));
                  return BoxedUnit.UNIT;
               }
            }
         }

         if (x0$1 != null) {
            Object var11 = scala.Array..MODULE$.unapplySeq(x0$1);
            if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var11) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 2) == 0) {
               String var12 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 0);
               String primaryPythonResource = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var11), 1);
               if ("--primary-py-file".equals(var12) && primaryPythonResource != null) {
                  mainAppResource.elem = new PythonMainAppResource(primaryPythonResource);
                  return BoxedUnit.UNIT;
               }
            }
         }

         if (x0$1 != null) {
            Object var15 = scala.Array..MODULE$.unapplySeq(x0$1);
            if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var15) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var15)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var15), 2) == 0) {
               String var16 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var15), 0);
               String primaryRFile = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var15), 1);
               if ("--primary-r-file".equals(var16) && primaryRFile != null) {
                  mainAppResource.elem = new RMainAppResource(primaryRFile);
                  return BoxedUnit.UNIT;
               }
            }
         }

         if (x0$1 != null) {
            Object var19 = scala.Array..MODULE$.unapplySeq(x0$1);
            if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var19) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var19)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var19), 2) == 0) {
               String var20 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var19), 0);
               String clazz = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var19), 1);
               if ("--main-class".equals(var20) && clazz != null) {
                  mainClass.elem = new Some(clazz);
                  return BoxedUnit.UNIT;
               }
            }
         }

         if (x0$1 != null) {
            Object var23 = scala.Array..MODULE$.unapplySeq(x0$1);
            if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var23) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var23)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var23), 2) == 0) {
               String var24 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var23), 0);
               String arg = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var23), 1);
               if ("--arg".equals(var24) && arg != null) {
                  return driverArgs.$plus$eq(arg);
               }
            }
         }

         if (x0$1 != null) {
            Object var27 = scala.Array..MODULE$.unapplySeq(x0$1);
            if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var27) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var27)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var27), 2) == 0) {
               String var28 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var27), 0);
               String user = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var27), 1);
               if ("--proxy-user".equals(var28) && user != null) {
                  proxyUser.elem = new Some(user);
                  return BoxedUnit.UNIT;
               }
            }
         }

         String invalid = scala.Predef..MODULE$.wrapRefArray((Object[])x0$1).mkString(" ");
         throw new RuntimeException("Unknown arguments: " + invalid);
      });
      scala.Predef..MODULE$.require(((Option)mainClass.elem).isDefined(), () -> "Main class must be specified via --main-class");
      return new ClientArguments((MainAppResource)mainAppResource.elem, (String)((Option)mainClass.elem).get(), (String[])driverArgs.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)), (Option)proxyUser.elem);
   }

   public ClientArguments apply(final MainAppResource mainAppResource, final String mainClass, final String[] driverArgs, final Option proxyUser) {
      return new ClientArguments(mainAppResource, mainClass, driverArgs, proxyUser);
   }

   public Option unapply(final ClientArguments x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.mainAppResource(), x$0.mainClass(), x$0.driverArgs(), x$0.proxyUser())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClientArguments$.class);
   }

   private ClientArguments$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
