package org.glassfish.jersey.server;

import org.glassfish.jersey.internal.inject.AnnotationLiteral;

public final class BackgroundSchedulerLiteral extends AnnotationLiteral implements BackgroundScheduler {
   public static final BackgroundScheduler INSTANCE = new BackgroundSchedulerLiteral();

   private BackgroundSchedulerLiteral() {
   }
}
