package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.xml.parsing.XhtmlEntities$;

public final class Xhtml$ {
   public static final Xhtml$ MODULE$ = new Xhtml$();
   private static final List minimizableElements;

   static {
      minimizableElements = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"base", "meta", "link", "hr", "br", "param", "img", "area", "input", "col"})));
   }

   public String toXhtml(final Node node) {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$toXhtml$1(node, sb);
         return BoxedUnit.UNIT;
      });
   }

   public String toXhtml(final NodeSeq nodeSeq) {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$toXhtml$2(nodeSeq, sb);
         return BoxedUnit.UNIT;
      });
   }

   private List minimizableElements() {
      return minimizableElements;
   }

   public void toXhtml(final Node x, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final boolean minimizeTags) {
      if (x instanceof Comment) {
         Comment var10 = (Comment)x;
         if (!stripComments) {
            var10.buildString(sb);
            BoxedUnit var20 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var19 = BoxedUnit.UNIT;
         }
      } else {
         if (x instanceof EntityRef) {
            EntityRef var11 = (EntityRef)x;
            if (decodeEntities) {
               decode$1(var11, sb);
               BoxedUnit var18 = BoxedUnit.UNIT;
               return;
            }
         }

         if (x instanceof SpecialNode) {
            SpecialNode var12 = (SpecialNode)x;
            var12.buildString(sb);
            BoxedUnit var17 = BoxedUnit.UNIT;
         } else if (x instanceof Group) {
            Group var13 = (Group)x;
            var13.nodes().foreach((x$1) -> {
               $anonfun$toXhtml$3(x, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags, x$1);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var16 = BoxedUnit.UNIT;
         } else {
            sb.append('<');
            x.nameToString(sb);
            if (x.attributes() != null) {
               x.attributes().buildString(sb);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            x.scope().buildString(sb, pscope);
            if (this.shortForm$1(minimizeTags, x)) {
               sb.append(" />");
               BoxedUnit var15 = BoxedUnit.UNIT;
            } else {
               sb.append('>');
               this.sequenceToXML(x.child(), x.scope(), sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
               sb.append("</");
               x.nameToString(sb);
               sb.append('>');
               BoxedUnit var14 = BoxedUnit.UNIT;
            }
         }
      }
   }

   public NamespaceBinding toXhtml$default$2() {
      return TopScope$.MODULE$;
   }

   public StringBuilder toXhtml$default$3() {
      return new StringBuilder();
   }

   public boolean toXhtml$default$4() {
      return false;
   }

   public boolean toXhtml$default$5() {
      return false;
   }

   public boolean toXhtml$default$6() {
      return false;
   }

   public boolean toXhtml$default$7() {
      return true;
   }

   public void sequenceToXML(final Seq children, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final boolean minimizeTags) {
      if (children.nonEmpty()) {
         boolean doSpaces = children.forall((x) -> BoxesRunTime.boxToBoolean($anonfun$sequenceToXML$1(x)));
         ((IterableOnceOps)children.take(children.length() - 1)).foreach((c) -> {
            MODULE$.toXhtml(c, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
            return doSpaces ? sb.append(' ') : BoxedUnit.UNIT;
         });
         this.toXhtml((Node)children.last(), pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
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
      return false;
   }

   public boolean sequenceToXML$default$6() {
      return false;
   }

   public boolean sequenceToXML$default$7() {
      return true;
   }

   // $FF: synthetic method
   public static final void $anonfun$toXhtml$1(final Node node$1, final StringBuilder sb) {
      NamespaceBinding x$3 = MODULE$.toXhtml$default$2();
      boolean x$4 = MODULE$.toXhtml$default$4();
      boolean x$5 = MODULE$.toXhtml$default$5();
      boolean x$6 = MODULE$.toXhtml$default$6();
      boolean x$7 = MODULE$.toXhtml$default$7();
      MODULE$.toXhtml(node$1, x$3, sb, x$4, x$5, x$6, x$7);
   }

   // $FF: synthetic method
   public static final void $anonfun$toXhtml$2(final NodeSeq nodeSeq$1, final StringBuilder sb) {
      NamespaceBinding x$3 = MODULE$.sequenceToXML$default$2();
      boolean x$4 = MODULE$.sequenceToXML$default$4();
      boolean x$5 = MODULE$.sequenceToXML$default$5();
      boolean x$6 = MODULE$.sequenceToXML$default$6();
      boolean x$7 = MODULE$.sequenceToXML$default$7();
      MODULE$.sequenceToXML(nodeSeq$1, x$3, sb, x$4, x$5, x$6, x$7);
   }

   private static final StringBuilder decode$1(final EntityRef er, final StringBuilder sb$1) {
      Option var3 = XhtmlEntities$.MODULE$.entMap().get(er.entityName());
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         char chr = BoxesRunTime.unboxToChar(var4.value());
         if (chr >= 128) {
            return sb$1.append(chr);
         }
      }

      return er.buildString(sb$1);
   }

   private final boolean shortForm$1(final boolean minimizeTags$1, final Node x$2) {
      return minimizeTags$1 && (x$2.child() == null || x$2.child().isEmpty()) && this.minimizableElements().contains(x$2.label());
   }

   // $FF: synthetic method
   public static final void $anonfun$toXhtml$3(final Node x$2, final StringBuilder sb$1, final boolean stripComments$1, final boolean decodeEntities$1, final boolean preserveWhitespace$1, final boolean minimizeTags$1, final Node x$1) {
      MODULE$.toXhtml(x$1, x$2.scope(), sb$1, stripComments$1, decodeEntities$1, preserveWhitespace$1, minimizeTags$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$sequenceToXML$1(final Node x) {
      return Utility$.MODULE$.isAtomAndNotText(x);
   }

   private Xhtml$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
