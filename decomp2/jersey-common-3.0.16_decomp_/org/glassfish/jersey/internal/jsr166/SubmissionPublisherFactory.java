package org.glassfish.jersey.internal.jsr166;

import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

public class SubmissionPublisherFactory {
   public static SubmittableFlowPublisher createSubmissionPublisher() {
      return new SubmissionPublisher();
   }

   public static SubmittableFlowPublisher createSubmissionPublisher(Executor executor, int maxBufferCapacity) {
      return new SubmissionPublisher(executor, maxBufferCapacity);
   }

   public static SubmittableFlowPublisher createSubmissionPublisher(Executor executor, int maxBufferCapacity, BiConsumer handler) {
      return new SubmissionPublisher(executor, maxBufferCapacity, handler);
   }
}
