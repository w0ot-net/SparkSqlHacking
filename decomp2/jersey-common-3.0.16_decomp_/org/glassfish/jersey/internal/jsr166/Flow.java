package org.glassfish.jersey.internal.jsr166;

public final class Flow {
   static final int DEFAULT_BUFFER_SIZE = 256;

   private Flow() {
   }

   public static int defaultBufferSize() {
      return 256;
   }

   public interface Processor extends Subscriber, Publisher {
   }

   @FunctionalInterface
   public interface Publisher {
      void subscribe(Subscriber var1);
   }

   public interface Subscriber {
      void onSubscribe(Subscription var1);

      void onNext(Object var1);

      void onError(Throwable var1);

      void onComplete();
   }

   public interface Subscription {
      void request(long var1);

      void cancel();
   }
}
