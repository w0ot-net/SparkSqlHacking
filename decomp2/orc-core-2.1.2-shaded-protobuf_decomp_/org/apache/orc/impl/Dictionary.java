package org.apache.orc.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.hadoop.io.Text;

public interface Dictionary {
   int INITIAL_DICTIONARY_SIZE = 4096;

   void visit(Visitor var1) throws IOException;

   void clear();

   void getText(Text var1, int var2);

   ByteBuffer getText(int var1);

   int writeTo(OutputStream var1, int var2) throws IOException;

   int add(byte[] var1, int var2, int var3);

   int size();

   long getSizeInBytes();

   public static enum IMPL {
      RBTREE,
      HASH;

      // $FF: synthetic method
      private static IMPL[] $values() {
         return new IMPL[]{RBTREE, HASH};
      }
   }

   public interface Visitor {
      void visit(VisitorContext var1) throws IOException;
   }

   public interface VisitorContext {
      int getOriginalPosition();

      void writeBytes(OutputStream var1) throws IOException;

      Text getText();

      int getLength();
   }
}
