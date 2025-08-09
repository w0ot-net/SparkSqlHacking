package org.apache.spark.deploy.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.spark.util.Utils$;
import scala.reflect.ScalaSignature;

@JsonInclude(Include.NON_ABSENT)
@JsonAutoDetect(
   getterVisibility = Visibility.ANY,
   setterVisibility = Visibility.ANY
)
@JsonPropertyOrder(
   alphabetic = true
)
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005eAB\r\u001b\u0003\u0003QB\u0005C\u0003,\u0001\u0011\u0005Q\u0006C\u00041\u0001\t\u0007I\u0011A\u0019\t\ru\u0002\u0001\u0015!\u00033\u0011\u001dY\u0005A1A\u0005\u0002EBa\u0001\u0014\u0001!\u0002\u0013\u0011\u0004bB'\u0001\u0001\u0004%\t!\r\u0005\b\u001d\u0002\u0001\r\u0011\"\u0001P\u0011\u0019)\u0006\u0001)Q\u0005e!)a\u000b\u0001C\u0005/\")!\f\u0001C\u0001c!)1\f\u0001C\u00039\")Q\f\u0001C\t9\")a\f\u0001C\t?\")\u0001\u000f\u0001C\tc\u001eA\u0011q\u0006\u000e\t\u0002y\t\tDB\u0004\u001a5!\u0005a$a\r\t\r-\u0002B\u0011AA\u001b\u0011%\t9\u0004\u0005b\u0001\n\u0013\tI\u0004\u0003\u0005\u0002JA\u0001\u000b\u0011BA\u001e\u0011%\tY\u0005\u0005b\u0001\n\u0013\ti\u0005\u0003\u0005\u0002\\A\u0001\u000b\u0011BA(\u0011\u001d\ti\u0006\u0005C\u0001\u0003?Bq!!\u001a\u0011\t\u0003\t9\u0007C\u0004\u0002fA!\t!a\u001b\u00033M+(-\\5u%\u0016\u001cH\u000f\u0015:pi>\u001cw\u000e\\'fgN\fw-\u001a\u0006\u00037q\tAA]3ti*\u0011QDH\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005}\u0001\u0013!B:qCJ\\'BA\u0011#\u0003\u0019\t\u0007/Y2iK*\t1%A\u0002pe\u001e\u001c\"\u0001A\u0013\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0018\u0011\u0005=\u0002Q\"\u0001\u000e\u0002\u00175,7o]1hKRK\b/Z\u000b\u0002eA\u00111G\u000f\b\u0003ia\u0002\"!N\u0014\u000e\u0003YR!a\u000e\u0017\u0002\rq\u0012xn\u001c;?\u0013\tIt%\u0001\u0004Qe\u0016$WMZ\u0005\u0003wq\u0012aa\u0015;sS:<'BA\u001d(\u00031iWm]:bO\u0016$\u0016\u0010]3!Q\t\u0019q\b\u0005\u0002A\u00136\t\u0011I\u0003\u0002C\u0007\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0005\u0011+\u0015a\u00026bG.\u001cxN\u001c\u0006\u0003\r\u001e\u000b\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003!\u000b1aY8n\u0013\tQ\u0015I\u0001\u0006Kg>t\u0017j\u001a8pe\u0016\fa!Y2uS>t\u0017aB1di&|g\u000eI\u0001\b[\u0016\u001c8/Y4f\u0003-iWm]:bO\u0016|F%Z9\u0015\u0005A\u001b\u0006C\u0001\u0014R\u0013\t\u0011vE\u0001\u0003V]&$\bb\u0002+\b\u0003\u0003\u0005\rAM\u0001\u0004q\u0012\n\u0014\u0001C7fgN\fw-\u001a\u0011\u0002\u0013M,G/Q2uS>tGC\u0001)Y\u0011\u0015I\u0016\u00021\u00013\u0003\u0005\t\u0017A\u0002;p\u0015N|g.\u0001\u0005wC2LG-\u0019;f)\u0005\u0001\u0016A\u00033p-\u0006d\u0017\u000eZ1uK\u0006\u0001\u0012m]:feR4\u0015.\u001a7e\u0013N\u001cV\r^\u000b\u0003A\u0016$2\u0001U1o\u0011\u0015\u0011W\u00021\u0001d\u0003\u00151\u0018\r\\;f!\t!W\r\u0004\u0001\u0005\u000b\u0019l!\u0019A4\u0003\u0003Q\u000b\"\u0001[6\u0011\u0005\u0019J\u0017B\u00016(\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\n7\n\u00055<#aA!os\")q.\u0004a\u0001e\u0005!a.Y7f\u0003\u0019\t7o]3siR\u0019\u0001K]<\t\u000bMt\u0001\u0019\u0001;\u0002\u0013\r|g\u000eZ5uS>t\u0007C\u0001\u0014v\u0013\t1xEA\u0004C_>dW-\u00198\t\u000bat\u0001\u0019\u0001\u001a\u0002\u0017\u0019\f\u0017\u000e\\'fgN\fw-\u001a\u0015\u0005\u0001i\u0014W\u0010\u0005\u0002Aw&\u0011A0\u0011\u0002\f\u0015N|g.\u00138dYV$W\rJ\u0001\u007f\u0013\ry\u0018\u0011A\u0001\u000b\u001d>su,\u0011\"T\u000b:#&\u0002BA\u0002\u0003\u000b\tq!\u00138dYV$WMC\u0002\u0002\b\u0005\u000b1BS:p]&s7\r\\;eK\"Z\u0001!a\u0003\u0002\u0012\u0005M\u0011\u0011EA\n!\r\u0001\u0015QB\u0005\u0004\u0003\u001f\t%A\u0004&t_:\fU\u000f^8EKR,7\r^\u0001\u0011O\u0016$H/\u001a:WSNL'-\u001b7jif$#!!\u0006\n\t\u0005]\u0011\u0011D\u0001\u0004\u0003:K&\u0002BA\u000e\u0003;\t!BV5tS\nLG.\u001b;z\u0015\r\ty\"Q\u0001\u000f\u0015N|g.Q;u_\u0012+G/Z2u\u0003A\u0019X\r\u001e;feZK7/\u001b2jY&$\u0018\u0010K\u0004\u0001\u0003K\tY#!\f\u0011\u0007\u0001\u000b9#C\u0002\u0002*\u0005\u0013\u0011CS:p]B\u0013x\u000e]3sif|%\u000fZ3s\u0003)\tG\u000e\u001d5bE\u0016$\u0018nY\r\u0002\u0003\u0005I2+\u001e2nSR\u0014Vm\u001d;Qe>$xnY8m\u001b\u0016\u001c8/Y4f!\ty\u0003c\u0005\u0002\u0011KQ\u0011\u0011\u0011G\u0001\u000ea\u0006\u001c7.Y4f!J,g-\u001b=\u0016\u0005\u0005m\u0002\u0003BA\u001f\u0003\u000fj!!a\u0010\u000b\t\u0005\u0005\u00131I\u0001\u0005Y\u0006twM\u0003\u0002\u0002F\u0005!!.\u0019<b\u0013\rY\u0014qH\u0001\u000fa\u0006\u001c7.Y4f!J,g-\u001b=!\u0003\u0019i\u0017\r\u001d9feV\u0011\u0011q\n\t\u0005\u0003#\n9&\u0004\u0002\u0002T)\u0019\u0011QK\"\u0002\u0011\u0011\fG/\u00192j]\u0012LA!!\u0017\u0002T\taqJ\u00196fGRl\u0015\r\u001d9fe\u00069Q.\u00199qKJ\u0004\u0013a\u00039beN,\u0017i\u0019;j_:$2AMA1\u0011\u0019\t\u0019G\u0006a\u0001e\u0005!!n]8o\u0003!1'o\\7Kg>tGc\u0001\u0018\u0002j!1\u00111M\fA\u0002I*B!!\u001c\u0002rQ1\u0011qNA;\u0003o\u00022\u0001ZA9\t\u00191\u0007D1\u0001\u0002tE\u0011\u0001N\f\u0005\u0007\u0003GB\u0002\u0019\u0001\u001a\t\u000f\u0005e\u0004\u00041\u0001\u0002|\u0005)1\r\\1{uB)1'! \u0002p%\u0019\u0011q\u0010\u001f\u0003\u000b\rc\u0017m]:"
)
public abstract class SubmitRestProtocolMessage {
   @JsonIgnore
   private final String messageType;
   private final String action;
   private String message;

   public static SubmitRestProtocolMessage fromJson(final String json, final Class clazz) {
      return SubmitRestProtocolMessage$.MODULE$.fromJson(json, clazz);
   }

   public static SubmitRestProtocolMessage fromJson(final String json) {
      return SubmitRestProtocolMessage$.MODULE$.fromJson(json);
   }

   public static String parseAction(final String json) {
      return SubmitRestProtocolMessage$.MODULE$.parseAction(json);
   }

   public String messageType() {
      return this.messageType;
   }

   public String action() {
      return this.action;
   }

   public String message() {
      return this.message;
   }

   public void message_$eq(final String x$1) {
      this.message = x$1;
   }

   private void setAction(final String a) {
   }

   public String toJson() {
      this.validate();
      return SubmitRestProtocolMessage$.MODULE$.org$apache$spark$deploy$rest$SubmitRestProtocolMessage$$mapper().writeValueAsString(this);
   }

   public final void validate() {
      try {
         this.doValidate();
      } catch (Exception var2) {
         throw new SubmitRestProtocolException("Validation of message " + this.messageType() + " failed!", var2);
      }
   }

   public void doValidate() {
      if (this.action() == null) {
         throw new SubmitRestMissingFieldException("The action field is missing in " + this.messageType());
      }
   }

   public void assertFieldIsSet(final Object value, final String name) {
      if (value == null) {
         throw new SubmitRestMissingFieldException("'" + name + "' is missing in message " + this.messageType() + ".");
      }
   }

   public void assert(final boolean condition, final String failMessage) {
      if (!condition) {
         throw new SubmitRestProtocolException(failMessage, SubmitRestProtocolException$.MODULE$.$lessinit$greater$default$2());
      }
   }

   public SubmitRestProtocolMessage() {
      this.messageType = Utils$.MODULE$.getFormattedClassName(this);
      this.action = this.messageType();
      this.message = null;
   }
}
