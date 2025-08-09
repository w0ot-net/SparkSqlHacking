package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple5;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.Seq.;
import scala.collection.immutable.List;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.Set;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.CharRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.xml.parsing.TokenTests;

public final class Utility$ implements TokenTests {
   public static final Utility$ MODULE$ = new Utility$();
   private static final char SU;

   static {
      TokenTests.$init$(MODULE$);
      SU = 26;
   }

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

   public final char SU() {
      return SU;
   }

   public String implicitSbToString(final StringBuilder sb) {
      return sb.toString();
   }

   public String sbToString(final Function1 f) {
      StringBuilder sb = new StringBuilder();
      f.apply(sb);
      return sb.toString();
   }

   public boolean isAtomAndNotText(final Node x) {
      return x.isAtom() && !(x instanceof Text);
   }

   public Node trim(final Node x) {
      if (x != null) {
         Option var4 = Elem$.MODULE$.unapplySeq(x);
         if (!var4.isEmpty()) {
            String pre = (String)((Tuple5)var4.get())._1();
            String lab = (String)((Tuple5)var4.get())._2();
            MetaData md = (MetaData)((Tuple5)var4.get())._3();
            NamespaceBinding scp = (NamespaceBinding)((Tuple5)var4.get())._4();
            scala.collection.immutable.Seq child = (scala.collection.immutable.Seq)((Tuple5)var4.get())._5();
            Seq children = (Seq)this.combineAdjacentTextNodes(child).flatMap((xx) -> MODULE$.trimProper(xx));
            return Elem$.MODULE$.apply(pre, lab, md, scp, children.isEmpty(), NodeSeq$.MODULE$.seqToNodeSeq(children));
         }
      }

      throw new MatchError(x);
   }

   private Seq combineAdjacentTextNodes(final Seq children) {
      return (Seq)children.foldRight(.MODULE$.empty(), (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            Node var4 = (Node)var3._1();
            Seq var5 = (Seq)var3._2();
            if (var4 != null) {
               Option var6 = Text$.MODULE$.unapply(var4);
               if (!var6.isEmpty()) {
                  String left = (String)var6.get();
                  if (var5 != null) {
                     Option var8 = scala.package..MODULE$.$plus$colon().unapply(var5);
                     if (!var8.isEmpty()) {
                        Node var9 = (Node)((Tuple2)var8.get())._1();
                        Seq nodes = (Seq)((Tuple2)var8.get())._2();
                        if (var9 != null) {
                           Option var11 = Text$.MODULE$.unapply(var9);
                           if (!var11.isEmpty()) {
                              String right = (String)var11.get();
                              Text var13 = Text$.MODULE$.apply((new java.lang.StringBuilder(0)).append(left).append(right).toString());
                              return (Seq)nodes.$plus$colon(var13);
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var3 != null) {
            Node n = (Node)var3._1();
            Seq nodes = (Seq)var3._2();
            return (Seq)nodes.$plus$colon(n);
         } else {
            throw new MatchError(var3);
         }
      });
   }

   public Seq trimProper(final Node x) {
      if (x != null) {
         Option var4 = Elem$.MODULE$.unapplySeq(x);
         if (!var4.isEmpty()) {
            String pre = (String)((Tuple5)var4.get())._1();
            String lab = (String)((Tuple5)var4.get())._2();
            MetaData md = (MetaData)((Tuple5)var4.get())._3();
            NamespaceBinding scp = (NamespaceBinding)((Tuple5)var4.get())._4();
            scala.collection.immutable.Seq child = (scala.collection.immutable.Seq)((Tuple5)var4.get())._5();
            Seq children = (Seq)this.combineAdjacentTextNodes(child).flatMap((xx) -> MODULE$.trimProper(xx));
            return Elem$.MODULE$.apply(pre, lab, md, scp, children.isEmpty(), NodeSeq$.MODULE$.seqToNodeSeq(children));
         }
      }

      if (x != null) {
         Option var11 = Text$.MODULE$.unapply(x);
         if (!var11.isEmpty()) {
            String s = (String)var11.get();
            return (new TextBuffer()).append(scala.Predef..MODULE$.wrapString(s)).toText();
         }
      }

      return x;
   }

   public MetaData sort(final MetaData md) {
      if (!md.isNull() && !md.next().isNull()) {
         String key = md.key();
         MetaData smaller = this.sort(md.filter((m) -> BoxesRunTime.boxToBoolean($anonfun$sort$1(key, m))));
         MetaData greater = this.sort(md.filter((m) -> BoxesRunTime.boxToBoolean($anonfun$sort$2(key, m))));
         return (MetaData)smaller.foldRight(md.copy(greater), (x, xs) -> x.copy(xs));
      } else {
         return md;
      }
   }

   public Node sort(final Node n) {
      if (n != null) {
         Option var4 = Elem$.MODULE$.unapplySeq(n);
         if (!var4.isEmpty()) {
            String pre = (String)((Tuple5)var4.get())._1();
            String lab = (String)((Tuple5)var4.get())._2();
            MetaData md = (MetaData)((Tuple5)var4.get())._3();
            NamespaceBinding scp = (NamespaceBinding)((Tuple5)var4.get())._4();
            scala.collection.immutable.Seq child = (scala.collection.immutable.Seq)((Tuple5)var4.get())._5();
            Seq children = (Seq)child.map((nx) -> MODULE$.sort(nx));
            return Elem$.MODULE$.apply(pre, lab, this.sort(md), scp, children.isEmpty(), NodeSeq$.MODULE$.seqToNodeSeq(children));
         }
      }

      return n;
   }

   public final String escape(final String text) {
      return this.sbToString((x$1) -> {
         $anonfun$escape$1(text, x$1);
         return BoxedUnit.UNIT;
      });
   }

   public final StringBuilder escape(final String text, final StringBuilder s) {
      return (StringBuilder)scala.collection.StringOps..MODULE$.iterator$extension(scala.Predef..MODULE$.augmentString(text)).foldLeft(s, (sx, c) -> $anonfun$escape$2(sx, BoxesRunTime.unboxToChar(c)));
   }

   public final StringBuilder unescape(final String ref, final StringBuilder s) {
      return (StringBuilder)Utility.Escapes$.MODULE$.unescMap().get(ref).map((x) -> $anonfun$unescape$1(s, BoxesRunTime.unboxToChar(x))).orNull(scala..less.colon.less..MODULE$.refl());
   }

   public Set collectNamespaces(final Seq nodes) {
      return (Set)nodes.foldLeft(new HashSet(), (set, x) -> {
         MODULE$.collectNamespaces(x, set);
         return set;
      });
   }

   public void collectNamespaces(final Node n, final Set set) {
      if (n.doCollectNamespaces()) {
         set.$plus$eq(n.namespace());
         n.attributes().foreach((a) -> a instanceof PrefixedAttribute ? set.$plus$eq(a.getNamespace(n)) : BoxedUnit.UNIT);
         n.child().foreach((i) -> {
            $anonfun$collectNamespaces$3(set, i);
            return BoxedUnit.UNIT;
         });
      }
   }

   /** @deprecated */
   public StringBuilder toXML(final Node x, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final boolean minimizeTags) {
      return this.serialize(x, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags ? MinimizeMode$.MODULE$.Always() : MinimizeMode$.MODULE$.Never());
   }

   public NamespaceBinding toXML$default$2() {
      return TopScope$.MODULE$;
   }

   public StringBuilder toXML$default$3() {
      return new StringBuilder();
   }

   public boolean toXML$default$4() {
      return false;
   }

   public boolean toXML$default$5() {
      return true;
   }

   public boolean toXML$default$6() {
      return false;
   }

   public boolean toXML$default$7() {
      return false;
   }

   public StringBuilder serialize(final Node x, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final Enumeration.Value minimizeTags) {
      this.serializeImpl(new scala.collection.immutable..colon.colon(x, scala.collection.immutable.Nil..MODULE$), pscope, false, stripComments, minimizeTags, sb);
      return sb;
   }

   public NamespaceBinding serialize$default$2() {
      return TopScope$.MODULE$;
   }

   public StringBuilder serialize$default$3() {
      return new StringBuilder();
   }

   public boolean serialize$default$4() {
      return false;
   }

   public boolean serialize$default$5() {
      return true;
   }

   public boolean serialize$default$6() {
      return false;
   }

   public Enumeration.Value serialize$default$7() {
      return MinimizeMode$.MODULE$.Default();
   }

   private void serializeImpl(final Seq ns, final NamespaceBinding pscope, final boolean spaced, final boolean stripComments, final Enumeration.Value minimizeTags, final StringBuilder sb) {
      this.ser$1(new scala.collection.immutable..colon.colon(ns.toList(), scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon(pscope, scala.collection.immutable.Nil..MODULE$), (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapBooleanArray(new boolean[]{spaced})), scala.collection.immutable.Nil..MODULE$, sb, stripComments, minimizeTags);
   }

   public void sequenceToXML(final Seq children, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final Enumeration.Value minimizeTags) {
      if (children.nonEmpty()) {
         boolean spaced = children.forall((x) -> BoxesRunTime.boxToBoolean($anonfun$sequenceToXML$1(x)));
         this.serializeImpl(children, pscope, spaced, stripComments, minimizeTags, sb);
      }
   }

   public NamespaceBinding sequenceToXML$default$2() {
      return TopScope$.MODULE$;
   }

   public StringBuilder sequenceToXML$default$3() {
      return new StringBuilder();
   }

   public boolean sequenceToXML$default$4() {
      return false;
   }

   public boolean sequenceToXML$default$5() {
      return true;
   }

   public boolean sequenceToXML$default$6() {
      return false;
   }

   public Enumeration.Value sequenceToXML$default$7() {
      return MinimizeMode$.MODULE$.Default();
   }

   public Tuple2 splitName(final String name) {
      int colon = name.indexOf(58);
      return colon < 0 ? new Tuple2(scala.None..MODULE$, name) : new Tuple2(new Some(scala.collection.StringOps..MODULE$.take$extension(scala.Predef..MODULE$.augmentString(name), colon)), scala.collection.StringOps..MODULE$.drop$extension(scala.Predef..MODULE$.augmentString(name), colon + 1));
   }

   public final Option prefix(final String name) {
      return (Option)this.splitName(name)._1();
   }

   public int hashCode(final String pre, final String label, final int attribHashCode, final int scpeHash, final Seq children) {
      return scala.util.hashing.MurmurHash3..MODULE$.orderedHash((IterableOnce)((SeqOps)((SeqOps)children.$plus$colon(BoxesRunTime.boxToInteger(scpeHash))).$plus$colon(BoxesRunTime.boxToInteger(attribHashCode))).$plus$colon(label), Statics.anyHash(pre));
   }

   public String appendQuoted(final String s) {
      return this.sbToString((x$2) -> {
         $anonfun$appendQuoted$1(s, x$2);
         return BoxedUnit.UNIT;
      });
   }

   public StringBuilder appendQuoted(final String s, final StringBuilder sb) {
      char ch = (char)(scala.collection.StringOps..MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(s), '"') ? 39 : 34);
      return sb.append((new java.lang.StringBuilder(0)).append(ch).append(s).append(ch).toString());
   }

   public StringBuilder appendEscapedQuoted(final String s, final StringBuilder sb) {
      sb.append('"');
      scala.collection.StringOps..MODULE$.foreach$extension(scala.Predef..MODULE$.augmentString(s), (c) -> $anonfun$appendEscapedQuoted$1(sb, BoxesRunTime.unboxToChar(c)));
      return sb.append('"');
   }

   public String getName(final String s, final int index) {
      if (index >= s.length()) {
         return null;
      } else {
         String xs = scala.collection.StringOps..MODULE$.drop$extension(scala.Predef..MODULE$.augmentString(s), index);
         return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(xs)) && this.isNameStart(scala.collection.StringOps..MODULE$.head$extension(scala.Predef..MODULE$.augmentString(xs))) ? scala.collection.StringOps..MODULE$.takeWhile$extension(scala.Predef..MODULE$.augmentString(xs), (ch) -> BoxesRunTime.boxToBoolean($anonfun$getName$1(BoxesRunTime.unboxToChar(ch)))) : "";
      }
   }

   public String checkAttributeValue(final String value) {
      int i = 0;

      while(i < value.length()) {
         char var3 = value.charAt(i);
         switch (var3) {
            case '&':
               String n = this.getName(value, i + 1);
               if (n == null) {
                  return (new java.lang.StringBuilder(48)).append("malformed entity reference in attribute value [").append(value).append("]").toString();
               }

               i = i + n.length() + 1;
               if (i >= value.length() || value.charAt(i) != ';') {
                  return (new java.lang.StringBuilder(48)).append("malformed entity reference in attribute value [").append(value).append("]").toString();
               }
            default:
               ++i;
               break;
            case '<':
               return "< not allowed in attribute value";
         }
      }

      return null;
   }

   public Seq parseAttributeValue(final String value) {
      StringBuilder sb = new StringBuilder();
      StringBuilder rfb = null;
      NodeBuffer nb = new NodeBuffer();
      Iterator it = scala.collection.StringOps..MODULE$.iterator$extension(scala.Predef..MODULE$.augmentString(value));

      while(it.hasNext()) {
         CharRef c = CharRef.create(BoxesRunTime.unboxToChar(it.next()));
         if (c.elem != '&') {
            sb.append(c.elem);
         } else {
            c.elem = BoxesRunTime.unboxToChar(it.next());
            if (c.elem == '#') {
               c.elem = BoxesRunTime.unboxToChar(it.next());
               String theChar = this.parseCharRef((JFunction0.mcC.sp)() -> c.elem, (JFunction0.mcV.sp)() -> c.elem = BoxesRunTime.unboxToChar(it.next()), (s) -> {
                  throw new RuntimeException(s);
               }, (s) -> {
                  throw new RuntimeException(s);
               });
               sb.append(theChar);
            } else {
               if (rfb == null) {
                  rfb = new StringBuilder();
               }

               rfb.append(c.elem);

               for(c.elem = BoxesRunTime.unboxToChar(it.next()); c.elem != ';'; c.elem = BoxesRunTime.unboxToChar(it.next())) {
                  rfb.append(c.elem);
               }

               String ref = rfb.toString();
               rfb.clear();
               StringBuilder var10 = this.unescape(ref, sb);
               if (var10 == null) {
                  if (sb.nonEmpty()) {
                     nb.$plus$eq(Text$.MODULE$.apply(sb.toString()));
                     sb.clear();
                  }

                  nb.$plus$eq(new EntityRef(ref));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            }
         }
      }

      if (sb.nonEmpty()) {
         Text x = Text$.MODULE$.apply(sb.toString());
         if (nb.isEmpty()) {
            return x;
         }

         nb.$plus$eq(x);
      } else {
         BoxedUnit var12 = BoxedUnit.UNIT;
      }

      return nb;
   }

   public String parseCharRef(final Function0 ch, final Function0 nextch, final Function1 reportSyntaxError, final Function1 reportTruncatedError) {
      boolean var10000;
      label142: {
         if (ch.apply$mcC$sp() == 'x') {
            nextch.apply$mcV$sp();
            if (true) {
               var10000 = true;
               break label142;
            }
         }

         var10000 = false;
      }

      boolean hex = var10000;
      int base = hex ? 16 : 10;

      int i;
      for(i = 0; ch.apply$mcC$sp() != ';' && ch.apply$mcC$sp() != 0; nextch.apply$mcV$sp()) {
         char var11 = ch.apply$mcC$sp();
         if ('0' == var11 ? true : ('1' == var11 ? true : ('2' == var11 ? true : ('3' == var11 ? true : ('4' == var11 ? true : ('5' == var11 ? true : ('6' == var11 ? true : ('7' == var11 ? true : ('8' == var11 ? true : '9' == var11))))))))) {
            i = i * base + scala.runtime.RichChar..MODULE$.asDigit$extension(scala.Predef..MODULE$.charWrapper(ch.apply$mcC$sp()));
            BoxedUnit var12 = BoxedUnit.UNIT;
         } else if ('a' == var11 ? true : ('b' == var11 ? true : ('c' == var11 ? true : ('d' == var11 ? true : ('e' == var11 ? true : ('f' == var11 ? true : ('A' == var11 ? true : ('B' == var11 ? true : ('C' == var11 ? true : ('D' == var11 ? true : ('E' == var11 ? true : 'F' == var11))))))))))) {
            if (!hex) {
               BoxedUnit var13 = (BoxedUnit)reportSyntaxError.apply("hex char not allowed in decimal char ref\nDid you mean to write &#x ?");
            } else {
               i = i * base + scala.runtime.RichChar..MODULE$.asDigit$extension(scala.Predef..MODULE$.charWrapper(ch.apply$mcC$sp()));
               BoxedUnit var14 = BoxedUnit.UNIT;
            }
         } else if (this.SU() == var11) {
            BoxedUnit var15 = (BoxedUnit)reportTruncatedError.apply("");
         } else {
            BoxedUnit var16 = (BoxedUnit)reportSyntaxError.apply((new java.lang.StringBuilder(37)).append("character '").append(ch.apply$mcC$sp()).append("' not allowed in char ref\n").toString());
         }
      }

      return i != 0 ? new String(new int[]{i}, 0, 1) : "";
   }

   // $FF: synthetic method
   public static final boolean $anonfun$sort$1(final String key$1, final MetaData m) {
      return scala.collection.StringOps..MODULE$.$less$extension(scala.Predef..MODULE$.augmentString(m.key()), key$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$sort$2(final String key$1, final MetaData m) {
      return scala.collection.StringOps..MODULE$.$greater$extension(scala.Predef..MODULE$.augmentString(m.key()), key$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$escape$1(final String text$1, final StringBuilder x$1) {
      MODULE$.escape(text$1, x$1);
   }

   // $FF: synthetic method
   public static final StringBuilder $anonfun$escape$2(final StringBuilder s, final char c) {
      Option var3 = Utility.Escapes$.MODULE$.escMap().get(BoxesRunTime.boxToCharacter(c));
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         String str = (String)var4.value();
         return s.$plus$plus$eq(str);
      } else {
         return c < ' ' && !scala.collection.StringOps..MODULE$.contains$extension(scala.Predef..MODULE$.augmentString("\n\r\t"), c) ? s : (StringBuilder)s.$plus$eq(BoxesRunTime.boxToCharacter(c));
      }
   }

   // $FF: synthetic method
   public static final StringBuilder $anonfun$unescape$1(final StringBuilder s$1, final char x) {
      return s$1.append(x);
   }

   // $FF: synthetic method
   public static final void $anonfun$collectNamespaces$3(final Set set$1, final Node i) {
      MODULE$.collectNamespaces(i, set$1);
   }

   private static final void sp$1(final List ns$1, final List spaced$1, final StringBuilder sb$1) {
      if (ns$1.nonEmpty() && BoxesRunTime.unboxToBoolean(spaced$1.head())) {
         sb$1.append(' ');
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$serializeImpl$1(final Node x) {
      return MODULE$.isAtomAndNotText(x);
   }

   private final void ser$1(final List nss, final List pscopes, final List spaced, final List toClose, final StringBuilder sb$1, final boolean stripComments$1, final Enumeration.Value minimizeTags$1) {
      while(true) {
         boolean var11 = false;
         scala.collection.immutable..colon.colon var12 = null;
         if (nss != null) {
            SeqOps var14 = scala.package..MODULE$.List().unapplySeq(nss);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var14) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14), 1) == 0) {
               List var15 = (List)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14), 0);
               if (scala.collection.immutable.Nil..MODULE$.equals(var15)) {
                  BoxedUnit var48 = BoxedUnit.UNIT;
                  var48 = BoxedUnit.UNIT;
                  return;
               }
            }
         }

         if (nss instanceof scala.collection.immutable..colon.colon) {
            var11 = true;
            var12 = (scala.collection.immutable..colon.colon)nss;
            List var16 = (List)var12.head();
            List rests = var12.next$access$1();
            if (scala.collection.immutable.Nil..MODULE$.equals(var16)) {
               if (toClose.head() != null) {
                  sb$1.append("</");
                  ((Node)toClose.head()).nameToString(sb$1);
                  sb$1.append('>');
               } else {
                  BoxedUnit var47 = BoxedUnit.UNIT;
               }

               List var51 = (List)pscopes.tail();
               List var53 = (List)spaced.tail();
               toClose = (List)toClose.tail();
               spaced = var53;
               pscopes = var51;
               nss = rests;
               continue;
            }
         }

         if (var11) {
            List var18 = (List)var12.head();
            List r = var12.next$access$1();
            if (var18 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var20 = (scala.collection.immutable..colon.colon)var18;
               Node n = (Node)var20.head();
               List ns = var20.next$access$1();
               if (n instanceof Comment) {
                  Comment var24 = (Comment)n;
                  if (!stripComments$1) {
                     var24.buildString(sb$1);
                     sp$1(ns, spaced, sb$1);
                  }

                  List var46 = r.$colon$colon(ns);
                  toClose = toClose;
                  spaced = spaced;
                  pscopes = pscopes;
                  nss = var46;
                  continue;
               }

               if (n instanceof SpecialNode) {
                  SpecialNode var26 = (SpecialNode)n;
                  var26.buildString(sb$1);
                  sp$1(ns, spaced, sb$1);
                  List var45 = r.$colon$colon(ns);
                  toClose = toClose;
                  spaced = spaced;
                  pscopes = pscopes;
                  nss = var45;
                  continue;
               }

               if (n instanceof Group) {
                  Group var28 = (Group)n;
                  List var29 = var28.nodes().toList();
                  List var44 = r.$colon$colon(ns).$colon$colon(var29);
                  NamespaceBinding var31 = var28.scope();
                  List var50 = pscopes.$colon$colon(var31);
                  List var52 = spaced.$colon$colon(BoxesRunTime.boxToBoolean(false));
                  toClose = toClose.$colon$colon((Object)null);
                  spaced = var52;
                  pscopes = var50;
                  nss = var44;
                  continue;
               }

               if (n instanceof Elem) {
                  Elem var32 = (Elem)n;
                  sb$1.append('<');
                  var32.nameToString(sb$1);
                  if (var32.attributes() != null) {
                     var32.attributes().buildString(sb$1);
                  } else {
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  label81: {
                     var32.scope().buildString(sb$1, (NamespaceBinding)pscopes.head());
                     if (var32.child().isEmpty()) {
                        label100: {
                           Enumeration.Value var33 = MinimizeMode$.MODULE$.Always();
                           if (minimizeTags$1 == null) {
                              if (var33 == null) {
                                 break label81;
                              }
                           } else if (minimizeTags$1.equals(var33)) {
                              break label81;
                           }

                           Enumeration.Value var34 = MinimizeMode$.MODULE$.Default();
                           if (minimizeTags$1 == null) {
                              if (var34 != null) {
                                 break label100;
                              }
                           } else if (!minimizeTags$1.equals(var34)) {
                              break label100;
                           }

                           if (var32.minimizeEmpty()) {
                              break label81;
                           }
                        }
                     }

                     sb$1.append('>');
                     boolean csp = var32.child().forall((x) -> BoxesRunTime.boxToBoolean($anonfun$serializeImpl$1(x)));
                     List var37 = var32.child().toList();
                     List var42 = r.$colon$colon(ns).$colon$colon(var37);
                     NamespaceBinding var39 = var32.scope();
                     List var10001 = pscopes.$colon$colon(var39);
                     List var10002 = spaced.$colon$colon(BoxesRunTime.boxToBoolean(csp));
                     toClose = toClose.$colon$colon(var32);
                     spaced = var10002;
                     pscopes = var10001;
                     nss = var42;
                     continue;
                  }

                  sb$1.append("/>");
                  sp$1(ns, spaced, sb$1);
                  List var43 = r.$colon$colon(ns);
                  toClose = toClose;
                  spaced = spaced;
                  pscopes = pscopes;
                  nss = var43;
                  continue;
               }

               throw new IllegalArgumentException((new java.lang.StringBuilder(30)).append("Don't know how to serialize a ").append(n.getClass().getName()).toString());
            }
         }

         throw new MatchError(nss);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$sequenceToXML$1(final Node x) {
      return MODULE$.isAtomAndNotText(x);
   }

   // $FF: synthetic method
   public static final void $anonfun$appendQuoted$1(final String s$2, final StringBuilder x$2) {
      MODULE$.appendQuoted(s$2, x$2);
   }

   // $FF: synthetic method
   public static final StringBuilder $anonfun$appendEscapedQuoted$1(final StringBuilder sb$2, final char c) {
      switch (c) {
         case '"':
            sb$2.append('\\');
            return sb$2.append('"');
         default:
            return sb$2.append(c);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getName$1(final char ch) {
      return MODULE$.isNameChar(ch);
   }

   private Utility$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
