package jakarta.servlet;

import java.io.IOException;

public interface RequestDispatcher {
   String FORWARD_REQUEST_URI = "jakarta.servlet.forward.request_uri";
   String FORWARD_CONTEXT_PATH = "jakarta.servlet.forward.context_path";
   String FORWARD_MAPPING = "jakarta.servlet.forward.mapping";
   String FORWARD_PATH_INFO = "jakarta.servlet.forward.path_info";
   String FORWARD_SERVLET_PATH = "jakarta.servlet.forward.servlet_path";
   String FORWARD_QUERY_STRING = "jakarta.servlet.forward.query_string";
   String INCLUDE_REQUEST_URI = "jakarta.servlet.include.request_uri";
   String INCLUDE_CONTEXT_PATH = "jakarta.servlet.include.context_path";
   String INCLUDE_PATH_INFO = "jakarta.servlet.include.path_info";
   String INCLUDE_MAPPING = "jakarta.servlet.include.mapping";
   String INCLUDE_SERVLET_PATH = "jakarta.servlet.include.servlet_path";
   String INCLUDE_QUERY_STRING = "jakarta.servlet.include.query_string";
   String ERROR_EXCEPTION = "jakarta.servlet.error.exception";
   String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";
   String ERROR_MESSAGE = "jakarta.servlet.error.message";
   String ERROR_REQUEST_URI = "jakarta.servlet.error.request_uri";
   String ERROR_SERVLET_NAME = "jakarta.servlet.error.servlet_name";
   String ERROR_STATUS_CODE = "jakarta.servlet.error.status_code";

   void forward(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

   void include(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
}
