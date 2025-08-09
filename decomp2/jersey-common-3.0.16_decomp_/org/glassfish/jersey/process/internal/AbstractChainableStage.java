package org.glassfish.jersey.process.internal;

public abstract class AbstractChainableStage implements ChainableStage {
   private Stage nextStage;

   protected AbstractChainableStage() {
      this((Stage)null);
   }

   protected AbstractChainableStage(Stage nextStage) {
      this.nextStage = nextStage;
   }

   public final void setDefaultNext(Stage next) {
      this.nextStage = next;
   }

   public final Stage getDefaultNext() {
      return this.nextStage;
   }
}
