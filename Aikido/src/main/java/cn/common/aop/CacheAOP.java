package cn.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import cn.common.annotation.CacheFind;
import cn.common.util.ObjectMapperUtil;
import redis.clients.jedis.JedisCluster;

@Component	
@Aspect		
public class CacheAOP {


	

	//@Autowired
	//private Jedis jedis;		
	//@Autowired					
	//private ShardedJedis jedis;
	
	//@Autowired //
	//@Qualifier("sentinelJedis") 
	//private Jedis jedis;       
	
	@Autowired
	private JedisCluster jedis;
	

	@Around("@annotation(cacheFind)")
	public Object around(ProceedingJoinPoint joinPoint,CacheFind cacheFind) {
		
		String key = getKey(joinPoint,cacheFind);
		String value = jedis.get(key);
		Object object = null;
		try {
			if(StringUtils.isEmpty(value)) {
			
				object = joinPoint.proceed();
				
				String json = ObjectMapperUtil.toJSON(object);
			
				if(cacheFind.secondes()>0) {
					int seconds = cacheFind.secondes();
					jedis.setex(key,seconds,json);
				}else {
					
					jedis.set(key, json);
				}
				System.out.println("AOP查询数据库!!!!!");
			}else {
				
				Class<?> targetClass = getReturnType(joinPoint);
				object = ObjectMapperUtil.toObj(value, targetClass);
				System.out.println("AOP查询缓存!!!!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
		//jedis.close();
		return object;
	}

	
	private Class<?> getReturnType(ProceedingJoinPoint joinPoint) {
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		return methodSignature.getReturnType();
	}

	
	private String getKey(ProceedingJoinPoint joinPoint, CacheFind cacheFind) {
		
		String key = cacheFind.key();
		if(StringUtils.isEmpty(key)) {
			
			String className = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			Object arg0 = joinPoint.getArgs()[0];
			key = className+"."+methodName+"::"+arg0;
		}
		return key;
	}



	/*@Pointcut("execution(* com.jt.service..*.*(..))")
	public void pointCut() {

	}	*/

	
	//@Before("pointCut()")
	/*@Before("execution(* com.jt.service..*.*(..))")
	public void before(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		System.out.println(methodName);
	}*/
}
