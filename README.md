# AndroidRetrofitService
Android端使用Retrofit实现与RestAPI后台进行数据交互

##前言
该项目主要实现Android移动端与RestAPI后台的交互过程

**方案一**：可以使用HTTPClient自己去实现，不过复杂度和情况需要逐一去考虑，我写过一个简单的demo:

> [https://github.com/Vence/AndroidAPIService](https://github.com/Vence/AndroidAPIService)

**方案二**：这里主要介绍使用类库Retrofit框架去实现RestAPI交互过程。

##关于Retrofit
关于这个类库，这里不多介绍，请参见
> [http://square.github.io/retrofit/](http://square.github.io/retrofit/)

官网上的一句概括Retrofit用途的话，这里引用过来
> ###A type-safe HTTP client for Android and Java

##使用过程

- RestAdapter使用单例模式

		public static RestAdapter  getInstance (){
		
			if(instance == null) {
				synchronized (RestAdapterManager.class) {
					if (instance == null)
						
						gson = new GsonBuilder()
							.registerTypeAdapterFactory(new ItemTypeAdapterFactory())
							.create();
						
						instance  = new RestAdapter.Builder()
							.setEndpoint(HttpAPIUtil.getInstance().getEndpoint())
							.setConverter(new GsonConverter(gson))
							.build();
				}
			}
			
			return instance;
			 
		}

- 声明接口和实现

LoginHttpService

	@POST("/api/{domainid}/loginnocaptcha")
	public void login(@Path("domainid")String domainID , @QueryMap Map<String , String> options , Callback<String> cb);

LoginService

	public void login(String domainID , String userName , String password , Callback<String> cb);
	
LoginServiceImpl

	private LoginHttpService loginHttpService = 
		RestAdapterManager.getInstance().create(LoginHttpService.class);

LoginHttpService是给Retrofit框架使用的，内部用**动态代理**实现了这个接口的具体实现，所以这里不需要去写LoginHttpService的实现。(有关动态代理知识，请参见[http://vence.gitcafe.io/blog/2015/08/04/java-proxy-info/](http://vence.gitcafe.io/blog/2015/08/04/java-proxy-info/))

在LoginServiceImpl 中调用了RestAdapter去创建LoginHttpService的一个实现


- 另外需要说明的一点是，Retrofit提供了自定义的json解析，restApi返回的格式不同，这里的解析也会有所差异

比如我的restApi返回数据的格式是

	{errorCode: 0 , errorMsg: null, result: [...]}

所以这里只有result是对我们有用的数据，这里解析如下：

	public class ItemTypeAdapterFactory implements TypeAdapterFactory {

		public <T> TypeAdapter<T> create(Gson gson , final TypeToken<T> type){
			
			final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
			final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
			
			
			return new TypeAdapter<T>(){
	
				@Override
				public T read(JsonReader in) throws IOException {
					
					JsonElement jsonElement = elementAdapter.read(in);
					
					if (jsonElement.isJsonObject()) {
						JsonObject jsonObject = jsonElement.getAsJsonObject();
						
						if (jsonObject.has("errorCode")
								&& jsonObject.get("errorCode").getAsInt() != 0) {
						
							throw new IllegalArgumentException(jsonObject.get("errorMsg").getAsString());
					
						} 
						if (jsonObject.has("result")) {
							
							return delegate.fromJsonTree(jsonObject.get("result"));
							
						}
							
					}
					
					return delegate.fromJsonTree(jsonElement);
				}
	
				@Override
				public void write(JsonWriter out, T value) throws IOException {
					// TODO Auto-generated method stub
					delegate.write(out, value);
					
				}}.nullSafe();
		}
	}

##其他
具体请参看源码