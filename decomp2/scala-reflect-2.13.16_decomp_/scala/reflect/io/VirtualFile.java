package scala.reflect.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-b\u0001B\r\u001b\u0001\u0005B\u0001B\n\u0001\u0003\u0006\u0004%\ta\n\u0005\tg\u0001\u0011\t\u0011)A\u0005Q!AA\u0007\u0001BC\u0002\u0013\u0005s\u0005\u0003\u00056\u0001\t\u0005\t\u0015!\u0003)\u0011\u00151\u0004\u0001\"\u00018\u0011\u00151\u0004\u0001\"\u0001<\u0011\u0015i\u0004\u0001\"\u0011?\u0011\u0015\u0019\u0005\u0001\"\u0011E\u0011\u0019i\u0005\u0001)Q\u0005\u001d\")A\u000b\u0001C\u0001+\")a\u000b\u0001C\u0001/\")q\f\u0001C!A\")A\r\u0001C\u0001K\")\u0011\u000e\u0001C!U\")a\u000e\u0001C!_\")\u0001\u000f\u0001C\u0001c\")!\u000f\u0001C\u0001g\")A\u000f\u0001C!g\")Q\u000f\u0001C\u0001m\")!\u0010\u0001C\u0001w\"9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\t\u0001\u0011\u0005\u0011\u0011\u0002\u0005\b\u0003'\u0001A\u0011AA\u000b\u0011\u001d\ti\u0002\u0001C\u0001\u0003?\u00111BV5siV\fGNR5mK*\u00111\u0004H\u0001\u0003S>T!!\b\u0010\u0002\u000fI,g\r\\3di*\tq$A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001\u0011\u0003CA\u0012%\u001b\u0005Q\u0012BA\u0013\u001b\u00051\t%m\u001d;sC\u000e$h)\u001b7f\u0003\u0011q\u0017-\\3\u0016\u0003!\u0002\"!\u000b\u0019\u000f\u0005)r\u0003CA\u0016\u001f\u001b\u0005a#BA\u0017!\u0003\u0019a$o\\8u}%\u0011qFH\u0001\u0007!J,G-\u001a4\n\u0005E\u0012$AB*ue&twM\u0003\u00020=\u0005)a.Y7fA\u0005!\u0001/\u0019;i\u0003\u0015\u0001\u0018\r\u001e5!\u0003\u0019a\u0014N\\5u}Q\u0019\u0001(\u000f\u001e\u0011\u0005\r\u0002\u0001\"\u0002\u0014\u0006\u0001\u0004A\u0003\"\u0002\u001b\u0006\u0001\u0004ACC\u0001\u001d=\u0011\u00151c\u00011\u0001)\u0003!A\u0017m\u001d5D_\u0012,G#A \u0011\u0005\u0001\u000bU\"\u0001\u0010\n\u0005\ts\"aA%oi\u00061Q-];bYN$\"!\u0012%\u0011\u0005\u00013\u0015BA$\u001f\u0005\u001d\u0011un\u001c7fC:DQ!\u0013\u0005A\u0002)\u000bA\u0001\u001e5biB\u0011\u0001iS\u0005\u0003\u0019z\u00111!\u00118z\u0003\u001d\u0019wN\u001c;f]R\u00042\u0001Q(R\u0013\t\u0001fDA\u0003BeJ\f\u0017\u0010\u0005\u0002A%&\u00111K\b\u0002\u0005\u0005f$X-\u0001\u0005bEN|G.\u001e;f+\u0005A\u0014\u0001\u00024jY\u0016,\u0012\u0001\u0017\t\u00033vk\u0011A\u0017\u0006\u00037mS\u0011\u0001X\u0001\u0005U\u00064\u0018-\u0003\u0002_5\n!a)\u001b7f\u0003)\u0019\u0018N_3PaRLwN\\\u000b\u0002CB\u0019\u0001IY \n\u0005\rt\"AB(qi&|g.A\u0003j]B,H/F\u0001g!\tIv-\u0003\u0002i5\nY\u0011J\u001c9viN#(/Z1n\u0003\u0019yW\u000f\u001e9viV\t1\u000e\u0005\u0002ZY&\u0011QN\u0017\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\u0012k:\u001c\u0018MZ3U_\nKH/Z!se\u0006LX#\u0001(\u0002\u0013\r|g\u000e^1j]\u0016\u0014X#\u0001\u0012\u0002\u0017%\u001cH)\u001b:fGR|'/_\u000b\u0002\u000b\u0006I\u0011n\u001d,jeR,\u0018\r\\\u0001\rY\u0006\u001cH/T8eS\u001aLW\rZ\u000b\u0002oB\u0011\u0001\t_\u0005\u0003sz\u0011A\u0001T8oO\u0006A\u0011\u000e^3sCR|'/F\u0001}!\u0011i\u0018\u0011\u0001\u0012\u000f\u0005\u0001s\u0018BA@\u001f\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0001\u0002\u0006\tA\u0011\n^3sCR|'O\u0003\u0002\u0000=\u000511M]3bi\u0016$\"!a\u0003\u0011\u0007\u0001\u000bi!C\u0002\u0002\u0010y\u0011A!\u00168ji\u00061A-\u001a7fi\u0016\f!\u0002\\8pWV\u0004h*Y7f)\u0015\u0011\u0013qCA\r\u0011\u00151s\u00031\u0001)\u0011\u0019\tYb\u0006a\u0001\u000b\u0006IA-\u001b:fGR|'/_\u0001\u0014Y>|7.\u001e9OC6,WK\\2iK\u000e\\W\r\u001a\u000b\u0007\u0003C\t9#!\u000b\u0011\u0007\u0001\u000b\u0019#C\u0002\u0002&y\u0011qAT8uQ&tw\rC\u0003'1\u0001\u0007\u0001\u0006\u0003\u0004\u0002\u001ca\u0001\r!\u0012"
)
public class VirtualFile extends AbstractFile {
   private final String name;
   private final String path;
   public byte[] scala$reflect$io$VirtualFile$$content;

   public String name() {
      return this.name;
   }

   public String path() {
      return this.path;
   }

   public int hashCode() {
      return this.path().hashCode();
   }

   public boolean equals(final Object that) {
      if (!(that instanceof VirtualFile)) {
         return false;
      } else {
         String var10000 = ((VirtualFile)that).path();
         String var2 = this.path();
         if (var10000 == null) {
            if (var2 == null) {
               return true;
            }
         } else if (var10000.equals(var2)) {
            return true;
         }

         return false;
      }
   }

   public VirtualFile absolute() {
      return this;
   }

   public java.io.File file() {
      return null;
   }

   public Option sizeOption() {
      return new Some(this.scala$reflect$io$VirtualFile$$content.length);
   }

   public InputStream input() {
      return new ByteArrayInputStream(this.scala$reflect$io$VirtualFile$$content);
   }

   public OutputStream output() {
      return new ByteArrayOutputStream() {
         // $FF: synthetic field
         private final VirtualFile $outer;

         public void close() {
            super.close();
            this.$outer.scala$reflect$io$VirtualFile$$content = this.toByteArray();
         }

         public {
            if (VirtualFile.this == null) {
               throw null;
            } else {
               this.$outer = VirtualFile.this;
            }
         }
      };
   }

   public byte[] unsafeToByteArray() {
      return this.scala$reflect$io$VirtualFile$$content;
   }

   public AbstractFile container() {
      return NoAbstractFile$.MODULE$;
   }

   public boolean isDirectory() {
      return false;
   }

   public boolean isVirtual() {
      return true;
   }

   public long lastModified() {
      return 0L;
   }

   public Iterator iterator() {
      if (!this.isDirectory()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$iterator$1(this)).toString());
      } else if (.MODULE$.Iterator() == null) {
         throw null;
      } else {
         return scala.collection.Iterator..scala$collection$Iterator$$_empty;
      }
   }

   public void create() {
      throw this.unsupported();
   }

   public void delete() {
      throw this.unsupported();
   }

   public AbstractFile lookupName(final String name, final boolean directory) {
      if (!this.isDirectory()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$lookupName$1(this)).toString());
      } else {
         return null;
      }
   }

   public Nothing lookupNameUnchecked(final String name, final boolean directory) {
      return this.unsupported();
   }

   // $FF: synthetic method
   public static final String $anonfun$iterator$1(final VirtualFile $this) {
      return (new StringBuilder(18)).append("not a directory '").append($this).append("'").toString();
   }

   // $FF: synthetic method
   public static final String $anonfun$lookupName$1(final VirtualFile $this) {
      return (new StringBuilder(18)).append("not a directory '").append($this).append("'").toString();
   }

   public VirtualFile(final String name, final String path) {
      this.name = name;
      this.path = path;
      this.scala$reflect$io$VirtualFile$$content = scala.Array..MODULE$.emptyByteArray();
   }

   public VirtualFile(final String name) {
      this(name, name);
   }
}
