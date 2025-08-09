package org.apache.spark.mllib.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=;aAB\u0004\t\u0002-\tbAB\n\b\u0011\u0003YA\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0003\u001f\u0003\u0011\u0005q\u0004C\u0003+\u0003\u0011\u00051\u0006C\u00034\u0003\u0011%A'A\u000bDQ>dWm]6z\t\u0016\u001cw.\u001c9pg&$\u0018n\u001c8\u000b\u0005!I\u0011A\u00027j]\u0006dwM\u0003\u0002\u000b\u0017\u0005)Q\u000e\u001c7jE*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014x\r\u0005\u0002\u0013\u00035\tqAA\u000bDQ>dWm]6z\t\u0016\u001cw.\u001c9pg&$\u0018n\u001c8\u0014\u0005\u0005)\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\t\u0012!B:pYZ,Gc\u0001\u0011'QA\u0019a#I\u0012\n\u0005\t:\"!B!se\u0006L\bC\u0001\f%\u0013\t)sC\u0001\u0004E_V\u0014G.\u001a\u0005\u0006O\r\u0001\r\u0001I\u0001\u0002\u0003\")\u0011f\u0001a\u0001A\u0005\u0011!\r_\u0001\bS:4XM]:f)\r\u0001CF\f\u0005\u0006[\u0011\u0001\r\u0001I\u0001\u0004+\u0006K\u0007\"B\u0018\u0005\u0001\u0004\u0001\u0014!A6\u0011\u0005Y\t\u0014B\u0001\u001a\u0018\u0005\rIe\u000e^\u0001\u0011G\",7m\u001b*fiV\u0014hNV1mk\u0016$2!\u000e\u001dC!\t1b'\u0003\u00028/\t!QK\\5u\u0011\u0015IT\u00011\u0001;\u0003\u0011IgNZ8\u0011\u0005m\u0002U\"\u0001\u001f\u000b\u0005ur\u0014\u0001B;uS2T!aP\b\u0002\r9,G\u000f\\5c\u0013\t\tEH\u0001\u0003j]R<\u0006\"B\"\u0006\u0001\u0004!\u0015AB7fi\"|G\r\u0005\u0002F\u0019:\u0011aI\u0013\t\u0003\u000f^i\u0011\u0001\u0013\u0006\u0003\u0013r\ta\u0001\u0010:p_Rt\u0014BA&\u0018\u0003\u0019\u0001&/\u001a3fM&\u0011QJ\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005-;\u0002"
)
public final class CholeskyDecomposition {
   public static double[] inverse(final double[] UAi, final int k) {
      return CholeskyDecomposition$.MODULE$.inverse(UAi, k);
   }

   public static double[] solve(final double[] A, final double[] bx) {
      return CholeskyDecomposition$.MODULE$.solve(A, bx);
   }
}
