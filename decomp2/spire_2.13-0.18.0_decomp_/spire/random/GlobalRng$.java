package spire.random;

import spire.random.rng.Cmwc5$;
import spire.random.rng.SyncGenerator;

public final class GlobalRng$ extends LongBasedGenerator {
   public static final GlobalRng$ MODULE$ = new GlobalRng$();
   private static final SyncGenerator rng;

   static {
      rng = Cmwc5$.MODULE$.fromTime(Cmwc5$.MODULE$.fromTime$default$1()).sync();
   }

   private SyncGenerator rng() {
      return rng;
   }

   public SyncGenerator sync() {
      return this.rng();
   }

   public Generator copyInit() {
      return this.rng().copyInit();
   }

   public byte[] getSeedBytes() {
      return this.rng().getSeedBytes();
   }

   public void setSeedBytes(final byte[] bytes) {
      this.rng().setSeedBytes(bytes);
   }

   public long nextLong() {
      return this.rng().nextLong();
   }

   private GlobalRng$() {
   }
}
