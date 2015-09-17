# ws_proxy
a simple webservice message proxy system

该项目提供对webservice消息的路由功能，因为只是实现高效转发，不需要提供具体服务，所以关键点就是
如何不解析soap消息，而只找到其路由的关键key，然后对其进行转发即可。

这个关键问题的解决是采用cxf的拦截器，

```
public class ProxyInterceptor extends AbstractPhaseInterceptor<Message> {
    public ProxyInterceptor() {
		  super(Phase.RECEIVE);
	  }
}
```

这个拦截器可以通过指定放置在消息接收的各个阶段，如receive、pre-marshal、marshal-ending等


拦截器会调用负载均衡的逻辑，分配对应的服务端，将消息放到对应的消息队列，会有对应的发送线程实时扫描消息队列

发送消息

