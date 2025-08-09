package org.apache.curator.framework.state;

public enum ConnectionState {
   CONNECTED {
      public boolean isConnected() {
         return true;
      }
   },
   SUSPENDED {
      public boolean isConnected() {
         return false;
      }
   },
   RECONNECTED {
      public boolean isConnected() {
         return true;
      }
   },
   LOST {
      public boolean isConnected() {
         return false;
      }
   },
   READ_ONLY {
      public boolean isConnected() {
         return true;
      }
   };

   private ConnectionState() {
   }

   public abstract boolean isConnected();
}
