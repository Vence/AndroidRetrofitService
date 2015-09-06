package com.shenj.teworksandroid.http.common.retrofit;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 自定义json转换类
 * 
 * @author 王文路
 * @date 2015-8-3
 *
 */
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
