package org.apache.spark.broadcast;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.zip.Adler32;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.internal.MDC;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.storage.BlockData;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.BlockResult;
import org.apache.spark.storage.BroadcastBlockId;
import org.apache.spark.storage.BroadcastBlockId$;
import org.apache.spark.storage.ByteBufferBlockData;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tmd!B\u00193\u0001QR\u0004\u0002C/\u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u0013y\u0003!\u0011!Q\u0001\n}\u0013\u0007\u0002C2\u0001\u0005\u0003\u0005\u000b\u0011\u00023\t\u0011\u001d\u0004!1!Q\u0001\f!DQA\u001c\u0001\u0005\u0002=D\u0011B\u001e\u0001A\u0002\u0003\u0007I\u0011B<\t\u0017\u0005\u0005\u0001\u00011AA\u0002\u0013%\u00111\u0001\u0005\u000b\u0003\u001f\u0001\u0001\u0019!A!B\u0013A\bbCA\r\u0001\u0001\u0007\t\u0019!C\u0005\u00037A1\"!\f\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u00020!Y\u00111\u0007\u0001A\u0002\u0003\u0005\u000b\u0015BA\u000f\u0011-\t9\u0004\u0001a\u0001\u0002\u0004%I!!\u000f\t\u0017\u0005\u0005\u0003\u00011AA\u0002\u0013%\u00111\t\u0005\f\u0003\u000f\u0002\u0001\u0019!A!B\u0013\tY\u0004C\u0006\u0002L\u0001\u0001\r\u00111A\u0005\n\u00055\u0003bCA(\u0001\u0001\u0007\t\u0019!C\u0005\u0003#B!\"!\u0016\u0001\u0001\u0004\u0005\t\u0015)\u0003e\u0011%\tI\u0006\u0001a\u0001\n\u0013\ti\u0005C\u0005\u0002\\\u0001\u0001\r\u0011\"\u0003\u0002^!9\u0011\u0011\r\u0001!B\u0013!\u0007bBA2\u0001\u0011%\u0011Q\r\u0005\n\u0003g\u0002!\u0019!C\u0005\u0003kB\u0001\"a!\u0001A\u0003%\u0011q\u000f\u0005\n\u0003\u000b\u0003!\u0019!C\u0005\u0003sA\u0001\"a\"\u0001A\u0003%\u00111\b\u0005\f\u0003\u0013\u0003\u0001\u0019!a\u0001\n\u0013\tY\tC\u0006\u0002\u0014\u0002\u0001\r\u00111A\u0005\n\u0005U\u0005bCAM\u0001\u0001\u0007\t\u0011)Q\u0005\u0003\u001bCq!a'\u0001\t#\ni\nC\u0004\u0002 \u0002!I!!)\t\u000f\u0005M\u0006\u0001\"\u0003\u00026\"9\u00111\u0018\u0001\u0005\n\u0005u\u0006bBAd\u0001\u0011E\u0013\u0011\u001a\u0005\b\u0003\u001f\u0004A\u0011KAi\u0011\u001d\t)\u000e\u0001C\u0005\u0003/Dq!a9\u0001\t\u0013\ti\nC\u0004\u0002f\u0002!I!a:\t\u0011\u0005M\b\u0001\"\u00015\u0003\u001bBQ\"!>\u0001!\u0003\r\t\u0011!C\u0005\u0003o\u0014waBA}e!%\u00111 \u0004\u0007cIBI!!@\t\r9LC\u0011\u0001B\u0003\u0011%\u00119!\u000bb\u0001\n\u0013\u0011I\u0001\u0003\u0005\u0003\u0018%\u0002\u000b\u0011\u0002B\u0006\u0011\u001d\u0011I\"\u000bC\u0001\u00057AqA!\u0011*\t\u0003\u0011\u0019\u0005C\u0004\u0003d%\"\tA!\u001a\t\u0013\t=\u0014&!A\u0005\n\tE$\u0001\u0005+peJ,g\u000e\u001e\"s_\u0006$7-Y:u\u0015\t\u0019D'A\u0005ce>\fGmY1ti*\u0011QGN\u0001\u0006gB\f'o\u001b\u0006\u0003oa\na!\u00199bG\",'\"A\u001d\u0002\u0007=\u0014x-\u0006\u0002<\u0005N!\u0001\u0001P(V!\rid\bQ\u0007\u0002e%\u0011qH\r\u0002\n\u0005J|\u0017\rZ2bgR\u0004\"!\u0011\"\r\u0001\u0011)1\t\u0001b\u0001\u000b\n\tAk\u0001\u0001\u0012\u0005\u0019c\u0005CA$K\u001b\u0005A%\"A%\u0002\u000bM\u001c\u0017\r\\1\n\u0005-C%a\u0002(pi\"Lgn\u001a\t\u0003\u000f6K!A\u0014%\u0003\u0007\u0005s\u0017\u0010\u0005\u0002Q'6\t\u0011K\u0003\u0002Si\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002U#\n9Aj\\4hS:<\u0007C\u0001,\\\u001b\u00059&B\u0001-Z\u0003\tIwNC\u0001[\u0003\u0011Q\u0017M^1\n\u0005q;&\u0001D*fe&\fG.\u001b>bE2,\u0017aA8cU\u0006\u0011\u0011\u000e\u001a\t\u0003\u000f\u0002L!!\u0019%\u0003\t1{gnZ\u0005\u0003=z\nab]3sS\u0006d\u0017N_3e\u001f:d\u0017\u0010\u0005\u0002HK&\u0011a\r\u0013\u0002\b\u0005>|G.Z1o\u0003))g/\u001b3f]\u000e,G%\r\t\u0004S2\u0004U\"\u00016\u000b\u0005-D\u0015a\u0002:fM2,7\r^\u0005\u0003[*\u0014\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\tA\u001cH/\u001e\u000b\u0003cJ\u00042!\u0010\u0001A\u0011\u00159W\u0001q\u0001i\u0011\u0015iV\u00011\u0001A\u0011\u0015qV\u00011\u0001`\u0011\u0015\u0019W\u00011\u0001e\u0003\u0019yf/\u00197vKV\t\u0001\u0010E\u0002z}\u0002k\u0011A\u001f\u0006\u0003wr\f1A]3g\u0015\ti\u0018,\u0001\u0003mC:<\u0017BA@{\u0005%\u0011VMZ3sK:\u001cW-\u0001\u0006`m\u0006dW/Z0%KF$B!!\u0002\u0002\fA\u0019q)a\u0002\n\u0007\u0005%\u0001J\u0001\u0003V]&$\b\u0002CA\u0007\u000f\u0005\u0005\t\u0019\u0001=\u0002\u0007a$\u0013'A\u0004`m\u0006dW/\u001a\u0011)\u0007!\t\u0019\u0002E\u0002H\u0003+I1!a\u0006I\u0005%!(/\u00198tS\u0016tG/\u0001\td_6\u0004(/Z:tS>t7i\u001c3fGV\u0011\u0011Q\u0004\t\u0006\u000f\u0006}\u00111E\u0005\u0004\u0003CA%AB(qi&|g\u000e\u0005\u0003\u0002&\u0005%RBAA\u0014\u0015\tAF'\u0003\u0003\u0002,\u0005\u001d\"\u0001E\"p[B\u0014Xm]:j_:\u001cu\u000eZ3d\u0003Q\u0019w.\u001c9sKN\u001c\u0018n\u001c8D_\u0012,7m\u0018\u0013fcR!\u0011QAA\u0019\u0011%\tiACA\u0001\u0002\u0004\ti\"A\td_6\u0004(/Z:tS>t7i\u001c3fG\u0002B3aCA\n\u0003%\u0011Gn\\2l'&TX-\u0006\u0002\u0002<A\u0019q)!\u0010\n\u0007\u0005}\u0002JA\u0002J]R\fQB\u00197pG.\u001c\u0016N_3`I\u0015\fH\u0003BA\u0003\u0003\u000bB\u0011\"!\u0004\u000e\u0003\u0003\u0005\r!a\u000f\u0002\u0015\tdwnY6TSj,\u0007\u0005K\u0002\u000f\u0003'\tQ\"[:M_\u000e\fG.T1ti\u0016\u0014X#\u00013\u0002#%\u001cHj\\2bY6\u000b7\u000f^3s?\u0012*\u0017\u000f\u0006\u0003\u0002\u0006\u0005M\u0003\u0002CA\u0007!\u0005\u0005\t\u0019\u00013\u0002\u001d%\u001cHj\\2bY6\u000b7\u000f^3sA!\u001a\u0011#a\u0005\u0002\u001f\rDWmY6tk6,e.\u00192mK\u0012\f1c\u00195fG.\u001cX/\\#oC\ndW\rZ0%KF$B!!\u0002\u0002`!A\u0011QB\n\u0002\u0002\u0003\u0007A-\u0001\tdQ\u0016\u001c7n];n\u000b:\f'\r\\3eA\u000591/\u001a;D_:4G\u0003BA\u0003\u0003OBq!!\u001b\u0016\u0001\u0004\tY'\u0001\u0003d_:4\u0007\u0003BA7\u0003_j\u0011\u0001N\u0005\u0004\u0003c\"$!C*qCJ\\7i\u001c8g\u0003-\u0011'o\\1eG\u0006\u001cH/\u00133\u0016\u0005\u0005]\u0004\u0003BA=\u0003\u007fj!!a\u001f\u000b\u0007\u0005uD'A\u0004ti>\u0014\u0018mZ3\n\t\u0005\u0005\u00151\u0010\u0002\u0011\u0005J|\u0017\rZ2bgR\u0014En\\2l\u0013\u0012\fAB\u0019:pC\u0012\u001c\u0017m\u001d;JI\u0002\n\u0011B\\;n\u00052|7m[:\u0002\u00159,XN\u00117pG.\u001c\b%A\u0005dQ\u0016\u001c7n];ngV\u0011\u0011Q\u0012\t\u0006\u000f\u0006=\u00151H\u0005\u0004\u0003#C%!B!se\u0006L\u0018!D2iK\u000e\\7/^7t?\u0012*\u0017\u000f\u0006\u0003\u0002\u0006\u0005]\u0005\"CA\u00077\u0005\u0005\t\u0019AAG\u0003)\u0019\u0007.Z2lgVl7\u000fI\u0001\tO\u0016$h+\u00197vKR\t\u0001)\u0001\u0007dC2\u001c7\t[3dWN,X\u000e\u0006\u0003\u0002<\u0005\r\u0006bBAS=\u0001\u0007\u0011qU\u0001\u0006E2|7m\u001b\t\u0005\u0003S\u000by+\u0004\u0002\u0002,*\u0019\u0011QV-\u0002\u00079Lw.\u0003\u0003\u00022\u0006-&A\u0003\"zi\u0016\u0014UO\u001a4fe\u0006YqO]5uK\ncwnY6t)\u0011\tY$a.\t\r\u0005ev\u00041\u0001A\u0003\u00151\u0018\r\\;f\u0003)\u0011X-\u00193CY>\u001c7n\u001d\u000b\u0003\u0003\u007f\u0003RaRAH\u0003\u0003\u0004B!!\u001f\u0002D&!\u0011QYA>\u0005%\u0011En\\2l\t\u0006$\u0018-A\u0006e_Vs\u0007/\u001a:tSN$H\u0003BA\u0003\u0003\u0017Da!!4\"\u0001\u0004!\u0017\u0001\u00032m_\u000e\\\u0017N\\4\u0002\u0013\u0011|G)Z:ue>LH\u0003BA\u0003\u0003'Da!!4#\u0001\u0004!\u0017aC<sSR,wJ\u00196fGR$B!!\u0002\u0002Z\"9\u00111\\\u0012A\u0002\u0005u\u0017aA8viB\u0019a+a8\n\u0007\u0005\u0005xK\u0001\nPE*,7\r^(viB,Ho\u0015;sK\u0006l\u0017A\u0005:fC\u0012\u0014%o\\1eG\u0006\u001cHO\u00117pG.\fqC]3mK\u0006\u001cXM\u00117pG.l\u0015M\\1hKJdunY6\u0015\t\u0005\u0015\u0011\u0011\u001e\u0005\b\u0003W,\u0003\u0019AAw\u0003\u001d\u0011Gn\\2l\u0013\u0012\u0004B!!\u001f\u0002p&!\u0011\u0011_A>\u0005\u001d\u0011En\\2l\u0013\u0012\fa\u0002[1t\u0007\u0006\u001c\u0007.\u001a3WC2,X-\u0001\u0005tkB,'\u000fJ5e+\u0005y\u0016\u0001\u0005+peJ,g\u000e\u001e\"s_\u0006$7-Y:u!\ti\u0014fE\u0003*\u0003\u007f|U\u000bE\u0002H\u0005\u0003I1Aa\u0001I\u0005\u0019\te.\u001f*fMR\u0011\u00111`\u0001\u0015i>\u0014(/\u001a8u\u0005J|\u0017\rZ2bgRdunY6\u0016\u0005\t-\u0001C\u0002B\u0007\u0005'\t9(\u0004\u0002\u0003\u0010)\u0019!\u0011\u0003\u001b\u0002\tU$\u0018\u000e\\\u0005\u0005\u0005+\u0011yAA\u0004LKfdunY6\u0002+Q|'O]3oi\n\u0013x.\u00193dCN$Hj\\2lA\u0005q!\r\\8dW&4\u0017p\u00142kK\u000e$X\u0003\u0002B\u000f\u0005W!\"Ba\b\u0003.\t=\"\u0011\u0007B )\u0011\u0011\tCa\t\u0011\u000b\u001d\u000by)a*\t\u0013\t\u0015R&!AA\u0004\t\u001d\u0012AC3wS\u0012,gnY3%eA!\u0011\u000e\u001cB\u0015!\r\t%1\u0006\u0003\u0006\u00076\u0012\r!\u0012\u0005\u0007;6\u0002\rA!\u000b\t\u000f\u0005]R\u00061\u0001\u0002<!9!1G\u0017A\u0002\tU\u0012AC:fe&\fG.\u001b>feB!!q\u0007B\u001e\u001b\t\u0011IDC\u0002\u00034QJAA!\u0010\u0003:\tQ1+\u001a:jC2L'0\u001a:\t\u000f\u0005eQ\u00061\u0001\u0002\u001e\u0005\u0001RO\u001c\"m_\u000e\\\u0017NZ=PE*,7\r^\u000b\u0005\u0005\u000b\u0012Y\u0005\u0006\u0005\u0003H\tM#q\fB1)\u0011\u0011IE!\u0014\u0011\u0007\u0005\u0013Y\u0005B\u0003D]\t\u0007Q\tC\u0005\u0003P9\n\t\u0011q\u0001\u0003R\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\t%d'\u0011\n\u0005\b\u0005+r\u0003\u0019\u0001B,\u0003\u0019\u0011Gn\\2lgB)q)a$\u0003ZA\u0019aKa\u0017\n\u0007\tusKA\u0006J]B,Ho\u0015;sK\u0006l\u0007b\u0002B\u001a]\u0001\u0007!Q\u0007\u0005\b\u00033q\u0003\u0019AA\u000f\u0003%)h\u000e]3sg&\u001cH\u000f\u0006\u0005\u0002\u0006\t\u001d$\u0011\u000eB7\u0011\u0015qv\u00061\u0001`\u0011\u0019\u0011Yg\fa\u0001I\u0006\u0001\"/Z7pm\u00164%o\\7Ee&4XM\u001d\u0005\u0007\u0003\u001b|\u0003\u0019\u00013\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tM\u0004\u0003\u0002B;\u0005oj\u0011\u0001`\u0005\u0004\u0005sb(AB(cU\u0016\u001cG\u000f"
)
public class TorrentBroadcast extends Broadcast {
   private final boolean serializedOnly;
   private final ClassTag evidence$1;
   private transient Reference _value;
   private transient Option compressionCodec;
   private transient int blockSize;
   private transient boolean isLocalMaster;
   private boolean checksumEnabled;
   private final BroadcastBlockId broadcastId;
   private final int numBlocks;
   private int[] checksums;

   public static Object unBlockifyObject(final InputStream[] blocks, final Serializer serializer, final Option compressionCodec, final ClassTag evidence$3) {
      return TorrentBroadcast$.MODULE$.unBlockifyObject(blocks, serializer, compressionCodec, evidence$3);
   }

   public static ByteBuffer[] blockifyObject(final Object obj, final int blockSize, final Serializer serializer, final Option compressionCodec, final ClassTag evidence$2) {
      return TorrentBroadcast$.MODULE$.blockifyObject(obj, blockSize, serializer, compressionCodec, evidence$2);
   }

   // $FF: synthetic method
   private long super$id() {
      return super.id();
   }

   private Reference _value() {
      return this._value;
   }

   private void _value_$eq(final Reference x$1) {
      this._value = x$1;
   }

   private Option compressionCodec() {
      return this.compressionCodec;
   }

   private void compressionCodec_$eq(final Option x$1) {
      this.compressionCodec = x$1;
   }

   private int blockSize() {
      return this.blockSize;
   }

   private void blockSize_$eq(final int x$1) {
      this.blockSize = x$1;
   }

   private boolean isLocalMaster() {
      return this.isLocalMaster;
   }

   private void isLocalMaster_$eq(final boolean x$1) {
      this.isLocalMaster = x$1;
   }

   private boolean checksumEnabled() {
      return this.checksumEnabled;
   }

   private void checksumEnabled_$eq(final boolean x$1) {
      this.checksumEnabled = x$1;
   }

   private void setConf(final SparkConf conf) {
      this.compressionCodec_$eq((Option)(BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.BROADCAST_COMPRESS())) ? new Some(CompressionCodec$.MODULE$.createCodec(conf)) : .MODULE$));
      this.blockSize_$eq((int)BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.BROADCAST_BLOCKSIZE())) * 1024);
      this.checksumEnabled_$eq(BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.BROADCAST_CHECKSUM())));
      this.isLocalMaster_$eq(Utils$.MODULE$.isLocalMaster(conf));
   }

   private BroadcastBlockId broadcastId() {
      return this.broadcastId;
   }

   private int numBlocks() {
      return this.numBlocks;
   }

   private int[] checksums() {
      return this.checksums;
   }

   private void checksums_$eq(final int[] x$1) {
      this.checksums = x$1;
   }

   public synchronized Object getValue() {
      Object memoized = this._value() == null ? null : this._value().get();
      if (memoized != null) {
         return memoized;
      } else {
         Object newlyRead = this.readBroadcastBlock();
         this._value_$eq((Reference)(this.serializedOnly ? new WeakReference(newlyRead) : new SoftReference(newlyRead)));
         return newlyRead;
      }
   }

   private int calcChecksum(final ByteBuffer block) {
      Adler32 adler = new Adler32();
      if (block.hasArray()) {
         adler.update(block.array(), block.arrayOffset() + block.position(), block.limit() - block.position());
      } else {
         byte[] bytes = new byte[block.remaining()];
         block.duplicate().get(bytes);
         adler.update(bytes);
      }

      return (int)adler.getValue();
   }

   private int writeBlocks(final Object value) {
      BlockManager blockManager = SparkEnv$.MODULE$.get().blockManager();
      if (this.serializedOnly && !this.isLocalMaster()) {
         this._value_$eq(new WeakReference(value));
      } else if (!blockManager.putSingle(this.broadcastId(), value, org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK(), false, this.evidence$1)) {
         throw org.apache.spark.SparkException..MODULE$.internalError("Failed to store " + this.broadcastId() + " in BlockManager", "BROADCAST");
      }

      try {
         ByteBuffer[] blocks = TorrentBroadcast$.MODULE$.blockifyObject(value, this.blockSize(), SparkEnv$.MODULE$.get().serializer(), this.compressionCodec(), this.evidence$1);
         if (this.checksumEnabled()) {
            this.checksums_$eq(new int[blocks.length]);
         }

         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])blocks))), (x0$1) -> {
            $anonfun$writeBlocks$1(this, blockManager, x0$1);
            return BoxedUnit.UNIT;
         });
         return blocks.length;
      } catch (Throwable var5) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Store broadcast ", " fail, remove all pieces of the broadcast"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_ID..MODULE$, this.broadcastId())})))));
         blockManager.removeBroadcast(super.id(), true);
         throw var5;
      }
   }

   private BlockData[] readBlocks() {
      BlockData[] blocks = new BlockData[this.numBlocks()];
      BlockManager bm = SparkEnv$.MODULE$.get().blockManager();
      ((IterableOnceOps)scala.util.Random..MODULE$.shuffle((IterableOnce)scala.package..MODULE$.Seq().range(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(this.numBlocks()), scala.math.Numeric.IntIsIntegral..MODULE$), scala.collection.BuildFrom..MODULE$.buildFromIterableOps())).foreach((JFunction1.mcVI.sp)(pid) -> {
         BroadcastBlockId pieceId = new BroadcastBlockId(this.super$id(), "piece" + pid);
         this.logDebug(() -> "Reading piece " + pieceId + " of " + this.broadcastId());
         Option var7 = bm.getLocalBytes(pieceId);
         if (var7 instanceof Some var8) {
            BlockData block = (BlockData)var8.value();
            blocks[pid] = block;
            this.releaseBlockManagerLock(pieceId);
            BoxedUnit var15 = BoxedUnit.UNIT;
         } else if (.MODULE$.equals(var7)) {
            Option var10 = bm.getRemoteBytes(pieceId);
            if (var10 instanceof Some) {
               Some var11 = (Some)var10;
               ChunkedByteBuffer b = (ChunkedByteBuffer)var11.value();
               if (this.checksumEnabled()) {
                  int sum = this.calcChecksum(b.chunks()[0]);
                  if (sum != this.checksums()[pid]) {
                     throw org.apache.spark.SparkException..MODULE$.internalError("corrupt remote block " + pieceId + " of " + this.broadcastId() + ": " + sum + " != " + this.checksums()[pid], "BROADCAST");
                  }
               }

               if (!bm.putBytes(pieceId, b, org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK_SER(), true, this.evidence$1)) {
                  throw org.apache.spark.SparkException..MODULE$.internalError("Failed to store " + pieceId + " of " + this.broadcastId() + " in local BlockManager", "BROADCAST");
               } else {
                  blocks[pid] = new ByteBufferBlockData(b, true);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  var10000 = BoxedUnit.UNIT;
               }
            } else if (.MODULE$.equals(var10)) {
               throw org.apache.spark.SparkException..MODULE$.internalError("Failed to get " + pieceId + " of " + this.broadcastId(), "BROADCAST");
            } else {
               throw new MatchError(var10);
            }
         } else {
            throw new MatchError(var7);
         }
      });
      return blocks;
   }

   public void doUnpersist(final boolean blocking) {
      TorrentBroadcast$.MODULE$.unpersist(super.id(), false, blocking);
   }

   public void doDestroy(final boolean blocking) {
      TorrentBroadcast$.MODULE$.unpersist(super.id(), true, blocking);
   }

   private void writeObject(final ObjectOutputStream out) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.assertValid();
         out.defaultWriteObject();
      });
   }

   private Object readBroadcastBlock() {
      return Utils$.MODULE$.tryOrIOException(() -> TorrentBroadcast$.MODULE$.org$apache$spark$broadcast$TorrentBroadcast$$torrentBroadcastLock().withLock(this.broadcastId(), () -> {
            Map broadcastCache = SparkEnv$.MODULE$.get().broadcastManager().cachedValues();
            return scala.Option..MODULE$.apply(broadcastCache.get(this.broadcastId())).map((x$6) -> x$6).getOrElse(() -> {
               this.setConf(SparkEnv$.MODULE$.get().conf());
               BlockManager blockManager = SparkEnv$.MODULE$.get().blockManager();
               Option var4 = blockManager.getLocalValues(this.broadcastId());
               if (var4 instanceof Some var5) {
                  BlockResult blockResult = (BlockResult)var5.value();
                  if (blockResult.data().hasNext()) {
                     Object x = blockResult.data().next();
                     this.releaseBlockManagerLock(this.broadcastId());
                     if (x != null) {
                        broadcastCache.put(this.broadcastId(), x);
                     } else {
                        BoxedUnit var18 = BoxedUnit.UNIT;
                     }

                     return x;
                  } else {
                     throw org.apache.spark.SparkException..MODULE$.internalError("Failed to get locally stored broadcast data: " + this.broadcastId(), "BROADCAST");
                  }
               } else if (.MODULE$.equals(var4)) {
                  String estimatedTotalSize = Utils$.MODULE$.bytesToString((long)this.numBlocks() * (long)this.blockSize());
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Started reading broadcast variable ", " with ", " pieces "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_ID..MODULE$, BoxesRunTime.boxToLong(this.super$id())), new MDC(org.apache.spark.internal.LogKeys.NUM_BROADCAST_BLOCK..MODULE$, BoxesRunTime.boxToInteger(this.numBlocks()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(estimated total size ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, estimatedTotalSize)}))))));
                  long startTimeNs = System.nanoTime();
                  BlockData[] blocks = this.readBlocks();
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Reading broadcast variable ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_ID..MODULE$, BoxesRunTime.boxToLong(this.super$id()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" took ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, Utils$.MODULE$.getUsedTimeNs(startTimeNs))}))))));

                  Object var17;
                  try {
                     Object obj = TorrentBroadcast$.MODULE$.unBlockifyObject((InputStream[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(blocks), (x$7) -> x$7.toInputStream(), scala.reflect.ClassTag..MODULE$.apply(InputStream.class)), SparkEnv$.MODULE$.get().serializer(), this.compressionCodec(), this.evidence$1);
                     if (!this.serializedOnly || this.isLocalMaster() || Utils$.MODULE$.isInRunningSparkTask()) {
                        StorageLevel storageLevel = org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK();
                        if (!blockManager.putSingle(this.broadcastId(), obj, storageLevel, false, this.evidence$1)) {
                           throw org.apache.spark.SparkException..MODULE$.internalError("Failed to store " + this.broadcastId() + " in BlockManager", "BROADCAST");
                        }
                     }

                     if (obj != null) {
                        broadcastCache.put(this.broadcastId(), obj);
                     } else {
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     }

                     var17 = obj;
                  } finally {
                     scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(blocks), (x$8) -> {
                        $anonfun$readBroadcastBlock$8(x$8);
                        return BoxedUnit.UNIT;
                     });
                  }

                  return var17;
               } else {
                  throw new MatchError(var4);
               }
            });
         }));
   }

   private void releaseBlockManagerLock(final BlockId blockId) {
      BlockManager blockManager = SparkEnv$.MODULE$.get().blockManager();
      Option var4 = scala.Option..MODULE$.apply(TaskContext$.MODULE$.get());
      if (var4 instanceof Some var5) {
         TaskContext taskContext = (TaskContext)var5.value();
         taskContext.addTaskCompletionListener((Function1)((x$9) -> {
            $anonfun$releaseBlockManagerLock$1(blockManager, blockId, x$9);
            return BoxedUnit.UNIT;
         }));
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var4)) {
         blockManager.releaseLock(blockId, blockManager.releaseLock$default$2());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var4);
      }
   }

   public boolean hasCachedValue() {
      return BoxesRunTime.unboxToBoolean(TorrentBroadcast$.MODULE$.org$apache$spark$broadcast$TorrentBroadcast$$torrentBroadcastLock().withLock(this.broadcastId(), (JFunction0.mcZ.sp)() -> {
         this.setConf(SparkEnv$.MODULE$.get().conf());
         BlockManager blockManager = SparkEnv$.MODULE$.get().blockManager();
         Option var3 = blockManager.getLocalValues(this.broadcastId());
         if (var3 instanceof Some var4) {
            BlockResult blockResult = (BlockResult)var4.value();
            if (blockResult.data().hasNext()) {
               Object x = blockResult.data().next();
               this.releaseBlockManagerLock(this.broadcastId());
               return x != null;
            }
         }

         return false;
      }));
   }

   // $FF: synthetic method
   public static final void $anonfun$writeBlocks$1(final TorrentBroadcast $this, final BlockManager blockManager$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         ByteBuffer block = (ByteBuffer)x0$1._1();
         int i = x0$1._2$mcI$sp();
         if ($this.checksumEnabled()) {
            $this.checksums()[i] = $this.calcChecksum(block);
         }

         BroadcastBlockId pieceId = new BroadcastBlockId($this.super$id(), "piece" + i);
         ChunkedByteBuffer bytes = new ChunkedByteBuffer(block.duplicate());
         if (!blockManager$1.putBytes(pieceId, bytes, org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK_SER(), true, $this.evidence$1)) {
            throw org.apache.spark.SparkException..MODULE$.internalError("Failed to store " + pieceId + " of " + $this.broadcastId() + " in local BlockManager", "BROADCAST");
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$readBroadcastBlock$8(final BlockData x$8) {
      x$8.dispose();
   }

   // $FF: synthetic method
   public static final void $anonfun$releaseBlockManagerLock$1(final BlockManager blockManager$2, final BlockId blockId$1, final TaskContext x$9) {
      blockManager$2.releaseLock(blockId$1, blockManager$2.releaseLock$default$2());
   }

   public TorrentBroadcast(final Object obj, final long id, final boolean serializedOnly, final ClassTag evidence$1) {
      super(id, evidence$1);
      this.serializedOnly = serializedOnly;
      this.evidence$1 = evidence$1;
      this.checksumEnabled = false;
      this.setConf(SparkEnv$.MODULE$.get().conf());
      this.broadcastId = new BroadcastBlockId(super.id(), BroadcastBlockId$.MODULE$.apply$default$2());
      this.numBlocks = this.writeBlocks(obj);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
