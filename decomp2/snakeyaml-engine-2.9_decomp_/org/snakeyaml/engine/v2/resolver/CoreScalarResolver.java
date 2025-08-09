package org.snakeyaml.engine.v2.resolver;

import java.util.regex.Pattern;
import org.snakeyaml.engine.v2.nodes.Tag;

public class CoreScalarResolver extends BaseScalarResolver {
   public static final Pattern BOOL = Pattern.compile("^(?:true|True|TRUE|false|False|FALSE)$");
   public static final Pattern FLOAT = Pattern.compile("^([-+]?(\\.[0-9]+|[0-9]+(\\.[0-9]*)?)([eE][-+]?[0-9]+)?)|([-+]?\\.(?:inf|Inf|INF))|(\\.(?:nan|NaN|NAN))$");
   public static final Pattern INT = Pattern.compile("^([-+]?[0-9]+)|(0o[0-7]+)|(0x[0-9a-fA-F]+)$");
   public static final Pattern NULL = Pattern.compile("^(?:~|null|Null|NULL| )$");

   protected void addImplicitResolvers() {
      this.addImplicitResolver(Tag.NULL, EMPTY, (String)null);
      this.addImplicitResolver(Tag.BOOL, BOOL, "tfTF");
      this.addImplicitResolver(Tag.INT, INT, "-+0123456789");
      this.addImplicitResolver(Tag.FLOAT, FLOAT, "-+0123456789.");
      this.addImplicitResolver(Tag.NULL, NULL, "n\u0000");
      this.addImplicitResolver(Tag.ENV_TAG, ENV_FORMAT, "$");
   }
}
