package yi.tian.spring.bean.life.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import yi.tian.spring.bean.life.LifeApplicationDemo;

/**
 * BeanCycleAware
 *
 * @author yitian
 * @date 2020/4/6 10:59
 */
@Configuration
public class BeanCycleAware implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,ResourceLoaderAware,BeanPostProcessor,InitializingBean {

    private ConfigurableListableBeanFactory beanFactory;

    private volatile int i=1;

    private ApplicationContext applicationContext;

    public BeanCycleAware(){
        System.out.println((i++)+":实例化BeanCycleAware");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println((i++)+":------------BeanNameAware#setBeanName:"+s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println((i++)+":------------BeanFactoryAware#setBeanFactory:"+beanFactory);
    }


    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println((i++)+":------------ApplicationContextAware#setApplicationContext");
    }

    /**
     * Set the ResourceLoader that this object runs in.
     * <p>This might be a ResourcePatternResolver, which can be checked
     * through {@code instanceof ResourcePatternResolver}. See also the
     * {@code ResourcePatternUtils.getResourcePatternResolver} method.
     * <p>Invoked after population of normal bean properties but before an init callback
     * like InitializingBean's {@code afterPropertiesSet} or a custom init-method.
     * Invoked before ApplicationContextAware's {@code setApplicationContext}.
     *
     * @param resourceLoader ResourceLoader object to be used by this object
     * @see ResourcePatternResolver
     * @see ResourcePatternUtils#getResourcePatternResolver
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println((i++)+":------------ResourceLoaderAware#setResourceLoader");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        BeanCycleAware t = applicationContext.getBean(BeanCycleAware.class);
        if(bean.equals(t)){
            System.out.println((i++)+":------------BeanPostProcessor#postProcessBeforeInitialization:"+beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof BeanCycleAware){
            System.out.println((i++)+":------------BeanPostProcessor#postProcessAfterInitialization:"+beanName);
        }
        return bean;
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println((i++)+":------------InitializingBean#afterPropertiesSet");
    }

//    @Bean(initMethod = "initMethod",name = "xxx1")
//    public BeanCycleAware beanCycleAware(){
//        System.out.println((i++)+":------------:beanCycleAware");
//        return new BeanCycleAware();
//    }


    public void initMethod(){
        System.out.println((i++)+":------------:initMethod");
    }

}
