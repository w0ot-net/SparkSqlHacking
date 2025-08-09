package org.glassfish.jersey.server.internal.process;

import java.util.function.Function;
import org.glassfish.jersey.process.internal.ChainableStage;
import org.glassfish.jersey.process.internal.Stage;
import org.glassfish.jersey.process.internal.Stages;
import org.glassfish.jersey.server.ContainerResponse;

class DefaultRespondingContext implements RespondingContext {
   private Stage rootStage;

   public void push(Function responseTransformation) {
      this.rootStage = this.rootStage == null ? new Stages.LinkedStage(responseTransformation) : new Stages.LinkedStage(responseTransformation, this.rootStage);
   }

   public void push(ChainableStage stage) {
      if (this.rootStage != null) {
         stage.setDefaultNext(this.rootStage);
      }

      this.rootStage = stage;
   }

   public Stage createRespondingRoot() {
      return this.rootStage;
   }
}
