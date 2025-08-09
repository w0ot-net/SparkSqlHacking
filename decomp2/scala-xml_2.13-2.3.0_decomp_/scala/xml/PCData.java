package scala.xml;

import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3AAC\u0006\u0001!!I\u0001\u0005\u0001B\u0001B\u0003%Q#\t\u0005\u0006E\u0001!\ta\t\u0005\u0006M\u0001!\teJ\u0004\u0006e-A\ta\r\u0004\u0006\u0015-A\t\u0001\u000e\u0005\u0006E\u0015!\t\u0001\u0011\u0005\u0006\u0003\u0016!\tA\u0011\u0005\u0006\t\u0016!\t!\u0012\u0005\b\u001d\u0016\t\t\u0011\"\u0003P\u0005\u0019\u00016\tR1uC*\u0011A\"D\u0001\u0004q6d'\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001!\u0005\t\u0004%M)R\"A\u0006\n\u0005QY!\u0001B!u_6\u0004\"AF\u000f\u000f\u0005]Y\u0002C\u0001\r\u000e\u001b\u0005I\"B\u0001\u000e\u0010\u0003\u0019a$o\\8u}%\u0011A$D\u0001\u0007!J,G-\u001a4\n\u0005yy\"AB*ue&twM\u0003\u0002\u001d\u001b\u0005!A-\u0019;b\u0013\t\u00013#\u0001\u0004=S:LGO\u0010\u000b\u0003I\u0015\u0002\"A\u0005\u0001\t\u000b\u0001\u0012\u0001\u0019A\u000b\u0002\u0017\t,\u0018\u000e\u001c3TiJLgn\u001a\u000b\u0003QA\u0002\"!K\u0017\u000f\u0005)ZS\"A\u0007\n\u00051j\u0011a\u00029bG.\fw-Z\u0005\u0003]=\u0012Qb\u0015;sS:<')^5mI\u0016\u0014(B\u0001\u0017\u000e\u0011\u0015\t4\u00011\u0001)\u0003\t\u0019(-\u0001\u0004Q\u0007\u0012\u000bG/\u0019\t\u0003%\u0015\u00192!B\u001b9!\tQc'\u0003\u00028\u001b\t1\u0011I\\=SK\u001a\u0004\"!\u000f \u000e\u0003iR!a\u000f\u001f\u0002\u0005%|'\"A\u001f\u0002\t)\fg/Y\u0005\u0003\u007fi\u0012AbU3sS\u0006d\u0017N_1cY\u0016$\u0012aM\u0001\u0006CB\u0004H.\u001f\u000b\u0003I\rCQ\u0001I\u0004A\u0002U\tq!\u001e8baBd\u0017\u0010\u0006\u0002G\u0013B\u0019!fR\u000b\n\u0005!k!AB(qi&|g\u000eC\u0003K\u0011\u0001\u00071*A\u0003pi\",'\u000f\u0005\u0002+\u0019&\u0011Q*\u0004\u0002\u0004\u0003:L\u0018\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001)\u0011\u0005E#V\"\u0001*\u000b\u0005Mc\u0014\u0001\u00027b]\u001eL!!\u0016*\u0003\r=\u0013'.Z2u\u0001"
)
public class PCData extends Atom {
   public StringBuilder buildString(final StringBuilder sb) {
      String dataStr = ((String)super.data()).replaceAll("]]>", "]]]]><![CDATA[>");
      return sb.append((new java.lang.StringBuilder(12)).append("<![CDATA[").append(dataStr).append("]]>").toString());
   }

   public PCData(final String data) {
      super(data);
   }
}
