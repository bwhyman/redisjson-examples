# redis-om-spring-examples
redis7开始原生支持json数据格式，集成在redis stack。   
官方基于spring-data-redis扩展的支持redis-stack的框架。

### RedisJson
启动时扫描@Document，基于全限定性类名命名及属性注解创建索引。  
```shell
"FT.CREATE" "com.example.redisjsonexamples.entity.UserIdx" 
"ON" "JSON" "PREFIX" "1" "com.example.redisjsonexamples.entity.User:" 
"SCHEMA" "$.name" "AS" "name" "TAG" "SEPARATOR" "|" "$.address" "AS" "address" "TAG" "SEPARATOR" "|"
```

### save() Execute Processing
执行save()，先查询key为"com.example.redisjsonexamples.entity.User"的Set类型集合中是否存在指定ID(指定类型的ID集合)，有则移除  
```shell
"SISMEMBER" "com.example.redisjsonexamples.entity.User" "01GGQBZJDCY1AKJH7YF56GB73E"
```
查询key为"com.example.redisjsonexamples.entity.User:ID"的JSON类型文档中是否存在指定ID的json数据，有则移除。  
即以类型+ID创建一个独立的json文档，而不是统一放在一个类似Set里  
```shell
"EXISTS" "com.example.redisjsonexamples.entity.User:01GGQBZJDCY1AKJH7YF56GB73E"
```
在指定json文档的指定路径保存json数据  
```shell
JSON.SET" "com.example.redisjsonexamples.entity.User:01GGQBZJDCY1AKJH7YF56GB73E" "." 
"{\"id\":\"01GGQBZJDCY1AKJH7YF56GB73E\",\"name\":\"BO\",\"insertTime\":1667231631610}"
```
最后，在Set集合，加入生成的主键

### RedisSearch
RedisSearch 是支持搜索的底层搜索引擎，
支持 Text全文搜索，支持模糊检索；Tag精确匹配搜索，不支持模糊检索；Numeric围查询；Geo数值范围查询；Vector向量相似性查询。

ft.search目前不支持类似SQL的isnull()/len()等函数，无法实现检索属性不存在记录(官方反馈回复)。  

### @Indexed & @Searchable
@Indexed，为属性添加搜索字段，基于属性数据类型自动映射为redissearch搜索类型。  
@Searchable，声明为全文搜索字段。

### @Aggregation
聚合根的检索写法很麻烦。  
value属性，不为空时，不自动追加*  

### @Query
不支持自定义搜索语句的写法，很不灵活。  

### JSONOperations
较灵活的自定义操作接口，但需了解原生redissearch语句，ID等需手动拼接。

### ULID
snowflake算法较强基于时间，如果服务器时间重置可能产生主键冲突。  
ULID，分布式主键生成算法。  
48位时间+80位随机数；按过滤了特殊字符的base32编码；按可排序顺序，生成26个字符；不区分大小写；

### entityStream

```java
entityStream.of(User.class)
                .filter(new TextTagField<User, String>(User.class.getDeclaredField("name"), true)
                        .eq("BO"))
                .collect(Collectors.toList())
                .forEach(user -> {
                    log.debug(user.getId());
                });
```
### Others
redis-om-spring基于Jedis而非Lettuce，不知未来如何扩展出异步非阻塞模式，再单写一个？  
许多功能还没有，有些功能原生redissearch也都没有。