package com.BlogApplication.Exception;

public class ResourceNotFoundExceptionNew extends RuntimeException{
	String resourceName;
	String fieldName;
	String name;
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ResourceNotFoundExceptionNew(String resourceName,String fieldName,String name) {
	    super(String.format("%s not found with %s :%d",resourceName,fieldName,name));
	    this.resourceName=resourceName;
	    this.fieldName=fieldName;
	    this.name=name;
	}

}
