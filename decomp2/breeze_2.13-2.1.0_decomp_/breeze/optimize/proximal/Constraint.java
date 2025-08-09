package breeze.optimize.proximal;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;Qa\u0005\u000b\t\u0002m1Q!\b\u000b\t\u0002yAQ!J\u0001\u0005\u0002\u0019*A!H\u0001\u0001O!91&\u0001b\u0001\n\u0003a\u0003BB\u0017\u0002A\u0003%q\u0005C\u0004/\u0003\t\u0007I\u0011\u0001\u0017\t\r=\n\u0001\u0015!\u0003(\u0011\u001d\u0001\u0014A1A\u0005\u00021Ba!M\u0001!\u0002\u00139\u0003b\u0002\u001a\u0002\u0005\u0004%\t\u0001\f\u0005\u0007g\u0005\u0001\u000b\u0011B\u0014\t\u000fQ\n!\u0019!C\u0001Y!1Q'\u0001Q\u0001\n\u001dBqAN\u0001C\u0002\u0013\u0005A\u0006\u0003\u00048\u0003\u0001\u0006Ia\n\u0005\bq\u0005\u0011\r\u0011\"\u0001-\u0011\u0019I\u0014\u0001)A\u0005O!9!(AA\u0001\n\u0013Y\u0014AC\"p]N$(/Y5oi*\u0011QCF\u0001\taJ|\u00070[7bY*\u0011q\u0003G\u0001\t_B$\u0018.\\5{K*\t\u0011$\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ta\u0012!D\u0001\u0015\u0005)\u0019uN\\:ue\u0006Lg\u000e^\n\u0003\u0003}\u0001\"\u0001I\u0012\u000e\u0003\u0005R\u0011AI\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0005\u00121\"\u00128v[\u0016\u0014\u0018\r^5p]\u00061A(\u001b8jiz\"\u0012a\u0007\t\u0003Q%j\u0011!A\u0005\u0003U\r\u0012QAV1mk\u0016\f\u0001\"\u0013#F\u001dRKE+W\u000b\u0002O\u0005I\u0011\nR#O)&#\u0016\fI\u0001\u0007'6{u\n\u0016%\u0002\u000fMkuj\u0014+IA\u0005A\u0001kT*J)&3V)A\u0005Q\u001fNKE+\u0013,FA\u0005\u0019!i\u0014-\u0002\t\t{\u0005\fI\u0001\u0007'B\u000b%kU#\u0002\u000fM\u0003\u0016IU*FA\u0005AQ)U+B\u0019&#\u0016,A\u0005F#V\u000bE*\u0013+ZA\u0005\u0011\u0002KU(C\u0003\nKE*\u0013+Z'&k\u0005\u000bT#Y\u0003M\u0001&k\u0014\"B\u0005&c\u0015\nV-T\u00136\u0003F*\u0012-!\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005a\u0004CA\u001fC\u001b\u0005q$BA A\u0003\u0011a\u0017M\\4\u000b\u0003\u0005\u000bAA[1wC&\u00111I\u0010\u0002\u0007\u001f\nTWm\u0019;"
)
public final class Constraint {
   public static Enumeration.Value PROBABILITYSIMPLEX() {
      return Constraint$.MODULE$.PROBABILITYSIMPLEX();
   }

   public static Enumeration.Value EQUALITY() {
      return Constraint$.MODULE$.EQUALITY();
   }

   public static Enumeration.Value SPARSE() {
      return Constraint$.MODULE$.SPARSE();
   }

   public static Enumeration.Value BOX() {
      return Constraint$.MODULE$.BOX();
   }

   public static Enumeration.Value POSITIVE() {
      return Constraint$.MODULE$.POSITIVE();
   }

   public static Enumeration.Value SMOOTH() {
      return Constraint$.MODULE$.SMOOTH();
   }

   public static Enumeration.Value IDENTITY() {
      return Constraint$.MODULE$.IDENTITY();
   }

   public static Enumeration.ValueSet ValueSet() {
      return Constraint$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return Constraint$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return Constraint$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return Constraint$.MODULE$.apply(x);
   }

   public static int maxId() {
      return Constraint$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return Constraint$.MODULE$.values();
   }

   public static String toString() {
      return Constraint$.MODULE$.toString();
   }
}
