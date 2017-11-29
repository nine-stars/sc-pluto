package com.iyb.ak.exception;

import com.iyb.ak.entity.base.MessageVo;
import com.iyb.ak.logger.ErrorLogInfo;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fanjun on 2017/5/17.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Value("${spring.application.name:}")
    private String systemName;

    @ExceptionHandler(BadRequestException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<MessageVo> handleBadRequestException(HttpServletRequest request, HttpServletResponse response, BadRequestException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessageList(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<MessageVo> handleBusinessException(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
        log.error(ErrorLogInfo.build(ex.getSrcClass(), systemName, ex.getErrorCode(), ex.getMessage()).toJson());
        Throwable orgEx = ex.getOriginalException();
        if (orgEx != null)
            log.error(orgEx.getMessage(), orgEx);
        else
            log.error(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessageList());
    }

    @ExceptionHandler(UnauthorizedException.class)
//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<MessageVo> handleUnauthorizedException(HttpServletRequest request, HttpServletResponse response, UnauthorizedException ex) {
        log.error(ErrorLogInfo.build(ex.getSrcClass(), systemName, ex.getErrorCode(), ex.getMessage(), ex.getLoginId()).toJson());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessageList(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<MessageVo> handleNotFoundException(HttpServletRequest request, HttpServletResponse response, NotFoundException ex) {
        log.warn(ex.getMessage(), ex);
        if(ex.getMessageList() == null){
            NotFoundException ne = new NotFoundException(BaseException.ERR_9996, this);
            return new ResponseEntity<>(ne.getMessageList(), HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(ex.getMessageList(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
//    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<MessageVo> handleForbiddenException(HttpServletRequest request, HttpServletResponse response, ForbiddenException ex) {
        log.error(ErrorLogInfo.build(ex.getSrcClass(), systemName, ex.getErrorCode(), ex.getMessage()).toJson());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessageList(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MovedPermanentlyException.class)
//    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY)
    @ResponseBody
    public ResponseEntity<MessageVo> handleMovedPermanentlyException(HttpServletRequest request, HttpServletResponse response, MovedPermanentlyException ex) {
        log.warn(ErrorLogInfo.build(ex.getSrcClass(), systemName, ex.getErrorCode(), ex.getMessage()).toJson());
        return new ResponseEntity<>(ex.getMessageList(), HttpStatus.MOVED_PERMANENTLY);
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
////    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ResponseEntity<MessageVo> handleValidException(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException ex) {
//        BindingResult result = ex.getBindingResult();
//        BadRequestException brex = new BadRequestException(result, this);
//        return new ResponseEntity<>(brex.getMessageList(), HttpStatus.BAD_REQUEST);
//    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        BadRequestException brex = new BadRequestException(result, this);
        return handleExceptionInternal(ex, brex.getMessageList(), headers, status, request);
    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        log.error(ErrorLogInfo.build(ex, systemName, BaseException.ERR_9999, ex.getMessage()).toJson());
        log.error(ex.getMessage(), ex);
        if(body instanceof MessageVo) {
            MessageVo messages = (MessageVo) body;
            return new ResponseEntity<>(messages, headers, status);
        }
        else {
            MessageVo messages = new MessageVo();
            messages.addMessageObj(BaseException.ERR_9999, ex.getMessage(), "");
            return new ResponseEntity<>(messages, headers, status);
        }

    }

//    @ExceptionHandler(AuthorizationException.class)
////    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
//    @ResponseBody
//    public ResponseEntity<MessageVo> handleAuthorizationException(HttpServletRequest request, HttpServletResponse response, AuthorizationException ex) {
//        log.error(ErrorLogInfo.build(ex, systemName, BaseException.ERR_9997, ex.getMessage()).toJson());
//        log.error(ex.getMessage(), ex);
//        UnauthorizedException uae = new UnauthorizedException(BaseException.ERR_9997, this);
//        return new ResponseEntity<>(uae.getMessageList(), HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<MessageVo> handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error(ErrorLogInfo.build(ex, systemName, BaseException.ERR_9999, ex.getMessage()).toJson());
        log.error(ex.getMessage(), ex);
        MessageVo messages = new MessageVo();
        messages.addMessageObj(BaseException.ERR_9999, ex.getMessage(), "");
        return new ResponseEntity<>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseBody
    public ResponseEntity<MessageVo> handleHystrixRuntimeException(HttpServletRequest request, HttpServletResponse response, HystrixRuntimeException ex) {
        Throwable th = ex.getCause();
        if (th instanceof UnauthorizedException) {
//      		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return handleUnauthorizedException(request, response, (UnauthorizedException)th);
        } else if (th instanceof BadRequestException) {
//      		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return handleBadRequestException(request, response, (BadRequestException)th);
        } else if (th instanceof BusinessException) {
//      		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return handleBusinessException(request, response, (BusinessException)th);
        } else if (th instanceof NotFoundException) {
//      		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return handleNotFoundException(request, response, (NotFoundException)th);
        } else if (th instanceof ForbiddenException) {
//      		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return handleForbiddenException(request, response, (ForbiddenException)th);
        } else if (th instanceof MovedPermanentlyException) {
//      		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            return handleMovedPermanentlyException(request, response, (MovedPermanentlyException)th);
        } else {
//      		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return handleException(request, response, (Exception)th);
        }
    }
}
