package scala.collection.immutable;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2Q\u0001B\u0003\u0001\u000b-A\u0001\"\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\u0006K\u0001!\tA\n\u0005\u0006S\u0001!\tA\u000b\u0002\u0013\u0019>tw-T1q\u0017\u0016L\u0018\n^3sCR|'O\u0003\u0002\u0007\u000f\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\u0011%\t!bY8mY\u0016\u001cG/[8o\u0015\u0005Q\u0011!B:dC2\fWC\u0001\u0007\u0014'\t\u0001Q\u0002\u0005\u0003\u000f\u001fEqR\"A\u0003\n\u0005A)!a\u0004'p]\u001el\u0015\r]%uKJ\fGo\u001c:\u0011\u0005I\u0019B\u0002\u0001\u0003\u0006)\u0001\u0011\rA\u0006\u0002\u0002-\u000e\u0001\u0011CA\f\u001c!\tA\u0012$D\u0001\n\u0013\tQ\u0012BA\u0004O_RD\u0017N\\4\u0011\u0005aa\u0012BA\u000f\n\u0005\r\te.\u001f\t\u00031}I!\u0001I\u0005\u0003\t1{gnZ\u0001\u0003SR\u00042AD\u0012\u0012\u0013\t!SAA\u0004M_:<W*\u00199\u0002\rqJg.\u001b;?)\t9\u0003\u0006E\u0002\u000f\u0001EAQ!\t\u0002A\u0002\t\nqA^1mk\u0016|e\r\u0006\u0002\u001fW!)Af\u0001a\u0001[\u0005\u0019A/\u001b9\u0011\u00079\n\u0014C\u0004\u0002\u000f_%\u0011\u0001'B\u0001\b\u0019>tw-T1q\u0013\t\u00114GA\u0002USBT!\u0001M\u0003"
)
public class LongMapKeyIterator extends LongMapIterator {
   public long valueOf(final LongMap.Tip tip) {
      return tip.key();
   }

   public LongMapKeyIterator(final LongMap it) {
      super(it);
   }
}
