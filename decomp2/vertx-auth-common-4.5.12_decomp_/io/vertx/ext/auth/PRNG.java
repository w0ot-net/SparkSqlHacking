package io.vertx.ext.auth;

import io.vertx.core.Vertx;
import io.vertx.ext.auth.impl.Codec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/** @deprecated */
@Deprecated
public class PRNG implements VertxContextPRNG {
   private static final int DEFAULT_SEED_INTERVAL_MILLIS = 300000;
   private static final int DEFAULT_SEED_BITS = 64;
   private final SecureRandom random;
   private final long seedID;
   private final Vertx vertx;
   private volatile boolean dirty = false;

   public PRNG(Vertx vertx) {
      this.vertx = vertx;
      String algorithm = System.getProperty("io.vertx.ext.auth.prng.algorithm");
      int seedInterval = Integer.getInteger("io.vertx.ext.auth.prng.seed.interval", 300000);
      int seedBits = Integer.getInteger("io.vertx.ext.auth.prng.seed.bits", 64);
      if (algorithm != null) {
         try {
            this.random = SecureRandom.getInstance(algorithm);
         } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
         }
      } else {
         this.random = new SecureRandom();
      }

      this.random.nextBytes(new byte[1]);
      if (seedInterval > 0 && seedBits > 0) {
         AtomicBoolean seeding = new AtomicBoolean(false);
         this.seedID = vertx.setPeriodic((long)seedInterval, (id) -> {
            if (this.dirty && seeding.compareAndSet(false, true)) {
               vertx.executeBlocking((future) -> future.complete(this.random.generateSeed(seedBits / 8)), false, (generateSeed) -> {
                  seeding.set(false);
                  this.dirty = false;
                  this.random.setSeed((byte[])generateSeed.result());
               });
            }

         });
      } else {
         this.seedID = -1L;
      }

   }

   public void close() {
      if (this.seedID != -1L) {
         this.vertx.cancelTimer(this.seedID);
      }

   }

   public void nextBytes(byte[] bytes) {
      if (bytes != null) {
         this.random.nextBytes(bytes);
         this.dirty = true;
      }

   }

   public int nextInt() {
      int var1;
      try {
         var1 = this.random.nextInt();
      } finally {
         this.dirty = true;
      }

      return var1;
   }

   public int nextInt(int bound) {
      int var2;
      try {
         var2 = this.random.nextInt(bound);
      } finally {
         this.dirty = true;
      }

      return var2;
   }

   public boolean nextBoolean() {
      boolean var1;
      try {
         var1 = this.random.nextBoolean();
      } finally {
         this.dirty = true;
      }

      return var1;
   }

   public long nextLong() {
      long var1;
      try {
         var1 = this.random.nextLong();
      } finally {
         this.dirty = true;
      }

      return var1;
   }

   public float nextFloat() {
      float var1;
      try {
         var1 = this.random.nextFloat();
      } finally {
         this.dirty = true;
      }

      return var1;
   }

   public double nextDouble() {
      double var1;
      try {
         var1 = this.random.nextDouble();
      } finally {
         this.dirty = true;
      }

      return var1;
   }

   public double nextGaussian() {
      double var1;
      try {
         var1 = this.random.nextGaussian();
      } finally {
         this.dirty = true;
      }

      return var1;
   }

   public String nextString(int length) {
      byte[] data = new byte[length];
      this.nextBytes(data);
      return Codec.base64UrlEncode(data);
   }
}
