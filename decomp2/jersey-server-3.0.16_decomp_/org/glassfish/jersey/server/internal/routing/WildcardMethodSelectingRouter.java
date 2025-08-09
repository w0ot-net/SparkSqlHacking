package org.glassfish.jersey.server.internal.routing;

import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.glassfish.jersey.internal.routing.CombinedMediaType;
import org.glassfish.jersey.message.MessageBodyWorkers;

final class WildcardMethodSelectingRouter extends AbstractMethodSelectingRouter implements Router {
   WildcardMethodSelectingRouter(MessageBodyWorkers workers, List methodRoutings) {
      super(workers, methodRoutings);
   }

   protected AbstractMethodSelectingRouter.ConsumesProducesAcceptor createConsumesProducesAcceptor(CombinedMediaType.EffectiveMediaType consumes, CombinedMediaType.EffectiveMediaType produces, MethodRouting methodRouting) {
      return new ConsumesProducesAcceptor(consumes, produces, methodRouting);
   }

   private static class ConsumesProducesAcceptor extends AbstractMethodSelectingRouter.ConsumesProducesAcceptor {
      private ConsumesProducesAcceptor(CombinedMediaType.EffectiveMediaType consumes, CombinedMediaType.EffectiveMediaType produces, MethodRouting methodRouting) {
         super(consumes, produces, methodRouting);
      }

      boolean isConsumable(MediaType contentType) {
         return contentType == null || this.consumes.getMediaType().isCompatible(contentType);
      }
   }
}
