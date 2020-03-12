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
public class payment {
	@TableId(type = IdType.AUTO)
	private Integer pay_id;
	private Integer id;
	private Integer amount;
	private Date    paid_date;
	private Integer member_id;
}
