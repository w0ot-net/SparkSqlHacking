package scala;

import scala.annotation.Annotation;
import scala.annotation.StaticAnnotation;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3Aa\u0003\u0007\u0001\u001f!A\u0011\u0004\u0001B\u0001B\u0003%!\u0004\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003\u001b\u0011\u00151\u0003\u0001\"\u0001(\u0011\u00151\u0003\u0001\"\u0001-\u0011\u00151\u0003\u0001\"\u0001<\u000f\u001dYE\"!A\t\u000213qa\u0003\u0007\u0002\u0002#\u0005Q\nC\u0003'\u000f\u0011\u0005\u0011\u000bC\u0004S\u000fE\u0005I\u0011A*\t\u000fq;\u0011\u0013!C\u0001'\nqA-\u001a9sK\u000e\fG/\u001a3OC6,'\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0019\u0001\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0005Ma\u0011AC1o]>$\u0018\r^5p]&\u0011QC\u0005\u0002\u000b\u0003:tw\u000e^1uS>t\u0007CA\t\u0018\u0013\tA\"C\u0001\tTi\u0006$\u0018nY!o]>$\u0018\r^5p]\u0006!a.Y7f!\tY\"E\u0004\u0002\u001dAA\u0011Q\u0004D\u0007\u0002=)\u0011qDD\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005b\u0011A\u0002)sK\u0012,g-\u0003\u0002$I\t11\u000b\u001e:j]\u001eT!!\t\u0007\u0002\u000bMLgnY3\u0002\rqJg.\u001b;?)\rA#f\u000b\t\u0003S\u0001i\u0011\u0001\u0004\u0005\b3\r\u0001\n\u00111\u0001\u001b\u0011\u001d)3\u0001%AA\u0002i!2\u0001K\u00172\u0011\u0015IB\u00011\u0001/!\tIs&\u0003\u00021\u0019\t11+_7c_2DQ!\n\u0003A\u0002iAc\u0001B\u001a7o\u0015J\u0004CA\u00155\u0013\t)DB\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-I\u00019\u0003Q\"\u0006.\u001a\u0011qCJ\fW.\u001a;fe\u0002r\u0017-\\3!g\"|W\u000f\u001c3!E\u0016\u0004\u0013\rI*ue&tw\r\f\u0011o_R\u0004\u0013\rI:z[\n|GNL\u0011\u0002u\u00051!GL\u00194]A\"\"\u0001\u000b\u001f\t\u000be)\u0001\u0019\u0001\u0018)\r\u0015\u0019dgN\u0013:Q\t\u0001q\b\u0005\u0002A\u00076\t\u0011I\u0003\u0002C%\u0005!Q.\u001a;b\u0013\t!\u0015IA\u0003qCJ\fW\u000e\u000b\u0004\u0001\rZJU%\u000f\t\u0003S\u001dK!\u0001\u0013\u0007\u0003+\u0011,\u0007O]3dCR,G-\u00138iKJLG/\u00198dK\u0006\n!*A\u0014TG\",G-\u001e7fI\u00022wN\u001d\u0011cK&tw\r\t4j]\u0006d\u0007%\u001b8!i\",\u0007EZ;ukJ,\u0017A\u00043faJ,7-\u0019;fI:\u000bW.\u001a\t\u0003S\u001d\u0019\"a\u0002(\u0011\u0005%z\u0015B\u0001)\r\u0005\u0019\te.\u001f*fMR\tA*A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%M\u000b\u0002)*\u0012!$V\u0016\u0002-B\u0011qKW\u0007\u00021*\u0011\u0011LE\u0001\nk:\u001c\u0007.Z2lK\u0012L!a\u0017-\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HE\r"
)
public class deprecatedName extends Annotation implements StaticAnnotation {
   public static String $lessinit$greater$default$2() {
      deprecatedName$ var10000 = deprecatedName$.MODULE$;
      return "";
   }

   public static String $lessinit$greater$default$1() {
      deprecatedName$ var10000 = deprecatedName$.MODULE$;
      return "<none>";
   }

   public deprecatedName(final String name, final String since) {
   }

   /** @deprecated */
   public deprecatedName(final Symbol name, final String since) {
      this(name.name(), since);
   }

   /** @deprecated */
   public deprecatedName(final Symbol name) {
      this(name.name(), "");
   }
}
