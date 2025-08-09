package spire.std;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0013\u0007C\u0003:\u0001\u0011\u0005#\bC\u0003>\u0001\u0011\u0005c\bC\u0003B\u0001\u0011\u0005#\tC\u0003F\u0001\u0011\u0005c\tC\u0003J\u0001\u0011\u0005#\nC\u0003N\u0001\u0011\u0005c\nC\u0003R\u0001\u0011\u0005#\u000bC\u0003V\u0001\u0011\u0005aK\u0001\u0006GY>\fGo\u0014:eKJT!!\u0004\b\u0002\u0007M$HMC\u0001\u0010\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0019\u0011$\n\u0015\u000f\u0005i\u0011cBA\u000e!\u001d\tar$D\u0001\u001e\u0015\tq\u0002#\u0001\u0004=e>|GOP\u0005\u0002\u001f%\u0011\u0011ED\u0001\bC2<WM\u0019:b\u0013\t\u0019C%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0005r\u0011B\u0001\u0014(\u0005\u0015y%\u000fZ3s\u0015\t\u0019C\u0005\u0005\u0002\u0014S%\u0011!\u0006\u0006\u0002\u0006\r2|\u0017\r^\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00035\u0002\"a\u0005\u0018\n\u0005=\"\"\u0001B+oSR\f1!Z9w)\r\u0011Tg\u000e\t\u0003'MJ!\u0001\u000e\u000b\u0003\u000f\t{w\u000e\\3b]\")aG\u0001a\u0001Q\u0005\t\u0001\u0010C\u00039\u0005\u0001\u0007\u0001&A\u0001z\u0003\u0011qW-\u001d<\u0015\u0007IZD\bC\u00037\u0007\u0001\u0007\u0001\u0006C\u00039\u0007\u0001\u0007\u0001&\u0001\u0002hiR\u0019!g\u0010!\t\u000bY\"\u0001\u0019\u0001\u0015\t\u000ba\"\u0001\u0019\u0001\u0015\u0002\u000b\u001d$X-\u001d<\u0015\u0007I\u001aE\tC\u00037\u000b\u0001\u0007\u0001\u0006C\u00039\u000b\u0001\u0007\u0001&\u0001\u0002miR\u0019!g\u0012%\t\u000bY2\u0001\u0019\u0001\u0015\t\u000ba2\u0001\u0019\u0001\u0015\u0002\u000b1$X-\u001d<\u0015\u0007IZE\nC\u00037\u000f\u0001\u0007\u0001\u0006C\u00039\u000f\u0001\u0007\u0001&A\u0002nS:$2\u0001K(Q\u0011\u00151\u0004\u00021\u0001)\u0011\u0015A\u0004\u00021\u0001)\u0003\ri\u0017\r\u001f\u000b\u0004QM#\u0006\"\u0002\u001c\n\u0001\u0004A\u0003\"\u0002\u001d\n\u0001\u0004A\u0013aB2p[B\f'/\u001a\u000b\u0004/j[\u0006CA\nY\u0013\tIFCA\u0002J]RDQA\u000e\u0006A\u0002!BQ\u0001\u000f\u0006A\u0002!\u0002"
)
public interface FloatOrder extends Order.mcF.sp {
   // $FF: synthetic method
   static boolean eqv$(final FloatOrder $this, final float x, final float y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final float x, final float y) {
      return this.eqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final FloatOrder $this, final float x, final float y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final float x, final float y) {
      return this.neqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final FloatOrder $this, final float x, final float y) {
      return $this.gt(x, y);
   }

   default boolean gt(final float x, final float y) {
      return this.gt$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final FloatOrder $this, final float x, final float y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final float x, final float y) {
      return this.gteqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final FloatOrder $this, final float x, final float y) {
      return $this.lt(x, y);
   }

   default boolean lt(final float x, final float y) {
      return this.lt$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final FloatOrder $this, final float x, final float y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final float x, final float y) {
      return this.lteqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static float min$(final FloatOrder $this, final float x, final float y) {
      return $this.min(x, y);
   }

   default float min(final float x, final float y) {
      return this.min$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static float max$(final FloatOrder $this, final float x, final float y) {
      return $this.max(x, y);
   }

   default float max(final float x, final float y) {
      return this.max$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static int compare$(final FloatOrder $this, final float x, final float y) {
      return $this.compare(x, y);
   }

   default int compare(final float x, final float y) {
      return this.compare$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.eqv$mcF$sp(x, y);
   }

   default boolean eqv$mcF$sp(final float x, final float y) {
      return x == y;
   }

   // $FF: synthetic method
   static boolean neqv$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.neqv$mcF$sp(x, y);
   }

   default boolean neqv$mcF$sp(final float x, final float y) {
      return x != y;
   }

   // $FF: synthetic method
   static boolean gt$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.gt$mcF$sp(x, y);
   }

   default boolean gt$mcF$sp(final float x, final float y) {
      return x > y;
   }

   // $FF: synthetic method
   static boolean gteqv$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.gteqv$mcF$sp(x, y);
   }

   default boolean gteqv$mcF$sp(final float x, final float y) {
      return x >= y;
   }

   // $FF: synthetic method
   static boolean lt$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.lt$mcF$sp(x, y);
   }

   default boolean lt$mcF$sp(final float x, final float y) {
      return x < y;
   }

   // $FF: synthetic method
   static boolean lteqv$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.lteqv$mcF$sp(x, y);
   }

   default boolean lteqv$mcF$sp(final float x, final float y) {
      return x <= y;
   }

   // $FF: synthetic method
   static float min$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.min$mcF$sp(x, y);
   }

   default float min$mcF$sp(final float x, final float y) {
      return Math.min(x, y);
   }

   // $FF: synthetic method
   static float max$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.max$mcF$sp(x, y);
   }

   default float max$mcF$sp(final float x, final float y) {
      return Math.max(x, y);
   }

   // $FF: synthetic method
   static int compare$mcF$sp$(final FloatOrder $this, final float x, final float y) {
      return $this.compare$mcF$sp(x, y);
   }

   default int compare$mcF$sp(final float x, final float y) {
      return Float.compare(x, y);
   }

   static void $init$(final FloatOrder $this) {
   }
}
