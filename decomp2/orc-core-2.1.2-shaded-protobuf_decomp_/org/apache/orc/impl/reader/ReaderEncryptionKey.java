package org.apache.orc.impl.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.orc.EncryptionAlgorithm;
import org.apache.orc.EncryptionKey;
import org.apache.orc.OrcProto;
import org.apache.orc.impl.HadoopShims;
import org.jetbrains.annotations.NotNull;

public class ReaderEncryptionKey implements EncryptionKey {
   private final String name;
   private final int version;
   private final EncryptionAlgorithm algorithm;
   private final List roots = new ArrayList();
   private State state;

   public ReaderEncryptionKey(OrcProto.EncryptionKey key) {
      this.state = ReaderEncryptionKey.State.UNTRIED;
      this.name = key.getKeyName();
      this.version = key.getKeyVersion();
      this.algorithm = EncryptionAlgorithm.fromSerialization(key.getAlgorithm().getNumber());
   }

   public String getKeyName() {
      return this.name;
   }

   public int getKeyVersion() {
      return this.version;
   }

   public EncryptionAlgorithm getAlgorithm() {
      return this.algorithm;
   }

   public ReaderEncryptionVariant[] getEncryptionRoots() {
      return (ReaderEncryptionVariant[])this.roots.toArray(new ReaderEncryptionVariant[0]);
   }

   public HadoopShims.KeyMetadata getMetadata() {
      return new HadoopShims.KeyMetadata(this.name, this.version, this.algorithm);
   }

   public State getState() {
      return this.state;
   }

   public void setFailure() {
      this.state = ReaderEncryptionKey.State.FAILURE;
   }

   public void setSuccess() {
      if (this.state == ReaderEncryptionKey.State.FAILURE) {
         throw new IllegalStateException("Key " + this.name + " had already failed.");
      } else {
         this.state = ReaderEncryptionKey.State.SUCCESS;
      }
   }

   void addVariant(ReaderEncryptionVariant newVariant) {
      this.roots.add(newVariant);
   }

   public boolean equals(Object other) {
      if (other != null && this.getClass() == other.getClass()) {
         if (other == this) {
            return true;
         } else {
            return this.compareTo((EncryptionKey)other) == 0;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.name.hashCode() * 127 + this.version * 7 + this.algorithm.hashCode();
   }

   public int compareTo(@NotNull EncryptionKey other) {
      int result = this.name.compareTo(other.getKeyName());
      if (result == 0) {
         result = Integer.compare(this.version, other.getKeyVersion());
      }

      return result;
   }

   public String toString() {
      String var10000 = this.name;
      return var10000 + "@" + this.version + " w/ " + String.valueOf(this.algorithm);
   }

   public boolean isAvailable() {
      if (this.getState() == ReaderEncryptionKey.State.SUCCESS) {
         return true;
      } else {
         if (this.getState() == ReaderEncryptionKey.State.UNTRIED && this.roots.size() > 0) {
            try {
               return ((ReaderEncryptionVariant)this.roots.get(0)).getFileFooterKey() != null;
            } catch (IOException var2) {
               this.setFailure();
            }
         }

         return false;
      }
   }

   public static enum State {
      UNTRIED,
      FAILURE,
      SUCCESS;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{UNTRIED, FAILURE, SUCCESS};
      }
   }
}
