package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOConsumer;

public class ObservableInputStream extends ProxyInputStream {
   private final List observers;

   ObservableInputStream(AbstractBuilder builder) throws IOException {
      super((ProxyInputStream.AbstractBuilder)builder);
      this.observers = builder.observers;
   }

   public ObservableInputStream(InputStream inputStream) {
      this(inputStream, (List)(new ArrayList()));
   }

   private ObservableInputStream(InputStream inputStream, List observers) {
      super(inputStream);
      this.observers = observers;
   }

   public ObservableInputStream(InputStream inputStream, Observer... observers) {
      this(inputStream, Arrays.asList(observers));
   }

   public void add(Observer observer) {
      this.observers.add(observer);
   }

   public void close() throws IOException {
      IOException ioe = null;

      try {
         super.close();
      } catch (IOException e) {
         ioe = e;
      }

      if (ioe == null) {
         this.noteClosed();
      } else {
         this.noteError(ioe);
      }

   }

   public void consume() throws IOException {
      IOUtils.consume((InputStream)this);
   }

   private void forEachObserver(IOConsumer action) throws IOException {
      IOConsumer.forAll(action, (Iterable)this.observers);
   }

   public List getObservers() {
      return new ArrayList(this.observers);
   }

   protected void noteClosed() throws IOException {
      this.forEachObserver(Observer::closed);
   }

   protected void noteDataByte(int value) throws IOException {
      this.forEachObserver((observer) -> observer.data(value));
   }

   protected void noteDataBytes(byte[] buffer, int offset, int length) throws IOException {
      this.forEachObserver((observer) -> observer.data(buffer, offset, length));
   }

   protected void noteError(IOException exception) throws IOException {
      this.forEachObserver((observer) -> observer.error(exception));
   }

   protected void noteFinished() throws IOException {
      this.forEachObserver(Observer::finished);
   }

   private void notify(byte[] buffer, int offset, int result, IOException ioe) throws IOException {
      if (ioe != null) {
         this.noteError(ioe);
         throw ioe;
      } else {
         if (result == -1) {
            this.noteFinished();
         } else if (result > 0) {
            this.noteDataBytes(buffer, offset, result);
         }

      }
   }

   public int read() throws IOException {
      int result = 0;
      IOException ioe = null;

      try {
         result = super.read();
      } catch (IOException ex) {
         ioe = ex;
      }

      if (ioe != null) {
         this.noteError(ioe);
         throw ioe;
      } else {
         if (result == -1) {
            this.noteFinished();
         } else {
            this.noteDataByte(result);
         }

         return result;
      }
   }

   public int read(byte[] buffer) throws IOException {
      int result = 0;
      IOException ioe = null;

      try {
         result = super.read(buffer);
      } catch (IOException ex) {
         ioe = ex;
      }

      this.notify(buffer, 0, result, ioe);
      return result;
   }

   public int read(byte[] buffer, int offset, int length) throws IOException {
      int result = 0;
      IOException ioe = null;

      try {
         result = super.read(buffer, offset, length);
      } catch (IOException ex) {
         ioe = ex;
      }

      this.notify(buffer, offset, result, ioe);
      return result;
   }

   public void remove(Observer observer) {
      this.observers.remove(observer);
   }

   public void removeAllObservers() {
      this.observers.clear();
   }

   public abstract static class AbstractBuilder extends ProxyInputStream.AbstractBuilder {
      private List observers;

      public void setObservers(List observers) {
         this.observers = observers;
      }
   }

   public static class Builder extends AbstractBuilder {
      public ObservableInputStream get() throws IOException {
         return new ObservableInputStream(this);
      }
   }

   public abstract static class Observer {
      public void closed() throws IOException {
      }

      public void data(byte[] buffer, int offset, int length) throws IOException {
      }

      public void data(int value) throws IOException {
      }

      public void error(IOException exception) throws IOException {
         throw exception;
      }

      public void finished() throws IOException {
      }
   }
}
