package cn.rabbitmq.test;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
@Component
public class RegistryBindingBean implements BeanDefinitionRegistryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		//BeanDefinition defaultExchange = beanFactory.getBeanDefinition("defaultExchange");
		//BeanDefinition myFactory = beanFactory.getBeanDefinition("myFactory");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Class<?> cls = Binding.class;
        Class<?> m=BindingFactoryBean.class;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(m);
        GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
        definition.getPropertyValues().add("bindingClass", cls);
        definition.setBeanClass(BindingFactoryBean.class);
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        // 注册bean名,一般为类名首字母小写
        registry.registerBeanDefinition("myBinding", definition);
        
	}
	
/*	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        
        BeanDefinitionBuilder builder1 = BeanDefinitionBuilder.genericBeanDefinition();
        GenericBeanDefinition definition1 = (GenericBeanDefinition) builder1.getRawBeanDefinition();
        definition1.setBeanClass(Queue.class);
        definition1.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        registry.registerBeanDefinition("myQueue", definition1);
	}*/


	
}
