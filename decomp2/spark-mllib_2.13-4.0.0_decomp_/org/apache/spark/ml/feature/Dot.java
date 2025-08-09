package org.apache.spark.ml.feature;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t<aa\u0003\u0007\t\u0002:1bA\u0002\r\r\u0011\u0003s\u0011\u0004C\u00034\u0003\u0011\u0005A\u0007C\u00046\u0003\u0005\u0005I\u0011\t\u001c\t\u000f}\n\u0011\u0011!C\u0001\u0001\"9A)AA\u0001\n\u0003)\u0005bB&\u0002\u0003\u0003%\t\u0005\u0014\u0005\b'\u0006\t\t\u0011\"\u0001U\u0011\u001dI\u0016!!A\u0005BiCqaW\u0001\u0002\u0002\u0013\u0005C\fC\u0004^\u0003\u0005\u0005I\u0011\u00020\u0002\u0007\u0011{GO\u0003\u0002\u000e\u001d\u00059a-Z1ukJ,'BA\b\u0011\u0003\tiGN\u0003\u0002\u0012%\u0005)1\u000f]1sW*\u00111\u0003F\u0001\u0007CB\f7\r[3\u000b\u0003U\t1a\u001c:h!\t9\u0012!D\u0001\r\u0005\r!u\u000e^\n\u0006\u0003i\u00013E\n\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]\t\u0013B\u0001\u0012\r\u0005AIe\u000e^3sC\u000e$\u0018M\u00197f)\u0016\u0014X\u000e\u0005\u0002\u001cI%\u0011Q\u0005\b\u0002\b!J|G-^2u!\t9\u0003G\u0004\u0002)]9\u0011\u0011&L\u0007\u0002U)\u00111\u0006L\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ$\u0003\u000209\u00059\u0001/Y2lC\u001e,\u0017BA\u00193\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tyC$\u0001\u0004=S:LGO\u0010\u000b\u0002-\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\u000e\t\u0003quj\u0011!\u000f\u0006\u0003um\nA\u0001\\1oO*\tA(\u0001\u0003kCZ\f\u0017B\u0001 :\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0011\t\u0005\u0002\u001c\u0005&\u00111\t\b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003\r&\u0003\"aG$\n\u0005!c\"aA!os\"9!*BA\u0001\u0002\u0004\t\u0015a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001N!\rq\u0015KR\u0007\u0002\u001f*\u0011\u0001\u000bH\u0001\u000bG>dG.Z2uS>t\u0017B\u0001*P\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005UC\u0006CA\u000eW\u0013\t9FDA\u0004C_>dW-\u00198\t\u000f);\u0011\u0011!a\u0001\r\u0006A\u0001.Y:i\u0007>$W\rF\u0001B\u0003!!xn\u0015;sS:<G#A\u001c\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003}\u0003\"\u0001\u000f1\n\u0005\u0005L$AB(cU\u0016\u001cG\u000f"
)
public final class Dot {
   public static String toString() {
      return Dot$.MODULE$.toString();
   }

   public static int hashCode() {
      return Dot$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return Dot$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return Dot$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return Dot$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return Dot$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return Dot$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return Dot$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return Dot$.MODULE$.productElementName(n);
   }

   public static Term interact(final Term other) {
      return Dot$.MODULE$.interact(other);
   }

   public static ColumnInteraction asInteraction() {
      return Dot$.MODULE$.asInteraction();
   }

   public static Term subtract(final Term other) {
      return Dot$.MODULE$.subtract(other);
   }

   public static Term add(final Term other) {
      return Dot$.MODULE$.add(other);
   }

   public static Terms asTerms() {
      return Dot$.MODULE$.asTerms();
   }
}
