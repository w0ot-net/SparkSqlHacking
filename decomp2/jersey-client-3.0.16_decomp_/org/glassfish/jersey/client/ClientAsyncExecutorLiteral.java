package org.glassfish.jersey.client;

import org.glassfish.jersey.internal.inject.AnnotationLiteral;

public final class ClientAsyncExecutorLiteral extends AnnotationLiteral implements ClientAsyncExecutor {
   public static final ClientAsyncExecutor INSTANCE = new ClientAsyncExecutorLiteral();

   private ClientAsyncExecutorLiteral() {
   }
}
