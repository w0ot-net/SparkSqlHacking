package org.apache.spark.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.sql.Dataset;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re\u0001B\r\u001b\u0001\u000eBQ!\u0010\u0001\u0005\u0002yB\u0011\u0002\u0011\u0001A\u0002\u0003\u0007I\u0011A!\t\u0013\u0015\u0003\u0001\u0019!a\u0001\n\u00031\u0005\"\u0003'\u0001\u0001\u0004\u0005\t\u0015)\u0003C\u0011%Q\u0006\u00011AA\u0002\u0013\u00051\fC\u0005q\u0001\u0001\u0007\t\u0019!C\u0001c\"Iq\r\u0001a\u0001\u0002\u0003\u0006K\u0001\u0018\u0005\bo\u0002\t\t\u0011\"\u0001?\u0011\u001dA\b!!A\u0005BeD\u0011\"!\u0002\u0001\u0003\u0003%\t!a\u0002\t\u0013\u0005=\u0001!!A\u0005\u0002\u0005E\u0001\"CA\u000b\u0001\u0005\u0005I\u0011IA\f\u0011%\t)\u0003AA\u0001\n\u0003\t9\u0003C\u0005\u00022\u0001\t\t\u0011\"\u0011\u00024!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0013\u0011\b\u0005\n\u0003w\u0001\u0011\u0011!C!\u0003{A\u0011\"a\u0010\u0001\u0003\u0003%\t%!\u0011\b\u0013\u0005E#$!A\t\u0002\u0005Mc\u0001C\r\u001b\u0003\u0003E\t!!\u0016\t\ru\u001aB\u0011AA7\u0011%\tYdEA\u0001\n\u000b\ni\u0004\u0003\u0005\u0002pM\t\t\u0011\"!?\u0011%\t\thEA\u0001\n\u0003\u000b\u0019\bC\u0005\u0002zM\t\t\u0011\"\u0003\u0002|\taAK]1og\u001a|'/\\#oI*\u00111\u0004H\u0001\u0003[2T!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001'\u0015\u0001AE\u000b\u00182!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fMB\u00111\u0006L\u0007\u00025%\u0011QF\u0007\u0002\b\u001b2+e/\u001a8u!\t)s&\u0003\u00021M\t9\u0001K]8ek\u000e$\bC\u0001\u001a;\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027E\u00051AH]8pizJ\u0011aJ\u0005\u0003s\u0019\nq\u0001]1dW\u0006<W-\u0003\u0002<y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011HJ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003}\u0002\"a\u000b\u0001\u0002\u0017Q\u0014\u0018M\\:g_JlWM]\u000b\u0002\u0005B\u00111fQ\u0005\u0003\tj\u00111\u0002\u0016:b]N4wN]7fe\u0006yAO]1og\u001a|'/\\3s?\u0012*\u0017\u000f\u0006\u0002H\u0015B\u0011Q\u0005S\u0005\u0003\u0013\u001a\u0012A!\u00168ji\"91jAA\u0001\u0002\u0004\u0011\u0015a\u0001=%c\u0005aAO]1og\u001a|'/\\3sA!\u0012AA\u0014\t\u0003\u001fbk\u0011\u0001\u0015\u0006\u0003#J\u000b!\"\u00198o_R\fG/[8o\u0015\t\u0019F+A\u0004kC\u000e\\7o\u001c8\u000b\u0005U3\u0016!\u00034bgR,'\u000f_7m\u0015\u00059\u0016aA2p[&\u0011\u0011\f\u0015\u0002\u000b\u0015N|g.S4o_J,\u0017AB8viB,H/F\u0001]a\tiV\rE\u0002_C\u000el\u0011a\u0018\u0006\u0003Ar\t1a]9m\u0013\t\u0011wLA\u0004ECR\f7/\u001a;\u0011\u0005\u0011,G\u0002\u0001\u0003\nM\u001e\t\t\u0011!A\u0003\u0002%\u00141a\u0018\u00133\u0003\u001dyW\u000f\u001e9vi\u0002B#a\u0002(\u0012\u0005)l\u0007CA\u0013l\u0013\tagEA\u0004O_RD\u0017N\\4\u0011\u0005\u0015r\u0017BA8'\u0005\r\te._\u0001\u000b_V$\b/\u001e;`I\u0015\fHCA$s\u0011\u001dYe!!AA\u0002M\u0004$\u0001\u001e<\u0011\u0007y\u000bW\u000f\u0005\u0002em\u0012IaM]A\u0001\u0002\u0003\u0015\t![\u0001\u0005G>\u0004\u00180A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002uB\u001910!\u0001\u000e\u0003qT!! @\u0002\t1\fgn\u001a\u0006\u0002\u007f\u0006!!.\u0019<b\u0013\r\t\u0019\u0001 \u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005%\u0001cA\u0013\u0002\f%\u0019\u0011Q\u0002\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u00075\f\u0019\u0002\u0003\u0005L\u0017\u0005\u0005\t\u0019AA\u0005\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\r!\u0015\tY\"!\tn\u001b\t\tiBC\u0002\u0002 \u0019\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019#!\b\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003S\ty\u0003E\u0002&\u0003WI1!!\f'\u0005\u001d\u0011un\u001c7fC:DqaS\u0007\u0002\u0002\u0003\u0007Q.\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001>\u00026!A1JDA\u0001\u0002\u0004\tI!\u0001\u0005iCND7i\u001c3f)\t\tI!\u0001\u0005u_N#(/\u001b8h)\u0005Q\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002*\u0005\r\u0003bB&\u0012\u0003\u0003\u0005\r!\u001c\u0015\u0004\u0001\u0005\u001d\u0003\u0003BA%\u0003\u001bj!!a\u0013\u000b\u0005Ec\u0012\u0002BA(\u0003\u0017\u0012\u0001\"\u0012<pYZLgnZ\u0001\r)J\fgn\u001d4pe6,e\u000e\u001a\t\u0003WM\u0019RaEA,\u0003G\u0002R!!\u0017\u0002`}j!!a\u0017\u000b\u0007\u0005uc%A\u0004sk:$\u0018.\\3\n\t\u0005\u0005\u00141\f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0004\u0004\u0003BA3\u0003Wj!!a\u001a\u000b\u0007\u0005%d0\u0001\u0002j_&\u00191(a\u001a\u0015\u0005\u0005M\u0013!B1qa2L\u0018aB;oCB\u0004H.\u001f\u000b\u0005\u0003S\t)\b\u0003\u0005\u0002x]\t\t\u00111\u0001@\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003{\u00022a_A@\u0013\r\t\t\t \u0002\u0007\u001f\nTWm\u0019;"
)
public class TransformEnd implements MLEvent, Product, Serializable {
   @JsonIgnore
   private Transformer transformer;
   @JsonIgnore
   private Dataset output;

   public static boolean unapply(final TransformEnd x$0) {
      return TransformEnd$.MODULE$.unapply(x$0);
   }

   public static TransformEnd apply() {
      return TransformEnd$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return MLEvent.logEvent$(this);
   }

   public Transformer transformer() {
      return this.transformer;
   }

   public void transformer_$eq(final Transformer x$1) {
      this.transformer = x$1;
   }

   public Dataset output() {
      return this.output;
   }

   public void output_$eq(final Dataset x$1) {
      this.output = x$1;
   }

   public TransformEnd copy() {
      return new TransformEnd();
   }

   public String productPrefix() {
      return "TransformEnd";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof TransformEnd;
   }

   public String productElementName(final int x$1) {
      return (String)Statics.ioobe(x$1);
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      return x$1 instanceof TransformEnd && ((TransformEnd)x$1).canEqual(this);
   }

   public TransformEnd() {
      SparkListenerEvent.$init$(this);
      MLEvent.$init$(this);
      Product.$init$(this);
   }
}
