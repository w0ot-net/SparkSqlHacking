package org.apache.orc.impl;

import org.apache.orc.EncryptionVariant;
import org.apache.orc.OrcProto;
import org.jetbrains.annotations.NotNull;

public class StreamName implements Comparable {
   private final int column;
   private final EncryptionVariant encryption;
   private final OrcProto.Stream.Kind kind;

   public StreamName(int column, OrcProto.Stream.Kind kind) {
      this(column, kind, (EncryptionVariant)null);
   }

   public StreamName(int column, OrcProto.Stream.Kind kind, EncryptionVariant encryption) {
      this.column = column;
      this.kind = kind;
      this.encryption = encryption;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof StreamName other)) {
         return false;
      } else {
         return other.column == this.column && other.kind == this.kind && this.encryption == other.encryption;
      }
   }

   public int compareTo(@NotNull StreamName streamName) {
      Area area = this.getArea();
      Area otherArea = streamName.getArea();
      if (area != otherArea) {
         return otherArea.compareTo(area);
      } else if (this.encryption != streamName.encryption) {
         if (this.encryption != null && streamName.encryption != null) {
            return this.encryption.getVariantId() < streamName.encryption.getVariantId() ? -1 : 1;
         } else {
            return this.encryption == null ? -1 : 1;
         }
      } else if (this.column != streamName.column) {
         return this.column < streamName.column ? -1 : 1;
      } else {
         return this.kind.compareTo(streamName.kind);
      }
   }

   public int getColumn() {
      return this.column;
   }

   public OrcProto.Stream.Kind getKind() {
      return this.kind;
   }

   public Area getArea() {
      return getArea(this.kind);
   }

   public static Area getArea(OrcProto.Stream.Kind kind) {
      switch (kind) {
         case FILE_STATISTICS:
         case STRIPE_STATISTICS:
            return StreamName.Area.FOOTER;
         case ROW_INDEX:
         case DICTIONARY_COUNT:
         case BLOOM_FILTER:
         case BLOOM_FILTER_UTF8:
         case ENCRYPTED_INDEX:
            return StreamName.Area.INDEX;
         default:
            return StreamName.Area.DATA;
      }
   }

   public EncryptionVariant getEncryption() {
      return this.encryption;
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append("column ");
      buffer.append(this.column);
      buffer.append(" kind ");
      buffer.append(this.kind);
      if (this.encryption != null) {
         buffer.append(" encrypt ");
         buffer.append(this.encryption.getKeyDescription());
      }

      return buffer.toString();
   }

   public int hashCode() {
      return (this.encryption == null ? 0 : this.encryption.getVariantId() * 10001) + this.column * 101 + this.kind.getNumber();
   }

   public static enum Area {
      DATA,
      INDEX,
      FOOTER;

      // $FF: synthetic method
      private static Area[] $values() {
         return new Area[]{DATA, INDEX, FOOTER};
      }
   }
}
