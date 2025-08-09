package spire.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.macros.whitebox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uq!\u0002\u0006\f\u0011\u0003\u0001b!\u0002\n\f\u0011\u0003\u0019\u0002\"\u0002\u000e\u0002\t\u0003YR\u0001\u0002\u000f\u0002\u0001uAQ!J\u0001\u0005\u0002\u0019BQ\u0001U\u0001\u0005\u0002ECQaW\u0001\u0005\u0002qCQA[\u0001\u0005\u0002-DQ\u0001^\u0001\u0005\u0002UDq!!\u0004\u0002\t\u0003\ty!\u0001\u0004d_6\u0004\u0018\r\u001e\u0006\u0003\u00195\ta!\\1de>\u001c(\"\u0001\b\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011\u0011#A\u0007\u0002\u0017\t11m\\7qCR\u001c\"!\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t\u0001CA\u0004D_:$X\r\u001f;\u0011\u0005y!S\"A\u0010\u000b\u0005\u0001\n\u0013\u0001C<iSR,'m\u001c=\u000b\u00051\u0011#BA\u0012\u0017\u0003\u001d\u0011XM\u001a7fGRL!\u0001H\u0010\u0002\u001b\u0019\u0014Xm\u001d5UKJlg*Y7f+\t9\u0003\u0007\u0006\u0002)[Q\u0011\u0011f\u0011\t\u0003Uur!a\u000b\u001d\u000f\u00051jC\u0002\u0001\u0005\u0006]\u0011\u0001\raL\u0001\u0002GB\u0011A\u0006\r\u0003\u0006c\u0011\u0011\rA\r\u0002\u0002\u0007F\u00111G\u000e\t\u0003+QJ!!\u000e\f\u0003\u000f9{G\u000f[5oOB\u0011qgA\u0007\u0002\u0003%\u0011\u0011HO\u0001\tk:Lg/\u001a:tK&\u0011Ad\u000f\u0006\u0003y\u0005\n\u0001B\u00197bG.\u0014w\u000e_\u0005\u0003}}\u0012\u0001\u0002V3s[:\u000bW.Z\u0005\u0003\u0001\u0006\u0013QAT1nKNT!A\u0011\u0012\u0002\u0007\u0005\u0004\u0018\u000eC\u0003E\t\u0001\u0007Q)A\u0001t!\t1UJ\u0004\u0002H\u0017B\u0011\u0001JF\u0007\u0002\u0013*\u0011!jD\u0001\u0007yI|w\u000e\u001e \n\u000513\u0012A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001\u0014\f\u0002\u0011Q,'/\u001c(b[\u0016,\"AU-\u0015\u0005M;FC\u0001+[!\t)VH\u0004\u0002Wq9\u0011Af\u0016\u0005\u0006]\u0015\u0001\r\u0001\u0017\t\u0003Ye#Q!M\u0003C\u0002IBQ\u0001R\u0003A\u0002\u0015\u000b\u0011\u0002^=qK\u000eCWmY6\u0016\u0005u\u001bGC\u00010b)\ty\u0006\u000e\u0005\u0002aI:\u0011A&\u0019\u0005\u0006]\u0019\u0001\rA\u0019\t\u0003Y\r$Q!\r\u0004C\u0002IJ!!\u001a4\u0003\tQ\u0013X-Z\u0005\u0003O\u0006\u0012q!\u00117jCN,7\u000fC\u0003j\r\u0001\u0007q,A\u0001u\u0003=\u0011Xm]3u\u0019>\u001c\u0017\r\\!uiJ\u001cXC\u00017s)\ti\u0007\u000f\u0006\u0002ogB\u0011q\u000e\u001a\b\u0003YADQAL\u0004A\u0002E\u0004\"\u0001\f:\u0005\u000bE:!\u0019\u0001\u001a\t\u000b%<\u0001\u0019\u00018\u0002\u000fM,Go\u0014:jOV\u0011a/ \u000b\u0003on$R\u0001_A\u0003\u0003\u0013\u0001\"!\u001f@\u000f\u0005iDdB\u0001\u0017|\u0011\u0015q\u0003\u00021\u0001}!\taS\u0010B\u00032\u0011\t\u0007!'C\u0002\u0000\u0003\u0003\u0011\u0001\u0002V=qKR\u0013X-Z\u0005\u0004\u0003\u0007\t%!\u0002+sK\u0016\u001c\bBBA\u0004\u0011\u0001\u0007\u00010\u0001\u0002ui\"1\u0011\u000e\u0003a\u0001\u0003\u0017\u0001\"A\u001f3\u0002\rA\u0014X\rZ3g+\u0011\t\t\"a\u0007\u0015\t\u0005M\u0011q\u0003\t\u0004\u0003+!gb\u0001\u0017\u0002\u0018!1a&\u0003a\u0001\u00033\u00012\u0001LA\u000e\t\u0015\t\u0014B1\u00013\u0001"
)
public final class compat {
   public static Trees.TreeApi predef(final Context c) {
      return compat$.MODULE$.predef(c);
   }

   public static Trees.TypeTreeApi setOrig(final Context c, final Trees.TypeTreeApi tt, final Trees.TreeApi t) {
      return compat$.MODULE$.setOrig(c, tt, t);
   }

   public static Trees.TreeApi resetLocalAttrs(final Context c, final Trees.TreeApi t) {
      return compat$.MODULE$.resetLocalAttrs(c, t);
   }

   public static Trees.TreeApi typeCheck(final Context c, final Trees.TreeApi t) {
      return compat$.MODULE$.typeCheck(c, t);
   }

   public static Names.TermNameApi termName(final Context c, final String s) {
      return compat$.MODULE$.termName(c, s);
   }

   public static Names.TermNameApi freshTermName(final Context c, final String s) {
      return compat$.MODULE$.freshTermName(c, s);
   }
}
