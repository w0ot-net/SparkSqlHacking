package org.sparkproject.jetty.client.api;

import java.nio.ByteBuffer;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;
import java.util.function.LongConsumer;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.util.Callback;

public interface Response {
   Request getRequest();

   List getListeners(Class var1);

   HttpVersion getVersion();

   int getStatus();

   String getReason();

   HttpFields getHeaders();

   boolean abort(Throwable var1);

   public interface ContentListener extends AsyncContentListener {
      void onContent(Response var1, ByteBuffer var2);

      default void onContent(Response response, ByteBuffer content, Callback callback) {
         try {
            this.onContent(response, content);
            callback.succeeded();
         } catch (Throwable x) {
            callback.failed(x);
         }

      }
   }

   public interface AsyncContentListener extends DemandedContentListener {
      void onContent(Response var1, ByteBuffer var2, Callback var3);

      default void onContent(Response response, LongConsumer demand, ByteBuffer content, Callback callback) {
         Runnable var10003 = () -> {
            callback.succeeded();
            demand.accept(1L);
         };
         Objects.requireNonNull(callback);
         this.onContent(response, content, Callback.from(var10003, callback::failed));
      }
   }

   public interface DemandedContentListener extends ResponseListener {
      default void onBeforeContent(Response response, LongConsumer demand) {
         demand.accept(1L);
      }

      void onContent(Response var1, LongConsumer var2, ByteBuffer var3, Callback var4);
   }

   public interface Listener extends BeginListener, HeaderListener, HeadersListener, ContentListener, SuccessListener, FailureListener, CompleteListener {
      default void onBegin(Response response) {
      }

      default boolean onHeader(Response response, HttpField field) {
         return true;
      }

      default void onHeaders(Response response) {
      }

      default void onContent(Response response, ByteBuffer content) {
      }

      default void onSuccess(Response response) {
      }

      default void onFailure(Response response, Throwable failure) {
      }

      default void onComplete(Result result) {
      }

      public static class Adapter implements Listener {
      }
   }

   public interface BeginListener extends ResponseListener {
      void onBegin(Response var1);
   }

   public interface CompleteListener extends ResponseListener {
      void onComplete(Result var1);
   }

   public interface FailureListener extends ResponseListener {
      void onFailure(Response var1, Throwable var2);
   }

   public interface HeaderListener extends ResponseListener {
      boolean onHeader(Response var1, HttpField var2);
   }

   public interface HeadersListener extends ResponseListener {
      void onHeaders(Response var1);
   }

   public interface ResponseListener extends EventListener {
   }

   public interface SuccessListener extends ResponseListener {
      void onSuccess(Response var1);
   }
}
