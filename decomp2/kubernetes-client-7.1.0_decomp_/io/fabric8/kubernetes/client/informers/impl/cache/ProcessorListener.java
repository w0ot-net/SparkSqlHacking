package io.fabric8.kubernetes.client.informers.impl.cache;

import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class ProcessorListener {
   private long resyncPeriodInMillis;
   private ZonedDateTime nextResync;
   private ResourceEventHandler handler;

   public ProcessorListener(ResourceEventHandler handler, long resyncPeriodInMillis) {
      this.resyncPeriodInMillis = resyncPeriodInMillis;
      this.handler = handler;
      this.determineNextResync(ZonedDateTime.now());
   }

   public void add(Notification notification) {
      notification.handle(this.handler);
   }

   public void determineNextResync(ZonedDateTime now) {
      this.nextResync = now.plus(this.resyncPeriodInMillis, ChronoUnit.MILLIS);
   }

   public boolean isReSync() {
      return this.resyncPeriodInMillis != 0L;
   }

   public boolean shouldResync(ZonedDateTime now) {
      return this.resyncPeriodInMillis != 0L && (now.isAfter(this.nextResync) || now.equals(this.nextResync));
   }

   public long getResyncPeriodInMillis() {
      return this.resyncPeriodInMillis;
   }

   public ResourceEventHandler getHandler() {
      return this.handler;
   }

   public abstract static class Notification {
      private final Object oldObject;
      private final Object newObject;

      public Notification(Object oldObject, Object newObject) {
         this.oldObject = oldObject;
         this.newObject = newObject;
      }

      public Object getOldObject() {
         return this.oldObject;
      }

      public Object getNewObject() {
         return this.newObject;
      }

      public abstract void handle(ResourceEventHandler var1);
   }

   public static final class UpdateNotification extends Notification {
      public UpdateNotification(Object oldObject, Object newObject) {
         super(oldObject, newObject);
      }

      public void handle(ResourceEventHandler resourceEventHandler) {
         resourceEventHandler.onUpdate(this.getOldObject(), this.getNewObject());
      }
   }

   public static final class AddNotification extends Notification {
      public AddNotification(Object newObject) {
         super((Object)null, newObject);
      }

      public void handle(ResourceEventHandler resourceEventHandler) {
         resourceEventHandler.onAdd(this.getNewObject());
      }
   }

   public static final class DeleteNotification extends Notification {
      private boolean unknownFinalState;

      public DeleteNotification(Object oldObject) {
         this(oldObject, false);
      }

      public DeleteNotification(Object oldObject, boolean unknownFinalState) {
         super(oldObject, (Object)null);
         this.unknownFinalState = unknownFinalState;
      }

      public void handle(ResourceEventHandler resourceEventHandler) {
         resourceEventHandler.onDelete(this.getOldObject(), this.unknownFinalState);
      }
   }
}
