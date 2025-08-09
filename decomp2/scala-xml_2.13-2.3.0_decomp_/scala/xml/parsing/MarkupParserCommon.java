package scala.xml.parsing;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.CharRef;
import scala.runtime.Nothing;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.sys.package.;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005\tMb\u0001\u0003\u0017.!\u0003\r\t!M\u001a\t\u000bq\u0002A\u0011\u0001 \t\u000b\t\u0003A\u0011C\"\u0005\u000b\u001d\u0003!\u0011\u0001%\u0005\u000b1\u0003!\u0011\u0001%\u0005\u000b5\u0003!\u0011\u0001%\u0005\u000b9\u0003!\u0011\u0001%\u0005\u000b=\u0003!\u0011\u0001%\t\u000bA\u0003a\u0011A)\t\u000b\u0011\u0004a\u0011A3\t\u000b5\u0004A\u0011\u00038\t\u000bM\u0004A\u0011\u0001;\t\u000bU\u0004A\u0011\u0001<\t\u000bU\u0004A\u0011\u0001?\t\u000bu\u0004A\u0011\u0002@\t\u000f\u0005U\u0001\u0001\"\u0001\u0002\u0018!9\u0011Q\u0004\u0001\u0005\u0002\u0005}\u0001bBA\u0011\u0001\u0011%\u00111\u0005\u0005\b\u0003S\u0001A\u0011BA\u0016\u0011\u001d\t\t\u0004\u0001C\u0001\u0003gAq!!\r\u0001\t\u0003\t)\u0005C\u0004\u00022\u0001!\t!a\b\t\u000f\u0005%\u0003A\"\u0001\u0002L!9\u0011q\u0007\u0001\u0007\u0002\u0005M\u0003BBA!\u0001\u0019\u0005a\bC\u0004\u0002V\u00011\t\"a\u0015\t\u000f\u0005]\u0003A\"\u0001\u0002Z!I\u0011\u0011\r\u0001A\u0002\u001b\u0005\u00111\r\u0005\n\u0003K\u0002\u0001\u0019!D\u0001\u0003OBq!!\u001c\u0001\r\u0003\ty\u0007C\u0004\u0002z\u00011\t!a\u001f\t\u000f\u0005e\u0004A\"\u0001\u0002\u0002\"9\u0011q\u0012\u0001\u0007\u0002\u0005E\u0005bBAK\u0001\u0019\u0005\u0011q\u0013\u0005\b\u0003;\u0003A\u0011CAP\u0011\u001d\t\t\f\u0001C\u0001\u0003gCq!!-\u0001\t\u0003\t9\f\u0003\u0004\u0002H\u0002!\tA\u0010\u0005\u0007\u0003\u0013\u0004A\u0011\u0001 \t\r\u0005-\u0007\u0001\"\u0001?\u0011\u001d\ti\r\u0001C\u0001\u0003\u001fDq!!:\u0001\t\u0003\t9\u000fC\u0004\u0003\u000e\u0001!\tBa\u0004\t\u000f\t-\u0002\u0001\"\u0003\u0003.\t\u0011R*\u0019:lkB\u0004\u0016M]:fe\u000e{W.\\8o\u0015\tqs&A\u0004qCJ\u001c\u0018N\\4\u000b\u0005A\n\u0014a\u0001=nY*\t!'A\u0003tG\u0006d\u0017mE\u0002\u0001ia\u0002\"!\u000e\u001c\u000e\u0003EJ!aN\u0019\u0003\r\u0005s\u0017PU3g!\tI$(D\u0001.\u0013\tYTF\u0001\u0006U_.,g\u000eV3tiN\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002\u007fA\u0011Q\u0007Q\u0005\u0003\u0003F\u0012A!\u00168ji\u0006YQO\u001c:fC\u000eD\u0017M\u00197f+\u0005!\u0005CA\u001bF\u0013\t1\u0015GA\u0004O_RD\u0017N\\4\u0003\u0013%s\u0007/\u001e;UsB,\u0017C\u0001#J!\t)$*\u0003\u0002Lc\t\u0019\u0011I\\=\u0003\u0019A{7/\u001b;j_:$\u0016\u0010]3\u0003\u0017\u0015cW-\\3oiRK\b/\u001a\u0002\u000e\u001d\u0006lWm\u001d9bG\u0016$\u0016\u0010]3\u0003\u001d\u0005#HO]5ckR,7\u000fV=qK\u0006aQn[!uiJL'-\u001e;fgR\u0019!\u000bV1\u0011\u0005M;Q\"\u0001\u0001\t\u000bUC\u0001\u0019\u0001,\u0002\t9\fW.\u001a\t\u0003/zs!\u0001\u0017/\u0011\u0005e\u000bT\"\u0001.\u000b\u0005mk\u0014A\u0002\u001fs_>$h(\u0003\u0002^c\u00051\u0001K]3eK\u001aL!a\u00181\u0003\rM#(/\u001b8h\u0015\ti\u0016\u0007C\u0003c\u0011\u0001\u00071-\u0001\u0004qg\u000e|\u0007/\u001a\t\u0003'\u001a\t1\"\\6Qe>\u001c\u0017J\\:ueR!am\u001a6l!\t\u0019V\u0001C\u0003i\u0013\u0001\u0007\u0011.\u0001\u0005q_NLG/[8o!\t\u0019F\u0001C\u0003V\u0013\u0001\u0007a\u000bC\u0003m\u0013\u0001\u0007a+\u0001\u0003uKb$\u0018\u0001\u0002=UC\u001e$\"a\u001c:\u0011\tU\u0002hKU\u0005\u0003cF\u0012a\u0001V;qY\u0016\u0014\u0004\"\u00022\u000b\u0001\u0004\u0019\u0017A\u0003=Qe>\u001c\u0017J\\:ueV\ta-A\by\u0003R$(/\u001b2vi\u00164\u0016\r\\;f)\t1v\u000fC\u0003y\u0019\u0001\u0007\u00110A\u0003f]\u0012\u001c\u0005\u000e\u0005\u00026u&\u001110\r\u0002\u0005\u0007\"\f'\u000fF\u0001W\u00035!\u0018m[3V]RLGn\u00115beR!ak`A\t\u0011\u001d\t\tA\u0004a\u0001\u0003\u0007\t!!\u001b;\u0011\u000b\u0005\u0015\u00111B=\u000f\u0007U\n9!C\u0002\u0002\nE\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u000e\u0005=!\u0001C%uKJ\fGo\u001c:\u000b\u0007\u0005%\u0011\u0007\u0003\u0004\u0002\u00149\u0001\r!_\u0001\u0004K:$\u0017a\u0002=F]\u0012$\u0016m\u001a\u000b\u0004\u007f\u0005e\u0001BBA\u000e\u001f\u0001\u0007a+A\u0005ti\u0006\u0014HOT1nK\u0006)\u0001PT1nKV\ta+A\u0007biR\u0014x,\u001e8fg\u000e\f\u0007/\u001a\u000b\u0004-\u0006\u0015\u0002BBA\u0014#\u0001\u0007a+A\u0001t\u0003]qwN]7bY&TX-\u0011;ue&\u0014W\u000f^3WC2,X\rF\u0002W\u0003[Aa!a\f\u0013\u0001\u00041\u0016AB1uiZ\fG.\u0001\u0005y\u0007\"\f'OU3g)\u00151\u0016QGA \u0011\u001d\t9d\u0005a\u0001\u0003s\t!a\u00195\u0011\tU\nY$_\u0005\u0004\u0003{\t$!\u0003$v]\u000e$\u0018n\u001c81\u0011\u001d\t\te\u0005a\u0001\u0003\u0007\naA\\3yi\u000eD\u0007\u0003B\u001b\u0002<}\"2AVA$\u0011\u001d\t\t\u0001\u0006a\u0001\u0003\u0007\t\u0011\u0002\\8pW\u0006DW-\u00193\u0015\u0005\u00055\u0003#BA\u0003\u0003\u001fJ\u0018\u0002BA)\u0003\u001f\u0011\u0001CQ;gM\u0016\u0014X\rZ%uKJ\fGo\u001c:\u0016\u0003e\f1c\u00195`e\u0016$XO\u001d8j]\u001e|f.\u001a=uG\"\f1!Z8g+\t\tY\u0006E\u00026\u0003;J1!a\u00182\u0005\u001d\u0011un\u001c7fC:\fa\u0001^7qa>\u001cX#A5\u0002\u0015Ql\u0007\u000f]8t?\u0012*\u0017\u000fF\u0002@\u0003SB\u0001\"a\u001b\u001d\u0003\u0003\u0005\r![\u0001\u0004q\u0012\n\u0014\u0001\u0004=IC:$G.Z#se>\u0014H#B \u0002r\u0005U\u0004BBA:;\u0001\u0007\u00110\u0001\u0003uQ\u0006$\bBBA<;\u0001\u0007a+A\u0002ng\u001e\f\u0011C]3q_J$8+\u001f8uCb,%O]8s)\ry\u0014Q\u0010\u0005\u0007\u0003\u007fr\u0002\u0019\u0001,\u0002\u0007M$(\u000fF\u0003@\u0003\u0007\u000bi\tC\u0004\u0002\u0006~\u0001\r!a\"\u0002\u0007A|7\u000fE\u00026\u0003\u0013K1!a#2\u0005\rIe\u000e\u001e\u0005\u0007\u0003\u007fz\u0002\u0019\u0001,\u0002\u001dQ\u0014XO\\2bi\u0016$WI\u001d:peR\u0019A)a%\t\r\u0005]\u0004\u00051\u0001W\u0003))'O]8s\u001d>,e\u000e\u001a\u000b\u0004\t\u0006e\u0005BBANC\u0001\u0007a+A\u0002uC\u001e\fa\"\u001a:s_J\fe\u000e\u001a*fgVdG/\u0006\u0003\u0002\"\u0006\u001dFCBAR\u0003W\u000bi\u000b\u0005\u0003\u0002&\u0006\u001dF\u0002\u0001\u0003\u0007\u0003S\u0013#\u0019\u0001%\u0003\u0003QCa!a\u001e#\u0001\u00041\u0006bBAXE\u0001\u0007\u00111U\u0001\u0002q\u00061\u0001\u0010V8lK:$2aPA[\u0011\u0019\t\u0019h\ta\u0001sR\u0019q(!/\t\u000f\u0005MD\u00051\u0001\u0002<B)\u0011QXAbs6\u0011\u0011q\u0018\u0006\u0004\u0003\u0003\f\u0014AC2pY2,7\r^5p]&!\u0011QYA`\u0005\r\u0019V-]\u0001\u0004q\u0016\u000b\u0016!\u0003=Ta\u0006\u001cWm\u00149u\u0003\u0019A8\u000b]1dK\u0006I!/\u001a;ve:LgnZ\u000b\u0005\u0003#\f9\u000e\u0006\u0003\u0002T\u0006\rH\u0003BAk\u00033\u0004B!!*\u0002X\u00121\u0011\u0011\u0016\u0015C\u0002!Cq!a7)\u0001\u0004\ti.A\u0001g!\u0019)\u0014q\\Ak\u007f%\u0019\u0011\u0011]\u0019\u0003\u0013\u0019+hn\u0019;j_:\f\u0004bBAXQ\u0001\u0007\u0011Q[\u0001\u0007g\u00064\u0018N\\4\u0016\r\u0005%(1AAx)\u0019\tY/!@\u0003\bQ!\u0011Q^Az!\u0011\t)+a<\u0005\r\u0005E\u0018F1\u0001I\u0005\u0005\u0011\u0005\u0002CA{S\u0011\u0005\r!a>\u0002\t\t|G-\u001f\t\u0006k\u0005e\u0018Q^\u0005\u0004\u0003w\f$\u0001\u0003\u001fcs:\fW.\u001a \t\u000f\u0005}\u0018\u00061\u0001\u0003\u0002\u00051q-\u001a;uKJ\u0004B!!*\u0003\u0004\u00111!QA\u0015C\u0002!\u0013\u0011!\u0011\u0005\b\u0005\u0013I\u0003\u0019\u0001B\u0006\u0003\u0019\u0019X\r\u001e;feB1Q'a8\u0003\u0002}\n!\u0002\u001f+bW\u0016,f\u000e^5m+\u0011\u0011\tB!\u0006\u0015\u0011\tM!q\u0003B\u0011\u0005O\u0001B!!*\u0003\u0016\u00111\u0011\u0011\u0016\u0016C\u0002!CqA!\u0007+\u0001\u0004\u0011Y\"A\u0004iC:$G.\u001a:\u0011\u000fU\u0012i\"\u001b,\u0003\u0014%\u0019!qD\u0019\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004b\u0002B\u0012U\u0001\u0007!QE\u0001\u000ba>\u001c\u0018\u000e^5p]\u0016\u0014\b\u0003B\u001b\u0002<%DaA!\u000b+\u0001\u00041\u0016!B;oi&d\u0017\u0001\u00029fK.$B!a\u0017\u00030!1!\u0011G\u0016A\u0002Y\u000b!\u0002\\8pW&twMR8s\u0001"
)
public interface MarkupParserCommon extends TokenTests {
   // $FF: synthetic method
   static Nothing unreachable$(final MarkupParserCommon $this) {
      return $this.unreachable();
   }

   default Nothing unreachable() {
      return this.truncatedError("Cannot be reached.");
   }

   Object mkAttributes(final String name, final Object pscope);

   Object mkProcInstr(final Object position, final String name, final String text);

   // $FF: synthetic method
   static Tuple2 xTag$(final MarkupParserCommon $this, final Object pscope) {
      return $this.xTag(pscope);
   }

   default Tuple2 xTag(final Object pscope) {
      String name = this.xName();
      this.xSpaceOpt();
      return new Tuple2(name, this.mkAttributes(name, pscope));
   }

   // $FF: synthetic method
   static Object xProcInstr$(final MarkupParserCommon $this) {
      return $this.xProcInstr();
   }

   default Object xProcInstr() {
      String n = this.xName();
      this.xSpaceOpt();
      return this.xTakeUntil((x$1, x$2) -> this.mkProcInstr(x$1, n, x$2), () -> this.tmppos(), "?>");
   }

   // $FF: synthetic method
   static String xAttributeValue$(final MarkupParserCommon $this, final char endCh) {
      return $this.xAttributeValue(endCh);
   }

   default String xAttributeValue(final char endCh) {
      StringBuilder buf = new StringBuilder();

      while(this.ch() != endCh && !this.eof()) {
         if (this.ch() == '<') {
            throw this.truncatedError("'<' not allowed in attrib value");
         }

         if (this.ch() == Utility$.MODULE$.SU()) {
            throw this.truncatedError("");
         }

         buf.append(this.ch_returning_nextch());
      }

      this.ch_returning_nextch();
      return buf.toString();
   }

   // $FF: synthetic method
   static String xAttributeValue$(final MarkupParserCommon $this) {
      return $this.xAttributeValue();
   }

   default String xAttributeValue() {
      String str = this.xAttributeValue(this.ch_returning_nextch());
      return this.normalizeAttributeValue(str);
   }

   private String takeUntilChar(final Iterator it, final char end) {
      StringBuilder buf = new StringBuilder();

      while(it.hasNext()) {
         char var5 = BoxesRunTime.unboxToChar(it.next());
         if (end == var5) {
            return buf.toString();
         }

         buf.append(var5);
      }

      throw .MODULE$.error((new java.lang.StringBuilder(11)).append("Expected '").append(end).append("'").toString());
   }

   // $FF: synthetic method
   static void xEndTag$(final MarkupParserCommon $this, final String startName) {
      $this.xEndTag(startName);
   }

   default void xEndTag(final String startName) {
      this.xToken('/');
      String var10000 = this.xName();
      if (var10000 == null) {
         if (startName != null) {
            throw this.errorNoEnd(startName);
         }
      } else if (!var10000.equals(startName)) {
         throw this.errorNoEnd(startName);
      }

      this.xSpaceOpt();
      this.xToken('>');
   }

   // $FF: synthetic method
   static String xName$(final MarkupParserCommon $this) {
      return $this.xName();
   }

   default String xName() {
      if (this.ch() == Utility$.MODULE$.SU()) {
         throw this.truncatedError("");
      } else if (!this.isNameStart(this.ch())) {
         return (String)this.errorAndResult((new java.lang.StringBuilder(46)).append("name expected, but char '").append(this.ch()).append("' cannot start a name").toString(), "");
      } else {
         StringBuilder buf = new StringBuilder();

         do {
            buf.append(this.ch_returning_nextch());
         } while(this.isNameChar(this.ch()));

         if (BoxesRunTime.unboxToChar(buf.last()) == ':') {
            this.reportSyntaxError("name cannot end in ':'");
            return scala.collection.StringOps..MODULE$.dropRight$extension(scala.Predef..MODULE$.augmentString(buf.toString()), 1);
         } else {
            return buf.toString();
         }
      }
   }

   private String attr_unescape(final String s) {
      switch (s == null ? 0 : s.hashCode()) {
         case 3309:
            if ("gt".equals(s)) {
               return ">";
            }
            break;
         case 3464:
            if ("lt".equals(s)) {
               return "<";
            }
            break;
         case 96708:
            if ("amp".equals(s)) {
               return "&";
            }
            break;
         case 3000915:
            if ("apos".equals(s)) {
               return "'";
            }
            break;
         case 3482377:
            if ("quot".equals(s)) {
               return "\"";
            }
            break;
         case 107953788:
            if ("quote".equals(s)) {
               return "\"";
            }
      }

      return (new java.lang.StringBuilder(2)).append("&").append(s).append(";").toString();
   }

   private String normalizeAttributeValue(final String attval) {
      StringBuilder buf = new StringBuilder();

      Object var10001;
      for(BufferedIterator it = scala.collection.StringOps..MODULE$.iterator$extension(scala.Predef..MODULE$.augmentString(attval)).buffered(); it.hasNext(); buf.append(var10001)) {
         char var4 = BoxesRunTime.unboxToChar(it.next());
         switch (var4) {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
               var10001 = " ";
               break;
            case '&':
               if (BoxesRunTime.unboxToChar(it.head()) == '#') {
                  it.next();
                  var10001 = this.xCharRef(it);
               } else {
                  var10001 = this.attr_unescape(this.takeUntilChar(it, ';'));
               }
               break;
            default:
               var10001 = BoxesRunTime.boxToCharacter(var4);
         }
      }

      return buf.toString();
   }

   // $FF: synthetic method
   static String xCharRef$(final MarkupParserCommon $this, final Function0 ch, final Function0 nextch) {
      return $this.xCharRef(ch, nextch);
   }

   default String xCharRef(final Function0 ch, final Function0 nextch) {
      return Utility$.MODULE$.parseCharRef(ch, nextch, (str) -> {
         $anonfun$xCharRef$1(this, str);
         return BoxedUnit.UNIT;
      }, (msg) -> this.truncatedError(msg));
   }

   // $FF: synthetic method
   static String xCharRef$(final MarkupParserCommon $this, final Iterator it) {
      return $this.xCharRef(it);
   }

   default String xCharRef(final Iterator it) {
      CharRef c = CharRef.create(BoxesRunTime.unboxToChar(it.next()));
      return Utility$.MODULE$.parseCharRef((JFunction0.mcC.sp)() -> c.elem, (JFunction0.mcV.sp)() -> c.elem = BoxesRunTime.unboxToChar(it.next()), (str) -> {
         $anonfun$xCharRef$5(this, str);
         return BoxedUnit.UNIT;
      }, (msg) -> this.truncatedError(msg));
   }

   // $FF: synthetic method
   static String xCharRef$(final MarkupParserCommon $this) {
      return $this.xCharRef();
   }

   default String xCharRef() {
      return this.xCharRef((JFunction0.mcC.sp)() -> this.ch(), (JFunction0.mcV.sp)() -> this.nextch());
   }

   BufferedIterator lookahead();

   char ch();

   void nextch();

   char ch_returning_nextch();

   boolean eof();

   Object tmppos();

   void tmppos_$eq(final Object x$1);

   void xHandleError(final char that, final String msg);

   void reportSyntaxError(final String str);

   void reportSyntaxError(final int pos, final String str);

   Nothing truncatedError(final String msg);

   Nothing errorNoEnd(final String tag);

   // $FF: synthetic method
   static Object errorAndResult$(final MarkupParserCommon $this, final String msg, final Object x) {
      return $this.errorAndResult(msg, x);
   }

   default Object errorAndResult(final String msg, final Object x) {
      this.reportSyntaxError(msg);
      return x;
   }

   // $FF: synthetic method
   static void xToken$(final MarkupParserCommon $this, final char that) {
      $this.xToken(that);
   }

   default void xToken(final char that) {
      if (this.ch() == that) {
         this.nextch();
      } else {
         this.xHandleError(that, (new java.lang.StringBuilder(25)).append("'").append(that).append("' expected instead of '").append(this.ch()).append("'").toString());
      }
   }

   // $FF: synthetic method
   static void xToken$(final MarkupParserCommon $this, final Seq that) {
      $this.xToken(that);
   }

   default void xToken(final Seq that) {
      that.foreach((thatx) -> {
         $anonfun$xToken$1(this, BoxesRunTime.unboxToChar(thatx));
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static void xEQ$(final MarkupParserCommon $this) {
      $this.xEQ();
   }

   default void xEQ() {
      this.xSpaceOpt();
      this.xToken('=');
      this.xSpaceOpt();
   }

   // $FF: synthetic method
   static void xSpaceOpt$(final MarkupParserCommon $this) {
      $this.xSpaceOpt();
   }

   default void xSpaceOpt() {
      while(this.isSpace(this.ch()) && !this.eof()) {
         this.nextch();
      }

   }

   // $FF: synthetic method
   static void xSpace$(final MarkupParserCommon $this) {
      $this.xSpace();
   }

   default void xSpace() {
      if (this.isSpace(this.ch())) {
         this.nextch();
         this.xSpaceOpt();
      } else {
         this.xHandleError(this.ch(), "whitespace expected");
      }
   }

   // $FF: synthetic method
   static Object returning$(final MarkupParserCommon $this, final Object x, final Function1 f) {
      return $this.returning(x, f);
   }

   default Object returning(final Object x, final Function1 f) {
      f.apply(x);
      return x;
   }

   // $FF: synthetic method
   static Object saving$(final MarkupParserCommon $this, final Object getter, final Function1 setter, final Function0 body) {
      return $this.saving(getter, setter, body);
   }

   default Object saving(final Object getter, final Function1 setter, final Function0 body) {
      Object var10000;
      try {
         var10000 = body.apply();
      } finally {
         setter.apply(getter);
      }

      return var10000;
   }

   // $FF: synthetic method
   static Object xTakeUntil$(final MarkupParserCommon $this, final Function2 handler, final Function0 positioner, final String until) {
      return $this.xTakeUntil(handler, positioner, until);
   }

   default Object xTakeUntil(final Function2 handler, final Function0 positioner, final String until) {
      StringBuilder sb = new StringBuilder();
      char head = scala.collection.StringOps..MODULE$.head$extension(scala.Predef..MODULE$.augmentString(until));
      String rest = scala.collection.StringOps..MODULE$.tail$extension(scala.Predef..MODULE$.augmentString(until));

      while(!this.eof()) {
         if (this.ch() == head && this.peek(rest)) {
            return handler.apply(positioner.apply(), sb.toString());
         }

         if (this.ch() == Utility$.MODULE$.SU() || this.eof()) {
            throw this.truncatedError((new java.lang.StringBuilder(19)).append("died parsing until ").append(until).toString());
         }

         sb.append(this.ch());
         this.nextch();
      }

      throw this.unreachable();
   }

   private boolean peek(final String lookingFor) {
      boolean var10000;
      if (this.lookahead().take(lookingFor.length()).sameElements(scala.collection.StringOps..MODULE$.iterator$extension(scala.Predef..MODULE$.augmentString(lookingFor)))) {
         scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), lookingFor.length()).foreach$mVc$sp((JFunction1.mcVI.sp)(x$3) -> this.nextch());
         if (true) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   // $FF: synthetic method
   static void $anonfun$xCharRef$1(final MarkupParserCommon $this, final String str) {
      $this.reportSyntaxError(str);
   }

   // $FF: synthetic method
   static void $anonfun$xCharRef$5(final MarkupParserCommon $this, final String str) {
      $this.reportSyntaxError(str);
   }

   // $FF: synthetic method
   static void $anonfun$xToken$1(final MarkupParserCommon $this, final char that) {
      $this.xToken(that);
   }

   static void $init$(final MarkupParserCommon $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
