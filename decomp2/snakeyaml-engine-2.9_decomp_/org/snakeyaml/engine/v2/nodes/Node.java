package org.snakeyaml.engine.v2.nodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.comments.CommentLine;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.exceptions.Mark;

public abstract class Node {
   private final Optional startMark;
   protected Optional endMark;
   protected boolean resolved;
   private Tag tag;
   private boolean recursive;
   private Optional anchor;
   private List inLineComments;
   private List blockComments;
   private List endComments;
   private Map properties;

   public Node(Tag tag, Optional startMark, Optional endMark) {
      this.setTag(tag);
      this.startMark = startMark;
      this.endMark = endMark;
      this.recursive = false;
      this.resolved = true;
      this.anchor = Optional.empty();
      this.inLineComments = null;
      this.blockComments = null;
      this.endComments = null;
      this.properties = null;
   }

   public Tag getTag() {
      return this.tag;
   }

   public void setTag(Tag tag) {
      Objects.requireNonNull(tag, "tag in a Node is required.");
      this.tag = tag;
   }

   public Optional getEndMark() {
      return this.endMark;
   }

   public abstract NodeType getNodeType();

   public Optional getStartMark() {
      return this.startMark;
   }

   public final boolean equals(Object obj) {
      return super.equals(obj);
   }

   public boolean isRecursive() {
      return this.recursive;
   }

   public void setRecursive(boolean recursive) {
      this.recursive = recursive;
   }

   public final int hashCode() {
      return super.hashCode();
   }

   public Optional getAnchor() {
      return this.anchor;
   }

   public void setAnchor(Optional anchor) {
      this.anchor = anchor;
   }

   public Object setProperty(String key, Object value) {
      if (this.properties == null) {
         this.properties = new HashMap();
      }

      return this.properties.put(key, value);
   }

   public Object getProperty(String key) {
      return this.properties == null ? null : this.properties.get(key);
   }

   public List getInLineComments() {
      return this.inLineComments;
   }

   public void setInLineComments(List inLineComments) {
      this.inLineComments = inLineComments;
   }

   public List getBlockComments() {
      return this.blockComments;
   }

   public void setBlockComments(List blockComments) {
      this.blockComments = blockComments;
   }

   public List getEndComments() {
      return this.endComments;
   }

   public void setEndComments(List endComments) {
      this.endComments = endComments;
   }
}
