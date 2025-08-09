package org.apache.spark.io;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.zip.Checksum;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3Qa\u0003\u0007\u0001\u001dQA\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006I!\u0006\u0005\u0006=\u0001!\ta\b\u0005\nG\u0001\u0001\r\u00111A\u0005\n\u0011B\u0011\"\f\u0001A\u0002\u0003\u0007I\u0011\u0002\u0018\t\u0013]\u0002\u0001\u0019!A!B\u0013)\u0003\"\u0002\u001d\u0001\t\u0003I\u0004\"\u0002\u001f\u0001\t\u0003j\u0004\"\u0002\u001f\u0001\t\u0003\u001a\u0005\"B(\u0001\t\u0003\u0002\u0006\"B)\u0001\t\u0003\u0002&AG'vi\u0006\u0014G.Z\"iK\u000e\\W\rZ(viB,Ho\u0015;sK\u0006l'BA\u0007\u000f\u0003\tIwN\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h'\t\u0001Q\u0003\u0005\u0002\u001755\tqC\u0003\u0002\u000e1)\t\u0011$\u0001\u0003kCZ\f\u0017BA\u000e\u0018\u00051yU\u000f\u001e9viN#(/Z1n\u0003\ryW\u000f^\u0002\u0001\u0003\u0019a\u0014N\\5u}Q\u0011\u0001E\t\t\u0003C\u0001i\u0011\u0001\u0004\u0005\u00069\t\u0001\r!F\u0001\tG\",7m[:v[V\tQ\u0005\u0005\u0002'W5\tqE\u0003\u0002)S\u0005\u0019!0\u001b9\u000b\u0005)B\u0012\u0001B;uS2L!\u0001L\u0014\u0003\u0011\rCWmY6tk6\fAb\u00195fG.\u001cX/\\0%KF$\"aL\u001b\u0011\u0005A\u001aT\"A\u0019\u000b\u0003I\nQa]2bY\u0006L!\u0001N\u0019\u0003\tUs\u0017\u000e\u001e\u0005\bm\u0011\t\t\u00111\u0001&\u0003\rAH%M\u0001\nG\",7m[:v[\u0002\n1b]3u\u0007\",7m[:v[R\u0011qF\u000f\u0005\u0006w\u0019\u0001\r!J\u0001\u0002G\u0006)qO]5uKR\u0011qF\u0010\u0005\u0006\u007f\u001d\u0001\r\u0001Q\u0001\u0002EB\u0011\u0001'Q\u0005\u0003\u0005F\u00121!\u00138u)\u0011yCiS'\t\u000b}B\u0001\u0019A#\u0011\u0007A2\u0005*\u0003\u0002Hc\t)\u0011I\u001d:bsB\u0011\u0001'S\u0005\u0003\u0015F\u0012AAQ=uK\")A\n\u0003a\u0001\u0001\u0006\u0019qN\u001a4\t\u000b9C\u0001\u0019\u0001!\u0002\u00071,g.A\u0003gYV\u001c\b\u000eF\u00010\u0003\u0015\u0019Gn\\:f\u0001"
)
public class MutableCheckedOutputStream extends OutputStream {
   private final OutputStream out;
   private Checksum checksum;

   private Checksum checksum() {
      return this.checksum;
   }

   private void checksum_$eq(final Checksum x$1) {
      this.checksum = x$1;
   }

   public void setChecksum(final Checksum c) {
      this.checksum_$eq(c);
   }

   public void write(final int b) {
      .MODULE$.assert(this.checksum() != null, () -> "Checksum is not set.");
      this.checksum().update(b);
      this.out.write(b);
   }

   public void write(final byte[] b, final int off, final int len) {
      .MODULE$.assert(this.checksum() != null, () -> "Checksum is not set.");
      this.checksum().update(b, off, len);
      this.out.write(b, off, len);
   }

   public void flush() {
      this.out.flush();
   }

   public void close() {
      this.out.close();
   }

   public MutableCheckedOutputStream(final OutputStream out) {
      this.out = out;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
