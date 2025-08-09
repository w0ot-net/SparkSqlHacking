package org.bouncycastle.jcajce.provider.drbg;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;

public class DRBG {
   private static final String PREFIX = DRBG.class.getName();
   private static final String[][] initialEntropySourceNames = new String[][]{{"sun.security.provider.Sun", "sun.security.provider.SecureRandom"}, {"org.apache.harmony.security.provider.crypto.CryptoProvider", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl"}, {"com.android.org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLRandom"}, {"org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLRandom"}};
   private static EntropyDaemon entropyDaemon = null;
   private static Thread entropyThread = null;

   private static final Object[] findSource() {
      for(int var0 = 0; var0 < initialEntropySourceNames.length; ++var0) {
         String[] var1 = initialEntropySourceNames[var0];

         try {
            Object[] var2 = new Object[]{Class.forName(var1[0]).newInstance(), Class.forName(var1[1]).newInstance()};
            return var2;
         }
      }

      return null;
   }

   private static SecureRandom createBaseRandom(boolean var0) {
      if (Properties.getPropertyValue("org.bouncycastle.drbg.entropysource") != null) {
         EntropySourceProvider var7 = createEntropySource();
         EntropySource var9 = var7.get(128);
         byte[] var3 = var0 ? generateDefaultPersonalizationString(var9.getEntropy()) : generateNonceIVPersonalizationString(var9.getEntropy());
         return (new SP800SecureRandomBuilder(var7)).setPersonalizationString(var3).buildHash(new SHA512Digest(), var9.getEntropy(), var0);
      } else if (Properties.isOverrideSet("org.bouncycastle.drbg.entropy_thread")) {
         synchronized(entropyDaemon) {
            if (entropyThread == null) {
               entropyThread = new Thread(entropyDaemon, "BC Entropy Daemon");
               entropyThread.setDaemon(true);
               entropyThread.start();
            }
         }

         HybridEntropySource var6 = new HybridEntropySource(entropyDaemon, 256);
         byte[] var8 = var0 ? generateDefaultPersonalizationString(var6.getEntropy()) : generateNonceIVPersonalizationString(var6.getEntropy());
         return (new SP800SecureRandomBuilder(new EntropySourceProvider() {
            public EntropySource get(int var1) {
               return new HybridEntropySource(DRBG.entropyDaemon, var1);
            }
         })).setPersonalizationString(var8).buildHash(new SHA512Digest(), var6.getEntropy(), var0);
      } else {
         OneShotHybridEntropySource var1 = new OneShotHybridEntropySource(256);
         byte[] var2 = var0 ? generateDefaultPersonalizationString(var1.getEntropy()) : generateNonceIVPersonalizationString(var1.getEntropy());
         return (new SP800SecureRandomBuilder(new EntropySourceProvider() {
            public EntropySource get(int var1) {
               return new OneShotHybridEntropySource(var1);
            }
         })).setPersonalizationString(var2).buildHash(new SHA512Digest(), var1.getEntropy(), var0);
      }
   }

   private static EntropySourceProvider createCoreEntropySourceProvider() {
      boolean var0 = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
         public Boolean run() {
            try {
               Class var1 = SecureRandom.class;
               return var1.getMethod("getInstanceStrong") != null;
            } catch (Exception var2) {
               return false;
            }
         }
      });
      if (var0) {
         SecureRandom var1 = (SecureRandom)AccessController.doPrivileged(new PrivilegedAction() {
            public SecureRandom run() {
               try {
                  return (SecureRandom)SecureRandom.class.getMethod("getInstanceStrong").invoke((Object)null);
               } catch (Exception var2) {
                  return null;
               }
            }
         });
         return (EntropySourceProvider)(var1 == null ? createInitialEntropySource() : new IncrementalEntropySourceProvider(var1, true));
      } else {
         return createInitialEntropySource();
      }
   }

   private static EntropySourceProvider createInitialEntropySource() {
      String var0 = (String)AccessController.doPrivileged(new PrivilegedAction() {
         public String run() {
            return Security.getProperty("securerandom.source");
         }
      });
      if (var0 == null) {
         return new IncrementalEntropySourceProvider(new CoreSecureRandom(findSource()), true);
      } else {
         try {
            return new URLSeededEntropySourceProvider(new URL(var0));
         } catch (Exception var2) {
            return new IncrementalEntropySourceProvider(new CoreSecureRandom(findSource()), true);
         }
      }
   }

   private static EntropySourceProvider createEntropySource() {
      final String var0 = Properties.getPropertyValue("org.bouncycastle.drbg.entropysource");
      return (EntropySourceProvider)AccessController.doPrivileged(new PrivilegedAction() {
         public EntropySourceProvider run() {
            try {
               Class var1 = ClassUtil.loadClass(DRBG.class, var0);
               return (EntropySourceProvider)var1.newInstance();
            } catch (Exception var2) {
               throw new IllegalStateException("entropy source " + var0 + " not created: " + var2.getMessage(), var2);
            }
         }
      });
   }

   private static byte[] generateDefaultPersonalizationString(byte[] var0) {
      return Arrays.concatenate(Strings.toByteArray("Default"), var0, Pack.longToBigEndian(Thread.currentThread().getId()), Pack.longToBigEndian(System.currentTimeMillis()));
   }

   private static byte[] generateNonceIVPersonalizationString(byte[] var0) {
      return Arrays.concatenate(Strings.toByteArray("Nonce"), var0, Pack.longToLittleEndian(Thread.currentThread().getId()), Pack.longToLittleEndian(System.currentTimeMillis()));
   }

   private static void sleep(long var0) throws InterruptedException {
      if (var0 != 0L) {
         Thread.sleep(var0);
      }

   }

   static {
      entropyDaemon = new EntropyDaemon();
   }

   private static class CoreSecureRandom extends SecureRandom {
      CoreSecureRandom(Object[] var1) {
         super((SecureRandomSpi)var1[1], (Provider)var1[0]);
      }
   }

   public static class Default extends SecureRandomSpi {
      private static final SecureRandom random = DRBG.createBaseRandom(true);

      protected void engineSetSeed(byte[] var1) {
         random.setSeed(var1);
      }

      protected void engineNextBytes(byte[] var1) {
         random.nextBytes(var1);
      }

      protected byte[] engineGenerateSeed(int var1) {
         return random.generateSeed(var1);
      }
   }

   private static class HybridEntropySource implements EntropySource {
      private final AtomicBoolean seedAvailable = new AtomicBoolean(false);
      private final AtomicInteger samples = new AtomicInteger(0);
      private final SP800SecureRandom drbg;
      private final SignallingEntropySource entropySource;
      private final int bytesRequired;
      private final byte[] additionalInput = Pack.longToBigEndian(System.currentTimeMillis());

      HybridEntropySource(EntropyDaemon var1, int var2) {
         EntropySourceProvider var3 = DRBG.createCoreEntropySourceProvider();
         this.bytesRequired = (var2 + 7) / 8;
         this.entropySource = new SignallingEntropySource(var1, this.seedAvailable, var3, 256);
         this.drbg = (new SP800SecureRandomBuilder(new EntropySourceProvider() {
            public EntropySource get(int var1) {
               return HybridEntropySource.this.entropySource;
            }
         })).setPersonalizationString(Strings.toByteArray("Bouncy Castle Hybrid Entropy Source")).buildHMAC(new HMac(new SHA512Digest()), this.entropySource.getEntropy(), false);
      }

      public boolean isPredictionResistant() {
         return true;
      }

      public byte[] getEntropy() {
         byte[] var1 = new byte[this.bytesRequired];
         if (this.samples.getAndIncrement() > 128) {
            if (this.seedAvailable.getAndSet(false)) {
               this.samples.set(0);
               this.drbg.reseed(this.additionalInput);
            } else {
               this.entropySource.schedule();
            }
         }

         this.drbg.nextBytes(var1);
         return var1;
      }

      public int entropySize() {
         return this.bytesRequired * 8;
      }

      private static class SignallingEntropySource implements IncrementalEntropySource {
         private final EntropyDaemon entropyDaemon;
         private final AtomicBoolean seedAvailable;
         private final IncrementalEntropySource entropySource;
         private final int byteLength;
         private final AtomicReference entropy = new AtomicReference();
         private final AtomicBoolean scheduled = new AtomicBoolean(false);

         SignallingEntropySource(EntropyDaemon var1, AtomicBoolean var2, EntropySourceProvider var3, int var4) {
            this.entropyDaemon = var1;
            this.seedAvailable = var2;
            this.entropySource = (IncrementalEntropySource)var3.get(var4);
            this.byteLength = (var4 + 7) / 8;
         }

         public boolean isPredictionResistant() {
            return true;
         }

         public byte[] getEntropy() {
            try {
               return this.getEntropy(0L);
            } catch (InterruptedException var2) {
               Thread.currentThread().interrupt();
               throw new IllegalStateException("initial entropy fetch interrupted");
            }
         }

         public byte[] getEntropy(long var1) throws InterruptedException {
            byte[] var3 = (byte[])this.entropy.getAndSet((Object)null);
            if (var3 != null && var3.length == this.byteLength) {
               this.scheduled.set(false);
            } else {
               var3 = this.entropySource.getEntropy(var1);
            }

            return var3;
         }

         void schedule() {
            if (!this.scheduled.getAndSet(true)) {
               this.entropyDaemon.addTask(new EntropyGatherer(this.entropySource, this.seedAvailable, this.entropy));
            }

         }

         public int entropySize() {
            return this.byteLength * 8;
         }
      }
   }

   public static class Mappings extends AsymmetricAlgorithmProvider {
      public void configure(ConfigurableProvider var1) {
         var1.addAlgorithm("SecureRandom.DEFAULT", DRBG.PREFIX + "$Default");
         var1.addAlgorithm("SecureRandom.NONCEANDIV", DRBG.PREFIX + "$NonceAndIV");
      }
   }

   public static class NonceAndIV extends SecureRandomSpi {
      private static final SecureRandom random = DRBG.createBaseRandom(false);

      protected void engineSetSeed(byte[] var1) {
         random.setSeed(var1);
      }

      protected void engineNextBytes(byte[] var1) {
         random.nextBytes(var1);
      }

      protected byte[] engineGenerateSeed(int var1) {
         return random.generateSeed(var1);
      }
   }

   private static class OneShotHybridEntropySource implements EntropySource {
      private final AtomicBoolean seedAvailable = new AtomicBoolean(false);
      private final AtomicInteger samples = new AtomicInteger(0);
      private final SP800SecureRandom drbg;
      private final OneShotSignallingEntropySource entropySource;
      private final int bytesRequired;
      private final byte[] additionalInput = Pack.longToBigEndian(System.currentTimeMillis());

      OneShotHybridEntropySource(int var1) {
         EntropySourceProvider var2 = DRBG.createCoreEntropySourceProvider();
         this.bytesRequired = (var1 + 7) / 8;
         this.entropySource = new OneShotSignallingEntropySource(this.seedAvailable, var2, 256);
         this.drbg = (new SP800SecureRandomBuilder(new EntropySourceProvider() {
            public EntropySource get(int var1) {
               return OneShotHybridEntropySource.this.entropySource;
            }
         })).setPersonalizationString(Strings.toByteArray("Bouncy Castle Hybrid Entropy Source")).buildHMAC(new HMac(new SHA512Digest()), this.entropySource.getEntropy(), false);
      }

      public boolean isPredictionResistant() {
         return true;
      }

      public byte[] getEntropy() {
         byte[] var1 = new byte[this.bytesRequired];
         if (this.samples.getAndIncrement() > 1024) {
            if (this.seedAvailable.getAndSet(false)) {
               this.samples.set(0);
               this.drbg.reseed(this.additionalInput);
            } else {
               this.entropySource.schedule();
            }
         }

         this.drbg.nextBytes(var1);
         return var1;
      }

      public int entropySize() {
         return this.bytesRequired * 8;
      }

      private static class OneShotSignallingEntropySource implements IncrementalEntropySource {
         private final AtomicBoolean seedAvailable;
         private final IncrementalEntropySource entropySource;
         private final int byteLength;
         private final AtomicReference entropy = new AtomicReference();
         private final AtomicBoolean scheduled = new AtomicBoolean(false);

         OneShotSignallingEntropySource(AtomicBoolean var1, EntropySourceProvider var2, int var3) {
            this.seedAvailable = var1;
            this.entropySource = (IncrementalEntropySource)var2.get(var3);
            this.byteLength = (var3 + 7) / 8;
         }

         public boolean isPredictionResistant() {
            return true;
         }

         public byte[] getEntropy() {
            try {
               return this.getEntropy(0L);
            } catch (InterruptedException var2) {
               Thread.currentThread().interrupt();
               throw new IllegalStateException("initial entropy fetch interrupted");
            }
         }

         public byte[] getEntropy(long var1) throws InterruptedException {
            byte[] var3 = (byte[])this.entropy.getAndSet((Object)null);
            if (var3 != null && var3.length == this.byteLength) {
               this.scheduled.set(false);
            } else {
               var3 = this.entropySource.getEntropy(var1);
            }

            return var3;
         }

         void schedule() {
            if (!this.scheduled.getAndSet(true)) {
               Thread var1 = new Thread(new EntropyGatherer(this.entropySource, this.seedAvailable, this.entropy));
               var1.setDaemon(true);
               var1.start();
            }

         }

         public int entropySize() {
            return this.byteLength * 8;
         }
      }
   }

   private static class URLSeededEntropySourceProvider implements EntropySourceProvider {
      private final InputStream seedStream;

      URLSeededEntropySourceProvider(final URL var1) {
         this.seedStream = (InputStream)AccessController.doPrivileged(new PrivilegedAction() {
            public InputStream run() {
               try {
                  return var1.openStream();
               } catch (IOException var2) {
                  throw new IllegalStateException("unable to open random source");
               }
            }
         });
      }

      private int privilegedRead(final byte[] var1, final int var2, final int var3) {
         return (Integer)AccessController.doPrivileged(new PrivilegedAction() {
            public Integer run() {
               try {
                  return URLSeededEntropySourceProvider.this.seedStream.read(var1, var2, var3);
               } catch (IOException var2x) {
                  throw new InternalError("unable to read random source");
               }
            }
         });
      }

      public EntropySource get(final int var1) {
         return new IncrementalEntropySource() {
            private final int numBytes = (var1 + 7) / 8;

            public boolean isPredictionResistant() {
               return true;
            }

            public byte[] getEntropy() {
               try {
                  return this.getEntropy(0L);
               } catch (InterruptedException var2) {
                  Thread.currentThread().interrupt();
                  throw new IllegalStateException("initial entropy fetch interrupted");
               }
            }

            public byte[] getEntropy(long var1x) throws InterruptedException {
               byte[] var3 = new byte[this.numBytes];
               int var4 = 0;

               int var5;
               while(var4 != var3.length && (var5 = URLSeededEntropySourceProvider.this.privilegedRead(var3, var4, var3.length - var4)) > -1) {
                  var4 += var5;
                  DRBG.sleep(var1x);
               }

               if (var4 != var3.length) {
                  throw new InternalError("unable to fully read random source");
               } else {
                  return var3;
               }
            }

            public int entropySize() {
               return var1;
            }
         };
      }
   }
}
