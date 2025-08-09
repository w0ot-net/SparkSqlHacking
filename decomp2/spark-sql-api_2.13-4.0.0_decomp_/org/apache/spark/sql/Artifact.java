package org.apache.spark.sql;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import scala.MatchError;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005e!B\u0013'\u0001\u0019r\u0003\u0002C\u001b\u0001\u0005\u000b\u0007I\u0011A\u001c\t\u0011\t\u0003!\u0011!Q\u0001\naB\u0001b\u0011\u0001\u0003\u0006\u0004%\t\u0001\u0012\u0005\n\u0003s\u0002!\u0011!Q\u0001\n\u0015CaA\u0016\u0001\u0005\n\u0005m\u0004\u0002\u0003>\u0001\u0011\u000b\u0007I\u0011A>\b\rI3\u0003\u0012\u0001\u0014T\r\u0019)c\u0005#\u0001'+\")a\u000b\u0003C\u0001/\"9\u0001\f\u0003b\u0001\n\u00039\u0004BB-\tA\u0003%\u0001\bC\u0004[\u0011\t\u0007I\u0011A\u001c\t\rmC\u0001\u0015!\u00039\u0011\u001da\u0006B1A\u0005\u0002]Ba!\u0018\u0005!\u0002\u0013A\u0004\"\u00020\t\t\u0003y\u0006bBA\u0012\u0011\u0011\u0005\u0011Q\u0005\u0005\b\u0003\u0013BA\u0011AA&\u0011\u001d\t\t\u0006\u0003C\u0001\u0003'Bq!!\u0017\t\t\u0003\tY\u0006C\u0004\u0002d!!\t!!\u001a\t\u000f\u0005%\u0004\u0002\"\u0003\u0002l\u00199\u0001\u000f\u0003I\u0001$C\t\b\"\u0002:\u0018\r\u0003\u0019\b\"\u0002>\u0018\r\u0003YhABA\r\u0011\u0001\tY\u0002\u0003\u000565\t\u0015\r\u0011\"\u00018\u0011!\u0011%D!A!\u0002\u0013A\u0004B\u0002,\u001b\t\u0003\ti\u0002C\u0003{5\u0011\u00053\u0010C\u0003s5\u0011\u00053O\u0002\u0004\u0002\u0002!\u0001\u00111\u0001\u0005\u000b\u0003\u000b\u0001#\u0011!Q\u0001\n\u0005\u001d\u0001B\u0002,!\t\u0003\t\u0019\u0002C\u0003{A\u0011\u00053\u0010C\u0003sA\u0011\u00053O\u0001\u0005BeRLg-Y2u\u0015\t9\u0003&A\u0002tc2T!!\u000b\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u0005-b\u0013AB1qC\u000eDWMC\u0001.\u0003\ry'oZ\n\u0003\u0001=\u0002\"\u0001M\u001a\u000e\u0003ER\u0011AM\u0001\u0006g\u000e\fG.Y\u0005\u0003iE\u0012a!\u00118z%\u00164\u0017\u0001\u00029bi\"\u001c\u0001!F\u00019!\tI\u0004)D\u0001;\u0015\tYD(\u0001\u0003gS2,'BA\u001f?\u0003\rq\u0017n\u001c\u0006\u0002\u007f\u0005!!.\u0019<b\u0013\t\t%H\u0001\u0003QCRD\u0017!\u00029bi\"\u0004\u0013aB:u_J\fw-Z\u000b\u0002\u000bB\u0011ai\u0006\b\u0003\u000f\u001eq!\u0001S)\u000f\u0005%\u0003fB\u0001&P\u001d\tYe*D\u0001M\u0015\tie'\u0001\u0004=e>|GOP\u0005\u0002[%\u00111\u0006L\u0005\u0003S)J!a\n\u0015\u0002\u0011\u0005\u0013H/\u001b4bGR\u0004\"\u0001\u0016\u0005\u000e\u0003\u0019\u001a\"\u0001C\u0018\u0002\rqJg.\u001b;?)\u0005\u0019\u0016\u0001D\"M\u0003N\u001bv\f\u0015*F\r&C\u0016!D\"M\u0003N\u001bv\f\u0015*F\r&C\u0006%\u0001\u0006K\u0003J{\u0006KU#G\u0013b\u000b1BS!S?B\u0013VIR%YA\u0005a1)Q\"I\u000b~\u0003&+\u0012$J1\u0006i1)Q\"I\u000b~\u0003&+\u0012$J1\u0002\n\u0001D\\3x\u0003J$\u0018NZ1di\u001a\u0013x.\\#yi\u0016t7/[8o)\u0011\u0001\u0017m[7\u0011\u0005Q\u0003\u0001\"\u00022\u0011\u0001\u0004\u0019\u0017\u0001\u00034jY\u0016t\u0015-\\3\u0011\u0005\u0011DgBA3g!\tY\u0015'\u0003\u0002hc\u00051\u0001K]3eK\u001aL!!\u001b6\u0003\rM#(/\u001b8h\u0015\t9\u0017\u0007C\u0003m!\u0001\u0007\u0001(\u0001\buCJ<W\r\u001e$jY\u0016\u0004\u0016\r\u001e5\t\u000b\r\u0003\u0002\u0019\u00018\u0011\u0005=<R\"\u0001\u0005\u0003\u00131{7-\u00197ECR\f7CA\f0\u0003\u0019\u0019HO]3b[V\tA\u000f\u0005\u0002vq6\taO\u0003\u0002x}\u0005\u0011\u0011n\\\u0005\u0003sZ\u00141\"\u00138qkR\u001cFO]3b[\u0006!1/\u001b>f+\u0005a\bC\u0001\u0019~\u0013\tq\u0018G\u0001\u0003M_:<\u0017fA\f!5\tA\u0011J\\'f[>\u0014\u0018pE\u0002!_9\fQAY=uKN\u0004R\u0001MA\u0005\u0003\u001bI1!a\u00032\u0005\u0015\t%O]1z!\r\u0001\u0014qB\u0005\u0004\u0003#\t$\u0001\u0002\"zi\u0016$B!!\u0006\u0002\u0018A\u0011q\u000e\t\u0005\b\u0003\u000b\u0011\u0003\u0019AA\u0004\u0005%aunY1m\r&dWmE\u0002\u001b_9$B!a\b\u0002\"A\u0011qN\u0007\u0005\u0006ku\u0001\r\u0001O\u0001\u000fa\u0006\u00148/Z!si&4\u0017m\u0019;t)\u0011\t9#!\u000f\u0011\u000b\u0005%\u00121\u00071\u000f\t\u0005-\u0012q\u0006\b\u0004\u0017\u00065\u0012\"\u0001\u001a\n\u0007\u0005E\u0012'A\u0004qC\u000e\\\u0017mZ3\n\t\u0005U\u0012q\u0007\u0002\u0004'\u0016\f(bAA\u0019c!9\u00111H\tA\u0002\u0005u\u0012aA;sSB!\u0011qHA#\u001b\t\t\tEC\u0002\u0002Dy\n1A\\3u\u0013\u0011\t9%!\u0011\u0003\u0007U\u0013\u0016*\u0001\boK^T\u0015M]!si&4\u0017m\u0019;\u0015\u000b\u0001\fi%a\u0014\t\u000b1\u0014\u0002\u0019\u0001\u001d\t\u000b\r\u0013\u0002\u0019\u00018\u0002!9,wo\u00117bgN\f%\u000f^5gC\u000e$H#\u00021\u0002V\u0005]\u0003\"\u00027\u0014\u0001\u0004A\u0004\"B\"\u0014\u0001\u0004q\u0017\u0001\u00058fo\u000e\u000b7\r[3BeRLg-Y2u)\u0015\u0001\u0017QLA1\u0011\u0019\ty\u0006\u0006a\u0001G\u0006\u0011\u0011\u000e\u001a\u0005\u0006\u0007R\u0001\rA\\\u0001\u0010]\u0016<\u0018J^=BeRLg-Y2ugR!\u0011qEA4\u0011\u001d\tY$\u0006a\u0001\u0003{\t1B\\3x\u0003J$\u0018NZ1diRI\u0001-!\u001c\u0002r\u0005U\u0014q\u000f\u0005\u0007\u0003_2\u0002\u0019\u0001\u001d\u0002\rA\u0014XMZ5y\u0011\u0019\t\u0019H\u0006a\u0001G\u0006q!/Z9vSJ,GmU;gM&D\b\"\u00027\u0017\u0001\u0004A\u0004\"B\"\u0017\u0001\u0004q\u0017\u0001C:u_J\fw-\u001a\u0011\u0015\u000b\u0001\fi(a \t\u000bU*\u0001\u0019\u0001\u001d\t\u000b\r+\u0001\u0019A#"
)
public class Artifact {
   private long size;
   private final Path path;
   private final LocalData storage;
   private volatile boolean bitmap$0;

   public static Seq newIvyArtifacts(final URI uri) {
      return Artifact$.MODULE$.newIvyArtifacts(uri);
   }

   public static Artifact newCacheArtifact(final String id, final LocalData storage) {
      return Artifact$.MODULE$.newCacheArtifact(id, storage);
   }

   public static Artifact newClassArtifact(final Path targetFilePath, final LocalData storage) {
      return Artifact$.MODULE$.newClassArtifact(targetFilePath, storage);
   }

   public static Artifact newJarArtifact(final Path targetFilePath, final LocalData storage) {
      return Artifact$.MODULE$.newJarArtifact(targetFilePath, storage);
   }

   public static Seq parseArtifacts(final URI uri) {
      return Artifact$.MODULE$.parseArtifacts(uri);
   }

   public static Artifact newArtifactFromExtension(final String fileName, final Path targetFilePath, final LocalData storage) {
      return Artifact$.MODULE$.newArtifactFromExtension(fileName, targetFilePath, storage);
   }

   public static Path CACHE_PREFIX() {
      return Artifact$.MODULE$.CACHE_PREFIX();
   }

   public static Path JAR_PREFIX() {
      return Artifact$.MODULE$.JAR_PREFIX();
   }

   public static Path CLASS_PREFIX() {
      return Artifact$.MODULE$.CLASS_PREFIX();
   }

   public Path path() {
      return this.path;
   }

   public LocalData storage() {
      return this.storage;
   }

   private long size$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            LocalData var4 = this.storage();
            if (var4 == null) {
               throw new MatchError(var4);
            }

            this.size = var4.size();
            this.bitmap$0 = true;
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.size;
   }

   public long size() {
      return !this.bitmap$0 ? this.size$lzycompute() : this.size;
   }

   public Artifact(final Path path, final LocalData storage) {
      this.path = path;
      this.storage = storage;
      .MODULE$.require(!path.isAbsolute(), () -> "Bad path: " + this.path());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class LocalFile implements LocalData {
      private final Path path;

      public Path path() {
         return this.path;
      }

      public long size() {
         return Files.size(this.path());
      }

      public InputStream stream() {
         return Files.newInputStream(this.path());
      }

      public LocalFile(final Path path) {
         this.path = path;
      }
   }

   public static class InMemory implements LocalData {
      private final byte[] bytes;

      public long size() {
         return (long)this.bytes.length;
      }

      public InputStream stream() {
         return new ByteArrayInputStream(this.bytes);
      }

      public InMemory(final byte[] bytes) {
         this.bytes = bytes;
      }
   }

   public interface LocalData {
      InputStream stream();

      long size();
   }
}
