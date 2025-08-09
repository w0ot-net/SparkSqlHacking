package org.apache.spark.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NioManagedBuffer;
import org.apache.spark.network.shuffle.BlockFetchingListener;
import org.apache.spark.network.shuffle.BlockStoreClient;
import org.apache.spark.network.shuffle.DownloadFileManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.EncryptedManagedBuffer;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.ThreadUtils$;
import scala.concurrent.Awaitable;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.concurrent.Promise.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}cA\u0002\u0005\n\u0003\u0003Y\u0011\u0003C\u0003\u0019\u0001\u0011\u0005!\u0004C\u0003\u001e\u0001\u0019\u0005a\u0004C\u0003+\u0001\u0019\u00051\u0006C\u00030\u0001\u0019\u0005\u0001\u0007C\u0003=\u0001\u0019\u0005Q\bC\u0003s\u0001\u0011\u00051\u000fC\u0003\u007f\u0001\u0011\u0005qP\u0001\u000bCY>\u001c7\u000e\u0016:b]N4WM]*feZL7-\u001a\u0006\u0003\u0015-\tqA\\3uo>\u00148N\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\t\u0001!\u0003\u0005\u0002\u0014-5\tAC\u0003\u0002\u0016\u0013\u000591\u000f[;gM2,\u0017BA\f\u0015\u0005A\u0011En\\2l'R|'/Z\"mS\u0016tG/\u0001\u0004=S:LGOP\u0002\u0001)\u0005Y\u0002C\u0001\u000f\u0001\u001b\u0005I\u0011\u0001B5oSR$\"aH\u0013\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\tUs\u0017\u000e\u001e\u0005\u0006M\t\u0001\raJ\u0001\u0011E2|7m\u001b#bi\u0006l\u0015M\\1hKJ\u0004\"\u0001\b\u0015\n\u0005%J!\u0001\u0005\"m_\u000e\\G)\u0019;b\u001b\u0006t\u0017mZ3s\u0003\u0011\u0001xN\u001d;\u0016\u00031\u0002\"\u0001I\u0017\n\u00059\n#aA%oi\u0006A\u0001n\\:u\u001d\u0006lW-F\u00012!\t\u0011\u0014H\u0004\u00024oA\u0011A'I\u0007\u0002k)\u0011a'G\u0001\u0007yI|w\u000e\u001e \n\u0005a\n\u0013A\u0002)sK\u0012,g-\u0003\u0002;w\t11\u000b\u001e:j]\u001eT!\u0001O\u0011\u0002\u0017U\u0004Hn\\1e\u00052|7m\u001b\u000b\t}\u00113u)S)Z=B\u0019qHQ\u0010\u000e\u0003\u0001S!!Q\u0011\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0002D\u0001\n1a)\u001e;ve\u0016DQ!R\u0003A\u0002E\n\u0001\u0002[8ti:\fW.\u001a\u0005\u0006U\u0015\u0001\r\u0001\f\u0005\u0006\u0011\u0016\u0001\r!M\u0001\u0007Kb,7-\u00133\t\u000b)+\u0001\u0019A&\u0002\u000f\tdwnY6JIB\u0011AjT\u0007\u0002\u001b*\u0011ajC\u0001\bgR|'/Y4f\u0013\t\u0001VJA\u0004CY>\u001c7.\u00133\t\u000bI+\u0001\u0019A*\u0002\u0013\tdwnY6ECR\f\u0007C\u0001+X\u001b\u0005)&B\u0001,\n\u0003\u0019\u0011WO\u001a4fe&\u0011\u0001,\u0016\u0002\u000e\u001b\u0006t\u0017mZ3e\u0005V4g-\u001a:\t\u000bi+\u0001\u0019A.\u0002\u000b1,g/\u001a7\u0011\u00051c\u0016BA/N\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u0011\u0015yV\u00011\u0001a\u0003!\u0019G.Y:t)\u0006<\u0007GA1j!\r\u0011WmZ\u0007\u0002G*\u0011A-I\u0001\be\u00164G.Z2u\u0013\t17M\u0001\u0005DY\u0006\u001c8\u000fV1h!\tA\u0017\u000e\u0004\u0001\u0005\u0013)t\u0016\u0011!A\u0001\u0006\u0003Y'aA0%cE\u0011An\u001c\t\u0003A5L!A\\\u0011\u0003\u000f9{G\u000f[5oOB\u0011\u0001\u0005]\u0005\u0003c\u0006\u00121!\u00118z\u000391W\r^2i\u00052|7m[*z]\u000e$ba\u0015;wobL\b\"B;\u0007\u0001\u0004\t\u0014\u0001\u00025pgRDQA\u000b\u0004A\u00021BQ\u0001\u0013\u0004A\u0002EBQA\u0013\u0004A\u0002EBQA\u001f\u0004A\u0002m\fq\u0002^3na\u001aKG.Z'b]\u0006<WM\u001d\t\u0003'qL!! \u000b\u0003'\u0011{wO\u001c7pC\u00124\u0015\u000e\\3NC:\fw-\u001a:\u0002\u001fU\u0004Hn\\1e\u00052|7m[*z]\u000e$rbHA\u0001\u0003\u0007\t)!a\u0002\u0002\n\u0005-\u0011Q\u0002\u0005\u0006\u000b\u001e\u0001\r!\r\u0005\u0006U\u001d\u0001\r\u0001\f\u0005\u0006\u0011\u001e\u0001\r!\r\u0005\u0006\u0015\u001e\u0001\ra\u0013\u0005\u0006%\u001e\u0001\ra\u0015\u0005\u00065\u001e\u0001\ra\u0017\u0005\u0007?\u001e\u0001\r!a\u00041\t\u0005E\u0011Q\u0003\t\u0005E\u0016\f\u0019\u0002E\u0002i\u0003+!1\"a\u0006\u0002\u000e\u0005\u0005\t\u0011!B\u0001W\n\u0019q\f\n\u001a)\u000b\u001d\tY\"!\r\u0011\u000b\u0001\ni\"!\t\n\u0007\u0005}\u0011E\u0001\u0004uQJ|wo\u001d\t\u0005\u0003G\ti#\u0004\u0002\u0002&)!\u0011qEA\u0015\u0003\tIwN\u0003\u0002\u0002,\u0005!!.\u0019<b\u0013\u0011\ty#!\n\u0003\u0017%{U\t_2faRLwN\\\u0019\u0007=E\n\u0019$!\u00182\u0013\r\n)$a\u000f\u0002T\u0005uRc\u0001\u0019\u00028\u00119\u0011\u0011H\rC\u0002\u0005\r#!\u0001+\n\t\u0005u\u0012qH\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u000b\u0007\u0005\u0005\u0013%\u0001\u0004uQJ|wo]\t\u0004Y\u0006\u0015\u0003\u0003BA$\u0003\u001br1\u0001IA%\u0013\r\tY%I\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ty%!\u0015\u0003\u0013QC'o\\<bE2,'bAA&CEJ1%!\u0016\u0002X\u0005e\u0013\u0011\t\b\u0004A\u0005]\u0013bAA!CE*!\u0005I\u0011\u0002\\\t)1oY1mCF\u001aa%!\t"
)
public abstract class BlockTransferService extends BlockStoreClient {
   public abstract void init(final BlockDataManager blockDataManager);

   public abstract int port();

   public abstract String hostName();

   public abstract Future uploadBlock(final String hostname, final int port, final String execId, final BlockId blockId, final ManagedBuffer blockData, final StorageLevel level, final ClassTag classTag);

   public ManagedBuffer fetchBlockSync(final String host, final int port, final String execId, final String blockId, final DownloadFileManager tempFileManager) {
      Promise result = .MODULE$.apply();
      this.fetchBlocks(host, port, execId, (String[])((Object[])(new String[]{blockId})), new BlockFetchingListener(result) {
         private final Promise result$1;

         public void onBlockTransferSuccess(final String x$1, final ManagedBuffer x$2) {
            super.onBlockTransferSuccess(x$1, x$2);
         }

         public void onBlockTransferFailure(final String x$1, final Throwable x$2) {
            super.onBlockTransferFailure(x$1, x$2);
         }

         public String getTransferType() {
            return super.getTransferType();
         }

         public void onBlockFetchFailure(final String blockId, final Throwable exception) {
            this.result$1.failure(exception);
         }

         public void onBlockFetchSuccess(final String blockId, final ManagedBuffer data) {
            if (data instanceof FileSegmentManagedBuffer var5) {
               this.result$1.success(var5);
               BoxedUnit var12 = BoxedUnit.UNIT;
            } else if (data instanceof EncryptedManagedBuffer var6) {
               this.result$1.success(var6);
               BoxedUnit var11 = BoxedUnit.UNIT;
            } else {
               try {
                  ByteBuffer ret = ByteBuffer.allocate((int)data.size());
                  ret.put(data.nioByteBuffer());
                  ret.flip();
                  this.result$1.success(new NioManagedBuffer(ret));
                  BoxedUnit var10 = BoxedUnit.UNIT;
               } catch (Throwable var9) {
                  this.result$1.failure(var9);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

            }
         }

         public {
            this.result$1 = result$1;
         }
      }, tempFileManager);
      return (ManagedBuffer)ThreadUtils$.MODULE$.awaitResult((Awaitable)result.future(), scala.concurrent.duration.Duration..MODULE$.Inf());
   }

   public void uploadBlockSync(final String hostname, final int port, final String execId, final BlockId blockId, final ManagedBuffer blockData, final StorageLevel level, final ClassTag classTag) throws IOException {
      Future future = this.uploadBlock(hostname, port, execId, blockId, blockData, level, classTag);
      ThreadUtils$.MODULE$.awaitResult((Awaitable)future, scala.concurrent.duration.Duration..MODULE$.Inf());
   }
}
