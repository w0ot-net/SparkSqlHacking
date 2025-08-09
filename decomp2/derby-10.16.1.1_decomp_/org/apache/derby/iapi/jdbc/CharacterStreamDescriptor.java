package org.apache.derby.iapi.jdbc;

import java.io.InputStream;
import org.apache.derby.iapi.types.PositionedStream;

public class CharacterStreamDescriptor {
   public static final long BEFORE_FIRST = 0L;
   private final long dataOffset;
   private final long curBytePos;
   private final long curCharPos;
   private final long byteLength;
   private final long charLength;
   private final long maxCharLength;
   private final boolean bufferable;
   private final boolean positionAware;
   private final InputStream stream;

   private CharacterStreamDescriptor(Builder var1) {
      this.bufferable = var1.bufferable;
      this.positionAware = var1.positionAware;
      this.dataOffset = var1.dataOffset;
      this.curBytePos = var1.curBytePos;
      this.curCharPos = var1.curCharPos;
      this.byteLength = var1.byteLength;
      this.charLength = var1.charLength;
      this.maxCharLength = var1.maxCharLength;
      this.stream = var1.stream;
   }

   public boolean isBufferable() {
      return this.bufferable;
   }

   public boolean isPositionAware() {
      return this.positionAware;
   }

   public long getByteLength() {
      return this.byteLength;
   }

   public long getCharLength() {
      return this.charLength;
   }

   public long getCurBytePos() {
      return this.curBytePos;
   }

   public long getCurCharPos() {
      return this.curCharPos;
   }

   public long getDataOffset() {
      return this.dataOffset;
   }

   public long getMaxCharLength() {
      return this.maxCharLength;
   }

   public InputStream getStream() {
      return this.stream;
   }

   public PositionedStream getPositionedStream() {
      if (!this.positionAware) {
         throw new IllegalStateException("stream is not position aware: " + this.stream.getClass().getName());
      } else {
         return (PositionedStream)this.stream;
      }
   }

   public String toString() {
      int var10000 = this.hashCode();
      return "CharacterStreamDescriptor-" + var10000 + "#bufferable=" + this.bufferable + ":positionAware=" + this.positionAware + ":byteLength=" + this.byteLength + ":charLength=" + this.charLength + ":curBytePos=" + this.curBytePos + ":curCharPos=" + this.curCharPos + ":dataOffset=" + this.dataOffset + ":stream=" + this.stream.getClass();
   }

   public static class Builder {
      private static final long DEFAULT_MAX_CHAR_LENGTH = Long.MAX_VALUE;
      private boolean bufferable = false;
      private boolean positionAware = false;
      private long curBytePos = 0L;
      private long curCharPos = 1L;
      private long byteLength = 0L;
      private long charLength = 0L;
      private long dataOffset = 0L;
      private long maxCharLength = Long.MAX_VALUE;
      private InputStream stream;

      public Builder bufferable(boolean var1) {
         this.bufferable = var1;
         return this;
      }

      public Builder positionAware(boolean var1) {
         this.positionAware = var1;
         return this;
      }

      public Builder curBytePos(long var1) {
         this.curBytePos = var1;
         return this;
      }

      public Builder curCharPos(long var1) {
         this.curCharPos = var1;
         return this;
      }

      public Builder byteLength(long var1) {
         this.byteLength = var1;
         return this;
      }

      public Builder copyState(CharacterStreamDescriptor var1) {
         this.bufferable = var1.bufferable;
         this.byteLength = var1.byteLength;
         this.charLength = var1.charLength;
         this.curBytePos = var1.curBytePos;
         this.curCharPos = var1.curCharPos;
         this.dataOffset = var1.dataOffset;
         this.maxCharLength = var1.maxCharLength;
         this.positionAware = var1.positionAware;
         this.stream = var1.stream;
         return this;
      }

      public Builder charLength(long var1) {
         this.charLength = var1;
         return this;
      }

      public Builder dataOffset(long var1) {
         this.dataOffset = var1;
         return this;
      }

      public Builder maxCharLength(long var1) {
         this.maxCharLength = var1;
         return this;
      }

      public Builder stream(InputStream var1) {
         this.stream = var1;
         return this;
      }

      public CharacterStreamDescriptor build() {
         return new CharacterStreamDescriptor(this);
      }

      public String toString() {
         int var10000 = this.hashCode();
         String var1 = "CharacterStreamBuiler@" + var10000 + ":bufferable=" + this.bufferable + ", isPositionAware=" + this.positionAware + ", curBytePos=" + this.curBytePos + ", curCharPos=" + this.curCharPos + ", dataOffset=" + this.dataOffset + ", byteLength=" + this.byteLength + ", charLength=" + this.charLength + ", maxCharLength=" + this.maxCharLength + ", stream=" + this.stream.getClass();
         return var1;
      }
   }
}
