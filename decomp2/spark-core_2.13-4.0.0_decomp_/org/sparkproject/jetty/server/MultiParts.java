package org.sparkproject.jetty.server;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.http.Part;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import org.sparkproject.jetty.server.handler.ContextHandler;

public interface MultiParts extends Closeable {
   Collection getParts() throws IOException;

   Part getPart(String var1) throws IOException;

   boolean isEmpty();

   ContextHandler.Context getContext();

   EnumSet getNonComplianceWarnings();

   public static enum NonCompliance {
      CR_LINE_TERMINATION("https://tools.ietf.org/html/rfc2046#section-4.1.1"),
      LF_LINE_TERMINATION("https://tools.ietf.org/html/rfc2046#section-4.1.1"),
      NO_CRLF_AFTER_PREAMBLE("https://tools.ietf.org/html/rfc2046#section-5.1.1"),
      BASE64_TRANSFER_ENCODING("https://tools.ietf.org/html/rfc7578#section-4.7"),
      QUOTED_PRINTABLE_TRANSFER_ENCODING("https://tools.ietf.org/html/rfc7578#section-4.7"),
      TRANSFER_ENCODING("https://tools.ietf.org/html/rfc7578#section-4.7");

      final String _rfcRef;

      private NonCompliance(String rfcRef) {
         this._rfcRef = rfcRef;
      }

      public String getURL() {
         return this._rfcRef;
      }

      // $FF: synthetic method
      private static NonCompliance[] $values() {
         return new NonCompliance[]{CR_LINE_TERMINATION, LF_LINE_TERMINATION, NO_CRLF_AFTER_PREAMBLE, BASE64_TRANSFER_ENCODING, QUOTED_PRINTABLE_TRANSFER_ENCODING, TRANSFER_ENCODING};
      }
   }

   public static class MultiPartsHttpParser implements MultiParts {
      private final MultiPartFormInputStream _httpParser;
      private final ContextHandler.Context _context;
      private final Request _request;

      public MultiPartsHttpParser(InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir, Request request) throws IOException {
         this(in, contentType, config, contextTmpDir, request, 1000);
      }

      public MultiPartsHttpParser(InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir, Request request, int maxParts) throws IOException {
         this._httpParser = new MultiPartFormInputStream(in, contentType, config, contextTmpDir, maxParts);
         this._context = request.getContext();
         this._request = request;
      }

      public Collection getParts() throws IOException {
         Collection<Part> parts = this._httpParser.getParts();
         this.setNonComplianceViolationsOnRequest();
         return parts;
      }

      public Part getPart(String name) throws IOException {
         Part part = this._httpParser.getPart(name);
         this.setNonComplianceViolationsOnRequest();
         return part;
      }

      public void close() {
         this._httpParser.deleteParts();
      }

      public boolean isEmpty() {
         return this._httpParser.isEmpty();
      }

      public ContextHandler.Context getContext() {
         return this._context;
      }

      public EnumSet getNonComplianceWarnings() {
         return this._httpParser.getNonComplianceWarnings();
      }

      private void setNonComplianceViolationsOnRequest() {
         List<String> violations = (List)this._request.getAttribute("org.sparkproject.jetty.http.compliance.violations");
         if (violations == null) {
            EnumSet<NonCompliance> nonComplianceWarnings = this._httpParser.getNonComplianceWarnings();
            violations = new ArrayList();

            for(NonCompliance nc : nonComplianceWarnings) {
               String var10001 = nc.name();
               violations.add(var10001 + ": " + nc.getURL());
            }

            this._request.setAttribute("org.sparkproject.jetty.http.compliance.violations", violations);
         }
      }
   }

   public static class MultiPartsUtilParser implements MultiParts {
      private final MultiPartInputStreamParser _utilParser;
      private final ContextHandler.Context _context;
      private final Request _request;

      public MultiPartsUtilParser(InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir, Request request) throws IOException {
         this(in, contentType, config, contextTmpDir, request, 1000);
      }

      public MultiPartsUtilParser(InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir, Request request, int maxParts) throws IOException {
         this._utilParser = new MultiPartInputStreamParser(in, contentType, config, contextTmpDir, maxParts);
         this._context = request.getContext();
         this._request = request;
      }

      public Collection getParts() throws IOException {
         Collection<Part> parts = this._utilParser.getParts();
         this.setNonComplianceViolationsOnRequest();
         return parts;
      }

      public Part getPart(String name) throws IOException {
         Part part = this._utilParser.getPart(name);
         this.setNonComplianceViolationsOnRequest();
         return part;
      }

      public void close() {
         this._utilParser.deleteParts();
      }

      public boolean isEmpty() {
         return this._utilParser.getParsedParts().isEmpty();
      }

      public ContextHandler.Context getContext() {
         return this._context;
      }

      public EnumSet getNonComplianceWarnings() {
         return this._utilParser.getNonComplianceWarnings();
      }

      private void setNonComplianceViolationsOnRequest() {
         List<String> violations = (List)this._request.getAttribute("org.sparkproject.jetty.http.compliance.violations");
         if (violations == null) {
            EnumSet<NonCompliance> nonComplianceWarnings = this._utilParser.getNonComplianceWarnings();
            violations = new ArrayList();

            for(NonCompliance nc : nonComplianceWarnings) {
               String var10001 = nc.name();
               violations.add(var10001 + ": " + nc.getURL());
            }

            this._request.setAttribute("org.sparkproject.jetty.http.compliance.violations", violations);
         }
      }
   }
}
