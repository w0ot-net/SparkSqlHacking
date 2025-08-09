package org.glassfish.jersey.client;

import org.glassfish.jersey.internal.inject.AnnotationLiteral;

public final class ClientBackgroundSchedulerLiteral extends AnnotationLiteral implements ClientBackgroundScheduler {
   public static final ClientBackgroundScheduler INSTANCE = new ClientBackgroundSchedulerLiteral();

   private ClientBackgroundSchedulerLiteral() {
   }
}
