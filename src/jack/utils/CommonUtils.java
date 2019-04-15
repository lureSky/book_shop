package jack.utils;

import java.util.UUID;

import org.junit.Test;

public class CommonUtils {

	static public String getUUID() {
		return UUID.randomUUID().toString();
	}
	
/*	@Test
	public void commonTest() {
		String s = getUUID();
		System.out.println(s);
	}*/
}
