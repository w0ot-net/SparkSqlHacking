package org.apache.spark.serializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import org.apache.spark.SparkConf;
import org.apache.spark.io.CompressionCodec;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.security.CryptoStreamUtils$;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BroadcastBlockId;
import org.apache.spark.storage.RDDBlockId;
import org.apache.spark.storage.ShuffleBlockBatchId;
import org.apache.spark.storage.ShuffleBlockChunkId;
import org.apache.spark.storage.ShuffleBlockId;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.storage.TempLocalBlockId;
import org.apache.spark.storage.TempShuffleBlockId;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.apache.spark.util.io.ChunkedByteBufferOutputStream;
import scala.Option;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015c!\u0002\u0010 \u0001\u0005:\u0003\u0002\u0003\u0018\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0019\t\u0011Q\u0002!\u0011!Q\u0001\nUB\u0001\"\u000f\u0001\u0003\u0002\u0003\u0006IA\u000f\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0007\u0002!\t!\u0013\u0005\u0007\u0019\u0002\u0001\u000b\u0011B'\t\u000bA\u0003A\u0011A)\t\r}\u0003\u0001\u0015!\u0003a\u0011\u0019\t\b\u0001)A\u0005e\"A\u0011Q\u0001\u0001!\u0002\u0013\t9\u0001\u0003\u0005\u0002\u000e\u0001\u0001\u000b\u0011BA\u0004\u0011!\ty\u0001\u0001Q\u0001\n\u0005\u001d\u0001\u0002CA\t\u0001\u0001\u0006I!a\u0002\t\u0015\u0005M\u0001\u0001#b\u0001\n\u0013\t)\u0002C\u0004\u0002$\u0001!\t!!\n\t\u000f\u0005\u001d\u0002\u0001\"\u0001\u0002*!9\u0011\u0011\b\u0001\u0005\u0002\u0005m\u0002bBA\u001d\u0001\u0011\u0005\u0011Q\n\u0005\b\u0003W\u0002A\u0011BA7\u0011\u001d\ty\b\u0001C\u0001\u0003\u0003Cq!a \u0001\t\u0003\t\u0019\nC\u0004\u0002 \u0002!\t!!)\t\u000f\u0005}\u0005\u0001\"\u0001\u0002&\"9\u0011\u0011\u0016\u0001\u0005\u0002\u0005-\u0006bBAU\u0001\u0011\u0005\u0011\u0011\u0017\u0005\b\u0003o\u0003A\u0011AA]\u0011\u001d\t9\u000f\u0001C\u0001\u0003SDqA!\u0004\u0001\t\u0003\u0011y\u0001C\u0004\u0003.\u0001!\tAa\f\u0003#M+'/[1mSj,'/T1oC\u001e,'O\u0003\u0002!C\u0005Q1/\u001a:jC2L'0\u001a:\u000b\u0005\t\u001a\u0013!B:qCJ\\'B\u0001\u0013&\u0003\u0019\t\u0007/Y2iK*\ta%A\u0002pe\u001e\u001c\"\u0001\u0001\u0015\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r\u0005s\u0017PU3g\u0003E!WMZ1vYR\u001cVM]5bY&TXM]\u0002\u0001!\t\t$'D\u0001 \u0013\t\u0019tD\u0001\u0006TKJL\u0017\r\\5{KJ\fAaY8oMB\u0011agN\u0007\u0002C%\u0011\u0001(\t\u0002\n'B\f'o[\"p]\u001a\fQ\"\u001a8def\u0004H/[8o\u0017\u0016L\bcA\u0015<{%\u0011AH\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007%r\u0004)\u0003\u0002@U\t)\u0011I\u001d:bsB\u0011\u0011&Q\u0005\u0003\u0005*\u0012AAQ=uK\u00061A(\u001b8jiz\"B!\u0012$H\u0011B\u0011\u0011\u0007\u0001\u0005\u0006]\u0011\u0001\r\u0001\r\u0005\u0006i\u0011\u0001\r!\u000e\u0005\u0006s\u0011\u0001\rA\u000f\u000b\u0004\u000b*[\u0005\"\u0002\u0018\u0006\u0001\u0004\u0001\u0004\"\u0002\u001b\u0006\u0001\u0004)\u0014AD6ss>\u001cVM]5bY&TXM\u001d\t\u0003c9K!aT\u0010\u0003\u001d-\u0013\u0018p\\*fe&\fG.\u001b>fe\u0006)2/\u001a;EK\u001a\fW\u000f\u001c;DY\u0006\u001c8\u000fT8bI\u0016\u0014HC\u0001*V!\tI3+\u0003\u0002UU\t!QK\\5u\u0011\u00151v\u00011\u0001X\u0003-\u0019G.Y:t\u0019>\fG-\u001a:\u0011\u0005akV\"A-\u000b\u0005i[\u0016\u0001\u00027b]\u001eT\u0011\u0001X\u0001\u0005U\u00064\u0018-\u0003\u0002_3\nY1\t\\1tg2{\u0017\rZ3s\u00039\u0019HO]5oO\u000ec\u0017m]:UC\u001e\u00042!\u00193g\u001b\u0005\u0011'BA2+\u0003\u001d\u0011XM\u001a7fGRL!!\u001a2\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"a\u001a8\u000f\u0005!d\u0007CA5+\u001b\u0005Q'BA60\u0003\u0019a$o\\8u}%\u0011QNK\u0001\u0007!J,G-\u001a4\n\u0005=\u0004(AB*ue&twM\u0003\u0002nU\u0005\u0019\u0003O]5nSRLg/Z!oIB\u0013\u0018.\\5uSZ,\u0017I\u001d:bs\u000ec\u0017m]:UC\u001e\u001c\bcA4tk&\u0011A\u000f\u001d\u0002\u0004'\u0016$\bG\u0001<z!\r\tGm\u001e\t\u0003qfd\u0001\u0001B\u0005{\u0013\u0005\u0005\t\u0011!B\u0001w\n\u0019q\fJ\u0019\u0012\u0005q|\bCA\u0015~\u0013\tq(FA\u0004O_RD\u0017N\\4\u0011\u0007%\n\t!C\u0002\u0002\u0004)\u00121!\u00118z\u0003E\u0019w.\u001c9sKN\u001c(I]8bI\u000e\f7\u000f\u001e\t\u0004S\u0005%\u0011bAA\u0006U\t9!i\\8mK\u0006t\u0017aD2p[B\u0014Xm]:TQV4g\r\\3\u0002\u0019\r|W\u000e\u001d:fgN\u0014F\rZ:\u0002)\r|W\u000e\u001d:fgN\u001c\u0006.\u001e4gY\u0016\u001c\u0006/\u001b7m\u0003A\u0019w.\u001c9sKN\u001c\u0018n\u001c8D_\u0012,7-\u0006\u0002\u0002\u0018A!\u0011\u0011DA\u0010\u001b\t\tYBC\u0002\u0002\u001e\u0005\n!![8\n\t\u0005\u0005\u00121\u0004\u0002\u0011\u0007>l\u0007O]3tg&|gnQ8eK\u000e\f\u0011#\u001a8def\u0004H/[8o\u000b:\f'\r\\3e+\t\t9!\u0001\u0006dC:,6/Z&ss>$B!a\u0002\u0002,!9\u0011Q\u0006\tA\u0002\u0005=\u0012AA2ua\u0011\t\t$!\u000e\u0011\t\u0005$\u00171\u0007\t\u0004q\u0006UBaCA\u001c\u0003W\t\t\u0011!A\u0003\u0002m\u00141a\u0018\u00134\u000359W\r^*fe&\fG.\u001b>feR)\u0001'!\u0010\u0002J!9\u0011QF\tA\u0002\u0005}\u0002\u0007BA!\u0003\u000b\u0002B!\u00193\u0002DA\u0019\u00010!\u0012\u0005\u0017\u0005\u001d\u0013QHA\u0001\u0002\u0003\u0015\ta\u001f\u0002\u0004?\u0012\"\u0004bBA&#\u0001\u0007\u0011qA\u0001\tCV$x\u000eU5dWR)\u0001'a\u0014\u0002^!9\u0011\u0011\u000b\nA\u0002\u0005M\u0013aC6fs\u000ec\u0017m]:UC\u001e\u0004D!!\u0016\u0002ZA!\u0011\rZA,!\rA\u0018\u0011\f\u0003\f\u00037\ny%!A\u0001\u0002\u000b\u00051PA\u0002`IUBq!a\u0018\u0013\u0001\u0004\t\t'A\u0007wC2,Xm\u00117bgN$\u0016m\u001a\u0019\u0005\u0003G\n9\u0007\u0005\u0003bI\u0006\u0015\u0004c\u0001=\u0002h\u0011Y\u0011\u0011NA/\u0003\u0003\u0005\tQ!\u0001|\u0005\ryFEN\u0001\u000fg\"|W\u000f\u001c3D_6\u0004(/Z:t)\u0011\t9!a\u001c\t\u000f\u0005E4\u00031\u0001\u0002t\u00059!\r\\8dW&#\u0007\u0003BA;\u0003wj!!a\u001e\u000b\u0007\u0005e\u0014%A\u0004ti>\u0014\u0018mZ3\n\t\u0005u\u0014q\u000f\u0002\b\u00052|7m[%e\u0003)9(/\u00199TiJ,\u0017-\u001c\u000b\u0007\u0003\u0007\u000bi)a$\u0011\t\u0005\u0015\u0015\u0011R\u0007\u0003\u0003\u000fS1!!\b\\\u0013\u0011\tY)a\"\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\b\u0003c\"\u0002\u0019AA:\u0011\u001d\t\t\n\u0006a\u0001\u0003\u0007\u000b\u0011a\u001d\u000b\u0007\u0003+\u000bY*!(\u0011\t\u0005\u0015\u0015qS\u0005\u0005\u00033\u000b9I\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW\u000eC\u0004\u0002rU\u0001\r!a\u001d\t\u000f\u0005EU\u00031\u0001\u0002\u0016\u0006\trO]1q\r>\u0014XI\\2ssB$\u0018n\u001c8\u0015\t\u0005\r\u00151\u0015\u0005\b\u0003#3\u0002\u0019AAB)\u0011\t)*a*\t\u000f\u0005Eu\u00031\u0001\u0002\u0016\u0006\u0011rO]1q\r>\u00148i\\7qe\u0016\u001c8/[8o)\u0019\t)*!,\u00020\"9\u0011\u0011\u000f\rA\u0002\u0005M\u0004bBAI1\u0001\u0007\u0011Q\u0013\u000b\u0007\u0003\u0007\u000b\u0019,!.\t\u000f\u0005E\u0014\u00041\u0001\u0002t!9\u0011\u0011S\rA\u0002\u0005\r\u0015a\u00053bi\u0006\u001cVM]5bY&TXm\u0015;sK\u0006lW\u0003BA^\u0003\u000f$\u0002\"!0\u0002L\u00065\u0017\u0011\u001b\u000b\u0004%\u0006}\u0006\"CAa5\u0005\u0005\t9AAb\u0003))g/\u001b3f]\u000e,G%\r\t\u0005C\u0012\f)\rE\u0002y\u0003\u000f$a!!3\u001b\u0005\u0004Y(!\u0001+\t\u000f\u0005E$\u00041\u0001\u0002t!9\u0011q\u001a\u000eA\u0002\u0005U\u0015\u0001D8viB,Ho\u0015;sK\u0006l\u0007bBAj5\u0001\u0007\u0011Q[\u0001\u0007m\u0006dW/Z:\u0011\r\u0005]\u0017\u0011]Ac\u001d\u0011\tI.!8\u000f\u0007%\fY.C\u0001,\u0013\r\tyNK\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\u0019/!:\u0003\u0011%#XM]1u_JT1!a8+\u00035!\u0017\r^1TKJL\u0017\r\\5{KV!\u00111\u001eB\u0003)\u0019\tiOa\u0002\u0003\nQ!\u0011q^A\u007f!\u0011\t\t0!?\u000e\u0005\u0005M(\u0002BA\u000f\u0003kT1!a>\"\u0003\u0011)H/\u001b7\n\t\u0005m\u00181\u001f\u0002\u0012\u0007\",hn[3e\u0005f$XMQ;gM\u0016\u0014\b\"CA\u00007\u0005\u0005\t9\u0001B\u0001\u0003))g/\u001b3f]\u000e,GE\r\t\u0005C\u0012\u0014\u0019\u0001E\u0002y\u0005\u000b!a!!3\u001c\u0005\u0004Y\bbBA97\u0001\u0007\u00111\u000f\u0005\b\u0003'\\\u0002\u0019\u0001B\u0006!\u0019\t9.!9\u0003\u0004\u0005\tC-\u0019;b'\u0016\u0014\u0018.\u00197ju\u0016<\u0016\u000e\u001e5FqBd\u0017nY5u\u00072\f7o\u001d+bORA\u0011q\u001eB\t\u0005'\u0011y\u0002C\u0004\u0002rq\u0001\r!a\u001d\t\u000f\u0005MG\u00041\u0001\u0003\u0016A\"!q\u0003B\u000e!\u0019\t9.!9\u0003\u001aA\u0019\u0001Pa\u0007\u0005\u0017\tu!1CA\u0001\u0002\u0003\u0015\ta\u001f\u0002\u0004?\u0012:\u0004b\u0002B\u00119\u0001\u0007!1E\u0001\tG2\f7o\u001d+bOB\"!Q\u0005B\u0015!\u0011\tGMa\n\u0011\u0007a\u0014I\u0003B\u0006\u0003,\t}\u0011\u0011!A\u0001\u0006\u0003Y(aA0%q\u0005)B-\u0019;b\t\u0016\u001cXM]5bY&TXm\u0015;sK\u0006lW\u0003\u0002B\u0019\u0005s!bAa\r\u0003@\t\u0005C\u0003\u0002B\u001b\u0005w\u0001b!a6\u0002b\n]\u0002c\u0001=\u0003:\u00111\u0011\u0011Z\u000fC\u0002mDqA!\t\u001e\u0001\u0004\u0011i\u0004\u0005\u0003bI\n]\u0002bBA9;\u0001\u0007\u00111\u000f\u0005\b\u0005\u0007j\u0002\u0019AAB\u0003-Ig\u000e];u'R\u0014X-Y7"
)
public class SerializerManager {
   private CompressionCodec compressionCodec;
   private final Serializer defaultSerializer;
   private final SparkConf conf;
   private final Option encryptionKey;
   private final KryoSerializer kryoSerializer;
   private final ClassTag stringClassTag;
   private final Set primitiveAndPrimitiveArrayClassTags;
   private final boolean compressBroadcast;
   private final boolean compressShuffle;
   private final boolean compressRdds;
   private final boolean compressShuffleSpill;
   private volatile boolean bitmap$0;

   public void setDefaultClassLoader(final ClassLoader classLoader) {
      this.kryoSerializer.setDefaultClassLoader(classLoader);
   }

   private CompressionCodec compressionCodec$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.compressionCodec = CompressionCodec$.MODULE$.createCodec(this.conf);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.compressionCodec;
   }

   private CompressionCodec compressionCodec() {
      return !this.bitmap$0 ? this.compressionCodec$lzycompute() : this.compressionCodec;
   }

   public boolean encryptionEnabled() {
      return this.encryptionKey.isDefined();
   }

   public boolean canUseKryo(final ClassTag ct) {
      boolean var10000;
      if (!this.primitiveAndPrimitiveArrayClassTags.contains(ct)) {
         label29: {
            ClassTag var2 = this.stringClassTag;
            if (ct == null) {
               if (var2 == null) {
                  break label29;
               }
            } else if (ct.equals(var2)) {
               break label29;
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Serializer getSerializer(final ClassTag ct, final boolean autoPick) {
      return (Serializer)(autoPick && this.canUseKryo(ct) ? this.kryoSerializer : this.defaultSerializer);
   }

   public Serializer getSerializer(final ClassTag keyClassTag, final ClassTag valueClassTag) {
      return (Serializer)(this.canUseKryo(keyClassTag) && this.canUseKryo(valueClassTag) ? this.kryoSerializer : this.defaultSerializer);
   }

   private boolean shouldCompress(final BlockId blockId) {
      if (blockId instanceof ShuffleBlockId) {
         return this.compressShuffle;
      } else if (blockId instanceof ShuffleBlockChunkId) {
         return this.compressShuffle;
      } else if (blockId instanceof BroadcastBlockId) {
         return this.compressBroadcast;
      } else if (blockId instanceof RDDBlockId) {
         return this.compressRdds;
      } else if (blockId instanceof TempLocalBlockId) {
         return this.compressShuffleSpill;
      } else if (blockId instanceof TempShuffleBlockId) {
         return this.compressShuffle;
      } else {
         return blockId instanceof ShuffleBlockBatchId ? this.compressShuffle : false;
      }
   }

   public InputStream wrapStream(final BlockId blockId, final InputStream s) {
      return this.wrapForCompression(blockId, this.wrapForEncryption(s));
   }

   public OutputStream wrapStream(final BlockId blockId, final OutputStream s) {
      return this.wrapForCompression(blockId, this.wrapForEncryption(s));
   }

   public InputStream wrapForEncryption(final InputStream s) {
      return (InputStream)this.encryptionKey.map((key) -> CryptoStreamUtils$.MODULE$.createCryptoInputStream(s, this.conf, key)).getOrElse(() -> s);
   }

   public OutputStream wrapForEncryption(final OutputStream s) {
      return (OutputStream)this.encryptionKey.map((key) -> CryptoStreamUtils$.MODULE$.createCryptoOutputStream(s, this.conf, key)).getOrElse(() -> s);
   }

   public OutputStream wrapForCompression(final BlockId blockId, final OutputStream s) {
      return this.shouldCompress(blockId) ? this.compressionCodec().compressedOutputStream(s) : s;
   }

   public InputStream wrapForCompression(final BlockId blockId, final InputStream s) {
      return this.shouldCompress(blockId) ? this.compressionCodec().compressedInputStream(s) : s;
   }

   public void dataSerializeStream(final BlockId blockId, final OutputStream outputStream, final Iterator values, final ClassTag evidence$1) {
      BufferedOutputStream byteStream = new BufferedOutputStream(outputStream);
      boolean autoPick = !(blockId instanceof StreamBlockId);
      SerializerInstance ser = this.getSerializer((ClassTag).MODULE$.implicitly(evidence$1), autoPick).newInstance();
      ser.serializeStream(this.wrapForCompression(blockId, (OutputStream)byteStream)).writeAll(values, evidence$1).close();
   }

   public ChunkedByteBuffer dataSerialize(final BlockId blockId, final Iterator values, final ClassTag evidence$2) {
      return this.dataSerializeWithExplicitClassTag(blockId, values, (ClassTag).MODULE$.implicitly(evidence$2));
   }

   public ChunkedByteBuffer dataSerializeWithExplicitClassTag(final BlockId blockId, final Iterator values, final ClassTag classTag) {
      ChunkedByteBufferOutputStream bbos = new ChunkedByteBufferOutputStream(4194304, (x$1) -> $anonfun$dataSerializeWithExplicitClassTag$1(BoxesRunTime.unboxToInt(x$1)));
      BufferedOutputStream byteStream = new BufferedOutputStream(bbos);
      boolean autoPick = !(blockId instanceof StreamBlockId);
      SerializerInstance ser = this.getSerializer(classTag, autoPick).newInstance();
      ser.serializeStream(this.wrapForCompression(blockId, (OutputStream)byteStream)).writeAll(values, scala.reflect.ClassTag..MODULE$.Any()).close();
      return bbos.toChunkedByteBuffer();
   }

   public Iterator dataDeserializeStream(final BlockId blockId, final InputStream inputStream, final ClassTag classTag) {
      BufferedInputStream stream = new BufferedInputStream(inputStream);
      boolean autoPick = !(blockId instanceof StreamBlockId);
      return this.getSerializer(classTag, autoPick).newInstance().deserializeStream(this.wrapForCompression(blockId, (InputStream)stream)).asIterator();
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$dataSerializeWithExplicitClassTag$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   public SerializerManager(final Serializer defaultSerializer, final SparkConf conf, final Option encryptionKey) {
      this.defaultSerializer = defaultSerializer;
      this.conf = conf;
      this.encryptionKey = encryptionKey;
      this.kryoSerializer = new KryoSerializer(conf);
      this.stringClassTag = (ClassTag).MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.apply(String.class));
      Set primitiveClassTags = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new ClassTag[]{scala.reflect.ClassTag..MODULE$.Boolean(), scala.reflect.ClassTag..MODULE$.Byte(), scala.reflect.ClassTag..MODULE$.Char(), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Float(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.Null(), scala.reflect.ClassTag..MODULE$.Short()})));
      Set arrayClassTags = (Set)primitiveClassTags.map((x$1) -> x$1.wrap());
      this.primitiveAndPrimitiveArrayClassTags = (Set)primitiveClassTags.$plus$plus(arrayClassTags);
      this.compressBroadcast = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.BROADCAST_COMPRESS()));
      this.compressShuffle = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_COMPRESS()));
      this.compressRdds = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.RDD_COMPRESS()));
      this.compressShuffleSpill = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SPILL_COMPRESS()));
   }

   public SerializerManager(final Serializer defaultSerializer, final SparkConf conf) {
      this(defaultSerializer, conf, scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
