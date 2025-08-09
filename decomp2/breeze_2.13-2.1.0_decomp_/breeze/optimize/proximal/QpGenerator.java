package breeze.optimize.proximal;

import breeze.linalg.DenseMatrix;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q:Q!\u0002\u0004\t\u000251Qa\u0004\u0004\t\u0002AAQaF\u0001\u0005\u0002aAQ!G\u0001\u0005\u0002iAQ!K\u0001\u0005\u0002)\n1\"\u00159HK:,'/\u0019;pe*\u0011q\u0001C\u0001\taJ|\u00070[7bY*\u0011\u0011BC\u0001\t_B$\u0018.\\5{K*\t1\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tq\u0011!D\u0001\u0007\u0005-\t\u0006oR3oKJ\fGo\u001c:\u0014\u0005\u0005\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002\u001b\u00059q-\u001a;He\u0006lGCA\u000e%!\rar$I\u0007\u0002;)\u0011aDC\u0001\u0007Y&t\u0017\r\\4\n\u0005\u0001j\"a\u0003#f]N,W*\u0019;sSb\u0004\"A\u0005\u0012\n\u0005\r\u001a\"A\u0002#pk\ndW\rC\u0003&\u0007\u0001\u0007a%A\u0003o\u000fJ\fW\u000e\u0005\u0002\u0013O%\u0011\u0001f\u0005\u0002\u0004\u0013:$\u0018!B1qa2LHcA\u00162eAA!\u0003L\u000e/]9r3$\u0003\u0002.'\t1A+\u001e9mKZ\u00022\u0001H\u0018\"\u0013\t\u0001TDA\u0006EK:\u001cXMV3di>\u0014\b\"B\u0013\u0005\u0001\u00041\u0003\"B\u001a\u0005\u0001\u00041\u0013a\u00038FcV\fG.\u001b;jKN\u0004"
)
public final class QpGenerator {
   public static Tuple6 apply(final int nGram, final int nEqualities) {
      return QpGenerator$.MODULE$.apply(nGram, nEqualities);
   }

   public static DenseMatrix getGram(final int nGram) {
      return QpGenerator$.MODULE$.getGram(nGram);
   }
}
