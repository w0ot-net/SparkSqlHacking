package org.apache.spark.graphx;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class EdgeDirection$ implements Serializable {
   public static final EdgeDirection$ MODULE$ = new EdgeDirection$();
   private static final EdgeDirection In = new EdgeDirection("In");
   private static final EdgeDirection Out = new EdgeDirection("Out");
   private static final EdgeDirection Either = new EdgeDirection("Either");
   private static final EdgeDirection Both = new EdgeDirection("Both");

   public final EdgeDirection In() {
      return In;
   }

   public final EdgeDirection Out() {
      return Out;
   }

   public final EdgeDirection Either() {
      return Either;
   }

   public final EdgeDirection Both() {
      return Both;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EdgeDirection$.class);
   }

   private EdgeDirection$() {
   }
}
