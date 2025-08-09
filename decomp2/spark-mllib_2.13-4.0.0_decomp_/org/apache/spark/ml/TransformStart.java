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
   bytes = "\u0006\u0005\u0005\re\u0001B\r\u001b\u0001\u000eBQ!\u0010\u0001\u0005\u0002yB\u0011\u0002\u0011\u0001A\u0002\u0003\u0007I\u0011A!\t\u0013\u0015\u0003\u0001\u0019!a\u0001\n\u00031\u0005\"\u0003'\u0001\u0001\u0004\u0005\t\u0015)\u0003C\u0011%Q\u0006\u00011AA\u0002\u0013\u00051\fC\u0005q\u0001\u0001\u0007\t\u0019!C\u0001c\"Iq\r\u0001a\u0001\u0002\u0003\u0006K\u0001\u0018\u0005\bo\u0002\t\t\u0011\"\u0001?\u0011\u001dA\b!!A\u0005BeD\u0011\"!\u0002\u0001\u0003\u0003%\t!a\u0002\t\u0013\u0005=\u0001!!A\u0005\u0002\u0005E\u0001\"CA\u000b\u0001\u0005\u0005I\u0011IA\f\u0011%\t)\u0003AA\u0001\n\u0003\t9\u0003C\u0005\u00022\u0001\t\t\u0011\"\u0011\u00024!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0013\u0011\b\u0005\n\u0003w\u0001\u0011\u0011!C!\u0003{A\u0011\"a\u0010\u0001\u0003\u0003%\t%!\u0011\b\u0013\u0005E#$!A\t\u0002\u0005Mc\u0001C\r\u001b\u0003\u0003E\t!!\u0016\t\ru\u001aB\u0011AA7\u0011%\tYdEA\u0001\n\u000b\ni\u0004\u0003\u0005\u0002pM\t\t\u0011\"!?\u0011%\t\thEA\u0001\n\u0003\u000b\u0019\bC\u0005\u0002zM\t\t\u0011\"\u0003\u0002|\tqAK]1og\u001a|'/\\*uCJ$(BA\u000e\u001d\u0003\tiGN\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019R\u0001\u0001\u0013+]E\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0007CA\u0016-\u001b\u0005Q\u0012BA\u0017\u001b\u0005\u001diE*\u0012<f]R\u0004\"!J\u0018\n\u0005A2#a\u0002)s_\u0012,8\r\u001e\t\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005Y\u0012\u0013A\u0002\u001fs_>$h(C\u0001(\u0013\tId%A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d'\u0003\u0019a\u0014N\\5u}Q\tq\b\u0005\u0002,\u0001\u0005YAO]1og\u001a|'/\\3s+\u0005\u0011\u0005CA\u0016D\u0013\t!%DA\u0006Ue\u0006t7OZ8s[\u0016\u0014\u0018a\u0004;sC:\u001chm\u001c:nKJ|F%Z9\u0015\u0005\u001dS\u0005CA\u0013I\u0013\tIeE\u0001\u0003V]&$\bbB&\u0004\u0003\u0003\u0005\rAQ\u0001\u0004q\u0012\n\u0014\u0001\u0004;sC:\u001chm\u001c:nKJ\u0004\u0003F\u0001\u0003O!\ty\u0005,D\u0001Q\u0015\t\t&+\u0001\u0006b]:|G/\u0019;j_:T!a\u0015+\u0002\u000f)\f7m[:p]*\u0011QKV\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011aV\u0001\u0004G>l\u0017BA-Q\u0005)Q5o\u001c8JO:|'/Z\u0001\u0006S:\u0004X\u000f^\u000b\u00029B\u0012Q,\u001a\t\u0004=\u0006\u001cW\"A0\u000b\u0005\u0001d\u0012aA:rY&\u0011!m\u0018\u0002\b\t\u0006$\u0018m]3u!\t!W\r\u0004\u0001\u0005\u0013\u0019<\u0011\u0011!A\u0001\u0006\u0003I'aA0%c\u00051\u0011N\u001c9vi\u0002B#a\u0002(\u0012\u0005)l\u0007CA\u0013l\u0013\tagEA\u0004O_RD\u0017N\\4\u0011\u0005\u0015r\u0017BA8'\u0005\r\te._\u0001\nS:\u0004X\u000f^0%KF$\"a\u0012:\t\u000f-3\u0011\u0011!a\u0001gB\u0012AO\u001e\t\u0004=\u0006,\bC\u00013w\t%1'/!A\u0001\u0002\u000b\u0005\u0011.\u0001\u0003d_BL\u0018!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001{!\rY\u0018\u0011A\u0007\u0002y*\u0011QP`\u0001\u0005Y\u0006twMC\u0001\u0000\u0003\u0011Q\u0017M^1\n\u0007\u0005\rAP\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u0013\u00012!JA\u0006\u0013\r\tiA\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004[\u0006M\u0001\u0002C&\f\u0003\u0003\u0005\r!!\u0003\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0007\u0011\u000b\u0005m\u0011\u0011E7\u000e\u0005\u0005u!bAA\u0010M\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\r\u0012Q\u0004\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002*\u0005=\u0002cA\u0013\u0002,%\u0019\u0011Q\u0006\u0014\u0003\u000f\t{w\u000e\\3b]\"91*DA\u0001\u0002\u0004i\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A_A\u001b\u0011!Ye\"!AA\u0002\u0005%\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005%\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003i\fa!Z9vC2\u001cH\u0003BA\u0015\u0003\u0007BqaS\t\u0002\u0002\u0003\u0007Q\u000eK\u0002\u0001\u0003\u000f\u0002B!!\u0013\u0002N5\u0011\u00111\n\u0006\u0003#rIA!a\u0014\u0002L\tAQI^8mm&tw-\u0001\bUe\u0006t7OZ8s[N#\u0018M\u001d;\u0011\u0005-\u001a2#B\n\u0002X\u0005\r\u0004#BA-\u0003?zTBAA.\u0015\r\tiFJ\u0001\beVtG/[7f\u0013\u0011\t\t'a\u0017\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0007\u0007\u0005\u0003\u0002f\u0005-TBAA4\u0015\r\tIG`\u0001\u0003S>L1aOA4)\t\t\u0019&A\u0003baBd\u00170A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005%\u0012Q\u000f\u0005\t\u0003o:\u0012\u0011!a\u0001\u007f\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0004cA>\u0002\u0000%\u0019\u0011\u0011\u0011?\u0003\r=\u0013'.Z2u\u0001"
)
public class TransformStart implements MLEvent, Product, Serializable {
   @JsonIgnore
   private Transformer transformer;
   @JsonIgnore
   private Dataset input;

   public static boolean unapply(final TransformStart x$0) {
      return TransformStart$.MODULE$.unapply(x$0);
   }

   public static TransformStart apply() {
      return TransformStart$.MODULE$.apply();
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

   public Dataset input() {
      return this.input;
   }

   public void input_$eq(final Dataset x$1) {
      this.input = x$1;
   }

   public TransformStart copy() {
      return new TransformStart();
   }

   public String productPrefix() {
      return "TransformStart";
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
      return x$1 instanceof TransformStart;
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
      return x$1 instanceof TransformStart && ((TransformStart)x$1).canEqual(this);
   }

   public TransformStart() {
      SparkListenerEvent.$init$(this);
      MLEvent.$init$(this);
      Product.$init$(this);
   }
}
