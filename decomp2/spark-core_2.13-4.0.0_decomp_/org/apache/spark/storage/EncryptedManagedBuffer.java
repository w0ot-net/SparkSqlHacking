package org.apache.spark.storage;

import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513Qa\u0003\u0007\u0001\u001dQA\u0001\"\b\u0001\u0003\u0006\u0004%\ta\b\u0005\tI\u0001\u0011\t\u0011)A\u0005A!)Q\u0005\u0001C\u0001M!)\u0011\u0006\u0001C!U!)\u0011\u0007\u0001C!e!)1\b\u0001C!y!)\u0001\t\u0001C!y!)\u0011\t\u0001C!\u0005\")\u0011\n\u0001C!\u0015\")1\n\u0001C!\u0015\n1RI\\2ssB$X\rZ'b]\u0006<W\r\u001a\"vM\u001a,'O\u0003\u0002\u000e\u001d\u000591\u000f^8sC\u001e,'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0014\u0005\u0001)\u0002C\u0001\f\u001c\u001b\u00059\"B\u0001\r\u001a\u0003\u0019\u0011WO\u001a4fe*\u0011!DD\u0001\b]\u0016$xo\u001c:l\u0013\tarCA\u0007NC:\fw-\u001a3Ck\u001a4WM]\u0001\nE2|7m\u001b#bi\u0006\u001c\u0001!F\u0001!!\t\t#%D\u0001\r\u0013\t\u0019CB\u0001\nF]\u000e\u0014\u0018\u0010\u001d;fI\ncwnY6ECR\f\u0017A\u00032m_\u000e\\G)\u0019;bA\u00051A(\u001b8jiz\"\"a\n\u0015\u0011\u0005\u0005\u0002\u0001\"B\u000f\u0004\u0001\u0004\u0001\u0013\u0001B:ju\u0016$\u0012a\u000b\t\u0003Y=j\u0011!\f\u0006\u0002]\u0005)1oY1mC&\u0011\u0001'\f\u0002\u0005\u0019>tw-A\u0007oS>\u0014\u0015\u0010^3Ck\u001a4WM\u001d\u000b\u0002gA\u0011A'O\u0007\u0002k)\u0011agN\u0001\u0004]&|'\"\u0001\u001d\u0002\t)\fg/Y\u0005\u0003uU\u0012!BQ=uK\n+hMZ3s\u00039\u0019wN\u001c<feR$vNT3uif$\u0012!\u0010\t\u0003YyJ!aP\u0017\u0003\r\u0005s\u0017PU3g\u0003Q\u0019wN\u001c<feR$vNT3uif4uN]*tY\u0006\t2M]3bi\u0016Le\u000e];u'R\u0014X-Y7\u0015\u0003\r\u0003\"\u0001R$\u000e\u0003\u0015S!AR\u001c\u0002\u0005%|\u0017B\u0001%F\u0005-Ie\u000e];u'R\u0014X-Y7\u0002\rI,G/Y5o)\u0005)\u0012a\u0002:fY\u0016\f7/\u001a"
)
public class EncryptedManagedBuffer extends ManagedBuffer {
   private final EncryptedBlockData blockData;

   public EncryptedBlockData blockData() {
      return this.blockData;
   }

   public long size() {
      return this.blockData().size();
   }

   public ByteBuffer nioByteBuffer() {
      return this.blockData().toByteBuffer();
   }

   public Object convertToNetty() {
      return this.blockData().toNetty();
   }

   public Object convertToNettyForSsl() {
      return this.blockData().toNettyForSsl();
   }

   public InputStream createInputStream() {
      return this.blockData().toInputStream();
   }

   public ManagedBuffer retain() {
      return this;
   }

   public ManagedBuffer release() {
      return this;
   }

   public EncryptedManagedBuffer(final EncryptedBlockData blockData) {
      this.blockData = blockData;
   }
}
