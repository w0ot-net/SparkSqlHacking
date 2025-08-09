package org.apache.logging.log4j.core.impl;

import java.io.Serializable;
import java.util.Objects;
import org.apache.logging.log4j.core.pattern.PlainTextRenderer;
import org.apache.logging.log4j.core.pattern.TextRenderer;

public final class ExtendedStackTraceElement implements Serializable {
   static final ExtendedStackTraceElement[] EMPTY_ARRAY = new ExtendedStackTraceElement[0];
   private static final long serialVersionUID = -2171069569241280505L;
   private final ExtendedClassInfo extraClassInfo;
   private final StackTraceElement stackTraceElement;

   public ExtendedStackTraceElement(final StackTraceElement stackTraceElement, final ExtendedClassInfo extraClassInfo) {
      this.stackTraceElement = stackTraceElement;
      this.extraClassInfo = extraClassInfo;
   }

   public ExtendedStackTraceElement(final String declaringClass, final String methodName, final String fileName, final int lineNumber, final boolean exact, final String location, final String version) {
      this(new StackTraceElement(declaringClass, methodName, fileName, lineNumber), new ExtendedClassInfo(exact, location, version));
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof ExtendedStackTraceElement)) {
         return false;
      } else {
         ExtendedStackTraceElement other = (ExtendedStackTraceElement)obj;
         if (!Objects.equals(this.extraClassInfo, other.extraClassInfo)) {
            return false;
         } else {
            return Objects.equals(this.stackTraceElement, other.stackTraceElement);
         }
      }
   }

   public String getClassName() {
      return this.stackTraceElement.getClassName();
   }

   public boolean getExact() {
      return this.extraClassInfo.getExact();
   }

   public ExtendedClassInfo getExtraClassInfo() {
      return this.extraClassInfo;
   }

   public String getFileName() {
      return this.stackTraceElement.getFileName();
   }

   public int getLineNumber() {
      return this.stackTraceElement.getLineNumber();
   }

   public String getLocation() {
      return this.extraClassInfo.getLocation();
   }

   public String getMethodName() {
      return this.stackTraceElement.getMethodName();
   }

   public StackTraceElement getStackTraceElement() {
      return this.stackTraceElement;
   }

   public String getVersion() {
      return this.extraClassInfo.getVersion();
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.extraClassInfo, this.stackTraceElement});
   }

   public boolean isNativeMethod() {
      return this.stackTraceElement.isNativeMethod();
   }

   void renderOn(final StringBuilder output, final TextRenderer textRenderer) {
      this.render(this.stackTraceElement, output, textRenderer);
      textRenderer.render(" ", output, "Text");
      this.extraClassInfo.renderOn(output, textRenderer);
   }

   private void render(final StackTraceElement stElement, final StringBuilder output, final TextRenderer textRenderer) {
      String fileName = stElement.getFileName();
      int lineNumber = stElement.getLineNumber();
      textRenderer.render(this.getClassName(), output, "StackTraceElement.ClassName");
      textRenderer.render(".", output, "StackTraceElement.ClassMethodSeparator");
      textRenderer.render(stElement.getMethodName(), output, "StackTraceElement.MethodName");
      if (stElement.isNativeMethod()) {
         textRenderer.render("(Native Method)", output, "StackTraceElement.NativeMethod");
      } else if (fileName != null && lineNumber >= 0) {
         textRenderer.render("(", output, "StackTraceElement.Container");
         textRenderer.render(fileName, output, "StackTraceElement.FileName");
         textRenderer.render(":", output, "StackTraceElement.ContainerSeparator");
         textRenderer.render(Integer.toString(lineNumber), output, "StackTraceElement.LineNumber");
         textRenderer.render(")", output, "StackTraceElement.Container");
      } else if (fileName != null) {
         textRenderer.render("(", output, "StackTraceElement.Container");
         textRenderer.render(fileName, output, "StackTraceElement.FileName");
         textRenderer.render(")", output, "StackTraceElement.Container");
      } else {
         textRenderer.render("(", output, "StackTraceElement.Container");
         textRenderer.render("Unknown Source", output, "StackTraceElement.UnknownSource");
         textRenderer.render(")", output, "StackTraceElement.Container");
      }

   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      this.renderOn(sb, PlainTextRenderer.getInstance());
      return sb.toString();
   }
}
