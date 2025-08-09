package org.glassfish.jersey.server.internal.process;

import java.util.function.Function;
import org.glassfish.jersey.process.internal.ChainableStage;
import org.glassfish.jersey.process.internal.Stage;

public interface RespondingContext {
   void push(Function var1);

   void push(ChainableStage var1);

   Stage createRespondingRoot();
}
