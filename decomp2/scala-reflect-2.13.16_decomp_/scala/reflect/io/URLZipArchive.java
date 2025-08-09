package scala.reflect.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import scala.Function0;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;

@ScalaSignature(
   bytes = "\u0006\u000514AAD\b\u0003-!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003\u001e\u0011\u00151\u0003\u0001\"\u0001(\u0011\u0015Q\u0003\u0001\"\u0001,\u0011\u0015A\u0004\u0001\"\u0001:\u0011\u0015\u0001\u0005\u0001\"\u0001:\u0011\u0015\t\u0005\u0001\"\u0001C\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u0015i\u0005\u0001\"\u0011O\u0011\u00159\u0006\u0001\"\u0011Y\u0011\u0015a\u0006\u0001\"\u0011^\u0011\u0019\u0001\u0007\u0001)Q\u0005C\")q\r\u0001C\u0001Q\niQK\u0015'[SB\f%o\u00195jm\u0016T!\u0001E\t\u0002\u0005%|'B\u0001\n\u0014\u0003\u001d\u0011XM\u001a7fGRT\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001q\u0003\u0005\u0002\u001935\tq\"\u0003\u0002\u001b\u001f\tQ!,\u001b9Be\u000eD\u0017N^3\u0002\u0007U\u0014H.F\u0001\u001e!\tq2%D\u0001 \u0015\t\u0001\u0013%A\u0002oKRT\u0011AI\u0001\u0005U\u00064\u0018-\u0003\u0002%?\t\u0019QK\u0015'\u0002\tU\u0014H\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005!J\u0003C\u0001\r\u0001\u0011\u0015Y2\u00011\u0001\u001e\u0003!IG/\u001a:bi>\u0014X#\u0001\u0017\u0011\u00075\nDG\u0004\u0002/_5\t1#\u0003\u00021'\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001a4\u0005!IE/\u001a:bi>\u0014(B\u0001\u0019\u0014!\t)d'D\u0001\u0001\u0013\t9\u0014DA\u0003F]R\u0014\u00180\u0001\u0003oC6,W#\u0001\u001e\u0011\u0005mrT\"\u0001\u001f\u000b\u0005u\n\u0013\u0001\u00027b]\u001eL!a\u0010\u001f\u0003\rM#(/\u001b8h\u0003\u0011\u0001\u0018\r\u001e5\u0002\u000b%t\u0007/\u001e;\u0016\u0003\r\u0003\"\u0001\u0012$\u000e\u0003\u0015S!\u0001E\u0011\n\u0005\u001d+%aC%oaV$8\u000b\u001e:fC6\fA\u0002\\1ti6{G-\u001b4jK\u0012,\u0012A\u0013\t\u0003]-K!\u0001T\n\u0003\t1{gnZ\u0001\tG\u0006tW)];bYR\u0011qJ\u0015\t\u0003]AK!!U\n\u0003\u000f\t{w\u000e\\3b]\")1+\u0003a\u0001)\u0006)q\u000e\u001e5feB\u0011a&V\u0005\u0003-N\u00111!\u00118z\u0003!A\u0017m\u001d5D_\u0012,G#A-\u0011\u00059R\u0016BA.\u0014\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\u0005=s\u0006\"B0\f\u0001\u0004!\u0016\u0001\u0002;iCR\f!b\u00197pg\u0016\f'\r\\3t!\ri#\rZ\u0005\u0003GN\u0012A\u0001T5tiB\u0011A)Z\u0005\u0003M\u0016\u0013\u0011b\u00117pg\u0016\f'\r\\3\u0002\u000b\rdwn]3\u0015\u0003%\u0004\"A\f6\n\u0005-\u001c\"\u0001B+oSR\u0004"
)
public final class URLZipArchive extends ZipArchive {
   private final URL url;
   private List closeables;

   public URL url() {
      return this.url;
   }

   public Iterator iterator() {
      ZipArchive.DirEntry root = new ZipArchive.DirEntry("/");
      HashMap dirs = new HashMap();
      dirs.put("/", root);
      Streamable$ var10004 = Streamable$.MODULE$;
      Function0 bytes_is = () -> this.input();
      byte[] var12 = Streamable.Bytes.toByteArray$(new Streamable.Bytes(bytes_is) {
         private final Function0 is$1;

         public long length() {
            return Streamable.Bytes.length$(this);
         }

         public BufferedInputStream bufferedInput() {
            return Streamable.Bytes.bufferedInput$(this);
         }

         public Iterator bytes() {
            return Streamable.Bytes.bytes$(this);
         }

         public Iterator bytesAsInts() {
            return Streamable.Bytes.bytesAsInts$(this);
         }

         public byte[] toByteArray() {
            return Streamable.Bytes.toByteArray$(this);
         }

         public InputStream inputStream() {
            return (InputStream)this.is$1.apply();
         }

         public {
            this.is$1 = is$1;
         }
      });
      Object var9 = null;
      ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(var12));
      List var10001 = this.closeables;
      if (var10001 == null) {
         throw null;
      } else {
         List $colon$colon_this = var10001;
         .colon.colon var11 = new .colon.colon(in, $colon$colon_this);
         Object var10 = null;
         this.closeables = var11;
         this.loop$1(in, dirs);

         Iterator var10000;
         try {
            var10000 = root.iterator();
         } finally {
            dirs.clear();
         }

         return var10000;
      }
   }

   public String name() {
      return this.url().getFile();
   }

   public String path() {
      return this.url().getPath();
   }

   public InputStream input() {
      return this.url().openStream();
   }

   public long lastModified() {
      try {
         return this.url().openConnection().getLastModified();
      } catch (IOException var1) {
         return 0L;
      }
   }

   public boolean canEqual(final Object other) {
      return other instanceof URLZipArchive;
   }

   public int hashCode() {
      return this.url().hashCode();
   }

   public boolean equals(final Object that) {
      if (!(that instanceof URLZipArchive)) {
         return false;
      } else {
         URLZipArchive var2 = (URLZipArchive)that;
         URL var10000 = this.url();
         URL var3 = var2.url();
         if (var10000 == null) {
            if (var3 == null) {
               return true;
            }
         } else if (var10000.equals(var3)) {
            return true;
         }

         return false;
      }
   }

   public void close() {
      List var10000 = this.closeables;
      if (var10000 == null) {
         throw null;
      } else {
         for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            ((Closeable)foreach_these.head()).close();
         }

      }
   }

   private final void loop$1(final ZipInputStream in$1, final HashMap dirs$2) {
      while(true) {
         ZipEntry zipEntry = in$1.getNextEntry();
         if (zipEntry == null) {
            return;
         }

         ZipArchive.DirEntry dir = this.getDir(dirs$2, zipEntry);
         if (!zipEntry.isDirectory()) {
            class EmptyFileEntry$1 extends ZipArchive.Entry {
               public byte[] toByteArray() {
                  return null;
               }

               public Some sizeOption() {
                  return new Some(0);
               }

               public EmptyFileEntry$1(final ZipEntry zipEntry$1) {
                  super(zipEntry$1.getName());
               }
            }


            class FileEntry$1 extends ZipArchive.Entry {
               private final byte[] toByteArray;
               private final ZipEntry zipEntry$1;
               private final ZipInputStream in$1;

               public byte[] toByteArray() {
                  return this.toByteArray;
               }

               public Some sizeOption() {
                  return new Some((int)this.zipEntry$1.getSize());
               }

               private final void loop$2(final IntRef offset$1, final int len$1, final byte[] arr$1) {
                  while(true) {
                     if (offset$1.elem < len$1) {
                        int read = this.in$1.read(arr$1, offset$1.elem, len$1 - offset$1.elem);
                        if (read >= 0) {
                           offset$1.elem += read;
                           continue;
                        }
                     }

                     return;
                  }
               }

               public FileEntry$1(final ZipEntry zipEntry$1, final ZipInputStream in$1) {
                  super(zipEntry$1.getName());
                  this.zipEntry$1 = zipEntry$1;
                  this.in$1 = in$1;
                  int len = (int)zipEntry$1.getSize();
                  byte[] arr = len == 0 ? scala.Array..MODULE$.emptyByteArray() : new byte[len];

                  int loop$2_read;
                  int var7;
                  for(var7 = 0; var7 < len; var7 += loop$2_read) {
                     loop$2_read = this.in$1.read(arr, var7, len - var7);
                     if (loop$2_read < 0) {
                        break;
                     }
                  }

                  if (var7 == arr.length) {
                     this.toByteArray = arr;
                  } else {
                     throw new IOException(scala.collection.StringOps..MODULE$.format$extension("Input stream truncated: read %d of %d bytes", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var7, len})));
                  }
               }
            }

            ZipArchive.Entry f = (ZipArchive.Entry)(zipEntry.getSize() == 0L ? new EmptyFileEntry$1(zipEntry) : new FileEntry$1(zipEntry, in$1));
            dir.entries().update(f.name(), f);
         }

         in$1.closeEntry();
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$close$2(final Closeable x$3) {
      x$3.close();
   }

   public URLZipArchive(final URL url) {
      super((java.io.File)null);
      this.url = url;
      this.closeables = scala.collection.immutable.Nil..MODULE$;
   }

   // $FF: synthetic method
   public static final Object $anonfun$close$2$adapted(final Closeable x$3) {
      $anonfun$close$2(x$3);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
