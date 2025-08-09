package org.apache.spark.api.python;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.SparkEnv;
import org.apache.spark.TaskContext;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005MtAB\b\u0011\u0011\u0003!\"D\u0002\u0004\u001d!!\u0005A#\b\u0005\u0006I\u0005!\tA\n\u0005\bO\u0005\u0011\r\u0011\"\u0001)\u0011\u00191\u0015\u0001)A\u0005S!9q)\u0001b\u0001\n\u0013A\u0005BB(\u0002A\u0003%\u0011\nC\u0003Q\u0003\u0011\u0005\u0011KB\u0003\u001d!\u0001!2\u000bC\u0005^\u0011\t\u0005\t\u0015!\u0003_[\"Ia\u000e\u0003B\u0001B\u0003%qN\u001f\u0005\u0006I!!\ta\u001f\u0005\u0006}\"!\tf \u0005\b\u0003oAA\u0011KA\u001d\u00115\t\u0019\u0007\u0003I\u0001\u0004\u0003\u0005I\u0011BA3[\u0006a\u0001+\u001f;i_:\u0014VO\u001c8fe*\u0011\u0011CE\u0001\u0007af$\bn\u001c8\u000b\u0005M!\u0012aA1qS*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014x\r\u0005\u0002\u001c\u00035\t\u0001C\u0001\u0007QsRDwN\u001c*v]:,'o\u0005\u0002\u0002=A\u0011qDI\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u00025\u0005)\"/\u001e8oS:<Wj\u001c8ji>\u0014H\u000b\u001b:fC\u0012\u001cX#A\u0015\u0011\t)\"t\u0007\u0011\b\u0003WIj\u0011\u0001\f\u0006\u0003[9\n!bY8oGV\u0014(/\u001a8u\u0015\ty\u0003'\u0001\u0003vi&d'\"A\u0019\u0002\t)\fg/Y\u0005\u0003g1\n\u0011cQ8oGV\u0014(/\u001a8u\u0011\u0006\u001c\b.T1q\u0013\t)dG\u0001\u0006LKf\u001cV\r\u001e,jK^T!a\r\u0017\u0011\t}A$(P\u0005\u0003s\u0001\u0012a\u0001V;qY\u0016\u0014\u0004CA\u000e<\u0013\ta\u0004C\u0001\u0007QsRDwN\\,pe.,'\u000f\u0005\u0002 }%\u0011q\b\t\u0002\u0005\u0019>tw\r\u0005\u0002B\t6\t!I\u0003\u0002Da\u0005!A.\u00198h\u0013\t)%IA\u0004C_>dW-\u00198\u0002-I,hN\\5oO6{g.\u001b;peRC'/Z1eg\u0002\nq\u0002\u001d:j]R\u0004\u0016\u0010\u001e5p]&sgm\\\u000b\u0002\u0013B\u0011!*T\u0007\u0002\u0017*\u0011A\nL\u0001\u0007CR|W.[2\n\u00059[%!D!u_6L7MQ8pY\u0016\fg.\u0001\tqe&tG\u000fU=uQ>t\u0017J\u001c4pA\u0005)\u0011\r\u001d9msR)!+a\u001a\u0002rA\u00111\u0004C\n\u0003\u0011Q\u0003BaG+X/&\u0011a\u000b\u0005\u0002\u0011\u0005\u0006\u001cX\rU=uQ>t'+\u001e8oKJ\u00042a\b-[\u0013\tI\u0006EA\u0003BeJ\f\u0017\u0010\u0005\u0002 7&\u0011A\f\t\u0002\u0005\u0005f$X-A\u0003gk:\u001c7\u000fE\u0002`O*t!\u0001Y3\u000f\u0005\u0005$W\"\u00012\u000b\u0005\r,\u0013A\u0002\u001fs_>$h(C\u0001\"\u0013\t1\u0007%A\u0004qC\u000e\\\u0017mZ3\n\u0005!L'aA*fc*\u0011a\r\t\t\u00037-L!\u0001\u001c\t\u0003-\rC\u0017-\u001b8fIBKH\u000f[8o\rVt7\r^5p]NL!!X+\u0002\u001f)|'-\u0011:uS\u001a\f7\r^+V\u0013\u0012\u00032a\b9s\u0013\t\t\bE\u0001\u0004PaRLwN\u001c\t\u0003g^t!\u0001^;\u0011\u0005\u0005\u0004\u0013B\u0001<!\u0003\u0019\u0001&/\u001a3fM&\u0011\u00010\u001f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Y\u0004\u0013B\u00018V)\r\u0011F0 \u0005\u0006;.\u0001\rA\u0018\u0005\u0006].\u0001\ra\\\u0001\n]\u0016<xK]5uKJ$B\"!\u0001\u0002\n\u0005U\u0011\u0011DA\u0012\u0003[\u0001B!a\u0001\u0002\u00065\t\u0001\"C\u0002\u0002\bU\u0013aa\u0016:ji\u0016\u0014\bbBA\u0006\u0019\u0001\u0007\u0011QB\u0001\u0004K:4\b\u0003BA\b\u0003#i\u0011\u0001F\u0005\u0004\u0003'!\"\u0001C*qCJ\\WI\u001c<\t\r\u0005]A\u00021\u0001;\u0003\u00199xN]6fe\"9\u00111\u0004\u0007A\u0002\u0005u\u0011!D5oaV$\u0018\n^3sCR|'\u000f\u0005\u0003`\u0003?9\u0016bAA\u0011S\nA\u0011\n^3sCR|'\u000fC\u0004\u0002&1\u0001\r!a\n\u0002\u001dA\f'\u000f^5uS>t\u0017J\u001c3fqB\u0019q$!\u000b\n\u0007\u0005-\u0002EA\u0002J]RDq!a\f\r\u0001\u0004\t\t$A\u0004d_:$X\r\u001f;\u0011\t\u0005=\u00111G\u0005\u0004\u0003k!\"a\u0003+bg.\u001cuN\u001c;fqR\f\u0011C\\3x%\u0016\fG-\u001a:Ji\u0016\u0014\u0018\r^8s)I\ti\"a\u000f\u0002L\u0005=\u00131KA+\u0003/\ni&!\u0019\t\u000f\u0005uR\u00021\u0001\u0002@\u000511\u000f\u001e:fC6\u0004B!!\u0011\u0002H5\u0011\u00111\t\u0006\u0004\u0003\u000b\u0002\u0014AA5p\u0013\u0011\tI%a\u0011\u0003\u001f\u0011\u000bG/Y%oaV$8\u000b\u001e:fC6Dq!!\u0014\u000e\u0001\u0004\t\t!\u0001\u0004xe&$XM\u001d\u0005\u0007\u0003#j\u0001\u0019A\u001f\u0002\u0013M$\u0018M\u001d;US6,\u0007bBA\u0006\u001b\u0001\u0007\u0011Q\u0002\u0005\u0007\u0003/i\u0001\u0019\u0001\u001e\t\u000f\u0005eS\u00021\u0001\u0002\\\u0005\u0019\u0001/\u001b3\u0011\t}\u0001\u0018q\u0005\u0005\u0007\u0003?j\u0001\u0019A%\u0002!I,G.Z1tK\u0012|%o\u00117pg\u0016$\u0007bBA\u0018\u001b\u0001\u0007\u0011\u0011G\u0001\fgV\u0004XM\u001d\u0013gk:\u001c7/F\u0001_\u0011\u001d\tIg\u0002a\u0001\u0003W\nAAZ;oGB\u00191$!\u001c\n\u0007\u0005=\u0004C\u0001\bQsRDwN\u001c$v]\u000e$\u0018n\u001c8\t\u000b9<\u0001\u0019A8"
)
public class PythonRunner extends BasePythonRunner {
   public static PythonRunner apply(final PythonFunction func, final Option jobArtifactUUID) {
      return PythonRunner$.MODULE$.apply(func, jobArtifactUUID);
   }

   public static ConcurrentHashMap.KeySetView runningMonitorThreads() {
      return PythonRunner$.MODULE$.runningMonitorThreads();
   }

   // $FF: synthetic method
   public Seq org$apache$spark$api$python$PythonRunner$$super$funcs() {
      return super.funcs();
   }

   public BasePythonRunner.Writer newWriter(final SparkEnv env, final PythonWorker worker, final Iterator inputIterator, final int partitionIndex, final TaskContext context) {
      return new BasePythonRunner.Writer(env, worker, inputIterator, partitionIndex, context) {
         // $FF: synthetic field
         private final PythonRunner $outer;
         private final Iterator inputIterator$1;

         public void writeCommand(final DataOutputStream dataOut) {
            PythonWorkerUtils$.MODULE$.writePythonFunction((PythonFunction)((ChainedPythonFunctions)this.$outer.org$apache$spark$api$python$PythonRunner$$super$funcs().head()).funcs().head(), dataOut);
         }

         public boolean writeNextInputToStream(final DataOutputStream dataOut) {
            if (PythonRDD$.MODULE$.writeNextElementToStream(this.inputIterator$1, dataOut)) {
               return true;
            } else {
               dataOut.writeInt(SpecialLengths$.MODULE$.END_OF_DATA_SECTION());
               return false;
            }
         }

         public {
            if (PythonRunner.this == null) {
               throw null;
            } else {
               this.$outer = PythonRunner.this;
               this.inputIterator$1 = inputIterator$1;
            }
         }
      };
   }

   public Iterator newReaderIterator(final DataInputStream stream, final BasePythonRunner.Writer writer, final long startTime, final SparkEnv env, final PythonWorker worker, final Option pid, final AtomicBoolean releasedOrClosed, final TaskContext context) {
      return new BasePythonRunner.ReaderIterator(stream, writer, startTime, env, worker, pid, releasedOrClosed, context) {
         private final BasePythonRunner.Writer writer$1;
         private final DataInputStream stream$1;

         public byte[] read() {
            if (this.writer$1.exception().isDefined()) {
               throw (Throwable)this.writer$1.exception().get();
            } else {
               byte[] var10000;
               try {
                  int var2 = this.stream$1.readInt();
                  if (var2 >= 0) {
                     var10000 = PythonWorkerUtils$.MODULE$.readBytes(var2, this.stream$1);
                  } else if (SpecialLengths$.MODULE$.TIMING_DATA() == var2) {
                     this.handleTimingData();
                     var10000 = this.read();
                  } else {
                     if (SpecialLengths$.MODULE$.PYTHON_EXCEPTION_THROWN() == var2) {
                        throw this.handlePythonException();
                     }

                     if (SpecialLengths$.MODULE$.END_OF_DATA_SECTION() != var2) {
                        throw new MatchError(BoxesRunTime.boxToInteger(var2));
                     }

                     this.handleEndOfDataSection();
                     var10000 = null;
                  }
               } catch (Throwable var5) {
                  PartialFunction catchExpr$1 = this.handleException();
                  if (!catchExpr$1.isDefinedAt(var5)) {
                     throw var5;
                  }

                  var10000 = (byte[])catchExpr$1.apply(var5);
               }

               return var10000;
            }
         }

         public {
            this.writer$1 = writer$1;
            this.stream$1 = stream$1;
         }
      };
   }

   public PythonRunner(final Seq funcs, final Option jobArtifactUUID) {
      super(funcs, PythonEvalType$.MODULE$.NON_UDF(), (int[][])((Object[])(new int[][]{{0}})), jobArtifactUUID, .MODULE$.Map().empty());
   }
}
