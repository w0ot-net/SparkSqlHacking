package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055f!\u0002\u0014(\u0001%z\u0003\u0002\u0003\u001c\u0001\u0005\u000b\u0007I\u0011\u0001\u001d\t\u0011q\u0002!\u0011!Q\u0001\neB\u0001\"\u0010\u0001\u0003\u0006\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005\u007f!A1\t\u0001BC\u0002\u0013\u0005A\t\u0003\u0005Q\u0001\t\u0005\t\u0015!\u0003F\u0011!\t\u0006A!b\u0001\n\u0003!\u0005\u0002\u0003*\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011M\u0003!Q1A\u0005\u0002yB\u0001\u0002\u0016\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t+\u0002\u0011)\u0019!C\u0001}!Aa\u000b\u0001B\u0001B\u0003%q\b\u0003\u0005X\u0001\t\u0015\r\u0011\"\u0001Y\u0011!i\u0006A!A!\u0002\u0013I\u0006\u0002\u00030\u0001\u0005\u000b\u0007I\u0011A0\t\u0011!\u0004!\u0011!Q\u0001\n\u0001D\u0001\"\u001b\u0001\u0003\u0006\u0004%\tA\u0010\u0005\tU\u0002\u0011\t\u0011)A\u0005\u007f!A1\u000e\u0001BC\u0002\u0013\u0005A\u000e\u0003\u0005w\u0001\t\u0005\t\u0015!\u0003n\u0011!9\bA!b\u0001\n\u0003A\b\u0002C@\u0001\u0005\u0003\u0005\u000b\u0011B=\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u0011q\u0004\u0001\u0005B\u0005\u0005r\u0001CA\u0012O!\u0005\u0011&!\n\u0007\u000f\u0019:\u0003\u0012A\u0015\u0002(!9\u0011\u0011\u0001\u000e\u0005\u0002\u0005%\u0002bBA\u00165\u0011%\u0011Q\u0006\u0005\b\u0003'RB\u0011BA+\u0011\u001d\tYF\u0007C\u0001\u0003;Bq!a\u0019\u001b\t\u0013\t)\u0007C\u0004\u0002xi!I!!\u001f\t\u000f\u0005}$\u0004\"\u0003\u0002\u0002\"9\u00111\u0012\u000e\u0005\n\u00055\u0005bBAJ5\u0011%\u0011Q\u0013\u0005\b\u0003?SB\u0011BAQ\u0011\u001d\t)K\u0007C\u0001\u0003O\u0013q\u0002V1tW\u0012+7o\u0019:jaRLwN\u001c\u0006\u0003Q%\n\u0011b]2iK\u0012,H.\u001a:\u000b\u0005)Z\u0013!B:qCJ\\'B\u0001\u0017.\u0003\u0019\t\u0007/Y2iK*\ta&A\u0002pe\u001e\u001c\"\u0001\u0001\u0019\u0011\u0005E\"T\"\u0001\u001a\u000b\u0003M\nQa]2bY\u0006L!!\u000e\u001a\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0018m]6JI\u000e\u0001Q#A\u001d\u0011\u0005ER\u0014BA\u001e3\u0005\u0011auN\\4\u0002\u000fQ\f7o[%eA\u0005i\u0011\r\u001e;f[B$h*^7cKJ,\u0012a\u0010\t\u0003c\u0001K!!\u0011\u001a\u0003\u0007%sG/\u0001\bbiR,W\u000e\u001d;Ok6\u0014WM\u001d\u0011\u0002\u0015\u0015DXmY;u_JLE-F\u0001F!\t1UJ\u0004\u0002H\u0017B\u0011\u0001JM\u0007\u0002\u0013*\u0011!jN\u0001\u0007yI|w\u000e\u001e \n\u00051\u0013\u0014A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001\u0014\u001a\u0002\u0017\u0015DXmY;u_JLE\rI\u0001\u0005]\u0006lW-A\u0003oC6,\u0007%A\u0003j]\u0012,\u00070\u0001\u0004j]\u0012,\u0007\u0010I\u0001\fa\u0006\u0014H/\u001b;j_:LE-\u0001\u0007qCJ$\u0018\u000e^5p]&#\u0007%A\u0005beRLg-Y2ugV\t\u0011\f\u0005\u0002[76\t\u0011&\u0003\u0002]S\tq!j\u001c2BeRLg-Y2u'\u0016$\u0018AC1si&4\u0017m\u0019;tA\u0005Q\u0001O]8qKJ$\u0018.Z:\u0016\u0003\u0001\u0004\"!\u00194\u000e\u0003\tT!a\u00193\u0002\tU$\u0018\u000e\u001c\u0006\u0002K\u0006!!.\u0019<b\u0013\t9'M\u0001\u0006Qe>\u0004XM\u001d;jKN\f1\u0002\u001d:pa\u0016\u0014H/[3tA\u0005!1\r];t\u0003\u0015\u0019\u0007/^:!\u0003%\u0011Xm]8ve\u000e,7/F\u0001n!\u0011q7/R;\u000e\u0003=T!\u0001]9\u0002\u0013%lW.\u001e;bE2,'B\u0001:3\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003i>\u00141!T1q!\u0011q7/R\u001d\u0002\u0015I,7o\\;sG\u0016\u001c\b%\u0001\btKJL\u0017\r\\5{K\u0012$\u0016m]6\u0016\u0003e\u0004\"A_?\u000e\u0003mT!\u0001 3\u0002\u00079Lw.\u0003\u0002\u007fw\nQ!)\u001f;f\u0005V4g-\u001a:\u0002\u001fM,'/[1mSj,G\rV1tW\u0002\na\u0001P5oSRtD\u0003GA\u0003\u0003\u0013\tY!!\u0004\u0002\u0010\u0005E\u00111CA\u000b\u0003/\tI\"a\u0007\u0002\u001eA\u0019\u0011q\u0001\u0001\u000e\u0003\u001dBQAN\fA\u0002eBQ!P\fA\u0002}BQaQ\fA\u0002\u0015CQ!U\fA\u0002\u0015CQaU\fA\u0002}BQ!V\fA\u0002}BQaV\fA\u0002eCQAX\fA\u0002\u0001DQ![\fA\u0002}BQa[\fA\u00025DQa^\fA\u0002e\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u000b\u0006yA+Y:l\t\u0016\u001c8M]5qi&|g\u000eE\u0002\u0002\bi\u0019\"A\u0007\u0019\u0015\u0005\u0005\u0015\u0012AF:fe&\fG.\u001b>f'R\u0014\u0018N\\4M_:<W*\u00199\u0015\r\u0005=\u0012QGA\"!\r\t\u0014\u0011G\u0005\u0004\u0003g\u0011$\u0001B+oSRDq!a\u000e\u001d\u0001\u0004\tI$A\u0002nCB\u0004b!a\u000f\u0002B\u0015KTBAA\u001f\u0015\r\ty$]\u0001\b[V$\u0018M\u00197f\u0013\r!\u0018Q\b\u0005\b\u0003\u000bb\u0002\u0019AA$\u0003\u001d!\u0017\r^1PkR\u0004B!!\u0013\u0002P5\u0011\u00111\n\u0006\u0004\u0003\u001b\"\u0017AA5p\u0013\u0011\t\t&a\u0013\u0003!\u0011\u000bG/Y(viB,Ho\u0015;sK\u0006l\u0017AE:fe&\fG.\u001b>f%\u0016\u001cx.\u001e:dKN$b!a\f\u0002X\u0005e\u0003BBA\u001c;\u0001\u0007Q\u000eC\u0004\u0002Fu\u0001\r!a\u0012\u0002\r\u0015t7m\u001c3f)\rI\u0018q\f\u0005\b\u0003Cr\u0002\u0019AA\u0003\u0003=!\u0018m]6EKN\u001c'/\u001b9uS>t\u0017a\u00063fg\u0016\u0014\u0018.\u00197ju\u0016|\u0005\u000f^5p]N#(/\u001b8h)\u0011\t9'!\u001c\u0011\tE\nI'R\u0005\u0004\u0003W\u0012$AB(qi&|g\u000eC\u0004\u0002p}\u0001\r!!\u001d\u0002\u0005%t\u0007\u0003BA%\u0003gJA!!\u001e\u0002L\tyA)\u0019;b\u0013:\u0004X\u000f^*ue\u0016\fW.\u0001\u000beKN,'/[1mSj,\u0017I\u001d;jM\u0006\u001cGo\u001d\u000b\u00043\u0006m\u0004bBA?A\u0001\u0007\u0011\u0011O\u0001\u0007I\u0006$\u0018-\u00138\u0002+M,'/[1mSj,w\n\u001d;j_:\u001cFO]5oOR1\u0011qFAB\u0003\u000fCq!!\"\"\u0001\u0004\t9'A\u0002tiJDq!!#\"\u0001\u0004\t9%A\u0002pkR\f!c]3sS\u0006d\u0017N_3BeRLg-Y2ugR1\u0011qFAH\u0003#CQa\u0016\u0012A\u0002eCq!!\u0012#\u0001\u0004\t9%\u0001\reKN,'/[1mSj,7\u000b\u001e:j]\u001eduN\\4NCB$B!a&\u0002\u001eB1\u00111HAM\u000bfJA!a'\u0002>\t9\u0001*Y:i\u001b\u0006\u0004\bbBA?G\u0001\u0007\u0011\u0011O\u0001\u0015I\u0016\u001cXM]5bY&TXMU3t_V\u00148-Z:\u0015\u00075\f\u0019\u000bC\u0004\u0002~\u0011\u0002\r!!\u001d\u0002\r\u0011,7m\u001c3f)\u0011\t)!!+\t\r\u0005-V\u00051\u0001z\u0003)\u0011\u0017\u0010^3Ck\u001a4WM\u001d"
)
public class TaskDescription {
   private final long taskId;
   private final int attemptNumber;
   private final String executorId;
   private final String name;
   private final int index;
   private final int partitionId;
   private final JobArtifactSet artifacts;
   private final Properties properties;
   private final int cpus;
   private final Map resources;
   private final ByteBuffer serializedTask;

   public static TaskDescription decode(final ByteBuffer byteBuffer) {
      return TaskDescription$.MODULE$.decode(byteBuffer);
   }

   public static ByteBuffer encode(final TaskDescription taskDescription) {
      return TaskDescription$.MODULE$.encode(taskDescription);
   }

   public long taskId() {
      return this.taskId;
   }

   public int attemptNumber() {
      return this.attemptNumber;
   }

   public String executorId() {
      return this.executorId;
   }

   public String name() {
      return this.name;
   }

   public int index() {
      return this.index;
   }

   public int partitionId() {
      return this.partitionId;
   }

   public JobArtifactSet artifacts() {
      return this.artifacts;
   }

   public Properties properties() {
      return this.properties;
   }

   public int cpus() {
      return this.cpus;
   }

   public Map resources() {
      return this.resources;
   }

   public ByteBuffer serializedTask() {
      return this.serializedTask;
   }

   public String toString() {
      return "TaskDescription(" + this.name() + ")";
   }

   public TaskDescription(final long taskId, final int attemptNumber, final String executorId, final String name, final int index, final int partitionId, final JobArtifactSet artifacts, final Properties properties, final int cpus, final Map resources, final ByteBuffer serializedTask) {
      this.taskId = taskId;
      this.attemptNumber = attemptNumber;
      this.executorId = executorId;
      this.name = name;
      this.index = index;
      this.partitionId = partitionId;
      this.artifacts = artifacts;
      this.properties = properties;
      this.cpus = cpus;
      this.resources = resources;
      this.serializedTask = serializedTask;
      .MODULE$.assert(cpus > 0, () -> "CPUs per task should be > 0");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
