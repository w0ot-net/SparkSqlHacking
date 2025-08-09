package scala.xml.dtd;

import scala.collection.Seq;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.xml.parsing.TokenTests;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Qa\u0002\u0005\u0002\"=AQA\u0007\u0001\u0005\u0002mAQA\b\u0001\u0005\u0002}AQ!\f\u0001\u0005B9BQa\f\u0001\u0005\u0002ABQA\u000f\u0001\u0007\u0002mBQ\u0001\u0010\u0001\u0007\u0002m\u0012!\"\u0012=uKJt\u0017\r\\%E\u0015\tI!\"A\u0002ei\u0012T!a\u0003\u0007\u0002\u0007alGNC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001\u0001\t\u0015!\t\t\"#D\u0001\r\u0013\t\u0019BB\u0001\u0004B]f\u0014VM\u001a\t\u0003+ai\u0011A\u0006\u0006\u0003/)\tq\u0001]1sg&tw-\u0003\u0002\u001a-\tQAk\\6f]R+7\u000f^:\u0002\rqJg.\u001b;?)\u0005a\u0002CA\u000f\u0001\u001b\u0005A\u0011AB9v_R,G\r\u0006\u0002!WA\u0011\u0011\u0005\u000b\b\u0003E\u0019\u0002\"a\t\u0007\u000e\u0003\u0011R!!\n\b\u0002\rq\u0012xn\u001c;?\u0013\t9C\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003S)\u0012aa\u0015;sS:<'BA\u0014\r\u0011\u0015a#\u00011\u0001!\u0003\u0005\u0019\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0001\n1BY;jY\u0012\u001cFO]5oOR\u0011\u0011\u0007\u000f\t\u0003eUr!!E\u001a\n\u0005Qb\u0011a\u00029bG.\fw-Z\u0005\u0003m]\u0012Qb\u0015;sS:<')^5mI\u0016\u0014(B\u0001\u001b\r\u0011\u0015ID\u00011\u00012\u0003\t\u0019(-\u0001\u0005tsN$X-\\%e+\u0005\u0001\u0013\u0001\u00039vE2L7-\u00133*\t\u0001q\u0004I\u0011\u0006\u0003\u007f!\tABT8FqR,'O\\1m\u0013\u0012K!!\u0011\u0005\u0003\u0011A+(\r\\5d\u0013\u0012K!a\u0011\u0005\u0003\u0011MK8\u000f^3n\u0013\u0012\u0003"
)
public abstract class ExternalID implements TokenTests {
   public final boolean isSpace(final char ch) {
      return TokenTests.isSpace$(this, ch);
   }

   public final boolean isSpace(final Seq cs) {
      return TokenTests.isSpace$(this, cs);
   }

   public boolean isAlpha(final char c) {
      return TokenTests.isAlpha$(this, c);
   }

   public boolean isAlphaDigit(final char c) {
      return TokenTests.isAlphaDigit$(this, c);
   }

   public boolean isNameChar(final char ch) {
      return TokenTests.isNameChar$(this, ch);
   }

   public boolean isNameStart(final char ch) {
      return TokenTests.isNameStart$(this, ch);
   }

   public boolean isName(final String s) {
      return TokenTests.isName$(this, s);
   }

   public boolean isPubIDChar(final char ch) {
      return TokenTests.isPubIDChar$(this, ch);
   }

   public boolean isValidIANAEncoding(final Seq ianaEncoding) {
      return TokenTests.isValidIANAEncoding$(this, ianaEncoding);
   }

   public boolean checkSysID(final String s) {
      return TokenTests.checkSysID$(this, s);
   }

   public boolean checkPubID(final String s) {
      return TokenTests.checkPubID$(this, s);
   }

   public String quoted(final String s) {
      char c = (char)(.MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(s), '"') ? 39 : 34);
      return (new StringBuilder(0)).append(c).append(s).append(c).toString();
   }

   public String toString() {
      if (this.publicId() == null) {
         return (new StringBuilder(7)).append("SYSTEM ").append(this.quoted(this.systemId())).toString();
      } else {
         return this.systemId() == null ? (new StringBuilder(7)).append("PUBLIC ").append(this.quoted(this.publicId())).toString() : (new StringBuilder(8)).append("PUBLIC ").append(this.quoted(this.publicId())).append(" ").append(this.quoted(this.systemId())).toString();
      }
   }

   public scala.collection.mutable.StringBuilder buildString(final scala.collection.mutable.StringBuilder sb) {
      return sb.append(this.toString());
   }

   public abstract String systemId();

   public abstract String publicId();

   public ExternalID() {
      TokenTests.$init$(this);
   }
}
