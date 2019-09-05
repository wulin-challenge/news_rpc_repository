import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.wulin.brace.core.utils.SerializeUtil;


/**
 * 测试内部类是否支持序列化,结果是ArrayList.SubList内部类是不支持序列化的
 * @author wulin
 *
 */
public class TestSerializeUtil {
	
	public static void main(String[] args) {
		new TestSerializeUtil().testSeri();
	}
	
	public void testSeri() {
		
		List<String> list = new ArrayList<>(Arrays.asList("123","456","678"));
		
		List<String> subList = list.subList(0, 1);
		
//		String s = "hessian";
		String s = "kryo";
		
		byte[] data = SerializeUtil.serialize(subList, s);
		ArrayList deserialize = SerializeUtil.deserialize(data, ArrayList.class, s);
		
		System.out.println();
	}

}
