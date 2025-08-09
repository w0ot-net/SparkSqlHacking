package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer;
import com.fasterxml.jackson.databind.util.NameTransformer;
import scala.Option;
import scala..less.colon.less.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec\u0001\u0002\b\u0010\u0001qA\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\t{\u0001\u0011\t\u0011)A\u0005}!A!\t\u0001B\u0001B\u0003%1\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003K\u0011!\t\u0006A!A!\u0002\u0013\u0011\u0006\u0002\u0003-\u0001\u0005\u0003\u0005\u000b\u0011B-\t\u0011q\u0003!\u0011!Q\u0001\nuCQ\u0001\u0019\u0001\u0005\u0002\u0005DQa\u001d\u0001\u0005BQDq!a\u0003\u0001\t\u0003\ni\u0001C\u0004\u0002 \u0001!\t&!\t\t\u000f\u0005E\u0002\u0001\"\u0015\u00024!9\u0011\u0011\t\u0001\u0005R\u0005\r#\u0001\u0007*fg>dg/\u001a3PaRLwN\\*fe&\fG.\u001b>fe*\u0011\u0001#E\u0001\u0004g\u0016\u0014(B\u0001\n\u0014\u0003\u0015\u00198-\u00197b\u0015\t!R#\u0001\u0004n_\u0012,H.\u001a\u0006\u0003-]\tqA[1dWN|gN\u0003\u0002\u00193\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u00025\u0005\u00191m\\7\u0004\u0001M\u0011\u0001!\b\t\u0004=\u00112S\"A\u0010\u000b\u0005\u0001\n\u0013aA:uI*\u0011\u0001C\t\u0006\u0003GU\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003K}\u0011qCU3gKJ,gnY3UsB,7+\u001a:jC2L'0\u001a:1\u0005\u001dr\u0003c\u0001\u0015+Y5\t\u0011FC\u0001\u0013\u0013\tY\u0013F\u0001\u0004PaRLwN\u001c\t\u0003[9b\u0001\u0001B\u00050\u0001\u0005\u0005\t\u0011!B\u0001a\t!q\fJ\u00192#\t\tD\u0007\u0005\u0002)e%\u00111'\u000b\u0002\b\u001d>$\b.\u001b8h!\tAS'\u0003\u00027S\t\u0019\u0011I\\=\u0002\t\t\f7/\u001a\u0019\u0003sm\u00022A\b\u0013;!\ti3\bB\u0005=\u0003\u0005\u0005\t\u0011!B\u0001a\t\u0019q\fJ\u001d\u0002\u0011A\u0014x\u000e]3sif\u0004\"a\u0010!\u000e\u0003\tJ!!\u0011\u0012\u0003\u0019\t+\u0017M\u001c)s_B,'\u000f^=\u0002\u0007Y$8\u000f\u0005\u0002E\u000f6\tQI\u0003\u0002GE\u0005A!n]8oif\u0004X-\u0003\u0002I\u000b\nqA+\u001f9f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018\u0001\u0003<bYV,7+\u001a:1\u0005-{\u0005cA M\u001d&\u0011QJ\t\u0002\u000f\u0015N|gnU3sS\u0006d\u0017N_3s!\tis\nB\u0005Q\t\u0005\u0005\t\u0011!B\u0001a\t!q\fJ\u00191\u0003%)hn\u001e:baB,'\u000f\u0005\u0002T-6\tAK\u0003\u0002VE\u0005!Q\u000f^5m\u0013\t9FKA\bOC6,GK]1og\u001a|'/\\3s\u0003E\u0019X\u000f\u001d9sKN\u001c\u0018M\u00197f-\u0006dW/\u001a\t\u0003QiK!aW\u0015\u0003\r\u0005s\u0017PU3g\u00035\u0019X\u000f\u001d9sKN\u001ch*\u001e7mgB\u0011\u0001FX\u0005\u0003?&\u0012qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\tE\u0012L'n\u001b9reB\u00111\rA\u0007\u0002\u001f!)q\u0007\u0003a\u0001KB\u0012a\r\u001b\t\u0004=\u0011:\u0007CA\u0017i\t%aD-!A\u0001\u0002\u000b\u0005\u0001\u0007C\u0003>\u0011\u0001\u0007a\bC\u0003C\u0011\u0001\u00071\tC\u0003J\u0011\u0001\u0007A\u000e\r\u0002n_B\u0019q\b\u00148\u0011\u00055zG!\u0003)l\u0003\u0003\u0005\tQ!\u00011\u0011\u0015\t\u0006\u00021\u0001S\u0011\u0015A\u0006\u00021\u0001Z\u0011\u0015a\u0006\u00021\u0001^\u000319\u0018\u000e\u001e5SKN|GN^3e)\u0019)80 @\u0002\nA\u0019a\u0004\n<1\u0005]L\bc\u0001\u0015+qB\u0011Q&\u001f\u0003\nu&\t\t\u0011!A\u0003\u0002A\u0012Aa\u0018\u00132g!)A0\u0003a\u0001}\u0005!\u0001O]8q\u0011\u0015\u0011\u0015\u00021\u0001D\u0011\u0015I\u0015\u00021\u0001\u0000a\u0011\t\t!!\u0002\u0011\t}b\u00151\u0001\t\u0004[\u0005\u0015AACA\u0004}\u0006\u0005\t\u0011!B\u0001a\t!q\fJ\u00193\u0011\u0015\t\u0016\u00021\u0001S\u0003Q9\u0018\u000e\u001e5D_:$XM\u001c;J]\u000edWo]5p]R1\u0011qBA\u000e\u0003;\u0001BA\b\u0013\u0002\u0012A\"\u00111CA\f!\u0011A#&!\u0006\u0011\u00075\n9\u0002\u0002\u0006\u0002\u001a)\t\t\u0011!A\u0003\u0002A\u0012Aa\u0018\u00132i!)\u0001L\u0003a\u00013\")AL\u0003a\u0001;\u0006yq,[:WC2,X\r\u0015:fg\u0016tG\u000fF\u0002^\u0003GAq!!\n\f\u0001\u0004\t9#A\u0003wC2,X\r\r\u0003\u0002*\u00055\u0002\u0003\u0002\u0015+\u0003W\u00012!LA\u0017\t-\ty#a\t\u0002\u0002\u0003\u0005)\u0011\u0001\u0019\u0003\t}#\u0013'N\u0001\u000f?\u001e,GOU3gKJ,gnY3e)\rI\u0016Q\u0007\u0005\b\u0003Ka\u0001\u0019AA\u001ca\u0011\tI$!\u0010\u0011\t!R\u00131\b\t\u0004[\u0005uBaCA \u0003k\t\t\u0011!A\u0003\u0002A\u0012Aa\u0018\u00132m\u00059rlZ3u%\u00164WM]3oG\u0016$\u0017J\u001a)sKN,g\u000e\u001e\u000b\u00043\u0006\u0015\u0003bBA\u0013\u001b\u0001\u0007\u0011q\t\u0019\u0005\u0003\u0013\ni\u0005\u0005\u0003)U\u0005-\u0003cA\u0017\u0002N\u0011Y\u0011qJA#\u0003\u0003\u0005\tQ!\u00011\u0005\u0011yF%M\u001c"
)
public class ResolvedOptionSerializer extends ReferenceTypeSerializer {
   public ReferenceTypeSerializer withResolved(final BeanProperty prop, final TypeSerializer vts, final JsonSerializer valueSer, final NameTransformer unwrapper) {
      return new ResolvedOptionSerializer(this, prop, vts, valueSer, unwrapper, this._suppressableValue, this._suppressNulls);
   }

   public ReferenceTypeSerializer withContentInclusion(final Object suppressableValue, final boolean suppressNulls) {
      return new ResolvedOptionSerializer(this, this._property, this._valueTypeSerializer, this._valueSerializer, this._unwrapper, suppressableValue, suppressNulls);
   }

   public boolean _isValuePresent(final Option value) {
      return value.isDefined();
   }

   public Object _getReferenced(final Option value) {
      return value.get();
   }

   public Object _getReferencedIfPresent(final Option value) {
      return value.orNull(.MODULE$.refl());
   }

   public ResolvedOptionSerializer(final ReferenceTypeSerializer base, final BeanProperty property, final TypeSerializer vts, final JsonSerializer valueSer, final NameTransformer unwrapper, final Object suppressableValue, final boolean suppressNulls) {
      super(base, property, vts, valueSer, unwrapper, suppressableValue, suppressNulls);
   }
}
