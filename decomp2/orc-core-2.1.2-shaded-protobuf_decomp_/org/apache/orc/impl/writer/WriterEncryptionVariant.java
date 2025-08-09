package org.apache.orc.impl.writer;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import org.apache.orc.EncryptionVariant;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.FileStatistics;
import org.apache.orc.impl.LocalKey;
import org.jetbrains.annotations.NotNull;

public class WriterEncryptionVariant implements EncryptionVariant {
   private int id;
   private final WriterEncryptionKey key;
   private final TypeDescription root;
   private final LocalKey material;
   private final OrcProto.FileStatistics.Builder fileStats = FileStatistics.newBuilder();
   private final List encodings = new ArrayList();

   public WriterEncryptionVariant(WriterEncryptionKey key, TypeDescription root, LocalKey columnKey) {
      this.key = key;
      this.root = root;
      this.material = columnKey;
   }

   public WriterEncryptionKey getKeyDescription() {
      return this.key;
   }

   public TypeDescription getRoot() {
      return this.root;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getVariantId() {
      return this.id;
   }

   public Key getFileFooterKey() {
      return this.material.getDecryptedKey();
   }

   public Key getStripeKey(long stripe) {
      return this.material.getDecryptedKey();
   }

   public LocalKey getMaterial() {
      return this.material;
   }

   public void clearFileStatistics() {
      this.fileStats.clearColumn();
   }

   public OrcProto.FileStatistics getFileStatistics() {
      return this.fileStats.build();
   }

   public void addEncoding(OrcProto.ColumnEncoding encoding) {
      this.encodings.add(encoding);
   }

   public List getEncodings() {
      return this.encodings;
   }

   public void clearEncodings() {
      this.encodings.clear();
   }

   public int hashCode() {
      return this.key.hashCode() << 16 ^ this.root.getId();
   }

   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (other != null && other.getClass() == this.getClass()) {
         return this.compareTo((EncryptionVariant)((WriterEncryptionVariant)other)) == 0;
      } else {
         return false;
      }
   }

   public int compareTo(@NotNull EncryptionVariant other) {
      int result = this.key.compareTo(other.getKeyDescription());
      if (result == 0) {
         result = Integer.compare(this.root.getId(), other.getRoot().getId());
      }

      return result;
   }
}
