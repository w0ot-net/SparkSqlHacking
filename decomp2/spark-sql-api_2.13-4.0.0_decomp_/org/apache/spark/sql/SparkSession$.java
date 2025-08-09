package org.apache.spark.sql;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Symbols;
import scala.runtime.ModuleSerializationProxy;
import scala.util.Try.;

public final class SparkSession$ extends SparkSessionCompanion implements Serializable {
   public static final SparkSession$ MODULE$ = new SparkSession$();
   private static SparkSessionCompanion org$apache$spark$sql$SparkSession$$CLASSIC_COMPANION;
   private static SparkSessionCompanion org$apache$spark$sql$SparkSession$$CONNECT_COMPANION;
   private static volatile byte bitmap$0;

   private SparkSessionCompanion CLASSIC_COMPANION$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            org$apache$spark$sql$SparkSession$$CLASSIC_COMPANION = this.lookupCompanion("org.apache.spark.sql.classic.SparkSession");
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return org$apache$spark$sql$SparkSession$$CLASSIC_COMPANION;
   }

   public SparkSessionCompanion org$apache$spark$sql$SparkSession$$CLASSIC_COMPANION() {
      return (byte)(bitmap$0 & 1) == 0 ? this.CLASSIC_COMPANION$lzycompute() : org$apache$spark$sql$SparkSession$$CLASSIC_COMPANION;
   }

   private SparkSessionCompanion CONNECT_COMPANION$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            org$apache$spark$sql$SparkSession$$CONNECT_COMPANION = this.lookupCompanion("org.apache.spark.sql.connect.SparkSession");
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return org$apache$spark$sql$SparkSession$$CONNECT_COMPANION;
   }

   public SparkSessionCompanion org$apache$spark$sql$SparkSession$$CONNECT_COMPANION() {
      return (byte)(bitmap$0 & 2) == 0 ? this.CONNECT_COMPANION$lzycompute() : org$apache$spark$sql$SparkSession$$CONNECT_COMPANION;
   }

   public SparkSessionCompanion org$apache$spark$sql$SparkSession$$DEFAULT_COMPANION() {
      return (SparkSessionCompanion).MODULE$.apply(() -> MODULE$.org$apache$spark$sql$SparkSession$$CLASSIC_COMPANION()).orElse(() -> .MODULE$.apply(() -> MODULE$.org$apache$spark$sql$SparkSession$$CONNECT_COMPANION())).getOrElse(() -> {
         throw new IllegalStateException("Cannot find a SparkSession implementation on the Classpath.");
      });
   }

   private SparkSessionCompanion lookupCompanion(final String name) {
      Class cls = org.apache.spark.util.SparkClassUtils..MODULE$.classForName(name, org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$2(), org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$3());
      JavaUniverse.JavaMirror mirror = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());
      Symbols.ModuleSymbolApi module = mirror.classSymbol(cls).companion().asModule();
      return (SparkSessionCompanion)mirror.reflectModule(module).instance();
   }

   public SparkSession.Builder builder() {
      return new SparkSession.Builder();
   }

   public void setActiveSession(final SparkSession session) {
      super.setActiveSession(session);
   }

   public void setDefaultSession(final SparkSession session) {
      super.setDefaultSession(session);
   }

   public Option getActiveSession() {
      return super.getActiveSession();
   }

   public Option getDefaultSession() {
      return super.getDefaultSession();
   }

   public Option tryCastToImplementation(final SparkSession session) {
      return new Some(session);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkSession$.class);
   }

   private SparkSession$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
