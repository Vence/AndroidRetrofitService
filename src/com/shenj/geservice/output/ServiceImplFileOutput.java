package com.shenj.geservice.output;

import java.util.List;

import com.shenj.gservice.pojo.SJMethod;
import com.shenj.gservice.pojo.SJMethodParam;
import com.shenj.gservice.util.MainUtil;

/**
 * Service实现文件的内容
 * 
 * @author 王文路
 * @date 2015-8-26
 *
 */
public class ServiceImplFileOutput implements IFileOutput{
	
	private boolean isSync;	//是否同步
	private String packageInfo;
	
	public ServiceImplFileOutput(boolean isSync, String packageInfo) {
		super();
		this.isSync = isSync;
		this.packageInfo = packageInfo;
	}

	@Override
	public String getFileContent(List<SJMethod> methods, String pojoName) {
		
		// 该类中使用到的类或变量定义
		String fileName = pojoName + "ServiceImpl";
		String service = pojoName + "Service";
		String serviceVariable  = pojoName.toLowerCase() + "Service";
		String httpService = pojoName + "HttpService";
		String httpServiceserviceVariable = pojoName.toLowerCase() + "HttpService";
		
		StringBuffer buf = new StringBuffer();
		
		// 包名
		buf.append("package " + packageInfo).append(";\r\n\r\n");
		
		// 包引入
		buf.append("import java.util.List;").append("\r\n")
		.append("import retrofit.Callback;").append("\r\n")
		.append("\r\n");
		
		// 定义类  实现接口
		buf.append("public class ").append(fileName).append(" implements ").append(service).append("{\r\n");
		
		// 定义HttpService变量
		buf.append(MainUtil.INDENTATION).append("private")
		.append(" ").append(httpService).append(" ").append(httpServiceserviceVariable).append(";\r\n");
		
		buf.append("\r\n");
		
		// 构造函数
		buf.append(MainUtil.INDENTATION).append("public ").append(fileName).append(" () {")
		.append("\r\n")
		.append(MainUtil.INDENTATION).append(MainUtil.INDENTATION).append("super();").append("\r\n")
		.append(MainUtil.INDENTATION).append(MainUtil.INDENTATION)
		.append(httpServiceserviceVariable).append(" = RestAdapterManager.getInstance().create( ").append(httpService)
		.append(".class);").append("\r\n");
		buf.append(MainUtil.INDENTATION).append("}");
		
		// 方法实现
		for (SJMethod method : methods) {
			buf.append("\r\n");
			buf.append(getMethodContent(method , pojoName));
			buf.append("\r\n");
		}
		
		// 类闭合
		buf.append("}");
		
		return buf.toString();
	}

	public String getMethodContent(SJMethod method , String pojoName) {
		
		String httpService = pojoName.toLowerCase() + "HttpService";
		
		StringBuffer buf = new StringBuffer();

		// 注解 - 接口的实现Override
		buf.append(MainUtil.INDENTATION).append("@Override").append("\r\n");
		
		// 定义方法
		buf.append(MainUtil.INDENTATION).append("public ");
		
		if(this.isSync) {
			buf.append(method.getReturnType()).append(" " );
		} else {
			buf.append("void ");
		}
		
		buf.append(method.getMethodName()).append("(");
		
		// 方法的参数
		for (int i = 0 ; i <  method.getParams().size(); i ++) {
			SJMethodParam param = method.getParams().get(i);
			
			buf.append(param.getParamType()).append(" ").append(param.getValueName());
			if (i != method.getParams().size()-1)
				buf.append(",");
		}
		
		if (!this.isSync) {
			// 方法的参数 - 最后一个固定为回调
			if (method.getHasReturn())
				buf.append(",Callback<").append(method.getReturnType()).append("> cb");
			else 
				buf.append(",Callback").append(" cb");
		}
		
		buf.append("){").append("\r\n");
		
		// 方法体
		buf.append(MainUtil.INDENTATION).append(MainUtil.INDENTATION);
		
		if (this.isSync && !method.getReturnType().equals("void")) {
			buf.append("return ");
		}
		
		buf.append(httpService).append(".").append(method.getMethodName())
		.append("(");
		
		// 方法体 - 调用的方法的参数
		for (int i = 0 ; i < method.getParams().size() ; i++) {
			
			SJMethodParam param  = method.getParams().get(i);
			
			buf.append(param.getValueName());
			
			if (i != method.getParams().size() - 1)
				buf.append(",");
		}
		
		if (!this.isSync) {
			buf.append(",cb");
		}
		
		buf.append(");").append("\r\n");
		
		//方法闭合
		buf.append(MainUtil.INDENTATION).append("}");
		
		return buf.toString();
		
	}

}
