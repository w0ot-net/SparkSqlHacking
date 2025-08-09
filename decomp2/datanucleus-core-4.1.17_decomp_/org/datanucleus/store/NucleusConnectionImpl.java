package org.datanucleus.store;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class NucleusConnectionImpl implements NucleusConnection {
   private final Object nativeConnection;
   private final Runnable onClose;
   private boolean isAvailable = true;

   public NucleusConnectionImpl(Object conn, Runnable onClose) {
      this.nativeConnection = conn;
      this.onClose = onClose;
   }

   public void close() {
      if (!this.isAvailable) {
         throw new NucleusUserException(Localiser.msg("046001"));
      } else {
         this.isAvailable = false;
         this.onClose.run();
      }
   }

   public boolean isAvailable() {
      return this.isAvailable;
   }

   public Object getNativeConnection() {
      return this.nativeConnection;
   }
}
