package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.blackbox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rg!C\u000e\u001d!\u0003\r\taIA`\u0011\u0015A\u0003\u0001\"\u0001*\u000b\u0011i\u0003\u0001\u0001\u0018\u0006\tq\u0002\u0001!P\u0003\u0005\u0003\u0002\u0001!)\u0002\u0003G\u0001\u00019U\u0001B%\u0001\u0001)+A\u0001\u0014\u0001\u0001\u001b\u0016!\u0011\u000b\u0001\u0001S\u000b\u00111\u0006\u0001A,\u0006\tm\u0003\u0001\u0001X\u0003\u0005=\u0002\u0001q,\u0002\u0003n\u0001\u0001qW\u0001B9\u0001\u0001ID\u0011\"!\u0002\u0001\u0005\u0004%\t!a\u0002\t\u000f\u0005\u0015\u0001A\"\u0001\u0002\u000e\u00151\u0011q\u0004\u0001\u0001\u0003C)a!!\u000e\u0001\u0001\u0005]\u0002\"CA!\u0001\t\u0007I\u0011AA\"\u0011%\tI\u0005\u0001b\u0001\n\u0003\tY\u0005C\u0004\u0002B\u00011\t!!\u0015\t\u000f\u0005%\u0003A\"\u0001\u0002b!9\u0011Q\u000e\u0001\u0005\u0002\u0005=\u0004bBA?\u0001\u0011\u0005\u0011q\u0010\u0005\b\u0003\u001b\u0003A\u0011AAH\u0011\u001d\tY\n\u0001C\u0001\u0003;Cq!!+\u0001\t\u0003\tYKA\u0004BY&\f7/Z:\u000b\u0005uq\u0012AB7bGJ|7O\u0003\u0002 A\u00059!/\u001a4mK\u000e$(\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001\u0001\n\t\u0003K\u0019j\u0011\u0001I\u0005\u0003O\u0001\u0012a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001+!\t)3&\u0003\u0002-A\t!QK\\5u\u0005\u0019\u0019\u00160\u001c2pYB\u0011qf\u000e\b\u0003aEj\u0011\u0001A\u0005\u0003eM\n\u0001\"\u001e8jm\u0016\u00148/Z\u0005\u0003iU\u0012qaQ8oi\u0016DHO\u0003\u000279\u0005A!\r\\1dW\n|\u00070\u0003\u0002.q%\u0011\u0011H\u000f\u0002\b'fl'm\u001c7t\u0015\tYd$A\u0002ba&\u0014A\u0001V=qKB\u0011qFP\u0005\u0003y}J!\u0001\u0011\u001e\u0003\u000bQK\b/Z:\u0003\t9\u000bW.\u001a\t\u0003_\rK!!\u0011#\n\u0005\u0015S$!\u0002(b[\u0016\u001c(\u0001\u0003+fe6t\u0015-\\3\u0011\u0005=B\u0015B\u0001$E\u0005!!\u0016\u0010]3OC6,\u0007CA\u0018L\u0013\tIEI\u0001\u0003Ue\u0016,\u0007CA\u0018O\u0013\tau*\u0003\u0002Qu\t)AK]3fg\nA\u0001k\\:ji&|g\u000e\u0005\u00020'&\u0011\u0011\u000bV\u0005\u0003+j\u0012\u0011\u0002U8tSRLwN\\:\u0003\u000bM\u001bw\u000e]3\u0011\u0005=B\u0016B\u0001,Z\u0013\tQ&H\u0001\u0004TG>\u0004Xm\u001d\u0002\n\u001b>$\u0017NZ5feN\u0004\"aL/\n\u0005m{%a\u0001*v]B\u0011q\u0006Y\u0005\u0003=\u0006L!A\u0019\u000f\u0003\u0011Us\u0017N^3sg\u0016Dca\u00033hQ*\\\u0007CA\u0013f\u0013\t1\u0007E\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-I\u0001j\u0003a\u001bg&\u001a8dY>\u001c\u0018N\\4Ue\u0016,Wf\u001d;zY\u0016\u0004\u0013\tU%tA\u0005\u0014X\r\t8po\u0002\"W\r\u001d:fG\u0006$X\rZ\u001e!G>t7/\u001e7uAQDW\rI:dC2\fGm\\2!M>\u0014\b%\\8sK\u0002JgNZ8s[\u0006$\u0018n\u001c8\u0002\u000bMLgnY3\"\u00031\faA\r\u00182c9\u0002$aD\"p[BLG.\u0019;j_:,f.\u001b;\u0011\u0005=z\u0017BA7bQ\u0019aAm\u001a5kW\n!Q\t\u001f9s+\t\u0019\u0018\u0010E\u00020i^L!!];\n\u0005YT$!B#yaJ\u001c\bC\u0001=z\u0019\u0001!aA_\u0007\u0005\u0006\u0004Y(!\u0001+\u0012\u0005q|\bCA\u0013~\u0013\tq\bEA\u0004O_RD\u0017N\\4\u0011\u0007\u0015\n\t!C\u0002\u0002\u0004\u0001\u00121!\u00118z\u0003\u0011)\u0005\u0010\u001d:\u0016\u0005\u0005%abA\u0018\u0002\f%\u0019\u0011QA;\u0016\t\u0005=\u0011q\u0003\u000b\u0005\u0003#\ty\u0003\u0006\u0003\u0002\u0014\u0005e\u0001\u0003\u0002\u0019\u000e\u0003+\u00012\u0001_A\f\t\u0015QxB1\u0001|\u0011%\tYbDA\u0001\u0002\b\ti\"\u0001\u0006fm&$WM\\2fIE\u0002B\u0001\r\t\u0002\u0016\tYq+Z1l)f\u0004X\rV1h+\u0011\t\u0019#!\f\u0011\u000b=\n)#a\u000b\n\t\u0005}\u0011qE\u0005\u0004\u0003SQ$\u0001\u0003+za\u0016$\u0016mZ:\u0011\u0007a\fi\u0003B\u0003{!\t\u00071\u0010C\u0004\u00022=\u0001\r!a\r\u0002\tQ\u0014X-\u001a\t\u0003a\u001d\u0011q\u0001V=qKR\u000bw-\u0006\u0003\u0002:\u0005}\u0002#B\u0018\u0002<\u0005u\u0012\u0002BA\u001b\u0003O\u00012\u0001_A \t\u0015Q\u0018C1\u0001|\u0003-9V-Y6UsB,G+Y4\u0016\u0005\u0005\u0015cbA\u0018\u0002H%!\u0011\u0011IA\u0014\u0003\u001d!\u0016\u0010]3UC\u001e,\"!!\u0014\u000f\u0007=\ny%\u0003\u0003\u0002J\u0005\u001dR\u0003BA*\u00033\"B!!\u0016\u0002\\A!\u0001\u0007EA,!\rA\u0018\u0011\f\u0003\u0006uR\u0011\ra\u001f\u0005\b\u0003;\"\u0002\u0019AA0\u0003\r!\b/\u001a\t\u0003a\r)B!a\u0019\u0002jQ!\u0011QMA6!\u0011\u0001\u0014#a\u001a\u0011\u0007a\fI\u0007B\u0003{+\t\u00071\u0010C\u0004\u0002^U\u0001\r!a\u0018\u0002\u0017],\u0017m\u001b+za\u0016$\u0016mZ\u000b\u0005\u0003c\n9\b\u0006\u0003\u0002t\u0005e\u0004\u0003\u0002\u0019\u0011\u0003k\u00022\u0001_A<\t\u0015QhC1\u0001|\u0011\u001d\tYH\u0006a\u0002\u0003g\nQ!\u0019;uC\u001e\fq\u0001^=qKR\u000bw-\u0006\u0003\u0002\u0002\u0006\u001dE\u0003BAB\u0003\u0013\u0003B\u0001M\t\u0002\u0006B\u0019\u00010a\"\u0005\u000bi<\"\u0019A>\t\u000f\u0005-u\u0003q\u0001\u0002\u0004\u0006!A\u000f^1h\u0003)9X-Y6UsB,wJZ\u000b\u0005\u0003#\u000bI\n\u0006\u0003\u0002`\u0005M\u0005bBA>1\u0001\u000f\u0011Q\u0013\t\u0005aA\t9\nE\u0002y\u00033#QA\u001f\rC\u0002m\fa\u0001^=qK>3W\u0003BAP\u0003O#B!a\u0018\u0002\"\"9\u00111R\rA\u0004\u0005\r\u0006\u0003\u0002\u0019\u0012\u0003K\u00032\u0001_AT\t\u0015Q\u0018D1\u0001|\u0003!\u0019\u00180\u001c2pY>3W\u0003BAW\u0003{#B!a,\u00026B\u0019q&!-\n\u0007\u0005M\u0006H\u0001\u0006UsB,7+_7c_2D\u0011\"a.\u001b\u0003\u0003\u0005\u001d!!/\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u00031!\u0005m\u0006c\u0001=\u0002>\u0012)!P\u0007b\u0001wB\u0019\u0011\u0011Y\u001a\u000e\u0003U\u0002"
)
public interface Aliases {
   void scala$reflect$macros$Aliases$_setter_$Expr_$eq(final Exprs.Expr$ x$1);

   void scala$reflect$macros$Aliases$_setter_$WeakTypeTag_$eq(final TypeTags.WeakTypeTag$ x$1);

   void scala$reflect$macros$Aliases$_setter_$TypeTag_$eq(final TypeTags.TypeTag$ x$1);

   Exprs.Expr$ Expr();

   Exprs.Expr Expr(final Trees.TreeApi tree, final TypeTags.WeakTypeTag evidence$1);

   TypeTags.WeakTypeTag$ WeakTypeTag();

   TypeTags.TypeTag$ TypeTag();

   TypeTags.WeakTypeTag WeakTypeTag(final Types.TypeApi tpe);

   TypeTags.TypeTag TypeTag(final Types.TypeApi tpe);

   // $FF: synthetic method
   static TypeTags.WeakTypeTag weakTypeTag$(final Aliases $this, final TypeTags.WeakTypeTag attag) {
      return $this.weakTypeTag(attag);
   }

   default TypeTags.WeakTypeTag weakTypeTag(final TypeTags.WeakTypeTag attag) {
      return attag;
   }

   // $FF: synthetic method
   static TypeTags.TypeTag typeTag$(final Aliases $this, final TypeTags.TypeTag ttag) {
      return $this.typeTag(ttag);
   }

   default TypeTags.TypeTag typeTag(final TypeTags.TypeTag ttag) {
      return ttag;
   }

   // $FF: synthetic method
   static Types.TypeApi weakTypeOf$(final Aliases $this, final TypeTags.WeakTypeTag attag) {
      return $this.weakTypeOf(attag);
   }

   default Types.TypeApi weakTypeOf(final TypeTags.WeakTypeTag attag) {
      return attag.tpe();
   }

   // $FF: synthetic method
   static Types.TypeApi typeOf$(final Aliases $this, final TypeTags.TypeTag ttag) {
      return $this.typeOf(ttag);
   }

   default Types.TypeApi typeOf(final TypeTags.TypeTag ttag) {
      return ttag.tpe();
   }

   // $FF: synthetic method
   static Symbols.TypeSymbolApi symbolOf$(final Aliases $this, final TypeTags.WeakTypeTag evidence$2) {
      return $this.symbolOf(evidence$2);
   }

   default Symbols.TypeSymbolApi symbolOf(final TypeTags.WeakTypeTag evidence$2) {
      return ((Context)this).universe().symbolOf(evidence$2);
   }

   static void $init$(final Aliases $this) {
      $this.scala$reflect$macros$Aliases$_setter_$Expr_$eq(((Context)$this).universe().Expr());
      $this.scala$reflect$macros$Aliases$_setter_$WeakTypeTag_$eq(((Context)$this).universe().WeakTypeTag());
      $this.scala$reflect$macros$Aliases$_setter_$TypeTag_$eq(((Context)$this).universe().TypeTag());
   }
}
