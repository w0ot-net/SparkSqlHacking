package scala.xml.parsing;

import scala.reflect.ScalaSignature;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.NodeSeq;
import scala.xml.NodeSeq$;

@ScalaSignature(
   bytes = "\u0006\u0005]3Qa\u0002\u0005\u0002\u0002=AQ\u0001\u0006\u0001\u0005\u0002UAQa\u0006\u0001\u0005BaAQa\u0011\u0001\u0005B\u0011CQA\u0013\u0001\u0005B-CQA\u0014\u0001\u0005B=CQa\u0015\u0001\u0005BQ\u0013A\u0003R3gCVdG/T1sWV\u0004\b*\u00198eY\u0016\u0014(BA\u0005\u000b\u0003\u001d\u0001\u0018M]:j]\u001eT!a\u0003\u0007\u0002\u0007alGNC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\t\u0011\u0005E\u0011R\"\u0001\u0005\n\u0005MA!!D'be.,\b\u000fS1oI2,'/\u0001\u0004=S:LGO\u0010\u000b\u0002-A\u0011\u0011\u0003A\u0001\u0005K2,W\u000e\u0006\u0005\u001a;\r\u0002$g\u000e\u001fB!\tQ2$D\u0001\u000b\u0013\ta\"BA\u0004O_\u0012,7+Z9\t\u000by\u0011\u0001\u0019A\u0010\u0002\u0007A|7\u000f\u0005\u0002!C5\tA\"\u0003\u0002#\u0019\t\u0019\u0011J\u001c;\t\u000b\u0011\u0012\u0001\u0019A\u0013\u0002\u0007A\u0014X\r\u0005\u0002'[9\u0011qe\u000b\t\u0003Q1i\u0011!\u000b\u0006\u0003U9\ta\u0001\u0010:p_Rt\u0014B\u0001\u0017\r\u0003\u0019\u0001&/\u001a3fM&\u0011af\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051b\u0001\"B\u0019\u0003\u0001\u0004)\u0013!\u00027bE\u0016d\u0007\"B\u001a\u0003\u0001\u0004!\u0014!B1uiJ\u001c\bC\u0001\u000e6\u0013\t1$B\u0001\u0005NKR\fG)\u0019;b\u0011\u0015A$\u00011\u0001:\u0003\u0015\u00198m\u001c9f!\tQ\"(\u0003\u0002<\u0015\t\u0001b*Y7fgB\f7-\u001a\"j]\u0012Lgn\u001a\u0005\u0006{\t\u0001\rAP\u0001\u0006K6\u0004H/\u001f\t\u0003A}J!\u0001\u0011\u0007\u0003\u000f\t{w\u000e\\3b]\")!I\u0001a\u00013\u0005!\u0011M]4t\u0003%\u0001(o\\2J]N$(\u000f\u0006\u0003\u001a\u000b\u001aC\u0005\"\u0002\u0010\u0004\u0001\u0004y\u0002\"B$\u0004\u0001\u0004)\u0013A\u0002;be\u001e,G\u000fC\u0003J\u0007\u0001\u0007Q%A\u0002uqR\fqaY8n[\u0016tG\u000fF\u0002\u001a\u00196CQA\b\u0003A\u0002}AQA\u0013\u0003A\u0002\u0015\n\u0011\"\u001a8uSRL(+\u001a4\u0015\u0007e\u0001\u0016\u000bC\u0003\u001f\u000b\u0001\u0007q\u0004C\u0003S\u000b\u0001\u0007Q%A\u0001o\u0003\u0011!X\r\u001f;\u0015\u0007e)f\u000bC\u0003\u001f\r\u0001\u0007q\u0004C\u0003J\r\u0001\u0007Q\u0005"
)
public abstract class DefaultMarkupHandler extends MarkupHandler {
   public NodeSeq elem(final int pos, final String pre, final String label, final MetaData attrs, final NamespaceBinding scope, final boolean empty, final NodeSeq args) {
      return NodeSeq$.MODULE$.Empty();
   }

   public NodeSeq procInstr(final int pos, final String target, final String txt) {
      return NodeSeq$.MODULE$.Empty();
   }

   public NodeSeq comment(final int pos, final String comment) {
      return NodeSeq$.MODULE$.Empty();
   }

   public NodeSeq entityRef(final int pos, final String n) {
      return NodeSeq$.MODULE$.Empty();
   }

   public NodeSeq text(final int pos, final String txt) {
      return NodeSeq$.MODULE$.Empty();
   }
}
