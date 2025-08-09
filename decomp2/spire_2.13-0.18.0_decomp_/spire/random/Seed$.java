package spire.random;

import spire.util.Pack.;

public final class Seed$ {
   public static final Seed$ MODULE$ = new Seed$();
   private static final Seed zero;

   static {
      zero = MODULE$.apply(new byte[]{0, 0, 0, 0});
   }

   public Seed zero() {
      return zero;
   }

   public Seed apply(final int n) {
      return new Seed(.MODULE$.intToBytes(n));
   }

   public Seed apply(final long n) {
      return new Seed(.MODULE$.longToBytes(n));
   }

   public Seed apply(final byte[] bytes) {
      return new Seed((byte[])(([B)bytes).clone());
   }

   private Seed$() {
   }
}
