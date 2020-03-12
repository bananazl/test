package cn.common.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult implements Serializable{
	
	private static final long serialVersionUID = 7004985513093683827L;
	private Integer status;		
	private String msg;			
	private Object data;		
	
	
	public static SysResult success() {
		
		return new SysResult(200,"调用成功!", null);
	}
	
	
	public static SysResult success(Object data) {
		
		return new SysResult(200,"调用成功!",data);
	}
	
	
	public static SysResult success(String msg,Object data) {
		
		return new SysResult(200,msg, data);
	}
	
	
	public static SysResult fail() {
		
		return new SysResult(201,"业务调用失败",null);
	}
	
}






