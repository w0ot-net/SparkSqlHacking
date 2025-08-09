package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a!\u0003\b\u0010!\u0003\r\nAFA\u0006\u0011\u0015Y\u0002A\"\u0001\u001d\u0011\u0015\u0001\u0004A\"\u00012\u0011\u00159\u0004A\"\u00019\u0011\u0015q\u0004A\"\u00019\u0011\u0015\u0001\u0005A\"\u0001B\u0011\u0015\u0001\u0005A\"\u0001F\u0011\u0015\u0001\u0005A\"\u0001M\u0011\u0015\u0001\u0005A\"\u0001T\u0011\u0015\u0001\u0005A\"\u0001[\u0011\u0015\u0001\u0005A\"\u0001b\u0011\u0015\u0001\u0005A\"\u0001i\u0011\u0015\u0001\u0005A\"\u0001p\u0011\u0015\u0001\u0005A\"\u0001\u007f\u0005%)\u0005\u0010\u001d:Vi&d7O\u0003\u0002\u0011#\u00051Q.Y2s_NT!AE\n\u0002\u000fI,g\r\\3di*\tA#A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u00019\u0002C\u0001\r\u001a\u001b\u0005\u0019\u0012B\u0001\u000e\u0014\u0005\u0019\te.\u001f*fM\u0006YA.\u001b;fe\u0006dg*\u001e7m+\u0005i\u0002c\u0001\u0010 G5\t\u0001!\u0003\u0002!C\t!Q\t\u001f9s\u0013\t\u0011sBA\u0004BY&\f7/Z:\u0011\u0005a!\u0013BA\u0013\u0014\u0005\u0011qU\u000f\u001c7)\r\u00059#fK\u0017/!\tA\u0002&\u0003\u0002*'\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\nA&A\fvg\u0016\u0004\u0013/^1tSF,x\u000e^3tA%t7\u000f^3bI\u0006)1/\u001b8dK\u0006\nq&\u0001\u00043]E\nd\u0006M\u0001\fY&$XM]1m+:LG/F\u00013!\rqrd\r\t\u00031QJ!!N\n\u0003\tUs\u0017\u000e\u001e\u0015\u0007\u0005\u001dR3&\f\u0018\u0002\u00171LG/\u001a:bYR\u0013X/Z\u000b\u0002sA\u0019ad\b\u001e\u0011\u0005aY\u0014B\u0001\u001f\u0014\u0005\u001d\u0011un\u001c7fC:DcaA\u0014+W5r\u0013\u0001\u00047ji\u0016\u0014\u0018\r\u001c$bYN,\u0007F\u0002\u0003(U-jc&A\u0004mSR,'/\u00197\u0015\u0005e\u0012\u0005\"B\"\u0006\u0001\u0004Q\u0014!\u0001=)\r\u00159#fK\u0017/)\t1%\nE\u0002\u001f?\u001d\u0003\"\u0001\u0007%\n\u0005%\u001b\"\u0001\u0002\"zi\u0016DQa\u0011\u0004A\u0002\u001dCcAB\u0014+W5rCCA'R!\rqrD\u0014\t\u00031=K!\u0001U\n\u0003\u000bMCwN\u001d;\t\u000b\r;\u0001\u0019\u0001()\r\u001d9#fK\u0017/)\t!\u0006\fE\u0002\u001f?U\u0003\"\u0001\u0007,\n\u0005]\u001b\"aA%oi\")1\t\u0003a\u0001+\"2\u0001b\n\u0016,[9\"\"aW0\u0011\u0007yyB\f\u0005\u0002\u0019;&\u0011al\u0005\u0002\u0005\u0019>tw\rC\u0003D\u0013\u0001\u0007A\f\u000b\u0004\nO)ZSF\f\u000b\u0003E\u001a\u00042AH\u0010d!\tAB-\u0003\u0002f'\t)a\t\\8bi\")1I\u0003a\u0001G\"2!b\n\u0016,[9\"\"![7\u0011\u0007yy\"\u000e\u0005\u0002\u0019W&\u0011An\u0005\u0002\u0007\t>,(\r\\3\t\u000b\r[\u0001\u0019\u00016)\r-9#fK\u0017/)\t\u0001H\u0010E\u0002\u001f?E\u0004\"A]=\u000f\u0005M<\bC\u0001;\u0014\u001b\u0005)(B\u0001<\u0016\u0003\u0019a$o\\8u}%\u0011\u0001pE\u0001\u0007!J,G-\u001a4\n\u0005i\\(AB*ue&twM\u0003\u0002y'!)1\t\u0004a\u0001c\"2Ab\n\u0016,[9\"2a`A\u0004!\u0011qr$!\u0001\u0011\u0007a\t\u0019!C\u0002\u0002\u0006M\u0011Aa\u00115be\"11)\u0004a\u0001\u0003\u0003Ac!D\u0014+W5r\u0003\u0003BA\u0007\u0003'i!!a\u0004\u000b\u0007\u0005Eq\"\u0001\u0005cY\u0006\u001c7NY8y\u0013\u0011\t)\"a\u0004\u0003\u000f\r{g\u000e^3yi\u0002"
)
public interface ExprUtils {
   /** @deprecated */
   Exprs.Expr literalNull();

   /** @deprecated */
   Exprs.Expr literalUnit();

   /** @deprecated */
   Exprs.Expr literalTrue();

   /** @deprecated */
   Exprs.Expr literalFalse();

   /** @deprecated */
   Exprs.Expr literal(final boolean x);

   /** @deprecated */
   Exprs.Expr literal(final byte x);

   /** @deprecated */
   Exprs.Expr literal(final short x);

   /** @deprecated */
   Exprs.Expr literal(final int x);

   /** @deprecated */
   Exprs.Expr literal(final long x);

   /** @deprecated */
   Exprs.Expr literal(final float x);

   /** @deprecated */
   Exprs.Expr literal(final double x);

   /** @deprecated */
   Exprs.Expr literal(final String x);

   /** @deprecated */
   Exprs.Expr literal(final char x);
}
