package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.View;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tmfa\u0002\u0012$!\u0003\r\tA\u000b\u0005\u0006\u001d\u0002!\ta\u0014\u0005\u0006'\u0002!\t\u0005\u0016\u0005\u00061\u0002!\t%\u0017\u0005\u0006;\u00021\tA\u0018\u0005\u0006E\u0002!)a\u0019\u0005\u0006E\u0002!)!\u001b\u0005\u0006u\u0002!)a\u001f\u0005\b\u0003s\u0001AQAA\u001e\u0011\u001d\t\t\u0005\u0001C\u0001\u0003\u0007Ba!\u0018\u0001\u0005\u0006\u0005\u001d\u0003bBA*\u0001\u0011\u0015\u0011Q\u000b\u0005\b\u00037\u0002a\u0011AA/\u0011\u001d\t9\u000b\u0001D\u0001\u0003SCq!a/\u0001\r\u0003\ti\fC\u0004\u0002<\u00021\t!!4\t\u000f\u0005U\b\u0001\"\u0001\u0002x\"9\u0011Q \u0001\u0005\u0002\u0005}\bb\u0002B\b\u0001\u0011\u0005!\u0011\u0003\u0005\b\u00057\u0001a\u0011\u0001B\u000f\u0011\u001d\u0011Y\u0003\u0001C\u0001\u0005[AqA!\r\u0001\t\u0003\u0011\u0019\u0004C\u0004\u00038\u0001!\tA!\u000f\t\u000f\tu\u0002\u0001\"\u0001\u0003@!9!1\t\u0001\u0005\u0002\t\u0015\u0003b\u0002B(\u0001\u0011%!\u0011\u000b\u0005\b\u0005+\u0002A\u0011\u0001B,\u0011\u001d\u0011I\u0007\u0001C\u0001\u0005WBqAa\u001c\u0001\t\u0003\u0011\t\b\u0003\u0005\u0003z\u0001\u0001K\u0011\u000bB>\u000f\u001d\u0011Yi\tE\u0001\u0005\u001b3aAI\u0012\t\u0002\t=\u0005b\u0002BP?\u0011\u0005!\u0011\u0015\u0005\n\u0005G{\u0012\u0011!C\u0005\u0005K\u0013aAQ;gM\u0016\u0014(B\u0001\u0013&\u0003\u001diW\u000f^1cY\u0016T!AJ\u0014\u0002\u0015\r|G\u000e\\3di&|gNC\u0001)\u0003\u0015\u00198-\u00197b\u0007\u0001)\"a\u000b\u001c\u0014\u000f\u0001a\u0003g\u0010#H\u0015B\u0011QFL\u0007\u0002O%\u0011qf\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0007E\u0012D'D\u0001$\u0013\t\u00194EA\u0002TKF\u0004\"!\u000e\u001c\r\u0001\u0011)q\u0007\u0001b\u0001q\t\t\u0011)\u0005\u0002:yA\u0011QFO\u0005\u0003w\u001d\u0012qAT8uQ&tw\r\u0005\u0002.{%\u0011ah\n\u0002\u0004\u0003:L\b#B\u0019Ai\t\u001b\u0015BA!$\u0005\u0019\u0019V-](qgB\u0011\u0011\u0007\u0001\t\u0004c\u0001!\u0004cA\u0019Fi%\u0011ai\t\u0002\t\u000fJ|w/\u00192mKB\u0019\u0011\u0007\u0013\u001b\n\u0005%\u001b#AC*ie&t7.\u00192mKB!1\n\u0014\u001bC\u001b\u0005)\u0013BA'&\u0005]IE/\u001a:bE2,g)Y2u_JLH)\u001a4bk2$8/\u0001\u0004%S:LG\u000f\n\u000b\u0002!B\u0011Q&U\u0005\u0003%\u001e\u0012A!\u00168ji\u0006y\u0011\u000e^3sC\ndWMR1di>\u0014\u00180F\u0001V!\rYeKQ\u0005\u0003/\u0016\u0012!bU3r\r\u0006\u001cGo\u001c:z\u0003%Ygn\\<o'&TX-F\u0001[!\ti3,\u0003\u0002]O\t\u0019\u0011J\u001c;\u0002\u000fA\u0014X\r]3oIR\u0011q\fY\u0007\u0002\u0001!)\u0011\r\u0002a\u0001i\u0005!Q\r\\3n\u0003\u0019\t\u0007\u000f]3oIR\u0011q\f\u001a\u0005\u0006C\u0016\u0001\r\u0001\u000e\u0015\u0003\u000b\u0019\u0004\"!L4\n\u0005!<#AB5oY&tW\r\u0006\u0002`U\")1N\u0002a\u0001Y\u0006)Q\r\\3ngB\u0019Q&\u001c\u001b\n\u00059<#A\u0003\u001fsKB,\u0017\r^3e}!2a\u0001]:um^\u0004\"!L9\n\u0005I<#A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017%A;\u0002+U\u001bX\rI1qa\u0016tG-\u00117mA%t7\u000f^3bI\u0006)1/\u001b8dK\u0006\n\u00010\u0001\u00043]E\u001ad\u0006\r\u0015\u0003\r\u0019\f\u0011\"\u00199qK:$\u0017\t\u001c7\u0015\u0005}c\b\"B6\b\u0001\u0004i\bcA&\u007fi%\u0011q0\n\u0002\r\u0013R,'/\u00192mK>s7-\u001a\u0015\by\u0006\r\u0011\u0011BA\u0007!\ri\u0013QA\u0005\u0004\u0003\u000f9#A\u00043faJ,7-\u0019;fI:\u000bW.Z\u0011\u0003\u0003\u0017\t!\u0001_:2\u0013\r\ny!!\n\u0002.\u0005\u001d\u0002\u0003BA\t\u0003?qA!a\u0005\u0002\u001cA\u0019\u0011QC\u0014\u000e\u0005\u0005]!bAA\rS\u00051AH]8pizJ1!!\b(\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011EA\u0012\u0005\u0019\u0019FO]5oO*\u0019\u0011QD\u0014\n\t\u0005\u001d\u0012\u0011F\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u000b\u0007\u0005-r%\u0001\beKB\u0014XmY1uK\u0012t\u0015-\\32\u0013\r\ny#!\r\u00024\u0005-bbA\u0017\u00022%\u0019\u00111F\u00142\u000b\tjs%!\u000e\u0003\u000bM\u001c\u0017\r\\1)\u0005\u001d1\u0017A\u0004\u0013qYV\u001cH%Z9%G>dwN\u001c\u000b\u0004?\u0006u\u0002\"B1\t\u0001\u0004!\u0004F\u0001\u0005g\u0003)\u0001(/\u001a9f]\u0012\fE\u000e\u001c\u000b\u0004?\u0006\u0015\u0003\"B6\n\u0001\u0004iHcA0\u0002J!)1N\u0003a\u0001Y\":!\u0002]:\u0002NY<\u0018EAA(\u0003Y)6/\u001a\u0011qe\u0016\u0004XM\u001c3BY2\u0004\u0013N\\:uK\u0006$\u0007F\u0001\u0006g\u0003M!\u0003\u000f\\;tIAdWo\u001d\u0013fc\u0012\u001aw\u000e\\8o)\ry\u0016q\u000b\u0005\u0006W.\u0001\r! \u0015\u0003\u0017\u0019\fa!\u001b8tKJ$H#\u0002)\u0002`\u0005\r\u0004BBA1\u0019\u0001\u0007!,A\u0002jIbDQ!\u0019\u0007A\u0002QBS\u0001DA4\u0003\u007f\u0002R!LA5\u0003[J1!a\u001b(\u0005\u0019!\bN]8xgB!\u0011qNA=\u001d\u0011\t\t(!\u001e\u000f\t\u0005U\u00111O\u0005\u0002Q%\u0019\u0011qO\u0014\u0002\u000fA\f7m[1hK&!\u00111PA?\u0005eIe\u000eZ3y\u001fV$xJ\u001a\"pk:$7/\u0012=dKB$\u0018n\u001c8\u000b\u0007\u0005]t%M\u0004\u001f\u0003\u001f\t\t)!*2\u0013\r\n\u0019)a#\u0002\u001e\u00065U\u0003BAC\u0003\u000f+\"!a\u0004\u0005\u000f\u0005%\u0015F1\u0001\u0002\u0014\n\tA+\u0003\u0003\u0002\u000e\u0006=\u0015a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013GC\u0002\u0002\u0012\u001e\na\u0001\u001e5s_^\u001c\u0018cA\u001d\u0002\u0016B!\u0011qSAM\u001d\ri\u0013QO\u0005\u0005\u00037\u000biHA\u0005UQJ|w/\u00192mKFJ1%a(\u0002\"\u0006\r\u0016\u0011\u0013\b\u0004[\u0005\u0005\u0016bAAIOE*!%L\u0014\u00026E\u001aa%!\u001c\u0002\u0013%t7/\u001a:u\u00032dG#\u0002)\u0002,\u00065\u0006BBA1\u001b\u0001\u0007!\fC\u0003l\u001b\u0001\u0007Q\u0010K\u0003\u000e\u0003O\n\t,M\u0004\u001f\u0003\u001f\t\u0019,!/2\u0013\r\n\u0019)a#\u00026\u00065\u0015'C\u0012\u0002 \u0006\u0005\u0016qWAIc\u0015\u0011SfJA\u001bc\r1\u0013QN\u0001\u0007e\u0016lwN^3\u0015\u0007Q\ny\f\u0003\u0004\u0002b9\u0001\rA\u0017\u0015\u0006\u001d\u0005\u001d\u00141Y\u0019\b=\u0005=\u0011QYAfc%\u0019\u00131QAF\u0003\u000f\fi)M\u0005$\u0003?\u000b\t+!3\u0002\u0012F*!%L\u0014\u00026E\u001aa%!\u001c\u0015\u000bA\u000by-!5\t\r\u0005\u0005t\u00021\u0001[\u0011\u0019\t\u0019n\u0004a\u00015\u0006)1m\\;oi\"*q\"a\u001a\u0002XF:a$a\u0004\u0002Z\u0006}\u0017'C\u0012\u0002\u0004\u0006-\u00151\\AGc%\u0019\u0013qTAQ\u0003;\f\t*M\u0003#[\u001d\n)$M\u0002'\u0003[BSaDAr\u0003W\u0004R!LA5\u0003K\u0004B!a\u001c\u0002h&!\u0011\u0011^A?\u0005aIE\u000e\\3hC2\f%oZ;nK:$X\t_2faRLwN\\\u0019\b=\u0005=\u0011Q^Azc%\u0019\u00131QAF\u0003_\fi)M\u0005$\u0003?\u000b\t+!=\u0002\u0012F*!%L\u0014\u00026E\u001aa%!:\u0002\u0017M,(\r\u001e:bGR|e.\u001a\u000b\u0004?\u0006e\bBBA~!\u0001\u0007A'A\u0001y\u0003%!(/[7Ti\u0006\u0014H\u000fF\u0002Q\u0005\u0003AaAa\u0001\u0012\u0001\u0004Q\u0016!\u00018)\u0011E\u00018Oa\u0002w\u0005\u0017\t#A!\u0003\u0002/U\u001cX\r\t3s_BLe\u000e\u00157bG\u0016\u0004\u0013N\\:uK\u0006$\u0017E\u0001B\u0007\u0003\u0019\u0011d&M\u001a/i\u00059AO]5n\u000b:$Gc\u0001)\u0003\u0014!1!1\u0001\nA\u0002iC\u0003B\u00059t\u0005/1(1B\u0011\u0003\u00053\tA$^:fA\u0011\u0014x\u000e\u001d*jO\"$\u0018J\u001c)mC\u000e,\u0007%\u001b8ti\u0016\fG-\u0001\u0007qCR\u001c\u0007.\u00138QY\u0006\u001cW\rF\u0004`\u0005?\u0011\u0019Ca\n\t\r\t\u00052\u00031\u0001[\u0003\u00111'o\\7\t\r\t\u00152\u00031\u0001~\u0003\u0015\u0001\u0018\r^2i\u0011\u0019\u0011Ic\u0005a\u00015\u0006A!/\u001a9mC\u000e,G-A\u0006ee>\u0004\u0018J\u001c)mC\u000e,GcA0\u00030!1!1\u0001\u000bA\u0002i\u000b\u0001\u0003\u001a:paJKw\r\u001b;J]Bc\u0017mY3\u0015\u0007}\u0013)\u0004\u0003\u0004\u0003\u0004U\u0001\rAW\u0001\fi\u0006\\W-\u00138QY\u0006\u001cW\rF\u0002`\u0005wAaAa\u0001\u0017\u0001\u0004Q\u0016\u0001\u0005;bW\u0016\u0014\u0016n\u001a5u\u0013:\u0004F.Y2f)\ry&\u0011\t\u0005\u0007\u0005\u00079\u0002\u0019\u0001.\u0002\u0019Md\u0017nY3J]Bc\u0017mY3\u0015\u000b}\u00139Ea\u0013\t\r\t%\u0003\u00041\u0001[\u0003\u0015\u0019H/\u0019:u\u0011\u0019\u0011i\u0005\u0007a\u00015\u0006\u0019QM\u001c3\u0002\u00159|'/\\1mSj,G\rF\u0002[\u0005'BaAa\u0001\u001a\u0001\u0004Q\u0016\u0001\u00053s_B<\u0006.\u001b7f\u0013:\u0004F.Y2f)\ry&\u0011\f\u0005\b\u00057R\u0002\u0019\u0001B/\u0003\u0005\u0001\bCB\u0017\u0003`Q\u0012\u0019'C\u0002\u0003b\u001d\u0012\u0011BR;oGRLwN\\\u0019\u0011\u00075\u0012)'C\u0002\u0003h\u001d\u0012qAQ8pY\u0016\fg.\u0001\tuC.,w\u000b[5mK&s\u0007\u000b\\1dKR\u0019qL!\u001c\t\u000f\tm3\u00041\u0001\u0003^\u0005a\u0001/\u00193U_&s\u0007\u000b\\1dKR)qLa\u001d\u0003x!1!Q\u000f\u000fA\u0002i\u000b1\u0001\\3o\u0011\u0015\tG\u00041\u00015\u00031\u0019HO]5oOB\u0013XMZ5y+\t\u0011i\b\u0005\u0003\u0003\u0000\t%UB\u0001BA\u0015\u0011\u0011\u0019I!\"\u0002\t1\fgn\u001a\u0006\u0003\u0005\u000f\u000bAA[1wC&!\u0011\u0011\u0005BA\u0003\u0019\u0011UO\u001a4feB\u0011\u0011gH\n\u0004?\tE\u0005#\u0002BJ\u00053\u0013ebA&\u0003\u0016&\u0019!qS\u0013\u0002\u0015M+\u0017OR1di>\u0014\u00180\u0003\u0003\u0003\u001c\nu%\u0001\u0003#fY\u0016<\u0017\r^3\u000b\u0007\t]U%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0005\u001b\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa*\u0011\t\t}$\u0011V\u0005\u0005\u0005W\u0013\tI\u0001\u0004PE*,7\r\u001e\u0015\b?\t=&Q\u0017B\\!\ri#\u0011W\u0005\u0004\u0005g;#\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0001f\u0002\u0010\u00030\nU&q\u0017"
)
public interface Buffer extends Seq, Growable, Shrinkable {
   static Builder newBuilder() {
      return Buffer$.MODULE$.newBuilder();
   }

   static scala.collection.SeqOps from(final IterableOnce it) {
      return Buffer$.MODULE$.from(it);
   }

   static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      Buffer$ var10000 = Buffer$.MODULE$;
      return x;
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      SeqFactory.Delegate tabulate_this = Buffer$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$7$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      SeqFactory.Delegate tabulate_this = Buffer$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$5$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      SeqFactory.Delegate tabulate_this = Buffer$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$3$adapted);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      SeqFactory.Delegate tabulate_this = Buffer$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$1$adapted);
   }

   static Object tabulate(final int n, final Function1 f) {
      return Buffer$.MODULE$.from(new View.Tabulate(n, f));
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      SeqFactory.Delegate fill_this = Buffer$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$4);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      SeqFactory.Delegate fill_this = Buffer$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$3);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      SeqFactory.Delegate fill_this = Buffer$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$2);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      SeqFactory.Delegate fill_this = Buffer$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$1);
   }

   static Object fill(final int n, final Function0 elem) {
      return Buffer$.MODULE$.from(new View.Fill(n, elem));
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Buffer$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Buffer$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      return Buffer$.MODULE$.from(new View.Unfold(init, f));
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      return Buffer$.MODULE$.from(new View.Iterate(start, len, f));
   }

   // $FF: synthetic method
   static SeqFactory iterableFactory$(final Buffer $this) {
      return $this.iterableFactory();
   }

   default SeqFactory iterableFactory() {
      return Buffer$.MODULE$;
   }

   // $FF: synthetic method
   static int knownSize$(final Buffer $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return -1;
   }

   Buffer prepend(final Object elem);

   // $FF: synthetic method
   static Buffer append$(final Buffer $this, final Object elem) {
      return $this.append(elem);
   }

   default Buffer append(final Object elem) {
      return (Buffer)this.addOne(elem);
   }

   // $FF: synthetic method
   static Buffer append$(final Buffer $this, final scala.collection.immutable.Seq elems) {
      return $this.append(elems);
   }

   /** @deprecated */
   default Buffer append(final scala.collection.immutable.Seq elems) {
      return (Buffer)this.addAll(elems);
   }

   // $FF: synthetic method
   static Buffer appendAll$(final Buffer $this, final IterableOnce elems) {
      return $this.appendAll(elems);
   }

   default Buffer appendAll(final IterableOnce elems) {
      return (Buffer)this.addAll(elems);
   }

   // $FF: synthetic method
   static Buffer $plus$eq$colon$(final Buffer $this, final Object elem) {
      return $this.$plus$eq$colon(elem);
   }

   default Buffer $plus$eq$colon(final Object elem) {
      return this.prepend(elem);
   }

   // $FF: synthetic method
   static Buffer prependAll$(final Buffer $this, final IterableOnce elems) {
      return $this.prependAll(elems);
   }

   default Buffer prependAll(final IterableOnce elems) {
      this.insertAll(0, elems);
      return this;
   }

   // $FF: synthetic method
   static Buffer prepend$(final Buffer $this, final scala.collection.immutable.Seq elems) {
      return $this.prepend(elems);
   }

   /** @deprecated */
   default Buffer prepend(final scala.collection.immutable.Seq elems) {
      return this.prependAll(elems);
   }

   // $FF: synthetic method
   static Buffer $plus$plus$eq$colon$(final Buffer $this, final IterableOnce elems) {
      return $this.$plus$plus$eq$colon(elems);
   }

   default Buffer $plus$plus$eq$colon(final IterableOnce elems) {
      return this.prependAll(elems);
   }

   void insert(final int idx, final Object elem) throws IndexOutOfBoundsException;

   void insertAll(final int idx, final IterableOnce elems) throws IndexOutOfBoundsException;

   Object remove(final int idx) throws IndexOutOfBoundsException;

   void remove(final int idx, final int count) throws IndexOutOfBoundsException, IllegalArgumentException;

   // $FF: synthetic method
   static Buffer subtractOne$(final Buffer $this, final Object x) {
      return $this.subtractOne(x);
   }

   default Buffer subtractOne(final Object x) {
      int i = this.indexOf(x);
      if (i != -1) {
         this.remove(i);
      }

      return this;
   }

   // $FF: synthetic method
   static void trimStart$(final Buffer $this, final int n) {
      $this.trimStart(n);
   }

   /** @deprecated */
   default void trimStart(final int n) {
      this.dropInPlace(n);
   }

   // $FF: synthetic method
   static void trimEnd$(final Buffer $this, final int n) {
      $this.trimEnd(n);
   }

   /** @deprecated */
   default void trimEnd(final int n) {
      this.dropRightInPlace(n);
   }

   Buffer patchInPlace(final int from, final IterableOnce patch, final int replaced);

   // $FF: synthetic method
   static Buffer dropInPlace$(final Buffer $this, final int n) {
      return $this.dropInPlace(n);
   }

   default Buffer dropInPlace(final int n) {
      this.remove(0, this.normalized(n));
      return this;
   }

   // $FF: synthetic method
   static Buffer dropRightInPlace$(final Buffer $this, final int n) {
      return $this.dropRightInPlace(n);
   }

   default Buffer dropRightInPlace(final int n) {
      int norm = this.normalized(n);
      this.remove(this.length() - norm, norm);
      return this;
   }

   // $FF: synthetic method
   static Buffer takeInPlace$(final Buffer $this, final int n) {
      return $this.takeInPlace(n);
   }

   default Buffer takeInPlace(final int n) {
      int norm = this.normalized(n);
      this.remove(norm, this.length() - norm);
      return this;
   }

   // $FF: synthetic method
   static Buffer takeRightInPlace$(final Buffer $this, final int n) {
      return $this.takeRightInPlace(n);
   }

   default Buffer takeRightInPlace(final int n) {
      this.remove(0, this.length() - this.normalized(n));
      return this;
   }

   // $FF: synthetic method
   static Buffer sliceInPlace$(final Buffer $this, final int start, final int end) {
      return $this.sliceInPlace(start, end);
   }

   default Buffer sliceInPlace(final int start, final int end) {
      return this.takeInPlace(end).dropInPlace(start);
   }

   private int normalized(final int n) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      return Math.min(Math.max(n, max_y), this.length());
   }

   // $FF: synthetic method
   static Buffer dropWhileInPlace$(final Buffer $this, final Function1 p) {
      return $this.dropWhileInPlace(p);
   }

   default Buffer dropWhileInPlace(final Function1 p) {
      int idx = this.indexWhere((x$1) -> BoxesRunTime.boxToBoolean($anonfun$dropWhileInPlace$1(p, x$1)));
      if (idx < 0) {
         this.clear();
         return this;
      } else {
         return this.dropInPlace(idx);
      }
   }

   // $FF: synthetic method
   static Buffer takeWhileInPlace$(final Buffer $this, final Function1 p) {
      return $this.takeWhileInPlace(p);
   }

   default Buffer takeWhileInPlace(final Function1 p) {
      int idx = this.indexWhere((x$2) -> BoxesRunTime.boxToBoolean($anonfun$takeWhileInPlace$1(p, x$2)));
      return idx < 0 ? this : this.takeInPlace(idx);
   }

   // $FF: synthetic method
   static Buffer padToInPlace$(final Buffer $this, final int len, final Object elem) {
      return $this.padToInPlace(len, elem);
   }

   default Buffer padToInPlace(final int len, final Object elem) {
      while(this.length() < len) {
         this.addOne(elem);
      }

      return this;
   }

   // $FF: synthetic method
   static String stringPrefix$(final Buffer $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "Buffer";
   }

   // $FF: synthetic method
   static boolean $anonfun$dropWhileInPlace$1(final Function1 p$1, final Object x$1) {
      return !BoxesRunTime.unboxToBoolean(p$1.apply(x$1));
   }

   // $FF: synthetic method
   static boolean $anonfun$takeWhileInPlace$1(final Function1 p$2, final Object x$2) {
      return !BoxesRunTime.unboxToBoolean(p$2.apply(x$2));
   }

   static void $init$(final Buffer $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
