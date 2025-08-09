package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.databind.Module;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005a2qa\u0001\u0003\u0011\u0002\u0007\u0005q\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003\"\u0001\u0011\u0005#E\u0001\u0010SKF,\u0018N]3e!J|\u0007/\u001a:uS\u0016\u001c8k\u00195f[\u0006lu\u000eZ;mK*\u0011QAB\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u000f!\ta!\\8ek2,'BA\u0005\u000b\u0003\u001dQ\u0017mY6t_:T!a\u0003\u0007\u0002\u0013\u0019\f7\u000f^3sq6d'\"A\u0007\u0002\u0007\r|Wn\u0001\u0001\u0014\u0007\u0001\u0001b\u0003\u0005\u0002\u0012)5\t!C\u0003\u0002\u0014\u0011\u0005AA-\u0019;bE&tG-\u0003\u0002\u0016%\t1Qj\u001c3vY\u0016\u0004\"a\u0006\r\u000e\u0003\u0011I!!\u0007\u0003\u0003\u001b)\u000b7m[:p]6{G-\u001e7f\u0003\u0019!\u0013N\\5uIQ\tA\u0004\u0005\u0002\u001e?5\taDC\u0001\u0006\u0013\t\u0001cD\u0001\u0003V]&$\u0018!D4fi6{G-\u001e7f\u001d\u0006lW\rF\u0001$!\t!3F\u0004\u0002&SA\u0011aEH\u0007\u0002O)\u0011\u0001FD\u0001\u0007yI|w\u000e\u001e \n\u0005)r\u0012A\u0002)sK\u0012,g-\u0003\u0002-[\t11\u000b\u001e:j]\u001eT!A\u000b\u0010)\r\u0001y#gM\u001b7!\ti\u0002'\u0003\u00022=\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\nA'A*xS2d\u0007EY3!e\u0016lwN^3eA%t\u0007e\r\u00181]A\u0002#/\u001a7fCN,\u0007%Y:!U\u0006\u001c7n]8o[5|G-\u001e7f[)\u001cxN\\*dQ\u0016l\u0017\rI5tA\t,\u0017N\\4!I&\u001c8m\u001c8uS:,X\rZ\u0001\u0006g&t7-Z\u0011\u0002o\u00051!GL\u00194]A\u0002"
)
public interface RequiredPropertiesSchemaModule extends JacksonModule {
   // $FF: synthetic method
   static String getModuleName$(final RequiredPropertiesSchemaModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "RequiredPropertiesSchemaModule";
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$2) {
      x$2.insertAnnotationIntrospector(DefaultRequiredAnnotationIntrospector$.MODULE$);
   }

   static void $init$(final RequiredPropertiesSchemaModule $this) {
      $this.$plus$eq((x$2) -> {
         $anonfun$$init$$1(x$2);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
