package org.apache.orc.impl.writer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.orc.EncryptionAlgorithm;
import org.apache.orc.EncryptionKey;
import org.apache.orc.impl.HadoopShims;
import org.jetbrains.annotations.NotNull;

public class WriterEncryptionKey implements EncryptionKey {
   private final HadoopShims.KeyMetadata metadata;
   private final List roots = new ArrayList();
   private int id;

   public WriterEncryptionKey(HadoopShims.KeyMetadata key) {
      this.metadata = key;
   }

   public void addRoot(WriterEncryptionVariant root) {
      this.roots.add(root);
   }

   public HadoopShims.KeyMetadata getMetadata() {
      return this.metadata;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getKeyName() {
      return this.metadata.getKeyName();
   }

   public int getKeyVersion() {
      return this.metadata.getVersion();
   }

   public EncryptionAlgorithm getAlgorithm() {
      return this.metadata.getAlgorithm();
   }

   public WriterEncryptionVariant[] getEncryptionRoots() {
      return (WriterEncryptionVariant[])this.roots.toArray(new WriterEncryptionVariant[0]);
   }

   public boolean isAvailable() {
      return true;
   }

   public int getId() {
      return this.id;
   }

   public void sortRoots() {
      Collections.sort(this.roots);
   }

   public int hashCode() {
      return this.id;
   }

   public boolean equals(Object other) {
      if (other != null && this.getClass() == other.getClass()) {
         return this.compareTo((EncryptionKey)other) == 0;
      } else {
         return false;
      }
   }

   public int compareTo(@NotNull EncryptionKey other) {
      int result = this.getKeyName().compareTo(other.getKeyName());
      if (result == 0) {
         result = Integer.compare(this.getKeyVersion(), other.getKeyVersion());
      }

      return result;
   }

   public String toString() {
      return this.metadata.toString();
   }
}
