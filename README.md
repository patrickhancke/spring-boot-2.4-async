# Spring Boot 2.4 project with Async custom setup

After startup, trigger the long running job asynchronously by issuing a ```GET``` request
on ```http://localhost:8080/job```.

It will show logging similar to

`2021-12-30 10:28:42.399  INFO 29072 --- [nio-8080-exec-1] b.p.springboot24async.MyController       : triggering long running job 2021-12-30 10:28:42.402  INFO 29072 --- [nio-8080-exec-1] b.p.springboot24async.MyController       : job launched in the background 2021-12-30 10:28:42.407  INFO 29072 --- [     my-prefix1] b.p.springboot24async.AsyncService       : starting long running job 2021-12-30 10:28:42.419  INFO 29072 --- [nio-8080-exec-1] b.p.springboot24async.AsyncConfig        : custom interceptor (after completion): request=org.apache.catalina.connector.RequestFacade@2596ee1b 2021-12-30 10:28:42.479  INFO 29072 --- [nio-8080-exec-2] b.p.springboot24async.AsyncConfig        : custom interceptor (after completion): request=org.apache.catalina.connector.RequestFacade@2596ee1b 2021-12-30 10:28:42.542  INFO 29072 --- [nio-8080-exec-2] b.p.springboot24async.AsyncConfig        : custom interceptor (after completion): request=org.apache.catalina.core.ApplicationHttpRequest@202d1b9e 2021-12-30 10:28:52.417  INFO 29072 --- [     my-prefix1] b.p.springboot24async.AsyncService       : finished long running job`