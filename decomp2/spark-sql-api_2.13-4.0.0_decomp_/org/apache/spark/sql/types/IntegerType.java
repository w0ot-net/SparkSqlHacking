package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005q4A\u0001E\t\u00019!)\u0011\u0005\u0001C\u0005E!)A\u0005\u0001C!K!)A\u0006\u0001C![!1\u0011\b\u0001C!+i:QAQ\t\t\u0002\u000e3Q\u0001E\t\t\u0002\u0012CQ!\t\u0004\u0005\u0002ECqA\u0015\u0004\u0002\u0002\u0013\u00053\u000bC\u0004\\\r\u0005\u0005I\u0011A\u0013\t\u000fq3\u0011\u0011!C\u0001;\"91MBA\u0001\n\u0003\"\u0007bB6\u0007\u0003\u0003%\t\u0001\u001c\u0005\bc\u001a\t\t\u0011\"\u0011s\u0011\u001d\u0019h!!A\u0005BQDq!\u001e\u0004\u0002\u0002\u0013%aOA\u0006J]R,w-\u001a:UsB,'B\u0001\n\u0014\u0003\u0015!\u0018\u0010]3t\u0015\t!R#A\u0002tc2T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0004\u0005\u0002\u001f?5\t\u0011#\u0003\u0002!#\ta\u0011J\u001c;fOJ\fG\u000eV=qK\u00061A(\u001b8jiz\"\u0012a\t\t\u0003=\u0001\t1\u0002Z3gCVdGoU5{KV\ta\u0005\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003FA\u0002J]R\fAb]5na2,7\u000b\u001e:j]\u001e,\u0012A\f\t\u0003_Yr!\u0001\r\u001b\u0011\u0005EBS\"\u0001\u001a\u000b\u0005MZ\u0012A\u0002\u001fs_>$h(\u0003\u00026Q\u00051\u0001K]3eK\u001aL!a\u000e\u001d\u0003\rM#(/\u001b8h\u0015\t)\u0004&\u0001\u0006bg:+H\u000e\\1cY\u0016,\u0012a\t\u0015\u0003\u0001q\u0002\"!\u0010!\u000e\u0003yR!aP\u000b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002B}\t11\u000b^1cY\u0016\f1\"\u00138uK\u001e,'\u000fV=qKB\u0011aDB\n\u0005\r\r*\u0005\n\u0005\u0002(\r&\u0011q\t\u000b\u0002\b!J|G-^2u!\tIeJ\u0004\u0002K\u0019:\u0011\u0011gS\u0005\u0002S%\u0011Q\nK\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005K\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002NQQ\t1)A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002)B\u0011QKW\u0007\u0002-*\u0011q\u000bW\u0001\u0005Y\u0006twMC\u0001Z\u0003\u0011Q\u0017M^1\n\u0005]2\u0016\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003=\u0006\u0004\"aJ0\n\u0005\u0001D#aA!os\"9!MCA\u0001\u0002\u00041\u0013a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001f!\r1\u0017NX\u0007\u0002O*\u0011\u0001\u000eK\u0001\u000bG>dG.Z2uS>t\u0017B\u00016h\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u00055\u0004\bCA\u0014o\u0013\ty\u0007FA\u0004C_>dW-\u00198\t\u000f\td\u0011\u0011!a\u0001=\u0006A\u0001.Y:i\u0007>$W\rF\u0001'\u0003!!xn\u0015;sS:<G#\u0001+\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003]\u0004\"!\u0016=\n\u0005e4&AB(cU\u0016\u001cG\u000f\u000b\u0002\u0007y!\u0012Q\u0001\u0010"
)
public class IntegerType extends IntegralType {
   public static boolean canEqual(final Object x$1) {
      return IntegerType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return IntegerType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return IntegerType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return IntegerType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return IntegerType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return IntegerType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return IntegerType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 4;
   }

   public String simpleString() {
      return "int";
   }

   public IntegerType asNullable() {
      return this;
   }
}
