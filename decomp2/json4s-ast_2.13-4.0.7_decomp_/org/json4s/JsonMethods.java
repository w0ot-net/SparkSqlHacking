package org.json4s;

import org.json4s.prefs.EmptyValueStrategy;
import org.json4s.prefs.EmptyValueStrategy$;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eaa\u0002\u0007\u000e!\u0003\r\tA\u0005\u0005\u00065\u00011\ta\u0007\u0005\bw\u0001\t\n\u0011\"\u0001=\u0011\u001dI\u0005!%A\u0005\u0002)CQ\u0001\u0014\u0001\u0007\u00025Cqa\u0017\u0001\u0012\u0002\u0013\u0005A\fC\u0004_\u0001E\u0005I\u0011A0\t\u000b\u0005\u0004a\u0011\u00012\t\u000fI\u0004\u0011\u0013!C\u0001{!91\u000fAI\u0001\n\u0003!\b\"\u0002<\u0001\r\u00039\bbBA\u0006\u0001\u0019\u0005\u0011Q\u0002\u0002\f\u0015N|g.T3uQ>$7O\u0003\u0002\u000f\u001f\u00051!n]8oiMT\u0011\u0001E\u0001\u0004_J<7\u0001A\u000b\u0003'\u0011\u001c\"\u0001\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0015\u0001\u0018M]:f+\ta\u0012\u0006\u0006\u0003\u001eeQJDC\u0001\u0010#!\ty\u0002%D\u0001\u000e\u0013\t\tSB\u0001\u0004K-\u0006dW/\u001a\u0005\bG\u0005\t\t\u0011q\u0001%\u0003))g/\u001b3f]\u000e,G%\r\t\u0004?\u0015:\u0013B\u0001\u0014\u000e\u0005-\t5OS:p]&s\u0007/\u001e;\u0011\u0005!JC\u0002\u0001\u0003\u0006U\u0005\u0011\ra\u000b\u0002\u0002\u0003F\u0011Af\f\t\u0003+5J!A\f\f\u0003\u000f9{G\u000f[5oOB\u0011Q\u0003M\u0005\u0003cY\u00111!\u00118z\u0011\u0015\u0019\u0014\u00011\u0001(\u0003\tIg\u000eC\u00046\u0003A\u0005\t\u0019\u0001\u001c\u0002-U\u001cXMQ5h\t\u0016\u001c\u0017.\\1m\r>\u0014Hi\\;cY\u0016\u0004\"!F\u001c\n\u0005a2\"a\u0002\"p_2,\u0017M\u001c\u0005\bu\u0005\u0001\n\u00111\u00017\u0003A)8/\u001a\"jO&sGOR8s\u0019>tw-A\bqCJ\u001cX\r\n3fM\u0006,H\u000e\u001e\u00133+\ti\u0004*F\u0001?U\t1thK\u0001A!\t\te)D\u0001C\u0015\t\u0019E)A\u0005v]\u000eDWmY6fI*\u0011QIF\u0001\u000bC:tw\u000e^1uS>t\u0017BA$C\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006U\t\u0011\raK\u0001\u0010a\u0006\u00148/\u001a\u0013eK\u001a\fW\u000f\u001c;%gU\u0011Qh\u0013\u0003\u0006U\r\u0011\raK\u0001\ta\u0006\u00148/Z(qiV\u0011aj\u0016\u000b\u0005\u001fbK&\f\u0006\u0002Q'B\u0019Q#\u0015\u0010\n\u0005I3\"AB(qi&|g\u000eC\u0004U\t\u0005\u0005\t9A+\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002 KY\u0003\"\u0001K,\u0005\u000b)\"!\u0019A\u0016\t\u000bM\"\u0001\u0019\u0001,\t\u000fU\"\u0001\u0013!a\u0001m!9!\b\u0002I\u0001\u0002\u00041\u0014A\u00059beN,w\n\u001d;%I\u00164\u0017-\u001e7uII*\"!P/\u0005\u000b)*!\u0019A\u0016\u0002%A\f'o]3PaR$C-\u001a4bk2$HeM\u000b\u0003{\u0001$QA\u000b\u0004C\u0002-\naA]3oI\u0016\u0014H\u0003B2gQ*\u0004\"\u0001\u000b3\u0005\u000b\u0015\u0004!\u0019A\u0016\u0003\u0003QCQaZ\u0004A\u0002y\tQA^1mk\u0016Dq![\u0004\u0011\u0002\u0003\u0007a'A\nbY^\f\u0017p]#tG\u0006\u0004X-\u00168jG>$W\rC\u0004l\u000fA\u0005\t\u0019\u00017\u0002%\u0015l\u0007\u000f^=WC2,Xm\u0015;sCR,w-\u001f\t\u0003[Bl\u0011A\u001c\u0006\u0003_6\tQ\u0001\u001d:fMNL!!\u001d8\u0003%\u0015k\u0007\u000f^=WC2,Xm\u0015;sCR,w-_\u0001\u0011e\u0016tG-\u001a:%I\u00164\u0017-\u001e7uII\n\u0001C]3oI\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003UT#\u0001\\ \u0002\u000f\r|W\u000e]1diR\u0019\u00010a\u0002\u0011\u0007e\f\tA\u0004\u0002{}B\u00111PF\u0007\u0002y*\u0011Q0E\u0001\u0007yI|w\u000e\u001e \n\u0005}4\u0012A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0004\u0005\u0015!AB*ue&twM\u0003\u0002\u0000-!1\u0011\u0011\u0002\u0006A\u0002\r\f\u0011\u0001Z\u0001\u0007aJ,G\u000f^=\u0015\u0007a\fy\u0001\u0003\u0004\u0002\n-\u0001\ra\u0019"
)
public interface JsonMethods {
   JValue parse(final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$1);

   // $FF: synthetic method
   static boolean parse$default$2$(final JsonMethods $this) {
      return $this.parse$default$2();
   }

   default boolean parse$default$2() {
      return false;
   }

   // $FF: synthetic method
   static boolean parse$default$3$(final JsonMethods $this) {
      return $this.parse$default$3();
   }

   default boolean parse$default$3() {
      return true;
   }

   Option parseOpt(final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$2);

   // $FF: synthetic method
   static boolean parseOpt$default$2$(final JsonMethods $this) {
      return $this.parseOpt$default$2();
   }

   default boolean parseOpt$default$2() {
      return false;
   }

   // $FF: synthetic method
   static boolean parseOpt$default$3$(final JsonMethods $this) {
      return $this.parseOpt$default$3();
   }

   default boolean parseOpt$default$3() {
      return true;
   }

   Object render(final JValue value, final boolean alwaysEscapeUnicode, final EmptyValueStrategy emptyValueStrategy);

   // $FF: synthetic method
   static boolean render$default$2$(final JsonMethods $this) {
      return $this.render$default$2();
   }

   default boolean render$default$2() {
      return false;
   }

   // $FF: synthetic method
   static EmptyValueStrategy render$default$3$(final JsonMethods $this) {
      return $this.render$default$3();
   }

   default EmptyValueStrategy render$default$3() {
      return EmptyValueStrategy$.MODULE$.default();
   }

   String compact(final Object d);

   String pretty(final Object d);
}
