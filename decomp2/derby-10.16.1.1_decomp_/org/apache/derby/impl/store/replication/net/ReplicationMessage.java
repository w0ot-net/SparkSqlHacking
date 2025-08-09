package org.apache.derby.impl.store.replication.net;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ReplicationMessage implements Externalizable {
   public static final long serialVersionUID = 1L;
   private Object message;
   private int type;
   public static final int TYPE_INITIATE_VERSION = 0;
   public static final int TYPE_INITIATE_INSTANT = 1;
   public static final int TYPE_LOG = 10;
   public static final int TYPE_ACK = 11;
   public static final int TYPE_ERROR = 12;
   public static final int TYPE_PING = 13;
   public static final int TYPE_PONG = 14;
   public static final int TYPE_STOP = 20;
   public static final int TYPE_FAILOVER = 21;

   public ReplicationMessage() {
   }

   public ReplicationMessage(int var1, Object var2) {
      this.type = var1;
      this.message = var2;
   }

   public Object getMessage() {
      return this.message;
   }

   public int getType() {
      return this.type;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      switch ((int)var1.readLong()) {
         case 1:
            this.type = var1.readInt();
            this.message = var1.readObject();
         default:
      }
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeLong(1L);
      var1.writeInt(this.type);
      var1.writeObject(this.message);
   }
}
