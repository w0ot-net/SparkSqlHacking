package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.collection.mutable.Set;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tUw!B\u001e=\u0011\u0003\te!B\"=\u0011\u0003!\u0005\"B(\u0002\t\u0003\u0001\u0006bB)\u0002\u0005\u0004%)A\u0015\u0005\u0007-\u0006\u0001\u000bQB*\t\u000b]\u000bA1\u0001-\t\r5\fA\u0011\u0001\u001fo\u0011\u00199\u0018\u0001\"\u0001=q\"9\u00111A\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0005\u0003\u0011%\u00111\u0002\u0005\b\u0003;\tA\u0011AA\u0010\u0011\u001d\t\u0019#\u0001C\u0001\u0003KAq!a\t\u0002\t\u0003\t\t\u0004C\u0004\u00028\u0005!)!!\u000f\b\u000f\u0005}\u0012\u0001#\u0001\u0002B\u00199\u0011QI\u0001\t\u0002\u0005\u001d\u0003BB(\u0010\t\u0003\tI\u0005C\u0005\u0002L=\u0011\r\u0011\"\u0001\u0002N!A\u0011QK\b!\u0002\u0013\ty\u0005C\u0005\u0002X=\u0011\r\u0011\"\u0001\u0002Z!A\u0011QL\b!\u0002\u0013\tY\u0006C\u0005\u0002`=\u0011\r\u0011\"\u0001\u0002N!A\u0011\u0011M\b!\u0002\u0013\ty\u0005C\u0004\u00028\u0005!)!a\u0019\t\u000f\u0005-\u0014\u0001\"\u0002\u0002n!9\u0011QO\u0001\u0005\u0002\u0005]\u0004bBA;\u0003\u0011\u0005\u0011\u0011\u0012\u0005\b\u0003#\u000bA\u0011AAJ\u0011%\t9-AI\u0001\n\u0003\tI\rC\u0005\u0002`\u0006\t\n\u0011\"\u0001\u0002b\"I\u0011Q]\u0001\u0012\u0002\u0013\u0005\u0011q\u001d\u0005\n\u0003W\f\u0011\u0013!C\u0001\u0003OD\u0011\"!<\u0002#\u0003%\t!a:\t\u0013\u0005=\u0018!%A\u0005\u0002\u0005\u001d\bbBAy\u0003\u0011\u0005\u00111\u001f\u0005\n\u0005'\t\u0011\u0013!C\u0001\u0003\u0013D\u0011B!\u0006\u0002#\u0003%\t!!9\t\u0013\t]\u0011!%A\u0005\u0002\u0005\u001d\b\"\u0003B\r\u0003E\u0005I\u0011AAt\u0011%\u0011Y\"AI\u0001\n\u0003\t9\u000fC\u0005\u0003\u001e\u0005\t\n\u0011\"\u0001\u0003 !9!1E\u0001\u0005\n\t\u0015\u0002b\u0002B\u001c\u0003\u0011\u0005!\u0011\b\u0005\n\u0005\u0013\n\u0011\u0013!C\u0001\u0003\u0013D\u0011Ba\u0013\u0002#\u0003%\t!!9\t\u0013\t5\u0013!%A\u0005\u0002\u0005\u001d\b\"\u0003B(\u0003E\u0005I\u0011AAt\u0011%\u0011\t&AI\u0001\n\u0003\t9\u000fC\u0005\u0003T\u0005\t\n\u0011\"\u0001\u0003 !9!QK\u0001\u0005\u0002\t]\u0003b\u0002B5\u0003\u0011\u0015!1\u000e\u0005\b\u0005_\nA\u0011\u0001B9\u0011\u001d\u0011Y)\u0001C\u0001\u0005\u001bCqAa#\u0002\t\u0003\u0011\t\nC\u0004\u0003\u0018\u0006!\tA!'\t\u000f\t}\u0015\u0001\"\u0001\u0003\"\"9!\u0011V\u0001\u0005\u0002\t-\u0006b\u0002BY\u0003\u0011\u0005!1\u0017\u0005\b\u0005o\u000bA\u0011\u0001B]\u0003\u001d)F/\u001b7jifT!!\u0010 \u0002\u0007alGNC\u0001@\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AQ\u0001\u000e\u0003q\u0012q!\u0016;jY&$\u0018pE\u0002\u0002\u000b&\u0003\"AR$\u000e\u0003yJ!\u0001\u0013 \u0003\r\u0005s\u0017PU3g!\tQU*D\u0001L\u0015\taE(A\u0004qCJ\u001c\u0018N\\4\n\u00059[%A\u0003+pW\u0016tG+Z:ug\u00061A(\u001b8jiz\"\u0012!Q\u0001\u0003'V+\u0012a\u0015\t\u0003\rRK!!\u0016 \u0003\t\rC\u0017M]\u0001\u0004'V\u0003\u0013AE5na2L7-\u001b;TER{7\u000b\u001e:j]\u001e$\"!\u00173\u0011\u0005i\u000bgBA.`!\taf(D\u0001^\u0015\tq\u0006)\u0001\u0004=e>|GOP\u0005\u0003Az\na\u0001\u0015:fI\u00164\u0017B\u00012d\u0005\u0019\u0019FO]5oO*\u0011\u0001M\u0010\u0005\u0006K\u0016\u0001\rAZ\u0001\u0003g\n\u0004\"a\u001a6\u000f\u0005\u0019C\u0017BA5?\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u001b7\u0003\u001bM#(/\u001b8h\u0005VLG\u000eZ3s\u0015\tIg(\u0001\u0006tER{7\u000b\u001e:j]\u001e$\"!W8\t\u000bA4\u0001\u0019A9\u0002\u0003\u0019\u0004BA\u0012:gi&\u00111O\u0010\u0002\n\rVt7\r^5p]F\u0002\"AR;\n\u0005Yt$\u0001B+oSR\f\u0001#[:Bi>l\u0017I\u001c3O_R$V\r\u001f;\u0015\u0005ed\bC\u0001${\u0013\tYhHA\u0004C_>dW-\u00198\t\u000bu<\u0001\u0019\u0001@\u0002\u0003a\u0004\"AQ@\n\u0007\u0005\u0005AH\u0001\u0003O_\u0012,\u0017\u0001\u0002;sS6$2A`A\u0004\u0011\u0015i\b\u00021\u0001\u007f\u0003a\u0019w.\u001c2j]\u0016\fEM[1dK:$H+\u001a=u\u001d>$Wm\u001d\u000b\u0005\u0003\u001b\tI\u0002E\u0003\u0002\u0010\u0005Ua0\u0004\u0002\u0002\u0012)\u0019\u00111\u0003 \u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0018\u0005E!aA*fc\"9\u00111D\u0005A\u0002\u00055\u0011\u0001C2iS2$'/\u001a8\u0002\u0015Q\u0014\u0018.\u001c)s_B,'\u000f\u0006\u0003\u0002\u000e\u0005\u0005\u0002\"B?\u000b\u0001\u0004q\u0018\u0001B:peR$B!a\n\u0002.A\u0019!)!\u000b\n\u0007\u0005-BH\u0001\u0005NKR\fG)\u0019;b\u0011\u001d\tyc\u0003a\u0001\u0003O\t!!\u001c3\u0015\u0007y\f\u0019\u0004\u0003\u0004\u000261\u0001\rA`\u0001\u0002]\u00061Qm]2ba\u0016$2!WA\u001e\u0011\u0019\ti$\u0004a\u00013\u0006!A/\u001a=u\u0003\u001d)5oY1qKN\u00042!a\u0011\u0010\u001b\u0005\t!aB#tG\u0006\u0004Xm]\n\u0003\u001f\u0015#\"!!\u0011\u0002\u000bA\f\u0017N]:\u0016\u0005\u0005=\u0003#\u0002.\u0002Re\u001b\u0016bAA*G\n\u0019Q*\u00199\u0002\rA\f\u0017N]:!\u0003\u0019)7oY'baV\u0011\u00111\f\t\u00065\u0006E3+W\u0001\bKN\u001cW*\u00199!\u0003!)h.Z:d\u001b\u0006\u0004\u0018!C;oKN\u001cW*\u00199!)\u00151\u0017QMA4\u0011\u0019\tid\u0006a\u00013\"1\u0011\u0011N\fA\u0002\u0019\f\u0011a]\u0001\tk:,7oY1qKR)a-a\u001c\u0002t!1\u0011\u0011\u000f\rA\u0002e\u000b1A]3g\u0011\u0019\tI\u0007\u0007a\u0001M\u0006\t2m\u001c7mK\u000e$h*Y7fgB\f7-Z:\u0015\t\u0005e\u0014Q\u0011\t\u0006\u0003w\n\t)W\u0007\u0003\u0003{RA!a \u0002\u0012\u00059Q.\u001e;bE2,\u0017\u0002BAB\u0003{\u00121aU3u\u0011\u001d\t9)\u0007a\u0001\u0003\u001b\tQA\\8eKN$R\u0001^AF\u0003\u001bCa!!\u000e\u001b\u0001\u0004q\bbBAH5\u0001\u0007\u0011\u0011P\u0001\u0004g\u0016$\u0018!\u0002;p16cEc\u00044\u0002\u0016\u0006]\u0015\u0011UAR\u0003O\u000bY+a,\t\u000bu\\\u0002\u0019\u0001@\t\u0013\u0005e5\u0004%AA\u0002\u0005m\u0015A\u00029tG>\u0004X\rE\u0002C\u0003;K1!a(=\u0005Aq\u0015-\\3ta\u0006\u001cWMQ5oI&tw\rC\u0004f7A\u0005\t\u0019\u00014\t\u0011\u0005\u00156\u0004%AA\u0002e\fQb\u001d;sSB\u001cu.\\7f]R\u001c\b\u0002CAU7A\u0005\t\u0019A=\u0002\u001d\u0011,7m\u001c3f\u000b:$\u0018\u000e^5fg\"A\u0011QV\u000e\u0011\u0002\u0003\u0007\u00110\u0001\nqe\u0016\u001cXM\u001d<f/\"LG/Z:qC\u000e,\u0007\u0002CAY7A\u0005\t\u0019A=\u0002\u00195Lg.[7ju\u0016$\u0016mZ:)\u0017m\t),a/\u0002>\u0006\u0005\u00171\u0019\t\u0004\r\u0006]\u0016bAA]}\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u0012\u0011qX\u0001F!2,\u0017m]3!kN,\u0007\u0005Y:fe&\fG.\u001b>fA\u0002Jgn\u001d;fC\u0012\u0004\u0013M\u001c3!gB,7-\u001b4zA\u0005\u0004\u0003-\\5oS6L'0\u001a+bON\u0004\u0007\u0005]1sC6,G/\u001a:\u0002\u000bMLgnY3\"\u0005\u0005\u0015\u0017A\u0002\u001a/cAr\u0003'A\bu_bkE\n\n3fM\u0006,H\u000e\u001e\u00133+\t\tYM\u000b\u0003\u0002\u001c\u000657FAAh!\u0011\t\t.a7\u000e\u0005\u0005M'\u0002BAk\u0003/\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005eg(\u0001\u0006b]:|G/\u0019;j_:LA!!8\u0002T\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001fQ|\u0007,\u0014'%I\u00164\u0017-\u001e7uIM*\"!a9+\u0007\u0019\fi-A\bu_bkE\n\n3fM\u0006,H\u000e\u001e\u00135+\t\tIOK\u0002z\u0003\u001b\fq\u0002^8Y\u001b2#C-\u001a4bk2$H%N\u0001\u0010i>DV\n\u0014\u0013eK\u001a\fW\u000f\u001c;%m\u0005yAo\u001c-N\u0019\u0012\"WMZ1vYR$s'A\u0005tKJL\u0017\r\\5{KRya-!>\u0002x\u0006e\u00181`A\u007f\u0003\u007f\u0014\t\u0001C\u0003~E\u0001\u0007a\u0010C\u0005\u0002\u001a\n\u0002\n\u00111\u0001\u0002\u001c\"9QM\tI\u0001\u0002\u00041\u0007\u0002CASEA\u0005\t\u0019A=\t\u0011\u0005%&\u0005%AA\u0002eD\u0001\"!,#!\u0003\u0005\r!\u001f\u0005\n\u0003c\u0013\u0003\u0013!a\u0001\u0005\u0007\u0001BA!\u0002\u0003\f9\u0019!Ia\u0002\n\u0007\t%A(\u0001\u0007NS:LW.\u001b>f\u001b>$W-\u0003\u0003\u0003\u000e\t=!!\u0002,bYV,\u0017b\u0001B\t}\tYQI\\;nKJ\fG/[8o\u0003M\u0019XM]5bY&TX\r\n3fM\u0006,H\u000e\u001e\u00133\u0003M\u0019XM]5bY&TX\r\n3fM\u0006,H\u000e\u001e\u00134\u0003M\u0019XM]5bY&TX\r\n3fM\u0006,H\u000e\u001e\u00135\u0003M\u0019XM]5bY&TX\r\n3fM\u0006,H\u000e\u001e\u00136\u0003M\u0019XM]5bY&TX\r\n3fM\u0006,H\u000e\u001e\u00137\u0003M\u0019XM]5bY&TX\r\n3fM\u0006,H\u000e\u001e\u00138+\t\u0011\tC\u000b\u0003\u0003\u0004\u00055\u0017!D:fe&\fG.\u001b>f\u00136\u0004H\u000eF\u0007u\u0005O\u0011YC!\f\u00032\tM\"Q\u0007\u0005\b\u0005SI\u0003\u0019AA\u0007\u0003\tq7\u000fC\u0004\u0002\u001a&\u0002\r!a'\t\r\t=\u0012\u00061\u0001z\u0003\u0019\u0019\b/Y2fI\"1\u0011QU\u0015A\u0002eDq!!-*\u0001\u0004\u0011\u0019\u0001C\u0003fS\u0001\u0007a-A\u0007tKF,XM\\2f)>DV\n\u0014\u000b\u0010i\nm\"Q\bB \u0005\u0003\u0012\u0019E!\u0012\u0003H!9\u00111\u0004\u0016A\u0002\u00055\u0001\"CAMUA\u0005\t\u0019AAN\u0011\u001d)'\u0006%AA\u0002\u0019D\u0001\"!*+!\u0003\u0005\r!\u001f\u0005\t\u0003SS\u0003\u0013!a\u0001s\"A\u0011Q\u0016\u0016\u0011\u0002\u0003\u0007\u0011\u0010C\u0005\u00022*\u0002\n\u00111\u0001\u0003\u0004\u000592/Z9vK:\u001cW\rV8Y\u001b2#C-\u001a4bk2$HEM\u0001\u0018g\u0016\fX/\u001a8dKR{\u0007,\u0014'%I\u00164\u0017-\u001e7uIM\nqc]3rk\u0016t7-\u001a+p16cE\u0005Z3gCVdG\u000f\n\u001b\u0002/M,\u0017/^3oG\u0016$v\u000eW'MI\u0011,g-Y;mi\u0012*\u0014aF:fcV,gnY3U_bkE\n\n3fM\u0006,H\u000e\u001e\u00137\u0003]\u0019X-];f]\u000e,Gk\u001c-N\u0019\u0012\"WMZ1vYR$s'A\u0005ta2LGOT1nKR!!\u0011\fB3!\u00191%1\fB03&\u0019!Q\f \u0003\rQ+\b\u000f\\33!\u00111%\u0011M-\n\u0007\t\rdH\u0001\u0004PaRLwN\u001c\u0005\u0007\u0005O\n\u0004\u0019A-\u0002\t9\fW.Z\u0001\u0007aJ,g-\u001b=\u0015\t\t}#Q\u000e\u0005\u0007\u0005O\u0012\u0004\u0019A-\u0002\u0011!\f7\u000f[\"pI\u0016$BBa\u001d\u0003z\tu$\u0011\u0011BC\u0005\u0013\u00032A\u0012B;\u0013\r\u00119H\u0010\u0002\u0004\u0013:$\bB\u0002B>g\u0001\u0007\u0011,A\u0002qe\u0016DaAa 4\u0001\u0004I\u0016!\u00027bE\u0016d\u0007b\u0002BBg\u0001\u0007!1O\u0001\u000fCR$(/\u001b2ICND7i\u001c3f\u0011\u001d\u00119i\ra\u0001\u0005g\n\u0001b]2qK\"\u000b7\u000f\u001b\u0005\b\u00037\u0019\u0004\u0019AA\u0007\u00031\t\u0007\u000f]3oIF+x\u000e^3e)\rI&q\u0012\u0005\u0007\u0003S\"\u0004\u0019A-\u0015\u000b\u0019\u0014\u0019J!&\t\r\u0005%T\u00071\u0001Z\u0011\u0015)W\u00071\u0001g\u0003M\t\u0007\u000f]3oI\u0016\u001b8-\u00199fIF+x\u000e^3e)\u00151'1\u0014BO\u0011\u0019\tIG\u000ea\u00013\")QM\u000ea\u0001M\u00069q-\u001a;OC6,G#B-\u0003$\n\u0015\u0006BBA5o\u0001\u0007\u0011\fC\u0004\u0003(^\u0002\rAa\u001d\u0002\u000b%tG-\u001a=\u0002'\rDWmY6BiR\u0014\u0018NY;uKZ\u000bG.^3\u0015\u0007e\u0013i\u000b\u0003\u0004\u00030b\u0002\r!W\u0001\u0006m\u0006dW/Z\u0001\u0014a\u0006\u00148/Z!uiJL'-\u001e;f-\u0006dW/\u001a\u000b\u0005\u0003\u001b\u0011)\f\u0003\u0004\u00030f\u0002\r!W\u0001\ra\u0006\u00148/Z\"iCJ\u0014VM\u001a\u000b\n3\nm&Q\u0019Bf\u0005#DqA!0;\u0001\u0004\u0011y,\u0001\u0002dQB!aI!1T\u0013\r\u0011\u0019M\u0010\u0002\n\rVt7\r^5p]BBqAa2;\u0001\u0004\u0011I-\u0001\u0004oKb$8\r\u001b\t\u0005\r\n\u0005G\u000fC\u0004\u0003Nj\u0002\rAa4\u0002#I,\u0007o\u001c:u'ftG/\u0019=FeJ|'\u000f\u0005\u0003Gef#\bb\u0002Bju\u0001\u0007!qZ\u0001\u0015e\u0016\u0004xN\u001d;UeVt7-\u0019;fI\u0016\u0013(o\u001c:"
)
public final class Utility {
   public static String parseCharRef(final Function0 ch, final Function0 nextch, final Function1 reportSyntaxError, final Function1 reportTruncatedError) {
      return Utility$.MODULE$.parseCharRef(ch, nextch, reportSyntaxError, reportTruncatedError);
   }

   public static Seq parseAttributeValue(final String value) {
      return Utility$.MODULE$.parseAttributeValue(value);
   }

   public static String checkAttributeValue(final String value) {
      return Utility$.MODULE$.checkAttributeValue(value);
   }

   public static String getName(final String s, final int index) {
      return Utility$.MODULE$.getName(s, index);
   }

   public static StringBuilder appendEscapedQuoted(final String s, final StringBuilder sb) {
      return Utility$.MODULE$.appendEscapedQuoted(s, sb);
   }

   public static StringBuilder appendQuoted(final String s, final StringBuilder sb) {
      return Utility$.MODULE$.appendQuoted(s, sb);
   }

   public static String appendQuoted(final String s) {
      return Utility$.MODULE$.appendQuoted(s);
   }

   public static int hashCode(final String pre, final String label, final int attribHashCode, final int scpeHash, final Seq children) {
      return Utility$.MODULE$.hashCode(pre, label, attribHashCode, scpeHash, children);
   }

   public static Option prefix(final String name) {
      return Utility$.MODULE$.prefix(name);
   }

   public static Tuple2 splitName(final String name) {
      return Utility$.MODULE$.splitName(name);
   }

   public static Enumeration.Value sequenceToXML$default$7() {
      return Utility$.MODULE$.sequenceToXML$default$7();
   }

   public static boolean sequenceToXML$default$6() {
      return Utility$.MODULE$.sequenceToXML$default$6();
   }

   public static boolean sequenceToXML$default$5() {
      return Utility$.MODULE$.sequenceToXML$default$5();
   }

   public static boolean sequenceToXML$default$4() {
      return Utility$.MODULE$.sequenceToXML$default$4();
   }

   public static StringBuilder sequenceToXML$default$3() {
      return Utility$.MODULE$.sequenceToXML$default$3();
   }

   public static NamespaceBinding sequenceToXML$default$2() {
      return Utility$.MODULE$.sequenceToXML$default$2();
   }

   public static void sequenceToXML(final Seq children, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final Enumeration.Value minimizeTags) {
      Utility$.MODULE$.sequenceToXML(children, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
   }

   public static Enumeration.Value serialize$default$7() {
      return Utility$.MODULE$.serialize$default$7();
   }

   public static boolean serialize$default$6() {
      return Utility$.MODULE$.serialize$default$6();
   }

   public static boolean serialize$default$5() {
      return Utility$.MODULE$.serialize$default$5();
   }

   public static boolean serialize$default$4() {
      return Utility$.MODULE$.serialize$default$4();
   }

   public static StringBuilder serialize$default$3() {
      return Utility$.MODULE$.serialize$default$3();
   }

   public static NamespaceBinding serialize$default$2() {
      return Utility$.MODULE$.serialize$default$2();
   }

   public static StringBuilder serialize(final Node x, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final Enumeration.Value minimizeTags) {
      return Utility$.MODULE$.serialize(x, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
   }

   public static boolean toXML$default$7() {
      return Utility$.MODULE$.toXML$default$7();
   }

   public static boolean toXML$default$6() {
      return Utility$.MODULE$.toXML$default$6();
   }

   public static boolean toXML$default$5() {
      return Utility$.MODULE$.toXML$default$5();
   }

   public static boolean toXML$default$4() {
      return Utility$.MODULE$.toXML$default$4();
   }

   public static StringBuilder toXML$default$3() {
      return Utility$.MODULE$.toXML$default$3();
   }

   public static NamespaceBinding toXML$default$2() {
      return Utility$.MODULE$.toXML$default$2();
   }

   /** @deprecated */
   public static StringBuilder toXML(final Node x, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final boolean minimizeTags) {
      return Utility$.MODULE$.toXML(x, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
   }

   public static void collectNamespaces(final Node n, final Set set) {
      Utility$.MODULE$.collectNamespaces(n, set);
   }

   public static Set collectNamespaces(final Seq nodes) {
      return Utility$.MODULE$.collectNamespaces(nodes);
   }

   public static StringBuilder unescape(final String ref, final StringBuilder s) {
      return Utility$.MODULE$.unescape(ref, s);
   }

   public static StringBuilder escape(final String text, final StringBuilder s) {
      return Utility$.MODULE$.escape(text, s);
   }

   public static String escape(final String text) {
      return Utility$.MODULE$.escape(text);
   }

   public static Node sort(final Node n) {
      return Utility$.MODULE$.sort(n);
   }

   public static MetaData sort(final MetaData md) {
      return Utility$.MODULE$.sort(md);
   }

   public static Seq trimProper(final Node x) {
      return Utility$.MODULE$.trimProper(x);
   }

   public static Node trim(final Node x) {
      return Utility$.MODULE$.trim(x);
   }

   public static String implicitSbToString(final StringBuilder sb) {
      return Utility$.MODULE$.implicitSbToString(sb);
   }

   public static char SU() {
      return Utility$.MODULE$.SU();
   }

   public static boolean checkPubID(final String s) {
      return Utility$.MODULE$.checkPubID(s);
   }

   public static boolean checkSysID(final String s) {
      return Utility$.MODULE$.checkSysID(s);
   }

   public static boolean isValidIANAEncoding(final Seq ianaEncoding) {
      return Utility$.MODULE$.isValidIANAEncoding(ianaEncoding);
   }

   public static boolean isPubIDChar(final char ch) {
      return Utility$.MODULE$.isPubIDChar(ch);
   }

   public static boolean isName(final String s) {
      return Utility$.MODULE$.isName(s);
   }

   public static boolean isNameStart(final char ch) {
      return Utility$.MODULE$.isNameStart(ch);
   }

   public static boolean isNameChar(final char ch) {
      return Utility$.MODULE$.isNameChar(ch);
   }

   public static boolean isAlphaDigit(final char c) {
      return Utility$.MODULE$.isAlphaDigit(c);
   }

   public static boolean isAlpha(final char c) {
      return Utility$.MODULE$.isAlpha(c);
   }

   public static boolean isSpace(final Seq cs) {
      return Utility$.MODULE$.isSpace(cs);
   }

   public static boolean isSpace(final char ch) {
      return Utility$.MODULE$.isSpace(ch);
   }

   public static class Escapes$ {
      public static final Escapes$ MODULE$ = new Escapes$();
      private static final Map pairs;
      private static final Map escMap;
      private static final Map unescMap;

      static {
         pairs = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("lt"), BoxesRunTime.boxToCharacter('<')), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("gt"), BoxesRunTime.boxToCharacter('>')), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("amp"), BoxesRunTime.boxToCharacter('&')), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("quot"), BoxesRunTime.boxToCharacter('"')), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("apos"), BoxesRunTime.boxToCharacter('\''))})));
         escMap = (Map)MODULE$.pairs().$minus("apos").map((x0$1) -> {
            if (x0$1 != null) {
               String s = (String)x0$1._1();
               char c = x0$1._2$mcC$sp();
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(BoxesRunTime.boxToCharacter(c)), (new java.lang.StringBuilder(2)).append("&").append(s).append(";").toString());
            } else {
               throw new MatchError(x0$1);
            }
         });
         unescMap = MODULE$.pairs();
      }

      public Map pairs() {
         return pairs;
      }

      public Map escMap() {
         return escMap;
      }

      public Map unescMap() {
         return unescMap;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
