package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.math.Ordered;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Me\u0001B\u0014)\u0005=B\u0001\"\u0010\u0001\u0003\u0006\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005\u007f!a1\t\u0001C\u0001\u0002\u0003\u0005\t\u0011!C\u0005\t\")a\t\u0001C\u0001\u000f\")!\n\u0001C\u0001\u0017\")a\n\u0001C\u0001\u001f\")!\n\u0001C\u0001#\")a\n\u0001C\u0001#\")!\u000b\u0001C\u0001'\")q\u000b\u0001C\u0001'\")\u0001\f\u0001C\u0001'\")\u0011\f\u0001C\u00015\")A\f\u0001C!;\"9a\rAA\u0001\n\u0003:\u0007b\u00025\u0001\u0003\u0003%\t%[\u0004\u0006_\"B\t\u0001\u001d\u0004\u0006O!B\t!\u001d\u0005\u0006\u0007F!\t!\u001e\u0005\bmF\u0011\r\u0011\"\u0002x\u0011\u0019Q\u0018\u0003)A\u0007q\"910\u0005b\u0001\n\u000b\t\u0006B\u0002?\u0012A\u000351\bC\u0004~#\t\u0007IQA)\t\ry\f\u0002\u0015!\u0004<\u0011\u0019y\u0018\u0003\"\u0002\u0002\u0002!9\u0011QB\t\u0005\u0002\u0005=\u0001bBA\u001d#\u0011\u0015\u00111\b\u0005\b\u0003\u000b\nBQAA$\u0011\u001d\ty%\u0005C\u0003\u0003#Bq!!\u0012\u0012\t\u000b\tI\u0006C\u0004\u0002PE!)!!\u0018\t\u000f\u0005\u0005\u0014\u0003\"\u0002\u0002d!9\u0011qM\t\u0005\u0006\u0005%\u0004bBA7#\u0011\u0015\u0011q\u000e\u0005\b\u0003g\nBQAA;\u0011\u001d\ti(\u0005C\u0003\u0003\u007fB\u0011\"a!\u0012\u0003\u0003%)!!\"\t\u0013\u0005%\u0015#!A\u0005\u0006\u0005-%!\u0002#faRD'BA\u0015+\u0003!Ig\u000e^3s]\u0006d'BA\u0016-\u0003\u001d\u0011XM\u001a7fGRT\u0011!L\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001\u0001\u0007\u000e\t\u0003cIj\u0011\u0001L\u0005\u0003g1\u0012a!\u00118z-\u0006d\u0007cA\u001b9w9\u0011\u0011GN\u0005\u0003o1\nq\u0001]1dW\u0006<W-\u0003\u0002:u\t9qJ\u001d3fe\u0016$'BA\u001c-!\ta\u0004!D\u0001)\u0003\u0015!W\r\u001d;i+\u0005y\u0004CA\u0019A\u0013\t\tEFA\u0002J]R\fa\u0001Z3qi\"\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002<\u000b\")Qh\u0001a\u0001\u007f\u0005\u0019Q.\u0019=\u0015\u0005mB\u0005\"B%\u0005\u0001\u0004Y\u0014\u0001\u0002;iCR\fA\u0001Z3deR\u00111\b\u0014\u0005\u0006\u001b\u0016\u0001\raP\u0001\u0002]\u0006!\u0011N\\2s)\tY\u0004\u000bC\u0003N\r\u0001\u0007q(F\u0001<\u0003)I7OT3hCRLg/Z\u000b\u0002)B\u0011\u0011'V\u0005\u0003-2\u0012qAQ8pY\u0016\fg.\u0001\u0004jgj+'o\\\u0001\u000bSN\fe.\u001f#faRD\u0017aB2p[B\f'/\u001a\u000b\u0003\u007fmCQ!\u0013\u0007A\u0002m\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002=B\u0011q\fZ\u0007\u0002A*\u0011\u0011MY\u0001\u0005Y\u0006twMC\u0001d\u0003\u0011Q\u0017M^1\n\u0005\u0015\u0004'AB*ue&tw-\u0001\u0005iCND7i\u001c3f)\u0005y\u0014AB3rk\u0006d7\u000f\u0006\u0002UU\"91nDA\u0001\u0002\u0004a\u0017a\u0001=%cA\u0011\u0011'\\\u0005\u0003]2\u00121!\u00118z\u0003\u0015!U\r\u001d;i!\ta\u0014c\u0005\u0002\u0012eB\u0011\u0011g]\u0005\u0003i2\u0012a!\u00118z%\u00164G#\u00019\u0002\u001b\u0005s\u0017\u0010R3qi\"4\u0016\r\\;f+\u0005Ax\"A=\u001e\u0003u\u0010a\"\u00118z\t\u0016\u0004H\u000f\u001b,bYV,\u0007%\u0001\u0005B]f$U\r\u001d;i\u0003%\te.\u001f#faRD\u0007%\u0001\u0003[KJ|\u0017!\u0002.fe>\u0004\u0013!B1qa2LHcA\u001e\u0002\u0004!)Q(\u0007a\u0001\u007f!\u001a\u0011$a\u0002\u0011\u0007E\nI!C\u0002\u0002\f1\u0012a!\u001b8mS:,\u0017!C7bq&lW/\u001c\"z+\u0011\t\t\"a\t\u0015\t\u0005M\u0011q\u0006\u000b\u0004w\u0005U\u0001bBA\f5\u0001\u0007\u0011\u0011D\u0001\u0003M\u001a\u0004R\u0001PA\u000e\u0003?I1!!\b)\u00055!U\r\u001d;i\rVt7\r^5p]B!\u0011\u0011EA\u0012\u0019\u0001!q!!\n\u001b\u0005\u0004\t9CA\u0001B#\r\tI\u0003\u001c\t\u0004c\u0005-\u0012bAA\u0017Y\t9aj\u001c;iS:<\u0007bBA\u00195\u0001\u0007\u00111G\u0001\u0003qN\u0004R!NA\u001b\u0003?I1!a\u000e;\u0005\u0011a\u0015n\u001d;\u0002\u001b5\f\u0007\u0010J3yi\u0016t7/[8o)\u0011\ti$!\u0011\u0015\u0007m\ny\u0004C\u0003J7\u0001\u00071\b\u0003\u0004\u0002Dm\u0001\raO\u0001\u0006IQD\u0017n]\u0001\u000fI\u0016\u001c'\u000fJ3yi\u0016t7/[8o)\u0011\tI%!\u0014\u0015\u0007m\nY\u0005C\u0003N9\u0001\u0007q\b\u0003\u0004\u0002Dq\u0001\raO\u0001\u000fS:\u001c'\u000fJ3yi\u0016t7/[8o)\u0011\t\u0019&a\u0016\u0015\u0007m\n)\u0006C\u0003N;\u0001\u0007q\b\u0003\u0004\u0002Du\u0001\ra\u000f\u000b\u0004w\u0005m\u0003BBA\"=\u0001\u00071\bF\u0002<\u0003?Ba!a\u0011 \u0001\u0004Y\u0014\u0001F5t\u001d\u0016<\u0017\r^5wK\u0012*\u0007\u0010^3og&|g\u000eF\u0002U\u0003KBa!a\u0011!\u0001\u0004Y\u0014\u0001E5t5\u0016\u0014x\u000eJ3yi\u0016t7/[8o)\r!\u00161\u000e\u0005\u0007\u0003\u0007\n\u0003\u0019A\u001e\u0002)%\u001c\u0018I\\=EKB$\b\u000eJ3yi\u0016t7/[8o)\r!\u0016\u0011\u000f\u0005\u0007\u0003\u0007\u0012\u0003\u0019A\u001e\u0002#\r|W\u000e]1sK\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002x\u0005mDcA \u0002z!)\u0011j\ta\u0001w!1\u00111I\u0012A\u0002m\n!\u0003^8TiJLgn\u001a\u0013fqR,gn]5p]R\u0019Q,!!\t\r\u0005\rC\u00051\u0001<\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007\u001d\f9\t\u0003\u0004\u0002D\u0015\u0002\raO\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:$B!!$\u0002\u0012R\u0019A+a$\t\u000f-4\u0013\u0011!a\u0001Y\"1\u00111\t\u0014A\u0002m\u0002"
)
public final class Depth implements Ordered {
   private final int depth;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return Depth$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      Depth$ var10000 = Depth$.MODULE$;
      return Integer.hashCode($this);
   }

   public static String toString$extension(final int $this) {
      return Depth$.MODULE$.toString$extension($this);
   }

   public static int compare$extension(final int $this, final int that) {
      return Depth$.MODULE$.compare$extension($this, that);
   }

   public static boolean isAnyDepth$extension(final int $this) {
      return Depth$.MODULE$.isAnyDepth$extension($this);
   }

   public static boolean isZero$extension(final int $this) {
      return Depth$.MODULE$.isZero$extension($this);
   }

   public static boolean isNegative$extension(final int $this) {
      return Depth$.MODULE$.isNegative$extension($this);
   }

   public static int incr$extension(final int $this) {
      return Depth$.MODULE$.incr$extension($this, 1);
   }

   public static int decr$extension(final int $this) {
      return Depth$.MODULE$.decr$extension($this, 1);
   }

   public static int incr$extension(final int $this, final int n) {
      return Depth$.MODULE$.incr$extension($this, n);
   }

   public static int decr$extension(final int $this, final int n) {
      return Depth$.MODULE$.decr$extension($this, n);
   }

   public static int max$extension(final int $this, final int that) {
      return Depth$.MODULE$.max$extension($this, that);
   }

   public static int maximumBy(final List xs, final DepthFunction ff) {
      Depth$ maximumBy_this = Depth$.MODULE$;
      List maximumBy_ys = xs;

      int maximumBy_mm;
      for(maximumBy_mm = maximumBy_this.Zero(); !maximumBy_ys.isEmpty(); maximumBy_ys = (List)maximumBy_ys.tail()) {
         maximumBy_mm = maximumBy_this.max$extension(maximumBy_mm, ff.apply(maximumBy_ys.head()));
      }

      return maximumBy_mm;
   }

   public static int apply(final int depth) {
      Depth$ apply_this = Depth$.MODULE$;
      return depth < -3 ? apply_this.AnyDepth() : depth;
   }

   public static int Zero() {
      return Depth$.MODULE$.Zero();
   }

   public static int AnyDepth() {
      return Depth$.MODULE$.AnyDepth();
   }

   public static int AnyDepthValue() {
      Depth$ var10000 = Depth$.MODULE$;
      return -3;
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   public int depth() {
      return this.depth;
   }

   public int max(final int that) {
      return Depth$.MODULE$.max$extension(this.depth(), that);
   }

   public int decr(final int n) {
      return Depth$.MODULE$.decr$extension(this.depth(), n);
   }

   public int incr(final int n) {
      return Depth$.MODULE$.incr$extension(this.depth(), n);
   }

   public int decr() {
      return Depth$.MODULE$.decr$extension(this.depth(), 1);
   }

   public int incr() {
      return Depth$.MODULE$.incr$extension(this.depth(), 1);
   }

   public boolean isNegative() {
      return Depth$.MODULE$.isNegative$extension(this.depth());
   }

   public boolean isZero() {
      return Depth$.MODULE$.isZero$extension(this.depth());
   }

   public boolean isAnyDepth() {
      return Depth$.MODULE$.isAnyDepth$extension(this.depth());
   }

   public int compare(final int that) {
      return Depth$.MODULE$.compare$extension(this.depth(), that);
   }

   public String toString() {
      return Depth$.MODULE$.toString$extension(this.depth());
   }

   public int hashCode() {
      Depth$ var10000 = Depth$.MODULE$;
      return Integer.hashCode(this.depth());
   }

   public boolean equals(final Object x$1) {
      return Depth$.MODULE$.equals$extension(this.depth(), x$1);
   }

   public Depth(final int depth) {
      this.depth = depth;
   }
}
