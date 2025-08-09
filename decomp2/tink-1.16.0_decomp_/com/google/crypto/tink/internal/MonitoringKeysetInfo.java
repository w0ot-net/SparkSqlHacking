package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
@Alpha
public final class MonitoringKeysetInfo {
   private final MonitoringAnnotations annotations;
   private final List entries;
   @Nullable
   private final Integer primaryKeyId;

   private MonitoringKeysetInfo(MonitoringAnnotations annotations, List entries, Integer primaryKeyId) {
      this.annotations = annotations;
      this.entries = entries;
      this.primaryKeyId = primaryKeyId;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public MonitoringAnnotations getAnnotations() {
      return this.annotations;
   }

   public List getEntries() {
      return this.entries;
   }

   @Nullable
   public Integer getPrimaryKeyId() {
      return this.primaryKeyId;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof MonitoringKeysetInfo)) {
         return false;
      } else {
         MonitoringKeysetInfo info = (MonitoringKeysetInfo)obj;
         return this.annotations.equals(info.annotations) && this.entries.equals(info.entries) && Objects.equals(this.primaryKeyId, info.primaryKeyId);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.annotations, this.entries});
   }

   public String toString() {
      return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", this.annotations, this.entries, this.primaryKeyId);
   }

   @Immutable
   public static final class Entry {
      private final KeyStatus status;
      private final int keyId;
      private final String keyType;
      private final String keyPrefix;

      public KeyStatus getStatus() {
         return this.status;
      }

      public int getKeyId() {
         return this.keyId;
      }

      public String getKeyType() {
         return this.keyType;
      }

      public String getKeyPrefix() {
         return this.keyPrefix;
      }

      private Entry(KeyStatus status, int keyId, String keyType, String keyPrefix) {
         this.status = status;
         this.keyId = keyId;
         this.keyType = keyType;
         this.keyPrefix = keyPrefix;
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Entry)) {
            return false;
         } else {
            Entry entry = (Entry)obj;
            return this.status == entry.status && this.keyId == entry.keyId && this.keyType.equals(entry.keyType) && this.keyPrefix.equals(entry.keyPrefix);
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.status, this.keyId, this.keyType, this.keyPrefix});
      }

      public String toString() {
         return String.format("(status=%s, keyId=%s, keyType='%s', keyPrefix='%s')", this.status, this.keyId, this.keyType, this.keyPrefix);
      }
   }

   public static final class Builder {
      @Nullable
      private ArrayList builderEntries = new ArrayList();
      private MonitoringAnnotations builderAnnotations;
      @Nullable
      private Integer builderPrimaryKeyId;

      public Builder() {
         this.builderAnnotations = MonitoringAnnotations.EMPTY;
         this.builderPrimaryKeyId = null;
      }

      @CanIgnoreReturnValue
      public Builder setAnnotations(MonitoringAnnotations annotations) {
         if (this.builderEntries == null) {
            throw new IllegalStateException("setAnnotations cannot be called after build()");
         } else {
            this.builderAnnotations = annotations;
            return this;
         }
      }

      @CanIgnoreReturnValue
      public Builder addEntry(KeyStatus status, int keyId, String keyType, String keyPrefix) {
         if (this.builderEntries == null) {
            throw new IllegalStateException("addEntry cannot be called after build()");
         } else {
            this.builderEntries.add(new Entry(status, keyId, keyType, keyPrefix));
            return this;
         }
      }

      @CanIgnoreReturnValue
      public Builder setPrimaryKeyId(int primaryKeyId) {
         if (this.builderEntries == null) {
            throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
         } else {
            this.builderPrimaryKeyId = primaryKeyId;
            return this;
         }
      }

      private boolean isKeyIdInEntries(int keyId) {
         for(Entry entry : this.builderEntries) {
            if (entry.getKeyId() == keyId) {
               return true;
            }
         }

         return false;
      }

      public MonitoringKeysetInfo build() throws GeneralSecurityException {
         if (this.builderEntries == null) {
            throw new IllegalStateException("cannot call build() twice");
         } else if (this.builderPrimaryKeyId != null && !this.isKeyIdInEntries(this.builderPrimaryKeyId)) {
            throw new GeneralSecurityException("primary key ID is not present in entries");
         } else {
            MonitoringKeysetInfo output = new MonitoringKeysetInfo(this.builderAnnotations, Collections.unmodifiableList(this.builderEntries), this.builderPrimaryKeyId);
            this.builderEntries = null;
            return output;
         }
      }
   }
}
