package org.yaml.snakeyaml.representer;

import java.util.Date;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

public class JsonRepresenter extends Representer {
   public JsonRepresenter(DumperOptions options) {
      super(options);
      this.representers.put(byte[].class, new RepresentByteArray());
      this.multiRepresenters.put(Date.class, new RepresentDate());
      if (options.getDefaultScalarStyle() != DumperOptions.ScalarStyle.JSON_SCALAR_STYLE) {
         throw new IllegalStateException("JSON requires ScalarStyle.JSON_SCALAR_STYLE");
      } else if (options.getNonPrintableStyle() != DumperOptions.NonPrintableStyle.ESCAPE) {
         throw new IllegalStateException("JSON requires NonPrintableStyle.ESCAPE");
      }
   }

   protected class RepresentDate extends SafeRepresenter.RepresentDate {
      public Tag getDefaultTag() {
         return Tag.STR;
      }
   }

   protected class RepresentByteArray implements Represent {
      public Node representData(Object data) {
         char[] binary = Base64Coder.encode((byte[])data);
         return JsonRepresenter.this.representScalar(Tag.STR, String.valueOf(binary));
      }
   }
}
