
`spring.datasource.url=jdbc:mysql://**www.test.com:3306****/testdb`
 
想将url改成下面这种方式

`spring.datasource.url=jdbc:mysql://****www.test.com/mysql****/testdb`

> 使用nginx,通过stream模块来代理TCP连接，
> 但是这样只能代理 ip/域名 + 端口号 的tcp请求，不能代理 www.test.com/mysql请求
> 因为 stream 模块内不支持location 
```
stream {
	upstream mysql {
		hash $remote_addr consistent;
		server 127.0.0.1:3306 max_fails=3 fail_timeout=30s;
	}
	server {
		listen 3308;
		proxy_connect_timeout 30s;
		proxy_timeout 600s;
		proxy_pass mysql;
	}
}
```