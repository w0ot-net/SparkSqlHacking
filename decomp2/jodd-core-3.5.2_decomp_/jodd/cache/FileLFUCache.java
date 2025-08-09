package jodd.cache;

import java.io.File;
import java.io.IOException;
import jodd.io.FileUtil;

public class FileLFUCache {
   protected final LFUCache cache;
   protected final int maxSize;
   protected final int maxFileSize;
   protected int usedSize;

   public FileLFUCache(int maxSize) {
      this(maxSize, maxSize / 2, 0L);
   }

   public FileLFUCache(int maxSize, int maxFileSize) {
      this(maxSize, maxFileSize, 0L);
   }

   public FileLFUCache(int maxSize, int maxFileSize, long timeout) {
      this.cache = new LFUCache(0, timeout) {
         public boolean isFull() {
            return FileLFUCache.this.usedSize > FileLFUCache.this.maxSize;
         }

         protected void onRemove(File key, byte[] cachedObject) {
            FileLFUCache var10000 = FileLFUCache.this;
            var10000.usedSize -= cachedObject.length;
         }
      };
      this.maxSize = maxSize;
      this.maxFileSize = maxFileSize;
   }

   public int getMaxSize() {
      return this.maxSize;
   }

   public int getUsedSize() {
      return this.usedSize;
   }

   public int getMaxFileSize() {
      return this.maxFileSize;
   }

   public int getCachedFilesCount() {
      return this.cache.size();
   }

   public long getCacheTimeout() {
      return this.cache.getCacheTimeout();
   }

   public void clear() {
      this.cache.clear();
      this.usedSize = 0;
   }

   public byte[] getFileBytes(String fileName) throws IOException {
      return this.getFileBytes(new File(fileName));
   }

   public byte[] getFileBytes(File file) throws IOException {
      byte[] bytes = (byte[])this.cache.get(file);
      if (bytes != null) {
         return bytes;
      } else {
         bytes = FileUtil.readBytes(file);
         if (this.maxFileSize != 0 && file.length() > (long)this.maxFileSize) {
            return bytes;
         } else {
            this.usedSize += bytes.length;
            this.cache.put(file, bytes);
            return bytes;
         }
      }
   }
}
