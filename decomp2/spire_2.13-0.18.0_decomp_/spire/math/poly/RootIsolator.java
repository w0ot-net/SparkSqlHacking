package spire.math.poly;

import scala.collection.immutable.Vector;
import scala.reflect.ScalaSignature;
import spire.math.Polynomial;

@ScalaSignature(
   bytes = "\u0006\u0005%4q\u0001D\u0007\u0011\u0002G\u0005B\u0003C\u0003\u001d\u0001\u0019\u0005QdB\u0003O\u001b!\u0005qJB\u0003\r\u001b!\u0005\u0001\u000bC\u0003R\u0007\u0011\u0005!\u000bC\u0004T\u0007\t\u0007I1\u0001+\t\rY\u001b\u0001\u0015!\u0003V\u0011\u001d96A1A\u0005\u0004aCa!X\u0002!\u0002\u0013I\u0006b\u00020\u0004\u0005\u0004%\u0019a\u0018\u0005\u0007I\u000e\u0001\u000b\u0011\u00021\t\u000b\u0015\u001cAQ\u00024\u0003\u0019I{w\u000e^%t_2\fGo\u001c:\u000b\u00059y\u0011\u0001\u00029pYfT!\u0001E\t\u0002\t5\fG\u000f\u001b\u0006\u0002%\u0005)1\u000f]5sK\u000e\u0001QCA\u000b8'\t\u0001a\u0003\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004B]f\u0014VMZ\u0001\rSN|G.\u0019;f%>|Go\u001d\u000b\u0003=E\u00022aH\u0014+\u001d\t\u0001SE\u0004\u0002\"I5\t!E\u0003\u0002$'\u00051AH]8pizJ\u0011!G\u0005\u0003Ma\tq\u0001]1dW\u0006<W-\u0003\u0002)S\t1a+Z2u_JT!A\n\r\u0011\u0007-bc&D\u0001\u0010\u0013\tisB\u0001\u0005J]R,'O^1m!\tYs&\u0003\u00021\u001f\tA!+\u0019;j_:\fG\u000eC\u0003\u000f\u0003\u0001\u0007!\u0007E\u0002,gUJ!\u0001N\b\u0003\u0015A{G.\u001f8p[&\fG\u000e\u0005\u00027o1\u0001A!\u0002\u001d\u0001\u0005\u0004I$!A!\u0012\u0005ij\u0004CA\f<\u0013\ta\u0004DA\u0004O_RD\u0017N\\4\u0011\u0005]q\u0014BA \u0019\u0005\r\te._\u0015\u0003\u0001\u00053AA\u0011\u0001\u0001\u0007\niA\b\\8dC2\u00043\r[5mIz\u001a2!\u0011#M!\t)%*D\u0001G\u0015\t9\u0005*\u0001\u0003mC:<'\"A%\u0002\t)\fg/Y\u0005\u0003\u0017\u001a\u0013aa\u00142kK\u000e$\bcA'\u0001k5\tQ\"\u0001\u0007S_>$\u0018j]8mCR|'\u000f\u0005\u0002N\u0007M\u00111AF\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003=\u000bACU1uS>t\u0017\r\u001c*p_RL5o\u001c7bi>\u0014X#A+\u0011\u00075\u0003a&A\u000bSCRLwN\\1m%>|G/S:pY\u0006$xN\u001d\u0011\u0002-\tKw\rR3dS6\fGNU8pi&\u001bx\u000e\\1u_J,\u0012!\u0017\t\u0004\u001b\u0002Q\u0006CA\u0010\\\u0013\ta\u0016F\u0001\u0006CS\u001e$UmY5nC2\fqCQ5h\t\u0016\u001c\u0017.\\1m%>|G/S:pY\u0006$xN\u001d\u0011\u0002%\tKw-\u00138u%>|G/S:pY\u0006$xN]\u000b\u0002AB\u0019Q\nA1\u0011\u0005}\u0011\u0017BA2*\u0005\u0019\u0011\u0015nZ%oi\u0006\u0019\")[4J]R\u0014vn\u001c;Jg>d\u0017\r^8sA\u0005\u0019a+Q*\u0015\u0005y9\u0007\"\u0002\b\f\u0001\u0004A\u0007cA\u00164C\u0002"
)
public interface RootIsolator {
   static RootIsolator BigIntRootIsolator() {
      return RootIsolator$.MODULE$.BigIntRootIsolator();
   }

   static RootIsolator BigDecimalRootIsolator() {
      return RootIsolator$.MODULE$.BigDecimalRootIsolator();
   }

   static RootIsolator RationalRootIsolator() {
      return RootIsolator$.MODULE$.RationalRootIsolator();
   }

   Vector isolateRoots(final Polynomial poly);
}
