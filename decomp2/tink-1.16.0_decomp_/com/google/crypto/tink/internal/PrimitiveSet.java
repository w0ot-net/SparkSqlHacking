package com.google.crypto.tink.internal;

import com.google.crypto.tink.CryptoFormat;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public final class PrimitiveSet {
   private final Map entries;
   private final List entriesInKeysetOrder;
   private final Entry primary;
   private final Class primitiveClass;
   private final MonitoringAnnotations annotations;

   private static void storeEntryInPrimitiveSet(Entry entry, Map entries, List entriesInKeysetOrder) {
      List<Entry<P>> list = new ArrayList();
      list.add(entry);
      List<Entry<P>> existing = (List)entries.put(entry.getOutputPrefix(), Collections.unmodifiableList(list));
      if (existing != null) {
         List<Entry<P>> newList = new ArrayList();
         newList.addAll(existing);
         newList.add(entry);
         entries.put(entry.getOutputPrefix(), Collections.unmodifiableList(newList));
      }

      entriesInKeysetOrder.add(entry);
   }

   @Nullable
   public Entry getPrimary() {
      return this.primary;
   }

   public boolean hasAnnotations() {
      return !this.annotations.toMap().isEmpty();
   }

   public MonitoringAnnotations getAnnotations() {
      return this.annotations;
   }

   public List getRawPrimitives() {
      return this.getPrimitive(CryptoFormat.RAW_PREFIX);
   }

   public List getPrimitive(final byte[] identifier) {
      List<Entry<P>> found = (List)this.entries.get(Bytes.copyFrom(identifier));
      return found != null ? found : Collections.emptyList();
   }

   public Collection getAll() {
      return this.entries.values();
   }

   public List getAllInKeysetOrder() {
      return Collections.unmodifiableList(this.entriesInKeysetOrder);
   }

   private PrimitiveSet(Map entries, List entriesInKeysetOrder, Entry primary, MonitoringAnnotations annotations, Class primitiveClass) {
      this.entries = entries;
      this.entriesInKeysetOrder = entriesInKeysetOrder;
      this.primary = primary;
      this.primitiveClass = primitiveClass;
      this.annotations = annotations;
   }

   public Class getPrimitiveClass() {
      return this.primitiveClass;
   }

   public static Builder newBuilder(Class primitiveClass) {
      return new Builder(primitiveClass);
   }

   public static final class Entry {
      private final Object fullPrimitive;
      private final Bytes outputPrefix;
      private final KeyStatusType status;
      private final OutputPrefixType outputPrefixType;
      private final int keyId;
      private final String keyTypeUrl;
      private final Key key;

      private Entry(Object fullPrimitive, final Bytes outputPrefix, KeyStatusType status, OutputPrefixType outputPrefixType, int keyId, String keyTypeUrl, Key key) {
         this.fullPrimitive = fullPrimitive;
         this.outputPrefix = outputPrefix;
         this.status = status;
         this.outputPrefixType = outputPrefixType;
         this.keyId = keyId;
         this.keyTypeUrl = keyTypeUrl;
         this.key = key;
      }

      public Object getFullPrimitive() {
         return this.fullPrimitive;
      }

      public KeyStatusType getStatus() {
         return this.status;
      }

      public OutputPrefixType getOutputPrefixType() {
         return this.outputPrefixType;
      }

      private final Bytes getOutputPrefix() {
         return this.outputPrefix;
      }

      public int getKeyId() {
         return this.keyId;
      }

      public String getKeyTypeUrl() {
         return this.keyTypeUrl;
      }

      public Key getKey() {
         return this.key;
      }

      @Nullable
      public Parameters getParameters() {
         return this.key == null ? null : this.key.getParameters();
      }
   }

   public static class Builder {
      private final Class primitiveClass;
      private Map entries;
      private final List entriesInKeysetOrder;
      private Entry primary;
      private MonitoringAnnotations annotations;

      @CanIgnoreReturnValue
      private Builder addEntry(final Object fullPrimitive, Key key, Keyset.Key protoKey, boolean asPrimary) throws GeneralSecurityException {
         if (this.entries == null) {
            throw new IllegalStateException("addEntry cannot be called after build");
         } else if (fullPrimitive == null) {
            throw new NullPointerException("`fullPrimitive` must not be null");
         } else if (protoKey.getStatus() != KeyStatusType.ENABLED) {
            throw new GeneralSecurityException("only ENABLED key is allowed");
         } else {
            Entry<P> entry = new Entry(fullPrimitive, Bytes.copyFrom(CryptoFormat.getOutputPrefix(protoKey)), protoKey.getStatus(), protoKey.getOutputPrefixType(), protoKey.getKeyId(), protoKey.getKeyData().getTypeUrl(), key);
            PrimitiveSet.storeEntryInPrimitiveSet(entry, this.entries, this.entriesInKeysetOrder);
            if (asPrimary) {
               if (this.primary != null) {
                  throw new IllegalStateException("you cannot set two primary primitives");
               }

               this.primary = entry;
            }

            return this;
         }
      }

      @CanIgnoreReturnValue
      public Builder addFullPrimitive(final Object fullPrimitive, Key key, Keyset.Key protoKey) throws GeneralSecurityException {
         return this.addEntry(fullPrimitive, key, protoKey, false);
      }

      @CanIgnoreReturnValue
      public Builder addPrimaryFullPrimitive(final Object fullPrimitive, Key key, Keyset.Key protoKey) throws GeneralSecurityException {
         return this.addEntry(fullPrimitive, key, protoKey, true);
      }

      @CanIgnoreReturnValue
      public Builder setAnnotations(MonitoringAnnotations annotations) {
         if (this.entries == null) {
            throw new IllegalStateException("setAnnotations cannot be called after build");
         } else {
            this.annotations = annotations;
            return this;
         }
      }

      public PrimitiveSet build() throws GeneralSecurityException {
         if (this.entries == null) {
            throw new IllegalStateException("build cannot be called twice");
         } else {
            PrimitiveSet<P> output = new PrimitiveSet(this.entries, this.entriesInKeysetOrder, this.primary, this.annotations, this.primitiveClass);
            this.entries = null;
            return output;
         }
      }

      private Builder(Class primitiveClass) {
         this.entries = new HashMap();
         this.entriesInKeysetOrder = new ArrayList();
         this.primitiveClass = primitiveClass;
         this.annotations = MonitoringAnnotations.EMPTY;
      }
   }
}
