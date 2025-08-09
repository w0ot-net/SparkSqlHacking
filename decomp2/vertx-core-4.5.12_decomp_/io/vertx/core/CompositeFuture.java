package io.vertx.core;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.impl.future.CompositeFutureImpl;
import java.util.ArrayList;
import java.util.List;

public interface CompositeFuture extends Future {
   /** @deprecated */
   @Deprecated
   static CompositeFuture all(Future f1, Future f2) {
      return CompositeFutureImpl.all(f1, f2);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture all(Future f1, Future f2, Future f3) {
      return CompositeFutureImpl.all(f1, f2, f3);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture all(Future f1, Future f2, Future f3, Future f4) {
      return CompositeFutureImpl.all(f1, f2, f3, f4);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture all(Future f1, Future f2, Future f3, Future f4, Future f5) {
      return CompositeFutureImpl.all(f1, f2, f3, f4, f5);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture all(Future f1, Future f2, Future f3, Future f4, Future f5, Future f6) {
      return CompositeFutureImpl.all(f1, f2, f3, f4, f5, f6);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture all(List futures) {
      return CompositeFutureImpl.all((Future[])futures.toArray(new Future[0]));
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture any(Future f1, Future f2) {
      return CompositeFutureImpl.any(f1, f2);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture any(Future f1, Future f2, Future f3) {
      return CompositeFutureImpl.any(f1, f2, f3);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture any(Future f1, Future f2, Future f3, Future f4) {
      return CompositeFutureImpl.any(f1, f2, f3, f4);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture any(Future f1, Future f2, Future f3, Future f4, Future f5) {
      return CompositeFutureImpl.any(f1, f2, f3, f4, f5);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture any(Future f1, Future f2, Future f3, Future f4, Future f5, Future f6) {
      return CompositeFutureImpl.any(f1, f2, f3, f4, f5, f6);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture any(List futures) {
      return CompositeFutureImpl.any((Future[])futures.toArray(new Future[0]));
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture join(Future f1, Future f2) {
      return CompositeFutureImpl.join(f1, f2);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture join(Future f1, Future f2, Future f3) {
      return CompositeFutureImpl.join(f1, f2, f3);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture join(Future f1, Future f2, Future f3, Future f4) {
      return CompositeFutureImpl.join(f1, f2, f3, f4);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture join(Future f1, Future f2, Future f3, Future f4, Future f5) {
      return CompositeFutureImpl.join(f1, f2, f3, f4, f5);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture join(Future f1, Future f2, Future f3, Future f4, Future f5, Future f6) {
      return CompositeFutureImpl.join(f1, f2, f3, f4, f5, f6);
   }

   /** @deprecated */
   @Deprecated
   static CompositeFuture join(List futures) {
      return CompositeFutureImpl.join((Future[])futures.toArray(new Future[0]));
   }

   CompositeFuture onComplete(Handler var1);

   default CompositeFuture onSuccess(Handler handler) {
      Future.super.onSuccess(handler);
      return this;
   }

   default CompositeFuture onFailure(Handler handler) {
      Future.super.onFailure(handler);
      return this;
   }

   Throwable cause(int var1);

   boolean succeeded(int var1);

   boolean failed(int var1);

   boolean isComplete(int var1);

   Object resultAt(int var1);

   int size();

   @GenIgnore
   default List list() {
      int size = this.size();
      ArrayList<T> list = new ArrayList(size);

      for(int index = 0; index < size; ++index) {
         list.add(this.resultAt(index));
      }

      return list;
   }

   @GenIgnore
   default List causes() {
      int size = this.size();
      ArrayList<Throwable> list = new ArrayList(size);

      for(int index = 0; index < size; ++index) {
         list.add(this.cause(index));
      }

      return list;
   }
}
