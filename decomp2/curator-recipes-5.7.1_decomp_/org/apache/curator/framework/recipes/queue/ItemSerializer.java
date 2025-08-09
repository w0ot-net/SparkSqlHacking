package org.apache.curator.framework.recipes.queue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.curator.shaded.com.google.common.collect.Lists;

class ItemSerializer {
   private static final int VERSION = 65537;
   private static final byte ITEM_OPCODE = 1;
   private static final byte EOF_OPCODE = 2;
   private static final int INITIAL_BUFFER_SIZE = 4096;

   static MultiItem deserialize(byte[] bytes, QueueSerializer serializer) throws Exception {
      DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
      int version = in.readInt();
      if (version != 65537) {
         throw new IOException(String.format("Incorrect version. Expected %d - Found: %d", 65537, version));
      } else {
         List<T> items = Lists.newArrayList();

         while(true) {
            byte opcode = in.readByte();
            if (opcode == 2) {
               final Iterator<T> iterator = items.iterator();
               return new MultiItem() {
                  public Object nextItem() {
                     return iterator.hasNext() ? iterator.next() : null;
                  }
               };
            }

            if (opcode != 1) {
               throw new IOException(String.format("Incorrect opcode. Expected %d - Found: %d", 1, opcode));
            }

            int size = in.readInt();
            if (size < 0) {
               throw new IOException(String.format("Bad size: %d", size));
            }

            byte[] itemBytes = new byte[size];
            if (size > 0) {
               in.readFully(itemBytes);
            }

            items.add(serializer.deserialize(itemBytes));
         }
      }
   }

   static byte[] serialize(MultiItem items, QueueSerializer serializer) throws Exception {
      ByteArrayOutputStream bytes = new ByteArrayOutputStream(4096);
      DataOutputStream out = new DataOutputStream(bytes);
      out.writeInt(65537);

      while(true) {
         T item = (T)items.nextItem();
         if (item == null) {
            out.writeByte(2);
            out.close();
            return bytes.toByteArray();
         }

         byte[] itemBytes = serializer.serialize(item);
         out.writeByte(1);
         out.writeInt(itemBytes.length);
         if (itemBytes.length > 0) {
            out.write(itemBytes);
         }
      }
   }

   private ItemSerializer() {
   }
}
