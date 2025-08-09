package org.apache.spark.status.api.v1.streaming;

import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001\u0002\n\u0014\u0001\tB\u0001\"\u000b\u0001\u0003\u0006\u0004%\tA\u000b\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005W!A1\t\u0001BC\u0002\u0013\u0005A\t\u0003\u0005N\u0001\t\u0005\t\u0015!\u0003F\u0011!q\u0005A!b\u0001\n\u0003!\u0005\u0002C(\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011A\u0003!Q1A\u0005\u0002EC\u0001\"\u0018\u0001\u0003\u0002\u0003\u0006IA\u0015\u0005\t=\u0002\u0011)\u0019!C\u0001#\"Aq\f\u0001B\u0001B\u0003%!\u000b\u0003\u0005a\u0001\t\u0015\r\u0011\"\u0001b\u0011!1\u0007A!A!\u0002\u0013\u0011\u0007\u0002C4\u0001\u0005\u000b\u0007I\u0011\u00015\t\u0011)\u0004!\u0011!Q\u0001\n%D\u0001b\u001b\u0001\u0003\u0006\u0004%\t\u0001\u001c\u0005\ts\u0002\u0011\t\u0011)A\u0005[\"1!\u0010\u0001C\u00017m\u00141cT;uaV$x\n]3sCRLwN\\%oM>T!\u0001F\u000b\u0002\u0013M$(/Z1nS:<'B\u0001\f\u0018\u0003\t1\u0018G\u0003\u0002\u00193\u0005\u0019\u0011\r]5\u000b\u0005iY\u0012AB:uCR,8O\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0012\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\r\u0005s\u0017PU3g\u0003)yW\u000f\u001e9vi>\u0003\u0018\nZ\u000b\u0002WA\u0011Af\u0010\b\u0003[qr!AL\u001d\u000f\u0005=BdB\u0001\u00198\u001d\t\tdG\u0004\u00023k5\t1G\u0003\u00025C\u00051AH]8pizJ\u0011\u0001I\u0005\u0003=}I!\u0001H\u000f\n\u0005QY\u0012B\u0001\u001e<\u0003\t)\u0018N\u0003\u0002\u00157%\u0011QHP\u0001\u001d'R\u0014X-Y7j]\u001eTuN\u0019)s_\u001e\u0014Xm]:MSN$XM\\3s\u0015\tQ4(\u0003\u0002A\u0003\nQq*\u001e;qkR|\u0005/\u00133\u000b\u0005ur\u0014aC8viB,Ho\u00149JI\u0002\nAA\\1nKV\tQ\t\u0005\u0002G\u0015:\u0011q\t\u0013\t\u0003e\u0015J!!S\u0013\u0002\rA\u0013X\rZ3g\u0013\tYEJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0013\u0016\nQA\\1nK\u0002\n1\u0002Z3tGJL\u0007\u000f^5p]\u0006aA-Z:de&\u0004H/[8oA\u0005I1\u000f^1siRKW.Z\u000b\u0002%B\u0019AeU+\n\u0005Q+#AB(qi&|g\u000e\u0005\u0002W76\tqK\u0003\u0002Y3\u0006!Q\u000f^5m\u0015\u0005Q\u0016\u0001\u00026bm\u0006L!\u0001X,\u0003\t\u0011\u000bG/Z\u0001\u000bgR\f'\u000f\u001e+j[\u0016\u0004\u0013aB3oIRKW.Z\u0001\tK:$G+[7fA\u0005AA-\u001e:bi&|g.F\u0001c!\r!3k\u0019\t\u0003I\u0011L!!Z\u0013\u0003\t1{gnZ\u0001\nIV\u0014\u0018\r^5p]\u0002\nQBZ1jYV\u0014XMU3bg>tW#A5\u0011\u0007\u0011\u001aV)\u0001\bgC&dWO]3SK\u0006\u001cxN\u001c\u0011\u0002\r)|'-\u00133t+\u0005i\u0007c\u00018tm:\u0011q.\u001d\b\u0003eAL\u0011AJ\u0005\u0003e\u0016\nq\u0001]1dW\u0006<W-\u0003\u0002uk\n\u00191+Z9\u000b\u0005I,\u0003C\u0001\u0017x\u0013\tA\u0018I\u0001\u0006Ta\u0006\u00148NS8c\u0013\u0012\fqA[8c\u0013\u0012\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0010yz|\u0018\u0011AA\u0002\u0003\u000b\t9!!\u0003\u0002\fA\u0011Q\u0010A\u0007\u0002'!)\u0011&\u0005a\u0001W!)1)\u0005a\u0001\u000b\")a*\u0005a\u0001\u000b\")\u0001+\u0005a\u0001%\")a,\u0005a\u0001%\")\u0001-\u0005a\u0001E\")q-\u0005a\u0001S\")1.\u0005a\u0001[\u0002"
)
public class OutputOperationInfo {
   private final int outputOpId;
   private final String name;
   private final String description;
   private final Option startTime;
   private final Option endTime;
   private final Option duration;
   private final Option failureReason;
   private final Seq jobIds;

   public int outputOpId() {
      return this.outputOpId;
   }

   public String name() {
      return this.name;
   }

   public String description() {
      return this.description;
   }

   public Option startTime() {
      return this.startTime;
   }

   public Option endTime() {
      return this.endTime;
   }

   public Option duration() {
      return this.duration;
   }

   public Option failureReason() {
      return this.failureReason;
   }

   public Seq jobIds() {
      return this.jobIds;
   }

   public OutputOperationInfo(final int outputOpId, final String name, final String description, final Option startTime, final Option endTime, final Option duration, final Option failureReason, final Seq jobIds) {
      this.outputOpId = outputOpId;
      this.name = name;
      this.description = description;
      this.startTime = startTime;
      this.endTime = endTime;
      this.duration = duration;
      this.failureReason = failureReason;
      this.jobIds = jobIds;
   }
}
