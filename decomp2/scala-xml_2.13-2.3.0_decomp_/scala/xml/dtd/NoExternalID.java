package scala.xml.dtd;

import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005M:Q\u0001C\u0005\t\u0002A1QAE\u0005\t\u0002MAQaF\u0001\u0005\u0002aAq!G\u0001C\u0002\u0013\u0005#\u0004\u0003\u0004$\u0003\u0001\u0006Ia\u0007\u0005\bI\u0005\u0011\r\u0011\"\u0011\u001b\u0011\u0019)\u0013\u0001)A\u00057!)a%\u0001C!O\u0005aaj\\#yi\u0016\u0014h.\u00197J\t*\u0011!bC\u0001\u0004IR$'B\u0001\u0007\u000e\u0003\rAX\u000e\u001c\u0006\u0002\u001d\u0005)1oY1mC\u000e\u0001\u0001CA\t\u0002\u001b\u0005I!\u0001\u0004(p\u000bb$XM\u001d8bY&#5CA\u0001\u0015!\t\tR#\u0003\u0002\u0017\u0013\tQQ\t\u001f;fe:\fG.\u0013#\u0002\rqJg.\u001b;?)\u0005\u0001\u0012\u0001\u00039vE2L7-\u00133\u0016\u0003m\u0001\"\u0001\b\u0011\u000f\u0005uqR\"A\u0006\n\u0005}Y\u0011aH*dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7MU3ukJtG+\u001f9fg&\u0011\u0011E\t\u0002\u000f\u001d>,\u0005\u0010^3s]\u0006d\u0017\nR%e\u0015\ty2\"A\u0005qk\nd\u0017nY%eA\u0005A1/_:uK6LE-A\u0005tsN$X-\\%eA\u0005AAo\\*ue&tw\rF\u0001)!\tI\u0003G\u0004\u0002+]A\u00111&D\u0007\u0002Y)\u0011QfD\u0001\u0007yI|w\u000e\u001e \n\u0005=j\u0011A\u0002)sK\u0012,g-\u0003\u00022e\t11\u000b\u001e:j]\u001eT!aL\u0007"
)
public final class NoExternalID {
   public static String toString() {
      return NoExternalID$.MODULE$.toString();
   }

   public static Null systemId() {
      return NoExternalID$.MODULE$.systemId();
   }

   public static Null publicId() {
      return NoExternalID$.MODULE$.publicId();
   }

   public static StringBuilder buildString(final StringBuilder sb) {
      return NoExternalID$.MODULE$.buildString(sb);
   }

   public static String quoted(final String s) {
      return NoExternalID$.MODULE$.quoted(s);
   }

   public static boolean checkPubID(final String s) {
      return NoExternalID$.MODULE$.checkPubID(s);
   }

   public static boolean checkSysID(final String s) {
      return NoExternalID$.MODULE$.checkSysID(s);
   }

   public static boolean isValidIANAEncoding(final Seq ianaEncoding) {
      return NoExternalID$.MODULE$.isValidIANAEncoding(ianaEncoding);
   }

   public static boolean isPubIDChar(final char ch) {
      return NoExternalID$.MODULE$.isPubIDChar(ch);
   }

   public static boolean isName(final String s) {
      return NoExternalID$.MODULE$.isName(s);
   }

   public static boolean isNameStart(final char ch) {
      return NoExternalID$.MODULE$.isNameStart(ch);
   }

   public static boolean isNameChar(final char ch) {
      return NoExternalID$.MODULE$.isNameChar(ch);
   }

   public static boolean isAlphaDigit(final char c) {
      return NoExternalID$.MODULE$.isAlphaDigit(c);
   }

   public static boolean isAlpha(final char c) {
      return NoExternalID$.MODULE$.isAlpha(c);
   }

   public static boolean isSpace(final Seq cs) {
      return NoExternalID$.MODULE$.isSpace(cs);
   }

   public static boolean isSpace(final char ch) {
      return NoExternalID$.MODULE$.isSpace(ch);
   }
}
