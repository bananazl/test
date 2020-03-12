package cn.common.pojo;

import java.sql.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@TableName("tb_cart")
@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class schedule extends BasePojo {
	@TableId(type = IdType.AUTO)
	 private  Integer  class_id;
	 private  Date    class_time;
	 private  String  teacher;
	 private  String  content;
	 private  Integer  limited;

}
