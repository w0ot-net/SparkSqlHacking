package org.apache.spark.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.lang.invoke.SerializedLambda;
import java.util.Enumeration;
import org.apache.commons.text.StringEscapeUtils;
import scala.MatchError;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005)4AAD\b\u00051!A1\u0005\u0001B\u0001B\u0003%A\u0005\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003)\u0011\u0015)\u0004\u0001\"\u00017\u0011\u001dY\u0004A1A\u0005\nqBa!\u0012\u0001!\u0002\u0013i\u0004b\u0002$\u0001\u0005\u0004%Ia\u0012\u0005\u0007\u001f\u0002\u0001\u000b\u0011\u0002%\t\u000bA\u0003A\u0011I)\t\u000bI\u0003A\u0011I*\t\u000bi\u0003A\u0011I.\t\u000b}\u0003A\u0011\t1\t\u000b\r\u0004A\u0011\t3\t\u000b\u0019\u0004A\u0011B4\u0003\u001da\u001b8oU1gKJ+\u0017/^3ti*\u0011\u0001#E\u0001\u0003k&T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0002\u0001'\t\u0001\u0011\u0004\u0005\u0002\u001bC5\t1D\u0003\u0002\u001d;\u0005!\u0001\u000e\u001e;q\u0015\tqr$A\u0004tKJ4H.\u001a;\u000b\u0003\u0001\nqA[1lCJ$\u0018-\u0003\u0002#7\tI\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u/J\f\u0007\u000f]3s\u0003\r\u0011X-\u001d\t\u00035\u0015J!AJ\u000e\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f^\u0001\u000eK\u001a4Wm\u0019;jm\u0016,6/\u001a:\u0011\u0005%\u0012dB\u0001\u00161!\tYc&D\u0001-\u0015\tis#\u0001\u0004=e>|GO\u0010\u0006\u0002_\u0005)1oY1mC&\u0011\u0011GL\u0001\u0007!J,G-\u001a4\n\u0005M\"$AB*ue&twM\u0003\u00022]\u00051A(\u001b8jiz\"2aN\u001d;!\tA\u0004!D\u0001\u0010\u0011\u0015\u00193\u00011\u0001%\u0011\u001593\u00011\u0001)\u0003yqUi\u0016'J\u001d\u0016{\u0016I\u0014#`'&su\tT#`#V{E+R0S\u000b\u001e+\u0005,F\u0001>!\tq4)D\u0001@\u0015\t\u0001\u0015)\u0001\u0005nCR\u001c\u0007.\u001b8h\u0015\t\u0011e&\u0001\u0003vi&d\u0017B\u0001#@\u0005\u0015\u0011VmZ3y\u0003}qUi\u0016'J\u001d\u0016{\u0016I\u0014#`'&su\tT#`#V{E+R0S\u000b\u001e+\u0005\fI\u0001\ra\u0006\u0014\u0018-\\3uKJl\u0015\r]\u000b\u0002\u0011B!\u0011&\u0013\u0015L\u0013\tQEGA\u0002NCB\u00042\u0001T')\u001b\u0005q\u0013B\u0001(/\u0005\u0015\t%O]1z\u00035\u0001\u0018M]1nKR,'/T1qA\u0005iq-\u001a;SK6|G/Z+tKJ$\u0012\u0001K\u0001\u0010O\u0016$\b+\u0019:b[\u0016$XM]'baR\tA\u000b\u0005\u0003V3\"ZU\"\u0001,\u000b\u0005\t;&\"\u0001-\u0002\t)\fg/Y\u0005\u0003\u0015Z\u000b\u0011cZ3u!\u0006\u0014\u0018-\\3uKJt\u0015-\\3t)\u0005a\u0006cA+^Q%\u0011aL\u0016\u0002\f\u000b:,X.\u001a:bi&|g.\u0001\nhKR\u0004\u0016M]1nKR,'OV1mk\u0016\u001cHCA&b\u0011\u0015\u00117\u00021\u0001)\u0003\u0011q\u0017-\\3\u0002\u0019\u001d,G\u000fU1sC6,G/\u001a:\u0015\u0005!*\u0007\"\u00022\r\u0001\u0004A\u0013\u0001C:ue&\u0004\blU*\u0015\u0005!B\u0007\"B5\u000e\u0001\u0004A\u0013aA:ue\u0002"
)
public class XssSafeRequest extends HttpServletRequestWrapper {
   private final String effectiveUser;
   private final Regex NEWLINE_AND_SINGLE_QUOTE_REGEX;
   private final Map parameterMap;

   private Regex NEWLINE_AND_SINGLE_QUOTE_REGEX() {
      return this.NEWLINE_AND_SINGLE_QUOTE_REGEX;
   }

   private Map parameterMap() {
      return this.parameterMap;
   }

   public String getRemoteUser() {
      return this.effectiveUser;
   }

   public java.util.Map getParameterMap() {
      return .MODULE$.MapHasAsJava(this.parameterMap()).asJava();
   }

   public Enumeration getParameterNames() {
      return .MODULE$.IteratorHasAsJava(this.parameterMap().keys().iterator()).asJavaEnumeration();
   }

   public String[] getParameterValues(final String name) {
      return (String[])this.parameterMap().get(name).orNull(scala..less.colon.less..MODULE$.refl());
   }

   public String getParameter(final String name) {
      return (String)this.parameterMap().get(name).flatMap((x$2) -> scala.collection.ArrayOps..MODULE$.headOption$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$2))).orNull(scala..less.colon.less..MODULE$.refl());
   }

   private String stripXSS(final String str) {
      return str != null ? StringEscapeUtils.escapeHtml4(this.NEWLINE_AND_SINGLE_QUOTE_REGEX().replaceAllIn(str, "")) : null;
   }

   public XssSafeRequest(final HttpServletRequest req, final String effectiveUser) {
      super(req);
      this.effectiveUser = effectiveUser;
      this.NEWLINE_AND_SINGLE_QUOTE_REGEX = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(?i)(\\r\\n|\\n|\\r|%0D%0A|%0A|%0D|'|%27)"));
      this.parameterMap = .MODULE$.MapHasAsScala(super.getParameterMap()).asScala().map((x0$1) -> {
         if (x0$1 != null) {
            String name = (String)x0$1._1();
            String[] values = (String[])x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.stripXSS(name)), scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])values), (str) -> this.stripXSS(str), scala.reflect.ClassTag..MODULE$.apply(String.class)));
         } else {
            throw new MatchError(x0$1);
         }
      }).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
