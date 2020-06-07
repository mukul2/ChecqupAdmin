package com.chequp.admin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceNameRate {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("service_id")
@Expose
private Integer serviceId;
@SerializedName("service_name")
@Expose
private String serviceName;
@SerializedName("rate")
@Expose
private Integer rate;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getServiceId() {
return serviceId;
}

public void setServiceId(Integer serviceId) {
this.serviceId = serviceId;
}

public String getServiceName() {
return serviceName;
}

public void setServiceName(String serviceName) {
this.serviceName = serviceName;
}

public Integer getRate() {
return rate;
}

public void setRate(Integer rate) {
this.rate = rate;
}

}