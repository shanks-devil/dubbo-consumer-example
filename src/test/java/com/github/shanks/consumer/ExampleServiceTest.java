package com.github.shanks.consumer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.shanks.api.ExampleService;
import com.github.shanks.domain.Demo;

@RunWith(SpringJUnit4ClassRunner.class)
@ImportResource(locations = "classpath:remote-consumer.xml")
@SpringApplicationConfiguration(classes = BootApplication.class)
@ActiveProfiles("test")
public class ExampleServiceTest {

	@Reference(version = "1.0.0",loadbalance = "roundrobin")
	private ExampleService exampleService;

	@Test
	public void getOne() {
		List<Runnable> tasks = new ArrayList<Runnable>(100000);
		for (int i = 0; i < 800; i++) {
			tasks.add(new Runnable() {
				@Override
				public void run() {
					Demo demo = exampleService.getOne();
					System.out.println(demo.getTitle());
					System.out.println(demo.getTime());
				}

			});
		}

		try {
			ConcurrencyTestUtil.assertConcurrent("1024tasks", tasks, Integer.MAX_VALUE, 5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
