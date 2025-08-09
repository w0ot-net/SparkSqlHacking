package org.glassfish.jersey.server.internal.scanning;

import java.io.InputStream;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.server.ResourceFinder;
import org.glassfish.jersey.server.internal.AbstractResourceFinderAdapter;
import org.glassfish.jersey.server.internal.LocalizationMessages;

public final class CompositeResourceFinder extends AbstractResourceFinderAdapter {
   private static final Logger LOGGER = Logger.getLogger(CompositeResourceFinder.class.getName());
   private final Deque stack = new LinkedList();
   private ResourceFinder current = null;

   public boolean hasNext() {
      if (this.current == null) {
         if (this.stack.isEmpty()) {
            return false;
         }

         this.current = (ResourceFinder)this.stack.pop();
      }

      if (this.current.hasNext()) {
         return true;
      } else if (!this.stack.isEmpty()) {
         this.current = (ResourceFinder)this.stack.pop();
         return this.hasNext();
      } else {
         return false;
      }
   }

   public String next() {
      if (this.hasNext()) {
         return (String)this.current.next();
      } else {
         throw new NoSuchElementException();
      }
   }

   public InputStream open() {
      return this.current.open();
   }

   public void close() {
      if (this.current != null) {
         this.stack.addFirst(this.current);
         this.current = null;
      }

      for(ResourceFinder finder : this.stack) {
         try {
            finder.close();
         } catch (RuntimeException e) {
            LOGGER.log(Level.CONFIG, LocalizationMessages.ERROR_CLOSING_FINDER(finder.getClass()), e);
         }
      }

      this.stack.clear();
   }

   public void reset() {
      throw new UnsupportedOperationException();
   }

   public void push(ResourceFinder iterator) {
      this.stack.push(iterator);
   }
}
