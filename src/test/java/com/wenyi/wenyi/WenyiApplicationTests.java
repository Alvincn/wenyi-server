package com.wenyi.wenyi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class WenyiApplicationTests {

	@Test
	void contextLoads() {
			int j,cnt=0;
			List<String> list = Arrays.asList("1", "0", "1", "0", "1", "1", "1");
			ArrayList<String> list1 = new ArrayList<>();
			ArrayList<Integer> list2 = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals("1")) {
					list2.add(i);
				}
			}
			for (int i = 0; i < list2.size(); i++) {
				if (i!=0 && i % 2 == 0) {
					for (j = list2.get(i - 1)+1; j < list2.get(i); j++) {
						list1.add(list.get(j));
					}
				}
				list1.add(list.get(list2.get(i)));
				++cnt;
				if ((i + 1) % 2 == 0 && cnt==2 ) {
					for (j = list2.get(i - 1)+1; j < list2.get(i); j++) {
						list1.add(list.get(j));
					}
					cnt = 0;
				}

			}
			if (list2.get(list2.size()-1)!=list.size()-1){
				for (j = list2.get(list2.size()-1)+1; j < list.size(); j++) {
					list1.add(list.get(j));
				}
			}
			System.out.println(list1);

	}

}
