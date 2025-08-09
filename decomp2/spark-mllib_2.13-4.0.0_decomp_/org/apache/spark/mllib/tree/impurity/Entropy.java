package org.apache.spark.mllib.tree.impurity;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u;QAC\u0006\t\u0002a1QAG\u0006\t\u0002mAQ!J\u0001\u0005\u0002\u0019BqaJ\u0001C\u0002\u0013%\u0001\u0006\u0003\u0004-\u0003\u0001\u0006I!\u000b\u0005\u0007[\u0005!\t!\u0004\u0018\t\u000bE\nA\u0011\t\u001a\t\u000bE\nA\u0011I\"\t\u000b5\u000bA\u0011\u0001(\t\u000fE\u000b\u0011\u0011!C\u0005%\u00069QI\u001c;s_BL(B\u0001\u0007\u000e\u0003!IW\u000e];sSRL(B\u0001\b\u0010\u0003\u0011!(/Z3\u000b\u0005A\t\u0012!B7mY&\u0014'B\u0001\n\u0014\u0003\u0015\u0019\b/\u0019:l\u0015\t!R#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0005\u0019qN]4\u0004\u0001A\u0011\u0011$A\u0007\u0002\u0017\t9QI\u001c;s_BL8cA\u0001\u001dEA\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\t1\u0011I\\=SK\u001a\u0004\"!G\u0012\n\u0005\u0011Z!\u0001C%naV\u0014\u0018\u000e^=\u0002\rqJg.\u001b;?)\u0005A\u0012!B0m_\u001e\u0014T#A\u0015\u0011\u0005uQ\u0013BA\u0016\u001f\u0005\u0019!u.\u001e2mK\u00061q\f\\8he\u0001\nA\u0001\\8heQ\u0011\u0011f\f\u0005\u0006a\u0015\u0001\r!K\u0001\u0002q\u0006I1-\u00197dk2\fG/\u001a\u000b\u0004SMB\u0004\"\u0002\u001b\u0007\u0001\u0004)\u0014AB2pk:$8\u000fE\u0002\u001em%J!a\u000e\u0010\u0003\u000b\u0005\u0013(/Y=\t\u000be2\u0001\u0019A\u0015\u0002\u0015Q|G/\u00197D_VtG\u000fK\u0002\u0007w\u0005\u0003\"\u0001P \u000e\u0003uR!AP\t\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002A{\t)1+\u001b8dK\u0006\n!)A\u00032]Er\u0003\u0007\u0006\u0003*\t\u001aC\u0005\"B#\b\u0001\u0004I\u0013!B2pk:$\b\"B$\b\u0001\u0004I\u0013aA:v[\")\u0011j\u0002a\u0001S\u0005Q1/^7TcV\f'/Z:)\u0007\u001dY4*I\u0001M\u0003\u0015\td\u0006\r\u00181\u0003!Ign\u001d;b]\u000e,W#A(\u000e\u0003\u0005A3\u0001C\u001eB\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005\u0019\u0006C\u0001+Z\u001b\u0005)&B\u0001,X\u0003\u0011a\u0017M\\4\u000b\u0003a\u000bAA[1wC&\u0011!,\u0016\u0002\u0007\u001f\nTWm\u0019;)\u0007\u0005Y4\nK\u0002\u0001w-\u0003"
)
public final class Entropy {
   public static Entropy$ instance() {
      return Entropy$.MODULE$.instance();
   }

   public static double calculate(final double count, final double sum, final double sumSquares) {
      return Entropy$.MODULE$.calculate(count, sum, sumSquares);
   }

   public static double calculate(final double[] counts, final double totalCount) {
      return Entropy$.MODULE$.calculate(counts, totalCount);
   }
}
