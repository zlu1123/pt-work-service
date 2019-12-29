package com.lsg.exception;

/**
*
* 自定义异常
* @author hubin
*/
public class BusinessException extends Exception {

   private String errorCode;

   public BusinessException(String errorCode, String msg) {
       super(msg);
       this.errorCode = errorCode;
   }
   
   public BusinessException(String msg) {
       super(msg);
   }
   
   @Override
   public Throwable fillInStackTrace() {
       return this;
   }

   public String getErrorCode() {
       return errorCode;
   }

   public void setErrorCode(String errorCode) {
       this.errorCode = errorCode;
   }
}
