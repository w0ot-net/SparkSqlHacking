package javax.servlet.descriptor;

import java.util.Collection;

public interface JspPropertyGroupDescriptor {
   Collection getUrlPatterns();

   String getElIgnored();

   String getPageEncoding();

   String getScriptingInvalid();

   String getIsXml();

   Collection getIncludePreludes();

   Collection getIncludeCodas();

   String getDeferredSyntaxAllowedAsLiteral();

   String getTrimDirectiveWhitespaces();

   String getDefaultContentType();

   String getBuffer();

   String getErrorOnUndeclaredNamespace();
}
