package org.apache.ivy.plugins.repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.event.EventListenerList;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.settings.TimeoutConstraint;

public abstract class AbstractRepository implements Repository {
   private EventListenerList listeners;
   private String name;
   private TransferEvent evt;
   private final TimeoutConstraint timeoutConstraint;

   public AbstractRepository() {
      this((TimeoutConstraint)null);
   }

   protected AbstractRepository(TimeoutConstraint timeoutConstraint) {
      this.listeners = new EventListenerList();
      this.timeoutConstraint = timeoutConstraint;
   }

   public void addTransferListener(TransferListener listener) {
      this.listeners.add(TransferListener.class, listener);
   }

   public void removeTransferListener(TransferListener listener) {
      this.listeners.remove(TransferListener.class, listener);
   }

   public boolean hasTransferListener(TransferListener listener) {
      return Arrays.asList(this.listeners.getListeners(TransferListener.class)).contains(listener);
   }

   protected void fireTransferInitiated(Resource res, int requestType) {
      this.evt = new TransferEvent(this, res, 0, requestType);
      this.fireTransferEvent(this.evt);
   }

   protected void fireTransferStarted() {
      this.evt.setEventType(1);
      this.fireTransferEvent(this.evt);
   }

   protected void fireTransferStarted(long totalLength) {
      this.evt.setEventType(1);
      this.evt.setTotalLength(totalLength);
      this.evt.setTotalLengthSet(true);
      this.fireTransferEvent(this.evt);
   }

   protected void fireTransferProgress(long length) {
      this.evt.setEventType(3);
      this.evt.setLength(length);
      if (!this.evt.isTotalLengthSet()) {
         this.evt.setTotalLength(this.evt.getTotalLength() + length);
      }

      this.fireTransferEvent(this.evt);
   }

   protected void fireTransferCompleted() {
      this.evt.setEventType(2);
      if (this.evt.getTotalLength() > 0L && !this.evt.isTotalLengthSet()) {
         this.evt.setTotalLengthSet(true);
      }

      this.fireTransferEvent(this.evt);
   }

   protected void fireTransferCompleted(long totalLength) {
      this.evt.setEventType(2);
      this.evt.setTotalLength(totalLength);
      this.evt.setTotalLengthSet(true);
      this.fireTransferEvent(this.evt);
   }

   protected void fireTransferError() {
      this.evt.setEventType(4);
      this.fireTransferEvent(this.evt);
   }

   protected void fireTransferError(Exception ex) {
      this.evt.setEventType(4);
      this.evt.setException(ex);
      this.fireTransferEvent(this.evt);
   }

   protected void fireTransferEvent(TransferEvent evt) {
      Object[] listeners = this.listeners.getListenerList();

      for(int i = listeners.length - 2; i >= 0; i -= 2) {
         if (listeners[i] == TransferListener.class) {
            ((TransferListener)listeners[i + 1]).transferProgress(evt);
         }
      }

   }

   public String getFileSeparator() {
      return "/";
   }

   public String standardize(String source) {
      return source.replace('\\', '/');
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public TimeoutConstraint getTimeoutConstraint() {
      return this.timeoutConstraint;
   }

   public String toString() {
      return this.getName();
   }

   public void put(Artifact artifact, File source, String destination, boolean overwrite) throws IOException {
      this.put(source, destination, overwrite);
   }

   protected void put(File source, String destination, boolean overwrite) throws IOException {
      throw new UnsupportedOperationException("put in not supported by " + this.getName());
   }
}
