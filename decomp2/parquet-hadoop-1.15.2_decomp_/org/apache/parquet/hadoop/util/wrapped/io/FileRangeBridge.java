package org.apache.parquet.hadoop.util.wrapped.io;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import org.apache.parquet.io.ParquetFileRange;
import org.apache.parquet.util.DynMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class FileRangeBridge {
   private static final Logger LOG = LoggerFactory.getLogger(FileRangeBridge.class);
   public static final String CLASSNAME = "org.apache.hadoop.fs.FileRange";
   private static final FileRangeBridge INSTANCE = new FileRangeBridge();
   private final boolean available;
   private final Class fileRangeInterface;
   private final DynMethods.UnboundMethod _getData;
   private final DynMethods.UnboundMethod _setData;
   private final DynMethods.UnboundMethod _getLength;
   private final DynMethods.UnboundMethod _getOffset;
   private final DynMethods.UnboundMethod _getReference;
   private final DynMethods.UnboundMethod createFileRange;

   FileRangeBridge() {
      Class<?> loadedClass;
      try {
         loadedClass = this.getClass().getClassLoader().loadClass("org.apache.hadoop.fs.FileRange");
      } catch (ReflectiveOperationException e) {
         LOG.debug("No {}", "org.apache.hadoop.fs.FileRange", e);
         loadedClass = null;
      }

      this.fileRangeInterface = loadedClass;
      this._getOffset = BindingUtils.loadInvocation(loadedClass, Long.TYPE, "getOffset");
      this._getLength = BindingUtils.loadInvocation(loadedClass, Integer.TYPE, "getLength");
      this._getData = BindingUtils.loadInvocation(loadedClass, (Class)null, "getData");
      this._setData = BindingUtils.loadInvocation(loadedClass, Void.TYPE, "setData", CompletableFuture.class);
      this._getReference = BindingUtils.loadInvocation(loadedClass, Object.class, "getReference");
      this.createFileRange = BindingUtils.loadInvocation(this.fileRangeInterface, Object.class, "createFileRange", Long.TYPE, Integer.TYPE, Object.class);
      this.available = loadedClass != null && BindingUtils.implemented(this.createFileRange, this._getOffset, this._getLength, this._getData, this._setData, this._getReference);
      LOG.debug("FileRangeBridge availability: {}", this.available);
   }

   public boolean available() {
      return this.available;
   }

   private void checkAvailable() {
      if (!this.available()) {
         throw new UnsupportedOperationException("Interface org.apache.hadoop.fs.FileRange not found");
      }
   }

   public Class getFileRangeInterface() {
      return this.fileRangeInterface;
   }

   public WrappedFileRange createFileRange(long offset, int length, Object reference) {
      this.checkAvailable();
      return new WrappedFileRange(this.createFileRange.invoke((Object)null, new Object[]{offset, length, reference}));
   }

   public WrappedFileRange toFileRange(ParquetFileRange in) {
      return this.createFileRange(in.getOffset(), in.getLength(), in);
   }

   public String toString() {
      return "FileRangeBridge{available=" + this.available + ", fileRangeInterface=" + this.fileRangeInterface + ", _getOffset=" + this._getOffset + ", _getLength=" + this._getLength + ", _getData=" + this._getData + ", _setData=" + this._setData + ", _getReference=" + this._getReference + ", createFileRange=" + this.createFileRange + '}';
   }

   public static FileRangeBridge instance() {
      return INSTANCE;
   }

   public static boolean bridgeAvailable() {
      return instance().available();
   }

   class WrappedFileRange {
      private final Object fileRange;

      WrappedFileRange(Object fileRange) {
         this.fileRange = Objects.requireNonNull(fileRange);
      }

      public long getOffset() {
         return (Long)FileRangeBridge.this._getOffset.invoke(this.fileRange, new Object[0]);
      }

      public int getLength() {
         return (Integer)FileRangeBridge.this._getLength.invoke(this.fileRange, new Object[0]);
      }

      public CompletableFuture getData() {
         return (CompletableFuture)FileRangeBridge.this._getData.invoke(this.fileRange, new Object[0]);
      }

      public void setData(CompletableFuture data) {
         FileRangeBridge.this._setData.invoke(this.fileRange, new Object[]{data});
      }

      public Object getReference() {
         return FileRangeBridge.this._getReference.invoke(this.fileRange, new Object[0]);
      }

      public Object getFileRange() {
         return this.fileRange;
      }

      public String toString() {
         return "WrappedFileRange{fileRange=" + this.fileRange + '}';
      }
   }
}
