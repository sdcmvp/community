package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

//因为正式运行程序是CommunityApplication这个配置类，那么测试的代码中呢也是想用这个配置类，一运行就使用这个类，
// 那怎么在测试代码中引用这个类呢？加下面的注解
@ContextConfiguration(classes = CommunityApplication.class)

//Spring容器是自动创建的，那么我们怎么获得这个容器呢？通过实现一个接口ApplicationContextAware
//ApplicationContext 继承HierarchicalBeanFactory， HierarchicalBeanFactory继承BeanFactory。BeanFactory相当于Spring顶层接口
//ApplicationContext是子接口，他比父接口要扩张更多的方法，他的功能更强一点，通常我们使用ApplicationContext
/*
如果一个类实现了ApplicationContextAware接口，实现了setApplicationContext方法，那么Spring容器就会检测到，在扫描组件的时候检测到这样的
bean，然后调用set方法，把自身，即容器传进去
 */
public class CommunityApplicationTests implements ApplicationContextAware {

	//加一个成员变量来记录Spring容器，暂存（引用）一下这个容器，然后后面就可以使用他了
	private ApplicationContext applicationContext;

	//传入一个参数ApplicationContext，这个参数就相当于Spring容器
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);


		//从application中获取自动装配的bean，调取获取bean的方法，可以通过名字获取也可以通过类型获取，通常为类型
		//这个bean类型为AlphaDaoHibernateImpl，当然可以调用接口类型AlphaDao
		//从容器中获取AlphaDao类型的bean
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement() {
		//通过容器获得Service
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}
}
