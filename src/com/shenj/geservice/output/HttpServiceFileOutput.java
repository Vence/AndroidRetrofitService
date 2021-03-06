package com.shenj.geservice.output;

import java.util.List;

import com.shenj.gservice.pojo.SJMethod;
import com.shenj.gservice.pojo.SJMethodParam;
import com.shenj.gservice.util.MainUtil;

/**
 * HttpService文件的内容
 * 
 * @author 王文路
 * @date 2015-8-26
 *
 */
public class HttpServiceFileOutput implements IFileOutput{
	
	private boolean isSync;	//是否同步
	private String packageInfo;

	public HttpServiceFileOutput(boolean isSync , String packageInfo) {
		super();
		this.isSync = isSync;
		this.packageInfo = packageInfo;
	}

	@Override
	public String getFileContent(List<SJMethod> methods , String pojoName) {
	
		String fileName = pojoName + "HttpService";
		
		StringBuffer buf = new StringBuffer();
		
		// 包名
		buf.append("package " + this.packageInfo).append(";\r\n\r\n");
		
		// 包引入
		buf.append("import java.util.List;").append("\r\n")
		.append("import retrofit.Callback;").append("\r\n")
		.append("import retrofit.http.DELETE;").append("\r\n")
		.append("import retrofit.http.GET;").append("\r\n")
		.append("import retrofit.http.POST;").append("\r\n")
		.append("import retrofit.http.PUT;").append("\r\n")
		.append("import retrofit.http.Path;").append("\r\n")
		.append("import retrofit.http.Query;").append("\r\n")
		.append("\r\n");
		
		// 接口定义
		buf.append("public interface ").append(fileName).append("{");
		
		// 接口方法定义
		for (SJMethod method : methods) {
			buf.append("\r\n");
			buf.append(getMethodContent(method));
			buf.append("\r\n");
		}
		
		// 闭合
		buf.append("}");
		
		return buf.toString();
	}
	
	public String getMethodContent(SJMethod method){
		
		StringBuffer buf = new StringBuffer();
		
		// 注解 - 方法+ url
		buf.append(MainUtil.INDENTATION);
		buf.append("@").append(method.getRequestMethod()).append("(\"")
		.append(method.getRequestUrl()).append("\")");
		
		buf.append("\r\n");
		
		// 方法定义
		buf.append(MainUtil.INDENTATION).append("public ");
		
		if (this.isSync)
			buf.append(method.getReturnType()).append(" " );
		else 
			buf.append("void ");
		
		buf.append(method.getMethodName()).append("(");
		
		// 方法参数
		for (int i = 0 ; i <  method.getParams().size() ; i++) {
			
			SJMethodParam param = method.getParams().get(i);
			
			buf.append("@").append(param.getQueryType()).append("(\"").append(param.getKeyName())
			.append("\")").append(param.getParamType()) 
			.append(" ").append(param.getValueName());
			
			if (i != method.getParams().size() -1)
				buf.append(",");
			
		}
		
		if (!isSync) {
			
			// 方法参数- 回调(固定格式)
			if (method.getHasReturn())
				buf.append(",Callback<").append(method.getReturnType()).append("> cb");
			else 
				buf.append(",Callback").append(" cb");
		}
		
		buf.append(");");
		
		return buf.toString();
		
	}

}
