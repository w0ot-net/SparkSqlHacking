package scala.xml.parsing;

import scala.reflect.ScalaSignature;
import scala.xml.Comment;
import scala.xml.Elem$;
import scala.xml.EntityRef;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.NodeSeq;
import scala.xml.ProcInstr;
import scala.xml.Text;
import scala.xml.Text$;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194Q\u0001C\u0005\u0002\u0002AAQ!\u0006\u0001\u0005\u0002YAq\u0001\u0007\u0001C\u0002\u001b\u0005\u0011\u0004C\u0003\u001f\u0001\u0011\u0005s\u0004C\u0003G\u0001\u0011\u0005s\tC\u0003Q\u0001\u0011\u0005\u0013\u000bC\u0003X\u0001\u0011\u0005\u0003\fC\u0003`\u0001\u0011\u0005\u0003MA\nD_:\u001cHO];di&tw\rS1oI2,'O\u0003\u0002\u000b\u0017\u00059\u0001/\u0019:tS:<'B\u0001\u0007\u000e\u0003\rAX\u000e\u001c\u0006\u0002\u001d\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u0012!\t\u00112#D\u0001\n\u0013\t!\u0012BA\u0007NCJ\\W\u000f\u001d%b]\u0012dWM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003]\u0001\"A\u0005\u0001\u0002\u0015A\u0014Xm]3sm\u0016<6+F\u0001\u001b!\tYB$D\u0001\u000e\u0013\tiRBA\u0004C_>dW-\u00198\u0002\t\u0015dW-\u001c\u000b\tA\u0011Jc\u0007O\u001fC\tB\u0011\u0011EI\u0007\u0002\u0017%\u00111e\u0003\u0002\b\u001d>$WmU3r\u0011\u0015)3\u00011\u0001'\u0003\r\u0001xn\u001d\t\u00037\u001dJ!\u0001K\u0007\u0003\u0007%sG\u000fC\u0003+\u0007\u0001\u00071&A\u0002qe\u0016\u0004\"\u0001L\u001a\u000f\u00055\n\u0004C\u0001\u0018\u000e\u001b\u0005y#B\u0001\u0019\u0010\u0003\u0019a$o\\8u}%\u0011!'D\u0001\u0007!J,G-\u001a4\n\u0005Q*$AB*ue&twM\u0003\u00023\u001b!)qg\u0001a\u0001W\u0005)A.\u00192fY\")\u0011h\u0001a\u0001u\u0005)\u0011\r\u001e;sgB\u0011\u0011eO\u0005\u0003y-\u0011\u0001\"T3uC\u0012\u000bG/\u0019\u0005\u0006}\r\u0001\raP\u0001\u0007aN\u001cw\u000e]3\u0011\u0005\u0005\u0002\u0015BA!\f\u0005Aq\u0015-\\3ta\u0006\u001cWMQ5oI&tw\rC\u0003D\u0007\u0001\u0007!$A\u0003f[B$\u0018\u0010C\u0003F\u0007\u0001\u0007\u0001%A\u0003o_\u0012,7/A\u0005qe>\u001c\u0017J\\:ueR!\u0001j\u0013'O!\t\t\u0013*\u0003\u0002K\u0017\tI\u0001K]8d\u0013:\u001cHO\u001d\u0005\u0006K\u0011\u0001\rA\n\u0005\u0006\u001b\u0012\u0001\raK\u0001\u0007i\u0006\u0014x-\u001a;\t\u000b=#\u0001\u0019A\u0016\u0002\u0007QDH/A\u0004d_6lWM\u001c;\u0015\u0007I+f\u000b\u0005\u0002\"'&\u0011Ak\u0003\u0002\b\u0007>lW.\u001a8u\u0011\u0015)S\u00011\u0001'\u0011\u0015yU\u00011\u0001,\u0003%)g\u000e^5usJ+g\rF\u0002Z9v\u0003\"!\t.\n\u0005m[!!C#oi&$\u0018PU3g\u0011\u0015)c\u00011\u0001'\u0011\u0015qf\u00011\u0001,\u0003\u0005q\u0017\u0001\u0002;fqR$2!\u00193f!\t\t#-\u0003\u0002d\u0017\t!A+\u001a=u\u0011\u0015)s\u00011\u0001'\u0011\u0015yu\u00011\u0001,\u0001"
)
public abstract class ConstructingHandler extends MarkupHandler {
   public abstract boolean preserveWS();

   public NodeSeq elem(final int pos, final String pre, final String label, final MetaData attrs, final NamespaceBinding pscope, final boolean empty, final NodeSeq nodes) {
      return Elem$.MODULE$.apply(pre, label, attrs, pscope, empty, nodes);
   }

   public ProcInstr procInstr(final int pos, final String target, final String txt) {
      return new ProcInstr(target, txt);
   }

   public Comment comment(final int pos, final String txt) {
      return new Comment(txt);
   }

   public EntityRef entityRef(final int pos, final String n) {
      return new EntityRef(n);
   }

   public Text text(final int pos, final String txt) {
      return Text$.MODULE$.apply(txt);
   }
}
