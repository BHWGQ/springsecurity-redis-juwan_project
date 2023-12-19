package com.example.demo.Exception;

/**
 * @author: ftt
 * @createDate: 2023/8/22
 */

import com.example.demo.dto.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常处理器
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Result<String> result(MethodArgumentNotValidException e) {
//        log.error(e.getMessage());
//        StringBuilder sb = new StringBuilder();
//        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
//        String message = allErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.joining(";"));
//        return Result.fail(message);
//    }

//    @ExceptionHandler(PortalException.class)
//    public Result<String> portalException(PortalException e) {
//        if (PortalException.USER_NOT_FOUND.equals(e)) {
//            return Result.fail(e.getMessage());
//        } else {
//            return Result.fail();
//        }
//    }
//    @ExceptionHandler({ConstraintViolationException.class})
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<?> bindExceptionHandler(ConstraintViolationException ex) {
//        log.error("", ex);
//        String message = "";
//        //取出@NotEmpty后面message的自定义错误信息
//        Optional<ConstraintViolation<?>> aa = ex.getConstraintViolations().stream().findFirst();
//        if (aa.isPresent()) {
//            message = aa.get().getMessage();
//        }
//        //赋值并抛出
//        Result<?> res = Result.fail(message);
//        return res;
//    }
//
//    /**
//     * 异常处理方法
//     * 处理自定义的业务异常
//     */




    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行时异常：----------------{}", e.getMessage());
        System.out.println("运行时异常：");
        return Result.fail(e.getMessage());
    }



    @ExceptionHandler(CommonException.class)
    public Result<String> exceptionHandler(CommonException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getMessage());
    }
}
