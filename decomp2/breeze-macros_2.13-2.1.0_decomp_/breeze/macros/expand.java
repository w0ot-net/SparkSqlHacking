package breeze.macros;

import scala.annotation.Annotation;
import scala.annotation.StaticAnnotation;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3A\u0001E\t\u0001-!)!\u0005\u0001C\u0001G\u001d)A&\u0005E\u0001[\u0019)\u0001#\u0005E\u0001]!)!e\u0001C\u0001g\u0019!Ag\u0001\u00016\u0011!1TA!A!\u0002\u00139\u0004\"\u0002\u0012\u0006\t\u0003id\u0001B!\u0004\u0001\tC\u0001B\u000e\u0005\u0003\u0002\u0003\u0006Ia\u000e\u0005\u0006E!!\ta\u0011\u0004\u0005\r\u000e\u0001q\tC\u0003#\u0017\u0011\u0005\u0001J\u0002\u0003K\u0007\u0001Y\u0005\u0002\u0003\u001c\u000e\u0005\u0003\u0005\u000b\u0011B\u001c\t\u000b\tjA\u0011A'\u0003\r\u0015D\b/\u00198e\u0015\t\u00112#\u0001\u0004nC\u000e\u0014xn\u001d\u0006\u0002)\u00051!M]3fu\u0016\u001c\u0001aE\u0002\u0001/}\u0001\"\u0001G\u000f\u000e\u0003eQ!AG\u000e\u0002\u0015\u0005tgn\u001c;bi&|gNC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq\u0012D\u0001\u0006B]:|G/\u0019;j_:\u0004\"\u0001\u0007\u0011\n\u0005\u0005J\"\u0001E*uCRL7-\u00118o_R\fG/[8o\u0003\u0019a\u0014N\\5u}Q\tA\u0005\u0005\u0002&\u00015\t\u0011\u0003K\u0002\u0001O)\u0002\"\u0001\u0007\u0015\n\u0005%J\"aD2p[BLG.\u001a+j[\u0016|e\u000e\\=\"\u0003-\nA%V:fAQDW\r\t2sK\u0016TX-L2pI\u0016<WM\\\u0017fqB\fg\u000e\u001a\u0011qYV<\u0017N\\\u0001\u0007Kb\u0004\u0018M\u001c3\u0011\u0005\u0015\u001a1CA\u00020!\t\u0001\u0014'D\u0001\u001c\u0013\t\u00114D\u0001\u0004B]f\u0014VM\u001a\u000b\u0002[\t!\u0011M]4t'\r)qcH\u0001\u0005CJ<7\u000fE\u00021qiJ!!O\u000e\u0003\u0015q\u0012X\r]3bi\u0016$g\b\u0005\u00021w%\u0011Ah\u0007\u0002\u0004\u0003:LHC\u0001 A!\tyT!D\u0001\u0004\u0011\u00151t\u00011\u00018\u0005\u001d)\u0007p\u00197vI\u0016\u001c2\u0001C\f )\t!U\t\u0005\u0002@\u0011!)aG\u0003a\u0001o\t1a/\u00197jMf\u001c2aC\f )\u0005I\u0005CA \f\u0005!\u0019X-];f]\u000e,WC\u0001'R'\riqc\b\u000b\u0003\u001d^\u00032aP\u0007P!\t\u0001\u0016\u000b\u0004\u0001\u0005\u000bIk!\u0019A*\u0003\u0003Q\u000b\"\u0001\u0016\u001e\u0011\u0005A*\u0016B\u0001,\u001c\u0005\u001dqu\u000e\u001e5j]\u001eDQAN\bA\u0002]\u0002"
)
public class expand extends Annotation implements StaticAnnotation {
   public static class args extends Annotation implements StaticAnnotation {
      public args(final Seq args) {
      }
   }

   public static class exclude extends Annotation implements StaticAnnotation {
      public exclude(final Seq args) {
      }
   }

   public static class valify extends Annotation implements StaticAnnotation {
   }

   public static class sequence extends Annotation implements StaticAnnotation {
      public sequence(final Seq args) {
      }
   }
}
