# 开发中常用的类库

## FastJson(不推荐)

https://github.com/alibaba/fastjson

## JackSon

https://github.com/FasterXML/jackson

常用注解:

- @JsonProperty("xxx"): 将当前的属性名在json字符串中重新命名为当前设置的这个值，比如在示例中，将age-->mAge
- @JsonIgnore: 将被标注的属性在生成json字符串的时候，直接忽略
- @JsonInclude: 是一个类级别的设置，JsonInclude.Include.NON_EMPTY标识只有非NULL的值才会被纳入json
  string之中，其余的都被忽略，比如这里的location属性，并没有出现在最终的结果字符串中。
- @JsonSerialize: 使用自定义的类来实现自定义的字段转换。写入操作。
- @JsonDeserialize: 解析的时候，自定义的转换器；读取操作。
- @JsonAutoDetect: 设置类的访问策略，是否所有的属性都可以，还是按照一定的方式来提取。
- @JsonRawValue: 无转换的将属性值写入到json 字符串中。 写入操作 @JsonValue: 标注方法，用以替代缺省的方法，由该方法来完成json的字符输出。

## GSON

