package org.apache.spark.sql.catalyst.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.misc.Interval;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!4Q\u0001D\u0007\u0001\u001beA\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006IA\f\u0005\u0006c\u0001!\tA\r\u0005\u0006m\u0001!\te\u000e\u0005\u0006}\u0001!\te\u0010\u0005\u0006\u0017\u0002!\t\u0005\u0014\u0005\u0006!\u0002!\t\u0005\u0014\u0005\u0006#\u0002!\tE\u0015\u0005\u0006+\u0002!\tE\u0016\u0005\u00063\u0002!\t\u0005\u0014\u0005\u00065\u0002!\te\u0017\u0005\u0006I\u0002!\t%\u001a\u0002\u0014+B\u0004XM]\"bg\u0016\u001c\u0005.\u0019:TiJ,\u0017-\u001c\u0006\u0003\u001d=\ta\u0001]1sg\u0016\u0014(B\u0001\t\u0012\u0003!\u0019\u0017\r^1msN$(B\u0001\n\u0014\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sON\u0019\u0001A\u0007\u0012\u0011\u0005m\u0001S\"\u0001\u000f\u000b\u0005uq\u0012\u0001\u00027b]\u001eT\u0011aH\u0001\u0005U\u00064\u0018-\u0003\u0002\"9\t1qJ\u00196fGR\u0004\"a\t\u0016\u000e\u0003\u0011R!!\n\u0014\u0002\u000fI,h\u000e^5nK*\u0011q\u0005K\u0001\u0003mRR!!K\f\u0002\u000b\u0005tG\u000f\u001c:\n\u0005-\"#AC\"iCJ\u001cFO]3b[\u00069qO]1qa\u0016$7\u0001\u0001\t\u0003G=J!\u0001\r\u0013\u0003'\r{G-\u001a)pS:$8\t[1s'R\u0014X-Y7\u0002\rqJg.\u001b;?)\t\u0019T\u0007\u0005\u00025\u00015\tQ\u0002C\u0003-\u0005\u0001\u0007a&A\u0004d_:\u001cX/\\3\u0015\u0003a\u0002\"!\u000f\u001f\u000e\u0003iR\u0011aO\u0001\u0006g\u000e\fG.Y\u0005\u0003{i\u0012A!\u00168ji\u0006iq-\u001a;T_V\u00148-\u001a(b[\u0016$\u0012\u0001\u0011\t\u0003\u0003\"s!A\u0011$\u0011\u0005\rST\"\u0001#\u000b\u0005\u0015k\u0013A\u0002\u001fs_>$h(\u0003\u0002Hu\u00051\u0001K]3eK\u001aL!!\u0013&\u0003\rM#(/\u001b8h\u0015\t9%(A\u0003j]\u0012,\u0007\u0010F\u0001N!\tId*\u0003\u0002Pu\t\u0019\u0011J\u001c;\u0002\t5\f'o[\u0001\be\u0016dW-Y:f)\tA4\u000bC\u0003U\u000f\u0001\u0007Q*\u0001\u0004nCJ\\WM]\u0001\u0005g\u0016,7\u000e\u0006\u00029/\")\u0001\f\u0003a\u0001\u001b\u0006)q\u000f[3sK\u0006!1/\u001b>f\u0003\u001d9W\r\u001e+fqR$\"\u0001\u0011/\t\u000buS\u0001\u0019\u00010\u0002\u0011%tG/\u001a:wC2\u0004\"a\u00182\u000e\u0003\u0001T!!\u0019\u0013\u0002\t5L7oY\u0005\u0003G\u0002\u0014\u0001\"\u00138uKJ4\u0018\r\\\u0001\u0003\u0019\u0006#\"!\u00144\t\u000b\u001d\\\u0001\u0019A'\u0002\u0003%\u0004"
)
public class UpperCaseCharStream implements CharStream {
   private final CodePointCharStream wrapped;

   public void consume() {
      this.wrapped.consume();
   }

   public String getSourceName() {
      return this.wrapped.getSourceName();
   }

   public int index() {
      return this.wrapped.index();
   }

   public int mark() {
      return this.wrapped.mark();
   }

   public void release(final int marker) {
      this.wrapped.release(marker);
   }

   public void seek(final int where) {
      this.wrapped.seek(where);
   }

   public int size() {
      return this.wrapped.size();
   }

   public String getText(final Interval interval) {
      return this.wrapped.getText(interval);
   }

   public int LA(final int i) {
      int la = this.wrapped.LA(i);
      return la != 0 && la != -1 ? Character.toUpperCase(la) : la;
   }

   public UpperCaseCharStream(final CodePointCharStream wrapped) {
      this.wrapped = wrapped;
   }
}
