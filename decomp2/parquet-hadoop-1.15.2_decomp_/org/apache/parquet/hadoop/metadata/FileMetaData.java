package org.apache.parquet.hadoop.metadata;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import org.apache.parquet.crypto.InternalFileDecryptor;
import org.apache.parquet.schema.MessageType;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonIgnore;

public final class FileMetaData implements Serializable {
   private static final long serialVersionUID = 1L;
   private final MessageType schema;
   private final Map keyValueMetaData;
   private final String createdBy;
   private final InternalFileDecryptor fileDecryptor;
   private final EncryptionType encryptionType;

   public FileMetaData(MessageType schema, Map keyValueMetaData, String createdBy) {
      this(schema, keyValueMetaData, createdBy, (EncryptionType)null, (InternalFileDecryptor)null);
   }

   /** @deprecated */
   @Deprecated
   public FileMetaData(MessageType schema, Map keyValueMetaData, String createdBy, InternalFileDecryptor fileDecryptor) {
      this(schema, keyValueMetaData, createdBy, (EncryptionType)null, fileDecryptor);
   }

   public FileMetaData(MessageType schema, Map keyValueMetaData, String createdBy, EncryptionType encryptionType, InternalFileDecryptor fileDecryptor) {
      this.schema = (MessageType)Objects.requireNonNull(schema, "schema cannot be null");
      this.keyValueMetaData = Collections.unmodifiableMap((Map)Objects.requireNonNull(keyValueMetaData, "keyValueMetaData cannot be null"));
      this.createdBy = createdBy;
      this.fileDecryptor = fileDecryptor;
      this.encryptionType = encryptionType;
   }

   public MessageType getSchema() {
      return this.schema;
   }

   public String toString() {
      return "FileMetaData{schema: " + this.schema + ", metadata: " + this.keyValueMetaData + "}";
   }

   public Map getKeyValueMetaData() {
      return this.keyValueMetaData;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   @JsonIgnore
   public InternalFileDecryptor getFileDecryptor() {
      return this.fileDecryptor;
   }

   public EncryptionType getEncryptionType() {
      return this.encryptionType;
   }

   public static enum EncryptionType {
      UNENCRYPTED,
      PLAINTEXT_FOOTER,
      ENCRYPTED_FOOTER;
   }
}
