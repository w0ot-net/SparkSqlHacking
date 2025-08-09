package spire.random.rng;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import spire.random.Generator;

@ScalaSignature(
   bytes = "\u0006\u0005-4A\u0001E\t\u00011!AQ\u0004\u0001B\u0001B\u0003%a\u0004C\u0003'\u0001\u0011\u0005q\u0005C\u0004,\u0001\u0001\u0007I\u0011\u0002\u0017\t\u000fA\u0002\u0001\u0019!C\u0005c!1!\b\u0001Q!\n5BQa\u000f\u0001\u0005\u0002qBQ!\u0010\u0001\u0005\u0002yBQa\u0010\u0001\u0005\u0002\u0001CQa\u0012\u0001\u0005\u0002!CQa\u0013\u0001\u0005\u00021CQ\u0001\u0015\u0001\u0005\u0002E;Q!V\t\t\u0002Y3Q\u0001E\t\t\u0002]CQAJ\u0007\u0005\u0002mCQ\u0001X\u0007\u0005\u0002u\u0013!bQ=dY\u0016$g)\u001b7f\u0015\t\u00112#A\u0002s]\u001eT!\u0001F\u000b\u0002\rI\fg\u000eZ8n\u0015\u00051\u0012!B:qSJ,7\u0001A\n\u0003\u0001e\u0001\"AG\u000e\u000e\u0003MI!\u0001H\n\u0003\u0013\u001d+g.\u001a:bi>\u0014\u0018!\u00014\u0011\u0005}!S\"\u0001\u0011\u000b\u0005\u0005\u0012\u0013AA5p\u0015\u0005\u0019\u0013\u0001\u00026bm\u0006L!!\n\u0011\u0003\t\u0019KG.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005!R\u0003CA\u0015\u0001\u001b\u0005\t\u0002\"B\u000f\u0003\u0001\u0004q\u0012a\u00013jgV\tQ\u0006\u0005\u0002 ]%\u0011q\u0006\t\u0002\u0010\t\u0006$\u0018-\u00138qkR\u001cFO]3b[\u00069A-[:`I\u0015\fHC\u0001\u001a9!\t\u0019d'D\u00015\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0005\u0011)f.\u001b;\t\u000fe\"\u0011\u0011!a\u0001[\u0005\u0019\u0001\u0010J\u0019\u0002\t\u0011L7\u000fI\u0001\u0007e\u0016Lg.\u001b;\u0016\u0003I\n\u0001bY8qs&s\u0017\u000e^\u000b\u00023\u0005aq-\u001a;TK\u0016$')\u001f;fgV\t\u0011\tE\u00024\u0005\u0012K!a\u0011\u001b\u0003\u000b\u0005\u0013(/Y=\u0011\u0005M*\u0015B\u0001$5\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u0005IJ\u0005\"\u0002&\n\u0001\u0004\t\u0015!\u00022zi\u0016\u001c\u0018a\u00028fqRLe\u000e\u001e\u000b\u0002\u001bB\u00111GT\u0005\u0003\u001fR\u00121!\u00138u\u0003!qW\r\u001f;M_:<G#\u0001*\u0011\u0005M\u001a\u0016B\u0001+5\u0005\u0011auN\\4\u0002\u0015\rK8\r\\3e\r&dW\r\u0005\u0002*\u001bM\u0011Q\u0002\u0017\t\u0003geK!A\u0017\u001b\u0003\r\u0005s\u0017PU3g)\u00051\u0016!B1qa2LHC\u0001\u0015_\u0011\u0015yv\u00021\u0001a\u0003\u0011\u0001\u0018\r\u001e5\u0011\u0005\u0005DgB\u00012g!\t\u0019G'D\u0001e\u0015\t)w#\u0001\u0004=e>|GOP\u0005\u0003OR\na\u0001\u0015:fI\u00164\u0017BA5k\u0005\u0019\u0019FO]5oO*\u0011q\r\u000e"
)
public class CycledFile extends Generator {
   private final File f;
   private DataInputStream dis;

   public static CycledFile apply(final String path) {
      return CycledFile$.MODULE$.apply(path);
   }

   private DataInputStream dis() {
      return this.dis;
   }

   private void dis_$eq(final DataInputStream x$1) {
      this.dis = x$1;
   }

   public void reinit() {
      if (this.dis() != null) {
         this.dis().close();
      }

      this.dis_$eq(new DataInputStream(new FileInputStream(this.f)));
   }

   public Generator copyInit() {
      return new CycledFile(this.f);
   }

   public byte[] getSeedBytes() {
      throw new UnsupportedOperationException("getSeedBytes");
   }

   public void setSeedBytes(final byte[] bytes) {
      throw new UnsupportedOperationException("setSeedBytes");
   }

   public int nextInt() {
      int var10000;
      try {
         var10000 = this.dis().readInt();
      } catch (EOFException var2) {
         this.reinit();
         var10000 = this.dis().readInt();
      }

      return var10000;
   }

   public long nextLong() {
      long var10000;
      try {
         var10000 = this.dis().readLong();
      } catch (EOFException var2) {
         this.reinit();
         var10000 = (long)this.dis().readInt();
      }

      return var10000;
   }

   public CycledFile(final File f) {
      this.f = f;
      this.dis = null;
      if (!f.canRead()) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("can't read %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{f})));
      } else {
         this.reinit();

         try {
            this.nextLong();
         } catch (EOFException var3) {
            throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s contains less than 8 bytes"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{f})));
         }

      }
   }
}
