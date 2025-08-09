package org.apache.spark.input;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.sparkproject.guava.io.ByteStreams;
import org.sparkproject.guava.io.Closeables;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001\u0002\t\u0012\u0001iA\u0001\"\f\u0001\u0003\u0002\u0003\u0006IA\f\u0005\ts\u0001\u0011\t\u0011)A\u0005u!Aa\b\u0001B\u0001B\u0003%q\bC\u0003H\u0001\u0011\u0005\u0001\nC\u0004O\u0001\t\u0007I\u0011B(\t\rY\u0003\u0001\u0015!\u0003Q\u0011\u001d9\u0006A1A\u0005\n=Ca\u0001\u0017\u0001!\u0002\u0013\u0001\u0006\u0002C-\u0001\u0011\u000b\u0007I\u0011\u0002.\t\u0011}\u0003\u0001R1A\u0005\n\u0001D\u0001b\u001a\u0001\t\u0006\u0004%I\u0001\u001b\u0005\u0006[\u0002!\tA\u001c\u0005\u0006}\u0002!\ta \u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u0019\t9\u0002\u0001C\u0001A\n\u0011\u0002k\u001c:uC\ndW\rR1uCN#(/Z1n\u0015\t\u00112#A\u0003j]B,HO\u0003\u0002\u0015+\u0005)1\u000f]1sW*\u0011acF\u0001\u0007CB\f7\r[3\u000b\u0003a\t1a\u001c:h\u0007\u0001\u00192\u0001A\u000e\"!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fMB\u0011!E\u000b\b\u0003G!r!\u0001J\u0014\u000e\u0003\u0015R!AJ\r\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0012BA\u0015\u001e\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000b\u0017\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005%j\u0012AB5ta2LG\u000f\u0005\u00020o5\t\u0001G\u0003\u0002\u0013c)\u0011!gM\u0001\u0004Y&\u0014'B\u0001\u001b6\u0003%i\u0017\r\u001d:fIV\u001cWM\u0003\u00027+\u00051\u0001.\u00193p_BL!\u0001\u000f\u0019\u0003!\r{WNY5oK\u001aKG.Z*qY&$\u0018aB2p]R,\u0007\u0010\u001e\t\u0003wqj\u0011aM\u0005\u0003{M\u0012!\u0003V1tW\u0006#H/Z7qi\u000e{g\u000e^3yi\u0006)\u0011N\u001c3fqB\u0011\u0001)R\u0007\u0002\u0003*\u0011!iQ\u0001\u0005Y\u0006twMC\u0001E\u0003\u0011Q\u0017M^1\n\u0005\u0019\u000b%aB%oi\u0016<WM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\t%[E*\u0014\t\u0003\u0015\u0002i\u0011!\u0005\u0005\u0006[\u0011\u0001\rA\f\u0005\u0006s\u0011\u0001\rA\u000f\u0005\u0006}\u0011\u0001\raP\u0001\nG>tgMQ=uKN,\u0012\u0001\u0015\t\u00049E\u001b\u0016B\u0001*\u001e\u0005\u0015\t%O]1z!\taB+\u0003\u0002V;\t!!)\u001f;f\u0003)\u0019wN\u001c4CsR,7\u000fI\u0001\u000bgBd\u0017\u000e\u001e\"zi\u0016\u001c\u0018aC:qY&$()\u001f;fg\u0002\nQa\u001d9mSR,\u0012A\f\u0015\u0003\u0013q\u0003\"\u0001H/\n\u0005yk\"!\u0003;sC:\u001c\u0018.\u001a8u\u0003\u0011\u0019wN\u001c4\u0016\u0003\u0005\u0004\"A\u00193\u000e\u0003\rT!aX\u001b\n\u0005\u0015\u001c'!D\"p]\u001aLw-\u001e:bi&|g\u000e\u000b\u0002\u000b9\u0006!\u0001/\u0019;i+\u0005I\u0007C\u0001!k\u0013\tY\u0017I\u0001\u0004TiJLgn\u001a\u0015\u0003\u0017q\u000bAa\u001c9f]R\tq\u000e\u0005\u0002qg6\t\u0011O\u0003\u0002s\u0007\u0006\u0011\u0011n\\\u0005\u0003iF\u0014q\u0002R1uC&s\u0007/\u001e;TiJ,\u0017-\u001c\u0015\u0004\u0019Yd\bCA<{\u001b\u0005A(BA=\u0014\u0003)\tgN\\8uCRLwN\\\u0005\u0003wb\u0014QaU5oG\u0016\f\u0013!`\u0001\u0006c9\u0012d\u0006M\u0001\bi>\f%O]1z)\u0005\u0001\u0006fA\u0007wy\u00069q-\u001a;QCRDGCAA\u0004!\u0011\tI!!\u0005\u000f\t\u0005-\u0011Q\u0002\t\u0003IuI1!a\u0004\u001e\u0003\u0019\u0001&/\u001a3fM&\u00191.a\u0005\u000b\u0007\u0005=Q\u0004K\u0002\u000fmr\f\u0001cZ3u\u0007>tg-[4ve\u0006$\u0018n\u001c8)\t=1\u00181D\u0011\u0003\u0003;\tQA\r\u00183]A\u0002"
)
public class PortableDataStream implements Serializable {
   private transient CombineFileSplit split;
   private transient Configuration conf;
   private transient String path;
   private final Integer index;
   private final byte[] confBytes;
   private final byte[] splitBytes;
   private transient volatile byte bitmap$trans$0;

   private byte[] confBytes() {
      return this.confBytes;
   }

   private byte[] splitBytes() {
      return this.splitBytes;
   }

   private CombineFileSplit split$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            ByteArrayInputStream bais = new ByteArrayInputStream(this.splitBytes());
            CombineFileSplit nsplit = new CombineFileSplit();
            nsplit.readFields(new DataInputStream(bais));
            this.split = nsplit;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.split;
   }

   private CombineFileSplit split() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.split$lzycompute() : this.split;
   }

   private Configuration conf$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            ByteArrayInputStream bais = new ByteArrayInputStream(this.confBytes());
            Configuration nconf = new Configuration(false);
            nconf.readFields(new DataInputStream(bais));
            this.conf = nconf;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.conf;
   }

   private Configuration conf() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.conf$lzycompute() : this.conf;
   }

   private String path$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            Path pathp = this.split().getPath(.MODULE$.Integer2int(this.index));
            this.path = pathp.toString();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.path;
   }

   private String path() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.path$lzycompute() : this.path;
   }

   public DataInputStream open() {
      Path pathp = this.split().getPath(.MODULE$.Integer2int(this.index));
      FileSystem fs = pathp.getFileSystem(this.conf());
      return fs.open(pathp);
   }

   public byte[] toArray() {
      DataInputStream stream = this.open();

      byte[] var10000;
      try {
         var10000 = ByteStreams.toByteArray(stream);
      } finally {
         Closeables.close(stream, true);
      }

      return var10000;
   }

   public String getPath() {
      return this.path();
   }

   public Configuration getConfiguration() {
      return this.conf();
   }

   public PortableDataStream(final CombineFileSplit isplit, final TaskAttemptContext context, final Integer index) {
      this.index = index;
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      context.getConfiguration().write(new DataOutputStream(baos));
      this.confBytes = baos.toByteArray();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      isplit.write(new DataOutputStream(baos));
      this.splitBytes = baos.toByteArray();
   }
}
