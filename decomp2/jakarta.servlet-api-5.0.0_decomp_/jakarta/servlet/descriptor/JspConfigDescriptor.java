package jakarta.servlet.descriptor;

import java.util.Collection;

public interface JspConfigDescriptor {
   Collection getTaglibs();

   Collection getJspPropertyGroups();
}
