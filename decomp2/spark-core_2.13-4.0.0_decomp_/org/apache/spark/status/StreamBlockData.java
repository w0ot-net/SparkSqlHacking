package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q4Q!\u0006\f\u00011yA\u0001\"\n\u0001\u0003\u0006\u0004%\ta\n\u0005\tg\u0001\u0011\t\u0011)A\u0005Q!AA\u0007\u0001BC\u0002\u0013\u0005q\u0005\u0003\u00056\u0001\t\u0005\t\u0015!\u0003)\u0011!1\u0004A!b\u0001\n\u00039\u0003\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u0011a\u0002!Q1A\u0005\u0002\u001dB\u0001\"\u000f\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\tu\u0001\u0011)\u0019!C\u0001w!Aq\b\u0001B\u0001B\u0003%A\b\u0003\u0005A\u0001\t\u0015\r\u0011\"\u0001<\u0011!\t\u0005A!A!\u0002\u0013a\u0004\u0002\u0003\"\u0001\u0005\u000b\u0007I\u0011A\u001e\t\u0011\r\u0003!\u0011!Q\u0001\nqB\u0001\u0002\u0012\u0001\u0003\u0006\u0004%\t!\u0012\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005\r\"A!\n\u0001BC\u0002\u0013\u0005Q\t\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003G\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u0015I\u0006\u0001\"\u0001[\u0005=\u0019FO]3b[\ncwnY6ECR\f'BA\f\u0019\u0003\u0019\u0019H/\u0019;vg*\u0011\u0011DG\u0001\u0006gB\f'o\u001b\u0006\u00037q\ta!\u00199bG\",'\"A\u000f\u0002\u0007=\u0014xm\u0005\u0002\u0001?A\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1\u0011I\\=SK\u001a\fAA\\1nK\u000e\u0001Q#\u0001\u0015\u0011\u0005%\u0002dB\u0001\u0016/!\tY\u0013%D\u0001-\u0015\tic%\u0001\u0004=e>|GOP\u0005\u0003_\u0005\na\u0001\u0015:fI\u00164\u0017BA\u00193\u0005\u0019\u0019FO]5oO*\u0011q&I\u0001\u0006]\u0006lW\rI\u0001\u000bKb,7-\u001e;pe&#\u0017aC3yK\u000e,Ho\u001c:JI\u0002\n\u0001\u0002[8tiB{'\u000f^\u0001\nQ>\u001cH\u000fU8si\u0002\nAb\u001d;pe\u0006<W\rT3wK2\fQb\u001d;pe\u0006<W\rT3wK2\u0004\u0013!C;tK6+Wn\u001c:z+\u0005a\u0004C\u0001\u0011>\u0013\tq\u0014EA\u0004C_>dW-\u00198\u0002\u0015U\u001cX-T3n_JL\b%A\u0004vg\u0016$\u0015n]6\u0002\u0011U\u001cX\rR5tW\u0002\nA\u0002Z3tKJL\u0017\r\\5{K\u0012\fQ\u0002Z3tKJL\u0017\r\\5{K\u0012\u0004\u0013aB7f[NK'0Z\u000b\u0002\rB\u0011\u0001eR\u0005\u0003\u0011\u0006\u0012A\u0001T8oO\u0006AQ.Z7TSj,\u0007%\u0001\u0005eSN\\7+\u001b>f\u0003%!\u0017n]6TSj,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u000b\u001dB\u000b&k\u0015+V-^C\u0006CA(\u0001\u001b\u00051\u0002\"B\u0013\u0014\u0001\u0004A\u0003\"\u0002\u001b\u0014\u0001\u0004A\u0003\"\u0002\u001c\u0014\u0001\u0004A\u0003\"\u0002\u001d\u0014\u0001\u0004A\u0003\"\u0002\u001e\u0014\u0001\u0004a\u0004\"\u0002!\u0014\u0001\u0004a\u0004\"\u0002\"\u0014\u0001\u0004a\u0004\"\u0002#\u0014\u0001\u00041\u0005\"\u0002&\u0014\u0001\u00041\u0015aA6fsV\t1\fE\u0002!9\"J!!X\u0011\u0003\u000b\u0005\u0013(/Y=)\u0005Qy\u0006C\u00011j\u001b\u0005\t'B\u00012d\u0003)\tgN\\8uCRLwN\u001c\u0006\u0003I\u0016\fqA[1dWN|gN\u0003\u0002gO\u0006Ia-Y:uKJDX\u000e\u001c\u0006\u0002Q\u0006\u00191m\\7\n\u0005)\f'A\u0003&t_:LuM\\8sK\"\u0012A\u0003\u001c\t\u0003[Jl\u0011A\u001c\u0006\u0003_B\fqa\u001b<ti>\u0014XM\u0003\u0002r1\u0005!Q\u000f^5m\u0013\t\u0019hNA\u0004L-&sG-\u001a="
)
public class StreamBlockData {
   private final String name;
   private final String executorId;
   private final String hostPort;
   private final String storageLevel;
   private final boolean useMemory;
   private final boolean useDisk;
   private final boolean deserialized;
   private final long memSize;
   private final long diskSize;

   public String name() {
      return this.name;
   }

   public String executorId() {
      return this.executorId;
   }

   public String hostPort() {
      return this.hostPort;
   }

   public String storageLevel() {
      return this.storageLevel;
   }

   public boolean useMemory() {
      return this.useMemory;
   }

   public boolean useDisk() {
      return this.useDisk;
   }

   public boolean deserialized() {
      return this.deserialized;
   }

   public long memSize() {
      return this.memSize;
   }

   public long diskSize() {
      return this.diskSize;
   }

   @JsonIgnore
   @KVIndex
   public String[] key() {
      return (String[])((Object[])(new String[]{this.name(), this.executorId()}));
   }

   public StreamBlockData(final String name, final String executorId, final String hostPort, final String storageLevel, final boolean useMemory, final boolean useDisk, final boolean deserialized, final long memSize, final long diskSize) {
      this.name = name;
      this.executorId = executorId;
      this.hostPort = hostPort;
      this.storageLevel = storageLevel;
      this.useMemory = useMemory;
      this.useDisk = useDisk;
      this.deserialized = deserialized;
      this.memSize = memSize;
      this.diskSize = diskSize;
   }
}
