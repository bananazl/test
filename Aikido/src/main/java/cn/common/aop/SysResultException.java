package cn.common.aop;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.common.vo.SysResult;



//1.标识全局异常处理机制.
@RestControllerAdvice   //@ControllerAdvice(AOP中�?�知) + @ResponseBody
public class SysResultException {
	
	/**
	 * 如果后台服务器发生运行时异常.则执行异常方法?
	 */
	@ExceptionHandler(RuntimeException.class)
	public SysResult sysResult(Exception exception) {
		exception.printStackTrace(); //输出/log日志保存
		return SysResult.fail();
	}
}
