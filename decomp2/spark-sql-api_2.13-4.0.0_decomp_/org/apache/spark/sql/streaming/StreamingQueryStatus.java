package org.apache.spark.sql.streaming;

import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.json4s.JString;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005]4A\u0001E\t\u00019!Aq\u0006\u0001BC\u0002\u0013\u0005\u0001\u0007\u0003\u0005:\u0001\t\u0005\t\u0015!\u00032\u0011!Q\u0004A!b\u0001\n\u0003Y\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u0011\u0001\u0003!Q1A\u0005\u0002mB\u0001\"\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\u0010\u0005\u0007\u0005\u0002!\tbE\"\t\u000b%\u0003A\u0011\u0001\u0019\t\u000b)\u0003A\u0011\u0001\u0019\t\u000b-\u0003A\u0011\t'\t\r5\u0003A\u0011A\nO\u0011!\u0011\u0006!%A\u0005\u0002M\u0019\u0006\u0002\u00030\u0001#\u0003%\taE0\t\u0011\u0005\u0004\u0011\u0013!C\u0001'}CaA\u0019\u0001\u0005\u0002M\u0019'\u0001F*ue\u0016\fW.\u001b8h#V,'/_*uCR,8O\u0003\u0002\u0013'\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003)U\t1a]9m\u0015\t1r#A\u0003ta\u0006\u00148N\u0003\u0002\u00193\u00051\u0011\r]1dQ\u0016T\u0011AG\u0001\u0004_J<7\u0001A\n\u0004\u0001u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"AB!osJ+g\r\u0005\u0002%Y9\u0011QE\u000b\b\u0003M%j\u0011a\n\u0006\u0003Qm\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0011\n\u0005-z\u0012a\u00029bG.\fw-Z\u0005\u0003[9\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aK\u0010\u0002\u000f5,7o]1hKV\t\u0011\u0007\u0005\u00023m9\u00111\u0007\u000e\t\u0003M}I!!N\u0010\u0002\rA\u0013X\rZ3g\u0013\t9\u0004H\u0001\u0004TiJLgn\u001a\u0006\u0003k}\t\u0001\"\\3tg\u0006<W\rI\u0001\u0010SN$\u0015\r^1Bm\u0006LG.\u00192mKV\tA\b\u0005\u0002\u001f{%\u0011ah\b\u0002\b\u0005>|G.Z1o\u0003AI7\u000fR1uC\u00063\u0018-\u001b7bE2,\u0007%A\bjgR\u0013\u0018nZ4fe\u0006\u001bG/\u001b<f\u0003AI7\u000f\u0016:jO\u001e,'/Q2uSZ,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005\t\u001a;\u0005\n\u0005\u0002F\u00015\t\u0011\u0003C\u00030\u000f\u0001\u0007\u0011\u0007C\u0003;\u000f\u0001\u0007A\bC\u0003A\u000f\u0001\u0007A(\u0001\u0003kg>t\u0017A\u00039sKR$\u0018PS:p]\u0006AAo\\*ue&tw\rF\u00012\u0003\u0011\u0019w\u000e]=\u0015\t\u0011{\u0005+\u0015\u0005\b_-\u0001\n\u00111\u00012\u0011\u001dQ4\u0002%AA\u0002qBq\u0001Q\u0006\u0011\u0002\u0003\u0007A(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003QS#!M+,\u0003Y\u0003\"a\u0016/\u000e\u0003aS!!\u0017.\u0002\u0013Ut7\r[3dW\u0016$'BA. \u0003)\tgN\\8uCRLwN\\\u0005\u0003;b\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012\u0001\u0019\u0016\u0003yU\u000babY8qs\u0012\"WMZ1vYR$3'A\u0005kg>tg+\u00197vKV\tA\r\u0005\u0002f]:\u0011am\u001b\b\u0003O&t!A\n5\n\u0003iI!A[\r\u0002\r)\u001cxN\u001c\u001bt\u0013\taW.A\u0004Kg>t\u0017i\u0015+\u000b\u0005)L\u0012BA8q\u0005\u0019Qe+\u00197vK*\u0011A.\u001c\u0015\u0003\u0001I\u0004\"a];\u000e\u0003QT!aW\u000b\n\u0005Y$(\u0001C#w_24\u0018N\\4"
)
public class StreamingQueryStatus implements Serializable {
   private final String message;
   private final boolean isDataAvailable;
   private final boolean isTriggerActive;

   public String message() {
      return this.message;
   }

   public boolean isDataAvailable() {
      return this.isDataAvailable;
   }

   public boolean isTriggerActive() {
      return this.isTriggerActive;
   }

   public String json() {
      return .MODULE$.compact(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public String prettyJson() {
      return .MODULE$.pretty(.MODULE$.render(this.jsonValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public String toString() {
      return this.prettyJson();
   }

   public StreamingQueryStatus copy(final String message, final boolean isDataAvailable, final boolean isTriggerActive) {
      return new StreamingQueryStatus(message, isDataAvailable, isTriggerActive);
   }

   public String copy$default$1() {
      return this.message();
   }

   public boolean copy$default$2() {
      return this.isDataAvailable();
   }

   public boolean copy$default$3() {
      return this.isTriggerActive();
   }

   public JValue jsonValue() {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("message"), new JString(this.message())), scala.Predef..MODULE$.$conforms()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("isDataAvailable"), org.json4s.JBool..MODULE$.apply(this.isDataAvailable())), scala.Predef..MODULE$.$conforms(), scala.Predef..MODULE$.$conforms())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("isTriggerActive"), org.json4s.JBool..MODULE$.apply(this.isTriggerActive())));
   }

   public StreamingQueryStatus(final String message, final boolean isDataAvailable, final boolean isTriggerActive) {
      this.message = message;
      this.isDataAvailable = isDataAvailable;
      this.isTriggerActive = isTriggerActive;
   }
}
