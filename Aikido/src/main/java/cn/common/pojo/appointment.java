package cn.common.pojo;

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
public class appointment extends BasePojo{
	@TableId(type = IdType.AUTO)
   private  Integer appoint_id;
   private  Integer  class_id;
   private  String    member;
   
}
