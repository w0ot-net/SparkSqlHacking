package org.apache.spark;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M:aAC\u0006\t\u0002-\tbAB\n\f\u0011\u0003YA\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0004\u001f\u0003\t\u0007I\u0011A\u0010\t\r\u0011\n\u0001\u0015!\u0003!\u0011\u001d)\u0013A1A\u0005\u0002}AaAJ\u0001!\u0002\u0013\u0001\u0003bB\u0014\u0002\u0005\u0004%\ta\b\u0005\u0007Q\u0005\u0001\u000b\u0011\u0002\u0011\t\u000f%\n\u0011\u0011!C\u0005U\u0005\u0011RI\u001d:pe6+7o]1hK\u001a{'/\\1u\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<\u0007C\u0001\n\u0002\u001b\u0005Y!AE#se>\u0014X*Z:tC\u001e,gi\u001c:nCR\u001c\"!A\u000b\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0011#\u0001\u0004Q%\u0016#F+W\u000b\u0002AA\u0011\u0011EI\u0007\u0002\u0003%\u00111%\u0007\u0002\u0006-\u0006dW/Z\u0001\b!J+E\u000bV-!\u0003\u001di\u0015JT%N\u00032\u000b\u0001\"T%O\u00136\u000bE\nI\u0001\t'R\u000be\nR!S\t\u0006I1\u000bV!O\t\u0006\u0013F\tI\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002WA\u0011A&M\u0007\u0002[)\u0011afL\u0001\u0005Y\u0006twMC\u00011\u0003\u0011Q\u0017M^1\n\u0005Ij#AB(cU\u0016\u001cG\u000f"
)
public final class ErrorMessageFormat {
   public static Enumeration.Value STANDARD() {
      return ErrorMessageFormat$.MODULE$.STANDARD();
   }

   public static Enumeration.Value MINIMAL() {
      return ErrorMessageFormat$.MODULE$.MINIMAL();
   }

   public static Enumeration.Value PRETTY() {
      return ErrorMessageFormat$.MODULE$.PRETTY();
   }

   public static Enumeration.ValueSet ValueSet() {
      return ErrorMessageFormat$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return ErrorMessageFormat$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return ErrorMessageFormat$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return ErrorMessageFormat$.MODULE$.apply(x);
   }

   public static int maxId() {
      return ErrorMessageFormat$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return ErrorMessageFormat$.MODULE$.values();
   }

   public static String toString() {
      return ErrorMessageFormat$.MODULE$.toString();
   }
}
