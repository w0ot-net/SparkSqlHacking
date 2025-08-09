package org.apache.spark.network.protocol;

import org.apache.spark.network.buffer.ManagedBuffer;
import org.sparkproject.guava.base.Objects;

public abstract class AbstractMessage implements Message {
   private final ManagedBuffer body;
   private final boolean isBodyInFrame;

   protected AbstractMessage() {
      this((ManagedBuffer)null, false);
   }

   protected AbstractMessage(ManagedBuffer body, boolean isBodyInFrame) {
      this.body = body;
      this.isBodyInFrame = isBodyInFrame;
   }

   public ManagedBuffer body() {
      return this.body;
   }

   public boolean isBodyInFrame() {
      return this.isBodyInFrame;
   }

   protected boolean equals(AbstractMessage other) {
      return this.isBodyInFrame == other.isBodyInFrame && Objects.equal(this.body, other.body);
   }
}
