package scala.reflect.api;

import java.io.ObjectStreamException;
import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E4Q!\u0003\u0006\u0001\u001dAA\u0001\u0002\b\u0001\u0003\u0002\u0004%\tA\b\u0005\tG\u0001\u0011\t\u0019!C\u0001I!A!\u0006\u0001B\u0001B\u0003&q\u0004\u0003\u0005,\u0001\t\u0005\r\u0011\"\u0001-\u0011!\u0001\u0006A!a\u0001\n\u0003\t\u0006\u0002\u0003%\u0001\u0005\u0003\u0005\u000b\u0015B\u0017\t\u000bM\u0003A\u0011\u0001+\t\u000bq\u0003A\u0011B/\u0003\u001dM+'/[1mSj,G-\u0012=qe*\u00111\u0002D\u0001\u0004CBL'BA\u0007\u000f\u0003\u001d\u0011XM\u001a7fGRT\u0011aD\u0001\u0006g\u000e\fG.Y\n\u0004\u0001E)\u0002C\u0001\n\u0014\u001b\u0005q\u0011B\u0001\u000b\u000f\u0005\u0019\te.\u001f*fMB\u0011a#\u0007\b\u0003%]I!\u0001\u0007\b\u0002\u000fA\f7m[1hK&\u0011!d\u0007\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u000319\tQ\u0001\u001e:fK\u000e\u001c\u0001!F\u0001 !\t\u0001\u0013%D\u0001\u000b\u0013\t\u0011#BA\u0006Ue\u0016,7I]3bi>\u0014\u0018!\u0003;sK\u0016\u001cw\fJ3r)\t)\u0003\u0006\u0005\u0002\u0013M%\u0011qE\u0004\u0002\u0005+:LG\u000fC\u0004*\u0005\u0005\u0005\t\u0019A\u0010\u0002\u0007a$\u0013'\u0001\u0004ue\u0016,7\rI\u0001\u0004i\u0006<W#A\u00171\u000592\u0005cA\u0018A\t:\u0011\u0001'\u0010\b\u0003cmr!AM\u001d\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c\u001e\u0003\u0019a$o\\8u}%\tq\"\u0003\u0002\u000e\u001d%\u0011!\bD\u0001\beVtG/[7f\u0013\tABH\u0003\u0002;\u0019%\u0011ahP\u0001\tk:Lg/\u001a:tK*\u0011\u0001\u0004P\u0005\u0003\u0003\n\u00131bV3bWRK\b/\u001a+bO&\u00111I\u0003\u0002\t)f\u0004X\rV1hgB\u0011QI\u0012\u0007\u0001\t%9e!!A\u0001\u0002\u000b\u0005\u0011JA\u0002`IU\nA\u0001^1hAE\u0011!*\u0014\t\u0003%-K!\u0001\u0014\b\u0003\u000f9{G\u000f[5oOB\u0011!CT\u0005\u0003\u001f:\u00111!\u00118z\u0003\u001d!\u0018mZ0%KF$\"!\n*\t\u000f%*\u0011\u0011!a\u0001[\u00051A(\u001b8jiz\"2!\u0016,X!\t\u0001\u0003\u0001C\u0003\u001d\u000f\u0001\u0007q\u0004C\u0003,\u000f\u0001\u0007\u0001\f\r\u0002Z7B\u0019q\u0006\u0011.\u0011\u0005\u0015[F!C$X\u0003\u0003\u0005\tQ!\u0001J\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0003EA3\u0001C0k!\r\u0011\u0002MY\u0005\u0003C:\u0011a\u0001\u001e5s_^\u001c\bCA2i\u001b\u0005!'BA3g\u0003\tIwNC\u0001h\u0003\u0011Q\u0017M^1\n\u0005%$'!F(cU\u0016\u001cGo\u0015;sK\u0006lW\t_2faRLwN\\\u0012\u0002E\"\"\u0001\u0001\\8q!\t\u0011R.\u0003\u0002o\u001d\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0003\u0001"
)
public class SerializedExpr implements Serializable {
   private static final long serialVersionUID = 1L;
   private TreeCreator treec;
   private TypeTags.WeakTypeTag tag;

   public TreeCreator treec() {
      return this.treec;
   }

   public void treec_$eq(final TreeCreator x$1) {
      this.treec = x$1;
   }

   public TypeTags.WeakTypeTag tag() {
      return this.tag;
   }

   public void tag_$eq(final TypeTags.WeakTypeTag x$1) {
      this.tag = x$1;
   }

   private Object readResolve() throws ObjectStreamException {
      ClassLoader var10000;
      try {
         var10000 = Thread.currentThread().getContextClassLoader();
      } catch (SecurityException var3) {
         var10000 = null;
      }

      ClassLoader loader = var10000;
      JavaUniverse.JavaMirror m = scala.reflect.runtime.package$.MODULE$.universe().runtimeMirror(loader);
      return ((Exprs)scala.reflect.runtime.package$.MODULE$.universe()).Expr().apply((Mirror)m, this.treec(), this.tag().in((Mirror)m));
   }

   public SerializedExpr(final TreeCreator treec, final TypeTags.WeakTypeTag tag) {
      this.treec = treec;
      this.tag = tag;
      super();
   }
}
